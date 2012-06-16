package org.deschutter.parser.actions;

/**
 *
 * @author berten
 */
public class ActorTargetAction extends RiftAction {

    private final String target;
    private final Integer amount;

    public ActorTargetAction(ActionTypeEnum actionType, String actor, String target,String skill,Integer amount,Integer secondsIntoFight) {
        super(actionType, actor,skill,secondsIntoFight);
        this.target = target;
        this.amount = amount;
    }

    public String getTarget() {
        return target;
    }

    public Integer getAmount() {
        return amount;
    }
    
    
}