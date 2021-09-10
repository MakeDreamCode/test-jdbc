package dao;

import entity.Address;
import java.util.List;
import java.util.Optional;

public interface AddressDao {
    Address create(Address address);

    Optional<Address> getById(Long id);

    List<Address> getAll();
}
