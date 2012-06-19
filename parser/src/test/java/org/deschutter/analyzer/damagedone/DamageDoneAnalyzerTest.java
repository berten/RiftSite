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
        assertEquals(new Double(1729), analyzedFight.getDamagePerSecond("Nilus"));
        assertEquals(new Integer(3458), analyzedFight.getDamageDone("Nilus", "Fireball"));
    }

    @Test
    public void two_Attacks_registers_damage_done() {
        analyzedFight = new AnalyzedFight();
        analyzer.analyze(analyzedFight, new DamageDoneAction("Nilus", "Murdantix", "Fireball", 3458, 0, DamageTypeEnum.FIRE, Boolean.FALSE, 0, 0, 0, 0));
        analyzer.analyze(analyzedFight, new DamageDoneAction("Nilus", "Murdantix", "Flame Bolt", 3848, 1, DamageTypeEnum.FIRE, Boolean.FALSE, 0, 0, 0, 0));

        assertEquals(new Integer(7306), analyzedFight.getDamageDone("Nilus"));
        assertEquals(new Double(3653), analyzedFight.getDamagePerSecond("Nilus"));
        assertEquals(new Integer(3458), analyzedFight.getDamageDone("Nilus", "Fireball"));
        assertEquals(new Integer(3848), analyzedFight.getDamageDone("Nilus", "Flame Bolt"));
    }

    @Test
    public void threeAttacks_TwoActors_registers_damage_done() {
        analyzedFight = new AnalyzedFight();
        analyzer.analyze(analyzedFight, new DamageDoneAction("Nilus", "Murdantix", "Fireball", 3458, 0, DamageTypeEnum.FIRE, Boolean.FALSE, 0, 0, 0, 0));
        analyzer.analyze(analyzedFight, new DamageDoneAction("Nilus", "Murdantix", "Flame Bolt", 3848, 1, DamageTypeEnum.FIRE, Boolean.FALSE, 0, 0, 0, 0));
        analyzer.analyze(analyzedFight, new DamageDoneAction("Xetion", "Murdantix", "Flame Bolt", 4138, 1, DamageTypeEnum.FIRE, Boolean.FALSE, 0, 0, 0, 0));

        assertEquals(new Integer(7306), analyzedFight.getDamageDone("Nilus"));
        assertEquals(new Double(3653), analyzedFight.getDamagePerSecond("Nilus"));
        assertEquals(new Integer(3458), analyzedFight.getDamageDone("Nilus", "Fireball"));
        assertEquals(new Integer(3848), analyzedFight.getDamageDone("Nilus", "Flame Bolt"));


        assertEquals(new Integer(4138), analyzedFight.getDamageDone("Xetion"));
        assertEquals(new Double(2069), analyzedFight.getDamagePerSecond("Xetion"));
        assertEquals(new Integer(4138), analyzedFight.getDamageDone("Xetion", "Flame Bolt"));
    }
}