package org.jfree.data.xy;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;


/**
 * Tests for {@link AbstractXYZDataset}.
 */
public class AbstrXYZDatasetTest {

    class DatasetXYZ extends AbstractXYZDataset {
        @Override
        public int getSeriesCount() {
            return 0;
        }

        @Override
        public Comparable getSeriesKey(int series) {
            return null;
        }


        @Override
        public Number getZ(int series, int item) {
            return 0;
        }

        @Override
        public int getItemCount(int series) {
            return 0;
        }

        @Override
        public Number getX(int series, int item) {
            return null;
        }

        @Override
        public Number getY(int series, int item) {
            return null;
        }
    }

    class DatasetXYZNull extends AbstractXYZDataset {
        @Override
        public int getSeriesCount() {
            return 0;
        }

        @Override
        public Comparable getSeriesKey(int series) {
            return null;
        }


        @Override
        public Number getZ(int series, int item) {
            return null;
        }

        @Override
        public int getItemCount(int series) {
            return 0;
        }

        @Override
        public Number getX(int series, int item) {
            return null;
        }

        @Override
        public Number getY(int series, int item) {
            return null;
        }
    }

    @Test
    public void testZValue() {
        AbstractXYZDataset d = new DatasetXYZ();
        assertEquals(d.getZValue(0, 0), 0.0);
    }

    @Test
    public void testZValueNull() {
        AbstractXYZDataset d = new DatasetXYZNull();
        assertEquals(d.getZValue(0, 0), null);
    }

}
