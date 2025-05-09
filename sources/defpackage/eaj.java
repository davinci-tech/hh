package defpackage;

import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.knit.data.KnitHealthDataChangeListener;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes8.dex */
public class eaj implements HiSubscribeListener {
    private String b;
    private WeakReference<KnitHealthDataChangeListener> d;

    public eaj(KnitHealthDataChangeListener knitHealthDataChangeListener, String str) {
        this.d = new WeakReference<>(knitHealthDataChangeListener);
        this.b = str;
    }

    @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
    public void onResult(List<Integer> list, List<Integer> list2) {
        KnitHealthDataChangeListener knitHealthDataChangeListener = this.d.get();
        if (knitHealthDataChangeListener != null && koq.c(list)) {
            knitHealthDataChangeListener.setSubscribeList(list);
        }
    }

    @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
    public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
        KnitHealthDataChangeListener knitHealthDataChangeListener = this.d.get();
        if (knitHealthDataChangeListener == null) {
            return;
        }
        if (ArkUIXConstants.DELETE.equals(str)) {
            knitHealthDataChangeListener.onDataDeleted();
        } else if (ArkUIXConstants.INSERT.equals(str)) {
            knitHealthDataChangeListener.onDataInserted(j);
        } else {
            LogUtil.h(this.b, "unknown changeKey = ", str);
        }
    }
}
