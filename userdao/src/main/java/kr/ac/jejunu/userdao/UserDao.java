package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final Context context;

    public UserDao(Context context) {
        this.context = context;
    }


    public User get(Integer id) throws SQLException {
        StatementStragety statementStragety = new GetStatementStragety(id);
        return context.JdbcContextForGet(statementStragety);
    }


    public void insert(User user) throws SQLException{
        StatementStragety statementStragety = new InsertStatementStragety(user);
        context.JdbcContextForInsert(user, statementStragety);

    }


    public void update(User user) throws SQLException {
        StatementStragety statementStragety = new UpdateStatementStragety(user);
        context.JdbcContextForUpdate(statementStragety);
    }


    public void delete(Integer id) throws SQLException {
        StatementStragety statementStragety = new DeleteStatementStragety(id);
        context.JdbcContextForDelete(statementStragety);
    }

}