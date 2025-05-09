package com.huawei.ui.commonui.base.viewmodel;

import com.huawei.ui.commonui.base.Consumable;
import java.io.Serializable;

/* loaded from: classes9.dex */
public class ObservableFild<T> extends ObservableViewModel implements Serializable {
    static final long serialVersionUID = 1;

    @Override // com.huawei.ui.commonui.base.viewmodel.ObservableViewModel
    protected void onDetach() {
    }

    @Override // com.huawei.ui.commonui.base.viewmodel.ObservableViewModel
    public Consumable.ConsumableType getType() {
        return Consumable.ConsumableType.UNKNOW_CONSUMABLE_TYPE;
    }
}
