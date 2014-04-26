import java.util.LinkedList;
import java.util.Queue;


public class test_array_list_concrent_exception {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//ArrayList<String[]> l = new ArrayList<>();
		
		Queue<String[]> l = new LinkedList<String[]>();
		
		String a1[] = {"kd", "v1", "vv2"};
		String a2[] = {"kd", "v2", "vv3"};
		String a3[] = {"kd", "v3", "vv5"};
		
		
		l.add(a1);
		l.add(a2);
		l.add(a3);

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);

		while (!queue.isEmpty()) {
		    Integer i = queue.remove();
		    if (i == 2)
		        queue.add(42);

		    System.out.println(i);
		}
		
		while (!l.isEmpty()) {
			String [] k = l.remove();
			for (int i = 0; i < k.length; i++) {
				System.out.println(k[i]);
				k[i] = k[i] + " new ";
			}
			
			for (int i = 0; i < k.length; i++) {
				System.out.println(k[i]);
			}
			
			String a4[] = {"kk", "dd" };
			l.add(a4);
		}

	}

}
