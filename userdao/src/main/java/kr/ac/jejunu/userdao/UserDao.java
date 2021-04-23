package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final Context context;

    public UserDao(Context context) {
        this.context = context;
    }


    public User get(Integer id) throws SQLException {
        String sql = "select * from userinfo where id = ?";
        Object[] params = new Object[]{id};
        return context.JdbcContextForGet(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql
            );
            for(int i = 0; i < params.length; i++){
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        });
    }


    public void insert(User user) throws SQLException{
        String sql = "insert into userinfo (name, password) values (?, ?)";
        Object[] params = new Object[]{user.getName(), user.getPassword()};
        context.JdbcContextForInsert(user, connection -> {
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


    public void update(User user) throws SQLException {
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};

        context.JdbcContextForUpdate(connection -> {
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


    public void delete(Integer id) throws SQLException {
        Object[] params = new Object[]{id};

        context.JdbcContextForDelete(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from userinfo where id = ?",
                    Statement.RETURN_GENERATED_KEYS
            );
            for(int i = 0; i < params.length; i++){
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        });
    }

}