package org.deschutter.parser.actions;

/**
 *
 * @author berten
 */
public class HealingAction extends ActorTargetAction {

    private final Integer overheal;

    public HealingAction(String actor, String target, String skill, Integer amount, Integer secondsIntoFight, Boolean criticalHit, Integer overHeal) {
        super(ActionTypeEnum.HEAL, actor, target, skill, amount, secondsIntoFight, criticalHit);
        this.overheal = overHeal;
    }

    public Integer getOverheal() {
        return overheal;
    }
}