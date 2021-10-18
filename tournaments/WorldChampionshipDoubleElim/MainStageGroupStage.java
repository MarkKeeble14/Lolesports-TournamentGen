package WorldChampionshipDoubleElim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import CustomExceptions.MismatchedNumberOfGroupsException;
import DefiningQualificationDetails.QualifiedThroughGroupPlacement;
import DefiningTeams.Team;
import StaticVariables.Strings;
import StaticVariables.Teams;
import Stats.Standings;
import Stats.ResultsTracker;
import TournamentComponents.Group;
import TournamentComponents.GroupStage;
import TournamentComponents.Tournament;

public class MainStageGroupStage extends GroupStage {
	public MainStageGroupStage(String label, Tournament partOf) {
		super(label, partOf);
	}

	int requiredNumberOfGroups = 4;
	
	@Override
	public void Simulate(List<Group> groups) throws Exception {
		if (groups.size() != requiredNumberOfGroups) {
			throw new MismatchedNumberOfGroupsException(requiredNumberOfGroups, groups.size());
		}
		
		ResultsTracker tracker = super.getPartOf().getT();
		Standings standings = super.getPartOf().getEots();
		
		// Set Groups
		Group A = groups.get(0);
		Group B = groups.get(1);
		Group C = groups.get(2);
		Group D = groups.get(3);
		
		A.FullSimulate(super.getLabel(), tracker, true);
		B.FullSimulate(super.getLabel(), tracker, true);
		C.FullSimulate(super.getLabel(), tracker, true);
		D.FullSimulate(super.getLabel(), tracker, true);
		
		standings.PlaceTeam(A.GetTeamFromPlacement(4), 16);
		standings.PlaceTeam(B.GetTeamFromPlacement(4), 16);
		standings.PlaceTeam(C.GetTeamFromPlacement(4), 16);
		standings.PlaceTeam(D.GetTeamFromPlacement(4), 16);
		
		List<Team> GSQ = new ArrayList<Team>(
				Arrays.asList(	A.GetTeamFromPlacement(1),
								A.GetTeamFromPlacement(2),
								A.GetTeamFromPlacement(3),
								B.GetTeamFromPlacement(1),
								B.GetTeamFromPlacement(2),
								B.GetTeamFromPlacement(3),
								C.GetTeamFromPlacement(1),
								C.GetTeamFromPlacement(2),
								C.GetTeamFromPlacement(3),
								D.GetTeamFromPlacement(1),
								D.GetTeamFromPlacement(2),
								D.GetTeamFromPlacement(3)));
	
		SetQualified(groups, GSQ);
		
		super.setGroups(groups);
	}
	
	@Override
	public void SetQualified(List<Group> groups, List<Team> teams) {
		Group A = groups.get(0);
		Group B = groups.get(1);
		Group C = groups.get(2);
		Group D = groups.get(3);
		
		Team A1 = teams.get(0);
		A1.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, A, 1));
		Team A2 = teams.get(1);
		A2.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, A, 2));
		Team A3 = teams.get(2);
		A3.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, A, 3));

		Team B1 = teams.get(3);
		B1.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, B, 1));
		Team B2 = teams.get(4);
		B2.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, B, 2));
		Team B3 = teams.get(5);
		B3.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, B, 3));
		
		Team C1 = teams.get(6);
		C1.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, C, 1));
		Team C2 = teams.get(7);
		C2.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, C, 2));
		Team C3 = teams.get(8);
		C3.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, C, 3));
		
		Team D1 = teams.get(9);
		D1.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, D, 1));
		Team D2 = teams.get(10);
		D2.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, D, 2));
		Team D3 = teams.get(11);
		D3.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, D, 3));
	}
}