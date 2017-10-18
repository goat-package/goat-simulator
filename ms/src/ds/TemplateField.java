
package ds;

public interface TemplateField {

	public boolean match(Object o);

	public boolean implies(TemplateField f);

}
