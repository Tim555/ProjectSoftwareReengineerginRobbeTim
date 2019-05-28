package org.jfree.chart.renderer;

import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.chart.util.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ShapeManager implements Cloneable, Serializable {

    /** The default shape. */
    public static final Shape DEFAULT_SHAPE
            = new Rectangle2D.Double(-3.0, -3.0, 6.0, 6.0);


    /** A shape list. */
    private ShapeList shapeList;

    /**
     * A flag that controls whether or not the shapeList is auto-populated
     * in the {@link #lookupSeriesShape(int)} method.
     *
     * @since 1.0.6
     */
    private boolean autoPopulateSeriesShape;

    /** The base shape. */
    private transient Shape defaultShape;

    /**
     * The per-series legend shape settings.
     *
     * @since 1.0.11
     */
    private ShapeList legendShapeList;

    /**
     * The base shape for legend items.  If this is {@code null}, the
     * series shape will be used.
     *
     * @since 1.0.11
     */
    private transient Shape defaultLegendShape;

    /**
     * Abstractrenderer
     */
    private ListenerManager listenerManager;

    /**
     * drawingSupplier
     */
    private DrawingSupplier drawingSupplier;

    /**
     * A special flag that, if true, will cause the getLegendItem() method
     * to configure the legend shape as if it were a line.
     *
     * @since 1.0.14
     */
    private boolean treatLegendShapeAsLine;

    public ShapeManager(ListenerManager listenerManager, DrawingSupplier drawingSupplier) {

        this.shapeList = new ShapeList();
        this.defaultShape = DEFAULT_SHAPE;
        this.autoPopulateSeriesShape = true;

        this.legendShapeList = new ShapeList();
        this.defaultLegendShape = null;

        this.treatLegendShapeAsLine = false;
        this.treatLegendShapeAsLine = false;

        this.listenerManager = listenerManager;
        this.drawingSupplier = drawingSupplier;
    }

    // SHAPE

    /**
     * Returns a shape used to represent a data item.
     * <p>
     * The default implementation passes control to the
     * {@link #lookupSeriesShape(int)} method. You can override this method if
     * you require different behaviour.
     *
     * @param row  the row (or series) index (zero-based).
     * @param column  the column (or category) index (zero-based).
     *
     * @return The shape (never {@code null}).
     */
    public Shape getItemShape(int row, int column) {
        return lookupSeriesShape(row);
    }

    /**
     * Returns a shape used to represent the items in a series.
     *
     * @param series  the series (zero-based index).
     *
     * @return The shape (never {@code null}).
     *
     * @since 1.0.6
     */
    public Shape lookupSeriesShape(int series) {

        Shape result = getSeriesShape(series);
        if (result == null && this.autoPopulateSeriesShape) {
            DrawingSupplier supplier = this.drawingSupplier;
            if (supplier != null) {
                result = supplier.getNextShape();
                setSeriesShape(series, result, false);
            }
        }
        if (result == null) {
            result = this.defaultShape;
        }
        return result;

    }

    /**
     * Returns a shape used to represent the items in a series.
     *
     * @param series  the series (zero-based index).
     *
     * @return The shape (possibly {@code null}).
     *
     * @see #setSeriesShape(int, Shape)
     */
    public Shape getSeriesShape(int series) {
        return this.shapeList.getShape(series);
    }

    /**
     * Sets the shape used for a series and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param shape  the shape ({@code null} permitted).
     *
     * @see #getSeriesShape(int)
     */
    public void setSeriesShape(int series, Shape shape) {
        setSeriesShape(series, shape, true);
    }

    /**
     * Sets the shape for a series and, if requested, sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero based).
     * @param shape  the shape ({@code null} permitted).
     * @param notify  notify listeners?
     *
     * @see #getSeriesShape(int)
     */
    public void setSeriesShape(int series, Shape shape, boolean notify) {
        this.shapeList.setShape(series, shape);
        if (notify) {
            this.listenerManager.fireChangeEvent();
        }
    }

    /**
     * Returns the default shape.
     *
     * @return The shape (never {@code null}).
     *
     * @see #setDefaultShape(Shape)
     */
    public Shape getDefaultShape() {
        return this.defaultShape;
    }

    /**
     * Sets the default shape and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     *
     * @param shape  the shape ({@code null} not permitted).
     *
     * @see #getDefaultShape()
     */
    public void setDefaultShape(Shape shape) {
        // defer argument checking...
        setDefaultShape(shape, true);
    }

    /**
     * Sets the default shape and, if requested, sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param shape  the shape ({@code null} not permitted).
     * @param notify  notify listeners?
     *
     * @see #getDefaultShape()
     */
    public void setDefaultShape(Shape shape, boolean notify) {
        Args.nullNotPermitted(shape, "shape");
        this.defaultShape = shape;
        if (notify) {
            this.listenerManager.fireChangeEvent();
        }
    }

    /**
     * Returns the flag that controls whether or not the series shape list is
     * automatically populated when {@link #lookupSeriesShape(int)} is called.
     *
     * @return A boolean.
     *
     * @since 1.0.6
     *
     * @see #setAutoPopulateSeriesShape(boolean)
     */
    public boolean getAutoPopulateSeriesShape() {
        return this.autoPopulateSeriesShape;
    }

    /**
     * Sets the flag that controls whether or not the series shape list is
     * automatically populated when {@link #lookupSeriesShape(int)} is called.
     *
     * @param auto  the new flag value.
     *
     * @since 1.0.6
     *
     * @see #getAutoPopulateSeriesShape()
     */
    public void setAutoPopulateSeriesShape(boolean auto) {
        this.autoPopulateSeriesShape = auto;
    }


    /**
     * Performs a lookup for the legend shape.
     *
     * @param series  the series index.
     *
     * @return The shape (possibly {@code null}).
     *
     * @since 1.0.11
     */
    public Shape lookupLegendShape(int series) {
        Shape result = getLegendShape(series);
        if (result == null) {
            result = this.defaultLegendShape;
        }
        if (result == null) {
            result = lookupSeriesShape(series);
        }
        return result;
    }

    /**
     * Returns the legend shape defined for the specified series (possibly
     * {@code null}).
     *
     * @param series  the series index.
     *
     * @return The shape (possibly {@code null}).
     *
     * @see #lookupLegendShape(int)
     *
     * @since 1.0.11
     */
    public Shape getLegendShape(int series) {
        return this.legendShapeList.getShape(series);
    }

    /**
     * Sets the shape used for the legend item for the specified series, and
     * sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index.
     * @param shape  the shape ({@code null} permitted).
     *
     * @since 1.0.11
     */
    public void setLegendShape(int series, Shape shape) {
        this.legendShapeList.setShape(series, shape);
        this.listenerManager.fireChangeEvent();
    }

    /**
     * Returns the default legend shape, which may be {@code null}.
     *
     * @return The default legend shape.
     *
     * @since 1.0.11
     */
    public Shape getDefaultLegendShape() {
        return this.defaultLegendShape;
    }

    /**
     * Set Shapelist
     */
    public void setShapeList(ShapeList shapeList) {
        this.shapeList = shapeList;
    }


    /**
     * Sets the default legend shape and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param shape  the shape ({@code null} permitted).
     *
     * @since 1.0.11
     */
    public void setDefaultLegendShape(Shape shape) {
        this.defaultLegendShape = shape;
        this.listenerManager.fireChangeEvent();
    }

    /**
     * Returns the flag that controls whether or not the legend shape is
     * treated as a line when creating legend items.
     *
     * @return A boolean.
     *
     * @since 1.0.14
     */
    public boolean getTreatLegendShapeAsLine() {
        return this.treatLegendShapeAsLine;
    }

    /**
     * Sets the flag that controls whether or not the legend shape is
     * treated as a line when creating legend items.
     *
     * @param treatAsLine  the new flag value.
     *
     * @since 1.0.14
     */
    public void setTreatLegendShapeAsLine(boolean treatAsLine) {
        if (this.treatLegendShapeAsLine != treatAsLine) {
            this.treatLegendShapeAsLine = treatAsLine;
            this.listenerManager.fireChangeEvent();
        }
    }

    public ShapeList getShapeList() {
        return this.shapeList;
    }

    public ShapeList getLegendShapeList() {
        return this.legendShapeList;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ShapeManager)) {
            return false;
        }
        ShapeManager that = (ShapeManager) obj;

        if (this.treatLegendShapeAsLine != that.getTreatLegendShapeAsLine()) {
            return false;
        }
        if (!ObjectUtils.equal(this.shapeList, that.shapeList)) {
            return false;
        }
        if (!ShapeUtils.equal(this.defaultShape, that.defaultShape)) {
            return false;
        }
        if (!ObjectUtils.equal(this.legendShapeList,
                that.legendShapeList)) {
            return false;
        }
        if (!ShapeUtils.equal(this.defaultLegendShape,
                that.defaultLegendShape)) {
            return false;
        }

        return true;
    }

    /**
     * Compare To
     */
    public boolean compareTo(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ShapeManager)) {
            return false;
        }
        ShapeManager that = (ShapeManager) obj;

        if (this.treatLegendShapeAsLine != that.treatLegendShapeAsLine) {
            return false;
        }
        if (!ObjectUtils.equal(this.getShapeList(), that.shapeList)) {
            return false;
        }
        if (!ShapeUtils.equal(this.defaultShape, that.defaultShape)) {
            return false;
        }
        if (!ObjectUtils.equal(this.legendShapeList,
                that.legendShapeList)) {
            return false;
        }
        if (!ShapeUtils.equal(this.defaultLegendShape,
                that.defaultLegendShape)) {
            return false;
        }

        return true;
    }


    /**
     * Returns an independent copy of the ShapeManager.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if some component of the renderer
     *         does not support cloning.
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        ShapeManager clone = (ShapeManager) super.clone();

        if (this.shapeList != null) {
            clone.shapeList = (ShapeList) this.shapeList.clone();
        }
        if (this.defaultShape != null) {
            clone.defaultShape = ShapeUtils.clone(this.defaultShape);
        }
        if (this.legendShapeList != null) {
            clone.legendShapeList = (ShapeList) this.legendShapeList.clone();
        }

        return clone;
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
        SerialUtils.writeShape(this.defaultShape, stream);
        SerialUtils.writeShape(this.defaultLegendShape, stream);
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
        this.defaultShape = SerialUtils.readShape(stream);
        this.defaultLegendShape = SerialUtils.readShape(stream);
    }
}
