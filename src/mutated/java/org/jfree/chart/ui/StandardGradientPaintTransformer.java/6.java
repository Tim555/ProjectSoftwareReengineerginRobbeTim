/* LittleDarwin generated mutant
 mutant type: conditionalOperatorDeletion
 ----> before: ! ( obj   instanceof StandardGradientPaintTransformer    )   
----> after:   ( obj   instanceof StandardGradientPaintTransformer    )   
----> line number in original file: 139
----> mutated nodes: 181
*/ 

package org . jfree . chart . ui  ;
 import java . awt . GradientPaint  ;
 import java . awt . Shape  ;
 import java . awt . geom . Rectangle2D  ;
 import java . io . Serializable  ;
 import org . jfree . chart . util . PublicCloneable  ;
 public  class StandardGradientPaintTransformer implements GradientPaintTransformer   , Cloneable   , PublicCloneable   , Serializable    { private   static   final   long   serialVersionUID  = - 8155025776964678320L        ;
   private   GradientPaintTransformType   type    ;
   public   StandardGradientPaintTransformer ( )  { this   ( GradientPaintTransformType   . VERTICAL   )   ;
  }      public   StandardGradientPaintTransformer ( final  GradientPaintTransformType   type    )  { if ( type   == null     )  { throw new IllegalArgumentException  ( "Null 'type' argument."     )     ;
  }     this   . type  = type     ;
  }      public   GradientPaintTransformType   getType ( )  { return this   . type  ;
  }      @ Override      public   GradientPaint   transform ( GradientPaint   paint   , Shape   target    )  { GradientPaint   result  = paint       ;
  Rectangle2D   bounds  = target   . getBounds2D  ( )      ;
  if ( this   . type  . equals  ( GradientPaintTransformType   . VERTICAL   )  )  { result   = new GradientPaint  ( ( float   ) bounds   . getCenterX  ( )   , ( float   ) bounds   . getMinY  ( )   , paint   . getColor1  ( )  , ( float   ) bounds   . getCenterX  ( )   , ( float   ) bounds   . getMaxY  ( )   , paint   . getColor2  ( )   )       ;
  }   else if ( this   . type  . equals  ( GradientPaintTransformType   . HORIZONTAL   )  )  { result   = new GradientPaint  ( ( float   ) bounds   . getMinX  ( )   , ( float   ) bounds   . getCenterY  ( )   , paint   . getColor1  ( )  , ( float   ) bounds   . getMaxX  ( )   , ( float   ) bounds   . getCenterY  ( )   , paint   . getColor2  ( )   )       ;
  }   else if ( this   . type  . equals  ( GradientPaintTransformType   . CENTER_HORIZONTAL   )  )  { result   = new GradientPaint  ( ( float   ) bounds   . getCenterX  ( )   , ( float   ) bounds   . getCenterY  ( )   , paint   . getColor2  ( )  , ( float   ) bounds   . getMaxX  ( )   , ( float   ) bounds   . getCenterY  ( )   , paint   . getColor1  ( )  , true     )       ;
  }   else if ( this   . type  . equals  ( GradientPaintTransformType   . CENTER_VERTICAL   )  )  { result   = new GradientPaint  ( ( float   ) bounds   . getCenterX  ( )   , ( float   ) bounds   . getMinY  ( )   , paint   . getColor1  ( )  , ( float   ) bounds   . getCenterX  ( )   , ( float   ) bounds   . getCenterY  ( )   , paint   . getColor2  ( )  , true     )       ;
  }        return result   ;
  }      @ Override      public   boolean   equals ( Object   obj    )  { if ( obj   == this    )  { return true    ;
  }     if (   ( obj   instanceof StandardGradientPaintTransformer    )    )  { return false    ;
  }     StandardGradientPaintTransformer   that  = ( StandardGradientPaintTransformer   ) obj        ;
  if ( this   . type  != that   . type   )  { return false    ;
  }     return true    ;
  }      public   Object   clone ( )  throws CloneNotSupportedException   { return super   . clone  ( )  ;
  }      public   int   hashCode ( )  { return ( this   . type  != null     ? this   . type  . hashCode  ( )  : 0     )   ;
  }      }      