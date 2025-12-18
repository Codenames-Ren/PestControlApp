package view;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.hibernate.Session;

import pestcontrol.util.Hibernate;
import pestcontrol.entity.Payment;
import pestcontrol.entity.Client;
import pestcontrol.entity.Package;

/**
 *
 * @author ren
 */


public class FramePayment extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
    private Long selectedPaymentId = null;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FramePayment.class.getName());

    public FramePayment() {
        initComponents();
        setLocationRelativeTo(null);  
        
        cmbClientName.setModel(new javax.swing.DefaultComboBoxModel<>());
        cmbPackageName.setModel(new javax.swing.DefaultComboBoxModel<>());
        cmbPaymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>());
        cmbPackageName.addActionListener(e -> updateTotal());
        
        loadClientCombo();
        loadPackageCombo();
        loadPaymentMethod();
        
        cmbClientName.setSelectedIndex(-1);
        cmbPackageName.setSelectedIndex(-1);
        cmbPaymentMethod.setSelectedIndex(-1);
        
        txtSubtotal.setEditable(false);
        
        initTable();
        loadTable();
        selectTable();
        setButtonDefault();
    }
    
    private void initTable() {
        tableModel = new DefaultTableModel(
            new Object[]{"ID", "No", "Client", "Package", "Service Date", "Payment Method", "Total"}, 0
        ) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        table.setModel(tableModel);

        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
    }
    
    private void loadTable() {
        tableModel.setRowCount(0);

        Session s = Hibernate.getSessionFactory().openSession();
        try {
            List<Payment> list = s.createQuery("FROM Payment", Payment.class).list();
            int no = 1;
            for (Payment p : list) {
                tableModel.addRow(new Object[]{
                    p.getId(),
                    no++,
                    p.getClient().getName(),
                    p.getPkg().getName(),
                    p.getServiceDate(),
                    p.getPaymentMethod(),
                    formatRupiah(p.getTotalAmount())
                });
            }
        } finally {
            s.close();
        }
    }

    private void clearForm() {
        cmbClientName.setSelectedIndex(-1);
        cmbPackageName.setSelectedIndex(-1);
        cmbPaymentMethod.setSelectedIndex(-1);
        txtSubtotal.setText("");
        txtSearch.setText("");
        selectedPaymentId = null;
        setButtonDefault();
    }

    private void selectTable() {
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row < 0) return;

                selectedPaymentId = (Long) table.getValueAt(row, 0);
            }
        });
    }

    private void setButtonDefault() {
        btnAdd.setEnabled(true);
    }
    
    private void loadClientCombo() {
        cmbClientName.removeAllItems();

        Session s = Hibernate.getSessionFactory().openSession();
        try {
            List<Client> list = s.createQuery("FROM Client", Client.class).list();
            for (Client c : list) {
                cmbClientName.addItem(c);
            }
        } finally {
            s.close();
        }
    }
    
    private void loadPackageCombo() {
        cmbPackageName.removeAllItems();

        Session s = Hibernate.getSessionFactory().openSession();
        try {
            List<pestcontrol.entity.Package> list =
                s.createQuery("FROM Package", pestcontrol.entity.Package.class).list();

            for (pestcontrol.entity.Package p : list) {
                cmbPackageName.addItem(p);
            }
        } finally {
            s.close();
        }
    }

    private void loadPaymentMethod() {
        cmbPaymentMethod.removeAllItems();
        cmbPaymentMethod.addItem("Cash");
        cmbPaymentMethod.addItem("Transfer Bank");
        cmbPaymentMethod.addItem("E-Wallet");
    }
    
    private String formatRupiah(double amount) {
        java.text.DecimalFormat formatter = new java.text.DecimalFormat("#,###");
        java.text.DecimalFormatSymbols symbols = new java.text.DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        formatter.setDecimalFormatSymbols(symbols);
        return "Rp " + formatter.format(amount);
    }

    private void updateTotal() {
        pestcontrol.entity.Package pkg =
            (pestcontrol.entity.Package) cmbPackageName.getSelectedItem();

        if (pkg == null) {
            txtSubtotal.setText("");
            return;
        }

        double admin = 15000;
        double total = pkg.getPrice() + admin;

        txtSubtotal.setText(formatRupiah(total));
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnAdd = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        cmbClientName = new javax.swing.JComboBox<>();
        cmbPackageName = new javax.swing.JComboBox<>();
        dtpService = new com.toedter.calendar.JCalendar();
        cmbPaymentMethod = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Client Data");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Payment Forms");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Client Name");

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Package Name");

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Service Date");

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Payment Method");

        jSeparator1.setBackground(new java.awt.Color(255, 204, 102));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        btnAdd.setBackground(new java.awt.Color(51, 204, 0));
        btnAdd.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(204, 153, 0));
        btnCancel.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Search");

        txtSearch.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        btnSearch.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        btnSearch.setText("🔍");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        table.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Package", "Service Date", "Subtotal"
            }
        ));
        jScrollPane2.setViewportView(table);

        cmbClientName.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        cmbClientName.setModel(new javax.swing.DefaultComboBoxModel<>());

        cmbPackageName.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        cmbPackageName.setModel(new javax.swing.DefaultComboBoxModel<>());

        cmbPaymentMethod.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        cmbPaymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Subtotal");

        txtSubtotal.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 38, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmbPaymentMethod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dtpService, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2)
                                        .addComponent(cmbClientName, 0, 357, Short.MAX_VALUE)
                                        .addComponent(cmbPackageName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(56, 56, 56)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPackageName, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dtpService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(155, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
            try {
                Payment p = new Payment();

                p.setClient((Client) cmbClientName.getSelectedItem());
                p.setPkg((pestcontrol.entity.Package) cmbPackageName.getSelectedItem());
                p.setPaymentMethod(cmbPaymentMethod.getSelectedItem().toString());

                java.util.Date d = dtpService.getDate();
                p.setServiceDate(
                    d.toInstant()
                     .atZone(java.time.ZoneId.systemDefault())
                     .toLocalDateTime()
                );

                // GANTI BAGIAN INI ↓
                String subtotalText = txtSubtotal.getText()
                    .replace("Rp ", "")
                    .replace(".", "");
                p.setTotalAmount(Double.parseDouble(subtotalText));

                Session s = Hibernate.getSessionFactory().openSession();
                s.beginTransaction();
                s.save(p);
                s.getTransaction().commit();
                s.close();

                JOptionPane.showMessageDialog(this, "Payment saved!");
                loadTable();
                clearForm();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String key = txtSearch.getText();

        if (key == null || key.trim().isEmpty()) {
            loadTable();
            return;
        }

        tableModel.setRowCount(0);

        Session s = Hibernate.getSessionFactory().openSession();
        try {
            List<Payment> list = s.createQuery(
                "FROM Payment p WHERE p.client.name LIKE :x",
                Payment.class
            ).setParameter("x", "%" + key + "%")
             .list();

            int no = 1;
            for (Payment p : list) {
                tableModel.addRow(new Object[]{
                    p.getId(),
                    no++,
                    p.getClient().getName(),
                    p.getPkg().getName(),
                    p.getServiceDate(),
                    p.getPaymentMethod(),
                    formatRupiah(p.getTotalAmount())
                });
            }
        } finally {
            s.close();
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        clearForm();
        loadTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
          clearForm();
          table.clearSelection();
    }//GEN-LAST:event_btnCancelActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FramePayment().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<Client> cmbClientName;
    private javax.swing.JComboBox<pestcontrol.entity.Package> cmbPackageName;
    private javax.swing.JComboBox<String> cmbPaymentMethod;
    private com.toedter.calendar.JCalendar dtpService;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSubtotal;
    // End of variables declaration//GEN-END:variables
}
