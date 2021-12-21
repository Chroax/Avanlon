package object.weapon;

import entity.JobClass;
import object.SuperObject;
import ui.GamePanel;

public class Weapon extends SuperObject
{
    private int spd;
    private int phyDamage;
    private int magDamage;

    private JobClass jobClass;

    public Weapon(GamePanel gp) { super(gp); }

    public int getSpd() {return spd;}
    public void setSpd(int spd) {this.spd = spd;}
    public int getPhyDamage() {return phyDamage;}
    public void setPhyDamage(int phyDamage) {this.phyDamage = phyDamage;}
    public int getMagDamage() {return magDamage;}
    public void setMagDamage(int magDamage) {this.magDamage = magDamage;}
    public JobClass getJobClass() {return jobClass;}
    public void setJobClass(JobClass jobClass) {this.jobClass = jobClass;}
}
