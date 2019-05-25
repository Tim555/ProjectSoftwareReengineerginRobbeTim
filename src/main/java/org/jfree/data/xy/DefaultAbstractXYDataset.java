package org.jfree.data.xy;

import java.util.List;

public class DefaultAbstractXYDataset extends AbstractXYDataset {



    /**
     * Returns the number of series in the dataset.
     *
     * @return The series count.
     */
    /**
     * Storage for the series in the dataset.  We use a list because the
     * order of the series is significant.  This list must be kept in sync
     * with the seriesKeys list.
     */
    protected List seriesList;

    /**
     * Storage for the series keys.  This list must be kept in sync with the
     * seriesList.
     */
    protected List seriesKeys;

    /**
     * Constructor
     */
    public DefaultAbstractXYDataset() {
        this.seriesKeys = new java.util.ArrayList();
        this.seriesList = new java.util.ArrayList();
    }


    @Override
    public int getSeriesCount() {
        return this.seriesList.size();
    }

    /**
     * Returns the key for a series.
     *
     * @param series  the series index (in the range {@code 0} to
     *     {@code getSeriesCount() - 1}).
     *
     * @return The key for the series.
     *
     * @throws IllegalArgumentException if {@code series} is not in the
     *     specified range.
     */
    @Override
    public Comparable getSeriesKey(int series) {
        if ((series < 0) || (series >= getSeriesCount())) {
            throw new IllegalArgumentException("Series index out of bounds");
        }
        return (Comparable) this.seriesKeys.get(series);
    }


    /**
     * Returns the number of items in the specified series.
     *
     * @param series  the series index (in the range {@code 0} to
     *     {@code getSeriesCount() - 1}).
     *
     * @return The item count.
     *
     * @throws IllegalArgumentException if {@code series} is not in the
     *     specified range.
     */
    @Override
    public int getItemCount(int series) {
        if ((series < 0) || (series >= getSeriesCount())) {
            throw new IllegalArgumentException("Series index out of bounds");
        }
        double[][] seriesArray = (double[][]) this.seriesList.get(series);
        return seriesArray[0].length;
    }



    /**
     * Returns the x-value for an item within a series.
     *
     * @param series  the series index (in the range {@code 0} to
     *     {@code getSeriesCount() - 1}).
     * @param item  the item index (in the range {@code 0} to
     *     {@code getItemCount(series)}).
     *
     * @return The x-value.
     *
     * @throws ArrayIndexOutOfBoundsException if {@code series} is not
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if {@code item} is not
     *     within the specified range.
     *
     * @see #getX(int, int)
     */
    @Override
    public double getXValue(int series, int item) {
        double[][] seriesData = (double[][]) this.seriesList.get(series);
        return seriesData[0][item];
    }

    /**
     * Returns the y-value for an item within a series.
     *
     * @param series  the series index (in the range {@code 0} to
     *     {@code getSeriesCount() - 1}).
     * @param item  the item index (in the range {@code 0} to
     *     {@code getItemCount(series)}).
     *
     * @return The y-value.
     *
     * @throws ArrayIndexOutOfBoundsException if {@code series} is not
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if {@code item} is not
     *     within the specified range.
     *
     * @see #getY(int, int)
     */
    @Override
    public double getYValue(int series, int item) {
        double[][] seriesData = (double[][]) this.seriesList.get(series);
        return seriesData[1][item];
    }

    /**
     * Returns the x-value for an item within a series.
     *
     * @param series  the series index (in the range {@code 0} to
     *     {@code getSeriesCount() - 1}).
     * @param item  the item index (in the range {@code 0} to
     *     {@code getItemCount(series)}).
     *
     * @return The x-value.
     *
     * @throws ArrayIndexOutOfBoundsException if {@code series} is not
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if {@code item} is not
     *     within the specified range.
     *
     * @see #getXValue(int, int)
     */
    @Override
    public Number getX(int series, int item) {
        return new Double(getXValue(series, item));
    }

    /**
     * Returns the y-value for an item within a series.
     *
     * @param series  the series index (in the range {@code 0} to
     *     {@code getSeriesCount() - 1}).
     * @param item  the item index (in the range {@code 0} to
     *     {@code getItemCount(series)}).
     *
     * @return The y-value.
     *
     * @throws ArrayIndexOutOfBoundsException if {@code series} is not
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if {@code item} is not
     *     within the specified range.
     *
     * @see #getX(int, int)
     */
    @Override
    public Number getY(int series, int item) {
        return new Double(getYValue(series, item));
    }

    /**
     * Returns the index of the series with the specified key, or -1 if there
     * is no such series in the dataset.
     *
     * @param seriesKey  the series key ({@code null} permitted).
     *
     * @return The index, or -1.
     */
    @Override
    public int indexOf(Comparable seriesKey) {
        return this.seriesKeys.indexOf(seriesKey);
    }
}
