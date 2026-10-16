class Solution {
    static final long MOD = 1_000_000_007L;
    long[] sum, sq, lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            update(1, 0, n - 1, last[x] + 1, i);
            last[x] = i;
            ans = (ans + sq[1]) % MOD;
        }

        return (int) ans;
    }

    void apply(int p, int l, int r, long v) {
        long len = r - l + 1L;
        sq[p] = (sq[p] + 2 * v % MOD * sum[p] % MOD
                + len % MOD * v % MOD * v % MOD) % MOD;
        sum[p] = (sum[p] + len % MOD * v) % MOD;
        lazy[p] = (lazy[p] + v) % MOD;
    }

    void push(int p, int l, int r) {
        if (lazy[p] == 0 || l == r) return;
        int m = (l + r) >>> 1;
        long v = lazy[p];
        apply(p << 1, l, m, v);
        apply(p << 1 | 1, m + 1, r, v);
        lazy[p] = 0;
    }

    void update(int p, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return;

        if (ql <= l && r <= qr) {
            apply(p, l, r, 1);
            return;
        }

        push(p, l, r);
        int m = (l + r) >>> 1;
        update(p << 1, l, m, ql, qr);
        update(p << 1 | 1, m + 1, r, ql, qr);

        sum[p] = (sum[p << 1] + sum[p << 1 | 1]) % MOD;
        sq[p] = (sq[p << 1] + sq[p << 1 | 1]) % MOD;
    }
}
