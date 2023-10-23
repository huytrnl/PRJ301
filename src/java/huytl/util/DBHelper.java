/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytl.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author huytr
 */
public class DBHelper implements Serializable {
    public static Connection createConnection()
        throws /*ClassNotFoundException*/NamingException, SQLException {
//        //1. Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create connection String url
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=SE1708MVC2;"; // (instanceName)
//        //3. Open connection
//        Connection con = DriverManager.getConnection(url,"sa","123456");
//        return con;
        //1. GET CURRENT CONTEXT;
        Context currentContext = new InitialContext();
        //2. Look up our datasource
        Context tomcatContext = (Context)currentContext.lookup("java:comp/env");
        //3. Look up our reference
        DataSource ds = (DataSource) tomcatContext.lookup("SE1708DS");
        //4. Open 
        Connection con = ds.getConnection();
        return con;
    }
}
