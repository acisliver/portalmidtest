package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateStatementStragety implements StatementStragety {
    @Override
    public PreparedStatement makeStatement(Connection connection, Object object) throws SQLException {
        User user = (User) object;
        PreparedStatement preparedStatement = connection.prepareStatement(
                "update userinfo set name = ?, password = ? where id = ?",
                Statement.RETURN_GENERATED_KEYS
        );
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, user.getId());
        return preparedStatement;
    }
}
