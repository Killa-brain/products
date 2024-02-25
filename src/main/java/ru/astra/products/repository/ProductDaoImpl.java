package ru.astra.products.repository;

import org.springframework.stereotype.Repository;
import ru.astra.products.domain.Product;
import ru.astra.products.domain.ProductType;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDaoImpl implements ProductDao {

    public static final String SELECT_PRODUCTS = "SELECT * FROM product";
    public static final String WHERE_ID = " WHERE id = ?";
    public static final String WHERE_USER_ID = " WHERE user_id = ?";
    public static final String SAVE_PRODUCT = "INSERT INTO product " +
            "(user_id, account_number, balance, product_type) " +
            "VALUES (?, ?, ?, ?)";
    public static final String UPDATE_PRODUCT = "UPDATE product " +
            "SET account_number = ?, balance = ?, product_type = ? WHERE id = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE id = ?";
    private DataSource dataSource;

    public ProductDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Product> findById(Long id) {
        var query = SELECT_PRODUCTS + WHERE_ID;
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.ofNullable(mapToProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Product> findAllByUserId(Long userId) {
        var query = SELECT_PRODUCTS + WHERE_USER_ID;
        return getProductList(query, userId);
    }

    @Override
    public List<Product> findAll() {
        return getProductList(SELECT_PRODUCTS, null);
    }

    @Override
    public Product save(Product product) {
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(SAVE_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, product.getUserId());
            preparedStatement.setInt(2, product.getAccountNumber());
            preparedStatement.setBigDecimal(3, product.getBalance());
            preparedStatement.setString(4, product.getProductType().getValue());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long id = generatedKeys.getLong(1);
                product.setId(id);
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Product product) {
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(UPDATE_PRODUCT)) {
            preparedStatement.setInt(1, product.getAccountNumber());
            preparedStatement.setBigDecimal(2, product.getBalance());
            preparedStatement.setString(3, product.getProductType().getValue());
            preparedStatement.setLong(4, product.getId());
            return preparedStatement.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeById(Long id) {
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(DELETE_PRODUCT)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Product mapToProduct(ResultSet rs) throws SQLException {
        return new Product(rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getInt("account_number"),
                rs.getBigDecimal("balance"),
                ProductType.getByValue(rs.getString("product_type")));
    }

    private List<Product> getProductList(String query, Long userId) {
        List<Product> products = new ArrayList<>();
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(query)) {
            if (userId != null) {
                preparedStatement.setLong(1, userId);
            }
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                var product = mapToProduct(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
