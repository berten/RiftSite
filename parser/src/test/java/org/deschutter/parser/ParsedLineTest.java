package org.deschutter.parser;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author berten
 */
public class ParsedLineTest {

    private ParsedLine parsedLine;

    @Before
    public void setUp() {
        parsedLine = new ParsedLine(new Date(),"20:23:29: ( 3 , T=P#R=R#353391833781567547 , T=N#R=O#9223372040713575731 , T=X#R=X#0 , T=X#R=X#0 , Mimii , Rusila Dreadblade , 991 , 1843227230 , Life's Vengeance ) Mimii's Life's Vengeance hits Rusila Dreadblade for 991 Life damage. ");
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
        assertEquals(new Integer(991),parsedLine.getAmount());
    }
}