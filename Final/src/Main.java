import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.jar.Attributes.Name;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeKind;
import javax.naming.spi.DirStateFactory.Result;

import org.jsoup.select.Evaluator.IsEmpty;
import org.json.JSONObject;
import org.json.*;


public class Main {
	public static void main(String[] args) throws IOException {
		ArrayList<Keyword> keywords = new ArrayList<Keyword>();


		//heap
		KeywordHeap heap = new KeywordHeap(); 
		//root node
		WebPage rootPage = new WebPage("https://www.google.com", "運動比賽觀看");	
		WebTree tree = new WebTree(rootPage);
		
	
		
		//GoogleQuery
		HashMap<String,String> g = new GoogleQuery("運動比賽觀看平台").query();
		Collection<String> h = new GoogleQuery("運動比賽觀看平台").query().keySet();
		for(String title : h)
		{
			try {
				/*檢查網案是否產生404NotFound，是的話則不加入樹的節點*/
				URL u = new URL(g.get(title));
				InputStream c = u.openConnection().getInputStream();
				/*動態新增WebNode*/
				tree.root.addChild(new WebNode(new WebPage(g.get(title),title)));
			} catch (Exception e) {
				// TODO: Do Nothing
			}

			/*決定樹的大小*/
			/*
			 * if(tree.treeSize()>5)
			 * break;
			*/
		}
		
		/*系統設定關鍵字*/
		String[] setKey = {"broadcast", "final", "semifinal", "quarter final", "replay", "full match", "medal", "stadium", "field", "captain", "athletics", 
				"commentate", "highlight", "live", "play", "singles", "doubles", "gold", "silver", "bronze", "won", "game", "match", "champion", "觀看", 
				"轉播", "直播", "賽事"};
		for(String k:setKey)
		{
			Keyword keyword = new Keyword(k, 1);
			keywords.add(keyword);
		}
		
		/*詢問使用者要輸入什麼關鍵字*/
		System.out.print("Search: ");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		/*儲存使用者關鍵字*/
		String[] namelist = name.split(" ");
		for(int i=0;i<namelist.length;i++)
		{
			/*權重依據關鍵字多寡*/
			Keyword k = new Keyword(name, 100/namelist.length);
			keywords.add(k);
		}
		
		tree.setPostOrderScore(keywords);
		/*查看分數
		tree.eularPrintTree();
		*/ 
		tree.printHeap(heap, tree.constructHeap(tree));
		
		scanner.close();
	}
}
	
	

