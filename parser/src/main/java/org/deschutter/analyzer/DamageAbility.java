/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.deschutter.analyzer;

import java.util.ArrayList;

/**
 *
 * @author berten
 */
public class DamageAbility {

    private String name;
    private ArrayList<Integer> hits = new ArrayList<>();
    private Integer duration = 0;

    public DamageAbility(String name) {
        this.name = name;
    }

    public Integer getTotalDamage() {
        Integer dmg = 0;
        for (Integer hit : hits) {
            dmg += hit;
        }
        return dmg;
    }
    
    public Double getDamagePerSecond() {
        return new Double(getTotalDamage() / duration);
    }

    public ArrayList<Integer> getHits() {
        return hits;
    }

    public String getName() {
        return name;
    }

    public void addHit(Integer hit,Integer duration) {
        if (this.duration < duration) this.duration = duration;
        hits.add(hit);
    }

    public Integer getBiggestHit() {
        Integer biggest = 0;
        for (Integer hit : hits) {
            if (hit > biggest) {
                biggest = hit;
            }
        }
        return biggest;
    }
}
