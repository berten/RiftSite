package org.deschutter.parser.actions;

/**
 *
 * @author berten
 */
public class ActorTargetAction extends RiftAction {

    private final String target;
    private Boolean succesfull;


    public ActorTargetAction(ActionTypeEnum actionType, String actor, String target, String skill, Integer secondsIntoFight, Boolean succesfull) {
        super(actionType, actor, skill, secondsIntoFight);
        this.target = target;
        this.succesfull = succesfull;
    }

    public String getTarget() {
        return target;
    }


}