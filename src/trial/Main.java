package trial;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, MalformedURLException, IOException, InterruptedException 
	{
		//All needed data structures
		HashMap<Long, PlayerStat> playerInfo = new HashMap<Long, PlayerStat>();
		HashMap<Long, HeroStat> heroInfo = new HashMap<Long, HeroStat>();
		HashMap<Long,String> heroMap = new HashMap<Long,String>();
		ArrayList<String[]> matrix = new ArrayList<String[]>();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //There are plenty of unused fields
		
		//Get hero names
		HeroReader heroList = mapper.readValue(new URL("https://api.steampowered.com/IEconDOTA2_570/GetHeroes/v0001/?key=76B0087D72B1F2A5ED4EA292757BEA68&language=en_us"), HeroReader.class);
		heroMap = heroList.getResult().getHeroMap();
		//System.out.println(heroList.getHeroes().size());
		
		//Using getResult() because that is how the json is arranged on the api side.
		
		for(int i = 0; i < heroList.getResult().getHeroes().size() ; i++) //Loop for the heroes
		{
			
//			if(i==24 || i == 108) // There is no 24 or 108 id hero
//			{
//				i++;
//			}
			int heroId = heroList.getResult().getHeroes().get(i).getId().intValue();
			String urlString = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/?key=76B0087D72B1F2A5ED4EA292757BEA68&account_id=35202280&hero_id=" + heroId;
			Result matchList = mapper.readValue(new URL(urlString), Result.class);
			ArrayList<Match> matches = matchList.getResult().getMatches();
			//Add a pause between API calls
			Thread.sleep(100);
			
			for(int k = 0; k < matches.size(); k++ ) // Loop for the matches for each hero
			{
				Long matchId = matches.get(k).getMatch_id();
				//Get match details to learn the winning side
				//To do : Make the key a variable that is to be asked to the user
				String urlDetails = "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?key=76B0087D72B1F2A5ED4EA292757BEA68&match_id=" + matchId;;
				MatchDetails matchDet = null;
				try{ //Sometimes the API returns a 503 HTTP response if too busy, keep the program flowing
					matchDet = mapper.readValue(new URL(urlDetails), MatchDetails.class);
					Thread.sleep(100);
				}catch(Exception e)
				{
					k--;
					//e.printStackTrace();
					//System.out.println("");
				}
				if(matchDet != null && matchDet.getResult().getHuman_players() == 10)
				{
					PlayerStat.updatePlayerStat(playerInfo, matches.get(k).getPlayers(), matchDet.getResult().getRadiant_win());
					//Keeps track of who you won and lost against, same with heroes
					HeroStat.updateHeroStat(heroInfo, matches.get(k).getPlayers(), matchDet.getResult().getRadiant_win(), new Long(i));
					//This add to a Nx113 matrix where each column is hero id, and entry indicating if that hero is on our side, other side or not played
					HeroReader.addToMatrix(matrix, matches.get(k).getPlayers(), matchDet.getResult().getRadiant_win(), heroMap.get(new Long(heroId)));
				}
				System.out.println("Hero: " + i + "/111 -- Match: " + (k+1) + "/" + matches.size());
			}
			
		}
		
		PlayerStat.writeToCSV("Test", playerInfo);
		HeroStat.writeToCSV("Test2", heroInfo);
		HeroReader.writeMatToCSV("test3", matrix, heroMap);
	}

}
