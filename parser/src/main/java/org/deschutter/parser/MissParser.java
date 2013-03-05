package org.deschutter.parser;


import org.deschutter.parser.actions.ActionTypeEnum;
import org.deschutter.parser.actions.MissAction;
import org.deschutter.parser.actions.RiftAction;

public class MissParser implements IParser {
    @Override
    public Boolean canHandle(ParsedLine parsedLine) {
        return parsedLine.getType() == CombatTypeEnum.MISS;
    }

    @Override
    public RiftAction handle(ParsedLine parsedLine) {
        return new MissAction(ActionTypeEnum.ATTACK,parsedLine.getActor(),parsedLine.getTarget(),parsedLine.getSkill(),parsedLine.getSecondsIntoFight());
    }
}
