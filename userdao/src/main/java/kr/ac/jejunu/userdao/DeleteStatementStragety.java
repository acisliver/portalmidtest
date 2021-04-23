package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteStatementStragety implements StatementStragety {
    @Override
    public PreparedStatement makeStatement(Connection connection, Object object) throws SQLException {
        Integer id = (Integer) object;
        PreparedStatement preparedStatement = connection.prepareStatement(
                "delete from userinfo where id = ?",
                Statement.RETURN_GENERATED_KEYS
        );
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }
}
