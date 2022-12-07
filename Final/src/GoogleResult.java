import java.io.IOException;

public class GoogleResult {
	public static void main(String[] args) 
	{
		try 
		{
			GoogleQuery g = new GoogleQuery("NCCU");
			System.out.println(g.query());

		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
