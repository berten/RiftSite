package org.deschutter.parser;

import org.deschutter.parser.actions.HealingAction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author berten
 */
public class HealingParserTest {

    HealingParser parser;
    private String actor = "Nilus";
    private Integer amount = new Integer(1);
    private Integer secondsIntoFight = new Integer(5);
    private String target = "Khaet";
    private String skill = "skill";
    private ParsedLine parsedLine;
    private HealingAction riftAction;

    @Before
    public void setUp() {
        parsedLine = mock(ParsedLine.class);
        when(parsedLine.getActor()).thenReturn(actor);
        when(parsedLine.getType()).thenReturn(CombatTypeEnum.HEAL);
        when(parsedLine.getTarget()).thenReturn(target);
        when(parsedLine.getSkill()).thenReturn(skill);
        when(parsedLine.getAmount()).thenReturn(amount);
        when(parsedLine.getSecondsIntoFight()).thenReturn(secondsIntoFight);
        parser = new HealingParser();

        riftAction = parser.handle(parsedLine);
    }

    @Test
    public void canHandle() {
        assertTrue(parser.canHandle(parsedLine));
    }

    @Test
    public void returnsCorrectType() {
        assertTrue(parser.handle(parsedLine) instanceof HealingAction);
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
    public void typeNHeal_noCriticalHit() {
        assertFalse(riftAction.isCriticalHit());
    }

    @Test
    public void typeCriticalHeal_criticalHit() {
        when(parsedLine.getType()).thenReturn(CombatTypeEnum.CRITICAL_HEAL);
         assertTrue(parser.canHandle(parsedLine));
        riftAction = parser.handle(parsedLine);
        assertTrue(riftAction.isCriticalHit());
    }
}