package org.deschutter.analyzer.damage.miss;


import org.deschutter.analyzer.damage.MissAbility;

import java.util.ArrayList;
import java.util.List;

public class Miss {
    private String actor;
    private List<MissAbility> misses = new ArrayList<>();

    public Miss(String actor) {
        this.actor = actor;
    }

    public String getActor() {
        return actor;
    }

    public void addMiss(String target, String skill) {
        misses.add(new MissAbility(target,skill));
    }

    public List<MissAbility> getMisses() {
        return misses;
    }
}
