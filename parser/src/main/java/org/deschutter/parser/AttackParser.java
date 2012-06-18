package org.deschutter.parser;

import org.deschutter.parser.actions.AttackAction;

/**
 *
 * @author berten
 */
public class AttackParser implements IParser {

    public Boolean canHandle(ParsedLine parsedLine) {
        if (parsedLine.getType() == CombatTypeEnum.NORMAL_ATTACK) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public AttackAction handle(ParsedLine parsedLine) {
        return new AttackAction(parsedLine.getActor(), parsedLine.getTarget(), parsedLine.getSkill(), parsedLine.getAmount(),parsedLine.getSecondsIntoFight(),parsedLine.getDamageType());
    }
}