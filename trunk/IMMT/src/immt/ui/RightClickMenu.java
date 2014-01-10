package immt.ui;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class RightClickMenu extends JPopupMenu {
    
    JMenuItem saveAs;
    
    public RightClickMenu(final ImagePanel mainWindow){
        saveAs = new JMenuItem(new AbstractAction("Save as...") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mainWindow.saveImage();
            }
        });
        add(saveAs);
    }
    
}
