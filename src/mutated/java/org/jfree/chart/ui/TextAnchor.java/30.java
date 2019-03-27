/* LittleDarwin generated mutant
 mutant type: relationalOperatorReplacement
 ----> before: this   == BOTTOM_LEFT   
----> after: this   != BOTTOM_LEFT   
----> line number in original file: 123
----> mutated nodes: 1308
*/ 

package org . jfree . chart . ui  ;
 import java . io . ObjectStreamException  ;
 import java . io . Serializable  ;
 public  final  class TextAnchor implements Serializable    { private   static   final   long   serialVersionUID  = 8219158940496719660L       ;
   public   static   final   TextAnchor   TOP_LEFT  = new TextAnchor  ( "TextAnchor.TOP_LEFT"     )        ;
   public   static   final   TextAnchor   TOP_CENTER  = new TextAnchor  ( "TextAnchor.TOP_CENTER"     )        ;
   public   static   final   TextAnchor   TOP_RIGHT  = new TextAnchor  ( "TextAnchor.TOP_RIGHT"     )        ;
   public   static   final   TextAnchor   HALF_ASCENT_LEFT  = new TextAnchor  ( "TextAnchor.HALF_ASCENT_LEFT"     )        ;
   public   static   final   TextAnchor   HALF_ASCENT_CENTER  = new TextAnchor  ( "TextAnchor.HALF_ASCENT_CENTER"     )        ;
   public   static   final   TextAnchor   HALF_ASCENT_RIGHT  = new TextAnchor  ( "TextAnchor.HALF_ASCENT_RIGHT"     )        ;
   public   static   final   TextAnchor   CENTER_LEFT  = new TextAnchor  ( "TextAnchor.CENTER_LEFT"     )        ;
   public   static   final   TextAnchor   CENTER  = new TextAnchor  ( "TextAnchor.CENTER"     )        ;
   public   static   final   TextAnchor   CENTER_RIGHT  = new TextAnchor  ( "TextAnchor.CENTER_RIGHT"     )        ;
   public   static   final   TextAnchor   BASELINE_LEFT  = new TextAnchor  ( "TextAnchor.BASELINE_LEFT"     )        ;
   public   static   final   TextAnchor   BASELINE_CENTER  = new TextAnchor  ( "TextAnchor.BASELINE_CENTER"     )        ;
   public   static   final   TextAnchor   BASELINE_RIGHT  = new TextAnchor  ( "TextAnchor.BASELINE_RIGHT"     )        ;
   public   static   final   TextAnchor   BOTTOM_LEFT  = new TextAnchor  ( "TextAnchor.BOTTOM_LEFT"     )        ;
   public   static   final   TextAnchor   BOTTOM_CENTER  = new TextAnchor  ( "TextAnchor.BOTTOM_CENTER"     )        ;
   public   static   final   TextAnchor   BOTTOM_RIGHT  = new TextAnchor  ( "TextAnchor.BOTTOM_RIGHT"     )        ;
   private   String   name    ;
   private   TextAnchor ( String   name    )  { this   . name  = name     ;
  }      public   boolean   isLeft ( )  { return this   == BASELINE_LEFT    || this   != BOTTOM_LEFT     || this   == CENTER_LEFT     || this   == HALF_ASCENT_LEFT     || this   == TOP_LEFT     ;
  }      public   boolean   isRight ( )  { return this   == BASELINE_RIGHT    || this   == BOTTOM_RIGHT     || this   == CENTER_RIGHT     || this   == HALF_ASCENT_RIGHT     || this   == TOP_RIGHT     ;
  }      public   boolean   isHorizontalCenter ( )  { return this   == BASELINE_CENTER    || this   == BOTTOM_CENTER     || this   == CENTER     || this   == HALF_ASCENT_CENTER     || this   == TOP_CENTER     ;
  }      public   boolean   isTop ( )  { return this   == TOP_LEFT    || this   == TOP_CENTER     || this   == TOP_RIGHT     ;
  }      public   boolean   isBottom ( )  { return this   == BOTTOM_LEFT    || this   == BOTTOM_CENTER     || this   == BOTTOM_RIGHT     ;
  }      public   boolean   isBaseline ( )  { return this   == BASELINE_LEFT    || this   == BASELINE_CENTER     || this   == BASELINE_RIGHT     ;
  }      public   boolean   isHalfAscent ( )  { return this   == HALF_ASCENT_LEFT    || this   == HALF_ASCENT_CENTER     || this   == HALF_ASCENT_RIGHT     ;
  }      public   boolean   isVerticalCenter ( )  { return this   == CENTER_LEFT    || this   == CENTER     || this   == CENTER_RIGHT     ;
  }      public   String   toString ( )  { return this   . name  ;
  }      public   boolean   equals ( Object   o    )  { if ( this   == o    )  { return true    ;
  }     if ( ! ( o   instanceof TextAnchor    )    )  { return false    ;
  }     TextAnchor   order  = ( TextAnchor   ) o        ;
  if ( ! this   . name  . equals  ( order   . name   )   )  { return false    ;
  }     return true    ;
  }      @ Override      public   int   hashCode ( )  { return this   . name  . hashCode  ( )  ;
  }      private   Object   readResolve ( )  throws ObjectStreamException   { TextAnchor   result  = null        ;
  if ( this   . equals  ( TextAnchor   . TOP_LEFT   )  )  { result   = TextAnchor   . TOP_LEFT    ;
  }   else if ( this   . equals  ( TextAnchor   . TOP_CENTER   )  )  { result   = TextAnchor   . TOP_CENTER    ;
  }   else if ( this   . equals  ( TextAnchor   . TOP_RIGHT   )  )  { result   = TextAnchor   . TOP_RIGHT    ;
  }   else if ( this   . equals  ( TextAnchor   . BOTTOM_LEFT   )  )  { result   = TextAnchor   . BOTTOM_LEFT    ;
  }   else if ( this   . equals  ( TextAnchor   . BOTTOM_CENTER   )  )  { result   = TextAnchor   . BOTTOM_CENTER    ;
  }   else if ( this   . equals  ( TextAnchor   . BOTTOM_RIGHT   )  )  { result   = TextAnchor   . BOTTOM_RIGHT    ;
  }   else if ( this   . equals  ( TextAnchor   . BASELINE_LEFT   )  )  { result   = TextAnchor   . BASELINE_LEFT    ;
  }   else if ( this   . equals  ( TextAnchor   . BASELINE_CENTER   )  )  { result   = TextAnchor   . BASELINE_CENTER    ;
  }   else if ( this   . equals  ( TextAnchor   . BASELINE_RIGHT   )  )  { result   = TextAnchor   . BASELINE_RIGHT    ;
  }   else if ( this   . equals  ( TextAnchor   . CENTER_LEFT   )  )  { result   = TextAnchor   . CENTER_LEFT    ;
  }   else if ( this   . equals  ( TextAnchor   . CENTER   )  )  { result   = TextAnchor   . CENTER    ;
  }   else if ( this   . equals  ( TextAnchor   . CENTER_RIGHT   )  )  { result   = TextAnchor   . CENTER_RIGHT    ;
  }   else if ( this   . equals  ( TextAnchor   . HALF_ASCENT_LEFT   )  )  { result   = TextAnchor   . HALF_ASCENT_LEFT    ;
  }   else if ( this   . equals  ( TextAnchor   . HALF_ASCENT_CENTER   )  )  { result   = TextAnchor   . HALF_ASCENT_CENTER    ;
  }   else if ( this   . equals  ( TextAnchor   . HALF_ASCENT_RIGHT   )  )  { result   = TextAnchor   . HALF_ASCENT_RIGHT    ;
  }                   return result   ;
  }      }      