/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package laczenie_z_baza;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.client.am.Connection;
import org.apache.derby.client.am.PreparedStatement;
import org.apache.derby.client.am.ResultSet;
import org.apache.derby.client.am.Statement;
import osoba.Pearson;





/**
 *
 * @author Komputer
 */
public class DBkontroler {
    String url="jdbc:derby://localhost:1527/akademik";
    String driver = "org.apache.derby.jdbc.ClientDriver";
    static Statement statement;
    static Connection connection;

    public DBkontroler() {
    }
    
    public void insterCreate(String sql)
    {
        takeDriver();
        try{
            connect();
            statement.executeUpdate(sql);
            close();
        }
        catch(SQLException ex)
        {
            System.err.println("SQLException: "+ex.getMessage());
        }
    }
    
    public List printTable(String sql)
    {
        List<Pearson>listG = new ArrayList<>();
        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        int result = -1;
        
        try{
            takeDriver();
            connect();
            resultSet =  (ResultSet) prepStatement.executeQuery();
            while(resultSet.next())
            {
                Pearson gimnastyka = new Pearson();
                listG.add(gimnastyka);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();;
        } finally {
            if(prepStatement != null)
            {
                try{
                    prepStatement.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        try{
            connect();
            statement.executeUpdate(sql);
            close();
        }
        catch(SQLException ex)
        {
            System.err.println("SQLException: "+ex.getMessage());
        }
        return listG;
    }
    
    private void takeDriver()
    {
        try{
            Class.forName(driver);
        }
        catch(java.lang.ClassNotFoundException e)
        {
            Messenger.addErrorMessage(e, "Niestety nie powiodło się pobranie strownika bazy danych");
            System.err.println(e.getMessage());
        }
    }
    private void connect() throws SQLException
    {
        connection = (Connection) DriverManager.getConnection(url, "projekt", "Pogoda123456");
        statement = (Statement) connection.createStatement();
    }
    
    private void close() throws SQLException
    {
        statement.close();
        connection.close();
    }
}
