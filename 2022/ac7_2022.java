import java.io.*;

class fileobject{
    boolean filetype; //true file false dir
    long size;
    String name;
    int parent;
    

    public fileobject(String name,long size, int parent)
    {
        filetype = true;
        this.size = size;
        this.name = name;
        this.parent = parent;
    }

    public fileobject(String name,String size, int parent)
    {   
        try{
            this.size = (long)Integer.parseInt(size);
        
        }catch(Exception e){this.size = 0;}
        filetype = true;
        
        this.name = name;
        this.parent = parent;
    }

    public fileobject(String name, int parent)
    {
        filetype = false;
        size = 0;
        this.name = name;
        this.parent = parent;
    }

    public  boolean isDir()
    {
        return !filetype;
    }

    public  boolean isFile()
    {
        return filetype;
    }

    public long size()
    {
        return size;
    }

}

public class ac7_2022
{

    public static fileobject[] filelist = new fileobject[1];
    public static int filelistcurrentdir = 0;    
    public static int linecount = 0;
    public static long filesadded = 0;
public static void main(String[] args)
{   int filesizelimit = 100000;
    long counter = 0;
    String filename = args[args.length-1];
    filelist[0]=new fileobject("/",-1);

    try{
    BufferedReader br = new BufferedReader(new FileReader(filename));
    String line = br.readLine();


    while(line != null)
    {linecount++;
       // System.out.println(line);
        if(isCommand(line))
            processCommand(line);
        else
        addFileObject(line);
        line = br.readLine();
    }
    br.close();
    }catch(Exception e){System.out.println(e);}
  //  printArray(filelist);
    System.out.println("");
    for(int i = 0; i< filelist.length;i++)
    {
        if(filelist[i].isDir())
        {System.out.println(i+" "+filelist[i].name+":"+getDirSize(i));
            if(getDirSize(i)<=filesizelimit)
                {counter+= getDirSize(i);
                System.out.println("C"+counter);
                }
    }
       }
       System.out.println(counter);
       System.out.println(linecount);

       //printxcl();

       System.out.println("FILES ADDED:"+filesadded);
       long tlong = 0;
       for(int i = 0; i< filelist.length;i++)
        if(filelist[i].isFile())
            tlong++;
            System.out.println("FILES counted:"+tlong);




    //    String t[] = printStructure(0).split("\n");
    //    System.out.println("------------------------------------------------");
    //    for(int i = t.length-1;i>=0;i--)
    //     System.out.println(t[i]);

    //    //System.out.println("\n"+t);
    //    System.out.println("\n"+counter);
}



public static void printArray(fileobject[] farray)
{
    System.out.println("Length:"+filelist.length);
    for(int i = 0; i< filelist.length;i++)
    {   System.out.print(farray[i].name+" "+i+" "+farray[i].parent);
        if(filelist[i].isFile())
        System.out.println(" "+filelist[i].size);
        else   
        System.out.println(" "+"directory");
    }

}

public static void printxcl()
{
    for(int i = 0; i < filelist.length;i++)
    if(filelist[i].isDir())
        System.out.println(filelist[i].name+","+getDirSize(i));
}

public static void addFileObject(String line)
{
    String lineParts[] = line.split(" ");
    if(lineParts.length >= 2)
    {
        if(lineParts[0].contains("dir"))
        filelist=addElement(new fileobject(lineParts[1],filelistcurrentdir),filelist);
        else
           {filelist= addElement(new fileobject(lineParts[1],lineParts[0],filelistcurrentdir),filelist);
            System.out.println(lineParts[0]+"\t"+filelist[filelist.length-1].size);
            filesadded++;
            }
         //   System.out.println(filelist.length);
    }
    
}

public static boolean isCommand(String line)
{
    if(line.length()>0)
{  
            if(line.charAt(0)=='$')
            return true;
}
        return false;
}

public static boolean processCommand(String line)
{
    String[] commands = line.split(" ");
    if(commands[1].contains("cd"))
        {
            filelistcurrentdir=getDirID(commands[2]);
            return (false);
        }
    if(commands[1].contains("ls"))
        return(true);
    
        return(false);
}

public static int getDirID(String name)
{
    int t_int = filelistcurrentdir;
    if(name.contains("/") || name.contains("\\"))
        return(0);
    if(name.contains(".."))
        {t_int=filelist[filelistcurrentdir].parent; //get parent
        if(t_int < 0) t_int = 0;
        return t_int;
        }
        
    for(int i = 0; i< filelist.length;i++)
    if(filelist[i].name.compareTo(name)==0)
        return(i);

    return(t_int);

}

public static long getDirSize(int id)
{   long size = 0;
    int children[] = findChildren(id);
    if(children != null)
    {
        for(int i = 0; i < children.length;i++)
        {
            if(filelist[children[i]].isFile())
                size += filelist[children[i]].size;
            else    
                size += getDirSize(children[i]);
        }
    }
    return size;
}

public static String printStructure(int start)
{
        

    String results = "";
    int[] children = findChildren(start);
    if(children != null){
    for(int i = 0; i<children.length;i++)
    {
        if(!filelist[children[i]].isDir())
            results = results + printStructure(children[i]);
    }
    for(int i = 0; i<children.length;i++)
    {
        if(filelist[children[i]].isDir())
            results = results + printStructure(children[i]);
    }

    }
    for(int i = 0; i<findDistanceFromStart(start);i++)
        {System.out.print("\t");
            results = results + "\t";
        }
        if(filelist[start].isDir())
        {System.out.println(filelist[start].name);
            results = results + filelist[start].name + "(DIR, SIZE="+getDirSize(start) +"\n";
        }
        else{
    System.out.println(filelist[start].name + " (FILE, SIZE=" + filelist[start].size+")");
    results = results + filelist[start].name + " (FILE, SIZE=" + filelist[start].size+")"+"\n";
        }

        return results;

}

public static int findDistanceFromStart(int id)
{   
    int distance = 0;
    int parent = filelist[id].parent;
    while(parent >=0)
    {   distance ++;
        parent = filelist[parent].parent;
    }
    return distance;
}


public static int[] findChildren(int id)
{
    int[] children = null;
    for(int i = 0; i < filelist.length;i++)
    if(filelist[i].parent == id)
    {  // System.out.print("*");
        children = addElement2(i, children);
    }
    return children;
}
public static fileobject[] addElement(fileobject newObject, fileobject[] oldArray)
{
    int newArrayLength = 1;
    if(oldArray != null)
    newArrayLength = oldArray.length + 1;
    fileobject[] t_array = new fileobject[newArrayLength];
    for(int i = 0 ; i < oldArray.length;i++)
    t_array[i]=oldArray[i];
    t_array[t_array.length-1]=newObject;
 //   printArray(t_array);
    return t_array;
}


public static int[] addElement2(int newObject, int[] oldArray)
{   int newArrayLength = 1;
    if(oldArray != null)
    newArrayLength = oldArray.length + 1;
    int[] t_array = new int[newArrayLength];
    if(oldArray != null)
    for(int i = 0 ; i < oldArray.length;i++)
    t_array[i]=oldArray[i];
    t_array[t_array.length-1]=newObject;
 //   printArray(t_array);
    return t_array;
}


// public static object[] addElement1(Object newObject, Object[] oldArray)
// {   Class c1 = newObject.getClass();
//     Class c2 = oldArray.getClass();
//     int newArrayLength = 1;
//     if(oldArray != null)
//     newArrayLength = oldArray.length + 1;
  
//     c1[] t_array = new c1[newArrayLength];
//     for(int i = 0 ; i < oldArray.length;i++)
//     t_array[i]=oldArray[i];
//     t_array[t_array.length-1]=newObject;
//  //   printArray(t_array);
//     return t_array;
// }

}