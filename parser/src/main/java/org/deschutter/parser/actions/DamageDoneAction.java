
package org.deschutter.parser.actions;

/**
 *
 * @author berten
 */
public class DamageDoneAction extends AttackAction {

    public DamageDoneAction(String actor, String target, String skill, Integer amount, Integer secondsIntoFight, DamageTypeEnum damageType, Boolean criticalHit, Integer blocked, Integer absorbed, Integer overkill,Integer deflected) {
        super(actor, target, skill, amount, secondsIntoFight, damageType, criticalHit, blocked, absorbed, overkill,deflected);
    }
}
