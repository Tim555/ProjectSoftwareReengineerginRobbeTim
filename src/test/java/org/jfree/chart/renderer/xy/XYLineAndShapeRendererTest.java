/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2017, by Object Refinery Limited and Contributors.
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
 * XYLineAndShapeRendererTest.java
 * -------------------------------
 * (C) Copyright 2004-2016, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 27-Jan-2004 : Version 1 (DG);
 * 07-Jan-2005 : Added check for findRangeBounds() method (DG);
 * 21-Feb-2007 : Check independence in testCloning() (DG);
 * 17-May-2007 : Added testGetLegendItemSeriesIndex() (DG);
 * 22-Apr-2008 : Added testPublicCloneable() (DG);
 *
 */

package org.jfree.chart.renderer.xy;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.TestUtils;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.*;
import org.jfree.chart.urls.TimeSeriesURLGenerator;
import org.jfree.chart.util.PublicCloneable;
import org.jfree.data.Range;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.Test;

import javax.imageio.ImageIO;

import static org.junit.Assert.*;

/**
 * Tests for the {@link XYLineAndShapeRenderer} class.
 */
public class XYLineAndShapeRendererTest {

    /**
     * Test that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {

        XYLineAndShapeRenderer r1 = new XYLineAndShapeRenderer();
        XYLineAndShapeRenderer r2 = new XYLineAndShapeRenderer();
        assertEquals(r1, r2);
        assertEquals(r2, r1);

        r1.setSeriesLinesVisible(3, true);
        assertFalse(r1.equals(r2));
        r2.setSeriesLinesVisible(3, true);
        assertTrue(r1.equals(r2));

        r1.setDefaultLinesVisible(false);
        assertFalse(r1.equals(r2));
        r2.setDefaultLinesVisible(false);
        assertTrue(r1.equals(r2));

        r1.setLegendLine(new Line2D.Double(1.0, 2.0, 3.0, 4.0));
        assertFalse(r1.equals(r2));
        r2.setLegendLine(new Line2D.Double(1.0, 2.0, 3.0, 4.0));
        assertTrue(r1.equals(r2));

        r1.setSeriesShapesVisible(3, true);
        assertFalse(r1.equals(r2));
        r2.setSeriesShapesVisible(3, true);
        assertTrue(r1.equals(r2));

        r1.setDefaultShapesVisible(false);
        assertFalse(r1.equals(r2));
        r2.setDefaultShapesVisible(false);
        assertTrue(r1.equals(r2));

        r1.setSeriesShapesFilled(3, true);
        assertFalse(r1.equals(r2));
        r2.setSeriesShapesFilled(3, true);
        assertTrue(r1.equals(r2));

        r1.setDefaultShapesFilled(false);
        assertFalse(r1.equals(r2));
        r2.setDefaultShapesFilled(false);
        assertTrue(r1.equals(r2));

        r1.setDrawOutlines(!r1.getDrawOutlines());
        assertFalse(r1.equals(r2));
        r2.setDrawOutlines(r1.getDrawOutlines());
        assertTrue(r1.equals(r2));

        r1.setUseOutlinePaint(true);
        assertFalse(r1.equals(r2));
        r2.setUseOutlinePaint(true);
        assertTrue(r1.equals(r2));

        r1.setUseFillPaint(true);
        assertFalse(r1.equals(r2));
        r2.setUseFillPaint(true);
        assertTrue(r1.equals(r2));

        r1.setDrawSeriesLineAsPath(true);
        assertFalse(r1.equals(r2));
        r2.setDrawSeriesLineAsPath(true);
        assertTrue(r1.equals(r2));
    }

    /**
     * Test that the equals() method works for a TimeSeriesURLGenerator.
     */
    @Test
    public void testEquals2() {
        XYLineAndShapeRenderer r1 = new XYLineAndShapeRenderer();
        XYLineAndShapeRenderer r2 = new XYLineAndShapeRenderer();
        assertEquals(r1, r2);
        assertEquals(r2, r1);

        r1.setURLGenerator(new TimeSeriesURLGenerator());
        assertFalse(r1.equals(r2));
        r2.setURLGenerator(new TimeSeriesURLGenerator());
        assertTrue(r1.equals(r2));
    }


    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        XYLineAndShapeRenderer r1 = new XYLineAndShapeRenderer();
        XYLineAndShapeRenderer r2 = new XYLineAndShapeRenderer();
        assertTrue(r1.equals(r2));
        int h1 = r1.hashCode();
        int h2 = r2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        Rectangle2D legendShape = new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0);
        XYLineAndShapeRenderer r1 = new XYLineAndShapeRenderer();
        r1.setLegendLine(legendShape);
        XYLineAndShapeRenderer r2 = (XYLineAndShapeRenderer) r1.clone();
        assertTrue(r1 != r2);
        assertTrue(r1.getClass() == r2.getClass());
        assertTrue(r1.equals(r2));

        r1.setSeriesLinesVisible(0, false);
        assertFalse(r1.equals(r2));
        r2.setSeriesLinesVisible(0, false);
        assertTrue(r1.equals(r2));

        legendShape.setRect(4.0, 3.0, 2.0, 1.0);
        assertFalse(r1.equals(r2));
        r2.setLegendLine(new Rectangle2D.Double(4.0, 3.0, 2.0, 1.0));
        assertTrue(r1.equals(r2));

        r1.setSeriesShapesVisible(1, true);
        assertFalse(r1.equals(r2));
        r2.setSeriesShapesVisible(1, true);
        assertTrue(r1.equals(r2));

        r1.setSeriesShapesFilled(1, true);
        assertFalse(r1.equals(r2));
        r2.setSeriesShapesFilled(1, true);
        assertTrue(r1.equals(r2));
    }

    /**
     * Verify that this class implements {@link PublicCloneable}.
     */
    @Test
    public void testPublicCloneable() {
        XYLineAndShapeRenderer r1 = new XYLineAndShapeRenderer();
        assertTrue(r1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        XYLineAndShapeRenderer r1 = new XYLineAndShapeRenderer();
        XYLineAndShapeRenderer r2 = (XYLineAndShapeRenderer) 
                TestUtils.serialised(r1);
        assertEquals(r1, r2);
    }

    /**
     * Check that the renderer is calculating the domain bounds correctly.
     */
    @Test
    public void testFindDomainBounds() {
        XYSeriesCollection dataset
                = RendererXYPackageUtils.createTestXYSeriesCollection();
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Test Chart", "X", "Y", dataset, PlotOrientation.VERTICAL,
                false, false, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setAutoRangeIncludesZero(false);
        Range bounds = domainAxis.getRange();
        assertFalse(bounds.contains(0.9));
        assertTrue(bounds.contains(1.0));
        assertTrue(bounds.contains(2.0));
        assertFalse(bounds.contains(2.10));
    }

    /**
     * Check that the renderer is calculating the range bounds correctly.
     */
    @Test
    public void testFindRangeBounds() {
        TableXYDataset dataset
                = RendererXYPackageUtils.createTestTableXYDataset();
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Test Chart", "X", "Y", dataset, PlotOrientation.VERTICAL,
                false, false, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRangeIncludesZero(false);
        Range bounds = rangeAxis.getRange();
        assertFalse(bounds.contains(1.0));
        assertTrue(bounds.contains(2.0));
        assertTrue(bounds.contains(5.0));
        assertFalse(bounds.contains(6.0));
    }

    /**
     * A check for the datasetIndex and seriesIndex fields in the LegendItem
     * returned by the getLegendItem() method.
     */
    @Test
    public void testGetLegendItemSeriesIndex() {
        XYSeriesCollection d1 = new XYSeriesCollection();
        XYSeries s1 = new XYSeries("S1");
        s1.add(1.0, 1.1);
        XYSeries s2 = new XYSeries("S2");
        s2.add(1.0, 1.1);
        d1.addSeries(s1);
        d1.addSeries(s2);

        XYSeriesCollection d2 = new XYSeriesCollection();
        XYSeries s3 = new XYSeries("S3");
        s3.add(1.0, 1.1);
        XYSeries s4 = new XYSeries("S4");
        s4.add(1.0, 1.1);
        XYSeries s5 = new XYSeries("S5");
        s5.add(1.0, 1.1);
        d2.addSeries(s3);
        d2.addSeries(s4);
        d2.addSeries(s5);

        XYLineAndShapeRenderer r = new XYLineAndShapeRenderer();
        XYPlot plot = new XYPlot(d1, new NumberAxis("x"),
                new NumberAxis("y"), r);
        plot.setDataset(1, d2);
        /*JFreeChart chart =*/ new JFreeChart(plot);
        LegendItem li = r.getLegendItem(1, 2);
        assertEquals("S5", li.getLabel());
        assertEquals(1, li.getDatasetIndex());
        assertEquals(2, li.getSeriesIndex());
    }

    @Test
    public void test_gettersAndSetters(){
        XYLineAndShapeRenderer r = new XYLineAndShapeRenderer();

        assertFalse(r.getDrawSeriesLineAsPath());
        r.setDrawSeriesLineAsPath(true);
        assertTrue(r.getDrawSeriesLineAsPath());
        r.setDrawSeriesLineAsPath(true);
        assertTrue(r.getDrawSeriesLineAsPath());

        r.setSeriesLinesVisible(0, true);
        assertTrue(r.getItemLineVisible(0, 0));

        assertTrue(r.getDefaultLinesVisible());
        assertEquals(r.getLegendLine().getBounds2D(), new Line2D.Double(-7,0,7,0).getBounds2D());

        r.setSeriesShapesFilled(0, true);
        assertTrue(r.getItemShapeFilled(0, 0));

        assertTrue(r.getDefaultShapesFilled());
        assertTrue(r.getDrawOutlines());

        assertFalse(r.getUseFillPaint());

        XYLineAndShapeRenderer.State state = new XYLineAndShapeRenderer.State(null);
        state.setLastPointGood(true);
        assertTrue(state.isLastPointGood());
    }

    public XYPlot getPlot(){
        XYSeries series = new XYSeries("Series");
        series.add(1.0, 1.0);
        series.add(2.0, 2.0);
        series.add(3.0, 3.0);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        NumberAxis xAxis = new NumberAxis("testX");
        NumberAxis yAxis = new NumberAxis("testY");
        XYItemRenderer renderer= new XYLineAndShapeRenderer(true, false);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        return plot;
    }

    static boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
            for (int x = 0; x < img1.getWidth(); x++) {
                for (int y = 0; y < img1.getHeight(); y++) {
                    if (img1.getRGB(x, y) != img2.getRGB(x, y))
                        return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Test
    public void test_drawItem_notVisible() throws IOException {
        XYLineAndShapeRenderer r = new XYLineAndShapeRenderer();
        XYPlot plot = getPlot();

        BufferedImage image = new BufferedImage(200 , 100,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = image.createGraphics();
        Rectangle2D area = new Rectangle(0, 0, 40, 40);

        r.setDefaultSeriesVisible(false, false);

        r.drawItem(g2, null, area,null,
                plot, plot.getDomainAxis(), plot.getRangeAxis(),
                plot.getDataset(),0,0,null, 0);

        File file = new File("./.testImages/testXYShapesAndLinesDrawItem");
        file.delete();
        boolean result = ImageIO.write(image, "jpeg", file);


        BufferedImage test = null;
        BufferedImage real = null;
        try {
            test = ImageIO.read(new File("./.testImages/testXYShapesAndLinesDrawItemOracle1"));
            real = ImageIO.read(new File("./.testImages/testXYShapesAndLinesDrawItem"));
        } catch (IOException e) {
            fail(e.getMessage());
        }

        boolean outcome = bufferedImagesEqual(test, real);

        assertTrue(outcome);



    }

    @Test
    public void test_drawItem() throws IOException {
        XYLineAndShapeRenderer r = new XYLineAndShapeRenderer();
        XYPlot plot = getPlot();

        BufferedImage image = new BufferedImage(200 , 100,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = image.createGraphics();
        Rectangle2D area = new Rectangle(0, 0, 40, 40);

        XYLineAndShapeRenderer.State state = new XYLineAndShapeRenderer.State(null);
        state.setLastPointGood(false);

        r.setDrawSeriesLineAsPath(true);

        r.drawItem(g2, state, area,null,
                plot, plot.getDomainAxis(), plot.getRangeAxis(),
                plot.getDataset(),0,0,null, 0);

        File file = new File("./.testImages/testXYShapesAndLinesDrawItem1");
        file.delete();
        boolean result = ImageIO.write(image, "jpeg", file);


        BufferedImage test = null;
        BufferedImage real = null;
        try {
            test = ImageIO.read(new File("./.testImages/testXYShapesAndLinesDrawItemOracle2"));
            real = ImageIO.read(new File("./.testImages/testXYShapesAndLinesDrawItem1"));
        } catch (IOException e) {
            fail(e.getMessage());
        }

        boolean outcome = bufferedImagesEqual(test, real);

        assertTrue(outcome);



    }

}
