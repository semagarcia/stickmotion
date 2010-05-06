/*
 * StickMotion.java
 * 
 * Created on 16-mar-2010, 0:22:50
 */

package gui;

import java.awt.GraphicsConfiguration;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.media.j3d.Canvas3D;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;

import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 * Clase principal de entrada - Aplicación gráfica
 * 
 * @author sema
 */
public class StickMotion extends javax.swing.JFrame {
  private Object editorTextArea;
  // Variable que represento el gestor de acciones (deshacer y rehacer)
  private final javax.swing.undo.UndoManager undoRedoManager;
  // Variable que indica si el documento actual ha sido guardado o aún no
  private boolean documentSaved = false;
  // Variable que indica si el documento actual ha sido guardado o no
  private boolean documentModified = false;
  // Variable que indica el nombre del fichero, cuando se haya abierto
  private String nomFile = new String("");

  // Variable que contiene la escena 3D
  private engine3d.Scene scene;

  /** Creates new form StickMotion */
  public StickMotion() {
    initComponents();

    /* Inicio de otros componentes */
    editorsticky.DefaultSyntaxKit.initKit();
    editor.setContentType("text/sticky");

    /* Por defecto, el icono de guardar aparece deshabilitado */
    iconSaveStk.setEnabled(false);

    /* Deshacer y Rehacer */
    undoRedoManager = new javax.swing.undo.UndoManager();
    editor.getDocument().addUndoableEditListener(undoRedoManager);

    // loadScene();
  } // fin constructor StickMotion()

  /**
   * Charges the 3d scene and shows it on animationPanel
   */
  private Canvas3D loadCanvas3D() {
    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    Canvas3D canvas3D = new Canvas3D(config);
    canvas3D.setSize(460, 345);
    animationPanel.add(canvas3D);

    return canvas3D;
  }

  /**
   * This method is called from within the constructor to initialize the form.
   */
  // <editor-fold defaultstate="collapsed"
  // desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    areaApp = new javax.swing.JPanel();
    animationPanel = new javax.swing.JPanel();
    optionPanelResults = new javax.swing.JPanel();
    jScrollPane2 = new javax.swing.JScrollPane();
    editorResults = new javax.swing.JEditorPane();
    labeleditorResult = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    editor = new javax.swing.JEditorPane();
    statusPanel = new javax.swing.JPanel();
    statusLabel = new javax.swing.JLabel();
    currentStatusLabel = new javax.swing.JLabel();
    toolBar = new javax.swing.JToolBar();
    iconNewDoc = new javax.swing.JButton();
    iconLoadStk = new javax.swing.JButton();
    iconSaveStk = new javax.swing.JButton();
    iconExit = new javax.swing.JButton();
    separatorFile_Edit = new javax.swing.JToolBar.Separator();
    iconUndo = new javax.swing.JButton();
    iconRedo = new javax.swing.JButton();
    iconCopy = new javax.swing.JButton();
    iconPaste = new javax.swing.JButton();
    iconCut = new javax.swing.JButton();
    separatorEdit_Sticky = new javax.swing.JToolBar.Separator();
    iconInterpreter = new javax.swing.JButton();
    separatorSticky_Help = new javax.swing.JToolBar.Separator();
    iconHelp = new javax.swing.JButton();
    iconAbout = new javax.swing.JButton();
    menuBar = new javax.swing.JMenuBar();
    menuBarFile = new javax.swing.JMenu();
    optionFileNew = new javax.swing.JMenuItem();
    optionFileLoad = new javax.swing.JMenuItem();
    optionFileSave = new javax.swing.JMenuItem();
    optionFileSaveAs = new javax.swing.JMenuItem();
    optionFileSeparator1 = new javax.swing.JPopupMenu.Separator();
    optionFileInterpreter = new javax.swing.JMenuItem();
    optionFileSeparator2 = new javax.swing.JPopupMenu.Separator();
    optionFileExit = new javax.swing.JMenuItem();
    menuBarEdit = new javax.swing.JMenu();
    optionEditUndo = new javax.swing.JMenuItem();
    optionEditRedo = new javax.swing.JMenuItem();
    optionEditSeparator1 = new javax.swing.JPopupMenu.Separator();
    optionEditCut = new javax.swing.JMenuItem();
    optionEditCopy = new javax.swing.JMenuItem();
    optionEditPaste = new javax.swing.JMenuItem();
    optionEditSeparator2 = new javax.swing.JPopupMenu.Separator();
    optionEditSearch = new javax.swing.JMenuItem();
    optionEditClear = new javax.swing.JMenuItem();
    menuBarSticky = new javax.swing.JMenu();
    subStickyLoops = new javax.swing.JMenu();
    optionLoopWhile = new javax.swing.JMenuItem();
    optionLoopFor = new javax.swing.JMenuItem();
    subStickyCondition = new javax.swing.JMenu();
    optionCondIf = new javax.swing.JMenuItem();
    optionStickySeparator = new javax.swing.JPopupMenu.Separator();
    subStickyBasics = new javax.swing.JMenu();
    optionBasicAntArm = new javax.swing.JMenuItem();
    optionBasicArm = new javax.swing.JMenuItem();
    optionBasicNek = new javax.swing.JMenuItem();
    optionBasicBody = new javax.swing.JMenuItem();
    optionBasicLegs = new javax.swing.JMenuItem();
    optionBasicKnees = new javax.swing.JMenuItem();
    menuBarHelp = new javax.swing.JMenu();
    optionHelpHelp = new javax.swing.JMenuItem();
    optionHelpSeparator = new javax.swing.JPopupMenu.Separator();
    optionHelpSticky = new javax.swing.JMenuItem();
    optionHelpAbout = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("StickMotion: Editor de posturas, posiciones y movimientos");
    setResizable(false);

    areaApp.setBorder(javax.swing.BorderFactory.createTitledBorder(
        javax.swing.BorderFactory
            .createBevelBorder(javax.swing.border.BevelBorder.RAISED),
        "Entorno de trabajo"));

