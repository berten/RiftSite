package org.deschutter.analyzer.damagedone;

import org.deschutter.analyzer.AnalyzedFight;
import org.deschutter.analyzer.IAnalyzer;
import org.deschutter.parser.actions.DamageDoneAction;
import org.deschutter.parser.actions.RiftAction;
import org.springframework.stereotype.Component;

/**
 *
 * @author berten
 */
@Component
public class DamageDoneAnalyzer implements IAnalyzer {

    @Override
    public void analyze(AnalyzedFight fight, RiftAction action) {
        if (action instanceof DamageDoneAction) {
            final DamageDoneAction damageDoneAction = (DamageDoneAction) action;
            
            fight.addDamageDone(action.getActor(), damageDoneAction.getAmount());
            fight.setDuration(action.getSecondsIntoFight());
        }
    }
}