/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immt.ui;

import ij.ImagePlus;
import immt.algorithms.Algorithm;
import immt.algorithms.GeometricFilter;
import immt.algorithms.MeanFilter;
import immt.algorithms.snakes.externalforces.PotentialMcInerney99YDirection;
import immt.algorithms.snakes.imagedatafunction.FMcInerney99SignModified;
import immt.algorithms.snakes.tsnake.polar.TSnakePolar;
import immt.algorithms.structures.GreyScaleImage;
import immt.algorithms.structures.UndeterminedColumns;
import immt.util.ConfigurationManager;
import immt.util.Functions;
import immt.util.Matrix;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author Gaston
 */
public class WizardWindow extends ShellWindow {

    private ImagePlus imagePlus;
    private Rectangle roi1;
    private double[] topSnakeY;
    private double[] botSnakeY;

    /**
     * Creates new form WizardWindow
     */
    public WizardWindow() {
        initComponents();

        ShowAdvancedMethod(false);

        ShowStep1(true);
        ShowStep2(false);
        ShowStep4(false);
        ShowStep5(false);
        ShowStep6(false);
    }

    private void ShowAdvancedMethod(boolean value) {
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
        step4_3 = new javax.swing.JLabel();
        step4_4 = new javax.swing.JLabel();
        step5_1 = new javax.swing.JButton();
        step6_2 = new javax.swing.JLabel();
        step6_3 = new javax.swing.JLabel();
        step6_4 = new javax.swing.JLabel();
        step6_5 = new javax.swing.JLabel();
        media = new javax.swing.JLabel();
        minimo = new javax.swing.JLabel();
        maximo = new javax.swing.JLabel();
        desviacion = new javax.swing.JLabel();
        step6_6 = new javax.swing.JButton();
        step6_7 = new javax.swing.JButton();
        step4_5 = new javax.swing.JButton();
        step6_8 = new javax.swing.JLabel();
        mediciones_realizadas = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        step6_9 = new javax.swing.JLabel();
        mediciones_realizadas1 = new javax.swing.JLabel();

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
            .addGap(0, 651, Short.MAX_VALUE)
        );

