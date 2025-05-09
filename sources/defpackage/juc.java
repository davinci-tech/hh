package defpackage;

import android.os.Bundle;
import com.huawei.datatype.DeviceCommand;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseAdviceManager;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseParams;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.MotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class juc {
    private static juc c;
    private IBaseResponseCallback g;
    private List<JSONArray> i;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14087a = new Object();
    private int e = 0;
    private int b = 0;
    private Map<Integer, List<JSONArray>> f = new HashMap(16);

    public static juc b() {
        juc jucVar;
        synchronized (d) {
            if (c == null) {
                LogUtil.a("BloodOxygenManager", "getInstance()");
                c = new juc();
            }
            jucVar = c;
        }
        return jucVar;
    }

    public void b(int i) {
        this.b = i;
        LogUtil.a("BloodOxygenManager", "getDeviceWorkoutRecordStatistic() mBloodOxygenIndex :", Integer.valueOf(i));
    }

    public void b(int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("BloodOxygenManager", "mBloodOxygenIndex :", Integer.valueOf(this.b), "workout_record_id :", Integer.valueOf(i));
        if (iBaseResponseCallback == null) {
            LogUtil.a("BloodOxygenManager", "handleBloodOxygen callback is null.");
        } else if (this.b != 0) {
            this.i = new ArrayList(16);
            a(i, this.b, i2, iBaseResponseCallback);
        } else {
            iBaseResponseCallback.d(i2, null);
        }
    }

    public void e() {
        this.f.clear();
    }

    public void c(int i, int i2, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        LogUtil.a("BloodOxygenManager", "getBloodOxygenListStatistic enter");
        synchronized (f14087a) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(23);
            deviceCommand.setCommandID(20);
            String a2 = cvx.a(i);
            String d2 = cvx.d(a2.length() / 2);
            String e = cvx.e(1);
            String a3 = cvx.a(i2);
            String d3 = cvx.d(a3.length() / 2);
            String e2 = cvx.e(2);
            String e3 = cvx.e(3);
            String e4 = cvx.e(0);
            StringBuilder sb = new StringBuilder(16);
            sb.append(e);
            sb.append(d2);
            sb.append(a2);
            sb.append(e2);
            sb.append(d3);
            sb.append(a3);
            sb.append(e3);
            sb.append(e4);
            deviceCommand.setDataLen(sb.length() / 2);
            deviceCommand.setDataContent(cvx.a(sb.toString()));
            jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
            this.g = iBaseResponseCallback;
        }
    }

    public void b(List<cwd> list, List<cwe> list2) {
        jud judVar = new jud();
        for (cwd cwdVar : list) {
            int a2 = CommonUtil.a(cwdVar.e(), 16);
            if (a2 == 1) {
                judVar.d(Integer.parseInt(cwdVar.c(), 16));
            } else if (a2 == 2) {
                judVar.a(Integer.parseInt(cwdVar.c(), 16));
            } else {
                LogUtil.c("BloodOxygenManager", "Invalid value");
            }
        }
        ArrayList arrayList = new ArrayList(16);
        Iterator<cwe> it = list2.iterator();
        while (it.hasNext()) {
            Iterator<cwe> it2 = it.next().a().iterator();
            while (it2.hasNext()) {
                e(it2.next(), arrayList);
            }
        }
        judVar.e(arrayList);
        IBaseResponseCallback iBaseResponseCallback = this.g;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(100000, kkm.d(judVar, "getWorkoutRecordBloodOxygenList"));
        }
    }

    private void e(cwe cweVar, List<jua> list) {
        jua juaVar = new jua();
        Bundle bundle = new Bundle();
        for (cwd cwdVar : cweVar.e()) {
            int w = CommonUtil.w(cwdVar.e());
            if (w == 5) {
                bundle.putLong("interval", CommonUtil.y(cwdVar.c()));
            } else if (w == 6) {
                bundle.putInt("value", CommonUtil.w(cwdVar.c()));
            } else {
                LogUtil.c("BloodOxygenManager", "Invalid value");
            }
        }
        juaVar.bJu_(bundle);
        list.add(juaVar);
    }

    public void a(int i, MotionPath motionPath) {
        char c2 = 1;
        try {
            ArrayList arrayList = new ArrayList(16);
            Map<Integer, List<JSONArray>> map = this.f;
            if (map != null && map.get(Integer.valueOf(i)) != null) {
                arrayList.addAll(this.f.get(Integer.valueOf(i)));
                LogUtil.a("BloodOxygenManager", "RecordBlood bloodOxygenArrayList size:", Integer.valueOf(arrayList.size()));
            }
            ArrayList arrayList2 = new ArrayList(16);
            int i2 = 0;
            while (i2 < arrayList.size()) {
                Object[] objArr = new Object[4];
                objArr[0] = "Enter for i bloodOxygenArrayList.size :";
                objArr[c2] = Integer.valueOf(arrayList.size());
                objArr[2] = "i :";
                char c3 = 3;
                objArr[3] = Integer.valueOf(i2);
                LogUtil.c("BloodOxygenManager", objArr);
                if (arrayList.get(i2) instanceof JSONArray) {
                    JSONArray jSONArray = (JSONArray) arrayList.get(i2);
                    int i3 = 0;
                    while (i3 < jSONArray.length()) {
                        Object[] objArr2 = new Object[4];
                        objArr2[0] = "Enter for i blood_oxygen_struct.length() :";
                        objArr2[c2] = Integer.valueOf(jSONArray.length());
                        objArr2[2] = "j :";
                        objArr2[c3] = Integer.valueOf(i3);
                        LogUtil.c("BloodOxygenManager", objArr2);
                        JSONObject jSONObject = jSONArray.getJSONObject(i3);
                        arrayList2.add(new kof(jSONObject.getLong("interval"), jSONObject.getInt("value")));
                        c3 = 3;
                        LogUtil.c("BloodOxygenManager", "interval:", Long.valueOf(jSONObject.getLong("interval")), "value:", Integer.valueOf(jSONObject.getInt("value")));
                        i3++;
                        arrayList = arrayList;
                        c2 = 1;
                    }
                }
                i2++;
                arrayList = arrayList;
                c2 = 1;
            }
            motionPath.saveSpo2List(arrayList2);
        } catch (JSONException unused) {
            LogUtil.b("BloodOxygenManager", "saveOxygenData JSONException");
        }
    }

    private void a(final int i, final int i2, final int i3, final IBaseResponseCallback iBaseResponseCallback) {
        try {
            if (this.e < i2) {
                LogUtil.a("BloodOxygenManager", "getBloodOxygenList. workout_record_id :", Integer.valueOf(i), "bloodOxygenIndex :", Integer.valueOf(this.e), "errorCode :", Integer.valueOf(i3));
                c(i, this.e, new IBaseResponseCallback() { // from class: juc.1
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i4, Object obj) {
                        LogUtil.a("BloodOxygenManager", "Enter getBloodOxygenList().onResponse()");
                        juc.this.e(obj, i, i2, i3, iBaseResponseCallback);
                    }
                });
                return;
            }
            c();
            if (this.i != null) {
                this.f.put(Integer.valueOf(i), this.i);
                LogUtil.a("BloodOxygenManager", "mWorkoutRecordBloodOxygenMapList.size :", Integer.valueOf(this.f.size()));
            }
            iBaseResponseCallback.d(i3, null);
            this.b = 0;
            this.e = 0;
        } catch (JSONException unused) {
            LogUtil.b("BloodOxygenManager", "getBloodOxygenList JSONException");
            d(i3);
        }
    }

    private void d(int i) {
        HwExerciseAdviceManager.getInstance().setWorkoutRunPlayFailCount(HwExerciseAdviceManager.getInstance().getWorkoutRunPlayFailCount() + 1);
        HwExerciseParams.getInstance().setWorkoutRecordStatisticIndex(HwExerciseParams.getInstance().getWorkoutRecordStatisticIndex() + 1);
        HwExerciseAdviceManager.getInstance().getDeviceWorkoutRecordStatistic(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Object obj, int i, int i2, int i3, IBaseResponseCallback iBaseResponseCallback) {
        try {
            if (obj == null) {
                LogUtil.h("BloodOxygenManager", "Enter getBloodOxygenStatisticJsonList objectData is null");
                d(i3);
                return;
            }
            if (!(obj instanceof Map)) {
                LogUtil.h("BloodOxygenManager", "Enter getBloodOxygenStatisticJsonList objectData not instanceof Map");
                d(i3);
                return;
            }
            Object obj2 = ((Map) obj).get("value");
            if (!(obj2 instanceof String)) {
                LogUtil.h("BloodOxygenManager", "Enter getBloodOxygenStatisticJsonList valueObject not instanceof String");
                d(i3);
                return;
            }
            String str = (String) obj2;
            JSONObject jSONObject = new JSONObject(str);
            if (!(jSONObject.get("bloodOxygenStructList") instanceof JSONArray)) {
                LogUtil.h("BloodOxygenManager", "Enter getBloodOxygenStatisticJsonList not instanceof JSONArray");
                d(i3);
                return;
            }
            JSONArray jSONArray = (JSONArray) jSONObject.get("bloodOxygenStructList");
            LogUtil.a("BloodOxygenManager", "value :", str);
            this.e++;
            List<JSONArray> list = this.i;
            if (list != null) {
                list.add(jSONArray);
            }
            a(i, i2, i3, iBaseResponseCallback);
        } catch (JSONException unused) {
            LogUtil.b("BloodOxygenManager", "getBloodOxygenStatisticJsonList onResponse JSONException");
            this.e++;
            a(i, i2, 2, iBaseResponseCallback);
        }
    }

    private void c() {
        List<JSONArray> list = this.i;
        if (list == null) {
            LogUtil.h("BloodOxygenManager", "printWorkoutRecordBloodOxygenInfo mBloodOxygenStatisticJsonList is null");
            return;
        }
        LogUtil.a("BloodOxygenManager", "printWorkoutRecordBloodOxygenInfo enter. mSectionRecordsStatisticJsonObjects.size :", Integer.valueOf(list.size()));
        for (int i = 0; i < this.i.size(); i++) {
            try {
                if (this.i.get(i) instanceof JSONArray) {
                    JSONArray jSONArray = this.i.get(i);
                    LogUtil.c("BloodOxygenManager", "printWorkoutRecordBloodOxygenInfo bloodOxygenStruct.length() :", Integer.valueOf(jSONArray.length()));
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i2);
                        LogUtil.c("BloodOxygenManager", "interval :", Long.valueOf(jSONObject.getLong("interval")), "value :", Integer.valueOf(jSONObject.getInt("value")));
                    }
                }
            } catch (JSONException unused) {
                LogUtil.b("BloodOxygenManager", "printWorkoutRecordBloodOxygenInfo JSONException");
                return;
            }
        }
    }
}
