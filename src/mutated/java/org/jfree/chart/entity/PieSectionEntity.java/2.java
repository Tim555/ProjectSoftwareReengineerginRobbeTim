/* LittleDarwin generated mutant
 mutant type: relationalOperatorReplacement
 ----> before: this   . pieIndex  != that   . pieIndex  
----> after: this   . pieIndex  == that   . pieIndex  
----> line number in original file: 219
----> mutated nodes: 312
*/ 

package org . jfree . chart . entity  ;
 import java . awt . Shape  ;
 import java . io . Serializable  ;
 import org . jfree . chart . HashUtils  ;
 import org . jfree . chart . util . ObjectUtils  ;
 import org . jfree . data . general . PieDataset  ;
 public  class PieSectionEntity extends ChartEntity   implements Serializable    { private   static   final   long   serialVersionUID  = 9199892576531984162L       ;
   private   PieDataset   dataset    ;
   private   int   pieIndex    ;
   private   int   sectionIndex    ;
   private   Comparable   sectionKey    ;
   public   PieSectionEntity ( Shape   area   , PieDataset   dataset   , int   pieIndex   , int   sectionIndex   , Comparable   sectionKey   , String   toolTipText   , String   urlText    )  { super   ( area   , toolTipText   , urlText    )   ;
  this   . dataset  = dataset     ;
  this   . pieIndex  = pieIndex     ;
  this   . sectionIndex  = sectionIndex     ;
  this   . sectionKey  = sectionKey     ;
  }      public   PieDataset   getDataset ( )  { return this   . dataset  ;
  }      public   void setDataset ( PieDataset   dataset    )  { this   . dataset  = dataset     ;
  }      public   int   getPieIndex ( )  { return this   . pieIndex  ;
  }      public   void setPieIndex ( int   index    )  { this   . pieIndex  = index     ;
  }      public   int   getSectionIndex ( )  { return this   . sectionIndex  ;
  }      public   void setSectionIndex ( int   index    )  { this   . sectionIndex  = index     ;
  }      public   Comparable   getSectionKey ( )  { return this   . sectionKey  ;
  }      public   void setSectionKey ( Comparable   key    )  { this   . sectionKey  = key     ;
  }      @ Override      public   boolean   equals ( Object   obj    )  { if ( obj   == this    )  { return true    ;
  }     if ( ! ( obj   instanceof PieSectionEntity    )    )  { return false    ;
  }     PieSectionEntity   that  = ( PieSectionEntity   ) obj        ;
  if ( ! ObjectUtils   . equal  ( this   . dataset  , that   . dataset   )   )  { return false    ;
  }     if ( this   . pieIndex  == that   . pieIndex   )  { return false    ;
  }     if ( this   . sectionIndex  != that   . sectionIndex   )  { return false    ;
  }     if ( ! ObjectUtils   . equal  ( this   . sectionKey  , that   . sectionKey   )   )  { return false    ;
  }     return super   . equals  ( obj    )  ;
  }      @ Override      public   int   hashCode ( )  { int   result  = super   . hashCode  ( )      ;
  result   = HashUtils   . hashCode  ( result   , this   . pieIndex   )    ;
  result   = HashUtils   . hashCode  ( result   , this   . sectionIndex   )    ;
  return result   ;
  }      @ Override      public   String   toString ( )  { return "PieSection: "    + this   . pieIndex   + ", "     + this   . sectionIndex   + "("     + this   . sectionKey  . toString  ( )   + ")"     ;
  }      }      