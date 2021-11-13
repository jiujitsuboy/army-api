package com.goodgame.studio;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Army generator from the requested number
 * @author Jose
 *
 */
public class ArmyGenerator {

	private List<String> soldierTypes;

	/**
	 * Class constructor
	 * @param soldierTypes list of soldier types
	 */
	public ArmyGenerator(List<String> soldierTypes) {
		this.soldierTypes = soldierTypes;
	}	

	/**
	 * From a requested number of soldiers generate an army where the types of soldiers are calculated randomly using
	 * the soldierTypes list.
	 * 
	 * @param nroSoldiers Number of requested soldiers that the generated army should have in total.
	 * @return {@link Map}<{@link String},{@link Long}> type and number of soldiers generated.
	 */
	public Map<String, Long> generateArmy(int nroSoldiers) {

		Map<String, Long> army = new HashMap<String, Long>();

		if (this.soldierTypes != null && !this.soldierTypes.isEmpty()) {

			int accumulate = 0;
			long result = 0;

			Collections.shuffle(this.soldierTypes);

			long soldierTypeSubSet = ((nroSoldiers > soldierTypes.size()) ? (nroSoldiers) / soldierTypes.size() : 1);

			for (int index = 0; index < soldierTypes.size(); index++) {

				double variableRange = Math.floor(soldierTypeSubSet / (soldierTypes.size() - 1d));

				variableRange = (variableRange == 0 && (soldierTypeSubSet > 1 || nroSoldiers > soldierTypes.size())) ? 1
						: variableRange;


				long soldierTypeSubSetAdjustment = (long) Math.round(Math.random() * variableRange);
				boolean add = (Math.random() > 0.5);

				if (index + 1 == soldierTypes.size()) {
					result = (nroSoldiers - accumulate >= 0) ? nroSoldiers - accumulate : 0;
				} else {
					result = (add) ? soldierTypeSubSet + soldierTypeSubSetAdjustment
							: (Math.abs(soldierTypeSubSet - soldierTypeSubSetAdjustment) == 0) ? soldierTypeSubSet
									: Math.abs(soldierTypeSubSet - soldierTypeSubSetAdjustment);

					accumulate += result;
				}

				if (accumulate > nroSoldiers) {
					result = 0;
				}
				army.put(soldierTypes.get(index), result);
			}
		}

		return army;
	}

}
