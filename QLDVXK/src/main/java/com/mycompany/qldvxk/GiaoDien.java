/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.qldvxk;
import com.mongodb.client.MongoCollection;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.JSONArray;
import org.json.JSONObject;
import org.bson.Document;
/**
 *
 * @author LOI
 */

public class GiaoDien extends javax.swing.JFrame {
    private JTable busTable, driverTable, routeTable, ticketTable;
    private JTextField busIDField, busPlateField, busModelField, busCapacityField;
    private JTextField driverIDField, driverNameField, driverPhoneField, driverAgeField, driverLicenseField;
    private JTextField routeIDField, routeStartField, routeEndField, routeDistanceField, routeTimeField;
    private JTextField ticketIDField, ticketBusIDField, ticketRouteIDField, ticketPassengerField, ticketItineraryField, ticketTimeField;
    private JTabbedPane tabbedPane;
    /**
     * Creates new form GiaoDien
     */
    public GiaoDien() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Quản lý dịch vụ xe khách");
    setSize(800, 600);
    setLocationRelativeTo(null);
    getContentPane().setLayout(new BorderLayout());

    // Header panel
    JPanel headerPanel = new JPanel();
    headerPanel.setLayout(new GridLayout(1, 2));

    JLabel welcomeLabel = new JLabel("QUẢN LÝ DỊCH VỤ XE KHÁCH", SwingConstants.CENTER);
    welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
    headerPanel.add(welcomeLabel);

    getContentPane().add(headerPanel, BorderLayout.NORTH);

