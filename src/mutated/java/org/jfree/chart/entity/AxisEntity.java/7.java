/* LittleDarwin generated mutant
 mutant type: conditionalOperatorDeletion
 ----> before: ! ( obj   instanceof AxisEntity    )   
----> after:   ( obj   instanceof AxisEntity    )   
----> line number in original file: 144
----> mutated nodes: 512
*/ 

package org . jfree . chart . entity  ;
 import java . awt . Shape  ;
 import java . io . IOException  ;
 import java . io . ObjectInputStream  ;
 import java . io . ObjectOutputStream  ;
 import org . jfree . chart . HashUtils  ;
 import org . jfree . chart . axis . Axis  ;
 import org . jfree . chart . util . ObjectUtils  ;
 import org . jfree . chart . util . Args  ;
 import org . jfree . chart . util . SerialUtils  ;
 public  class AxisEntity extends ChartEntity   { private   static   final   long   serialVersionUID  = - 4445994133561919083L        ;
   private   Axis   axis    ;
   public   AxisEntity ( Shape   area   , Axis   axis    )  { this   ( area   , axis   , null     )   ;
  }      public   AxisEntity ( Shape   area   , Axis   axis   , String   toolTipText    )  { this   ( area   , axis   , toolTipText   , null     )   ;
  }      public   AxisEntity ( Shape   area   , Axis   axis   , String   toolTipText   , String   urlText    )  { super   ( area   , toolTipText   , urlText    )   ;
  Args   . nullNotPermitted  ( axis   , "axis"     )   ;
  this   . axis  = axis     ;
  }      public   Axis   getAxis ( )  { return this   . axis  ;
  }      @ Override      public   String   toString ( )  { StringBuilder   sb  = new StringBuilder  ( "AxisEntity: "     )         ;
  sb   . append  ( "tooltip = "     )   ;
  sb   . append  ( getToolTipText   ( )   )   ;
  return sb   . toString  ( )  ;
  }      @ Override      public   boolean   equals ( Object   obj    )  { if ( obj   == this    )  { return true    ;
  }     if (   ( obj   instanceof AxisEntity    )    )  { return false    ;
  }     AxisEntity   that  = ( AxisEntity   ) obj        ;
  if ( ! getArea   ( )  . equals  ( that   . getArea  ( )   )   )  { return false    ;
  }     if ( ! ObjectUtils   . equal  ( getToolTipText   ( )  , that   . getToolTipText  ( )   )   )  { return false    ;
  }     if ( ! ObjectUtils   . equal  ( getURLText   ( )  , that   . getURLText  ( )   )   )  { return false    ;
  }     if ( ! ( this   . axis  . equals  ( that   . axis   )  )    )  { return false    ;
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