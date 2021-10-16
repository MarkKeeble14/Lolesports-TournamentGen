package LCS;

import java.util.List;

import Classes.BracketSection;
import Classes.Group;
import Classes.Tournament;
import Matches.Series;
import Misc.Strings;
import StatsTracking.EOTStandings;
import StatsTracking.RegionalWLTracker;
import TournamentComponents.Bracket;

public class LockinKOBracket extends Bracket {

	public LockinKOBracket(String label, Tournament partOf, String fedTeamsThrough) {
		super(label, partOf, fedTeamsThrough);
		// TODO Auto-generated constructor stub
	}
	public LockinKOBracket(String label, Tournament partOf) {
		super(label, partOf);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void Simulate(List<Group> groups) throws Exception {
		RegionalWLTracker tracker = super.getPartOf().getT();
		EOTStandings standings = super.getPartOf().getEots();
		
		// Set Groups
		Group A = groups.get(0);
		Group B = groups.get(1);
		
		String qfs = Strings.Concat(Strings.BasicBridgeWSpace, super.getLabel(), Strings.QFS);
		String sfs = Strings.Concat(Strings.BasicBridgeWSpace, super.getLabel(), Strings.SFS);
		String fs = Strings.Concat(Strings.BasicBridgeWSpace, super.getLabel(), Strings.FS);
		
		BracketSection S1 = new BracketSection(qfs);
		BracketSection S2 = new BracketSection(sfs);
		BracketSection S3 = new BracketSection(fs);
		
		Series M1 = new Series(1, 3, tracker);
		Series M2 = new Series(2, 3, tracker);
		Series M3 = new Series(3, 3, tracker);
		Series M4 = new Series(4, 3, tracker);
		S1.addSeries(M1, M2, M3, M4);
		Series M5 = new Series(5, 5, tracker);
		Series M6 = new Series(6, 5, tracker);
		S2.addSeries(M5, M6);
		Series M7 = new Series(7, 5, tracker);
		S3.addSeries(M7);
		
		M1.setTeamA(A.GetTeamFromPlacement(1));
		M1.setTeamB(B.GetTeamFromPlacement(4));
		M1.Simulate();
		M2.setTeamA(A.GetTeamFromPlacement(3));
		M2.setTeamB(B.GetTeamFromPlacement(2));
		M2.Simulate();
		
		M3.setTeamA(B.GetTeamFromPlacement(1));
		M3.setTeamB(A.GetTeamFromPlacement(4));
		M3.Simulate();
		M4.setTeamA(B.GetTeamFromPlacement(3));
		M4.setTeamB(A.GetTeamFromPlacement(2));
		M4.Simulate();
		
		M5.setTeamA(M1.getWinner());
		M5.setTeamB(M2.getWinner());
		M5.Simulate();
		M6.setTeamA(M3.getWinner());
		M6.setTeamB(M4.getWinner());
		M6.Simulate();
		
		M7.setTeamA(M5.getWinner());
		M7.setTeamB(M6.getWinner());
		M7.Simulate();
		
		standings.PlaceTeam(M1.getLoser(), 6);
		standings.PlaceTeam(M2.getLoser(), 6);
		
		standings.PlaceTeam(M3.getLoser(), 6);
		standings.PlaceTeam(M4.getLoser(), 6);
		
		standings.PlaceTeam(M5.getLoser(), 4);
		standings.PlaceTeam(M6.getLoser(), 4);
		
		standings.PlaceTeam(M7.getLoser(), 2);
		standings.PlaceTeam(M7.getWinner(), 1);
		
		// General Tracking Stuff
		super.addBracketSections(S1, S2, S3);
		super.setChampionshipSeries(M7);
	}
}