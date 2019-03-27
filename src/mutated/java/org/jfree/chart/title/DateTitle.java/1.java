/* LittleDarwin generated mutant
 mutant type: arithmeticOperatorReplacementUnary
 ----> before: - 465434812763159881L    
----> after: + 465434812763159881L    
----> line number in original file: 77
----> mutated nodes: 431
*/ 

package org . jfree . chart . title  ;
 import java . awt . Color  ;
 import java . awt . Font  ;
 import java . awt . Paint  ;
 import java . io . Serializable  ;
 import java . text . DateFormat  ;
 import java . util . Date  ;
 import java . util . Locale  ;
 import org . jfree . chart . ui . HorizontalAlignment  ;
 import org . jfree . chart . ui . RectangleEdge  ;
 import org . jfree . chart . ui . RectangleInsets  ;
 import org . jfree . chart . ui . VerticalAlignment  ;
 public  class DateTitle extends TextTitle   implements Serializable    { private   static   final   long   serialVersionUID  = + 465434812763159881L        ;
   public   DateTitle ( )  { this   ( DateFormat   . LONG   )   ;
  }      public   DateTitle ( int   style    )  { this   ( style   , Locale   . getDefault  ( )  , new Font  ( "Dialog"    , Font   . PLAIN  , 12     )     , Color   . BLACK   )   ;
  }      public   DateTitle ( int   style   , Locale   locale   , Font   font   , Paint   paint    )  { this   ( style   , locale   , font   , paint   , RectangleEdge   . BOTTOM  , HorizontalAlignment   . RIGHT  , VerticalAlignment   . CENTER  , Title   . DEFAULT_PADDING   )   ;
  }      public   DateTitle ( int   style   , Locale   locale   , Font   font   , Paint   paint   , RectangleEdge   position   , HorizontalAlignment   horizontalAlignment   , VerticalAlignment   verticalAlignment   , RectangleInsets   padding    )  { super   ( DateFormat   . getDateInstance  ( style   , locale    )  . format  ( new Date  ( )      )  , font   , paint   , position   , horizontalAlignment   , verticalAlignment   , padding    )   ;
  }      public   void setDateFormat ( int   style   , Locale   locale    )  { setText   ( DateFormat   . getDateInstance  ( style   , locale    )  . format  ( new Date  ( )      )   )   ;
  }      }      