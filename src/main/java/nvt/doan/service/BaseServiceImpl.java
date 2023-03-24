package nvt.doan.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;
@Service
public abstract class BaseServiceImpl <E, ID extends Serializable> implements BaseService<E, Integer>  {
    private Class<E> classType;
    @Autowired
    private JpaRepository<E,Integer> jpaRepository;
    @Override
    public E save(E e) {
       return jpaRepository.save(e);
    }

    @Override
    public List<E> getAll() {
       return jpaRepository.findAll();
    }
    @Override
    public void deleteById(Integer id) {
        jpaRepository.deleteById(id);
    }
    @Override
    public E update(E e) {
      return  jpaRepository.save(e);
    }
}
