package UserInterface;

import java.awt.AlphaComposite;  
import java.awt.Graphics;  
import java.awt.Graphics2D;  
  
import javax.swing.ImageIcon;
import javax.swing.JComponent;  
import javax.swing.JTable;  
import javax.swing.table.DefaultTableCellRenderer;
  
public class OpaqueJTable extends JTable {  
  
    private static final long serialVersionUID = 1L;  
    private float alpha;  
  
    public OpaqueJTable() {  
        super();  
        alpha = 0.5f;  
    }  
  
    @Override  
    public void setOpaque(boolean isOpaque) {  
        if (isOpaque) {  
            alpha = 1.0f;  
        }  
    }  
    
    @Override  
    public boolean isOpaque() {  
        return alpha == 1f;  
    }  
    
    public float getAlpha() {  
        return alpha;  
    }  
    
    public void setAlpha(float alpha) {  
        this.alpha = alpha;  
    }  
  
    @Override  
    public void paintComponent(Graphics g) {  
        Graphics2D g2d = (Graphics2D) g;  
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));  
        super.paintComponent(g2d);  
    }  
  
}  

class JTableRenderer extends DefaultTableCellRenderer {

	protected void setValue(Object value) {
		if (value instanceof ImageIcon) {
			if (value != null) {
				ImageIcon d = (ImageIcon) value;
				setIcon(d);
			} else {
				setText("");
				setIcon(null);
			}
		} else {
			super.setValue(value);
		}
	}
}
