package org.deschutter.analyzer;

import org.deschutter.parser.actions.RiftAction;
import org.deschutter.parser.actions.DamageDoneAction;
import org.deschutter.Fight;
import java.util.ArrayList;
import java.util.List;
import org.deschutter.analyzer.damagedone.DamageDoneAnalyzer;
import org.deschutter.parser.actions.DamageTypeEnum;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

/**
 *
 * @author berten
 */
public class FightAnalyzerTest {

    private FightAnalyzer analyzer;
    private List<IAnalyzer> analyzers = new ArrayList<>();
    private DamageDoneAnalyzer damageDoneAnalyzer;
    private Fight fight;

    @Before
    public void setUp() {
        damageDoneAnalyzer = mock(DamageDoneAnalyzer.class);
        analyzers.add(damageDoneAnalyzer);
        this.analyzer = new FightAnalyzer(analyzers);
        fight = new Fight();


    }

    @Test
    public void callsAnalyzer() {
        final RiftAction riftAction = new DamageDoneAction("Nilus", "Murdantix", "Fireball", 1, 0, DamageTypeEnum.FIRE, Boolean.FALSE, 0, 0, 0, 0);
        fight.addAction(riftAction);
        analyzer.analyzeFight(fight);
        verify(damageDoneAnalyzer).analyze(any(AnalyzedFight.class), eq(riftAction));
    }
}