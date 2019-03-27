/* LittleDarwin generated mutant
 mutant type: relationalOperatorReplacement
 ----> before: dataset   == null    
----> after: dataset   != null    
----> line number in original file: 288
----> mutated nodes: 898
*/ 

package org . jfree . chart . renderer . xy  ;
 import java . awt . Graphics2D  ;
 import java . awt . Paint  ;
 import java . awt . Shape  ;
 import java . awt . geom . Rectangle2D  ;
 import java . io . IOException  ;
 import java . io . ObjectInputStream  ;
 import java . io . ObjectOutputStream  ;
 import org . jfree . chart . LegendItem  ;
 import org . jfree . chart . axis . ValueAxis  ;
 import org . jfree . chart . event . RendererChangeEvent  ;
 import org . jfree . chart . plot . CrosshairState  ;
 import org . jfree . chart . plot . PlotOrientation  ;
 import org . jfree . chart . plot . PlotRenderingInfo  ;
 import org . jfree . chart . plot . XYPlot  ;
 import org . jfree . chart . ui . RectangleEdge  ;
 import org . jfree . chart . util . Args  ;
 import org . jfree . chart . util . PublicCloneable  ;
 import org . jfree . chart . util . SerialUtils  ;
 import org . jfree . chart . util . ShapeUtils  ;
 import org . jfree . data . xy . XYDataset  ;
 public  class XYDotRenderer extends AbstractXYItemRenderer   implements XYItemRenderer   , PublicCloneable    { private   static   final   long   serialVersionUID  = - 2764344339073566425L        ;
   private   int   dotWidth    ;
   private   int   dotHeight    ;
   private   transient  Shape   legendShape    ;
   public   XYDotRenderer ( )  { super   ( )   ;
  this   . dotWidth  = 1      ;
  this   . dotHeight  = 1      ;
  this   . legendShape  = new Rectangle2D . Double  ( - 3.0     , - 3.0     , 6.0    , 6.0     )       ;
  }      public   int   getDotWidth ( )  { return this   . dotWidth  ;
  }      public   void setDotWidth ( int   w    )  { if ( w   <1     )  { throw new IllegalArgumentException  ( "Requires w > 0."     )     ;
  }     this   . dotWidth  = w     ;
  fireChangeEvent   ( )   ;
  }      public   int   getDotHeight ( )  { return this   . dotHeight  ;
  }      public   void setDotHeight ( int   h    )  { if ( h   <1     )  { throw new IllegalArgumentException  ( "Requires h > 0."     )     ;
  }     this   . dotHeight  = h     ;
  fireChangeEvent   ( )   ;
  }      public   Shape   getLegendShape ( )  { return this   . legendShape  ;
  }      public   void setLegendShape ( Shape   shape    )  { Args   . nullNotPermitted  ( shape   , "shape"     )   ;
  this   . legendShape  = shape     ;
  fireChangeEvent   ( )   ;
  }      @ Override      public   void drawItem ( Graphics2D   g2   , XYItemRendererState   state   , Rectangle2D   dataArea   , PlotRenderingInfo   info   , XYPlot   plot   , ValueAxis   domainAxis   , ValueAxis   rangeAxis   , XYDataset   dataset   , int   series   , int   item   , CrosshairState   crosshairState   , int   pass    )  { if ( ! getItemVisible   ( series   , item    )   )  { return ;
  }     double   x  = dataset   . getXValue  ( series   , item    )      ;
  double   y  = dataset   . getYValue  ( series   , item    )      ;
  double   adjx  = ( this   . dotWidth  - 1     )   / 2.0         ;
  double   adjy  = ( this   . dotHeight  - 1     )   / 2.0         ;
  if ( ! Double   . isNaN  ( y    )   )  { RectangleEdge   xAxisLocation  = plot   . getDomainAxisEdge  ( )      ;
  RectangleEdge   yAxisLocation  = plot   . getRangeAxisEdge  ( )      ;
  double   transX  = domainAxis   . valueToJava2D  ( x   , dataArea   , xAxisLocation    )  - adjx        ;
  double   transY  = rangeAxis   . valueToJava2D  ( y   , dataArea   , yAxisLocation    )  - adjy        ;
  g2   . setPaint  ( getItemPaint   ( series   , item    )   )   ;
  PlotOrientation   orientation  = plot   . getOrientation  ( )      ;
  if ( orientation   == PlotOrientation   . HORIZONTAL   )  { g2   . fillRect  ( ( int   ) transY    , ( int   ) transX    , this   . dotHeight  , this   . dotWidth   )   ;
  }   else if ( orientation   == PlotOrientation   . VERTICAL   )  { g2   . fillRect  ( ( int   ) transX    , ( int   ) transY    , this   . dotWidth  , this   . dotHeight   )   ;
  }      int   datasetIndex  = plot   . indexOf  ( dataset    )      ;
  updateCrosshairValues   ( crosshairState   , x   , y   , datasetIndex   , transX   , transY   , orientation    )   ;
  }     }      @ Override      public   LegendItem   getLegendItem ( int   datasetIndex   , int   series    )  { XYPlot   plot  = getPlot   ( )      ;
  if ( plot   == null     )  { return null    ;
  }     XYDataset   dataset  = plot   . getDataset  ( datasetIndex    )      ;
  if ( dataset   != null     )  { return null    ;
  }     LegendItem   result  = null        ;
  if ( getItemVisible   ( series   , 0     )  )  { String   label  = getLegendItemLabelGenerator   ( )  . generateLabel  ( dataset   , series    )      ;
  String   description  = label       ;
  String   toolTipText  = null        ;
  if ( getLegendItemToolTipGenerator   ( )  != null     )  { toolTipText   = getLegendItemToolTipGenerator   ( )  . generateLabel  ( dataset   , series    )    ;
  }     String   urlText  = null        ;
  if ( getLegendItemURLGenerator   ( )  != null     )  { urlText   = getLegendItemURLGenerator   ( )  . generateLabel  ( dataset   , series    )    ;
  }     Paint   fillPaint  = lookupSeriesPaint   ( series    )      ;
  result   = new LegendItem  ( label   , description   , toolTipText   , urlText   , getLegendShape   ( )  , fillPaint    )       ;
  result   . setLabelFont  ( lookupLegendTextFont   ( series    )   )   ;
  Paint   labelPaint  = lookupLegendTextPaint   ( series    )      ;
  if ( labelPaint   != null     )  { result   . setLabelPaint  ( labelPaint    )   ;
  }     result   . setSeriesKey  ( dataset   . getSeriesKey  ( series    )   )   ;
  result   . setSeriesIndex  ( series    )   ;
  result   . setDataset  ( dataset    )   ;
  result   . setDatasetIndex  ( datasetIndex    )   ;
  }     return result   ;
  }      @ Override      public   boolean   equals ( Object   obj    )  { if ( obj   == this    )  { return true    ;
  }     if ( ! ( obj   instanceof XYDotRenderer    )    )  { return false    ;
  }     XYDotRenderer   that  = ( XYDotRenderer   ) obj        ;
  if ( this   . dotWidth  != that   . dotWidth   )  { return false    ;
  }     if ( this   . dotHeight  != that   . dotHeight   )  { return false    ;
  }     if ( ! ShapeUtils   . equal  ( this   . legendShape  , that   . legendShape   )   )  { return false    ;
  }     return super   . equals  ( obj    )  ;
  }      @ Override      public   Object   clone ( )  throws CloneNotSupportedException   { return super   . clone  ( )  ;
  }      private   void readObject ( ObjectInputStream   stream    )  throws IOException  , ClassNotFoundException   { stream   . defaultReadObject  ( )   ;
  this   . legendShape  = SerialUtils   . readShape  ( stream    )    ;
  }      private   void writeObject ( ObjectOutputStream   stream    )  throws IOException   { stream   . defaultWriteObject  ( )   ;
  SerialUtils   . writeShape  ( this   . legendShape  , stream    )   ;
  }      }      