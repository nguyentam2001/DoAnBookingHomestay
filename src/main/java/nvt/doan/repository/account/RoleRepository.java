package nvt.doan.repository.account;

import nvt.doan.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Override
    Page<Role> findAll(Pageable pageable);

    List<Role> findByRoleIdIsNot(Integer id);

}