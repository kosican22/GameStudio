package sk.ness.minesweeper.swingui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Formatter;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

import sk.ness.minesweeper.Minesweeper;
import sk.ness.minesweeper.Settings;
import sk.ness.minesweeper.UserInterface;
import sk.ness.minesweeper.core.Field;
import sk.ness.minesweeper.core.GameState;
import sk.ness.minesweeper.core.Tile;

public class SwingUI extends javax.swing.JFrame implements UserInterface, MouseListener {
    private Field field;
    
    public SwingUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {}
        
        initComponents();
        
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/img/logo.gif")).getImage());
        setVisible(true);
        
        if(Minesweeper.getInstance().getSetting().equals(Settings.BEGINNER)){
            beginnerMenuItem.setSelected(true);
        } else if(Minesweeper.getInstance().getSetting().equals(Settings.INTERMEDIATE)){
            intermediateMenuItem.setSelected(true);
        } else if(Minesweeper.getInstance().getSetting().equals(Settings.EXPERT)){
            expertMenuItem.setSelected(true);
        }
    }
    
    public void newGameStarted(Field field) {
    	this.field=field;
    	contentPanel.removeAll(); // Odstrani vsetky komponenty, ktore obsahuje contentPanel
    	contentPanel.setLayout(new GridLayout(field.getRowCount(), field.getColumnCount()));
    	
		for (int r = 0; r < field.getRowCount(); r++)
			for (int c = 0; c < field.getColumnCount(); c++) {
				TileComponent tc = new TileComponent(field.getTile(r, c), r, c);
				tc.addMouseListener(this);
				contentPanel.add(tc);
			}
		pack();
		update();
        // throw new UnsupportedOperationException("Method newGameStarted not yet implemented");
    }
    
    public void mousePressed(MouseEvent e) {
//      throw new UnsupportedOperationException("Method mousePressed not yet implemented");
		if (field.getState() == GameState.PLAYING) {
			TileComponent tc = (TileComponent) e.getSource();

			if (SwingUtilities.isLeftMouseButton(e)) {
				field.openTile(tc.getRow(), tc.getColumn());
			}

			else if (SwingUtilities.isRightMouseButton(e)) {
				field.markTile(tc.getRow(), tc.getColumn());
			}

			update();
		}	
    }
    
    private void setMinesLeftLabelText() {
        StringBuilder sb = new StringBuilder();
        new Formatter(sb).format("%03d", field.getRemainingMineCount());
        
        minesLeftLabel.setText(sb.toString());
    }
    
    private void setTimeLabelText() {
        throw new UnsupportedOperationException("Method setTimeLabelText not yet implemented");
    }
    
    public void update() {
		for (Component component : contentPanel.getComponents()) {
			if (component instanceof TileComponent) {
				((TileComponent) component).updateStyle();
			}
		}
		setMinesLeftLabelText();
		
//      throw new UnsupportedOperationException("Method update not yet implemented");
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        buttonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        topPanel = new javax.swing.JPanel();
        infoPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        minesLeftLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        timeLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        newButton = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        gameMenu = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        beginnerMenuItem = new javax.swing.JRadioButtonMenuItem();
        intermediateMenuItem = new javax.swing.JRadioButtonMenuItem();
        expertMenuItem = new javax.swing.JRadioButtonMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        exitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minesweeper");
        setResizable(false);
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        topPanel.setLayout(new java.awt.BorderLayout());

        topPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 2, 5), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        infoPanel.setLayout(new java.awt.BorderLayout());

        infoPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        minesLeftLabel.setBackground(java.awt.Color.black);
        minesLeftLabel.setFont(new java.awt.Font("DialogInput", 1, 24));
        minesLeftLabel.setForeground(java.awt.Color.red);
        minesLeftLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minesLeftLabel.setText("888");
        minesLeftLabel.setMaximumSize(new java.awt.Dimension(50, 30));
        minesLeftLabel.setMinimumSize(new java.awt.Dimension(50, 30));
        minesLeftLabel.setOpaque(true);
        minesLeftLabel.setPreferredSize(new java.awt.Dimension(50, 30));
        jPanel2.add(minesLeftLabel, java.awt.BorderLayout.CENTER);

        infoPanel.add(jPanel2, java.awt.BorderLayout.WEST);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        timeLabel.setBackground(java.awt.Color.black);
        timeLabel.setFont(new java.awt.Font("DialogInput", 1, 24));
        timeLabel.setForeground(java.awt.Color.red);
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLabel.setText("888");
        timeLabel.setMaximumSize(new java.awt.Dimension(50, 30));
        timeLabel.setMinimumSize(new java.awt.Dimension(50, 30));
        timeLabel.setOpaque(true);
        timeLabel.setPreferredSize(new java.awt.Dimension(50, 30));
        jPanel3.add(timeLabel, java.awt.BorderLayout.CENTER);

        infoPanel.add(jPanel3, java.awt.BorderLayout.EAST);

        newButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/smile.gif")));
        newButton.setFocusPainted(false);
        newButton.setFocusable(false);
        newButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        jPanel4.add(newButton);

        infoPanel.add(jPanel4, java.awt.BorderLayout.CENTER);

        topPanel.add(infoPanel, java.awt.BorderLayout.CENTER);

        jPanel1.add(topPanel, java.awt.BorderLayout.NORTH);

        contentPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(3, 5, 5, 5), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        jPanel1.add(contentPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        gameMenu.setMnemonic('g');
        gameMenu.setText("Game");
        newMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        newMenuItem.setMnemonic('n');
        newMenuItem.setText("New");
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuItemActionPerformed(evt);
            }
        });

        gameMenu.add(newMenuItem);

        gameMenu.add(jSeparator1);

        buttonGroup.add(beginnerMenuItem);
        beginnerMenuItem.setMnemonic('b');
        beginnerMenuItem.setText("Beginner");
        beginnerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beginnerMenuItemActionPerformed(evt);
            }
        });

        gameMenu.add(beginnerMenuItem);

        buttonGroup.add(intermediateMenuItem);
        intermediateMenuItem.setMnemonic('i');
        intermediateMenuItem.setText("Intermediate");
        intermediateMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intermediateMenuItemActionPerformed(evt);
            }
        });

        gameMenu.add(intermediateMenuItem);

        buttonGroup.add(expertMenuItem);
        expertMenuItem.setMnemonic('e');
        expertMenuItem.setText("Expert");
        expertMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expertMenuItemActionPerformed(evt);
            }
        });

        gameMenu.add(expertMenuItem);

        gameMenu.add(jSeparator3);
        gameMenu.add(jSeparator2);

        exitMenuItem.setMnemonic('e');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });

        gameMenu.add(exitMenuItem);

        menuBar.add(gameMenu);

        setJMenuBar(menuBar);

    }// </editor-fold>//GEN-END:initComponents
    
    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        newMenuItemActionPerformed(null);
    }//GEN-LAST:event_newButtonActionPerformed
        
    private void expertMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expertMenuItemActionPerformed
        Minesweeper.getInstance().setSetting(Settings.EXPERT);
        Minesweeper.getInstance().newGame();
    }//GEN-LAST:event_expertMenuItemActionPerformed
    
    private void intermediateMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intermediateMenuItemActionPerformed
        Minesweeper.getInstance().setSetting(Settings.INTERMEDIATE);
        Minesweeper.getInstance().newGame();
    }//GEN-LAST:event_intermediateMenuItemActionPerformed
    
    private void beginnerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beginnerMenuItemActionPerformed
        Minesweeper.getInstance().setSetting(Settings.BEGINNER);
        Minesweeper.getInstance().newGame();
    }//GEN-LAST:event_beginnerMenuItemActionPerformed
    
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed
    
    private void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuItemActionPerformed
        Minesweeper.getInstance().newGame();
    }//GEN-LAST:event_newMenuItemActionPerformed
    
    public void mouseClicked(MouseEvent e) {
    }
    
    public void mouseReleased(MouseEvent e) {
    }
    
    public void mouseEntered(MouseEvent e) {
    }
    
    public void mouseExited(MouseEvent e) {
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButtonMenuItem beginnerMenuItem;
 
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JRadioButtonMenuItem expertMenuItem;
    private javax.swing.JMenu gameMenu;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JRadioButtonMenuItem intermediateMenuItem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel minesLeftLabel;
    private javax.swing.JButton newButton;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
