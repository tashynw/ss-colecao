import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.*;

import java.awt.*;
import java.awt.Color;

import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.*;

import java.util.*;
import java.util.List;
import java.io.File;
import java.io.IOException;

public class ManageStockUI extends JFrame {
    //Instance Variables/Attributes
    private JTextField searchbar;
    private JLabel search_label;
    private DefaultTableModel model;
    private JTable stable;
    private TableRowSorter sort;
    private JScrollPane pane;

    private JFrame frame;
    private JPanel background;
    private JPanel table;
    private JPanel addStock;

    private JLabel item_countlbl;
    private JLabel item_colorlbl;
    private JLabel item_size;
    private JLabel item_countlbl2;
    private JLabel searchLabel;
    private JLabel errormsg;

    private JButton back_btn;
    private JButton reset_btn;
    private JButton delete_btn;
    private JButton confirm_btn;
    private JButton searchButton;

    private JComboBox item_color_menu;
    private JComboBox item_size_menu;

    private JTextField itemNameText;
    private JTextField itemPriceText;
    private JTextField searchText;

    //bottom Pane
    private JLabel logo;

    public ManageStockUI(){
        this.frame=this;
        //Font and Defaults
        Font f = new Font("Montserrat", Font.BOLD, 20);
        frame.setTitle("SS Colecao - Manage Stock");

        //Search Bar
        searchbar = new JTextField(15);
        search_label = new JLabel("Search by Stock Name: ");

        //Table Setup

        String[] columnNames = {"Stock ID","Stock Name", "Stock Size", "Stock Color", "Price"};

        this.model = new DefaultTableModel(columnNames, 0);
        this.stable = new JTable(model);
        sort = new TableRowSorter<>(model);
        showTable((ArrayList<Object[]>) ManageStock.getAllStocks());
        stable.setBounds(20, 30, 450, 450);
        pane = new JScrollPane(stable);
        pane.setViewportView(stable);

        //Set Clickable Rows
        //otable.addMouseListener();

        //Content
        //Order Table Display
        table = new JPanel();
        table.setBackground(Color.GRAY);
        table.add(pane);

        //Add Order Pane
        JPanel infoPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        JPanel cinfoPanel = new JPanel(new GridLayout(7, 4, 10, 10));
        JPanel searchPanel = new JPanel(new GridLayout(7, 4, 10, 10));
        addStock = new JPanel(new BorderLayout());
        
        //Logo 
        ImageIcon logo_img = new ImageIcon("Ologo.png");
        Image img = logo_img.getImage();
		Image temp_img = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        logo_img = new ImageIcon(temp_img);
        logo = new JLabel(logo_img);
        logo.setBounds(0, 0, 100, 100);
        addStock.add(logo);

        //Add Order 
        JLabel addl = new JLabel("NEW STOCK ENTRY                    ");
        addl.setFont(f);
        addStock.setBorder( new TitledBorder("Add Stock"));
        addl.setForeground(Color.BLACK);
        addl.setBounds(100, 0, 1000, 100);
        addStock.add(addl);

        //Customer Information
        item_countlbl2 = new JLabel("Item Price: ");

        //Labels
        item_colorlbl = new JLabel("Item Color: ");
        item_size = new JLabel("Item Size: ");
        searchLabel = new JLabel("Search by Stock Type: ");

        //Stock Type
        String[] stockColors = {"Black", "White", "Pattern"};
        String[] stockSizes = {"S", "M", "L"};

        //Combo Boxes
        item_color_menu = new JComboBox<>(stockColors);
        item_size_menu = new JComboBox<>(stockSizes);

        //TextFields
        itemNameText = new JTextField(4);
        itemPriceText = new JTextField(4);
        searchText = new JTextField(4);
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ButtonListener());
        

        //Add to Cinfo Panel
        cinfoPanel.setBorder(new TitledBorder("Enter new Stock name"));
        cinfoPanel.setBounds(20, 80, 400, 220);

        cinfoPanel.add(item_countlbl2);
        cinfoPanel.add(itemNameText);

        //Add to Info Panel
        infoPanel.setBorder(new TitledBorder("Item Details"));
        infoPanel.setBounds(20, 325, 400, 200);

        infoPanel.add(item_colorlbl);
        infoPanel.add(item_color_menu);

