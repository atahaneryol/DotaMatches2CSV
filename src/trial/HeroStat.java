package trial;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class HeroStat {
	
	int winsWith;
	int lossWith;
	Long heroID;
	
	public HeroStat()
	{
		heroID = -1L;
		winsWith = 0;
		lossWith = 0;
	}
	
	public HeroStat(Long givenId)
	{
		heroID = givenId;
		winsWith = 0;
		lossWith = 0;
	}

	public int getWinsWith() {
		return winsWith;
	}

	public void setWinsWith(int winsWith) {
		this.winsWith = winsWith;
	}

	public int getLossWith() {
		return lossWith;
	}

	public void setLossWith(int lossWith) {
		this.lossWith = lossWith;
	}

	public Long getHeroID() {
		return heroID;
	}

	public void setHeroID(Long heroID) {
		this.heroID = heroID;
	}
	
	public void incWinsWith()
	{
		winsWith++;
	}
	
	public void incLossWith()
	{
		lossWith++;
	}
	
	public String toString()
	{
		String s = "ID: " + heroID + " Wins With: " + winsWith + " Loss With: " + lossWith;
		return s;
	}
	
	public String getHeroName()
	{
		return ""; // To do later
	}
	
	public static void updateHeroStat(HashMap<Long, HeroStat> givenMap, ArrayList<Player> givenPlayers, Boolean radWin, Long givenHeroID)
	{
		boolean weWin = false;
		if(getSide(givenPlayers))
		{
			weWin = radWin;
		}
		else
		{
			weWin = !radWin;
		}
		
		updateMap(givenMap,givenHeroID,weWin);
	}
	
	private static void updateMap(HashMap<Long, HeroStat> givenMap, Long hero, boolean weWin) 
	{
		HeroStat temp = givenMap.get(hero);
		if(temp == null) //This is a new hero
		{
			HeroStat addt = new HeroStat(hero); //Create new stats for the new player
			if(weWin)
			{
				addt.incWinsWith();
			}
			else
			{
				addt.incLossWith();
			}
			givenMap.put(hero, addt);
		}
		else //Have played with this player before
		{
			if(weWin)
			{
				temp.incWinsWith();
			}
			else
			{
				temp.incLossWith();
			}
		}
		
	}

	public static boolean getSide( ArrayList<Player> givenPlayers ) // Returns true if on radiant side
	{
		for(int i = 0; i < givenPlayers.size(); i++)
		{
			if(givenPlayers.get(i).getAccount_id() == 35202280)
			{
				if(i > 4)
				{
					return false;
				}
				else
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static void writeToCSV(String filename, HashMap<Long, HeroStat> givenMap) throws FileNotFoundException
	{
		ArrayList<HeroStat> lst = new ArrayList(givenMap.values());
		PrintWriter writer = new PrintWriter("HeroStatOut.csv");
		writer.println("AccountId,WinsWith,LossWith");
		for(int i = 0; i < lst.size(); i++)
		{
			String line = "" + lst.get(i).getHeroID() + "," + lst.get(i).getWinsWith() + "," + lst.get(i).getLossWith();
			writer.println(line);
		}
		writer.close();
	}
	

}
