package org.deschutter;

import java.io.FileReader;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.deschutter.parser.actions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 *
 * @author berten
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CombatAnalyzerIntegrationTest {

    @Autowired
    private CombatAnalyzer combatAnalyzer;

    @Test
    public void normalAttack_GetsParsed() throws Exception {
        final List<Fight> fights = combatAnalyzer.analyze(new FileReader(System.getProperty("user.dir") + "/src/test/resources/normalAttack.txt"));

        assertEquals(1, fights.size());
        Fight fight = fights.get(0);

        assertEquals(1, fight.getActions().size());
        RiftAction action = fight.getActions().get(0);
        assertTrue(action instanceof AttackAction);
        AttackAction attackAction = (AttackAction) action;

        assertEquals("Arakki", attackAction.getActor());
        assertEquals("Rusila Dreadblade", attackAction.getTarget());
        assertEquals(new Integer(154), attackAction.getAmount());
        assertEquals(DamageTypeEnum.WATER, attackAction.getDamageType());
        assertFalse(attackAction.isCriticalHit());
        assertEquals(new Integer(0), attackAction.getSecondsIntoFight());
        assertEquals("Lethal Poison", attackAction.getSkill());

    }

    @Test
    public void normalDamgeTaken_GetsParsed() throws Exception {
        final List<Fight> fights = combatAnalyzer.analyze(new FileReader(System.getProperty("user.dir") + "/src/test/resources/normalDamageTaken.txt"));

        assertEquals(1, fights.size());
        Fight fight = fights.get(0);

        assertEquals(1, fight.getActions().size());
        RiftAction action = fight.getActions().get(0);
        assertTrue(action instanceof AttackAction);
        AttackAction attackAction = (AttackAction) action;

        assertEquals("Dreaded Deathkeg", attackAction.getActor());
        assertEquals("Elfthryth", attackAction.getTarget());
        assertEquals(new Integer(3467), attackAction.getAmount());
        assertEquals(DamageTypeEnum.PHYSICAL, attackAction.getDamageType());
        assertFalse(attackAction.isCriticalHit());
        assertEquals(new Integer(0), attackAction.getSecondsIntoFight());
        assertEquals("attack", attackAction.getSkill());
        assertEquals(new Integer(8623), attackAction.getBlocked());
    }
}