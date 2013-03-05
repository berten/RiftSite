package org.deschutter.parser;

import java.util.Calendar;
import java.util.Date;
import org.deschutter.parser.actions.DamageTypeEnum;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 *
 * @author berten
 */
public class ParsedLineTest {

    private Date dateTime;
    private ParsedLine parsedLine;

    @Before
    public void setUp() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR_OF_DAY, 20);
        cal.set(Calendar.MINUTE, 23);
        cal.set(Calendar.SECOND, 25);
        dateTime = cal.getTime();

        parsedLine = new ParsedLine(dateTime, "20:23:29: ( 3 , T=P#R=R#353391833781567547 , T=N#R=O#9223372040713575731 , T=X#R=X#0 , T=X#R=X#0 , Mimii , Rusila Dreadblade , 991 , 1843227230 , Life's Vengeance ) Mimii's Life's Vengeance hits Rusila Dreadblade for 991 Life damage. (13 blocked 39 absorbed 14 overkill 37 overheal 34 deflected)");
    }

    @Test
    public void maps_Type() {
        assertSame(CombatTypeEnum.NORMAL_ATTACK, parsedLine.getType());
    }

    @Test
    public void maps_actor() {
        assertEquals("Mimii", parsedLine.getActor());
    }

    @Test
    public void maps_skill() {
        assertEquals("Life's Vengeance", parsedLine.getSkill());
    }

    @Test
    public void maps_Amount() {
        assertEquals(new Integer(991), parsedLine.getAmount());
    }

    @Test
    public void maps_damageType() {
        assertSame(DamageTypeEnum.LIFE, parsedLine.getDamageType());
    }

    @Test
    public void maps_blocked() {
        assertEquals(new Integer(13), parsedLine.getBlocked());
    }

    @Test
    public void maps_absorbed() {
        assertEquals(new Integer(39), parsedLine.getAbsorbed());
    }

    @Test
    public void maps_overkill() {
        assertEquals(new Integer(14), parsedLine.getOverkill());
    }

    @Test
    public void maps_overheal() {
        assertEquals(new Integer(37), parsedLine.getOverheal());
    }

    @Test
    public void maps_deflected() {
        assertEquals(new Integer(34), parsedLine.getDeflected());
    }

    @Test
    public void maps_actorIsInRaid() {
        assertTrue(parsedLine.getActorIsInRaid());
    }

    @Test
    public void maps_SecondsInFight() {
        assertEquals(new Integer(4), parsedLine.getSecondsIntoFight());
    }

    @Test
    public void maps_SecondsInFight_MidnightPassed() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 55);
        parsedLine = new ParsedLine(cal.getTime(), "00:00:04: ( 3 , T=P#R=R#353391833781567547 , T=N#R=O#9223372040713575731 , T=X#R=X#0 , T=X#R=X#0 , Mimii , Rusila Dreadblade , 991 , 1843227230 , Life's Vengeance ) Mimii's Life's Vengeance hits Rusila Dreadblade for 991 Life damage. (13 blocked 39 absorbed 14 overkill 37 overheal 34 deflected)");
        assertEquals(new Integer(9), parsedLine.getSecondsIntoFight());
    }

    @Test
    public void maps_targetIsInRaid() {
        assertFalse("Expected false, was " + parsedLine.getTargetIsInRaid(), parsedLine.getTargetIsInRaid());
    }

    @Test
    public void maps_actorIsPlayer_false() {
        parsedLine = new ParsedLine(new Date(), "20:23:29: ( 3 , T=N#R=O#353391833781567547 , T=P#R=R#9223372040713575731 , T=X#R=X#0 , T=X#R=X#0 , Mimii , Rusila Dreadblade , 991 , 1843227230 , Life's Vengeance ) Mimii's Life's Vengeance hits Rusila Dreadblade for 991 Life damage. (13 blocked 39 absorbed 14 overkill 37 overheal)");

        assertFalse(parsedLine.getActorIsInRaid());
        assertTrue(parsedLine.getTargetIsInRaid());
    }

    @Test
    public void maps_actorIsPlayer_targetNotInRaid() {
        parsedLine = new ParsedLine(new Date(), "20:23:29: ( 3 , T=N#R=O#353391833781567547 , T=P#R=O#9223372040713575731 , T=X#R=X#0 , T=X#R=X#0 , Mimii , Rusila Dreadblade , 991 , 1843227230 , Life's Vengeance ) Mimii's Life's Vengeance hits Rusila Dreadblade for 991 Life damage. (13 blocked 39 absorbed 14 overkill 37 overheal)");

        assertFalse(parsedLine.getActorIsInRaid());
        assertFalse(parsedLine.getTargetIsInRaid());
    }

    @Test
    public void maps_actorIsInRaid_false() {
        parsedLine = new ParsedLine(new Date(), "20:23:29: ( 3 , T=P#R=O#353391833781567547 , T=N#R=O#9223372040713575731 , T=X#R=X#0 , T=X#R=X#0 , Mimii , Rusila Dreadblade , 991 , 1843227230 , Life's Vengeance ) Mimii's Life's Vengeance hits Rusila Dreadblade for 991 Life damage. (13 blocked 39 absorbed 14 overkill 37 overheal)");
        assertFalse(parsedLine.getActorIsInRaid());
    }

    @Test
    public void maps_autoAttack_ranged() {
        parsedLine = new ParsedLine(new Date(),"20:23:29: ( 10 , T=N#R=O#9223372036961659671 , T=N#R=O#9223372036961659671 , T=X#R=X#0 , T=X#R=X#0 , Banner of Zeal , Banner of Zeal , 0 , 1659400144 , Autoattack (Ranged) ) Banner of Zeal's Autoattack (Ranged) misses Banner of Zeal.");
        assertFalse(parsedLine.getActorIsInRaid());
    }
}