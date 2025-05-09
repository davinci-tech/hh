package com.huawei.coresleepresult;

import android.text.TextUtils;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.bfd;
import defpackage.bfj;
import defpackage.bfk;
import defpackage.bfl;
import defpackage.kts;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

/* loaded from: classes3.dex */
public class HwCoreSleepDataProvider {

    /* renamed from: a, reason: collision with root package name */
    private static volatile boolean f1938a = false;
    private int b = 0;
    private int d = 30;

    private native String GetSleepResult(byte[] bArr, byte[] bArr2, int i);

    private native void SetUserInfo(int i, int i2);

    private static void b() {
        if (f1938a) {
            return;
        }
        try {
            System.loadLibrary("DetailSleepJni");
            f1938a = true;
            LogUtil.a("HwCoreSleepDataProvider", "load detail sleep jni lib success");
        } catch (UnsatisfiedLinkError e) {
            LogUtil.b("HwCoreSleepDataProvider", "load detail sleep jni lib fail:", e.getMessage());
            ReleaseLogUtil.c("BTSYNC_CoreSleep_HwCoreSleepDataProvider", "load detail sleep jni lib fail.");
        }
    }

    public void c(ArrayList<byte[]> arrayList, ArrayList<byte[]> arrayList2, IBaseResponseCallback iBaseResponseCallback) {
        String str;
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepDataProvider", "enter getCoreSleepProcessResult");
        if (arrayList2 == null || arrayList2.isEmpty()) {
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(100001, null);
            }
            ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepDataProvider", "leave getCoreSleepProcessResult strStatusContent size is empty");
            return;
        }
        int a2 = a(c((String) null));
        byte[] c = c(arrayList2);
        if (c == null || c.length == 0) {
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(100001, null);
            }
            ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepDataProvider", "leave getCoreSleepProcessResult status size is empty");
            return;
        }
        b();
        try {
            try {
                kts.d(32);
                if (arrayList != null && !arrayList.isEmpty()) {
                    LogUtil.a("HwCoreSleepDataProvider", "parseByteList 1");
                    byte[] c2 = c(arrayList);
                    e();
                    SetUserInfo(this.d, this.b);
                    str = GetSleepResult(c, c2, a2);
                } else {
                    LogUtil.a("HwCoreSleepDataProvider", "parseByteList 2");
                    str = GetSleepResult(c, new byte[0], a2);
                }
            } catch (UnsatisfiedLinkError unused) {
                ReleaseLogUtil.c("BTSYNC_CoreSleep_HwCoreSleepDataProvider", "getCoreSleepProcessResult UnsatisfiedLinkError");
                str = "";
            }
            kts.c(32);
            if (CommonUtil.bv()) {
                b(str);
            } else {
                LogUtil.a("HwCoreSleepDataProvider", "getCoreSleepProcessResult, result:", str);
            }
            c(str, iBaseResponseCallback);
            LogUtil.a("HwCoreSleepDataProvider", "leave getCoreSleepProcessResult");
        } catch (Throwable th) {
            kts.c(32);
            throw th;
        }
    }

    private void b(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepDataProvider", "getCoreSleepProcessResult, result: no result back.");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("statusInDayArr")) {
                jSONObject.remove("statusInDayArr");
            }
            ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepDataProvider", "getCoreSleepProcessResult, result:", jSONObject.toString());
        } catch (JSONException unused) {
            LogUtil.b("HwCoreSleepDataProvider", "getCoreSleepProcessResult, result: result is error, not json");
        }
    }

    private void c(String str, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepDataProvider", "enter parseSleepData");
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("errCode");
            bfd bfdVar = new bfd();
            if (i != 0) {
                ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepDataProvider", "calculate fail code : ", Integer.valueOf(i));
            } else {
                bfdVar = e(jSONObject);
            }
            LogUtil.c("HwCoreSleepDataProvider", "data:", bfdVar);
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(i, bfdVar);
            }
        } catch (JSONException unused) {
            ReleaseLogUtil.c("BTSYNC_CoreSleep_HwCoreSleepDataProvider", "sleepResult is error.");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(100001, null);
            }
        }
    }

    private bfd e(JSONObject jSONObject) {
        bfd bfdVar = new bfd();
        ArrayList<bfj> arrayList = new ArrayList<>(16);
        ArrayList<bfl> arrayList2 = new ArrayList<>(16);
        ArrayList<bfk> arrayList3 = new ArrayList<>(16);
        try {
            if (jSONObject.has("statusInMinuteArr")) {
                e(jSONObject, arrayList2);
            }
            if (jSONObject.has("statusInDayArr")) {
                c(jSONObject, arrayList);
            }
            if (jSONObject.has("errCodeArr")) {
                a(jSONObject, arrayList3);
            }
        } catch (JSONException unused) {
            ReleaseLogUtil.c("BTSYNC_CoreSleep_HwCoreSleepDataProvider", "processResultData fail.");
        }
        bfdVar.a(arrayList);
        bfdVar.c(arrayList2);
        bfdVar.e(arrayList3);
        return bfdVar;
    }

    private void a(JSONObject jSONObject, ArrayList<bfk> arrayList) throws JSONException {
        LogUtil.a("HwCoreSleepDataProvider", "has errCodeArray");
        JSONArray jSONArray = jSONObject.getJSONArray("errCodeArr");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            bfk bfkVar = new bfk();
            bfkVar.c(jSONArray.getJSONObject(i).getLong("startTime"));
            bfkVar.b(jSONArray.getJSONObject(i).getLong("endTime"));
            bfkVar.d(jSONArray.getJSONObject(i).getInt("errCode"));
            arrayList.add(bfkVar);
        }
    }

    private void e(JSONObject jSONObject, ArrayList<bfl> arrayList) throws JSONException {
        LogUtil.a("HwCoreSleepDataProvider", "has statusInMinuteArray");
        JSONArray jSONArray = jSONObject.getJSONArray("statusInMinuteArr");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            bfl bflVar = new bfl();
            bflVar.d(jSONArray.getJSONObject(i).getLong("startTime"));
            bflVar.a(jSONArray.getJSONObject(i).getLong("endTime"));
            String string = jSONArray.getJSONObject(i).getString("status");
            if (!string.isEmpty()) {
                bflVar.a(string);
            }
            arrayList.add(bflVar);
        }
    }

    private void c(JSONObject jSONObject, ArrayList<bfj> arrayList) throws JSONException {
        LogUtil.a("HwCoreSleepDataProvider", "has statusInDayArray");
        JSONArray jSONArray = jSONObject.getJSONArray("statusInDayArr");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            bfj bfjVar = new bfj();
            float f = (float) jSONArray.getJSONObject(i).getDouble("validData");
            bfjVar.e(f);
            if (f == 1.0f) {
                bfjVar.b(jSONArray.getJSONObject(i).getLong("startTime"));
                bfjVar.c(jSONArray.getJSONObject(i).getLong("fallAsleepTime"));
                bfjVar.d(jSONArray.getJSONObject(i).getLong("wakeUpTime"));
                arrayList.add(bfjVar);
            } else {
                bfjVar.b(jSONArray.getJSONObject(i).getLong("startTime"));
                bfjVar.c(jSONArray.getJSONObject(i).getLong("fallAsleepTime"));
                bfjVar.d(jSONArray.getJSONObject(i).getLong("wakeUpTime"));
                bfjVar.e(jSONArray.getJSONObject(i).getInt("sleepScore"));
                bfjVar.d(jSONArray.getJSONObject(i).getInt("sleepLatency"));
                bfjVar.e(jSONArray.getJSONObject(i).getLong("goBedTime"));
                bfjVar.b(jSONArray.getJSONObject(i).getInt("sleepEfficiency"));
                bfjVar.c(jSONArray.getJSONObject(i).getInt("snoreFreq"));
                bfjVar.a(jSONArray.getJSONObject(i).getInt("deepSleepPartCnt"));
                arrayList.add(bfjVar);
            }
        }
    }

    private byte[] c(ArrayList<byte[]> arrayList) {
        Iterator<byte[]> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().length;
        }
        byte[] bArr = new byte[i];
        Iterator<byte[]> it2 = arrayList.iterator();
        int i2 = 0;
        while (it2.hasNext()) {
            byte[] next = it2.next();
            System.arraycopy(next, 0, bArr, i2, next.length);
            i2 += next.length;
        }
        return bArr;
    }

    public String c(String str) {
        if (str != null && !str.isEmpty()) {
            return str;
        }
        return new SimpleDateFormat("Z").format(Calendar.getInstance().getTime());
    }

    private int a(String str) {
        int i = 1;
        if (str.contains(Marker.ANY_NON_NULL_MARKER)) {
            LogUtil.a("HwCoreSleepDataProvider", Marker.ANY_NON_NULL_MARKER);
            str = str.substring(1);
        } else if (str.contains(Constants.LINK)) {
            LogUtil.a("HwCoreSleepDataProvider", Constants.LINK);
            str = str.substring(1);
            i = -1;
        } else {
            LogUtil.h("HwCoreSleepDataProvider", "do not need parse input time");
        }
        return CommonUtil.m(BaseApplication.getContext(), str) * i;
    }

    private void e() {
        LogUtil.a("HwCoreSleepDataProvider", "enter getUserInfo");
        HiHealthNativeApi.a(BaseApplication.getContext()).fetchUserData(new HiCommonListener() { // from class: com.huawei.coresleepresult.HwCoreSleepDataProvider.4
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                if (obj instanceof List) {
                    List list = (List) obj;
                    if (list.isEmpty()) {
                        return;
                    }
                    HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                    HwCoreSleepDataProvider.this.b = hiUserInfo.getGender();
                    if (HwCoreSleepDataProvider.this.b == 1) {
                        HwCoreSleepDataProvider.this.b = 0;
                    } else {
                        HwCoreSleepDataProvider.this.b = 1;
                    }
                    HwCoreSleepDataProvider.this.d = hiUserInfo.getAge();
                    if (HwCoreSleepDataProvider.this.d <= 0 || HwCoreSleepDataProvider.this.d > 130) {
                        HwCoreSleepDataProvider.this.d = 30;
                    }
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.b("HwCoreSleepDataProvider", "get user info errorCode:", Integer.valueOf(i), ",errorMessage:", obj);
            }
        });
    }
}
