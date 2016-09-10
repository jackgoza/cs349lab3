/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;
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
            
            case (2): throw new Throwable();        // Checked Exception!
            
            case (3): throw new Error();            // Error! YAY ERRORS
                                                    // SAID NOONE EVER
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
        
	for (Iterator it = f.iterator(); it.hasNext();){
            
		System.out.println("Tree name: " + it);
        }
    }
}


class Tree {
    public Tree(String name){
        treeName = name;
    }
    public String treeName;
}

class Forest implements Iterable{
    
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
