package immt.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RightClickListener extends MouseAdapter {
    
    private ImagePanel mainWindow;
    
    public RightClickListener(ImagePanel mainWindow){
        this.mainWindow = mainWindow;
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    @Override
    public void mouseReleased(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e){
        RightClickMenu menu = new RightClickMenu(mainWindow);
        menu.show(e.getComponent(), e.getX(), e.getY());
        mainWindow.repaint();
    }
}