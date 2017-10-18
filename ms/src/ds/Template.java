
package ds;

import java.util.Arrays;


public class Template {

	private TemplateField[] fields;

	public Template(TemplateField... fields) {
		this.fields = fields;
	}

	public int size() {
		return fields.length;
	}

	public boolean match(Tuple t) {
		if (size() != t.size()) {
			return false;
		}
		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].match(t.get(i))) {
				return false;
			}
		}
		return true;
	}

	public boolean match(int i, Object o) {
		return fields[i].match(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Arrays.hashCode(fields);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Template) {
			return Arrays.deepEquals(fields, ((Template) obj).fields);
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
		return Arrays.deepToString(fields);
	}

	public TemplateField get(int i) {
		return fields[i];
	}

	public boolean implies(Template t) {
		if (size() != t.size()) {
			return false;
		}

		return true;
	}
}
