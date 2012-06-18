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

    private AttackAction attackAction = new AttackAction("Nilus", "Murdantix","skill",1,2,DamageTypeEnum.LIFE,Boolean.TRUE);

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
        assertEquals("skill",attackAction.getSkill());
    }
    
    @Test
    public void hasAmount() {
        assertEquals(new Integer(1),attackAction.getAmount());
    }
    
    @Test
    public void hasSecondsIntoFight() {
        assertEquals(new Integer(2),attackAction.getSecondsIntoFight());
    }
    @Test
    public void hasDamageType() {
        assertSame(DamageTypeEnum.LIFE,attackAction.getDamageType());
    }
    
     @Test
    public void hasCriticalHit() {
        assertTrue(attackAction.isCriticalHit());
    }
}