package com.huawei.ui.commonui.base;

import com.huawei.ui.commonui.base.viewmodel.ObservableViewModel;
import java.util.Collection;

/* loaded from: classes9.dex */
public interface ViewModelDecorator<T extends ObservableViewModel> {
    Collection<T> decorate(Consumable consumable);
}
