package org.jfree.chart.renderer;

import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.util.*;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PaintManager implements Serializable {
    private final AbstractRenderer abstractRenderer;
    /**
     * The default paint.
     */
    public static final Paint DEFAULT_PAINT = Color.BLUE;
    /**
     * The default outline paint.
     */
    public static final Paint DEFAULT_OUTLINE_PAINT = Color.GRAY;
    /**
     * The default value label paint.
     */
    public static final Paint DEFAULT_VALUE_LABEL_PAINT = Color.BLACK;

    /**
     * The paint list.
     */
    public PaintList paintList;
    /**
     * A flag that controls whether or not the paintList is auto-populated
     * in the {@link #lookupSeriesPaint(int)} method.
     *
     * @since 1.0.6
     */
    public boolean autoPopulateSeriesPaint;
    /**
     * The default paint, used when there is no paint assigned for a series.
     */
    public transient Paint defaultPaint;
    /**
     * The fill paint list.
     */
    public PaintList fillPaintList;
    /**
     * A flag that controls whether or not the fillPaintList is auto-populated
     * in the {@link #lookupSeriesFillPaint(int)} method.
     *
     * @since 1.0.6
     */
    public boolean autoPopulateSeriesFillPaint;
    /**
     * The base fill paint.
     */
    public transient Paint defaultFillPaint;
    /**
     * The outline paint list.
     */
    public PaintList outlinePaintList;
    /**
     * A flag that controls whether or not the outlinePaintList is
     * auto-populated in the {@link #lookupSeriesOutlinePaint(int)} method.
     *
     * @since 1.0.6
     */
    public boolean autoPopulateSeriesOutlinePaint;
    /**
     * The base outline paint.
     */
    public transient Paint defaultOutlinePaint;
    /**
     * The item label paint list (one paint per series).
     */
    public PaintList itemLabelPaintList;
    /**
     * The base item label paint.
     */
    public transient Paint defaultItemLabelPaint;
    /**
     * The per series legend text paint settings.
     *
     * @since 1.0.11
     */
    public PaintList legendTextPaint;
    /**
     * The default paint for the legend text items (if this is
     * {@code null}, the {@link LegendTitle} class will determine the
     * text paint to use.
     *
     * @since 1.0.11
     */
    public transient Paint defaultLegendTextPaint;

    public PaintManager(AbstractRenderer abstractRenderer) {
        this.abstractRenderer = abstractRenderer;


        this.paintList = new PaintList();
        this.defaultPaint = DEFAULT_PAINT;
        this.autoPopulateSeriesPaint = true;

        this.fillPaintList = new PaintList();
        this.defaultFillPaint = Color.WHITE;
        this.autoPopulateSeriesFillPaint = false;

        this.outlinePaintList = new PaintList();
        this.defaultOutlinePaint = DEFAULT_OUTLINE_PAINT;
        this.autoPopulateSeriesOutlinePaint = false;

        this.itemLabelPaintList = new PaintList();
        this.defaultItemLabelPaint = Color.BLACK;

        this.legendTextPaint = new PaintList();
        this.defaultLegendTextPaint = null;
    }

    /**
     * Returns the paint used to fill data items as they are drawn.
     * (this is typically the same for an entire series).
     * <p>
     * The default implementation passes control to the
     * {@code lookupSeriesPaint()} method. You can override this method
     * if you require different behaviour.
     *
     * @param row    the row (or series) index (zero-based).
     * @param column the column (or category) index (zero-based).
     * @return The paint (never {@code null}).
     */
    public Paint getItemPaint(int row, int column) {
        return lookupSeriesPaint(row);
    }

    /**
     * Returns the paint used to fill an item drawn by the renderer.
     *
     * @param series the series index (zero-based).
     * @return The paint (never {@code null}).
     * @since 1.0.6
     */
    public Paint lookupSeriesPaint(int series) {

        Paint seriesPaint = getSeriesPaint(series);
        if (seriesPaint == null && this.autoPopulateSeriesPaint) {
            DrawingSupplier supplier = abstractRenderer.getDrawingSupplier();
            if (supplier != null) {
                seriesPaint = supplier.getNextPaint();
                setSeriesPaint(series, seriesPaint, false);
            }
        }
        if (seriesPaint == null) {
            seriesPaint = this.defaultPaint;
        }
        return seriesPaint;

    }

    /**
     * Returns the paint used to fill an item drawn by the renderer.
     *
     * @param series the series index (zero-based).
     * @return The paint (possibly {@code null}).
     * @see #setSeriesPaint(int, Paint)
     */
    public Paint getSeriesPaint(int series) {
        return this.paintList.getPaint(series);
    }

    /**
     * Sets the paint used for a series and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint ({@code null} permitted).
     * @see #getSeriesPaint(int)
     */
    public void setSeriesPaint(int series, Paint paint) {
        setSeriesPaint(series, paint, true);
    }

    /**
     * Sets the paint used for a series and, if requested, sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series index.
     * @param paint  the paint ({@code null} permitted).
     * @param notify notify listeners?
     * @see #getSeriesPaint(int)
     */
    public void setSeriesPaint(int series, Paint paint, boolean notify) {
        this.paintList.setPaint(series, paint);
        if (notify) {
            abstractRenderer.getListenerManager().fireChangeEvent();
        }
    }

    /**
     * Clears the series paint settings for this renderer and, if requested,
     * sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param notify notify listeners?
     * @since 1.0.11
     */
    public void clearSeriesPaints(boolean notify) {
        this.paintList.clear();
        if (notify) {
            abstractRenderer.getListenerManager().fireChangeEvent();
        }
    }

    /**
     * Returns the default paint.
     *
     * @return The default paint (never {@code null}).
     * @see #setDefaultPaint(Paint)
     */
    public Paint getDefaultPaint() {
        return this.defaultPaint;
    }

    /**
     * Sets the default paint and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     *
     * @param paint the paint ({@code null} not permitted).
     * @see #getDefaultPaint()
     */
    public void setDefaultPaint(Paint paint) {
        // defer argument checking...
        setDefaultPaint(paint, true);
    }

    /**
     * Sets the default paint and, if requested, sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param paint  the paint ({@code null} not permitted).
     * @param notify notify listeners?
     * @see #getDefaultPaint()
     */
    public void setDefaultPaint(Paint paint, boolean notify) {
        this.defaultPaint = paint;
        if (notify) {
            abstractRenderer.getListenerManager().fireChangeEvent();
        }
    }

    /**
     * Returns the flag that controls whether or not the series paint list is
     * automatically populated when {@link #lookupSeriesPaint(int)} is called.
     *
     * @return A boolean.
     * @see #setAutoPopulateSeriesPaint(boolean)
     * @since 1.0.6
     */
    public boolean getAutoPopulateSeriesPaint() {
        return this.autoPopulateSeriesPaint;
    }

    /**
     * Sets the flag that controls whether or not the series paint list is
     * automatically populated when {@link #lookupSeriesPaint(int)} is called.
     *
     * @param auto the new flag value.
     * @see #getAutoPopulateSeriesPaint()
     * @since 1.0.6
     */
    public void setAutoPopulateSeriesPaint(boolean auto) {
        this.autoPopulateSeriesPaint = auto;
    }

    /**
     * Returns the paint used to fill data items as they are drawn.  The
     * default implementation passes control to the
     * {@link #lookupSeriesFillPaint(int)} method - you can override this
     * method if you require different behaviour.
     *
     * @param row    the row (or series) index (zero-based).
     * @param column the column (or category) index (zero-based).
     * @return The paint (never {@code null}).
     */
    public Paint getItemFillPaint(int row, int column) {
        return lookupSeriesFillPaint(row);
    }

    /**
     * Returns the paint used to fill an item drawn by the renderer.
     *
     * @param series the series (zero-based index).
     * @return The paint (never {@code null}).
     * @since 1.0.6
     */
    public Paint lookupSeriesFillPaint(int series) {

        Paint seriesFillPaint = getSeriesFillPaint(series);
        if (seriesFillPaint == null && this.autoPopulateSeriesFillPaint) {
            DrawingSupplier supplier = abstractRenderer.getDrawingSupplier();
            if (supplier != null) {
                seriesFillPaint = supplier.getNextFillPaint();
                setSeriesFillPaint(series, seriesFillPaint, false);
            }
        }
        if (seriesFillPaint == null) {
            seriesFillPaint = this.defaultFillPaint;
        }
        return seriesFillPaint;

    }

    /**
     * Returns the paint used to fill an item drawn by the renderer.
     *
     * @param series the series (zero-based index).
     * @return The paint (never {@code null}).
     * @see #setSeriesFillPaint(int, Paint)
     */
    public Paint getSeriesFillPaint(int series) {
        return this.fillPaintList.getPaint(series);
    }

    /**
     * Sets the paint used for a series fill and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint ({@code null} permitted).
     * @see #getSeriesFillPaint(int)
     */
    public void setSeriesFillPaint(int series, Paint paint) {
        setSeriesFillPaint(series, paint, true);
    }

    /**
     * Sets the paint used to fill a series and, if requested,
     * sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint ({@code null} permitted).
     * @param notify notify listeners?
     * @see #getSeriesFillPaint(int)
     */
    public void setSeriesFillPaint(int series, Paint paint, boolean notify) {
        this.fillPaintList.setPaint(series, paint);
        if (notify) {
            abstractRenderer.getListenerManager().fireChangeEvent();
        }
    }

    /**
     * Returns the default fill paint.
     *
     * @return The paint (never {@code null}).
     * @see #setDefaultFillPaint(Paint)
     */
    public Paint getDefaultFillPaint() {
        return this.defaultFillPaint;
    }

    /**
     * Sets the default fill paint and sends a {@link RendererChangeEvent} to
     * all registered listeners.
     *
     * @param paint the paint ({@code null} not permitted).
     * @see #getDefaultFillPaint()
     */
    public void setDefaultFillPaint(Paint paint) {
        // defer argument checking...
        setDefaultFillPaint(paint, true);
    }

    /**
     * Sets the default fill paint and, if requested, sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param paint  the paint ({@code null} not permitted).
     * @param notify notify listeners?
     * @see #getDefaultFillPaint()
     */
    public void setDefaultFillPaint(Paint paint, boolean notify) {
        Args.nullNotPermitted(paint, "paint");
        this.defaultFillPaint = paint;
        if (notify) {
            abstractRenderer.getListenerManager().fireChangeEvent();
        }
    }

    /**
     * Returns the flag that controls whether or not the series fill paint list
     * is automatically populated when {@link #lookupSeriesFillPaint(int)} is
     * called.
     *
     * @return A boolean.
     * @see #setAutoPopulateSeriesFillPaint(boolean)
     * @since 1.0.6
     */
    public boolean getAutoPopulateSeriesFillPaint() {
        return this.autoPopulateSeriesFillPaint;
    }

    /**
     * Sets the flag that controls whether or not the series fill paint list is
     * automatically populated when {@link #lookupSeriesFillPaint(int)} is
     * called.
     *
     * @param auto the new flag value.
     * @see #getAutoPopulateSeriesFillPaint()
     * @since 1.0.6
     */
    public void setAutoPopulateSeriesFillPaint(boolean auto) {
        this.autoPopulateSeriesFillPaint = auto;
    }

    /**
     * Returns the paint used to outline data items as they are drawn.
     * (this is typically the same for an entire series).
     * <p>
     * The default implementation passes control to the
     * {@link #lookupSeriesOutlinePaint} method.  You can override this method
     * if you require different behaviour.
     *
     * @param row    the row (or series) index (zero-based).
     * @param column the column (or category) index (zero-based).
     * @return The paint (never {@code null}).
     */
    public Paint getItemOutlinePaint(int row, int column) {
        return lookupSeriesOutlinePaint(row);
    }

    /**
     * Returns the paint used to outline an item drawn by the renderer.
     *
     * @param series the series (zero-based index).
     * @return The paint (never {@code null}).
     * @since 1.0.6
     */
    public Paint lookupSeriesOutlinePaint(int series) {

        Paint seriesOutlinePaint = getSeriesOutlinePaint(series);
        if (seriesOutlinePaint == null && this.autoPopulateSeriesOutlinePaint) {
            DrawingSupplier supplier = abstractRenderer.getDrawingSupplier();
            if (supplier != null) {
                seriesOutlinePaint = supplier.getNextOutlinePaint();
                setSeriesOutlinePaint(series, seriesOutlinePaint, false);
            }
        }
        if (seriesOutlinePaint == null) {
            seriesOutlinePaint = this.defaultOutlinePaint;
        }
        return seriesOutlinePaint;

    }

    /**
     * Returns the paint used to outline an item drawn by the renderer.
     *
     * @param series the series (zero-based index).
     * @return The paint (possibly {@code null}).
     * @see #setSeriesOutlinePaint(int, Paint)
     */
    public Paint getSeriesOutlinePaint(int series) {
        return this.outlinePaintList.getPaint(series);
    }

    /**
     * Sets the paint used for a series outline and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint ({@code null} permitted).
     * @see #getSeriesOutlinePaint(int)
     */
    public void setSeriesOutlinePaint(int series, Paint paint) {
        setSeriesOutlinePaint(series, paint, true);
    }

    /**
     * Sets the paint used to draw the outline for a series and, if requested,
     * sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint ({@code null} permitted).
     * @param notify notify listeners?
     * @see #getSeriesOutlinePaint(int)
     */
    public void setSeriesOutlinePaint(int series, Paint paint, boolean notify) {
        this.outlinePaintList.setPaint(series, paint);
        if (notify) {
            abstractRenderer.getListenerManager().fireChangeEvent();
        }
    }

    /**
     * Returns the default outline paint.
     *
     * @return The paint (never {@code null}).
     * @see #setDefaultOutlinePaint(Paint)
     */
    public Paint getDefaultOutlinePaint() {
        return this.defaultOutlinePaint;
    }

    /**
     * Sets the default outline paint and sends a {@link RendererChangeEvent} to
     * all registered listeners.
     *
     * @param paint the paint ({@code null} not permitted).
     * @see #getDefaultOutlinePaint()
     */
    public void setDefaultOutlinePaint(Paint paint) {
        // defer argument checking...
        setDefaultOutlinePaint(paint, true);
    }

    /**
     * Sets the default outline paint and, if requested, sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param paint  the paint ({@code null} not permitted).
     * @param notify notify listeners?
     * @see #getDefaultOutlinePaint()
     */
    public void setDefaultOutlinePaint(Paint paint, boolean notify) {
        Args.nullNotPermitted(paint, "paint");
        this.defaultOutlinePaint = paint;
        if (notify) {
            abstractRenderer.getListenerManager().fireChangeEvent();
        }
    }

    /**
     * Returns the flag that controls whether or not the series outline paint
     * list is automatically populated when
     * {@link #lookupSeriesOutlinePaint(int)} is called.
     *
     * @return A boolean.
     * @see #setAutoPopulateSeriesOutlinePaint(boolean)
     * @since 1.0.6
     */
    public boolean getAutoPopulateSeriesOutlinePaint() {
        return this.autoPopulateSeriesOutlinePaint;
    }

    /**
     * Sets the flag that controls whether or not the series outline paint list
     * is automatically populated when {@link #lookupSeriesOutlinePaint(int)}
     * is called.
     *
     * @param auto the new flag value.
     * @see #getAutoPopulateSeriesOutlinePaint()
     * @since 1.0.6
     */
    public void setAutoPopulateSeriesOutlinePaint(boolean auto) {
        this.autoPopulateSeriesOutlinePaint = auto;
    }

    /**
     * Returns the paint used to draw an item label.
     *
     * @param row    the row index (zero based).
     * @param column the column index (zero based).
     * @return The paint (never {@code null}).
     */
    public Paint getItemLabelPaint(int row, int column) {
        Paint result = getSeriesItemLabelPaint(row);
        if (result == null) {
            result = this.defaultItemLabelPaint;
        }
        return result;
    }

    /**
     * Returns the paint used to draw the item labels for a series.
     *
     * @param series the series index (zero based).
     * @return The paint (possibly {@code null}).
     * @see #setSeriesItemLabelPaint(int, Paint)
     */
    public Paint getSeriesItemLabelPaint(int series) {
        return this.itemLabelPaintList.getPaint(series);
    }

    /**
     * Sets the item label paint for a series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series (zero based index).
     * @param paint  the paint ({@code null} permitted).
     * @see #getSeriesItemLabelPaint(int)
     */
    public void setSeriesItemLabelPaint(int series, Paint paint) {
        setSeriesItemLabelPaint(series, paint, true);
    }

    /**
     * Sets the item label paint for a series and, if requested, sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series index (zero based).
     * @param paint  the paint ({@code null} permitted).
     * @param notify a flag that controls whether or not listeners are
     *               notified.
     * @see #getSeriesItemLabelPaint(int)
     */
    public void setSeriesItemLabelPaint(int series, Paint paint,
                                        boolean notify) {
        this.itemLabelPaintList.setPaint(series, paint);
        if (notify) {
            abstractRenderer.getListenerManager().fireChangeEvent();
        }
    }

    /**
     * Returns the default item label paint.
     *
     * @return The paint (never {@code null}).
     * @see #setDefaultItemLabelPaint(Paint)
     */
    public Paint getDefaultItemLabelPaint() {
        return this.defaultItemLabelPaint;
    }

    /**
     * Sets the default item label paint and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param paint the paint ({@code null} not permitted).
     * @see #getDefaultItemLabelPaint()
     */
    public void setDefaultItemLabelPaint(Paint paint) {
        // defer argument checking...
        setDefaultItemLabelPaint(paint, true);
    }

    /**
     * Sets the default item label paint and, if requested, sends a
     * {@link RendererChangeEvent} to all registered listeners..
     *
     * @param paint  the paint ({@code null} not permitted).
     * @param notify a flag that controls whether or not listeners are
     *               notified.
     * @see #getDefaultItemLabelPaint()
     */
    public void setDefaultItemLabelPaint(Paint paint, boolean notify) {
        Args.nullNotPermitted(paint, "paint");
        this.defaultItemLabelPaint = paint;
        if (notify) {
            abstractRenderer.getListenerManager().fireChangeEvent();
        }
    }

    /**
     * Performs a lookup for the legend text paint.
     *
     * @param series the series index.
     * @return The paint (possibly {@code null}).
     * @since 1.0.11
     */
    public Paint lookupLegendTextPaint(int series) {
        Paint result = getLegendTextPaint(series);
        if (result == null) {
            result = this.defaultLegendTextPaint;
        }
        return result;
    }

    /**
     * Returns the legend text paint defined for the specified series (possibly
     * {@code null}).
     *
     * @param series the series index.
     * @return The paint (possibly {@code null}).
     * @see #lookupLegendTextPaint(int)
     * @since 1.0.11
     */
    public Paint getLegendTextPaint(int series) {
        return this.legendTextPaint.getPaint(series);
    }

    /**
     * Sets the paint used for the legend text for the specified series, and
     * sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series index.
     * @param paint  the paint ({@code null} permitted).
     * @since 1.0.11
     */
    public void setLegendTextPaint(int series, Paint paint) {
        this.legendTextPaint.setPaint(series, paint);
        abstractRenderer.getListenerManager().fireChangeEvent();
    }

    /**
     * Returns the default legend text paint, which may be {@code null}.
     *
     * @return The default legend text paint.
     * @since 1.0.11
     */
    public Paint getDefaultLegendTextPaint() {
        return this.defaultLegendTextPaint;
    }

    /**
     * Sets the default legend text paint and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param paint the paint ({@code null} permitted).
     * @since 1.0.11
     */
    public void setDefaultLegendTextPaint(Paint paint) {
        this.setDefaultLegendTextPaint(paint, false);
    }
    /**
     * Sets the default legend text paint and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param paint the paint ({@code null} permitted).
     * @since 1.0.11
     */
    public void setDefaultLegendTextPaint(Paint paint, boolean notify) {
        this.defaultLegendTextPaint = paint;
        if (notify) {
            abstractRenderer.getListenerManager().fireChangeEvent();
        }
    }

    /**
     * Returns an independent copy of the paintmanager.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if some component of the renderer
     *         does not support cloning.
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        PaintManager clone = (PaintManager) super.clone();

        // 'paint' : immutable, no need to clone reference
        if (this.paintList != null) {
            clone.paintList = (PaintList) this.paintList.clone();
        }
        // 'basePaint' : immutable, no need to clone reference

        if (this.fillPaintList != null) {
            clone.fillPaintList = (PaintList) this.fillPaintList.clone();
        }
        // 'outlinePaint' : immutable, no need to clone reference
        if (this.outlinePaintList != null) {
            clone.outlinePaintList = (PaintList) this.outlinePaintList.clone();
        }

        // 'itemLabelPaint' : immutable, no need to clone reference
        if (this.itemLabelPaintList != null) {
            clone.itemLabelPaintList = (PaintList) this.itemLabelPaintList.clone();
        }

        if (this.legendTextPaint != null) {
            clone.legendTextPaint = (PaintList) this.legendTextPaint.clone();
        }

        return clone;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PaintManager)) {
            return false;
        }

        PaintManager that = (PaintManager) obj;

        if (!PaintUtils.equal(this.getDefaultFillPaint(),
                that.getDefaultFillPaint())) {
            return false;
        }
        if (!ObjectUtils.equal(this.outlinePaintList,
                that.outlinePaintList)) {
            return false;
        }
        if (!PaintUtils.equal(this.getDefaultOutlinePaint(),
                that.getDefaultOutlinePaint())) {
            return false;
        }
        if (!ObjectUtils.equal(this.paintList, that.paintList)) {
            return false;
        }
        if (!PaintUtils.equal(this.getDefaultPaint(), that.getDefaultPaint())) {
            return false;
        }
        if (!ObjectUtils.equal(this.fillPaintList, that.fillPaintList)) {
            return false;
        }

        return true;
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
        SerialUtils.writePaint(this.getDefaultPaint(), stream);
        SerialUtils.writePaint(this.getDefaultFillPaint(), stream);
        SerialUtils.writePaint(this.getDefaultOutlinePaint(), stream);
        SerialUtils.writePaint(this.getDefaultItemLabelPaint(), stream);
        SerialUtils.writePaint(this.getDefaultLegendTextPaint(), stream);
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
        this.setDefaultPaint(SerialUtils.readPaint(stream), false);
        this.setDefaultFillPaint(SerialUtils.readPaint(stream), false);
        this.setDefaultOutlinePaint(SerialUtils.readPaint(stream), false);
        this.setDefaultItemLabelPaint(SerialUtils.readPaint(stream), false);
        this.setDefaultLegendTextPaint(SerialUtils.readPaint(stream));
    }
}