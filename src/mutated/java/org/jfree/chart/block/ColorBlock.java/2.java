/* LittleDarwin generated mutant
 mutant type: conditionalOperatorDeletion
 ----> before: ! PaintUtils   . equal  ( this   . paint  , that   . paint   )  
----> after:   PaintUtils   . equal  ( this   . paint  , that   . paint   )  
----> line number in original file: 158
----> mutated nodes: 176
*/ 

package org . jfree . chart . block  ;
 import java . awt . Graphics2D  ;
 import java . awt . Paint  ;
 import java . awt . geom . Rectangle2D  ;
 import java . io . IOException  ;
 import java . io . ObjectInputStream  ;
 import java . io . ObjectOutputStream  ;
 import org . jfree . chart . ui . Size2D  ;
 import org . jfree . chart . util . PaintUtils  ;
 import org . jfree . chart . util . Args  ;
 import org . jfree . chart . util . SerialUtils  ;
 public  class ColorBlock extends AbstractBlock   implements Block    { static   final   long   serialVersionUID  = 3383866145634010865L       ;
   private   transient  Paint   paint    ;
   public   ColorBlock ( Paint   paint   , double   width   , double   height    )  { Args   . nullNotPermitted  ( paint   , "paint"     )   ;
  this   . paint  = paint     ;
  setWidth   ( width    )   ;
  setHeight   ( height    )   ;
  }      public   Paint   getPaint ( )  { return this   . paint  ;
  }      @ Override      public   Size2D   arrange ( Graphics2D   g2   , RectangleConstraint   constraint    )  { return new Size2D  ( calculateTotalWidth   ( getWidth   ( )   )  , calculateTotalHeight   ( getHeight   ( )   )   )     ;
  }      @ Override      public   void draw ( Graphics2D   g2   , Rectangle2D   area    )  { area   = trimMargin   ( area    )    ;
  drawBorder   ( g2   , area    )   ;
  area   = trimBorder   ( area    )    ;
  area   = trimPadding   ( area    )    ;
  g2   . setPaint  ( this   . paint   )   ;
  g2   . fill  ( area    )   ;
  }      @ Override      public   Object   draw ( Graphics2D   g2   , Rectangle2D   area   , Object   params    )  { draw   ( g2   , area    )   ;
  return null    ;
  }      @ Override      public   boolean   equals ( Object   obj    )  { if ( obj   == this    )  { return true    ;
  }     if ( ! ( obj   instanceof ColorBlock    )    )  { return false    ;
  }     ColorBlock   that  = ( ColorBlock   ) obj        ;
  if (   PaintUtils   . equal  ( this   . paint  , that   . paint   )   )  { return false    ;
  }     return super   . equals  ( obj    )  ;
  }      private   void writeObject ( ObjectOutputStream   stream    )  throws IOException   { stream   . defaultWriteObject  ( )   ;
  SerialUtils   . writePaint  ( this   . paint  , stream    )   ;
  }      private   void readObject ( ObjectInputStream   stream    )  throws IOException  , ClassNotFoundException   { stream   . defaultReadObject  ( )   ;
  this   . paint  = SerialUtils   . readPaint  ( stream    )    ;
  }      }      