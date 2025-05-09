package defpackage;

import com.huawei.basichealth.bloodpressure.BloodPressureSyncManager;
import com.huawei.health.bloodpressure.BloodPressureApi;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.health.bloodpressure.util.BloodPressureJsApi;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@ApiDefine(uri = BloodPressureApi.class)
@Singleton
/* loaded from: classes6.dex */
public class qgs implements BloodPressureApi {
    @Override // com.huawei.health.bloodpressure.BloodPressureApi
    public Class<? extends JsBaseModule> getBloodPressureJsApi() {
        return BloodPressureJsApi.class;
    }

    @Override // com.huawei.health.bloodpressure.BloodPressureApi
    public List<cbk> getBloodPressureMeasurePlan() {
        return qif.d();
    }

    @Override // com.huawei.health.bloodpressure.BloodPressureApi
    public void getBloodPressureMeasurePlan(ResponseCallback<List<cbk>> responseCallback) {
        qif.c(responseCallback);
    }

    @Override // com.huawei.health.bloodpressure.BloodPressureApi
    public void updateAllAlarm(List<cbk> list, boolean z, ResponseCallback<List<cbk>> responseCallback) {
        if (list == null || responseCallback == null) {
            LogUtil.h("BloodPressureImpl", "updateAllAlarm alarmInfoArray ", list, " callback ", responseCallback);
            return;
        }
        Iterator<cbk> it = list.iterator();
        while (it.hasNext()) {
            it.next().b(z);
        }
        qhc.d(list, z, responseCallback);
    }

    @Override // com.huawei.health.bloodpressure.BloodPressureApi
    public JSONObject queryBloodPressureDeviceStatus() {
        try {
            boolean z = qif.b(BaseApplication.getContext()) > 0;
            boolean e = BloodPressureSyncManager.e();
            LogUtil.a("BloodPressureImpl", "queryBloodPressureDeviceStatus isBondedProducts： ", Boolean.valueOf(z), ", isBloodPressureWatch: ", Boolean.valueOf(e));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("isBondedProducts", z);
            jSONObject.put("isBloodPressureWatch", e);
            return jSONObject;
        } catch (JSONException e2) {
            LogUtil.h("BloodPressureImpl", "queryBloodPressureDeviceStatus JSONException:", e2.getMessage());
            return null;
        }
    }

    @Override // com.huawei.health.bloodpressure.BloodPressureApi
    public int getBloodPressureGradeType(int i, int i2) {
        return eeu.c(i, i2);
    }
}