    animationPanel.setBorder(javax.swing.BorderFactory
        .createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
    animationPanel.setMaximumSize(new java.awt.Dimension(350, 350));
    animationPanel.setMinimumSize(new java.awt.Dimension(350, 350));
    animationPanel.setPreferredSize(new java.awt.Dimension(460, 345));

    javax.swing.GroupLayout animationPanelLayout = new javax.swing.GroupLayout(
        animationPanel);
    animationPanel.setLayout(animationPanelLayout);
    animationPanelLayout.setHorizontalGroup(animationPanelLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(
            0, 456, Short.MAX_VALUE));
    animationPanelLayout.setVerticalGroup(animationPanelLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(
            0, 341, Short.MAX_VALUE));

    optionPanelResults.setBorder(javax.swing.BorderFactory
        .createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

    editorResults.setEditable(false);
    editorResults.setOpaque(false);
    jScrollPane2.setViewportView(editorResults);

    labeleditorResult.setText("Resultado Interpretación:");

    javax.swing.GroupLayout optionPanelResultsLayout = new javax.swing.GroupLayout(
        optionPanelResults);
    optionPanelResults.setLayout(optionPanelResultsLayout);
    optionPanelResultsLayout.setHorizontalGroup(optionPanelResultsLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
            optionPanelResultsLayout.createSequentialGroup().addContainerGap()
                .addGroup(
                    optionPanelResultsLayout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labeleditorResult).addComponent(
                            jScrollPane2,
                            javax.swing.GroupLayout.Alignment.TRAILING,
                            javax.swing.GroupLayout.DEFAULT_SIZE, 432,
                            Short.MAX_VALUE)).addContainerGap()));
    optionPanelResultsLayout.setVerticalGroup(optionPanelResultsLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
            optionPanelResultsLayout.createSequentialGroup().addContainerGap()
                .addComponent(labeleditorResult).addPreferredGap(
                    javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2,
                    javax.swing.GroupLayout.PREFERRED_SIZE, 150,
                    javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(17,
                    Short.MAX_VALUE)));

    editor.addKeyListener(new java.awt.event.KeyAdapter() {
      @Override
      public void keyTyped(java.awt.event.KeyEvent evt) {
        editorKeyTyped(evt);
      }
    });
    jScrollPane1.setViewportView(editor);

