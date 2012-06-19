package org.deschutter.analyzer;

import java.util.ArrayList;
import java.util.List;
import org.deschutter.analyzer.damagedone.DamageDone;
import org.deschutter.analyzer.damagetaken.DamageTaken;

/**
 *
 * @author berten
 */
public class AnalyzedFight {

    private Integer duration = 0;
    private List<DamageDone> damageDone = new ArrayList<>();
    private List<DamageTaken> damageTaken = new ArrayList<>();



    private DamageDone getDamageDoneContainer(String name) {
        for (DamageDone damage : damageDone) {
            if (damage.getName().equals(name)) {
                return damage;
            }

        }
        final DamageDone newDamageDone = new DamageDone(name);
        damageDone.add(newDamageDone);
        return newDamageDone;
    }

    public void addDamageDone(Integer secondsIntoFight,String actor, Integer amount, String ability) {
        if(duration < secondsIntoFight +1)
        this.duration = secondsIntoFight + 1;
        getDamageDoneContainer(actor).addDamage(amount, ability,duration);
    }

    public List<DamageDone> getDamageDone() {
        return damageDone;
    }

    public DamageDone getDamageDone(String name) {
        return getDamageDoneContainer(name);
    }

    public DamageTaken getDamageTaken(String name) {
        return getDamageTakenContainer(name);
    }

    private DamageTaken getDamageTakenContainer(String name) {
        for (DamageTaken damage : damageTaken) {
            if (damage.getName().equals(name)) {
                return damage;
            }

        }
        final DamageTaken newDamageTaken = new DamageTaken(name);
        damageTaken.add(newDamageTaken);
        return newDamageTaken;
    }

    public void addDamageTaken(Integer secondsIntoFight,String target, Integer amount, String ability) {
        if(this.duration < secondsIntoFight + 1)
            duration = secondsIntoFight + 1;
        getDamageTakenContainer(target).addDamage(amount, ability,duration);
    }

    public List<DamageTaken> getDamageTaken() {
        return damageTaken;
    }

    public String toString() {
        return "AnalyzedFight{" + "duration=" + duration + ", damageDone=" + damageDone + '}';
    }

    public Integer getDuration() {
        return duration;
    }
    
    
}
