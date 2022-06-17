package ExamStorage;

public class HardProduct extends Product {

    private int productWeight;

    public HardProduct(ProductType productType, String name, int boxVolume, int productWeight) {
        super(productType, name, boxVolume);
        this.productWeight = productWeight;
    }

    public int getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(int productWeight) {
        this.productWeight = productWeight;
    }

    @Override
    public String toString() {
        return "Название: " + super.getName()
                + " | тип продукта: " + super.getProductType().getDesc()
                + " | количество коробок: " + super.getBoxVolume()
                + " | масса единицы: " + productWeight;
    }
}
