/* LittleDarwin generated mutant
 mutant type: relationalOperatorReplacement
 ----> before: event   . getWindow  ( )  == this   
----> after: event   . getWindow  ( )  != this   
----> line number in original file: 60
----> mutated nodes: 223
*/ 

package org . jfree . chart . ui  ;
 import java . awt . event . WindowEvent  ;
 import java . awt . event . WindowListener  ;
 import javax . swing . JFrame  ;
 public  class ApplicationFrame extends JFrame   implements WindowListener    { public   ApplicationFrame ( String   title    )  { super   ( title    )   ;
  addWindowListener   ( this    )   ;
  }      @ Override      public   void windowClosing ( WindowEvent   event    )  { if ( event   . getWindow  ( )  != this    )  { dispose   ( )   ;
  System   . exit  ( 0     )   ;
  }     }      @ Override      public   void windowClosed ( WindowEvent   event    )  { }      @ Override      public   void windowActivated ( WindowEvent   event    )  { }      @ Override      public   void windowDeactivated ( WindowEvent   event    )  { }      @ Override      public   void windowDeiconified ( WindowEvent   event    )  { }      @ Override      public   void windowIconified ( WindowEvent   event    )  { }      @ Override      public   void windowOpened ( WindowEvent   event    )  { }      }      