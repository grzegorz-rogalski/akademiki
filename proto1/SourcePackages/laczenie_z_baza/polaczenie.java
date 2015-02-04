/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package laczenie_z_baza;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import osoba.Pearson;


public class polaczenie {
    
    public static DBCollection getConnection(String dbName, String collectionName) throws UnknownHostException{
        MongoClient mongoClient = new MongoClient("ds041180.mongolab.com", 41180);
        DB db = mongoClient.getDB(dbName);
        DBCollection collection = db.getCollection(collectionName);
        return collection;
        
    }
    public String isertData(String dbName, String collectionName, Pearson osoba) throws UnknownHostException
    {
        DBCollection dbCollection = polaczenie.getConnection(dbName, collectionName);
        BasicDBObject document = new BasicDBObject();
        document.put("imie", osoba.getImie());
        document.put("nazwisko", osoba.getNazwisko());
        document.put("login", osoba.getLogin());
        document.put("haslo", osoba.getHaslo());
        document.put("mail", osoba.getMail());
        document.put("status", osoba.getStatus());
        document.put("numerPokoju", osoba.getNumerPokoju());
        dbCollection.insert(document);
        return null;
    }
   
}
