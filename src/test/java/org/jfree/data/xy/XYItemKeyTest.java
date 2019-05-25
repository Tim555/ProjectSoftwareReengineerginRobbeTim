package org.jfree.data.xy;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class XYItemKeyTest {

    /**
     * Check compare function
     */
    @Test
    public void testCompareFunction() {
        Double a = 4.0;
        Double b = 5.0;
        Double c = 10.0;
        XYItemKey<Double> key = new XYItemKey<Double>(b, 0);
        XYItemKey<Double> key2 = new XYItemKey<Double>(b, 0);
        XYItemKey<Double> key3 = new XYItemKey<Double>(a, 0);
        XYItemKey<Double> key4 = new XYItemKey<Double>(c, 0);

        assertTrue(key.compareTo(key2) == 0);
        assertTrue(key.compareTo(key3) == 1);
        assertTrue(key.compareTo(key4) == -1);
    }

    /**
     * Test the equal function
     */
    @Test
    public void testEqualFunction() {
        Double a = 4.0;
        Double b = 5.0;
        XYItemKey<Double> key = new XYItemKey<Double>(a, 0);
        XYItemKey<Double> key2 = new XYItemKey<Double>(a, 0);
        XYItemKey<Double> key3 = new XYItemKey<Double>(b, 0);
        XYItemKey<Double> key4 = new XYItemKey<Double>(b, 1);

        assertTrue(key.equals(key2));
        assertFalse(key.equals(key3));
        assertTrue(key.equals(key));
        assertFalse(key.equals(5));
        assertFalse(key3.equals(key4));
    }

    /**
     * Test getters
     */
    @Test
    public void testGetters() {
        Double a = 4.0;
        XYItemKey<Double> key = new XYItemKey<Double>(a, 0);

        assertTrue(key.getSeriesKey() == a);
        assertTrue(key.getItemIndex() == 0);

    }

    /**
     * Test hashcode
     */
    @Test
    public void testHashcode() {
        Double a = 4.0;
        XYItemKey<Double> key = new XYItemKey<Double>(a, 0);
        XYItemKey<Double> key2 = new XYItemKey<Double>(a, 0);

        assertTrue(key.hashCode() == key2.hashCode());

    }



}
