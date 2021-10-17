package WorldChampionshipLong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Classes.Group;
import Classes.Tournament;
import CustomExceptions.MismatchedNumberOfGroupsException;
import Misc.Strings;
import Misc.Teams;
import QualificationDetails.QualifiedThroughGroupPlacement;
import StatsTracking.EOTStandings;
import StatsTracking.RegionalWLTracker;
import Teams.Team;
import TournamentComponents.GroupStage;

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
		
		RegionalWLTracker tracker = super.getPartOf().getT();
		EOTStandings standings = super.getPartOf().getEots();
		
		// Set Groups
		Group A = groups.get(0);
		Group B = groups.get(1);
		Group C = groups.get(2);
		Group D = groups.get(3);
		
		A.FullSimulate(super.getLabel(), tracker, true);
		B.FullSimulate(super.getLabel(), tracker, true);
		C.FullSimulate(super.getLabel(), tracker, true);
		D.FullSimulate(super.getLabel(), tracker, true);
		
		standings.PlaceTeam(A.GetTeamFromPlacement(5), 20);
		standings.PlaceTeam(B.GetTeamFromPlacement(5), 20);
		standings.PlaceTeam(C.GetTeamFromPlacement(5), 20);
		standings.PlaceTeam(D.GetTeamFromPlacement(5), 20);
		
		List<Team> GSQ = new ArrayList<Team>(
				Arrays.asList(	A.GetTeamFromPlacement(1),
								A.GetTeamFromPlacement(2),
								A.GetTeamFromPlacement(3),
								A.GetTeamFromPlacement(4),
								B.GetTeamFromPlacement(1),
								B.GetTeamFromPlacement(2),
								B.GetTeamFromPlacement(3),
								B.GetTeamFromPlacement(4),
								C.GetTeamFromPlacement(1),
								C.GetTeamFromPlacement(2),
								C.GetTeamFromPlacement(3),
								C.GetTeamFromPlacement(4),
								D.GetTeamFromPlacement(1),
								D.GetTeamFromPlacement(2),
								D.GetTeamFromPlacement(3),
								D.GetTeamFromPlacement(4)));
	
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
		Team A4 = teams.get(3);
		A4.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, A, 4));

		Team B1 = teams.get(4);
		B1.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, B, 1));
		Team B2 = teams.get(5);
		B2.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, B, 2));
		Team B3 = teams.get(6);
		B3.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, B, 3));
		Team B4 = teams.get(7);
		B4.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, B, 4));
		
		Team C1 = teams.get(8);
		C1.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, C, 1));
		Team C2 = teams.get(9);
		C2.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, C, 2));
		Team C3 = teams.get(10);
		C3.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, C, 3));
		Team C4 = teams.get(11);
		C4.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, C, 4));
		
		Team D1 = teams.get(12);
		D1.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, D, 1));
		Team D2 = teams.get(13);
		D2.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, D, 2));
		Team D3 = teams.get(14);
		D3.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, D, 3));
		Team D4 = teams.get(15);
		D4.setNewQD(new QualifiedThroughGroupPlacement(Strings.MSGS, D, 4));
	}
}