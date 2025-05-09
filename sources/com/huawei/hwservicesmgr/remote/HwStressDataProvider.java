package com.huawei.hwservicesmgr.remote;

import android.content.Context;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import defpackage.jrq;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class HwStressDataProvider {
    private static volatile boolean c = false;
    private static HwStressDataProvider d;
    private static final Object e = new Object();
    private IBaseResponseCallback g;
    private int h;
    private int j;
    private int k;
    private int n;
    private int p;
    private int r;

    /* renamed from: a, reason: collision with root package name */
    private float[] f6398a = new float[18];
    private float[] b = new float[10];
    private int f = 0;
    private int i = 0;
    private float o = 0.0f;
    private float m = 100.0f;
    private float l = 0.0f;

    private boolean a(int i, int i2, int i3) {
        return i >= 0 && i2 >= 0 && i3 >= 0;
    }

    private boolean d(int i, int i2) {
        return i >= 0 && i2 >= 0;
    }

    private static native float[] getGameResultFromAlgorithm(int i, int i2, int i3, int i4, int i5, int[] iArr, int[] iArr2);

    private static native float[] getRelaxResultFromAlgorithm(int i, int i2, int i3, int i4, int i5, int[] iArr, int[] iArr2);

    private static native float[] getStressResultFromAlgorithm(float[] fArr, int i, int i2, int[] iArr, int[] iArr2);

    private HwStressDataProvider() {
    }

    public static HwStressDataProvider c() {
        HwStressDataProvider hwStressDataProvider;
        synchronized (e) {
            if (d == null) {
                d = new HwStressDataProvider();
            }
            hwStressDataProvider = d;
        }
        return hwStressDataProvider;
    }

    private static void e() {
        if (c) {
            LogUtil.h("HwStressDataProvider", "loadSo() so file is loaded");
            return;
        }
        try {
            System.loadLibrary("DetailStressJni");
            LogUtil.a("HwStressDataProvider", "load detail stress jni success");
        } catch (UnsatisfiedLinkError unused) {
            LogUtil.b("HwStressDataProvider", "load detail stress jni UnsatisfiedLinkError");
        }
    }

    public void b(int i, JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwStressDataProvider", "getStressResult(), enter getStressResult()");
        this.p = i;
        LogUtil.a("HwStressDataProvider", "getStressResult(), type:", Integer.valueOf(i));
        this.g = iBaseResponseCallback;
        switch (i) {
            case 1:
                LogUtil.a("HwStressDataProvider", "getStressResult(), enter STRESS_OPEN");
                g(jSONObject);
                break;
            case 2:
                LogUtil.a("HwStressDataProvider", "getStressResult(), enter STRESS_CLOSE");
                i(jSONObject);
                break;
            case 3:
            case 6:
            case 7:
            case 11:
            default:
                LogUtil.h("HwStressDataProvider", "getStressResult(), enter default type");
                break;
            case 4:
                LogUtil.a("HwStressDataProvider", "getStressResult(), enter STRESS_CALIBRATION_OPEN");
                f(jSONObject);
                break;
            case 5:
                LogUtil.a("HwStressDataProvider", "getStressResult(), enter STRESS_CALIBRATION_CLOSE");
                d(jSONObject);
                break;
            case 8:
                LogUtil.a("HwStressDataProvider", "getStressResult(), enter STRESS_CALIBRATION_RESET");
                a();
                break;
            case 9:
                LogUtil.a("HwStressDataProvider", "getStressResult(), enter RELAX_OPEN");
                a(jSONObject);
                break;
            case 10:
                LogUtil.a("HwStressDataProvider", "getStressResult(), enter RELAX_CLOSE");
                b(jSONObject);
                break;
            case 12:
                LogUtil.a("HwStressDataProvider", "getStressResult(), enter GAME_OPEN");
                e(jSONObject);
                break;
            case 13:
                LogUtil.a("HwStressDataProvider", "getStressResult(), enter GAME_CLOSE");
                c(jSONObject);
                break;
        }
    }

    public void c(final int[] iArr, final int[] iArr2) {
        LogUtil.a("HwStressDataProvider", "processRrData(), enter processRrData()");
        if (iArr == null || iArr2 == null || iArr.length == 0 || iArr2.length == 0) {
            LogUtil.h("HwStressDataProvider", "processRrData(), rrData is null");
            d(100);
        } else {
            final int length = iArr.length;
            LogUtil.a("HwStressDataProvider", "processRrData(), rrLength:", Integer.valueOf(length), ", rrData[0]:", Integer.valueOf(iArr[0]), ", rrData[rrLength-1]:", Integer.valueOf(iArr[length - 1]));
            jrq.b("HwStressDataProvider", new Runnable() { // from class: com.huawei.hwservicesmgr.remote.HwStressDataProvider.4
                @Override // java.lang.Runnable
                public void run() {
                    for (int i = 0; i < length; i++) {
                        LogUtil.a("HwStressDataProvider", "processRrData(), rrData[", Integer.valueOf(i), "]:", Integer.valueOf(iArr[i]), ", timestamp[", Integer.valueOf(i), "]:", Integer.valueOf(iArr2[i]));
                    }
                    int i2 = HwStressDataProvider.this.p;
                    if (i2 == 2) {
                        LogUtil.a("HwStressDataProvider", "processRrData(), enter STRESS_CLOSE");
                        HwStressDataProvider.this.a(length, iArr, iArr2);
                        return;
                    }
                    if (i2 == 5) {
                        LogUtil.a("HwStressDataProvider", "processRrData(), enter STRESS_CALIBRATION_CLOSE");
                        HwStressDataProvider.this.d(length, iArr, iArr2);
                    } else if (i2 == 10) {
                        LogUtil.a("HwStressDataProvider", "processRrData(), enter RELAX_CLOSE");
                        HwStressDataProvider.this.e(length, iArr, iArr2);
                    } else if (i2 == 13) {
                        LogUtil.a("HwStressDataProvider", "processRrData(), enter GAME_CLOSE");
                        HwStressDataProvider.this.c(length, iArr, iArr2);
                    } else {
                        LogUtil.h("HwStressDataProvider", "processRrData(), enter default type");
                    }
                }
            });
        }
    }

    private void c(JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("HwStressDataProvider", "gameClosePreProcess(), value is null");
            return;
        }
        try {
            this.k = jSONObject.getInt("duration");
            this.j = jSONObject.getInt(JsUtil.SCORE);
            LogUtil.a("HwStressDataProvider", "gameClosePreProcess(), mSignalTime:", Integer.valueOf(this.k), ", mGameScore:", Integer.valueOf(this.j));
        } catch (JSONException unused) {
            LogUtil.b("HwStressDataProvider", "gameClosePreProcess() JSONException");
        }
    }

    private void e(JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("HwStressDataProvider", "gameOpenPreProcess(), value is null");
            return;
        }
        try {
            int i = jSONObject.getInt("max_duration");
            this.f = i;
            LogUtil.a("HwStressDataProvider", "gameOpenPreProcess(), mMaxDuration:", Integer.valueOf(i));
        } catch (JSONException unused) {
            LogUtil.b("HwStressDataProvider", "gameOpenPreProcess() JSONException");
        }
    }

    private void b(JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("HwStressDataProvider", "relaxClosePreProcess(), value is null");
            return;
        }
        try {
            int i = jSONObject.getInt("duration");
            this.k = i;
            LogUtil.a("HwStressDataProvider", "relaxClosePreProcess(), mSignalTime:", Integer.valueOf(i));
        } catch (JSONException unused) {
            LogUtil.b("HwStressDataProvider", "relaxClosePreProcess() JSONException");
        }
    }

    private void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("HwStressDataProvider", "relaxOpenPreProcess(), value is null");
            return;
        }
        try {
            int i = jSONObject.getInt("max_duration");
            this.f = i;
            LogUtil.a("HwStressDataProvider", "relaxOpenPreProcess(), mMaxDuration:", Integer.valueOf(i));
        } catch (JSONException unused) {
            LogUtil.b("HwStressDataProvider", "relaxOpenPreProcess() JSONException");
        }
    }

    private void a() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(25), "calibration_flag");
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(25), "calibration_result");
        if (b != null && !b.isEmpty()) {
            SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(25), "calibration_flag");
        }
        if (b2 != null && !b2.isEmpty()) {
            SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(25), "calibration_result");
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", 8);
            jSONObject.put("result_code", 0);
        } catch (JSONException unused) {
            LogUtil.b("HwStressDataProvider", "stressCalibrationResetPreProcess() JSONException");
        }
        this.g.d(0, jSONObject.toString());
        LogUtil.a("HwStressDataProvider", "stressCalibrationResetPreProcess(), success!");
    }

    private void d(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                int i = jSONObject.getInt("duration");
                this.k = i;
                LogUtil.a("HwStressDataProvider", "stressCalibrationClosePreProcess(), mSignalTime:", Integer.valueOf(i));
            } catch (JSONException unused) {
                LogUtil.b("HwStressDataProvider", "stressCalibrationClosePreProcess() JSONException");
            }
        }
        for (int i2 = 0; i2 < 16; i2++) {
            this.f6398a[i2] = 0.0f;
        }
        float[] fArr = this.f6398a;
        fArr[16] = this.i;
        fArr[17] = 2.0f;
    }

    private void f(JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("HwStressDataProvider", "stressCalibrationOpenPreProcess() value is null");
            return;
        }
        try {
            this.i = jSONObject.getInt(JsUtil.SCORE);
            this.f = jSONObject.getInt("max_duration");
            LogUtil.a("HwStressDataProvider", "stressCalibrationOpenPreProcess(), mCalibrationScore:", Integer.valueOf(this.i), ", mMaxDuration:", Integer.valueOf(this.f));
        } catch (JSONException unused) {
            LogUtil.b("HwStressDataProvider", "stressCalibrationOpenPreProcess() JSONException");
        }
    }

    private void i(JSONObject jSONObject) {
        int i = 0;
        if (jSONObject != null) {
            try {
                int i2 = jSONObject.getInt("duration");
                this.k = i2;
                LogUtil.a("HwStressDataProvider", "stressClosePreProcess(), mSignalTime:", Integer.valueOf(i2));
            } catch (JSONException unused) {
                LogUtil.b("HwStressDataProvider", "stressClosePreProcess() JSONException");
            }
        }
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(25), "calibration_result");
        if (b == null || b.isEmpty()) {
            while (i < 14) {
                this.f6398a[i] = 0.0f;
                LogUtil.a("HwStressDataProvider", "stressClosePreProcess(), mCalibrationDataArray:", String.valueOf(0.0f));
                i++;
            }
        } else {
            String replace = b.replace("[", "").replace("]", "");
            LogUtil.a("HwStressDataProvider", "stressClosePreProcess(), calibrationResultString:", replace);
            String[] split = replace.split(",");
            if (split.length >= 10) {
                while (i < 10) {
                    try {
                        this.f6398a[i] = Float.parseFloat(split[i]);
                    } catch (NumberFormatException unused2) {
                        LogUtil.b("HwStressDataProvider", "stressClosePreProcess() NumberFormatException");
                    }
                    i++;
                }
            }
            for (int i3 = 10; i3 < 14; i3++) {
                this.f6398a[i3] = 0.0f;
            }
        }
        d();
    }

    private void d() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(25), "real_calib_score");
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(25), "max_score");
        String b3 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(25), "min_score");
        if (b != null) {
            try {
            } catch (NumberFormatException unused) {
                LogUtil.b("HwStressDataProvider", "stressClosePreProcess() NumberFormatException");
            }
            if (!b.isEmpty()) {
                this.l = Float.parseFloat(b);
                if (b2 == null && !b2.isEmpty()) {
                    this.o = Float.parseFloat(b2);
                } else {
                    this.o = 0.0f;
                }
                if (b3 == null && !b3.isEmpty()) {
                    this.m = Float.parseFloat(b3);
                } else {
                    this.m = 100.0f;
                }
                float[] fArr = this.f6398a;
                fArr[14] = this.o;
                fArr[15] = this.m;
                fArr[16] = this.l;
                fArr[17] = 0.0f;
            }
        }
        this.l = 0.0f;
        if (b2 == null) {
        }
        this.o = 0.0f;
        if (b3 == null) {
        }
        this.m = 100.0f;
        float[] fArr2 = this.f6398a;
        fArr2[14] = this.o;
        fArr2[15] = this.m;
        fArr2[16] = this.l;
        fArr2[17] = 0.0f;
    }

    private void g(JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("HwStressDataProvider", "stressOpenPreProcess() value is null");
            return;
        }
        try {
            int i = jSONObject.getInt("max_duration");
            this.f = i;
            LogUtil.a("HwStressDataProvider", "stressOpenPreProcess(), mMaxDuration:", Integer.valueOf(i));
        } catch (JSONException unused) {
            LogUtil.b("HwStressDataProvider", "stressOpenPreProcess() JSONException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, int[] iArr, int[] iArr2) {
        if (d(this.h, this.n) && d(this.k, i, this.j, iArr, iArr2)) {
            e();
            float[] gameResultFromAlgorithm = getGameResultFromAlgorithm(this.h, this.n, this.k, i, this.j, iArr, iArr2);
            if (gameResultFromAlgorithm != null && gameResultFromAlgorithm.length == 8) {
                LogUtil.a("HwStressDataProvider", "gameCloseProcess(), resultFromAlgorithm.length :", Integer.valueOf(gameResultFromAlgorithm.length));
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("type", 13);
                    jSONObject.put("flag", gameResultFromAlgorithm[7]);
                    jSONObject.put("final_score", gameResultFromAlgorithm[5]);
                    jSONObject.put("p_score", gameResultFromAlgorithm[4]);
                    jSONObject.put("d_score", gameResultFromAlgorithm[3]);
                    LogUtil.a("HwStressDataProvider", "gameCloseProcess(), type, flag, finalScore, pScore, dScore:", 13, ",", Float.valueOf(gameResultFromAlgorithm[7]), ",", Float.valueOf(gameResultFromAlgorithm[5]), ",", Float.valueOf(gameResultFromAlgorithm[4]), ",", Float.valueOf(gameResultFromAlgorithm[3]));
                    IBaseResponseCallback iBaseResponseCallback = this.g;
                    if (iBaseResponseCallback != null) {
                        iBaseResponseCallback.d(0, jSONObject.toString());
                    }
                } catch (JSONException unused) {
                    LogUtil.b("HwStressDataProvider", "gameCloseProcess() JSONException");
                }
                int length = gameResultFromAlgorithm.length;
                for (int i2 = 0; i2 < length; i2++) {
                    LogUtil.a("HwStressDataProvider", "gameCloseProcess(), gameFromAlgorithm[", Integer.valueOf(i2), "]:", Float.valueOf(gameResultFromAlgorithm[i2]));
                }
                return;
            }
            LogUtil.h("HwStressDataProvider", "gameCloseProcess(), resultFromAlgorithm is null!");
            d(101);
            return;
        }
        LogUtil.h("HwStressDataProvider", "gameCloseProcess() invalid gameParam!");
        d(100);
    }

    private boolean d(int i, int i2, int i3, int[] iArr, int[] iArr2) {
        return i >= 0 && i2 >= 0 && i3 >= 0 && iArr.length == iArr2.length;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, int[] iArr, int[] iArr2) {
        if (a(this.h, this.n, this.r) && d(this.k, i, iArr, iArr2)) {
            e();
            float[] relaxResultFromAlgorithm = getRelaxResultFromAlgorithm(this.h, this.n, this.r, this.k, i, iArr, iArr2);
            if (relaxResultFromAlgorithm != null && relaxResultFromAlgorithm.length == 23) {
                LogUtil.a("HwStressDataProvider", "relaxCloseProcessNew(), resultFromAlgorithm.length :", Integer.valueOf(relaxResultFromAlgorithm.length));
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("type", 10);
                    jSONObject.put("flag", relaxResultFromAlgorithm[20]);
                    jSONObject.put("grade", relaxResultFromAlgorithm[14]);
                    LogUtil.a("HwStressDataProvider", "relaxCloseProcessNew(), type, flag, grade :", 10, ",", Float.valueOf(relaxResultFromAlgorithm[20]), ",", Float.valueOf(relaxResultFromAlgorithm[14]));
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("bar_count", relaxResultFromAlgorithm[8]);
                    jSONObject2.put("codex_1", relaxResultFromAlgorithm[9]);
                    jSONObject2.put("codex_2", relaxResultFromAlgorithm[10]);
                    jSONObject2.put("codex_3", relaxResultFromAlgorithm[11]);
                    jSONObject2.put("codex_4", relaxResultFromAlgorithm[12]);
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(jSONObject2);
                    jSONObject.put("bar_codex", jSONArray);
                    IBaseResponseCallback iBaseResponseCallback = this.g;
                    if (iBaseResponseCallback != null) {
                        iBaseResponseCallback.d(0, jSONObject.toString());
                    }
                } catch (JSONException unused) {
                    LogUtil.b("HwStressDataProvider", "relaxCloseProcessNew() JSONException");
                }
                int length = relaxResultFromAlgorithm.length;
                for (int i2 = 0; i2 < length; i2++) {
                    LogUtil.a("HwStressDataProvider", "relaxCloseProcessNew(), relaxResultFromAlgorithm[", Integer.valueOf(i2), "]:,", Float.valueOf(relaxResultFromAlgorithm[i2]));
                }
                return;
            }
            LogUtil.h("HwStressDataProvider", "relaxCloseProcessNew(), resultFromAlgorithm is null!");
            d(101);
            return;
        }
        LogUtil.h("HwStressDataProvider", "relaxCloseProcessNew invalid relaxParam!");
        d(100);
    }

    private boolean d(int i, int i2, int[] iArr, int[] iArr2) {
        return i >= 0 && i2 >= 0 && iArr.length == iArr2.length;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, int[] iArr, int[] iArr2) {
        float[] fArr;
        if (e(this.f6398a, this.k, i, iArr, iArr2)) {
            e();
            try {
                fArr = getStressResultFromAlgorithm(this.f6398a, this.k, i, iArr, iArr2);
            } catch (UnsatisfiedLinkError unused) {
                LogUtil.b("HwStressDataProvider", "stressCalibrationCloseProcess load detail stress jni UnsatisfiedLinkError");
                fArr = null;
            }
            if (fArr != null && fArr.length == 19) {
                LogUtil.a("HwStressDataProvider", "stressCalibrationCloseProcess(), resultFromAlgorithm.length :", Integer.valueOf(fArr.length));
                c(fArr);
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("type", 5);
                    jSONObject.put("flag", fArr[18]);
                    jSONObject.put(JsUtil.SCORE, fArr[15]);
                    jSONObject.put("grade", fArr[16]);
                    LogUtil.a("HwStressDataProvider", "stressCalibrationCloseProcess(), type, flag, score, grade :", 5, ",", Float.valueOf(fArr[18]), ",", Float.valueOf(fArr[15]), ",", Float.valueOf(fArr[16]));
                    IBaseResponseCallback iBaseResponseCallback = this.g;
                    if (iBaseResponseCallback != null) {
                        iBaseResponseCallback.d(0, jSONObject.toString());
                    }
                } catch (JSONException unused2) {
                    LogUtil.b("HwStressDataProvider", "stressCalibrationCloseProcess() JSONException");
                }
                int length = fArr.length;
                for (int i2 = 0; i2 < length; i2++) {
                    LogUtil.a("HwStressDataProvider", "stressCalibrationCloseProcess(), stressCalibrationResultFromAlgorithm[", Integer.valueOf(i2), "]:", Float.valueOf(fArr[i2]));
                }
                return;
            }
            LogUtil.h("HwStressDataProvider", "stressCalibrationCloseProcess(), resultFromAlgorithm is null!");
            d(101);
            return;
        }
        LogUtil.h("HwStressDataProvider", "stressCalibrationCloseProcess invalid stressCalibrationParam!");
        d(100);
    }

    private void c(float[] fArr) {
        System.arraycopy(fArr, 0, this.b, 0, 10);
        if (((int) fArr[18]) == 1) {
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(25), "calibration_flag", String.valueOf(0), new StorageParams(0));
        }
        this.l = fArr[14];
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(25), "calibration_result", Arrays.toString(this.b), new StorageParams(0));
        Context context = BaseApplication.getContext();
        float f = this.l;
        SharedPreferenceManager.e(context, String.valueOf(25), "real_calib_score", String.valueOf(f), new StorageParams(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int[] iArr, int[] iArr2) {
        if (e(this.f6398a, this.k, i, iArr, iArr2)) {
            e();
            float[] stressResultFromAlgorithm = getStressResultFromAlgorithm(this.f6398a, this.k, i, iArr, iArr2);
            if (stressResultFromAlgorithm != null && stressResultFromAlgorithm.length == 19) {
                LogUtil.a("HwStressDataProvider", "stressCloseProcess(), resultFromAlgorithm.length :", Integer.valueOf(stressResultFromAlgorithm.length));
                b(stressResultFromAlgorithm);
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("type", 2);
                    jSONObject.put("flag", stressResultFromAlgorithm[18]);
                    jSONObject.put(JsUtil.SCORE, stressResultFromAlgorithm[15]);
                    jSONObject.put("grade", stressResultFromAlgorithm[16]);
                    LogUtil.a("HwStressDataProvider", "stressCloseProcess(), type, flag, score, grade :", 2, ",", Float.valueOf(stressResultFromAlgorithm[18]), ",", Float.valueOf(stressResultFromAlgorithm[15]), ",", Float.valueOf(stressResultFromAlgorithm[16]));
                    IBaseResponseCallback iBaseResponseCallback = this.g;
                    if (iBaseResponseCallback != null) {
                        iBaseResponseCallback.d(0, jSONObject.toString());
                    }
                } catch (JSONException unused) {
                    LogUtil.b("HwStressDataProvider", "stressCloseProcess JSONException");
                }
                int length = stressResultFromAlgorithm.length;
                for (int i2 = 0; i2 < length; i2++) {
                    LogUtil.a("HwStressDataProvider", "stressCloseProcess(), StressResultFromAlgorithm[", Integer.valueOf(i2), "]:", Float.valueOf(stressResultFromAlgorithm[i2]));
                }
                return;
            }
            LogUtil.h("HwStressDataProvider", "stressCloseProcess(), resultFromAlgorithm is null!");
            d(101);
            return;
        }
        LogUtil.h("HwStressDataProvider", "stressCloseProcess() invalid stressParam!");
        d(100);
    }

    private void b(float[] fArr) {
        if (((int) fArr[18]) == 1) {
            LogUtil.a("HwStressDataProvider", "stressCloseProcess(), Stress Algorithm Success!");
            float f = this.o;
            float f2 = fArr[14];
            if (f < f2) {
                this.o = f2;
                Context context = BaseApplication.getContext();
                float f3 = this.o;
                SharedPreferenceManager.e(context, String.valueOf(25), "max_score", String.valueOf(f3), new StorageParams(0));
            }
            float f4 = this.m;
            float f5 = fArr[14];
            if (f4 > f5) {
                this.m = f5;
                Context context2 = BaseApplication.getContext();
                float f6 = this.m;
                SharedPreferenceManager.e(context2, String.valueOf(25), "min_score", String.valueOf(f6), new StorageParams(0));
            }
        }
    }

    private void d(int i) {
        LogUtil.a("HwStressDataProvider", "invalidCallbackProcess(), errorFlagCode:", Integer.valueOf(i), ", type:", Integer.valueOf(this.p));
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.p);
            int i2 = this.p;
            if (i2 == 2 || i2 == 5) {
                jSONObject.put("flag", 0);
                jSONObject.put(JsUtil.SCORE, 0);
                jSONObject.put("grade", 0);
            } else if (i2 == 10) {
                jSONObject.put("flag", 0);
                jSONObject.put("grade", 0);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("bar_count", 0);
                jSONObject2.put("codex_1", 0);
                jSONObject2.put("codex_2", 0);
                jSONObject2.put("codex_3", 0);
                jSONObject2.put("codex_4", 0);
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(jSONObject2);
                jSONObject.put("bar_codex", jSONArray);
            } else if (i2 == 13) {
                jSONObject.put("flag", 0);
                jSONObject.put("final_score", 0);
                jSONObject.put("p_score", 0);
                jSONObject.put("d_score", 0);
            } else {
                LogUtil.h("HwStressDataProvider", "invalidCallbackProcess() default type");
            }
            if (this.g != null) {
                LogUtil.a("HwStressDataProvider", "invalidCallbackProcess(),", jSONObject.toString());
                this.g.d(0, jSONObject.toString());
            }
        } catch (JSONException unused) {
            LogUtil.b("HwStressDataProvider", "invalidCallbackProcess() JSONException");
        }
    }

    private boolean e(float[] fArr, int i, int i2, int[] iArr, int[] iArr2) {
        return fArr.length == 18 && i >= 0 && i2 >= 0 && iArr.length == iArr2.length;
    }
}
