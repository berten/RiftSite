package org.deschutter.analyzer.healingdone;

import org.deschutter.analyzer.AnalyzedFight;
import org.deschutter.analyzer.IAnalyzer;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
}