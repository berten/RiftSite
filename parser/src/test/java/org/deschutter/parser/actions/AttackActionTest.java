package org.deschutter.parser.actions;

import org.junit.Test;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author berten
 */
public class AttackActionTest {

    private AttackAction attackAction = new AttackAction("Nilus", "Murdantix", "skill", 1, 2, DamageTypeEnum.LIFE, Boolean.TRUE, new Integer(12), new Integer(13), new Integer(14));

    ;

    @Test
    public void correctType() {
        assertSame(ActionTypeEnum.ATTACK, attackAction.getActionType());
    }

    @Test
    public void hasActor() {
        assertEquals("Nilus", attackAction.getActor());
    }

    @Test
    public void hasTarget() {
        assertEquals("Murdantix", attackAction.getTarget());
    }

    @Test
    public void hasSkill() {
        assertEquals("skill", attackAction.getSkill());
    }

    @Test
    public void hasAmount() {
        assertEquals(new Integer(1), attackAction.getAmount());
    }

    @Test
    public void hasSecondsIntoFight() {
        assertEquals(new Integer(2), attackAction.getSecondsIntoFight());
    }

    @Test
    public void hasDamageType() {
        assertSame(DamageTypeEnum.LIFE, attackAction.getDamageType());
    }

    @Test
    public void hasCriticalHit() {
        assertTrue(attackAction.isCriticalHit());
    }

    @Test
    public void hasBlocked() {
        assertEquals(new Integer(12), attackAction.getBlocked());
    }

    @Test
    public void hasAbsorbed() {
        assertEquals(new Integer(13), attackAction.getAbsorbed());
    }

    @Test
    public void hasOverkill() {
        assertEquals(new Integer(14), attackAction.getOverkill());
    }
}