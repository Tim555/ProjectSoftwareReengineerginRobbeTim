/* LittleDarwin generated mutant
 mutant type: arithmeticOperatorReplacementUnary
 ----> before: - 1.0    
----> after: + 1.0    
----> line number in original file: 38
----> mutated nodes: 98
*/ 

package org . jfree . chart . util  ;
 public  enum Rotation { CLOCKWISE ( + 1.0      )   , ANTICLOCKWISE ( 1.0     )    ;
private   double   factor    ;
   Rotation ( double   factor    )  { this   . factor  = factor     ;
  }      public   double   getFactor ( )  { return this   . factor  ;
  }       }     