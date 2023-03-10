import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.*;

import java.awt.*;
import java.util.List;
import java.awt.Color;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ManageOrderUserInterface extends JFrame{
    //Instance Variables/Attributes
    private JTextField searchbar;
    private JLabel search_label;
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
    private JLabel contact;
    private JLabel address;
    private JLabel email;
    private JLabel courierlbl; //Drop Down
    private JLabel item_countlbl2;

    private JButton cancel_btn;
    private JButton confirm_btn;

    private JLabel item_typelbl;
    private JLabel item_colorlbl;
    private JLabel item_size;
    private JLabel errormsg;

    private JComboBox courier_menu;
    private JComboBox item_type_menu;
    private JComboBox item_color_menu;
    private JComboBox item_size_menu;

    private JTextField c_fname;
    private JTextField c_lname;
    private JTextField cont;
    private JTextField add;
    private JTextField e_add;
    private JTextField countery2;

    private JButton submit_btn;
    private JButton reset_btn;

    //bottom Pane
    private JButton back;
    private JButton details;
    private JButton addc;
    private JLabel logo;

    private String[][] det;
    private Order manageOrder;
    private ManageStockUserInterface stock_manager;
    private ManageCustomer customer_manager;
    private List<Order> manageOrder_queue = new ArrayList<Order>();

    public ManageOrderUserInterface(){
        this.frame = this;
        //Font and Defaults
        customer_manager = new ManageCustomer();

        Font f = new Font("Montserrat", Font.BOLD, 20);
        frame.setTitle("SS Colecao - Manage Orders");

        //Search Bar
        /*searchbar = new JTextField(15);
        search_label = new JLabel("Search");*/

        //Table Setup
        manageOrder_queue = ManageOrder.getAllOrders();
        String[] columnNames = {"Full Name", "Item Amount", "Status", "ItemName", "Total Price"};

        this.model = new DefaultTableModel(columnNames, 0);
        otable = new JTable(model);
        sort = new TableRowSorter<>(model);
        showTable((ArrayList<Order>) manageOrder_queue);
        otable.setRowSorter(sort);
        otable.setBounds(20, 30, 450, 450);
        pane = new JScrollPane(otable);
        pane.setViewportView(otable);

        //Set Clickable Rows
        //otable.addMouseListener();

        //Content
        //Order Table Display
        table = new JPanel();
        table.setBackground(Color.GRAY);
        //table.add(search_label);
        //table.add(searchbar);
        table.add(pane);

        back = new JButton("Back");
        back.addActionListener(new ButtonListener());

        addc = new JButton("Add Coupon");
        addc.addActionListener(new ButtonListener());

        /*searchbar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchbar.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchbar.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchbar.getText());
            }
            public void search(String str) {
                if (str.length() == 0) {
                    sort.setRowFilter(null);
                } 
                else{
                    sort.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });*/

        //Add Order Pane
        JPanel infoPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        JPanel cinfoPanel = new JPanel(new GridLayout(7, 4, 30, 10));
        addOrder = new JPanel(new BorderLayout());

        //Logo 
        ImageIcon logo_img = new ImageIcon("Ologo.png");
        Image img = logo_img.getImage();
		Image temp_img = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        logo_img = new ImageIcon(temp_img);
        logo = new JLabel(logo_img);
        logo.setBounds(0, 0, 100, 100);
        addOrder.add(logo);

        //Add Order 
        JLabel addl = new JLabel("NEW CUSTOMER ORDER                    ");
        addl.setFont(f);
        addOrder.setBorder( new TitledBorder("Add Order"));
        addl.setForeground(Color.BLACK);
        addl.setBounds(100, 0, 1000, 100);
        addOrder.add(addl);

        //Customer Information
        cust_fname = new JLabel("First Name: ");
        cust_lnamelbl = new JLabel("Last Name: ");
        email = new JLabel("Email: ");
        contact = new JLabel("Contact: ");
        address = new JLabel("Address: ");
        courierlbl = new JLabel("Delivery Mode: ");
        item_countlbl2 = new JLabel("Item Count: ");

        //Labels
        item_typelbl = new JLabel("Item Type: ");
        item_colorlbl = new JLabel("Item Color: ");
        item_size = new JLabel("Item Size: ");
        errormsg = new JLabel("");
        errormsg.setBounds(20, 100, 400, 250);

        //Stock Type
        String[] stockTypes = {"Bikini_Bottom", "Bikini_Top"};
        String[] stockColors = {"Black", "White", "Pattern"};
        String[] stockSizes = {"S", "M", "L"};
        String[] courier_mode = {"Pickup", "Delivery"};

        //Combo Boxes
        item_type_menu = new JComboBox<>(stockTypes);
        item_color_menu = new JComboBox<>(stockColors);
        item_size_menu = new JComboBox<>(stockSizes);
        courier_menu = new JComboBox<>(courier_mode);

        //TextFields
        c_fname = new JTextField(4);
		c_lname = new JTextField(4);
		cont = new JTextField(4);
		add = new JTextField(4);
		e_add = new JTextField(4);
        countery2 = new JTextField(4);

        //Add to Cinfo Panel
        cinfoPanel.setBorder( new TitledBorder("Customer Details"));
        cinfoPanel.setBounds(20, 80, 400, 250);

        cinfoPanel.add(cust_fname);
        cinfoPanel.add(c_fname);

        cinfoPanel.add(cust_lnamelbl);
        cinfoPanel.add(c_lname);

        cinfoPanel.add(contact);
        cinfoPanel.add(cont);

        cinfoPanel.add(address);
        cinfoPanel.add(add);

        cinfoPanel.add(email);
        cinfoPanel.add(e_add);

        cinfoPanel.add(courierlbl);
        cinfoPanel.add(courier_menu);

        //Add to Info Panel
        infoPanel.setBorder( new TitledBorder("Item Details"));
        infoPanel.setBounds(20, 350, 400, 200);
        infoPanel.add(item_typelbl);
        infoPanel.add(item_type_menu);

        infoPanel.add(item_colorlbl);
        infoPanel.add(item_color_menu);

        infoPanel.add(item_size);
        infoPanel.add(item_size_menu);

        infoPanel.add(item_countlbl2);
        infoPanel.add(countery2);

        //Button For Order Confirmation and Cancelling
        submit_btn = new JButton("Submit");
        submit_btn.addActionListener(new ButtonListener());

        reset_btn = new JButton("Reset");
        reset_btn.addActionListener(new ButtonListener());

        cancel_btn = new JButton("Cancel");
        cancel_btn.addActionListener(new ButtonListener());

        confirm_btn = new JButton("Confirm Order");
        confirm_btn.addActionListener(new ButtonListener());

        infoPanel.add(submit_btn);
        infoPanel.add(reset_btn);
        infoPanel.add(cancel_btn);
        infoPanel.add(confirm_btn);

        //Add to addOrder Panel
        addOrder.add(addl);
        addOrder.add(cinfoPanel);
        addOrder.add(infoPanel);
        addOrder.add(new JLabel(" "));

        //Loop for as many times as there are items accept input from JCombo fields

        //Control Panel
		JPanel mainPanel = new JPanel(new GridLayout(1, 2, 1, 0));
		mainPanel.setBackground(new Color(22, 22, 21));
        table.add(back);
        table.add(addc);
        table.add(errormsg);
        mainPanel.add(table);
        mainPanel.add(addOrder);


        //Set Panel Background
		background = new JPanel(new GridLayout(1, 1, 0, 10));
		background.setSize(910, 600);
        background.setBackground(new Color(22, 22, 21));
        
        //Add Panels to display
        background.add(mainPanel);
		frame.add(background);

        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Method to show orders in a list in GUI table display.
    private void showTable(ArrayList<Order> olst){
        if (olst.size()<=0) return;
        for(int j = 0; olst.size()>j; j++)
            addToTable(olst.get(j));
    }

    //Function to add a row to the table
    private void addToTable(Order o){
        String full = o.getCustomerName();
        int amt = o.getItemCount();
        String stat = o.getStatus();
        String itemName = o.getItem().getStockType().name();
        String totalCost = String.valueOf(Float.parseFloat(ManageStock.getPriceFromItemName(itemName)) * amt);

        String[] item= {full, String.valueOf(amt), stat, itemName, totalCost};
        model.addRow(item);

    }

    //Button Listeners for: Back
    //method to add function to the back button
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent eve)
		{
            //back
            if(eve.getSource()==back){
                background.setVisible(false);

                MainMenu mscreen = new MainMenu(frame);
                frame.add(mscreen);
                mscreen.setVisible(true);   
            }

            //add coupon
            //back
            if(eve.getSource()==addc){
                background.setVisible(false);

                AdministerCoupons ascreen = new AdministerCoupons(frame);
                frame.add(ascreen);
                ascreen.setVisible(true);   
            }

            //reset btn
            if(eve.getSource()==reset_btn){
                c_fname.setText("");
                c_lname.setText("");
                cont.setText("");
                add.setText("");
                e_add.setText("");
                countery2.setText("");
            }

            if(eve.getSource()==cancel_btn){
                background.setVisible(false);

                MainMenu mscreen = new MainMenu(frame);
                frame.add(mscreen);
                mscreen.setVisible(true);
            }

            //confirm
            if(eve.getSource()==confirm_btn){
                ManageCustomer.getAllCustomers();

                if(c_fname.getText().isBlank() || c_lname.getText().isBlank() || cont.getText().isBlank() || add.getText().isBlank() || e_add.getText().isBlank() || countery2.getText().isBlank()){
                    errormsg.setText("Please fill out all the fields");
                }
                else{
                    Order ord;
                    String fname = c_fname.getText();
                    String lname = c_lname.getText();
                    String contact = cont.getText();
                    String address = add.getText();
                    String email = e_add.getText();
                    int counter2 = Integer.parseInt(countery2.getText());

                    String mode = courier_menu.getSelectedItem().toString();

                    Customer cust = new Customer(fname, lname, contact, address, email);

                    //Stock Detail
                    StockType type = StockType.valueOf(item_type_menu.getSelectedItem().toString());
                    ItemColor color = ItemColor.valueOf(item_color_menu.getSelectedItem().toString());
                    Size size = Size.valueOf(item_size_menu.getSelectedItem().toString());
                    
                    String[] temp = {type.name() + ", " + color.name() + ", " + size.name()};
                    Stock details = null;
                    Customer customer = customer_manager.findCustomer(fname, lname);
                    if(customer!=null){
                        //Continue as normal
                        ord = new Order(customer, counter2, mode, "Pending", details);
                    }
                    else{
                        //Customer customer, int item_count, CourierMode delMode, int ticket_num, OrderStatus status, List<String[]> details
                        ManageCustomer.createCustomer(cust);
                        ord = new Order(cust, counter2, mode,"Pending", details);
                    }

                    try{
                        ManageOrder.createOrder(ord, ManageStock.getIdFromItemName(item_type_menu.getSelectedItem().toString()));
                        //if(model.getRowCount()>0) model.setRowCount(0);
                        showTable((ArrayList<Order>)ManageOrder.getAllOrders());
                    }
                    catch(Error e){
                        errormsg.setText("Recheck Input Values");
                    }
                }
            }
        }
	}
}

