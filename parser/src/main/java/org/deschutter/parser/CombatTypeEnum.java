package org.deschutter.parser;

/**
 *
 * @author berten
 */
public enum CombatTypeEnum {

// 1 casting_start
// 2 interrupted
// 3 damage_direct
// 4 damage_periodic
// 5 heal, periodic and direct
// 6 buff_gain
// 7 buff_fade
// 8 effect_afflicted
// 9 effect_dissipates
// 10 miss
// 11 target_slain
// 12 died
// 14 damage_self? unbound? unsummoned? not sure about this one.
// 15 dodge
// 16 parry
// 18 exp_granted
// 19 resist
// 23 critical hit
// 26 immune
// 28 critical heal
    CASTING_START(1), INTERRUPTED(2), NORMAL_ATTACK(3), 
    DOT(4), HEAL(5), BUFF_GAIN(6), BUFF_LOSS(7), 
    MISS(10), TARGET_DEATH(10), DEATH(12), DODGE(15), PARRY(16),
    RESIST(19),CRITICAL_HIT(23),IMMUNE(26),CRITICAL_HEAL(28);
    Integer parserValue;

    private CombatTypeEnum(Integer parserValue) {
        this.parserValue = parserValue;
    }

    public static CombatTypeEnum byValue(Integer parserValue) {
        for (CombatTypeEnum combatTypeEnum : CombatTypeEnum.values()) {
            if (combatTypeEnum.getParserValue().equals(parserValue)) {
                return combatTypeEnum;
            }
        }
        return null;
    }

    private Integer getParserValue() {
        return parserValue;
    }
}
