package triangular_storage;

public class TriangularStorage {
    private final long ACC_SEED = 1;
    private final int ADD_SEED = 2;

    public TailCall<Long> getNthTriangular(int n) {
        return getNthTriangular(n, ACC_SEED, ADD_SEED);
    }

    /** Gets the nth value in a triangular sequence starting
     * @param n Which value in the sequence to return
     * @param acc Sum of the added values
     * @param add The value to add to accumulated for the iteration
     * @return An instance of TailCall (functional interface) that will decrement n and increment the add value
     */
    public TailCall<Long> getNthTriangular(int n, long acc, int add) {
        return (n == 1)
            ? TailCall.complete(acc)
            : () -> getNthTriangular(n - 1, acc + add, add + 1);
    }

    /** Returns the id of the box at column x and row y
     * @param x Column of the box starting from the left
     * @param y Row of the box starting from the bottom
     * @return String representation of the id of item at (x, y)
     */
    public String answer(int x, int y) {
        var xBase = getNthTriangular(x, 1, 2).execute();
        return getNthTriangular(y, xBase, x).execute().toString();
    }
}
