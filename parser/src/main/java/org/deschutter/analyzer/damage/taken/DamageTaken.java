package org.deschutter.analyzer.damage.taken;

import org.deschutter.analyzer.AnalyzedFight;
import org.deschutter.analyzer.damage.Damage;

/**
 *
 * @author berten
 */
public class DamageTaken extends Damage {

    private Integer totalBlocked = 0;
    private Integer totalDeflected = 0;
    private Integer totalAbsorbed = 0;

    public DamageTaken(String name, AnalyzedFight fight) {
        super(name, fight);
    }

    public void addDamage(Integer amount, String ability, Integer absorbed, Integer blocked, Integer deflected) {
        getAbility(ability).addHit(amount, absorbed, blocked, deflected);
        incrementTotalDamage(amount);
        recalculateDamagePerSecond();
        this.totalBlocked += blocked;
        this.totalDeflected += deflected;
        this.totalAbsorbed += absorbed;
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
}