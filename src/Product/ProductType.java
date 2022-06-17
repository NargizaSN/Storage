package ExamStorage;

public enum ProductType {
    SHAMPOO (0, "Шампунь"),

    SOAP (1, "Мыло"),

    JUICE (2, "Сок");

    private static final ProductType[] VALUES = values();
    private final int id;
    private final String desc;

    ProductType(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static ProductType fromId(int id) {
        return VALUES[id];
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescription() {
        String result = "";
        for (int i = 0; i < VALUES.length; i++) {
            result = result + " " + VALUES[i].desc + "(" + VALUES[i].id + ") ";
        }

        return result;
    }
}