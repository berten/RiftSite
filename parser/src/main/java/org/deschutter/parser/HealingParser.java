package org.deschutter.parser;

import org.deschutter.parser.actions.HealingAction;

/**
 *
 * @author berten
 */
public class HealingParser implements IParser {

    public Boolean canHandle(ParsedLine parsedLine) {
        if (parsedLine.getType() == CombatTypeEnum.HEAL) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public HealingAction handle(ParsedLine parsedLine) {
        return new HealingAction(parsedLine.getActor(), parsedLine.getTarget(), parsedLine.getSkill(), parsedLine.getAmount(),parsedLine.getSecondsIntoFight());
    }
}