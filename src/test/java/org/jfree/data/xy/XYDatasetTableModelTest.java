package org.jfree.data.xy;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class XYDatasetTableModelTest {

    @Test
    public void testCounts() {
        XYDatasetTableModel dataset = new XYDatasetTableModel();
        CategoryTableXYDataset categoryTableXYDataset = new CategoryTableXYDataset();
        categoryTableXYDataset.add(1,2,"test123");

        dataset.setModel(categoryTableXYDataset);

        assertTrue(dataset.getColumnCount() == 2);
        assertTrue(dataset.getRowCount() == 1);
        assertTrue(dataset.getColumnName(0) == "X Value");
        assertTrue(dataset.getColumnName(1) == "test123");

    }

    @Test
    public void testCounts2() {
        CategoryTableXYDataset categoryTableXYDataset = new CategoryTableXYDataset();
        XYDatasetTableModel dataset = new XYDatasetTableModel(categoryTableXYDataset);
        categoryTableXYDataset.add(1,2,"test123");

        dataset.setModel(categoryTableXYDataset);

        assertTrue(dataset.getColumnCount() == 2);
        assertTrue(dataset.getRowCount() == 1);
        assertTrue(dataset.getColumnName(0) == "X Value");
        assertTrue(dataset.getColumnName(1) == "test123");

    }

    @Test
    public void testNoModel() {
        XYDatasetTableModel dataset = new XYDatasetTableModel();

        assertTrue(dataset.getColumnCount() == 0);
        assertTrue(dataset.getRowCount() == 0);
        assertTrue(dataset.getValueAt(10,10) == null);

    }

}
