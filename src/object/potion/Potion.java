package object.potion;

import entity.JobClass;
import object.SuperObject;
import ui.GamePanel;

public class Potion extends SuperObject
{
    private int point;
    private String genre;

    public Potion(GamePanel gp) { super(gp); }

    public int getPoint() {return point;}
    public void setPoint(int point) {this.point = point;}
    public String getGenre() {return genre;}
    public void setGenre(String genre) {this.genre = genre;}
}

