package nvt.doan.service.account;

import nvt.doan.entities.Role;
import nvt.doan.repository.account.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    // Lấy danh sách tất cả role trừ CUSTOMER
    public List<Role> getRolesExceptCustomer(){
        return roleRepository.findByRoleIdIsNot(1);
    }

    // Lấy danh sách tất cả role và phân trang
    public Page<Role> getAllRoles(Pageable pageable){
        return roleRepository.findAll(pageable);
    }

    public List<Integer> getPageNumbers(int totalPages) {
        return IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
    }

}
