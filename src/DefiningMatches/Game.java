package DefiningMatches;

import java.text.DecimalFormat;
import java.util.Random;

import DefiningTeams.Team;
import Drivers.Driver;
import StaticVariables.Settings;
import Stats.MatchStats;

public class Game extends Matchup {
	private String stageLabel;
	private int matchLabel;
	private Team teamA;
	private Team teamB;
	
	private Random rand = new Random();
	private MatchStats WLT;
	
	private boolean setManually = false;
	
	public void Simulate() {
		double oddsTeamAWins = CalculateChance(teamA.getRating(), teamB.getRating()) * 100;
		
		double random = rand.nextDouble() * 100;
		if (random > 0 && random < oddsTeamAWins) {
			super.setWinner(teamA);
			super.setLoser(teamB);
		} else {
			super.setWinner(teamB);
			super.setLoser(teamA);
		}
		
		// Update Teams Records
		super.getWinner().getRecord(stageLabel).MatchWin(super.getLoser());
		super.getLoser().getRecord(stageLabel).MatchLoss(super.getWinner());
		
		WLT.Update(stageLabel, this);
	}
	
	public void TBSimulate() {
		double oddsTeamAWins = CalculateChance(teamA.getRating(), teamB.getRating()) * 100;
		
		double random = rand.nextDouble() * 100;
		if (random > 0 && random < oddsTeamAWins) {
			super.setWinner(teamA);
			super.setLoser(teamB);
		} else {
			super.setWinner(teamB);
			super.setLoser(teamA);
		}
		
		// Update Teams Records
		super.getWinner().getRecord(stageLabel).TiebreakerWin(super.getLoser());
		super.getLoser().getRecord(stageLabel).TiebreakerLoss(super.getWinner());
		
		WLT.Update(stageLabel, this);
	}
	
	/**
	* Constructor
	* @param label The match id/number of the match, i.e., M1, M2, M3, etc
	* @param A Combatant A 
	* @param B Combatant B
	*/
	public Game(String sl, int l, Team A, Team B, MatchStats t) {
		stageLabel = sl;
		matchLabel = l;
		teamA = A;
		teamB = B;
		WLT = t;
	}
	
	/**
	* Constructor
	* @param label The match id/number of the match, i.e., M1, M2, M3, etc
	*/
	public Game(String sl, int l, MatchStats t) {
		stageLabel = sl;
		matchLabel = l;
		WLT = t;
	}
	
	// Calculates the chance of a team with rating RA beating a team with rating RB
	private double CalculateChance(double RA, double RB) {
		// Scale is used to bump up the ratings to a larger number so that the ELO formula would work better
		// It essentially just exaggerates the odds/makes an actual discernable difference between teams 
		// with conventionally small elo ratings
		
		// Higher means rating matters more
		// A scale of 1 makes most matchups 50/50
		int scale = Settings.ELO_SCALING;
		RA = RA * scale;
		RB = RB * scale;
		
		// Formula to determine chance
		double exp = (RB-RA)/400;
		double x = Math.pow(10, exp);
		double y = 1 + x;
		double z = 1 / y;
		return z;
	}
	
	public void setResult(Team t1, Team t2) {
		super.setWinner(t1);
		super.setLoser(t2);
		
		// Update Teams Records
		super.getWinner().getRecord(stageLabel).MatchWin(super.getLoser());
		super.getLoser().getRecord(stageLabel).MatchLoss(super.getWinner());
		
		WLT.Update(stageLabel, this);
		
		setManually = true;
	}
	
	public void setTBResult(Team t1, Team t2) {
		super.setWinner(t1);
		super.setLoser(t2);
		
		// Update Teams Records
		super.getWinner().getRecord(stageLabel).TiebreakerWin(super.getLoser());
		super.getLoser().getRecord(stageLabel).TiebreakerLoss(super.getWinner());
		
		WLT.Update(stageLabel, this);
		
		setManually = true;
	}
	
	public Team getWinner() {
		return super.getWinner();
	}

	public Team getLoser() {
		return super.getLoser();
	}

	public Team getTeamA() {
		return teamA;
	}

	public Team getTeamB() {
		return teamB;
	}

	public void setTeamA(Team teamA) {
		this.teamA = teamA;
	}

	public void setTeamB(Team teamB) {
		this.teamB = teamB;
	}
	
	public int getMatchLabel() {
		return matchLabel;
	}
	
	@Override
	public String toString() {
		if (super.getWinner() == null) {
			return getMatchDetails();
		} else {
			String s = getMatchDetails();
			if (super.getWinner() == teamA) {
				s += teamA.getTag() + " > " + teamB.getTag();
			} else {
				s += teamA.getTag() + " < " + teamB.getTag();
			}
			if (setManually) {
				s += "\n\nSet Manually";
			}
			return s;	
		}
	}
	
	public String getMatchDetails() {
		String s = "";
		
		s += matchLabel + ": " + stageLabel + " - " + teamA + " VS " + teamB + "\n";
		
		DecimalFormat df = new DecimalFormat("00.00");
		
		double oddsTeamAWins = CalculateChance(teamA.getRating(), teamB.getRating()) * 100;
		double oddsTeamBWins = CalculateChance(teamB.getRating(), teamA.getRating()) * 100;
		
		s += teamA.getTag() + ": " + df.format(oddsTeamAWins) + "% Chance to Win\n";
		s += teamB.getTag() + ": " + df.format(oddsTeamBWins) + "% Chance to Win\n\n";
		
		return s;
	}

	@Override
	public boolean resultDetermined() {
		return super.getWinner() != null;
	}

	public boolean getSetManually() {
		return setManually;
	}
}
