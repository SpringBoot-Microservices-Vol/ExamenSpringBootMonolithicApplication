package com.sergiu.util;

import java.math.BigDecimal;

public class GradeUtils {

    public static double calculateAverageWriteTest(Double firstGrade, Double secondGrade) {
        Double testGrade = (firstGrade + secondGrade) / 2;
        BigDecimal result = new BigDecimal(testGrade);
        return result.setScale(2, BigDecimal.ROUND_FLOOR).doubleValue();
    }

    public static double calculateFinalResult(Double writeTest, Double bacBestGrade, Double bacGrade) {
        Double result = writeTest * 0.5 + bacBestGrade * 0.25 + bacGrade * 0.25;
        return (new BigDecimal(result)).setScale(2, BigDecimal.ROUND_FLOOR).doubleValue();
    }
}
