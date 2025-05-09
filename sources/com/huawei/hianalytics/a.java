package com.huawei.hianalytics;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.framework.config.DeviceAttributeCollector;
import com.huawei.hianalytics.framework.config.EvtHeaderAttributeCollector;
import com.huawei.hianalytics.framework.config.ICollectorConfig;
import com.huawei.hianalytics.framework.config.RomAttributeCollector;
import com.huawei.openalliance.ad.constant.OsType;
import com.huawei.operation.utils.Constants;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class a implements ICollectorConfig {

    /* renamed from: a, reason: collision with root package name */
    public final l f3831a = i.a().m550a();

    /* renamed from: a, reason: collision with other field name */
    public final String f3a;

    @Override // com.huawei.hianalytics.framework.config.ICollectorConfig
    public long getAbsTime() {
        if (!q0.f3896a.f69a) {
            return 0L;
        }
        long j = i.a().m550a().c;
        if (j == 0) {
            j = j.a("global_v2", "public_key_abs_time", 0L);
            i.a().m550a().c = j;
        }
        if (Math.abs(j) > 604800000) {
            return j;
        }
        return 0L;
    }

    @Override // com.huawei.hianalytics.framework.config.ICollectorConfig
    public String getAppId() {
        e1 a2 = i.a().a(this.f3a);
        String str = a2 != null ? a2.f32a : "";
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        l lVar = this.f3831a;
        return TextUtils.isEmpty(lVar.g) ? lVar.f : lVar.g;
    }

    @Override // com.huawei.hianalytics.framework.config.ICollectorConfig
    public String getCollectUrl(String str) {
        a1 a2 = j.a(this.f3a, str);
        return a2 != null ? a2.f14c : "";
    }

    @Override // com.huawei.hianalytics.framework.config.ICollectorConfig
    public Map<String, String> getHttpHeader(String str) {
        HashMap hashMap = new HashMap();
        a1 a2 = j.a(this.f3a, str);
        Map<String, String> map = a2 != null ? a2.f8a : null;
        if (map != null) {
            hashMap.putAll(map);
        }
        return hashMap;
    }

    @Override // com.huawei.hianalytics.framework.config.ICollectorConfig
    public int getMetricPolicy(String str) {
        a1 a2 = j.a(this.f3a, str);
        if (a2 == null) {
            return 1;
        }
        return a2.c;
    }

    @Override // com.huawei.hianalytics.framework.config.ICollectorConfig
    public RomAttributeCollector getRomAttribute(String str, String str2) {
        Pair pair;
        String str3;
        Locale locale;
        a1 a2;
        f fVar = new f();
        l lVar = this.f3831a;
        int i = lVar.f3882a;
        int i2 = lVar.b;
        if (i2 == 0 || i == 0) {
            DisplayMetrics displayMetrics = EnvUtils.getAppContext().getResources().getDisplayMetrics();
            i = displayMetrics.heightPixels;
            i2 = displayMetrics.widthPixels;
            l lVar2 = this.f3831a;
            lVar2.f3882a = i;
            lVar2.b = i2;
        }
        m0 a3 = m0.a();
        String str4 = this.f3a;
        a3.getClass();
        e1 a4 = i.a().a(str4);
        String str5 = "";
        if ((a4 == null || (a2 = a4.a(str)) == null) ? false : a2.f9a) {
            String str6 = i.a().m550a().k;
            String str7 = i.a().m550a().l;
            if (TextUtils.isEmpty(str6) || TextUtils.isEmpty(str7)) {
                Context context = a3.f56a;
                if (Build.VERSION.SDK_INT > 28 || j.m562a(context, "android.permission.READ_PHONE_STATE")) {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    if (telephonyManager == null) {
                        pair = new Pair("", "");
                    } else if (telephonyManager.getSimState() != 5) {
                        pair = new Pair("", "");
                    } else {
                        String networkOperator = telephonyManager.getNetworkOperator();
                        pair = (TextUtils.isEmpty(networkOperator) || TextUtils.equals(networkOperator, Constants.NULL)) ? new Pair("", "") : networkOperator.length() > 3 ? new Pair(networkOperator.substring(0, 3), networkOperator.substring(3)) : new Pair("", "");
                    }
                } else {
                    HiLog.w("DeviceIdUtils", "pair value is empty");
                    pair = new Pair("", "");
                }
                i.a().m550a().k = (String) pair.first;
                i.a().m550a().l = (String) pair.second;
            } else {
                pair = new Pair(str6, str7);
            }
        } else {
            pair = new Pair("", "");
        }
        e1 a5 = i.a().a(this.f3a);
        String str8 = a5 != null ? a5.e : "";
        if (TextUtils.isEmpty(str8)) {
            fVar.i = "";
        } else {
            fVar.i = str8;
        }
        String b = j.b("com.huawei.android.os.SystemPropertiesEx", "ro.huawei.build.display.id", "");
        if (TextUtils.isEmpty(b)) {
            b = Build.DISPLAY;
        }
        fVar.f34a = b;
        if (TextUtils.isEmpty(str2)) {
            str2 = this.f3831a.h;
        }
        fVar.e = str2;
        String str9 = this.f3831a.f49a;
        if (str9 == null) {
            fVar.f35b = "";
        } else {
            fVar.f35b = str9;
        }
        fVar.h = "hianalytics";
        fVar.f = "3.2.13.501";
        fVar.c = Build.MODEL;
        fVar.d = this.f3831a.f;
        fVar.g = ("oper".equals(str) || "maint".equals(str)) ? j.a(m0.a().f56a, this.f3a, str) : "";
        a1 a6 = j.a(this.f3a, str);
        String str10 = a6 != null ? a6.f18e : "";
        if (TextUtils.isEmpty(str10)) {
            fVar.l = "";
        } else {
            fVar.l = str10;
        }
        fVar.q = (String) pair.first;
        fVar.r = (String) pair.second;
        e1 a7 = i.a().a(this.f3a);
        String str11 = a7 != null ? a7.f33b : "";
        if (TextUtils.isEmpty(str11)) {
            fVar.j = "";
        } else {
            fVar.j = str11;
        }
        e1 a8 = i.a().a(this.f3a);
        String str12 = a8 != null ? a8.c : "";
        if (TextUtils.isEmpty(str12)) {
            fVar.k = "";
        } else {
            fVar.k = str12;
        }
        fVar.n = Build.VERSION.RELEASE;
        fVar.f3851a = i;
        fVar.b = i2;
        Configuration configuration = EnvUtils.getAppContext().getResources().getConfiguration();
        if (configuration != null && (locale = configuration.locale) != null) {
            str5 = locale.toString();
        }
        fVar.p = str5;
        if (TextUtils.isEmpty(this.f3831a.u)) {
            str3 = OsType.ANDROID;
        } else {
            fVar.o = this.f3831a.u;
            str3 = "harmony";
        }
        fVar.m = str3;
        return fVar;
    }

    @Override // com.huawei.hianalytics.framework.config.ICollectorConfig
    public boolean getShortLinkEnabled(String str) {
        a1 a2 = j.a(this.f3a, str);
        if (a2 == null) {
            return false;
        }
        return a2.f17d;
    }

    @Override // com.huawei.hianalytics.framework.config.ICollectorConfig
    public boolean isEnableSession(String str) {
        a1 a2 = j.a(this.f3a, str);
        if (a2 != null) {
            return a2.f12b;
        }
        return false;
    }

    @Override // com.huawei.hianalytics.framework.config.ICollectorConfig
    public boolean isLocalEncrypted(String str) {
        a1 a2 = j.a(this.f3a, str);
        if (a2 != null) {
            Boolean bool = a2.f6a;
            if (bool != null) {
                return bool.booleanValue();
            }
            Boolean bool2 = a2.f10b;
            if (bool2 != null) {
                return bool2.booleanValue();
            }
        }
        return true;
    }

    @Override // com.huawei.hianalytics.framework.config.ICollectorConfig
    public boolean isUploadEncrypted(String str) {
        a1 a2 = j.a(this.f3a, str);
        if (a2 == null) {
            return true;
        }
        Boolean bool = a2.f6a;
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean bool2 = a2.f13c;
        return bool2 != null ? bool2.booleanValue() : true ^ TextUtils.equals(str, "maint");
    }

    @Override // com.huawei.hianalytics.framework.config.ICollectorConfig
    public void requestConfig() {
        q0.f3896a.b();
    }

    @Override // com.huawei.hianalytics.framework.config.ICollectorConfig
    public String getUdid() {
        return j.f();
    }

    @Override // com.huawei.hianalytics.framework.config.ICollectorConfig
    public String getPkgName() {
        return j.c();
    }

    @Override // com.huawei.hianalytics.framework.config.ICollectorConfig
    public EvtHeaderAttributeCollector getEvtCustomHeader(String str, JSONObject jSONObject) {
        return new c(jSONObject);
    }

    @Override // com.huawei.hianalytics.framework.config.ICollectorConfig
    public DeviceAttributeCollector getDeviceAttribute(String str) {
        return new b(this.f3a, str);
    }

    public a(String str) {
        this.f3a = str;
    }
}
