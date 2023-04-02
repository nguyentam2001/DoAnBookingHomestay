package nvt.doan.service;

import nvt.doan.entities.Address;
import nvt.doan.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("addressServiceImpl")
public class AddressServiceImpl extends BaseServiceImpl<Address,Integer> implements AddressService  {
   @Autowired
   AddressRepository addressRepository;
    @Override
    public Address getAddressById(Integer addressId) {
        return addressRepository.getReferenceById(addressId);
    }
}
