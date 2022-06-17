package ExamStorage;

public class Descriptions {

    static void mainDesc() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Выберите пункт меню");
        System.out.println(" 1) Работа с товарами");
        System.out.println(" 2) Работа со складом");
        System.out.println(" 0) Выход из программы");
    }

    static void StoragesDesc() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Выберите пункт меню в работе со складами");
        System.out.println(" 1) Показать список складов и товаров в них");
        System.out.println(" 2) Добавить новый склад");
        System.out.println(" 3) Удалить склад");
        System.out.println(" 0) Выход в главное меню");
    }

    static void ProductsDesc() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Выберите пункт меню в работе с товарами");
        System.out.println(" 1) Добавление товара на склад");
        System.out.println(" 2) Добавление группы товаров на склад");
        System.out.println(" 3) Удаление товара со склада");
        System.out.println(" 4) Удаление группы товаров со склада");
        System.out.println(" 5) Показать типы товаров и их количество");
        System.out.println(" 0) Выход в главное меню");
    }

}
