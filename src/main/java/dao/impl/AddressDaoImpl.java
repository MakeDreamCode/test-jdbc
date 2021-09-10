package dao.impl;

import dao.AddressDao;
import entity.Address;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressDaoImpl implements AddressDao {
    @Override
    public Address create(Address address) {
        String SQL_CREATE = "INSERT INTO ADDRESS (ID, COUNTRY, CITY, STREET, POST_CODE) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionUtil().getConnection();
             PreparedStatement createAddressStatement =
                     connection.prepareStatement(SQL_CREATE)) {
            createAddressStatement.setLong(1, address.getId());
            createAddressStatement.setString(2, address.getCountry());
            createAddressStatement.setString(3, address.getCity());
            createAddressStatement.setString(4, address.getStreet());
            createAddressStatement.setString(5, address.getPostCode());
            createAddressStatement.executeUpdate();
            return address;
        } catch (SQLException e) {
            throw new RuntimeException("Can`t create prepare statement!");
        }
    }

    @Override
    public Optional<Address> getById(Long id) {
        String SQL_GET_BY_ID = "SELECT * FROM ADDRESS WHERE ID = ?";
        try (Connection connection = new ConnectionUtil().getConnection();
             PreparedStatement getByIdAddressStatement = connection.prepareStatement(SQL_GET_BY_ID)) {
         getByIdAddressStatement.setLong(1, id);
         ResultSet resultSet = getByIdAddressStatement.executeQuery();
         Address address = null;
         if (resultSet.next()){
             address = parseResultSet(resultSet);
         }
        return Optional.ofNullable(address);
        } catch (SQLException e) {
            throw new RuntimeException("Can`t get address from DB by id:" + id);
        }
    }

    @Override
    public List<Address> getAll() {
        String SQL_GET_ALL = "SELECT * FROM ADDRESS";
        try (Connection connection = new ConnectionUtil().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);
            List<Address> addresses = new ArrayList<>();
            while (resultSet.next()) {
                addresses.add(parseResultSet(resultSet));
            }
            return addresses;
        } catch (SQLException e) {
            throw new RuntimeException("Can`t take all address form db!");
        }
    }

    private Address parseResultSet(ResultSet resultSet) {
        try {
            Long addressId = resultSet.getObject("ID", Long.class);
            String addressCountry = resultSet.getString("COUNTRY");
            String addressCity = resultSet.getString("CITY");
            String addressStreet = resultSet.getString("STREET");
            String addressPostCode = resultSet.getString("POST_CODE");
            return new Address(addressId, addressCountry, addressCity, addressStreet, addressPostCode);
        } catch (SQLException e) {
            throw new RuntimeException("Can`t parse ResultSet!");
        }
    }
}
