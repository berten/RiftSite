package org.deschutter.analyzer.damagetaken;

import java.util.ArrayList;
import java.util.List;
import org.deschutter.analyzer.AnalyzedFight;
import org.deschutter.analyzer.DamageAbility;
import org.deschutter.analyzer.IContainDuration;

/**
 *
 * @author berten
 */
public class DamageTaken implements IContainDuration{

    private String name;
    private Integer totalDamage = 0;
    private Integer totalBlocked = 0;
    private Integer totalDeflected = 0;
    private Integer totalAbsorbed = 0;
    private List<DamageAbility> abilities = new ArrayList<>();
    private AnalyzedFight fight;
    

    public DamageTaken(String name,AnalyzedFight fight) {
        this.name = name;
        this.fight = fight;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalDamage() {
        return totalDamage;
    }

    public void addDamage(Integer amount, String ability, Integer absorbed, Integer blocked, Integer deflected) {
        getAbility(ability).addHit(amount, absorbed, blocked, deflected);
        this.totalDamage += amount;
        this.totalBlocked += blocked;
        this.totalDeflected += deflected;
        this.totalAbsorbed += absorbed;
        
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
        final DamageAbility damageAbility = new DamageAbility(abilityName,this);
        abilities.add(damageAbility);
        return damageAbility;
    }

    public Double getDamagePerSecond() {
        return new Double(totalDamage) / new Double(getDuration());
    }

    public Integer getTotalAbsorbed() {
        return totalAbsorbed;
    }

    public Integer getTotalBlocked() {
        return totalBlocked;
    }

    public Integer getTotalDeflected() {
        return totalDeflected;
    }

    @Override
    public Integer getDuration() {
        return fight.getDuration();
    }
    
    
}