package Utilitario;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

public class ImagenFondo implements Border {
    public BufferedImage back;

    public ImagenFondo() throws Exception {
        
        URL imagenPath = new URL(getClass().getResource("../Recursos/Imagenes/fondoLS4.png").toString());
        back = ImageIO.read(imagenPath);
        
    }    

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawImage(back, (x + (width - back.getWidth())/2), (y + (height - back.getHeight())/2), null);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
    
}
