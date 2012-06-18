package org.deschutter.parser.actions;

/**
 *
 * @author berten
 */
public class RiftAction {

    private final ActionTypeEnum actionType;
    private final String actor;
    private final String skill;
    private final Integer secondsIntoFight;

    public RiftAction(ActionTypeEnum actionType, String actor, String skill, Integer secondsIntoFight) {
        this.actionType = actionType;
        this.actor = actor;
        this.skill = skill;
        this.secondsIntoFight = secondsIntoFight;
    }

    public ActionTypeEnum getActionType() {
        return actionType;
    }

    public String getActor() {
        return actor;
    }

    public String getSkill() {
        return skill;
    }

    public Integer getSecondsIntoFight() {
        return secondsIntoFight;
    }
}