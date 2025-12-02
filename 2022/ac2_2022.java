import java.io.*;
public class ac2_2022
{

    public static int currentCount = 0;

    public static void main(String[] args)
    {
        String filename = args[args.length-1];
        BufferedReader reader;
        String line = "";
        try
        {
            reader = new BufferedReader(new FileReader(filename));
            line = reader.readLine();
            while(line != null)
            {
                line=line.toUpperCase();
                if(line.length()>=3)
                    currentCount+=(getMove(line.charAt(0),line.charAt(2)));
                line = reader.readLine(); 
            }
     
        }
        catch(Exception e){System.out.println("PROBLEM");}
        System.out.println("ANSWER "+currentCount);
    }

    private static int getMove(char x, char z)
    {
        int v1 = (int)(x)-(int)('A');
        int v2 = (int)(z)-(int)('Y');
        return getScore(x,(char)((int)('X')+((3+v1+v2)%3)));
    }

    private static int getScore(char x, char y)
    {
        int v1 = (int)x-(int)('A');
        int v2 = (int)y-(int)('X');
        int delta = (2+v1-v2)%3;
        int[] scores = {0,6,3};
        return v2+scores[delta]+1;
    }
}
    // public static void bubbleSort(int[] a)
    // {   
    //      if(a.length>1){
       
    //     for(int i = 0; i<a.length-1;i++)
    //     for(int j = 0; j<(a.length-i-1);j++)
    //     if(a[j]<a[j+1])
    //     {
    //         int t_int = a[j+1];
    //         a[j+1]=a[j];
    //         a[j]=t_int;
    //     }
    // }
    // }
    // public static void addElement(int a)
    // {   int oldLength=0;
    //     if (calArray != null)
    //         oldLength = calArray.length;
            

    //     int t_array[] = new int[oldLength + 1];
        
        

    //     if(t_array.length>1)
    //     for(int i =0; i< calArray.length;i++)
    //     t_array[i]=calArray[i];
        
        
    //     t_array[t_array.length-1]=a;
    //     calArray=t_array;
    //     System.out.println("LENGTH: "+ t_array.length);
    // }

    
    // public static void compareMax()
    // {   //System.out.println(currentCount);
    //     addElement(currentCount);
     
    //     currentCount = 0;

 //   }

//}