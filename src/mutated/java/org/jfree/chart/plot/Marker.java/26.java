/* LittleDarwin generated mutant
 mutant type: conditionalOperatorDeletion
 ----> before: ! ( obj   instanceof Marker    )   
----> after:   ( obj   instanceof Marker    )   
----> line number in original file: 603
----> mutated nodes: 965
*/ 

package org . jfree . chart . plot  ;
 import java . awt . BasicStroke  ;
 import java . awt . Color  ;
 import java . awt . Font  ;
 import java . awt . Paint  ;
 import java . awt . Stroke  ;
 import java . io . IOException  ;
 import java . io . ObjectInputStream  ;
 import java . io . ObjectOutputStream  ;
 import java . io . Serializable  ;
 import java . util . EventListener  ;
 import javax . swing . event . EventListenerList  ;
 import org . jfree . chart . event . MarkerChangeEvent  ;
 import org . jfree . chart . event . MarkerChangeListener  ;
 import org . jfree . chart . ui . LengthAdjustmentType  ;
 import org . jfree . chart . ui . RectangleAnchor  ;
 import org . jfree . chart . ui . RectangleInsets  ;
 import org . jfree . chart . ui . TextAnchor  ;
 import org . jfree . chart . util . ObjectUtils  ;
 import org . jfree . chart . util . PaintUtils  ;
 import org . jfree . chart . util . Args  ;
 import org . jfree . chart . util . SerialUtils  ;
 public  abstract  class Marker implements Cloneable   , Serializable    { private   static   final   long   serialVersionUID  = - 734389651405327166L        ;
   private   transient  Paint   paint    ;
   private   transient  Stroke   stroke    ;
   private   transient  Paint   outlinePaint    ;
   private   transient  Stroke   outlineStroke    ;
   private   float   alpha    ;
   private   String   label  = null       ;
   private   Font   labelFont    ;
   private   transient  Paint   labelPaint    ;
   private   Color   labelBackgroundColor    ;
   private   RectangleAnchor   labelAnchor    ;
   private   TextAnchor   labelTextAnchor    ;
   private   RectangleInsets   labelOffset    ;
   private   LengthAdjustmentType   labelOffsetType    ;
   private   transient  EventListenerList   listenerList    ;
   protected   Marker ( )  { this   ( Color   . GRAY   )   ;
  }      protected   Marker ( Paint   paint    )  { this   ( paint   , new BasicStroke  ( 0.5f     )     , Color   . GRAY  , new BasicStroke  ( 0.5f     )     , 0.80f     )   ;
  }      protected   Marker ( Paint   paint   , Stroke   stroke   , Paint   outlinePaint   , Stroke   outlineStroke   , float   alpha    )  { Args   . nullNotPermitted  ( paint   , "paint"     )   ;
  Args   . nullNotPermitted  ( stroke   , "stroke"     )   ;
  if ( alpha   <0.0f     || alpha   >1.0f      )  { throw new IllegalArgumentException  ( "The 'alpha' value must be in the range 0.0f to 1.0f"     )     ;
  }     this   . paint  = paint     ;
  this   . stroke  = stroke     ;
  this   . outlinePaint  = outlinePaint     ;
  this   . outlineStroke  = outlineStroke     ;
  this   . alpha  = alpha     ;
  this   . labelFont  = new Font  ( "SansSerif"    , Font   . PLAIN  , 9     )       ;
  this   . labelPaint  = Color   . BLACK    ;
  this   . labelBackgroundColor  = new Color  ( 100    , 100    , 100    , 100     )       ;
  this   . labelAnchor  = RectangleAnchor   . TOP_LEFT    ;
  this   . labelOffset  = new RectangleInsets  ( 3.0    , 3.0    , 3.0    , 3.0     )       ;
  this   . labelOffsetType  = LengthAdjustmentType   . CONTRACT    ;
  this   . labelTextAnchor  = TextAnchor   . CENTER    ;
  this   . listenerList  = new EventListenerList  ( )       ;
  }      public   Paint   getPaint ( )  { return this   . paint  ;
  }      public   void setPaint ( Paint   paint    )  { Args   . nullNotPermitted  ( paint   , "paint"     )   ;
  this   . paint  = paint     ;
  notifyListeners   ( new MarkerChangeEvent  ( this    )      )   ;
  }      public   Stroke   getStroke ( )  { return this   . stroke  ;
  }      public   void setStroke ( Stroke   stroke    )  { Args   . nullNotPermitted  ( stroke   , "stroke"     )   ;
  this   . stroke  = stroke     ;
  notifyListeners   ( new MarkerChangeEvent  ( this    )      )   ;
  }      public   Paint   getOutlinePaint ( )  { return this   . outlinePaint  ;
  }      public   void setOutlinePaint ( Paint   paint    )  { this   . outlinePaint  = paint     ;
  notifyListeners   ( new MarkerChangeEvent  ( this    )      )   ;
  }      public   Stroke   getOutlineStroke ( )  { return this   . outlineStroke  ;
  }      public   void setOutlineStroke ( Stroke   stroke    )  { this   . outlineStroke  = stroke     ;
  notifyListeners   ( new MarkerChangeEvent  ( this    )      )   ;
  }      public   float   getAlpha ( )  { return this   . alpha  ;
  }      public   void setAlpha ( float   alpha    )  { if ( alpha   <0.0f     || alpha   >1.0f      )  { throw new IllegalArgumentException  ( "The 'alpha' value must be in the range 0.0f to 1.0f"     )     ;
  }     this   . alpha  = alpha     ;
  notifyListeners   ( new MarkerChangeEvent  ( this    )      )   ;
  }      public   String   getLabel ( )  { return this   . label  ;
  }      public   void setLabel ( String   label    )  { this   . label  = label     ;
  notifyListeners   ( new MarkerChangeEvent  ( this    )      )   ;
  }      public   Font   getLabelFont ( )  { return this   . labelFont  ;
  }      public   void setLabelFont ( Font   font    )  { Args   . nullNotPermitted  ( font   , "font"     )   ;
  this   . labelFont  = font     ;
  notifyListeners   ( new MarkerChangeEvent  ( this    )      )   ;
  }      public   Paint   getLabelPaint ( )  { return this   . labelPaint  ;
  }      public   void setLabelPaint ( Paint   paint    )  { Args   . nullNotPermitted  ( paint   , "paint"     )   ;
  this   . labelPaint  = paint     ;
  notifyListeners   ( new MarkerChangeEvent  ( this    )      )   ;
  }      public   Color   getLabelBackgroundColor ( )  { return this   . labelBackgroundColor  ;
  }      public   void setLabelBackgroundColor ( Color   color    )  { Args   . nullNotPermitted  ( color   , "color"     )   ;
  this   . labelBackgroundColor  = color     ;
  }      public   RectangleAnchor   getLabelAnchor ( )  { return this   . labelAnchor  ;
  }      public   void setLabelAnchor ( RectangleAnchor   anchor    )  { Args   . nullNotPermitted  ( anchor   , "anchor"     )   ;
  this   . labelAnchor  = anchor     ;
  notifyListeners   ( new MarkerChangeEvent  ( this    )      )   ;
  }      public   RectangleInsets   getLabelOffset ( )  { return this   . labelOffset  ;
  }      public   void setLabelOffset ( RectangleInsets   offset    )  { Args   . nullNotPermitted  ( offset   , "offset"     )   ;
  this   . labelOffset  = offset     ;
  notifyListeners   ( new MarkerChangeEvent  ( this    )      )   ;
  }      public   LengthAdjustmentType   getLabelOffsetType ( )  { return this   . labelOffsetType  ;
  }      public   void setLabelOffsetType ( LengthAdjustmentType   adj    )  { Args   . nullNotPermitted  ( adj   , "adj"     )   ;
  this   . labelOffsetType  = adj     ;
  notifyListeners   ( new MarkerChangeEvent  ( this    )      )   ;
  }      public   TextAnchor   getLabelTextAnchor ( )  { return this   . labelTextAnchor  ;
  }      public   void setLabelTextAnchor ( TextAnchor   anchor    )  { Args   . nullNotPermitted  ( anchor   , "anchor"     )   ;
  this   . labelTextAnchor  = anchor     ;
  notifyListeners   ( new MarkerChangeEvent  ( this    )      )   ;
  }      public   void addChangeListener ( MarkerChangeListener   listener    )  { this   . listenerList  . add  ( MarkerChangeListener   . class   , listener    )   ;
  }      public   void removeChangeListener ( MarkerChangeListener   listener    )  { this   . listenerList  . remove  ( MarkerChangeListener   . class   , listener    )   ;
  }      public   void notifyListeners ( MarkerChangeEvent   event    )  { Object  [ ]  listeners  = this   . listenerList  . getListenerList  ( )      ;
  for ( int   i  = listeners   . length  - 2          ;
i   >= 0     ;
i   -= 2        ) { if ( listeners   [ i   ]  == MarkerChangeListener   . class    )  { ( ( MarkerChangeListener   ) listeners   [ i   + 1     ]   )   . markerChanged  ( event    )   ;
  }     }     }      public   EventListener  [ ]  getListeners ( Class   listenerType    )  { return this   . listenerList  . getListeners  ( listenerType    )  ;
  }      @ Override      public   boolean   equals ( Object   obj    )  { if ( obj   == this    )  { return true    ;
  }     if (   ( obj   instanceof Marker    )    )  { return false    ;
  }     Marker   that  = ( Marker   ) obj        ;
  if ( ! PaintUtils   . equal  ( this   . paint  , that   . paint   )   )  { return false    ;
  }     if ( ! ObjectUtils   . equal  ( this   . stroke  , that   . stroke   )   )  { return false    ;
  }     if ( ! PaintUtils   . equal  ( this   . outlinePaint  , that   . outlinePaint   )   )  { return false    ;
  }     if ( ! ObjectUtils   . equal  ( this   . outlineStroke  , that   . outlineStroke   )   )  { return false    ;
  }     if ( this   . alpha  != that   . alpha   )  { return false    ;
  }     if ( ! ObjectUtils   . equal  ( this   . label  , that   . label   )   )  { return false    ;
  }     if ( ! ObjectUtils   . equal  ( this   . labelFont  , that   . labelFont   )   )  { return false    ;
  }     if ( ! PaintUtils   . equal  ( this   . labelPaint  , that   . labelPaint   )   )  { return false    ;
  }     if ( ! this   . labelBackgroundColor  . equals  ( that   . labelBackgroundColor   )   )  { return false    ;
  }     if ( this   . labelAnchor  != that   . labelAnchor   )  { return false    ;
  }     if ( this   . labelTextAnchor  != that   . labelTextAnchor   )  { return false    ;
  }     if ( ! ObjectUtils   . equal  ( this   . labelOffset  , that   . labelOffset   )   )  { return false    ;
  }     if ( ! this   . labelOffsetType  . equals  ( that   . labelOffsetType   )   )  { return false    ;
  }     return true    ;
  }      @ Override      public   Object   clone ( )  throws CloneNotSupportedException   { return super   . clone  ( )  ;
  }      private   void writeObject ( ObjectOutputStream   stream    )  throws IOException   { stream   . defaultWriteObject  ( )   ;
  SerialUtils   . writePaint  ( this   . paint  , stream    )   ;
  SerialUtils   . writeStroke  ( this   . stroke  , stream    )   ;
  SerialUtils   . writePaint  ( this   . outlinePaint  , stream    )   ;
  SerialUtils   . writeStroke  ( this   . outlineStroke  , stream    )   ;
  SerialUtils   . writePaint  ( this   . labelPaint  , stream    )   ;
  }      private   void readObject ( ObjectInputStream   stream    )  throws IOException  , ClassNotFoundException   { stream   . defaultReadObject  ( )   ;
  this   . paint  = SerialUtils   . readPaint  ( stream    )    ;
  this   . stroke  = SerialUtils   . readStroke  ( stream    )    ;
  this   . outlinePaint  = SerialUtils   . readPaint  ( stream    )    ;
  this   . outlineStroke  = SerialUtils   . readStroke  ( stream    )    ;
  this   . labelPaint  = SerialUtils   . readPaint  ( stream    )    ;
  this   . listenerList  = new EventListenerList  ( )       ;
  }      }      