
/**
 * 
 */
package util;

import java.util.LinkedList;
import java.util.List;


public class WeightedElement<S> implements WeightedStructure<S> {

	private double w;
	private S s;

	public WeightedElement(double w, S s) {
		this.w = w;
		this.s = s;
	}

	public double getWeight() {
		return w;
	}

	public S getElement() {
		return s;
	}

	public WeightedElement<S> residual(double w) {
		return new WeightedElement<S>(this.w - w, s);
	}

	@Override
	public double getTotalWeight() {
		return w;
	}

	@Override
	public WeightedElement<S> select(double w) {
		if (w <= this.w) {
			return this;
		}
		return null;
	}

	@Override
	public WeightedStructure<S> add(double w, S s) {
		WeightedLinkedList<S> list = new WeightedLinkedList<S>();
		list.add(this);
		list.add(w, s);
		return null;
	}

	@Override
	public WeightedStructure<S> add(WeightedStructure<S> s) {
		return new ComposedWeightedStructure<S>(this, s);
	}

	@Override
	public String toString() {
		return s+":"+w;
	}

	@Override
	public List<WeightedElement<S>> getAll() {
		LinkedList<WeightedElement<S>> list = new LinkedList<>();
		list.add(this);
		return list;
	}


}
