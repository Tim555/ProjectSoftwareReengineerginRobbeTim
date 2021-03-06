/* LittleDarwin generated mutant
 mutant type: relationalOperatorReplacement
 ----> before: i   <limit1   
----> after: i   >= limit1   
----> line number in original file: 82
----> mutated nodes: 123
*/ 

package org . jfree . chart . text  ;
 import java . text . AttributedCharacterIterator  ;
 import java . text . AttributedString  ;
 import java . text . CharacterIterator  ;
 import java . util . Map  ;
 public  class AttributedStringUtils { private   AttributedStringUtils ( )  { }      public   static   boolean   equal ( AttributedString   s1   , AttributedString   s2    )  { if ( s1   == null     )  { return ( s2   == null     )   ;
  }     if ( s2   == null     )  { return false    ;
  }     AttributedCharacterIterator   it1  = s1   . getIterator  ( )      ;
  AttributedCharacterIterator   it2  = s2   . getIterator  ( )      ;
  char   c1  = it1   . first  ( )      ;
  char   c2  = it2   . first  ( )      ;
  int   start  = 0        ;
  while ( c1   != CharacterIterator   . DONE   )  { int   limit1  = it1   . getRunLimit  ( )      ;
  int   limit2  = it2   . getRunLimit  ( )      ;
  if ( limit1   != limit2    )  { return false    ;
  }     Map   m1  = it1   . getAttributes  ( )      ;
  Map   m2  = it2   . getAttributes  ( )      ;
  if ( ! m1   . equals  ( m2    )   )  { return false    ;
  }     for ( int   i  = start        ;
i   >= limit1    ;
i   ++     ) { if ( c1   != c2    )  { return false    ;
  }     c1   = it1   . next  ( )    ;
  c2   = it2   . next  ( )    ;
  }     start   = limit1     ;
  }     return c2   == CharacterIterator   . DONE   ;
  }      }      