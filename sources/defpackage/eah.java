package defpackage;

import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes8.dex */
public class eah implements HiUnSubscribeListener {

    /* renamed from: a, reason: collision with root package name */
    private final String f11927a;

    public eah(String str) {
        this.f11927a = str;
    }

    @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
    public void onResult(boolean z) {
        LogUtil.a(this.f11927a, "KnitHealthDataUnSubscriber unsubscribe isSuccess = ", Boolean.valueOf(z));
    }
}
