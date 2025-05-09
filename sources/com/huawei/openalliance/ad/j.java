package com.huawei.openalliance.ad;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.ads.data.SearchInfo;
import com.huawei.hms.ads.jsb.inner.data.JsbCallBackData;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateData;
import com.huawei.openalliance.ad.beans.parameter.App;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.ContentTemplateRecord;
import com.huawei.openalliance.ad.db.bean.EncryptionField;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.ipc.CallResult;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.utils.m;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class j implements g {

    /* renamed from: a, reason: collision with root package name */
    protected String f7108a;
    protected String b;
    protected String c;
    private final byte[] d = new byte[0];
    private WeakReference<Activity> e;

    public boolean h(String str) {
        try {
            if (com.huawei.openalliance.ad.utils.cz.b(str)) {
                return false;
            }
            return Integer.parseInt(str.trim()) >= 10000300;
        } catch (Throwable th) {
            ho.d("H5Ad", "isSupportImpCtrl() exception: %s", th.getClass().getSimpleName());
            return false;
        }
    }

    protected Location g(String str) {
        JSONObject optJSONObject = new JSONObject(str).optJSONObject("location");
        if (optJSONObject != null) {
            String optString = optJSONObject.optString(JsbMapKeyNames.H5_LOC_LAT);
            String optString2 = optJSONObject.optString(JsbMapKeyNames.H5_LOC_LON);
            if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2) && Pattern.matches(Constants.LOC_PATTERN, optString) && Pattern.matches(Constants.LOC_PATTERN, optString2)) {
                Location location = new Location("");
                location.setLatitude(new BigDecimal(optString).doubleValue());
                location.setLongitude(new BigDecimal(optString2).doubleValue());
                return location;
            }
        }
        return null;
    }

    public MaterialClickInfo f(String str) {
        JSONObject jSONObject = new JSONObject(str);
        Integer valueOf = Integer.valueOf(jSONObject.optInt(MapKeyNames.CLICK_X, -111111));
        Integer valueOf2 = Integer.valueOf(jSONObject.optInt(MapKeyNames.CLICK_Y, -111111));
        String optString = jSONObject.optString(MapKeyNames.CREATIVE_SIZE, "");
        Float a2 = com.huawei.openalliance.ad.utils.cz.a(jSONObject.optString(MapKeyNames.DENSITY, "-111111"), Float.valueOf(-111111.0f));
        Integer valueOf3 = Integer.valueOf(jSONObject.optInt(MapKeyNames.UP_X, -111111));
        Integer valueOf4 = Integer.valueOf(jSONObject.optInt(MapKeyNames.UP_Y, -111111));
        Integer valueOf5 = Integer.valueOf(jSONObject.optInt(MapKeyNames.SLD, -111111));
        Long valueOf6 = Long.valueOf(jSONObject.optLong(MapKeyNames.CLICK_DOWN_TIME));
        Long valueOf7 = Long.valueOf(jSONObject.optLong(MapKeyNames.CLICK_UP_TIME));
        String optString2 = jSONObject.optString(MapKeyNames.SHAKE_ANGLE, "");
        if (valueOf.intValue() == -111111) {
            valueOf = null;
        }
        if (valueOf2.intValue() == -111111) {
            valueOf2 = null;
        }
        if (!com.huawei.openalliance.ad.utils.cz.p(optString)) {
            optString = null;
        }
        if (a2.floatValue() == -111111.0f) {
            a2 = null;
        }
        if (valueOf3.intValue() == -111111) {
            valueOf3 = null;
        }
        if (valueOf4.intValue() == -111111) {
            valueOf4 = null;
        }
        if (valueOf5.intValue() == -111111) {
            valueOf5 = null;
        }
        if (valueOf6.longValue() == 0) {
            valueOf6 = null;
        }
        if (valueOf7.longValue() == 0) {
            valueOf7 = null;
        }
        if (com.huawei.openalliance.ad.utils.cz.b(optString2)) {
            optString2 = null;
        }
        return new MaterialClickInfo.a().a(valueOf).b(valueOf2).b(optString).c(valueOf5).a(a2).d(valueOf3).e(valueOf4).b(valueOf6).a(valueOf7).a(optString2).a();
    }

    public String e(String str) {
        String optString = new JSONObject(str).optString(MapKeyNames.CREATIVE_SIZE, "");
        if (!com.huawei.openalliance.ad.utils.cz.p(optString)) {
            optString = null;
        }
        ho.b("BaseJsbCmd", "getCreativeSize: %s", optString);
        return optString;
    }

    public Integer d(String str) {
        int optInt = new JSONObject(str).optInt("source", -111111);
        if (optInt != -111111) {
            return Integer.valueOf(optInt);
        }
        return null;
    }

    protected RequestOptions c(Context context, String str) {
        RequestOptions.Builder builder = new RequestOptions.Builder();
        a(context, str, builder);
        return builder.build();
    }

    @Override // com.huawei.openalliance.ad.g
    public void b(String str) {
        this.c = str;
    }

    protected void b(RemoteCallResultCallback<String> remoteCallResultCallback, boolean z) {
        a(remoteCallResultCallback, this.f7108a, 1000, "ok", z);
    }

    public ContentRecord b(Context context, String str) {
        ContentRecord contentRecord;
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("contentId");
        String optString2 = jSONObject.optString("slotid");
        String optString3 = jSONObject.optString("templateId", "");
        int optInt = jSONObject.optInt("apiVer", 2);
        if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
            if (TextUtils.isEmpty(optString)) {
                ho.c("BaseJsbCmd", "content id is null");
                contentRecord = null;
            } else {
                contentRecord = es.a(context).a(optString);
            }
        } else if (optInt == 3) {
            contentRecord = et.b(context).a(optString, optString3, optString2);
            ContentTemplateRecord a2 = ev.a(context).a(contentRecord.m(), contentRecord.aN());
            if (a2 != null) {
                contentRecord.l(a2.c());
                contentRecord.a(new TemplateData(a2.d(), a2.e(), a2.f()));
            }
        } else {
            contentRecord = es.a(context).a(optString, optString2);
        }
        a(contentRecord, str);
        return contentRecord;
    }

    public boolean a(Context context, ContentRecord contentRecord) {
        if (contentRecord == null) {
            return false;
        }
        EncryptionField<String> aD = contentRecord.aD();
        return com.huawei.openalliance.ad.utils.cz.d(this.b, aD != null ? aD.a(context) : null) && os.k(contentRecord.V());
    }

    @Override // com.huawei.openalliance.ad.g
    public void a(String str) {
        this.b = str;
    }

    protected void a(RemoteCallResultCallback<String> remoteCallResultCallback, boolean z) {
        a(remoteCallResultCallback, this.f7108a, 1011, "", z);
    }

    @Override // com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        ho.c("BaseJsbCmd", "async execute is not implemented!");
        throw new IllegalStateException("async execute is not implemented!");
    }

    @Override // com.huawei.openalliance.ad.g
    public void a(Activity activity) {
        synchronized (this.d) {
            if (activity != null) {
                this.e = new WeakReference<>(activity);
                ho.a("BaseJsbCmd", "cur activity: %s, hash code: %s", activity.getClass(), Integer.valueOf(activity.hashCode()));
            }
        }
    }

    @Override // com.huawei.openalliance.ad.g
    public Object a(Context context, String str) {
        ho.c("BaseJsbCmd", "direct call is not implemented!");
        throw new IllegalStateException("direct call is not implemented!");
    }

    @Override // com.huawei.openalliance.ad.g
    public m.a a() {
        return m.a.IO;
    }

    public Context a(Context context) {
        Activity activity;
        synchronized (this.d) {
            WeakReference<Activity> weakReference = this.e;
            if (weakReference == null || (activity = weakReference.get()) == null) {
                return context;
            }
            ho.a("BaseJsbCmd", "cur activity: %s, hash code: %s", activity.getClass(), Integer.valueOf(activity.hashCode()));
            return activity;
        }
    }

    public static boolean i(String str) {
        return !com.huawei.openalliance.ad.utils.cz.b(str) && com.huawei.openalliance.ad.utils.cz.a(str.trim(), -111111) >= 10000301;
    }

    private Map<String, Bundle> c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Map map = (Map) com.huawei.openalliance.ad.utils.be.b(str, Map.class, Map.class);
        ho.a("BaseJsbCmd", "extras: %s", str);
        if (map == null || map.size() == 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            if (entry != null) {
                Bundle bundle = new Bundle();
                String str2 = (String) entry.getKey();
                for (Map.Entry entry2 : ((Map) entry.getValue()).entrySet()) {
                    if (entry2 != null) {
                        bundle.putString((String) entry2.getKey(), (String) entry2.getValue());
                    }
                }
                hashMap.put(str2, bundle);
            }
        }
        return hashMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.hms.ads.data.SearchInfo b(org.json.JSONObject r12) {
        /*
            r11 = this;
            r0 = 0
            if (r12 == 0) goto L86
            java.lang.String r1 = "searchQry"
            java.lang.String r1 = r12.optString(r1)
            java.lang.String r2 = "searchKwsType"
            java.lang.String r2 = r12.optString(r2)
            java.lang.String r3 = "searchKwsKW"
            java.lang.String r3 = r12.optString(r3)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            boolean r5 = android.text.TextUtils.isEmpty(r3)
            if (r5 != 0) goto L68
            java.lang.String r5 = ","
            java.lang.String[] r3 = r3.split(r5)
            boolean r6 = android.text.TextUtils.isEmpty(r2)
            r7 = 0
            if (r6 != 0) goto L3d
            java.lang.String[] r5 = r2.split(r5)
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L3b
            goto L3e
        L3b:
            int r2 = r5.length
            goto L3f
        L3d:
            r5 = r0
        L3e:
            r2 = r7
        L3f:
            r6 = r7
        L40:
            int r8 = r3.length
            if (r6 >= r8) goto L68
            r8 = r3[r6]
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 != 0) goto L65
            int r8 = r6 + 1
            if (r2 < r8) goto L5a
            r8 = r5[r6]
            int r8 = com.huawei.openalliance.ad.utils.cz.a(r8, r7)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            goto L5b
        L5a:
            r8 = r0
        L5b:
            com.huawei.hms.ads.data.Keyword r9 = new com.huawei.hms.ads.data.Keyword
            r10 = r3[r6]
            r9.<init>(r8, r10)
            r4.add(r9)
        L65:
            int r6 = r6 + 1
            goto L40
        L68:
            java.lang.String r2 = "searchChnl"
            java.lang.String r12 = r12.optString(r2)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L81
            boolean r2 = r4.isEmpty()
            if (r2 == 0) goto L81
            boolean r2 = android.text.TextUtils.isEmpty(r12)
            if (r2 != 0) goto L86
        L81:
            com.huawei.hms.ads.data.SearchInfo r0 = new com.huawei.hms.ads.data.SearchInfo
            r0.<init>(r1, r4, r12)
        L86:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.j.b(org.json.JSONObject):com.huawei.hms.ads.data.SearchInfo");
    }

    public static void a(RemoteCallResultCallback<String> remoteCallResultCallback, String str, int i, JSONObject jSONObject) {
        if (remoteCallResultCallback != null) {
            CallResult<String> callResult = new CallResult<>();
            callResult.setCode(i);
            try {
                callResult.setData(jSONObject.toString());
            } catch (Throwable th) {
                ho.c("BaseJsbCmd", "onCallResult by callBackJson " + th.getClass().getSimpleName());
            }
            remoteCallResultCallback.onRemoteCallResult(str, callResult);
        }
    }

    public static <T> void a(RemoteCallResultCallback<String> remoteCallResultCallback, String str, int i, T t, boolean z) {
        a(remoteCallResultCallback, str, i, new JsbCallBackData(t, z, null));
    }

    public static void a(RemoteCallResultCallback<String> remoteCallResultCallback, String str, int i, JsbCallBackData jsbCallBackData) {
        if (remoteCallResultCallback != null) {
            CallResult<String> callResult = new CallResult<>();
            callResult.setCode(i);
            try {
                callResult.setData(com.huawei.openalliance.ad.utils.be.b(jsbCallBackData));
            } catch (Throwable th) {
                ho.c("BaseJsbCmd", "onCallResult " + th.getClass().getSimpleName());
            }
            remoteCallResultCallback.onRemoteCallResult(str, callResult);
        }
    }

    private void a(ContentRecord contentRecord, String str) {
        if (contentRecord != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = !TextUtils.isEmpty(jSONObject.optString(JsbMapKeyNames.H5_SHOW_ID)) ? jSONObject.optString(JsbMapKeyNames.H5_SHOW_ID) : "";
                if (!TextUtils.isEmpty(optString)) {
                    contentRecord.c(optString);
                }
                if (!TextUtils.isEmpty(jSONObject.optString("requestId"))) {
                    contentRecord.y(jSONObject.optString("requestId"));
                }
                Long valueOf = Long.valueOf(jSONObject.optLong(JsbMapKeyNames.START_SHOW_TIME));
                if (valueOf.longValue() == 0) {
                    valueOf = Long.valueOf(com.huawei.openalliance.ad.utils.cz.a(optString, 0L));
                }
                if (valueOf.longValue() != 0) {
                    contentRecord.f(valueOf.longValue());
                }
                String optString2 = jSONObject.optString(JsbMapKeyNames.H5_CUSTOM_DATA);
                String optString3 = jSONObject.optString(JsbMapKeyNames.H5_USER_ID);
                if (optString2 != null) {
                    contentRecord.G(optString2);
                }
                if (optString3 != null) {
                    contentRecord.H(optString3);
                }
            } catch (Throwable unused) {
                ho.c("BaseJsbCmd", "update content failed");
            }
        }
    }

    private void a(RequestOptions.Builder builder, JSONObject jSONObject) {
        Integer valueOf = Integer.valueOf(jSONObject.optInt(JsbMapKeyNames.H5_CHILD_PROTECTION_TAG, -111111));
        Integer valueOf2 = Integer.valueOf(jSONObject.optInt(JsbMapKeyNames.H5_QUE, -111111));
        Integer valueOf3 = Integer.valueOf(jSONObject.optInt(JsbMapKeyNames.H5_UNDER_AGE_OF_PROMISE_TAG, -111111));
        Integer valueOf4 = Integer.valueOf(jSONObject.optInt(JsbMapKeyNames.H5_NPA, -111111));
        Integer valueOf5 = Integer.valueOf(jSONObject.optInt(JsbMapKeyNames.HW_DSP_NPA, -111111));
        Integer valueOf6 = Integer.valueOf(jSONObject.optInt(JsbMapKeyNames.THIRD_DSP_NPA, -111111));
        String optString = jSONObject.optString(JsbMapKeyNames.H5_AD_CONTENT_CLASSIFICATION);
        Boolean valueOf7 = Boolean.valueOf(jSONObject.optBoolean(JsbMapKeyNames.H5_REQUESTLOCATION, true));
        String optString2 = jSONObject.optString(JsbMapKeyNames.H5_CONSENT);
        String optString3 = jSONObject.optString(JsbMapKeyNames.H5_SEARCHTERM);
        String optString4 = jSONObject.optString(JsbMapKeyNames.H5_APPLANG);
        String optString5 = jSONObject.optString(JsbMapKeyNames.H5_APPCOUNTRY);
        Map<String, Bundle> c = c(jSONObject.optString(JsbMapKeyNames.H5_EXTRAS));
        App a2 = a(jSONObject.optJSONObject("app"));
        SearchInfo b = b(jSONObject.optJSONObject(JsbMapKeyNames.H5_SEARCHINFO));
        if (!jSONObject.isNull(JsbMapKeyNames.H5_USE_CONSENT_NPA)) {
            builder.setUseConsentNpa(jSONObject.optBoolean(JsbMapKeyNames.H5_USE_CONSENT_NPA));
        }
        if (-111111 != valueOf.intValue()) {
            builder.setTagForChildProtection(valueOf);
        }
        if (-111111 != valueOf3.intValue()) {
            builder.setTagForUnderAgeOfPromise(valueOf3);
        }
        if (!TextUtils.isEmpty(optString)) {
            builder.setAdContentClassification(optString);
        }
        if (-111111 != valueOf2.intValue()) {
            builder.setIsQueryUseEnabled(valueOf2);
        }
        if (-111111 != valueOf4.intValue()) {
            builder.setNonPersonalizedAd(valueOf4);
        }
        if (-111111 != valueOf5.intValue()) {
            builder.setHwNonPersonalizedAd(valueOf5);
        }
        if (-111111 != valueOf6.intValue()) {
            builder.setThirdNonPersonalizedAd(valueOf6);
        }
        if (!TextUtils.isEmpty(optString2)) {
            builder.setConsent(optString2);
        }
        if (!TextUtils.isEmpty(optString3)) {
            builder.setSearchTerm(optString3);
        }
        if (valueOf7 != null) {
            builder.setRequestLocation(valueOf7);
        }
        if (a2 != null) {
            builder.setApp(a2);
        }
        if (!TextUtils.isEmpty(optString4)) {
            builder.setAppLang(optString4);
        }
        if (!TextUtils.isEmpty(optString5)) {
            builder.setAppCountry(optString5);
        }
        if (c != null) {
            builder.setExtras(c);
        }
        if (b != null) {
            builder.setSearchInfo(b);
        }
    }

    private void a(Context context, String str, RequestOptions.Builder builder) {
        JSONObject jSONObject = new JSONObject(str);
        Integer valueOf = Integer.valueOf(jSONObject.optInt(JsbMapKeyNames.H5_BRAND, -111111));
        Boolean valueOf2 = Boolean.valueOf(jSONObject.optBoolean(JsbMapKeyNames.H5_APPINSTALLEDNOTIFY, true));
        Integer valueOf3 = Integer.valueOf(jSONObject.optInt(JsbMapKeyNames.H5_APPACTIVATESTYLE, 0));
        Boolean valueOf4 = Boolean.valueOf(jSONObject.optBoolean(JsbMapKeyNames.H5_APPAUTOOPENFORBIDDEN, false));
        String optString = jSONObject.optString(JsbMapKeyNames.H5_BELONG_COUNTRY);
        if (valueOf != null && -111111 != valueOf.intValue()) {
            HiAd.getInstance(context).setBrand(valueOf.intValue());
        }
        if (valueOf2 != null) {
            HiAd.getInstance(context).setAppInstalledNotify(valueOf2.booleanValue());
        }
        ho.b("BaseJsbCmd", "appActivateStyle: %s", valueOf3);
        if (valueOf3.intValue() != 0) {
            HiAd.getInstance(context).setAppActivateStyle(valueOf3.intValue());
        }
        if (valueOf4 != null) {
            HiAd.getInstance(context).setAppAutoOpenForbidden(valueOf4.booleanValue());
        }
        if (!TextUtils.isEmpty(optString)) {
            HiAd.getInstance(context).setCountryCode(optString);
        }
        if (builder != null) {
            a(builder, jSONObject);
        }
    }

    private App a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString("name");
        String optString2 = jSONObject.optString("version");
        String optString3 = jSONObject.optString(JsbMapKeyNames.H5_APP_PKGNAME);
        String optString4 = jSONObject.optString(JsbMapKeyNames.H5_APP_HOSTPKGNAME);
        if (TextUtils.isEmpty(optString) && TextUtils.isEmpty(optString3) && TextUtils.isEmpty(optString2) && TextUtils.isEmpty(optString4)) {
            return null;
        }
        return new App(optString3, optString, optString2, optString4);
    }

    public j(String str) {
        this.f7108a = str;
    }
}
