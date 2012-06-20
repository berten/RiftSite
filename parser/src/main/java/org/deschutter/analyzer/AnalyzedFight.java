package org.deschutter.analyzer;

import java.util.ArrayList;
import java.util.List;
import org.deschutter.analyzer.damage.done.DamageDone;
import org.deschutter.analyzer.damage.taken.DamageTaken;
import org.deschutter.analyzer.healing.done.HealingDone;

/**
 *
 * @author berten
 */
public class AnalyzedFight implements IContainDuration {

    private Integer duration = 0;
    private List<DamageDone> damageDone = new ArrayList<>();
    private List<DamageTaken> damageTaken = new ArrayList<>();
    private List<HealingDone> healingDone = new ArrayList<>();

    /*
     * Damage Done
     */
    private DamageDone getDamageDoneContainer(String name) {
        for (DamageDone damage : damageDone) {
            if (damage.getName().equals(name)) {
                return damage;
            }

        }
        final DamageDone newDamageDone = new DamageDone(name, this);
        damageDone.add(newDamageDone);
        return newDamageDone;
    }

    public void addDamageDone(Integer secondsIntoFight, String actor, Integer amount, String ability, Integer absorbed, Integer blocked, Integer deflected) {
        if (duration < secondsIntoFight + 1) {
            this.duration = secondsIntoFight + 1;
        }
        getDamageDoneContainer(actor).addDamage(amount, ability);
    }

    public List<DamageDone> getDamageDone() {
        return damageDone;
    }

    public DamageDone getDamageDone(String name) {
        return getDamageDoneContainer(name);
    }

    /*
     * Damage Taken
     */
    public DamageTaken getDamageTaken(String name) {
        return getDamageTakenContainer(name);
    }

    private DamageTaken getDamageTakenContainer(String name) {
        for (DamageTaken damage : damageTaken) {
            if (damage.getName().equals(name)) {
                return damage;
            }

        }
        final DamageTaken newDamageTaken = new DamageTaken(name, this);
        damageTaken.add(newDamageTaken);
        return newDamageTaken;
    }

    public void addDamageTaken(Integer secondsIntoFight, String target, Integer amount, String ability, Integer absorbed, Integer blocked, Integer deflected) {
        if (this.duration < secondsIntoFight + 1) {
            duration = secondsIntoFight + 1;
        }
        getDamageTakenContainer(target).addDamage(amount, ability, absorbed, blocked, deflected);


    }

    public List<DamageTaken> getDamageTaken() {
        return damageTaken;
    }

    /*
     * Healing Done
     */
    private HealingDone getHealingDoneContainer(String name) {
        for (HealingDone healing : healingDone) {
            if (healing.getName().equals(name)) {
                return healing;
            }

        }
        final HealingDone newHealingDone = new HealingDone(name, this);
        healingDone.add(newHealingDone);
        return newHealingDone;
    }

    public HealingDone getHealingDone(String name) {
        return getHealingDoneContainer(name);
    }

    public Integer getDuration() {
        return duration;
    }

    public void addHealingDone(Integer secondsIntoFight, String actor, Integer amount, String skill, Integer overheal) {

        if (this.duration < secondsIntoFight + 1) {
            duration = secondsIntoFight + 1;
        }
        getHealingDoneContainer(actor).addHeal(skill, amount, overheal);
    }
}