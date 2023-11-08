public class LinkedListDeque<T> implements Deque<T> {

    private DLNode sentinel;
    private int size;
    public LinkedListDeque() {
        sentinel = new DLNode<>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        DLNode p = new DLNode(item, sentinel.next, sentinel);
        if (sentinel.prev == sentinel) {
            sentinel.prev = p;
        }
        sentinel.next.prev = p;
        sentinel.next = p;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        DLNode p = new DLNode(item, sentinel, sentinel.prev);
        if (sentinel.next == sentinel) {
            sentinel.next = p;
        }
        sentinel.prev.next = p;
        sentinel.prev = p;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        DLNode l = sentinel.next;
        String bout = "";
        if (size == 0) {
            System.out.println();
            return;
        }
        for (int i = size; i > 0; i--) {
            if (i == 1) {
                bout += l.toString();
            } else {
                bout += l.toString() + " ";
            }
            l = l.next;
        }
        System.out.println(bout);
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T p = (T) sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return p;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T p = (T) sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return p;
    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        DLNode l = sentinel.next;
        if (index == 0) {
            return (T) l.item;
        }
        for (int i = index; i > 0; i--) {
            l = l.next;
        }
        T p = (T) l.item;
        return p;
    }

    public T getRecursive(int index) {
        if (index > size - 1 | index < 0) {
            return null;
        }
        DLNode l = sentinel.next;
        return (T) l.reCursive(index);
    }

    private static class DLNode<T> {
        /*
         * The access modifiers inside a private nested class are irrelevant:
         * both the inner class and the outer class can access these instance
         * variables and methods.
         */
        private T item;
        private DLNode next;
        private DLNode prev;

        DLNode(T item, DLNode next, DLNode prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        public T reCursive(int index) {
            if (index == 0) {
                return item;
            }
            return (T) (this.next).reCursive(index - 1);
        }

        @Override
        public String toString() {
            return item + "";
        }
    }
}
