import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		/*
		Construct a NodeTree from user input, and save the google result to a tree
		WebPage rootPage = new WebPage("https://www.google.com/", "Home");	
		WebTree tree = new WebTree(rootPage);
		*/
				
		System.out.print("type the keyword you want to search: ");
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		String keywordString = scanner.next();
		try 
		{
			GoogleQuery g = new GoogleQuery(keywordString);
			g.query();				
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	
		/*
		 * Construct a NodeTree from user input, and save the google result to a tree, but i can't QQ

		System.out.println("Please input (1)num of keywords (2)name and weight:");
		
		while(scanner.hasNextLine()){
			int numOfKeywords = scanner.nextInt();
			ArrayList<Keyword> keywords = new ArrayList<Keyword>();
			
			for(int i = 0; i < numOfKeywords; i++)
			{
				String name = scanner.next();
				double weight = scanner.nextDouble();
				Keyword k = new Keyword(name, weight);
				keywords.add(k);
			}
			
			tree.setPostOrderScore(keywords);
			tree.eularPrintTree();
		}
		*/
		
		
		scanner.close();
	}
	
	
}
