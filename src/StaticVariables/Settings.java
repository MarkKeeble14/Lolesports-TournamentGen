package StaticVariables;

import Drivers.DomesticDriver;
import Drivers.InternationalDriver;
import Enums.DRIVER_TYPE;
import Enums.ELO_SCALING_TYPE;

public class Settings {
	private static DRIVER_TYPE CURRENT_DRIVER;
	
	// Variables / Tuning
	// Higher means rating matters more, i.e, Upsets are LESS likely
	// A scale of 1 makes most matchups 50/50
	
	// 300 means upsets are reasonably likely; Works well for international competitions
	// 75 is very rigid; Works well for domestic competitions
	public static int ELO_SCALING;
	private static int REASONABLE_ELO_SCALING = 75;
	private static int RIGID_ELO_SCALING = 300;
	private static int PURE_ELO_SCALING = 500;
	private static int WACKY_ELO_SCALING = 50;
	
	public static final boolean SHOW_REGIONAL_WL_WITH_0_GAMES = false;
	
	public static final boolean PRINT_SERIES_SUMMARY = false;
	public static final boolean PRINT_DETAILED_SERIES_SUMMARY = true;
	
	public static final boolean PRINT_QUALIFICATION_REASONS = false;
	
	public static final boolean PRINT_GROUP_STAGE_GAMES = false;
	
	public static final boolean PRINT_GROUP_STAGE_SUMMARY = true;
	
	public static final boolean PRINT_OVERALL_WL = true;
	public static final boolean PRINT_INDIVIDUAL_WL = true;
	public static final boolean PRINT_MAJOR_REGIONAL_WL = true;
	public static final boolean PRINT_MINOR_REGIONAL_WL = true;

	public static final boolean PRINT_BRACKET_SUMMARY = true;

	public static final boolean PRINT_TOURNAMENT_STATS = true;
	
	public static void setEloScaling(ELO_SCALING_TYPE type) {
		switch(type) {
		case REASONABLE:
			ELO_SCALING = REASONABLE_ELO_SCALING;
			break;
		case WACKY:
			ELO_SCALING = WACKY_ELO_SCALING;
			break;
		case RIGID:
			ELO_SCALING = RIGID_ELO_SCALING;
			break;
		case PURE:
			ELO_SCALING = PURE_ELO_SCALING;
			break;
		}
	}
}