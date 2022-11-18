package bridge;

import bridge.model.Bridge;
import bridge.model.User;
import bridge.view.Input;
import bridge.view.InputView;
import bridge.view.OutputView;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.util.Lists.newArrayList;

public class ViewTest extends NsTest {
    private static final Integer TEST_INT = 3;
    private static final String TEST_STR = "U";
    private TestInput testInput = new TestInput(TEST_INT, TEST_STR);
    private Integer outputInt;
    private String outputStr;
    @Test
    void InputTest(){
        InputView inputView = new InputView(testInput);
        outputInt = inputView.readBridgeSize();
        outputStr = inputView.readMoving();
        assertThat(outputInt).isEqualTo(TEST_INT);
        assertThat(outputStr).isEqualTo(TEST_STR);
    }
    @Test
    void printMapTest(){
        OutputView outputView = new OutputView();
        List<String> bridgeList = newArrayList("U","D","U","U");
        outputView.printMap(bridgeList,false,2);
        assertThat(output()).contains(
                "[ O |   | X ]",
                "[   | O |   ]"
                );
    }
    @Test
    void printFirstMapTest(){
        OutputView outputView = new OutputView();
        List<String> bridgeList = newArrayList("U","D","U","U");
        outputView.printMap(bridgeList,true,0);
        assertThat(output()).contains(
                "[ O ]",
                "[   ]"
        );
    }
    @Test
    void printSecondMapTest(){
        OutputView outputView = new OutputView();
        List<String> bridgeList = newArrayList("U","D","U","U");
        outputView.printMap(bridgeList,false,1);
        assertThat(output()).contains(
                "[ O |   ]",
                "[   | X ]"
        );
    }
    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }

    static class TestInput implements Input {
        private Integer inputInt;
        private String inputStr;
        TestInput(Integer inputInt,String inputStr){
            this.inputInt = inputInt;
            this.inputStr = inputStr;
        }
        @Override
        public Integer inputInteger() {
            return this.inputInt;
        }

        @Override
        public String inputString() {
            return this.inputStr;
        }
    }
    static class TestNumberGenerator implements BridgeNumberGenerator {

        private final List<Integer> numbers;

        TestNumberGenerator(List<Integer> numbers) {
            this.numbers = numbers;
        }

        @Override
        public int generate() {
            return numbers.remove(0);
        }
    }
}