package org.deschutter.analyzer.damagedone;

import org.deschutter.analyzer.AnalyzedFight;
import org.deschutter.parser.actions.DamageDoneAction;
import org.deschutter.parser.actions.DamageTypeEnum;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author berten
 */
public class DamageDoneAnalyzerTest {

    private AnalyzedFight analyzedFight;
    private DamageDoneAnalyzer analyzer;

    @Before
    public void setUp() {
        analyzer = new DamageDoneAnalyzer();
    }

    @Test
    public void one_Attack_registers_damage_done() {
        analyzedFight = new AnalyzedFight();
        analyzer.analyze(analyzedFight, new DamageDoneAction("Nilus", "Murdantix", "Fireball", 3458, 1, DamageTypeEnum.FIRE, Boolean.FALSE, 0, 0, 0, 0));

        assertEquals(new Integer(3458), analyzedFight.getDamageDone("Nilus"));
        assertEquals(new Double(1729),analyzedFight.getDamagePerSecond("Nilus"));
    }
}