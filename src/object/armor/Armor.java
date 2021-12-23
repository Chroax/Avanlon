package object.armor;

import entity.JobClass;
import object.SuperObject;
import ui.GamePanel;

public class Armor extends SuperObject
{
    private int spd;
    private int phyDef;
    private int magDef;

    private JobClass jobClass;

    public Armor(GamePanel gp) { super(gp); }

    public int getSpd() {return spd;}
    public void setSpd(int spd) {this.spd = spd;}
    public int getPhyDef() {return phyDef;}
    public void setPhyDef(int phyDef) {this.phyDef = phyDef;}
    public int getMagDef() {return magDef;}
    public void setMagDef(int magDef) {this.magDef = magDef;}
    public JobClass getJobClass() {return jobClass;}
    public void setJobClass(JobClass jobClass) {this.jobClass = jobClass;}
}
