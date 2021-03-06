package LCS;

import java.util.List;

import DefiningMatches.Series;
import StaticVariables.Strings;
import Stats.MatchStats;
import Stats.Standings;
import TournamentComponents.Bracket;
import TournamentComponents.BracketSlice;
import TournamentComponents.Group;
import TournamentComponents.Tournament;

public class SummerPlayoffsLCS extends Bracket {
	public SummerPlayoffsLCS(String label, Tournament partOf) {
		super(label, partOf);
		// TODO Auto-generated constructor stub
	}
	
	public SummerPlayoffsLCS(String label, Tournament partOf, String fedTeamsThrough) {
		super(label, partOf, fedTeamsThrough);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void Simulate(List<Group> groups) throws Exception {
		// TODO Auto-generated method stub
		MatchStats tracker = super.getPartOf().getT();
		Standings standings = super.getPartOf().getEots();
		
		// Set Groups
		Group A = groups.get(0);
		
		BracketSlice S1 = new BracketSlice(super.getLabel(), Strings.S1, 1);
		BracketSlice S2 = new BracketSlice(super.getLabel(), Strings.S2, 2);
		BracketSlice S3 = new BracketSlice(super.getLabel(), Strings.S3, 3);
		BracketSlice S4 = new BracketSlice(super.getLabel(), Strings.S4, 4);
		BracketSlice S5 = new BracketSlice(super.getLabel(), Strings.FS, 5);
		
		Series M1 = new Series(1, 5, tracker);
		Series M2 = new Series(2, 5, tracker);
		Series M3 = new Series(3, 5, tracker);
		Series M4 = new Series(4, 5, tracker);
		S1.addSeries(M1, M2, M3, M4);
		Series M5 = new Series(5, 5, tracker);
		Series M6 = new Series(6, 5, tracker);
		Series M7 = new Series(7, 5, tracker);
		Series M8 = new Series(8, 5, tracker);
		S2.addSeries(M5, M6, M7, M8);
		Series M9 = new Series(9, 5, tracker);
		S3.addSeries(M9);
		Series M10 = new Series(10, 5, tracker);
		Series M11 = new Series(11, 5, tracker);
		S4.addSeries(M10, M11);
		Series M12 = new Series(12, 5, tracker);
		S5.addSeries(M12);
		
		M1.setTeamA(A.GetTeamFromPlacement(4));
		M1.setTeamB(A.GetTeamFromPlacement(5));
		M1.Simulate();
		
		M2.setTeamA(A.GetTeamFromPlacement(3));
		M2.setTeamB(A.GetTeamFromPlacement(6));
		M2.Simulate();
		
		M3.setTeamA(A.GetTeamFromPlacement(7));
		M3.setTeamB(M2.getLoser());
		M3.Simulate();
		standings.PlaceTeamDuringBacketStage(M3.getLoser(), true);
		
		M4.setTeamA(A.GetTeamFromPlacement(8));
		M4.setTeamB(M1.getLoser());
		M4.Simulate();
		standings.PlaceTeamDuringBacketStage(M4.getLoser(), false);
		
		M5.setTeamA(A.GetTeamFromPlacement(1));
		M5.setTeamB(M1.getWinner());
		M5.Simulate();
		
		M6.setTeamA(A.GetTeamFromPlacement(2));
		M6.setTeamB(M2.getWinner());
		M6.Simulate();
		
		M7.setTeamA(M3.getWinner());
		M7.setTeamB(M5.getLoser());
		M7.Simulate();
		standings.PlaceTeamDuringBacketStage(M7.getLoser(), true);
		
		M8.setTeamA(M4.getWinner());
		M8.setTeamB(M6.getLoser());
		M8.Simulate();
		standings.PlaceTeamDuringBacketStage(M8.getLoser(), false);
		
		M9.setTeamA(M5.getWinner());
		M9.setTeamB(M6.getWinner());
		M9.Simulate();
		
		M10.setTeamA(M7.getWinner());
		M10.setTeamB(M8.getWinner());
		M10.Simulate();
		standings.PlaceTeamDuringBacketStage(M10.getLoser(), true);
		
		M11.setTeamA(M10.getWinner());
		M11.setTeamB(M9.getLoser());
		M11.Simulate();
		standings.PlaceTeamDuringBacketStage(M11.getLoser(), true);
		
		M12.setTeamA(M9.getWinner());
		M12.setTeamB(M11.getWinner());
		M12.Simulate();
		standings.PlaceTeamDuringBacketStage(M12.getLoser(), true);
		standings.PlaceTeamDuringBacketStage(M12.getWinner(), true);
		
		// General Tracking Stuff
		super.addBracketSections(S1, S2, S3, S4, S5);
		super.setChampionshipSeries(M12);
	}

	@Override
	public void Simulate(Bracket b, List<Group> groups) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
