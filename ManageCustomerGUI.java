import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.*;

import java.awt.*;
import java.util.List;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionListener;

import java.util.*;

public class ManageCustomerGUI extends JFrame {
    // Instance Variables/Attributes

    private DefaultTableModel model;
    private JTable otable;
    private TableRowSorter sort;
    private JScrollPane pane;

    private JFrame frame;
    private JPanel background;
    private JPanel table;
    private JPanel addOrder;

    private JLabel cust_fname;
    private JLabel cust_lnamelbl;
    private JLabel address;
    private JLabel dob;
    private JLabel errormsg;

    private JButton addButton;
    private JButton deleteButton;
    private JButton saveButton;
    private JButton sortButton;

    private JTextField c_fname;
    private JTextField c_lname;
    private JTextField cont;
    private JTextField add;
    private JTextField doB;
    private JLabel logo;
    private ManageCustomer customer_manager;
    private List<Customer> manage_customerqueue = new ArrayList<Customer>();

    public ManageCustomerGUI() {
        this.frame = this;
        // Font and Defaults
        customer_manager = new ManageCustomer();

        Font f = new Font("Montserrat", Font.BOLD, 28);
        frame.setTitle("SS Colecao - Manage Customers");

        // Table Setup
        manage_customerqueue = ManageCustomer.getAllCustomers();
        String[] columnNames = { "Last Name", "First Name", "Address" };

        this.model = new DefaultTableModel(columnNames, 0);
        otable = new JTable(model);
        sort = new TableRowSorter<>(model);
        showTable((List<Customer>) manage_customerqueue);
        // otable.setRowSorter(sort);
        otable.setBounds(20, 30, 450, 450);
        pane = new JScrollPane(otable);
        pane.setViewportView(otable);

        // Content
        // Order Table Display
        table = new JPanel();
        table.setBackground(Color.GRAY);
        table.add(pane);

        // Add Order Pane
        JPanel infoPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        JPanel cinfoPanel = new JPanel(new GridLayout(7, 4, 30, 10));
        addOrder = new JPanel(new BorderLayout());

        // Logo
        ImageIcon logo_img = new ImageIcon("Ologo.png");
        Image img = logo_img.getImage();
        Image temp_img = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        logo_img = new ImageIcon(temp_img);
        logo = new JLabel(logo_img);
        logo.setBounds(0, 0, 100, 100);
        addOrder.add(logo);

        // Button
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ButtonListener());
        addButton = new JButton("Add Customer");
        addButton.addActionListener(new ButtonListener());
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ButtonListener());
        sortButton = new JButton("Sort");
        sortButton.addActionListener(new ButtonListener());

        // Add Order
        JLabel addl = new JLabel("MANAGE CUSTOMER                     ");
        addl.setFont(f);
        addl.setForeground(Color.BLACK);
        addl.setBounds(100, 0, 1000, 100);
        addOrder.add(addl);

        // Customer Information
        cust_fname = new JLabel("First Name: ");
        cust_lnamelbl = new JLabel("Last Name: ");
        address = new JLabel("Address: ");
        dob = new JLabel("Date of Birth: ");
        errormsg = new JLabel("");
        errormsg.setBounds(20, 100, 400, 250);

        // TextFields
        c_fname = new JTextField(4);
        c_lname = new JTextField(4);
        cont = new JTextField(4);
        add = new JTextField(4);
        doB = new JTextField(4);

        // Add to Cinfo Panel
        cinfoPanel.setBorder(new TitledBorder("Customer Information"));
        cinfoPanel.setBounds(50, 100, 400, 250);
        cinfoPanel.add(cust_fname);
        cinfoPanel.add(c_fname);
        cinfoPanel.add(cust_lnamelbl);
        cinfoPanel.add(c_lname);
        cinfoPanel.add(address);
        cinfoPanel.add(add);
        cinfoPanel.add(dob);
        cinfoPanel.add(doB);
        cinfoPanel.add(saveButton);
        cinfoPanel.add(sortButton);
        cinfoPanel.add(addButton);
        cinfoPanel.add(deleteButton);

        // Add to addOrder Panel
        addOrder.add(addl);
        addOrder.add(cinfoPanel);
        addOrder.add(infoPanel);
        addOrder.add(new JLabel(" "));

        // Control Panel
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 1, 0));
        mainPanel.setBackground(new Color(22, 22, 21));
        mainPanel.add(table);
        mainPanel.add(addOrder);

        // Set Panel Background
        background = new JPanel(new GridLayout(1, 1, 0, 10));
        background.setSize(910, 600);
        background.setBackground(new Color(22, 22, 21));

        // Add Panels to display
        background.add(mainPanel);
        frame.add(background);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Method to show orders in a list in GUI table display.
    private void showTable(List<Customer> manage_customerqueue2) {
        if (manage_customerqueue2.size() <= 0)
            return;
        for (int j = 0; manage_customerqueue2.size() > j; j++)
            addToTable(manage_customerqueue2.get(j));
    }

    // Function to add a row to the table
    private void addToTable(Customer c) {
        String lname = c.getLName();
        String fname = c.getFName();
        String addr = c.getAddress();

        String[] item = { lname, fname, addr };
        model.addRow(item);

    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent eve) {
            // PLEASE COMPLETE THE IMPLEMENTATION OF THE BUTTONS
            // WE HAVE ADD, DELETE, SAVE, SORT
        }
    }
}
