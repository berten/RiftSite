package org.deschutter.analyzer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author berten
 */
public class Damage implements IContainDuration {

    private String name;
    private Integer totalDamage = 0;
    private List<DamageAbility> abilities = new ArrayList<>();
    private Double damagePerSecond = 0d;
    private final AnalyzedFight fight;

    public Damage(String name, AnalyzedFight fight) {
        this.name = name;
        this.fight = fight;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalDamage() {
        return totalDamage;
    }

    public void recalculateDamagePerSecond() {
        this.damagePerSecond = new Double(totalDamage) / new Double(getDuration());
    }

    public void addDamage(Integer amount, String ability) {
        getAbility(ability).addHit(amount, 0, 0, 0);
        incrementTotalDamage(amount);
        recalculateDamagePerSecond();
    }

    public void incrementTotalDamage(Integer amount) {
        this.totalDamage += amount;
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
