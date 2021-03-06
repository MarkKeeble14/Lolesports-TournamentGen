package MSI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import CustomExceptions.MismatchedNumberOfGroupsException;
import DefiningMatches.Series;
import StaticVariables.Strings;
import Stats.Standings;
import Stats.MatchStats;
import TournamentComponents.Bracket;
import TournamentComponents.BracketSlice;
import TournamentComponents.Group;
import TournamentComponents.Pool;
import TournamentComponents.Tournament;

public class KnockoutBracket extends Bracket {
	public KnockoutBracket(String label, Tournament partOf) {
		super(label, partOf);
	}

	public KnockoutBracket(String label, TournamentMSI tournamentMSI, String rsgs) {
		super(label, tournamentMSI, rsgs);
	}

	int requiredNumberOfGroups = 1;
	
	@Override
	public void Simulate(List<Group> groups) throws Exception {
		if (groups.size() != requiredNumberOfGroups) {
			throw new MismatchedNumberOfGroupsException(requiredNumberOfGroups, groups.size());
		}
		
		MatchStats tracker = super.getPartOf().getT();
		Standings standings = super.getPartOf().getEots();
		
		// Set Groups
		Group A = groups.get(0);
		
		Pool poolOne = new Pool(Strings.LPoolOne, A.GetTeamFromPlacement(1), A.GetTeamFromPlacement(2));
		Pool poolTwo = new Pool(Strings.LPoolTwo, A.GetTeamFromPlacement(3), A.GetTeamFromPlacement(4));
		
		BracketSlice S1 = new BracketSlice(super.getLabel(), Strings.SFS, 1);
		BracketSlice S2 = new BracketSlice(super.getLabel(), Strings.FS, 2);
		
		Series M1 = new Series(1, 5, tracker);
		Series M2 = new Series(2, 5, tracker);
		S1.addSeries(M1, M2);
		Series M3 = new Series(3, 5, tracker);
		S2.addSeries(M3);
		
		M1.setTeamA(poolOne.Draw());
		M2.setTeamA(poolTwo.Draw());
		M1.setTeamB(poolOne.Draw());
		M2.setTeamB(poolTwo.Draw());
		M1.Simulate();
		M2.Simulate();
		standings.PlaceTeamDuringBacketStage(M1.getLoser(), true);
		standings.PlaceTeamDuringBacketStage(M2.getLoser(), false);
		
		M3.setTeamA(M1.getWinner());
		M3.setTeamB(M2.getWinner());
		M3.Simulate();
		standings.PlaceTeamDuringBacketStage(M3.getLoser(), true);
		standings.PlaceTeamDuringBacketStage(M3.getWinner(), true);
		
		// General Tracking Stuff
		super.addBracketSections(S1, S2);
		super.setChampionshipSeries(M3);
	}

	@Override
	public void Simulate(Bracket b, List<Group> groups) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
