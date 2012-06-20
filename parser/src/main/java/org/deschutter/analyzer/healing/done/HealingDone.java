package org.deschutter.analyzer.healing.done;

import java.util.ArrayList;
import java.util.List;
import org.deschutter.analyzer.IContainDuration;
import org.deschutter.analyzer.healing.HealingAbility;

/**
 *
 * @author berten
 */
public class HealingDone implements IContainDuration {

    private String name;
    private IContainDuration durationContainer;
    private Integer totalHealing = 0;
    private List<HealingAbility> abilities = new ArrayList<>();

    public HealingDone(String name, IContainDuration durationContainer) {
        this.name = name;
        this.durationContainer = durationContainer;
    }

    public String getName() {
        return name;
    }

    @Override
    public Integer getDuration() {
        return durationContainer.getDuration();
    }

    public Integer getTotalHealing() {
        return totalHealing;
    }

    public Integer getHealingPerSecond() {
        return totalHealing / getDuration();
    }

    public HealingAbility getAbility(String abilityName) {
        for (HealingAbility ability : abilities) {
            if (ability.getName().equals(abilityName)) {
                return ability;
            }
        }
        final HealingAbility healingAbility = new HealingAbility(abilityName, this);
        abilities.add(healingAbility);
        return healingAbility;
    }

    public void addHeal(String skill, Integer amount, Integer overheal) {
        totalHealing += amount;
        getAbility(skill).addHeal(amount, overheal);
    }
}