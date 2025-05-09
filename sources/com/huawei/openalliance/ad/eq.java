package com.huawei.openalliance.ad;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.LandpageAppWhiteList;
import com.huawei.openalliance.ad.beans.metadata.LandpageWebBlackList;
import com.huawei.openalliance.ad.beans.server.AppConfigRsp;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.SpKeys;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class eq {

    /* renamed from: a, reason: collision with root package name */
    protected final SharedPreferences f6847a;
    protected final SharedPreferences b;
    protected SharedPreferences c;
    protected final SharedPreferences d;
    protected final SharedPreferences e;
    protected final SharedPreferences l;
    protected Context m;
    protected Map<String, String> n;
    protected LandpageAppWhiteList q;
    protected final String r;
    protected LandpageWebBlackList t;
    private final String v;
    protected final byte[] f = new byte[0];
    protected final byte[] g = new byte[0];
    protected final byte[] h = new byte[0];
    protected final byte[] i = new byte[0];
    protected final byte[] j = new byte[0];
    protected final byte[] k = new byte[0];
    protected final Map<String, String> o = new HashMap();
    protected Boolean p = null;
    protected final byte[] s = new byte[0];
    protected String u = "0";

    /* JADX INFO: Access modifiers changed from: protected */
    public int e() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.IMG_SIZE_UPPER_LIMIT, 52428800);
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int d() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.GIF_SIZE_UPPER_LIMIT, Constants.GIF_SIZE_UPPER_LIMIT);
        }
        return i;
    }

    protected int c() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.GIF_TIME_LOWER_LIMIT_FRAME, 100);
        }
        return i;
    }

    protected int b() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.GIF_TIME_UPPER_LIMIT, 8000);
        }
        return i;
    }

    protected void a(List<String> list) {
        synchronized (this.f) {
            if (!com.huawei.openalliance.ad.utils.bg.a(list)) {
                this.f6847a.edit().putStringSet(SpKeys.SINGLE_INSTANCE_LS_MODEL_LIST, com.huawei.openalliance.ad.utils.bg.a(list, true)).commit();
            }
        }
    }

    protected void a(String str, String str2) {
        synchronized (this.f) {
            try {
                String string = this.f6847a.getString(MapKeyNames.REPORT_STRATEGY, "");
                JSONObject jSONObject = com.huawei.openalliance.ad.utils.cz.b(string) ? new JSONObject() : new JSONObject(string);
                jSONObject.put(str2, str);
                this.f6847a.edit().putString(MapKeyNames.REPORT_STRATEGY, jSONObject.toString()).commit();
            } catch (Throwable unused) {
                ho.d("BaseSpHandler", "set report strategy based on slot error");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(String str, AppConfigRsp appConfigRsp, boolean z) {
        synchronized (this.f) {
            ho.b("BaseSpHandler", "sv config");
            SharedPreferences.Editor edit = this.f6847a.edit();
            SharedPreferences.Editor edit2 = this.d.edit();
            edit.putLong(SpKeys.LOCATION_EXPIRE_TIME, appConfigRsp.B().longValue());
            edit.putLong(SpKeys.LOCATION_REFRESH_INTERVAL_TIME, appConfigRsp.E().longValue());
            edit.putInt(SpKeys.LOCATION_COLLECTED_SWITCH, appConfigRsp.C());
            edit.putInt(SpKeys.SPLASH_SHOW_TIME, appConfigRsp.h());
            a(edit, SpKeys.SPLASH_SHOW_MODE, appConfigRsp.i());
            edit.putInt("splash_skip_area", appConfigRsp.j());
            if (com.huawei.openalliance.ad.utils.x.j(this.m)) {
                a(edit, SpKeys.SLOGAN_SHOW_TIME, appConfigRsp.g());
            } else {
                a(edit, SpKeys.SLOGAN_SHOW_TIME, appConfigRsp.g(), 2000);
            }
            edit.putLong(SpKeys.SPLASH_SHOW_TIME_INTERVAL, appConfigRsp.e());
            edit.putLong(SpKeys.SLOGAN_REAL_MIN_SHOW_TIME, appConfigRsp.f());
            edit.putInt(SpKeys.SPLASH_APP_DAY_IMPFC, appConfigRsp.d());
            edit.putString(SpKeys.REDUCE_DISTURB_RULE, appConfigRsp.a(a()));
            edit.putInt(SpKeys.GIF_TIME_UPPER_LIMIT, appConfigRsp.a(b()));
            edit.putInt(SpKeys.GIF_TIME_LOWER_LIMIT_FRAME, appConfigRsp.b(c()));
            edit.putInt(SpKeys.GIF_SIZE_UPPER_LIMIT, appConfigRsp.c(d()));
            edit.putInt(SpKeys.IMG_SIZE_UPPER_LIMIT, appConfigRsp.d(e()));
            edit.putFloat(SpKeys.LIMIT_OF_CONTAINER_ASPECT_RATIO, (float) appConfigRsp.H());
            a(edit, SpKeys.SHOW_LANDING_PAGE_MENU, appConfigRsp.m());
            a(edit, "landpage_app_prompt", appConfigRsp.o());
            a(edit, SpKeys.CFG_REF_INTERVAL, appConfigRsp.p());
            if (z) {
                a(str, System.currentTimeMillis());
            }
            edit.putLong(SpKeys.CFG_REF_LAST_TIME, System.currentTimeMillis());
            a(edit, SpKeys.VALIDITY_SPLASH_EVENT, appConfigRsp.q());
            a(edit, SpKeys.VALIDITY_CLICK_SKIP, appConfigRsp.r());
            a(edit, SpKeys.VALIDITY_LOCK_EVENT, appConfigRsp.s());
            a(edit, SpKeys.VALIDITY_NATIVE_EVENT, appConfigRsp.t());
            a(edit, SpKeys.EXSPLASH_DELETE_MODE, appConfigRsp.J());
            a(edit, SpKeys.SPLASH_CACHE_NUM, appConfigRsp.K());
            edit.putString(SpKeys.GLOBAL_SWITCH, appConfigRsp.u());
            edit.putString(SpKeys.SUPPORT_HMS_SDK_VERCODE, appConfigRsp.w());
            edit.putLong(SpKeys.PRELOAD_SPLASH_REQ_TIME_INTERVAL, appConfigRsp.x());
            a(edit, SpKeys.MIN_BANNER_INTERVAL, appConfigRsp.b());
            a(edit, SpKeys.MAX_BANNER_INTERVAL, appConfigRsp.c());
            b(edit, appConfigRsp.D());
            a(edit, SpKeys.NEED_NOTIFY_KIT_WHEN_REQUEST, appConfigRsp.z());
            a(edit, SpKeys.DISKCACHE_VALID_TIME, appConfigRsp.I());
            edit.putString(SpKeys.APP_LIST, appConfigRsp.N());
            edit.putString(SpKeys.TEST_COUNTRY_CODE, appConfigRsp.P());
            a(edit, appConfigRsp.M(), str);
            a(edit, SpKeys.SUPPORT_GZIP, appConfigRsp.Q());
            a(edit, SpKeys.SUPPORT_SDK_SERVER_GZIP, appConfigRsp.aa());
            a(edit, SpKeys.DEFAULT_BANNER_INTERVAL, appConfigRsp.O());
            a(edit, SpKeys.REWARD_GAIN_TIME_PERCENT, appConfigRsp.R());
            a(edit, "ite_ad_close_tm", appConfigRsp.S());
            a(edit, SpKeys.ITE_AD_EXP, appConfigRsp.U());
            a(edit, "ite_ad_ca", appConfigRsp.V());
            a(edit, SpKeys.REWARD_CLOSE_BTN_PERCENT, appConfigRsp.W());
            a(edit, appConfigRsp.a());
            b(edit, "sha256", appConfigRsp.Y());
            a(edit, SpKeys.ALLOW_AD_SKIP_TIME, appConfigRsp.Z());
            a(edit, "splashInteractCloseEffectiveTime", appConfigRsp.F());
            b(edit, "splashFeedbackBtnText", appConfigRsp.G());
            a(edit, SpKeys.VIDEO_CACHA_SIZE, appConfigRsp.ab());
            a(edit, SpKeys.FLOW_CONTROL_SWITCH, appConfigRsp.ae());
            a(appConfigRsp.ac());
            a(edit2, SpKeys.ITE_AD_FS, appConfigRsp.T());
            a(edit2, SpKeys.OAID_REPORT_ON_NPA, appConfigRsp.X());
            List<String> v = appConfigRsp.v();
            if (!com.huawei.openalliance.ad.utils.bg.a(v)) {
                edit.putStringSet(SpKeys.DEF_BROSWER_PKG_LIST, new HashSet(v));
            }
            List<String> A = appConfigRsp.A();
            if (com.huawei.openalliance.ad.utils.bg.a(A)) {
                edit.putStringSet("scheme_info", null);
            } else {
                edit.putStringSet("scheme_info", new HashSet(A));
            }
            edit.commit();
            edit2.commit();
        }
        jx.a(this.m).c();
        synchronized (this.s) {
            this.q.a(appConfigRsp.n());
        }
        synchronized (this.k) {
            this.t.a(appConfigRsp.y());
        }
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.eq.4
            @Override // java.lang.Runnable
            public void run() {
                synchronized (eq.this.s) {
                    com.huawei.openalliance.ad.utils.cs.a(eq.this.q, eq.this.r);
                }
            }
        });
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.eq.5
            @Override // java.lang.Runnable
            public void run() {
                synchronized (eq.this.k) {
                    com.huawei.openalliance.ad.utils.cs.a(eq.this.t, eq.this.v);
                }
            }
        });
        final Integer L = appConfigRsp.L();
        if (L != null) {
            com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.eq.6
                @Override // java.lang.Runnable
                public void run() {
                    eq.this.a(L.intValue());
                    long intValue = L.intValue() * 1048576;
                    dk a2 = dh.a(eq.this.m, "normal");
                    long c = a2.c();
                    if (c > 0 && c != intValue) {
                        a2.a(intValue);
                    }
                    dk a3 = dh.a(eq.this.m, "ar");
                    long c2 = a3.c();
                    if (c2 <= 0 || c2 == intValue) {
                        return;
                    }
                    a3.a(intValue);
                }
            });
        }
        final Integer K = appConfigRsp.K();
        if (K != null) {
            com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.eq.7
                @Override // java.lang.Runnable
                public void run() {
                    dk a2 = dh.a(eq.this.m, "normal");
                    int b = a2.b();
                    if (b > 0 && b != K.intValue()) {
                        a2.a(K.intValue());
                    }
                    dk a3 = dh.a(eq.this.m, "ar");
                    int b2 = a3.b();
                    if (b2 <= 0 || b2 == K.intValue()) {
                        return;
                    }
                    a3.a(K.intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(String str, long j) {
        synchronized (this.f) {
            try {
                String string = this.f6847a.getString(SpKeys.CFG_REF_LAST_TIME_SLOTID, "");
                JSONObject jSONObject = com.huawei.openalliance.ad.utils.cz.b(string) ? new JSONObject() : new JSONObject(string);
                jSONObject.put(str, j);
                this.f6847a.edit().putString(SpKeys.CFG_REF_LAST_TIME_SLOTID, jSONObject.toString()).commit();
            } catch (Throwable unused) {
                ho.d("BaseSpHandler", "set cfg refresh time based on slot error");
            }
        }
    }

    protected void a(SharedPreferences.Editor editor, String str, Integer num) {
        if (num != null) {
            editor.putInt(str, num.intValue());
        }
    }

    protected void a(int i) {
        if (i <= 0) {
            return;
        }
        synchronized (this.f) {
            SharedPreferences.Editor edit = this.f6847a.edit();
            edit.putInt(SpKeys.DISK_CACHE_SIZE, i);
            edit.commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String a() {
        String string;
        synchronized (this.f) {
            string = this.f6847a.getString(SpKeys.REDUCE_DISTURB_RULE, null);
        }
        return string;
    }

    private void z() {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.eq.8
            @Override // java.lang.Runnable
            public void run() {
                eq eqVar = eq.this;
                eqVar.p = Boolean.valueOf(bz.a(eqVar.m).d());
            }
        });
    }

    private void b(SharedPreferences.Editor editor, String str, String str2) {
        if (str2 != null) {
            editor.putString(str, str2);
        }
    }

    private void b(SharedPreferences.Editor editor, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("apiWhiteList", jSONObject);
            b(editor, SpKeys.API_WHITE_LIST, jSONObject2.toString());
        } catch (JSONException unused) {
            ho.d("BaseSpHandler", "putApiWhiteList JSONException");
        }
    }

    private String aw() {
        ho.a("BaseSpHandler", "put support video codec");
        int codecCount = MediaCodecList.getCodecCount();
        HashSet hashSet = new HashSet();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                hashSet.addAll(Arrays.asList(codecInfoAt.getSupportedTypes()));
            }
        }
        String a2 = com.huawei.openalliance.ad.utils.cz.a((List<String>) new ArrayList(hashSet), ",");
        synchronized (this.f) {
            SharedPreferences.Editor edit = this.f6847a.edit();
            edit.putString(SpKeys.SUPPORT_VIDEO_CODEC, a2);
            edit.commit();
        }
        return a2;
    }

    private String av() {
        String string;
        synchronized (this.f) {
            string = this.f6847a.getString(SpKeys.SUPPORT_VIDEO_CODEC, "");
        }
        return !com.huawei.openalliance.ad.utils.cz.b(string) ? string : aw();
    }

    private String au() {
        String string;
        synchronized (this.f) {
            string = this.f6847a.getString(SpKeys.CONFIG_MAP, "");
        }
        return string;
    }

    private boolean at() {
        boolean z;
        synchronized (this.f) {
            z = this.f6847a.getBoolean(SpKeys.HAS_RESTORE_CONFIG, false);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void as() {
        synchronized (this.i) {
            for (Map.Entry<String, ?> entry : this.l.getAll().entrySet()) {
                this.o.put(entry.getKey(), (String) entry.getValue());
            }
        }
    }

    private void ar() {
        if (at()) {
            return;
        }
        try {
            SharedPreferences sharedPreferences = this.c;
            if (sharedPreferences == null) {
                if (ho.a()) {
                    ho.a("BaseSpHandler", "there is no old config file");
                    return;
                }
                return;
            }
            Map<String, ?> all = sharedPreferences.getAll();
            if (all != null && !all.isEmpty()) {
                Set<Map.Entry<String, ?>> entrySet = all.entrySet();
                if (entrySet != null && !entrySet.isEmpty()) {
                    SharedPreferences.Editor edit = this.f6847a.edit();
                    Iterator<Map.Entry<String, ?>> it = entrySet.iterator();
                    while (it.hasNext()) {
                        a(it.next(), edit);
                    }
                    edit.commit();
                }
                a(true);
                return;
            }
            if (ho.a()) {
                ho.a("BaseSpHandler", "there is no old config file");
            }
        } catch (Throwable th) {
            ho.c("BaseSpHandler", "restore config error:" + th.getClass().getSimpleName());
        }
    }

    private void a(boolean z) {
        synchronized (this.f) {
            this.f6847a.edit().putBoolean(SpKeys.HAS_RESTORE_CONFIG, z).commit();
        }
    }

    private void a(Map.Entry<String, ?> entry, SharedPreferences.Editor editor) {
        if (entry == null || editor == null) {
            return;
        }
        Object value = entry.getValue();
        String key = entry.getKey();
        if (value instanceof Integer) {
            editor.putInt(key, ((Integer) value).intValue());
            return;
        }
        if (value instanceof Boolean) {
            editor.putBoolean(key, ((Boolean) value).booleanValue());
            return;
        }
        if (value instanceof Long) {
            editor.putLong(key, ((Long) value).longValue());
            return;
        }
        if (value instanceof Float) {
            editor.putFloat(key, ((Float) value).floatValue());
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Set) {
            editor.putStringSet(key, new HashSet((Set) value));
        }
    }

    private void a(Object obj) {
        if (obj == null) {
            ho.a("BaseSpHandler", "allowed video codec is null");
            return;
        }
        String obj2 = obj.toString();
        String av = av();
        if (com.huawei.openalliance.ad.utils.cz.b(av)) {
            ho.a("BaseSpHandler", "support video codec is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(obj2)) {
            String[] split = obj2.split("\\|");
            if (split.length != 0) {
                for (String str : split) {
                    if (av.contains(str)) {
                        ho.a("BaseSpHandler", "support video codec: %s", str);
                        arrayList.add(str);
                    }
                }
            }
        }
        com.huawei.openalliance.ad.utils.cg.a(this.m).a(arrayList);
    }

    private void a(SharedPreferences.Editor editor, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            editor.remove(SpKeys.CONFIG_MAP);
            this.n = null;
            return;
        }
        try {
            ho.b("BaseSpHandler", "sv config map");
            JSONObject jSONObject = new JSONObject(str);
            b(editor, SpKeys.CONFIG_MAP, jSONObject.toString());
            Map<String, String> map = (Map) com.huawei.openalliance.ad.utils.be.b(jSONObject.toString(), Map.class, new Class[0]);
            this.n = map;
            a(map.get(Constants.ALLOWED_VIDEO_CODEC));
            String str3 = this.n.get(MapKeyNames.REPORT_STRATEGY);
            this.u = str3;
            a(str3, str2);
        } catch (JSONException unused) {
            ho.d("BaseSpHandler", "putConfigMap JSONException");
        }
    }

    private void a(SharedPreferences.Editor editor, String str, Long l) {
        if (l != null) {
            editor.putLong(str, l.longValue());
        }
    }

    private void a(SharedPreferences.Editor editor, String str, Integer num, int i) {
        if (num != null) {
            editor.putInt(str, num.intValue());
        } else {
            editor.putInt(str, i);
        }
    }

    private void a(SharedPreferences.Editor editor, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("trustAppList", jSONObject);
            b(editor, SpKeys.TRUST_APP_LIST, jSONObject2.toString());
        } catch (JSONException unused) {
            ho.d("BaseSpHandler", "putTrustAppList JSONException");
        }
    }

    public eq(Context context) {
        this.m = com.huawei.openalliance.ad.utils.x.i(context.getApplicationContext());
        z();
        this.f6847a = this.m.getSharedPreferences("HiAdSharedPreferences", 0);
        this.b = this.m.getSharedPreferences("HiAdSharedPreferences_sec", 0);
        this.d = this.m.getSharedPreferences("HiAdSharedPreferences_opti", 0);
        try {
            this.c = context.getSharedPreferences("HiAdSharedPreferences", 0);
        } catch (Throwable unused) {
            this.c = null;
            ho.c("BaseSpHandler", "create sp error.");
        }
        ar();
        this.l = this.m.getSharedPreferences("HiAd_url_cache_sp", 0);
        this.e = this.m.getSharedPreferences("HiAd_device_id_sp", 0);
        this.r = this.m.getFilesDir() + File.separator + Constants.PPS_ROOT_PATH + File.separator + "sp.config";
        this.v = this.m.getFilesDir() + File.separator + Constants.PPS_ROOT_PATH + File.separator + "black.config";
        this.n = (Map) com.huawei.openalliance.ad.utils.be.b(au(), Map.class, new Class[0]);
        synchronized (this.s) {
            this.q = new LandpageAppWhiteList();
        }
        synchronized (this.k) {
            this.t = new LandpageWebBlackList();
        }
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.eq.1
            @Override // java.lang.Runnable
            public void run() {
                Serializable a2 = com.huawei.openalliance.ad.utils.cs.a(eq.this.r);
                if (a2 == null || !(a2 instanceof LandpageAppWhiteList)) {
                    return;
                }
                synchronized (eq.this.s) {
                    eq.this.q = (LandpageAppWhiteList) a2;
                }
            }
        });
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.eq.2
            @Override // java.lang.Runnable
            public void run() {
                Serializable a2 = com.huawei.openalliance.ad.utils.cs.a(eq.this.v);
                if (a2 == null || !(a2 instanceof LandpageWebBlackList)) {
                    return;
                }
                synchronized (eq.this.k) {
                    eq.this.t = (LandpageWebBlackList) a2;
                }
            }
        });
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.eq.3
            @Override // java.lang.Runnable
            public void run() {
                eq.this.as();
                if (eq.this.o.isEmpty()) {
                    ho.c("BaseSpHandler", "load nothing from SP");
                }
            }
        });
    }
}
