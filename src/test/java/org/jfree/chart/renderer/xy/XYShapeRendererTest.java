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
 * ------------------------
 * XYShapeRendererTest.java
 * ------------------------
 * (C) Copyright 2010-2016, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Martin Hoeller (patch 2952086);
 *
 * Changes
 * -------
 * 17-Sep-2008 : Version 1 (DG);
 * 16-Feb-2010 : Added testFindZBounds() (MH);
 * 19-Oct-2011 : Added test3026341() (DG);
 *
 */

package org.jfree.chart.renderer.xy;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.TestUtils;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.data.Range;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.Test;

import javax.imageio.ImageIO;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

/**
 * Tests for the {@link XYShapeRenderer} class.
 */
public class XYShapeRendererTest {

    /**
     * Some checks for the equals() method.
     */
    @Test
    public void testEquals() {
        XYShapeRenderer r1 = new XYShapeRenderer();
        XYShapeRenderer r2 = new XYShapeRenderer();
        assertTrue(r1.equals(r2));
        assertTrue(r2.equals(r1));

        r1.setPaintScale(new LookupPaintScale(1.0, 2.0, Color.WHITE));
        assertFalse(r1.equals(r2));
        r2.setPaintScale(new LookupPaintScale(1.0, 2.0, Color.WHITE));
        assertTrue(r1.equals(r2));

        r1.setDrawOutlines(true);
        assertFalse(r1.equals(r2));
        r2.setDrawOutlines(true);
        assertTrue(r1.equals(r2));

        r1.setUseOutlinePaint(false);
        assertFalse(r1.equals(r2));
        r2.setUseOutlinePaint(false);
        assertTrue(r1.equals(r2));

        r1.setUseFillPaint(true);
        assertFalse(r1.equals(r2));
        r2.setUseFillPaint(true);
        assertTrue(r1.equals(r2));

        r1.setGuideLinesVisible(true);
        assertFalse(r1.equals(r2));
        r2.setGuideLinesVisible(true);
        assertTrue(r1.equals(r2));

        r1.setGuideLinePaint(Color.RED);
        assertFalse(r1.equals(r2));
        r2.setGuideLinePaint(Color.RED);
        assertTrue(r1.equals(r2));

    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        XYShapeRenderer r1 = new XYShapeRenderer();
        XYShapeRenderer r2 = (XYShapeRenderer) r1.clone();
        assertTrue(r1 != r2);
        assertTrue(r1.getClass() == r2.getClass());
        assertTrue(r1.equals(r2));
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        XYShapeRenderer r1 = new XYShapeRenderer();
        XYShapeRenderer r2 = (XYShapeRenderer) TestUtils.serialised(r1);
        assertEquals(r1, r2);
    }

    private static final double EPSILON = 0.0000000001;

    /**
     * Check if finding the bounds in z-dimension of an XYZDataset works. 
     */
    @Test
    public void testFindZBounds() {
        XYShapeRenderer r = new XYShapeRenderer();
        assertNull(r.findZBounds(null));

        DefaultXYZDataset dataset = new DefaultXYZDataset();
        Range range;

        double data1[][] = { {1,1,1}, {1,1,1}, {1,2,3} };
        dataset.addSeries("series1", data1);
        range = r.findZBounds(dataset);
        assertNotNull(range);
        assertEquals(1d, range.getLowerBound(), EPSILON);
        assertEquals(3d, range.getUpperBound(), EPSILON);

        double data2[][] = { {1,1,1}, {1,1,1}, {-1,-2,-3} };
        dataset.removeSeries("series1");
        dataset.addSeries("series2", data2);
        range = r.findZBounds(dataset);
        assertNotNull(range);
        assertEquals(-3d, range.getLowerBound(), EPSILON);
        assertEquals(-1d, range.getUpperBound(), EPSILON);

        double data3[][] = { {1,1,1}, {1,1,1}, {-1.2,2.9,3.8} };
        dataset.removeSeries("series2");
        dataset.addSeries("series3", data3);
        range = r.findZBounds(dataset);
        assertNotNull(range);
        assertEquals(-1.2d, range.getLowerBound(), EPSILON);
        assertEquals(3.8d, range.getUpperBound(), EPSILON);
    }

