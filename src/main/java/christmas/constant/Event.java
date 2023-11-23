package christmas.constant;

import static christmas.constant.Constant.ZERO;

import java.util.stream.Stream;

public enum Event {
    DDAY_EVENT("크리스마스 디데이 할인", 0),
    WEEK_DAY_EVENT("평일 할인", 0),
    WEEK_END_EVENT("주말 할인", 0),
    SPECIAL_EVENT("특별 할인", 0),
    COMPLIMENT_EVENT("증정 이벤트", 0);

    private String eventName;
    private int discountPrice;

    Event(String eventName, int discountPrice) {
        this.eventName = eventName;
        this.discountPrice = discountPrice;
    }

    public String getEventName() {
        return eventName;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public boolean isAnyEvent() {
        for(Event event : Event.values()) {
            if(event.discountPrice > ZERO) {
                return true;
            }
        }
        return false;
    }

    public static void resetEvent() {
        Stream.of(Event.values()).forEach(event -> {
            event.discountPrice = 0;
        });
    }
}
