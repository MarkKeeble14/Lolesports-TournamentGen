package Stats;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import DefiningTeams.Team;
import Enums.REGION;
import StaticVariables.Strings;

import java.util.Set;

public class Record implements Comparable<Record> {
	private String label;
	
	private Map<Team, WinLossCounter> matches = new HashMap<Team, WinLossCounter>();
	private Map<Team, WinLossCounter> tiebreakers = new HashMap<Team, WinLossCounter>();
	
	public Record(String label) {
		super();
		this.label = label;
	}
	
	public Record Loss(Map<Team, WinLossCounter> map, Team teamLostTo) {
		if (map.containsKey(teamLostTo)) {
			map.get(teamLostTo).Lose();
		} else {
			WinLossCounter counter = new WinLossCounter();
			counter.Lose();
			map.put(teamLostTo, counter);
		}
		return this;
	}
	
	public Record Win(Map<Team, WinLossCounter> map, Team teamLostTo) {
		if (map.containsKey(teamLostTo)) {
			map.get(teamLostTo).Win();
		} else {
			WinLossCounter counter = new WinLossCounter();
			counter.Win();
			map.put(teamLostTo, counter);
		}
		return this;
	}
	
	public Record MatchWin(Team teamBeat) {
		return Win(matches, teamBeat);
	}
	
	public Record MatchLoss(Team teamLostTo) {
		return Loss(matches, teamLostTo);
	}
	
	
	public Record TiebreakerWin(Team teamBeat) {
		return Win(tiebreakers, teamBeat);
	}
	
	public Record TiebreakerLoss(Team teamLostTo) {
		return Loss(tiebreakers, teamLostTo);
	}
	
	public boolean hasBeaten(Team team) {
		if (matches.containsKey(team)) {
			return matches.get(team).getWins() > 0;
		} else {
			return false;
		}
	}
	
	public boolean hasBeatenInTiebreaker(Team team) {
		if (tiebreakers.containsKey(team)) {
			return tiebreakers.get(team).getWins() > 0;
		} else {
			return false;
		}
	}
	
	public boolean hasLostToInTiebreaker(Team team) {
		if (tiebreakers.containsKey(team)) {
			return tiebreakers.get(team).getLosses() > 0;
		} else {
			return false;
		}
	}
	
	public boolean hasBeatenTeamWhichHasBeatenTeamInTiebreaker(Team team) {
		for (Entry<Team, WinLossCounter> entry : tiebreakers.entrySet()) {
			if (entry.getKey().getRecord().hasBeatenInTiebreaker(team)) {
				return true;
			}
		}
		return false;
	}
	
	public int getTimesBeat(Team team) {
		if (matches.containsKey(team)) {
			return matches.get(team).getWins();
		}
		return 0;
	}

	public int getNumberOfTiebreakers() {
		int i = 0;
		for (Entry<Team, WinLossCounter> entry : tiebreakers.entrySet()) {
			WinLossCounter counter = entry.getValue();
			i += counter.getWins() + counter.getLosses();
		}
		return i;
	}
	
	public int getNumberOfTiebreakerWins() {
		int i = 0;
		for (Entry<Team, WinLossCounter> entry : tiebreakers.entrySet()) {
			WinLossCounter counter = entry.getValue();
			if (counter.getWins() > 0) {
				i += counter.getWins();	
			}
		}
		return i;
	}
	
	public int getNumberOfTiebreakerLosses() {
		int i = 0;
		for (Entry<Team, WinLossCounter> entry : tiebreakers.entrySet()) {
			WinLossCounter counter = entry.getValue();
			if (counter.getLosses() > 0) {
				i += counter.getLosses();	
			}
		}
		return i;
	}
	
	public int getTiebreakerWinsOfTeamsLostToInTiebreakers() {
		int i = 0;
		for (Entry<Team, WinLossCounter> entry : tiebreakers.entrySet()) {
			WinLossCounter counter = entry.getValue();
			if (counter.getLosses() > 0) {
				i += entry.getKey().getRecord().getNumberOfTiebreakerWins();	
			}
		}
		return i;
	}
	
