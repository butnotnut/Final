import java.util.Comparator;

public class KeywordComparator implements Comparator<Keyword>{
	@Override
	public int compare(Keyword o1, Keyword o2){
		if(o1 == null || o2 == null) throw new NullPointerException();
		//compare count
		//If o1 is less than o2 return negative integer, o1 greater than o2 return positive integer, equal return zero
		if(o1.weight<o2.weight) return -1;
		if(o1.weight>o2.weight) return 1;
		
		return 0;
	}
}