        step1_1.setText("Cargar Imagen");
        step1_1.setOpaque(false);
        step1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                step1_1ActionPerformed(evt);
            }
        });

        step2_1.setText("Elija una region de interes.");

        step2_2.setText("Cuando termine, presione el siguiente");

        step2_3.setText("boton para aplicar el filtro.");

        step2_4.setText("Terminar con el marcado");
        step2_4.setOpaque(false);
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

        step4_3.setText("Seleccione los puntos para calcular");

        step4_4.setText("las intensidades. (CTRL + click)");

        step5_1.setText("Aplicar Medicion");
        step5_1.setOpaque(false);
        step5_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                step5_1ActionPerformed(evt);
            }
        });

        step6_2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        step6_2.setText("Media:");

        step6_3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        step6_3.setText("Minimo:");

        step6_4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        step6_4.setText("Maximo");

        step6_5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        step6_5.setText("Desviacion:");

        media.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        minimo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        maximo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        desviacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        step6_6.setText("Guardar Resultados");
        step6_6.setOpaque(false);
        step6_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                step6_6ActionPerformed(evt);
            }
        });

        step6_7.setText("Empezar de nuevo");
        step6_7.setOpaque(false);
        step6_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                step6_7ActionPerformed(evt);
            }
        });

        step4_5.setText("Borrar Puntos marcados");
        step4_5.setOpaque(false);
        step4_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                step4_5ActionPerformed(evt);
            }
        });

        step6_8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        step6_8.setText("Mediciones:");

        mediciones_realizadas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton1.setText("Modo Experto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        step6_9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        step6_9.setText("Distancia:");

        mediciones_realizadas1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

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
                        .addComponent(step2_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(step4_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(step4_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(step4_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(step4_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(step5_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(step6_2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(media, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(step6_3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(minimo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(step6_4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(maximo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(step6_5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(desviacion, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(step6_6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(step6_7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(step4_5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(step6_8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(mediciones_realizadas, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(step6_9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mediciones_realizadas1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addComponent(step4_3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(step4_4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(step4_5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(step4_2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(step5_1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(step6_2)
                            .addComponent(media))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(step6_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(minimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(step6_4)
                            .addComponent(maximo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(step6_5)
                            .addComponent(desviacion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(step6_8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mediciones_realizadas, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(step6_9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mediciones_realizadas1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(step6_6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(step6_7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(p_OriginalImage, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
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
                img = ImageIO.read(file);
                WritableRaster raster = img.getRaster();
                data = (DataBufferByte) raster.getDataBuffer();
            } catch (IOException ex) {
                Logger.getLogger(ShellWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImagePlus originalImage = new ImagePlus(fc.getSelectedFile().getAbsolutePath());
            p_OriginalImage.setImage(originalImage);
            p_OriginalImage.repaint();
            image = originalImage.getBufferedImage();
            imagePlus = originalImage;
            //changeButtonsEnabled(true);

            ShowStep2(true);
            
            startingDate = new Date();
            
        }
    }//GEN-LAST:event_step1_1ActionPerformed
    private boolean canContinue = false;

    Date startingDate;
    
    private void step2_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_step2_4ActionPerformed
        roi1 = p_OriginalImage.getSelectedRoi();
        if (roi1 != null) {            
            offsetRoiX = (int) roi1.getMinX();
            widthRoi = (int) roi1.getWidth();
            ExecuteDefaultFilter();
        }
    }//GEN-LAST:event_step2_4ActionPerformed
    int thresh1, thresh2;

    private float GetAverageIntensityInWindow(Matrix window) {
        float sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sum += window.getElementAt(i, j);
            }
        }
        return sum / 9;
    }

    void GetPointsIntensity() {

        //p_.setProcessor(currentImage.getProcessor().convertToFloat());
        ImagePlus currentImage = p_OriginalImage.getImage();
       // float[] imagePixels = (float[]) currentImage.getProcessor().getPixelsCopy();
  
        BufferedImage buffImage = currentImage.getBufferedImage();
        
        GreyScaleImage filtrada = new GreyScaleImage(buffImage);
        
        
       // Matrix imageMatrix = new Matrix(currentImage.getHeight(), currentImage.getWidth(), imagePixels);

        Point point1 = p_OriginalImage.GetPoint1();
        Matrix window1;
        if (point1 != null) {
            window1 = Functions.GetWindow(filtrada, new immt.util.Point(point1.x - p_OriginalImage.GetLeft(), point1.y - p_OriginalImage.GetTop()), 3);
            thresh1 = (int) Math.floor(GetAverageIntensityInWindow(window1));
        }
        Point point2 = p_OriginalImage.GetPoint2();
        Matrix window2;
        if (point2 != null) {
            window2 = Functions.GetWindow(filtrada, new immt.util.Point(point2.x - p_OriginalImage.GetLeft(), point2.y - p_OriginalImage.GetTop()), 3);
            thresh2 = (int) Math.floor(GetAverageIntensityInWindow(window2));
        }
    }

    private void step4_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_step4_2ActionPerformed
        ShowStep5(true);
        ExecuteDefaultSegmentation();

    }//GEN-LAST:event_step4_2ActionPerformed

    private void step5_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_step5_1ActionPerformed
        ExecuteDefaultMeasurments();
    }//GEN-LAST:event_step5_1ActionPerformed

    private void step6_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_step6_6ActionPerformed
        JFileChooser fc = new JFileChooser("./Resultados/");
        int returnVal = fc.showSaveDialog(null);
        String newline = System.getProperty("line.separator");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try (FileWriter fw = new FileWriter(fc.getSelectedFile() + ".txt")) {
                String s = "Media: " + media.getText() + newline;
                s += "Minimo: " + minimo.getText() + newline;
                s += "Maximo: " + maximo.getText() + newline;
                s += "Desviacion: " + desviacion.getText() + newline;
                s += "Cantidad de puntos medidos: "  + mediciones_realizadas.getText() + newline;
                s += "Distancia medida: "  + mediciones_realizadas1.getText() + newline;
                s += "Tiempo Total de Medicion (segundos): " + elapsedTime + newline;
                fw.write(s);
                fw.close();
            } catch (Exception ex) {
            }
            p_OriginalImage.saveImage(fc.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_step6_6ActionPerformed

    private void step6_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_step6_7ActionPerformed
        p_OriginalImage.resetPanel();
        step1_1.setEnabled(true);
        step2_4.setEnabled(true);
        step4_5.setEnabled(true);
        step4_2.setEnabled(true);
        step5_1.setEnabled(true);
        ShowStep1(true);
        ShowStep2(false);
        ShowStep4(false);
        ShowStep5(false);
        ShowStep6(false);
    }//GEN-LAST:event_step6_7ActionPerformed

    private void step4_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_step4_5ActionPerformed
        p_OriginalImage.ResetAllPoints();
    }//GEN-LAST:event_step4_5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       ShellWindow shell = new ShellWindow();
       shell.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ExecuteDefaultFilter() {
        int numberOfIterations = 10;

        GeometricFilter filter = new GeometricFilter(this);

        filter.setIterations(numberOfIterations);
        

        imagePlus.setRoi(roi1);
        filter.setOriginalImage(new ImagePlus("", imagePlus.getProcessor().crop()));

               
        filter.addPropertyChangeListener(this);

        filter.execute();
    }
    int offset = 5;

    int offsetRoiX, widthRoi;
    
    
    void ExecuteDefaultMeasurments() {
        ArrayList<immt.util.Point> newPoints = new ArrayList<>();

        Rectangle r = p_OriginalImage.getSelectedRoi();
        
        int realOffset1, realOffset2;
        //didnt select a new roi
        if(r.width == widthRoi)
        {
            realOffset2 = widthRoi;
            realOffset1 = 0;
        }   
        else
        {
            int halfOfTheScreen = (800 / 2);
            int leftSideOfImage = halfOfTheScreen - (widthRoi / 2);

            realOffset1 = (int) r.getMinX() - leftSideOfImage;
            realOffset2 = (int) r.getMaxX() - leftSideOfImage;
        }

        if(r.getWidth() < 10)
        {
            realOffset1 = 0;
            realOffset2 = 15;
        }
        
//for (int i = offset; i < roi1.width - offset; i++) {
        for (int i = realOffset1 + offset; i < realOffset2 - offset; i++) {
            double left = topSnakeY[i - offset];
            double right = topSnakeY[i + offset];

            // y = ax + b

            // a = (y2 - y1) / (x2 - x1)
            double a = (left - right) / ((i - offset) - (i + offset));

            // b = y - (a * x)
            double b = topSnakeY[i - offset] - (a * (i - offset));


            double lowest = Integer.MAX_VALUE;
            int lowestX = -1;

            // Straight line
            if (a == -0 || a == 0) {
                lowestX = i;
            } else {
                // Perpedincular line is with 1/ -a
                double aPerp = (1 / a) * -1;

                double newB = topSnakeY[i] - (aPerp * (i));

                int it = 0;
                for (double d : botSnakeY) {
                    // y = a x + b                        
                    double y = (aPerp * it) + newB;

                    double diff = d - y;
                    diff = Math.abs(diff);

                    if (diff < lowest) {
                        lowest = diff;
                        lowestX = it;
                    }
                    it++;
                }
            }

            newPoints.add(new immt.util.Point(i, topSnakeY[i]));
            newPoints.add(new immt.util.Point(lowestX, botSnakeY[lowestX]));
        }
        mediciones_realizadas.setText(String.valueOf(realOffset2 - realOffset1));
        CalculateStatistics(newPoints);
    }

    double EuclidianDistance(Point originalPoint, double x, double y) {
        return Math.sqrt(Math.pow(originalPoint.x - x, 2) + Math.pow(originalPoint.y - y, 2));
    }

    double EuclidianDistance(immt.util.Point originalPoint, double x, double y) {
        return Math.sqrt(Math.pow(originalPoint.getxCoord() - x, 2) + Math.pow(originalPoint.getyCoord() - y, 2));
    }

    private void CalculateStatistics(ArrayList<immt.util.Point> points) {
        double ppm = Double.parseDouble(ConfigurationManager.getAppSetting("ppm"));
        immt.util.Point firstPoint = null;
        double sumOfDistances = 0;

        // Need points??
        immt.util.Point maxPoint = new immt.util.Point(0, 0);
        immt.util.Point minPoint = new immt.util.Point(9999, 9999);

        double minDistance = Double.MAX_VALUE;
        double maxDistance = Double.MIN_VALUE;

        for (immt.util.Point p : points) {
            if (p.getyCoord() < minPoint.getyCoord()) {
                minPoint = p;
            }
            if (p.getyCoord() > maxPoint.getyCoord()) {
                maxPoint = p;
            }

            if (firstPoint == null) {
                firstPoint = p;
            } else {
                double distance = EuclidianDistance(firstPoint, p.getxCoord(), p.getyCoord()) * ppm;
                if (distance > maxDistance) {
                    maxDistance = distance;
                }
                if (distance < minDistance) {
                    minDistance = distance;
                }
                sumOfDistances += distance;
                firstPoint = null;
            }
        }

        double mean = sumOfDistances / (points.size() / 2);
        media.setText(String.valueOf(mean).substring(0, 5) + " mm.");
        minimo.setText(String.valueOf(minDistance).substring(0, 5) + " mm.");
        maximo.setText(String.valueOf(maxDistance).substring(0, 5) + " mm.");
        desviacion.setText(String.valueOf(GetStandarDeviation(mean, points)).substring(0, 5) + " mm.");
        int cantMediciones = Integer.parseInt(mediciones_realizadas.getText());
        double distancia = cantMediciones * ppm;
                
        mediciones_realizadas1.setText(String.valueOf(distancia).substring(0, 5) + " mm.");
        //mediciones_realizadas.setText(String.valueOf(points.size()));

        Date currentDate = new Date();
        
        elapsedTime = ((currentDate.getMinutes() * 60) + currentDate.getSeconds()) - ((startingDate.getMinutes()*60) +startingDate.getSeconds());
        
        ShowStep6(true);
    }

    // in seconds
    int elapsedTime;
    
    private double GetStandarDeviation(double mean, ArrayList<immt.util.Point> points) {
        double ppm = Double.parseDouble(ConfigurationManager.getAppSetting("ppm"));
        double result = 0;
        immt.util.Point firstPoint = null;

        for (immt.util.Point p : points) {
            if (firstPoint == null) {
                firstPoint = p;
            } else {
                double distance = EuclidianDistance(firstPoint, p.getxCoord(), p.getyCoord()) * ppm;
                result += Math.pow((distance - mean), 2);
                firstPoint = null;
            }
        }
        return Math.sqrt(result / (points.size() / 2));
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WizardWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WizardWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel desviacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel maximo;
    private javax.swing.JLabel media;
    private javax.swing.JLabel mediciones_realizadas;
    private javax.swing.JLabel mediciones_realizadas1;
    private javax.swing.JLabel minimo;
    private immt.ui.ImagePanel p_OriginalImage;
    private javax.swing.JButton step1_1;
    private javax.swing.JLabel step2_1;
    private javax.swing.JLabel step2_2;
    private javax.swing.JLabel step2_3;
    private javax.swing.JButton step2_4;
    private javax.swing.JLabel step4_1;
    private javax.swing.JButton step4_2;
    private javax.swing.JLabel step4_3;
    private javax.swing.JLabel step4_4;
    private javax.swing.JButton step4_5;
    private javax.swing.JButton step5_1;
    private javax.swing.JLabel step6_2;
    private javax.swing.JLabel step6_3;
    private javax.swing.JLabel step6_4;
    private javax.swing.JLabel step6_5;
    private javax.swing.JButton step6_6;
    private javax.swing.JButton step6_7;
    private javax.swing.JLabel step6_8;
    private javax.swing.JLabel step6_9;
    // End of variables declaration//GEN-END:variables

    private void ShowStep1(boolean b) {
        step1_1.setVisible(b);
    }

    private void ShowStep2(boolean b) {
        if(b)
            step1_1.setEnabled(false);
        step2_1.setVisible(b);
        step2_2.setVisible(b);
        step2_3.setVisible(b);
        step2_4.setVisible(b);
    }

    private void ShowStep4(boolean b) {
        if(b)
            step2_4.setEnabled(false);
        step4_1.setVisible(b);
        step4_2.setVisible(b);
        step4_3.setVisible(b);
        step4_4.setVisible(b);
        step4_5.setVisible(b);
    }

    private void ShowStep5(boolean b) {
       if(b)
       {
           step4_2.setEnabled(false);
           step4_5.setEnabled(false);
       }
        step5_1.setVisible(b);
    }

    private void ShowStep6(boolean b) {
        //if(b)
        //    step5_1.setEnabled(false);
        step6_2.setVisible(b);
        step6_4.setVisible(b);
        step6_5.setVisible(b);
        step6_3.setVisible(b);
        step6_6.setVisible(b);
        //step6_7.setVisible(b);
        step6_8.setVisible(b);
        step6_9.setVisible(b);
        minimo.setVisible(b);
        maximo.setVisible(b);
        media.setVisible(b);
        desviacion.setVisible(b);
        mediciones_realizadas.setVisible(b);
        mediciones_realizadas1.setVisible(b);
    }

    private void ExecuteDefaultSegmentation() {
        GreyScaleImage filtrada = new GreyScaleImage(p_OriginalImage.getImage().getBufferedImage());

        GetPointsIntensity();
        
        System.out.println("THRESH 1: " + thresh1 + "  TRESH 2:"  + thresh2 );
        
        // Default values
        int gauss = 5;
        int potential = 0;
        int stretching = 20;
        int bending = 0;
        int damping = 26;
        int amplitude = 10;
        int iterations = 250;
        int contour = p_OriginalImage.getYofPoints();

        int[] initialCountour = new int[filtrada.getWidth()];
        for (int i = 0; i < filtrada.getWidth(); i++) {
            initialCountour[i] = contour;
        }
        
        System.out.println("CONTOUR: " + contour);

        PotentialMcInerney99YDirection P = new PotentialMcInerney99YDirection(filtrada, gauss, potential); //Aca pongan potentialScale=0, no vamos a usar el gradiente de la imagen

        //Lumen - Intima : 53
        FMcInerney99SignModified F= new FMcInerney99SignModified(filtrada, thresh1, true);

        TSnakePolar tsnake = new TSnakePolar();

        BufferedImage img= tsnake.ejecutar(filtrada, new UndeterminedColumns(), P, F, damping, amplitude, stretching, bending, 0, 1., iterations, initialCountour, false, TSnakePolar.GRADIENTE_LIBRE);

        topSnakeY = tsnake.getY();


        // Media - Adventicia : 180
        F = new FMcInerney99SignModified(filtrada, thresh2, true);

        img = tsnake.ejecutar(filtrada, new UndeterminedColumns(), P, F, damping, amplitude, stretching, bending, 0, 1., iterations, initialCountour, false, TSnakePolar.GRADIENTE_LIBRE);

        for (int i = 0; i < topSnakeY.length - 1; i++) {
            img.setRGB(i, (int) topSnakeY[i], Color.YELLOW.getRGB());
        }

        //ImagePanel panel = new ImagePanel(new ImagePlus("snakes", img), null);

        botSnakeY = tsnake.getY();

        p_OriginalImage.setImage(new ImagePlus("snakes", img));
        p_OriginalImage.repaint();

    }
}
