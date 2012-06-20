package org.deschutter.analyzer.damagetaken;

import org.deschutter.analyzer.AnalyzedFight;
import org.deschutter.analyzer.IAnalyzer;
import org.deschutter.parser.actions.DamageTakenAction;
import org.deschutter.parser.actions.RiftAction;
import org.springframework.stereotype.Component;

/**
 *
 * @author berten
 */
@Component
public class DamageTakenAnalyzer implements IAnalyzer {

    @Override
    public void analyze(AnalyzedFight fight, RiftAction action) {
        if (action instanceof DamageTakenAction) {
            final DamageTakenAction damageTakenAction = (DamageTakenAction) action;

            fight.addDamageTaken(damageTakenAction.getSecondsIntoFight(), damageTakenAction.getTarget(), damageTakenAction.getAmount(), damageTakenAction.getSkill(),damageTakenAction.getAbsorbed(),damageTakenAction.getBlocked(),damageTakenAction.getDeflected());

        }
    }
}