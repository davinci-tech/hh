package com.huawei.ui.commonui.base;

import android.app.Activity;
import com.huawei.ui.commonui.base.Consumable;

/* loaded from: classes9.dex */
public interface BindableContext {
    Activity getTargetActivity();

    Bindable queryBindable(Consumable.ConsumableType consumableType);
}
