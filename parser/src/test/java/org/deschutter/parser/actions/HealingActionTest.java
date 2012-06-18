package org.deschutter.parser.actions;

import org.junit.Test;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 *
 * @author berten
 */
public class HealingActionTest {

    private HealingAction healingAction = new HealingAction("Nilus", "Khaet","skill",1,2,Boolean.FALSE,new Integer(12));

    @Test
    public void correctType() {
        assertSame(ActionTypeEnum.HEAL, healingAction.getActionType());
    }

    @Test
    public void hasActor() {
        assertEquals("Nilus", healingAction.getActor());
    }

    @Test
    public void hasTarget() {
        assertEquals("Khaet", healingAction.getTarget());
    }
    
    @Test
    public void hasSkill() {
        assertEquals("skill",healingAction.getSkill());
    }
    
    @Test
    public void hasAmount() {
        assertEquals(new Integer(1),healingAction.getAmount());
    }
    
    @Test
    public void hasSecondsIntoFight() {
        assertEquals(new Integer(2),healingAction.getSecondsIntoFight());
    }
    
    @Test
    public void hasCriticalHit() {
        assertFalse(healingAction.isCriticalHit());
    }
    
    @Test
    public void hasOverheal() {
        assertEquals(new Integer(12),healingAction.getOverheal());
    }
}