package christmas.model;

import static christmas.constant.Constant.COMPLIMENT_MENU;
import static christmas.constant.Constant.MIN_COMPLIMENT_PRICE;
import static christmas.constant.Constant.MIN_ORDER_PRICE;
import static christmas.constant.Constant.NONE;
import static christmas.constant.Constant.ZERO;

import christmas.constant.Event;
import christmas.constant.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Stream;

public class EventManager {
    private static InputView inputView;
    private static OutputView outputView;
    public EventManager() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        Customer customer = getCustomerOrder();
        makeReservationWithEvent(customer);
        showEventDetail(customer);
    }

    private Customer getCustomerOrder() {
        Day visitDay = getCustomerOrderDate();
        return getCustomerOrderMenu(visitDay);
    }

    private Day getCustomerOrderDate() {
        while(true) {
            try {
                String date = getVisitDate();
                return new Day(date);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Customer getCustomerOrderMenu(Day visitDay) {
        while(true) {
            try {
                String menu = getOrderMenu();
                return new Customer(new Order(visitDay, menu));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String getVisitDate() {
        while(true) {
            try {
                return inputView.printAskDate();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String getOrderMenu() {
        return inputView.printAskMenu();
    }

    private void makeReservationWithEvent(Customer customer) {
        if(customer.getOrder().getCharge().getTotalChargeBeforeDiscount() > MIN_COMPLIMENT_PRICE) {
            giveComplimentMenu(customer.getOrder());
        }
        checkBenefit(customer.getOrder());
        calcCharge(customer.getOrder());
        checkBadge(customer);
    }

    private void giveComplimentMenu(Order order) {
        order.giveComplimentMenu();
    }

    private void checkBenefit(Order order) {
        if(order.getCharge().getTotalChargeBeforeDiscount() < MIN_ORDER_PRICE) {
            return;
        }
        order.calcDdayEvent();
        order.calcWeekDayEvent();
        order.calcWeekEndEvent();
        order.calcSpecialEvent();
    }

    private void calcCharge(Order order) {
        order.getCharge().calcCharge();
    }

    private void checkBadge(Customer customer) {
        int benefitCharge = customer.getOrder().getCharge().getBenefitCharge();
        customer.getBadge().setBadgeByCharge(benefitCharge);
    }

    private void showEventDetail(Customer customer) {
        outputView.printEventDetail(customer);
        printOrderedMenu(customer.getOrder());
        printChargeBeforeDiscount(customer.getOrder());
        printComplimentMenu(customer.getOrder());
        printBenefitDetail();
        outputView.printBenefitCharge(customer.getOrder().getCharge().getBenefitCharge());
        outputView.printPayCharge(customer.getOrder().getCharge().getPayCharge());
        outputView.printBadge(customer.getBadge().getBadgeType());
    }

    private void printOrderedMenu(Order order) {
        List<Menu> orderedMenu = order.getOrderMenu();
        StringBuilder output = new StringBuilder();
        for(Menu menu : orderedMenu) {
            output.append(menu.getMenuName() + " " + menu.getAmount() + "개\n");
        }
        outputView.printOrderedMenu(output);
    }

    private void printChargeBeforeDiscount(Order order) {
        outputView.printCharge(order.getCharge().getTotalChargeBeforeDiscount());
    }

    private void printComplimentMenu(Order order) {
        String menu = NONE;
        if(order.getIsGivenCompliment()) {
            menu = COMPLIMENT_MENU;
        }
        outputView.printComplimentMenu(menu);
    }

    private void printBenefitDetail() {
        StringBuilder sb = new StringBuilder();
        if(!Event.COMPLIMENT_EVENT.isAnyEvent()) {
            outputView.printBenefit(sb.append(NONE));
            return;
        }
        Stream.of(Event.values()).forEachOrdered(event -> {
            if(event.getDiscountPrice() > ZERO) {
                sb.append(event.getEventName() + ": " + makeMoneyForm(event.getDiscountPrice()) + "원\n");
            }
        });
        outputView.printBenefit(sb);
    }

    private String makeMoneyForm(int money) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(-money);
    }
}
