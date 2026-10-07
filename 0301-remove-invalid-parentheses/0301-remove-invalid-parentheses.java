import java.util.*;

class Solution {

    public List<String> removeInvalidParentheses(String s) {

        List<String> ans = new ArrayList<>();

        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        q.offer(s);
        visited.add(s);

        boolean found = false;

        while (!q.isEmpty()) {

            int size = q.size();

            for (int k = 0; k < size; k++) {

                String curr = q.poll();

                if (isValid(curr)) {
                    ans.add(curr);
                    found = true;
                }

                // Agar current level par valid mil gaya,
                // next level par jaane ki zarurat nahi
                if (found) {
                    continue;
                }

                // Ek-ek character remove karo
                for (int i = 0; i < curr.length(); i++) {

                    // Letters ko remove karne ki zarurat nahi
                    if (curr.charAt(i) != '(' &&
                        curr.charAt(i) != ')') {
                        continue;
                    }

                    String next = curr.substring(0, i)
                            + curr.substring(i + 1);

                    if (!visited.contains(next)) {
                        visited.add(next);
                        q.offer(next);
                    }
                }
            }

            // Minimum removals wali level mil chuki hai
            if (found) {
                break;
            }
        }

        return ans;
    }

    // Check whether parentheses are valid
    private boolean isValid(String s) {

        int balance = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                balance++;
            }
            else if (c == ')') {
                balance--;

                // Closing bracket without opening bracket
                if (balance < 0) {
                    return false;
                }
            }
        }

        // All opening brackets must be closed
        return balance == 0;
    }
}
