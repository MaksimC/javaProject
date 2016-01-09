package MemoryTest;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.sql.*;
import java.util.*;

/**
 * Created by emaktse on 09.01.2016.
 */

public class Database {

    Connection conn = null;

    // Constructor - method which start immediately after this object is called
    public Database() {
        looYhendus();
        looTabel();
    }

    // Et andmebaasi kasutada peame sellega esiteks ühenduse looma
    private void looYhendus() {
        try {
            Class.forName("org.sqlite.JDBC");                          // Lae draiver sqlite.jar failist
            conn = DriverManager.getConnection("jdbc:sqlite:test.db"); // loo ühendus andmebaasi failiga
        } catch (Exception e) {                                      // püüa kinni võimalikud errorid
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Opened database successfully");            // lihtsalt meie enda jaoks teade
    }

    // Et andmebaasist kasu oleks, loome uue tabeli. See on nagu uus 'sheet' excelis.
    public void looTabel() {
        // Käsk ise on CREATE TABLE ja sulgude vahel on kõik tulbad, mida tahan, et tabel hoiaks.
        String sql = "CREATE TABLE IF NOT EXISTS USER (ID INT AUTO_INCREMENT, NAME TEXT, AGE INT, RESULT INT);";
        teostaAndmebaasiMuudatus(sql);
    }

    // Andmebaasi muudatused ei tagasta väärtusi (erinevalt
    // päringutest) ja on lihtne eraldi meetodi tuua.
    private void teostaAndmebaasiMuudatus(String sql) {
        try {
            // Statement objekt on vajalik, et SQL_Login käsku käivitada
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
            stat.close(); // Statement tuleb samuti kinni panna nagu ka Connection.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUserToDB(String name, int age) {
        // Andmete sisestamiseks on käsk INSERT. Esimestes sulgudes on tulabad KUHU salvestada,
        // teistes sulgudes VALUES() on MIDA salvestada.
        String sql = "INSERT INTO USER (NAME, AGE) VALUES ('" + name + "','" + age + "')";
        teostaAndmebaasiMuudatus(sql);
    }


    // Kui programmis avad ainult ühendusi ja ühtegi ei sulge siis see kulutab arvuti (serveri) ressursse.
    // Üsna kiiresti võib masina kokku jooksutada.
    public void sulgeYhendus() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Ühendus suletud");
    }

    public void uuendaKasutajaAndmeid(String name, int result) {

        try {
            Statement stat = conn.createStatement();
            // Andmete uuendamise käsi on UPDATE. SET ütleb, et nüüd tulevad need uued andmed sisse. WHERE ütleb mis ridu uuendada.
            String sql = String.format("UPDATE USER SET RESULT = '%s' WHERE NAME = '%s';", result, name);
            stat.executeUpdate(sql);
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    // Kasutaja andmete päring.
    public HashMap extractDBtoHashMap() {
        HashMap<String, Integer> allUsersHashMap = new HashMap<String, Integer>();
        try {
            Statement stat = conn.createStatement();
            String sql = "SELECT * FROM USER"; // LIMIT piirab tulemuste arvu.

            ResultSet resultSet = stat.executeQuery(sql);

            // Extract database into HashMap in cycle
            while (resultSet.next()) {
                allUsersHashMap.put(resultSet.getString("NAME"), resultSet.getInt("RESULT"));
            }

            resultSet.close();
            stat.close();
            System.out.println(allUsersHashMap);
            return allUsersHashMap;
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return allUsersHashMap;
    }

}
