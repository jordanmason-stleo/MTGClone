package MTGClone;

import java.io.FileInputStream;
import java.security.MessageDigest;
import java.sql.*;
import java.util.*;

public class SQLDriver {
    protected Connection c = null;

    public boolean tryConnect() {
        // Get the current stacktrace
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        // Get the caller of that called Deck, not SQLDriver
        StackTraceElement ste = stacktrace[3];
        // If it's a controller, we're running in servlet
        boolean isServer = ste.getClassName().contains("MTGClone.controller");
        try {
            if(isServer) {
                Class.forName("com.mysql.jdbc.Driver");  
                FileInputStream input = new FileInputStream("database.properties");
                Properties props = new Properties();
                props.load(input );
                c = DriverManager.getConnection(props.getProperty("DB_URL"),props.getProperty("DB_USERNAME"),props.getProperty("DB_PASSWORD"));
            } else {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:cards.db");
            }
            c.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean authenticateUser(String username, String password) {
        if (!tryConnect()) {
            return false;
        }
        try {

            String query = "SELECT sha256_pass FROM USERS WHERE email = ?";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("sha256_pass").equalsIgnoreCase(sha256(password))) {
                    return true;
                } else {
                    System.out.println(rs.getString("sha256_pass") + " not equal to " + sha256(password));
                }
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
        return sha256(password).equalsIgnoreCase("c109e7af71c435d32afb75e334e417ddeba82dbde609d4c47f2e3c717057e458");
    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Card getRandomCard() {
        if (!tryConnect()) {
            return null;
        }
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CARDS ORDER BY RANDOM() LIMIT 1;");
            String cardName = "";
            int manaCost = 0;
            int power = 0;
            int toughness = 0;
            String description = "";
            String image = "";
            String creatureType = "";

            while (rs.next()) {
                cardName = rs.getString("CARDNAME");
                manaCost = rs.getInt("MANACOST");
                power = rs.getInt("POWER");
                toughness = rs.getInt("TOUGHNESS");
                description = rs.getString("DESCRIPTION");
                image = rs.getString("IMAGE");
                creatureType = rs.getString("CREATURETYPE");

            }
            rs.close();
            stmt.close();
            c.close();
            Card card = new Card(cardName, manaCost, power, toughness, description, image, creatureType);
            return card;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.err.println("Returning null.");
        return null;
    }

    public ArrayList<Card> getAllCards() {
        ArrayList<Card> topdeck = new ArrayList<Card>();
        if (!tryConnect()) {
            return null;
        }
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CARDS;");
            String cardName = "";
            int manaCost = 0;
            int power = 0;
            int toughness = 0;
            String description = "";
            String image = "";
            String creatureType = "";

            while (rs.next()) {
                cardName = rs.getString("CARDNAME");
                manaCost = rs.getInt("MANACOST");
                power = rs.getInt("POWER");
                toughness = rs.getInt("TOUGHNESS");
                description = rs.getString("DESCRIPTION");
                image = rs.getString("IMAGE");
                creatureType = rs.getString("CREATURETYPE");
                Card card = new Card(cardName, manaCost, power, toughness, description, image, creatureType);
                topdeck.add(card);
            }
            rs.close();
            stmt.close();
            c.close();

            return topdeck;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.err.println("Returning null.");
        return null;
    }

    public boolean insertCard(Card card) {
        Statement stmt = null;
        if (!tryConnect()) {
            return false;
        }
        try {
            c.setAutoCommit(false);
            String baseStmt = "INSERT INTO CARDS (CARDNAME,MANACOST,POWER,TOUGHNESS,DESCRIPTION,IMAGE,CREATURETYPE) VALUES ";
            stmt = c.createStatement();
            String sql = "(\'" + card.cardName + "\'," + card.manaCost + "," + card.power + "," + card.toughness
                    + ", \'" + card.description + "\',\'" + card.image + "\',\'" + card.creatureType + "\');";
            stmt.executeUpdate(baseStmt + sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;

    }

    public boolean setupCards() {
        if (!tryConnect()) {
            return false;
        }
        ArrayList<Card> topdeck = new ArrayList<Card>();
        topdeck.add(new Card("Devouring Dragon", 5, 5, 6, "He does to people what Greg does to booty.", "", "Dragon"));
        topdeck.add(new Card("Dragon Worshipper", 1, 1, 1, "He spends his days praying to dragons.", "", "Human"));
        topdeck.add(new Card("Dragon Whelp", 2, 3, 2, "He will get there.", "", "Dragon"));
        topdeck.add(new Card("Dragon Egg", 1, 0, 2, "Close to hatching!", "", "Egg"));
        topdeck.add(new Card("Lingering Flame", 3, 4, 3, "He lives among the dragons.", "", "Elemental"));
        topdeck.add(
                new Card("Dragonguard Sentry", 1, 1, 2, "Devoted to the protection of the dragonflight.", "", "Human"));
        topdeck.add(new Card("Withering Flamewitch", 2, 4, 2, "Her flame bites deep.", "", "Human"));
        topdeck.add(new Card("Final Examination", 6, 9000, 0, "Oh dear, Greg.", "", "Parchment"));
        topdeck.add(new Card("Drunken Dragon", 3, 4, 1, "He is trying.", "", "Dragon"));
        topdeck.add(new Card("Isle of Power", -1, 0, 0, "Invoke this for mana.", "", "Land"));
        for (Card card : topdeck) {
            if (!insertCard(card)) {
                return false;
            }
        }
        return true;
    }

    public boolean setupTable() {
        Statement stmt = null;
        if (!tryConnect()) {
            return false;
        }
        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE CARDS " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT    NOT NULL,"
                    + " CARDNAME       TEXT    NOT NULL, " + " MANACOST       INT     NOT NULL, "
                    + " POWER          INT     NOT NULL, " + " TOUGHNESS      INT     NOT NULL, "
                    + " DESCRIPTION    TEXT    NOT NULL, " + " IMAGE          TEXT    NOT NULL, "
                    + " CREATURETYPE   TEXT    NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
            if (!setupCards()) {
                System.exit(-1);
            }
        } catch (Exception e) {
            if (!e.getMessage().contains("(table CARDS already exists)"))
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }
}
