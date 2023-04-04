package ohm.softa.a04;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

public interface SimpleList<T> extends Iterable<T> {
    /**
     * Add a given object to the back of the list.
     */
    void add(T o);

    /**
     * @return current size of the list
     */
    int size();

    void delete(T item);

    SimpleList<T> copy();

    /**
     * Generate a new list using the given filter instance.
     *
     * @return a new, filtered list
     */
    default SimpleList<T> filter(SimpleFilter<T> filter) {
        SimpleList<T> list = new SimpleListImpl<>();
        for (T item : this) {
            if (filter.include(item)) {
                list.add(item);
            }
        }
        return list;
    }

    default <R> SimpleList<R> map(Function<T, R> transform) {
        SimpleList<R> newList = new SimpleListImpl<>();
        for(T item : this) {
            newList.add(transform.apply(item));
        }
        return newList;
    }

    default void addDefault(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T item = clazz.getDeclaredConstructor().newInstance();
        this.add(item);
    }

    default T getItem(int index) {
        int count = 0;
        for (T item : this) {
            if(count == index) {
                return item;
            }
            count++;
        }
        return null;
    }
}
