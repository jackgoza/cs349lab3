/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.io.*;

/**
 *
 * @author jack
 */

public class lab3 {

    public static void f() throws Throwable{
        Random ran = new Random();
        int x = ran.nextInt(3); // will return 0, 1 or 2 with equal probability
        switch(x){
            case (1): throw new RuntimeException(); // Unchecked Exception
            
            case (2): throw new Throwable();        // Checked Exception! YAY
                                                    // SAID NOONE EVER
            
            case (3): return;                       // No error.

        }
        
    }
    
    public static void main(String[] args) {
        
        // Part A. Create a class called Tree that encapsulates the name 
        // of a type of tree. Create a class called Forest that implements 
        // the interface Iterable. When you iterate over an instance of Forest 
        // you should get back instances of the class Tree. 
        
	Forest f = new Forest();
        f.forestOfTrees.add(new Tree("Oak")); // This is purposely lazy
        f.forestOfTrees.add(new Tree("Ash"));
        f.forestOfTrees.add(new Tree("Cherry"));
        

	for (Tree t : f) {
		System.out.println("Tree name: " + t.treeName);
	}

        // Part B. Write a routine f() that throws a checked exception back to
        // the calling routine, on average, 1/3 of the time, an unchecked 
        // exception 1/3 of the time and no exceptions 1/3 of the time.
        // Call the routine 100 times and print a count of how many times a 
        // checked exception was thrown, how many times an unchecked exception 
        // was thrown and how many times no exception at all was thrown. 
        
        int checked = 0; 
        int unchecked = 0; 
        int noError = 0;
        
        for(int i = 0; i < 100; i++){
        
            try{
                f();
                noError++; // inc no error count
            }
            catch(RuntimeException e){
                unchecked++; // inc unchecked error count
            }
            catch(Throwable t){
                checked++; // inc checked error count
            }

            
        }
        
        // print out all that jazz and blues
        
        System.out.println("F() was run 100 times. ");
        System.out.println(checked + " checked exceptions were thrown.");
        System.out.println(unchecked + " unchecked exceptions were thrown.");
        System.out.println(noError + " nothing was thrown.");
        
        // Part C. 
        // Read the serialized object into your program, cast it to the type 
        // PersistentStorage and then call and print the results of methods 
        // getInstanceSecret() and getComputedSecret().
        
        try{
        ObjectInputStream objectIn = null;
        objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("Lab3Object2.ser.txt")));

        PersistentStorage store1 = null;
        store1 = (PersistentStorage) objectIn.readObject();
        System.out.println(store1.getInstanceSecret());
        System.out.println(store1.getComputedSecret());
        
        }
        catch (Exception e){
            e.printStackTrace();
        }

        
    }
}


class Tree {
    public Tree(String name){
        this.treeName = name;
    }
    public String treeName;
}

class Forest implements Iterable<Tree>{
    
    public ArrayList<Tree> forestOfTrees = new ArrayList();
    
    public Iterator<Tree> iterator() {
        Iterator<Tree> iTree = forestOfTrees.iterator();
        return iTree;
    }
    
}

class PersistentStorage implements Serializable {
    private static final long serialVersionUID = 123L;
    private String s1;
    private String s2;

    public PersistentStorage(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public String getInstanceSecret() {
        return s2 + s1;
    }
    
    public String getComputedSecret() {
    	return System.getProperty("user.dir");
    }
}
