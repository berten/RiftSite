package org.deschutter.parser;

import org.deschutter.parser.actions.AttackAction;
import org.deschutter.parser.actions.DamageTypeEnum;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author berten
 */
public class AttackParserTest {

    AttackParser parser;
    private String actor = "Nilus";
    private Integer amount = new Integer(1);
    private Integer secondsIntoFight = new Integer(5);
    private String target = "Murdantix";
    private String skill = "skill";
    private ParsedLine parsedLine;
    private AttackAction riftAction;

    @Before
    public void setUp() {
        parsedLine = mock(ParsedLine.class);
        when(parsedLine.getActor()).thenReturn(actor);
        when(parsedLine.getType()).thenReturn(CombatTypeEnum.NORMAL_ATTACK);
        when(parsedLine.getTarget()).thenReturn(target);
        when(parsedLine.getSkill()).thenReturn(skill);
        when(parsedLine.getAmount()).thenReturn(amount);
        when(parsedLine.getSecondsIntoFight()).thenReturn(secondsIntoFight);
        when(parsedLine.getDamageType()).thenReturn(DamageTypeEnum.LIFE);
        parser = new AttackParser();

        riftAction = parser.handle(parsedLine);
    }

    @Test
    public void canHandle() {
        assertTrue(parser.canHandle(parsedLine));
    }

    @Test
    public void returnsCorrectType() {
        assertTrue(parser.handle(parsedLine) instanceof AttackAction);
    }

    @Test
    public void returnsCorrectActor() {
        assertEquals(actor, riftAction.getActor());
    }

    @Test
    public void returnsCorrectTarget() {
        assertEquals(target, riftAction.getTarget());
    }

    @Test
    public void returnsCorrectSkill() {
        assertEquals(skill, riftAction.getSkill());
    }

    @Test
    public void returnsCorrectAmount() {
        assertEquals(amount, riftAction.getAmount());
    }

    @Test
    public void returnsCorrectTime() {
        assertEquals(secondsIntoFight, riftAction.getSecondsIntoFight());
    }
    
    @Test
    public void returnsCorrectDamageType() {
        assertSame(DamageTypeEnum.LIFE,riftAction.getDamageType());
    }
}