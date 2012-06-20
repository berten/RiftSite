package org.deschutter.analyzer.healing.done;

import org.deschutter.analyzer.AnalyzedFight;
import org.deschutter.analyzer.IAnalyzer;
import org.deschutter.parser.actions.HealingAction;
import org.deschutter.parser.actions.RiftAction;
import org.springframework.stereotype.Component;

/**
 *
 * @author berten
 */
@Component
public class HealingDoneAnalyzer implements IAnalyzer {

    @Override
    public void analyze(AnalyzedFight fight, RiftAction action) {
     if(!(action instanceof HealingAction)) {
         return;
     }
     
      final HealingAction healingAction = (HealingAction) action;

            fight.addHealingDone(action.getSecondsIntoFight(), action.getActor(), healingAction.getAmount(), healingAction.getSkill(), healingAction.getOverheal());
    }
}