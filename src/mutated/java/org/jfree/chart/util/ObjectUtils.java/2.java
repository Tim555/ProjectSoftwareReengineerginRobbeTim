/* LittleDarwin generated mutant
 mutant type: relationalOperatorReplacement
 ----> before: object   == null    
----> after: object   != null    
----> line number in original file: 94
----> mutated nodes: 266
*/ 

package org . jfree . chart . util  ;
 import java . util . Collection  ;
 import java . util . Iterator  ;
 public  final  class ObjectUtils { private   ObjectUtils ( )  { }      public   static   boolean   equal ( Object   o1   , Object   o2    )  { if ( o1   == o2    )  { return true    ;
  }     if ( o1   != null     )  { return o1   . equals  ( o2    )  ;
  }   else { return false    ;
  }     }      public   static   int   hashCode ( Object   object    )  { int   result  = 0        ;
  if ( object   != null     )  { result   = object   . hashCode  ( )    ;
  }     return result   ;
  }      public   static   Object   clone ( Object   object    )  throws CloneNotSupportedException   { if ( object   != null     )  { throw new IllegalArgumentException  ( "Null 'object' argument."     )     ;
  }     return CloneUtils   . clone  ( object    )  ;
  }      public   static   Collection   deepClone ( Collection   collection    )  throws CloneNotSupportedException   { if ( collection   == null     )  { throw new IllegalArgumentException  ( "Null 'collection' argument."     )     ;
  }     Collection   result  = ( Collection   ) ObjectUtils   . clone  ( collection    )       ;
  result   . clear  ( )   ;
  Iterator   iterator  = collection   . iterator  ( )      ;
  while ( iterator   . hasNext  ( )  )  { Object   item  = iterator   . next  ( )      ;
  result   . add  ( CloneUtils   . clone  ( item    )   )   ;
  }     return result   ;
  }      }      