import java.io.*;

public class AOC03
{
public static int total1 = 0;

public static void main(String[] args)
	{
		
		if(args.length==0)
		{
			System.out.println("Missing filename argument");
			System.exit(0);
		}

		BufferedReader reader;
		String line = "";
		try
		{
			reader = new BufferedReader(new FileReader(args[0]));
			line = reader.readLine();
			while(line != null)
			{
				processLine(line);
				line = reader.readLine();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		System.out.println("" + total1);
	}

public static void processLine(String line)
	{
		int[] batteries = {0,1};
		int maxDigitOne = 0;
		int maxDigitTwo = 1;
		System.out.println(line);
		for(int q = 0; q<batteries.length; q++)
		{
			System.out.print("\nQ:"+q + "   ");
			int iterationStart = (q==0?0:(batteries[q-1]+1));
			int iterationStop = line.length() - batteries.length + q +1;
			for(int i = iterationStart; i< iterationStop; i++)
				{System.out.print(line.charAt(i));
					if(line.charAt(i) > line.charAt(batteries[q]))
					batteries[q] = i;
				}
//		System.out.println(">"+batteries[q]);
		}
		System.out.println("<");
		String joltage = "";
		for(int k =0;k<batteries.length;k++)
			joltage += ("" + line.charAt(batteries[k]));
		
//		System.out.println ("" + stringToNum(joltage));	
		System.out.println(joltage);
		total1+=stringToNum(joltage);
		System.out.println (joltage + "    -     " + total1);	
	}

	private static int stringToNum(String numberCandidate)
	{
		int returnValue = -1;
		try
		{
			returnValue = Integer.parseInt(numberCandidate);
		}
		catch(Exception e)
		{
			System.out.println("stringToNum Error : " + numberCandidate);
		}
		return returnValue;
	}


}
