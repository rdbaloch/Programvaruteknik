import java.time.LocalDate;
import java.util.Map;

public class ImplDataSource implements DataSource{
	
	private String name;
	private String unit;
	private Map<LocalDate, Double> loadData;
	
	public ImplDataSource(String getName,String unit, Map<LocalDate, Double> loadData, String name ){
		this.name= name;
		this.unit= unit;
		this.loadData = loadData;
		
	}

	@Override
	public String getName() {
		
		return name;
	}
	
	public Map<LocalDate, Double>  addData (LocalDate localDate, Double value){
		
		loadData.put(localDate, value);
		
		
		return loadData;
		
	}

	@Override
	public String getUnit() {
		
		return unit;
	}

	@Override
	public Map<LocalDate, Double> getData() {
		
		return loadData;
	}

}
