/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2019, by Object Refinery Limited and Contributors.
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
 * --------------------
 * XYShapeRenderer.java
 * --------------------
 * (C) Copyright 2008-2019 by Andreas Haumer, xS+S and Contributors.
 *
 * Original Author:  Martin Hoeller (x Software + Systeme  xS+S - Andreas
 *                       Haumer);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes:
 * --------
 * 17-Sep-2008 : Version 1, based on a contribution from Martin Hoeller with
 *               amendments by David Gilbert (DG);
 * 16-Feb-2010 : Added findZBounds() (patch 2952086) (MH);
 * 19-Oct-2011 : Fixed NPE in findRangeBounds() (bug 3026341) (DG);
 * 03-Jul-2013 : Use ParamChecks (DG);
 * 09-Sep-2015 : Add update for crosshair (DG);
 * 18-Feb-2017 : Updates for crosshairs (bug #36) (DG);
 *
 */

package org.jfree.chart.renderer.xy;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.chart.renderer.PaintScale;
import org.jfree.chart.util.Args;
import org.jfree.chart.util.CloneUtils;
import org.jfree.chart.util.PublicCloneable;
import org.jfree.chart.util.SerialUtils;
import org.jfree.chart.util.ShapeUtils;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetUtils;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYZDataset;

/**
 * A renderer that draws shapes at (x, y) coordinates and, if the dataset
 * is an instance of {@link XYZDataset}, fills the shapes with a paint that
 * is based on the z-value (the paint is obtained from a lookup table).  The
 * renderer also allows for optional guidelines, horizontal and vertical lines
 * connecting the shape to the edges of the plot.
 * <br><br>
 * The example shown here is generated by the
 * {@code XYShapeRendererDemo1.java} program included in the JFreeChart
 * demo collection:
 * <br><br>
 * <img src="../../../../../images/XYShapeRendererSample.png"
 * alt="XYShapeRendererSample.png">
 * <br><br>
 * This renderer has similarities to, but also differences from, the
 * {@link XYLineAndShapeRenderer}.
 *
 * @since 1.0.11
 */
public class XYShapeRenderer extends AbstractXYItemRenderer
        implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {

    /** Auto generated serial version id. */
    private static final long serialVersionUID = 8320552104211173221L;

    /** The paint scale (never null). */
    private PaintScale paintScale;

    /** A flag that controls whether or not the shape outlines are drawn. */
    private boolean drawOutlines;

    /**
     * A flag that controls whether or not the outline paint is used (if not,
     * the regular paint is used).
     */
    private boolean useOutlinePaint;

    /**
     * A flag that controls whether or not the fill paint is used (if not,
     * the fill paint is used).
     */
    private boolean useFillPaint;

    /** Flag indicating if guide lines should be drawn for every item. */
    private boolean guideLinesVisible;

    /** The paint used for drawing the guide lines (never null). */
    private transient Paint guideLinePaint;

    /** The stroke used for drawing the guide lines (never null). */
    private transient Stroke guideLineStroke;

    /**
     * Creates a new {@code XYShapeRenderer} instance with default
     * attributes.
     */
    public XYShapeRenderer() {
        this.paintScale = new LookupPaintScale();
        this.useFillPaint = false;
        this.drawOutlines = false;
        this.useOutlinePaint = true;
        this.guideLinesVisible = false;
        this.guideLinePaint = Color.darkGray;
        this.guideLineStroke = new BasicStroke();
        this.getShapeManager().setDefaultShape(new Ellipse2D.Double(-5.0, -5.0, 10.0, 10.0));
        this.getShapeManager().setAutoPopulateSeriesShape(false);
    }

    /**
     * Returns the paint scale used by the renderer.
     *
     * @return The paint scale (never {@code null}).
     *
     * @see #setPaintScale(PaintScale)
     */
    public PaintScale getPaintScale() {
        return this.paintScale;
    }

    /**
     * Sets the paint scale used by the renderer and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param scale  the scale ({@code null} not permitted).
     *
     * @see #getPaintScale()
     */
    public void setPaintScale(PaintScale scale) {
        Args.nullNotPermitted(scale, "scale");
        this.paintScale = scale;
        this.getListenerManager().notifyListeners(new RendererChangeEvent(this));
    }

    /**
     * Returns {@code true} if outlines should be drawn for shapes, and
     * {@code false} otherwise.
     *
     * @return A boolean.
     *
     * @see #setDrawOutlines(boolean)
     */
    public boolean getDrawOutlines() {
        return this.drawOutlines;
    }

    /**
     * Sets the flag that controls whether outlines are drawn for
     * shapes, and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     * <P>
     * In some cases, shapes look better if they do NOT have an outline, but
     * this flag allows you to set your own preference.
     *
     * @param flag  the flag.
     *
     * @see #getDrawOutlines()
     */
    public void setDrawOutlines(boolean flag) {
        this.drawOutlines = flag;
        this.getListenerManager().fireChangeEvent();
    }

    /**
     * Returns {@code true} if the renderer should use the fill paint
     * setting to fill shapes, and {@code false} if it should just
     * use the regular paint.
     * <p>
     * Refer to {@code XYLineAndShapeRendererDemo2.java} to see the
     * effect of this flag.
     *
     * @return A boolean.
     *
     * @see #setUseFillPaint(boolean)
     * @see #getUseOutlinePaint()
     */
    public boolean getUseFillPaint() {
        return this.useFillPaint;
    }

    /**
     * Sets the flag that controls whether the fill paint is used to fill
     * shapes, and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     *
     * @param flag  the flag.
     *
     * @see #getUseFillPaint()
     */
    public void setUseFillPaint(boolean flag) {
        this.useFillPaint = flag;
        this.getListenerManager().fireChangeEvent();
    }

    /**
     * Returns the flag that controls whether the outline paint is used for
     * shape outlines.  If not, the regular series paint is used.
     *
     * @return A boolean.
     *
     * @see #setUseOutlinePaint(boolean)
     */
    public boolean getUseOutlinePaint() {
        return this.useOutlinePaint;
    }

    /**
     * Sets the flag that controls whether the outline paint is used for shape
     * outlines, and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     *
     * @param use  the flag.
     *
     * @see #getUseOutlinePaint()
     */
    public void setUseOutlinePaint(boolean use) {
        this.useOutlinePaint = use;
        this.getListenerManager().fireChangeEvent();
    }

    /**
     * Returns a flag that controls whether or not guide lines are drawn for
     * each data item (the lines are horizontal and vertical "crosshairs"
     * linking the data point to the axes).
     *
     * @return A boolean.
     *
     * @see #setGuideLinesVisible(boolean)
     */
    public boolean isGuideLinesVisible() {
        return this.guideLinesVisible;
    }

    /**
     * Sets the flag that controls whether or not guide lines are drawn for
     * each data item and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     *
     * @param visible  the new flag value.
     *
     * @see #isGuideLinesVisible()
     */
    public void setGuideLinesVisible(boolean visible) {
        this.guideLinesVisible = visible;
        this.getListenerManager().fireChangeEvent();
    }

    /**
     * Returns the paint used to draw the guide lines.
     *
     * @return The paint (never {@code null}).
     *
     * @see #setGuideLinePaint(Paint)
     */
    public Paint getGuideLinePaint() {
        return this.guideLinePaint;
    }

    /**
     * Sets the paint used to draw the guide lines and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param paint  the paint ({@code null} not permitted).
     *
     * @see #getGuideLinePaint()
     */
    public void setGuideLinePaint(Paint paint) {
        Args.nullNotPermitted(paint, "paint");
        this.guideLinePaint = paint;
        this.getListenerManager().fireChangeEvent();
    }

    /**
     * Returns the stroke used to draw the guide lines.
     *
     * @return The stroke.
     *
     * @see #setGuideLineStroke(Stroke)
     */
    public Stroke getGuideLineStroke() {
        return this.guideLineStroke;
    }

    /**
     * Sets the stroke used to draw the guide lines and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param stroke  the stroke ({@code null} not permitted).
     *
     * @see #getGuideLineStroke()
     */
    public void setGuideLineStroke(Stroke stroke) {
        Args.nullNotPermitted(stroke, "stroke");
        this.guideLineStroke = stroke;
        this.getListenerManager().fireChangeEvent();
    }

    /**
     * Returns the lower and upper bounds (range) of the x-values in the
     * specified dataset.
     *
     * @param dataset  the dataset ({@code null} permitted).
     *
     * @return The range ({@code null} if the dataset is {@code null}
     *         or empty).
     */
    @Override
    public Range findDomainBounds(XYDataset dataset) {
        if (dataset == null) {
            return null;
        }
        Range r = DatasetUtils.findDomainBounds(dataset, false);
        if (r == null) {
            return null;
        }
        double offset = 0; // TODO getSeriesShape(n).getBounds().width / 2;
        return new Range(r.getLowerBound() + offset,
                         r.getUpperBound() + offset);
    }

    /**
     * Returns the range of values the renderer requires to display all the
     * items from the specified dataset.
     *
     * @param dataset  the dataset ({@code null} permitted).
     *
     * @return The range ({@code null} if the dataset is {@code null}
     *         or empty).
     */
    @Override
    public Range findRangeBounds(XYDataset dataset) {
        if (dataset == null) {
            return null;
        }
        Range r = DatasetUtils.findRangeBounds(dataset, false);
        if (r == null) {
            return null;
        }
        double offset = 0; // TODO getSeriesShape(n).getBounds().height / 2;
        return new Range(r.getLowerBound() + offset, r.getUpperBound()
                + offset);
    }

    /**
     * Return the range of z-values in the specified dataset.
     *  
     * @param dataset  the dataset ({@code null} permitted).
     * 
     * @return The range ({@code null} if the dataset is {@code null}
     *         or empty).
     */
    public Range findZBounds(XYZDataset dataset) {
        if (dataset != null) {
            return DatasetUtils.findZBounds(dataset);
        } else {
            return null;
        }
    }

    /**
     * Returns the number of passes required by this renderer.
     *
     * @return {@code 2}.
     */
    @Override
    public int getPassCount() {
        return 2;
    }

    /**
     * Draws the block representing the specified item.
     *
     * @param g2  the graphics device.
     * @param state  the state.
     * @param dataArea  the data area.
     * @param info  the plot rendering info.
     * @param plot  the plot.
     * @param domainAxis  the x-axis.
     * @param rangeAxis  the y-axis.
     * @param dataset  the dataset.
     * @param series  the series index.
     * @param item  the item index.
     * @param crosshairState  the crosshair state.
     * @param pass  the pass index.
     */
    @Override
    public void drawItem(Graphics2D g2, XYItemRendererState state,
            Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot,
            ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset,
            int series, int item, CrosshairState crosshairState, int pass) {

        Shape hotspot;
        EntityCollection entities = null;
        if (info != null) {
            entities = info.getOwner().getEntityCollection();
        }

        double x = dataset.getXValue(series, item);
        double y = dataset.getYValue(series, item);
        if (Double.isNaN(x) || Double.isNaN(y)) {
            // can't draw anything
            return;
        }

        double transX = domainAxis.valueToJava2D(x, dataArea,
                plot.getDomainAxisEdge());
        double transY = rangeAxis.valueToJava2D(y, dataArea,
                plot.getRangeAxisEdge());

        PlotOrientation orientation = plot.getOrientation();

        // draw optional guide lines
        if ((pass == 0) && this.guideLinesVisible) {
            g2.setStroke(this.guideLineStroke);
            g2.setPaint(this.guideLinePaint);
            if (orientation == PlotOrientation.HORIZONTAL) {
                g2.draw(new Line2D.Double(transY, dataArea.getMinY(), transY,
                        dataArea.getMaxY()));
                g2.draw(new Line2D.Double(dataArea.getMinX(), transX,
                        dataArea.getMaxX(), transX));
            } else {
                g2.draw(new Line2D.Double(transX, dataArea.getMinY(), transX,
                        dataArea.getMaxY()));
                g2.draw(new Line2D.Double(dataArea.getMinX(), transY,
                        dataArea.getMaxX(), transY));
            }
        } else if (pass == 1) {
            Shape shape = this.getShapeManager().getItemShape(series, item);
            if (orientation == PlotOrientation.HORIZONTAL) {
                shape = ShapeUtils.createTranslatedShape(shape, transY,
                        transX);
            } else if (orientation == PlotOrientation.VERTICAL) {
                shape = ShapeUtils.createTranslatedShape(shape, transX,
                        transY);
            }
            hotspot = shape;
            if (shape.intersects(dataArea)) {
                //if (getItemShapeFilled(series, item)) {
                    g2.setPaint(getPaint(dataset, series, item));
                    g2.fill(shape);
               //}
                if (this.drawOutlines) {
                    if (getUseOutlinePaint()) {
                        g2.setPaint(paintManager.getItemOutlinePaint(series, item));
                    } else {
                        g2.setPaint(paintManager.getItemPaint(series, item));
                    }
                    g2.setStroke(getItemOutlineStroke(series, item));
                    g2.draw(shape);
                }
            }
            
            int datasetIndex = plot.indexOf(dataset);
            updateCrosshairValues(crosshairState, x, y, datasetIndex,
                    transX, transY, orientation);

            // add an entity for the item...
            if (entities != null) {
                addEntity(entities, hotspot, dataset, series, item, 0.0, 0.0);
            }
        }
    }

    /**
     * Get the paint for a given series and item from a dataset.
     *
     * @param dataset  the dataset.
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The paint.
     */
    protected Paint getPaint(XYDataset dataset, int series, int item) {
        Paint p;
        if (dataset instanceof XYZDataset) {
            double z = ((XYZDataset) dataset).getZValue(series, item);
            p = this.paintScale.getPaint(z);
        } else {
            if (this.useFillPaint) {
                p = paintManager.getItemFillPaint(series, item);
            }
            else {
                p = paintManager.getItemPaint(series, item);
            }
        }
        return p;
    }

    /**
     * Tests this instance for equality with an arbitrary object.  This method
     * returns {@code true} if and only if:
     * <ul>
     * <li>{@code obj} is an instance of {@code XYShapeRenderer} (not
     *     {@code null});</li>
     * <li>{@code obj} has the same field values as this
     *     {@code XYShapeRenderer};</li>
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
        if (!(obj instanceof XYShapeRenderer)) {
            return false;
        }
        XYShapeRenderer that = (XYShapeRenderer) obj;
        if (!this.paintScale.equals(that.paintScale)) {
            return false;
        }
        if (this.drawOutlines != that.drawOutlines) {
            return false;
        }
        if (this.useOutlinePaint != that.useOutlinePaint) {
            return false;
        }
        if (this.useFillPaint != that.useFillPaint) {
            return false;
        }
        if (this.guideLinesVisible != that.guideLinesVisible) {
            return false;
        }
        if (!this.guideLinePaint.equals(that.guideLinePaint)) {
            return false;
        }
        if (!this.guideLineStroke.equals(that.guideLineStroke)) {
            return false;
        }
        return super.equals(obj);
    }

    /**
     * Returns a clone of this renderer.
     *
     * @return A clone of this renderer.
     *
     * @throws CloneNotSupportedException if there is a problem creating the
     *     clone.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        XYShapeRenderer clone = (XYShapeRenderer) super.clone();
        clone.paintScale = (PaintScale) CloneUtils.clone(this.paintScale);
        return clone;
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.guideLinePaint = SerialUtils.readPaint(stream);
        this.guideLineStroke = SerialUtils.readStroke(stream);
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException  if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtils.writePaint(this.guideLinePaint, stream);
        SerialUtils.writeStroke(this.guideLineStroke, stream);
    }

}
