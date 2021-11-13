package com.goodgame.studio.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.goodgame.studio.ArmyGenerator;

public class Client {

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		int nroSoldiers = (args.length == 0) ? (int) Math.round(Math.random() * Integer.MAX_VALUE)
				: Integer.parseInt(args[0]);

		ArmyGenerator armyGenerator = new ArmyGenerator(Client.getTypeOfSoldiers());

		Map<String, Long> army = armyGenerator.generateArmy(nroSoldiers);

		System.out.println(String.format("Total soldiers requested: %,d\n", nroSoldiers));
		army.entrySet().stream().forEach((entry) -> {
			System.out.println(String.format("%,d %s", entry.getValue(), entry.getKey()));
		});

		System.out.println(
				String.format("\nTotal soldiers generated: %,d", army.values().stream().reduce(0l, (a, b) -> a + b)));
		long endTime = System.currentTimeMillis();

		System.out.println(String.format("Generation time: %d ms", (endTime - startTime)));

	}

	/**
	 * Helper method that retrieve the types of soldiers from which the army is
	 * going to be build.
	 * 
	 * This method could load this information from a database, text file, rest
	 * endpoint, etc. For this example it has a hardcoded list.
	 * 
	 * @return
	 */
	private static List<String> getTypeOfSoldiers() {

		List<String> soldierTypes = new ArrayList<>();
		soldierTypes.add("Spearmen");
		soldierTypes.add("Archers");
		soldierTypes.add("Swordsmen");
		soldierTypes.add("Artillery");
		soldierTypes.add("Horsemen");

		return soldierTypes;
	}

}
