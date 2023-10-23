/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytl.registration;

import huytl.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author huytr
 */
public class RegistrationDAO implements Serializable {
    
    public boolean checkLogin(String username, String password)
        throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        //nhung doi tuong cua jdbc phai khai bao, dong lai, sau do moi 
        try {
        
            //1. Create Connection
            con = DBHelper.createConnection();
            if (con!=null){
                //2. Create SQL String
                String sql = "Select username "
                    + "From Registration "
                    + "Where username = ? "
                    + "And password = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Excute Query
                rs=stm.executeQuery();
                if (rs.next()){
                    result = true;
                }
                //5. Process
            }//end connection is available
        } catch (NamingException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null){
                rs.close();
            }
            if (stm != null){
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }
        return result;
    }
    
    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }
    
    public boolean searchLastname(String searchValue)
        throws SQLException, ClassNotFoundException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        //nhung doi tuong cua jdbc phai khai bao, dong lai, sau do moi 
        try {
        
            //1. Create Connection
            con = DBHelper.createConnection();
            if (con!=null){
                //2. Create SQL String
                String sql = "Select username, password, lastname, isAdmin "
                    + "From Registration "
                    + "Where lastname Like ? ";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Excute Query
                rs=stm.executeQuery();
                while (rs.next()){
                    //5.1 get data from rs
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 get data into DTO
                    RegistrationDTO dto = new RegistrationDTO(username,
                        password, fullName, role);
                    //5.3 add DTO into List
                    if (this.accounts == null){
                        this.accounts = new ArrayList<>();
                    }//end accounts has not existed
                    this.accounts.add(dto);
                }//end traverse rs to get data
                //5. Process
            }//end connection is available
        } catch (NamingException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null){
                rs.close();
            }
            if (stm != null){
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }
        return result;
    }
    
    public boolean deleteAccount(String username) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        //nhung doi tuong cua jdbc phai khai bao, dong lai, sau do moi 
        try {
        
            //1. Create Connection
            con = DBHelper.createConnection();
            if (con!=null){
                //2. Create SQL String
                String sql = "Delete "
                    + "From Registration "
                    + "Where username = ? ";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                
                int effectRows = stm.executeUpdate();
                //5. Process
                if (effectRows > 0) {
                    result = true;
                }
            }//end connection is available
        } finally {
            if (stm != null){
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }
        return result;
    }
    public boolean updateAccount(String username, String password, String isAdmin) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        //nhung doi tuong cua jdbc phai khai bao, dong lai, sau do moi 
        try {
        
            //1. Create Connection
            con = DBHelper.createConnection();
            if (con!=null){
                //2. Create SQL String
                String sql = "Update Registration "
                    + "Set password = ?,"
                        + "isAdmin = ? "
                    + "Where username = ? ";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, isAdmin);
                stm.setString(3, username);
                
                int effectRows = stm.executeUpdate();
                //5. Process
                if (effectRows > 0) {
                    result = true;
                }
            }//end connection is available
        } finally {
            if (stm != null){
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }
        return result;
    }
}
