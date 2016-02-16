import java.time.LocalDate;


public enum Resolution {


	YEAR{
		@Override
		public String getLocalDate (LocalDate localDate){
			String date = "";
			date = localDate.getYear() + "";
			return date;
		}
	},

	QUARTER{
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




	public abstract String getLocalDate( LocalDate localDate);{

	}
}


