package uk.connorwright.StarGame.window.commands.help;

import uk.connorwright.StarGame.util.Strings;

public class DisplayCommands {


	public static void showHelp() {
		System.out.println(Strings.HELP_TEXT_MENU);
		System.out.println(Strings.HELP_DIVIDER);
		System.out.println(Strings.HELP_TEXT_SECTION_CHEATS);
		System.out.println(Strings.HELP_TEXT_GOD);
		System.out.println(Strings.HELP_DIVIDER);
		System.out.println(Strings.HELP_TEXT_SECTION_HELPFUL);
		System.out.println(Strings.HELP_TEXT_RESTART);
		System.out.println(Strings.HELP_DIVIDER);
		System.out.println(Strings.HELP_TEXT_SECTION_DEV);
		System.out.println(Strings.HELP_TEXT_NEXTSTATE);
		System.out.println(Strings.HELP_DIVIDER);

	}

}
