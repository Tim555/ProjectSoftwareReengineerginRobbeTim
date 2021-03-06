/* LittleDarwin generated mutant
 mutant type: relationalOperatorReplacement
 ----> before: value   <= lclip   
----> after: value   >lclip   
----> line number in original file: 306
----> mutated nodes: 3503
*/ 

package org . jfree . chart . renderer . category  ;
 import java . awt . BasicStroke  ;
 import java . awt . Color  ;
 import java . awt . GradientPaint  ;
 import java . awt . Graphics2D  ;
 import java . awt . Paint  ;
 import java . awt . Stroke  ;
 import java . awt . geom . Line2D  ;
 import java . awt . geom . Rectangle2D  ;
 import java . io . IOException  ;
 import java . io . ObjectInputStream  ;
 import java . io . ObjectOutputStream  ;
 import java . io . Serializable  ;
 import org . jfree . chart . axis . CategoryAxis  ;
 import org . jfree . chart . axis . ValueAxis  ;
 import org . jfree . chart . entity . EntityCollection  ;
 import org . jfree . chart . event . RendererChangeEvent  ;
 import org . jfree . chart . labels . CategoryItemLabelGenerator  ;
 import org . jfree . chart . plot . CategoryPlot  ;
 import org . jfree . chart . plot . PlotOrientation  ;
 import org . jfree . chart . ui . GradientPaintTransformer  ;
 import org . jfree . chart . ui . RectangleEdge  ;
 import org . jfree . chart . util . ObjectUtils  ;
 import org . jfree . chart . util . PaintUtils  ;
 import org . jfree . chart . util . PublicCloneable  ;
 import org . jfree . chart . util . SerialUtils  ;
 import org . jfree . data . Range  ;
 import org . jfree . data . category . CategoryDataset  ;
 import org . jfree . data . statistics . StatisticalCategoryDataset  ;
 public  class StatisticalBarRenderer extends BarRenderer   implements CategoryItemRenderer   , Cloneable   , PublicCloneable   , Serializable    { private   static   final   long   serialVersionUID  = - 4986038395414039117L        ;
   private   transient  Paint   errorIndicatorPaint    ;
   private   transient  Stroke   errorIndicatorStroke    ;
   public   StatisticalBarRenderer ( )  { super   ( )   ;
  this   . errorIndicatorPaint  = Color   . gray    ;
  this   . errorIndicatorStroke  = new BasicStroke  ( 1.0f     )       ;
  }      public   Paint   getErrorIndicatorPaint ( )  { return this   . errorIndicatorPaint  ;
  }      public   void setErrorIndicatorPaint ( Paint   paint    )  { this   . errorIndicatorPaint  = paint     ;
  fireChangeEvent   ( )   ;
  }      public   Stroke   getErrorIndicatorStroke ( )  { return this   . errorIndicatorStroke  ;
  }      public   void setErrorIndicatorStroke ( Stroke   stroke    )  { this   . errorIndicatorStroke  = stroke     ;
  fireChangeEvent   ( )   ;
  }      @ Override      public   Range   findRangeBounds ( CategoryDataset   dataset    )  { return findRangeBounds   ( dataset   , true     )  ;
  }      @ Override      public   void drawItem ( Graphics2D   g2   , CategoryItemRendererState   state   , Rectangle2D   dataArea   , CategoryPlot   plot   , CategoryAxis   domainAxis   , ValueAxis   rangeAxis   , CategoryDataset   data   , int   row   , int   column   , int   pass    )  { int   visibleRow  = state   . getVisibleSeriesIndex  ( row    )      ;
  if ( visibleRow   <0     )  { return ;
  }     if ( ! ( data   instanceof StatisticalCategoryDataset    )    )  { throw new IllegalArgumentException  ( "Requires StatisticalCategoryDataset."     )     ;
  }     StatisticalCategoryDataset   statData  = ( StatisticalCategoryDataset   ) data        ;
  PlotOrientation   orientation  = plot   . getOrientation  ( )      ;
  if ( orientation   == PlotOrientation   . HORIZONTAL   )  { drawHorizontalItem   ( g2   , state   , dataArea   , plot   , domainAxis   , rangeAxis   , statData   , visibleRow   , row   , column    )   ;
  }   else if ( orientation   == PlotOrientation   . VERTICAL   )  { drawVerticalItem   ( g2   , state   , dataArea   , plot   , domainAxis   , rangeAxis   , statData   , visibleRow   , row   , column    )   ;
  }      }      protected   void drawHorizontalItem ( Graphics2D   g2   , CategoryItemRendererState   state   , Rectangle2D   dataArea   , CategoryPlot   plot   , CategoryAxis   domainAxis   , ValueAxis   rangeAxis   , StatisticalCategoryDataset   dataset   , int   visibleRow   , int   row   , int   column    )  { double   rectY  = calculateBarW0   ( plot   , PlotOrientation   . HORIZONTAL  , dataArea   , domainAxis   , state   , visibleRow   , column    )      ;
  Number   meanValue  = dataset   . getMeanValue  ( row   , column    )      ;
  if ( meanValue   == null     )  { return ;
  }     double   value  = meanValue   . doubleValue  ( )      ;
  double   base  = 0.0        ;
  double   lclip  = getLowerClip   ( )      ;
  double   uclip  = getUpperClip   ( )      ;
  if ( uclip   <= 0.0     )  { if ( value   >= uclip    )  { return ;
  }     base   = uclip     ;
  if ( value   <= lclip    )  { value   = lclip     ;
  }     }   else if ( lclip   <= 0.0     )  { if ( value   >= uclip    )  { value   = uclip     ;
  }   else { if ( value   <= lclip    )  { value   = lclip     ;
  }     }     }   else { if ( value   >lclip    )  { return ;
  }     base   = getLowerClip   ( )    ;
  if ( value   >= uclip    )  { value   = uclip     ;
  }     }      RectangleEdge   yAxisLocation  = plot   . getRangeAxisEdge  ( )      ;
  double   transY1  = rangeAxis   . valueToJava2D  ( base   , dataArea   , yAxisLocation    )      ;
  double   transY2  = rangeAxis   . valueToJava2D  ( value   , dataArea   , yAxisLocation    )      ;
  double   rectX  = Math   . min  ( transY2   , transY1    )      ;
  double   rectHeight  = state   . getBarWidth  ( )      ;
  double   rectWidth  = Math   . abs  ( transY2   - transY1     )      ;
  Rectangle2D   bar  = new Rectangle2D . Double  ( rectX   , rectY   , rectWidth   , rectHeight    )         ;
  Paint   itemPaint  = getItemPaint   ( row   , column    )      ;
  GradientPaintTransformer   t  = getGradientPaintTransformer   ( )      ;
  if ( t   != null     && itemPaint   instanceof GradientPaint     )  { itemPaint   = t   . transform  ( ( GradientPaint   ) itemPaint    , bar    )    ;
  }     g2   . setPaint  ( itemPaint    )   ;
  g2   . fill  ( bar    )   ;
  if ( isDrawBarOutline   ( )  && state   . getBarWidth  ( )  >BAR_OUTLINE_WIDTH_THRESHOLD     )  { Stroke   stroke  = getItemOutlineStroke   ( row   , column    )      ;
  Paint   paint  = getItemOutlinePaint   ( row   , column    )      ;
  if ( stroke   != null     && paint   != null      )  { g2   . setStroke  ( stroke    )   ;
  g2   . setPaint  ( paint    )   ;
  g2   . draw  ( bar    )   ;
  }     }     Number   n  = dataset   . getStdDevValue  ( row   , column    )      ;
  if ( n   != null     )  { double   valueDelta  = n   . doubleValue  ( )      ;
  double   highVal  = rangeAxis   . valueToJava2D  ( meanValue   . doubleValue  ( )  + valueDelta    , dataArea   , yAxisLocation    )      ;
  double   lowVal  = rangeAxis   . valueToJava2D  ( meanValue   . doubleValue  ( )  - valueDelta    , dataArea   , yAxisLocation    )      ;
  if ( this   . errorIndicatorPaint  != null     )  { g2   . setPaint  ( this   . errorIndicatorPaint   )   ;
  }   else { g2   . setPaint  ( getItemOutlinePaint   ( row   , column    )   )   ;
  }     if ( this   . errorIndicatorStroke  != null     )  { g2   . setStroke  ( this   . errorIndicatorStroke   )   ;
  }   else { g2   . setStroke  ( getItemOutlineStroke   ( row   , column    )   )   ;
  }     Line2D   line     ;
  line   = new Line2D . Double  ( lowVal   , rectY   + rectHeight   / 2.0d      , highVal   , rectY   + rectHeight   / 2.0d       )       ;
  g2   . draw  ( line    )   ;
  line   = new Line2D . Double  ( highVal   , rectY   + rectHeight   * 0.25      , highVal   , rectY   + rectHeight   * 0.75       )       ;
  g2   . draw  ( line    )   ;
  line   = new Line2D . Double  ( lowVal   , rectY   + rectHeight   * 0.25      , lowVal   , rectY   + rectHeight   * 0.75       )       ;
  g2   . draw  ( line    )   ;
  }     CategoryItemLabelGenerator   generator  = getItemLabelGenerator   ( row   , column    )      ;
  if ( generator   != null     && isItemLabelVisible   ( row   , column    )   )  { drawItemLabel   ( g2   , dataset   , row   , column   , plot   , generator   , bar   , ( value   <0.0     )    )   ;
  }     EntityCollection   entities  = state   . getEntityCollection  ( )      ;
  if ( entities   != null     )  { addItemEntity   ( entities   , dataset   , row   , column   , bar    )   ;
  }     }      protected   void drawVerticalItem ( Graphics2D   g2   , CategoryItemRendererState   state   , Rectangle2D   dataArea   , CategoryPlot   plot   , CategoryAxis   domainAxis   , ValueAxis   rangeAxis   , StatisticalCategoryDataset   dataset   , int   visibleRow   , int   row   , int   column    )  { double   rectX  = calculateBarW0   ( plot   , PlotOrientation   . VERTICAL  , dataArea   , domainAxis   , state   , visibleRow   , column    )      ;
  Number   meanValue  = dataset   . getMeanValue  ( row   , column    )      ;
  if ( meanValue   == null     )  { return ;
  }     double   value  = meanValue   . doubleValue  ( )      ;
  double   base  = 0.0        ;
  double   lclip  = getLowerClip   ( )      ;
  double   uclip  = getUpperClip   ( )      ;
  if ( uclip   <= 0.0     )  { if ( value   >= uclip    )  { return ;
  }     base   = uclip     ;
  if ( value   <= lclip    )  { value   = lclip     ;
  }     }   else if ( lclip   <= 0.0     )  { if ( value   >= uclip    )  { value   = uclip     ;
  }   else { if ( value   <= lclip    )  { value   = lclip     ;
  }     }     }   else { if ( value   <= lclip    )  { return ;
  }     base   = getLowerClip   ( )    ;
  if ( value   >= uclip    )  { value   = uclip     ;
  }     }      RectangleEdge   yAxisLocation  = plot   . getRangeAxisEdge  ( )      ;
  double   transY1  = rangeAxis   . valueToJava2D  ( base   , dataArea   , yAxisLocation    )      ;
  double   transY2  = rangeAxis   . valueToJava2D  ( value   , dataArea   , yAxisLocation    )      ;
  double   rectY  = Math   . min  ( transY2   , transY1    )      ;
  double   rectWidth  = state   . getBarWidth  ( )      ;
  double   rectHeight  = Math   . abs  ( transY2   - transY1     )      ;
  Rectangle2D   bar  = new Rectangle2D . Double  ( rectX   , rectY   , rectWidth   , rectHeight    )         ;
  Paint   itemPaint  = getItemPaint   ( row   , column    )      ;
  GradientPaintTransformer   t  = getGradientPaintTransformer   ( )      ;
  if ( t   != null     && itemPaint   instanceof GradientPaint     )  { itemPaint   = t   . transform  ( ( GradientPaint   ) itemPaint    , bar    )    ;
  }     g2   . setPaint  ( itemPaint    )   ;
  g2   . fill  ( bar    )   ;
  if ( isDrawBarOutline   ( )  && state   . getBarWidth  ( )  >BAR_OUTLINE_WIDTH_THRESHOLD     )  { Stroke   stroke  = getItemOutlineStroke   ( row   , column    )      ;
  Paint   paint  = getItemOutlinePaint   ( row   , column    )      ;
  if ( stroke   != null     && paint   != null      )  { g2   . setStroke  ( stroke    )   ;
  g2   . setPaint  ( paint    )   ;
  g2   . draw  ( bar    )   ;
  }     }     Number   n  = dataset   . getStdDevValue  ( row   , column    )      ;
  if ( n   != null     )  { double   valueDelta  = n   . doubleValue  ( )      ;
  double   highVal  = rangeAxis   . valueToJava2D  ( meanValue   . doubleValue  ( )  + valueDelta    , dataArea   , yAxisLocation    )      ;
  double   lowVal  = rangeAxis   . valueToJava2D  ( meanValue   . doubleValue  ( )  - valueDelta    , dataArea   , yAxisLocation    )      ;
  if ( this   . errorIndicatorPaint  != null     )  { g2   . setPaint  ( this   . errorIndicatorPaint   )   ;
  }   else { g2   . setPaint  ( getItemOutlinePaint   ( row   , column    )   )   ;
  }     if ( this   . errorIndicatorStroke  != null     )  { g2   . setStroke  ( this   . errorIndicatorStroke   )   ;
  }   else { g2   . setStroke  ( getItemOutlineStroke   ( row   , column    )   )   ;
  }     Line2D   line     ;
  line   = new Line2D . Double  ( rectX   + rectWidth   / 2.0d      , lowVal   , rectX   + rectWidth   / 2.0d      , highVal    )       ;
  g2   . draw  ( line    )   ;
  line   = new Line2D . Double  ( rectX   + rectWidth   / 2.0d      - 5.0d     , highVal   , rectX   + rectWidth   / 2.0d      + 5.0d     , highVal    )       ;
  g2   . draw  ( line    )   ;
  line   = new Line2D . Double  ( rectX   + rectWidth   / 2.0d      - 5.0d     , lowVal   , rectX   + rectWidth   / 2.0d      + 5.0d     , lowVal    )       ;
  g2   . draw  ( line    )   ;
  }     CategoryItemLabelGenerator   generator  = getItemLabelGenerator   ( row   , column    )      ;
  if ( generator   != null     && isItemLabelVisible   ( row   , column    )   )  { drawItemLabel   ( g2   , dataset   , row   , column   , plot   , generator   , bar   , ( value   <0.0     )    )   ;
  }     EntityCollection   entities  = state   . getEntityCollection  ( )      ;
  if ( entities   != null     )  { addItemEntity   ( entities   , dataset   , row   , column   , bar    )   ;
  }     }      @ Override      public   boolean   equals ( Object   obj    )  { if ( obj   == this    )  { return true    ;
  }     if ( ! ( obj   instanceof StatisticalBarRenderer    )    )  { return false    ;
  }     StatisticalBarRenderer   that  = ( StatisticalBarRenderer   ) obj        ;
  if ( ! PaintUtils   . equal  ( this   . errorIndicatorPaint  , that   . errorIndicatorPaint   )   )  { return false    ;
  }     if ( ! ObjectUtils   . equal  ( this   . errorIndicatorStroke  , that   . errorIndicatorStroke   )   )  { return false    ;
  }     return super   . equals  ( obj    )  ;
  }      private   void writeObject ( ObjectOutputStream   stream    )  throws IOException   { stream   . defaultWriteObject  ( )   ;
  SerialUtils   . writePaint  ( this   . errorIndicatorPaint  , stream    )   ;
  SerialUtils   . writeStroke  ( this   . errorIndicatorStroke  , stream    )   ;
  }      private   void readObject ( ObjectInputStream   stream    )  throws IOException  , ClassNotFoundException   { stream   . defaultReadObject  ( )   ;
  this   . errorIndicatorPaint  = SerialUtils   . readPaint  ( stream    )    ;
  this   . errorIndicatorStroke  = SerialUtils   . readStroke  ( stream    )    ;
  }      }      