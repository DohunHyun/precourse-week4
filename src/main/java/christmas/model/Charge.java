package christmas.model;

import christmas.constant.Event;
import christmas.constant.Menu;
import java.util.List;
import java.util.stream.Stream;

public class Charge {
    private int totalChargeBeforeDiscount;
    private int benefitCharge;
    private int payCharge;

    public Charge(List<Menu> orderMenu) {
        for(Menu menu : orderMenu) {
            totalChargeBeforeDiscount += menu.getPrice() * menu.getAmount();
        }
        benefitCharge = 0;
        payCharge = 0;
    }

    public int getTotalChargeBeforeDiscount() {
        return totalChargeBeforeDiscount;
    }

    public int getBenefitCharge() {
        return benefitCharge;
    }

    public int getPayCharge() {
        return payCharge;
    }

    public void calcCharge() {
        Stream.of(Event.values()).forEach(event -> {
            benefitCharge += event.getDiscountPrice();
        });
        payCharge = totalChargeBeforeDiscount - benefitCharge + Event.COMPLIMENT_EVENT.getDiscountPrice();
    }
}
