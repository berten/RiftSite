package org.deschutter.analyzer.damagetaken;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author berten
 */
public class DamageTaken {

    private String name;
    private Integer totalDamage = 0;
    private Map<String, Integer> abilities = new HashMap<>();

    public DamageTaken(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalDamage() {
        return totalDamage;
    }

    public Integer getTotalDamage(String ability) {
        return abilities.get(ability);
    }

    public void addDamage(Integer amount, String ability) {
        if (abilities.get(ability) == null) {
            abilities.put(ability, amount);
        } else {
            abilities.put(ability, abilities.get(ability) + amount);
        }
        this.totalDamage += amount;
    }

    public String toString() {
        return "DamageDone{" + "name=" + name + ", totalDamage=" + totalDamage + '}';
    }
}