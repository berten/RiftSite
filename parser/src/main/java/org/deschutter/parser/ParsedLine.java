package org.deschutter.parser;

import java.util.Date;

/**
 *
 * @author berten
 */
public class ParsedLine {

    public String line;
    //"20:23:29: ( 3 , T=P#R=R#353391833781567547 , T=N#R=O#9223372040713575731 , T=X#R=X#0 , T=X#R=X#0 , Mimii , Rusila Dreadblade , 991 , 1843227230 , Life's Vengeance ) Mimii's Life's Vengeance hits Rusila Dreadblade for 991 Life damage. "
    private String typeInteger = "0";
    private String actor = "";
    private String target = "";
    private String skill = "";
    private Integer amount = 0;
    private Integer secondsIntoFight = 0;

    public ParsedLine(Date fightStartTime, String line) {
        this.line = line;
        
        if (!line.isEmpty()) {
            final String[] splitted = line.split("\\(");
            final String[] split = splitted[1].split(",");

            this.typeInteger = split[0].trim();
            this.actor = split[5].trim();
            final String lastPart = split[9].trim();
            
            this.skill = lastPart.split("\\)")[0].trim();
            
            this.target = split[6].trim();
            this.amount = new Integer(split[7].trim());
        }
    }

    public CombatTypeEnum getType() {
        final Integer integer = new Integer(typeInteger);
        return CombatTypeEnum.byValue(integer);
    }

    public String getActor() {
        return this.actor;
    }

    String getTarget() {
        return this.target;
    }

    public String getSkill() {
        return skill;
    }

    public Integer getAmount() {
        return amount;
    }
    
    public Integer getSecondsIntoFight() {
        return 0;
    }
    
    
}