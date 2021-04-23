package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final Context context;

    public UserDao(Context context) {
        this.context = context;
    }


    public User get(Integer id) throws SQLException {
        return context.JdbcContextForGet(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from userinfo where id = ?"
            );
            preparedStatement.setLong(1, id);
            return preparedStatement;
        });
    }


    public void insert(User user) throws SQLException{
        context.JdbcContextForInsert(user, connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into userinfo (name, password) values (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            return preparedStatement;
        });

    }


    public void update(User user) throws SQLException {

        context.JdbcContextForUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update userinfo set name = ?, password = ? where id = ?",
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());
            return preparedStatement;
        });
    }


    public void delete(Integer id) throws SQLException {
        context.JdbcContextForDelete(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from userinfo where id = ?",
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setInt(1, id);
            return preparedStatement;
        });
    }

}