package triangular_storage;

import java.util.stream.Stream;

@FunctionalInterface
public interface TailCall<T> {
    TailCall<T> next();

    default boolean isComplete() {
        return false;
    }

    default T result() {
        throw new Error("Not implemented");
    }

    /** Iterates through the pending recursive calls decrementing n and accumulating the resulting value
     * @return The resulting accumulated value stored in the last recursive call
     */
    default T execute() {
        return Stream.iterate(this, TailCall::next)
                .filter(TailCall::isComplete)
                .findFirst()
                .get()
                .result();
    }

    /** Returns an instance of TailCall that indicates the recursion is complete
     * @param value The resulting accumulated value of the recursive calls
     * @return An instance of tail call that indicates the recursion is complete
     *      and containing the resulting accumulated value of the recursive calls
     */
    static <T> TailCall<T> complete(final T value) {
        return new TailCall<>() {
            @Override
            public TailCall<T> next() {
                throw new Error("not implemented.");
            }

            @Override
            public boolean isComplete() {
                return true;
            }

            @Override
            public T result() {
                return value;
            }
        };
    }
}

