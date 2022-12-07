import java.io.IOException;
import java.util.ArrayList;

public class WebTree {
public WebNode root;
	
	public WebTree(WebPage rootPage){
		this.root = new WebNode(rootPage);
	}
	
	public void setPostOrderScore(ArrayList<Keyword> keywords) throws IOException{
		setPostOrderScore(root, keywords);
	}
	
	private void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords) throws IOException{
		//compute the score of children nodes via post-order, then setNodeScore for startNode
		if(startNode.children.size()!=0){
			for(WebNode child: startNode.children){
				setPostOrderScore(child, keywords);
			}
		}
		startNode.setNodeScore(keywords);
			
	}
	
	public void eularPrintTree(){
		eularPrintTree(root);
	}
	
	private void eularPrintTree(WebNode startNode){
		int nodeDepth = startNode.getDepth();
		
		if(nodeDepth > 1) System.out.print("\n" + repeat("\t", nodeDepth-1));

		System.out.print("(");
		System.out.print(startNode.webPage.name + "," + startNode.nodeScore);
		
		//3. print child via pre-order
		for(WebNode child: startNode.children){
			eularPrintTree(child);
		}
		
		System.out.print(")");
				
		if(startNode.isTheLastChild()) System.out.print("\n" + repeat("\t", nodeDepth-2));	
	}
	
	public ArrayList<Keyword> constructHeap(WebTree rootpage)throws IOException{
		ArrayList<Keyword> arrayList = new ArrayList<>(); 
		constructHeap(rootpage.root,arrayList);
		return arrayList;
	}
	
	private void constructHeap(WebNode root,ArrayList<Keyword>arrayList)throws IOException{
		if(root.children.size()!=0){
			for(WebNode w: root.children){
				constructHeap(w, arrayList);
			}
		}
		Keyword rootKeyword = new Keyword(root.webPage.name, root.nodeScore);
		arrayList.add(rootKeyword);
	}
	
	public void printHeap(KeywordHeap heap,ArrayList<Keyword> arrayList)throws IOException{
		for(Keyword k:arrayList){
			heap.add(k);
		}
		heap.output();
	}
	
	private String repeat(String str, int repeat){
		String retVal = "";
		for(int i = 0; i < repeat; i++){
			retVal += str;
		}
		return retVal;
	}
}
