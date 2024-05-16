package com.hqj.easyrpc.protocol;

import lombok.Getter;

/**
 * ClassName: ProtocolMessageStatusEnum
 * Package: com.hqj.easyrpc.protocol
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/16 - 20:30
 * @Version v1.0
 */
@Getter
public enum ProtocolMessageStatusEnum {
    OK("ok", 20),
    BAD_REQUEST("badRequest", 40),
    BAD_RESPONSE("badResponse", 50);

    private final String text;

    private final int value;

    ProtocolMessageStatusEnum(String text,int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 获得枚举
     * @param value
     * @return
     */

    public static ProtocolMessageStatusEnum getEnumByValue(int value) {
        for(ProtocolMessageStatusEnum anEnum : ProtocolMessageStatusEnum.values()) {
            if(anEnum.value == value) {
                return anEnum;
            }
        }
        return null;
    }

}
