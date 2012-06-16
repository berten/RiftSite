package org.deschutter.parser.actions;

/**
 *
 * @author berten
 */
public class AttackAction extends ActorTargetAction {

    public AttackAction(String actor, String target, String skill,Integer amount,Integer secondsIntoFight) {
        super(ActionTypeEnum.ATTACK, actor, target, skill,amount,secondsIntoFight);
    }
}
