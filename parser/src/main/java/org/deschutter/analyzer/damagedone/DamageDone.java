package org.deschutter.analyzer.damagedone;

/**
 *
 * @author berten
 */
public class DamageDone {

    private String name;
    private Integer totalDamage = 0;

    public DamageDone(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalDamage() {
        return totalDamage;
    }

    public void addDamage(Integer amount) {
        this.totalDamage += amount;
    }
}