    // Left panel with buttons
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new GridLayout(7, 1, 5, 5));
    leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    leftPanel.setPreferredSize(new Dimension(200, getHeight()));

    JButton btnHome = new JButton("Trang Chủ");
    JButton btnManageBuses = new JButton("Quản Lý Xe Khách");
    JButton btnManageDrivers = new JButton("Quản Lý Tài Xế");
    JButton btnManageRoutes = new JButton("Quản Lý Hành Trình");
    JButton btnManageTickets = new JButton("Quản Lý Vé");

    leftPanel.add(btnHome);
    leftPanel.add(btnManageBuses);
    leftPanel.add(btnManageDrivers);
    leftPanel.add(btnManageRoutes);
    leftPanel.add(btnManageTickets);

    getContentPane().add(leftPanel, BorderLayout.WEST);

    // Center panel with tabs
    tabbedPane = new JTabbedPane();
    tabbedPane.setPreferredSize(new Dimension(600, getHeight()));

    // Tab Trang Chủ
    JPanel homePanel = createHomePanel();
    tabbedPane.addTab("Trang Chủ", homePanel);

    // Tab Xe Khách
    JPanel busPanel = createBusPanel();
    tabbedPane.addTab("Xe Khách", busPanel);

    // Tab Tài Xế
    JPanel driverPanel = createDriverPanel();
    tabbedPane.addTab("Tài Xế", driverPanel);

    // Tab Hành Trình
    JPanel routePanel = createRoutePanel();
    tabbedPane.addTab("Hành Trình", routePanel);

    // Tab Vé
    JPanel ticketPanel = createTicketPanel();
    tabbedPane.addTab("Vé", ticketPanel);

    getContentPane().add(tabbedPane, BorderLayout.CENTER);

    // Footer panel with logout button
    JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
    JButton logoutButton = new JButton("Đăng Xuất");
    footerPanel.add(logoutButton);

    getContentPane().add(footerPanel, BorderLayout.SOUTH);

    logoutButton.addActionListener(e -> {
    // Đóng cửa sổ hiện tại
    this.dispose();

    // Mở cửa sổ đăng nhập
    DangNhap dangNhapFrame = new DangNhap();
    dangNhapFrame.setVisible(true);
});


    // Add action listeners to switch tabs
    btnHome.addActionListener(e -> tabbedPane.setSelectedIndex(0));
    btnManageBuses.addActionListener(e -> tabbedPane.setSelectedIndex(1));
    btnManageDrivers.addActionListener(e -> tabbedPane.setSelectedIndex(2));
    btnManageRoutes.addActionListener(e -> tabbedPane.setSelectedIndex(3));
    btnManageTickets.addActionListener(e -> tabbedPane.setSelectedIndex(4));

    // Add ChangeListener to load data when tabs are selected
    tabbedPane.addChangeListener(e -> {
        int selectedIndex = tabbedPane.getSelectedIndex();
        switch (selectedIndex) {
            case 1: // Tab Xe Khách
                loadBusData();
                break;
            case 2: // Tab Tài Xế
                loadDriverData();
                break;
            case 3: // Tab Hành Trình
                loadRouteData();
                break;
            case 4: // Tab Vé
                loadTicketData();
                break;
        }
    });
    
    // Initial load for bus data
    loadBusData(); // Load data for the first tab initially
}

    
    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel homeLabel = new JLabel("Chào mừng bạn đến với Hệ thống quản lý dịch vụ xe khách", SwingConstants.CENTER);
        // Thiết lập hình nền
        JLabel background = new JLabel(new ImageIcon("C:\\Users\\LOI\\Documents\\NetBeansProjects\\QLDVXK\\src\\main\\java\\com\\mycompany\\qldvxk\\xekhach1.jpeg"));
        background.setBounds(0, 0, getWidth(), getHeight());
        panel.add(background);
        homeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(homeLabel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createBusPanel() {
    JPanel panel = new JPanel(new BorderLayout());

    JPanel inputPanel = new JPanel(new BorderLayout());
    inputPanel.setBorder(BorderFactory.createTitledBorder("Nhập Xe Khách"));

    JPanel inputFieldsPanel = new JPanel(new GridLayout(4, 2, 5, 5));
    inputFieldsPanel.add(new JLabel("ID Xe Khách:"));
    busIDField = new JTextField();
    inputFieldsPanel.add(busIDField);

    inputFieldsPanel.add(new JLabel("Biển Số:"));
    busPlateField = new JTextField();
    inputFieldsPanel.add(busPlateField);

    inputFieldsPanel.add(new JLabel("Mẫu Xe:"));
    busModelField = new JTextField();
    inputFieldsPanel.add(busModelField);

    inputFieldsPanel.add(new JLabel("Sức Chứa:"));
    busCapacityField = new JTextField();
    inputFieldsPanel.add(busCapacityField);

    inputPanel.add(inputFieldsPanel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
    
    JButton addButton = new JButton("Thêm");
    addButton.addActionListener(e -> addBus());
    buttonPanel.add(addButton);

    JButton updateButton = new JButton("Sửa");
    updateButton.addActionListener(e -> updateBus());
    buttonPanel.add(updateButton);

    JButton deleteButton = new JButton("Xóa");
    deleteButton.addActionListener(e -> deleteBus());
    buttonPanel.add(deleteButton);

    JButton resetButton = new JButton("Reset");
    resetButton.addActionListener(e -> resetBusFields());
    buttonPanel.add(resetButton);
    
    JButton searchButton = new JButton("Tìm Kiếm");
    searchButton.addActionListener(e -> searchBus());
    buttonPanel.add(searchButton);

    inputPanel.add(buttonPanel, BorderLayout.SOUTH);

    panel.add(inputPanel, BorderLayout.NORTH);

    busTable = new JTable();
    loadBusData();
    panel.add(new JScrollPane(busTable), BorderLayout.CENTER);

    return panel;
}

private void addBus() {
    Connect connect = new Connect();
    MongoCollection<Document> collection = connect.getCollection("Buses");

    Document doc = new Document("bus_id", busIDField.getText())
                    .append("license_plate", busPlateField.getText())
                    .append("model", busModelField.getText())
                    .append("capacity", Integer.parseInt(busCapacityField.getText()));

    collection.insertOne(doc);
    loadBusData();
    connect.close();
}

private void updateBus() {
    Connect connect = new Connect();
    MongoCollection<Document> collection = connect.getCollection("Buses");

    Document query = new Document("bus_id", busIDField.getText());
    Document update = new Document("$set", new Document("license_plate", busPlateField.getText())
                                             .append("model", busModelField.getText())
                                             .append("capacity", Integer.parseInt(busCapacityField.getText())));

    collection.updateOne(query, update);
    loadBusData();
    connect.close();
}

private void deleteBus() {
    Connect connect = new Connect();
    MongoCollection<Document> collection = connect.getCollection("Buses");

    collection.deleteOne(new Document("bus_id", busIDField.getText()));
    loadBusData();
    connect.close();
}

private void resetBusFields() {
    busIDField.setText("");
    busPlateField.setText("");
    busModelField.setText("");
    busCapacityField.setText("");
}



    private JPanel createDriverPanel() {
    JPanel panel = new JPanel(new BorderLayout());

    JPanel inputPanel = new JPanel(new BorderLayout());
    inputPanel.setBorder(BorderFactory.createTitledBorder("Nhập Tài Xế"));

    JPanel inputFieldsPanel = new JPanel(new GridLayout(6, 2, 5, 5));
    inputFieldsPanel.add(new JLabel("ID Tài Xế:"));
    driverIDField = new JTextField();
    inputFieldsPanel.add(driverIDField);

    inputFieldsPanel.add(new JLabel("Tên Tài Xế:"));
    driverNameField = new JTextField();
    inputFieldsPanel.add(driverNameField);

    inputFieldsPanel.add(new JLabel("Số Điện Thoại:"));
    driverPhoneField = new JTextField();
    inputFieldsPanel.add(driverPhoneField);

    inputFieldsPanel.add(new JLabel("Tuổi:"));
    driverAgeField = new JTextField();
    inputFieldsPanel.add(driverAgeField);

    inputFieldsPanel.add(new JLabel("Giấy Phép Lái Xe:"));
    driverLicenseField = new JTextField();
    inputFieldsPanel.add(driverLicenseField);

    inputPanel.add(inputFieldsPanel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

    JButton addButton = new JButton("Thêm");
    addButton.addActionListener(e -> addDriver());
    buttonPanel.add(addButton);

    JButton updateButton = new JButton("Sửa");
    updateButton.addActionListener(e -> updateDriver());
    buttonPanel.add(updateButton);

    JButton deleteButton = new JButton("Xóa");
    deleteButton.addActionListener(e -> deleteDriver());
    buttonPanel.add(deleteButton);

    JButton resetButton = new JButton("Reset");
    resetButton.addActionListener(e -> resetDriverFields());
    buttonPanel.add(resetButton);
    
    JButton searchButton = new JButton("Tìm Kiếm");
    searchButton.addActionListener(e -> searchDriver());
    buttonPanel.add(searchButton);

    inputPanel.add(buttonPanel, BorderLayout.SOUTH);

    panel.add(inputPanel, BorderLayout.NORTH);

    driverTable = new JTable();
    loadDriverData();
    panel.add(new JScrollPane(driverTable), BorderLayout.CENTER);

    return panel;
}

private void addDriver() {
    Connect connect = new Connect();
    MongoCollection<Document> collection = connect.getCollection("Driver");

    Document doc = new Document("driver_id", driverIDField.getText())
                    .append("name", driverNameField.getText())
                    .append("phone_number", driverPhoneField.getText())
                    .append("age", Integer.parseInt(driverAgeField.getText()))
                    .append("license_number", driverLicenseField.getText());

    collection.insertOne(doc);
    loadDriverData();
    connect.close();
}

private void updateDriver() {
    Connect connect = new Connect();
    MongoCollection<Document> collection = connect.getCollection("Driver");

    Document query = new Document("driver_id", driverIDField.getText());
    Document update = new Document("$set", new Document("name", driverNameField.getText())
                                             .append("phone_number", driverPhoneField.getText())
                                             .append("age", Integer.parseInt(driverAgeField.getText()))
                                             .append("license_number", driverLicenseField.getText()));

    collection.updateOne(query, update);
    loadDriverData();
    connect.close();
}

private void deleteDriver() {
    Connect connect = new Connect();
    MongoCollection<Document> collection = connect.getCollection("Driver");

    collection.deleteOne(new Document("driver_id", driverIDField.getText()));
    loadDriverData();
    connect.close();
}

private void resetDriverFields() {
    driverIDField.setText("");
    driverNameField.setText("");
    driverPhoneField.setText("");
    driverAgeField.setText("");
    driverLicenseField.setText("");
}


    private JPanel createRoutePanel() {
    JPanel panel = new JPanel(new BorderLayout());

    JPanel inputPanel = new JPanel(new BorderLayout());
    inputPanel.setBorder(BorderFactory.createTitledBorder("Nhập Hành Trình"));

    JPanel inputFieldsPanel = new JPanel(new GridLayout(5, 2, 5, 5)); // Ensure there are 5 rows and 2 columns
    inputFieldsPanel.add(new JLabel("ID Hành Trình:"));
    routeIDField = new JTextField();
    inputFieldsPanel.add(routeIDField);

    inputFieldsPanel.add(new JLabel("Điểm Bắt Đầu:"));
    routeStartField = new JTextField();
    inputFieldsPanel.add(routeStartField);

    inputFieldsPanel.add(new JLabel("Điểm Kết Thúc:"));
    routeEndField = new JTextField();
    inputFieldsPanel.add(routeEndField);

    inputFieldsPanel.add(new JLabel("Khoảng Cách:"));
    routeDistanceField = new JTextField();
    inputFieldsPanel.add(routeDistanceField);

    inputFieldsPanel.add(new JLabel("Thời Gian:"));
    routeTimeField = new JTextField();
    inputFieldsPanel.add(routeTimeField);

    inputPanel.add(inputFieldsPanel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

    JButton addButton = new JButton("Thêm");
    addButton.addActionListener(e -> addRoute());
    buttonPanel.add(addButton);

    JButton updateButton = new JButton("Sửa");
    updateButton.addActionListener(e -> updateRoute());
    buttonPanel.add(updateButton);

    JButton deleteButton = new JButton("Xóa");
    deleteButton.addActionListener(e -> deleteRoute());
    buttonPanel.add(deleteButton);

    JButton resetButton = new JButton("Reset");
    resetButton.addActionListener(e -> resetRouteFields());
    buttonPanel.add(resetButton);
    
    JButton searchButton = new JButton("Tìm Kiếm");
    searchButton.addActionListener(e -> searchRoute());
    buttonPanel.add(searchButton);
    
    inputPanel.add(buttonPanel, BorderLayout.SOUTH);

    panel.add(inputPanel, BorderLayout.NORTH);

    routeTable = new JTable();
    loadRouteData();
    panel.add(new JScrollPane(routeTable), BorderLayout.CENTER);

    return panel;
}


private void addRoute() {
    Connect connect = new Connect();
    MongoCollection<Document> collection = connect.getCollection("Route");

    Document doc = new Document("route_id", routeIDField.getText())
                    .append("start_location", routeStartField.getText())
                    .append("end_location", routeEndField.getText())
                    .append("distance", Double.parseDouble(routeDistanceField.getText()))
                    .append("duration", routeTimeField.getText());

    collection.insertOne(doc);
    loadRouteData();
    connect.close();
}

private void updateRoute() {
    Connect connect = new Connect();
    MongoCollection<Document> collection = connect.getCollection("Route");

    Document query = new Document("route_id", routeIDField.getText());
    Document update = new Document("$set", new Document("start_location", routeStartField.getText())
                                             .append("end_location", routeEndField.getText())
                                             .append("distance", Double.parseDouble(routeDistanceField.getText()))
                                             .append("duration", routeTimeField.getText()));

    collection.updateOne(query, update);
    loadRouteData();
    connect.close();
}

private void deleteRoute() {
    Connect connect = new Connect();
    MongoCollection<Document> collection = connect.getCollection("Route");

    collection.deleteOne(new Document("route_id", routeIDField.getText()));
    loadRouteData();
    connect.close();
}

private void resetRouteFields() {
    routeIDField.setText("");
    routeStartField.setText("");
    routeEndField.setText("");
    routeDistanceField.setText("");
    routeTimeField.setText("");
}




    private JPanel createTicketPanel() {
    JPanel panel = new JPanel(new BorderLayout());

    JPanel inputPanel = new JPanel(new BorderLayout());
    inputPanel.setBorder(BorderFactory.createTitledBorder("Nhập Vé"));

    JPanel inputFieldsPanel = new JPanel(new GridLayout(6, 2, 5, 5));
    inputFieldsPanel.add(new JLabel("ID Vé:"));
    ticketIDField = new JTextField();
    inputFieldsPanel.add(ticketIDField);

    inputFieldsPanel.add(new JLabel("ID Xe Khách:"));
    ticketBusIDField = new JTextField();
    inputFieldsPanel.add(ticketBusIDField);

    inputFieldsPanel.add(new JLabel("ID Hành Trình:"));
    ticketRouteIDField = new JTextField();
    inputFieldsPanel.add(ticketRouteIDField);

    inputFieldsPanel.add(new JLabel("Tên Hành Khách:"));
    ticketPassengerField = new JTextField();
    inputFieldsPanel.add(ticketPassengerField);

    inputFieldsPanel.add(new JLabel("Điểm Đón:"));
    ticketItineraryField = new JTextField();
    inputFieldsPanel.add(ticketItineraryField);

    inputFieldsPanel.add(new JLabel("Thời Gian:"));
    ticketTimeField = new JTextField();
    inputFieldsPanel.add(ticketTimeField);

    inputPanel.add(inputFieldsPanel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

    JButton addButton = new JButton("Thêm");
    addButton.addActionListener(e -> addTicket());
    buttonPanel.add(addButton);

    JButton updateButton = new JButton("Sửa");
    updateButton.addActionListener(e -> updateTicket());
    buttonPanel.add(updateButton);

    JButton deleteButton = new JButton("Xóa");
    deleteButton.addActionListener(e -> deleteTicket());
    buttonPanel.add(deleteButton);

    JButton resetButton = new JButton("Reset");
    resetButton.addActionListener(e -> resetTicketFields());
    buttonPanel.add(resetButton);
    
    JButton searchButton = new JButton("Tìm Kiếm");
    searchButton.addActionListener(e -> searchTicket());
    buttonPanel.add(searchButton);

    inputPanel.add(buttonPanel, BorderLayout.SOUTH);

    panel.add(inputPanel, BorderLayout.NORTH);

    ticketTable = new JTable();
    loadTicketData();
    panel.add(new JScrollPane(ticketTable), BorderLayout.CENTER);

    return panel;
}


private void addTicket() {
    Connect connect = new Connect();
    MongoCollection<Document> collection = connect.getCollection("Ticket");

    Document doc = new Document("ticket_id", ticketIDField.getText())
                    .append("bus_id", ticketBusIDField.getText())
                    .append("route_id", ticketRouteIDField.getText())
                    .append("passenger_name", ticketPassengerField.getText()) // Sửa ở đây
                    .append("journey", ticketItineraryField.getText())
                    .append("time", ticketTimeField.getText());

    collection.insertOne(doc);
    loadTicketData();
    connect.close();
}


private void updateTicket() {
    Connect connect = new Connect();
    MongoCollection<Document> collection = connect.getCollection("Ticket");

    Document query = new Document("ticket_id", ticketIDField.getText());
    Document update = new Document("$set", new Document("passenger_name", ticketPassengerField.getText()) // Sửa ở đây
                                             .append("bus_id", ticketBusIDField.getText())
                                             .append("route_id", ticketRouteIDField.getText())
                                             .append("journey", ticketItineraryField.getText())
                                             .append("time", ticketTimeField.getText()));

    collection.updateOne(query, update);
    loadTicketData();
    connect.close();
}


private void deleteTicket() {
    Connect connect = new Connect();
    MongoCollection<Document> collection = connect.getCollection("Ticket");

    collection.deleteOne(new Document("ticket_id", ticketIDField.getText()));
    loadTicketData();
    connect.close();
}

private void resetTicketFields() {
    ticketIDField.setText("");
    ticketPassengerField.setText(""); // Thay ticketCustomerNameField bằng ticketPassengerField
    ticketBusIDField.setText("");
    ticketRouteIDField.setText("");
    ticketItineraryField.setText(""); // Thêm các trường khác nếu cần thiết
    ticketTimeField.setText(""); // Thêm các trường khác nếu cần thiết
}




    private void loadBusData() {
        Connect connect = new Connect();
        MongoCollection<Document> collection = connect.getCollection("Buses");

        try {
            DefaultTableModel busTableModel = new DefaultTableModel();
            busTableModel.addColumn("ID Xe Khách");
            busTableModel.addColumn("Biển Số");
            busTableModel.addColumn("Mẫu Xe");
            busTableModel.addColumn("Sức Chứa");

            for (Document doc : collection.find()) {
                busTableModel.addRow(new Object[]{
                    doc.getString("bus_id"),
                    doc.getString("license_plate"),
                    doc.getString("model"),
                    doc.getInteger("capacity")
                });
            }

            busTable.setModel(busTableModel);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connect.close();
        }
    }
    private void searchBus() {
    Connect connect = new Connect();
    MongoCollection<Document> collection = connect.getCollection("Buses");

    try {
        String searchQuery = busIDField.getText(); // Lấy từ khóa tìm kiếm từ trường ID xe khách

        Document query = new Document("bus_id", searchQuery);
        Document doc = collection.find(query).first();

        if (doc != null) {
            DefaultTableModel busTableModel = new DefaultTableModel();
            busTableModel.addColumn("ID Xe Khách");
            busTableModel.addColumn("Biển Số");
            busTableModel.addColumn("Mẫu Xe");
            busTableModel.addColumn("Sức Chứa");

            busTableModel.addRow(new Object[]{
                doc.getString("bus_id"),
                doc.getString("license_plate"),
                doc.getString("model"),
                doc.getInteger("capacity")
            });

            busTable.setModel(busTableModel);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        connect.close();
    }
}

private void searchDriver() {
    Connect connect = new Connect();
    MongoCollection<Document> collection = connect.getCollection("Driver");

    try {
        String searchQuery = driverIDField.getText(); // Lấy từ khóa tìm kiếm từ trường ID tài xế

        Document query = new Document("driver_id", searchQuery);
        Document doc = collection.find(query).first();

        if (doc != null) {
            DefaultTableModel driverTableModel = new DefaultTableModel();
            driverTableModel.addColumn("ID Tài Xế");
            driverTableModel.addColumn("Tên Tài Xế");
            driverTableModel.addColumn("Số Điện Thoại");
            driverTableModel.addColumn("Tuổi");
            driverTableModel.addColumn("Giấy Phép Lái Xe");

            driverTableModel.addRow(new Object[]{
                doc.getString("driver_id"),
                doc.getString("name"),
                doc.getString("phone_number"),
                doc.getInteger("age"),
                doc.getString("license_number")
            });

            driverTable.setModel(driverTableModel);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        connect.close();
    }
}

private void searchRoute() {
    Connect connect = new Connect();
    MongoCollection<Document> collection = connect.getCollection("Route");

    try {
        String searchQuery = routeIDField.getText(); // Lấy từ khóa tìm kiếm từ trường ID hành trình

        Document query = new Document("route_id", searchQuery);
        Document doc = collection.find(query).first();

        if (doc != null) {
            DefaultTableModel routeTableModel = new DefaultTableModel();
            routeTableModel.addColumn("ID Hành Trình");
            routeTableModel.addColumn("Điểm Bắt Đầu");
            routeTableModel.addColumn("Điểm Kết Thúc");
            routeTableModel.addColumn("Khoảng Cách");
            routeTableModel.addColumn("Thời Gian");

            Object distance = doc.get("distance");
            double distanceValue;

            if (distance instanceof Double) {
                distanceValue = (Double) distance;
            } else if (distance instanceof Integer) {
                distanceValue = ((Integer) distance).doubleValue();
            } else {
                distanceValue = 0.0; // or handle error
            }

            routeTableModel.addRow(new Object[]{
                doc.getString("route_id"),
                doc.getString("start_location"),
                doc.getString("end_location"),
                distanceValue,
                doc.getString("duration")
            });

            routeTable.setModel(routeTableModel);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        connect.close();
    }
}

private void searchTicket() {
    Connect connect = new Connect();
    MongoCollection<Document> collection = connect.getCollection("Ticket");

    try {
        String searchQuery = ticketIDField.getText(); // Lấy từ khóa tìm kiếm từ trường ID vé

        Document query = new Document("ticket_id", searchQuery);
        Document doc = collection.find(query).first();

        if (doc != null) {
            DefaultTableModel ticketTableModel = new DefaultTableModel();
            ticketTableModel.addColumn("ID Vé");
            ticketTableModel.addColumn("ID Xe Khách");
            ticketTableModel.addColumn("ID Hành Trình");
            ticketTableModel.addColumn("Tên Hành Khách");
            ticketTableModel.addColumn("Điểm Đón");
            ticketTableModel.addColumn("Thời Gian");

            ticketTableModel.addRow(new Object[]{
                doc.getString("ticket_id"),
                doc.getString("bus_id"),
                doc.getString("route_id"),
                doc.getString("passenger_name"),
                doc.getString("journey"),
                doc.getString("time")
            });

            ticketTable.setModel(ticketTableModel);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        connect.close();
    }
}


    private void loadDriverData() {
    Connect connect = new Connect();
    MongoCollection<Document> collection = connect.getCollection("Driver");

    try {
        DefaultTableModel driverTableModel = new DefaultTableModel();
        driverTableModel.addColumn("ID Tài Xế");
        driverTableModel.addColumn("Tên Tài Xế");
        driverTableModel.addColumn("Số Điện Thoại");
        driverTableModel.addColumn("Tuổi");
        driverTableModel.addColumn("Giấy Phép Lái Xe");

        for (Document doc : collection.find()) {
            System.out.println("Loading document: " + doc.toJson()); // Log the document
            driverTableModel.addRow(new Object[]{
                doc.getString("driver_id"),
                doc.getString("name"),
                doc.getString("phone_number"),
                doc.getInteger("age"),
                doc.getString("license_number")
            });
        }

        driverTable.setModel(driverTableModel);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        connect.close();
    }
}


    private void loadRouteData() {
    Connect connect = new Connect();
    MongoCollection<Document> collection = connect.getCollection("Route");

    try {
        DefaultTableModel routeTableModel = new DefaultTableModel();
        routeTableModel.addColumn("ID Hành Trình");
        routeTableModel.addColumn("Điểm Bắt Đầu");
        routeTableModel.addColumn("Điểm Kết Thúc");
        routeTableModel.addColumn("Khoảng Cách");
        routeTableModel.addColumn("Thời Gian");

        for (Document doc : collection.find()) {
            Object distance = doc.get("distance");
            double distanceValue;

            if (distance instanceof Double) {
                distanceValue = (Double) distance;
            } else if (distance instanceof Integer) {
                distanceValue = ((Integer) distance).doubleValue();
            } else {
                distanceValue = 0.0; // or handle error
            }

            routeTableModel.addRow(new Object[]{
                doc.getString("route_id"),
                doc.getString("start_location"),
                doc.getString("end_location"),
                distanceValue,
                doc.getString("duration")
            });
        }

        routeTable.setModel(routeTableModel);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        connect.close();
    }
}




    private void loadTicketData() {
        Connect connect = new Connect();
        MongoCollection<Document> collection = connect.getCollection("Ticket");

        try {
            DefaultTableModel ticketTableModel = new DefaultTableModel();
            ticketTableModel.addColumn("ID Vé");
            ticketTableModel.addColumn("ID Xe Khách");
            ticketTableModel.addColumn("ID Hành Trình");
            ticketTableModel.addColumn("Tên Hành Khách");
            ticketTableModel.addColumn("Điểm Đón");
            ticketTableModel.addColumn("Thời Gian");

            for (Document doc : collection.find()) {
                ticketTableModel.addRow(new Object[]{
                    doc.getString("ticket_id"),
                    doc.getString("bus_id"),
                    doc.getString("route_id"),
                    doc.getString("passenger_name"),
                    doc.getString("journey"),
                    doc.getString("time")
                });
            }

            ticketTable.setModel(ticketTableModel);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connect.close();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new GiaoDien().setVisible(true);
        });
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GiaoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiaoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiaoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiaoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GiaoDien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
