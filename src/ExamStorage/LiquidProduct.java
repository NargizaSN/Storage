package ExamStorage;

public class LiquidProduct extends Product {


    private int productVolume;

    public LiquidProduct(ProductType productType, String name, int boxVolume, int productVolume) {
        super(productType, name, boxVolume);
        this.productVolume = productVolume;
    }

    public int getProductVolume() {
        return productVolume;
    }

    public void setProductVolume(int productVolume) {
        this.productVolume = productVolume;
    }

}
