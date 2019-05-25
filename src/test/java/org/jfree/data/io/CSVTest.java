package org.jfree.data.io;

import org.jfree.data.category.CategoryDataset;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class CSVTest {

    /**
     * Some checks for the constructor.
     */
    @Test
    public void testReadCorrectInput() throws IOException {
        CSV csv = new CSV(',', '"');
        FileReader fr = new FileReader("src/test/java/org/jfree/data/io/data.csv");
        CategoryDataset a = csv.readCategoryDataset(fr);
        assertTrue(a.getColumnCount() == 2);
        assertTrue(a.getRowCount() == 5);
        assertEquals(a.getColumnKey(0), "test6");
        assertEquals(a.getRowKey(0), "test");
    }
    /**
     * Some checks for the constructor.
     */
    @Test
    public void testReadDefaultConstructorCorrectInput() throws IOException {
        CSV csv = new CSV();
        FileReader fr = new FileReader("src/test/java/org/jfree/data/io/data.csv");
        CategoryDataset a = csv.readCategoryDataset(fr);
        assertTrue(a.getColumnCount() == 2);
        assertTrue(a.getRowCount() == 5);
        assertEquals(a.getColumnKey(0), "test6");
        assertEquals(a.getRowKey(0), "test");
    }
}
