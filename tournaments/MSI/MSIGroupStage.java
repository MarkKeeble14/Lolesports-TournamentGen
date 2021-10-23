package MSI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import CustomExceptions.MismatchedNumberOfGroupsException;
import DefiningQualificationDetails.QualifiedThroughGroupPlacement;
import DefiningTeams.Team;
import StaticVariables.Strings;
import Stats.Standings;
import Stats.MatchStats;
import TournamentComponents.Group;
import TournamentComponents.GroupStage;
import TournamentComponents.Tournament;

public class MSIGroupStage extends GroupStage {
	public MSIGroupStage(String label, Tournament partOf) {
		super(label, partOf);
	}

	int requiredNumberOfGroups = 3;
	
	@Override
	public void Simulate(List<Group> groups) throws Exception {
		if (groups.size() != requiredNumberOfGroups) {
			throw new MismatchedNumberOfGroupsException(requiredNumberOfGroups, groups.size());
		}
		
		MatchStats tracker = super.getPartOf().getT();
		Standings standings = super.getPartOf().getEots();
		
		// Set Groups
		Group A = groups.get(0);
		Group B = groups.get(1);
		Group C = groups.get(2);
		
		// Play out games
		A.FullSimulate(super.getLabel(), tracker, true); 
		B.FullSimulate(super.getLabel(), tracker, true); 
		C.FullSimulate(super.getLabel(), tracker, true); 
	
		SetQualified(groups, standings);
		
		super.setGroups(groups);
	}
}
