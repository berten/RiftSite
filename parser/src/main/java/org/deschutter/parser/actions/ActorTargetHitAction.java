package org.deschutter.parser.actions;

public class ActorTargetHitAction extends ActorTargetAction {
    private Integer amount;
    private Boolean criticalHit;

    public ActorTargetHitAction(ActionTypeEnum actionType, String actor, String target, String skill, Integer secondsIntoFight, Integer amount, Boolean criticalHit) {
        super(actionType, actor, target, skill, secondsIntoFight, Boolean.TRUE);
        this.amount = amount;
        this.criticalHit = criticalHit;
    }


    public Integer getAmount() {
        return amount;
    }

    public Boolean isCriticalHit() {
        return criticalHit;
    }
}
