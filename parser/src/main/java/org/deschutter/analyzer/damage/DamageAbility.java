package org.deschutter.analyzer.damage;

import org.deschutter.analyzer.IContainDuration;

import java.util.ArrayList;

/**
 *
 * @author berten
 */
public class DamageAbility {
    private final IContainDuration durationContainer;

    private static class Hit {

        private final Integer damage;
        private final Integer absorbed;
        private final Integer blocked;
        private final Integer deflected;

        public Hit(Integer damage, Integer absorbed, Integer blocked, Integer deflected) {
            this.damage = damage;
            this.absorbed = absorbed;
            this.blocked = blocked;
            this.deflected = deflected;
        }

        public Integer getAbsorbed() {
            return absorbed;
        }

        public Integer getBlocked() {
            return blocked;
        }

        public Integer getDamage() {
            return damage;
        }

        public Integer getDeflected() {
            return deflected;
        }
    }
    private String name;
    private ArrayList<Hit> hits = new ArrayList<>();
    
    
    private Integer totalDamage = 0;
    private Integer totalAbsorbed = 0;
    private Integer totalDeflected = 0;
    private Integer totalBlocked = 0;

    public DamageAbility(String name,IContainDuration durationContainer) {
        this.name = name;
        this.durationContainer = durationContainer;
    }

 



    public Integer getDamagePerSecond() {
        return totalDamage / durationContainer.getDuration();
    }

    public ArrayList<Hit> getHits() {
        return hits;
    }

    public String getName() {
        return name;
    }

    public void addHit(Integer hit, Integer absorbed, Integer blocked, Integer deflected) {
        
        totalAbsorbed += absorbed;
        totalDamage += hit;
        totalBlocked += blocked;
        totalDeflected += deflected;
        
        hits.add(new Hit(hit, absorbed, blocked, deflected));
    }

    public Integer getBiggestHit() {
        Integer biggest = 0;
        for (Hit hit : hits) {
            if (hit.getDamage() > biggest) {
                biggest = hit.getDamage();
            }
        }
        return biggest;
    }

    public Integer getTotalAbsorbed() {
        return totalAbsorbed;
    }

    public Integer getTotalBlocked() {
        return totalBlocked;
    }

    public Integer getTotalDamage() {
        return totalDamage;
    }

    public Integer getTotalDeflected() {
        return totalDeflected;
    }
}