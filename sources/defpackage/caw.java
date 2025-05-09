package defpackage;

import android.content.Context;
import com.huawei.health.basesport.wearengine.SportBaseEngineManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.unitedevice.constant.WearEngineModule;

/* loaded from: classes3.dex */
public class caw extends SportBaseEngineManager {

    /* renamed from: a, reason: collision with root package name */
    private static volatile caw f587a;
    private static final Object c = new Object();

    private caw(Context context) {
        super(context);
    }

    public static caw d() {
        caw cawVar;
        synchronized (c) {
            LogUtil.a("Suggestion_HiWearEngineFitnessManager", "getInstance()");
            if (f587a == null) {
                f587a = new caw(BaseApplication.getContext());
            }
            cawVar = f587a;
        }
        return cawVar;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.FITNESS_MODULE;
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
        return "Suggestion_HiWearEngineFitnessManager";
    }
}
