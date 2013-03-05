package org.deschutter.parser.actions;


public class MissAction extends ActorTargetAction {

    public MissAction(ActionTypeEnum actionType, String actor, String target, String skill, Integer secondsIntoFight) {
        super(actionType, actor, target, skill, secondsIntoFight, Boolean.FALSE);
    }
}
