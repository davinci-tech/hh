package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class gig {
    private static void e() {
        HashMap hashMap = new HashMap(4);
        hashMap.put("click", 1);
        hashMap.put("type", 2);
        hashMap.put("from", 5);
        hashMap.put("dataType", new String[]{"weight"});
        ixx.d().d(BaseApplication.e(), AnalyticsValue.HEALTH_HEALTH_WEIGHT_INPUT_2030017.value(), hashMap, 0);
    }

    public static void c(double d) {
        ckm ckmVar = new ckm();
        ckmVar.setBodyFatRat(0.0f);
        ckmVar.setWeight((float) d);
        ckmVar.setStartTime(System.currentTimeMillis());
        ckmVar.e(true);
        ckmVar.setEndTime(System.currentTimeMillis());
        HealthDevice healthDevice = new HealthDevice() { // from class: gig.2
            @Override // com.huawei.health.device.model.HealthDevice
            public String getAddress() {
                return null;
            }

            @Override // com.huawei.health.device.model.HealthDevice
            public String getDeviceName() {
                return null;
            }

            @Override // com.huawei.health.device.model.HealthDevice
            public String getUniqueId() {
                return "-1";
            }
        };
        dfd dfdVar = new dfd(10006);
        dfdVar.e(new IBaseResponseCallback() { // from class: gid
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                gig.e(i, obj);
            }
        });
        dfdVar.onDataChanged(healthDevice, ckmVar);
    }

    static /* synthetic */ void e(int i, Object obj) {
        if (i == 0) {
            e();
        }
    }
}
