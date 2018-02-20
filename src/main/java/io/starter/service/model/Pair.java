package io.starter.service.model;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 14.02.2018
 */
public class Pair<L, R> {

    private final L left;
    private final R right;

    private Pair(L l, R r) {
        this.left = l;
        this.right = r;
    }

    public static <L, R> Pair<L,R> of(L l, R r) {
        return new Pair<>(l, r);
    }

    public boolean haveLeft() {
        return (left != null);
    }

    public boolean haveRight() {
        return (right != null);
    }

    public boolean haveBoth() {
        return haveLeft() && haveRight();
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (left != null ? !left.equals(pair.left) : pair.left != null) return false;
        return right != null ? right.equals(pair.right) : pair.right == null;
    }

    @Override
    public int hashCode() {
        int result = left != null ? left.hashCode() : 0;
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }
}
