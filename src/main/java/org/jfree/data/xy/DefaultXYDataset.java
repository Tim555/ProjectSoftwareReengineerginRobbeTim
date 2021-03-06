/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2013, by Object Refinery Limited and Contributors.
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
 * DefaultXYDataset.java
 * ---------------------
 * (C) Copyright 2006-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 06-Jul-2006 : Version 1 (DG);
 * 02-Nov-2006 : Fixed a problem with adding a new series with the same key
 *               as an existing series (see bug 1589392) (DG);
 * 25-Jan-2007 : Implemented PublicCloneable (DG);
 *
 */

package org.jfree.data.xy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jfree.chart.util.PublicCloneable;

import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeEvent;

/**
 * A default implementation of the {@link XYDataset} interface that stores
 * data values in arrays of double primitives.
 *
 * @since 1.0.2
 */
public class DefaultXYDataset extends DefaultAbstractXYDataset
        implements XYDataset, PublicCloneable {


    /**
     * Creates a new {@code DefaultXYDataset} instance, initially
     * containing no data.
     */
    public DefaultXYDataset() {
        this.seriesKeys = new java.util.ArrayList();
        this.seriesList = new java.util.ArrayList();
    }
    

    /**
     * Returns the order of the domain (x-) values in the dataset.  In this
     * implementation, we cannot guarantee that the x-values are ordered, so
     * this method returns {@code DomainOrder.NONE}.
     *
     * @return {@code DomainOrder.NONE}.
     */
    @Override
    public DomainOrder getDomainOrder() {
        return DomainOrder.NONE;
    }


    /**
     * Adds a series or if a series with the same key already exists replaces
     * the data for that series, then sends a {@link DatasetChangeEvent} to
     * all registered listeners.
     *
     * @param seriesKey  the series key ({@code null} not permitted).
     * @param data  the data (must be an array with length 2, containing two
     *     arrays of equal length, the first containing the x-values and the
     *     second containing the y-values).
     */
    public void addSeries(Comparable seriesKey, double[][] data) {
        if (seriesKey == null) {
            throw new IllegalArgumentException(
                    "The 'seriesKey' cannot be null.");
        }
        if (data == null) {
            throw new IllegalArgumentException("The 'data' is null.");
        }
        if (data.length != 2) {
            throw new IllegalArgumentException(
                    "The 'data' array must have length == 2.");
        }
        if (data[0].length != data[1].length) {
            throw new IllegalArgumentException(
                "The 'data' array must contain two arrays with equal length.");
        }
        int seriesIndex = indexOf(seriesKey);
        if (seriesIndex == -1) {  // add a new series
            this.seriesKeys.add(seriesKey);
            this.seriesList.add(data);
        }
        else {  // replace an existing series
            this.seriesList.remove(seriesIndex);
            this.seriesList.add(seriesIndex, data);
        }
        notifyListeners(new DatasetChangeEvent(this, this));
    }

    /**
     * Removes a series from the dataset, then sends a
     * {@link DatasetChangeEvent} to all registered listeners.
     *
     * @param seriesKey  the series key ({@code null} not permitted).
     *
     */
    public void removeSeries(Comparable seriesKey) {
        int seriesIndex = indexOf(seriesKey);
        if (seriesIndex >= 0) {
            this.seriesKeys.remove(seriesIndex);
            this.seriesList.remove(seriesIndex);
            notifyListeners(new DatasetChangeEvent(this, this));
        }
    }

    /**
     * Tests this {@code DefaultXYDataset} instance for equality with an
     * arbitrary object.  This method returns {@code true} if and only if:
     * <ul>
     * <li>{@code obj} is not {@code null};</li>
     * <li>{@code obj} is an instance of {@code DefaultXYDataset};</li>
     * <li>both datasets have the same number of series, each containing
     *         exactly the same values.</li>
     * </ul>
     *
     * @param obj  the object ({@code null} permitted).
     *
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DefaultXYDataset)) {
            return false;
        }
        DefaultXYDataset that = (DefaultXYDataset) obj;
        if (!this.seriesKeys.equals(that.seriesKeys)) {
            return false;
        }
        for (int i = 0; i < this.seriesList.size(); i++) {
            double[][] d1 = (double[][]) this.seriesList.get(i);
            double[][] d2 = (double[][]) that.seriesList.get(i);
            double[] d1x = d1[0];
            double[] d2x = d2[0];
            if (!Arrays.equals(d1x, d2x)) {
                return false;
            }
            double[] d1y = d1[1];
            double[] d2y = d2[1];
            if (!Arrays.equals(d1y, d2y)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        int result;
        result = this.seriesKeys.hashCode();
        result = 29 * result + this.seriesList.hashCode();
        return result;
    }

    /**
     * Creates an independent copy of this dataset.
     *
     * @return The cloned dataset.
     *
     * @throws CloneNotSupportedException if there is a problem cloning the
     *     dataset (for instance, if a non-cloneable object is used for a
     *     series key).
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        DefaultXYDataset clone = (DefaultXYDataset) super.clone();
        clone.seriesKeys = new java.util.ArrayList(this.seriesKeys);
        clone.seriesList = new ArrayList(this.seriesList.size());
        for (int i = 0; i < this.seriesList.size(); i++) {
            double[][] data = (double[][]) this.seriesList.get(i);
            double[] x = data[0];
            double[] y = data[1];
            double[] xx = new double[x.length];
            double[] yy = new double[y.length];
            System.arraycopy(x, 0, xx, 0, x.length);
            System.arraycopy(y, 0, yy, 0, y.length);
            clone.seriesList.add(i, new double[][] {xx, yy});
        }
        return clone;
    }

}
