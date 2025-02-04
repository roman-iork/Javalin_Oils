import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Calculation {
    static Map<String, Double> calculate(HashMap<String, HashMap<String, Double>> allOilsData, Map<String, Double> weights) {
        var resultMap = new HashMap<String, Double>();
        var weightTotal = weights.values().stream().mapToDouble(Double::doubleValue).sum();
        for (var oil : allOilsData.entrySet()) {
            var oilWeight = weights.get(oil.getKey());
            var acidData = oil.getValue();
            for (var acid : acidData.entrySet()) {
                var acidName = acid.getKey();
                var acidRawAmount = acid.getValue();
                var acidGramsPerItsWeight = (oilWeight * acidRawAmount) / 100;
                var acidPercentageTotal = (acidGramsPerItsWeight * 100) / weightTotal;
                var decimal = new BigDecimal(Double.toString(acidPercentageTotal))
                        .setScale(1, RoundingMode.HALF_UP).doubleValue();
                resultMap.merge(acidName, decimal, (a, b) -> {
                    var res = a + b;
                    return new BigDecimal(Double.toString(res))
                            .setScale(1, RoundingMode.HALF_UP).doubleValue();
                });
            }
        }
        return resultMap;
    }

    static HashMap<String, Double> calculateAuto(
            HashMap<String, HashMap<String, Double>> allOilsData,
            Double weight,
            Double ratio,
            List<String> acids,
            HashMap<String, Double> oilsWeights) {
        var oils = allOilsData.keySet();

        var allAcidsPercentage = calculate(allOilsData, oilsWeights);

        var mapOfTwo = new HashMap<String, Double>();
        mapOfTwo.put(acids.getFirst(), allAcidsPercentage.get(acids.getFirst()));
        mapOfTwo.put(acids.getLast(), allAcidsPercentage.get(acids.getLast()));
        var sortedMap = mapOfTwo.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
        var higherPercentage = sortedMap.sequencedValues().getFirst();
        var lowerPercentage = sortedMap.sequencedValues().getLast();
        var resultRatio = higherPercentage / lowerPercentage;

        var higherAcid = sortedMap.sequencedKeySet().getFirst();
        var lowerAcid = sortedMap.sequencedKeySet().getLast();

        var ratioInsideOil = new HashMap<String, Double>();
        for (var oil : oils) {
            var oilData = allOilsData.get(oil);
            var higherAcidInside = oilData.getOrDefault(higherAcid, 0.1);
            var lowerAcidInside = oilData.getOrDefault(lowerAcid, 0.1);
            var ratioInside = higherAcidInside / lowerAcidInside;
            ratioInsideOil.put(oil, ratioInside);
        }

        var totalWeight = oilsWeights.values().stream().mapToDouble(Double::doubleValue).sum();
        var whileCounter = 0;
        while (
                Math.abs(weight - totalWeight) + Math.abs(ratio - resultRatio) > 0.005
        ) {
            whileCounter++;
            var weightDiffAbs = Math.abs(weight - totalWeight);
            var ratioDiffAbs = Math.abs(ratio - resultRatio);
            var oilToAdjust = "";
            double adjustedWeight;
            if (weightDiffAbs > ratioDiffAbs) {
                oilToAdjust = ratioInsideOil.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldValue, newValue) -> oldValue,
                                LinkedHashMap::new)).sequencedKeySet().getLast();
                if (weight - totalWeight > 0) {
                    adjustedWeight = oilsWeights.get(oilToAdjust) + 0.001;
                } else {
                    adjustedWeight = oilsWeights.get(oilToAdjust) - 0.001;
                }
            } else {
                oilToAdjust = ratioInsideOil.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldValue, newValue) -> oldValue,
                                LinkedHashMap::new)).sequencedKeySet().getFirst();

                if (ratio - resultRatio > 0) {
                    adjustedWeight = oilsWeights.get(oilToAdjust) + 0.001;
                } else {
                    adjustedWeight = oilsWeights.get(oilToAdjust) - 0.001;
                }
            }
            oilsWeights.put(oilToAdjust, adjustedWeight);
            totalWeight = oilsWeights.values().stream().mapToDouble(Double::doubleValue).sum();
            allAcidsPercentage = calculate(allOilsData, oilsWeights);

            mapOfTwo = new HashMap<String, Double>();
            mapOfTwo.put(acids.getFirst(), allAcidsPercentage.get(acids.getFirst()));
            mapOfTwo.put(acids.getLast(), allAcidsPercentage.get(acids.getLast()));
            sortedMap = mapOfTwo.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue,
                            LinkedHashMap::new));
            higherPercentage = sortedMap.sequencedValues().getFirst();
            lowerPercentage = sortedMap.sequencedValues().getLast();
            resultRatio = higherPercentage / lowerPercentage;
            if (whileCounter > 200000 || adjustedWeight < 0) {
                return null;
            }
        }
        return oilsWeights;
    }
}
