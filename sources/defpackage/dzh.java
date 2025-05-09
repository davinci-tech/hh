package defpackage;

import com.huawei.health.device.api.WiFiWeightSaveHandlerApi;
import com.huawei.health.device.callback.WeightInsertStatusCallback;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Iterator;
import java.util.List;

@ApiDefine(uri = WiFiWeightSaveHandlerApi.class)
/* loaded from: classes3.dex */
public class dzh implements WiFiWeightSaveHandlerApi {
    @Override // com.huawei.health.device.api.WiFiWeightSaveHandlerApi
    public void setMainUserHeartRate(List<HiHealthData> list) {
        cso csoVar = new cso(MultiUsersManager.INSTANCE.getCurrentUser(), new WeightInsertStatusCallback() { // from class: dzg
            @Override // com.huawei.health.device.callback.WeightInsertStatusCallback
            public final void isSuccess(boolean z) {
                dzh.d(z);
            }
        });
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            csoVar.b(it.next());
        }
    }

    static /* synthetic */ void d(boolean z) {
        if (z) {
            LogUtil.a("WiFiWeightSaveHandlerImpl", "setMainUserHeartRate success");
        }
    }
}
