package org.deschutter.analyzer.damagedone;

import java.util.ArrayList;
import java.util.List;
import org.deschutter.analyzer.AnalyzedFight;
import org.deschutter.analyzer.DamageAbility;
import org.deschutter.analyzer.IContainDuration;

/**
 *
 * @author berten
 */
public class DamageDone implements IContainDuration {

    private String name;
    private Integer totalDamage = 0;
    private List<DamageAbility> abilities = new ArrayList<>();
    private Double damagePerSecond = 0d;
    private final AnalyzedFight fight;

    public DamageDone(String name, AnalyzedFight fight) {
        this.name = name;
        this.fight = fight;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalDamage() {
        return totalDamage;
    }

    public void addDamage(Integer duration, Integer amount, String ability, Integer absorbed, Integer blocked, Integer deflected) {
        getAbility(ability).addHit(amount, absorbed, blocked, deflected);
        this.totalDamage += amount;
        this.damagePerSecond = new Double(totalDamage) / new Double(duration);
    }

    public String toString() {
        return "DamageDone{" + "name=" + name + ", totalDamage=" + totalDamage + '}';
    }

    public DamageAbility getAbility(String abilityName) {
        for (DamageAbility ability : abilities) {
            if (ability.getName().equals(abilityName)) {
                return ability;
            }
        }
        final DamageAbility damageAbility = new DamageAbility(abilityName, this);
        abilities.add(damageAbility);
        return damageAbility;
    }

    public Double getDamagePerSecond() {
        return damagePerSecond;
    }

    public List<DamageAbility> getAbilities() {
        return abilities;
    }

    @Override
    public Integer getDuration() {
        return fight.getDuration();
    }
}