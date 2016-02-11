import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DataCollectionBuilder {

	private DataSource xData;
	private DataSource yData;
	private Resolution resolution;
	private Map<String, List <MatchedDataPair>> resultData;
	private Map<String, MatchedDataPair> finalResult;

	public DataCollectionBuilder(DataSource xData, DataSource yData, Resolution resolution){
		this.resolution = resolution;
		this.xData = xData;
		this.yData = yData;
	}

	
	public String getTitle(){	
		return null; // toDo
	}

	
	public DataCollection getResult(){
		return null; // toDo
	}

	
	public String getLocalDate (LocalDate localDate){
		String date = null;

		switch (resolution){
		case YEAR:
			date = localDate.getYear() + "";
			break;

		case QUARTER:
			date = ((localDate.getMonthValue() /3)+1)+ "" ;


		case MONTH:
			date = localDate.getYear() +"-"  +localDate.getMonthValue();
			break;

		case WEEK:
			break;


		case DAY:
			date = localDate.getYear()+"-"+localDate.getMonthValue()+"-"+localDate.getDayOfMonth();
			break;
		}
		return date;
	}

	private List searchMatchedPair(){

		ArrayList <MatchedDataPair> MatchedXY = new  ArrayList <MatchedDataPair>() ;	

		for(Entry<LocalDate, Double> x: xData.getData().entrySet()){
			
			for(Entry<LocalDate, Double> y: yData.getData().entrySet()){
				
				if(getLocalDate(x.getKey()).equals(getLocalDate(y.getKey()))){
					MatchedXY.add(new MatchedDataPair(x.getValue(),y.getValue()));
				}
			}
		}
		return MatchedXY;
	}


	private DataCollection buildDataCollection(){
		return null;
	}
}
