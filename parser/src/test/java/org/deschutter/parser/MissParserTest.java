package org.deschutter.parser;


import org.deschutter.parser.actions.AttackAction;
import org.deschutter.parser.actions.DamageTypeEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MissParserTest {


    private MissParser missParser;
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
    public void setUp() throws Exception {
        missParser = new MissParser();
        parsedLine =   mock(ParsedLine.class);
        when(parsedLine.getActor()).thenReturn(actor);
        when(parsedLine.getType()).thenReturn(CombatTypeEnum.MISS);
        when(parsedLine.getTarget()).thenReturn(target);
        when(parsedLine.getSkill()).thenReturn(skill);
        when(parsedLine.getAmount()).thenReturn(amount);
        when(parsedLine.getSecondsIntoFight()).thenReturn(secondsIntoFight);
        when(parsedLine.getActorIsInRaid()).thenReturn(Boolean.TRUE);
    }

    @Test
    public void canHandle() {
        assertTrue(missParser.canHandle(parsedLine));
    }
}
