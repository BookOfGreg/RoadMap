import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import java.util.ArrayList;

/**
 * Write a description of class Window here.
 * 
 * @author Greg Myers
 * @version 10/05/2011
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

    public boolean render(ArrayList<Route> routes, String time, String message)
    {
        BufferStrategy bf = this.getBufferStrategy();
        Graphics g = null;
        try{
            g = bf.getDrawGraphics();
            drawThings(g,routes,time);
            g.setColor(Color.red);
            g.drawString(message,50,320);
        }finally{
            g.dispose();
        }
        bf.show();
        return true;
    }

    private void drawThings(Graphics g,ArrayList<Route> routes, String time)
    {
        g.setColor(Color.white);
        g.fillRect(0,0,800,600);
        g.setColor(Color.black);
        g.drawString(time, 50, 50);
        g.drawString("Graph not to scale",50,40);
        for (Route r:routes){
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

            for (Vehicle v:r.getVehicles()){
                if (v.getType().equals("private")){
                    g.setColor(Color.green);
                }else{
                    g.setColor(Color.blue);
                }
                r.getLength();
                v.getDistanceTraveled();
                int posX = 0;
                int posY = 0;
                if (v.getTarget()==r.getNode1().getName()){
                    posX = (int)Math.round(r.getNode2().getX());
                    posY = (int)Math.round(r.getNode2().getY());
                    posX += (int)Math.round((v.getDistanceTraveled()/r.getLength())*(r.getNode1().getX() - r.getNode2().getX()));
                    posY += (int)Math.round((v.getDistanceTraveled()/r.getLength())*(r.getNode1().getY() - r.getNode2().getY()));
                }else{
                    posX = (int)Math.round(r.getNode1().getX());
                    posY = (int)Math.round(r.getNode1().getY());
                    posX += (int)Math.round((v.getDistanceTraveled()/r.getLength())*(r.getNode2().getX() - r.getNode1().getX()));
                    posY += (int)Math.round((v.getDistanceTraveled()/r.getLength())*(r.getNode2().getY() - r.getNode1().getY()));
                }
                g.drawString(v.getRegistration(),posX,posY);
                g.drawString(Float.toString(v.getTotalCost()),posX,posY-10);

                g.fillOval(posX-2,
                    posY-2,
                    4,
                    4);
            }
        }
    }

    public boolean render(ArrayList<Route> routes, String time)
    {
        BufferStrategy bf = this.getBufferStrategy();
        Graphics g = null;
        try{
            g = bf.getDrawGraphics();
            drawThings(g,routes,time);
        }finally{
            g.dispose();
        }
        bf.show();
        return true;
    }
}
