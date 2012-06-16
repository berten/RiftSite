package org.deschutter.parser.actions;


import org.junit.Test;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author berten
 */
public class RiftActionTest {

    private RiftAction riftAction = new RiftAction(ActionTypeEnum.ATTACK, "actor","skill",2);

    @Test
    public void hasType() {
        assertSame(ActionTypeEnum.ATTACK, riftAction.getActionType());
    }

    @Test
    public void hasActor() {
        assertSame("actor", riftAction.getActor());
    }
    
    @Test
    public void hasSkill() {
        assertSame("skill",riftAction.getSkill());
    }
    
    @Test
    public void hasSecondsIntoFight() {
        assertEquals(new Integer(2),riftAction.getSecondsIntoFight());
    }
}