/* LittleDarwin generated mutant
 mutant type: conditionalOperatorDeletion
 ----> before: ! ( this   . chart  . equals  ( that   . chart   )  )   
----> after:   ( this   . chart  . equals  ( that   . chart   )  )   
----> line number in original file: 156
----> mutated nodes: 313
*/ 

package org . jfree . chart . entity  ;
 import java . awt . Shape  ;
 import java . io . IOException  ;
 import java . io . ObjectInputStream  ;
 import java . io . ObjectOutputStream  ;
 import org . jfree . chart . JFreeChart  ;
 import org . jfree . chart . HashUtils  ;
 import org . jfree . chart . util . ObjectUtils  ;
 import org . jfree . chart . util . Args  ;
 import org . jfree . chart . util . SerialUtils  ;
 public  class JFreeChartEntity extends ChartEntity   { private   static   final   long   serialVersionUID  = - 4445994133561919083L        ;
   private   JFreeChart   chart    ;
   public   JFreeChartEntity ( Shape   area   , JFreeChart   chart    )  { this   ( area   , chart   , null     )   ;
  }      public   JFreeChartEntity ( Shape   area   , JFreeChart   chart   , String   toolTipText    )  { this   ( area   , chart   , toolTipText   , null     )   ;
  }      public   JFreeChartEntity ( Shape   area   , JFreeChart   chart   , String   toolTipText   , String   urlText    )  { super   ( area   , toolTipText   , urlText    )   ;
  Args   . nullNotPermitted  ( chart   , "chart"     )   ;
  this   . chart  = chart     ;
  }      public   JFreeChart   getChart ( )  { return this   . chart  ;
  }      @ Override      public   String   toString ( )  { StringBuilder   sb  = new StringBuilder  ( "JFreeChartEntity: "     )         ;
  sb   . append  ( "tooltip = "     )   ;
  sb   . append  ( getToolTipText   ( )   )   ;
  return sb   . toString  ( )  ;
  }      @ Override      public   boolean   equals ( Object   obj    )  { if ( obj   == this    )  { return true    ;
  }     if ( ! ( obj   instanceof JFreeChartEntity    )    )  { return false    ;
  }     JFreeChartEntity   that  = ( JFreeChartEntity   ) obj        ;
  if ( ! getArea   ( )  . equals  ( that   . getArea  ( )   )   )  { return false    ;
  }     if ( ! ObjectUtils   . equal  ( getToolTipText   ( )  , that   . getToolTipText  ( )   )   )  { return false    ;
  }     if ( ! ObjectUtils   . equal  ( getURLText   ( )  , that   . getURLText  ( )   )   )  { return false    ;
  }     if (   ( this   . chart  . equals  ( that   . chart   )  )    )  { return false    ;
  }     return true    ;
  }      @ Override      public   int   hashCode ( )  { int   result  = 39        ;
  result   = HashUtils   . hashCode  ( result   , getToolTipText   ( )   )    ;
  result   = HashUtils   . hashCode  ( result   , getURLText   ( )   )    ;
  return result   ;
  }      @ Override      public   Object   clone ( )  throws CloneNotSupportedException   { return super   . clone  ( )  ;
  }      private   void writeObject ( ObjectOutputStream   stream    )  throws IOException   { stream   . defaultWriteObject  ( )   ;
  SerialUtils   . writeShape  ( getArea   ( )  , stream    )   ;
  }      private   void readObject ( ObjectInputStream   stream    )  throws IOException  , ClassNotFoundException   { stream   . defaultReadObject  ( )   ;
  setArea   ( SerialUtils   . readShape  ( stream    )   )   ;
  }      }      