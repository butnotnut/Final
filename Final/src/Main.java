import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import javax.lang.model.type.TypeKind;

import org.jsoup.select.Evaluator.IsEmpty;

public class Main {
	public static void main(String[] args) throws IOException {
		//heap
		KeywordHeap heap = new KeywordHeap(); 
		//root node
		WebPage rootPage = new WebPage("https://www.google.com", "運動比賽觀看");	
		WebTree tree = new WebTree(rootPage);
		
		
		/*動態新增WebNode*/
		/*原本打算加入網站的子網站，但限縮在運動比賽觀看的話，通常也只有那幾個網站，所以在此不動態新增*/
		tree.root.addChild(new WebNode(new WebPage("https://hamivideo.hinet.net/%E9%81%8B%E5%8B%95%E9%A4%A8.do","HamiVideo")));
		tree.root.addChild(new WebNode(new WebPage("https://eltaott.tv/","elta")));
		tree.root.children.get(1).addChild(new WebNode(new WebPage("https://eltaott.tv/channel/play/101/1", "elta1")));
		tree.root.addChild(new WebNode(new WebPage("https://www.youtube.com/channel/UC3P83RUWwKbZ4bkhNti4ZuQ", "緯來體育台yt")));
		tree.root.addChild(new WebNode(new WebPage("https://www.sportslottery.com.tw/","台灣運彩")));

		
		System.out.println("Search: ");
		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNextLine()){
			/*加關鍵字*/
			ArrayList<Keyword> keywords = new ArrayList<Keyword>();
			
			/*輸入終止條件*/
			while(!scanner.hasNext("."))
			{
				String name = scanner.next();
				/*給使用者自調權重*/
				//double weight = scanner.nextDouble();
				
				Keyword k = new Keyword(name, 1);//權重預設為1
				keywords.add(k);
			}
			tree.setPostOrderScore(keywords);
			tree.printHeap(heap, tree.constructHeap(tree));
			break;
		}
		scanner.close();
	}
}
	
	