	public int getTotalWinsOfTeamsLostToInTiebreakers() {
		int i = 0;
		for (Entry<Team, WinLossCounter> entry : tiebreakers.entrySet()) {
			if (entry.getValue().getLosses() > 0) {
				entry.getKey().getRecord().getWins(true);	
			}
		}
		return i;
	}
	
	public int getWinsOfTeamsBeat() {
		int i = 0;
		for (Entry<Team, WinLossCounter> entry : matches.entrySet()) {
			i += entry.getKey().getRecord().getWins(false);
		}
		return i;
	}
	
	public int getWins(boolean includeTiebreakers) {
		int i = 0; 
		for (Entry<Team, WinLossCounter> entry : matches.entrySet()) {
			i += entry.getValue().getWins();
		}
		if (includeTiebreakers) {
			for (Entry<Team, WinLossCounter> entry : tiebreakers.entrySet()) {
				i += entry.getValue().getWins();
			}	
		}
		return i;
	}

	public int getLosses(boolean includeTiebreakers) {
		int i = 0; 
		for (Entry<Team, WinLossCounter> entry : matches.entrySet()) {
			i += entry.getValue().getLosses();
		}
		if (includeTiebreakers) {
			for (Entry<Team, WinLossCounter> entry : tiebreakers.entrySet()) {
				i += entry.getValue().getLosses();
			}
		}
		return i;
	}

	public Map<Team, WinLossCounter> getMatches() {
		return matches;
	}

	public Map<Team, WinLossCounter> getTiebreakers() {
		return tiebreakers;
	}

	@Override
	public int compareTo(Record r) {
		if (this.getWins(false) > r.getWins(false)) {
			return 1;
		} else if (this.getWins(false) < r.getWins(false)) {
			return -1;
		} else {
			return 0;			
		}
	}
	
	@Override
	public String toString() {
		return "Record [label=" + label + ", wins=" + getWins(true) + ", losses=" + getLosses(true) + "]";
	}

	public String detailedPrint() {
		String s = "";
		int wins = getWins(true);
		int losses = getLosses(true);
		if (tiebreakers.size() > 0) {
			s += StringifyRecord(label, wins, losses);
			s += StringifyMap(matches);
			s += "\n" + label + ": Tiebreakers\n";
			s += StringifyMap(tiebreakers);
		} else {
			s += StringifyRecord(label, wins, losses);
			s += StringifyMap(matches);
		}
		return s;
	}
	
	private String StringifyRecord(String caption, int wins, int losses) {
		DecimalFormat df = new DecimalFormat("#0.00");
		
		String s1 = caption;
		String s2 = "| Wins=" + wins;
		String s3 = "| Losses=" + losses;
		String s4 = "| Percent - " + df.format((float)wins / (losses + wins) * 100) + "%";
		String s = String.format(Strings.RecordTableFormat, s1, s2, s3, s4);
		return s;
	}
	
	private String StringifyMap(Map<Team, WinLossCounter> map) {
		String s = "";
		Set<Entry<Team, WinLossCounter>> set = map.entrySet();
		for (Entry<Team, WinLossCounter> entry : set) {
			WinLossCounter WLC = entry.getValue();
			s += StringifyRecord(entry.getKey().toString(), WLC.getWins(), WLC.getLosses());
		}
		return s;
	}

	public Map<Team, Integer> getTimesBeatTeamMap() {
		Map<Team, Integer> map = new HashMap<Team, Integer>();
		Set<Entry<Team, WinLossCounter>> set = matches.entrySet();
		for (Entry<Team, WinLossCounter> entry : set) {
			if (entry.getValue().getWins() > 0) {
				map.put(entry.getKey(), entry.getValue().getWins());
			}
		}
		return map;
	}

	public String getLabel() {
		return label;
	}
}
