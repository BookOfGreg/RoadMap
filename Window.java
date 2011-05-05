import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import java.util.ArrayList;

/**
 * Write a description of class Window here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Window extends JFrame
{
    /**
     * Constructor for objects of class Window
     */
    public Window()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setVisible(true);
        this.createBufferStrategy(2);
    }
    
    public boolean render(ArrayList<Route> routes, String time)
    {
        BufferStrategy bf = this.getBufferStrategy();
        Graphics g = null;
        try {
            g = bf.getDrawGraphics();

            g.setColor(Color.white);
            g.fillRect(0,0,800,600);
            g.setColor(Color.black);
            g.drawString(time, 50, 50);
            for (Route r:routes)
            {
                g.setColor(Color.black);

                g.drawLine((int)Math.round(r.getNode1().getX()), 
                    (int)Math.round(r.getNode1().getY()),
                    (int)Math.round(r.getNode2().getX()), 
                    (int)Math.round(r.getNode2().getY()));

                g.fillOval((int)Math.round(r.getNode1().getX())-2,
                    (int)Math.round(r.getNode1().getY())-2,
                    4,
                    4);

                g.fillOval((int)Math.round(r.getNode2().getX())-2,
                    (int)Math.round(r.getNode2().getY())-2,
                    4,
                    4);

                g.setColor(Color.red);

                g.drawString(Character.toString(r.getNode1().getName()),
                    (int)Math.round(r.getNode1().getX()), 
                    (int)Math.round(r.getNode1().getY()));

                g.drawString(Character.toString(r.getNode2().getName()),
                    (int)Math.round(r.getNode2().getX()), 
                    (int)Math.round(r.getNode2().getY()));  

                for (Vehicle v:r.getVehicles())
                {
                    if (v.getType().equals("private"))
                    {
                        g.setColor(Color.green);
                    }
                    else
                    {
                        g.setColor(Color.blue);
                    }
                    r.getLength();
                    v.getDistanceTraveled();

                    // smallestx+((distance/length)*dx)
                    int posX = (int)Math.round(
                            Math.min(r.getNode1().getX(),
                                r.getNode2().getX()) 
                            +Math.abs(
                                (v.getDistanceTraveled()/r.getLength())
                                *(r.getNode1().getX()-r.getNode2().getX())
                            ));
                    // smallesty+((distance/length)*dy)
                    int posY = (int)Math.round(
                            Math.min(r.getNode1().getY(),
                                r.getNode2().getY()) 
                            +Math.abs(
                                (v.getDistanceTraveled()/r.getLength())
                                *(r.getNode1().getY()-r.getNode2().getY())
                            ));
                    g.drawString(v.getRegistration(),posX,posY);
                    
                    g.fillOval(posX-2,
                        posY-2,
                        4,
                        4);
                }

            }
            // It is assumed that mySprite is created somewhere else.
            // This is just an example for passing off the Graphics object.
            //mySprite.draw(g);

        } finally {
            // It is best to dispose() a Graphics object when done with it.
            g.dispose();
        }

        // Shows the contents of the backbuffer on the screen.
        bf.show();

        //Tell the System to do the Drawing now, otherwise it can take a few extra ms until 
        //Drawing is done which looks very jerky
        //Toolkit.getDefaultToolkit().sync();
        return true;
    }
}
