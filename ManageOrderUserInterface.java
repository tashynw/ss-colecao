import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.*;

import java.awt.*;
import java.awt.Color;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.util.*;

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
    private JButton addc;
    private JLabel logo;

    public ManageOrderUserInterface(){
        this.frame = this;
        //Font and Defaults

        Font f = new Font("Montserrat", Font.BOLD, 20);
        frame.setTitle("SS Colecao - Manage Orders");

        //Table Setup
        String[] columnNames = {"Order ID","Full Name", "Item Amount", "Status", "ItemName", "Total Price"};

        this.model = new DefaultTableModel(columnNames, 0);
        this.otable = new JTable(model);
        showTable((ArrayList<Object[]>) ManageOrder.getAllOrders());
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

        cancel_btn = new JButton("Manage Customers");
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
    public void showTable(ArrayList<Object[]> olst){
        if (olst == null || olst.isEmpty()) return;
        if (model == null) {
            String[] columnNames = {"Order ID","Full Name", "Item Amount", "Status", "ItemName", "Total Price"};
            model = new DefaultTableModel(columnNames, 0);
            otable.setModel(model);
        }
        for(int j = 0; j<olst.size(); j++)
            addToTable(olst.get(j));
    }

    //Function to add a row to the table
    private void addToTable(Object[] o){
        String orderId = (String)o[0];
        String full = ((Order)o[1]).getCustomerName();
        int amt = ((Order)o[1]).getItemCount();
        String stat = ((Order)o[1]).getStatus();
        String itemName = ((Order)o[1]).getItem().getItemName();
        String totalCost = String.format("%.2f", ((Order)o[1]).getTotalPrice());

        String[] item= {orderId,full, String.valueOf(amt), stat, itemName, totalCost};
        model.addRow(item);
    }

    //Button Listeners for: Back
    //method to add function to the back button
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent eve)
		{
            //back
            if(eve.getSource()==back){
                setVisible(false);

                MainMenu mscreen = new MainMenu();
                //frame.add(mscreen);
                mscreen.setVisible(true);
            }

            //add coupon
            //back
            if(eve.getSource()==addc){
                int selectedRowIndex = otable.getSelectedRow();
                if(selectedRowIndex!=-1){
                    Object[] rowData = model.getDataVector().elementAt(otable.convertRowIndexToModel(selectedRowIndex)).toArray();
                    Order selectedOrder = ManageOrder.findOrder((String)rowData[0]);
                    AddCouponUserInterface couponScreen = new AddCouponUserInterface((String)rowData[0],selectedOrder);
                    couponScreen.setVisible(true);
                }
            }

            //reset btn
            if(eve.getSource()==reset_btn){
                c_fname.setText("");
                c_lname.setText("");
                cont.setText("");
                add.setText("");
                e_add.setText("");
                countery2.setText("");
                model.setRowCount(0);
                showTable((ArrayList<Object[]>) ManageOrder.getAllOrders());
            }

            if(eve.getSource()==cancel_btn){
                ManageCustomerGUI manageCustomerScreen = new ManageCustomerGUI();
                manageCustomerScreen.setVisible(true);
            }

            //confirm
            if(eve.getSource()==confirm_btn){
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
                    String type = String.valueOf(item_type_menu.getSelectedItem().toString());
                    String color = String.valueOf(item_color_menu.getSelectedItem().toString());
                    String size = String.valueOf(item_size_menu.getSelectedItem().toString());
                    
                    String[] temp = {type + ", " + color + ", " + size};
                    Stock details = ManageStock.findStock(type);
                    Customer customer = ManageCustomer.findCustomer(fname, lname);
                    if(customer!=null){
                        //Continue as normal
                        ord = new Order(customer, counter2, mode, "Pending", details, (double) Double.parseDouble(ManageStock.getPriceFromItemName(type.toString()))*counter2);
                    }
                    else{
                        ManageCustomer.createCustomer(cust);
                        ord = new Order(cust, counter2, mode,"Pending", details, (double) Double.parseDouble(ManageStock.getPriceFromItemName(type.toString()))*counter2);
                    }

                    try{
                        ManageOrder.createOrder(ord, ManageStock.getIdFromItemName(item_type_menu.getSelectedItem().toString()));
                        model.setRowCount(0);
                        showTable((ArrayList<Object[]>) ManageOrder.getAllOrders());
                    }
                    catch(Error e){
                        errormsg.setText("Recheck Input Values");
                    }
                }
            }
        }
	}
}

