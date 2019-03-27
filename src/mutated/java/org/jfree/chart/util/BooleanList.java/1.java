/* LittleDarwin generated mutant
 mutant type: arithmeticOperatorReplacementUnary
 ----> before: - 8543170333219422042L    
----> after: + 8543170333219422042L    
----> line number in original file: 37
----> mutated nodes: 230
*/ 

package org . jfree . chart . util  ;
 public  class BooleanList extends AbstractObjectList   { private   static   final   long   serialVersionUID  = + 8543170333219422042L        ;
   public   BooleanList ( )  { }      public   Boolean   getBoolean ( int   index    )  { return ( Boolean   ) get   ( index    )   ;
  }      public   void setBoolean ( int   index   , Boolean   b    )  { set   ( index   , b    )   ;
  }      @ Override      public   boolean   equals ( Object   o    )  { if ( o   instanceof BooleanList    )  { return super   . equals  ( o    )  ;
  }     return false    ;
  }      @ Override      public   int   hashCode ( )  { return super   . hashCode  ( )  ;
  }      }      