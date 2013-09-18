package org.deschutter.analyzer;

import java.util.ArrayList;
import java.util.List;

import org.deschutter.analyzer.damage.DamageAbility;
import org.deschutter.analyzer.damage.done.DamageDone;
import org.deschutter.analyzer.damage.miss.Miss;
import org.deschutter.analyzer.damage.taken.DamageTaken;
import org.deschutter.analyzer.healing.done.HealingDone;
import org.deschutter.parser.actions.MissAction;

/**
 *
 * @author berten
 */
public class AnalyzedFight implements IContainDuration {

    private Integer duration = 0;
    private List<DamageDone> damageDone = new ArrayList<>();
    private List<DamageTaken> damageTaken = new ArrayList<>();
    private List<HealingDone> healingDone = new ArrayList<>();
    private List<Miss> misses = new ArrayList<>();

    /*
     * Damage Done
     */
    private DamageDone getDamageDoneContainer(String actor) {
        for (DamageDone damage : damageDone) {
            if (damage.getName().equals(actor)) {
                return damage;
            }

        }
        final DamageDone newDamageDone = new DamageDone(actor, this);
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

    public void addMissAction(Integer secondsIntoFight, String actor,String skill,String target) {
        if (this.duration < secondsIntoFight + 1) {
            duration = secondsIntoFight + 1;
        }
        getMissContainer(actor).addMiss(target,skill);
    }

    private Miss getMissContainer(String actor) {
        for (Miss miss : misses) {
            if(miss.getActor().equals(actor)) {
                      return miss;
            }
        }
        Miss miss = new Miss(actor);
        misses.add(miss) ;
        return miss;
    }

    public List<Miss> getMisses() {
        return misses;
    }

    public Miss getMiss(String name) {
        return getMissContainer(name);
    }

    public void print() {

        System.out.println("Fight Duration: " + duration + " seconds");

        System.out.println("Damage Done");
        for (DamageDone damage : damageDone) {
            System.out.println(damage.getName() + " :" + damage.getTotalDamage() + " " + damage.getDamagePerSecond());
            for (DamageAbility damageAbility : damage.getAbilities()) {
                System.out.println("              " + damageAbility.getName()+ ": " + damageAbility.getTotalDamage() + " Biggest Hit: " + damageAbility.getBiggestHit() + " Amount of hits: " + damageAbility.getHits().size());
            }


        }
        System.out.println("=================================================");
        System.out.println("Healing Done");
        for (HealingDone healing : healingDone) {
            System.out.println(healing.getName()+ " :" + healing.getTotalHealing() + " " +healing.getHealingPerSecond());
        }

        System.out.println("=================================================");
        System.out.println("Damage Taken");
        for (DamageTaken taken : damageTaken) {
                              System.out.println(taken.getName() + ": Absorbed: (" + taken.getTotalAbsorbed() + ") Blocked: (" + taken.getTotalBlocked() + ") Deflected: (" + taken.getTotalDeflected() + ") Taken: (" + taken.getTotalDamage()+")");
        }
        System.out.println("=================================================");
        System.out.println("Misses");
        for (Miss miss : misses) {
             System.out.println(miss.getActor() + ": " + miss.getMisses());
        }
    }
}