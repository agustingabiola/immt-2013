package immt.ui.parameters;

import ij.ImagePlus;
import immt.algorithms.MeanFilter;
import immt.ui.ShellWindow;

public class MeanFilterParams extends javax.swing.JPanel {

    private ShellWindow parent;
    private ImagePlus image;
    private MeanFilter algorithm;
    
    public MeanFilterParams(ShellWindow parent, ImagePlus image, MeanFilter algorithm) {
        initComponents();
        this.parent = parent;
        this.image = image;
        this.algorithm = algorithm;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        l_Radio = new javax.swing.JLabel();
        tf_Radio = new javax.swing.JTextField();
        b_Execute = new javax.swing.JButton();

        l_Radio.setText("Radio:");

        b_Execute.setText("Execute");
        b_Execute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_ExecuteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b_Execute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_Radio)
                        .addGap(18, 18, 18)
                        .addComponent(tf_Radio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 50, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_Radio)
                    .addComponent(tf_Radio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(b_Execute)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void b_ExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_ExecuteActionPerformed
        algorithm.setRadio(Integer.parseInt(tf_Radio.getText()));
        algorithm.run(image);
        parent.createNewTab(algorithm);
    }//GEN-LAST:event_b_ExecuteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_Execute;
    private javax.swing.JLabel l_Radio;
    private javax.swing.JTextField tf_Radio;
    // End of variables declaration//GEN-END:variables
}
