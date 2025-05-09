package com.huawei.ui.commonui.base;

/* loaded from: classes9.dex */
public interface Consumable<T> {

    public interface ConsumableType {
        public static final ConsumableType UNKNOW_CONSUMABLE_TYPE = new ConsumableType() { // from class: com.huawei.ui.commonui.base.Consumable.ConsumableType.5
        };
    }

    T getData();

    ConsumableType getType();
}
