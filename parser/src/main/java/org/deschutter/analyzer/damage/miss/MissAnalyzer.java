package org.deschutter.analyzer.damage.miss;

import org.deschutter.analyzer.AnalyzedFight;
import org.deschutter.analyzer.IAnalyzer;
import org.deschutter.parser.actions.MissAction;
import org.deschutter.parser.actions.RiftAction;
import org.springframework.stereotype.Component;

@Component
public class MissAnalyzer implements IAnalyzer {
    @Override
    public void analyze(AnalyzedFight fight, RiftAction action) {
        if(action instanceof MissAction) {
            fight.addMissAction(action.getSecondsIntoFight(),action.getActor(),action.getSkill(),((MissAction) action).getTarget());
        }
    }
}
