/* LittleDarwin generated mutant
 mutant type: arithmeticOperatorReplacementUnary
 ----> before: - 708669381577938219L    
----> after: + 708669381577938219L    
----> line number in original file: 41
----> mutated nodes: 826
*/ 

package org . jfree . chart . util  ;
 import java . awt . Paint  ;
 import java . io . IOException  ;
 import java . io . ObjectInputStream  ;
 import java . io . ObjectOutputStream  ;
 public  class PaintList extends AbstractObjectList   { private   static   final   long   serialVersionUID  = + 708669381577938219L        ;
   public   PaintList ( )  { super   ( )   ;
  }      public   Paint   getPaint ( int   index    )  { return ( Paint   ) get   ( index    )   ;
  }      public   void setPaint ( int   index   , Paint   paint    )  { set   ( index   , paint    )   ;
  }      @ Override      public   boolean   equals ( Object   obj    )  { if ( obj   == null     )  { return false    ;
  }     if ( obj   == this    )  { return true    ;
  }     if ( ! ( obj   instanceof PaintList    )    )  { return false    ;
  }     PaintList   that  = ( PaintList   ) obj        ;
  int   listSize  = size   ( )      ;
  for ( int   i  = 0         ;
i   <listSize    ;
i   ++     ) { if ( ! PaintUtils   . equal  ( getPaint   ( i    )  , that   . getPaint  ( i    )   )   )  { return false    ;
  }     }     return true    ;
  }      @ Override      public   int   hashCode ( )  { return super   . hashCode  ( )  ;
  }      private   void writeObject ( ObjectOutputStream   stream    )  throws IOException   { stream   . defaultWriteObject  ( )   ;
  int   count  = size   ( )      ;
  stream   . writeInt  ( count    )   ;
  for ( int   i  = 0         ;
i   <count    ;
i   ++     ) { Paint   paint  = getPaint   ( i    )      ;
  if ( paint   != null     )  { stream   . writeInt  ( i    )   ;
  SerialUtils   . writePaint  ( paint   , stream    )   ;
  }   else { stream   . writeInt  ( - 1      )   ;
  }     }     }      private   void readObject ( ObjectInputStream   stream    )  throws IOException  , ClassNotFoundException   { stream   . defaultReadObject  ( )   ;
  int   count  = stream   . readInt  ( )      ;
  for ( int   i  = 0         ;
i   <count    ;
i   ++     ) { int   index  = stream   . readInt  ( )      ;
  if ( index   != - 1      )  { setPaint   ( index   , SerialUtils   . readPaint  ( stream    )   )   ;
  }     }     }      }      