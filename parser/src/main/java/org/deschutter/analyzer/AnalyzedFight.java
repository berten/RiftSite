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

    public Double getDamagePerSecond(String name) {
        return new Double(getDamageDone(name)) / new Double(duration);
    }

    private DamageDone getDamageDoneContainer(String name) {
        for (DamageDone damage : damageDone) {
            System.out.println(damage.getName() + " " + name);
            if (damage.getName().equals(name)) {
                return damage;
            }

        }
        return new DamageDone(name);
    }

    public void addDamageDone(String actor, Integer amount) {
        getDamageDoneContainer(actor).addDamage(amount);
    }
}
