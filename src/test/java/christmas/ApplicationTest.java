package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @DisplayName("할인 전 총 주문금액이 10,000원 미만인 경우 이벤트 적용 안됨")
    @Test
    void 혜택_내역_없음_출력1() {
        assertSimpleTest(() -> {
            runException("3", "아이스크림-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @DisplayName("디데이 할인 테스트")
    @Test
    void 디데이_할인() {
        assertSimpleTest(() -> {
            run("3", "초코케이크-1");
            assertThat(output()).contains("크리스마스 디데이 할인: -1,200원");
        });
    }

    @DisplayName("평일 할인 테스트")
    @Test
    void 평일_할인() {
        assertSimpleTest(() -> {
            run("4", "초코케이크-1");
            assertThat(output()).contains("평일 할인: -2,023원");
        });
    }

    @DisplayName("주말 할인 테스트")
    @Test
    void 주말_할인() {
        assertSimpleTest(() -> {
            run("2", "티본스테이크-1");
            assertThat(output()).contains("주말 할인: -2,023원");
        });
    }

    @DisplayName("특별 할인 테스트")
    @Test
    void 특별_할인() {
        assertSimpleTest(() -> {
            run("10", "티본스테이크-1");
            assertThat(output()).contains("특별 할인: -1,000원");
        });
    }

    @DisplayName("증정 이벤트 테스트")
    @Test
    void 증정_이벤트() {
        assertSimpleTest(() -> {
            run("31", "티본스테이크-5");
            assertThat(output()).contains("<증정 메뉴>" + LINE_SEPARATOR + "샴페인 1개", "증정 이벤트: -25,000원");
        });
    }

    @DisplayName("배지 테스트 - 없음")
    @Test
    void 배지_없음() {
        assertSimpleTest(() -> {
            run("1", "아이스크림-1");
            assertThat(output()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + "없음");
        });
    }

    @DisplayName("배지 테스트 - 별")
    @Test
    void 배지_별() {
        assertSimpleTest(() -> {
            run("3", "아이스크림-3");
            assertThat(output()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + "별");
        });
    }

    @DisplayName("배지 테스트 - 트리")
    @Test
    void 배지_트리() {
        assertSimpleTest(() -> {
            run("3", "아이스크림-5");
            assertThat(output()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + "트리");
        });
    }

    @DisplayName("배지 테스트 - 산타")
    @Test
    void 배지_산타() {
        assertSimpleTest(() -> {
            run("3", "아이스크림-10");
            assertThat(output()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + "산타");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
