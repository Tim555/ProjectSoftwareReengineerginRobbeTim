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
 * ---------------------
 * OHLCDataItemTest.java
 * ---------------------
 * (C) Copyright 2005-2016 by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 29-Apr-2005 : Version 1 (DG);
 *
 */

package org.jfree.data.xy;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.jfree.chart.TestUtils;
import org.junit.Test;

/**
 * Tests for the {@link OHLCDataItem} class.
 */
public class OHLCDataItemTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        OHLCDataItem i1 = new OHLCDataItem(new Date(1L), 1.0, 2.0, 3.0, 4.0, 
                5.0);
        OHLCDataItem i2 = new OHLCDataItem(new Date(1L), 1.0, 2.0, 3.0, 4.0, 
                5.0);
        OHLCDataItem i3 = new OHLCDataItem(new Date(1L), 1.0, 3.0, 3.0, 4.0,
                5.0);
        OHLCDataItem i4 = new OHLCDataItem(new Date(1L), 1.0, 2.0, 4.0, 4.0,
                5.0);
        OHLCDataItem i5 = new OHLCDataItem(new Date(1L), 1.0, 2.0, 3.0, 5.0,
                5.0);
        OHLCDataItem i6 = new OHLCDataItem(new Date(1L), 2.0, 2.0, 3.0, 5.0,
                5.0);
        assertTrue(i1.equals(i2));
        assertTrue(i2.equals(i1));
        assertTrue(i2.equals(i2));
        assertFalse(i2.equals(4));
        assertFalse(i2.equals(i3));
        assertFalse(i2.equals(i4));
        assertFalse(i2.equals(i5));
        assertFalse(i2.equals(i6));
    }

    @Test
    public void testGetters() {
        OHLCDataItem i1 = new OHLCDataItem(
                new Date(1L), 1.0, 2.0, 3.0, 4.0, 5.0
        );

        assertEquals(i1.getOpen(), (Number) 1.0);
        assertEquals(i1.getHigh(), (Number) 2.0);
        assertEquals(i1.getLow() , (Number) 3.0);
        assertEquals(i1.getClose() , (Number) 4.0);
        assertEquals(i1.getVolume() , (Number) 5.0);

    }

    /**
     * Instances of this class are immutable - cloning not required.
     */
    @Test
    public void testCloning() {
        OHLCDataItem i1 = new OHLCDataItem(
            new Date(1L), 1.0, 2.0, 3.0, 4.0, 5.0
        );
        assertFalse(i1 instanceof Cloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        OHLCDataItem i1 = new OHLCDataItem(new Date(1L), 1.0, 2.0, 3.0, 4.0, 
                5.0);
        OHLCDataItem i2 = (OHLCDataItem) TestUtils.serialised(i1);


        assertEquals(i1, i2);
    }

    @Test(expected = ClassCastException.class)
    public void testCompareWrongObject() {
        OHLCDataItem i1 = new OHLCDataItem(new Date(1L), 1.0, 2.0, 3.0, 4.0,
                5.0);
        i1.compareTo(5);

    }

}
