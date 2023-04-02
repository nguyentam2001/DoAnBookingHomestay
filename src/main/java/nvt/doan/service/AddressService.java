package nvt.doan.service;

import nvt.doan.entities.Address;
import org.hibernate.hql.internal.ast.tree.IdentNode;

public interface AddressService extends  BaseService<Address, Integer>{

    public Address getAddressById(Integer addressId);
}
