
/**
 * 
 */
package sim;

import org.apache.commons.math3.random.RandomGenerator;


public class SequenceOfActivities implements Activity {

	@Override
	public String toString() {
		if (activities.length > 0) {
			return activities[0].toString();
		} else {
			return "...";
		}
	}

	private Activity[] activities;

	public SequenceOfActivities(Activity... activities) {
		this.activities = activities;
	}

	@Override
	public boolean execute(RandomGenerator r, double starting_time, double duration) {
		boolean result = true;
		for (Activity activity : activities) {
			result = activity.execute(r, starting_time, duration);
			if (!result) {
				return result;
			}
		}
		return result;
	}

	@Override
	public String getName() {
		String toReturn = "";
		for( int i=0 ; i<activities.length ; i++ ) {
			toReturn += activities[i].getName();
		}
		return toReturn;
	}

}
