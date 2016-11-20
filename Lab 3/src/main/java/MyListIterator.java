/**
 * Created by S. Stefani on 2016-11-20.
 */
public interface MyListIterator {

    Object next();

    boolean hasNext();

    void add(Object data);

    void remove();

    void set(Object data);
}
