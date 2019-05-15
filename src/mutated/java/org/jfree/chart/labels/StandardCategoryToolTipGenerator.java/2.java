/* LittleDarwin generated mutant
 mutant type: relationalOperatorReplacement
 ----> before: obj   == this   
----> after: obj   != this   
----> line number in original file: 123
----> mutated nodes: 78
*/ 

package org . jfree . chart . labels  ;
 import java . io . Serializable  ;
 import java . text . DateFormat  ;
 import java . text . NumberFormat  ;
 import org . jfree . data . category . CategoryDataset  ;
 public  class StandardCategoryToolTipGenerator extends AbstractCategoryItemLabelGenerator   implements CategoryToolTipGenerator   , Serializable    { private   static   final   long   serialVersionUID  = - 6768806592218710764L        ;
   public   static   final   String   DEFAULT_TOOL_TIP_FORMAT_STRING  = "({0}, {1}) = {2}"       ;
   public   StandardCategoryToolTipGenerator ( )  { super   ( DEFAULT_TOOL_TIP_FORMAT_STRING   , NumberFormat   . getInstance  ( )   )   ;
  }      public   StandardCategoryToolTipGenerator ( String   labelFormat   , NumberFormat   formatter    )  { super   ( labelFormat   , formatter    )   ;
  }      public   StandardCategoryToolTipGenerator ( String   labelFormat   , DateFormat   formatter    )  { super   ( labelFormat   , formatter    )   ;
  }      @ Override      public   String   generateToolTip ( CategoryDataset   dataset   , int   row   , int   column    )  { return generateLabelString   ( dataset   , row   , column    )  ;
  }      @ Override      public   boolean   equals ( Object   obj    )  { if ( obj   != this    )  { return true    ;
  }     if ( ! ( obj   instanceof StandardCategoryToolTipGenerator    )    )  { return false    ;
  }     return super   . equals  ( obj    )  ;
  }      }      