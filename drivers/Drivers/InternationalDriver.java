package Drivers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DefiningTeams.RatingDefinedTeam;
import DefiningTeams.Team;
import Enums.ELO_SCALING_TYPE;
import MSI.TournamentMSI;
import StaticVariables.Settings;
import StaticVariables.Strings;
import StaticVariables.Teams;
import TournamentComponents.Pool;
import TournamentComponents.Tournament;
import Utility.Util;
import Utility.UtilMaps;
import WorldChampionship.TournamentWorldChampionship;
import WorldChampionship2VS3.TournamentWorldChampionship2VS3;
import WorldChampionshipCurrentState.CurrentStateOfTournamentWorldChampionship;
import WorldChampionshipDoubleElim.TournamentWorldChampionshipDoubleElim;
import WorldChampionshipLong.TournamentWorldChampionshipLongFormat;

public class InternationalDriver {
	// Worlds
	// Current State - 		100,000 	~10 Seconds
	// Standard - 			10,000		~4-5 Seconds
	// DoubleElim - 		10,000		~4-5 Seconds
	// 2VS3 - 				10,000		~4-5 Seconds
	// Long - 				10,000		~22 Seconds
	
	// MSI
	// Standard - 			10,000		~1-2 Seconds
	private static final int numberOfSims = 10000;
	
	// Main
	public static void main(String[] args) throws Exception {
		Settings.setEloScaling(ELO_SCALING_TYPE.REASONABLE);
		
		// SimulateCurrentWorldsState().PrintInfo(true, false, false, true);
		// SimulateStandardWC().PrintInfo(false, true, false, true);
		// SimulateDoubleElimWC().PrintInfo(false, false, false, true);
		// Simulate2VS3WC().PrintInfo(false, false, false, true);
		
		// SimulateStandardMSI().PrintInfo(false, false, false, true);
		
		// SimulateLongWC().PrintInfo(true, true, true, true);
		
		LoopTournament(numberOfSims);
	}

	private static List<Pool> getLongWCPools() {
		Pool P1 = new Pool(Strings.LPoolOne, new RatingDefinedTeam(Teams.EDG), new RatingDefinedTeam(Teams.DK),
				new RatingDefinedTeam(Teams.MAD), new RatingDefinedTeam(Teams.O100T)); 
		
		Pool P2 = new Pool(Strings.LPoolTwo, new RatingDefinedTeam(Teams.FPX), new RatingDefinedTeam(Teams.GEN), 
				new RatingDefinedTeam(Teams.FNC), new RatingDefinedTeam(Teams.PSG));
		
		Pool P3 = new Pool(Strings.LPoolThree, new RatingDefinedTeam(Teams.RNG), new RatingDefinedTeam(Teams.T1), 
				new RatingDefinedTeam(Teams.TL), new RatingDefinedTeam(Teams.GAM)); 
		
		Pool P4 = new Pool(Strings.LPoolFour, new RatingDefinedTeam(Teams.RGE), new RatingDefinedTeam(Teams.C9));
		
		Pool PI1 = new Pool(Strings.LPIPoolOne, new RatingDefinedTeam(Teams.LNG), new RatingDefinedTeam(Teams.HLE), 
				new RatingDefinedTeam(Teams.G2), new RatingDefinedTeam(Teams.TSM), 
				new RatingDefinedTeam(Teams.BYG), new RatingDefinedTeam(Teams.JT), 
				new RatingDefinedTeam(Teams.SGB), new RatingDefinedTeam(Teams.TS));
		
		Pool PI2 = new Pool(Strings.LPIPoolTwo, new RatingDefinedTeam(Teams.INF), new RatingDefinedTeam(Teams.GS), 
				new RatingDefinedTeam(Teams.UOL), new RatingDefinedTeam(Teams.PCE),
				new RatingDefinedTeam(Teams.RED), new RatingDefinedTeam(Teams.DFM));
		
		return new ArrayList<Pool>(Arrays.asList(PI1, PI2, P1, P2, P3, P4));
	}
	
	private static List<Pool> get2021WCPools() {
		Pool PIPool1 = new Pool(Strings.LPIPoolOne, new RatingDefinedTeam(Teams.LNG), new RatingDefinedTeam(Teams.HLE), new RatingDefinedTeam(Teams.BYG), new RatingDefinedTeam(Teams.C9)); 
		Pool PIPool2 = new Pool(Strings.LPIPoolTwo, new RatingDefinedTeam(Teams.INF), new RatingDefinedTeam(Teams.GS), new RatingDefinedTeam(Teams.UOL), new RatingDefinedTeam(Teams.PCE), new RatingDefinedTeam(Teams.RED), new RatingDefinedTeam(Teams.DFM));
		Pool P1 = new Pool(Strings.LPoolOne, new RatingDefinedTeam(Teams.DK), new RatingDefinedTeam(Teams.EDG), new RatingDefinedTeam(Teams.MAD), new RatingDefinedTeam(Teams.PSG)); 
		Pool P2 = new Pool(Strings.LPoolTwo, new RatingDefinedTeam(Teams.O100T), new RatingDefinedTeam(Teams.FNC), new RatingDefinedTeam(Teams.GEN), new RatingDefinedTeam(Teams.FPX));
		Pool P3 = new Pool(Strings.LPoolThree, new RatingDefinedTeam(Teams.TL), new RatingDefinedTeam(Teams.T1), new RatingDefinedTeam(Teams.RGE), new RatingDefinedTeam(Teams.RNG));
		return new ArrayList<Pool>(Arrays.asList(PIPool1, PIPool2, P1, P2, P3));
	}
	
