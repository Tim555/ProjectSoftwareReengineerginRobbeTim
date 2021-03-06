/* LittleDarwin generated mutant
 mutant type: arithmeticOperatorReplacementBinary
 ----> before: 37    * result   
----> after: 37    / result   
----> line number in original file: 430
----> mutated nodes: 217
*/ 

package org . jfree . chart . title  ;
 import java . awt . Graphics2D  ;
 import java . awt . geom . Rectangle2D  ;
 import java . io . IOException  ;
 import java . io . ObjectInputStream  ;
 import java . io . ObjectOutputStream  ;
 import java . io . Serializable  ;
 import javax . swing . event . EventListenerList  ;
 import org . jfree . chart . block . AbstractBlock  ;
 import org . jfree . chart . block . Block  ;
 import org . jfree . chart . event . TitleChangeEvent  ;
 import org . jfree . chart . event . TitleChangeListener  ;
 import org . jfree . chart . ui . HorizontalAlignment  ;
 import org . jfree . chart . ui . RectangleEdge  ;
 import org . jfree . chart . ui . RectangleInsets  ;
 import org . jfree . chart . ui . VerticalAlignment  ;
 import org . jfree . chart . util . ObjectUtils  ;
 import org . jfree . chart . util . Args  ;
 public  abstract  class Title extends AbstractBlock   implements Block   , Cloneable   , Serializable    { private   static   final   long   serialVersionUID  = - 6675162505277817221L        ;
   public   static   final   RectangleEdge   DEFAULT_POSITION  = RectangleEdge   . TOP     ;
   public   static   final   HorizontalAlignment   DEFAULT_HORIZONTAL_ALIGNMENT  = HorizontalAlignment   . CENTER     ;
   public   static   final   VerticalAlignment   DEFAULT_VERTICAL_ALIGNMENT  = VerticalAlignment   . CENTER     ;
   public   static   final   RectangleInsets   DEFAULT_PADDING  = new RectangleInsets  ( 1    , 1    , 1    , 1     )        ;
   public   boolean   visible    ;
   private   RectangleEdge   position    ;
   private   HorizontalAlignment   horizontalAlignment    ;
   private   VerticalAlignment   verticalAlignment    ;
   private   transient  EventListenerList   listenerList    ;
   private   boolean   notify    ;
   protected   Title ( )  { this   ( Title   . DEFAULT_POSITION  , Title   . DEFAULT_HORIZONTAL_ALIGNMENT  , Title   . DEFAULT_VERTICAL_ALIGNMENT  , Title   . DEFAULT_PADDING   )   ;
  }      protected   Title ( RectangleEdge   position   , HorizontalAlignment   horizontalAlignment   , VerticalAlignment   verticalAlignment    )  { this   ( position   , horizontalAlignment   , verticalAlignment   , Title   . DEFAULT_PADDING   )   ;
  }      protected   Title ( RectangleEdge   position   , HorizontalAlignment   horizontalAlignment   , VerticalAlignment   verticalAlignment   , RectangleInsets   padding    )  { Args   . nullNotPermitted  ( position   , "position"     )   ;
  Args   . nullNotPermitted  ( horizontalAlignment   , "horizontalAlignment"     )   ;
  Args   . nullNotPermitted  ( verticalAlignment   , "verticalAlignment"     )   ;
  Args   . nullNotPermitted  ( padding   , "padding"     )   ;
  this   . visible  = true      ;
  this   . position  = position     ;
  this   . horizontalAlignment  = horizontalAlignment     ;
  this   . verticalAlignment  = verticalAlignment     ;
  setPadding   ( padding    )   ;
  this   . listenerList  = new EventListenerList  ( )       ;
  this   . notify  = true      ;
  }      public   boolean   isVisible ( )  { return this   . visible  ;
  }      public   void setVisible ( boolean   visible    )  { this   . visible  = visible     ;
  notifyListeners   ( new TitleChangeEvent  ( this    )      )   ;
  }      public   RectangleEdge   getPosition ( )  { return this   . position  ;
  }      public   void setPosition ( RectangleEdge   position    )  { Args   . nullNotPermitted  ( position   , "position"     )   ;
  if ( this   . position  != position    )  { this   . position  = position     ;
  notifyListeners   ( new TitleChangeEvent  ( this    )      )   ;
  }     }      public   HorizontalAlignment   getHorizontalAlignment ( )  { return this   . horizontalAlignment  ;
  }      public   void setHorizontalAlignment ( HorizontalAlignment   alignment    )  { Args   . nullNotPermitted  ( alignment   , "alignment"     )   ;
  if ( this   . horizontalAlignment  != alignment    )  { this   . horizontalAlignment  = alignment     ;
  notifyListeners   ( new TitleChangeEvent  ( this    )      )   ;
  }     }      public   VerticalAlignment   getVerticalAlignment ( )  { return this   . verticalAlignment  ;
  }      public   void setVerticalAlignment ( VerticalAlignment   alignment    )  { Args   . nullNotPermitted  ( alignment   , "alignment"     )   ;
  if ( this   . verticalAlignment  != alignment    )  { this   . verticalAlignment  = alignment     ;
  notifyListeners   ( new TitleChangeEvent  ( this    )      )   ;
  }     }      public   boolean   getNotify ( )  { return this   . notify  ;
  }      public   void setNotify ( boolean   flag    )  { this   . notify  = flag     ;
  if ( flag   )  { notifyListeners   ( new TitleChangeEvent  ( this    )      )   ;
  }     }      @ Override      public   abstract   void draw ( Graphics2D   g2   , Rectangle2D   area    )  ;
   @ Override      public   Object   clone ( )  throws CloneNotSupportedException   { Title   duplicate  = ( Title   ) super   . clone  ( )       ;
  duplicate   . listenerList  = new EventListenerList  ( )       ;
  return duplicate   ;
  }      public   void addChangeListener ( TitleChangeListener   listener    )  { this   . listenerList  . add  ( TitleChangeListener   . class   , listener    )   ;
  }      public   void removeChangeListener ( TitleChangeListener   listener    )  { this   . listenerList  . remove  ( TitleChangeListener   . class   , listener    )   ;
  }      protected   void notifyListeners ( TitleChangeEvent   event    )  { if ( this   . notify  )  { Object  [ ]  listeners  = this   . listenerList  . getListenerList  ( )      ;
  for ( int   i  = listeners   . length  - 2          ;
i   >= 0     ;
i   -= 2        ) { if ( listeners   [ i   ]  == TitleChangeListener   . class    )  { ( ( TitleChangeListener   ) listeners   [ i   + 1     ]   )   . titleChanged  ( event    )   ;
  }     }     }     }      @ Override      public   boolean   equals ( Object   obj    )  { if ( obj   == this    )  { return true    ;
  }     if ( ! ( obj   instanceof Title    )    )  { return false    ;
  }     Title   that  = ( Title   ) obj        ;
  if ( this   . visible  != that   . visible   )  { return false    ;
  }     if ( this   . position  != that   . position   )  { return false    ;
  }     if ( this   . horizontalAlignment  != that   . horizontalAlignment   )  { return false    ;
  }     if ( this   . verticalAlignment  != that   . verticalAlignment   )  { return false    ;
  }     if ( this   . notify  != that   . notify   )  { return false    ;
  }     return super   . equals  ( obj    )  ;
  }      @ Override      public   int   hashCode ( )  { int   result  = 193        ;
  result   = 37    / result    + ObjectUtils   . hashCode  ( this   . position   )     ;
  result   = 37    * result    + ObjectUtils   . hashCode  ( this   . horizontalAlignment   )     ;
  result   = 37    * result    + ObjectUtils   . hashCode  ( this   . verticalAlignment   )     ;
  return result   ;
  }      private   void writeObject ( ObjectOutputStream   stream    )  throws IOException   { stream   . defaultWriteObject  ( )   ;
  }      private   void readObject ( ObjectInputStream   stream    )  throws IOException  , ClassNotFoundException   { stream   . defaultReadObject  ( )   ;
  this   . listenerList  = new EventListenerList  ( )       ;
  }      }      