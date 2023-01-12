import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Main {
	public static void main(String[] args) throws IOException, ServletException {
		
		
		ArrayList<Keyword> keywords = new ArrayList<Keyword>();
		URL u;
		InputStream c;

		// heap
		KeywordHeap heap = new KeywordHeap();
		// root node
		WebPage rootPage = new WebPage("https://www.google.com", "運動比賽觀看");
		WebTree tree = new WebTree(rootPage);

		/* 詢問使用者要輸入什麼關鍵字 */
		System.out.print("Search: ");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();

		// GoogleQuery
		HashMap<String, String> g = new GoogleQuery(name).query();

		Collection<String> h = g.keySet();
		for (String title : h) {
			try {
				/* 檢查網案是否產生404NotFound，是的話則不加入樹的節點 */
				u = new URL(g.get(title));
				c = u.openConnection().getInputStream();
				/* 動態新增WebNode */
				tree.root.addChild(new WebNode(new WebPage(g.get(title), title)));

			} catch (Exception e) {
				// TODO: Do Nothing
			}

			/* 決定樹的大小 */
			/*
			 * if(tree.treeSize()>5) break;
			 */
		}

		/* 系統設定關鍵字 */
		String[] setKey = { "broadcast", "replay", "full match", "medal", "stadium", "field", "captain", "athletics",
				"commentate", "highlight", "live", "play", "Live", "game", "match", "champion", "觀看", "Video", "轉播",
				"直播", "賽事", "體育", "線上看", "運動", "線上看" };
		String[] setbadKey = { "國外", "購物", "新聞", "盤點", "免費" };
		for (String k : setKey) {
			Keyword keyword = new Keyword(k, 1);
			keywords.add(keyword);
		}
		for (String k : setbadKey) {
			Keyword keyword = new Keyword(k, -10);
			keywords.add(keyword);
		}

//  /*儲存使用者關鍵字*/
//  String[] namelist = name.split(" ");
//  for(int i=0;i<namelist.length;i++)
//  {
//   /*權重依據關鍵字多寡*/
//   Keyword k = new Keyword(name, 100/namelist.length);
//   keywords.add(k);
//  }

		/* 設定網頁分數 */
		tree.setPostOrderScore(keywords);
		// 排序好的樹，儲存的是WebNode，有parent, child, Webpage, nodescore

		/* 用排序好的樹建heap，在此回傳型態是array list，裡面的Keyword帶有分數 */
		ArrayList<Keyword> ll = new ArrayList<>();
		ll = tree.constructHeap(tree);

		/* 刪除rootpage */
		ll.remove(0);

		/* 把帶有分數的Keyword的節點丟進heap，就可以取最大值 */
		for (Keyword k : ll) {
			heap.add(k);
		}

		/* 前端需要的部分 */
		String[][] s = new String[ll.size()][2];
		int num = 0;
		while (num < ll.size() - 1) {
			s[num][0] = heap.poll().name;
			s[num][1] = g.get(s[num][0]);

			System.out.println(s[num][0] + " " + s[num][1]);
			num++;
		}

		/* 查看分數 */
//  tree.eularPrintTree();
//  
//  tree.printHeap(heap, tree.constructHeap(tree));

		scanner.close();
	}
}