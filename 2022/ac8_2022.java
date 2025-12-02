import java.io.*;
public class ac8_2022{

   public static String[] fmap;
   public static String points = "";
public static void main(String args[])
{
    String filename = args[args.length-1];
    try{
        BufferedReader br = new BufferedReader(new FileReader(filename));
    
        String line = br.readLine();
        while(line != null)
        {if(line.length()>0)
        fmap = addRow(line,fmap);
            line=br.readLine();
        }
        for(int row=0;row<fmap.length;row++)
        {
        for(int col=0;col<fmap[row].length();col++)
        System.out.print(fmap[row].charAt(col));
        System.out.println();
        }

        for(int row=0;row<fmap.length;row++)
        {
       // points = points + (findVis(row,'L')+","+row+"\n");
        points = points + (findVis(row,'R')+","+row+"\n");
        }
        // for(int col=0;col<fmap[0].length();col++)
        // {
        // points = points + col+","+(findVis(col,'U')+"\n");
        // points = points + col+","+(findVis(col,'D')+"\n");
        // }
        //addBorders();
        System.out.println(points);
        points = removeDuplicates();
        System.out.println("\n"+points);
        
    }

    catch(Exception e){}
}

public static int getVal(char c)
{   System.out.println(""+c);
    int t_int = -1;
    try{
        t_int = Integer.parseInt(""+c);
    }
    catch(Exception e){System.out.println(e);}
    return t_int;
}

public static int findVis(int row,char direction)
{System.out.println("\n"+row+" "+direction);
    int max = 0;
    if(direction=='L')
    { 
        for(int i = 1; i< fmap[row].length(); i++)
        if(getVal(fmap[row].charAt(i))>getVal(fmap[row].charAt(max)))
            max = i;
    }
    if(direction=='R')
    {max = fmap[row].length()-1;
        for(int i = fmap[row].length()-2;i>=0; i--)
        if(getVal(fmap[row].charAt(i))>getVal(fmap[row].charAt(max)))
            max = i;
    }
    
    if(direction=='U')
    {max = 0;
        for(int i = 1;i<fmap.length;i++)
        if(getVal(fmap[i].charAt(row))>getVal(fmap[max].charAt(row)))
            max = i;
    }
    if(direction=='D')
    {max = fmap.length-1;
        for(int i =fmap.length-2;i>=0;i--)
        if(getVal(fmap[i].charAt(row))>getVal(fmap[max].charAt(row)))
            max = i;
    }
    
            return max;
}

public static void addBorders()
{
    for(int i = 0; i< fmap.length;i++)
        {
            points = points + 0+","+i+"\n";
            points = points + (fmap[0].length()-1)+","+i+"\n";
            
        }
        for(int i = 0; i< fmap[0].length();i++)
    {
        points = points + i +",0\n";
        points = points + i +","+(fmap.length-1)+"\n";
        
    }

}

public static boolean arraySearch(String item, String[] arraylist)
{   if(arraylist == null)
        return false;
    for(int i = 0; i < arraylist.length; i++)
    {   
        if(arraylist[i].compareTo(item)==0)
        return true;
    }
    return false;
}

public static String removeDuplicates()
{
    String[] pointslist= points.split("\n");
    String[] tString = null;
    

    for(int i = 0; i < pointslist.length;i++)
        {
            if(!arraySearch(pointslist[i],tString))
                if(pointslist[i].split(",")[0].compareTo("0")!=0)
                if(pointslist[i].split(",")[0].compareTo(""+(fmap[0].length() - 1))!=0)
             if(pointslist[i].split(",")[1].compareTo(""+(fmap.length - 1))!=0)
               if(pointslist[i].split(",")[1].compareTo("0")!=0)
                //if(pointslist[i].split(",")[1].compareTo("0")!=0)
                    tString=addRow(pointslist[i],tString);
        }
    String tString1 = "";
    for(int i = 0; i< tString.length;i++)
    tString1 = tString1 + tString[i]+"\n";
    return tString1;
}
public static String[] addRow(String newObject, String[] oldArray)
{   int newArrayLength = 1;
    if(oldArray != null)
    newArrayLength = oldArray.length + 1;
    String[] t_array = new String[newArrayLength];
    if(oldArray != null)
    for(int i = 0 ; i < oldArray.length;i++)
    t_array[i]=oldArray[i];
    t_array[t_array.length-1]=newObject;
    return t_array;
}



}