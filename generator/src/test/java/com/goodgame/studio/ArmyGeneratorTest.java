package com.goodgame.studio;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ArmyGeneratorTest {

	private ArmyGenerator classUnderTest;
	private List<String> soldierTypes;
	
	@Test
	void generateArmyWithLessSoldiersThanTypesAvailable() {

		final int nroSoldiers = 2;

		soldierTypes = new ArrayList<>();
		soldierTypes.add("Spearmen");
		soldierTypes.add("Archers");
		soldierTypes.add("Swordsmen");
		soldierTypes.add("Artillery");
		soldierTypes.add("Horsemen");

		classUnderTest = new ArmyGenerator(soldierTypes);
		Map<String, Long> army = classUnderTest.generateArmy(nroSoldiers);

		long nroSoldiersGenerated = army.values().stream().reduce(0l, (a, b) -> a + b);

		// The army generated has all the types of soldiers
		assertEquals(soldierTypes.size(), army.size());
		// the number of soldiers generated should be the same requested
		assertEquals(nroSoldiers, nroSoldiersGenerated);
	}

	@Test
	void generateArmyWithMoreSoldiersThanTypesAvailable() {

		final int nroSoldiers = 5200;

		soldierTypes = new ArrayList<>();
		soldierTypes.add("Spearmen");
		soldierTypes.add("Archers");
		soldierTypes.add("Swordsmen");
		soldierTypes.add("Artillery");
		soldierTypes.add("Horsemen");

		classUnderTest = new ArmyGenerator(soldierTypes);
		Map<String, Long> army = classUnderTest.generateArmy(nroSoldiers);

		long nroSoldiersGenerated = army.values().stream().reduce(0l, (a, b) -> a + b);

		// The army generated has all the types of soldiers
		assertEquals(soldierTypes.size(), army.size());
		// the number of soldiers generated should be the same requested
		assertEquals(nroSoldiers, nroSoldiersGenerated);
	}

	@Test
	void generateArmyMultipleTimeWithRandomDistributionOfSoldiers() {

		final int nroSoldiers = 13;
		final String soldierTypeSample = "Swordsmen";
		Map<String, Long> army = null;

		int iterations = 300;
		final int TIMES_ITERATED = iterations;
		int numberGeneratedForSoldierTypeSample = 0;
		long nroSoldiersGenerated = 0;

		soldierTypes = new ArrayList<>();
		soldierTypes.add("Spearmen");
		soldierTypes.add("Archers");
		soldierTypes.add("Swordsmen");
		soldierTypes.add("Artillery");
		soldierTypes.add("Horsemen");

		classUnderTest = new ArmyGenerator(soldierTypes);

		while (iterations > 0) {

			army = classUnderTest.generateArmy(nroSoldiers);
			numberGeneratedForSoldierTypeSample += army.get(soldierTypeSample);

			nroSoldiersGenerated = army.values().stream().reduce(0l, (a, b) -> a + b);
			// the number of soldiers generated should be the same requested
			assertEquals(nroSoldiers, nroSoldiersGenerated);

			iterations--;
		}

		// validate that the number of this soldier type, changes on every execution (it
		// could be times that the number is the same due to the randomness
		// characteristics, that why we tested with a big number if iteration to reduce
		// the likelihood of repetition )
		assertNotEquals(TIMES_ITERATED, numberGeneratedForSoldierTypeSample / TIMES_ITERATED);
		// The army generated has all the types of soldiers
		assertEquals(soldierTypes.size(), army.size());

	}

	@Test
	void generateEmptyArmyDueToEmptySoldierTypeSet() {
		final int nroSoldiers = 10;
		final int ZERO_SOLDIERS = 0;

		soldierTypes = new ArrayList<>();

		classUnderTest = new ArmyGenerator(soldierTypes);
		Map<String, Long> army = classUnderTest.generateArmy(nroSoldiers);

		long nroSoldiersGenerated = army.values().stream().reduce(0l, (a, b) -> a + b);

		// The army generated is empty as the types of soldiers
		assertEquals(soldierTypes.size(), army.size());
		// the number of soldiers generated should be ZERO
		assertEquals(ZERO_SOLDIERS, nroSoldiersGenerated);
	}
	
	@Test
	void generateEmptyArmyDueToNullSoldierTypeSet() {
		final int nroSoldiers = 10;
		final int ZERO_SOLDIERS = 0;

		soldierTypes = null;

		classUnderTest = new ArmyGenerator(soldierTypes);
		Map<String, Long> army = classUnderTest.generateArmy(nroSoldiers);

		long nroSoldiersGenerated = army.values().stream().reduce(0l, (a, b) -> a + b);

		// The army generated is empty as the types of soldiers
		assertEquals(ZERO_SOLDIERS, army.size());
		// the number of soldiers generated should be ZERO
		assertEquals(ZERO_SOLDIERS, nroSoldiersGenerated);
	}
}
