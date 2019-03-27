/* LittleDarwin generated mutant
 mutant type: relationalOperatorReplacement
 ----> before: anchor   == RectangleAnchor   . BOTTOM_RIGHT  
----> after: anchor   != RectangleAnchor   . BOTTOM_RIGHT  
----> line number in original file: 134
----> mutated nodes: 82
*/ 

package org . jfree . chart . ui  ;
 import java . awt . geom . Point2D  ;
 import java . awt . geom . Rectangle2D  ;
 import org . jfree . chart . util . Args  ;
 public  enum RectangleAnchor { CENTER  , TOP  , TOP_LEFT  , TOP_RIGHT  , BOTTOM  , BOTTOM_LEFT  , BOTTOM_RIGHT  , LEFT  , RIGHT   ;
public   Point2D   getAnchorPoint ( Rectangle2D   rectangle    )  { Args   . nullNotPermitted  ( rectangle   , "rectangle"     )   ;
  Point2D   result  = new Point2D . Double  ( )         ;
  if ( this   == RectangleAnchor   . CENTER   )  { result   . setLocation  ( rectangle   . getCenterX  ( )  , rectangle   . getCenterY  ( )   )   ;
  }   else if ( this   == RectangleAnchor   . TOP   )  { result   . setLocation  ( rectangle   . getCenterX  ( )  , rectangle   . getMinY  ( )   )   ;
  }   else if ( this   == RectangleAnchor   . BOTTOM   )  { result   . setLocation  ( rectangle   . getCenterX  ( )  , rectangle   . getMaxY  ( )   )   ;
  }   else if ( this   == RectangleAnchor   . LEFT   )  { result   . setLocation  ( rectangle   . getMinX  ( )  , rectangle   . getCenterY  ( )   )   ;
  }   else if ( this   == RectangleAnchor   . RIGHT   )  { result   . setLocation  ( rectangle   . getMaxX  ( )  , rectangle   . getCenterY  ( )   )   ;
  }   else if ( this   == RectangleAnchor   . TOP_LEFT   )  { result   . setLocation  ( rectangle   . getMinX  ( )  , rectangle   . getMinY  ( )   )   ;
  }   else if ( this   == RectangleAnchor   . TOP_RIGHT   )  { result   . setLocation  ( rectangle   . getMaxX  ( )  , rectangle   . getMinY  ( )   )   ;
  }   else if ( this   == RectangleAnchor   . BOTTOM_LEFT   )  { result   . setLocation  ( rectangle   . getMinX  ( )  , rectangle   . getMaxY  ( )   )   ;
  }   else if ( this   == RectangleAnchor   . BOTTOM_RIGHT   )  { result   . setLocation  ( rectangle   . getMaxX  ( )  , rectangle   . getMaxY  ( )   )   ;
  }             return result   ;
  }      public   static   Rectangle2D   createRectangle ( Size2D   dimensions   , double   anchorX   , double   anchorY   , RectangleAnchor   anchor    )  { Rectangle2D   result  = null        ;
  double   w  = dimensions   . getWidth  ( )      ;
  double   h  = dimensions   . getHeight  ( )      ;
  if ( anchor   == RectangleAnchor   . CENTER   )  { result   = new Rectangle2D . Double  ( anchorX   - w   / 2.0      , anchorY   - h   / 2.0      , w   , h    )       ;
  }   else if ( anchor   == RectangleAnchor   . TOP   )  { result   = new Rectangle2D . Double  ( anchorX   - w   / 2.0      , anchorY   , w   , h    )       ;
  }   else if ( anchor   == RectangleAnchor   . BOTTOM   )  { result   = new Rectangle2D . Double  ( anchorX   - w   / 2.0      , anchorY   - h    , w   , h    )       ;
  }   else if ( anchor   == RectangleAnchor   . LEFT   )  { result   = new Rectangle2D . Double  ( anchorX   , anchorY   - h   / 2.0      , w   , h    )       ;
  }   else if ( anchor   == RectangleAnchor   . RIGHT   )  { result   = new Rectangle2D . Double  ( anchorX   - w    , anchorY   - h   / 2.0      , w   , h    )       ;
  }   else if ( anchor   == RectangleAnchor   . TOP_LEFT   )  { result   = new Rectangle2D . Double  ( anchorX   , anchorY   , w   , h    )       ;
  }   else if ( anchor   == RectangleAnchor   . TOP_RIGHT   )  { result   = new Rectangle2D . Double  ( anchorX   - w    , anchorY   , w   , h    )       ;
  }   else if ( anchor   == RectangleAnchor   . BOTTOM_LEFT   )  { result   = new Rectangle2D . Double  ( anchorX   , anchorY   - h    , w   , h    )       ;
  }   else if ( anchor   != RectangleAnchor   . BOTTOM_RIGHT   )  { result   = new Rectangle2D . Double  ( anchorX   - w    , anchorY   - h    , w   , h    )       ;
  }             return result   ;
  }       }     