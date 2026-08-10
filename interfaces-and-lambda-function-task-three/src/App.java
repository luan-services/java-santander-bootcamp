public class App {

    public static void main(String[] args) {
        Taxable food = new Food(100.00);
        Taxable health = new HealthAndWellness(100.00);
        Taxable clothing = new Clothing(100.00);
        Taxable culture = new Culture(100.00);

        System.out.printf("Imposto de Alimentação: R$ %.2f%n", food.calculateTax());
        System.out.printf("Imposto de Saúde e bem-estar: R$ %.2f%n", health.calculateTax());
        System.out.printf("Imposto de Vestuário: R$ %.2f%n", clothing.calculateTax());
        System.out.printf("Imposto de Cultura: R$ %.2f%n", culture.calculateTax());
    }
}