    /**
     * Test for bug 3026341.
     */
    @Test
    public void test3026341() {
        XYShapeRenderer renderer = new XYShapeRenderer();
        assertNull(renderer.findRangeBounds(null));

        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("S1");
        series.add(1.0, null);
        dataset.addSeries(series);
        Range r = renderer.findRangeBounds(dataset);
        assertNull(r);

        // test findDomainBounds as well
        r = renderer.findDomainBounds(dataset);
        assertEquals(r.getLowerBound(), 1.0, EPSILON);
        assertEquals(r.getUpperBound(), 1.0, EPSILON);

        dataset.removeAllSeries();
        r = renderer.findDomainBounds(dataset);
        assertNull(r);
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
        XYShapeRenderer r = new XYShapeRenderer();
        XYPlot plot = getPlot();

        BufferedImage image = new BufferedImage(200 , 100,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = image.createGraphics();
        Rectangle2D area = new Rectangle(0, 0, 40, 40);

        r.setDefaultSeriesVisible(false, false);

        r.drawItem(g2, null, area,null,
                plot, plot.getDomainAxis(), plot.getRangeAxis(),
                plot.getDataset(),0,0,null, 0);

        File file = new File("./.testImages/testXYShapesDrawItem1");
        file.delete();
        boolean result = ImageIO.write(image, "jpeg", file);


        BufferedImage test = null;
        BufferedImage real = null;
        try {
            test = ImageIO.read(new File("./.testImages/testXYShapesDrawItemOracle1"));
            real = ImageIO.read(new File("./.testImages/testXYShapesDrawItem1"));
        } catch (IOException e) {
            fail(e.getMessage());
        }

        boolean outcome = bufferedImagesEqual(test, real);

        assertTrue(outcome);



    }

    @Test
    public void test_drawItem() throws IOException {
        XYShapeRenderer r = new XYShapeRenderer();
        XYPlot plot = getPlot();

        r.setGuideLinesVisible(true);

        BufferedImage image = new BufferedImage(200 , 100,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = image.createGraphics();
        Rectangle2D area = new Rectangle(0, 0, 40, 40);



        r.drawItem(g2, null, area,null,
                plot, plot.getDomainAxis(), plot.getRangeAxis(),
                plot.getDataset(),0,0,null, 0);
        r.drawItem(g2, null, area,null,
                plot, plot.getDomainAxis(), plot.getRangeAxis(),
                plot.getDataset(),0,0,null, 1);


        File file = new File("./.testImages/testXYShapesDrawItem2");
        file.delete();
        boolean result = ImageIO.write(image, "jpeg", file);


        BufferedImage test = null;
        BufferedImage real = null;
        try {
            test = ImageIO.read(new File("./.testImages/testXYShapesDrawItemOracle2"));
            real = ImageIO.read(new File("./.testImages/testXYShapesDrawItem2"));
        } catch (IOException e) {
            fail(e.getMessage());
        }

        boolean outcome = bufferedImagesEqual(test, real);

        assertTrue(outcome);



    }

    @Test
    public void test_drawItemHorizontal() throws IOException {
        XYShapeRenderer r = new XYShapeRenderer();
        XYPlot plot = getPlot();
        plot.setOrientation(PlotOrientation.HORIZONTAL);
        r.setGuideLinesVisible(true);

        BufferedImage image = new BufferedImage(200 , 100,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = image.createGraphics();
        Rectangle2D area = new Rectangle(0, 0, 40, 40);

        XYItemRendererState state = new XYItemRendererState(null);



        r.drawItem(g2, state, area,null,
                plot, plot.getDomainAxis(), plot.getRangeAxis(),
                plot.getDataset(),0,0,null, 0);
        r.drawItem(g2, state, area,null,
                plot, plot.getDomainAxis(), plot.getRangeAxis(),
                plot.getDataset(),0,0,null, 1);


        File file = new File("./.testImages/testXYShapesDrawItem3");
        file.delete();
        boolean result = ImageIO.write(image, "jpeg", file);


        BufferedImage test = null;
        BufferedImage real = null;
        try {
            test = ImageIO.read(new File("./.testImages/testXYShapesDrawItemOracle3"));
            real = ImageIO.read(new File("./.testImages/testXYShapesDrawItem3"));
        } catch (IOException e) {
            fail(e.getMessage());
        }

        boolean outcome = bufferedImagesEqual(test, real);

        assertTrue(outcome);



    }

    @Test
    public void test_drawItemUseFillPaint() throws IOException {
        XYShapeRenderer r = new XYShapeRenderer();
        r.setUseFillPaint(true);
        XYPlot plot = getPlot();
        plot.setOrientation(PlotOrientation.HORIZONTAL);

        BufferedImage image = new BufferedImage(200 , 100,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = image.createGraphics();
        Rectangle2D area = new Rectangle(0, 0, 40, 40);



        r.drawItem(g2, null, area,null,
                plot, plot.getDomainAxis(), plot.getRangeAxis(),
                plot.getDataset(),0,0,null, 0);
        r.drawItem(g2, null, area,null,
                plot, plot.getDomainAxis(), plot.getRangeAxis(),
                plot.getDataset(),0,0,null, 1);


        File file = new File("./.testImages/testXYShapesDrawItem4");
        file.delete();
        boolean result = ImageIO.write(image, "jpeg", file);


        BufferedImage test = null;
        BufferedImage real = null;
        try {
            test = ImageIO.read(new File("./.testImages/testXYShapesDrawItemOracle4"));
            real = ImageIO.read(new File("./.testImages/testXYShapesDrawItem4"));
        } catch (IOException e) {
            fail(e.getMessage());
        }

        boolean outcome = bufferedImagesEqual(test, real);

        assertTrue(outcome);



    }

    @Test
    public void test_drawItemUseOutlinePaint() throws IOException {
        XYShapeRenderer r = new XYShapeRenderer();
        r.setUseFillPaint(true);
        r.setUseOutlinePaint(true);
        r.setDrawOutlines(true);
        XYPlot plot = getPlot();
        plot.setOrientation(PlotOrientation.HORIZONTAL);

        BufferedImage image = new BufferedImage(200 , 100,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = image.createGraphics();
        Rectangle2D area = new Rectangle(0, 0, 40, 40);


        r.drawItem(g2, null, area,null,
                plot, plot.getDomainAxis(), plot.getRangeAxis(),
                plot.getDataset(),0,0,null, 0);
        r.drawItem(g2, null, area,null,
                plot, plot.getDomainAxis(), plot.getRangeAxis(),
                plot.getDataset(),0,0,null, 1);


        File file = new File("./.testImages/testXYShapesDrawItem5");
        file.delete();
        boolean result = ImageIO.write(image, "jpeg", file);


        BufferedImage test = null;
        BufferedImage real = null;
        try {
            test = ImageIO.read(new File("./.testImages/testXYShapesDrawItemOracle5"));
            real = ImageIO.read(new File("./.testImages/testXYShapesDrawItem5"));
        } catch (IOException e) {
            fail(e.getMessage());
        }

        boolean outcome = bufferedImagesEqual(test, real);

        assertTrue(outcome);



    }

    @Test
    public void test_drawItemNoUseOutlinePaint() throws IOException {
        XYShapeRenderer r = new XYShapeRenderer();
        r.setUseFillPaint(true);
        r.setUseOutlinePaint(false);
        r.setDrawOutlines(true);
        XYPlot plot = getPlot();
        plot.setOrientation(PlotOrientation.HORIZONTAL);

        BufferedImage image = new BufferedImage(200 , 100,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = image.createGraphics();
        Rectangle2D area = new Rectangle(0, 0, 40, 40);


        r.drawItem(g2, null, area,null,
                plot, plot.getDomainAxis(), plot.getRangeAxis(),
                plot.getDataset(),0,0,null, 0);
        r.drawItem(g2, null, area,null,
                plot, plot.getDomainAxis(), plot.getRangeAxis(),
                plot.getDataset(),0,0,null, 1);


        File file = new File("./.testImages/testXYShapesDrawItem7");
        file.delete();
        boolean result = ImageIO.write(image, "jpeg", file);


        BufferedImage test = null;
        BufferedImage real = null;
        try {
            test = ImageIO.read(new File("./.testImages/testXYShapesDrawItemOracle7"));
            real = ImageIO.read(new File("./.testImages/testXYShapesDrawItem7"));
        } catch (IOException e) {
            fail(e.getMessage());
        }

        boolean outcome = bufferedImagesEqual(test, real);

        assertTrue(outcome);



    }

    @Test
    public void test_drawNoOutline() throws IOException {
        XYShapeRenderer r = new XYShapeRenderer();
        r.setDrawOutlines(false);
        XYPlot plot = getPlot();
        plot.setOrientation(PlotOrientation.HORIZONTAL);

        BufferedImage image = new BufferedImage(200 , 100,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = image.createGraphics();
        Rectangle2D area = new Rectangle(0, 0, 40, 40);



        r.drawItem(g2, null, area,null,
                plot, plot.getDomainAxis(), plot.getRangeAxis(),
                plot.getDataset(),0,0,null, 0);
        r.drawItem(g2, null, area,null,
                plot, plot.getDomainAxis(), plot.getRangeAxis(),
                plot.getDataset(),0,0,null, 1);


        File file = new File("./.testImages/testXYShapesDrawItem6");
        file.delete();
        boolean result = ImageIO.write(image, "jpeg", file);


        BufferedImage test = null;
        BufferedImage real = null;
        try {
            test = ImageIO.read(new File("./.testImages/testXYShapesDrawItemOracle6"));
            real = ImageIO.read(new File("./.testImages/testXYShapesDrawItem6"));
        } catch (IOException e) {
            fail(e.getMessage());
        }

        boolean outcome = bufferedImagesEqual(test, real);

        assertTrue(outcome);



    }



}
