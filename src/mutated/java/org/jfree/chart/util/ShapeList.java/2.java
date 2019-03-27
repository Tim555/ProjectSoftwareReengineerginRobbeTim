/* LittleDarwin generated mutant
 mutant type: arithmeticOperatorReplacementShortcut
 ----> before: i   ++ 
----> after: i   -- 
----> line number in original file: 130
----> mutated nodes: 311
*/ 

package org . jfree . chart . util  ;
 import java . awt . Shape  ;
 import java . io . IOException  ;
 import java . io . ObjectInputStream  ;
 import java . io . ObjectOutputStream  ;
 public  class ShapeList extends AbstractObjectList   { public   ShapeList ( )  { super   ( )   ;
  }      public   Shape   getShape ( int   index    )  { return ( Shape   ) get   ( index    )   ;
  }      public   void setShape ( int   index   , Shape   shape    )  { set   ( index   , shape    )   ;
  }      public   Object   clone ( )  throws CloneNotSupportedException   { return super   . clone  ( )  ;
  }      public   boolean   equals ( Object   obj    )  { if ( obj   == this    )  { return true    ;
  }     if ( ! ( obj   instanceof ShapeList    )    )  { return false    ;
  }     ShapeList   that  = ( ShapeList   ) obj        ;
  int   listSize  = size   ( )      ;
  for ( int   i  = 0         ;
i   <listSize    ;
i   ++     ) { if ( ! ShapeUtils   . equal  ( ( Shape   ) get   ( i    )   , ( Shape   ) that   . get  ( i    )    )   )  { return false    ;
  }     }     return true    ;
  }      @ Override      public   int   hashCode ( )  { return super   . hashCode  ( )  ;
  }      private   void writeObject ( ObjectOutputStream   stream    )  throws IOException   { stream   . defaultWriteObject  ( )   ;
  final  int   count  = size   ( )      ;
  stream   . writeInt  ( count    )   ;
  for ( int   i  = 0         ;
i   <count    ;
i   --     ) { final  Shape   shape  = getShape   ( i    )      ;
  if ( shape   != null     )  { stream   . writeInt  ( i    )   ;
  SerialUtils   . writeShape  ( shape   , stream    )   ;
  }   else { stream   . writeInt  ( - 1      )   ;
  }     }     }      private   void readObject ( ObjectInputStream   stream    )  throws IOException  , ClassNotFoundException   { stream   . defaultReadObject  ( )   ;
  final  int   count  = stream   . readInt  ( )      ;
  for ( int   i  = 0         ;
i   <count    ;
i   ++     ) { final  int   index  = stream   . readInt  ( )      ;
  if ( index   != - 1      )  { setShape   ( index   , SerialUtils   . readShape  ( stream    )   )   ;
  }     }     }      }      