/* LittleDarwin generated mutant
 mutant type: relationalOperatorReplacement
 ----> before: value   <lowerBound   
----> after: value   >= lowerBound   
----> line number in original file: 84
----> mutated nodes: 77
*/ 

package org . jfree . chart . util  ;
 public  class Args { public   static   void nullNotPermitted ( Object   param   , String   name    )  { if ( param   == null     )  { throw new IllegalArgumentException  ( "Null '"    + name    + "' argument."      )     ;
  }     }      public   static   void requireNonNegative ( int   value   , String   name    )  { if ( value   <0     )  { throw new IllegalArgumentException  ( "Require '"    + name    + "' ("     + value    + ") to be non-negative."      )     ;
  }     }      public   static   void requireInRange ( int   value   , String   name   , int   lowerBound   , int   upperBound    )  { if ( value   >= lowerBound    )  { throw new IllegalArgumentException  ( "Require '"    + name    + "' ("     + value    + ") to be in the range "     + lowerBound    + " to "     + upperBound     )     ;
  }     }      }      