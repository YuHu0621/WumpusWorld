/*
 /* Main class for playing in the Wumpus world
 */

//package edu.umn.d.cs5541.program3.wumpusworld;


/**
 *
 * Class used to run a WumpusWorld game
 */
public class Main {

	/**
	 * Routine to run a game using user input
	 *
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		WumpusMaze myWorld;
		if(args.length != 1){
			System.err.println("Wrong input: please enter Brave, Careful, Dumb, or simulation. Simulation runs 10000 rounds.");
		}
		if (args[0].equals("simulation")) {
			int success = 0;
			for (int i = 0; i < 100; i++) {
				myWorld = new WumpusMaze();
				
				BraveAgent myBraveAgent = new BraveAgent(myWorld);
				if (myBraveAgent.dfs()) {
					success++;
				}
			}
			

			int Dumbsuccess = 0;
			for (int i = 0; i < 100; i++) {
				myWorld = new WumpusMaze();
				
				DumbAgent myDumbAgent = new DumbAgent(myWorld);
				if (myDumbAgent.dfs()) {
					Dumbsuccess++;
				}
			}
			

			int Carefulsuccess = 0;
			for (int i = 0; i < 100; i++) {
				myWorld = new WumpusMaze();
				
				CarefulAgent myCarefulAgent = new CarefulAgent(myWorld);
				if (myCarefulAgent.dfs()) {
					Carefulsuccess++;
				}
			}
			System.out.println("Brave agent Probability: " + success);
			System.out.println("Dumb agent Probability: " + Dumbsuccess);
			System.out.println("Careful agent Probability: " + Carefulsuccess);
		}else if(args[0].equals("Brave")){
			myWorld = new WumpusMaze();
			System.out.println("Here comes the brave agent");
			BraveAgent myBraveAgent = new BraveAgent(myWorld);
			myBraveAgent.dfs();
		}else if(args[0].equals("Careful")){
			myWorld = new WumpusMaze();
			 System.out.println("Here comes my careful agent: ");
			 CarefulAgent myCarefulAgent = new CarefulAgent(myWorld);
			 myCarefulAgent.dfs();
		}else if(args[0].equals("Dumb")){
			myWorld = new WumpusMaze();
			 System.out.println("Here comes my dumb agent: ");
			 DumbAgent myDumbAgent = new DumbAgent(myWorld);
			 myDumbAgent.dfs();
		}else{
			System.err.println("Wrong input: please enter Brave, Careful or Dumb");
		}
		

	}

}
