import java.io.*;

public class AOC02
{
	
	private static long total1 = 0;
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
	System.out.println(total1);

	}
	
	private static void processLine(String input)
	{
		String[] inputs = input.split(",");
		for(int i = 0; i < inputs.length;i++)
		{
			String[] numbers = inputs[i].split("-");
			
		System.out.println(numbers[0] + "-" + numbers[1]);
		for(long j = stringToNum(numbers[0]);j<=stringToNum(numbers[1]);j++)
			{
				checkDigitForRepeat(j);
				if(findPatternNumbers(""+j))
					System.out.println(j);
		
			}
	
		}
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

	private static boolean findPatternNumbers(String patternCandidate)
	{
		System.out.print("analyzing " + patternCandidate);
		for(int i = 2; i<= patternCandidate.length(); i++)
		if(patternCandidate.length() % i == 0)
		{
					
			boolean mismatchFound = false;
			int patternLength = patternCandidate.length() / i;
			int j = patternLength;
			String patternToMatch = patternCandidate.substring(0,j);
			System.out.print(": "+j + " : " + patternToMatch + ";");
			while((j<=patternCandidate.length() - patternLength) && !mismatchFound)
			{
			int stop = j + patternCandidate.length();
			String patternSubString = patternCandidate.substring(j,stop); 
			System.out.print("@" + patternSubString);
			mismatchFound = (patternSubString.compareTo(patternToMatch) != 0);
			if(mismatchFound) System.out.println("exiting");
			j = stop;
			}
			if(!mismatchFound)
			return(true);	
		
		} 
		return(false);
	

	}


	

	private static void checkDigitForRepeat(long candidate)
	{
		String intString = "" +candidate;
		if(intString.length() % 2 != 0)
			return;
		if (intString.substring(0,intString.length()/2).compareTo(intString.substring(intString.length()/2,intString.length()))==0)
		{	//System.out.println(candidate + " repeat");
		//total1 += stringToNum(intString.substring(0,intString.length()/2));
		total1 += candidate;
//		System.out.println(total1);
		}
		
	}

}