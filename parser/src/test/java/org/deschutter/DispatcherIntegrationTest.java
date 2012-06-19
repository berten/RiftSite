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
public class DispatcherIntegrationTest {

    @Autowired
    private Dispatcher combatAnalyzer;

    @Test
    public void normalAttack_GetsParsed() throws Exception {
        final List<Fight> fights = combatAnalyzer.dispatch(new FileReader(System.getProperty("user.dir") + "/src/test/resources/normalAttack.txt"));

        assertEquals(1, fights.size());
        Fight fight = fights.get(0);

        assertEquals(1, fight.getActions().size());
        RiftAction action = fight.getActions().get(0);
        assertTrue(action instanceof DamageDoneAction);
        AttackAction attackAction = (AttackAction) action;

        assertEquals("Arakki", attackAction.getActor());
        assertEquals("Rusila Dreadblade", attackAction.getTarget());
        assertEquals(new Integer(154), attackAction.getAmount());
        assertEquals(DamageTypeEnum.WATER, attackAction.getDamageType());
        assertFalse(attackAction.isCriticalHit());
        assertEquals(new Integer(0), attackAction.getSecondsIntoFight());
        assertEquals("Lethal Poison", attackAction.getSkill());
        assertEquals(new Integer(0), attackAction.getBlocked());
        assertEquals(new Integer(0), attackAction.getOverkill());
        assertEquals(new Integer(0), attackAction.getAbsorbed());
    }
    
   @Test
    public void dot_GetsParsed() throws Exception {
        final List<Fight> fights = combatAnalyzer.dispatch(new FileReader(System.getProperty("user.dir") + "/src/test/resources/dotAttack.txt"));

        assertEquals(1, fights.size());
        Fight fight = fights.get(0);

        assertEquals(1, fight.getActions().size());
        RiftAction action = fight.getActions().get(0);
        assertTrue(action instanceof DamageDoneAction);
        AttackAction attackAction = (AttackAction) action;

        assertEquals("Nilus", attackAction.getActor());
        assertEquals("Dreaded Longshot", attackAction.getTarget());
        assertEquals(new Integer(232), attackAction.getAmount());
        assertEquals(DamageTypeEnum.FIRE, attackAction.getDamageType());
        assertFalse(attackAction.isCriticalHit());
        assertEquals(new Integer(0), attackAction.getSecondsIntoFight());
        assertEquals("Searing Vitality", attackAction.getSkill());
        assertEquals(new Integer(0), attackAction.getBlocked());
        assertEquals(new Integer(0), attackAction.getOverkill());
        assertEquals(new Integer(0), attackAction.getAbsorbed());

    }

    @Test
    public void critical_Attack_GetsParsed() throws Exception {
        final List<Fight> fights = combatAnalyzer.dispatch(new FileReader(System.getProperty("user.dir") + "/src/test/resources/criticalAttack.txt"));

        assertEquals(1, fights.size());
        Fight fight = fights.get(0);

        assertEquals(1, fight.getActions().size());
        RiftAction action = fight.getActions().get(0);
        assertTrue(action instanceof DamageDoneAction);
        AttackAction attackAction = (AttackAction) action;

        assertEquals("Froesie", attackAction.getActor());
        assertEquals("Rusila Dreadblade", attackAction.getTarget());
        assertEquals(new Integer(2448), attackAction.getAmount());
        assertEquals(DamageTypeEnum.EARTH, attackAction.getDamageType());
        assertTrue(attackAction.isCriticalHit());
        assertEquals(new Integer(0), attackAction.getSecondsIntoFight());
        assertEquals("Pillaging Stone", attackAction.getSkill());
        assertEquals(new Integer(0), attackAction.getBlocked());
        assertEquals(new Integer(0), attackAction.getOverkill());
        assertEquals(new Integer(0), attackAction.getAbsorbed());

    }

