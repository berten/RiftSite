package org.deschutter.parser;

import org.deschutter.parser.actions.AttackAction;
import org.deschutter.parser.actions.DamageDoneAction;
import org.springframework.stereotype.Component;

/**
 *
 * @author berten
 */
@Component
public class DamageDoneParser implements IParser {

    public Boolean canHandle(ParsedLine parsedLine) {
        if ((parsedLine.getType() == CombatTypeEnum.NORMAL_ATTACK || parsedLine.getType() == CombatTypeEnum.CRITICAL_HIT || parsedLine.getType() == CombatTypeEnum.DOT) && parsedLine.getActorIsInRaid()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    
    public AttackAction handle(ParsedLine parsedLine) {
        return new DamageDoneAction(parsedLine.getActor(), parsedLine.getTarget(), parsedLine.getSkill(), parsedLine.getAmount(), parsedLine.getSecondsIntoFight(), parsedLine.getDamageType(), parsedLine.getType() == CombatTypeEnum.CRITICAL_HIT ? Boolean.TRUE : Boolean.FALSE,parsedLine.getBlocked(),parsedLine.getAbsorbed(),parsedLine.getOverkill());
    }
}