package cpa.inventeur;

/**
 * @author Liu Jiaqi
 * @date 2018-4-12
 */
public enum Skill {
	PHYSICS("physics"),CHEMISTRY("chemistry"),MACHINERY("machinery"),MATHS("maths");
	private String name;
	
	Skill(String name){
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
