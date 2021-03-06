/**
 * AboutStickMotion.java
 * 
 */

package gui;

/**
 * Dialog for "About StickMotion"
 */
public class AboutStickMotion extends javax.swing.JDialog {

  /** Creates new form AboutStickMotion */
  public AboutStickMotion(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   */
  private void initComponents() {

    imagePanel = new javax.swing.JPanel();
    imageStickman = new javax.swing.JLabel();
    creditsPanel = new javax.swing.JPanel();
    aboutLabel1 = new javax.swing.JLabel();
    aboutLabel2 = new javax.swing.JLabel();
    aboutLabel3 = new javax.swing.JLabel();
    aboutLabel4 = new javax.swing.JLabel();
    aboutLabel5 = new javax.swing.JLabel();
    aboutLabel6 = new javax.swing.JLabel();
    aboutLabel7 = new javax.swing.JLabel();
    aboutLabel8 = new javax.swing.JLabel();
    aboutLabel9 = new javax.swing.JLabel();
    backButton = new javax.swing.JButton();
    aboutLabel10 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Acerca de... StickMotion 1.0");

    imagePanel.setBorder(javax.swing.BorderFactory
        .createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

    imageStickman.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gui/images/stickman.jpg")));

    javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(
        imagePanel);
    imagePanel.setLayout(imagePanelLayout);
    imagePanelLayout.setHorizontalGroup(imagePanelLayout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addComponent(imageStickman));
    imagePanelLayout.setVerticalGroup(imagePanelLayout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addComponent(imageStickman));

    creditsPanel.setBorder(javax.swing.BorderFactory
        .createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

    aboutLabel1.setText("StickMotion® ha sido desarrollada por: ");

    aboutLabel2.setText("- Carmona Varo, Fernando");

    aboutLabel3.setText("- García García, José Manuel");

    aboutLabel4.setText("- López Fernández, David");

    aboutLabel5.setText("- Navas Torres, Francisco Javier");

    aboutLabel6.setText("- Porras Bueno, Javier");

    aboutLabel7.setText("Para el profesor Enríque Yeguas, docente de la parte");

    aboutLabel8.setText("práctica de la asignatura Procesadores del Lenguaje.");

    aboutLabel9.setText("Ingeniería Superior en Informática - EPS/UCO");

    backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gui/images/next2.png")));
    backButton.setText("¡Volver!");
    backButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        backButtonActionPerformed(evt);
      }
    });

    aboutLabel10.setText("Curso Académico 2009/2010");

    javax.swing.GroupLayout creditsPanelLayout = new javax.swing.GroupLayout(
        creditsPanel);
    creditsPanel.setLayout(creditsPanelLayout);
    creditsPanelLayout
        .setHorizontalGroup(creditsPanelLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                creditsPanelLayout
                    .createSequentialGroup()
                    .addGroup(
                        creditsPanelLayout
                            .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(
                                creditsPanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(aboutLabel1))
                            .addGroup(
                                creditsPanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(aboutLabel8))
                            .addGroup(
                                creditsPanelLayout.createSequentialGroup()
                                    .addGap(35, 35, 35).addComponent(
                                        aboutLabel9))
                            .addGroup(
                                creditsPanelLayout
                                    .createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                        false)
                                    .addGroup(
                                        javax.swing.GroupLayout.Alignment.LEADING,
                                        creditsPanelLayout
                                            .createSequentialGroup()
                                            .addGap(48, 48, 48)
                                            .addGroup(
                                                creditsPanelLayout
                                                    .createParallelGroup(
                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(
                                                        creditsPanelLayout
                                                            .createSequentialGroup()
                                                            .addGroup(
                                                                creditsPanelLayout
                                                                    .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(
                                                                        aboutLabel3)
                                                                    .addComponent(
                                                                        aboutLabel4))
                                                            .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                55,
                                                                Short.MAX_VALUE)
                                                            .addComponent(
                                                                backButton))
                                                    .addComponent(aboutLabel2)
                                                    .addComponent(aboutLabel5)
                                                    .addComponent(aboutLabel6)))
                                    .addGroup(
                                        javax.swing.GroupLayout.Alignment.LEADING,
                                        creditsPanelLayout
                                            .createSequentialGroup()
                                            .addContainerGap().addComponent(
                                                aboutLabel7))).addGroup(
                                creditsPanelLayout.createSequentialGroup()
                                    .addGap(80, 80, 80).addComponent(
                                        aboutLabel10))).addContainerGap(
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    creditsPanelLayout
        .setVerticalGroup(creditsPanelLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                creditsPanelLayout
                    .createSequentialGroup()
                    .addContainerGap()
                    .addComponent(aboutLabel1)
                    .addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(aboutLabel2)
                    .addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(
                        creditsPanelLayout
                            .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(
                                creditsPanelLayout
                                    .createSequentialGroup()
                                    .addComponent(aboutLabel3)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(aboutLabel4)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(aboutLabel5)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(aboutLabel6)).addComponent(
                                backButton)).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(aboutLabel7).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(aboutLabel8).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(aboutLabel9).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(aboutLabel10).addContainerGap(
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
        getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        layout.createSequentialGroup().addContainerGap().addComponent(
            imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(
            javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(
            creditsPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(
            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    layout.setVerticalGroup(layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        layout.createSequentialGroup().addGroup(
            layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.TRAILING, false).addGroup(
                layout.createSequentialGroup().addContainerGap().addComponent(
                    creditsPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(
                    layout.createSequentialGroup().addGap(12, 12, 12)
                        .addComponent(imagePanel,
                            javax.swing.GroupLayout.DEFAULT_SIZE, 232,
                            Short.MAX_VALUE))).addContainerGap()));

    pack();
  }

  /**
   * Method for hiding the panel when the "back" button is pressed
   * 
   * @param evt
   */
  private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
    setVisible(false); // Ocultamos. hide() es deprecated
  }

  /**
   * Main Method
   * 
   * @param args
   *          the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        AboutStickMotion dialog = new AboutStickMotion(
            new javax.swing.JFrame(), true);
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

  private javax.swing.JLabel aboutLabel1;
  private javax.swing.JLabel aboutLabel10;
  private javax.swing.JLabel aboutLabel2;
  private javax.swing.JLabel aboutLabel3;
  private javax.swing.JLabel aboutLabel4;
  private javax.swing.JLabel aboutLabel5;
  private javax.swing.JLabel aboutLabel6;
  private javax.swing.JLabel aboutLabel7;
  private javax.swing.JLabel aboutLabel8;
  private javax.swing.JLabel aboutLabel9;
  private javax.swing.JButton backButton;
  private javax.swing.JPanel creditsPanel;
  private javax.swing.JPanel imagePanel;
  private javax.swing.JLabel imageStickman;
}
