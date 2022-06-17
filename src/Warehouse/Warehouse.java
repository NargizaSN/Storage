package ExamStorage;

import ExamStorage.Product;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private int warehouseId;
    private int volume;
    private List<Product> products = new ArrayList<>();

    public Warehouse() {
    }

    public Warehouse(int warehouseId, int volume) {
        this.warehouseId = warehouseId;
        this.volume = volume;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(int index) {
        this.products.remove(index);
    }

    public int getProductsVolume() {
        int result = 0;
        for (Product p : products) {
            result += p.getBoxVolume();
        }
        return result;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getFreeSpace() {
        return this.getVolume() - this.getProductsVolume();
    }

    @Override
    public String toString() {
        return "Склад номер: " + warehouseId +
                ", общий объем: " + volume + ", свободный объем: " + this.getFreeSpace();
    }
}