package WorldChampionship;

import java.util.ArrayList;
import java.util.List;

import CustomExceptions.MismatchedNumberOfGroupsException;
import CustomExceptions.MismatchedNumberOfPoolsException;
import DefiningTeams.Team;
import TournamentComponents.DrawStructure;
import TournamentComponents.Group;
import TournamentComponents.Pool;
import TournamentComponents.Tournament;

public class MainStageGroupDraw extends DrawStructure {
	public MainStageGroupDraw(String label, Tournament partOf) {
		super(label, partOf);
	}

	int requiredNumberOfGroups = 4;
	int requiredNumberOfPools = 4;
	
	@Override
	public void Simulate(List<Group> groups, List<Pool> pools) throws Exception {
		if (groups.size() != requiredNumberOfGroups) {
			throw new MismatchedNumberOfGroupsException(requiredNumberOfGroups, groups.size());
		}
		if (pools.size() != requiredNumberOfPools) {
			throw new MismatchedNumberOfPoolsException(requiredNumberOfPools, pools.size());
		}
		
		// Set Pools
		Pool P1 = pools.get(0);
		Pool P2 = pools.get(1);
		Pool P3 = pools.get(2);
		Pool PI = pools.get(3);
		
		// Set Groups
		Group A = groups.get(0);
		Group B = groups.get(1);
		Group C = groups.get(2);
		Group D = groups.get(3);
		
		// Draw Teams From Pool One
		A.Add(P1.Draw());
		B.Add(P1.Draw());
		C.Add(P1.Draw());
		D.Add(P1.Draw());
		pools.remove(0);
		
		// Draw Teams From Pool Two
		A.Add(P2.DrawWithSameRegionRule(pools, 0, groups, 0, new ArrayList<Team>()));
		B.Add(P2.DrawWithSameRegionRule(pools, 0, groups, 1, new ArrayList<Team>()));
		C.Add(P2.DrawWithSameRegionRule(pools, 0, groups, 2, new ArrayList<Team>()));
		D.Add(P2.DrawWithSameRegionRule(pools, 0, groups, 3, new ArrayList<Team>()));
		pools.remove(0);
		
		// Draw Teams From Pool Three
		A.Add(P3.DrawWithSameRegionRule(pools, 0, groups, 0, new ArrayList<Team>()));
		B.Add(P3.DrawWithSameRegionRule(pools, 0, groups, 1, new ArrayList<Team>()));
		C.Add(P3.DrawWithSameRegionRule(pools, 0, groups, 2, new ArrayList<Team>()));
		D.Add(P3.DrawWithSameRegionRule(pools, 0, groups, 3, new ArrayList<Team>()));
		pools.remove(0);
		
		// Draw Teams From Pool PI
		A.Add(PI.DrawWithSameRegionRule(pools, 0, groups, 0, new ArrayList<Team>()));
		B.Add(PI.DrawWithSameRegionRule(pools, 0, groups, 1, new ArrayList<Team>()));
		C.Add(PI.DrawWithSameRegionRule(pools, 0, groups, 2, new ArrayList<Team>()));
		D.Add(PI.DrawWithSameRegionRule(pools, 0, groups, 3, new ArrayList<Team>()));
		pools.remove(0);
		
		super.setGroups(groups);
	}
}
