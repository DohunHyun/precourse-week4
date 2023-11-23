package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static christmas.constant.Constant.ERROR_DATE;
import static christmas.constant.Constant.ERROR_ORDER;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ErrorTest extends NsTest{
    @DisplayName("날짜에 문자 입력시 예외 발생")
    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains(ERROR_DATE);
        });
    }

    @DisplayName("날짜에 범위 밖 숫자 입력시 예외 발생")
    @Test
    void 날짜_예외_테스트1() {
        assertSimpleTest(() -> {
            runException("0");
            assertThat(output()).contains(ERROR_DATE);
        });
    }

    @DisplayName("주문 내역 중 갯수에 문자 입력시 예외 발생")
    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains(ERROR_ORDER);
        });
    }

    @DisplayName("주문내역에 음료만 있는 경우 예외 발생")
    @Test
    void 주문_예외_테스트1() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-3");
            assertThat(output()).contains(ERROR_ORDER);
        });
    }

    @DisplayName("주문 메뉴 개수가 20개 초과한 경우 예외 발생")
    @Test
    void 주문_예외_테스트2() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-20,초코케이크-1");
            assertThat(output()).contains(ERROR_ORDER);
        });
    }

    @DisplayName("주문 내역 중 메뉴에 없는 메뉴 입력시 예외 발생")
    @Test
    void 주문_예외_테스트3() {
        assertSimpleTest(() -> {
            runException("3", "새로운메뉴-1");
            assertThat(output()).contains(ERROR_ORDER);
        });
    }

    @DisplayName("주문 내역 형식에 맞지 않는 입력시 예외 발생")
    @Test
    void 주문_예외_테스트4() {
        assertSimpleTest(() -> {
            runException("3", "초코 케이크-1");
            assertThat(output()).contains(ERROR_ORDER);
        });
    }

    @DisplayName("주문 내역 중 중복되는 메뉴 입력시 예외 발생")
    @Test
    void 주문_예외_테스트5() {
        assertSimpleTest(() -> {
            runException("3", "초코케이크-1,초코케이크-1");
            assertThat(output()).contains(ERROR_ORDER);
        });
    }

    @DisplayName("Order 생성시 오류 발생 처리")
    @Test
    void 주문_예외_테스트6() {
        assertSimpleTest(() -> {
//            new
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
