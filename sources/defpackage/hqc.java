package defpackage;

import android.content.Context;
import com.huawei.health.basesport.wearengine.SportBaseEngineManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.unitedevice.constant.WearEngineModule;

/* loaded from: classes4.dex */
public class hqc extends SportBaseEngineManager {

    /* renamed from: a, reason: collision with root package name */
    private static volatile hqc f13320a;
    private static final Object b = new Object();

    protected hqc(Context context) {
        super(context);
    }

    public static hqc c() {
        hqc hqcVar;
        synchronized (b) {
            LogUtil.a("Track_HiWearEngineTrackManager", "getInstance()");
            if (f13320a == null) {
                f13320a = new hqc(BaseApplication.getContext());
            }
            hqcVar = f13320a;
        }
        return hqcVar;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.MOTION_TRACK_MODULE;
    }

    @Override // com.huawei.health.basesport.wearengine.SportBaseEngineManager, com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "in.huawei.fitness";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return OfflineMapWearEngineManager.WEAR_FINGERPRINT;
    }

    @Override // com.huawei.health.basesport.wearengine.SportBaseEngineManager
    public String getTag() {
        return "Track_HiWearEngineTrackManager";
    }
}
