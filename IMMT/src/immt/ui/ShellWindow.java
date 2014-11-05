package immt.ui;

import ij.ImagePlus;
import immt.algorithms.Algorithm;
import immt.algorithms.WeightedMeanFilter;
import immt.algorithms.GeometricFilter;
import immt.algorithms.MeanFilter;
import immt.algorithms.MedianFilter;
import immt.edge.EdgeOperator;
import immt.edge.Prewitt;
import immt.edge.Sobel;
import immt.ui.parameters.BaseParams;
import immt.util.Compare;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShellWindow extends javax.swing.JFrame implements PropertyChangeListener {

    public Algorithm algorithms[];
    public BaseParams p_BaseParams;
    public BufferedImage image;

    public ShellWindow() {
        loadPreProcessingAlgorithms();
        initComponents();
        changeButtonsEnabled(false);
        //loadEdgeOperators();
    }
 /*     
    /**
     * Loads the edge operators into the ComboBox
    
    public void loadEdgeOperators() {
      
// Create all the EdgeOperators
        EdgeOperator operators[] = {
            //new Sobel(this),
            //new Prewitt(this)};

        // Put them inside the ComboBox
        for (EdgeOperator operator : operators) {
            cb_filter.addItem(operator);
        }
        
    }
*/
    /**
     * Loads the algorithms implemented into an array.
     */
    private void loadPreProcessingAlgorithms() {
        // Create all the EdgeOperators
        algorithms = new Algorithm[] {
            new MeanFilter(this),
            new MedianFilter(this),
            new WeightedMeanFilter(this),
            new GeometricFilter(this),
            new Sobel (this),
            new Prewitt(this)};
    }   
    
    /***
     * Enables/Disables some controls, depending on the value passed as parameter
     * @param value if true, it enables the controls.
     */
    public void changeButtonsEnabled(boolean value){        
        b_filter.setEnabled(value);
        cb_filter.setEnabled(value);
    }

    
    /**
     * Displays the options of the algorithm chosen. This method gets called from inside the algorithms.
     *
     * @param options options to display in the screen.
     */
    public void showAlgorithmOption(JPanel options){
        // Add the options panel inside the shell window
        if (p_BaseParams != null) {
            p_Options.remove(p_BaseParams);
        }
        p_BaseParams = new BaseParams();
        p_Options.add(p_BaseParams);
        
        // Add the options to the panel
        p_BaseParams.add(options, BorderLayout.NORTH);

        // Recalculates space in the component.    
        p_BaseParams.revalidate();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p_Main = new javax.swing.JPanel();
        tp_Images = new javax.swing.JTabbedPane();
        p_OriginalImage = new immt.ui.ImagePanel(this);
        p_Options = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        li_Algorithms = new javax.swing.JList(algorithms){
            @Override
            public String getToolTipText(MouseEvent evt) {
                int index = locationToIndex(evt.getPoint());
                String toolTip=((Algorithm) this.getModel().getElementAt(index)).getDescription();
                toolTip= toolTip.replaceAll("\n", "<br>");
                toolTip="<html>"+toolTip+"</html>";
                return toolTip;
            }
        };
        p_StatusBar = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        l_StatusTitle = new javax.swing.JLabel();
        l_Status = new javax.swing.JLabel();
        pb_Status = new javax.swing.JProgressBar();
        p_actions = new javax.swing.JPanel();
        b_filter = new javax.swing.JButton();
        cb_filter = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

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

        p_actions.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));

        b_filter.setText("Apply Filter");
        b_filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_filterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout p_actionsLayout = new javax.swing.GroupLayout(p_actions);
        p_actions.setLayout(p_actionsLayout);
        p_actionsLayout.setHorizontalGroup(
            p_actionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_actionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(p_actionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b_filter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(cb_filter, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        p_actionsLayout.setVerticalGroup(
            p_actionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_actionsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cb_filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_filter)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout p_MainLayout = new javax.swing.GroupLayout(p_Main);
        p_Main.setLayout(p_MainLayout);
        p_MainLayout.setHorizontalGroup(
            p_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_MainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tp_Images, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(p_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(p_Options, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(p_actions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(p_StatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p_MainLayout.setVerticalGroup(
            p_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_MainLayout.createSequentialGroup()
                .addGroup(p_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p_MainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tp_Images, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 67, Short.MAX_VALUE))
                    .addGroup(p_MainLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(p_actions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(p_Options, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124)))
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

        jMenu2.setText("Run");

        jMenuItem1.setText("Snakes...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Configuration");

        jMenuItem2.setText("Settings...");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Opens the menu to select an image. By default, it opens to root/Images
     *
     * @param evt
     */
    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        final JFileChooser fc = new JFileChooser("./Images/");
        int returnVal = fc.showOpenDialog(this);
        DataBufferByte data;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //imageLoaded = true;
            BufferedImage img;
            try {
                File file = new File(fc.getSelectedFile().getAbsolutePath());
                byte[] fileContent = Files.readAllBytes(file.toPath());
                img = ImageIO.read(file);
                WritableRaster raster = img.getRaster();
                 data   = (DataBufferByte) raster.getDataBuffer();
            } catch (IOException ex) {
                Logger.getLogger(ShellWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImagePlus originalImage = new ImagePlus(fc.getSelectedFile().getAbsolutePath());
            p_OriginalImage.setImage(originalImage);
            p_OriginalImage.repaint();
            image = originalImage.getBufferedImage();
            changeButtonsEnabled(true);
        }
    }//GEN-LAST:event_openMenuItemActionPerformed

    public int MinRoiX;
    
     public int MinRoiY;
     
    public ImagePlus getImageToProcess() {
        ImagePanel panel = getCurrentImagePanelSelected();
        Rectangle roi = panel.getSelectedRoi();
        // if there is no ROI, just return the image
        if (roi == null) {
            MinRoiX = 0;
            return panel.getImage().duplicate();
        } else {
            MinRoiX = roi.x;
            MinRoiY = roi.y;
            ImagePlus image = panel.getImage();
            image.setRoi(roi);
            return new ImagePlus("", image.getProcessor().crop());
        }
    }
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Snakes snakeWindow = new Snakes(getCurrentImagePanelSelected().getImage(), this);
        snakeWindow.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        SettingsPanel settings = new SettingsPanel();
        settings.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void b_filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_filterActionPerformed
        // Gets the currently selected edge operator from the combo box
        EdgeOperator operator = (EdgeOperator) cb_filter.getSelectedItem();

        // If it was processed before, create a new SwingWorker
        if (operator.getResultingImage() != null) {
            operator = operator.clone();
        }

        // Executes it
        operator.execute();
    }//GEN-LAST:event_b_filterActionPerformed

    /**
     * Shows the options of the selected algorithm. Only works if an image is
     * previously opened.
     *
     * @param evt
     */
    private void li_AlgorithmsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_li_AlgorithmsMouseClicked
        ImagePlus image = p_OriginalImage.getImage();
        if (image != null) {
            int clickIndex = li_Algorithms.locationToIndex(evt.getPoint());
            Algorithm selectedAlgorithm = (Algorithm) li_Algorithms.getModel().getElementAt(clickIndex);
            selectedAlgorithm.showAlgorithmOptions();
        }
    }//GEN-LAST:event_li_AlgorithmsMouseClicked

    public void createEdgeResultWindow(EdgeOperator operator){
        // Create new Jframe to display the resulting image  
        String filterName = cb_filter.getSelectedItem().toString();        
        JFrame newFrame = new JFrame(filterName + " & " + operator.getName() + " edge operator");
        
        // Set the resulting image in a new ImagePanel
        ImagePanel newPanel = new ImagePanel(operator.getResultingImage(), this);
        
        newPanel.setOpaque(true);
        // Add the ImagePanel to the Jframe
        newFrame.add(newPanel);
        newFrame.setSize(new Dimension(800, 600));
        newFrame.setVisible(true);
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShellWindow().setVisible(true);
            }
        });
    }
    
    /**
     * Updates the status label.
     */
    public void setStatus(String status) {
        l_Status.setText(status);
    }

    public Point GetPoint1()
    {
        return getCurrentImagePanelSelected().GetPoint1();    
    }
    
      public Point GetPoint2()
    {
        return getCurrentImagePanelSelected().GetPoint2();    
    }

    
    /**
     * Creates a new Tab with the result of the algorithm selected. If that
     * algorithm was already run before, it only changes de image of the tab
     * with the name of the algorithm.
     */
    public void createNewTab(Algorithm currentAlgorithm) {;
        String name = currentAlgorithm.getName();
        int index = tp_Images.indexOfTab(name);
        if (index == -1) {
            tp_Images.add(currentAlgorithm.getName(), new ImagePanel(currentAlgorithm.getResultingImage(), this));
            index = tp_Images.indexOfTab(name);

        } else {
            ImagePanel tab = (ImagePanel) tp_Images.getComponentAt(index);
            tab.setImage(currentAlgorithm.getResultingImage());
            tab.repaint();
        }
        tp_Images.setSelectedIndex(index);
    }


    /**
     * Obtain the original image opened by the user.
     *
     * @return Original Image opened.
     */
    public ImagePlus getOriginalImage() {
        Rectangle roi = p_OriginalImage.getSelectedRoi();
        if(roi == null)
            return p_OriginalImage.getImage();
        else{
            ImagePlus originalImage = p_OriginalImage.getImage();
            originalImage.setRoi(roi);
            return new ImagePlus("",originalImage.getProcessor().crop());
        }
    }
    
    public ImagePanel getCurrentImagePanelSelected(){
        String tabName = tp_Images.getTitleAt(tp_Images.getSelectedIndex());
        int index = tp_Images.indexOfTab(tabName);
        if(index != -1) {
            ImagePanel tab = (ImagePanel) tp_Images.getComponentAt(index);
            return tab;
        }
        return null;
    }


    
    /**
     * Updates progress bar, while algorithm is being run.
     *
     * @param evt
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("progress")) {
            int progress = (Integer) evt.getNewValue();
            pb_Status.setValue(progress);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_filter;
    private javax.swing.JComboBox cb_filter;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    public javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel l_Status;
    private javax.swing.JLabel l_StatusTitle;
    private javax.swing.JList li_Algorithms;
    private javax.swing.JMenuItem openMenuItem;
    protected javax.swing.JPanel p_Main;
    private javax.swing.JPanel p_Options;
    private immt.ui.ImagePanel p_OriginalImage;
    private javax.swing.JPanel p_StatusBar;
    private javax.swing.JPanel p_actions;
    private javax.swing.JProgressBar pb_Status;
    private javax.swing.JTabbedPane tp_Images;
    // End of variables declaration//GEN-END:variables
}
