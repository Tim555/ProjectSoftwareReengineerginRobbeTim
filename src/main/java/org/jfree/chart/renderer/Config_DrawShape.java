package org.jfree.chart.renderer;

import java.awt.*;

/**
 * POD Class that contains the configuration for drawing a shape.
 */
public class Config_DrawShape{

    private boolean itemShapeFilled;

    private boolean useFillPaint;

    private Paint itemFillPaint;

    private Paint itemPaint;

    private boolean drawOutlines;

    private boolean useOutlinePaint;

    private Paint itemOutlinePaint;

    private Stroke itemOutlineStroke;


    public Config_DrawShape(boolean itemShapeFilled ,
                              boolean useFillPaint,
                              Paint itemFillPaint,
                              Paint itemPaint,
                              boolean drawOutlines,
                              boolean useOutlinePaint,
                              Paint itemOutlinePaint,
                              Stroke itemOutlineStroke) {
        this.itemShapeFilled = itemShapeFilled;
        this.useFillPaint = useFillPaint;
        this.itemFillPaint = itemFillPaint;
        this.itemPaint = itemPaint;
        this.drawOutlines = drawOutlines;
        this.useOutlinePaint = useOutlinePaint;
        this.itemOutlinePaint = itemOutlinePaint;
        this.itemOutlineStroke = itemOutlineStroke;
    }

    public Config_DrawShape() {
        this(false, false, null, null, false, false,null, null);
    }

    public boolean isItemShapeFilled() {
        return itemShapeFilled;
    }

    public void setItemShapeFilled(boolean itemShapeFilled) {
        this.itemShapeFilled = itemShapeFilled;
    }

    public boolean isUseFillPaint() {
        return useFillPaint;
    }

    public void setUseFillPaint(boolean useFillPaint) {
        this.useFillPaint = useFillPaint;
    }

    public Paint getItemFillPaint() {
        return itemFillPaint;
    }

    public void setItemFillPaint(Paint itemFillPaint) {
        this.itemFillPaint = itemFillPaint;
    }

    public Paint getItemPaint() {
        return itemPaint;
    }

    public void setItemPaint(Paint itemPaint) {
        this.itemPaint = itemPaint;
    }

    public boolean isDrawOutlines() {
        return drawOutlines;
    }

    public void setDrawOutlines(boolean drawOutlines) {
        this.drawOutlines = drawOutlines;
    }

    public boolean isUseOutlinePaint() {
        return useOutlinePaint;
    }

    public void setUseOutlinePaint(boolean useOutlinePaint) {
        this.useOutlinePaint = useOutlinePaint;
    }

    public Paint getItemOutlinePaint() {
        return itemOutlinePaint;
    }

    public void setItemOutlinePaint(Paint itemOutlinePaint) {
        this.itemOutlinePaint = itemOutlinePaint;
    }

    public Stroke getItemOutlineStroke() {
        return itemOutlineStroke;
    }

    public void setItemOutlineStroke(Stroke itemOutlineStroke) {
        this.itemOutlineStroke = itemOutlineStroke;
    }
}