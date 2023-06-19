package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
        private static ConnectionPool pool;
        private List<Connection> freeCon;
        private List<Connection> busyCon;
        private int size;

        private ConnectionPool() throws SQLException {
            freeCon = new ArrayList<>(size);
            busyCon = new ArrayList<>();
            size = 5;
            for(int i=0; i<size; i++){
                freeCon.add(DriverManager.getConnection("jdbc:mysql://localhost:3306/order_accounting_system", "root", "password"));
            }
        }

        public synchronized static ConnectionPool getInstance() throws SQLException {
            if(pool==null) pool = new ConnectionPool();
            return pool;
        }

        public Connection getConnection() throws SQLException {
            checkFreeConnection();
            Connection connection = freeCon.remove(freeCon.size()-1);
            busyCon.add(connection);
            return connection;
        }

        public void resiveConnection(Connection connection) throws SQLException {
            busyCon.remove(connection);
            freeCon.add(connection);
            deleteConnection();
        }

        private void checkFreeConnection() throws SQLException {
            if(freeCon.size()<2) {
                for (int i=1; i<size; i++){
                    freeCon.add(DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db", "root", "root"));
                }
            }
        }

        private void deleteConnection() throws SQLException {
            if(freeCon.size()>size){
                int iter = freeCon.size()-size;
                for(int i=0; i<iter; i++){
                    freeCon.remove(i).close();
                }
            }
        }

        public int getSizeFreeCon(){
            return freeCon.size();
        }
}
