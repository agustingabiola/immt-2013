package immt.ui.parameters;

import immt.algorithms.MedianFilter;
import immt.ui.ShellWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MedianFilterParams extends javax.swing.JPanel {

    private final ShellWindow parent;
    private MedianFilter algorithm;
    private final Logger logger = LoggerFactory.getLogger(MedianFilterParams.class);

    /**
     * *
     * Parameters for the Mean Filter
     *
     * @param parent parent of the window
     * @param algorithm mean filter algorithm to apply
     */
    public MedianFilterParams(ShellWindow parent, MedianFilter algorithm) {
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

        l_Size = new javax.swing.JLabel();
        b_Execute = new javax.swing.JButton();
        cb_radio = new javax.swing.JComboBox();

        l_Size.setText("Size of window");

        b_Execute.setText("Execute");
        b_Execute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_ExecuteActionPerformed(evt);
            }
        });

        cb_radio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1x1", "3x3", "5x5", "7x7", "9x9", "11x11", "13x13" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b_Execute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_Size)
                        .addGap(18, 18, 18)
                        .addComponent(cb_radio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_Size)
                    .addComponent(cb_radio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        int size = (cb_radio.getSelectedIndex() * 2) + 1;
        try {
            // If it was processed before, create a new SwingWorker
            if (algorithm.getResultingImage() != null) {
                algorithm = (MedianFilter) algorithm.clone();
            }

            algorithm.setWindowSize(size);
            algorithm.setOriginalImage(parent.getOriginalImage());

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
    private javax.swing.JComboBox cb_radio;
    private javax.swing.JLabel l_Size;
    // End of variables declaration//GEN-END:variables
}