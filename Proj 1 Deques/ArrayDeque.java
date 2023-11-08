public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private int minusOne(int index) {
        int i = index - 1;
        if (i < 0) {
            i = items.length - 1;
        }
        return i;
    }

    private int plusOne(int index) {
        int i = index + 1;
        if (i == items.length) {
            i = 0;
        }
        return i;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int begin = plusOne(nextFirst);
        int end = minusOne(nextLast);
        if (begin <= end) {
            System.arraycopy(items, begin, a, 0, size);
        } else {
            int size0 = items.length - begin;
            int size1 = size - size0;
            System.arraycopy(items, begin, a, 0, size0);
            System.arraycopy(items, 0, a, size0, size1);
        }
        nextFirst = a.length - 1;
        nextLast = size;
        items = a;
    }

    @Override
    public void addFirst(T item) {
        if (size + 1 > items.length) {
            this.resize(size * 4);
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst = minusOne(nextFirst);
    }

    @Override
    public void addLast(T item) {
        if (size + 1 > items.length) {
            this.resize(size * 4);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (size == 0) {
            System.out.println();
            return;
        }
        int firstPos = plusOne(nextFirst);
        String a = "";
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                a += items[firstPos] + "";
            } else {
                a += items[firstPos] + "" + " ";
            }
            firstPos = plusOne(firstPos);
        }
        System.out.println(a);
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if ((float) (size - 1) / items.length < 0.25 && items.length >= 16) {
            this.resize(items.length / 2);
        }
        nextFirst = plusOne(nextFirst);
        size--;
        T a = items[nextFirst];
        items[nextFirst] = null;
        return a;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if ((float) (size - 1) / items.length < 0.25 && items.length >= 16) {
            this.resize(items.length / 2);
        }
        nextLast = minusOne(nextLast);
        size--;
        T a = items[nextLast];
        items[nextLast] = null;
        return a;
    }

    @Override
    public T get(int index) {
        int begin = plusOne(nextFirst);
        if (index > size - 1 || index < 0) {
            return null;
        }
        return items[(begin + index) % items.length];
    }
}
