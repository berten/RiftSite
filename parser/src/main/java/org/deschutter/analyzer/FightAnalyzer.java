package org.deschutter.analyzer;

import java.util.List;
import org.deschutter.Fight;
import org.deschutter.parser.actions.RiftAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author berten
 */
@Service
public class FightAnalyzer {

    private List<IAnalyzer> analyzers;

    @Autowired
    public FightAnalyzer(List<IAnalyzer> analyzers) {
        this.analyzers = analyzers;
    }

    @Async
    public void analyzeFight(Fight fight) {
        final AnalyzedFight analyzedFight = new AnalyzedFight();
        for (RiftAction action : fight.getActions()) {
            for (IAnalyzer analyzer : analyzers) {

                analyzer.analyze(analyzedFight, action);
            }
        }
    }
}