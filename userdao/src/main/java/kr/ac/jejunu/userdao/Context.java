package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class Context {
    private final DataSource dataSource;

    public Context(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    User JdbcContextForGet(StatementStragety statementStragety) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();


            preparedStatement = statementStragety.makeStatement(connection);


            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } finally {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return user;
    }

    void JdbcContextForInsert(User user, StatementStragety statementStragety) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();


            preparedStatement = statementStragety.makeStatement(connection);

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            user.setId(resultSet.getInt(1));
        } finally {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    void JdbcContextForUpdate(StatementStragety statementStragety) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();


            preparedStatement = statementStragety.makeStatement(connection);

            preparedStatement.executeUpdate();

        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    void JdbcContextForDelete(StatementStragety statementStragety) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();

            preparedStatement = statementStragety.makeStatement(connection);

            preparedStatement.executeUpdate();

        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    User get(String sql, Object[] params) throws SQLException {
        return JdbcContextForGet(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql
            );
            for(int i = 0; i < params.length; i++){
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        });
    }

    void delete(String sql, Object[] params) throws SQLException {
        JdbcContextForDelete(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );
            for(int i = 0; i < params.length; i++){
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        });
    }

    void update(String sql, Object[] params) throws SQLException {
        JdbcContextForUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );
            for(int i = 0; i < params.length; i++){
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        });
    }

    void insert(User user, String sql, Object[] params, UserDao userDao) throws SQLException {
        JdbcContextForInsert(user, connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );
            for(int i = 0; i < params.length; i++){
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        });
    }
}