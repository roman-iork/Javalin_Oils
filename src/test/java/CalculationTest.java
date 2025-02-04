import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculationTest {

    @Test
    void testCalculate() {
        var allOilsData = new HashMap<String, HashMap<String, Double>>();
        var acidData = new HashMap<String, Double>();
        acidData.put("apple", 40.0);
        acidData.put("lemon", 30.0);
        acidData.put("milk",20.0);
        allOilsData.put("one", acidData);
        var acidData2 = new HashMap<String, Double>();
        acidData2.put("apple", 30.0);
        acidData2.put("lemon", 20.0);
        acidData2.put("milk",10.0);
        allOilsData.put("two", acidData2);

        var weights = new HashMap<String, Double>();
        weights.put("one", 100.0);
        weights.put("two", 2.0);

        var res = Calculation.calculate(allOilsData, weights);
        System.out.println(res);
    }

    @Test
    void testCalculateAuto() {
        var allOilsData = new HashMap<String, HashMap<String, Double>>();
        var acidData = new HashMap<String, Double>();
        acidData.put("apple", 40.0);
        acidData.put("lemon", 30.0);
        acidData.put("milk",20.0);
        allOilsData.put("one", acidData);
        var acidData2 = new HashMap<String, Double>();
        acidData2.put("apple", 30.0);
        acidData2.put("lemon", 20.0);
        acidData2.put("milk",10.0);
        allOilsData.put("two", acidData2);
        var weights = new HashMap<String, Double>();
        weights.put("one", 100.0);
        weights.put("two", 2.0);

        var res = Calculation.calculateAuto(allOilsData, 12.0, 1.7, List.of("apple", "lemon"), weights);
        System.out.println(res);
    }
}