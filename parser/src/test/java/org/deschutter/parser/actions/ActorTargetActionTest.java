package org.deschutter.parser.actions;

import org.junit.Test;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author berten
 */
public class ActorTargetActionTest {

    private ActorTargetAction actorTargetAction = new ActorTargetAction(ActionTypeEnum.ATTACK, "actor", "target","skill",1,2);

    @Test
    public void hasType() {
        assertSame(ActionTypeEnum.ATTACK, actorTargetAction.getActionType());
    }

    @Test
    public void hasActor() {
        assertEquals("actor", actorTargetAction.getActor());
    }

    @Test
    public void hasTarget() {
        assertEquals("target", actorTargetAction.getTarget());
    }
    
    @Test
    public void hasSkill() {
        assertEquals("skill",actorTargetAction.getSkill());
    }
    
    @Test
    public void hasAmount() {
        assertEquals(new Integer(1),actorTargetAction.getAmount());
    }
    
    @Test
    public void hasSecondsIntoFight() {
        assertEquals(new Integer(2),actorTargetAction.getSecondsIntoFight());
    }
    
    
}