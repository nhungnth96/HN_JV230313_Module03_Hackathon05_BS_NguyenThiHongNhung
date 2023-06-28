package ra.service;

import java.util.List;

public interface IService<T,E> {
    List<T> getAll();
    T findById(E e);;
    void save(T t);
    void delete(E e);
}
