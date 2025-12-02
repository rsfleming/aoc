import java.io.*;

import javax.lang.model.util.ElementScanner6;

 class branch
{

    public int[]leaves = new int[0];
    public int value = -1;

    branch()
    {
        value = -1;
    }
    branch(int _value)
    {
        leaves = null;
        value = _value;
    }

    public void addLeaf(int leaf)
    {
        int newLength = 1;
        if(leaves!=null)
            newLength=leaves.length+1;
        int[] tArray = new int[newLength];
        for(int i = 0; i< newLength-1;i++)
            tArray[i]=leaves[i];
        tArray[newLength-1]=leaf;
        leaves = tArray;
    }

}



public class ac13_2022
{   public static branch[] tree;
    public static int part1counter = 0;
  
    public static void main(String[] args)
    {  int treePointer = 0;
        addBranch(new branch(treePointer++));
        try{
            BufferedReader br = new BufferedReader(new FileReader(args[args.length-1]));
            String line = "";
            String entry = "";
            while(line!=null)
            {
                line =br.readLine();
                
                if(line == null || line.length() ==0)
                   { processPair(entry,treePointer++); entry="";}
                else    
                    entry+=line+"\n";
            }
        }
        
        catch(Exception e){System.out.println(e);}

        printTree();
        analyze2();
        System.out.println(part1counter);
}


public static boolean isLeaf(int i)
{   if(tree[i].value>=0)
        return true;
    if(tree[i].leaves==null)
        return false;
    if(tree[i].leaves.length==0)
        return false;
    return false;
}

public static boolean nodeIsEmpty(int i)
{   if(tree[i].leaves==null)
        return true;
    if(tree[i].leaves.length==0)
        return true;
    
    return false;
}


public static boolean isLessThan(int node1, int node2)
{   System.out.println("Compare "+ printNode(node1) + " to  " +printNode(node2));
    boolean isLT = true;
    if(isLeaf(node1) && isLeaf(node2))
    {//System.out.print("L"+tree[node1].value+" "+tree[node2].value);
        return tree[node1].value<=tree[node2].value;
    }
    if(!isLeaf(node1) && !isLeaf(node2))
    {   if(nodeIsEmpty(node1)&&nodeIsEmpty(node2))
        {   System.out.print("emptynode to empty node");
            return true;
        }
        if(!nodeIsEmpty(node1)&&!nodeIsEmpty(node2))
      {  if(tree[node1].leaves.length > tree[node2].leaves.length)
         {  System.out.print("node 1 leaves > node 2 leaves");
            return false;
         }
        for(int i = 0; i< tree[node1].leaves.length; i++)
        isLT = isLT && isLessThan(tree[node1].leaves[i], tree[node2].leaves[i]);
       return isLT;
      }
    
        if(nodeIsEmpty(node1)&&!nodeIsEmpty(node2))
            return true;
        if(!nodeIsEmpty(node1)&&nodeIsEmpty(node2))
            return false;
    }
    if(isLeaf(node1) && !isLeaf(node2))
        {System.out.print("T");
        if(nodeIsEmpty(node2))
        return false;
            addBranch(new branch(tree[node1].value));
            tree[node1].addLeaf(tree.length-1);
            return(isLessThan(tree[node1].leaves[0], tree[node2].leaves[0]))   ; 
            }
    if(!isLeaf(node1) && isLeaf(node2))
        {System.out.print("O");
           if(nodeIsEmpty(node1))
            return true;
            addBranch(new branch(tree[node2].value));
            tree[node2].addLeaf(tree.length-1);
            return(isLessThan(tree[node1].leaves[0], tree[node2].leaves[0]))   ; 
            }
    return isLT;
}

public static void analyzeTree()
{   String parentnodes = "";
    String child1nodes = "";
    String child2nodes = "";

    for(int i = 1; i < tree.length; i++)
    if(getParent(i)==0)
    { parentnodes="";
    System.out.println("NODE "+i);
        for(int j = 1; j < tree.length; j++)
        if(getParent(j)==i)
        parentnodes+=j+"\n";
    

    for(int ii = 0; ii < parentnodes.split("\n").length;ii+=2)
    {   child1nodes = "";
     
        child2nodes = "";
        for(int j = 1; j < tree.length; j++)
        {if(getParent(j)==getStringVal(parentnodes.split("\n")[ii]))
        child1nodes+=j+"\n";
        if(getParent(j)==getStringVal(parentnodes.split("\n")[ii+1]))
        child2nodes+=j+"\n";
            
    }
    
    int leftLength = child1nodes.split("\n").length; 
    int rightLength = child2nodes.split("\n").length; 
    
    {   boolean rightOrder = true;
        if(leftLength == rightLength)
       { System.out.println(leftLength + " "+rightLength);
           for(int k = 0; k< leftLength;k++)
    
        {  
            int leftVal = -1;
            int rightVal =-1;

            if(child1nodes.split("\n")[k].length()>0)
                {leftVal = add1stChildren(getStringVal(child1nodes.split("\n")[k]));
             
                }
                if(child2nodes.split("\n")[k].length()>0)
               { rightVal = add1stChildren(getStringVal(child2nodes.split("\n")[k]));
              
                
            } 
             
                    if(leftVal > rightVal)
                        rightOrder = false;
                        System.out.println(leftVal +" "+rightVal);
                    }   

        if(rightOrder)
            System.out.println("Left side is smaller, right order");
        else    
        System.out.println("Right side is smaller, wrong order");
    }
    else{
    if( rightLength<leftLength)
    System.out.println("right side ran out of items");
    if( rightLength>leftLength)
    System.out.println("left side ran out of items");
    }
}
    
    }}}

public static int addChildren(int position)
{   int retval = 0;
    if(tree[position].value >= 0)
        return tree[position].value;
    if(tree[position].leaves != null)
    {
        for(int i = 0; i < tree[position].leaves.length;i++)
            retval=addChildren(tree[position].leaves[i]);
            if(retval<0)
                retval--;
    }
    return retval;
}

public static int add1stChildren(int position)
{   int retval = 0;
    if(tree[position].value >= 0)
        return tree[position].value;
    if(tree[position].leaves != null)
    {
        //for(int i = 0; i < tree[position].leaves.length;i++)
            retval+=addChildren(tree[position].leaves[0]);
    }
    return retval;
}


public static void printTree()
{ System.out.println("printing tree");
 for(int i = 0; i < tree[0].leaves.length;i++)
 {
    System.out.println(i);
    for(int j = 0; j< tree[tree[0].leaves[i]].leaves.length;j++)
        System.out.println(printNode(tree[tree[0].leaves[i]].leaves[j]));
 }   
}

public static void analyze2()
{ System.out.println("analyzing tree");
 for(int i = 0; i < tree[0].leaves.length;i++)
 {
    System.out.println("LINE "+(i+1));
    //for(int j = 0; j< tree[tree[0].leaves[i]].leaves.length;j++)
        if(isLessThan(tree[tree[0].leaves[i]].leaves[0],tree[tree[0].leaves[i]].leaves[1]))
        {System.out.println("<");
        part1counter+=i+1;
        }
        else 
        System.out.println(">");
       
 }   
}


public static String printNode(int nodeNumber)
{   if(isLeaf(nodeNumber))
        return(tree[nodeNumber].value+"");
    String t = "[";
    if(tree[nodeNumber].leaves!=null)
    for(int i = 0; i< tree[nodeNumber].leaves.length;i++)
    {
        if(isLeaf(tree[nodeNumber].leaves[i]))
          {if(i>0) t+=',';
          t+=tree[tree[nodeNumber].leaves[i]].value;
          }
        else
          t+=printNode(tree[nodeNumber].leaves[i]);
    }
        t=t+"]";
    return t;
}

public static int getParent(int currentPosition)
{
    for(int i = 0; i< tree.length; i++)
        {
            if(tree[i].leaves!=null)
                for(int j = 0; j<tree[i].leaves.length;j++)
                    if(tree[i].leaves[j]==currentPosition)
                        return i;
        }
        return -1;
}

public static int getDistance(int start, int currentPosition)
{   int retval = -1;
    int parentID = currentPosition;
    int distance = 0;
    while(parentID != start && parentID != -1)
    {   
        distance++;
        parentID = getParent(parentID);   
    }
    if(parentID >=0)
        retval = distance;
    return retval;
}

public static void compareNode(int node)
{

}

public static int countInstances(String s, char c)
{
    int counter = 0;
    for(int i =0; i< s.length();i++)
    {
        if(s.charAt(i)==c)
        counter++;
    }
    return counter;
}

public static int getStringVal(String s)
{
    int retval = -1;
try{retval = Integer.parseInt(s);}
catch(Exception e){}
    return retval;
}

public static void processPair(String s,int id)
{   
    addBranch(new branch());
    int currentnode =tree.length-1; 
    tree[0].addLeaf(currentnode);

    String[] lines = s.split("\n");
    for(int line = 0; line < lines.length;line++)
        processLine(lines[line],currentnode);
}

public static String processLine(String line, int parentNode)
{
   // System.out.println(line + " " + parentNode);
int index =0;
String usedChars = "";
String tempString = "";
while(index < line.length())
{
    if(line.charAt(index) != '[' && line.charAt(index) != ']' && line.charAt(index) != ',')  //digit
        tempString += line.charAt(index++);
    else
        if(tempString.length()==0)
        {
            if(line.charAt(index)=='[')
            {
                usedChars+='[';
                index++;
                addBranch(new branch());
                tree[parentNode].addLeaf(tree.length-1);
                usedChars+=(processLine(line.substring(index,line.length()),tree.length-1));
             //   System.out.println("used "+usedChars+ " "+line.indexOf(usedChars));
                index = line.indexOf(usedChars)+usedChars.length();
                
            
            }
            else
            {if(line.charAt(index)==',')
            {
                usedChars+=',';
                index++;
            }
            if(line.charAt(index)==']')
            {
                usedChars+=']';
                index++;
                return usedChars;
            }}
        }
        if(tempString.length()>0) // process digit
            {
             usedChars+=tempString;
             addBranch(new branch(getStringVal(tempString)));
             tree[parentNode].addLeaf(tree.length-1);
             tempString="";
            }

        }
return(usedChars);

}

public static void addBranch(branch b)
{
int newLength = 1;
if(tree!=null)
    newLength=tree.length+1;
branch[] newTree = new branch[newLength];
for(int i = 0; i< newLength-1;i++)
    newTree[i]=tree[i];
newTree[newLength-1]=b;
tree=newTree;
}

}

