package christmas.constant;

import java.util.List;

public class Constant {
    public static final List<String> WEEKDAY = List.of("일", "월", "화", "수", "목");
    public static final List<String> WEEKEND = List.of("금", "토");
    public static final List<Integer> SPECIALDAY = List.of(3, 10, 17, 24, 25, 31);
    public static final int ORDER_MAX_AMOUNT = 20;
    public static final int ZERO = 0;
    public static final int THIS_YEAR = 2023;
    public static final int THIS_MONTH = 12;
    public static final int MIN_ORDER_PRICE = 10000;
    public static final int MIN_COMPLIMENT_PRICE = 120000;
    public static final int GIVE_SANTA_BADGE = 20000;
    public static final int GIVE_TREE_BADGE = 10000;
    public static final int GIVE_STAR_BADGE = 5000;
    public static final int DISCOUNT_DDAY_MAX_DATE = 25;
    public static final int DISCOUNT_DDAY_DEFAULT = 1000;
    public static final int DISCOUNT_DDAY_PLUS = 100;
    public static final int DISCOUNT_SPECIAL_DEFAULT = 1000;
    public static final String NONE = "없음";
    public static final String DESSERT = "DESSERT";
    public static final String DRINK = "DRINK";
    public static final String MAIN = "MAIN";
    public static final String SANTA = "산타";
    public static final String TREE = "트리";
    public static final String STAR = "별";
    public static final String COMPLIMENT_MENU = "샴페인 1개";
    public static final String ERROR_ORDER = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String ERROR_DATE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
}
