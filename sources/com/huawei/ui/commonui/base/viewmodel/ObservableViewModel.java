package com.huawei.ui.commonui.base.viewmodel;

import androidx.lifecycle.ViewModel;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.Consumable;
import com.huawei.ui.commonui.base.Observable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public abstract class ObservableViewModel extends ViewModel implements Observable {
    private static final String TAG = "ObservableViewModel";
    protected List<Observable.OnPropertyChangedCallback> mCallbacks = new ArrayList();

    protected abstract Consumable.ConsumableType getType();

    protected abstract void onDetach();

    @Override // com.huawei.ui.commonui.base.Observable
    public void addOnPropertyChangedCallback(Observable.OnPropertyChangedCallback onPropertyChangedCallback) {
        if (onPropertyChangedCallback == null) {
            LogUtil.h(TAG, "can not add null callback");
        } else if (this.mCallbacks.contains(onPropertyChangedCallback)) {
            LogUtil.h(TAG, "already contains this callback");
        } else {
            this.mCallbacks.add(onPropertyChangedCallback);
        }
    }

    @Override // com.huawei.ui.commonui.base.Observable
    public void removeOnPropertyChangedCallback(Observable.OnPropertyChangedCallback onPropertyChangedCallback) {
        this.mCallbacks.remove(onPropertyChangedCallback);
    }

    public void detach() {
        onDetach();
        this.mCallbacks.clear();
    }

    public void notifyChange(int i) {
        Iterator<Observable.OnPropertyChangedCallback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onPropertyChanged(this, i);
        }
    }
}
