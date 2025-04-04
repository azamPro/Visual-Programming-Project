package auth;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import db.DBConnection;

public class EventsPanel extends JPanel {

    private JTable eventsTable;
    private DefaultTableModel tableModel;

    public EventsPanel() {
        // Set a preferred size so the panel is big enough for all columns
        setPreferredSize(new Dimension(1200, 700)); 
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Title label at the top
        JLabel titleLabel = new JLabel("Events Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Create table model with column names
        String[] columnNames = {"Name", "Date", "Location", "Description", "Total Seats", "Availability"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };

        // Create the table and apply the model
        eventsTable = new JTable(tableModel);
        eventsTable.setRowHeight(60); // Increase row height for multi-line description
        eventsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        eventsTable.setFillsViewportHeight(true);

        // Automatically resize columns to fit the available width (no horizontal scroll)
        eventsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Customize table header
        JTableHeader header = eventsTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(100, 100, 100));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        // Center-align non-description columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        // Apply renderer to every column except "Description" (index 3)
        for (int i = 0; i < eventsTable.getColumnCount(); i++) {
            if (i != 3) {
                eventsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        // Use a custom renderer for the "Description" column to allow multi-line text
        eventsTable.getColumnModel().getColumn(3).setCellRenderer(new TextAreaRenderer());

        // Populate the table with data from the database
        loadEventsData();

        // Put the table in a scroll pane with custom border
        JScrollPane scrollPane = new JScrollPane(eventsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadEventsData() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT event_name, date, location, description, total_seats, availablity FROM events";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            while (rs.next()) {
                String name = rs.getString("event_name");
                Timestamp dateTS = rs.getTimestamp("date");
                String dateStr = (dateTS != null) ? sdf.format(dateTS) : "";
                String location = rs.getString("location");
                String description = rs.getString("description");
                int totalSeats = rs.getInt("total_seats");
                boolean available = rs.getBoolean("availablity");
                String availableStr = available ? "Yes" : "No";

                tableModel.addRow(new Object[] {
                    name,
                    dateStr,
                    location,
                    description,
                    totalSeats,
                    availableStr
                });
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error loading events from database:\n" + ex.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Custom renderer for multi-line text in the Description column.
     */
    private static class TextAreaRenderer extends JTextArea implements TableCellRenderer {
        public TextAreaRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
            setFont(new Font("Arial", Font.PLAIN, 14));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            setText(value != null ? value.toString() : "");
            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);

            // Apply selection colors if needed
            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }

            return this;
        }
    }
}

