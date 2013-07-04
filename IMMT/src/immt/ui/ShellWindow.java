package immt.ui;

import ij.ImagePlus;
import immt.algorithms.Algorithm;
import immt.algorithms.FrostFilter;
import immt.algorithms.MeanFilter;
import immt.ui.parameters.BaseParams;
import immt.ui.parameters.FrostFilterParams;
import immt.ui.parameters.MeanFilterParams;
import java.awt.BorderLayout;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFileChooser;

public class ShellWindow extends javax.swing.JFrame implements PropertyChangeListener{

    private Algorithm algorithms[];
    private BaseParams p_BaseParams;

    public ShellWindow() {
        loadPreProcessingAlgorithms();
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p_Main = new javax.swing.JPanel();
        tp_Images = new javax.swing.JTabbedPane();
        p_OriginalImage = new immt.ui.ImagePanel();
        p_Options = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        li_Algorithms = new javax.swing.JList(algorithms);
        p_StatusBar = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        l_StatusTitle = new javax.swing.JLabel();
        l_Status = new javax.swing.JLabel();
        pb_Status = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Intima-Media Measurment Tool");
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        tp_Images.setToolTipText("");

        p_OriginalImage.setPreferredSize(new java.awt.Dimension(800, 652));

        javax.swing.GroupLayout p_OriginalImageLayout = new javax.swing.GroupLayout(p_OriginalImage);
        p_OriginalImage.setLayout(p_OriginalImageLayout);
        p_OriginalImageLayout.setHorizontalGroup(
            p_OriginalImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 911, Short.MAX_VALUE)
        );
        p_OriginalImageLayout.setVerticalGroup(
            p_OriginalImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 652, Short.MAX_VALUE)
        );

        tp_Images.addTab("Original Image", p_OriginalImage);

        p_Options.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p_Options.setMaximumSize(new java.awt.Dimension(32773, 593));
        p_Options.setLayout(new javax.swing.BoxLayout(p_Options, javax.swing.BoxLayout.PAGE_AXIS));

        jScrollPane1.setMaximumSize(new java.awt.Dimension(32767, 150));

        li_Algorithms.setBorder(javax.swing.BorderFactory.createTitledBorder("Algorithms"));
        li_Algorithms.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        li_Algorithms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                li_AlgorithmsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(li_Algorithms);

        p_Options.add(jScrollPane1);

        l_StatusTitle.setText("Status:");

        l_Status.setText("IDLE");

        javax.swing.GroupLayout p_StatusBarLayout = new javax.swing.GroupLayout(p_StatusBar);
        p_StatusBar.setLayout(p_StatusBarLayout);
        p_StatusBarLayout.setHorizontalGroup(
            p_StatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(p_StatusBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(l_StatusTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(l_Status)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 706, Short.MAX_VALUE)
                .addComponent(pb_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        p_StatusBarLayout.setVerticalGroup(
            p_StatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_StatusBarLayout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(p_StatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_StatusTitle)
                    .addComponent(l_Status)
                    .addComponent(pb_Status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout p_MainLayout = new javax.swing.GroupLayout(p_Main);
        p_Main.setLayout(p_MainLayout);
        p_MainLayout.setHorizontalGroup(
            p_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_MainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tp_Images, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p_Options, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(p_MainLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(p_StatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        p_MainLayout.setVerticalGroup(
            p_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_MainLayout.createSequentialGroup()
                .addGroup(p_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p_MainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tp_Images, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_MainLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(p_Options, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(p_StatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(p_Main);

        jMenu1.setText("File");

        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenuItem.setText("Load Image...");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(openMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Opens the menu to select an image.
     * By default, it opens to project root / Images
     * 
     * @param evt 
     */
    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        final JFileChooser fc = new JFileChooser("./Images/");
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            ImagePlus originalImage = new ImagePlus(fc.getSelectedFile().getAbsolutePath());
            p_OriginalImage.setImage(originalImage);
            p_OriginalImage.repaint();

        }
    }//GEN-LAST:event_openMenuItemActionPerformed

    /**
     * Shows the options of the selected algorithm.
     * Only works if an image is previously opened.
     * 
     * @param evt
     */
    private void li_AlgorithmsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_li_AlgorithmsMouseClicked
        ImagePlus image = p_OriginalImage.getImage();
        if (image != null) {
            int clickIndex = li_Algorithms.locationToIndex(evt.getPoint());
            Algorithm selectedAlgorithm = (Algorithm) li_Algorithms.getModel().getElementAt(clickIndex);
            showAlgorithmOptions(selectedAlgorithm);
        }
    }//GEN-LAST:event_li_AlgorithmsMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShellWindow().setVisible(true);
            }
        });
    }
    
     /**
     * Loads the algorithms implemented into an array.
     */
    private void loadPreProcessingAlgorithms() {
        int numberAlgorithms = 2;
        algorithms = new Algorithm[numberAlgorithms];
        algorithms[0] = new MeanFilter(this);
        algorithms[1] = new FrostFilter(this);
        
    }
    
    /**
     * Updates the status label.
     */
    public void setStatus(String status){
        l_Status.setText(status);
    }

    /**
     * Creates a new Tab with the result of the algorithm selected.
     * If that algorithm was already run before, it only changes de 
     * image of the tab with the name of the algorithm.
     */
    public void createNewTab(Algorithm currentAlgorithm) {
        ImagePanel tab = (ImagePanel) getTab(currentAlgorithm.getName());
        if (tab == null) {
            tp_Images.add(currentAlgorithm.getName(), new ImagePanel(currentAlgorithm.getResultingImage()));
        } else {
            // TODO: Swingworker doesn't allow multiple runs. This is not working properly
            tab.setImage(currentAlgorithm.getResultingImage());
            tab.repaint();
        }
    }

    /**
     * Displays the options of the algorithm chosen.
     * 
     * @param selectedAlgorithm the chosen algorithm
     */
    private void showAlgorithmOptions(Algorithm selectedAlgorithm) {            
        // TODO: See multiple instances of options being shown
        if (p_BaseParams == null) {
            p_BaseParams = new BaseParams();
        }
        
        p_Options.add(p_BaseParams);
        switch (selectedAlgorithm.getName()) {
            case "Mean Filter": {
                MeanFilterParams p_MeanFilterParams = new MeanFilterParams(this, (MeanFilter) selectedAlgorithm);
                p_BaseParams.add(p_MeanFilterParams, BorderLayout.NORTH);
                break;
            }
            case "Frost Filter": {
                FrostFilterParams p_FrostFilterParams = new FrostFilterParams(this, (FrostFilter) selectedAlgorithm);
                p_BaseParams.add(p_FrostFilterParams, BorderLayout.NORTH);
                break;
            }
        }
        // Recalculates space in the component.    
        p_BaseParams.revalidate();
    }

    /**
     * Gets the tab from the pane that matches the given name.
     * In case it doesn´t exist, it returns null.
     * 
     * @param tabName name of the tab to look for
     * @return the component (Tab) that matches the tabName
     */
    private Component getTab(String tabName) {
        int index = tp_Images.indexOfTab(tabName);
        if (index != -1) {
            return tp_Images.getComponentAt(index);
        }
        return null;
    }

    /**
     * Obtain the original image opened by the user.
     * 
     * @return Original Image opened.
     */
    public ImagePlus getOriginalImage(){
        return p_OriginalImage.getImage();    
    }
    
    /**
     * Updates progress bar, while algorithm is being run.
     * 
     * @param evt 
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            pb_Status.setValue(progress);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel l_Status;
    private javax.swing.JLabel l_StatusTitle;
    private javax.swing.JList li_Algorithms;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JPanel p_Main;
    private javax.swing.JPanel p_Options;
    private immt.ui.ImagePanel p_OriginalImage;
    private javax.swing.JPanel p_StatusBar;
    private javax.swing.JProgressBar pb_Status;
    private javax.swing.JTabbedPane tp_Images;
    // End of variables declaration//GEN-END:variables


}
