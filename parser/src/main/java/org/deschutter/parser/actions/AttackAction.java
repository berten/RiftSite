package org.deschutter.parser.actions;

/**
 *
 * @author berten
 */
public class AttackAction extends ActorTargetAction {

    private final DamageTypeEnum damageType;
    private final Integer blocked;
    private final Integer absorbed;
    private final Integer overkill;
    private final Integer deflected;

    public AttackAction(String actor, String target, String skill, Integer amount, Integer secondsIntoFight, DamageTypeEnum damageType,Boolean criticalHit,Integer blocked, Integer absorbed, Integer overkill,Integer deflected) {
        super(ActionTypeEnum.ATTACK, actor, target, skill, amount, secondsIntoFight,criticalHit);
        this.damageType = damageType;
        this.blocked = blocked;
        this.absorbed = absorbed;
        this.overkill = overkill;
        this.deflected = deflected;
    }

    public DamageTypeEnum getDamageType() {
        return damageType;
    }

    public Integer getAbsorbed() {
        return absorbed;
    }

    public Integer getBlocked() {
        return blocked;
    }

    public Integer getOverkill() {
        return overkill;
    }

    public Integer getDeflected() {
        return deflected;
    }
}