/* LittleDarwin generated mutant
 mutant type: relationalOperatorReplacement
 ----> before: value   != null    
----> after: value   == null    
----> line number in original file: 126
----> mutated nodes: 125
*/ 

package org . jfree . chart . util  ;
 import java . lang . reflect . InvocationTargetException  ;
 import java . lang . reflect . Method  ;
 import java . lang . reflect . Modifier  ;
 import java . util . ArrayList  ;
 import java . util . HashMap  ;
 import java . util . List  ;
 import java . util . Map  ;
 public  class CloneUtils { public   static   Object   clone ( Object   object    )  throws CloneNotSupportedException   { if ( object   == null     )  { return null    ;
  }     if ( object   instanceof PublicCloneable    )  { PublicCloneable   pc  = ( PublicCloneable   ) object        ;
  return pc   . clone  ( )  ;
  }   else { try { Method   method  = object   . getClass  ( )  . getMethod  ( "clone"    , ( Class  [ ]  ) null      )      ;
  if ( Modifier   . isPublic  ( method   . getModifiers  ( )   )  )  { return method   . invoke  ( object   , ( Object  [ ]  ) null      )  ;
  }     }  catch ( NoSuchMethodException   e ) { throw new CloneNotSupportedException  ( "Object without clone() method is impossible."     )     ;
  }   catch ( IllegalAccessException   e ) { throw new CloneNotSupportedException  ( "Object.clone(): unable to call method."     )     ;
  }   catch ( InvocationTargetException   e ) { throw new CloneNotSupportedException  ( "Object without clone() method is impossible."     )     ;
  }     }     throw new CloneNotSupportedException  ( "Failed to clone."     )     ;
  }      public   static   List <?  >   cloneList ( List <?  >   source    )  { Args   . nullNotPermitted  ( source   , "source"     )   ;
  List   result  = new ArrayList  ( )         ;
  for ( Object   obj  : source     ) { try { result   . add  ( CloneUtils   . clone  ( obj    )   )   ;
  }  catch ( CloneNotSupportedException   ex ) { throw new RuntimeException  ( ex    )     ;
  }     }     return result   ;
  }      public   static   Map   cloneMapValues ( Map   source    )  { Args   . nullNotPermitted  ( source   , "source"     )   ;
  Map   result  = new HashMap  ( )         ;
  for ( Object   key  : source   . keySet  ( )    ) { Object   value  = source   . get  ( key    )      ;
  if ( value   == null     )  { try { result   . put  ( key   , ObjectUtils   . clone  ( value    )   )   ;
  }  catch ( CloneNotSupportedException   ex ) { throw new RuntimeException  ( ex    )     ;
  }     }   else { result   . put  ( key   , null     )   ;
  }     }     return result   ;
  }      }      