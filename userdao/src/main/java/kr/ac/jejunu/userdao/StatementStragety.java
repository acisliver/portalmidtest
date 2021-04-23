package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStragety {
    PreparedStatement makeStatement(Connection connection, Object object) throws SQLException;
}
