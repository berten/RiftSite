package org.deschutter.parser.actions;

/**
 *
 * @author berten
 */
public class AttackAction extends ActorTargetAction {

    private final DamageTypeEnum damageType;

    public AttackAction(String actor, String target, String skill, Integer amount, Integer secondsIntoFight, DamageTypeEnum damageType,Boolean criticalHit) {
        super(ActionTypeEnum.ATTACK, actor, target, skill, amount, secondsIntoFight,criticalHit);
        this.damageType = damageType;
    }

    public DamageTypeEnum getDamageType() {
        return damageType;
    }
}