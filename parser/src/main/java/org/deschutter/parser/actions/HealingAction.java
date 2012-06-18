package org.deschutter.parser.actions;

/**
 *
 * @author berten
 */
public class HealingAction extends ActorTargetAction {

    public HealingAction(String actor, String target, String skill,Integer amount,Integer secondsIntoFight) {
        super(ActionTypeEnum.HEAL, actor, target, skill,amount,secondsIntoFight);
    }
}
