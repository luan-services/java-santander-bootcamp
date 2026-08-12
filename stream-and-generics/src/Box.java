public class Box<T> {
    /* T is a type parameter chosen when a Box object is declared */
    private T value;

    public Box(T value) {
        this.value = value;
    }

    /* the method returns the same type selected for this Box */
    public T getValue() {
        return value;
    }

    /* the method accepts only the type selected for this Box */
    public void setValue(T value) {
        this.value = value;
    }
}
