package org.deschutter.analyzer.healing;

import java.util.ArrayList;
import java.util.List;
import org.deschutter.analyzer.IContainDuration;

/**
 *
 * @author berten
 */
public class HealingAbility {

   

    private static class Heal {

        private final Integer heal;
        private final Integer overheal;

        public Heal(Integer heal, Integer overheal) {
            this.heal = heal;
            this.overheal = overheal;
        }

        public Integer getHeal() {
            return heal;
        }

        public Integer getOverheal() {
            return overheal;
        }
    }
    private String name;
    private IContainDuration durationContainer;
    private List<Heal> heals = new ArrayList<>();

    public HealingAbility(String name, IContainDuration durationContainer) {
        this.name = name;
        this.durationContainer = durationContainer;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalHealing() {
        Integer healingDone = 0;
        for (Heal heal : heals) {
            healingDone += heal.getHeal();
        }
        return healingDone;
    }

    public Integer getHealingPerSecond() {
        return getTotalHealing() / durationContainer.getDuration();
    }

    public Integer getOverHealing() {
        Integer overHealingDone = 0;
        for (Heal heal : heals) {
            overHealingDone += heal.getOverheal();
        }
        return overHealingDone;
    }
    
     public void addHeal(Integer amount, Integer overheal) {
        heals.add(new Heal(amount,overheal));
    }
}