	private static List<Pool> get2021MSIPools() {
		Pool P1 = new Pool(Strings.LPoolOne, new RatingDefinedTeam(Teams.RNG), new RatingDefinedTeam(Teams.DK), new RatingDefinedTeam(Teams.PSG), new RatingDefinedTeam(Teams.C9), new RatingDefinedTeam(Teams.MAD), new RatingDefinedTeam(Teams.GAM)); 
		Pool P2 = new Pool(Strings.LPoolTwo, new RatingDefinedTeam(Teams.PGG), new RatingDefinedTeam(Teams.UOL), new RatingDefinedTeam(Teams.PNG), new RatingDefinedTeam(Teams.IW), new RatingDefinedTeam(Teams.DFM), new RatingDefinedTeam(Teams.INF));
		return new ArrayList<Pool>(Arrays.asList(P1, P2));
	}
	
	private static Tournament SimulateLongWC() throws Exception {
		Tournament WC = new TournamentWorldChampionshipLongFormat(Strings.LWC);
		List<Pool> pools = getLongWCPools();
		WC.Simulate(pools);
		return WC;
	}
	
	public static Tournament SimulateStandardWC() throws Exception {
		Tournament WC = new TournamentWorldChampionship(Strings.LWC);
		List<Pool> pools = get2021WCPools();
		WC.Simulate(pools);
		return WC;
	}
	
	public static Tournament SimulateDoubleElimWC() throws Exception {
		Tournament WC = new TournamentWorldChampionshipDoubleElim(Strings.LWC);
		List<Pool> pools = get2021WCPools();
		WC.Simulate(pools);
		return WC;
	}
	
	public static Tournament Simulate2VS3WC() throws Exception {
		Tournament WC = new TournamentWorldChampionship2VS3(Strings.LWC);
		List<Pool> pools = get2021WCPools();
		WC.Simulate(pools);
		return WC;
	}
	
	// Simulates the current World Championship, from it's current state (Updated Manually).
	public static Tournament SimulateCurrentWorldsState() throws Exception {
		Tournament WC = new CurrentStateOfTournamentWorldChampionship(Strings.LWC);
		WC.Simulate(null);
		return WC;
	}
	
	public static Tournament SimulateStandardMSI() throws Exception {
		Tournament MSI = new TournamentMSI();
		List<Pool> pools = get2021MSIPools();
		MSI.Simulate(pools);
		return MSI;
	}
	
	private static void LoopTournament(int x) throws Exception {
		Map<Integer, Tournament> tournamentMap = new HashMap<Integer, Tournament>();
		Map<String, Integer> timesTeamWonMap = new HashMap<String, Integer>();
		Map<String, List<Integer>> indexOfTeamWins = new HashMap<String, List<Integer>>();
		for (int i = 0; i < x; i++) {
			
			// Change this to specifiy which tournament to simulate
			Tournament T = SimulateStandardMSI();
			
			tournamentMap.put(tournamentMap.size(), T);
			Team champion = T.getWinner();
			if (timesTeamWonMap.containsKey(champion.getTag())) {
				timesTeamWonMap.put(champion.getTag(), timesTeamWonMap.get(champion.getTag()) + 1);
				List<Integer> prevWins =  indexOfTeamWins.get(champion.getTag());
				prevWins.add(i);
				indexOfTeamWins.put(champion.getTag(), prevWins);
			} else {
				timesTeamWonMap.put(champion.getTag(), 1);
				indexOfTeamWins.put(champion.getTag(), new ArrayList<Integer>(Arrays.asList(i)));
			}
		}
		
		Util.PrintSectionBreak("World's Simulations");
		
		// Program
		Scanner scan = new Scanner(System.in);
		String sentinal = "NO";
		String input = "";
		while (true) {
			// Print Results 
			// Currently can count duplicate tournament results (Same exact outcomes) as a seperate tournament win,
			// Which might skew the results a little?
			timesTeamWonMap = UtilMaps.sortByIntegerValue(timesTeamWonMap);
			Util.NicePrintResults(timesTeamWonMap, x);
			
			System.out.print("\nShow me a World where X Wins: ");
			input = scan.nextLine().toUpperCase();
			
			if (input.compareTo(sentinal) == 0) {
				System.out.println("\nOk");
				break;
			}
			
			// Print out possible numbers
			if (indexOfTeamWins.containsKey(input)) {
				List<Integer> options = indexOfTeamWins.get(input);
				
				if (options.size() > 0) {
					int index = options.get(0);
					options.remove(0);
					indexOfTeamWins.put(input, options);
					
					System.out.println("Print Tournament Progression?: ");
					boolean printTProg = GetYN(scan);
					
					System.out.println("Print Tournament Champion Records?: ");
					boolean printCRec = GetYN(scan);
					
					System.out.println("Print W/L?: ");
					boolean printRWL = GetYN(scan);
					
					System.out.println("Print Tournament Standings?: ");
					boolean printTStand = GetYN(scan);
					
					Util.PrintSectionBreak("Printing out Results of: Simulation #" + index + " -");
					tournamentMap.get(index).PrintInfo(printCRec, printRWL, printTStand, printTProg);
				} else {
					System.out.println("No more saved simulations where " + input + " Wins; Run again if you'd like");
				}
			} else {
				System.out.println("No saved simulations where " + input + " Wins; Run again if you'd like");
				continue;
			}
		}
		
		scan.close();
	}
	
	private static boolean GetYN(Scanner scan) {
		System.out.print("Y or N?: ");
		
		String input = scan.nextLine();
		while (input.toUpperCase().compareTo("Y") != 0 && input.toUpperCase().compareTo("N") != 0) {
			input = scan.nextLine();
		}
		
		return input.toUpperCase().compareTo("Y") == 0 ? true : false;
	}
}