        infoPanel.add(item_size);
        infoPanel.add(item_size_menu);

        infoPanel.add(item_countlbl2);
        infoPanel.add(itemPriceText);

        //Button For Order Confirmation and Deleting
        back_btn = new JButton("Back");
        submit_btn.addActionListener(new ButtonListener());

        back_btn = new JButton("Reset");
        reset_btn.addActionListener(new ButtonListener());

        delete_btn = new JButton("Delete");
        delete_btn.addActionListener(new ButtonListener());

        confirm_btn = new JButton("Confirm Stock");
        confirm_btn.addActionListener(new ButtonListener());

        infoPanel.add(back_btn);
        infoPanel.add(reset_btn);
        infoPanel.add(delete_btn);
        infoPanel.add(confirm_btn);

        searchPanel.setBorder( new TitledBorder(""));
        searchPanel.setBounds(20, 550, 400, 200);
        searchPanel.add(searchLabel);
        searchPanel.add(searchText);
        searchPanel.add(searchButton);

        //Add to addOrder Panel
        addStock.add(addl);
        addStock.add(cinfoPanel);
        addStock.add(infoPanel);
        addStock.add(searchPanel);
        addStock.add(new JLabel(" "));

        //Loop for as many times as there are items accept input from JCombo fields

        //Control Panel
		JPanel mainPanel = new JPanel(new GridLayout(1, 2, 1, 0));
		mainPanel.setBackground(new Color(22, 22, 21));
        mainPanel.add(table);
        mainPanel.add(addStock);

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
    private void showTable(ArrayList<Object[]> stocks){
        if (stocks == null || stocks.size() <= 0)
            return;
        for(int j=0; stocks.size()>j; j++)
            addToTable(stocks.get(j));
    }

    //Function to add a row to the table
    private void addToTable(Object[] st){
        String stockName = ((Stock)(st[1])).getStockName();
        String size = ((Stock)(st[1])).getItemSize();
        String col = ((Stock)(st[1])).getItemColor();
        double price = ((Stock)(st[1])).getPrice();

        String[] item= {(String)st[0], stockName, size, col, String.valueOf(price)};
        model.addRow(item);   
    }

    

	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent eve) {
            if(eve.getSource()==back_btn){
                setVisible(false);

                MainMenu mscreen = new MainMenu();
                //frame.add(mscreen);
                mscreen.setVisible(true);   
            }

            if(eve.getSource()==reset_btn){
                itemNameText.setText("");
                itemPriceText.setText("");
                searchText.setText("");
                model.setRowCount(0);
                showTable((ArrayList<Object[]>) ManageStock.getAllStocks());
            }

            if(eve.getSource()==delete_btn){
                int selectedRowIndex = stable.getSelectedRow();
                if(selectedRowIndex!=-1) {
                    Object[] rowData = model.getDataVector().elementAt(stable.convertRowIndexToModel(selectedRowIndex)).toArray();
                    String stockId = (String)rowData[0];
                    ManageStock.deleteStock(stockId);
                    model.setRowCount(0);
                    showTable((ArrayList<Object[]>) ManageStock.getAllStocks());
                }
            }

            if(eve.getSource()==confirm_btn){
                if(itemPriceText.getText().isBlank() || itemNameText.getText().isBlank()){
                    errormsg.setText("Please fill out all the fields");
                } else {
                    Stock newStock = new Stock(itemNameText.getText(), String.valueOf(item_color_menu.getSelectedItem()),String.valueOf(item_size_menu.getSelectedItem()),Double.valueOf(itemPriceText.getText()));
                    ManageStock.createStock(newStock);
                    model.setRowCount(0);
                    showTable((ArrayList<Object[]>) ManageStock.getAllStocks());
                }
            }
            
            if(eve.getSource()==searchButton){
                if(searchText.getText().isBlank()){
                    errormsg.setText("Please enter stock type to search");
                }
                else{
                    String stockNameToSearch = searchText.getText();

                    try{
                        model.setRowCount(0);
                        showTable((ArrayList<Object[]>) ManageStock.searchStocksByItemName(stockNameToSearch));
                    }
                    catch(Error e){
                        errormsg.setText("Recheck Input Values");
                    }
                }
            }

            
        }
	}
}

