package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {
    private final Context context;

    public UserDao(Context context) {
        this.context = context;
    }


    public User get(Integer id) throws SQLException {
        String sql = "select * from userinfo where id = ?";
        Object[] params = new Object[]{id};
        return context.get(sql, params);
    }


    public void insert(User user) throws SQLException{
        String sql = "insert into userinfo (name, password) values (?, ?)";
        Object[] params = new Object[]{user.getName(), user.getPassword()};
        context.insert(user, sql, params, this);
    }


    public void update(User user) throws SQLException {
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        context.update(sql, params);
    }


    public void delete(Integer id) throws SQLException {
        String sql = "delete from userinfo where id = ?";
        Object[] params = new Object[]{id};
        context.delete(sql, params);
    }

}