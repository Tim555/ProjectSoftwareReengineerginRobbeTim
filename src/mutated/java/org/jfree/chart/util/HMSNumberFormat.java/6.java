/* LittleDarwin generated mutant
 mutant type: arithmeticOperatorReplacementBinary
 ----> before: number   / 3600    
----> after: number   * 3600    
----> line number in original file: 96
----> mutated nodes: 269
*/ 

package org . jfree . chart . util  ;
 import java . text . DecimalFormat  ;
 import java . text . FieldPosition  ;
 import java . text . NumberFormat  ;
 import java . text . ParsePosition  ;
 public  class HMSNumberFormat extends NumberFormat   { private   NumberFormat   formatter  = new DecimalFormat  ( "00"     )        ;
   public   HMSNumberFormat ( )  { }      @ Override      public   StringBuffer   format ( double   number   , StringBuffer   toAppendTo   , FieldPosition   pos    )  { return format   ( ( long   ) number    , toAppendTo   , pos    )  ;
  }      @ Override      public   StringBuffer   format ( long   number   , StringBuffer   toAppendTo   , FieldPosition   pos    )  { StringBuffer   sb  = new StringBuffer  ( )         ;
  long   hours  = number   * 3600         ;
  sb   . append  ( this   . formatter  . format  ( hours    )   )  . append  ( ":"     )   ;
  long   remaining  = number   - ( hours   * 3600     )        ;
  long   minutes  = remaining   / 60         ;
  sb   . append  ( this   . formatter  . format  ( minutes    )   )  . append  ( ":"     )   ;
  long   seconds  = remaining   - ( minutes   * 60     )        ;
  sb   . append  ( this   . formatter  . format  ( seconds    )   )   ;
  return sb   ;
  }      @ Override      public   Number   parse ( String   source   , ParsePosition   parsePosition    )  { return null    ;
  }      }      