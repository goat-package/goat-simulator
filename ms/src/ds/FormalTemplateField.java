
package ds;


public class FormalTemplateField implements TemplateField {

	protected Class<?> clazz;

	public FormalTemplateField(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Override
	public boolean match(Object o) {
		return clazz.isInstance(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return clazz.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FormalTemplateField) {
			return this.clazz == ((FormalTemplateField) obj).clazz;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "?{" + clazz.toString() + "}";
	}

	@Override
	public boolean implies(TemplateField f) {
		if (f instanceof FormalTemplateField) {
			return ((FormalTemplateField) f).clazz.isAssignableFrom(this.clazz);
		}
		return false;
	}

}
