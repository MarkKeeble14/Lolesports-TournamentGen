package LCS;

import java.util.List;

import CustomExceptions.MismatchedNumberOfGroupsException;
import DefiningMatches.Series;
import StaticVariables.Strings;
import Stats.MatchStats;
import Stats.Standings;
import TournamentComponents.Bracket;
import TournamentComponents.BracketSlice;
import TournamentComponents.Group;
import TournamentComponents.Pool;
import TournamentComponents.Tournament;

public class MidSeasonShowdown extends Bracket {
	public MidSeasonShowdown(String label, Tournament partOf) {
		super(label, partOf);
		// TODO Auto-generated constructor stub
	}	
	
	public MidSeasonShowdown(String label, Tournament partOf, String fedTeamsThrough) {
		super(label, partOf, fedTeamsThrough);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Simulate(List<Group> groups) throws Exception {
		MatchStats tracker = super.getPartOf().getT();
		Standings standings = super.getPartOf().getEots();
		
		// Set Groups
		Group A = groups.get(0);
		
		BracketSlice S1 = new BracketSlice(super.getLabel(), Strings.S1, 1);
		BracketSlice S2 = new BracketSlice(super.getLabel(), Strings.S2, 2);
		BracketSlice S3 = new BracketSlice(super.getLabel(), Strings.S3, 3);
		BracketSlice S4 = new BracketSlice(super.getLabel(), Strings.S4, 4);
		
		Series M1 = new Series(1, 5, tracker);
		Series M2 = new Series(2, 5, tracker);
		Series M3 = new Series(3, 5, tracker);
		Series M4 = new Series(4, 5, tracker);
		S1.addSeries(M1, M2, M3, M4);
		Series M5 = new Series(5, 5, tracker);
		Series M6 = new Series(6, 5, tracker);
		S2.addSeries(M5, M6);
		Series M7 = new Series(7, 5, tracker);
		S3.addSeries(M7);
		Series M8 = new Series(8, 5, tracker);
		S4.addSeries(M8);
		
		M1.setTeamA(A.GetTeamFromPlacement(1));
		M1.setTeamB(A.GetTeamFromPlacement(4));
		M1.Simulate();
		M2.setTeamA(A.GetTeamFromPlacement(3));
		M2.setTeamB(A.GetTeamFromPlacement(2));
		M2.Simulate();
		
		M3.setTeamA(A.GetTeamFromPlacement(6));
		M3.setTeamB(M2.getLoser());
		M3.Simulate();
		M4.setTeamA(A.GetTeamFromPlacement(5));
		M4.setTeamB(M1.getLoser());
		M4.Simulate();
		standings.PlaceTeamDuringBacketStage(M3.getLoser(), true);
		standings.PlaceTeamDuringBacketStage(M4.getLoser(), false);
		
		M5.setTeamA(M1.getWinner());
		M5.setTeamB(M2.getWinner());
		M5.Simulate();
		M6.setTeamA(M3.getWinner());
		M6.setTeamB(M4.getWinner());
		M6.Simulate();
		standings.PlaceTeamDuringBacketStage(M6.getLoser(), true);
		
		M7.setTeamA(M5.getLoser());
		M7.setTeamB(M6.getWinner());
		M7.Simulate();
		standings.PlaceTeamDuringBacketStage(M7.getLoser(), true);
		
		M8.setTeamA(M5.getWinner());
		M8.setTeamB(M7.getWinner());
		M8.Simulate();
		standings.PlaceTeamDuringBacketStage(M8.getLoser(), true);
		standings.PlaceTeamDuringBacketStage(M8.getWinner(), true);
		
		super.addBracketSections(S1, S2, S3, S4);
		super.setChampionshipSeries(M8);
	}

	@Override
	public void Simulate(Bracket b, List<Group> groups) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
