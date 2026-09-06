package module2;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GradeAnalyzerTest {
    @Test
    void calculateAverage_returnsZero_whenListIsEmpty() {
        ArrayList<Integer> scores = new ArrayList<>();
        assertEquals(0.0, GradeAnalyzer.calculateAverage(scores));
    }

    @Test
    void calculateAverage_returnsCorrectAverage_forTypicalScores() {
        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(80, 90, 100));
        assertEquals(90.0, GradeAnalyzer.calculateAverage(scores));
    }

    @Test
    void calculateAverage_returnsSingleValue_whenListHasOneItem() {
        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(75));
        assertEquals(75.0, GradeAnalyzer.calculateAverage(scores));
    }

    @Test
    void calculateAverage_returnsDouble_notInteger() {
        // 1 + 2 = 3, divided by 2 = 1.5, not 1
        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(1, 2));
        assertEquals(1.5, GradeAnalyzer.calculateAverage(scores));
    }

    @Test
    void calculateAverage_handlesAllSameValues() {
        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(88, 88, 88));
        assertEquals(88.0, GradeAnalyzer.calculateAverage(scores));
    }

    @Test
    void calculateAverage_returnsCorrectAverage_forTenValues() {
        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(1,3,5,7,9,11,13,15,17,19));
        assertEquals(10.0, GradeAnalyzer.calculateAverage(scores));
    }

    @Test
    void calculateAverage_returnsCorrectAverage_forPositiveAndNegativeValueMix() {
        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(1,2,3,7,9,-1, -2, -3, -4, -5));
        assertEquals(0.7, GradeAnalyzer.calculateAverage(scores));
    }
}
