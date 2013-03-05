package org.deschutter.parser.actions;

/**
 *
 * @author berten
 */
public class HealingAction extends ActorTargetHitAction {

    private final Integer overheal;

    public HealingAction(String actor, String target, String skill, Integer amount, Integer secondsIntoFight, Boolean criticalHit, Integer overHeal) {
        super(ActionTypeEnum.HEAL, actor, target, skill,  secondsIntoFight,amount, criticalHit);
        this.overheal = overHeal;
    }

    public Integer getOverheal() {
        return overheal;
    }
}