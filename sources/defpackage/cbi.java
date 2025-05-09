package defpackage;

import android.content.res.Resources;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.bloodpressure.BloodPressureApi;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class cbi {
    private static BloodPressureApi b() {
        return (BloodPressureApi) Services.a("Main", BloodPressureApi.class);
    }

    public static List<cbk> a() {
        BloodPressureApi b = b();
        if (b == null) {
            LogUtil.h("BloodPressureUtils", "getBloodPressureMeasurePlan bloodPressureApi is null");
            return new ArrayList();
        }
        return b.getBloodPressureMeasurePlan();
    }

    public static void d(ResponseCallback<List<cbk>> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("BloodPressureUtils", "getBloodPressureMeasurePlan callback is null");
            return;
        }
        BloodPressureApi b = b();
        if (b == null) {
            LogUtil.h("BloodPressureUtils", "getBloodPressureMeasurePlan bloodPressureApi is null");
            responseCallback.onResponse(-1, null);
        } else {
            b.getBloodPressureMeasurePlan(responseCallback);
        }
    }

    public static void d(List<cbk> list, boolean z, ResponseCallback<List<cbk>> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("BloodPressureUtils", "updateAllAlarm callback is null");
            return;
        }
        if (koq.b(list)) {
            LogUtil.h("BloodPressureUtils", "updateAllAlarm alarmInfoList ", list);
            responseCallback.onResponse(-1, null);
            return;
        }
        BloodPressureApi b = b();
        if (b == null) {
            LogUtil.h("BloodPressureUtils", "updateAllAlarm bloodPressureApi is null");
            responseCallback.onResponse(-1, null);
        } else {
            b.updateAllAlarm(list, z, responseCallback);
        }
    }

    public static cbj e(List<cbk> list) {
        if (koq.b(list)) {
            LogUtil.h("BloodPressureUtils", "convertBloodPressureMeasurePlan list ", list);
            return new cbj();
        }
        ArrayList arrayList = new ArrayList();
        for (cbk cbkVar : list) {
            if (cbkVar != null) {
                cbg cbgVar = new cbg();
                cbgVar.d(cbkVar.b());
                cbgVar.e(cbkVar.f());
                cbgVar.a(cbkVar.d());
                cbgVar.b(cbkVar.a());
                cbgVar.c(cbkVar.e());
                cbgVar.c(cbkVar.i());
                arrayList.add(cbgVar);
            }
        }
        cbj cbjVar = new cbj();
        cbjVar.d(arrayList);
        return cbjVar;
    }

    public static List<cbk> a(cbj cbjVar) {
        if (cbjVar == null) {
            LogUtil.h("BloodPressureUtils", "convertBloodPressureAlarmInfoList measurePlan is null");
            return new ArrayList();
        }
        List<cbg> e = cbjVar.e();
        if (koq.b(e)) {
            LogUtil.h("BloodPressureUtils", "convertBloodPressureAlarmInfoList list ", e);
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (cbg cbgVar : e) {
            if (cbgVar != null) {
                cbk cbkVar = new cbk(cbgVar.a(), cbgVar.d(), cbgVar.c(), cbgVar.f());
                cbkVar.b(cbgVar.e());
                cbkVar.b(cbgVar.b());
                arrayList.add(cbkVar);
            }
        }
        return arrayList;
    }

    public static String a(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, 0);
        return nsj.c(BaseApplication.e(), calendar.getTimeInMillis(), 1);
    }

    public static Comparator<cbk> c() {
        return new Comparator() { // from class: cbm
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return cbi.c((cbk) obj, (cbk) obj2);
            }
        };
    }

    static /* synthetic */ int c(cbk cbkVar, cbk cbkVar2) {
        if (cbkVar == null || cbkVar2 == null) {
            return 0;
        }
        int a2 = cbkVar.a();
        int a3 = cbkVar2.a();
        if (a2 == a3) {
            return cbkVar.e() - cbkVar2.e();
        }
        if ((a2 > 2 && a3 > 2) || (a2 < 2 && a3 < 2)) {
            return a2 - a3;
        }
        if (a2 < 2) {
            return 1;
        }
        return a3 < 2 ? -1 : 0;
    }

    public static String a(cbk cbkVar) {
        if (cbkVar == null) {
            LogUtil.h("BloodPressureUtils", "getTargetName plan is null");
            return "";
        }
        Resources resources = BaseApplication.e().getResources();
        int b = cbkVar.b();
        if (b == 0) {
            return resources.getString(R$string.IDS_blood_pressure_get_up_comp);
        }
        if (b == 9) {
            return resources.getString(R$string.IDS_blood_pressure_before_sleep_comp);
        }
        return resources.getString(R$string.IDS_blood_pressure_customize_title_comp, Integer.valueOf(b));
    }

    public static void b(ResponseCallback<JSONObject> responseCallback) {
        BloodPressureApi b = b();
        if (b == null) {
            LogUtil.h("BloodPressureUtils", "queryBloodPressureDeviceStatus bloodPressureApi is null");
            responseCallback.onResponse(-1, null);
        }
        JSONObject queryBloodPressureDeviceStatus = b.queryBloodPressureDeviceStatus();
        if (queryBloodPressureDeviceStatus == null) {
            LogUtil.h("BloodPressureUtils", "queryBloodPressureDeviceStatus jsonObject is null");
            responseCallback.onResponse(-1, null);
        }
        responseCallback.onResponse(0, queryBloodPressureDeviceStatus);
    }
}
