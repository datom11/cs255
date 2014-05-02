import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class computeEDNew {

	static int[][] E_GLOBAL;
	static String s1, s2;
	static int len1, len2;

	public static void main(String args[]) {

		/*
		 * String str1="ALTRUISTIC"; String str2="ALGORITHM";
		 */
		String str1, str2;

		int ed;

		System.out.println("Enter string one:");
		Scanner scanIn = new Scanner(System.in);
		str1 = scanIn.nextLine();

		/*
		 * try{ System.in.read(); } catch(Exception e){
		 * System.out.println("invalid string one"); }
		 */
		System.out.println("Enter string two:");
		str2 = scanIn.nextLine();

		if (str2.length() > str1.length()) {
			String temp = str1;
			str1 = str2;
			str2 = temp;
		}

		System.out.println("str1:" + str1);
		System.out.println("str2:" + str2);

		s1 = str1;
		s2 = str2;

		len1 = str1.length();
		len2 = str2.length();

		System.out.println("**Computing Edit Distance**");
		ed = editDistance(str1, str2);
		System.out.println();
		System.out.println();
		System.out.println("Edit Distance between two strings:" + ed);
		System.out.println();
		System.out.println();
		
		//System.out.println("New value :" + E_GLOBAL[len1][len2]);

		System.out.println("----------------------------------");
		arrangeStringsByEditDistance();

	}

	public static int editDistance(String str1, String str2) {
		// TODO Auto-generated method stub
		int len1 = str1.length();
		int len2 = str2.length();
		int minimum;

		// System.out.println(len1);
		// System.out.println(len2);

		// init E[][]
		int[][] E = new int[len1 + 1][len2 + 1];

		// E[0][0]=0;
		for (int i = 1; i <= len1; i++) {
			E[i][0] = i; // this will give no. of insertions
		}
		/*
		 * for(int i=0;i<=len1;i++){ System.out.println(E[i][0]); }
		 */
		for (int j = 1; j <= len2; j++) {
			E[0][j] = j; // this will give no. deletions
		}

		for (int i1 = 0; i1 < len1; i1++) {
			int i = i1 + 1;
			char c1 = str1.charAt(i1);
			// System.out.println("i"+c1);

			for (int j1 = 0; j1 < len2; j1++) {
				int j = j1 + 1;
				char c2 = str2.charAt(j1);
				// System.out.println("j"+c2);

				if (c1 == c2) {
					// System.out.println("same char"+E[i][j]);
					int insert = (E[i][j - 1]) + 1;
					int delete = (E[i - 1][j]) + 1;
					int subs = (E[i - 1][j - 1]);
					// int insert = E[i][j+1]+1;
					// int delete = E[i+1][j]+1;

					// now find the min of these

					minimum = min(insert, delete, subs);
					E[i][j] = minimum; // this is when two characters are equal

				} else {
					int insert = (E[i][j - 1]) + 1;
					int delete = (E[i - 1][j]) + 1;
					int subs = (E[i - 1][j - 1]) + 1;
					// int insert = E[i][j+1]+1;
					// int delete = E[i+1][j]+1;

					// now find the min of these

					minimum = min(insert, delete, subs);

					E[i][j] = minimum;
				}
			}
		}

		System.out.println("Edit distance\n");
		System.out.print("      ");
		for (int i1=0; i1<str2.length(); i1++)
			System.out.print(Character.toString(str2.charAt(i1)) + "  ");
		
		System.out.println();
		
		for (int i = 0; i <= len1; i++) {
			if ( i == 0)
				System.out.print("   ");
			else
				System.out.print(Character.toString(str1.charAt(i-1)) + "  ");
				
			for (int j = 0; j <= len2; j++) {
				System.out.print(E[i][j] + "  ");
			}
			System.out.println();
		}
		// System.out.println(E[len1][len2]);

		E_GLOBAL = E;
		return E[len1][len2];

	}

	private static int min(int insert, int delete, int subs) {
		// System.out.println("called");
		// TODO Auto-generated method stub
		int smallest = insert;
		if (smallest > delete)
			smallest = delete;
		if (smallest > subs)
			smallest = subs;
		return smallest;
	}

	private static void arrangeStringsByEditDistance() {
		// TODO Auto-generated method stub

		Queue<ArrayList<String>> sequenceList = new LinkedList<ArrayList<String>>();
		ArrayList<ArrayList<String>> sequenceList_new = new ArrayList<ArrayList<String>>();
		
		//String seq1[] = new String[100];

		ArrayList< String> seq1 = new ArrayList<String>();
		
		sequenceList.add(seq1);

		//int k = len1 - 1;

		int i = len1;
		int j = len2;

		//for (String[] seq : sequenceList) {
		int cnt = 1;
		while (!sequenceList.isEmpty()) {

			ArrayList<String> seq = sequenceList.remove();
			
			//k = len1 - 1;
			//k = 0;
			i = len1;
			j = len2;
			
			//for (int k1 = k; k1 >= 0; k1--) {
			
			for (String val : seq) {
				if (val == "S") {
					i--;
					j--;
				}
				else if (val == "U") {
					i--;
				}
				else if (val == "L") {
					j--;
				}
			}
			
			//System.out.println("FOR SEQ = " + cnt);
			
			while (i >=1 && j >= 1) {

				int slant, up, left; 
				
				int min;
				
				if (s1.charAt(i-1) == s2.charAt(j-1)) {
					// System.out.println("same char"+E[i][j]);
					left = (E_GLOBAL[i][j - 1]) + 1;
					up = (E_GLOBAL[i - 1][j]) + 1;
					slant = (E_GLOBAL[i - 1][j - 1]);
					
					min = min(left, up, slant);

				} else {
					left = (E_GLOBAL[i][j - 1]) + 1;
					up = (E_GLOBAL[i - 1][j]) + 1;
					slant = (E_GLOBAL[i - 1][j - 1]) + 1;
					min = min(left, up, slant);
				}
				

				
				//System.out.println("for i, j = " + i + j);
				//System.out.println("out of " + slant + " " + up + " " + left
				//		+ " min is " + min);

				if (E_GLOBAL[i][j] == slant) {
					
					if (E_GLOBAL[i][j] == up) {
						//String seq2[] = new String[len1];
						ArrayList< String> seq2 = new ArrayList<String>(seq);
						//System.arraycopy(seq, 0, seq2, 0, seq.length);
						
						
						seq2.add("U");

						sequenceList.add(seq2);

					}
					if (E_GLOBAL[i][j] == left) {
						//tring seq3[] = new String[len1];
						ArrayList< String> seq3 = new ArrayList<String>(seq);
						//System.arraycopy(seq, 0, seq3, 0, seq.length);
						seq3.add("L");

						sequenceList.add(seq3);

					}
					
					seq.add("S");
					i--;
					j--;
					/*System.out.println("for i, j = " + i + j);
					System.out.println("out of " + slant + " " + up + " " + left
							+ " min is " + min);*/

					
				} else if (E_GLOBAL[i][j] == up) {
					
					
					if (E_GLOBAL[i][j] == left) {
						ArrayList< String> seq3 = new ArrayList<String>(seq);
						//System.arraycopy(seq, 0, seq3, 0, seq.length);
						seq3.add("L");
						sequenceList.add(seq3);

					}
					seq.add("U");
					//k++;
					i--;
				}
				else if (E_GLOBAL[i][j] == left) {
					seq.add("L");
					//k++;
					j--;
				}

			}
			cnt++;
		
			Collections.reverse(seq);
			sequenceList_new.add(seq);
		}

		System.out.println("\n\nPossible Arrangments of Strings: " + s1 + " AND " + s2);
		System.out.println("\n[NOTE: Notation For Operations\n      S=substitute | U=Delete | L=Insert");
		System.out.println();
		System.out.println();
		int b = 1;
		for (ArrayList<String> t : sequenceList_new) {
			System.out.println("Sequence # " + b );
			for (String s : t) {
				System.out.print(s + " ");
			}
			b++;
			System.out.println();
			printStrings(t);
			System.out.println("------------------------------------------------");
		}
	}

	private static void printStrings(ArrayList<String> t) {
		// TODO Auto-generated method stub
	
		int i1 = 0, j1 = 0;
		ArrayList<String> s1_new = new ArrayList<String>();
		ArrayList<String> s2_new = new ArrayList<String>();
		String []A = s1.split("");
		String []B = s2.split("");
		
		for (String v : t) {
			//System.out.print(v + " ---  ");
			if (v == "S") {
				i1++;
				j1++;
				//System.out.println( " i and j " + i1 + " " + j1);
				//System.out.println(A[i1] + " -> " + B[j1]);
				s1_new.add(A[i1]);
				s2_new.add(B[j1]);
			}
			else if (v == "U") {
				i1++;
				s1_new.add(A[i1]);
				s2_new.add("-");
			}
			else if (v == "L") {
				j1++;
				s1_new.add("-");
				s2_new.add(B[j1]);
			}
		}
		
		System.out.println();
		
		
		
		for (String k : s1_new) {
			System.out.print(k + " ");
		}

		
		System.out.println();
		int k1 = 0;
		
		for (String k : s1_new) {
			if (s1_new.get(k1).equals(s2_new.get(k1)))
				System.out.print("  ");
			else
			System.out.print("| ");
			
			k1++;
		}
		
		k1 = 0;
		System.out.println();
		for (String k : s2_new) {
			System.out.print(k + " ");
			
			k1++;
		}
		System.out.println();
		System.out.println();
	}
}
