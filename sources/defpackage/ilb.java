package defpackage;

import com.huawei.hihealth.IRealTimeDataCallback;
import com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder;

/* loaded from: classes7.dex */
public final /* synthetic */ class ilb implements HiHealthKitExtendBinder.Action {
    public final /* synthetic */ IRealTimeDataCallback b;

    @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
    public final void operate(int i) {
        this.b.onResult(i);
    }
}
