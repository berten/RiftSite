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

    public void setDuration(Integer secondsIntoFight) {
        this.duration = secondsIntoFight + 1;
    }

    public Integer getDamageDone(String name) {
        return getDamageDoneContainer(name).getTotalDamage();
    }

    public Integer getDamageDone(String name, String ability) {
        return getDamageDoneContainer(name).getTotalDamage(ability);
    }

    public Double getDamageDonePerSecond(String name) {
        return new Double(getDamageDone(name)) / new Double(duration);
    }

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

    public void addDamageDone(String actor, Integer amount, String ability) {
        getDamageDoneContainer(actor).addDamage(amount, ability);
    }

    public List<DamageDone> getDamageDone() {
        return damageDone;
    }

    public Integer getDamageTaken(String name) {
        return getDamageTakenContainer(name).getTotalDamage();
    }

    public Integer getDamageTaken(String name, String ability) {
        return getDamageTakenContainer(name).getTotalDamage(ability);
    }

    public Double getDamageTakenPerSecond(String name) {
        return new Double(getDamageTaken(name)) / new Double(duration);
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

    public void addDamageTaken(String target, Integer amount, String ability) {
        getDamageTakenContainer(target).addDamage(amount, ability);
    }

    public List<DamageTaken> getDamageTaken() {
        return damageTaken;
    }

    public String toString() {
        return "AnalyzedFight{" + "duration=" + duration + ", damageDone=" + damageDone + '}';
    }
}
