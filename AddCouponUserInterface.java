import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCouponUserInterface extends JFrame {
    private JFrame frame;
    private JLabel addCouponLabel;

    private JButton addCouponButton;
    private JButton closePageButton;

    private JTextField percentageInput;
    Order selectedOrder;
    String orderId;

    public AddCouponUserInterface(String orderId, Order order){
        this.selectedOrder = order;
        this.orderId = orderId;
        this.frame = this;
        JPanel cinfoPanel = new JPanel(new GridLayout(7, 4, 30, 10));
        // TextFields
        addCouponLabel = new JLabel("Enter coupon percentage: ");
        percentageInput = new JTextField(4);

        addCouponButton = new JButton("Add Coupon");
        addCouponButton.addActionListener(new ButtonListener());

        closePageButton = new JButton("Close");
        closePageButton.addActionListener(new ButtonListener());

        // Add to Cinfo Panel
        cinfoPanel.setBorder(new TitledBorder("Add Coupon"));
        cinfoPanel.setBounds(50, 100, 400, 250);
        cinfoPanel.add(addCouponLabel);
        cinfoPanel.add(percentageInput);
        cinfoPanel.add(addCouponButton);
        cinfoPanel.add(closePageButton);

        frame.add(cinfoPanel);
        pack();
        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent eve) {
            if (eve.getSource() == addCouponButton) {
                if(!percentageInput.getText().isBlank()){
                    Double percentage = Double.parseDouble(percentageInput.getText())/100;
                    ManageCoupon.createCouponAndAddToOrder(orderId, selectedOrder, percentage);

                    //close
                    frame.dispose();
                }
            }

            if(eve.getSource()==closePageButton){
                frame.dispose();
            }
        }
    }
}
