package christmas;

import static christmas.constant.Constant.GIVE_STAR_BADGE;
import static christmas.constant.Constant.NONE;
import static christmas.constant.Constant.STAR;
import static christmas.constant.Menu.DESSERT_ICECREAM;
import static org.junit.jupiter.api.Assertions.assertEquals;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.constant.Menu;
import christmas.model.Badge;
import christmas.model.Charge;
import christmas.model.Customer;
import christmas.model.Day;
import christmas.model.Order;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ModelTest extends NsTest {

    @DisplayName("배지 생성 테스트")
    @Test
    void 배지_생성() {
        Badge badge = new Badge();
        badge.setBadgeByCharge(GIVE_STAR_BADGE);
        assertEquals(badge.getBadgeType(), STAR);
    }

    @DisplayName("비용 할인 전 총 가격 생성 테스트")
    @Test
    void 비용_생성_할인전_총가격() {
        List<Menu> orderMenu = List.of(Menu.DESSERT_CHOCOLATECAKE, Menu.MAIN_STEAK);
        Charge charge = new Charge(orderMenu);
        int calcCharge = Menu.DESSERT_CHOCOLATECAKE.getPrice() + Menu.MAIN_STEAK.getPrice();
        assertEquals(charge.getTotalChargeBeforeDiscount(), calcCharge);
    }

    @DisplayName("고객 생성 테스트")
    @Test
    void 고객_생성() {
        Customer customer = new Customer(new Order(new Day("3"), "아이스크림-1"));
        assertEquals(customer.getOrder().getOrderMenu().get(0), DESSERT_ICECREAM);
        assertEquals(customer.getBadge().getBadgeType(), NONE);
    }

    @DisplayName("날짜 생성 테스트")
    @Test
    void 날짜_생성() {
        Day day = new Day("3");
        assertEquals(day.getDay(), 3);
        assertEquals(day.getDayOfWeek(), "일");
    }

    @DisplayName("주문 생성 테스트")
    @Test
    void 주문_생성() {
        Order order = new Order(new Day("3"), "아이스크림-3");
        assertEquals(order.getReservedDate().getDay(), 3);
        assertEquals(order.getOrderMenu().get(0).getMenuName(), DESSERT_ICECREAM.getMenuName());
        assertEquals(order.getOrderMenu().get(0).getAmount(), 3);
        assertEquals(order.getCharge().getTotalChargeBeforeDiscount(), DESSERT_ICECREAM.getPrice()*3);
        assertEquals(order.getIsGivenCompliment(), false);
    }

    @DisplayName("주문 내 증정메뉴 증정 테스트")
    @Test
    void 증정_테스트() {
        Order order = new Order(new Day("3"), "초코케이크-1");
        assertEquals(order.getIsGivenCompliment(), false);
        assertEquals(order.getCharge().getBenefitCharge(), 0);

        order.giveComplimentMenu();
        order.getCharge().calcCharge();
        assertEquals(order.getIsGivenCompliment(), true);
        assertEquals(order.getCharge().getBenefitCharge(), 25000);
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
