/* LittleDarwin generated mutant
 mutant type: relationalOperatorReplacement
 ----> before: i   <weekDayNames   . length  
----> after: i   >= weekDayNames   . length  
----> line number in original file: 225
----> mutated nodes: 4148
*/ 

package org . jfree . chart . date  ;
 import java . io . Serializable  ;
 import java . text . DateFormatSymbols  ;
 import java . text . SimpleDateFormat  ;
 import java . util . Calendar  ;
 import java . util . GregorianCalendar  ;
 public  abstract  class SerialDate implements Comparable   , Serializable   , MonthConstants    { private   static   final   long   serialVersionUID  = - 293716040467423637L        ;
   public   static   final   DateFormatSymbols   DATE_FORMAT_SYMBOLS  = new SimpleDateFormat  ( )     . getDateFormatSymbols  ( )     ;
   public   static   final   int   SERIAL_LOWER_BOUND  = 2       ;
   public   static   final   int   SERIAL_UPPER_BOUND  = 2958465       ;
   public   static   final   int   MINIMUM_YEAR_SUPPORTED  = 1900       ;
   public   static   final   int   MAXIMUM_YEAR_SUPPORTED  = 9999       ;
   public   static   final   int   MONDAY  = Calendar   . MONDAY     ;
   public   static   final   int   TUESDAY  = Calendar   . TUESDAY     ;
   public   static   final   int   WEDNESDAY  = Calendar   . WEDNESDAY     ;
   public   static   final   int   THURSDAY  = Calendar   . THURSDAY     ;
   public   static   final   int   FRIDAY  = Calendar   . FRIDAY     ;
   public   static   final   int   SATURDAY  = Calendar   . SATURDAY     ;
   public   static   final   int   SUNDAY  = Calendar   . SUNDAY     ;
   static   final   int  [ ]  LAST_DAY_OF_MONTH  = { 0     , 31     , 28     , 31     , 30     , 31     , 30     , 31     , 31     , 30     , 31     , 30     , 31     }     ;
   static   final   int  [ ]  AGGREGATE_DAYS_TO_END_OF_MONTH  = { 0     , 31     , 59     , 90     , 120     , 151     , 181     , 212     , 243     , 273     , 304     , 334     , 365     }     ;
   static   final   int  [ ]  AGGREGATE_DAYS_TO_END_OF_PRECEDING_MONTH  = { 0     , 0     , 31     , 59     , 90     , 120     , 151     , 181     , 212     , 243     , 273     , 304     , 334     , 365     }     ;
   static   final   int  [ ]  LEAP_YEAR_AGGREGATE_DAYS_TO_END_OF_MONTH  = { 0     , 31     , 60     , 91     , 121     , 152     , 182     , 213     , 244     , 274     , 305     , 335     , 366     }     ;
   static   final   int  [ ]  LEAP_YEAR_AGGREGATE_DAYS_TO_END_OF_PRECEDING_MONTH  = { 0     , 0     , 31     , 60     , 91     , 121     , 152     , 182     , 213     , 244     , 274     , 305     , 335     , 366     }     ;
   public   static   final   int   FIRST_WEEK_IN_MONTH  = 1       ;
   public   static   final   int   SECOND_WEEK_IN_MONTH  = 2       ;
   public   static   final   int   THIRD_WEEK_IN_MONTH  = 3       ;
   public   static   final   int   FOURTH_WEEK_IN_MONTH  = 4       ;
   public   static   final   int   LAST_WEEK_IN_MONTH  = 0       ;
   public   static   final   int   INCLUDE_NONE  = 0       ;
   public   static   final   int   INCLUDE_FIRST  = 1       ;
   public   static   final   int   INCLUDE_SECOND  = 2       ;
   public   static   final   int   INCLUDE_BOTH  = 3       ;
   public   static   final   int   PRECEDING  = - 1        ;
   public   static   final   int   NEAREST  = 0       ;
   public   static   final   int   FOLLOWING  = 1       ;
   private   String   description    ;
   protected   SerialDate ( )  { }      public   static   boolean   isValidWeekdayCode ( int   code    )  { switch ( code   )  { case SUNDAY    :  case MONDAY    :  case TUESDAY    :  case WEDNESDAY    :  case THURSDAY    :  case FRIDAY    :  case SATURDAY    :  return true    ;
   default :  return false    ;
   }   }      public   static   int   stringToWeekdayCode ( String   s    )  { final  String  [ ]  shortWeekdayNames  = DATE_FORMAT_SYMBOLS   . getShortWeekdays  ( )      ;
  final  String  [ ]  weekDayNames  = DATE_FORMAT_SYMBOLS   . getWeekdays  ( )      ;
  int   result  = - 1         ;
  s   = s   . trim  ( )    ;
  for ( int   i  = 0         ;
i   >= weekDayNames   . length   ;
i   ++     ) { if ( s   . equals  ( shortWeekdayNames   [ i   ]   )  )  { result   = i     ;
  break ;
  }     if ( s   . equals  ( weekDayNames   [ i   ]   )  )  { result   = i     ;
  break ;
  }     }     return result   ;
  }      public   static   String   weekdayCodeToString ( int   weekday    )  { final  String  [ ]  weekdays  = DATE_FORMAT_SYMBOLS   . getWeekdays  ( )      ;
  return weekdays   [ weekday   ]  ;
  }      public   static   String  [ ]  getMonths ( )  { return getMonths   ( false     )  ;
  }      public   static   String  [ ]  getMonths ( boolean   shortened    )  { if ( shortened   )  { return DATE_FORMAT_SYMBOLS   . getShortMonths  ( )  ;
  }   else { return DATE_FORMAT_SYMBOLS   . getMonths  ( )  ;
  }     }      public   static   boolean   isValidMonthCode ( int   code    )  { switch ( code   )  { case JANUARY    :  case FEBRUARY    :  case MARCH    :  case APRIL    :  case MAY    :  case JUNE    :  case JULY    :  case AUGUST    :  case SEPTEMBER    :  case OCTOBER    :  case NOVEMBER    :  case DECEMBER    :  return true    ;
   default :  return false    ;
   }   }      public   static   int   monthCodeToQuarter ( int   code    )  { switch ( code   )  { case JANUARY    :  case FEBRUARY    :  case MARCH    :  return 1    ;
   case APRIL    :  case MAY    :  case JUNE    :  return 2    ;
   case JULY    :  case AUGUST    :  case SEPTEMBER    :  return 3    ;
   case OCTOBER    :  case NOVEMBER    :  case DECEMBER    :  return 4    ;
   default :  throw new IllegalArgumentException  ( "SerialDate.monthCodeToQuarter: invalid month code."     )     ;
   }   }      public   static   String   monthCodeToString ( int   month    )  { return monthCodeToString   ( month   , false     )  ;
  }      public   static   String   monthCodeToString ( int   month   , boolean   shortened    )  { if ( ! isValidMonthCode   ( month    )   )  { throw new IllegalArgumentException  ( "SerialDate.monthCodeToString: month outside valid range."     )     ;
  }     final  String  [ ]  months     ;
  if ( shortened   )  { months   = DATE_FORMAT_SYMBOLS   . getShortMonths  ( )    ;
  }   else { months   = DATE_FORMAT_SYMBOLS   . getMonths  ( )    ;
  }     return months   [ month   - 1     ]  ;
  }      public   static   int   stringToMonthCode ( String   s    )  { final  String  [ ]  shortMonthNames  = DATE_FORMAT_SYMBOLS   . getShortMonths  ( )      ;
  final  String  [ ]  monthNames  = DATE_FORMAT_SYMBOLS   . getMonths  ( )      ;
  int   result  = - 1         ;
  s   = s   . trim  ( )    ;
  try { result   = Integer   . parseInt  ( s    )    ;
  }  catch ( NumberFormatException   e ) { }     if ( ( result   <1     )   || ( result   >12     )    )  { for ( int   i  = 0         ;
i   <monthNames   . length   ;
i   ++     ) { if ( s   . equals  ( shortMonthNames   [ i   ]   )  )  { result   = i   + 1       ;
  break ;
  }     if ( s   . equals  ( monthNames   [ i   ]   )  )  { result   = i   + 1       ;
  break ;
  }     }     }     return result   ;
  }      public   static   boolean   isValidWeekInMonthCode ( int   code    )  { switch ( code   )  { case FIRST_WEEK_IN_MONTH    :  case SECOND_WEEK_IN_MONTH    :  case THIRD_WEEK_IN_MONTH    :  case FOURTH_WEEK_IN_MONTH    :  case LAST_WEEK_IN_MONTH    :  return true    ;
   default :  return false    ;
   }   }      public   static   boolean   isLeapYear ( int   yyyy    )  { if ( ( yyyy   % 4     )   != 0     )  { return false    ;
  }   else if ( ( yyyy   % 400     )   == 0     )  { return true    ;
  }   else if ( ( yyyy   % 100     )   == 0     )  { return false    ;
  }   else { return true    ;
  }       }      public   static   int   leapYearCount ( int   yyyy    )  { int   leap4  = ( yyyy   - 1896     )   / 4         ;
  int   leap100  = ( yyyy   - 1800     )   / 100         ;
  int   leap400  = ( yyyy   - 1600     )   / 400         ;
  return leap4   - leap100    + leap400    ;
  }      public   static   int   lastDayOfMonth ( int   month   , int   yyyy    )  { final  int   result  = LAST_DAY_OF_MONTH   [ month   ]      ;
  if ( month   != FEBRUARY    )  { return result   ;
  }   else if ( isLeapYear   ( yyyy    )  )  { return result   + 1     ;
  }   else { return result   ;
  }      }      public   static   SerialDate   addDays ( int   days   , SerialDate   base    )  { int   serialDayNumber  = base   . toSerial  ( )  + days        ;
  return SerialDate   . createInstance  ( serialDayNumber    )  ;
  }      public   static   SerialDate   addMonths ( int   months   , SerialDate   base    )  { int   yy  = ( 12    * base   . getYYYY  ( )   + base   . getMonth  ( )   + months    - 1     )   / 12         ;
  if ( yy   <MINIMUM_YEAR_SUPPORTED    || yy   >MAXIMUM_YEAR_SUPPORTED     )  { throw new IllegalArgumentException  ( "Call to addMonths resulted in unsupported year"     )     ;
  }     int   mm  = ( 12    * base   . getYYYY  ( )   + base   . getMonth  ( )   + months    - 1     )   % 12     + 1         ;
  int   dd  = Math   . min  ( base   . getDayOfMonth  ( )  , SerialDate   . lastDayOfMonth  ( mm   , yy    )   )      ;
  return SerialDate   . createInstance  ( dd   , mm   , yy    )  ;
  }      public   static   SerialDate   addYears ( int   years   , SerialDate   base    )  { int   baseY  = base   . getYYYY  ( )      ;
  int   baseM  = base   . getMonth  ( )      ;
  int   baseD  = base   . getDayOfMonth  ( )      ;
  int   targetY  = baseY   + years        ;
  if ( targetY   <MINIMUM_YEAR_SUPPORTED    || targetY   >MAXIMUM_YEAR_SUPPORTED     )  { throw new IllegalArgumentException  ( "Call to addYears resulted in unsupported year"     )     ;
  }     int   targetD  = Math   . min  ( baseD   , SerialDate   . lastDayOfMonth  ( baseM   , targetY    )   )      ;
  return SerialDate   . createInstance  ( targetD   , baseM   , targetY    )  ;
  }      public   static   SerialDate   getPreviousDayOfWeek ( int   targetWeekday   , SerialDate   base    )  { if ( ! SerialDate   . isValidWeekdayCode  ( targetWeekday    )   )  { throw new IllegalArgumentException  ( "Invalid day-of-the-week code."     )     ;
  }     int   adjust     ;
  int   baseDOW  = base   . getDayOfWeek  ( )      ;
  if ( baseDOW   >targetWeekday    )  { adjust   = Math   . min  ( 0    , targetWeekday   - baseDOW     )    ;
  }   else { adjust   = - 7     + Math   . max  ( 0    , targetWeekday   - baseDOW     )     ;
  }     return SerialDate   . addDays  ( adjust   , base    )  ;
  }      public   static   SerialDate   getFollowingDayOfWeek ( int   targetWeekday   , SerialDate   base    )  { if ( ! SerialDate   . isValidWeekdayCode  ( targetWeekday    )   )  { throw new IllegalArgumentException  ( "Invalid day-of-the-week code."     )     ;
  }     int   adjust     ;
  int   baseDOW  = base   . getDayOfWeek  ( )      ;
  if ( baseDOW   >targetWeekday    )  { adjust   = 7    + Math   . min  ( 0    , targetWeekday   - baseDOW     )     ;
  }   else { adjust   = Math   . max  ( 0    , targetWeekday   - baseDOW     )    ;
  }     return SerialDate   . addDays  ( adjust   , base    )  ;
  }      public   static   SerialDate   getNearestDayOfWeek ( int   targetDOW   , SerialDate   base    )  { if ( ! SerialDate   . isValidWeekdayCode  ( targetDOW    )   )  { throw new IllegalArgumentException  ( "Invalid day-of-the-week code."     )     ;
  }     final  int   baseDOW  = base   . getDayOfWeek  ( )      ;
  int   adjust  = - Math   . abs  ( targetDOW   - baseDOW     )       ;
  if ( adjust   >= 4     )  { adjust   = 7    - adjust      ;
  }     if ( adjust   <= - 4      )  { adjust   = 7    + adjust      ;
  }     return SerialDate   . addDays  ( adjust   , base    )  ;
  }      public   SerialDate   getEndOfCurrentMonth ( SerialDate   base    )  { int   last  = SerialDate   . lastDayOfMonth  ( base   . getMonth  ( )  , base   . getYYYY  ( )   )      ;
  return SerialDate   . createInstance  ( last   , base   . getMonth  ( )  , base   . getYYYY  ( )   )  ;
  }      public   static   String   weekInMonthToString ( int   count    )  { switch ( count   )  { case SerialDate   . FIRST_WEEK_IN_MONTH   :  return "First"    ;
   case SerialDate   . SECOND_WEEK_IN_MONTH   :  return "Second"    ;
   case SerialDate   . THIRD_WEEK_IN_MONTH   :  return "Third"    ;
   case SerialDate   . FOURTH_WEEK_IN_MONTH   :  return "Fourth"    ;
   case SerialDate   . LAST_WEEK_IN_MONTH   :  return "Last"    ;
   default :  return "SerialDate.weekInMonthToString(): invalid code."    ;
   }   }      public   static   String   relativeToString ( int   relative    )  { switch ( relative   )  { case SerialDate   . PRECEDING   :  return "Preceding"    ;
   case SerialDate   . NEAREST   :  return "Nearest"    ;
   case SerialDate   . FOLLOWING   :  return "Following"    ;
   default :  return "ERROR : Relative To String"    ;
   }   }      public   static   SerialDate   createInstance ( int   day   , int   month   , int   yyyy    )  { return new SpreadsheetDate  ( day   , month   , yyyy    )     ;
  }      public   static   SerialDate   createInstance ( int   serial    )  { return new SpreadsheetDate  ( serial    )     ;
  }      public   static   SerialDate   createInstance ( java . util . Date   date    )  { GregorianCalendar   calendar  = new GregorianCalendar  ( )         ;
  calendar   . setTime  ( date    )   ;
  return new SpreadsheetDate  ( calendar   . get  ( Calendar   . DATE   )  , calendar   . get  ( Calendar   . MONTH   )  + 1     , calendar   . get  ( Calendar   . YEAR   )   )     ;
  }      public   abstract   int   toSerial ( )  ;
   public   abstract   java . util . Date   toDate ( )  ;
   public   String   getDescription ( )  { return this   . description  ;
  }      public   void setDescription ( String   description    )  { this   . description  = description     ;
  }      public   String   toString ( )  { return getDayOfMonth   ( )  + "-"     + SerialDate   . monthCodeToString  ( getMonth   ( )   )   + "-"     + getYYYY   ( )   ;
  }      public   abstract   int   getYYYY ( )  ;
   public   abstract   int   getMonth ( )  ;
   public   abstract   int   getDayOfMonth ( )  ;
   public   abstract   int   getDayOfWeek ( )  ;
   public   abstract   int   compare ( SerialDate   other    )  ;
   public   abstract   boolean   isOn ( SerialDate   other    )  ;
   public   abstract   boolean   isBefore ( SerialDate   other    )  ;
   public   abstract   boolean   isOnOrBefore ( SerialDate   other    )  ;
   public   abstract   boolean   isAfter ( SerialDate   other    )  ;
   public   abstract   boolean   isOnOrAfter ( SerialDate   other    )  ;
   public   abstract   boolean   isInRange ( SerialDate   d1   , SerialDate   d2    )  ;
   public   abstract   boolean   isInRange ( SerialDate   d1   , SerialDate   d2   , int   include    )  ;
   public   SerialDate   getPreviousDayOfWeek ( int   targetDOW    )  { return getPreviousDayOfWeek   ( targetDOW   , this    )  ;
  }      public   SerialDate   getFollowingDayOfWeek ( int   targetDOW    )  { return getFollowingDayOfWeek   ( targetDOW   , this    )  ;
  }      public   SerialDate   getNearestDayOfWeek ( int   targetDOW    )  { return getNearestDayOfWeek   ( targetDOW   , this    )  ;
  }      }      