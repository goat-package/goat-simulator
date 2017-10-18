
package ds;

import java.util.Arrays;


public class Tuple {

	private Object[] data;

	public Tuple(Object... data) {
		this.data = data;
	}

	public boolean isInstance(int i, Class<?> clazz) {
		return clazz.isInstance(data[i]);
	}

	public <T> T get(int i, Class<T> clazz) {
		return clazz.cast(data[i]);
	}

	public int size() {
		return data.length;
	}

	public Object get(int i) {
		return data[i];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Arrays.hashCode(data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Tuple) {
			return Arrays.deepEquals(data, ((Tuple) obj).data);
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
		return Arrays.deepToString(data);
	}
}
