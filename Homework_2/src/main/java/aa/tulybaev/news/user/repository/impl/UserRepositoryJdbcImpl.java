package aa.tulybaev.news.user.repository.impl;


import aa.tulybaev.news.user.entity.User;
import aa.tulybaev.news.user.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {

    //language=SQL
    private final String SQL_SELECT_ALL = "select id, username, password, email, age from account";

    //language=SQL
    private final String SQL_INSERT = "insert into account(username, password, email, age) values (?, ?, ?, ?);";

    //language=SQL
    private final String SQL_UPDATE = "UPDATE account SET username = ?, password = ?, email = ?, age = ? WHERE id = ?;";

    //language=SQL
    private final String SQL_SELECT_BY_ID = "SELECT id, username, password, email, age FROM account WHERE id = ?;";


    private final DataSource dataSource;

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getAge());

            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    user.setId(id);
                } else {
                    throw new SQLException("Cannot obtain id from user");
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<User> findAll() {

        List<User> users = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {

            while (resultSet.next()) {
                User user = new User(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getInt("age"));

                users.add(user);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        return users;
    }

    @Override
    public void update(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setInt(5, user.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("No user found with id: " + user.getId());
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    @Override
    public Optional<User> findById(Integer id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("email"),
                            resultSet.getInt("age")
                    );
                    return Optional.of(user);
                } else {
                    return Optional.empty();
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

}

