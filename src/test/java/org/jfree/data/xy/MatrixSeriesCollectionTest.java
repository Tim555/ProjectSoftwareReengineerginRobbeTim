/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2016, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * -------------------------------
 * MatrixSeriesCollectionTest.java
 * -------------------------------
 * (C) Copyright 2006-2016, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 27-Nov-2006 : Version 1 (DG);
 * 22-Apr-2008 : Added testPublicCloneable (DG);
 *
 */

package org.jfree.data.xy;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.jfree.chart.TestUtils;
import org.jfree.chart.util.PublicCloneable;

import org.junit.Test;

/**
 * Tests for the {@link MatrixSeriesCollection} class.
 */
public class MatrixSeriesCollectionTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        MatrixSeries s1 = new MatrixSeries("Series", 2, 3);
        s1.update(0, 0, 1.1);
        MatrixSeriesCollection c1 = new MatrixSeriesCollection();
        c1.addSeries(s1);
        MatrixSeries s2 = new MatrixSeries("Series", 2, 3);
        s2.update(0, 0, 1.1);
        MatrixSeriesCollection c2 = new MatrixSeriesCollection();
        c2.addSeries(s2);
        assertTrue(c1.equals(c2));
        assertTrue(c2.equals(c1));

        c1.addSeries(new MatrixSeries("Empty Series", 1, 1));
        assertFalse(c1.equals(c2));

        c2.addSeries(new MatrixSeries("Empty Series", 1, 1));
        assertTrue(c1.equals(c2));
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        MatrixSeries s1 = new MatrixSeries("Series", 2, 3);
        s1.update(0, 0, 1.1);
        MatrixSeriesCollection c1 = new MatrixSeriesCollection();
        c1.addSeries(s1);
        MatrixSeriesCollection c2 = (MatrixSeriesCollection) c1.clone();

        assertTrue(c1 != c2);
        assertTrue(c1.getClass() == c2.getClass());
        assertTrue(c1.equals(c2));

        // check independence
        s1.setDescription("XYZ");
        assertFalse(c1.equals(c2));
    }

    /**
     * Verify that this class implements {@link PublicCloneable}.
     */
    @Test
    public void testPublicCloneable() {
        MatrixSeriesCollection c1 = new MatrixSeriesCollection();
        assertTrue(c1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        MatrixSeries s1 = new MatrixSeries("Series", 2, 3);
        s1.update(0, 0, 1.1);
        MatrixSeriesCollection c1 = new MatrixSeriesCollection();
        c1.addSeries(s1);
        MatrixSeriesCollection c2 = (MatrixSeriesCollection) 
                TestUtils.serialised(c1);
        assertEquals(c1, c2);
    }

    /**
     * Constructor with null
     */
    @Test
    public void testConstructorWithNullValue() {
        MatrixSeriesCollection matrixSeriesCollection = new MatrixSeriesCollection(null);
    }

    /**
     * MatrixSeriesCollection with no series and get a serie should return NullMatrixSeries object
     */
    @Test
    public void testNullSeriesGetSerieTestValue() {
        MatrixSeriesCollection matrixSeriesCollection = new MatrixSeriesCollection(null);
        assertTrue(matrixSeriesCollection.getSeries(0) instanceof NullMatrixSeries);
    }


    /**
     * MatrixSeriesCollection serie get added
     */
    @Test
    public void testSeriesGetSerieTestValue() {
        MatrixSeries matrixSeries = new MatrixSeries("Series", 5, 5);
        MatrixSeriesCollection matrixSeriesCollection = new MatrixSeriesCollection(matrixSeries);

        assertTrue(matrixSeriesCollection.getSeries(0).equals(matrixSeries));
    }

    /**
     * getItemCount Test
     */
    @Test
    public void testGetItemCount() {
        MatrixSeries matrixSeries = new MatrixSeries("Series", 5, 5);
        MatrixSeriesCollection matrixSeriesCollection = new MatrixSeriesCollection(matrixSeries);

        assertTrue(matrixSeriesCollection.getItemCount(0) == 25);
    }

    /**
     * GetSeries Test negative
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetSeriesNegativeNumber() {
        MatrixSeries matrixSeries = new MatrixSeries("Series", 5, 5);
        MatrixSeriesCollection matrixSeriesCollection = new MatrixSeriesCollection(matrixSeries);

        matrixSeriesCollection.getSeries(-1);
    }

    /**
     * GetSeries Test max
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetSeriesmaxNumber() {
        MatrixSeries matrixSeries = new MatrixSeries("Series", 5, 5);
        MatrixSeriesCollection matrixSeriesCollection = new MatrixSeriesCollection(matrixSeries);

        matrixSeriesCollection.getSeries(Integer.MAX_VALUE);
    }

    /**
     * Test equals method
     */
    @Test
    public void testGetSeries() {
        MatrixSeries matrixSeries = new MatrixSeries("Series", 5, 5);
        MatrixSeriesCollection matrixSeriesCollection = new MatrixSeriesCollection(matrixSeries);

        // Equals itself
        assertTrue(matrixSeriesCollection.equals(matrixSeriesCollection));

        // Equals null
        assertFalse(matrixSeriesCollection.equals(null));

        // Equals Wrong Type
        assertFalse(matrixSeriesCollection.equals(5));
    }

    /**
     * Test Remove series method
     */
    @Test
    public void testRemoveSeries() {
        MatrixSeries matrixSeries = new MatrixSeries("Series", 5, 5);
        MatrixSeries matrixSeries2 = new MatrixSeries("Series2", 5, 5);
        MatrixSeriesCollection matrixSeriesCollection = new MatrixSeriesCollection(matrixSeries);
        MatrixSeriesCollection matrixSeriesCollection2 = new MatrixSeriesCollection(matrixSeries);
        MatrixSeriesCollection matrixSeriesCollection3 = new MatrixSeriesCollection(matrixSeries);
        MatrixSeriesCollection matrixSeriesCollection4 = new MatrixSeriesCollection(matrixSeries);

        matrixSeriesCollection.removeAllSeries();
        matrixSeriesCollection2.removeSeries(0);
        matrixSeriesCollection3.removeSeries(matrixSeries);
        matrixSeriesCollection4.removeSeries(matrixSeries2);

        assertTrue(matrixSeriesCollection.getSeriesCount() == 0);

        assertTrue(matrixSeriesCollection2.getSeriesCount() == 0);

        assertTrue(matrixSeriesCollection3.getSeriesCount() == 0);

        assertTrue(matrixSeriesCollection4.getSeriesCount() == 1);
    }

    /**
     * Test Remove series method
     */
    @Test
    public void testHashCode() {
        MatrixSeries matrixSeries = new MatrixSeries("Series", 5, 5);
        MatrixSeriesCollection matrixSeriesCollection = new MatrixSeriesCollection(null);
        MatrixSeriesCollection matrixSeriesCollection2 = new MatrixSeriesCollection(null);

        assertTrue(matrixSeriesCollection.hashCode() == matrixSeriesCollection2.hashCode());
    }

    /**
     * removeSeries test wrong parameters
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeSeriesWrongParameters() {
        MatrixSeries matrixSeries = new MatrixSeries("Series", 5, 5);
        MatrixSeriesCollection matrixSeriesCollection = new MatrixSeriesCollection(matrixSeries);
        matrixSeriesCollection.removeSeries(-5);
    }

    /**
     * removeSeries test wrong parameters
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeSeriesWrongParameters2() {
        MatrixSeries matrixSeries = new MatrixSeries("Series", 5, 5);
        MatrixSeriesCollection matrixSeriesCollection = new MatrixSeriesCollection(matrixSeries);
        matrixSeriesCollection.removeSeries(Integer.MAX_VALUE);
    }

    /**
     * Getters test
     */
    @Test
    public void testGetters() {
        MatrixSeries matrixSeries = new MatrixSeries("Series", 5, 5);

        MatrixSeriesCollection matrixSeriesCollection = new MatrixSeriesCollection(matrixSeries);

        Number i = 1;
        assertEquals(matrixSeriesCollection.getX(0, 1), i);
        assertEquals(matrixSeriesCollection.getY(0, 1), 0);
        assertEquals(matrixSeriesCollection.getZ(0, 1), 0.0);

        assertTrue(matrixSeriesCollection.getSeriesKey(0) == "Series");

    }

}
