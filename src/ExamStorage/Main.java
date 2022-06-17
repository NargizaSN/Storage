package ExamStorage;

import ExamStorage.Warehouse;

import java.util.*;

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
                    case 1:
                        // Метод для работы с товарами
                        workWithProducts();
                        break;
                    case 2:
                        // Метод для работы со складами
                        workWithStorages();
                        break;
                    case 0:
                        System.out.println("-------------------------------------------------------------");
                        System.out.println("Выход из программы...");
                        return;
                    default:
                        System.out.println("-------------------------------------------------------------");
                        System.out.println("Ошибка. Введите цифру из меню!!!");
                        break;
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
                    case 1:
                        System.out.println("Добавление товара на склад");
                        break;
                    case 2:
                        System.out.println("Добавление группы товаров на склад");
                        break;
                    case 3:
                        System.out.println("Удаление товара со склада");
                        break;
                    case 4:
                        System.out.println("Удаление группы товаров со склада");
                        break;
                    case 5:
                        System.out.println("Показать типы товаров и их количество");
                        break;
                    case 0:
                        System.out.println("Выход в главное меню");
                        return;
                    default:
                        System.out.println("-------------------------------------------------------------");
                        System.out.println("Ошибка. Введите цифру из меню!!!");
                        break;
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
                    case 1:
                        getWarehouseInfo();
                        break;
                    case 2:
                        addWarehouse();
                        break;
                    case 3:
                        delWarehouse();
                        break;
                    case 0:
                        System.out.println("Выход в главное меню");
                        return;
                    default:
                        System.out.println("-------------------------------------------------------------");
                        System.out.println("Ошибка. Введите цифру из меню!!!");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Ошибка. Допустим ввод только числовых значений!!!");
                in.nextLine();
            }
        }
    }

    private static void addWarehouse() {
        warehouseId++;

//        UUID warehouseId = UUID.randomUUID();
        int volume;
        String warehouseName;

        System.out.println("Введите название склада:");
        warehouseName = in.next();
        System.out.println("Введите объем склада:");
        volume = in.nextInt();

        Warehouse warehouse = new Warehouse(warehouseId, volume, warehouseName);
        warehouseList.add(warehouse);
        System.out.println("Склад добавлен");
    }

    private static void delWarehouse() {
        int warehouseId;
        System.out.println("Введите ID склада:");
        warehouseId = in.nextInt();
        boolean isRemoved = false;


        for (int i = 0; i < warehouseList.size(); i++) {
            if(warehouseList.get(i).getWarehouseId() == warehouseId){
                System.out.println("Склад найден");
                warehouseList.remove(i);
                System.out.println("Склад №" + warehouseId + " удален");
            }
        }
    }

    private static void getWarehouseInfo() {
        Iterator<Warehouse> iterator = warehouseList.iterator();
        while (iterator.hasNext()) {
            Warehouse warehouse = iterator.next();
            System.out.printf(warehouse.toString());
        }
    }


}