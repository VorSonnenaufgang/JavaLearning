package ink.vor.java.juc.countbarrierphore;

import lombok.Getter;

/**
 * @author muquanrui
 * @date 2022/10/1 14:42
 */

public enum CountryEnum {
    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "赵"), FIVE(5, "魏"), SIX(6, "韩");
    @Getter
    private Integer retCode;
    @Getter
    private String retMessage;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum forEachContryEnum(int index) {
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum e : values) {
            if (index == e.retCode) {
                return e;
            }
        }
        return null;
    }
}
