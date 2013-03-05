package org.deschutter.parser;

import org.deschutter.parser.actions.AttackAction;
import org.deschutter.parser.actions.DamageDoneAction;
import org.deschutter.parser.actions.DamageTypeEnum;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author berten
 */
public class DamageDoneParserTest {

    DamageDoneParser parser;
    private String actor = "Nilus";
    private Integer amount = new Integer(1);
    private Integer secondsIntoFight = new Integer(5);
    private String target = "Murdantix";
    private String skill = "skill";
    private ParsedLine parsedLine;
    private AttackAction riftAction;
    private Integer absorbed = new Integer(12);
    private Integer blocked = new Integer(13);
    private Integer overkill = new Integer(14);
    private Integer deflected = new Integer(15);

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
        when(parsedLine.getAbsorbed()).thenReturn(absorbed);
        when(parsedLine.getOverkill()).thenReturn(overkill);
        when(parsedLine.getBlocked()).thenReturn(blocked);
        when(parsedLine.getDeflected()).thenReturn(deflected);

        when(parsedLine.getActorIsInRaid()).thenReturn(Boolean.TRUE);
        parser = new DamageDoneParser();

        riftAction = parser.handle(parsedLine);
    }

    @Test
    public void canHandle() {
        assertTrue(parser.canHandle(parsedLine));
    }

    @Test
    public void actorNotInRaid_cantHandle() {
        when(parsedLine.getActorIsInRaid()).thenReturn(Boolean.FALSE);
        assertFalse(parser.canHandle(parsedLine));
    }

    @Test
    public void returnsCorrectType() {
        assertTrue(parser.handle(parsedLine) instanceof DamageDoneAction);
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
        assertSame(DamageTypeEnum.LIFE, riftAction.getDamageType());
    }

    @Test
    public void typeNormalAttack_noCriticalHit() {
        assertFalse(riftAction.isCriticalHit());
    }

    @Test
    public void typeCriticalHit_criticalHit() {
        when(parsedLine.getType()).thenReturn(CombatTypeEnum.CRITICAL_HIT);
        riftAction = parser.handle(parsedLine);
        assertTrue(riftAction.isCriticalHit());
    }

    @Test
    public void typeCriticalHit_canHandle() {
        when(parsedLine.getType()).thenReturn(CombatTypeEnum.CRITICAL_HIT);
        assertTrue(parser.canHandle(parsedLine));
    }

    @Test
    public void dot_noCriticalHit() {
        when(parsedLine.getType()).thenReturn(CombatTypeEnum.DOT);
        assertTrue(parser.canHandle(parsedLine));
        riftAction = parser.handle(parsedLine);
        assertFalse(riftAction.isCriticalHit());
    }

    @Test
    public void dot_canHandle() {
        when(parsedLine.getType()).thenReturn(CombatTypeEnum.DOT);
        assertTrue(parser.canHandle(parsedLine));
    }

    @Test
    public void returnsAbsorbed() {
        assertEquals(absorbed, riftAction.getAbsorbed());
    }

    @Test
    public void returnsBlocked() {
        assertEquals(blocked, riftAction.getBlocked());
    }

    @Test
    public void returnsOverkill() {
        assertEquals(overkill, riftAction.getOverkill());
    }

    @Test
    public void returnsDeflected() {
        assertEquals(deflected, riftAction.getDeflected());
    }
}