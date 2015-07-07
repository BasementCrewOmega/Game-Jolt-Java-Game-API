package org.ag.test;

import java.util.ArrayList;
import java.util.Random;

import org.gamejolt.DataStore.DataStoreOperation;
import org.gamejolt.DataStore.DataStoreType;
import org.gamejolt.GameJoltAPI;
import org.gamejolt.Trophy;
import org.gamejolt.User;

public class GJAPITester {
	
	public static void main(String[] args) {
		
		// Define these variables yourself! 
		final int GAME_ID = 77473;
		final String GAME_SECRET = "a1a11ce3cb62ab5ee9d9de87ef52d3e6";
		
		final String USER_NAME = "TheColorMan";
		final String USER_TOKEN = "j3j22493";
		
		final int TROPHY_ID= 33637;
		final int TABLE_ID = 80259;
		
		// Initialise Game Jolt API
		GameJoltAPI api = new GameJoltAPI(GAME_ID, GAME_SECRET);
		api.setVerbose(true);
		api.verifyUser(USER_NAME, USER_TOKEN);
		
		// This is a test, so make the library print everything it can.
		api.setVerbose(true);
		
		// Get Trophies
		ArrayList<Trophy> trophies = api.getTrophies();
		
		// Achieve a Trophy
		api.achieveTrophy(TROPHY_ID);
		
		//get single trophy and check if it is achieved
		Trophy t = api.getTrophy(TROPHY_ID);
		if (!t.isAchieved()){
			System.err.println("Trophy was achieved, but is not marked achieved...");
		}
                
                /*
                User me = api.getVerifiedUser();
                
		// get Userdata
		api.getUser(me.getName());
		api.getUser(me.getId());
		*/
                
		// Play Sessions
		api.sessionOpen();
		api.sessionCheck();
		api.sessionUpdate();
		api.sessionClose();
		
		// Add a Highscore
		int k = new Random().nextInt(200);
		if (TABLE_ID!=0){
			api.addHighscore(TABLE_ID,k+" Coins Test",k);
			api.addHighscore(TABLE_ID,"testguest",k+" Coins Test Guest", k-1);
		}else{
			api.addHighscore(k+" Coins Test", k);
			api.addHighscore("testguest",k+" Coins Test Guest", k-1);
		}
		
		//get highscores
		if (TABLE_ID!=0){
			api.getHighscores(TABLE_ID);
		}else{
			api.getHighscores();
		}
		//get highscoretables
		api.getHighscoreTables();
		
		//get rank of a score
		if (TABLE_ID!=0){
			api.getHighscoreRank(100,TABLE_ID);
		}else{
			api.getHighscoreRank(100);
		}
		
		// Get Data Store Keys (User-Specific, requires verified User)
		ArrayList<String> userDataStoreKeys = api.getDataStoreKeys(DataStoreType.USER);
		
		// Set a value in a Data Store
		api.setDataStore(DataStoreType.USER, "a_test_key", "a_test_value");
		
		// Update a value in a Data Store
		api.updateDataStore(DataStoreType.USER, "a_test_key", DataStoreOperation.APPEND, "lol");
		
		//get a Data Store from
		api.getDataStore(DataStoreType.USER, "a_test_key");
		
		//get the Servertime
		api.getServerTime();
		
	}
	
}
