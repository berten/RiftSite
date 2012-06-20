package org.deschutter.analyzer.healing.done;

import org.deschutter.analyzer.AnalyzedFight;
import org.deschutter.parser.actions.HealingAction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author berten
 */
public class HealingDoneAnalyzerTest {

    private HealingDoneAnalyzer analyzer;
    private AnalyzedFight fight;

    @Before
    public void setUp() {
        analyzer = new HealingDoneAnalyzer();
    }

    @Test
    public void single_heal() {
        fight = new AnalyzedFight();
        analyzer.analyze(fight, new HealingAction("Nilus", "Syldon", "Bloom", 3000, 0, Boolean.FALSE, 200));

        assertEquals(new Integer(3000), fight.getHealingDone("Nilus").getTotalHealing());
        assertEquals(new Integer(3000), fight.getHealingDone("Nilus").getHealingPerSecond());
        assertEquals(new Integer(3000), fight.getHealingDone("Nilus").getAbility("Bloom").getTotalHealing());
        assertEquals(new Integer(3000), fight.getHealingDone("Nilus").getAbility("Bloom").getHealingPerSecond());
        assertEquals(new Integer(200), fight.getHealingDone("Nilus").getAbility("Bloom").getOverHealing());
    }

    @Test
    public void two_heals() {
        fight = new AnalyzedFight();
        analyzer.analyze(fight, new HealingAction("Nilus", "Syldon", "Bloom", 3000, 0, Boolean.FALSE, 0));
        analyzer.analyze(fight, new HealingAction("Nilus", "Syldon", "Essence Surge", 8000, 1, Boolean.FALSE, 200));

        assertEquals(new Integer(11000), fight.getHealingDone("Nilus").getTotalHealing());
        assertEquals(new Integer(5500), fight.getHealingDone("Nilus").getHealingPerSecond());
        
        assertEquals(new Integer(3000), fight.getHealingDone("Nilus").getAbility("Bloom").getTotalHealing());
        assertEquals(new Integer(1500), fight.getHealingDone("Nilus").getAbility("Bloom").getHealingPerSecond());
        assertEquals(new Integer(0), fight.getHealingDone("Nilus").getAbility("Bloom").getOverHealing());

        assertEquals(new Integer(8000), fight.getHealingDone("Nilus").getAbility("Essence Surge").getTotalHealing());
        assertEquals(new Integer(4000), fight.getHealingDone("Nilus").getAbility("Essence Surge").getHealingPerSecond());
        assertEquals(new Integer(200), fight.getHealingDone("Nilus").getAbility("Essence Surge").getOverHealing());
    }
}