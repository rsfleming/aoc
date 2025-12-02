import java.io.*;
//advent of code day 1/12/2022
import javax.lang.model.util.ElementScanner6;


public class findmax{

    public static int maxcal = 0;
    public static int max2cal = 0;
    public static int max3cal = 0;
    public static int currentCount = 0;
    public static int calArray[];

    public static void main(String[] args)
    {
        String filename = "";
        BufferedReader reader;
        String line = "";
        if(args.length>0)
        
        {filename = args[args.length-1];
        System.out.println(filename);
        try{
        reader = new BufferedReader(new FileReader(filename));
        line = reader.readLine();
        while(line != null)
        {
        if(line.length()<1)
            {compareMax();
                
            }
            else
            {int tInt = Integer.parseInt(line);
                
            if(tInt > 0)
                currentCount+=tInt;
            }

        
        line = reader.readLine();
        
        }
        compareMax();
        }
        catch(Exception e)
        {

        }
      
        
        bubbleSort(calArray);
        for(int i =0; i<calArray.length;i++)
        System.out.println(calArray[i]);
        if(calArray.length>2)
        System.out.println("\ntop 3 total:"+(calArray[0]+calArray[1]+calArray[2]));
        System.out.println("1st place: "+calArray[0]);
        System.out.println("2nd place: "+calArray[1]);
        System.out.println("3rd place: "+calArray[2]);
        
    }
        
    }

    public static void bubbleSort(int[] a)
    {   
         if(a.length>1){
       
        for(int i = 0; i<a.length-1;i++)
        for(int j = 0; j<(a.length-i-1);j++)
        if(a[j]<a[j+1])
        {
            int t_int = a[j+1];
            a[j+1]=a[j];
            a[j]=t_int;
        }
    }
    }
    public static void addElement(int a)
    {   int oldLength=0;
        if (calArray != null)
            oldLength = calArray.length;
            

        int t_array[] = new int[oldLength + 1];
        
        

        if(t_array.length>1)
        for(int i =0; i< calArray.length;i++)
        t_array[i]=calArray[i];
        
        
        t_array[t_array.length-1]=a;
        calArray=t_array;
        System.out.println("LENGTH: "+ t_array.length);
    }

    
    public static void compareMax()
    {   //System.out.println(currentCount);
        addElement(currentCount);
     
        currentCount = 0;

    }

}