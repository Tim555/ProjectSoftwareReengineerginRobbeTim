package org.jfree.data.xy;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class NormalizedMatrixSeriesTest {

    /**
     * test original state of the normalized matrix (all zeroes)
     */
    @Test
    public void getAllZeroTest() {
        NormalizedMatrixSeries normalizedMatrixSeries = new NormalizedMatrixSeries("Matrix 1", 6, 5);

        // Check all cells are 0
        for (int row = 0; row < normalizedMatrixSeries.getRowCount(); row++) {
            for (int col = 0; col < normalizedMatrixSeries.getColumnsCount(); col++) {
                assertTrue(normalizedMatrixSeries.get(row, col) == 0.0);
            }
        }
    }

    /**
     * test original state of the normalized matrix (all zeroes)
     */
    @Test
    public void setZeroTest() {
        NormalizedMatrixSeries normalizedMatrixSeries = new NormalizedMatrixSeries("Matrix 1", 6, 5);

        normalizedMatrixSeries.update(0,0,1.0);
        assertTrue(normalizedMatrixSeries.get(0, 0) == 1.0);

        normalizedMatrixSeries.zeroAll();

        // Check all cells are 0
        for (int row = 0; row < normalizedMatrixSeries.getRowCount(); row++) {
            for (int col = 0; col < normalizedMatrixSeries.getColumnsCount(); col++) {
                assertTrue(normalizedMatrixSeries.get(row, col) == 0.0);
            }
        }
    }

    /**
     * test getItem function
     */
    @Test
    public void testGetItemFunction() {
        NormalizedMatrixSeries normalizedMatrixSeries = new NormalizedMatrixSeries("Matrix 1", 5, 5);

        normalizedMatrixSeries.update(0,0,1.0);
        assertEquals(normalizedMatrixSeries.getItem(1), 0.0);

    }

}
