import java.io.*;

public class AOC03
{
public static long total1 = 0;

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
public static void processLine1(String line)
{
String sortedLine = sortString(line);
System.out.println(line + " : " + sortedLine);


}

public static void processLine(String line)
	{
		int[] batteries = {0,1,2,3,4,5,6,7,8,9,10,11};
		int maxDigitOne = 0;
		int maxDigitTwo = 1;
	
	System.out.println("\n"+line + " LENGTH: " + line.length());
	System.out.println("POS\tRANGE\tSubSt\tStartVal");

		for(int q = 0; q<batteries.length; q++)
		{	
			System.out.print("Q:"+q + "\t");
			int iterationStart = (q==0?0:(batteries[q-1]+1));
			int iterationStop = line.length() - batteries.length + q +1;
			batteries[q]=iterationStart;
			System.out.print(iterationStart + ":" + iterationStop + "\t" + line.substring(iterationStart,iterationStop) + "\t+"+line.charAt(batteries[q])+"\t");
			
				for(int i = iterationStart+1; i< iterationStop; i++)
				
					if(line.charAt(i) > line.charAt(batteries[q]))
				{
				
				batteries[q] = i;
				System.out.print("NEW FOUND @ POSITION" + batteries[q] + " VALUE:" + line.charAt(batteries[q])+"\t");
				}
			System.out.println(batteries[q] + "\t" + line.charAt(batteries[q]));
//		System.out.println(">"+batteries[q]);
		}
		//System.out.println("<");
		String joltage = "";
		for(int k =0;k<batteries.length;k++)
			joltage += ("" + line.charAt(batteries[k]));
		
//		System.out.println ("" + stringToNum(joltage));	
		//System.out.println(joltage);
		total1+=stringToNum(joltage);
		System.out.println ("Line Value: "+joltage + "\tTotal: " + total1);	
	}

	private static String sortString(String input)
	{
		
		for(int i = 0; i < input.length()-1;i++)
			for(int j = i+1;j<input.length();j++)
				{
					if(input.charAt(i)<input.charAt(j))
						input = swapChar(input,i,j);
				}
		return input;
	}

	private static String swapChar(String input, int pos1, int pos2)
	{
		char tChar1 = input.charAt(pos1);
		char[] charArray = input.toCharArray();
		charArray[pos1] = charArray[pos2];
		charArray[pos2] = tChar1;
	
		
		return new String(charArray);
	}
	

	private static long stringToNum(String numberCandidate)
	{
		long returnValue = -1;
		try
		{
			returnValue = Long.parseLong(numberCandidate);
		}
		catch(Exception e)
		{
			System.out.println("stringToNum Error : " + numberCandidate);
		}
		return returnValue;
	}


}
