/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package immt.ui;

import ij.ImagePlus;
import immt.algorithms.Algorithm;
import immt.algorithms.GeometricFilter;
import immt.algorithms.snakes.externalforces.PotentialMcInerney99YDirection;
import immt.algorithms.snakes.imagedatafunction.FMcInerney99SignModified;
import immt.algorithms.snakes.tsnake.polar.TSnakePolar;
import immt.algorithms.structures.GreyScaleImage;
import immt.algorithms.structures.UndeterminedColumns;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Gaston
 */
public class WizardWindow extends ShellWindow {

    private ImagePlus imagePlus;
    
    private Rectangle roi1;
    
    private Rectangle roi2;
    
    /**
     * Creates new form WizardWindow
     */
    public WizardWindow() {
        initComponents();
        
        ShowAdvancedMethod(false);
        
        ShowStep1(true);
        ShowStep2(false);
        ShowStep4(false);
        
    }

    private void ShowAdvancedMethod(boolean value)
    {    
        p_Main.setVisible(value);
        jMenuBar1.setVisible(value);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p_OriginalImage = new immt.ui.ImagePanel(this);
        step1_1 = new javax.swing.JButton();
        step2_1 = new javax.swing.JLabel();
        step2_2 = new javax.swing.JLabel();
        step2_3 = new javax.swing.JLabel();
        step2_4 = new javax.swing.JButton();
        step4_2 = new javax.swing.JButton();
        step4_1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        p_OriginalImage.setToolTipText("");
        p_OriginalImage.setOpaque(false);
        p_OriginalImage.setPreferredSize(new java.awt.Dimension(800, 652));

        javax.swing.GroupLayout p_OriginalImageLayout = new javax.swing.GroupLayout(p_OriginalImage);
        p_OriginalImage.setLayout(p_OriginalImageLayout);
        p_OriginalImageLayout.setHorizontalGroup(
            p_OriginalImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        p_OriginalImageLayout.setVerticalGroup(
            p_OriginalImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 652, Short.MAX_VALUE)
        );

        step1_1.setText("Cargar Imagen");
        step1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                step1_1ActionPerformed(evt);
            }
        });

        step2_1.setText("Elija una region de interes.");

        step2_2.setText("Cuando termine, presione el siguiente");

        step2_3.setText("boton para aplicar el filtro.");

        step2_4.setText("Terminar con el marcado");
        step2_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                step2_4ActionPerformed(evt);
            }
        });

        step4_2.setText("Aplicar Segmentacion");
        step4_2.setOpaque(false);
        step4_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                step4_2ActionPerformed(evt);
            }
        });

        step4_1.setText("Se ha aplicado el filtro.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(step2_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(step2_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(step2_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(step1_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(step2_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(step4_2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(step4_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(p_OriginalImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(step1_1)
                        .addGap(26, 26, 26)
                        .addComponent(step2_1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(step2_2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(step2_3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(step2_4)
                        .addGap(18, 18, 18)
                        .addComponent(step4_1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(step4_2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(p_OriginalImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void step1_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_step1_1ActionPerformed
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
            imagePlus = originalImage;
            changeButtonsEnabled(true);
            
            ShowStep2(true);
        }
    }//GEN-LAST:event_step1_1ActionPerformed

    private boolean canContinue = false;
    
    private void step2_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_step2_4ActionPerformed
        roi1 = p_OriginalImage.getSelectedRoi();
        if( roi1 != null)
            ExecuteDefaultFilter();
    }//GEN-LAST:event_step2_4ActionPerformed

    private void step4_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_step4_2ActionPerformed
       ShowStep5(true);
       ExecuteDefaultSegmentation();
    }//GEN-LAST:event_step4_2ActionPerformed

    private void ExecuteDefaultFilter()
    {        
        int numberOfIterations = 10;
        
        GeometricFilter filter = new GeometricFilter(this);
        
        filter.setIterations(numberOfIterations);
        
            
        imagePlus.setRoi(roi1);
        filter.setOriginalImage(new ImagePlus("", imagePlus.getProcessor().crop()));
        
        filter.addPropertyChangeListener(this);
        
        filter.execute();
    }
    
    @Override
    public void createNewTab(Algorithm currentAlgorithm) {
        ShowStep4(true);
        ImagePlus img = currentAlgorithm.getResultingImage();
        p_OriginalImage.setImageWithRoi(img, roi1);        
        p_OriginalImage.repaint(); 
        
    }
    
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
            java.util.logging.Logger.getLogger(WizardWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WizardWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WizardWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WizardWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WizardWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private immt.ui.ImagePanel p_OriginalImage;
    private javax.swing.JButton step1_1;
    private javax.swing.JLabel step2_1;
    private javax.swing.JLabel step2_2;
    private javax.swing.JLabel step2_3;
    private javax.swing.JButton step2_4;
    private javax.swing.JLabel step4_1;
    private javax.swing.JButton step4_2;
    // End of variables declaration//GEN-END:variables

    private void ShowStep1(boolean b) {
       step1_1.setVisible(b);
    }

    private void ShowStep2(boolean b) {
        step2_1.setVisible(b);
        step2_2.setVisible(b);
        step2_3.setVisible(b);
        step2_4.setVisible(b);
    }

    private void ShowStep4(boolean b) {
        step4_1.setVisible(b);
        step4_2.setVisible(b);
    }

    private void ShowStep5(boolean b) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void ExecuteDefaultSegmentation() {
        GreyScaleImage filtrada = new GreyScaleImage(p_OriginalImage.getImage().getBufferedImage());
                
        // Default values
        int gauss = 5;        
        int potential = 0;
        int stretching = 20;
        int bending = 0;
        int damping = 30;
        int amplitude = 10;
        int iterations = 250;
        int contour = 0;
        
        // These should be modifiable
        int thresh1 = 53;
        int thresh2 = 180;
                       
        int[] initialCountour = new int[filtrada.getWidth()];        
        for(int i = 0; i < filtrada.getWidth() ; i++)
            initialCountour[i] = contour;
        
        PotentialMcInerney99YDirection P = new PotentialMcInerney99YDirection(filtrada, gauss, potential); //Aca pongan potentialScale=0, no vamos a usar el gradiente de la imagen
        
        //Lumen - Intima : 53
        FMcInerney99SignModified F = new FMcInerney99SignModified(filtrada, thresh1, true);
        
        TSnakePolar tsnake = new TSnakePolar();
                
        BufferedImage img = tsnake.ejecutar(filtrada, new UndeterminedColumns(), P, F, damping, amplitude, stretching, bending, 0, 1., iterations, initialCountour,false , TSnakePolar.GRADIENTE_LIBRE);

        double[] topSnakeY = tsnake.getY();

        
        // Media - Adventicia : 180
        F = new FMcInerney99SignModified(filtrada, thresh2, true);        
        
        img = tsnake.ejecutar(filtrada, new UndeterminedColumns(), P, F, damping, amplitude, stretching, bending, 0, 1., iterations, initialCountour,false , TSnakePolar.GRADIENTE_LIBRE);
        
        for(int i = 0; i < topSnakeY.length - 1 ; i++)
        {
            img.setRGB(i, (int)topSnakeY[i], Color.YELLOW.getRGB());            
        }
        
        ImagePanel panel = new ImagePanel(new ImagePlus("snakes", img), null);
        
        double[] botSnakeY = tsnake.getY();        
        
        p_OriginalImage.setImage(new ImagePlus("snakes", img));
        p_OriginalImage.repaint();   
    }
}
