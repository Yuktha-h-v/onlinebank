/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlinebank3client;

import com.mycompany.models.Accounts;
import com.mycompany.models.Transactions;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/*
 * @author(s): Carlos Amaro, Olga Minguett, Mariah Sonja
 * Title: OnlineBank3Client
 * Date: April, 2017
 * National College of Ireland
 * Web Services and API Development
 * Lecturer: Julie Power
 */
public class Withdraw extends javax.swing.JFrame {
    //Creates new form Withdraw
    //local variables
    List<Accounts> accountsList;
    List<String> accountIDString;
    Accounts account;

    public Withdraw() {
        
        // set the panel to the center
        //@reference: https://www.youtube.com/watch?v=xGzeEUHcsj8

        this.setLocationRelativeTo(null);
        initComponents();
        //create a new object for the array
        accountIDString = new ArrayList<>();
        accountsList = new ArrayList<>();
    }

    //setter 
    //This method set the account and value that the user has entered
    public void setAccount(List<Accounts> acList) {
        this.accountsList = acList;

        for (Accounts ac : accountsList) {
            accountIDString.add(ac.getAccountId() + "");
        }

        this.accountIdField.setModel(new DefaultComboBoxModel(accountIDString.toArray()));
    }

    public void Withdraw() {
         //Path to show the transactions the user just made
        String getUrl = "http://localhost:8080/Online_Bank3/api/transactions";
        Client client = Client.create();
        WebResource target = client.resource(getUrl);
        //Displays the accounts in the ComboBox
        account = accountsList.get(accountIdField.getSelectedIndex());
        
        //Bigdecimal converted to double because it's the data type
        //the system is expecting.(MySQL)
        //@reference:http://stackoverflow.com/questions/5749615/losing-precision-converting-from-java-bigdecimal-to-double
        BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(amountField.getText()));

        
        //We are setting all the information that we need to get to deliver the process
        //From the current customer and account
        Transactions tran = new Transactions();
        tran.setAccountId(account);
        tran.setAmount(amount);
        tran.setCustomerId(account.getCustomerId());
        tran.setDescription(descriptionTextArea.getText());
        tran.setTransactionType("Withdraw");
        //create the JSON object
        JSONObject param = new JSONObject(tran);
        //change back to string because the POST request wants a string version of the JSON
        //@reference: http://stackoverflow.com/questions/17651395/convert-jsonobject-to-string
        String entity = param.toString();
        //creates the POST request
        //@reference: http://stackoverflow.com/questions/16284743/how-to-submit-data-with-jersey-client-post-method
        ClientResponse response = target.accept("application/json")
                .type("application/json").post(ClientResponse.class, entity);
        
        // It's 204 because we are not getting content
        //@reference: http://stackoverflow.com/questions/16284743/how-to-submit-data-with-jersey-client-post-method
        //@reference: httpstatuses.com/204
        if (response.getStatus() == 204) {

            JOptionPane.showMessageDialog(null, "You withdraw has been successful!");

            BigDecimal newBalance = account.getAccountBalance().subtract(amount);
            //set new balance to from account
            account.setAccountBalance(newBalance);
            //send update account information to the api
            UpdateAccount(account);

        } else {
            JOptionPane.showMessageDialog(null, "We couldn't process your request!");
            System.out.println(response.getEntity(String.class));
        }
    }
    // use for updating account and its values
    private void UpdateAccount(Accounts account) {
        String getUrl = "http://localhost:8080/Online_Bank3/api/accounts/" + account.getAccountId();
         //creates a web instance for this especific account
        Client client = Client.create();
        WebResource target = client.resource(getUrl);

        JSONObject entity = new JSONObject(account);
        //@reference: http://stackoverflow.com/questions/27314049/put-method-restful-doesnt-work-as-a-way-to-update-resources
        ClientResponse putresponse = target.accept("application/json")
                .type("application/json").put(ClientResponse.class, entity.toString());

        if (putresponse.getStatus() == 204) {
            JOptionPane.showMessageDialog(null, "Your account was updated!");
        } else {
            JOptionPane.showMessageDialog(null, "Your account was NOT updated! check your information");
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        accountIdField = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        amountField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        processBtn = new javax.swing.JButton();
        home = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bbank.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setText("Withdraw");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel3.setText("Account Number:");

        accountIdField.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        accountIdField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel4.setText("Amount:");

        amountField.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel5.setText("Description:");

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        descriptionTextArea.setRows(5);
        jScrollPane1.setViewportView(descriptionTextArea);

        processBtn.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        processBtn.setText("Process");
        processBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processBtnActionPerformed(evt);
            }
        });

        home.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        home.setText("Home");
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });

        jLabel6.setText("Brave Bank, Inc. 2017");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel1)
                        .addGap(0, 37, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(11, 11, 11)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(amountField)
                            .addComponent(accountIdField, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(58, 58, 58))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(home)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(processBtn)
                .addGap(87, 87, 87))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(accountIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(60, 60, 60)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(processBtn)
                    .addComponent(home))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        // TODO add your handling code here:
         //dispose the page in GUI that the user is in
        this.dispose();
    }//GEN-LAST:event_homeActionPerformed

    private void processBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processBtnActionPerformed
        // TODO add your handling code here:
        // Calling the method that we have created before
        //This process the action.
        Withdraw();
        //dispose the page in GUI that the user is in
        //@reference: http://stackoverflow.com/questions/8632705/how-to-close-a-gui-when-i-push-a-jbutton
        this.dispose();
    }//GEN-LAST:event_processBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Withdraw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Withdraw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Withdraw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Withdraw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Withdraw().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> accountIdField;
    private javax.swing.JTextField amountField;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JButton home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton processBtn;
    // End of variables declaration//GEN-END:variables
}
