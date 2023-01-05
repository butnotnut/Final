
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestProject() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String a = "運動比賽觀看平台";
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if (request.getParameter("keyword") == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}

		ArrayList<Keyword> keywords = new ArrayList<Keyword>();
		URL u;
		InputStream c;

		// heap
		KeywordHeap heap = new KeywordHeap();
		// root node
		WebPage rootPage = new WebPage("https://www.google.com", "運動比賽觀看");
		WebTree tree = new WebTree(rootPage);

		// GoogleQuery
		String web_keyword = request.getParameter("keyword")+a;
		
		HashMap<String, String> g = new GoogleQuery(web_keyword).query();

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
		}

		/* 系統設定關鍵字 */
		String[] setKey = { "broadcast", "replay", "full match", "medal", "stadium", "field", "captain", "athletics",
				"commentate", "highlight", "live", "play", "Live", "game", "match", "champion", "觀看", "Video", "轉播",
				"直播", "賽事", "線上看", "運動", "線上看" };
		String[] setbadKey = { "國外", "購物", "新聞", "盤點", "免費" };
		for (String k : setKey) {
			Keyword keyword = new Keyword(k, 1);
			keywords.add(keyword);
		}
		Keyword keyword1 = new Keyword("運動比賽", 100);
		keywords.add(keyword1);
		Keyword keyword2 = new Keyword("體育", 100);
		keywords.add(keyword2);
		for (String k : setbadKey) {
			Keyword keyword = new Keyword(k, -10);
			keywords.add(keyword);
		}
		
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
//		request.setAttribute("k", s);
		int num = 0;
		while (num < ll.size() - 1) {
			s[num][0] = heap.poll().name;
			s[num][1] = g.get(s[num][0]);

			System.out.println(s[num][0] + " " + s[num][1]);
			num++;
		}
		request.setAttribute("k", s);
		request.getRequestDispatcher("googleitem.jsp").forward(request, response);
		tree.eularPrintTree();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
