public class Node<T extends Number> implements Comparable<Node>{
    private T value;
    private Node<T> left;
    private Node<T> right;

    public Node(T value){
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    @Override
    public int compareTo(Node node) {
        return this.value.intValue() - node.getValue().intValue();
    }
}
