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
	private Map<String, MatchedDataPair> finalResult;
	

	public DataCollectionBuilder(DataSource xData, DataSource yData, Resolution resolution){
		this.resolution = resolution;
		this.xData = xData;
		this.yData = yData;
	}

	
	public String getTitle(){	
		return "Title: " + xData.getName() + ", " + yData.getName();
	}

	
	public DataCollection getResult(){
		Map<String, List <MatchedDataPair>> MatchedXY = searchMatchedPair();
		
		for(Entry<String, List<MatchedDataPair>> pairs : MatchedXY.entrySet()){
			List <MatchedDataPair> list = pairs.getValue();
			double sumX = 0.0;
			double sumY = 0.0;
			int counter = 0;
			
			for(MatchedDataPair pair : list){
				sumX = pair.getXvalue();
				sumY = pair.getYvalue();
				counter++;
			}
			
			double averageX = (sumX/counter);
			double averageY = (sumX/counter);
			
			finalResult.put(pairs.getKey(), new MatchedDataPair(averageX, averageY));
		}
		
		return new DataCollection(getTitle(), xData.getName()+": " + xData.getUnit(), yData.getName()+": "+yData.getUnit(), finalResult);
		//finalResult.put(resolution.toString(), value)
	}


	
	public static void getLocalDatePoly(Resolution res, LocalDate localDate){
		System.out.println("" + res.getLocalDate(localDate));
	}
	
//	public String getLocalDate (LocalDate localDate){
//		String date = "";
//
////		switch (resolution){
////		case YEAR:
////			date =  ""+localDate.getYear() ;
////			break;
////
////		case QUARTER:
////			date = ((localDate.getMonthValue() /3)+1)+ "" ;
////
////
////		case MONTH:
////			date = localDate.getYear() +"-"  +localDate.getMonthValue();
////			break;
////
////		case WEEK:
////			date = (localDate.getDayOfWeek()) + "";
////			break;
////
////
////		case DAY:
////			date = localDate.getYear()+"-"+localDate.getMonthValue()+"-"+localDate.getDayOfMonth();
////			break;
////		}
////		return date;
//	}

	private Map<String, List <MatchedDataPair>> searchMatchedPair(){

		Map<String, List <MatchedDataPair>> MatchedXY = new HashMap<String, List <MatchedDataPair>>() ;	

		for(Entry<LocalDate, Double> x: xData.getData().entrySet()){
			for(Entry<LocalDate, Double> y: yData.getData().entrySet()){
				if(resolution.getLocalDate(x.getKey()).equals(resolution.getLocalDate(y.getKey()))){	
					if(MatchedXY.get(resolution.getLocalDate(x.getKey())) != null){
						List<MatchedDataPair> matchedPair = MatchedXY.get(resolution.getLocalDate(x.getKey()));
						matchedPair.add(new MatchedDataPair(x.getValue(), y.getValue()));
					} else {
						List<MatchedDataPair> matchedPair = new ArrayList<MatchedDataPair>();
						matchedPair.add(new MatchedDataPair(x.getValue(), y.getValue()));
						MatchedXY.put(resolution.getLocalDate(x.getKey()), matchedPair);
					}
					
				}
			}
		}
		return MatchedXY;
	}
	
}
