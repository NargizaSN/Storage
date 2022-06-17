package ExamStorage;

public class Product {

    private ProductType productType;
    private String name;
    private int boxVolume;

    public Product() {
    }

    public Product(ProductType productType, String name, int boxVolume) {
        this.productType = productType;
        this.name = name;
        this.boxVolume = boxVolume;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBoxVolume() {
        return boxVolume;
    }

    public void setBoxVolume(int boxVolume) {
        this.boxVolume = boxVolume;
    }
}