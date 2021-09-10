import dao.AddressDao;
import dao.impl.AddressDaoImpl;
import entity.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {

        LOGGER.info("Create object address1");
        Address address1 = new Address();
        address1.setId(1L);
        address1.setCountry("England");
        address1.setCity("London");
        address1.setStreet("Baker-street");
        address1.setPostCode("52204");

        Address address2 = new Address();
        address2.setId(2L);
        address2.setCountry("USA");
        address2.setCity("New York");
        address2.setStreet("Wall-street");
        address2.setPostCode("32205");


        LOGGER.info("Write object to DB");
        AddressDao addressDao = new AddressDaoImpl();

        addressDao.create(address1);
        System.out.println("Object address1 was created!");

        addressDao.create(address2);
        System.out.println("Object address2 was created!");

        List<Address> addresses = addressDao.getAll();
        System.out.println("Object List of address was created!");
        for (Address address : addresses) {
            System.out.println(address.toString());
        }

        System.out.println("Getting address with id=3");
        System.out.println(addressDao.getById(3L));
    }
}
