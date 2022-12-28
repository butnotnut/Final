import java.util.ArrayList;
import java.util.PriorityQueue;
public class KeywordHeap {
		public PriorityQueue<Keyword> heap;
		
		public KeywordHeap(){
			this.heap = new PriorityQueue<Keyword>(10, new KeywordComparator());
		}
		
		public void add(Keyword k){
			heap.offer(k);
		}
		
		public void peek(){
			if(heap.peek() == null){
				System.out.println("InvalidOperation");
				return;
			}
			
			Keyword k = heap.peek();		
			System.out.println(k.toString());
		}
		
		public void removeMin(){
			//remove minimal element and print it
			if(heap.peek()==null){
				System.out.println("InvalidOperation");
				return;
			}
			peek();
			heap.poll();
		}
		
		public void output(){
			//print the output in order and remove all element
			ArrayList<Keyword> arrayList = new ArrayList<Keyword>();
			Keyword k;

			while(heap.peek()!=null){
				k = heap.poll();
				/*移除root page(不是搜尋出來的結果)*/
				if(!(k.name=="運動比賽觀看")) 
					arrayList.add(0, k);
			}

			for(Keyword i:arrayList){
				System.out.println(i.name);
			}
		}
	}


