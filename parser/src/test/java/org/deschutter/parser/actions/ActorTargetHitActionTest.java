package org.deschutter.parser.actions;


import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertSame;
import static org.junit.Assert.assertEquals;

public class ActorTargetHitActionTest {

    private int secondsIntoFight;
    private int amount;
    private ActorTargetHitAction action;

    @Before
    public void setUp() throws Exception {
        secondsIntoFight = 1;
        amount = 2;
        action = new ActorTargetHitAction(ActionTypeEnum.ATTACK,"actor","target","skill", secondsIntoFight, amount,Boolean.FALSE);
    }

    @Test
    public void secondsIntoFight() {
           assertSame(secondsIntoFight, action.getSecondsIntoFight());
    }

    @Test
    public void amount() {
        assertSame(amount,action.getAmount());
    }

    @Test
    public void actor() {
        assertEquals("actor",action.getActor());
    }

    @Test
    public void target() {
        assertEquals("target",action.getTarget());
    }

    @Test
    public void actionType() {
        assertSame(ActionTypeEnum.ATTACK,action.getActionType());
    }

    @Test
    public void critHit() {
        assertFalse(action.isCriticalHit());
    }

    @Test
    public void skill() {
        assertEquals("skill",action.getSkill());
    }
}
