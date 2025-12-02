/*
bvwbjplbgvbhsrlpgdmjqwftvncz: first marker after character 5
nppdvjthqldpwncqszvftbrmjlhg: first marker after character 6
nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg: first marker after character 10
zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw: first marker after character 11

mjqjpqmgbljsphdztnvjfqwrcgsmlb: first marker after character 19
bvwbjplbgvbhsrlpgdmjqwftvncz: first marker after character 23
nppdvjthqldpwncqszvftbrmjlhg: first marker after character 23
nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg: first marker after character 29
zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw: first marker after character 26
*/
//JAVA


import java.io.*;

public class ac6_2022{

public static char[] buffer1 = new char[14];
public static int buffer1counter = 0;

public static void main(String[] args)
{
    String filename = args[args.length-1];
   
    try{
        BufferedReader br= new BufferedReader(new FileReader(filename));
    String line = br.readLine();
        while(line !=null)
        {   
            resetbuffer1();
            for(int i =0; i<line.length();i++)
            {
                buffer1addchar(line.charAt(i));
                if(i>=buffer1.length)
                    if(checkforunique())
                        {System.out.println(i+1+"\n");
                        break;
                        }

            }
            line =br.readLine();
        }

    }

    catch(Exception e){}
}

public static void buffer1addchar(char c)
{
    buffer1[buffer1counter]=c;
    buffer1counter++;
    if(buffer1counter>=buffer1.length) buffer1counter=0;
    dumpbuffer();

}

public static boolean checkforunique()
{
    for(int i = 0; i<buffer1.length-1;i++)
        for(int j = i+1;j<buffer1.length;j++)
            if(buffer1[i]==buffer1[j]) return false;

        return true;
}

public static void resetbuffer1()
{
    for(int i =0; i<buffer1.length;i++)
    buffer1[i]=(char)0;
    buffer1counter=0;
}

public static void dumpbuffer()
{
    for(int i =0; i<buffer1.length;i++)
    {
        System.out.print(buffer1[(buffer1counter+i)%4]+" ");
    
    }
    System.out.println();
}

}
