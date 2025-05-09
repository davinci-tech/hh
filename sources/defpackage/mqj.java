package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.R;
import com.huawei.health.pluginhealthzone.UserInfoCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.pluginhealthzone.cloud.HttpDataCallback;
import com.huawei.pluginhealthzone.interactors.AbnormalDataInformationCallback;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import com.huawei.ui.main.stories.healthzone.util.HealthZoneUserManager;
import defpackage.exh;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mqj {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f15112a = new Object();

    private static int c(int i, int i2) {
        if (i == 3) {
            return 3;
        }
        if (i == 1 && i2 == 2) {
            return 3;
        }
        if (i == 2 && i2 == 1) {
            return 3;
        }
        return i == 0 ? i2 : i;
    }

    private mqj() {
    }

    public static void c(JSONObject jSONObject, final AbnormalDataInformationCallback abnormalDataInformationCallback) {
        try {
            final LongSparseArray longSparseArray = new LongSparseArray(10);
            JSONArray jSONArray = jSONObject.getJSONArray("abnormalData");
            for (int i = 0; i < jSONArray.length(); i++) {
                cne_((mqo) new Gson().fromJson(jSONArray.get(i).toString(), mqo.class), longSparseArray);
            }
            mpq.d().getMyFollowRelations(null, new HttpDataCallback() { // from class: mqj.2
                @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
                public void onFailure(int i2, String str) {
                    AbnormalDataInformationCallback.this.onFailure(i2, str);
                }

                @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
                public void onSuccess(JSONObject jSONObject2) {
                    mqj.cng_(jSONObject2, longSparseArray, AbnormalDataInformationCallback.this);
                }
            });
        } catch (JsonSyntaxException | JSONException e) {
            LogUtil.b("HealthZonePushUtils", "getAbnormalDataString", LogAnonymous.b(e));
            abnormalDataInformationCallback.onFailure(1, LogAnonymous.b(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cng_(JSONObject jSONObject, LongSparseArray<mqc> longSparseArray, AbnormalDataInformationCallback abnormalDataInformationCallback) {
        try {
            cnj_(jSONObject, longSparseArray, abnormalDataInformationCallback);
            ArrayList arrayList = new ArrayList(10);
            if (longSparseArray.size() == 0) {
                LogUtil.h("HealthZonePushUtils", "No abnormal information");
                abnormalDataInformationCallback.onSuccess(arrayList);
            }
        } catch (JSONException e) {
            abnormalDataInformationCallback.onFailure(1, LogAnonymous.b((Throwable) e));
        }
    }

    private static void cnh_(long j, final List<mqc> list, final LongSparseArray<mqc> longSparseArray, final AbnormalDataInformationCallback abnormalDataInformationCallback) {
        if (longSparseArray == null) {
            LogUtil.h("HealthZonePushUtils", "getUserDataFromSocial abnormalDataMap is null");
            return;
        }
        final mqc mqcVar = longSparseArray.get(j);
        if (mqcVar == null) {
            LogUtil.h("HealthZonePushUtils", "getUserDataFromSocial abnormalDataInformation is null");
        } else {
            HealthZoneUserManager.d().e(2, String.valueOf(j), new UserInfoCallback<exh.b>() { // from class: mqj.1
                @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void infoCallback(exh.b bVar) {
                    if (bVar == null) {
                        LogUtil.h("HealthZonePushUtils", "getUserDataFromSocial user is null.");
                    } else {
                        String g = mqc.this.g();
                        String h = mqc.this.h();
                        String d = bVar.d();
                        if (BaseApplication.getContext().getString(R.string._2130840363_res_0x7f020b2b).equals(g) || TextUtils.isEmpty(g)) {
                            if (!TextUtils.isEmpty(d)) {
                                mqc.this.e(d);
                            } else {
                                LogUtil.a("HealthZonePushUtils", "getUserDataFromSocial user has no nickname.");
                                mqc.this.e(CommonUtil.m(h));
                            }
                        }
                    }
                    mqj.cnk_(mqc.this, list, longSparseArray, abnormalDataInformationCallback);
                }

                @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
                public void errorCallback(int i) {
                    LogUtil.b("HealthZonePushUtils", "getUserDataFromSocial errorCode is ", Integer.valueOf(i));
                }
            });
        }
    }

    private static void cnj_(JSONObject jSONObject, LongSparseArray<mqc> longSparseArray, AbnormalDataInformationCallback abnormalDataInformationCallback) throws JSONException, JsonSyntaxException {
        if (jSONObject == null) {
            LogUtil.h("HealthZonePushUtils", "setHealthZoneNickname data is null");
            return;
        }
        JSONArray jSONArray = jSONObject.getJSONArray("authRelations");
        ArrayList arrayList = new ArrayList(10);
        for (int i = 0; i < jSONArray.length(); i++) {
            mqc mqcVar = (mqc) new Gson().fromJson(jSONArray.getString(i), mqc.class);
            if (longSparseArray.get(mqcVar.d()) != null) {
                mqc mqcVar2 = longSparseArray.get(mqcVar.d());
                if (mqcVar2 == null) {
                    LogUtil.h("HealthZonePushUtils", "abnormalDataInformation is null");
                    return;
                }
                long d = mqcVar2.d();
                String g = mqcVar.g();
                longSparseArray.get(mqcVar.d()).d(TextUtils.isEmpty(mqcVar.h()) ? "" : mqcVar.h());
                if (!TextUtils.isEmpty(g)) {
                    mqcVar2.e(g);
                    cnk_(mqcVar2, arrayList, longSparseArray, abnormalDataInformationCallback);
                } else {
                    cnh_(d, arrayList, longSparseArray, abnormalDataInformationCallback);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cnk_(final mqc mqcVar, final List<mqc> list, final LongSparseArray<mqc> longSparseArray, final AbnormalDataInformationCallback abnormalDataInformationCallback) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: mqi
            @Override // java.lang.Runnable
            public final void run() {
                mqj.cni_(mqc.this, list, longSparseArray, abnormalDataInformationCallback);
            }
        });
    }

    static /* synthetic */ void cni_(mqc mqcVar, List list, LongSparseArray longSparseArray, AbnormalDataInformationCallback abnormalDataInformationCallback) {
        e(mqcVar);
        synchronized (f15112a) {
            list.add(mqcVar);
            if (list.size() == longSparseArray.size()) {
                abnormalDataInformationCallback.onSuccess(list);
            }
        }
    }

    private static void cne_(mqo mqoVar, LongSparseArray<mqc> longSparseArray) {
        long d = mqoVar.d();
        Gson gson = new Gson();
        mqf mqfVar = (mqf) gson.fromJson(CommonUtil.z(mqoVar.b()), mqf.class);
        int c = mqoVar.c();
        if (longSparseArray.get(d) != null) {
            mqc mqcVar = longSparseArray.get(d);
            c(mqoVar, mqcVar);
            SparseArray<mqo> cmW_ = mqcVar.cmW_();
            SparseIntArray cmY_ = mqcVar.cmY_();
            if (cmW_ == null || cmY_ == null) {
                LogUtil.h("HealthZonePushUtils", "constructAbnormalDataMap firstPushRecordSdsDaoBeanSparseArray is null");
                return;
            }
            mqo mqoVar2 = cmW_.get(c);
            cmY_.append(c, cmY_.get(c) + 1);
            mqcVar.cnb_(cmY_);
            long c2 = mqfVar.c();
            if (mqoVar2 == null || ((mqf) gson.fromJson(CommonUtil.z(mqoVar2.b()), mqf.class)).c() > c2) {
                cmW_.append(c, mqoVar);
                mqcVar.cmZ_(cmW_);
            }
            SparseArray<mqo> cmX_ = mqcVar.cmX_();
            if (cmX_ == null) {
                LogUtil.h("HealthZonePushUtils", "constructAbnormalDataMap lastPushRecordSdsDaoBeanSparseArray is null");
                return;
            }
            mqo mqoVar3 = cmX_.get(c);
            if (mqoVar3 == null || ((mqf) gson.fromJson(CommonUtil.z(mqoVar3.b()), mqf.class)).c() < c2) {
                cmX_.append(c, mqoVar);
                mqcVar.cna_(cmX_);
            }
            longSparseArray.append(d, mqcVar);
            return;
        }
        cnf_(mqoVar, d, c, longSparseArray);
    }

    private static void cnf_(mqo mqoVar, long j, int i, LongSparseArray<mqc> longSparseArray) {
        mqc mqcVar = new mqc();
        c(mqoVar, mqcVar);
        mqcVar.d(j);
        SparseArray<mqo> sparseArray = new SparseArray<>(10);
        SparseArray<mqo> sparseArray2 = new SparseArray<>(10);
        SparseIntArray sparseIntArray = new SparseIntArray(10);
        sparseArray.append(i, mqoVar);
        sparseArray2.append(i, mqoVar);
        sparseIntArray.append(i, 1);
        mqcVar.cmZ_(sparseArray);
        mqcVar.cna_(sparseArray2);
        mqcVar.cnb_(sparseIntArray);
        longSparseArray.append(j, mqcVar);
    }

    private static void c(mqo mqoVar, mqc mqcVar) {
        try {
            JSONObject jSONObject = new JSONObject(((mqf) new Gson().fromJson(CommonUtil.z(mqoVar.b()), mqf.class)).e());
            switch (mqoVar.c()) {
                case 24:
                    mqcVar.a(d(jSONObject.getDouble("BLOOD_PRESSURE_DIASTOLIC"), jSONObject.getDouble("BLOOD_PRESSURE_SYSTOLIC"), mqcVar.e()));
                    break;
                case 25:
                    mqcVar.e(b(jSONObject.getDouble(e(25, jSONObject)), mqcVar.a(), 7.0d, 4.4d));
                    break;
                case 26:
                    mqcVar.e(b(jSONObject.getDouble(e(26, jSONObject)), mqcVar.a(), 10.0d, 4.4d));
                    break;
                case 27:
                    mqcVar.e(b(jSONObject.getDouble(e(27, jSONObject)), mqcVar.a(), 7.0d, 3.9d));
                    break;
            }
        } catch (JsonSyntaxException | JSONException e) {
            LogUtil.b("HealthZonePushUtils", "setPressureOrSugarStatus:", LogAnonymous.b(e));
        }
    }

    private static int d(double d, double d2, int i) {
        return c(i, (d >= 90.000001d || d2 >= 140.000001d) ? 1 : (d < 60.000001d || d2 < 90.000001d) ? 2 : 0);
    }

    private static int b(double d, int i, double d2, double d3) {
        return c(i, d > d2 ? 1 : d < d3 ? 2 : 0);
    }

    private static void e(mqc mqcVar) {
        mqo mqoVar;
        mqo mqoVar2;
        synchronized (mqj.class) {
            Context context = BaseApplication.getContext();
            if (context == null) {
                LogUtil.b("HealthZonePushUtils", "BaseApplication context is null");
                return;
            }
            SparseArray<mqo> cmW_ = mqcVar.cmW_();
            SparseArray<mqo> cmX_ = mqcVar.cmX_();
            String g = mqcVar.g();
            if (g == null) {
                LogUtil.h("HealthZonePushUtils", "nickname is null because huid is not in the list");
                return;
            }
            if (cmW_ == null || cmX_ == null) {
                LogUtil.h("HealthZonePushUtils", "constructAbnormalDataString firstPushRecordList or lastPushRecordList is null");
                return;
            }
            if (mqcVar.cmY_() == null) {
                LogUtil.h("HealthZonePushUtils", "constructAbnormalDataString pushRecordCount is null");
                return;
            }
            ArrayList arrayList = new ArrayList(10);
            for (int i = 21; i <= 39; i++) {
                try {
                    mqoVar = cmW_.get(i);
                    mqoVar2 = cmX_.get(i);
                } catch (JsonSyntaxException e) {
                    LogUtil.b("HealthZonePushUtils", "constructAbnormalDataString:", LogAnonymous.b((Throwable) e));
                }
                if (mqoVar != null && mqoVar2 != null) {
                    String d = d(context, i, g);
                    if (!TextUtils.isEmpty(d)) {
                        arrayList.add(d);
                    }
                }
                LogUtil.h("HealthZonePushUtils", "firstRecord or lastRecord is null");
            }
            mqcVar.b(arrayList);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static String d(Context context, int i, String str) {
        char c;
        LogUtil.a("HealthZonePushUtils", "constructAbnormalDataString pushType is ", Integer.valueOf(i));
        String valueOf = String.valueOf(i);
        valueOf.hashCode();
        switch (valueOf.hashCode()) {
            case 1599:
                if (valueOf.equals("21")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case KakaConstants.TASK_MEASURE_TODAY_BLOOD_PRESSURE /* 1600 */:
                if (valueOf.equals("22")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1601:
                if (valueOf.equals("23")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1602:
                if (valueOf.equals("24")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1603:
                if (valueOf.equals("25")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1604:
                if (valueOf.equals("26")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1605:
                if (valueOf.equals(HealthZonePushReceiver.BLOOD_SUGAR_DAWN_NOTIFY)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1606:
                if (valueOf.equals("28")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return String.format(Locale.ROOT, context.getResources().getString(R.string._2130840376_res_0x7f020b38), str);
            case 1:
                return String.format(Locale.ROOT, context.getResources().getString(R.string._2130840376_res_0x7f020b38), str);
            case 2:
                return String.format(Locale.ROOT, context.getResources().getString(R.string._2130840382_res_0x7f020b3e), str);
            case 3:
                return String.format(Locale.ROOT, context.getResources().getString(R.string._2130840378_res_0x7f020b3a), str);
            case 4:
            case 5:
            case 6:
                return String.format(Locale.ROOT, context.getResources().getString(R.string._2130840381_res_0x7f020b3d), str);
            case 7:
                return String.format(Locale.ROOT, context.getResources().getString(R.string.IDS_step_reminder), str);
            default:
                return e(context, i, str);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static String e(Context context, int i, String str) {
        char c;
        String valueOf = String.valueOf(i);
        valueOf.hashCode();
        int hashCode = valueOf.hashCode();
        if (hashCode == 1607) {
            if (valueOf.equals(HealthZonePushReceiver.SLEEP_TIME_NOTIFY)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 1629) {
            switch (hashCode) {
                case 1631:
                    if (valueOf.equals(HealthZonePushReceiver.PRESSURE_NOTIFY)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1632:
                    if (valueOf.equals(HealthZonePushReceiver.CYCLE_BLOOD_OXYGEN_NOTIFY)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1633:
                    if (valueOf.equals(HealthZonePushReceiver.BODY_TEMPERATURE_HIGH_NOTIFY)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1634:
                    if (valueOf.equals(HealthZonePushReceiver.BODY_TEMPERATURE_LOW_NOTIFY)) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 1635:
                    if (valueOf.equals(HealthZonePushReceiver.DATA_POST_COMMENTS_NOTIFY)) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (valueOf.equals(HealthZonePushReceiver.SLEEP_SCORE_NOTIFY)) {
                c = 1;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                return String.format(Locale.ROOT, context.getResources().getString(R.string._2130840375_res_0x7f020b37), str);
            case 1:
                return String.format(Locale.ROOT, context.getResources().getString(R.string._2130840374_res_0x7f020b36), str);
            case 2:
                return String.format(Locale.ROOT, context.getResources().getString(R.string._2130840377_res_0x7f020b39), str);
            case 3:
                return String.format(Locale.ROOT, context.getResources().getString(R.string._2130840382_res_0x7f020b3e), str);
            case 4:
            case 5:
                return String.format(Locale.ROOT, context.getResources().getString(R.string._2130840380_res_0x7f020b3c), str);
            case 6:
                return String.format(Locale.ROOT, context.getResources().getString(R.string._2130840399_res_0x7f020b4f), str);
            default:
                return "";
        }
    }

    private static String e(int i, JSONObject jSONObject) {
        if (jSONObject == null) {
            return "";
        }
        switch (i) {
            case 25:
                if (!jSONObject.has("BLOOD_SUGAR_BF_BEFORE")) {
                    if (!jSONObject.has("BLOOD_SUGAR_LC_BEFORE")) {
                        if (jSONObject.has("BLOOD_SUGAR_DN_BEFORE")) {
                        }
                    }
                }
                break;
            case 26:
                if (!jSONObject.has("BLOOD_SUGAR_BF_AFTER")) {
                    if (!jSONObject.has("BLOOD_SUGAR_LC_AFTER")) {
                        if (!jSONObject.has("BLOOD_SUGAR_DN_AFTER")) {
                            if (jSONObject.has("BLOOD_SUGAR_SL_BEFORE")) {
                            }
                        }
                    }
                }
                break;
            case 27:
                if (jSONObject.has("BLOOD_SUGAR_BEFORE_DAWN")) {
                }
                break;
        }
        return "";
    }
}
