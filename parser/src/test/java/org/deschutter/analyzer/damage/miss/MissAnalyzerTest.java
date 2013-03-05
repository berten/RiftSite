package org.deschutter.analyzer.damage.miss;

import org.deschutter.analyzer.AnalyzedFight;
import org.deschutter.parser.actions.ActionTypeEnum;
import org.deschutter.parser.actions.MissAction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MissAnalyzerTest {

    private MissAnalyzer analyzer;
    private AnalyzedFight fight;

    @Before
    public void setUp() throws Exception {
        analyzer = new MissAnalyzer();
        fight = new AnalyzedFight();
    }

    @Test
    public void testAnalyze() throws Exception {
        analyzer.analyze(fight,new MissAction(ActionTypeEnum.ATTACK,"Nilus","Murdantix","Void Bolt",1));
        assertEquals(1,fight.getMiss("Nilus").getMisses().size());
    }
}
