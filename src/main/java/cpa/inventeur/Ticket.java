package cpa.inventeur;

/**
 * @author Wu Kejia
 * @date 2018-5-08
 */
public enum Ticket {
    
    SETALLFREE("Set all Free"), ADDONEPOINT("Add a point");
    
    private String name;

    Ticket(String ticket) {
        this.name = ticket;
    }

    @Override
    public String toString() {
        return name;
    }
}
