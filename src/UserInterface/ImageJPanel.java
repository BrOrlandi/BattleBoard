package UserInterface;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageJPanel extends JPanel  
{  
    BufferedImage background = null;  
      
    public ImageJPanel(BufferedImage img)  
    {  
        if (img == null)  
            throw new NullPointerException("Buffered image cannot be null!");  
        this.background = img;  
    }  

    public ImageJPanel(File imgSrc) throws IOException  
    {  
        this(ImageIO.read(imgSrc));  
    }  
  
    public ImageJPanel(String fileName) throws IOException  
    {  
        this(new File(fileName));  
    }  
      
    protected void paintComponent(Graphics g)  
    {          
    super.paintComponent(g);  
        Graphics2D g2d = (Graphics2D)g.create();  
        g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);  
        g2d.dispose();          
    }    
    
    public void setImage(String fileName) throws IOException
    {	
    	background = null;
    	background = ImageIO.read(new File(fileName));
    	repaint();
    }
}  