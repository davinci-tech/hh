package defpackage;

import android.os.Looper;
import com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.utils.handler.AppCheckNewVersionHandler;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class jkq extends AppCheckNewVersionHandler {

    /* renamed from: a, reason: collision with root package name */
    private String f13918a;
    private final WeakReference<HwUpdateService> c;
    private int d;

    public jkq(String str, int i, Looper looper, HwUpdateService hwUpdateService) {
        super(looper);
        this.f13918a = str;
        this.d = i;
        this.c = new WeakReference<>(hwUpdateService);
    }

    @Override // com.huawei.hwversionmgr.utils.handler.AppCheckNewVersionHandler
    public void handleCheckSuccess(kxj kxjVar) {
        LogUtil.a("R_OTA_BandCheckNewVersionToActivateHandler", "bandCheckNewVersionToActivate: handleCheckSuccess");
        jkk.d().b(this.f13918a, true);
        HwUpdateService hwUpdateService = this.c.get();
        if (hwUpdateService == null) {
            LogUtil.h("BandCheckNewVersionToActivateHandler", "service is null");
        } else {
            hwUpdateService.e(this.d, "BandCheckNewVersionToActivateHandler handleCheckSuccess");
        }
    }

    @Override // com.huawei.hwversionmgr.utils.handler.AppCheckNewVersionHandler
    public void handleCheckFailed(int i) {
        LogUtil.a("R_OTA_BandCheckNewVersionToActivateHandler", "bandCheckNewVersionToActivate: handleCheckFailed");
        if (i == 0) {
            LogUtil.a("R_OTA_BandCheckNewVersionToActivateHandler", "bandCheckNewVersionToActivate: FAILED_REASON_NOT_FOUND");
            jkk.d().b(this.f13918a, true);
        }
        HwUpdateService hwUpdateService = this.c.get();
        if (hwUpdateService == null) {
            LogUtil.h("BandCheckNewVersionToActivateHandler", "service is null");
        } else {
            hwUpdateService.e(this.d, "BandCheckNewVersionToActivateHandler handleCheckFailed");
        }
    }
}
