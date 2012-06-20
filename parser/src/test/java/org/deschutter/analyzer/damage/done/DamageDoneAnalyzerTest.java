package org.deschutter.analyzer.damage.done;

import org.deschutter.parser.actions.HealingAction;
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
        assertEquals(new Integer(3458), analyzedFight.getDamageDone("Nilus").getTotalDamage());
        assertEquals(new Integer(1729), analyzedFight.getDamageDone("Nilus").getDamagePerSecond());
        assertEquals(new Integer(3458), analyzedFight.getDamageDone("Nilus").getAbility("Fireball").getTotalDamage());
        assertEquals(1, analyzedFight.getDamageDone("Nilus").getAbility("Fireball").getHits().size());
        assertEquals(new Integer(3458), analyzedFight.getDamageDone("Nilus").getAbility("Fireball").getBiggestHit());
    }

    @Test
    public void two_Attacks_registers_damage_done() {
        analyzedFight = new AnalyzedFight();
        analyzer.analyze(analyzedFight, new DamageDoneAction("Nilus", "Murdantix", "Fireball", 3458, 0, DamageTypeEnum.FIRE, Boolean.FALSE, 0, 0, 0, 0));
        analyzer.analyze(analyzedFight, new DamageDoneAction("Nilus", "Murdantix", "Flame Bolt", 3848, 1, DamageTypeEnum.FIRE, Boolean.FALSE, 0, 0, 0, 0));

        assertEquals(new Integer(7306), analyzedFight.getDamageDone("Nilus").getTotalDamage());
        assertEquals(new Integer(3653), analyzedFight.getDamageDone("Nilus").getDamagePerSecond());
        assertEquals(new Integer(3458), analyzedFight.getDamageDone("Nilus").getAbility("Fireball").getTotalDamage());
        assertEquals(new Integer(3848), analyzedFight.getDamageDone("Nilus").getAbility("Flame Bolt").getTotalDamage());
    }

    @Test
    public void threeAttacks_TwoActors_registers_damage_done() {
        analyzedFight = new AnalyzedFight();
        analyzer.analyze(analyzedFight, new DamageDoneAction("Nilus", "Murdantix", "Fireball", 3458, 0, DamageTypeEnum.FIRE, Boolean.FALSE, 0, 0, 0, 0));
        analyzer.analyze(analyzedFight, new DamageDoneAction("Nilus", "Murdantix", "Flame Bolt", 3848, 1, DamageTypeEnum.FIRE, Boolean.FALSE, 0, 0, 0, 0));
        analyzer.analyze(analyzedFight, new DamageDoneAction("Xetion", "Murdantix", "Flame Bolt", 4138, 1, DamageTypeEnum.FIRE, Boolean.FALSE, 0, 0, 0, 0));

        assertEquals(new Integer(7306), analyzedFight.getDamageDone("Nilus").getTotalDamage());
        assertEquals(new Integer(3653), analyzedFight.getDamageDone("Nilus").getDamagePerSecond());
        assertEquals(new Integer(3458), analyzedFight.getDamageDone("Nilus").getAbility("Fireball").getTotalDamage());
        assertEquals(new Integer(3848), analyzedFight.getDamageDone("Nilus").getAbility("Flame Bolt").getTotalDamage());
        assertEquals(new Integer(3848), analyzedFight.getDamageDone("Nilus").getAbility("Flame Bolt").getTotalDamage());


        assertEquals(new Integer(4138), analyzedFight.getDamageDone("Xetion").getTotalDamage());
        assertEquals(new Integer(2069), analyzedFight.getDamageDone("Xetion").getDamagePerSecond());
        assertEquals(new Integer(4138), analyzedFight.getDamageDone("Xetion").getAbility("Flame Bolt").getTotalDamage());
    }

    @Test
    public void healingAction_doesNot_register_damage_done() {
        analyzedFight = new AnalyzedFight();
        analyzer.analyze(analyzedFight, new HealingAction("Nilus", "Khaet", "Bloom", 3458, 0, Boolean.FALSE, 0));

        assertEquals(0, analyzedFight.getDamageDone().size());
    }
}