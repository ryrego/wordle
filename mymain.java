import java.io.*;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Scanner;

public  class mymain {
    
public static ArrayList removeXWords(char c, ArrayList l, int count){
	ArrayList newL = new ArrayList();

       	for (int i=0; i<l.size();i++){
		String s = (String)l.get(i);

		//if (!(s.indexOf(c)>-1))   newL.add(s);         // bug - removing  too many if duplicate

		int dictCount = s.length() - s.replaceAll( Character.toString(c) ,"").length();
		if (dictCount < count) {
			newL.add(s);   // keep word if character count is less
		}
	}

	return newL;
}

public static ArrayList removeWords(char c, ArrayList l, int pos){
	ArrayList newL = new ArrayList();

       	for (int i=0; i<l.size();i++){
		String s = (String)l.get(i);
		if (s.indexOf(c)>-1 && s.indexOf(c)!=pos)   newL.add(s);
	}
	
	return newL;
}

    public static ArrayList findWords(int pos, char c, ArrayList l){
       ArrayList newL = new ArrayList();
        for (int i=0; i<l.size();i++){
            String s = (String)l.get(i);
            if (c==s.charAt(pos)){
                newL.add(l.get(i));
            }
        }
        return newL;
    }
    
    
    public static void main(String args[]) {
	ArrayList al = new ArrayList();
	String startWord = args[0];
	String clues = args[1];
	String line = "";
	Scanner myObj = new Scanner(System.in);


	if (startWord.length() != clues.length()) System.exit(1);

	try{
		BufferedReader reader = new BufferedReader (new FileReader("test.txt"));
		String s = reader.readLine();
		
		while(s != null) {
			
			s = s.toLowerCase();
			
			if (s.length()==startWord.length()) {
				al.add(s); 
				//System.out.println("adding: " + s);
			}

			s = reader.readLine();
		}
		

		}catch(Exception e){
			e.printStackTrace();
		}
      
while (!line.equals("*")) {
      
      	// go thru list of X chars and remove words
	for (int j=0; j<clues.length(); j++) 
	{
		if (clues.charAt(j)=='X') {
			// count how many are not X'ed - only remove words with count or greater
			int count = 1;
			char theChar = startWord.charAt(j);
			for (int k=0; k<startWord.length(); k++)
			{
				if (startWord.charAt(k)==theChar && clues.charAt(k) != 'X') {
					count++;
				}
			}
			
			// account for duplicates - remove words only with "count" or greater
//System.out.println(j);
			//int count = startWord.length() - startWord.replaceAll(startWord.substring(j, j+1),"").length();
System.out.println(count);
			al = removeXWords(startWord.charAt(j), al, count);
		}
	}

	for (int j=0; j<clues.length(); j++) 
	{
		if (clues.charAt(j)=='G') {
			al = findWords(j, startWord.charAt(j), al);
		}
	}

	// go thru list of Y chars and remove words that dont have the char
	for (int j=0; j<clues.length(); j++) 
	{
		if (clues.charAt(j)=='Y') {
			al = removeWords(startWord.charAt(j), al, j);
		}
	}

      // print words that remain
      for (int i=0; i<al.size(); i++) {
          System.out.println(al.get(i));
      }


	System.out.print("go again   <word> <GYX>  (* to stop): ");
	line = myObj.nextLine();
	if (!line.equals("*")) {  startWord = line.split(" ")[0] ;  clues = line.split(" ")[1];} 
}


    }

}
