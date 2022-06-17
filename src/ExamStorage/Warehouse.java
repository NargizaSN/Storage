package ExamStorage;

import ExamStorage.Product;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private int warehouseId;
    private int volume;
    private String warehouseName;
    private List<Product> products = new ArrayList<>();

    public Warehouse() {
    }

    public Warehouse(int warehouseId, int volume, String name) {
        this.warehouseId = warehouseId;
        this.volume = volume;
        this.warehouseName = name;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public String getWarehouseName() {
        return this.warehouseName;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getWarehouseVolume() {
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

    public int getProductsVolume() {
        int result = 0;
        for (Product p : products) {
            result += p.getBoxVolume();
        }
        return result;
    }

    public int getFreeSpace() {
        return this.getWarehouseVolume() - this.getProductsVolume();
    }

    @Override
    public String toString() {
        return "Склад №" + warehouseId + ':' +
                "\n\t Название склада: " + warehouseName +
                "\n\t Начальный объем: " + volume +
                "\n\t Доступный объем: " + getFreeSpace() +
                '\n';
    }
}