package cpa.inventeur.cardsenums;

import static cpa.inventeur.cardsenums.Skill.CHEMISTRY;
import static cpa.inventeur.cardsenums.Skill.MACHINERY;
import static cpa.inventeur.cardsenums.Skill.MATHS;
import static cpa.inventeur.cardsenums.Skill.PHYSICS;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wu Kejia
 * @author Huang Shenyuan
 * @date 2018-4-13
 */
public enum Invention {
    /**
     * FOR TEST
     */
    CAR("  CAR  ", 3, 1, 2, 2), PLANE(" PLANE ", 2, 4, 1, 3), BOAT(" BOAT  ", 3, 3, 3, 0), BIKE("  BIKE ", 1, 0, 2,2), 
    /**
     * USED IN GAME
     */
    ARROW(" ARROW ", 1, 0, 2, 2), STONEZAX("STONEZAX", 2, 0, 2, 1), VESSEL(" VESSEL", 2, 0, 1, 2), CHARIOT("CHARIOT", 1, 0, 3,1), 
    SMOKE(" SMOKE ", 1, 3, 0, 1), TOWER(" TOWER ", 2, 0, 2, 1), FRESCO(" FRESCO ", 1, 3, 0,1), PAPER("  PAPER ", 0, 3, 1, 1), 
    SUNDIAL("SUNDIAL ", 1, 0, 1, 3), WATERCLOCK("WATERCLK",0, 2, 1, 2), FIRE("  FIRE ", 3, 1, 1, 0), OILLAMP(" OILLAMP", 1, 3, 1, 0);

    private static final boolean FINISHED = true;
    private static final boolean INPROGRESS = false;
    private static final boolean SUCCESS = true;
    private static final boolean FAILED = false;
    private final String name;
    private boolean state;
    private final EnumMap<Skill, Integer> demande = new EnumMap<>(Skill.class);
    private final EnumMap<Skill, Integer> iniDemande = new EnumMap<>(Skill.class);
    private List<Ticket> tickets = new ArrayList<>();
    EnumMap<PlayerColor,Integer> contributer = new EnumMap<>(PlayerColor.class);

    Invention(String name, int phy, int che, int mac, int mat) {
        this.name = name;
        state = INPROGRESS;
        demande.put(PHYSICS, phy);
        demande.put(CHEMISTRY, che);
        demande.put(MACHINERY, mac);
        demande.put(MATHS, mat);
        iniDemande.putAll(demande);
    }

    public boolean addInventor(Inventor inventor) {
        boolean flag = FAILED;
        boolean st = FINISHED;
        int mark = countDemands();
        for (EnumMap.Entry<Skill, Integer> entry : demande.entrySet()) {
            Skill key = entry.getKey();
            int needs = entry.getValue();
            int has = inventor.getSkillValue(key);
            if (needs > 0 && has > 0) {
                demande.replace(key, needs, residualDemande(has, needs));
                flag = SUCCESS;
            }
            if (entry.getValue() > 0)
                st = INPROGRESS;
        }
        if (flag) {
            int contribute = mark-countDemands();
            PlayerColor color = inventor.getColor();
            if(!contributer.containsKey(color))
                contributer.put(color,contribute);
            else
                contributer.replace(color,contributer.get(color) + contribute);
        }
        this.state = st;
        return flag;
    }

    /**
     * @return true if all demandes have been met
     */
    public boolean isFinished() {
        return state;
    }

    public void setFinished() {
        this.state = FINISHED;
    }

    public String toCard() {
        StringBuilder invention = new StringBuilder();
        invention.append("\n\t|---------------|\n\t|===>" + name + "<===|\n");
        if (this.isFinished())
            invention.append("\t|- IS FINISHED -|\n");
        else
            invention.append("\t|- IN PROGRESS -|\n");
        invention.append("\t|---------------|\n");
        for (Skill skill : demande.keySet()) {
            invention.append("\t|-" + skill + toStars(skill) + "\n");
        }
        for (Ticket tic : tickets) {
            invention.append("\t["+tic+"]\n");
        }
        return invention.toString();
    }

    @Override
    public String toString() {
        return name;
    }

    public int getDemandeValue(Skill skill) {
        return demande.get(skill);
    }

    public int residualDemande(int has, int demande) {
        demande -= has;
        return demande <= 0 ? 0 : demande;
    }

    public void initial() {
        demande.putAll(iniDemande);
        state = INPROGRESS;
        contributer.clear();
        tickets.clear();
    }

    String toStars(Skill skill) {
        StringBuilder stars = new StringBuilder();
        int iniNum = this.iniDemande.get(skill);
        int num = iniNum - this.getDemandeValue(skill);
        int max = iniNum;
        for (int i = 0; i < max; i++) {
            if (num > 0)
                stars.append("★");
            else
                stars.append("☆");
            num--;
        }
        return stars.toString();
    }
    
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
    
    public boolean removeTicket(Ticket ticket) {
        for(int i = 0 ; i<tickets.size();i++)
            if(tickets.get(i).equals(ticket)) {
                tickets.remove(i);
                return true;
            }
        return false;
                
    }
    
    public List<Ticket> getTicket() {
        List<Ticket> hasTicket = new ArrayList<>();
        hasTicket.addAll(tickets);
        return hasTicket;
    }
    
    private int countDemands() {
        return demande.values().stream().reduce(0,(total,count)->total+count);
    }
    
    public int getContribute(PlayerColor color) {
        return contributer.get(color);
    }
    
    public Map<PlayerColor,Integer> getContributers(){
        EnumMap<PlayerColor,Integer> find = new EnumMap<>(PlayerColor.class);
        find.putAll(contributer);
        return find;
    }
}
