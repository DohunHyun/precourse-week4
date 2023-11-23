package christmas.view;

import christmas.model.Customer;

public class OutputView {

    public OutputView() {
        printWelcome();
    }

    private void printWelcome() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventDetail(Customer customer) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n", customer.getOrder().getReservedDate().getDay());
    }

    public void printOrderedMenu(StringBuilder output) {
        System.out.println("<주문 메뉴>");
        System.out.println(output);

    }

    public void printCharge(int charge) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%,d원\n\n", charge);
    }

    public void printComplimentMenu(String output) {
        System.out.println("<증정 메뉴>");
        System.out.println(output + "\n");
    }

    public void printBenefit(StringBuilder output) {
        System.out.println("<혜택 내역>");
        System.out.println(output);
    }

    public void printBenefitCharge(int charge) {
        System.out.println("<총혜택 금액>");
        System.out.printf("%,d원\n\n", -charge);
    }

    public void printPayCharge(int charge) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%,d원\n\n", charge);
    }

    public void printBadge(String output) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(output);
    }
}
