import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ConstructTree {
	public static void main(String[] args) throws IOException {
		//heap
		KeywordHeap heap = new KeywordHeap(); 
		//root node
		WebPage rootPage = new WebPage("https://www.google.com", "運動比賽觀看");	
		WebTree tree = new WebTree(rootPage);
		
		//build childnode
		tree.root.addChild(new WebNode(new WebPage("https://hamivideo.hinet.net/%E9%81%8B%E5%8B%95%E9%A4%A8.do","HamiVideo")));
		tree.root.addChild(new WebNode(new WebPage("https://eltaott.tv/","elta")));
		tree.root.children.get(1).addChild(new WebNode(new WebPage("https://eltaott.tv/channel/play/101/1", "elta1")));
		tree.root.addChild(new WebNode(new WebPage("https://www.youtube.com/channel/UC3P83RUWwKbZ4bkhNti4ZuQ", "緯來體育台yt")));
		tree.root.addChild(new WebNode(new WebPage("https://www.sportslottery.com.tw/","台灣運彩")));

		
		System.out.println("Please input (1)num of keywords (2)name and weight:");
		Scanner scanner = new Scanner(System.in);
		
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
			tree.printHeap(heap, tree.constructHeap(tree));
			
			
		}
		scanner.close();
		}
}
