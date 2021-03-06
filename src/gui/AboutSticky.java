/**
 * Help.java
 */
package gui;

/**
 * Dialog for "About Sticky"
 */
public class AboutSticky extends javax.swing.JDialog {

  /** Creates new form Help */
  public AboutSticky(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    jPanel2 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jButton1 = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Acerca de Sticky");

    jPanel1.setBorder(javax.swing.BorderFactory
        .createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gui/images/stickmanGif.gif")));

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        javax.swing.GroupLayout.Alignment.TRAILING,
        jPanel1Layout.createSequentialGroup().addContainerGap()
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 319,
                Short.MAX_VALUE).addContainerGap()));
    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        javax.swing.GroupLayout.Alignment.TRAILING,
        jPanel1Layout.createSequentialGroup().addContainerGap()
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 321,
                Short.MAX_VALUE).addContainerGap()));

    jPanel2.setBorder(javax.swing.BorderFactory
        .createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

    jLabel1.setText("Sticky: Lenguaje de programación de movimientos.");

    jLabel2.setText(" Creado por los mismos autores que StickMotion.");

    jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gui/images/next2.png")));
    jButton1.setText("¡Volver!");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        jPanel2Layout.createSequentialGroup().addGroup(
            jPanel2Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                jPanel2Layout.createSequentialGroup().addContainerGap()
                    .addGroup(
                        jPanel2Layout.createParallelGroup(
                            javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1).addComponent(jLabel2)))
                .addGroup(
                    jPanel2Layout.createSequentialGroup().addGap(131, 131, 131)
                        .addComponent(jButton1))).addContainerGap(
            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(
            jLabel1).addPreferredGap(
            javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(
            jLabel2).addPreferredGap(
            javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(
            jButton1).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
            Short.MAX_VALUE)));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
        getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        javax.swing.GroupLayout.Alignment.TRAILING,
        layout.createSequentialGroup().addContainerGap()
            .addGroup(
                layout.createParallelGroup(
                    javax.swing.GroupLayout.Alignment.TRAILING).addComponent(
                    jPanel1, javax.swing.GroupLayout.Alignment.LEADING,
                    javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2,
                        javax.swing.GroupLayout.Alignment.LEADING,
                        javax.swing.GroupLayout.DEFAULT_SIZE, 347,
                        Short.MAX_VALUE)).addContainerGap()));
    layout.setVerticalGroup(layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        layout.createSequentialGroup().addContainerGap().addComponent(jPanel1,
            javax.swing.GroupLayout.PREFERRED_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(
            javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(
            jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(
            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

    pack();
  }

  /**
   * Method to execute when the "Back" button is clicked
   * 
   * @param evt
   */
  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
    // When the "Volver" button is pressed
    setVisible(false);
  }// GEN-LAST:event_jButton1ActionPerformed

  /**
   * Main method
   * 
   * @param args
   *          the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        AboutSticky dialog = new AboutSticky(new javax.swing.JFrame(), true);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
          @Override
          public void windowClosing(java.awt.event.WindowEvent e) {
            System.exit(0);
          }
        });
        dialog.setVisible(true);
      }
    });
  }

  private javax.swing.JButton jButton1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
}
