package bplus;
import java.util.*;
import java.io.*; 
import java.util.Scanner; 
import java.nio.file.Paths;

public class BPlusTreeDemo {
    public static void main(String[] args) {
        BPlusTree bPlusTree = new BPlusTree(5);
        
        
        List<Set<String>> transactions = new ArrayList<>();
        
        BufferedReader br = null;
        String csvFileName = "dictionary.csv";
        String absolutePath = Paths.get(csvFileName).toAbsolutePath().toString();
        
  		try {
  			br = new BufferedReader(new FileReader(absolutePath));
  		} catch (FileNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
        String line = ""; 
        String splitBy = ","; 
        int wordCount = 0;
  	   try {
  			while ((line = br.readLine()) != null) {
  				String[] items = line.split(splitBy);
  				if(items.length>1) {
	  	  			bPlusTree.insert(items[0], items[1]);
	  				System.out.println(items[0]+":"+ items[1]);
  				}
  				transactions.add(new HashSet<>(Arrays.asList(items)));
  				wordCount++;
  			   }
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	   System.out.println("Total word inserted: "+wordCount);
       
//print the whole dictionary
//  	 for (Set<String> itemset : transactions) {
//         System.out.println(itemset);
//     }

        // Insert some key-value pairs into the B+ tree
        bPlusTree.insert("apple", "a type of fruit");
        bPlusTree.insert("banana", "a long curved fruit");
        bPlusTree.insert("cherry", "a small, round fruit");
        bPlusTree.insert("date", "a sweet fruit that grows on palm trees");
        bPlusTree.insert("elderberry", "a small, tart fruit");
        bPlusTree.insert("fig", "a sweet fruit that grows on trees");

        // Traverse the B+ tree and print its contents
        Scanner in = new Scanner(System.in);  
        for(;;) {
        	System.out.println("Enter the word you want to search: ");
        	String wordToBeSearched = in.nextLine();
        	bPlusTree.traverse(wordToBeSearched);
        	System.out.println("\n");
        	if(wordToBeSearched.equals("0"))break;
            
        }
        
        //bPlusTree.search("apple");
    }
}
