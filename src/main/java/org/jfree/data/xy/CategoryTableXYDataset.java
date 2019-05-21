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
 * ---------------------------
 * CategoryTableXYDataset.java
 * ---------------------------
 * (C) Copyright 2004-2016, by Andreas Schroeder and Contributors.
 *
 * Original Author:  Andreas Schroeder;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 31-Mar-2004 : Version 1 (AS);
 * 05-May-2004 : Now extends AbstractIntervalXYDataset (DG);
 * 15-Jul-2004 : Switched interval access method names (DG);
 * 18-Aug-2004 : Moved from org.jfree.data --> org.jfree.data.xy (DG);
 * 17-Nov-2004 : Updates required by changes to DomainInfo interface (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * 05-Oct-2005 : Made the interval delegate a dataset change listener (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 * 22-Apr-2008 : Implemented PublicCloneable, and fixed clone() method (DG);
 * 18-Oct-2011 : Fixed bug 3190615 - added clear() method (DG);
 *
 */

package org.jfree.data.xy;

import org.jfree.chart.util.PublicCloneable;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.DomainInfo;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetUtils;

/**
 * An implementation variant of the {@link TableXYDataset} where every series
 * shares the same x-values (required for generating stacked area charts).
 * This implementation uses a {@link DefaultKeyedValues2D} Object as backend
 * implementation and is hence more "category oriented" than the {@link
 * DefaultTableXYDataset} implementation.
 * <p>
 * This implementation provides no means to remove data items yet.
 * This is due to the lack of such facility in the DefaultKeyedValues2D class.
 * <p>
 * This class also implements the {@link IntervalXYDataset} interface, but this
 * implementation is provisional.
 */
public class CategoryTableXYDataset extends AbstractTableXYDataset
        implements TableXYDataset, IntervalXYDataset,
                   PublicCloneable {

    /**
     * The backing data structure.
     */
    private DefaultKeyedValues2D values;


    /**
     * Creates a new empty CategoryTableXYDataset.
     */
    public CategoryTableXYDataset() {
        super();
        this.values = new DefaultKeyedValues2D(true);
        addChangeListener(this.intervalDelegate);
    }

    /**
     * Adds a data item to this dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param x  the x value.
     * @param y  the y value.
     * @param seriesName  the name of the series to add the data item.
     */
    public void add(double x, double y, String seriesName) {
        add(new Double(x), new Double(y), seriesName, true);
    }

    /**
     * Adds a data item to this dataset and, if requested, sends a
     * {@link DatasetChangeEvent} to all registered listeners.
     *
     * @param x  the x value.
     * @param y  the y value.
     * @param seriesName  the name of the series to add the data item.
     * @param notify  notify listeners?
     */
    public void add(Number x, Number y, String seriesName, boolean notify) {
        this.values.addValue(y, (Comparable) x, seriesName);
        if (notify) {
            fireDatasetChanged();
        }
    }

    /**
     * Removes a value from the dataset.
     *
     * @param x  the x-value.
     * @param seriesName  the series name.
     */
    public void remove(double x, String seriesName) {
        remove(new Double(x), seriesName, true);
    }

    /**
     * Removes an item from the dataset.
     *
     * @param x  the x-value.
     * @param seriesName  the series name.
     * @param notify  notify listeners?
     */
    public void remove(Number x, String seriesName, boolean notify) {
        this.values.removeValue((Comparable) x, seriesName);
        if (notify) {
            fireDatasetChanged();
        }
    }

    /**
     * Clears all data from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     * 
     * @since 1.0.14
     */
    public void clear() {
        this.values.clear();
        fireDatasetChanged();
    }

    /**
     * Returns the number of series in the collection.
     *
     * @return The series count.
     */
    @Override
    public int getSeriesCount() {
        return this.values.getColumnCount();
    }

    /**
     * Returns the key for a series.
     *
     * @param series  the series index (zero-based).
     *
     * @return The key for a series.
     */
    @Override
    public Comparable getSeriesKey(int series) {
        return this.values.getColumnKey(series);
    }

    /**
     * Returns the number of x values in the dataset.
     *
     * @return The item count.
     */
    @Override
    public int getItemCount() {
        return this.values.getRowCount();
    }

    /**
     * Returns the number of items in the specified series.
     * Returns the same as {@link CategoryTableXYDataset#getItemCount()}.
     *
     * @param series  the series index (zero-based).
     *
     * @return The item count.
     */
    @Override
    public int getItemCount(int series) {
        return getItemCount();  // all series have the same number of items in
                                // this dataset
    }

    /**
     * Returns the x-value for the specified series and item.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    @Override
    public Number getX(int series, int item) {
        return (Number) this.values.getRowKey(item);
    }

    /**
     * Returns the y-value for the specified series and item.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The y value (possibly {@code null}).
     */
    @Override
    public Number getY(int series, int item) {
        return this.values.getValue(item, series);
    }


    /**
     * Tests this dataset for equality with an arbitrary object.
     *
     * @param obj  the object ({@code null} permitted).
     *
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CategoryTableXYDataset)) {
            return false;
        }
        CategoryTableXYDataset that = (CategoryTableXYDataset) obj;
        if (!this.intervalDelegate.equals(that.intervalDelegate)) {
            return false;
        }
        if (!this.values.equals(that.values)) {
            return false;
        }
        return true;
    }

    /**
     * Returns an independent copy of this dataset.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if there is some reason that cloning
     *     cannot be performed.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        CategoryTableXYDataset clone = (CategoryTableXYDataset) super.clone();
        clone.values = (DefaultKeyedValues2D) this.values.clone();
        clone.intervalDelegate = new IntervalXYDelegate(clone);
        // need to configure the intervalDelegate to match the original
        clone.intervalDelegate.setFixedIntervalWidth(getIntervalWidth());
        clone.intervalDelegate.setAutoWidth(isAutoWidth());
        clone.intervalDelegate.setIntervalPositionFactor(
                getIntervalPositionFactor());
        return clone;
    }

}
