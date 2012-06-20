package org.deschutter.analyzer.damage.taken;

import org.deschutter.parser.actions.HealingAction;
import org.deschutter.analyzer.AnalyzedFight;
import org.deschutter.parser.actions.DamageTakenAction;
import org.deschutter.parser.actions.DamageTypeEnum;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author berten
 */
public class DamageTakenAnalyzerTest {

    private AnalyzedFight analyzedFight;
    private DamageTakenAnalyzer analyzer;

    @Before
    public void setUp() {
        analyzer = new DamageTakenAnalyzer();
    }

    @Test
    public void one_Attack_registers_damage_taken() {
        analyzedFight = new AnalyzedFight();
        analyzer.analyze(analyzedFight, new DamageTakenAction("Akylios", "Nilus", "Fireball", 3458, 1, DamageTypeEnum.FIRE, Boolean.FALSE, 10, 20, 0, 30));
        assertEquals(new Integer(3458), analyzedFight.getDamageTaken("Nilus").getTotalDamage());
        assertEquals(new Integer(10), analyzedFight.getDamageTaken("Nilus").getTotalBlocked());
        assertEquals(new Integer(20), analyzedFight.getDamageTaken("Nilus").getTotalAbsorbed());
        assertEquals(new Integer(30), analyzedFight.getDamageTaken("Nilus").getTotalDeflected());
        assertEquals(new Integer(1729), analyzedFight.getDamageTaken("Nilus").getDamagePerSecond());
        assertEquals(new Integer(3458), analyzedFight.getDamageTaken("Nilus").getAbility("Fireball").getTotalDamage());
        assertEquals(new Integer(10), analyzedFight.getDamageTaken("Nilus").getAbility("Fireball").getTotalBlocked());
        assertEquals(new Integer(20), analyzedFight.getDamageTaken("Nilus").getAbility("Fireball").getTotalAbsorbed());
        assertEquals(new Integer(30), analyzedFight.getDamageTaken("Nilus").getAbility("Fireball").getTotalDeflected());
    }

    @Test
    public void two_Attacks_registers_damage_taken() {
        analyzedFight = new AnalyzedFight();
        analyzer.analyze(analyzedFight, new DamageTakenAction("Akylios", "Nilus", "Fireball", 3458, 0, DamageTypeEnum.FIRE, Boolean.FALSE, 0, 0, 0, 0));
        analyzer.analyze(analyzedFight, new DamageTakenAction("Akylios", "Nilus", "Flame Bolt", 3848, 1, DamageTypeEnum.FIRE, Boolean.FALSE, 0, 0, 0, 0));

        assertEquals(new Integer(7306), analyzedFight.getDamageTaken("Nilus").getTotalDamage());
        assertEquals(new Integer(3653), analyzedFight.getDamageTaken("Nilus").getDamagePerSecond());
        assertEquals(new Integer(3458), analyzedFight.getDamageTaken("Nilus").getAbility("Fireball").getTotalDamage());
        assertEquals(new Integer(3848), analyzedFight.getDamageTaken("Nilus").getAbility("Flame Bolt").getTotalDamage());
        assertEquals(new Integer(3458/2), analyzedFight.getDamageTaken("Nilus").getAbility("Fireball").getDamagePerSecond());
        assertEquals(new Integer(3848/2), analyzedFight.getDamageTaken("Nilus").getAbility("Flame Bolt").getDamagePerSecond());
    }

    @Test
    public void threeAttacks_TwoActors_registers_damage_taken() {
        analyzedFight = new AnalyzedFight();
        analyzer.analyze(analyzedFight, new DamageTakenAction("Akylios", "Nilus", "Fireball", 3458, 0, DamageTypeEnum.FIRE, Boolean.FALSE, 0, 0, 0, 0));
        analyzer.analyze(analyzedFight, new DamageTakenAction("Akylios", "Nilus", "Flame Bolt", 3848, 1, DamageTypeEnum.FIRE, Boolean.FALSE, 0, 0, 0, 0));
        analyzer.analyze(analyzedFight, new DamageTakenAction("Murdantix", "Nilus", "Flame Bolt", 4138, 1, DamageTypeEnum.FIRE, Boolean.FALSE, 0, 0, 0, 0));

        assertEquals(new Integer(11444), analyzedFight.getDamageTaken("Nilus").getTotalDamage());
        assertEquals(new Integer(5722), analyzedFight.getDamageTaken("Nilus").getDamagePerSecond());
        assertEquals(new Integer(3458), analyzedFight.getDamageTaken("Nilus").getAbility("Fireball").getTotalDamage());
        assertEquals(new Integer(7986), analyzedFight.getDamageTaken("Nilus").getAbility("Flame Bolt").getTotalDamage());

    }

    @Test
    public void healingAction_doesNot_register_damage_taken() {
        analyzedFight = new AnalyzedFight();
        analyzer.analyze(analyzedFight, new HealingAction("Akylios", "Khaet", "Bloom", 3458, 0, Boolean.FALSE, 0));

        assertEquals(0, analyzedFight.getDamageTaken().size());
    }
}