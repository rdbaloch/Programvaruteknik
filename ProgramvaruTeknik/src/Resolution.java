import java.time.LocalDate;

/**
 * This is an enumeration that defines different resolutions
 * 
 * @author Jonas Oster
 * @author Rashed Davodi
 * @author Jonatan Hogberg
 * @version 17/02 - 2016
 */

public enum Resolution {

	YEAR(){
		@Override
		public String getLocalDate (LocalDate localDate){
			String date = "";
			date = localDate.getYear() + "";
			return date;
		}
	},

	QUARTER(){
		@Override
		public String getLocalDate (LocalDate localDate){
			String date = "";
			date = ((localDate.getMonthValue() /3)+1)+ "" ;
			return date;
		}
	},

	MONTH{
		@Override
		public String getLocalDate (LocalDate localDate){
			String date = "";
			date += localDate.getYear() +"-"  +localDate.getMonthValue();
			return date;
		}
	},

	WEEK{
		@Override
		public String getLocalDate (LocalDate localDate){
			String date = "";
			date = (localDate.getDayOfWeek()) + "";
			return date;
		}
	},

	DAY{
		@Override
		public String getLocalDate (LocalDate localDate){
			String date = "";
			date = localDate.getYear()+"-"+localDate.getMonthValue()+"-"+localDate.getDayOfMonth();
			return date;
		}
	};

	public abstract String getLocalDate( LocalDate localDate);
}


