/* LittleDarwin generated mutant
 mutant type: arithmeticOperatorReplacementBinary
 ----> before: width   / 2    
----> after: width   * 2    
----> line number in original file: 593
----> mutated nodes: 2956
*/ 

package org . jfree . chart . renderer . xy  ;
 import java . awt . Color  ;
 import java . awt . Graphics2D  ;
 import java . awt . Paint  ;
 import java . awt . Shape  ;
 import java . awt . Stroke  ;
 import java . awt . geom . Ellipse2D  ;
 import java . awt . geom . Line2D  ;
 import java . awt . geom . Point2D  ;
 import java . awt . geom . Rectangle2D  ;
 import java . io . IOException  ;
 import java . io . ObjectInputStream  ;
 import java . io . ObjectOutputStream  ;
 import java . io . Serializable  ;
 import java . util . ArrayList  ;
 import java . util . Collections  ;
 import java . util . Iterator  ;
 import java . util . List  ;
 import org . jfree . chart . axis . ValueAxis  ;
 import org . jfree . chart . entity . EntityCollection  ;
 import org . jfree . chart . event . RendererChangeEvent  ;
 import org . jfree . chart . labels . BoxAndWhiskerXYToolTipGenerator  ;
 import org . jfree . chart . plot . CrosshairState  ;
 import org . jfree . chart . plot . PlotOrientation  ;
 import org . jfree . chart . plot . PlotRenderingInfo  ;
 import org . jfree . chart . plot . XYPlot  ;
 import org . jfree . chart . renderer . Outlier  ;
 import org . jfree . chart . renderer . OutlierList  ;
 import org . jfree . chart . renderer . OutlierListCollection  ;
 import org . jfree . chart . ui . RectangleEdge  ;
 import org . jfree . chart . util . PaintUtils  ;
 import org . jfree . chart . util . Args  ;
 import org . jfree . chart . util . PublicCloneable  ;
 import org . jfree . chart . util . SerialUtils  ;
 import org . jfree . data . Range  ;
 import org . jfree . data . statistics . BoxAndWhiskerXYDataset  ;
 import org . jfree . data . xy . XYDataset  ;
 public  class XYBoxAndWhiskerRenderer extends AbstractXYItemRenderer   implements XYItemRenderer   , Cloneable   , PublicCloneable   , Serializable    { private   static   final   long   serialVersionUID  = - 8020170108532232324L        ;
   private   double   boxWidth    ;
   private   transient  Paint   boxPaint    ;
   private   boolean   fillBox    ;
   private   transient  Paint   artifactPaint  = Color   . BLACK     ;
   public   XYBoxAndWhiskerRenderer ( )  { this   ( - 1.0      )   ;
  }      public   XYBoxAndWhiskerRenderer ( double   boxWidth    )  { super   ( )   ;
  this   . boxWidth  = boxWidth     ;
  this   . boxPaint  = Color   . GREEN    ;
  this   . fillBox  = true      ;
  setDefaultToolTipGenerator   ( new BoxAndWhiskerXYToolTipGenerator  ( )      )   ;
  }      public   double   getBoxWidth ( )  { return this   . boxWidth  ;
  }      public   void setBoxWidth ( double   width    )  { if ( width   != this   . boxWidth   )  { this   . boxWidth  = width     ;
  fireChangeEvent   ( )   ;
  }     }      public   Paint   getBoxPaint ( )  { return this   . boxPaint  ;
  }      public   void setBoxPaint ( Paint   paint    )  { this   . boxPaint  = paint     ;
  fireChangeEvent   ( )   ;
  }      public   boolean   getFillBox ( )  { return this   . fillBox  ;
  }      public   void setFillBox ( boolean   flag    )  { this   . fillBox  = flag     ;
  fireChangeEvent   ( )   ;
  }      public   Paint   getArtifactPaint ( )  { return this   . artifactPaint  ;
  }      public   void setArtifactPaint ( Paint   paint    )  { Args   . nullNotPermitted  ( paint   , "paint"     )   ;
  this   . artifactPaint  = paint     ;
  fireChangeEvent   ( )   ;
  }      @ Override      public   Range   findRangeBounds ( XYDataset   dataset    )  { return findRangeBounds   ( dataset   , true     )  ;
  }      protected   Paint   lookupBoxPaint ( int   series   , int   item    )  { Paint   p  = getBoxPaint   ( )      ;
  if ( p   != null     )  { return p   ;
  }   else { return getItemPaint   ( series   , item    )  ;
  }     }      @ Override      public   void drawItem ( Graphics2D   g2   , XYItemRendererState   state   , Rectangle2D   dataArea   , PlotRenderingInfo   info   , XYPlot   plot   , ValueAxis   domainAxis   , ValueAxis   rangeAxis   , XYDataset   dataset   , int   series   , int   item   , CrosshairState   crosshairState   , int   pass    )  { PlotOrientation   orientation  = plot   . getOrientation  ( )      ;
  if ( orientation   == PlotOrientation   . HORIZONTAL   )  { drawHorizontalItem   ( g2   , dataArea   , info   , plot   , domainAxis   , rangeAxis   , dataset   , series   , item   , crosshairState   , pass    )   ;
  }   else if ( orientation   == PlotOrientation   . VERTICAL   )  { drawVerticalItem   ( g2   , dataArea   , info   , plot   , domainAxis   , rangeAxis   , dataset   , series   , item   , crosshairState   , pass    )   ;
  }      }      public   void drawHorizontalItem ( Graphics2D   g2   , Rectangle2D   dataArea   , PlotRenderingInfo   info   , XYPlot   plot   , ValueAxis   domainAxis   , ValueAxis   rangeAxis   , XYDataset   dataset   , int   series   , int   item   , CrosshairState   crosshairState   , int   pass    )  { EntityCollection   entities  = null        ;
  if ( info   != null     )  { entities   = info   . getOwner  ( )  . getEntityCollection  ( )    ;
  }     BoxAndWhiskerXYDataset   boxAndWhiskerData  = ( BoxAndWhiskerXYDataset   ) dataset        ;
  Number   x  = boxAndWhiskerData   . getX  ( series   , item    )      ;
  Number   yMax  = boxAndWhiskerData   . getMaxRegularValue  ( series   , item    )      ;
  Number   yMin  = boxAndWhiskerData   . getMinRegularValue  ( series   , item    )      ;
  Number   yMedian  = boxAndWhiskerData   . getMedianValue  ( series   , item    )      ;
  Number   yAverage  = boxAndWhiskerData   . getMeanValue  ( series   , item    )      ;
  Number   yQ1Median  = boxAndWhiskerData   . getQ1Value  ( series   , item    )      ;
  Number   yQ3Median  = boxAndWhiskerData   . getQ3Value  ( series   , item    )      ;
  double   xx  = domainAxis   . valueToJava2D  ( x   . doubleValue  ( )  , dataArea   , plot   . getDomainAxisEdge  ( )   )      ;
  RectangleEdge   location  = plot   . getRangeAxisEdge  ( )      ;
  double   yyMax  = rangeAxis   . valueToJava2D  ( yMax   . doubleValue  ( )  , dataArea   , location    )      ;
  double   yyMin  = rangeAxis   . valueToJava2D  ( yMin   . doubleValue  ( )  , dataArea   , location    )      ;
  double   yyMedian  = rangeAxis   . valueToJava2D  ( yMedian   . doubleValue  ( )  , dataArea   , location    )      ;
  double   yyAverage  = 0.0        ;
  if ( yAverage   != null     )  { yyAverage   = rangeAxis   . valueToJava2D  ( yAverage   . doubleValue  ( )  , dataArea   , location    )    ;
  }     double   yyQ1Median  = rangeAxis   . valueToJava2D  ( yQ1Median   . doubleValue  ( )  , dataArea   , location    )      ;
  double   yyQ3Median  = rangeAxis   . valueToJava2D  ( yQ3Median   . doubleValue  ( )  , dataArea   , location    )      ;
  double   exactBoxWidth  = getBoxWidth   ( )      ;
  double   width  = exactBoxWidth       ;
  double   dataAreaX  = dataArea   . getHeight  ( )      ;
  double   maxBoxPercent  = 0.1        ;
  double   maxBoxWidth  = dataAreaX   * maxBoxPercent        ;
  if ( exactBoxWidth   <= 0.0     )  { int   itemCount  = boxAndWhiskerData   . getItemCount  ( series    )      ;
  exactBoxWidth   = dataAreaX   / itemCount    * 4.5     / 7       ;
  if ( exactBoxWidth   <3     )  { width   = 3      ;
  }   else if ( exactBoxWidth   >maxBoxWidth    )  { width   = maxBoxWidth     ;
  }   else { width   = exactBoxWidth     ;
  }      }     g2   . setPaint  ( getItemPaint   ( series   , item    )   )   ;
  Stroke   s  = getItemStroke   ( series   , item    )      ;
  g2   . setStroke  ( s    )   ;
  g2   . draw  ( new Line2D . Double  ( yyMax   , xx   , yyQ3Median   , xx    )      )   ;
  g2   . draw  ( new Line2D . Double  ( yyMax   , xx   - width   / 2      , yyMax   , xx   + width   / 2       )      )   ;
  g2   . draw  ( new Line2D . Double  ( yyMin   , xx   , yyQ1Median   , xx    )      )   ;
  g2   . draw  ( new Line2D . Double  ( yyMin   , xx   - width   / 2      , yyMin   , xx   + width   / 2       )      )   ;
  Shape   box     ;
  if ( yyQ1Median   <yyQ3Median    )  { box   = new Rectangle2D . Double  ( yyQ1Median   , xx   - width   / 2      , yyQ3Median   - yyQ1Median    , width    )       ;
  }   else { box   = new Rectangle2D . Double  ( yyQ3Median   , xx   - width   / 2      , yyQ1Median   - yyQ3Median    , width    )       ;
  }     if ( this   . fillBox  )  { g2   . setPaint  ( lookupBoxPaint   ( series   , item    )   )   ;
  g2   . fill  ( box    )   ;
  }     g2   . setStroke  ( getItemOutlineStroke   ( series   , item    )   )   ;
  g2   . setPaint  ( getItemOutlinePaint   ( series   , item    )   )   ;
  g2   . draw  ( box    )   ;
  g2   . setPaint  ( getArtifactPaint   ( )   )   ;
  g2   . draw  ( new Line2D . Double  ( yyMedian   , xx   - width   / 2      , yyMedian   , xx   + width   / 2       )      )   ;
  if ( yAverage   != null     )  { double   aRadius  = width   / 4         ;
  if ( ( yyAverage   >( dataArea   . getMinX  ( )  - aRadius    )    )   && ( yyAverage   <( dataArea   . getMaxX  ( )  + aRadius    )    )    )  { Ellipse2D . Double   avgEllipse  = new Ellipse2D . Double  ( yyAverage   - aRadius    , xx   - aRadius    , aRadius   * 2     , aRadius   * 2      )         ;
  g2   . fill  ( avgEllipse    )   ;
  g2   . draw  ( avgEllipse    )   ;
  }     }     if ( entities   != null     && box   . intersects  ( dataArea    )   )  { addEntity   ( entities   , box   , dataset   , series   , item   , yyAverage   , xx    )   ;
  }     }      public   void drawVerticalItem ( Graphics2D   g2   , Rectangle2D   dataArea   , PlotRenderingInfo   info   , XYPlot   plot   , ValueAxis   domainAxis   , ValueAxis   rangeAxis   , XYDataset   dataset   , int   series   , int   item   , CrosshairState   crosshairState   , int   pass    )  { EntityCollection   entities  = null        ;
  if ( info   != null     )  { entities   = info   . getOwner  ( )  . getEntityCollection  ( )    ;
  }     BoxAndWhiskerXYDataset   boxAndWhiskerData  = ( BoxAndWhiskerXYDataset   ) dataset        ;
  Number   x  = boxAndWhiskerData   . getX  ( series   , item    )      ;
  Number   yMax  = boxAndWhiskerData   . getMaxRegularValue  ( series   , item    )      ;
  Number   yMin  = boxAndWhiskerData   . getMinRegularValue  ( series   , item    )      ;
  Number   yMedian  = boxAndWhiskerData   . getMedianValue  ( series   , item    )      ;
  Number   yAverage  = boxAndWhiskerData   . getMeanValue  ( series   , item    )      ;
  Number   yQ1Median  = boxAndWhiskerData   . getQ1Value  ( series   , item    )      ;
  Number   yQ3Median  = boxAndWhiskerData   . getQ3Value  ( series   , item    )      ;
  List   yOutliers  = boxAndWhiskerData   . getOutliers  ( series   , item    )      ;
  if ( yOutliers   == null     )  { yOutliers   = Collections   . EMPTY_LIST    ;
  }     double   xx  = domainAxis   . valueToJava2D  ( x   . doubleValue  ( )  , dataArea   , plot   . getDomainAxisEdge  ( )   )      ;
  RectangleEdge   location  = plot   . getRangeAxisEdge  ( )      ;
  double   yyMax  = rangeAxis   . valueToJava2D  ( yMax   . doubleValue  ( )  , dataArea   , location    )      ;
  double   yyMin  = rangeAxis   . valueToJava2D  ( yMin   . doubleValue  ( )  , dataArea   , location    )      ;
  double   yyMedian  = rangeAxis   . valueToJava2D  ( yMedian   . doubleValue  ( )  , dataArea   , location    )      ;
  double   yyAverage  = 0.0        ;
  if ( yAverage   != null     )  { yyAverage   = rangeAxis   . valueToJava2D  ( yAverage   . doubleValue  ( )  , dataArea   , location    )    ;
  }     double   yyQ1Median  = rangeAxis   . valueToJava2D  ( yQ1Median   . doubleValue  ( )  , dataArea   , location    )      ;
  double   yyQ3Median  = rangeAxis   . valueToJava2D  ( yQ3Median   . doubleValue  ( )  , dataArea   , location    )      ;
  double   yyOutlier     ;
  double   exactBoxWidth  = getBoxWidth   ( )      ;
  double   width  = exactBoxWidth       ;
  double   dataAreaX  = dataArea   . getMaxX  ( )  - dataArea   . getMinX  ( )       ;
  double   maxBoxPercent  = 0.1        ;
  double   maxBoxWidth  = dataAreaX   * maxBoxPercent        ;
  if ( exactBoxWidth   <= 0.0     )  { int   itemCount  = boxAndWhiskerData   . getItemCount  ( series    )      ;
  exactBoxWidth   = dataAreaX   / itemCount    * 4.5     / 7       ;
  if ( exactBoxWidth   <3     )  { width   = 3      ;
  }   else if ( exactBoxWidth   >maxBoxWidth    )  { width   = maxBoxWidth     ;
  }   else { width   = exactBoxWidth     ;
  }      }     g2   . setPaint  ( getItemPaint   ( series   , item    )   )   ;
  Stroke   s  = getItemStroke   ( series   , item    )      ;
  g2   . setStroke  ( s    )   ;
  g2   . draw  ( new Line2D . Double  ( xx   , yyMax   , xx   , yyQ3Median    )      )   ;
  g2   . draw  ( new Line2D . Double  ( xx   - width   / 2      , yyMax   , xx   + width   / 2      , yyMax    )      )   ;
  g2   . draw  ( new Line2D . Double  ( xx   , yyMin   , xx   , yyQ1Median    )      )   ;
  g2   . draw  ( new Line2D . Double  ( xx   - width   * 2      , yyMin   , xx   + width   / 2      , yyMin    )      )   ;
  Shape   box     ;
  if ( yyQ1Median   >yyQ3Median    )  { box   = new Rectangle2D . Double  ( xx   - width   / 2      , yyQ3Median   , width   , yyQ1Median   - yyQ3Median     )       ;
  }   else { box   = new Rectangle2D . Double  ( xx   - width   / 2      , yyQ1Median   , width   , yyQ3Median   - yyQ1Median     )       ;
  }     if ( this   . fillBox  )  { g2   . setPaint  ( lookupBoxPaint   ( series   , item    )   )   ;
  g2   . fill  ( box    )   ;
  }     g2   . setStroke  ( getItemOutlineStroke   ( series   , item    )   )   ;
  g2   . setPaint  ( getItemOutlinePaint   ( series   , item    )   )   ;
  g2   . draw  ( box    )   ;
  g2   . setPaint  ( getArtifactPaint   ( )   )   ;
  g2   . draw  ( new Line2D . Double  ( xx   - width   / 2      , yyMedian   , xx   + width   / 2      , yyMedian    )      )   ;
  double   aRadius  = 0        ;
  double   oRadius  = width   / 3         ;
  if ( yAverage   != null     )  { aRadius   = width   / 4       ;
  if ( ( yyAverage   >( dataArea   . getMinY  ( )  - aRadius    )    )   && ( yyAverage   <( dataArea   . getMaxY  ( )  + aRadius    )    )    )  { Ellipse2D . Double   avgEllipse  = new Ellipse2D . Double  ( xx   - aRadius    , yyAverage   - aRadius    , aRadius   * 2     , aRadius   * 2      )         ;
  g2   . fill  ( avgEllipse    )   ;
  g2   . draw  ( avgEllipse    )   ;
  }     }     List   outliers  = new ArrayList  ( )         ;
  OutlierListCollection   outlierListCollection  = new OutlierListCollection  ( )         ;
  for ( int   i  = 0         ;
i   <yOutliers   . size  ( )   ;
i   ++     ) { double   outlier  = ( ( Number   ) yOutliers   . get  ( i    )   )   . doubleValue  ( )      ;
  if ( outlier   >boxAndWhiskerData   . getMaxOutlier  ( series   , item    )  . doubleValue  ( )   )  { outlierListCollection   . setHighFarOut  ( true     )   ;
  }   else if ( outlier   <boxAndWhiskerData   . getMinOutlier  ( series   , item    )  . doubleValue  ( )   )  { outlierListCollection   . setLowFarOut  ( true     )   ;
  }   else if ( outlier   >boxAndWhiskerData   . getMaxRegularValue  ( series   , item    )  . doubleValue  ( )   )  { yyOutlier   = rangeAxis   . valueToJava2D  ( outlier   , dataArea   , location    )    ;
  outliers   . add  ( new Outlier  ( xx   , yyOutlier   , oRadius    )      )   ;
  }   else if ( outlier   <boxAndWhiskerData   . getMinRegularValue  ( series   , item    )  . doubleValue  ( )   )  { yyOutlier   = rangeAxis   . valueToJava2D  ( outlier   , dataArea   , location    )    ;
  outliers   . add  ( new Outlier  ( xx   , yyOutlier   , oRadius    )      )   ;
  }        Collections   . sort  ( outliers    )   ;
  }     for ( Iterator   iterator  = outliers   . iterator  ( )       ;
iterator   . hasNext  ( )  ;
 ) { Outlier   outlier  = ( Outlier   ) iterator   . next  ( )       ;
  outlierListCollection   . add  ( outlier    )   ;
  }     double   maxAxisValue  = rangeAxis   . valueToJava2D  ( rangeAxis   . getUpperBound  ( )  , dataArea   , location    )  + aRadius        ;
  double   minAxisValue  = rangeAxis   . valueToJava2D  ( rangeAxis   . getLowerBound  ( )  , dataArea   , location    )  - aRadius        ;
  for ( Iterator   iterator  = outlierListCollection   . iterator  ( )       ;
iterator   . hasNext  ( )  ;
 ) { OutlierList   list  = ( OutlierList   ) iterator   . next  ( )       ;
  Outlier   outlier  = list   . getAveragedOutlier  ( )      ;
  Point2D   point  = outlier   . getPoint  ( )      ;
  if ( list   . isMultiple  ( )  )  { drawMultipleEllipse   ( point   , width   , oRadius   , g2    )   ;
  }   else { drawEllipse   ( point   , oRadius   , g2    )   ;
  }     }     if ( outlierListCollection   . isHighFarOut  ( )  )  { drawHighFarOut   ( aRadius   , g2   , xx   , maxAxisValue    )   ;
  }     if ( outlierListCollection   . isLowFarOut  ( )  )  { drawLowFarOut   ( aRadius   , g2   , xx   , minAxisValue    )   ;
  }     if ( entities   != null     && box   . intersects  ( dataArea    )   )  { addEntity   ( entities   , box   , dataset   , series   , item   , xx   , yyAverage    )   ;
  }     }      protected   void drawEllipse ( Point2D   point   , double   oRadius   , Graphics2D   g2    )  { Ellipse2D . Double   dot  = new Ellipse2D . Double  ( point   . getX  ( )  + oRadius   / 2      , point   . getY  ( )  , oRadius   , oRadius    )         ;
  g2   . draw  ( dot    )   ;
  }      protected   void drawMultipleEllipse ( Point2D   point   , double   boxWidth   , double   oRadius   , Graphics2D   g2    )  { Ellipse2D . Double   dot1  = new Ellipse2D . Double  ( point   . getX  ( )  - ( boxWidth   / 2     )    + oRadius    , point   . getY  ( )  , oRadius   , oRadius    )         ;
  Ellipse2D . Double   dot2  = new Ellipse2D . Double  ( point   . getX  ( )  + ( boxWidth   / 2     )    , point   . getY  ( )  , oRadius   , oRadius    )         ;
  g2   . draw  ( dot1    )   ;
  g2   . draw  ( dot2    )   ;
  }      protected   void drawHighFarOut ( double   aRadius   , Graphics2D   g2   , double   xx   , double   m    )  { double   side  = aRadius   * 2         ;
  g2   . draw  ( new Line2D . Double  ( xx   - side    , m   + side    , xx   + side    , m   + side     )      )   ;
  g2   . draw  ( new Line2D . Double  ( xx   - side    , m   + side    , xx   , m    )      )   ;
  g2   . draw  ( new Line2D . Double  ( xx   + side    , m   + side    , xx   , m    )      )   ;
  }      protected   void drawLowFarOut ( double   aRadius   , Graphics2D   g2   , double   xx   , double   m    )  { double   side  = aRadius   * 2         ;
  g2   . draw  ( new Line2D . Double  ( xx   - side    , m   - side    , xx   + side    , m   - side     )      )   ;
  g2   . draw  ( new Line2D . Double  ( xx   - side    , m   - side    , xx   , m    )      )   ;
  g2   . draw  ( new Line2D . Double  ( xx   + side    , m   - side    , xx   , m    )      )   ;
  }      @ Override      public   boolean   equals ( Object   obj    )  { if ( obj   == this    )  { return true    ;
  }     if ( ! ( obj   instanceof XYBoxAndWhiskerRenderer    )    )  { return false    ;
  }     if ( ! super   . equals  ( obj    )   )  { return false    ;
  }     XYBoxAndWhiskerRenderer   that  = ( XYBoxAndWhiskerRenderer   ) obj        ;
  if ( this   . boxWidth  != that   . getBoxWidth  ( )   )  { return false    ;
  }     if ( ! PaintUtils   . equal  ( this   . boxPaint  , that   . boxPaint   )   )  { return false    ;
  }     if ( ! PaintUtils   . equal  ( this   . artifactPaint  , that   . artifactPaint   )   )  { return false    ;
  }     if ( this   . fillBox  != that   . fillBox   )  { return false    ;
  }     return true    ;
  }      private   void writeObject ( ObjectOutputStream   stream    )  throws IOException   { stream   . defaultWriteObject  ( )   ;
  SerialUtils   . writePaint  ( this   . boxPaint  , stream    )   ;
  SerialUtils   . writePaint  ( this   . artifactPaint  , stream    )   ;
  }      private   void readObject ( ObjectInputStream   stream    )  throws IOException  , ClassNotFoundException   { stream   . defaultReadObject  ( )   ;
  this   . boxPaint  = SerialUtils   . readPaint  ( stream    )    ;
  this   . artifactPaint  = SerialUtils   . readPaint  ( stream    )    ;
  }      @ Override      public   Object   clone ( )  throws CloneNotSupportedException   { return super   . clone  ( )  ;
  }      }      