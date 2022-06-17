package ExamStorage;

import ExamStorage.HardProduct;
import ExamStorage.LiquidProduct;
import ExamStorage.Product;
import ExamStorage.ProductType;
import ExamStorage.Warehouse;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    private static List<Warehouse> warehouseList = new ArrayList<>();

    private static int warehouseId;

    public static void main(String[] args) {

        while (true) {
            try {
                int mainMenuItem;

                Descriptions.mainDesc();

                mainMenuItem = in.nextInt();

                switch (mainMenuItem) {
                    case 1 ->
                            // Метод для работы с товарами
                            workWithProducts();
                    case 2 ->
                            // Метод для работы со складами
                            workWithStorages();
                    case 0 -> {
                        System.out.println("-------------------------------------------------------------");
                        System.out.println("Выход из программы...");
                        return;
                    }
                    default -> {
                        System.out.println("-------------------------------------------------------------");
                        System.out.println("Ошибка. Введите цифру из меню!!!");
                    }
                }
            } catch (InputMismatchException ex) {
                System.out.println("Ошибка. Допустим ввод только числовых значений!!!");
                in.nextLine();
            }
        }
    }

    private static void workWithProducts() {
        while (true) {

            try {
                int mainMenuItem;
                Descriptions.ProductsDesc();

                mainMenuItem = in.nextInt();

                switch (mainMenuItem) {
                    case 1 -> {
                        System.out.println("Добавление товара на склад");
                        addProduct();
                    }
                    case 2 -> {
                        System.out.println("Добавление группы товаров на склад");
                        addProductsList();
                    }
                    case 3 -> {
                        System.out.println("Удаление товара со склада");
                        delProduct();
                    }
                    case 4 -> System.out.println("Удаление группы товаров со склада");
                    case 5 -> {
                        System.out.println("Показать типы товаров и их количество");
                        getProductsList();
                    }
                    case 0 -> {
                        System.out.println("Выход в главное меню");
                        return;
                    }
                    default -> {
                        System.out.println("-------------------------------------------------------------");
                        System.out.println("Ошибка. Введите цифру из меню!!!");
                    }
                }

            } catch (InputMismatchException ex) {
                System.out.println("Ошибка. Допустим ввод только числовых значений!!!");
                in.nextLine();
            }
        }
    }

    private static void workWithStorages() {
        while (true) {

            try {
                int mainMenuItem;
                Descriptions.StoragesDesc();

                mainMenuItem = in.nextInt();

                switch (mainMenuItem) {
                    case 1 -> {
                        System.out.println("Показать список складов и товаров в них: ");
                        getWarehouseInfo();
                    }
                    case 2 -> {
                        System.out.println("Добавить новый склад");
                        addWarehouse();
                    }
                    case 3 -> {
                        System.out.println("Удалить склад");
                        delWarehouse();
                    }
                    case 0 -> {
                        System.out.println("Выход в главное меню");
                        return;
                    }
                    default -> {
                        System.out.println("-------------------------------------------------------------");
                        System.out.println("Ошибка. Введите цифру из меню!!!");
                    }
                }
            } catch (InputMismatchException ex) {
                System.out.println("Ошибка. Допустим ввод только числовых значений!!!");
                in.nextLine();
            }
        }
    }

    private static void addWarehouse() {
        int volume = 0;

        try {
            System.out.print("Введите объем склада: ");
            volume = in.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Ошибка. Допустим ввод только числовых значений!!!");
            in.nextLine();
        }

        Warehouse warehouse = new Warehouse(warehouseId, volume);
        warehouseList.add(warehouse);
        warehouseId++;
    }

    private static void getWarehouseInfo() {
        if (warehouseList.size() == 0) {
            System.out.println("-------------------------------------");
            System.out.println("Нет складов");
            System.out.println("-------------------------------------");
        } else {
            for (Warehouse wh : warehouseList) {
                System.out.println("-------------------------------------");
                System.out.println(wh);
                System.out.println("-------------------------------------");
                for (Product p : wh.getProducts()) {
                    System.out.println(p);
                }
                System.out.println("=====================================");
            }
        }
    }

    private static void delWarehouse() {
        int index;

        if (warehouseList.size() == 0) {
            System.out.println("Нет складов");
        } else {
            getWarehouseInfo();
            System.out.println("Введите индекс склада для его удаления: ");
            index = in.nextInt();

            try {
                warehouseList.remove(index);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Ошибка. Неправильный индекс склада!");
            }
        }
    }

    private static void addProductToWarehouseByIndex(Product p, int index) {
        Warehouse w = warehouseList.get(index);
        if (w == null) {
            System.err.println("Выбранного склада не существует");
            return;
        }
        if (w.getFreeSpace() >= p.getBoxVolume()) {
            w.addProduct(p);
        } else {
            addProductToWarehouses(p);
        }
    }

    private static void addProductToWarehouses(Product p) {
        int whFreeSpace;
        int productSpace;
        int leftOver = 0;

        for (Warehouse w : warehouseList) {
            whFreeSpace = w.getFreeSpace();
            productSpace = p.getBoxVolume();


            if (whFreeSpace >= productSpace) {
                w.addProduct(p);
                System.out.println("На склад " + w.getWarehouseId() + " поместилось " + p.getBoxVolume() + " единиц товара");
                return;
            } else if (whFreeSpace > 0) {
                leftOver = productSpace - whFreeSpace;
                w.addProduct(excludeProductLeftover(p, whFreeSpace));
                System.out.println("На склад " + w.getWarehouseId() + " поместилось только " + whFreeSpace
                        + " единиц товара, перераспределим остаток " + leftOver + " на другой склад");
                p.setBoxVolume(leftOver);
            }
        }

        if (leftOver > 0) {
            System.out.println("На складах не осталось свободного места, возвращаем обратно " + leftOver);
        }
    }

    private static Product excludeProductLeftover(Product p, int space) {
        if (p instanceof HardProduct) {
            return new HardProduct(p.getProductType(), p.getName(), space, ((HardProduct) p).getProductWeight());
        }
        return new HardProduct(p.getProductType(), p.getName(), space, ((LiquidProduct) p).getProductVolume());
    }

    private static void addProductsList() {
        int count;

        try {
            System.out.print("Введите количество коробок товара: ");
            count = in.nextInt();
            for (int i = 0; i < count; i++) {
                addProduct();
            }
        } catch (InputMismatchException ex) {
            System.out.println("Ошибка. Допустим ввод только числовых значений!!!");
            in.nextLine();
        }

    }

    private static void addProduct() {
        int index;
        System.out.println("Введите индекс склада (-1 для распределения на все склады): ");

        try {
            index = in.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Ошибка. Допустим ввод только числовых значений!!!");
            in.nextLine();
            return;
        }

        addProduct(index);
    }

    private static void addProduct(int index) {
        int typeId;
        String name;
        int boxVolume;

        int productClass;
        int productVolume;

        Product product = null;

        try {
            System.out.print("Введите тип товара " + ProductType.getDescription() + ": ");
            typeId = in.nextInt();

            in.nextLine();
            System.out.println("Введите название товара: ");
            name = in.nextLine();

            System.out.print("Введите объем коробок: ");
            boxVolume = in.nextInt();

            System.out.print("Классификация товара жидкость(1) твердый материал(2): ");
            productClass = in.nextInt();
            if (productClass == 1) {
                System.out.print("Введите объем единицы товара: ");
                productVolume = in.nextInt();

                product = new LiquidProduct(ProductType.fromId(typeId), name, boxVolume, productVolume);
            } else if (productClass == 2) {
                System.out.print("Введите массу товара: ");
                productVolume = in.nextInt();
                product = new HardProduct(ProductType.fromId(typeId), name, boxVolume, productVolume);
            }

            if (index != -1) {
                addProductToWarehouseByIndex(product, index);
            } else {
                addProductToWarehouses(product);
            }

        } catch (InputMismatchException ex) {
            System.out.println("Ошибка. Допустим ввод только числовых значений!!!");
            in.nextLine();
        }
    }

    public static void delProduct() {
        if (warehouseList.size() == 0) {
            System.out.println("Нет складов");
        } else {
            int type;
            while (true) {
                try {
                    System.out.print("Введите тип товара " + ProductType.getDescription() + ": ");
                    type = in.nextInt();
                    break;
                } catch (InputMismatchException ex) {
                    System.out.println("Ошибка. Допустим ввод только числовых значений!!!");
                    in.nextLine();
                }
            }

            deleteProductsByType(ProductType.fromId(type));
        }
    }

    private static void deleteProductsByType(ProductType type) {
        for (Warehouse w : warehouseList) {
            List<Product> filteredProducts = w.getProducts()
                    .stream()
                    .filter((product) -> !product.getProductType().equals(type))
                    .collect(Collectors.toList());
            w.setProducts(filteredProducts);
        }
    }

    public static void getProductsList() {
        int index;

        try {
            System.out.println("Введите индекс склада (-1 для просмотра всех складов): ");
            index = in.nextInt();

            getProductsList(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка. Неправильный индекс склада!");
        }
    }

    public static void getProductsList(int index) {
        Warehouse wh = new Warehouse();
        boolean isFoundWh = false;

        if (warehouseList.size() == 0) {
            System.out.println("Нет складов");
        } else {

            if (index != -1) {
                wh = warehouseList.get(index);
                if (wh.getProducts().size() == 0) {
                    System.out.println("Склад пустой");
                } else {
                    for (Product pr : wh.getProducts()) {
                        System.out.println(pr.toString());
                    }
                }
            } else {
                for (Warehouse wh2 : warehouseList) {
                    for (Product pr : wh2.getProducts()) {
                        isFoundWh = true;
                        System.out.println("Склад №" + wh.getWarehouseId() +
                                "\n" + pr.toString());
                    }
                }

                if (!isFoundWh) {
                    System.out.println("Склады пустые");
                }
            }
        }
    }
}