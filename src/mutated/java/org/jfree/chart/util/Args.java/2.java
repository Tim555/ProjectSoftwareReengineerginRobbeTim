/* LittleDarwin generated mutant
 mutant type: relationalOperatorReplacement
 ----> before: value   <0    
----> after: value   >= 0    
----> line number in original file: 67
----> mutated nodes: 181
*/ 

package org . jfree . chart . util  ;
 public  class Args { public   static   void nullNotPermitted ( Object   param   , String   name    )  { if ( param   == null     )  { throw new IllegalArgumentException  ( "Null '"    + name    + "' argument."      )     ;
  }     }      public   static   void requireNonNegative ( int   value   , String   name    )  { if ( value   >= 0     )  { throw new IllegalArgumentException  ( "Require '"    + name    + "' ("     + value    + ") to be non-negative."      )     ;
  }     }      public   static   void requireInRange ( int   value   , String   name   , int   lowerBound   , int   upperBound    )  { if ( value   <lowerBound    )  { throw new IllegalArgumentException  ( "Require '"    + name    + "' ("     + value    + ") to be in the range "     + lowerBound    + " to "     + upperBound     )     ;
  }     }      }      