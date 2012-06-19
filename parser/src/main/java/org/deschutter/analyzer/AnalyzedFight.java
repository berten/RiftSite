package org.deschutter.analyzer;

import java.util.ArrayList;
import java.util.List;
import org.deschutter.analyzer.damagedone.DamageDone;

/**
 *
 * @author berten
 */
public class AnalyzedFight {

    private Integer duration = 0;
    private List<DamageDone> damageDone = new ArrayList<>();

    public void setDuration(Integer secondsIntoFight) {
        this.duration = secondsIntoFight + 1;
    }

    public Integer getDamageDone(String name) {
        return getDamageDoneContainer(name).getTotalDamage();
    }

    public Integer getDamageDone(String name, String ability) {
        return getDamageDoneContainer(name).getTotalDamage(ability);
    }

    public Double getDamagePerSecond(String name) {
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

    public String toString() {
        return "AnalyzedFight{" + "duration=" + duration + ", damageDone=" + damageDone + '}';
    }
}
