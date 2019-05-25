package org.jfree.data.xy;

import org.jfree.chart.util.Args;
import org.jfree.chart.util.ObjectUtils;

import java.util.List;

public class NullMatrixSeries extends MatrixSeries {

    /**
     * Constructs a new matrix series.
     * <p>
     * By default, all matrix items are initialzed to 0.
     * </p>
     */
    public NullMatrixSeries() {
        super("null", 0, 0);
    }

    /**
     * Returns the number of columns in this matrix series.
     *
     * @return The number of columns in this matrix series.
     */
    public int getColumnsCount() {
        return 0;
    }


    /**
     * Return the matrix item at the specified index.  Note that this method
     * creates a new {@code double} instance every time it is called.
     *
     * @param itemIndex item index.
     *
     * @return The matrix item at the specified index.
     *
     * @see #get(int, int)
     */
    public Number getItem(int itemIndex) {
        return 0;
    }


    /**
     * Returns the column of the specified item.
     *
     * @param itemIndex the index of the item.
     *
     * @return The column of the specified item.
     */
    public int getItemColumn(int itemIndex) {
        //assert itemIndex >= 0 && itemIndex < getItemCount();
        return 0;
    }


    /**
     * Returns the number of items in the series.
     *
     * @return The item count.
     */
    @Override
    public int getItemCount() {
        return 0;
    }


    /**
     * Returns the row of the specified item.
     *
     * @param itemIndex the index of the item.
     *
     * @return The row of the specified item.
     */
    public int getItemRow(int itemIndex) {
        //assert itemIndex >= 0 && itemIndex < getItemCount();
        return 0;
    }


    /**
     * Returns the number of rows in this matrix series.
     *
     * @return The number of rows in this matrix series.
     */
    public int getRowCount() {
        return 0;
    }


    /**
     * Returns the value of the specified item in this matrix series.
     *
     * @param i the row of the item.
     * @param j the column of the item.
     *
     * @return The value of the specified item in this matrix series.
     *
     * @see #getItem(int)
     * @see #update(int, int, double)
     */
    public double get(int i, int j) {
        return 0;
    }


    /**
     * Updates the value of the specified item in this matrix series.
     *
     * @param i the row of the item.
     * @param j the column of the item.
     * @param mij the new value for the item.
     *
     * @see #get(int, int)
     */
    public void update(int i, int j, double mij) {
    }

}
