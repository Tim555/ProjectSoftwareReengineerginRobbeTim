/* LittleDarwin generated mutant
 mutant type: arithmeticOperatorReplacementUnary
 ----> before: - 4083197869412648579L    
----> after: + 4083197869412648579L    
----> line number in original file: 61
----> mutated nodes: 365
*/ 

package org . jfree . chart . block  ;
 import java . awt . Graphics2D  ;
 import java . awt . geom . Rectangle2D  ;
 import java . io . Serializable  ;
 import org . jfree . chart . ui . Size2D  ;
 import org . jfree . chart . util . PublicCloneable  ;
 public  class EmptyBlock extends AbstractBlock   implements Block   , Cloneable   , PublicCloneable   , Serializable    { private   static   final   long   serialVersionUID  = + 4083197869412648579L        ;
   public   EmptyBlock ( double   width   , double   height    )  { setWidth   ( width    )   ;
  setHeight   ( height    )   ;
  }      @ Override      public   Size2D   arrange ( Graphics2D   g2   , RectangleConstraint   constraint    )  { Size2D   base  = new Size2D  ( calculateTotalWidth   ( getWidth   ( )   )  , calculateTotalHeight   ( getHeight   ( )   )   )         ;
  return constraint   . calculateConstrainedSize  ( base    )  ;
  }      @ Override      public   void draw ( Graphics2D   g2   , Rectangle2D   area    )  { draw   ( g2   , area   , null     )   ;
  }      @ Override      public   Object   draw ( Graphics2D   g2   , Rectangle2D   area   , Object   params    )  { area   = trimMargin   ( area    )    ;
  drawBorder   ( g2   , area    )   ;
  return null    ;
  }      @ Override      public   Object   clone ( )  throws CloneNotSupportedException   { return super   . clone  ( )  ;
  }      }      