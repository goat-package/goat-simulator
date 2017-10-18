/**
 * 
 */
package pm;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import org.apache.commons.math3.random.RandomGenerator;
import sim.Activity;
import sim.ModelI;
import util.WeightedElement;
import util.WeightedLinkedList;
import util.WeightedStructure;


public abstract class PopulationModel<S,T extends PopulationState<S>> implements ModelI {

	private LinkedList<PopulationRule<S,T>> rules;
	
	private double time;
	
	protected abstract T getCurrentState();
	
	protected abstract T copyState();
	
	protected abstract void setState(T newState);
	
	@Override
	public WeightedStructure<Activity> getActivities( RandomGenerator r ) {
		WeightedLinkedList<Activity> activities = new WeightedLinkedList<>();
		T currentState = getCurrentState();
		for (PopulationRule<S,T> rule : rules) {
			LinkedList<PopulationTransition<S,T>> enabled = rule.apply(r, currentState);
			for (PopulationTransition<S,T> tra : enabled) {
				activities.add( 
						new WeightedElement<Activity>(
							tra.getRate(), 
							tra
						) 
					);
			}
		}
		return activities;
	}

	@Override
	public void timeStep(double dt) {
		this.time += dt;
	}

	protected void apply(PopulationDrift<S> drift, Consumer<T> postTransitionAction) {
		T newState = copyState();
		drift.apply(newState);
		postTransitionAction.accept(newState);
		setState(newState);
	}
	
}
