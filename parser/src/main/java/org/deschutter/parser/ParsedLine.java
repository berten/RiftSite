package org.deschutter.parser;

import java.util.Calendar;
import java.util.Date;
import org.deschutter.parser.actions.DamageTypeEnum;

/**
 *
 * @author berten
 */
public class ParsedLine {

    private String line;
    //"20:23:29: ( 3 , T=P#R=R#353391833781567547 , T=N#R=O#9223372040713575731 , T=X#R=X#0 , T=X#R=X#0 , Mimii , Rusila Dreadblade , 991 , 1843227230 , Life's Vengeance ) Mimii's Life's Vengeance hits Rusila Dreadblade for 991 Life damage. "
    private String typeInteger = "0";
    private String actor = "";
    private String target = "";
    private String skill = "";
    private Integer amount = 0;
    private Integer secondsIntoFight = 0;
    private DamageTypeEnum damageType;
    private Integer absorbed = 0;
    private Integer blocked = 0;
    private Integer overheal = 0;
    private Integer overkill = 0;
    private Integer deflected = 0;
    private Boolean actorIsInRaid = Boolean.FALSE;
    private Boolean targetIsInRaid = Boolean.FALSE;
    private Date date;

    //Todo this isn't clean at all - fix this!!
    public ParsedLine(Date fightStartTime, String line) {
        this.line = line;

        if (!line.isEmpty()) {
            line = line.replace("(Ranged)","Ranged");
            final String[] splitted = line.split("\\(");
            final String[] split = splitted[1].split(",");

            this.typeInteger = split[0].trim();
            // Calculate actor is in raid
            final String[] actor = split[1].trim().split("#");
            this.actorIsInRaid = !actor[1].trim().equals("R=O");

            // Calculate actor is in raid
            final String[] target = split[2].trim().split("#");
            this.targetIsInRaid = !target[1].trim().equals("R=O");

            this.actor = split[5].trim();
            final String lastPart = split[9].trim();

            this.skill = lastPart.split("\\)")[0].trim();
            
            final String lineDescription = lastPart.split("\\)")[1];
            determinDamageType(lineDescription);

            this.target = split[6].trim();
            this.amount = new Integer(split[7].trim());
            
            if (splitted.length == 3) {
                final String[] mitigatioinsAndOverages = splitted[2].split("\\)")[0].split(" ");
                calculateMitigationsAndOverages(mitigatioinsAndOverages);
            }
            
            calculateTimes(splitted[0].split(":"), fightStartTime);
        }
    }

    private void determinDamageType(final String lineDescription) {
        //TODO This is ugly, but well it works for now
        if (lineDescription.contains("Life damage")) {
            this.damageType = DamageTypeEnum.LIFE;
        } else if (lineDescription.contains("Fire damage")) {
            this.damageType = DamageTypeEnum.FIRE;
        } else if (lineDescription.contains("Air damage")) {
            this.damageType = DamageTypeEnum.AIR;
        } else if (lineDescription.contains("Death damage")) {
            this.damageType = DamageTypeEnum.DEATH;
        } else if (lineDescription.contains("Earth damage")) {
            this.damageType = DamageTypeEnum.EARTH;
        } else if (lineDescription.contains("Water damage")) {
            this.damageType = DamageTypeEnum.WATER;
        } else if (lineDescription.contains("Physical damage")) {
            this.damageType = DamageTypeEnum.PHYSICAL;
        }
    }

    private void calculateMitigationsAndOverages(final String[] lastPartSplit) throws NumberFormatException {
        for (int i = 0; i < lastPartSplit.length; i++) {
            switch (lastPartSplit[i]) {
                case "absorbed":
                    this.absorbed = new Integer(lastPartSplit[i - 1]);
                    break;
                case "blocked":
                    this.blocked = new Integer(lastPartSplit[i - 1]);
                    break;
                case "overheal":
                    this.overheal = new Integer(lastPartSplit[i - 1]);
                    break;
                case "overkill":
                    this.overkill = new Integer(lastPartSplit[i - 1]);
                    break;
                case "deflected":
                    this.deflected = new Integer(lastPartSplit[i - 1]);
                    break;
            }
        }
    }

    private void calculateTimes(final String[] time, Date fightStartTime) throws NumberFormatException {
        Calendar cal = Calendar.getInstance();

        if (fightStartTime == null) {
            cal.setTime(new Date());
        } else {
            cal.setTime(fightStartTime);
        }



        cal.set(Calendar.HOUR_OF_DAY, new Integer(time[0]));
        cal.set(Calendar.MINUTE, new Integer(time[1]));
        cal.set(Calendar.SECOND, new Integer(time[2]));

        if (fightStartTime == null) {
            fightStartTime = cal.getTime();
        }

        if (fightStartTime.getTime() > cal.getTime().getTime()) {
            cal.add(Calendar.DATE, 1);
        }


        this.secondsIntoFight = new Long((cal.getTime().getTime() - fightStartTime.getTime()) / 1000).intValue();
        this.date = cal.getTime();
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
        return secondsIntoFight;
    }

    public DamageTypeEnum getDamageType() {
        return damageType;
    }

    public Integer getAbsorbed() {
        return absorbed;
    }

    public Integer getBlocked() {
        return blocked;
    }

    public Integer getOverheal() {
        return overheal;
    }

    public Integer getOverkill() {
        return overkill;
    }

    public Integer getDeflected() {
        return deflected;
    }

    public Boolean getActorIsInRaid() {
        return actorIsInRaid;
    }

    public Boolean getTargetIsInRaid() {
        return targetIsInRaid;
    }

    public String toString() {
        return "ParsedLine{" + "line=" + line + ", typeInteger=" + typeInteger + ", actor=" + actor + ", target=" + target + ", skill=" + skill + ", amount=" + amount + ", secondsIntoFight=" + secondsIntoFight + ", damageType=" + damageType + '}';
    }

    public Date getDate() {
        return date;
    }
}