package factory;
import java.io.IOException;
import org.junit.*;
import org.junit.Test;
public class UnitTesting {
	
	@Test
	public void testAt() throws IOException {
		
		String name = "LonS";
		int row = 2;
		int result = 36;
		Dataframe df = new Dataframe(new CSVFactory(), "cities.csv");
		Assert.assertTrue((Integer.parseInt((String) df.at(name, row))==result));
	}
	
	public void testIat() throw IOException{
		
		int col = 2;
		int row = 8;
		String result = Yakima;
		
	}
	
	
	
}
