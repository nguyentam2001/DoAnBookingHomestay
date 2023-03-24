package nvt.doan.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E, ID extends Serializable> {
    E save (E e);
    List<E> getAll();
    void deleteById(ID id);
    E update(E e);
}
