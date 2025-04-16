package services;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import models.Event;
import db.DBConnection;

// iport time 
// import java.text.SimpleDateFormat;
// import java.util.Date;

import exceptions.MessageBox;


public class EventService {
    public static ArrayList<Event> getAllEvents() {
        ArrayList<Event> events = new ArrayList<>();
        String sql = "SELECT e.event_name, e.event_id, e.location, e.date, c.category_name " +
        "FROM events e JOIN categories c ON e.category_id = c.category_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Event e = new Event(
                        rs.getInt("event_id"),
                        rs.getString("event_name"),
                        rs.getString("location"),
                        rs.getString("date"),
                        rs.getString("category_name")
                    );
                    events.add(e);
                }
                

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public static void registerUserForEvent(int userId, int eventId, String eventName) {
        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement regStmt = null;
        PreparedStatement notifStmt = null;
    
        try {
            conn = DBConnection.getConnection();
            Timestamp now = new Timestamp(System.currentTimeMillis());
    
            // 1. Check if the user is already registered
            String checkSql = "SELECT COUNT(*) FROM registrations WHERE attendee_id = ? AND event_id = ?";
            checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, userId);
            checkStmt.setInt(2, eventId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "You are already registered for this event.");
                return; // 🚫 Stop the registration
            }
    
            // 2. Insert into registrations
            String regSql = "INSERT INTO registrations (event_id, attendee_id, registration_time, status, created_at) VALUES (?, ?, ?, ?, ?)";
            regStmt = conn.prepareStatement(regSql);
            regStmt.setInt(1, eventId);
            regStmt.setInt(2, userId);
            regStmt.setTimestamp(3, now);
            regStmt.setString(4, "pending");
            regStmt.setTimestamp(5, now);
            regStmt.executeUpdate();
    
            // 3. Insert into notifications (✅ including event_id)
            String notifSql = "INSERT INTO notifications (user_id, event_id, message, sent_time, created_at, notification_type) VALUES (?, ?, ?, ?, ?, ?)";
            notifStmt = conn.prepareStatement(notifSql);
            notifStmt.setInt(1, userId);
            notifStmt.setInt(2, eventId);
            notifStmt.setString(3, "You are attending " + eventName + " — we are waiting for you.");
            notifStmt.setTimestamp(4, now);
            notifStmt.setTimestamp(5, now);
            notifStmt.setString(6, "register");
            notifStmt.executeUpdate();

    
            
            MessageBox.showSuccess("You are attending \"" + eventName + "\" — we're waiting for you.");

    
        } catch (SQLException ex) {
            ex.printStackTrace();
            MessageBox.showError("Error saving registration: " + ex.getMessage());

        } finally {
            try {
                if (checkStmt != null) checkStmt.close();
                if (regStmt != null) regStmt.close();
                if (notifStmt != null) notifStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    

}
