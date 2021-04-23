package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteStatementStragety implements StatementStragety {
    Integer id;

    public DeleteStatementStragety(Integer id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "delete from userinfo where id = ?",
                Statement.RETURN_GENERATED_KEYS
        );
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }
}
