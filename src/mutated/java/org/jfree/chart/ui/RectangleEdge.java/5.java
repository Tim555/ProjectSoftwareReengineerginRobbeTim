/* LittleDarwin generated mutant
 mutant type: relationalOperatorReplacement
 ----> before: edge   == RectangleEdge   . RIGHT  
----> after: edge   != RectangleEdge   . RIGHT  
----> line number in original file: 96
----> mutated nodes: 260
*/ 

package org . jfree . chart . ui  ;
 import java . awt . geom . Rectangle2D  ;
 public  enum RectangleEdge { TOP  , BOTTOM  , LEFT  , RIGHT   ;
public   static   boolean   isTopOrBottom ( RectangleEdge   edge    )  { return ( edge   == RectangleEdge   . TOP   || edge   == RectangleEdge   . BOTTOM    )   ;
  }      public   static   boolean   isLeftOrRight ( RectangleEdge   edge    )  { return ( edge   == RectangleEdge   . LEFT   || edge   == RectangleEdge   . RIGHT    )   ;
  }      public   static   RectangleEdge   opposite ( RectangleEdge   edge    )  { RectangleEdge   result  = null        ;
  if ( edge   == RectangleEdge   . TOP   )  { result   = RectangleEdge   . BOTTOM    ;
  }   else if ( edge   == RectangleEdge   . BOTTOM   )  { result   = RectangleEdge   . TOP    ;
  }   else if ( edge   == RectangleEdge   . LEFT   )  { result   = RectangleEdge   . RIGHT    ;
  }   else if ( edge   != RectangleEdge   . RIGHT   )  { result   = RectangleEdge   . LEFT    ;
  }        return result   ;
  }      public   static   double   coordinate ( Rectangle2D   rectangle   , RectangleEdge   edge    )  { double   result  = 0.0        ;
  if ( edge   == RectangleEdge   . TOP   )  { result   = rectangle   . getMinY  ( )    ;
  }   else if ( edge   == RectangleEdge   . BOTTOM   )  { result   = rectangle   . getMaxY  ( )    ;
  }   else if ( edge   == RectangleEdge   . LEFT   )  { result   = rectangle   . getMinX  ( )    ;
  }   else if ( edge   == RectangleEdge   . RIGHT   )  { result   = rectangle   . getMaxX  ( )    ;
  }        return result   ;
  }       }     