    javax.swing.GroupLayout areaAppLayout = new javax.swing.GroupLayout(areaApp);
    areaApp.setLayout(areaAppLayout);
    areaAppLayout.setHorizontalGroup(areaAppLayout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        javax.swing.GroupLayout.Alignment.TRAILING,
        areaAppLayout.createSequentialGroup().addContainerGap().addComponent(
            jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 688,
            Short.MAX_VALUE).addPreferredGap(
            javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(
            areaAppLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(optionPanelResults,
                    javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(animationPanel,
                    javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap()));
    areaAppLayout
        .setVerticalGroup(areaAppLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                areaAppLayout
                    .createSequentialGroup()
                    .addContainerGap()
                    .addGroup(
                        areaAppLayout
                            .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1,
                                javax.swing.GroupLayout.Alignment.LEADING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, 567,
                                Short.MAX_VALUE)
                            .addGroup(
                                areaAppLayout
                                    .createSequentialGroup()
                                    .addComponent(animationPanel,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        345,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(optionPanelResults,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE))).addContainerGap()));

    statusPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(
        javax.swing.border.EtchedBorder.RAISED, java.awt.Color.gray,
        java.awt.Color.darkGray));

    statusLabel.setText("Estado:");

    currentStatusLabel.setText("Preparado");

    javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(
        statusPanel);
    statusPanel.setLayout(statusPanelLayout);
    statusPanelLayout.setHorizontalGroup(statusPanelLayout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        statusPanelLayout.createSequentialGroup().addContainerGap()
            .addComponent(statusLabel).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(currentStatusLabel).addContainerGap(1060,
                Short.MAX_VALUE)));
    statusPanelLayout.setVerticalGroup(statusPanelLayout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        statusPanelLayout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.BASELINE).addComponent(
            statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30,
            Short.MAX_VALUE).addComponent(currentStatusLabel)));

    toolBar.setFloatable(false);
    toolBar.setRollover(true);

    iconNewDoc.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gui/images/newDoc.png"))); // NOI18N
    iconNewDoc.setToolTipText("Nuevo Documento");
    iconNewDoc.setFocusable(false);
    iconNewDoc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    iconNewDoc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    iconNewDoc.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        iconNewDocActionPerformed(evt);
      }
    });
    toolBar.add(iconNewDoc);

    iconLoadStk.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gui/images/load.png"))); // NOI18N
    iconLoadStk.setToolTipText("Abrir un fichero .stk");
    iconLoadStk.setFocusable(false);
    iconLoadStk.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    iconLoadStk.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    iconLoadStk.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        iconLoadStkActionPerformed(evt);
      }
    });
    toolBar.add(iconLoadStk);

    iconSaveStk.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gui/images/save.png"))); // NOI18N
    iconSaveStk.setToolTipText("Guarda el código actual");
    iconSaveStk.setFocusable(false);
    iconSaveStk.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    iconSaveStk.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    iconSaveStk.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        iconSaveStkActionPerformed(evt);
      }
    });
    toolBar.add(iconSaveStk);

    iconExit.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gui/images/exit.png"))); // NOI18N
    iconExit.setToolTipText("Sale de la aplicación");
    iconExit.setFocusable(false);
    iconExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    iconExit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    iconExit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        iconExitActionPerformed(evt);
      }
    });
    toolBar.add(iconExit);
    toolBar.add(separatorFile_Edit);

    iconUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gui/images/undo.png"))); // NOI18N
    iconUndo.setToolTipText("Deshace la última acción de texto");
    iconUndo.setFocusable(false);
    iconUndo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    iconUndo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    iconUndo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        iconUndoActionPerformed(evt);
      }
    });
    toolBar.add(iconUndo);

    iconRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gui/images/redo.png"))); // NOI18N
    iconRedo.setToolTipText("Rehace la última acción textual");
    iconRedo.setFocusable(false);
    iconRedo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    iconRedo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    iconRedo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        iconRedoActionPerformed(evt);
      }
    });
    toolBar.add(iconRedo);

    iconCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gui/images/copy.png"))); // NOI18N
    iconCopy.setToolTipText("Copiar texto seleccionado");
    iconCopy.setFocusable(false);
    iconCopy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    iconCopy.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    iconCopy.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        iconCopyActionPerformed(evt);
      }
    });
    toolBar.add(iconCopy);

    iconPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gui/images/paste.png"))); // NOI18N
    iconPaste.setToolTipText("Pega el texto del portapapeles");
    iconPaste.setFocusable(false);
    iconPaste.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    iconPaste.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    iconPaste.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        iconPasteActionPerformed(evt);
      }
    });
    toolBar.add(iconPaste);

    iconCut.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gui/images/cut.png"))); // NOI18N
    iconCut.setToolTipText("Corta el texto seleccionado");
    iconCut.setFocusable(false);
    iconCut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    iconCut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    iconCut.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        iconCutActionPerformed(evt);
      }
    });
    toolBar.add(iconCut);
    toolBar.add(separatorEdit_Sticky);

    iconInterpreter.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gui/images/play.png"))); // NOI18N
    iconInterpreter.setToolTipText("Comienza la interpretación del código");
    iconInterpreter.setFocusable(false);
    iconInterpreter
        .setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    iconInterpreter.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    iconInterpreter.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        iconInterpreterActionPerformed(evt);
      }
    });
    toolBar.add(iconInterpreter);
    toolBar.add(separatorSticky_Help);

    iconHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gui/images/help.png"))); // NOI18N
    iconHelp.setToolTipText("Ayuda");
    iconHelp.setFocusable(false);
    iconHelp.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    iconHelp.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    iconHelp.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        iconHelpActionPerformed(evt);
      }
    });
    toolBar.add(iconHelp);

    iconAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gui/images/about.png"))); // NOI18N
    iconAbout.setToolTipText("Acerca de StickMotion");
    iconAbout.setFocusable(false);
    iconAbout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    iconAbout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    iconAbout.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        iconAboutActionPerformed(evt);
      }
    });
    toolBar.add(iconAbout);

    menuBarFile.setText("StickMotion");
    menuBarFile.setToolTipText("Menú de la aplicación");
    menuBarFile.addMenuListener(new javax.swing.event.MenuListener() {
      public void menuSelected(javax.swing.event.MenuEvent evt) {
      }

      public void menuDeselected(javax.swing.event.MenuEvent evt) {
        menuBarFileMenuDeselected(evt);
      }

      public void menuCanceled(javax.swing.event.MenuEvent evt) {
      }
    });

    optionFileNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
    optionFileNew.setText("Nuevo...");
    optionFileNew.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        optionFileNewMouseEntered(evt);
      }
    });
    optionFileNew.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionFileNewActionPerformed(evt);
      }
    });
    menuBarFile.add(optionFileNew);

    optionFileLoad.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
    optionFileLoad.setText("Abrir...");
    optionFileLoad.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        optionFileLoadMouseEntered(evt);
      }
    });
    optionFileLoad.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionFileLoadActionPerformed(evt);
      }
    });
    menuBarFile.add(optionFileLoad);

    optionFileSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
    optionFileSave.setText("Guardar...");
    optionFileSave.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        optionFileSaveMouseEntered(evt);
      }
    });
    optionFileSave.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionFileSaveActionPerformed(evt);
      }
    });
    menuBarFile.add(optionFileSave);

    optionFileSaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
    optionFileSaveAs.setText("Guardar como...");
    optionFileSaveAs.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionFileSaveAsActionPerformed(evt);
      }
    });
    menuBarFile.add(optionFileSaveAs);
    menuBarFile.add(optionFileSeparator1);

    optionFileInterpreter.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
    optionFileInterpreter.setText("Interpretar");
    optionFileInterpreter.setToolTipText("Interpreta el código del editor");
    optionFileInterpreter.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        optionFileInterpreterMouseEntered(evt);
      }
    });
    optionFileInterpreter
        .addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            optionFileInterpreterActionPerformed(evt);
          }
        });
    menuBarFile.add(optionFileInterpreter);
    menuBarFile.add(optionFileSeparator2);

    optionFileExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
    optionFileExit.setMnemonic('S');
    optionFileExit.setText("Salir");
    optionFileExit.setToolTipText("Salir de la aplicación");
    optionFileExit.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        optionFileExitMouseEntered(evt);
      }
    });
    optionFileExit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionFileExitActionPerformed(evt);
      }
    });
    menuBarFile.add(optionFileExit);

    menuBar.add(menuBarFile);

    menuBarEdit.setText("Edición");
    menuBarEdit.addMenuListener(new javax.swing.event.MenuListener() {
      public void menuSelected(javax.swing.event.MenuEvent evt) {
      }

      public void menuDeselected(javax.swing.event.MenuEvent evt) {
        menuBarEditMenuDeselected(evt);
      }

      public void menuCanceled(javax.swing.event.MenuEvent evt) {
      }
    });

    optionEditUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
    optionEditUndo.setText("Deshacer");
    optionEditUndo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionEditUndoActionPerformed(evt);
      }
    });
    menuBarEdit.add(optionEditUndo);

    optionEditRedo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
    optionEditRedo.setText("Rehacer");
    optionEditRedo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionEditRedoActionPerformed(evt);
      }
    });
    menuBarEdit.add(optionEditRedo);
    menuBarEdit.add(optionEditSeparator1);

    optionEditCut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
    optionEditCut.setText("Cortar");
    optionEditCut.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        optionEditCutMouseEntered(evt);
      }
    });
    optionEditCut.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionEditCutActionPerformed(evt);
      }
    });
    menuBarEdit.add(optionEditCut);

    optionEditCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
    optionEditCopy.setText("Copiar");
    optionEditCopy.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        optionEditCopyMouseEntered(evt);
      }
    });
    optionEditCopy.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionEditCopyActionPerformed(evt);
      }
    });
    menuBarEdit.add(optionEditCopy);

    optionEditPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
    optionEditPaste.setText("Pegar");
    optionEditPaste.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        optionEditPasteMouseEntered(evt);
      }
    });
    optionEditPaste.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionEditPasteActionPerformed(evt);
      }
    });
    menuBarEdit.add(optionEditPaste);
    menuBarEdit.add(optionEditSeparator2);

    optionEditSearch.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
    optionEditSearch.setText("Buscar/Reemp");
    optionEditSearch.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionEditSearchActionPerformed(evt);
      }
    });
    menuBarEdit.add(optionEditSearch);

    optionEditClear.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
    optionEditClear.setText("Limpiar");
    optionEditClear.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        optionEditClearMouseEntered(evt);
      }
    });
    optionEditClear.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionEditClearActionPerformed(evt);
      }
    });
    menuBarEdit.add(optionEditClear);

    menuBar.add(menuBarEdit);

    menuBarSticky.setText("Sticky");
    menuBarSticky.addMenuListener(new javax.swing.event.MenuListener() {
      public void menuSelected(javax.swing.event.MenuEvent evt) {
      }

      public void menuDeselected(javax.swing.event.MenuEvent evt) {
        menuBarStickyMenuDeselected(evt);
      }

      public void menuCanceled(javax.swing.event.MenuEvent evt) {
      }
    });

    subStickyLoops.setText("Bucles");
    subStickyLoops.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        subStickyLoopsMouseEntered(evt);
      }
    });

    optionLoopWhile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.ALT_MASK));
    optionLoopWhile.setText("Mientras(...)");
    optionLoopWhile.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionLoopWhileActionPerformed(evt);
      }
    });
    subStickyLoops.add(optionLoopWhile);

    optionLoopFor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
    optionLoopFor.setText("Para(...;...;...)");
    optionLoopFor.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionLoopForActionPerformed(evt);
      }
    });
    subStickyLoops.add(optionLoopFor);

    menuBarSticky.add(subStickyLoops);

    subStickyCondition.setText("Condición");
    subStickyCondition.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        subStickyConditionMouseEntered(evt);
      }
    });

    optionCondIf.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
    optionCondIf.setText("Si(...)");
    optionCondIf.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionCondIfActionPerformed(evt);
      }
    });
    subStickyCondition.add(optionCondIf);

    menuBarSticky.add(subStickyCondition);
    menuBarSticky.add(optionStickySeparator);

    subStickyBasics.setText("Movimientos");
    subStickyBasics.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        subStickyBasicsMouseEntered(evt);
      }
    });

    optionBasicAntArm.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
    optionBasicAntArm.setText("Antebrazo");
    optionBasicAntArm.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionBasicAntArmActionPerformed(evt);
      }
    });
    subStickyBasics.add(optionBasicAntArm);

    optionBasicArm.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK));
    optionBasicArm.setText("Brazo");
    optionBasicArm.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionBasicArmActionPerformed(evt);
      }
    });
    subStickyBasics.add(optionBasicArm);

    optionBasicNek.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
    optionBasicNek.setText("Cuello");
    optionBasicNek.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionBasicNekActionPerformed(evt);
      }
    });
    subStickyBasics.add(optionBasicNek);

    optionBasicBody.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
    optionBasicBody.setText("Cuerpo");
    optionBasicBody.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionBasicBodyActionPerformed(evt);
      }
    });
    subStickyBasics.add(optionBasicBody);

    optionBasicLegs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK));
    optionBasicLegs.setText("Piernas");
    optionBasicLegs.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionBasicLegsActionPerformed(evt);
      }
    });
    subStickyBasics.add(optionBasicLegs);

    optionBasicKnees.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
    optionBasicKnees.setText("Rodillas");
    optionBasicKnees.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionBasicKneesActionPerformed(evt);
      }
    });
    subStickyBasics.add(optionBasicKnees);

    menuBarSticky.add(subStickyBasics);

    menuBar.add(menuBarSticky);

    menuBarHelp.setText("Ayuda");
    menuBarHelp.setToolTipText("Menú de ayuda de la aplicación");
    menuBarHelp.addMenuListener(new javax.swing.event.MenuListener() {
      public void menuSelected(javax.swing.event.MenuEvent evt) {
      }

      public void menuDeselected(javax.swing.event.MenuEvent evt) {
        menuBarHelpMenuDeselected(evt);
      }

      public void menuCanceled(javax.swing.event.MenuEvent evt) {
      }
    });

    optionHelpHelp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_F1, 0));
    optionHelpHelp.setText("Ayuda");
    optionHelpHelp.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        optionHelpHelpMouseEntered(evt);
      }
    });
    optionHelpHelp.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionHelpHelpActionPerformed(evt);
      }
    });
    menuBarHelp.add(optionHelpHelp);
    menuBarHelp.add(optionHelpSeparator);

    optionHelpSticky.setText("Sobre Sticky");
    optionHelpSticky.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        optionHelpStickyMouseEntered(evt);
      }
    });
    optionHelpSticky.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionHelpStickyActionPerformed(evt);
      }
    });
    menuBarHelp.add(optionHelpSticky);

    optionHelpAbout.setText("Acerca de...");
    optionHelpAbout.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        optionHelpAboutMouseEntered(evt);
      }
    });
    optionHelpAbout.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionHelpAboutActionPerformed(evt);
      }
    });
    menuBarHelp.add(optionHelpAbout);

    menuBar.add(menuBarHelp);

    setJMenuBar(menuBar);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
        getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addComponent(toolBar,
        javax.swing.GroupLayout.DEFAULT_SIZE, 1220, Short.MAX_VALUE).addGroup(
        layout.createSequentialGroup().addContainerGap().addComponent(
            statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap()).addGroup(
        layout.createSequentialGroup().addContainerGap().addComponent(areaApp,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap()));
    layout.setVerticalGroup(layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        javax.swing.GroupLayout.Alignment.TRAILING,
        layout.createSequentialGroup().addComponent(toolBar,
            javax.swing.GroupLayout.PREFERRED_SIZE, 25,
            javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(
            javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(
            areaApp, javax.swing.GroupLayout.PREFERRED_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(
            javax.swing.LayoutStyle.ComponentPlacement.RELATED,
            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)));

    pack();
  }// </editor-fold>//GEN-END:initComponents

  /* --------------------------------------------------------------------- */
  /* --------------------------- MENÚ STATUS TOOL ------------------------ */
  /* --------------------------------------------------------------------- */
  // Menú StickMotion
  private void menuBarFileMenuDeselected(javax.swing.event.MenuEvent evt) {// GEN-FIRST:event_menuBarFileMenuDeselected
    // Establece el estado como Preparado
    currentStatusLabel.setText("Preparado");
  }// GEN-LAST:event_menuBarFileMenuDeselected

  /* Aqui empiezan las funciones para establecer el contenido del text area */
  private void optionFileNewMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_optionFileNewMouseEntered
    currentStatusLabel.setText("Crea un nuevo fichero de programación Sticky");
  }// GEN-LAST:event_optionFileNewMouseEntered

  private void optionFileLoadMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_optionFileLoadMouseEntered
    currentStatusLabel.setText("Carga un fichero *.stk existente");
  }// GEN-LAST:event_optionFileLoadMouseEntered

  private void optionFileSaveMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_optionFileSaveMouseEntered
    currentStatusLabel.setText("Guarda el fichero actual de programación");
  }// GEN-LAST:event_optionFileSaveMouseEntered

  private void optionFileInterpreterMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_optionFileInterpreterMouseEntered
    // Cuando se pulse en la opción Interpretar, lanzar el analizador
    currentStatusLabel.setText("Lanza el intérprete de Sticky");
  }// GEN-LAST:event_optionFileInterpreterMouseEntered

  private void optionFileExitMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_optionFileExitMouseEntered
    currentStatusLabel
        .setText("Cierra la aplicación y sale de ella... ¿Es eso lo que quieres?");
  }// GEN-LAST:event_optionFileExitMouseEntered

  // Menú Edición
  private void menuBarEditMenuDeselected(javax.swing.event.MenuEvent evt) {// GEN-FIRST:event_menuBarEditMenuDeselected
    currentStatusLabel.setText("Preparado");
  }// GEN-LAST:event_menuBarEditMenuDeselected

  private void optionEditCopyMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_optionEditCopyMouseEntered
    currentStatusLabel.setText("Copia el texto seleccionado");
  }// GEN-LAST:event_optionEditCopyMouseEntered

  private void optionEditPasteMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_optionEditPasteMouseEntered
    currentStatusLabel.setText("Pega el contenido del portapapeles");
  }// GEN-LAST:event_optionEditPasteMouseEntered

  private void optionEditCutMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_optionEditCutMouseEntered
    currentStatusLabel.setText("Corta el contenido seleccionado");
  }// GEN-LAST:event_optionEditCutMouseEntered

  private void optionEditClearMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_optionEditClearMouseEntered
    currentStatusLabel.setText("Limpia el contenido del área de texto");
  }// GEN-LAST:event_optionEditClearMouseEntered

  // Menú Sticky
  private void menuBarStickyMenuDeselected(javax.swing.event.MenuEvent evt) {// GEN-FIRST:event_menuBarStickyMenuDeselected
    currentStatusLabel.setText("Preparado");
  }// GEN-LAST:event_menuBarStickyMenuDeselected

  private void subStickyLoopsMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_subStickyLoopsMouseEntered
    currentStatusLabel.setText("Bucles: mientras y para");
  }// GEN-LAST:event_subStickyLoopsMouseEntered

  private void subStickyConditionMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_subStickyConditionMouseEntered
    currentStatusLabel.setText("Condicional si");
  }// GEN-LAST:event_subStickyConditionMouseEntered

  private void subStickyBasicsMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_subStickyBasicsMouseEntered
    currentStatusLabel.setText("Conjunto de movimientos básicos");
  }// GEN-LAST:event_subStickyBasicsMouseEntered

  // Menú ayuda
  private void menuBarHelpMenuDeselected(javax.swing.event.MenuEvent evt) {// GEN-FIRST:event_menuBarHelpMenuDeselected
    currentStatusLabel.setText("Preparado");
  }// GEN-LAST:event_menuBarHelpMenuDeselected

  private void optionHelpHelpMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_optionHelpHelpMouseEntered
    currentStatusLabel.setText("Abre la ayuda de StickMotion");
  }// GEN-LAST:event_optionHelpHelpMouseEntered

  private void optionHelpStickyMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_optionHelpStickyMouseEntered
    currentStatusLabel.setText("Acerca del lenguaje de programación Sticky");
  }// GEN-LAST:event_optionHelpStickyMouseEntered

  private void optionHelpAboutMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_optionHelpAboutMouseEntered
    currentStatusLabel.setText("Acerca de los autores");
  }// GEN-LAST:event_optionHelpAboutMouseEntered

  /* --------------------------------------------------------------------- */
  /* --------------------------- MENÚ STICKMOTION ------------------------ */
  /* --------------------------------------------------------------------- */

  private void iconNewDocActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_iconNewDocActionPerformed
    // Cuando pulse sobre el icono de nuevo documento
    optionFileNewActionPerformed(evt); // Se llama a la función correspondiente
  }// GEN-LAST:event_iconNewDocActionPerformed

  private void iconLoadStkActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_iconLoadStkActionPerformed
    // Si ha pulsado en el icono de abrir un archivo
    optionFileLoadActionPerformed(evt);
  }// GEN-LAST:event_iconLoadStkActionPerformed

  private void iconSaveStkActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_iconSaveStkActionPerformed
    // Cuando pulse sobre el icono de guardar cambios
    optionFileSaveActionPerformed(evt); // Llamamos a esa función
  }// GEN-LAST:event_iconSaveStkActionPerformed

  private void optionFileSaveAsActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionFileSaveAsActionPerformed
    // Guardar el fichero mostrando el diágolo de guardar
    javax.swing.JFileChooser saveAs = new javax.swing.JFileChooser();
    int opc = saveAs.showSaveDialog(this); // Mostramos el diálogo

    // Comprobamos si se ha pulsado sobre aceptar
    if (opc == javax.swing.JFileChooser.APPROVE_OPTION) {
      nomFile = saveAs.getSelectedFile().getAbsolutePath();
      saveStk(); // Guardamos
    }
  }// GEN-LAST:event_optionFileSaveAsActionPerformed

  private void optionFileInterpreterActionPerformed(
      java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionFileInterpreterActionPerformed
    // Acción cuando el usuario clickee en Interpretar (menú y atajo teclado)
    editorResults.setText("Interpretando...\n");

    // TODO: Hay que enlazar esto con el ANTLR de forma que use el scene
    // y devuelva un string con los errores para mostrar en editorResults

    engine3d.Scene scene = new engine3d.Scene();

    scene.setTime(1000);
    // Going right
    scene.rotateStickman((float) (Math.PI / 2), 0, 1000);
    scene.addTime(1000);
    scene.moveForwardStickman(1, 1000);
    scene.addTime(1000);
    // Going left
    scene.rotateStickman((float) -Math.PI, 0, 1000);
    scene.addTime(1000);
    scene.moveForwardStickman(1, 1000);
    scene.addTime(1000);
    // Look to the front
    scene.rotateStickman((float) Math.PI / 2, 0, 1000);

    scene.start(loadCanvas3D());

  }// GEN-LAST:event_optionFileInterpreterActionPerformed

  private void iconInterpreterActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_iconInterpreterActionPerformed
    // Cuando se pulse en el botón (icono) de la barra de tareas
    optionFileInterpreterActionPerformed(evt);
  }// GEN-LAST:event_iconInterpreterActionPerformed

  private void iconExitActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_iconExitActionPerformed
    // Cuando pulse sobre el icono de salir de la aplicación
    optionFileExitActionPerformed(evt); // Llama y sale de la aplicación
  }// GEN-LAST:event_iconExitActionPerformed

  private void optionFileNewActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionFileNewActionPerformed
    // Cuando se pulsa sobre un nuevo documento
    // Si existen cambios sin guardar y no está en blanco (tiene código)
    if (!documentSaved && documentModified && !editor.getText().equals("")) {
      ImageIcon icon = new ImageIcon(this.getClass().getResource(
          "images/warning2.png"));
      int opc = javax.swing.JOptionPane.showConfirmDialog(this,
          "Cuidado, el contenido del editor ha cambiado, \npero"
              + " las modificaciones no han sido guardadas...\n"
              + "            ¿Desea conservar los cambios?",
          "Documento con cambios",
          javax.swing.JOptionPane.YES_NO_CANCEL_OPTION,
          javax.swing.JOptionPane.WARNING_MESSAGE, icon);
      // Ahora analizamos el resultado
      if (opc == JOptionPane.YES_OPTION && !nomFile.equals("")) { // Guardar
        saveStk(); // Guardamos directamente porque ya existe el nomFile
        editor.setText(""); // Se crea el fichero nuevo (se limpia)
      } else if (opc == JOptionPane.YES_OPTION && nomFile.equals("")) {
        optionFileSaveAsActionPerformed(evt); // Se llama a guardar fichero
        editor.setText(""); // Se crea el fichero nuevo (se limpia)
      } else if (opc == JOptionPane.NO_OPTION) // Si pulsa en no, limpiamos
        editor.setText(""); // Se crea el fichero nuevo (se limpia)
      // Si cierra la ventana de diálogo o pulsa sobre cancelar, no hacemos nada

      // También hay que limpiar el área de resultados
      editorResults.setText("");
    }
  }// GEN-LAST:event_optionFileNewActionPerformed

  private void optionFileLoadActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionFileLoadActionPerformed
    // Abre un fichero .stk y lo carga en el editor
    javax.swing.JFileChooser openAs = new javax.swing.JFileChooser();
    openAs.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
    // openAs.setAcceptAllFileFilterUsed(true);
    int opc = openAs.showOpenDialog(this); // Se muestra el diálogo

    // Definición de variables temporales
    String content = "", line = "";

    // Ahora comprobamos si ha pulsado sobre aceptar
    if (opc == javax.swing.JFileChooser.APPROVE_OPTION) {
      // Si se ha pulsado, obtenemos la ruta absoluta al archivo
      nomFile = openAs.getSelectedFile().getAbsolutePath();
      // Ahora comprobamos si es una extensión válida de fichero (.stk)
      if (!nomFile.endsWith(".stk")) {
        // Error e = new Error(this, true);
        // e.setVisible(true);
      } else {
        documentSaved = true; // documento en su estado actual, guardado
        documentModified = false; // documento no modificado, actualizado

        // El objeto FileReader lee desde un fichero de texto
        FileReader fr = null;
        // El objeto BufferedReader permite leer líneas completas
        BufferedReader br = null;

        // Leemos el archivo y lo insertamos en el editor
        try {
          // Apertura del fichero y vamos leyendo línea a línea
          fr = new FileReader(nomFile);
          br = new BufferedReader(fr);

          // Lectura línea línea del fichero hasta llegar a EOF
          while ((line = br.readLine()) != null)
            // Debemos insertar el salto de línea a mano
            content += (line + '\n');
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          // En finally cerramos el fichero, asegurando que se cierra tanto
          // si todo va bien como si salta una excepcion.
          try {
            if (null != fr)
              fr.close();
          } catch (Exception e2) {
            e2.printStackTrace();
          }
        } // fin finally
        // Una vez leído el contenido, lo insertamos en el editor
        editor.setText(content);
      } // fin else
    } // fin if(opc == x)
  }// GEN-LAST:event_optionFileLoadActionPerformed

  private void optionFileSaveActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionFileSaveActionPerformed
    // Guarda el codigo del editor en un fichero
    // Documento abierto, modificado y sin guardar dichos cambios
    if (!documentSaved && documentModified && !nomFile.equals(""))
      saveStk(); // Guarda directamente con el mismo nombre
    // Documento nuevo que será guardado por primera vez
    else if (!documentSaved && documentModified && nomFile.equals(""))
      optionFileSaveAsActionPerformed(evt); // Para guardar por primera vez
  }// GEN-LAST:event_optionFileSaveActionPerformed

  private void optionFileExitActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionFileExitActionPerformed
    // Preguntar antes de salir si hay algo abierto y si se quiere guardar
    System.exit(0);
  }// GEN-LAST:event_optionFileExitActionPerformed

  /* --------------------------------------------------------------------- */
  /* ---------------------------- MENÚ EDICIÓN --------------------------- */
  /* --------------------------------------------------------------------- */

  private void iconUndoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_iconUndoActionPerformed
    // Si el usuario pincha desde el icono, llamamos a su función manejadora
    optionEditUndoActionPerformed(evt);
  }// GEN-LAST:event_iconUndoActionPerformed

  private void iconRedoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_iconRedoActionPerformed
    // Si el usuario pincha desde el icono, llamamos a su función manejadora
    optionEditRedoActionPerformed(evt);
  }// GEN-LAST:event_iconRedoActionPerformed

  private void iconCutActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_iconCutActionPerformed
    optionEditCutActionPerformed(evt);
  }// GEN-LAST:event_iconCutActionPerformed

  private void iconCopyActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_iconCopyActionPerformed
    optionEditCopyActionPerformed(evt);
  }// GEN-LAST:event_iconCopyActionPerformed

  private void iconPasteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_iconPasteActionPerformed
    optionEditPasteActionPerformed(evt);
  }// GEN-LAST:event_iconPasteActionPerformed

  private void optionEditCutActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionEditCutActionPerformed
    editor.cut();
  }// GEN-LAST:event_optionEditCutActionPerformed

  private void optionEditCopyActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionEditCopyActionPerformed
    editor.copy();
  }// GEN-LAST:event_optionEditCopyActionPerformed

  private void optionEditPasteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionEditPasteActionPerformed
    editor.paste();
  }// GEN-LAST:event_optionEditPasteActionPerformed

  private void optionEditClearActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionEditClearActionPerformed
    editor.setText(""); // Limpiamos el área de texto
  }// GEN-LAST:event_optionEditClearActionPerformed

  private void optionEditUndoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionEditUndoActionPerformed
    if (undoRedoManager.canUndo()) // Si se puede deshacer
      undoRedoManager.undo(); // Deshacemos
  }// GEN-LAST:event_optionEditUndoActionPerformed

  private void optionEditRedoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionEditRedoActionPerformed
    if (undoRedoManager.canRedo()) // Si se puede rehacer
      undoRedoManager.redo(); // Rehacemos
  }// GEN-LAST:event_optionEditRedoActionPerformed

  /* --------------------------------------------------------------------- */
  /* ---------------------------- MENÚ STICKY ---------------------------- */
  /* --------------------------------------------------------------------- */

  private void optionLoopWhileActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionLoopWhileActionPerformed
    // Insertar el código correspondiente al bucle mientras
    String condition = JOptionPane
        .showInputDialog("Introduzca la condición del bucle:");

    Caret pos = editor.getCaret();
    String mientras = "mientras(" + condition + ") {\n\n}\n";
    try {
      editor.getDocument().insertString(pos.getDot(), mientras, null);
    } catch (BadLocationException ex) {
      Logger.getLogger(StickMotion.class.getName()).log(Level.SEVERE, null, ex);
    }
  }// GEN-LAST:event_optionLoopWhileActionPerformed

  private void optionLoopForActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionLoopForActionPerformed
    // Insertar el código correspondiente al bucle para
    String var = JOptionPane
        .showInputDialog("¿Qué variable deseas para iterar?");

    Caret pos = editor.getCaret();
    String para = "para(" + var + "; " + var + "; " + var + ") {\n\n}\n";
    try {
      editor.getDocument().insertString(pos.getDot(), para, null);
    } catch (BadLocationException ex) {
      Logger.getLogger(StickMotion.class.getName()).log(Level.SEVERE, null, ex);
    }
  }// GEN-LAST:event_optionLoopForActionPerformed

  private void optionCondIfActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionCondIfActionPerformed
    // Insertar el código correspondiente al condicional Si
    // Definimos las posibles opciones que se ofrecen al usuario
    String[] options = { "if", "if - else", "if{} - else{}" };
    // Mostramos ahora el cuadro de diálogo convenientemente "castingzado"
    String opc = (String) JOptionPane.showInputDialog(null,
        "Seleccione el tipo de construcción if",
        "Insercción de estructura condicional", JOptionPane.QUESTION_MESSAGE,
        null, options, // Las posibles opciones (array)
        options[2]); // La opción por defecto

    // Buscamos ahora el patrón seleccionado (no podemos insertarlo
    // directamente)
    String si = new String();
    if (opc.equals(options[0])) // if
      si = "si()\n\t";
    else if (opc.equals(options[1])) // if - else
      si = "si()\n\nsino\n\n";
    else if (opc.equals(options[2])) // if - else multimlínea
      si = "si() {\n\n} sino {\n\n}";

    // Ahora insertamos la construcción if terminada en la posición especificada
    Caret pos = editor.getCaret();
    try {
      editor.getDocument().insertString(pos.getDot(), si, null);
    } catch (BadLocationException ex) {
      Logger.getLogger(StickMotion.class.getName()).log(Level.SEVERE, null, ex);
    }
  }// GEN-LAST:event_optionCondIfActionPerformed

  private void optionBasicAntArmActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionBasicAntArmActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_optionBasicAntArmActionPerformed

  private void optionBasicArmActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionBasicArmActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_optionBasicArmActionPerformed

  private void optionBasicNekActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionBasicNekActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_optionBasicNekActionPerformed

  private void optionBasicBodyActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionBasicBodyActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_optionBasicBodyActionPerformed

  private void optionBasicLegsActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionBasicLegsActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_optionBasicLegsActionPerformed

  private void optionBasicKneesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionBasicKneesActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_optionBasicKneesActionPerformed

  /* -------------------------------------------------------------------- */
  /* ---------------------------- MENÚ AYUDA ---------------------------- */
  /* -------------------------------------------------------------------- */

  private void optionHelpHelpActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionHelpHelpActionPerformed
    // Cuando se pulse en Ayuda, mostrar el diágolo correspondiente
    Help Helpy = new Help(this, documentSaved);
    Helpy.setModal(true); // La hacemos modal
    Helpy.setVisible(true); // Mostramos la ventana de diálogo
  }// GEN-LAST:event_optionHelpHelpActionPerformed

  private void optionHelpStickyActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionHelpStickyActionPerformed
    // Cuando se pulse sobre Acerca de Sticky
    AboutSticky Sticky = new AboutSticky(this, documentSaved);
    Sticky.setModal(true); // La hacemos modal
    Sticky.setVisible(true); // Mostramos la ventana de diálogo
  }// GEN-LAST:event_optionHelpStickyActionPerformed

  private void optionHelpAboutActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionHelpAboutActionPerformed
    // Cuando quiera ver los créditos,hacemos visible dicha clase
    AboutStickMotion Credits = new AboutStickMotion(this, documentSaved);
    Credits.setModal(true); // La hacemos modal
    Credits.setVisible(true); // Mostramos la ventana de diálogo
  }// GEN-LAST:event_optionHelpAboutActionPerformed

  private void iconAboutActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_iconAboutActionPerformed
    // Acción al pinchar en el icono de acerca de stickmotion
    optionHelpAboutActionPerformed(evt);
  }// GEN-LAST:event_iconAboutActionPerformed

  private void iconHelpActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_iconHelpActionPerformed
    // Cuando se pulse en el icono, llamar al método correspondiente
    optionHelpHelpActionPerformed(evt);
  }// GEN-LAST:event_iconHelpActionPerformed

  /* --------------------------------------------------------------- */
  /* ----------------------------- OTRAS --------------------------- */
  /* --------------------------------------------------------------- */

  private void saveStk() {
    // Guarda el código del editor a un fichero
    FileWriter f = null;
    PrintWriter pw = null;
    try {
      f = new FileWriter(nomFile);
      pw = new PrintWriter(f);
      pw.println(editor.getText());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        // Nuevamente aprovechamos el finally para asegurarnos el cierre
        if (null != f)
          f.close();
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
    // Modificamos los flags correspondientes
    documentModified = false; // Fichero sin modificaciones actuales
    documentSaved = true; // Fichero guardado
    iconSaveStk.setEnabled(false); // No se puede guardar, es actual.
  }

  private void editorKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_editorKeyTyped
    // Cuando se modifica el contenido del editor se dispara este evento
    documentModified = true; // Se ha modificado el contenido del editor
    documentSaved = false; // Dichos cambios no se han guardado
    iconSaveStk.setEnabled(true); // Habilitamos el icono de guardar
  }// GEN-LAST:event_editorKeyTyped

  private void optionEditSearchActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_optionEditSearchActionPerformed

  }// GEN-LAST:event_optionEditSearchActionPerformed

  /**
   * @param args
   *          the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new StickMotion().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel animationPanel;
  private javax.swing.JPanel areaApp;
  private javax.swing.JLabel currentStatusLabel;
  private javax.swing.JEditorPane editor;
  private javax.swing.JEditorPane editorResults;
  private javax.swing.JButton iconAbout;
  private javax.swing.JButton iconCopy;
  private javax.swing.JButton iconCut;
  private javax.swing.JButton iconExit;
  private javax.swing.JButton iconHelp;
  private javax.swing.JButton iconInterpreter;
  private javax.swing.JButton iconLoadStk;
  private javax.swing.JButton iconNewDoc;
  private javax.swing.JButton iconPaste;
  private javax.swing.JButton iconRedo;
  private javax.swing.JButton iconSaveStk;
  private javax.swing.JButton iconUndo;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JLabel labeleditorResult;
  private javax.swing.JMenuBar menuBar;
  private javax.swing.JMenu menuBarEdit;
  private javax.swing.JMenu menuBarFile;
  private javax.swing.JMenu menuBarHelp;
  private javax.swing.JMenu menuBarSticky;
  private javax.swing.JMenuItem optionBasicAntArm;
  private javax.swing.JMenuItem optionBasicArm;
  private javax.swing.JMenuItem optionBasicBody;
  private javax.swing.JMenuItem optionBasicKnees;
  private javax.swing.JMenuItem optionBasicLegs;
  private javax.swing.JMenuItem optionBasicNek;
  private javax.swing.JMenuItem optionCondIf;
  private javax.swing.JMenuItem optionEditClear;
  private javax.swing.JMenuItem optionEditCopy;
  private javax.swing.JMenuItem optionEditCut;
  private javax.swing.JMenuItem optionEditPaste;
  private javax.swing.JMenuItem optionEditRedo;
  private javax.swing.JMenuItem optionEditSearch;
  private javax.swing.JPopupMenu.Separator optionEditSeparator1;
  private javax.swing.JPopupMenu.Separator optionEditSeparator2;
  private javax.swing.JMenuItem optionEditUndo;
  private javax.swing.JMenuItem optionFileExit;
  private javax.swing.JMenuItem optionFileInterpreter;
  private javax.swing.JMenuItem optionFileLoad;
  private javax.swing.JMenuItem optionFileNew;
  private javax.swing.JMenuItem optionFileSave;
  private javax.swing.JMenuItem optionFileSaveAs;
  private javax.swing.JPopupMenu.Separator optionFileSeparator1;
  private javax.swing.JPopupMenu.Separator optionFileSeparator2;
  private javax.swing.JMenuItem optionHelpAbout;
  private javax.swing.JMenuItem optionHelpHelp;
  private javax.swing.JPopupMenu.Separator optionHelpSeparator;
  private javax.swing.JMenuItem optionHelpSticky;
  private javax.swing.JMenuItem optionLoopFor;
  private javax.swing.JMenuItem optionLoopWhile;
  private javax.swing.JPanel optionPanelResults;
  private javax.swing.JPopupMenu.Separator optionStickySeparator;
  private javax.swing.JToolBar.Separator separatorEdit_Sticky;
  private javax.swing.JToolBar.Separator separatorFile_Edit;
  private javax.swing.JToolBar.Separator separatorSticky_Help;
  private javax.swing.JLabel statusLabel;
  private javax.swing.JPanel statusPanel;
  private javax.swing.JMenu subStickyBasics;
  private javax.swing.JMenu subStickyCondition;
  private javax.swing.JMenu subStickyLoops;
  private javax.swing.JToolBar toolBar;
  // End of variables declaration//GEN-END:variables

}
