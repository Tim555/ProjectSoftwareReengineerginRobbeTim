/* LittleDarwin generated mutant
 mutant type: arithmeticOperatorReplacementBinary
 ----> before: x   + w   
----> after: x   - w   
----> line number in original file: 304
----> mutated nodes: 2558
*/ 

package org . jfree . chart . plot  ;
 import java . awt . Graphics2D  ;
 import java . awt . geom . Point2D  ;
 import java . awt . geom . Rectangle2D  ;
 import java . io . IOException  ;
 import java . io . ObjectInputStream  ;
 import java . util . Collections  ;
 import java . util . Iterator  ;
 import java . util . List  ;
 import org . jfree . chart . LegendItemCollection  ;
 import org . jfree . chart . axis . AxisSpace  ;
 import org . jfree . chart . axis . AxisState  ;
 import org . jfree . chart . axis . NumberAxis  ;
 import org . jfree . chart . axis . ValueAxis  ;
 import org . jfree . chart . event . PlotChangeEvent  ;
 import org . jfree . chart . event . PlotChangeListener  ;
 import org . jfree . chart . ui . RectangleEdge  ;
 import org . jfree . chart . ui . RectangleInsets  ;
 import org . jfree . chart . util . ObjectUtils  ;
 import org . jfree . chart . util . Args  ;
 import org . jfree . chart . util . ShadowGenerator  ;
 import org . jfree . data . Range  ;
 public  class CombinedRangeCategoryPlot extends CategoryPlot   implements PlotChangeListener    { private   static   final   long   serialVersionUID  = 7260210007554504515L       ;
   private   List   subplots    ;
   private   double   gap    ;
   private   transient  Rectangle2D  [ ]  subplotArea    ;
   public   CombinedRangeCategoryPlot ( )  { this   ( new NumberAxis  ( )      )   ;
  }      public   CombinedRangeCategoryPlot ( ValueAxis   rangeAxis    )  { super   ( null    , null    , rangeAxis   , null     )   ;
  this   . subplots  = new java . util . ArrayList  ( )       ;
  this   . gap  = 5.0      ;
  }      public   double   getGap ( )  { return this   . gap  ;
  }      public   void setGap ( double   gap    )  { this   . gap  = gap     ;
  fireChangeEvent   ( )   ;
  }      public   void add ( CategoryPlot   subplot    )  { add   ( subplot   , 1     )   ;
  }      public   void add ( CategoryPlot   subplot   , int   weight    )  { Args   . nullNotPermitted  ( subplot   , "subplot"     )   ;
  if ( weight   <= 0     )  { throw new IllegalArgumentException  ( "Require weight >= 1."     )     ;
  }     subplot   . setParent  ( this    )   ;
  subplot   . setWeight  ( weight    )   ;
  subplot   . setInsets  ( new RectangleInsets  ( 0.0    , 0.0    , 0.0    , 0.0     )      )   ;
  subplot   . setRangeAxis  ( null     )   ;
  subplot   . setOrientation  ( getOrientation   ( )   )   ;
  subplot   . addChangeListener  ( this    )   ;
  this   . subplots  . add  ( subplot    )   ;
  ValueAxis   axis  = getRangeAxis   ( )      ;
  if ( axis   != null     )  { axis   . configure  ( )   ;
  }     fireChangeEvent   ( )   ;
  }      public   void remove ( CategoryPlot   subplot    )  { Args   . nullNotPermitted  ( subplot   , "subplot"     )   ;
  int   position  = - 1         ;
  int   size  = this   . subplots  . size  ( )      ;
  int   i  = 0        ;
  while ( position   == - 1      && i   <size     )  { if ( this   . subplots  . get  ( i    )  == subplot    )  { position   = i     ;
  }     i   ++   ;
  }     if ( position   != - 1      )  { this   . subplots  . remove  ( position    )   ;
  subplot   . setParent  ( null     )   ;
  subplot   . removeChangeListener  ( this    )   ;
  ValueAxis   range  = getRangeAxis   ( )      ;
  if ( range   != null     )  { range   . configure  ( )   ;
  }     ValueAxis   range2  = getRangeAxis   ( 1     )      ;
  if ( range2   != null     )  { range2   . configure  ( )   ;
  }     fireChangeEvent   ( )   ;
  }     }      public   List   getSubplots ( )  { if ( this   . subplots  != null     )  { return Collections   . unmodifiableList  ( this   . subplots   )  ;
  }   else { return Collections   . EMPTY_LIST  ;
  }     }      @ Override      protected   AxisSpace   calculateAxisSpace ( Graphics2D   g2   , Rectangle2D   plotArea    )  { AxisSpace   space  = new AxisSpace  ( )         ;
  PlotOrientation   orientation  = getOrientation   ( )      ;
  AxisSpace   fixed  = getFixedRangeAxisSpace   ( )      ;
  if ( fixed   != null     )  { if ( orientation   == PlotOrientation   . VERTICAL   )  { space   . setLeft  ( fixed   . getLeft  ( )   )   ;
  space   . setRight  ( fixed   . getRight  ( )   )   ;
  }   else if ( orientation   == PlotOrientation   . HORIZONTAL   )  { space   . setTop  ( fixed   . getTop  ( )   )   ;
  space   . setBottom  ( fixed   . getBottom  ( )   )   ;
  }      }   else { ValueAxis   valueAxis  = getRangeAxis   ( )      ;
  RectangleEdge   valueEdge  = Plot   . resolveRangeAxisLocation  ( getRangeAxisLocation   ( )  , orientation    )      ;
  if ( valueAxis   != null     )  { space   = valueAxis   . reserveSpace  ( g2   , this   , plotArea   , valueEdge   , space    )    ;
  }     }     Rectangle2D   adjustedPlotArea  = space   . shrink  ( plotArea   , null     )      ;
  int   n  = this   . subplots  . size  ( )      ;
  int   totalWeight  = 0        ;
  for ( int   i  = 0         ;
i   <n    ;
i   ++     ) { CategoryPlot   sub  = ( CategoryPlot   ) this   . subplots  . get  ( i    )       ;
  totalWeight   += sub   . getWeight  ( )    ;
  }     this   . subplotArea  = new Rectangle2D  [ n   ]      ;
  double   x  = adjustedPlotArea   . getX  ( )      ;
  double   y  = adjustedPlotArea   . getY  ( )      ;
  double   usableSize  = 0.0        ;
  if ( orientation   == PlotOrientation   . VERTICAL   )  { usableSize   = adjustedPlotArea   . getWidth  ( )  - this   . gap  * ( n   - 1     )       ;
  }   else if ( orientation   == PlotOrientation   . HORIZONTAL   )  { usableSize   = adjustedPlotArea   . getHeight  ( )  - this   . gap  * ( n   - 1     )       ;
  }      for ( int   i  = 0         ;
i   <n    ;
i   ++     ) { CategoryPlot   plot  = ( CategoryPlot   ) this   . subplots  . get  ( i    )       ;
  if ( orientation   == PlotOrientation   . VERTICAL   )  { double   w  = usableSize   * plot   . getWeight  ( )   / totalWeight        ;
  this   . subplotArea  [ i   ]  = new Rectangle2D . Double  ( x   , y   , w   , adjustedPlotArea   . getHeight  ( )   )       ;
  x   = x   - w    + this   . gap     ;
  }   else if ( orientation   == PlotOrientation   . HORIZONTAL   )  { double   h  = usableSize   * plot   . getWeight  ( )   / totalWeight        ;
  this   . subplotArea  [ i   ]  = new Rectangle2D . Double  ( x   , y   , adjustedPlotArea   . getWidth  ( )  , h    )       ;
  y   = y   + h    + this   . gap     ;
  }      AxisSpace   subSpace  = plot   . calculateDomainAxisSpace  ( g2   , this   . subplotArea  [ i   ]  , null     )      ;
  space   . ensureAtLeast  ( subSpace    )   ;
  }     return space   ;
  }      @ Override      public   void draw ( Graphics2D   g2   , Rectangle2D   area   , Point2D   anchor   , PlotState   parentState   , PlotRenderingInfo   info    )  { if ( info   != null     )  { info   . setPlotArea  ( area    )   ;
  }     RectangleInsets   insets  = getInsets   ( )      ;
  insets   . trim  ( area    )   ;
  AxisSpace   space  = calculateAxisSpace   ( g2   , area    )      ;
  Rectangle2D   dataArea  = space   . shrink  ( area   , null     )      ;
  setFixedDomainAxisSpaceForSubplots   ( space    )   ;
  ValueAxis   axis  = getRangeAxis   ( )      ;
  RectangleEdge   rangeEdge  = getRangeAxisEdge   ( )      ;
  double   cursor  = RectangleEdge   . coordinate  ( dataArea   , rangeEdge    )      ;
  AxisState   state  = axis   . draw  ( g2   , cursor   , area   , dataArea   , rangeEdge   , info    )      ;
  if ( parentState   == null     )  { parentState   = new PlotState  ( )       ;
  }     parentState   . getSharedAxisStates  ( )  . put  ( axis   , state    )   ;
  for ( int   i  = 0         ;
i   <this   . subplots  . size  ( )   ;
i   ++     ) { CategoryPlot   plot  = ( CategoryPlot   ) this   . subplots  . get  ( i    )       ;
  PlotRenderingInfo   subplotInfo  = null        ;
  if ( info   != null     )  { subplotInfo   = new PlotRenderingInfo  ( info   . getOwner  ( )   )       ;
  info   . addSubplotInfo  ( subplotInfo    )   ;
  }     Point2D   subAnchor  = null        ;
  if ( anchor   != null     && this   . subplotArea  [ i   ]  . contains  ( anchor    )   )  { subAnchor   = anchor     ;
  }     plot   . draw  ( g2   , this   . subplotArea  [ i   ]  , subAnchor   , parentState   , subplotInfo    )   ;
  }     if ( info   != null     )  { info   . setDataArea  ( dataArea    )   ;
  }     }      @ Override      public   void setOrientation ( PlotOrientation   orientation    )  { super   . setOrientation  ( orientation    )   ;
  Iterator   iterator  = this   . subplots  . iterator  ( )      ;
  while ( iterator   . hasNext  ( )  )  { CategoryPlot   plot  = ( CategoryPlot   ) iterator   . next  ( )       ;
  plot   . setOrientation  ( orientation    )   ;
  }     }      @ Override      public   void setShadowGenerator ( ShadowGenerator   generator    )  { setNotify   ( false     )   ;
  super   . setShadowGenerator  ( generator    )   ;
  Iterator   iterator  = this   . subplots  . iterator  ( )      ;
  while ( iterator   . hasNext  ( )  )  { CategoryPlot   plot  = ( CategoryPlot   ) iterator   . next  ( )       ;
  plot   . setShadowGenerator  ( generator    )   ;
  }     setNotify   ( true     )   ;
  }      @ Override      public   Range   getDataRange ( ValueAxis   axis    )  { Range   result  = null        ;
  if ( this   . subplots  != null     )  { Iterator   iterator  = this   . subplots  . iterator  ( )      ;
  while ( iterator   . hasNext  ( )  )  { CategoryPlot   subplot  = ( CategoryPlot   ) iterator   . next  ( )       ;
  result   = Range   . combine  ( result   , subplot   . getDataRange  ( axis    )   )    ;
  }     }     return result   ;
  }      @ Override      public   LegendItemCollection   getLegendItems ( )  { LegendItemCollection   result  = getFixedLegendItems   ( )      ;
  if ( result   == null     )  { result   = new LegendItemCollection  ( )       ;
  if ( this   . subplots  != null     )  { Iterator   iterator  = this   . subplots  . iterator  ( )      ;
  while ( iterator   . hasNext  ( )  )  { CategoryPlot   plot  = ( CategoryPlot   ) iterator   . next  ( )       ;
  LegendItemCollection   more  = plot   . getLegendItems  ( )      ;
  result   . addAll  ( more    )   ;
  }     }     }     return result   ;
  }      protected   void setFixedDomainAxisSpaceForSubplots ( AxisSpace   space    )  { Iterator   iterator  = this   . subplots  . iterator  ( )      ;
  while ( iterator   . hasNext  ( )  )  { CategoryPlot   plot  = ( CategoryPlot   ) iterator   . next  ( )       ;
  plot   . setFixedDomainAxisSpace  ( space   , false     )   ;
  }     }      @ Override      public   void handleClick ( int   x   , int   y   , PlotRenderingInfo   info    )  { Rectangle2D   dataArea  = info   . getDataArea  ( )      ;
  if ( dataArea   . contains  ( x   , y    )  )  { for ( int   i  = 0         ;
i   <this   . subplots  . size  ( )   ;
i   ++     ) { CategoryPlot   subplot  = ( CategoryPlot   ) this   . subplots  . get  ( i    )       ;
  PlotRenderingInfo   subplotInfo  = info   . getSubplotInfo  ( i    )      ;
  subplot   . handleClick  ( x   , y   , subplotInfo    )   ;
  }     }     }      @ Override      public   void plotChanged ( PlotChangeEvent   event    )  { notifyListeners   ( event    )   ;
  }      @ Override      public   boolean   equals ( Object   obj    )  { if ( obj   == this    )  { return true    ;
  }     if ( ! ( obj   instanceof CombinedRangeCategoryPlot    )    )  { return false    ;
  }     CombinedRangeCategoryPlot   that  = ( CombinedRangeCategoryPlot   ) obj        ;
  if ( this   . gap  != that   . gap   )  { return false    ;
  }     if ( ! ObjectUtils   . equal  ( this   . subplots  , that   . subplots   )   )  { return false    ;
  }     return super   . equals  ( obj    )  ;
  }      @ Override      public   Object   clone ( )  throws CloneNotSupportedException   { CombinedRangeCategoryPlot   result  = ( CombinedRangeCategoryPlot   ) super   . clone  ( )       ;
  result   . subplots  = ( List   ) ObjectUtils   . deepClone  ( this   . subplots   )     ;
  for ( Iterator   it  = result   . subplots  . iterator  ( )       ;
it   . hasNext  ( )  ;
 ) { Plot   child  = ( Plot   ) it   . next  ( )       ;
  child   . setParent  ( result    )   ;
  }     ValueAxis   rangeAxis  = result   . getRangeAxis  ( )      ;
  if ( rangeAxis   != null     )  { rangeAxis   . configure  ( )   ;
  }     return result   ;
  }      private   void readObject ( ObjectInputStream   stream    )  throws IOException  , ClassNotFoundException   { stream   . defaultReadObject  ( )   ;
  ValueAxis   rangeAxis  = getRangeAxis   ( )      ;
  if ( rangeAxis   != null     )  { rangeAxis   . configure  ( )   ;
  }     }      }      