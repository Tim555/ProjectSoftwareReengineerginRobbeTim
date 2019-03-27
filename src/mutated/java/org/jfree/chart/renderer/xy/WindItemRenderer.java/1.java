/* LittleDarwin generated mutant
 mutant type: arithmeticOperatorReplacementBinary
 ----> before: wforce   . doubleValue  ( )  * Math   . sin  ( ardir    )  
----> after: wforce   . doubleValue  ( )  / Math   . sin  ( ardir    )  
----> line number in original file: 186
----> mutated nodes: 178
*/ 

package org . jfree . chart . renderer . xy  ;
 import java . awt . Color  ;
 import java . awt . Font  ;
 import java . awt . Graphics2D  ;
 import java . awt . Paint  ;
 import java . awt . Stroke  ;
 import java . awt . geom . Line2D  ;
 import java . awt . geom . Rectangle2D  ;
 import java . io . Serializable  ;
 import org . jfree . chart . axis . ValueAxis  ;
 import org . jfree . chart . plot . CrosshairState  ;
 import org . jfree . chart . plot . PlotRenderingInfo  ;
 import org . jfree . chart . plot . XYPlot  ;
 import org . jfree . chart . ui . RectangleEdge  ;
 import org . jfree . chart . util . PublicCloneable  ;
 import org . jfree . data . xy . WindDataset  ;
 import org . jfree . data . xy . XYDataset  ;
 public  class WindItemRenderer extends AbstractXYItemRenderer   implements XYItemRenderer   , Cloneable   , PublicCloneable   , Serializable    { private   static   final   long   serialVersionUID  = 8078914101916976844L       ;
   public   WindItemRenderer ( )  { super   ( )   ;
  }      @ Override      public   void drawItem ( Graphics2D   g2   , XYItemRendererState   state   , Rectangle2D   plotArea   , PlotRenderingInfo   info   , XYPlot   plot   , ValueAxis   domainAxis   , ValueAxis   rangeAxis   , XYDataset   dataset   , int   series   , int   item   , CrosshairState   crosshairState   , int   pass    )  { WindDataset   windData  = ( WindDataset   ) dataset        ;
  Paint   seriesPaint  = getItemPaint   ( series   , item    )      ;
  Stroke   seriesStroke  = getItemStroke   ( series   , item    )      ;
  g2   . setPaint  ( seriesPaint    )   ;
  g2   . setStroke  ( seriesStroke    )   ;
  Number   x  = windData   . getX  ( series   , item    )      ;
  Number   windDir  = windData   . getWindDirection  ( series   , item    )      ;
  Number   wforce  = windData   . getWindForce  ( series   , item    )      ;
  double   windForce  = wforce   . doubleValue  ( )      ;
  double   wdirt  = Math   . toRadians  ( windDir   . doubleValue  ( )  * ( - 30.0     )    - 90.0      )      ;
  double   ax1   , ax2   , ay1   , ay2   , rax2   , ray2     ;
  RectangleEdge   domainAxisLocation  = plot   . getDomainAxisEdge  ( )      ;
  RectangleEdge   rangeAxisLocation  = plot   . getRangeAxisEdge  ( )      ;
  ax1   = domainAxis   . valueToJava2D  ( x   . doubleValue  ( )  , plotArea   , domainAxisLocation    )    ;
  ay1   = rangeAxis   . valueToJava2D  ( 0.0    , plotArea   , rangeAxisLocation    )    ;
  rax2   = x   . doubleValue  ( )  + ( windForce   * Math   . cos  ( wdirt    )   * 8000000.0     )      ;
  ray2   = windForce   * Math   . sin  ( wdirt    )     ;
  ax2   = domainAxis   . valueToJava2D  ( rax2   , plotArea   , domainAxisLocation    )    ;
  ay2   = rangeAxis   . valueToJava2D  ( ray2   , plotArea   , rangeAxisLocation    )    ;
  int   diri  = windDir   . intValue  ( )      ;
  int   forcei  = wforce   . intValue  ( )      ;
  String   dirforce  = diri   + "-"     + forcei        ;
  Line2D   line  = new Line2D . Double  ( ax1   , ay1   , ax2   , ay2    )         ;
  g2   . draw  ( line    )   ;
  g2   . setPaint  ( Color   . BLUE   )   ;
  g2   . setFont  ( new Font  ( "Dialog"    , 1    , 9     )      )   ;
  g2   . drawString  ( dirforce   , ( float   ) ax1    , ( float   ) ay1     )   ;
  g2   . setPaint  ( seriesPaint    )   ;
  g2   . setStroke  ( seriesStroke    )   ;
  double   alx2   , aly2   , arx2   , ary2     ;
  double   ralx2   , raly2   , rarx2   , rary2     ;
  double   aldir  = Math   . toRadians  ( windDir   . doubleValue  ( )  * ( - 30.0     )    - 90.0     - 5.0      )      ;
  ralx2   = wforce   . doubleValue  ( )  * Math   . cos  ( aldir    )   * 8000000     * 0.8     + x   . doubleValue  ( )     ;
  raly2   = wforce   . doubleValue  ( )  * Math   . sin  ( aldir    )   * 0.8       ;
  alx2   = domainAxis   . valueToJava2D  ( ralx2   , plotArea   , domainAxisLocation    )    ;
  aly2   = rangeAxis   . valueToJava2D  ( raly2   , plotArea   , rangeAxisLocation    )    ;
  line   = new Line2D . Double  ( alx2   , aly2   , ax2   , ay2    )       ;
  g2   . draw  ( line    )   ;
  double   ardir  = Math   . toRadians  ( windDir   . doubleValue  ( )  * ( - 30.0     )    - 90.0     + 5.0      )      ;
  rarx2   = wforce   . doubleValue  ( )  * Math   . cos  ( ardir    )   * 8000000     * 0.8     + x   . doubleValue  ( )     ;
  rary2   = wforce   . doubleValue  ( )  / Math   . sin  ( ardir    )   * 0.8       ;
  arx2   = domainAxis   . valueToJava2D  ( rarx2   , plotArea   , domainAxisLocation    )    ;
  ary2   = rangeAxis   . valueToJava2D  ( rary2   , plotArea   , rangeAxisLocation    )    ;
  line   = new Line2D . Double  ( arx2   , ary2   , ax2   , ay2    )       ;
  g2   . draw  ( line    )   ;
  }      @ Override      public   Object   clone ( )  throws CloneNotSupportedException   { return super   . clone  ( )  ;
  }      }      