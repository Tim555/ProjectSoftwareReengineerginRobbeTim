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
 * -------------------------------
 * AbstractXYItemRendererTest.java
 * -------------------------------
 * (C) Copyright 2004-2016, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 06-Oct-2004 : Version 1 (DG);
 * 24-Nov-2006 : Added cloning tests (DG);
 *
 */

package org.jfree.chart.renderer.xy;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.*;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.junit.Test;

import org.jfree.chart.labels.StandardXYSeriesLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Tests for the {@link AbstractXYItemRenderer} class.
 */
public class AbstractXYItemRendererTest {

    /**
     * Creates a test dataset.
     *
     * @return A test dataset.
     */
    private XYDataset createDataset1() {
        XYSeries series = new XYSeries("Series");
        series.add(1.0, 1.0);
        series.add(2.0, 2.0);
        series.add(3.0, 3.0);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    private XYPlot getPlot(){

        NumberAxis xAxis = new NumberAxis("testX");
        NumberAxis yAxis = new NumberAxis("testY");
        XYItemRenderer renderer= new XYLineAndShapeRenderer(true, false);
        XYPlot plot = new XYPlot(createDataset1(), xAxis, yAxis, renderer);
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

    private static final double EPSILON = 0.0000000001;

    /**
     * Some checks for the findDomainBounds() method.
     */
    @Test
    public void testFindDomainBounds() {
        AbstractXYItemRenderer renderer = new StandardXYItemRenderer();

        // check the bounds of a simple dataset
        XYDataset dataset = createDataset1();
        Range r = renderer.findDomainBounds(dataset);
        assertEquals(1.0, r.getLowerBound(), EPSILON);
        assertEquals(3.0, r.getUpperBound(), EPSILON);

        // check that a null dataset returns null bounds
        assertTrue(renderer.findDomainBounds(null) == null);
    }

    /**
     * Some checks for the findRangeBounds() method.
     */
    @Test
    public void testFindRangeBounds() {
        AbstractXYItemRenderer renderer = new StandardXYItemRenderer();
        // check that a null dataset returns null bounds
        assertTrue(renderer.findRangeBounds(null) == null);
    }

    /**
     * Check that the legendItemLabelGenerator is cloned.
     */
    @Test
    public void testCloning_LegendItemLabelGenerator() throws CloneNotSupportedException {
        StandardXYSeriesLabelGenerator generator
                = new StandardXYSeriesLabelGenerator("Series {0}");
        XYBarRenderer r1 = new XYBarRenderer();
        r1.setLegendItemLabelGenerator(generator);
        XYBarRenderer r2 = (XYBarRenderer) r1.clone();
        assertTrue(r1 != r2);
        assertTrue(r1.getClass() == r2.getClass());
        assertTrue(r1.equals(r2));

        // check that the generator has been cloned
        assertTrue(r1.getLegendItemLabelGenerator()
                != r2.getLegendItemLabelGenerator());
    }

    /**
     * Check that the legendItemToolTipGenerator is cloned.
     */
    @Test
    public void testCloning_LegendItemToolTipGenerator() 
            throws CloneNotSupportedException {
        StandardXYSeriesLabelGenerator generator
                = new StandardXYSeriesLabelGenerator("Series {0}");
        XYBarRenderer r1 = new XYBarRenderer();
        r1.setLegendItemToolTipGenerator(generator);
        XYBarRenderer r2 = (XYBarRenderer) r1.clone();

        assertTrue(r1 != r2);
        assertTrue(r1.getClass() == r2.getClass());
        assertTrue(r1.equals(r2));

        // check that the generator has been cloned
        assertTrue(r1.getLegendItemToolTipGenerator()
                != r2.getLegendItemToolTipGenerator());
    }

    /**
     * Check that the legendItemURLGenerator is cloned.
     */
    @Test
    public void testCloning_LegendItemURLGenerator() 
            throws CloneNotSupportedException {
        StandardXYSeriesLabelGenerator generator
                = new StandardXYSeriesLabelGenerator("Series {0}");
        XYBarRenderer r1 = new XYBarRenderer();
        r1.setLegendItemURLGenerator(generator);
        XYBarRenderer r2 = (XYBarRenderer) r1.clone();
        assertTrue(r1 != r2);
        assertTrue(r1.getClass() == r2.getClass());
        assertTrue(r1.equals(r2));

        // check that the generator has been cloned
        assertTrue(r1.getLegendItemURLGenerator()
                != r2.getLegendItemURLGenerator());
    }
    
    @Test
    public void testEquals_ObjectList() {
        XYBarRenderer r1 = new XYBarRenderer();
        r1.setSeriesItemLabelGenerator(0, new StandardXYItemLabelGenerator());
        XYBarRenderer r2 = new XYBarRenderer();
        r2.setSeriesItemLabelGenerator(0, new StandardXYItemLabelGenerator());
        assertEquals(r1, r2);
        r2.setSeriesItemLabelGenerator(1, new StandardXYItemLabelGenerator("X"));
        assertNotEquals(r1, r2);
    }

    @Test
    public void testEquals_ObjectList2() {
        XYBarRenderer r1 = new XYBarRenderer();
        r1.setSeriesToolTipGenerator(0, new StandardXYToolTipGenerator());
        XYBarRenderer r2 = new XYBarRenderer();
        r2.setSeriesToolTipGenerator(0, new StandardXYToolTipGenerator());
        assertEquals(r1, r2);
        r2.setSeriesToolTipGenerator(1, new StandardXYToolTipGenerator());
        assertNotEquals(r1, r2);
    }


    @Test
    public void test_MarkersVertical() throws IOException {
        XYPlot plot = getPlot();
        XYItemRenderer renderer = plot.getRenderer();

        BufferedImage image = new BufferedImage(200 , 100,
                BufferedImage.TYPE_INT_RGB);
       // BufferedImage test  = new BufferedImage(200 , 100,
       //         BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = image.createGraphics();
        Rectangle2D area = new Rectangle(0, 0, 40, 40);

        Marker marker1 = new ValueMarker(2);
        Marker marker2 = new IntervalMarker(3,4);


        renderer.drawDomainMarker(g2, plot, plot.getRangeAxis(), marker1, area);
        renderer.drawDomainMarker(g2, plot, plot.getRangeAxis(), marker2, area);

        renderer.drawRangeMarker(g2, plot, plot.getRangeAxis(), marker1, area);
        renderer.drawRangeMarker(g2, plot, plot.getRangeAxis(), marker2, area);

        File file = new File("./.testImages/testMarkersVertical");
        file.delete();
        boolean result = ImageIO.write(image, "jpeg", file);


        BufferedImage test = null;
        BufferedImage real = null;
        try {
            test = ImageIO.read(new File("./.testImages/testMarkersVerticalOracle"));
            real = ImageIO.read(new File("./.testImages/testMarkersVertical"));
        } catch (IOException e) {
            fail(e.getMessage());
        }

        boolean outcome = bufferedImagesEqual(test, real);

        assertTrue(outcome);


    }

    @Test
    public void test_MarkersHorizontal() throws IOException {
        XYPlot plot = getPlot();
        plot.setOrientation(PlotOrientation.HORIZONTAL);
        XYItemRenderer renderer = plot.getRenderer();

        BufferedImage image = new BufferedImage(200 , 100,
                BufferedImage.TYPE_INT_RGB);
        // BufferedImage test  = new BufferedImage(200 , 100,
        //         BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = image.createGraphics();
        Rectangle2D area = new Rectangle(0, 0, 40, 40);

        Marker marker1 = new ValueMarker(2);
        Marker marker2 = new IntervalMarker(3,4);


        renderer.drawDomainMarker(g2, plot, plot.getRangeAxis(), marker1, area);
        renderer.drawDomainMarker(g2, plot, plot.getRangeAxis(), marker2, area);

        renderer.drawRangeMarker(g2, plot, plot.getRangeAxis(), marker1, area);
        renderer.drawRangeMarker(g2, plot, plot.getRangeAxis(), marker2, area);

        File file = new File("./.testImages/testMarkersVertical");
        file.delete();
        boolean result = ImageIO.write(image, "jpeg", file);


        BufferedImage test = null;
        BufferedImage real = null;
        try {
            test = ImageIO.read(new File("./.testImages/testMarkersVerticalOracle"));
            real = ImageIO.read(new File("./.testImages/testMarkersVertical"));
        } catch (IOException e) {
            fail(e.getMessage());
        }

        boolean outcome = bufferedImagesEqual(test, real);

        assertTrue(outcome);


    }

    @Test
    public void test_MarkersLabel() throws IOException {
        XYPlot plot = getPlot();
        plot.setOrientation(PlotOrientation.HORIZONTAL);
        XYItemRenderer renderer = plot.getRenderer();

        BufferedImage image = new BufferedImage(200 , 100,
                BufferedImage.TYPE_INT_RGB);
        // BufferedImage test  = new BufferedImage(200 , 100,
        //         BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = image.createGraphics();
        Rectangle2D area = new Rectangle(0, 0, 40, 40);

        Marker marker1 = new ValueMarker(2);
        Marker marker2 = new IntervalMarker(3,4);

        marker1.setLabel("TestLabel1");
        marker2.setLabel("TestLabel2");


        renderer.drawDomainMarker(g2, plot, plot.getRangeAxis(), marker1, area);
        renderer.drawDomainMarker(g2, plot, plot.getRangeAxis(), marker2, area);

        renderer.drawRangeMarker(g2, plot, plot.getRangeAxis(), marker1, area);
        renderer.drawRangeMarker(g2, plot, plot.getRangeAxis(), marker2, area);

        File file = new File("./.testImages/testMarkersLabel");
        file.delete();
        boolean result = ImageIO.write(image, "jpeg", file);


        BufferedImage test = null;
        BufferedImage real = null;
        try {
            test = ImageIO.read(new File("./.testImages/testMarkersLabelOracle"));
            real = ImageIO.read(new File("./.testImages/testMarkersLabel"));
        } catch (IOException e) {
            fail(e.getMessage());
        }

        boolean outcome = bufferedImagesEqual(test, real);

        assertTrue(outcome);


    }



    @Test
    public void testEquals_findRangeBounds(){
        XYBarRenderer r1 = new XYBarRenderer();
        r1.setDataBoundsIncludesVisibleSeriesOnly(false);

        Range range = r1.findRangeBounds(createDataset1());
        Range test = new Range(1, 3);

        assertEquals(range, test);

        r1.setDataBoundsIncludesVisibleSeriesOnly(true);

        range = r1.findRangeBounds(createDataset1());
        assertEquals(range, test);

        XYPlot plot = new XYPlot();
        r1.setPlot(plot);
        range = r1.findRangeBounds(createDataset1());
        assertEquals(range, test);

        plot.setRenderer(r1);
        range = r1.findRangeBounds(createDataset1());
        assertEquals(range, test);

        plot.setDataset(createDataset1());
        plot.setDomainAxis(new NumberAxis());
        range = r1.findRangeBounds(createDataset1());
        assertEquals(range, test);
    }


}
