public class App {

    public static void main(String[] args) {
        GeometricShape square = new Square(5);
        GeometricShape rectangle = new Rectangle(4, 6);
        GeometricShape circle = new Circle(3);

        System.out.printf("Área do quadrado: %.2f%n", square.calculateArea());
        System.out.printf("Área do retângulo: %.2f%n", rectangle.calculateArea());
        System.out.printf("Área do círculo: %.2f%n", circle.calculateArea());
    }
}