    @Test
    public void normalDamgeTaken_GetsParsed() throws Exception {
        final List<Fight> fights = combatAnalyzer.dispatch(new FileReader(System.getProperty("user.dir") + "/src/test/resources/normalDamageTaken.txt"));

        assertEquals(1, fights.size());
        Fight fight = fights.get(0);

        assertEquals(1, fight.getActions().size());
        RiftAction action = fight.getActions().get(0);
        assertTrue(action instanceof DamageTakenAction);
        AttackAction attackAction = (AttackAction) action;

        assertEquals("Dreaded Deathkeg", attackAction.getActor());
        assertEquals("Elfthryth", attackAction.getTarget());
        assertEquals(new Integer(3467), attackAction.getAmount());
        assertEquals(DamageTypeEnum.PHYSICAL, attackAction.getDamageType());
        assertFalse(attackAction.isCriticalHit());
        assertEquals(new Integer(0), attackAction.getSecondsIntoFight());
        assertEquals("attack", attackAction.getSkill());
        assertEquals(new Integer(8623), attackAction.getBlocked());
        assertEquals(new Integer(0), attackAction.getOverkill());
        assertEquals(new Integer(0), attackAction.getAbsorbed());
    }

    @Test
    public void criticalDamgeTaken_GetsParsed() throws Exception {
        final List<Fight> fights = combatAnalyzer.dispatch(new FileReader(System.getProperty("user.dir") + "/src/test/resources/criticalDamageTaken.txt"));

        assertEquals(1, fights.size());
        Fight fight = fights.get(0);

        assertEquals(1, fight.getActions().size());
        RiftAction action = fight.getActions().get(0);
        assertTrue(action instanceof DamageTakenAction);
        AttackAction attackAction = (AttackAction) action;

        assertEquals("Dreaded Balemouth", attackAction.getActor());
        assertEquals("Elfthryth", attackAction.getTarget());
        assertEquals(new Integer(8865), attackAction.getAmount());
        assertEquals(DamageTypeEnum.PHYSICAL, attackAction.getDamageType());
        assertTrue(attackAction.isCriticalHit());
        assertEquals(new Integer(0), attackAction.getSecondsIntoFight());
        assertEquals("attack", attackAction.getSkill());
        assertEquals(new Integer(0), attackAction.getBlocked());
        assertEquals(new Integer(0), attackAction.getOverkill());
        assertEquals(new Integer(0), attackAction.getAbsorbed());
    }

    @Test
    public void normalHeal_GetsParsed() throws Exception {
        final List<Fight> fights = combatAnalyzer.dispatch(new FileReader(System.getProperty("user.dir") + "/src/test/resources/normalHeal.txt"));

        assertEquals(1, fights.size());
        Fight fight = fights.get(0);

        assertEquals(1, fight.getActions().size());
        RiftAction action = fight.getActions().get(0);
        assertTrue(action instanceof HealingAction);
        HealingAction healingAction = (HealingAction) action;

        assertEquals("Whopper", healingAction.getActor());
        assertEquals("Polkahunter", healingAction.getTarget());
        assertEquals(new Integer(0), healingAction.getAmount());
        assertFalse(healingAction.isCriticalHit());
        assertEquals(new Integer(0), healingAction.getSecondsIntoFight());
        assertEquals("Radiant Spores", healingAction.getSkill());
        assertEquals(new Integer(2001), healingAction.getOverheal());
    }

    @Test
    public void criticalHeal_GetsParsed() throws Exception {
        final List<Fight> fights = combatAnalyzer.dispatch(new FileReader(System.getProperty("user.dir") + "/src/test/resources/criticalHeal.txt"));

        assertEquals(1, fights.size());
        Fight fight = fights.get(0);

        assertEquals(1, fight.getActions().size());
        RiftAction action = fight.getActions().get(0);
        assertTrue(action instanceof HealingAction);
        HealingAction healingAction = (HealingAction) action;

        assertEquals("Mimii", healingAction.getActor());
        assertEquals("Arakki", healingAction.getTarget());
        assertEquals(new Integer(2836), healingAction.getAmount());
        assertTrue(healingAction.isCriticalHit());
        assertEquals(new Integer(0), healingAction.getSecondsIntoFight());
        assertEquals("Healing Invocation", healingAction.getSkill());
        assertEquals(new Integer(3762), healingAction.getOverheal());
    }
}