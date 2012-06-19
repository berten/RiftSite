package org.deschutter.analyzer.damagetaken;

import java.util.ArrayList;
import java.util.List;
import org.deschutter.analyzer.DamageAbility;

/**
 *
 * @author berten
 */
public class DamageTaken {

    private String name;
    private Integer totalDamage = 0;
    private List<DamageAbility> abilities = new ArrayList<>();
    private Double damagePerSecond = 0d;

    public DamageTaken(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalDamage() {
        return totalDamage;
    }

    public void addDamage(Integer amount, String ability, Integer duration) {
        getAbility(ability).addHit(amount, duration);
        this.totalDamage += amount;
        this.damagePerSecond = new Double(totalDamage) / new Double(duration);
    }

    public String toString() {
        return "DamageTaken{" + "name=" + name + ", totalDamage=" + totalDamage + '}';
    }

    public DamageAbility getAbility(String abilityName) {
        for (DamageAbility ability : abilities) {
            if (ability.getName().equals(abilityName)) {
                return ability;
            }
        }
        final DamageAbility damageAbility = new DamageAbility(abilityName);
        abilities.add(damageAbility);
        return damageAbility;
    }

    public Double getDamagePerSecond() {
        return damagePerSecond;
    }
}