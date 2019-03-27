/* LittleDarwin generated mutant
 mutant type: conditionalOperatorDeletion
 ----> before: ! ( obj   instanceof CategoryLabelEntity    )   
----> after:   ( obj   instanceof CategoryLabelEntity    )   
----> line number in original file: 95
----> mutated nodes: 357
*/ 

package org . jfree . chart . entity  ;
 import java . awt . Shape  ;
 import org . jfree . chart . HashUtils  ;
 import org . jfree . chart . axis . CategoryAxis  ;
 import org . jfree . chart . util . ObjectUtils  ;
 public  class CategoryLabelEntity extends TickLabelEntity   { private   Comparable   key    ;
   public   CategoryLabelEntity ( Comparable   key   , Shape   area   , String   toolTipText   , String   urlText    )  { super   ( area   , toolTipText   , urlText    )   ;
  this   . key  = key     ;
  }      public   Comparable   getKey ( )  { return this   . key  ;
  }      @ Override      public   boolean   equals ( Object   obj    )  { if ( obj   == this    )  { return true    ;
  }     if (   ( obj   instanceof CategoryLabelEntity    )    )  { return false    ;
  }     CategoryLabelEntity   that  = ( CategoryLabelEntity   ) obj        ;
  if ( ! ObjectUtils   . equal  ( this   . key  , that   . key   )   )  { return false    ;
  }     return super   . equals  ( obj    )  ;
  }      @ Override      public   int   hashCode ( )  { int   result  = super   . hashCode  ( )      ;
  result   = HashUtils   . hashCode  ( result   , this   . key   )    ;
  return result   ;
  }      @ Override      public   String   toString ( )  { StringBuilder   sb  = new StringBuilder  ( "CategoryLabelEntity: "     )         ;
  sb   . append  ( "category="     )   ;
  sb   . append  ( this   . key   )   ;
  sb   . append  ( ", tooltip="     )  . append  ( getToolTipText   ( )   )   ;
  sb   . append  ( ", url="     )  . append  ( getURLText   ( )   )   ;
  return sb   . toString  ( )  ;
  }      }      