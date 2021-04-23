package kr.ac.jejunu.userdao;

public class DaoFactory {
    public UserDao getUserDao() {
        return new UserDao(connectionMaker());
    }
    public ConnectionMaker connectionMaker(){
        return new JejuConnectionMaker();
    }
}
