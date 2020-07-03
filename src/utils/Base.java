package utils;

import java.util.Collection;

public class Base {
    public static<E> void addNotNull(Collection<E> collection, E e) {
        if(e!= null){
            collection.add(e);
        }
    }
}
