package defpackage;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Message;
import com.huawei.haf.handler.BaseHandlerCallback;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.hihealth.device.open.IHealthDeviceCallback;
import com.huawei.hihealth.device.open.MeasureController;
import com.huawei.hihealth.device.open.MeasureKit;
import com.huawei.hihealth.device.open.data.MeasureRecord;
import com.huawei.hihealth.device.open.data.MeasureResult;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwstressmgr.interfaces.StressWearAppInterface;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kxc {

    /* renamed from: a, reason: collision with root package name */
    private d f14675a;
    private ComponentName b;
    private AudioManager.OnAudioFocusChangeListener c;
    private int d;
    private AudioManager e;
    private boolean f;
    private boolean g;
    private String h;
    private boolean i;
    private boolean j;
    private List<Integer> k;
    private JSONObject l;
    private IBaseResponseCallback m;
    private List<Integer> n;
    private long o;
    private IBaseResponseCallback p;
    private String q;
    private StressWearAppInterface r;
    private ExtendHandler s;
    private IBaseResponseCallback t;

    class a extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
        }
    }

    private boolean b(int i) {
        return i == 1 || i == 4 || i == 12 || i == 8 || i == 9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(int i) {
        return i == 2 || i == 3 || i == 5 || i == 6 || i == 10 || i == 11 || i == 13 || i == 14;
    }

    private kxc() {
        this.d = 5;
        this.g = false;
        this.n = new ArrayList(16);
        this.k = new ArrayList(16);
        this.l = new JSONObject();
        this.h = "";
        this.q = "";
        this.i = false;
        this.m = new IBaseResponseCallback() { // from class: kxc.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("Opera_StressService", "stressFunctionCallback errorCode: ", Integer.valueOf(i), " objData: ", obj);
            }
        };
        this.t = new IBaseResponseCallback() { // from class: kxc.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj != null) {
                    LogUtil.a("Opera_StressService", "mStressWearCallback stress is not null errCode is ", Integer.valueOf(i));
                    kxc.this.e(i, obj);
                } else if (i != 100000) {
                    LogUtil.a("Opera_StressService", "failed to open or close stress");
                }
            }
        };
        this.s = HandlerCenter.yt_(new e(this), "stress_error_handler");
    }

    public static kxc e() {
        return b.d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, Object obj) {
        if (i != 100000) {
            LogUtil.a("Opera_StressService", "failed to open or close stress, errCode=", Integer.valueOf(i));
            return;
        }
        try {
            LogUtil.a("Opera_StressService", "mStressWearCallback get callback ");
            if (obj instanceof String) {
                a(new JSONObject((String) obj));
            }
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "mStressWearCallback JSONException");
        }
    }

    private void c() {
        this.i = true;
        if (this.f14675a != null) {
            BaseApplication.getContext().unregisterReceiver(this.f14675a);
            this.f14675a = null;
        }
    }

    private void a(JSONObject jSONObject) {
        LogUtil.a("Opera_StressService", "handleWearCallback() enter");
        if (jSONObject == null) {
            LogUtil.b("Opera_StressService", "handleWearCallback() jsonObject is null.");
            return;
        }
        LogUtil.a("Opera_StressService", "handleWearCallback() jsonObject: ", jSONObject.toString(), ", mIsWaitingFor2ndCallback is: ", Boolean.valueOf(this.g));
        this.l = jSONObject;
        int optInt = jSONObject.optInt("type");
        if (d(this.l)) {
            if (this.p != null) {
                LogUtil.a("Opera_StressService", " isSuccessfulResultCallback send to h5 type ", Integer.valueOf(optInt));
                this.p.d(0, this.l.toString());
                if (e(optInt)) {
                    this.p = null;
                }
            }
        } else if (this.p != null) {
            LogUtil.a("Opera_StressService", "send to h5 + type ", Integer.valueOf(optInt));
            this.p.d(0, jSONObject.toString());
            if (e(optInt)) {
                this.p = null;
            }
        }
        this.g = false;
        this.l = new JSONObject();
        c(10005);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0043, code lost:
    
        if (r8.getInt("result_code") == 0) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean d(org.json.JSONObject r8) {
        /*
            r7 = this;
            java.lang.String r0 = "isSuccessfulResultCallback enter."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "Opera_StressService"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r0 = 0
            java.lang.String r2 = "type"
            int r2 = r8.getInt(r2)     // Catch: org.json.JSONException -> L5f
            r3 = 2
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch: org.json.JSONException -> L5f
            java.lang.String r5 = "isSuccessfulResultCallback type: "
            r4[r0] = r5     // Catch: org.json.JSONException -> L5f
            java.lang.Integer r5 = java.lang.Integer.valueOf(r2)     // Catch: org.json.JSONException -> L5f
            r6 = 1
            r4[r6] = r5     // Catch: org.json.JSONException -> L5f
            com.huawei.hwlogsmodel.LogUtil.a(r1, r4)     // Catch: org.json.JSONException -> L5f
            switch(r2) {
                case 1: goto L3c;
                case 2: goto L33;
                case 3: goto L3c;
                case 4: goto L3c;
                case 5: goto L33;
                case 6: goto L3c;
                case 7: goto L2a;
                case 8: goto L3c;
                case 9: goto L3c;
                case 10: goto L33;
                case 11: goto L3c;
                case 12: goto L3c;
                case 13: goto L33;
                case 14: goto L3c;
                case 15: goto L3c;
                default: goto L27;
            }     // Catch: org.json.JSONException -> L5f
        L27:
            java.lang.Object[] r8 = new java.lang.Object[r6]     // Catch: org.json.JSONException -> L5f
            goto L47
        L2a:
            java.lang.String r2 = "calibration_flag"
            int r8 = r8.getInt(r2)     // Catch: org.json.JSONException -> L5f
            if (r8 != 0) goto L4e
            goto L45
        L33:
            java.lang.String r2 = "flag"
            int r8 = r8.getInt(r2)     // Catch: org.json.JSONException -> L5f
            if (r8 != r6) goto L4e
            goto L45
        L3c:
            java.lang.String r2 = "result_code"
            int r8 = r8.getInt(r2)     // Catch: org.json.JSONException -> L5f
            if (r8 != 0) goto L4e
        L45:
            r8 = r6
            goto L4f
        L47:
            java.lang.String r2 = "isSuccessfulResultCallback() error type."
            r8[r0] = r2     // Catch: org.json.JSONException -> L5f
            com.huawei.hwlogsmodel.LogUtil.a(r1, r8)     // Catch: org.json.JSONException -> L5f
        L4e:
            r8 = r0
        L4f:
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch: org.json.JSONException -> L5f
            java.lang.String r3 = "isSuccessfulResultCallback isSuccess:"
            r2[r0] = r3     // Catch: org.json.JSONException -> L5f
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r8)     // Catch: org.json.JSONException -> L5f
            r2[r6] = r3     // Catch: org.json.JSONException -> L5f
            com.huawei.hwlogsmodel.LogUtil.a(r1, r2)     // Catch: org.json.JSONException -> L5f
            return r8
        L5f:
            java.lang.String r8 = "isSuccessfulResultCallback() JSONException"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kxc.d(org.json.JSONObject):boolean");
    }

    public void c(StressWearAppInterface stressWearAppInterface) {
        LogUtil.a("Opera_StressService", "registerStressCallback() enter");
        this.r = stressWearAppInterface;
    }

    private void a(int i) {
        LogUtil.a("Opera_StressService", "updateStressStatus() enter, type: ", Integer.valueOf(i), " status: ", Integer.valueOf(this.d));
        switch (i) {
            case 1:
                this.d = 1;
                break;
            case 2:
            case 3:
            case 5:
            case 6:
            case 10:
            case 11:
            case 13:
            case 14:
                this.d = 5;
                break;
            case 4:
                this.d = 4;
                break;
            case 7:
            case 8:
            default:
                LogUtil.a("Opera_StressService", "updateStressStatus() is default");
                break;
            case 9:
                this.d = 2;
                break;
            case 12:
                this.d = 3;
                break;
        }
        LogUtil.a("Opera_StressService", "updateStressStatus() new control status: ", Integer.valueOf(this.d));
    }

    private void e(int i, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "openStress()");
        this.o = System.currentTimeMillis();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", 1);
            jSONObject.put("max_duration", i);
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "openStress JSONException");
        }
        e(jSONObject, iBaseResponseCallback);
        c(1, i);
    }

    private void h(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "closeStress()");
        JSONObject jSONObject = new JSONObject();
        long currentTimeMillis = (System.currentTimeMillis() - this.o) / 1000;
        try {
            jSONObject.put("type", 2);
            jSONObject.put("duration", currentTimeMillis);
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "closeStress JSONException");
        }
        e(jSONObject, iBaseResponseCallback);
        c(10001);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "abortStress()");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", 3);
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "abortStress JSONException");
        }
        e(jSONObject, iBaseResponseCallback);
        c(10001);
    }

    private void e(int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "openCalibration()");
        this.o = System.currentTimeMillis();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", 4);
            jSONObject.put(JsUtil.SCORE, i2);
            jSONObject.put("max_duration", i);
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "openCalibration JSONException");
        }
        e(jSONObject, iBaseResponseCallback);
        c(4, i);
    }

    private void f(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "closeCalibration()");
        JSONObject jSONObject = new JSONObject();
        long currentTimeMillis = (System.currentTimeMillis() - this.o) / 1000;
        try {
            jSONObject.put("type", 5);
            jSONObject.put("duration", currentTimeMillis);
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "closeCalibration JSONException");
        }
        e(jSONObject, iBaseResponseCallback);
        c(10004);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "abortCalibration()");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", 6);
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "abortCalibration JSONException");
        }
        e(jSONObject, iBaseResponseCallback);
        c(10004);
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "checkCalibration()");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", 7);
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "checkCalibration JSONException");
        }
        e(jSONObject, iBaseResponseCallback);
    }

    public void b(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "resetCalibration()");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", 8);
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "resetCalibration JSONException");
        }
        e(jSONObject, iBaseResponseCallback);
    }

    private void b(int i, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "openRelax()");
        this.o = System.currentTimeMillis();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", 9);
            jSONObject.put("max_duration", i);
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "openRelax JSONException");
        }
        e(jSONObject, iBaseResponseCallback);
        c(9, i);
    }

    private void i(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "closeRelax()");
        JSONObject jSONObject = new JSONObject();
        long currentTimeMillis = (System.currentTimeMillis() - this.o) / 1000;
        try {
            jSONObject.put("type", 10);
            jSONObject.put("duration", currentTimeMillis);
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "closeRelax JSONException");
        }
        e(jSONObject, iBaseResponseCallback);
        c(10002);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "abortRelax()");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", 11);
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "abortRelax JSONException");
        }
        e(jSONObject, iBaseResponseCallback);
        c(10002);
    }

    private void c(int i, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "openGame()");
        this.o = System.currentTimeMillis();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", 12);
            jSONObject.put("max_duration", i);
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "openGame JSONException");
        }
        e(jSONObject, iBaseResponseCallback);
        c(12, i);
    }

    private void d(int i, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "closeGame()");
        JSONObject jSONObject = new JSONObject();
        long currentTimeMillis = (System.currentTimeMillis() - this.o) / 1000;
        try {
            jSONObject.put("type", 13);
            jSONObject.put("duration", currentTimeMillis);
            jSONObject.put(JsUtil.SCORE, i);
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "closeGame JSONException");
        }
        e(jSONObject, iBaseResponseCallback);
        c(10003);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "abortGame()");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", 14);
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "abortGame JSONException");
        }
        e(jSONObject, iBaseResponseCallback);
        c(10003);
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "checkConnected() enter.");
        this.i = false;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", 15);
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "checkConnected JSONException");
        }
        e(jSONObject, iBaseResponseCallback);
    }

    private void e(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "switchStressFunctionStatus enter");
        if (this.f14675a == null && !this.i) {
            this.f14675a = new d();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PHONE_STATE");
            intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
            BaseApplication.getContext().registerReceiver(this.f14675a, intentFilter);
        }
        try {
            jSONObject.put("productId", this.h);
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "switchStressFunctionStatus JSONException");
        }
        StressWearAppInterface stressWearAppInterface = this.r;
        if (stressWearAppInterface != null) {
            LogUtil.a("Opera_StressService", "isSupportStressReport:", Boolean.valueOf(stressWearAppInterface.getDeviceSupportStressReport()), " mProductId ", this.h);
            this.p = iBaseResponseCallback;
            int optInt = jSONObject.optInt("type");
            b(jSONObject, optInt);
            a(optInt);
            return;
        }
        LogUtil.b("Opera_StressService", "switchStressFunctionStatus mStressWearAppInterface is null.");
    }

    private void b(JSONObject jSONObject, int i) {
        ArrayList<ContentValues> d2 = cjx.e().d(HealthDevice.HealthDeviceKind.HDK_HEART_RATE);
        if (koq.b(d2)) {
            this.r.setStressMeasureStatus(jSONObject, this.t);
            return;
        }
        if (i == 15) {
            String str = "";
            this.h = "";
            Iterator<ContentValues> it = d2.iterator();
            String str2 = "";
            while (it.hasNext()) {
                ContentValues next = it.next();
                if (cpa.z(next.getAsString("productId"))) {
                    str = next.getAsString("productId");
                    str2 = next.getAsString("uniqueId");
                }
            }
            if (cpa.z(str)) {
                a(jSONObject, str, str2);
                b();
                return;
            } else {
                this.r.setStressMeasureStatus(jSONObject, this.t);
                return;
            }
        }
        boolean z = cpa.z(this.h);
        if (b(i)) {
            if (z) {
                a(this.h, this.q, i);
            }
            this.r.setStressMeasureStatus(jSONObject, this.t);
        } else {
            if (!e(i)) {
                this.r.setStressMeasureStatus(jSONObject, this.t);
                return;
            }
            if (z) {
                try {
                    jSONObject.put("rri", i());
                } catch (JSONException unused) {
                    LogUtil.b("Opera_StressService", "determineDeviceTypes JSONException");
                }
                this.r.setStressMeasureStatus(jSONObject, this.t);
                a();
                return;
            }
            this.r.setStressMeasureStatus(jSONObject, this.t);
        }
    }

    private void a(final JSONObject jSONObject, final String str, final String str2) {
        com.huawei.hihealth.device.open.HealthDevice b2 = ceo.d().b(str, str2);
        MeasureKit c = cfl.a().c("54C9739F-CA5C-4347-9F00-75B9DDF2C649");
        if (c == null) {
            this.r.setStressMeasureStatus(jSONObject, this.t);
            return;
        }
        MeasureController measureController = c.getMeasureController();
        if (measureController != null && b2 != null) {
            measureController.prepare(b2, new IHealthDeviceCallback() { // from class: kxc.2
                @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
                public void onDataChanged(com.huawei.hihealth.device.open.HealthDevice healthDevice, MeasureResult measureResult) {
                }

                @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
                public void onFailed(com.huawei.hihealth.device.open.HealthDevice healthDevice, int i) {
                }

                @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
                public void onProgressChanged(com.huawei.hihealth.device.open.HealthDevice healthDevice, MeasureRecord measureRecord) {
                }

                @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
                public void onStatusChanged(com.huawei.hihealth.device.open.HealthDevice healthDevice, int i) {
                    LogUtil.a("Opera_StressService", "prepare heartRateData onStatusChanged: ", Integer.valueOf(i));
                    kxc.this.d(i, str, str2, jSONObject);
                }
            }, cpa.JZ_());
        } else {
            this.r.setStressMeasureStatus(jSONObject, this.t);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, String str, String str2, JSONObject jSONObject) {
        if (i != 0 && i != 8 && i != 16) {
            if (i == 2) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("type", 15);
                    jSONObject2.put("result_code", 0);
                    this.h = str;
                    this.q = str2;
                    this.t.d(100000, jSONObject2.toString());
                    return;
                } catch (JSONException unused) {
                    LogUtil.b("Opera_StressService", "switchStatus JSONException");
                    return;
                }
            }
            if (i != 3 && i != 12 && i != 13) {
                LogUtil.a("Opera_StressService", "prepare onStatusChanged status default.");
                return;
            }
        }
        this.r.setStressMeasureStatus(jSONObject, this.t);
    }

    private void a(String str, String str2, final int i) {
        this.n.clear();
        this.k.clear();
        this.j = true;
        this.f = false;
        this.s.sendEmptyMessage(2, PreConnectManager.CONNECT_INTERNAL);
        cjx.e().Gu_(str, str2, new IHealthDeviceCallback() { // from class: kxc.4
            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onFailed(com.huawei.hihealth.device.open.HealthDevice healthDevice, int i2) {
            }

            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onProgressChanged(com.huawei.hihealth.device.open.HealthDevice healthDevice, MeasureRecord measureRecord) {
            }

            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onDataChanged(com.huawei.hihealth.device.open.HealthDevice healthDevice, MeasureResult measureResult) {
                kxc.this.d(i);
                if (dic.c().d(measureResult) instanceof der) {
                    der derVar = (der) dic.c().d(measureResult);
                    LogUtil.a("Opera_StressService", "heartRateData");
                    if (derVar != null) {
                        LogUtil.c("Opera_StressService", "heartRateData:", Integer.valueOf(derVar.getHeartRate()));
                        kxc.this.e(measureResult.getRecords().get(0).getValueList("RRI_SQI_RESULT"));
                    }
                }
            }

            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onStatusChanged(com.huawei.hihealth.device.open.HealthDevice healthDevice, int i2) {
                LogUtil.a("Opera_StressService", "heartRateData_onStatusChanged: ", Integer.valueOf(i2));
                if (i2 == 14) {
                    kxc.this.d(i);
                } else if (i2 == 3 || i2 == 8) {
                    kxc kxcVar = kxc.this;
                    kxcVar.a(kxcVar.p, false);
                } else {
                    LogUtil.a("Opera_StressService", "openHeartRate onStatusChange unknow.");
                }
            }
        }, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(ArrayList<Number> arrayList) {
        if (arrayList != null) {
            LogUtil.c("Opera_StressService", "rri toString: ", a(arrayList));
        }
        if (arrayList != null) {
            if (arrayList.get(0).intValue() != 0) {
                int i = 0;
                for (int i2 = 0; i2 < 14; i2++) {
                    int intValue = arrayList.get(i2).intValue();
                    if (intValue != 0) {
                        i++;
                        this.k.add(Integer.valueOf(intValue));
                    }
                }
                for (int i3 = 0; i3 < i; i3++) {
                    this.n.add(Integer.valueOf(arrayList.get(i3 + 14).intValue()));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        if (this.f) {
            return;
        }
        this.f = true;
        this.s.removeMessages(2);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", i);
            jSONObject.put("result_code", 0);
            this.t.d(100000, jSONObject.toString());
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "sendToServer JSONException");
        }
    }

    private String d(List<Integer> list) {
        StringBuffer stringBuffer = new StringBuffer(16);
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            stringBuffer.append(String.valueOf(it.next()));
            stringBuffer.append(",");
        }
        if (stringBuffer.toString().contains(",")) {
            stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(","));
        }
        return stringBuffer.toString();
    }

    private String a(List<Number> list) {
        StringBuffer stringBuffer = new StringBuffer(16);
        Iterator<Number> it = list.iterator();
        while (it.hasNext()) {
            stringBuffer.append(String.valueOf(it.next().intValue()));
            stringBuffer.append(",");
        }
        if (stringBuffer.toString().contains(",")) {
            stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(","));
        }
        return stringBuffer.toString();
    }

    private void c(int i, long j) {
        LogUtil.a("Opera_StressService", "startStressTimeOutWaiting(), enter.");
        long j2 = j + 8;
        if (this.s != null) {
            LogUtil.a("Opera_StressService", "startStressTimeOutWaiting(), open type is: ", Integer.valueOf(i), " timeout: ", Long.valueOf(j2));
            if (i == 1) {
                this.s.sendEmptyMessage(10001, j2 * 1000);
                return;
            }
            if (i == 4) {
                this.s.sendEmptyMessage(10004, j2 * 1000);
                return;
            }
            if (i == 9) {
                this.s.sendEmptyMessage(10002, j2 * 1000);
                return;
            } else if (i == 12) {
                this.s.sendEmptyMessage(10003, j2 * 1000);
                return;
            } else {
                LogUtil.a("Opera_StressService", "startStressTimeOutWaiting() unknown type.");
                return;
            }
        }
        LogUtil.b("Opera_StressService", "startStressTimeOutWaiting() mStressErrorHandler is null");
    }

    private void c(int i) {
        LogUtil.a("Opera_StressService", "cancelStressTimeOutWaiting() time out type: ", Integer.valueOf(i));
        ExtendHandler extendHandler = this.s;
        if (extendHandler != null) {
            extendHandler.removeMessages(10001);
            this.s.removeMessages(10002);
            this.s.removeMessages(10003);
            this.s.removeMessages(10004);
            this.s.removeMessages(10005);
            this.s.removeMessages(i);
            return;
        }
        LogUtil.b("Opera_StressService", "cancelStressTimeOutWaiting() mStressErrorHandler is null");
    }

    public void b(int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "stressControl() enter. type: ", Integer.valueOf(i), ", duration: ", Integer.valueOf(i2));
        if (i2 < 0 || i2 > 90) {
            LogUtil.b("Opera_StressService", "stressControl() invalid parameter");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(1001, null);
                return;
            }
            return;
        }
        if (iBaseResponseCallback == null) {
            LogUtil.b("Opera_StressService", "stressControl() callback is null.");
            return;
        }
        if (i == 1) {
            e(i2, iBaseResponseCallback);
            return;
        }
        if (i == 2) {
            h(iBaseResponseCallback);
        } else if (i == 3) {
            g(iBaseResponseCallback);
        } else {
            iBaseResponseCallback.d(1001, null);
        }
    }

    public void d(int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "relaxControl() enter. type: ", Integer.valueOf(i), ", duration:", Integer.valueOf(i2));
        if (i2 < 0 || i2 > 3600) {
            LogUtil.b("Opera_StressService", "relaxControl() invalid parameter");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(1001, null);
                return;
            }
            return;
        }
        if (iBaseResponseCallback == null) {
            LogUtil.b("Opera_StressService", "relaxControl() callback is null.");
            return;
        }
        if (i == 1) {
            b(i2, iBaseResponseCallback);
            return;
        }
        if (i == 2) {
            i(iBaseResponseCallback);
        } else if (i == 3) {
            j(iBaseResponseCallback);
        } else {
            iBaseResponseCallback.d(1001, null);
        }
    }

    public void d(int i, int i2, int i3, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "gameControl() enter. type: ", Integer.valueOf(i), ",duration: ", Integer.valueOf(i2), ",score: ", Integer.valueOf(i3));
        if (i2 < 0 || i2 > 3600 || i3 < 0) {
            LogUtil.b("Opera_StressService", "gameControl() invalid parameter");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(1001, null);
                return;
            }
            return;
        }
        if (iBaseResponseCallback == null) {
            LogUtil.b("Opera_StressService", "gameControl() callback is null.");
            return;
        }
        if (i == 1) {
            c(i2, iBaseResponseCallback);
            return;
        }
        if (i == 2) {
            d(i3, iBaseResponseCallback);
        } else if (i == 3) {
            c(iBaseResponseCallback);
        } else {
            iBaseResponseCallback.d(1001, null);
        }
    }

    public void a(int i, int i2, int i3, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_StressService", "calibrationControl() enter. type: ", Integer.valueOf(i), ",duration: ", Integer.valueOf(i2), ",score: ", Integer.valueOf(i3));
        if (i2 < 0 || i2 > 90 || i3 < 0) {
            LogUtil.b("Opera_StressService", "calibrationControl() invalid parameter");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(1001, null);
                return;
            }
            return;
        }
        if (iBaseResponseCallback == null) {
            LogUtil.b("Opera_StressService", "calibrationControl() callback is null.");
            return;
        }
        if (i == 1) {
            e(i2, i3, iBaseResponseCallback);
            return;
        }
        if (i == 2) {
            f(iBaseResponseCallback);
        } else if (i == 3) {
            e(iBaseResponseCallback);
        } else {
            iBaseResponseCallback.d(1001, null);
        }
    }

    public void a(IBaseResponseCallback iBaseResponseCallback, boolean z) {
        LogUtil.a("Opera_StressService", "abort() enter.");
        this.j = z;
        if (iBaseResponseCallback == null) {
            LogUtil.a("Opera_StressService", "abort() callback is null. aborted by user clicking X isFromHeartKanban == " + z);
            if (!z) {
                cjx.e().b(this.h, this.q);
            }
            d();
            c();
        }
        LogUtil.a("Opera_StressService", "current stress scene is: ", Integer.valueOf(this.d));
        int i = this.d;
        if (i == 1) {
            g(iBaseResponseCallback);
            return;
        }
        if (i == 2) {
            j(iBaseResponseCallback);
            return;
        }
        if (i == 3) {
            c(iBaseResponseCallback);
            return;
        }
        if (i == 4) {
            e(iBaseResponseCallback);
        } else if (i == 5) {
            LogUtil.a("Opera_StressService", "abort() called when state is idle.");
        } else {
            LogUtil.a("Opera_StressService", "state error.");
        }
    }

    private void a() {
        if (this.j) {
            return;
        }
        cjx.e().e(this.h, this.q);
    }

    private JSONObject i() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("listtime", d(this.k));
            jSONObject.put("listrri", d(this.n));
            jSONObject.put("productId", this.h);
        } catch (JSONException unused) {
            LogUtil.b("Opera_StressService", "getRriJson JSONException");
        }
        return jSONObject;
    }

    public int b() {
        if (this.e == null) {
            this.e = (AudioManager) BaseApplication.getContext().getSystemService(PresenterUtils.AUDIO);
            this.b = new ComponentName(BaseApplication.getContext().getPackageName(), a.class.getName());
        }
        this.e.registerMediaButtonEventReceiver(this.b);
        if (this.c == null) {
            this.c = new AudioManager.OnAudioFocusChangeListener() { // from class: kxc.5
                @Override // android.media.AudioManager.OnAudioFocusChangeListener
                public void onAudioFocusChange(int i) {
                }
            };
        }
        return this.e.requestAudioFocus(this.c, 3, 2);
    }

    public void d() {
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
        AudioManager audioManager = this.e;
        if (audioManager != null && (onAudioFocusChangeListener = this.c) != null) {
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
        AudioManager audioManager2 = this.e;
        if (audioManager2 != null) {
            audioManager2.unregisterMediaButtonEventReceiver(this.b);
        }
    }

    static class b {
        private static final kxc d = new kxc();
    }

    static class e extends BaseHandlerCallback<kxc> {
        e(kxc kxcVar) {
            super(kxcVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandlerCallback
        /* renamed from: bSn_, reason: merged with bridge method [inline-methods] */
        public boolean handleMessageWhenReferenceNotNull(kxc kxcVar, Message message) {
            LogUtil.b("Opera_StressService", "mStressErrorHandler, error is: ", Integer.valueOf(message.what));
            switch (message.what) {
                case 10001:
                    kxcVar.g(kxcVar.m);
                    return true;
                case 10002:
                    kxcVar.j(kxcVar.m);
                    return true;
                case 10003:
                    kxcVar.c(kxcVar.m);
                    return true;
                case 10004:
                    kxcVar.e(kxcVar.m);
                    return true;
                case 10005:
                    LogUtil.b("Opera_StressService", "mStressErrorHandler, last received json to sent to H5 is: ", kxcVar.l.toString());
                    if (kxcVar.p != null) {
                        LogUtil.a("Opera_StressService", "send to h5");
                        kxcVar.p.d(0, kxcVar.l.toString());
                        if (kxcVar.e(kxcVar.l.optInt("type"))) {
                            kxcVar.p = null;
                        }
                    }
                    kxcVar.g = false;
                    kxcVar.l = new JSONObject();
                    return true;
                default:
                    LogUtil.b("Opera_StressService", "mStressErrorHandler, unknown error code");
                    return false;
            }
        }
    }

    class d extends BroadcastReceiver {
        private d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("Opera_StressService", "onReceive intent is null");
                return;
            }
            LogUtil.a("Opera_StressService", "onReceive action = ", intent.getAction());
            if (kxc.this.d == 1 || kxc.this.d == 4) {
                return;
            }
            kxc kxcVar = kxc.this;
            kxcVar.a(kxcVar.p, false);
        }
    }
}
