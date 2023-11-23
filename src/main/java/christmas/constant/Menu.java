package christmas.constant;

import static christmas.constant.Constant.DRINK;
import static christmas.constant.Constant.ERROR_ORDER;
import static christmas.constant.Constant.ORDER_MAX_AMOUNT;
import static christmas.constant.Constant.ZERO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum Menu {

    APPETIZER_SOUP(6000, "양송이수프", 1,"APPETIZER"),
    APPETIZER_TAPAS(5500, "타파스", 1, "APPETIZER"),
    APPETIZER_SALAD(8000, "시저샐러드", 1,"APPETIZER"),

    MAIN_STEAK(55000, "티본스테이크", 1,"MAIN"),
    MAIN_RIB(54000, "바비큐립", 1,"MAIN"),
    MAIN_SEAFOODPASTA(35000, "해산물파스타", 1,"MAIN"),
    MAIN_CHRISTMASPASTA(25000, "크리스마스파스타", 1,"MAIN"),

    DESSERT_CHOCOLATECAKE(15000, "초코케이크", 1,"DESSERT"),
    DESSERT_ICECREAM(5000, "아이스크림", 1,"DESSERT"),

    DRINK_ZEROCOKE(3000, "제로콜라", 1,"DRINK"),
    DRINK_REDWINE(60000, "레드와인", 1,"DRINK"),
    DRINK_CHAMPAGNE(25000, "샴페인", 1,"DRINK");

    private final int price;
    private final String menuName;
    private int amount;
    private final String type;

    Menu(int price, String menuName, int amount, String type) {
        this.price = price;
        this.menuName = menuName;
        this.amount = amount;
        this.type = type;
    }

    public static Menu getMenu(String input) {
        return Stream.of(Menu.values())
                .filter(menu -> menu.menuName.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_ORDER));
    }

    public int getPrice() {
        return price;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public static List<Menu> makeMenuList(String menuInput) {
        List<String> inputToString = Arrays.asList(menuInput.split(","));
        List<Menu> orderMenu = checkOrderMenu(inputToString);
        checkMenuSize(orderMenu);
        checkOnlyDrink(orderMenu);
        return orderMenu;
    }

    private static List<Menu> checkOrderMenu(List<String> inputToString) {
        List<Menu> orderMenu = new ArrayList<>();
        for(String menu : inputToString) {
            if(Integer.parseInt(menu.split("-")[1]) == ZERO
                    || orderMenu.contains(getMenu(menu.split("-")[0]))) { // 입력값이 0이거나, 이미 주문한 메뉴면
                throw new IllegalArgumentException(ERROR_ORDER);
            }
            setAmount(menu.split("-")[0], Integer.parseInt(menu.split("-")[1]));
            orderMenu.add(getMenu(menu.split("-")[0]));
        }
        return orderMenu;
    }

    private static void checkMenuSize(List<Menu> orderMenu) {
        int menuAmount = 0;
        for(Menu menu : orderMenu) {
            menuAmount += menu.getAmount();
        }
        if(menuAmount > ORDER_MAX_AMOUNT) {
            throw new IllegalArgumentException(ERROR_ORDER);
        }
    }

    private static void checkOnlyDrink(List<Menu> orderMenu) {
        if(isOrderOnlyDrink(orderMenu)) {
            throw new IllegalArgumentException(ERROR_ORDER);
        }
    }

    private static boolean isOrderOnlyDrink(List<Menu> orderMenu) {
        for(Menu menu : orderMenu) {
            if(!menu.getType().equals(DRINK)) {
                return false;
            }
        }
        return true;
    }

    private static void setAmount(String menuName, int amount) {
        getMenu(menuName).amount = amount;
    }

    public static void resetOrder() {
        Stream.of(Menu.values()).forEach(menu -> {
            menu.amount = 1;
        });
    }
}
