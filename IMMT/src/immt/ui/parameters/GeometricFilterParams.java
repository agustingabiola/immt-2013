package immt.ui.parameters;

import immt.algorithms.GeometricFilter;
import immt.ui.ShellWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeometricFilterParams extends javax.swing.JPanel {

    private ShellWindow parent;
    private GeometricFilter algorithm;
    private final Logger logger = LoggerFactory.getLogger(GeometricFilterParams.class);

    /**
     * *
     * Parameters for the Geometric Filter
     *
     * @param parent parent of the window
     * @param algorithm geometric filter algorithm to apply
     */
    public GeometricFilterParams(ShellWindow parent, GeometricFilter algorithm) {
        initComponents();
        this.parent = parent;
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

        l_Iterations = new javax.swing.JLabel();
        tf_Iterations = new javax.swing.JTextField();
        b_Execute = new javax.swing.JButton();

        l_Iterations.setText("# Iterations:");

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
                        .addComponent(l_Iterations)
                        .addGap(18, 18, 18)
                        .addComponent(tf_Iterations, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 19, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_Iterations)
                    .addComponent(tf_Iterations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(b_Execute)
                .addContainerGap(70, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * *
     * Execute the algorithm selected
     *
     * @param evt
     */
    private void b_ExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_ExecuteActionPerformed
        String iterations = tf_Iterations.getText();
        try {
            // If it was processed before, create a new SwingWorker
            if (algorithm.getResultingImage() != null) {
                algorithm = (GeometricFilter) algorithm.clone();
            }

            algorithm.setIterations(Integer.parseInt(iterations));
            algorithm.setOriginalImage(parent.getImageToProcess());

            // The ShellWindow listens for updates of progress
            algorithm.addPropertyChangeListener(parent);

            algorithm.execute();

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            parent.setStatus("Se ha producido un error. Por favor, intente de vuelta.");
        }
    }//GEN-LAST:event_b_ExecuteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_Execute;
    private javax.swing.JLabel l_Iterations;
    private javax.swing.JTextField tf_Iterations;
    // End of variables declaration//GEN-END:variables
}
