package org.deschutter.parser;

import org.deschutter.parser.actions.HealingAction;
import org.springframework.stereotype.Component;

/**
 *
 * @author berten
 */
@Component
public class HealingParser implements IParser {

    public Boolean canHandle(ParsedLine parsedLine) {
        if (parsedLine.getType() == CombatTypeEnum.HEAL || parsedLine.getType() == CombatTypeEnum.CRITICAL_HEAL) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public HealingAction handle(ParsedLine parsedLine) {
        return new HealingAction(parsedLine.getActor(), parsedLine.getTarget(), parsedLine.getSkill(), parsedLine.getAmount(),parsedLine.getSecondsIntoFight(),parsedLine.getType() == CombatTypeEnum.HEAL ? Boolean.FALSE : Boolean.TRUE);
    }
}