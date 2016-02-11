
public class MatchedDataPair {
	
	private Double xValue ;
	private Double yValue;
	
	
	public MatchedDataPair (double xValue, double yValue){
		
		this.xValue= xValue;
		this.yValue = yValue;
	}
	
	public Double getXvalue(){
		
		return xValue;
	}
	public Double getYvalue(){
		return yValue;
	}
	
	public String toString(){
		return "xValue"+ xValue +"yValue"+ yValue;
	}
	
}
