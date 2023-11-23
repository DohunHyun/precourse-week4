package christmas.model;

import static christmas.constant.Constant.DESSERT;
import static christmas.constant.Constant.DISCOUNT_DDAY_DEFAULT;
import static christmas.constant.Constant.DISCOUNT_DDAY_MAX_DATE;
import static christmas.constant.Constant.DISCOUNT_DDAY_PLUS;
import static christmas.constant.Constant.DISCOUNT_SPECIAL_DEFAULT;
import static christmas.constant.Constant.ERROR_ORDER;
import static christmas.constant.Constant.MAIN;
import static christmas.constant.Constant.THIS_YEAR;

import christmas.constant.Constant;
import christmas.constant.Event;
import christmas.constant.Menu;
import java.util.List;

public class Order {
    private List<Menu> orderMenu;
    private Day reservedDate;
    private Charge charge;
    private boolean isGivenCompliment;

    public Order(Day reservedDate, String menuInput) {
        Menu.resetOrder();
        Event.resetEvent();
        try {
            this.reservedDate = reservedDate;
            orderMenu = Menu.makeMenuList(menuInput);
            charge = new Charge(orderMenu);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ERROR_ORDER);
        }
    }

    public void giveComplimentMenu() {
        isGivenCompliment = true;
        Event.COMPLIMENT_EVENT.setDiscountPrice(Menu.DRINK_CHAMPAGNE.getPrice());
    }

    public void calcDdayEvent() {
        int discountPrice = 0;
        if(reservedDate.getDay() <= DISCOUNT_DDAY_MAX_DATE) {
            discountPrice = DISCOUNT_DDAY_DEFAULT;
            discountPrice += (reservedDate.getDay()-1) * DISCOUNT_DDAY_PLUS;
        }
        Event.DDAY_EVENT.setDiscountPrice(discountPrice);
    }

    public void calcWeekDayEvent() {
        if(Constant.WEEKDAY.contains(reservedDate.getDayOfWeek())) {
            for(Menu menu : orderMenu) {
                if(menu.getType().equals(DESSERT)) {
                    Event.WEEK_DAY_EVENT
                            .setDiscountPrice(Event.WEEK_DAY_EVENT.getDiscountPrice()
                                    + (THIS_YEAR * menu.getAmount()));
                }
            }
        }
    }

    public void calcWeekEndEvent() {
        if(Constant.WEEKEND.contains(reservedDate.getDayOfWeek())) {
            for(Menu menu : orderMenu) {
                if(menu.getType().equals(MAIN)) {
                    Event.WEEK_END_EVENT
                            .setDiscountPrice(Event.WEEK_END_EVENT.getDiscountPrice()
                                    + (THIS_YEAR * menu.getAmount()));
                }
            }
        }
    }

    public void calcSpecialEvent() {
        if(Constant.SPECIALDAY.contains(reservedDate.getDay())) {
            Event.SPECIAL_EVENT.setDiscountPrice(DISCOUNT_SPECIAL_DEFAULT);
        }
    }

    public List<Menu> getOrderMenu() {
        return orderMenu;
    }

    public Charge getCharge() {
        return charge;
    }

    public Day getReservedDate() {
        return reservedDate;
    }

    public boolean getIsGivenCompliment() {
        return isGivenCompliment;
    }
}
