import java.io.*;

class room
{
public int x;
public int y;
public int height;
public boolean up = false;
public boolean down = false;
public boolean left = false;
public boolean right = false;
public int score = 999999;


public room()
{
   setValues(0,0,0,null);
}

public room(int x, int y, int height)
{
    setValues(x, y, height,null);
}

public room(int x, int y, int height, int[] directions)
{
    setValues(x, y, height,directions);
}

public void setValues(int x, int y, int height, int[] directions)
{
    this.x = x;
    this.y = y;
    this.height = height;
   // this.neighbors = neighbors;
}

public String getDirectionString()
{
    String retval = "";
    if(up)
    retval +=" UP";
    if(down)
    retval +=" DOWN";
    if(left)
    retval +=" LEFT";
    if(right)
    retval +=" RIGHT";
    return retval;
}

}


public class ac12_2022
{

public static room[] house;
public static int startX=0;
public static int startY = 0;
public static int endX=0;
public static int endY = 0;
public static int getMaxX1 = 0;
public static int getMaxY1 = 0;
public static String badRooms ="";

public static void printPath(String path)
{ char c = ' ';
    for(int i = 0; i< path.split("\n").length;i++)
    {
        if(path.split("\n")[i].length()>0)
            {
                System.out.print(path.split("\n")[i]);
                int x = 0;
                int y = 0;
                try{
                    x = Integer.parseInt(path.split("\n")[i].split(",")[0]);
                    y = Integer.parseInt(path.split("\n")[i].split(",")[1]);
                    c = (char)(house[getID(x,y)].height+'a');
                    if(x == startX && y == startY)
                        c= 'S';
                        if(x == endX && y == endY)
                        c= 'E';
                }catch(Exception e){}
                System.out.println(" "+c);
            }
    }
}

public static void main(String[] args)
{
    String filename = args[args.length-1];
    try
    {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine();
        int linecount = 0;
        while(line != null && line.length()>0)
        {//  System.out.println(line);
            processLine(line, linecount++);
            line =br.readLine();
        }
       
        printHouse();
        updateDirections();
        getMaxX1 = getMaxX();
        getMaxY1 = getMaxY();
       
       System.out.println("Start = "+startX+","+startY);
       System.out.println("End = "+endX+","+endY);
       System.out.println("Extents = "+getMaxX1+","+getMaxY1);
         String path = findEnd("");
       // System.out.println(path);
        System.out.println("MOVES : "+(path.split("\n").length-1));
       
    }
    catch(Exception e){System.out.println(e);}
    
}

public static boolean pathContains(String a, String x)
{ 
    for(int i = 0; i<a.split("\n").length;i++ )
        if(a.split("\n")[i].compareTo(x)==0) return true;

    return false;
}
public static String findEnd(String path)
{   int pathlength = path.split("\n").length;
    System.out.print(" "+pathlength);
     String bestPath = "";

    if(path.length()==0)
        path+=(""+startX+","+startY+"\n");
    
    int currentX = getXFromPath(path);
    int currentY = getYFromPath(path);
  
    if(currentX==endX && currentY==endY)
    {   System.out.println("_____"+(path.split("\n").length-1)+"________\n"); 
          printPath(path);
          badRooms="";
            return(path);
    }

    int currentID = getID(currentX, currentY);
    if(house[currentID].score < pathlength)
        return "";
        house[currentID].score=pathlength;
    for(int i = 0; i< 4; i++)
    {
    boolean testCase = false;
    int xMod = 0;
    int yMod = 0;
   
    String direction = "";
    switch(i)
    {
        case 0: xMod = 1; yMod = 0; testCase = house[currentID].right; direction=("RIGHT");break;
        case 1: xMod = 0; yMod = -1; testCase = house[currentID].down; direction =("DOWN");break;
        case 2: xMod = 0; yMod = 1; testCase = house[currentID].up; direction=("UP");break;
        case 3: xMod = -1; yMod = 0; testCase = house[currentID].left; direction=("LEFT");break;
        
    }
        String testLocation =     (currentX+xMod)+","+(currentY+yMod);

    if(testCase && !pathContains(path,testLocation)&&(currentX+xMod<=getMaxX1)&&(currentY+yMod<=getMaxY1)&&(currentX+xMod>=0)&&(currentY+yMod>=0)&& !pathContains(badRooms,testLocation))

    {  // System.out.print (direction);
       // String rPath = findEnd(path+testLocation+","+(house[getID(currentX+xMod, currentY+yMod)].height)+"\n");
       
       String rPath = findEnd(path+testLocation+"\n");
         
       if(rPath.length()>0)
        {
            if(bestPath.length()==0)
                bestPath = rPath;
            else
                if(rPath.length() < bestPath.length())
                bestPath = rPath;

        }
        else
        badRooms+=testLocation+"\n";
    }
    }

    return bestPath;

}


public static int getXFromPath(String path)
{   int x = startX;
    try{ 
    if(path.length()>0)
      x = Integer.parseInt(path.split("\n")[path.split("\n").length-1].split(",")[0]);
    }
    catch(Exception e){System.out.println(e);}
    return x;
}
public static int getYFromPath(String path)
{   int y = startY;
    try{ 
    if(path.length()>0)
      y = Integer.parseInt(path.split("\n")[path.split("\n").length-1].split(",")[1]);
    }
    catch(Exception e){System.out.println(e);}
    return y;
}
public static void processLine(String line,int y)
{//System.out.print("&");
    for(int x =0; x < line.length();x++)
    {   int height = 0; 
        if(line.charAt(x)!= 'S' && line.charAt(x)!='E')
            height = line.charAt(x)-'a';
        if(line.charAt(x)== 'S')
            {
                startX=x; startY=y;
            }    

        if(line.charAt(x)== 'E')
            {
                endX=x; endY=y;
                height = 'z'-'a';
            }    
        room tRoom = new room(x,y,height);
        house = addRoom(house, tRoom);
    }
}


public static void printHouse()
{
    for(int y = 0; y < getMaxY()+1;y++)
    {for(int x = 0; x < getMaxX()+1;x++)
    {
        System.out.print((char)(house[getID(x,y)].height+'a'));
    }
    System.out.println();
    }
}

public static int getMaxX()
{
    int retval = -1;
    for(int i = 0; i< house.length; i++)
    if(house[i].x > retval) retval = house[i].x;
  //  System.out.println("max x"+retval);
    return retval;
}

public static int getMaxY()
{
    int retval = -1;
    for(int i = 0; i< house.length; i++)
    if(house[i].y > retval) retval = house[i].y;
   // System.out.println("max y"+retval);
    return retval;
}

public static void updateDirections()
{System.out.print("U");
    int maxX = getMaxX();
    int maxY = getMaxY();
    for(int y =0;y<=maxY;y++)
    for(int x =0;x<=maxX;x++)
    {
        //up
        
        if(y==0) house[getID(x,y)].down=false;
           else
         {   if(house[getID(x,y-1)].height<=house[getID(x,y)].height+1)
                house[getID(x,y)].down=true;
               // System.out.println((char)(house[getID(x,y)].height+'a') + ","+(char)(house[getID(x,y-1)].height+'a'));
         }
        //down
        if(y==maxY) house[getID(x,y)].up=false;
            else
            if(house[getID(x,y+1)].height<=house[getID(x,y)].height+1)
                house[getID(x,y)].up=true;    
        //left
        if(x==0) house[getID(x,y)].left=false;
        else
        if(house[getID(x-1,y)].height<=house[getID(x,y)].height+1)
            house[getID(x,y)].left=true;
        //right
        if(x==maxX) house[getID(x,y)].right=false;
            else
        {      
            if((house[getID(x+1,y)].height-1)<=house[getID(x,y)].height)
               { house[getID(x,y)].right=true;       // System.out.println((int)(house[getID(x,y)].height+'a') + ","+(int)(house[getID(x+1,y)].height+'a'));
            }
        }

    }
}

public static int getID(int x, int y)
{   
    
    for(int i =0; i< house.length;i++)
    if(house[i].x == x && house[i].y == y)
     return i;
    return -1;
}

public static room[] addRoom(room[] oldHouse, room newRoom)
{
    int houseSize = 1;
    
    if(oldHouse != null)
        houseSize = oldHouse.length+1;
    room[] newHouse = new room[houseSize];
    for(int i =0 ;i< houseSize-1;i++)
        newHouse[i]=oldHouse[i];
    newHouse[newHouse.length-1]=newRoom;
    return newHouse;
}

}