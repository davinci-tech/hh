package com.huawei.openalliance.ad;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.App;
import com.huawei.openalliance.ad.beans.metadata.FlowControl;
import com.huawei.openalliance.ad.beans.metadata.Location;
import com.huawei.openalliance.ad.beans.metadata.OaidRecord;
import com.huawei.openalliance.ad.beans.server.AppConfigRsp;
import com.huawei.openalliance.ad.beans.tags.TagCfgModel;
import com.huawei.openalliance.ad.constant.AdLoadMode;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.DefBrowserPkgList;
import com.huawei.openalliance.ad.constant.DefSchemeInfo;
import com.huawei.openalliance.ad.constant.DefaultUrlConstant;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.SpDefaultValues;
import com.huawei.openalliance.ad.constant.SpKeys;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class fh extends er implements gc {
    private static gc v;
    private static final byte[] w = new byte[0];
    private String x;
    private String y;

    @Override // com.huawei.openalliance.ad.gc
    public boolean ba() {
        return true;
    }

    @Override // com.huawei.openalliance.ad.gc
    public void z(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.f) {
            SharedPreferences.Editor edit = this.f6847a.edit();
            edit.putString(SpKeys.UNINSTALLED_APP_CACHE, str);
            edit.apply();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void y(String str) {
        synchronized (this.f) {
            this.f6847a.edit().remove(str + SpKeys.FLOW_CONTROL_COUNTS).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public int x(String str) {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(str + SpKeys.FLOW_CONTROL_COUNTS, 0);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public void w(String str) {
        synchronized (this.f) {
            String str2 = str + SpKeys.FLOW_CONTROL_COUNTS;
            this.f6847a.edit().putInt(str2, this.f6847a.getInt(str2, 0) + 1).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public FlowControl v(String str) {
        if (com.huawei.openalliance.ad.utils.cz.b(str)) {
            ho.b("SpHandler", "slot is empty");
            return null;
        }
        synchronized (this.f) {
            String string = this.f6847a.getString(str, null);
            if (com.huawei.openalliance.ad.utils.cz.b(string)) {
                return null;
            }
            return (FlowControl) com.huawei.openalliance.ad.utils.be.b(string, FlowControl.class, new Class[0]);
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void u(String str) {
        synchronized (this.f) {
            this.f6847a.edit().putString(SpKeys.SHIELD_OTHER_SPLASH_FASHION, str).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void t(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.f) {
            this.f6847a.edit().putLong(SpKeys.LAST_CALL_METHOD_TIME_PREFIX + str, com.huawei.openalliance.ad.utils.ao.c()).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void s(String str) {
        synchronized (this.f) {
            this.f6847a.edit().putString(SpKeys.PLACEMENT_CFG_REQ_AD_ID, str).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public OaidRecord r(String str) {
        if (ho.a()) {
            ho.a("SpHandler", "getOaidRecord, key: %s", str);
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        synchronized (this.f) {
            String string = this.f6847a.getString(str, "");
            if (!TextUtils.isEmpty(string)) {
                return (OaidRecord) com.huawei.openalliance.ad.utils.be.b(string, OaidRecord.class, new Class[0]);
            }
            ho.c("SpHandler", "oaid record do not exist for: " + str);
            return null;
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void q(String str) {
        synchronized (this.f) {
            if (!TextUtils.isEmpty(str)) {
                this.f6847a.edit().putString(SpKeys.GLOBAL_SWITCH, str).commit();
            }
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void p(String str) {
        synchronized (this.f) {
            if (!TextUtils.isEmpty(str)) {
                this.f6847a.edit().putString(SpKeys.LINKED_CONTENT_ID, str).commit();
            }
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void p(long j) {
        if (j <= 0) {
            return;
        }
        synchronized (this.f) {
            this.f6847a.edit().putLong(SpKeys.LOADER_LAST_CHECK_TIME, j).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void o(String str) {
        synchronized (this.f) {
            if (!TextUtils.isEmpty(str)) {
                this.f6847a.edit().putString(SpKeys.BELONG_COUNTRY_CODE, str).commit();
            }
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void o(long j) {
        synchronized (this.g) {
            this.e.edit().putLong(SpKeys.LAST_REQ_HONORQAID_TIME, j).apply();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void n(String str) {
        synchronized (this.f) {
            this.f6847a.edit().putString(SpKeys.R_D, str).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void n(long j) {
        synchronized (this.f) {
            this.f6847a.edit().putLong(SpKeys.LAST_QUERY_ACCOUNT_TIME, j).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void m(long j) {
        synchronized (this.f) {
            this.f6847a.edit().putLong(SpKeys.LAST_QRY_GRP_ID_TIME, j).apply();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void m(int i) {
        synchronized (this.f) {
            this.f6847a.edit().putInt(SpKeys.MEDIA_UI_MODE, i).apply();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public long m(String str) {
        if (com.huawei.openalliance.ad.utils.cz.b(str)) {
            return 0L;
        }
        synchronized (this.f) {
            try {
                String string = this.f6847a.getString(SpKeys.CFG_REF_LAST_TIME_SLOTID, "");
                if (com.huawei.openalliance.ad.utils.cz.b(string)) {
                    return 0L;
                }
                Object obj = new JSONObject(string).get(str);
                if (obj == null) {
                    return 0L;
                }
                return Long.parseLong(obj.toString());
            } catch (Throwable unused) {
                ho.d("SpHandler", "get cfg refresh time based on slot error");
                return 0L;
            }
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void l(long j) {
        synchronized (this.f) {
            this.f6847a.edit().putLong(SpKeys.CLCT_CTX_TIME, j).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void l(int i) {
        synchronized (this.f) {
            this.f6847a.edit().putInt(SpKeys.VIDEO_AUTO_PLAY_FROM_MEDIA, i).apply();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public Boolean l(String str) {
        Boolean c;
        synchronized (this.s) {
            c = this.q.c(str);
        }
        return c;
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean k(String str) {
        synchronized (this.k) {
            if (this.t == null) {
                return false;
            }
            return this.t.b(str);
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void k(long j) {
        synchronized (this.g) {
            this.e.edit().putLong(SpKeys.LAST_REQ_UUID_TIME, j).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void k(int i) {
        synchronized (this.f) {
            this.f6847a.edit().putInt(SpDefaultValues.DEFAULT_SPLASH_MODE, i).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean j(String str) {
        synchronized (this.s) {
            if (this.q == null) {
                return true;
            }
            return this.q.b(str);
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void j(long j) {
        synchronized (this.g) {
            this.e.edit().putLong(SpKeys.LAST_REQ_QAID_TIME, j).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void j(int i) {
        synchronized (this.f) {
            if (i >= 0) {
                a(this.f6847a.edit(), "landpage_app_prompt", Integer.valueOf(i));
            }
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void i(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.f) {
            this.y = str;
            SharedPreferences.Editor edit = this.f6847a.edit();
            edit.putString(SpKeys.PPS_STORE, str);
            edit.commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void i(long j) {
        synchronized (this.f) {
            this.f6847a.edit().putLong(SpKeys.LAST_QUERY_UA_TIME, j).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void i(int i) {
        synchronized (this.f) {
            if (i > 0) {
                this.f6847a.edit().putInt(SpKeys.EXSPLASH_REDUNDANCY_TIME, i).commit();
            }
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void h(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.f) {
            this.x = str;
            SharedPreferences.Editor edit = this.f6847a.edit();
            edit.putString(SpKeys.SERVER_STORE, str);
            edit.commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void h(long j) {
        synchronized (this.f) {
            SharedPreferences.Editor edit = this.f6847a.edit();
            edit.putLong(SpKeys.DEVICE_CONNECT_LIST_LAST_TIME, j);
            edit.commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void h(int i) {
        synchronized (this.f) {
            if (i > 0) {
                this.f6847a.edit().putInt(SpKeys.EXSPLASH_SLOGAN_SHOW_TIME, i).commit();
            }
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void g(String str) {
        synchronized (this.f) {
            this.f6847a.edit().putString(SpKeys.MAGLOCK_SHOW_ID, str).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void g(long j) {
        synchronized (this.f) {
            if (j > 0) {
                this.f6847a.edit().putLong(SpKeys.EXSPLASH_SLOGAN_START_TIME, j).commit();
            }
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void g(int i) {
        synchronized (this.f) {
            SharedPreferences.Editor edit = this.f6847a.edit();
            edit.putInt(SpKeys.AD_PRELOAD_INTERVAL, i);
            edit.commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void f(boolean z) {
        synchronized (this.f) {
            this.f6847a.edit().putBoolean(SpKeys.REMIND_AGAIN, z).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void f(String str) {
        synchronized (this.f) {
            this.f6847a.edit().putString(SpKeys.TODAY_DATE, str).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void f(long j) {
        synchronized (this.f) {
            SharedPreferences.Editor edit = this.f6847a.edit();
            edit.putLong(SpKeys.LAST_SYNC_CONFIRM_RESULT_TIME, j);
            edit.apply();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public long f(int i) {
        int e;
        if (4 == i) {
            e = d();
        } else {
            if (2 != i) {
                return 52428800L;
            }
            e = e();
        }
        return e;
    }

    @Override // com.huawei.openalliance.ad.gc
    public void e(boolean z) {
        synchronized (this.f) {
            this.f6847a.edit().putBoolean(SpKeys.AUTO_OPEN_FORBIDDEN, z).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void e(long j) {
        synchronized (this.f) {
            SharedPreferences.Editor edit = this.f6847a.edit();
            edit.putLong(SpKeys.CFG_REF_LAST_TIME, j);
            edit.commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void e(int i) {
        synchronized (this.f) {
            this.f6847a.edit().putInt(SpKeys.TODAY_SHOW_TIMES, i).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public String e(String str) {
        if (com.huawei.openalliance.ad.utils.cz.b(str)) {
            return "";
        }
        synchronized (this.f) {
            try {
                String string = this.f6847a.getString(MapKeyNames.REPORT_STRATEGY, "");
                if (com.huawei.openalliance.ad.utils.cz.b(string)) {
                    return "";
                }
                Object obj = new JSONObject(string).get(str);
                if (obj == null) {
                    return "";
                }
                return obj.toString();
            } catch (Throwable unused) {
                ho.b("SpHandler", "get report strategy based on slot error");
                return "";
            }
        }
    }

    @Override // com.huawei.openalliance.ad.eq, com.huawei.openalliance.ad.gc
    public int e() {
        return super.e();
    }

    @Override // com.huawei.openalliance.ad.gc
    public void d(boolean z) {
        synchronized (this.f) {
            this.f6847a.edit().putBoolean(SpKeys.APP_AD_LMT_KEY, z).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void d(long j) {
        synchronized (this.f) {
            this.f6847a.edit().putLong(SpKeys.NO_SHOW_AD_TIME, j).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void d(int i) {
        synchronized (this.f) {
            this.f6847a.edit().putInt(SpKeys.SMART_SCREEN_SLOGAN_TIME, i).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public String d(String str) {
        String e = e(str);
        return com.huawei.openalliance.ad.utils.cz.b(e) ? "0" : e;
    }

    @Override // com.huawei.openalliance.ad.eq, com.huawei.openalliance.ad.gc
    public int d() {
        return super.d();
    }

    @Override // com.huawei.openalliance.ad.gc
    public String cn() {
        String string;
        synchronized (this.f) {
            string = this.f6847a.getString("adid", "");
        }
        return string;
    }

    @Override // com.huawei.openalliance.ad.gc
    public long cm() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getLong(SpKeys.LOADER_LAST_CHECK_TIME, 0L);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public long cl() {
        long j;
        synchronized (this.g) {
            j = this.e.getLong(SpKeys.LAST_REQ_HONORQAID_TIME, 0L);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public List<TagCfgModel> ck() {
        ArrayList arrayList = new ArrayList();
        String b = b(SpKeys.TAG_CFG, "");
        if (com.huawei.openalliance.ad.utils.cz.b(b)) {
            return arrayList;
        }
        try {
            String optString = new JSONObject(b).optString(SpKeys.ALLOWED_TAGS, "");
            return com.huawei.openalliance.ad.utils.cz.b(optString) ? arrayList : (List) com.huawei.openalliance.ad.utils.be.b(optString, List.class, TagCfgModel.class);
        } catch (Exception unused) {
            ho.c("SpHandler", "get tag cfg list error");
            return arrayList;
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public long cj() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getLong(SpKeys.LAST_QUERY_ACCOUNT_TIME, 0L);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public String ci() {
        String string;
        synchronized (this.f) {
            string = this.f6847a.getString(SpKeys.UNINSTALLED_APP_CACHE, null);
        }
        return string;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int ch() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.FLOW_CONTROL_SWITCH, 0);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int cg() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.MEDIA_UI_MODE, -1);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public long cf() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getLong(SpKeys.LAST_QRY_GRP_ID_TIME, 0L);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean ce() {
        synchronized (this.f) {
            if (!com.huawei.openalliance.ad.utils.x.j(this.m)) {
                return false;
            }
            if (ho.a()) {
                ho.a("SpHandler", "isSingleMediaPlayerInstance, is tv");
            }
            Set<String> stringSet = this.f6847a.getStringSet(SpKeys.SINGLE_INSTANCE_LS_MODEL_LIST, new HashSet());
            String b = bz.a(this.m).b();
            if (!com.huawei.openalliance.ad.utils.bg.a(stringSet) && !TextUtils.isEmpty(b)) {
                return com.huawei.openalliance.ad.utils.cz.a(stringSet, b.toUpperCase(Locale.ENGLISH));
            }
            return true;
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public Set<String> cd() {
        Set<String> stringSet;
        synchronized (this.f) {
            stringSet = this.f6847a.getStringSet(SpKeys.NOTIFICATION_APP_LIST, new HashSet());
        }
        return stringSet;
    }

    @Override // com.huawei.openalliance.ad.gc
    public long cc() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getLong(SpKeys.CLCT_CTX_TIME, 0L);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean cb() {
        boolean z;
        synchronized (this.f) {
            z = this.f6847a.getBoolean(SpKeys.REMIND_AGAIN, true);
        }
        return z;
    }

    @Override // com.huawei.openalliance.ad.gc
    public String ca() {
        String string;
        synchronized (this.f) {
            string = this.f6847a.getString(SpKeys.SHIELD_OTHER_SPLASH_FASHION, "");
        }
        return string;
    }

    @Override // com.huawei.openalliance.ad.gc
    public void c(boolean z) {
        synchronized (this.f) {
            this.f6847a.edit().putBoolean(SpKeys.ENABLE_SHARE_PD, z).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void c(String str) {
        a(this.u, str);
    }

    @Override // com.huawei.openalliance.ad.gc
    public void c(long j) {
        synchronized (this.f) {
            this.f6847a.edit().putLong(SpKeys.ONLINE_STREAM_CLEAN_DISK_TIME, j).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void c(int i) {
        synchronized (this.f) {
            this.f6847a.edit().putInt(SpKeys.CACHE_SLOGAN_SHOW_TIME_DEF, i).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.eq
    public int c() {
        return super.c();
    }

    @Override // com.huawei.openalliance.ad.gc
    public int bz() {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(co(), 9);
        if (a2 != null) {
            return a2.intValue();
        }
        return 0;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int by() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.EXSPLASH_DELETE_MODE, 2);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public float bx() {
        float f;
        synchronized (this.f) {
            f = this.f6847a.getFloat(SpKeys.LIMIT_OF_CONTAINER_ASPECT_RATIO, 0.05f);
        }
        return f;
    }

    @Override // com.huawei.openalliance.ad.gc
    public String bw() {
        String string;
        synchronized (this.f) {
            string = this.f6847a.getString(SpKeys.BELONG_COUNTRY_CODE, null);
        }
        return string;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int bv() {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(co(), 8);
        if (a2 != null) {
            return a2.intValue();
        }
        return 1;
    }

    @Override // com.huawei.openalliance.ad.gc
    public Set<String> bu() {
        Set<String> stringSet;
        synchronized (this.f) {
            stringSet = this.f6847a.getStringSet("scheme_info", DefSchemeInfo.SCHEME_INFO);
        }
        return stringSet;
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean bt() {
        boolean z;
        synchronized (this.f) {
            z = true;
            if (1 != this.f6847a.getInt(SpKeys.NEED_NOTIFY_KIT_WHEN_REQUEST, 1)) {
                z = false;
            }
        }
        return z;
    }

    @Override // com.huawei.openalliance.ad.gc
    public long bs() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getLong(SpKeys.LAST_SYNC_CONFIRM_RESULT_TIME, 0L);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public long br() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getLong(SpKeys.MAX_BANNER_INTERVAL, 120L);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public long bq() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getInt(SpKeys.DEFAULT_BANNER_INTERVAL, 60);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public long bp() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getLong(SpKeys.MIN_BANNER_INTERVAL, 30L);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public long bo() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getLong(SpKeys.PRELOAD_SPLASH_REQ_TIME_INTERVAL, 600000L);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean bn() {
        boolean z;
        synchronized (this.f) {
            z = Math.abs(System.currentTimeMillis() - this.f6847a.getLong(SpKeys.NO_WIFI_REMIND_STARTTIME, 0L)) > ((long) this.f6847a.getInt(SpKeys.NO_WFII_REMIND_BLOCK_TIME, 7)) * 86400000;
        }
        return z;
    }

    @Override // com.huawei.openalliance.ad.gc
    public String bm() {
        String string;
        synchronized (this.f) {
            string = this.f6847a.getString(SpKeys.R_D, null);
        }
        return string;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int bl() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.AD_PRELOAD_INTERVAL, 0);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public Set<String> bk() {
        Set<String> stringSet;
        synchronized (this.f) {
            stringSet = this.f6847a.getStringSet(SpKeys.DEF_BROSWER_PKG_LIST, DefBrowserPkgList.CONTENT);
        }
        return stringSet;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int bj() {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(co(), 4);
        if (a2 != null) {
            return a2.intValue();
        }
        return 5;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int bi() {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(co(), 7);
        if (a2 != null) {
            return a2.intValue();
        }
        return 0;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int bh() {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(co(), 6);
        if (a2 != null) {
            return a2.intValue();
        }
        return 0;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int bg() {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(co(), 5);
        if (a2 != null) {
            return a2.intValue();
        }
        return 1;
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean bf() {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(co(), 3);
        return a2 == null || a2.intValue() == 1;
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean be() {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(co(), 2);
        return a2 != null && a2.intValue() == 1;
    }

    @Override // com.huawei.openalliance.ad.gc
    public String bd() {
        String string;
        synchronized (this.f) {
            string = this.f6847a.getString(SpKeys.API_WHITE_LIST, "");
        }
        return string;
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean bc() {
        synchronized (this.f) {
            return Integer.valueOf(this.f6847a.getInt(SpKeys.LOCATION_COLLECTED_SWITCH, 0)).intValue() == 1;
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean bb() {
        Integer a2 = com.huawei.openalliance.ad.utils.da.a(co(), 1);
        return a2 != null && a2.intValue() == 1;
    }

    @Override // com.huawei.openalliance.ad.gc
    public String bZ() {
        String string;
        synchronized (this.f) {
            string = this.f6847a.getString("splashFeedbackBtnText", "");
        }
        return string;
    }

    public int bY() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt("splashInteractCloseEffectiveTime", 30);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean bX() {
        boolean z;
        synchronized (this.f) {
            z = this.f6847a.getBoolean(SpKeys.AUTO_OPEN_FORBIDDEN, false);
        }
        return z;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int bW() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.ALLOW_AD_SKIP_TIME, 0);
        }
        return i * 1000;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int bV() {
        synchronized (this.h) {
            if (this.p == null) {
                this.p = Boolean.valueOf(bz.a(this.m).d());
            }
            if (!this.p.booleanValue()) {
                return 0;
            }
            return this.d.getInt(SpKeys.OAID_REPORT_ON_NPA, 0);
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public long bU() {
        long j;
        synchronized (this.g) {
            j = this.e.getLong(SpKeys.LAST_REQ_UUID_TIME, 0L);
            if (0 == j) {
                j = this.f6847a.getLong(SpKeys.LAST_REQ_UUID_TIME, 0L);
            }
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public long bT() {
        long j;
        synchronized (this.g) {
            j = this.e.getLong(SpKeys.LAST_REQ_QAID_TIME, 0L);
            if (0 == j) {
                j = this.f6847a.getLong(SpKeys.LAST_REQ_QAID_TIME, 0L);
            }
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int bS() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt("ite_ad_ca", 0);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int bR() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.ITE_AD_EXP, 0);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int bQ() {
        int i;
        synchronized (this.h) {
            if (this.p == null) {
                this.p = Boolean.valueOf(bz.a(this.m).d());
            }
            i = this.d.getInt(SpKeys.ITE_AD_FS, this.p.booleanValue() ? 1 : 0);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int bP() {
        int i;
        synchronized (this.f) {
            int i2 = this.f6847a.getInt("ite_ad_close_tm", 3);
            i = i2 >= 0 ? i2 : 3;
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int bO() {
        int max;
        synchronized (this.f) {
            max = Math.max(this.f6847a.getInt(SpKeys.REWARD_GAIN_TIME_PERCENT, 90), 1);
        }
        return max;
    }

    @Override // com.huawei.openalliance.ad.gc
    public Location bN() {
        Location location;
        synchronized (this.j) {
            String string = this.b.getString(SpKeys.LAST_KNOWN_LOCATION, "");
            location = TextUtils.isEmpty(string) ? null : (Location) com.huawei.openalliance.ad.utils.be.b(com.huawei.openalliance.ad.utils.f.b(string, com.huawei.openalliance.ad.utils.cp.b(this.m)), Location.class, new Class[0]);
        }
        return location;
    }

    @Override // com.huawei.openalliance.ad.gc
    public String bM() {
        String string;
        synchronized (this.f) {
            string = this.f6847a.getString(SpKeys.PLACEMENT_CFG_REQ_AD_ID, "");
        }
        return string;
    }

    @Override // com.huawei.openalliance.ad.gc
    public String bL() {
        String string;
        synchronized (this.f) {
            string = this.f6847a.getString(SpKeys.TRUST_APP_LIST, "");
        }
        return string;
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean bK() {
        boolean z;
        synchronized (this.f) {
            z = this.f6847a.getInt(SpKeys.SUPPORT_SDK_SERVER_GZIP, 0) == 1;
        }
        return z;
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean bJ() {
        boolean z;
        synchronized (this.f) {
            z = this.f6847a.getInt(SpKeys.SUPPORT_GZIP, 0) == 1;
        }
        return z;
    }

    @Override // com.huawei.openalliance.ad.gc
    public String bI() {
        String string;
        synchronized (this.f) {
            string = this.f6847a.getString(SpKeys.TEST_COUNTRY_CODE, "");
        }
        return string;
    }

    @Override // com.huawei.openalliance.ad.gc
    public List<App> bH() {
        ArrayList arrayList;
        synchronized (this.f) {
            String string = this.f6847a.getString(SpKeys.APP_LIST, "");
            if (!TextUtils.isEmpty(string)) {
                HashSet hashSet = new HashSet();
                String[] split = string.split(",");
                int length = split.length;
                if (length > 0) {
                    for (int i = 0; i < length; i++) {
                        if (!TextUtils.isEmpty(split[i])) {
                            App app = new App(this.m, split[i]);
                            if (!TextUtils.isEmpty(app.a())) {
                                hashSet.add(app);
                            }
                        }
                    }
                }
                if (hashSet.size() > 0) {
                    arrayList = new ArrayList();
                    arrayList.addAll(hashSet);
                }
            }
            arrayList = null;
        }
        return arrayList;
    }

    @Override // com.huawei.openalliance.ad.gc
    public long bG() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getLong(SpKeys.LAST_QUERY_UA_TIME, 0L);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public String bF() {
        synchronized (this.f) {
            boolean d = bz.a(this.m).d();
            boolean b = bz.b(this.m);
            if (d && !b) {
                return this.f6847a.getBoolean(SpKeys.APP_AD_LMT_KEY, false) ? "1" : "0";
            }
            return null;
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public long bE() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getLong(SpKeys.DEVICE_CONNECT_LIST_LAST_TIME, 0L);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int bD() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.EXSPLASH_REDUNDANCY_TIME, 100);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int bC() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.EXSPLASH_SLOGAN_SHOW_TIME, 0);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public Long bB() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getLong(SpKeys.EXSPLASH_SLOGAN_START_TIME, 0L);
        }
        return Long.valueOf(j);
    }

    @Override // com.huawei.openalliance.ad.gc
    public Long bA() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getLong(SpKeys.DISKCACHE_VALID_TIME, Constants.DISKCACHE_DEFAULT_VALID_TIME);
        }
        return Long.valueOf(j);
    }

    @Override // com.huawei.openalliance.ad.gc
    public void b(boolean z) {
        synchronized (this.f) {
            this.f6847a.edit().putBoolean(SpKeys.ENABLE_USER_INFO, z).commit();
            if (this.c != null) {
                this.c.edit().putBoolean(SpKeys.ENABLE_USER_INFO, z).commit();
            }
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void b(Set<String> set) {
        synchronized (this.f) {
            SharedPreferences.Editor edit = this.f6847a.edit();
            if (com.huawei.openalliance.ad.utils.bg.a(set)) {
                edit.putStringSet(SpKeys.NOTIFICATION_APP_LIST, null);
            } else {
                edit.putStringSet(SpKeys.NOTIFICATION_APP_LIST, set);
            }
            edit.commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void b(List<String> list) {
        synchronized (this.k) {
            if (list != null) {
                this.t.a(com.huawei.openalliance.ad.utils.bg.a(list, ","));
            }
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void b(long j) {
        synchronized (this.f) {
            this.f6847a.edit().putLong(SpKeys.LAST_CLEAN_DISK_TIME, j).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void b(int i) {
        synchronized (this.f) {
            this.f6847a.edit().putInt("splash_skip_area", i).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.eq
    public int b() {
        return super.b();
    }

    @Override // com.huawei.openalliance.ad.gc
    public int az() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt("splash_skip_area", 0);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public AdLoadMode ay() {
        AdLoadMode adLoadMode;
        synchronized (this.f) {
            int i = this.f6847a.getInt(SpKeys.SPLASH_SHOW_MODE, cp());
            adLoadMode = AdLoadMode.CACHE;
            if (2 == i) {
                adLoadMode = AdLoadMode.REAL;
            } else if (3 == i) {
                adLoadMode = AdLoadMode.REAL_NEW;
            }
        }
        return adLoadMode;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int ax() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.SPLASH_SHOW_TIME, 3000);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public long aw() {
        long max;
        synchronized (this.f) {
            max = Math.max(this.f6847a.getLong(SpKeys.LOCATION_REFRESH_INTERVAL_TIME, 1800000L), 300000L);
        }
        return max;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int av() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.SPLASH_CACHE_NUM, 10);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int au() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.VIDEO_CACHA_SIZE, 300);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public String at() {
        String string;
        synchronized (this.f) {
            string = PreferenceManager.getDefaultSharedPreferences(this.m.getApplicationContext()).getString(SpKeys.HW_ACSTRING, "");
        }
        return string;
    }

    @Override // com.huawei.openalliance.ad.gc
    public String as() {
        String string;
        synchronized (this.f) {
            string = PreferenceManager.getDefaultSharedPreferences(this.m.getApplicationContext()).getString(SpKeys.GOOGLE_ACSTRING, "");
        }
        return string;
    }

    @Override // com.huawei.openalliance.ad.gc
    public String ar() {
        String string;
        synchronized (this.f) {
            string = PreferenceManager.getDefaultSharedPreferences(this.m.getApplicationContext()).getString(SpKeys.IABTCF_TCSTRING, "");
        }
        return string;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int aZ() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.DISK_CACHE_SIZE, 800);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int aY() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.VALIDITY_NATIVE_EVENT, 2880);
        }
        return i * 60000;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int aX() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.VALIDITY_LOCK_EVENT, 10080);
        }
        return i * 60000;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int aW() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.VALIDITY_CLICK_SKIP, 30);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int aV() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.VALIDITY_SPLASH_EVENT, 2880);
        }
        return i * 60000;
    }

    @Override // com.huawei.openalliance.ad.gc
    public long aU() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getLong(SpKeys.CFG_REF_LAST_TIME, 0L);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int aT() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.CFG_REF_INTERVAL, 360);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean aS() {
        boolean z;
        synchronized (this.f) {
            z = this.f6847a.getInt("landpage_app_prompt", 0) == 1;
        }
        return z;
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean aR() {
        boolean z;
        synchronized (this.f) {
            z = this.f6847a.getInt(SpKeys.SHOW_LANDING_PAGE_MENU, 0) == 1;
        }
        return z;
    }

    @Override // com.huawei.openalliance.ad.gc
    public String aQ() {
        synchronized (this.f) {
            String str = this.y;
            if (str != null) {
                return str;
            }
            String string = this.f6847a.getString(SpKeys.PPS_STORE, "");
            this.y = string;
            return string;
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public String aP() {
        synchronized (this.f) {
            String str = this.x;
            if (str != null) {
                return str;
            }
            String string = this.f6847a.getString(SpKeys.SERVER_STORE, "");
            this.x = string;
            return string;
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public long aO() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getLong(SpKeys.NO_SHOW_AD_TIME, 0L);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public String aN() {
        String string;
        synchronized (this.f) {
            string = this.f6847a.getString(SpKeys.MAGLOCK_SHOW_ID, "");
        }
        return string;
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean aM() {
        boolean z;
        synchronized (this.f) {
            z = this.f6847a.getBoolean(SpKeys.ENABLE_SHARE_PD, true);
        }
        return z;
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean aL() {
        boolean z;
        synchronized (this.f) {
            z = (this.c != null && this.c.getBoolean(SpKeys.ENABLE_USER_INFO, false)) || this.f6847a.getBoolean(SpKeys.ENABLE_USER_INFO, false);
        }
        return z;
    }

    @Override // com.huawei.openalliance.ad.gc
    public String aK() {
        String string;
        synchronized (this.f) {
            string = this.f6847a.getString(SpKeys.TODAY_DATE, "");
        }
        return string;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int aJ() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.TODAY_SHOW_TIMES, 0);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int aI() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.SPLASH_APP_DAY_IMPFC, 0);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public long aH() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getLong(SpKeys.SLOGAN_REAL_MIN_SHOW_TIME, 300L);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public long aG() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getLong(SpKeys.SPLASH_SHOW_TIME_INTERVAL, 0L);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int aF() {
        int i;
        synchronized (this.f) {
            int aC = AdLoadMode.CACHE == ay() ? aC() : 2000;
            if (com.huawei.openalliance.ad.utils.x.j(this.m)) {
                aC = aE();
            }
            i = this.f6847a.getInt(SpKeys.SLOGAN_SHOW_TIME, aC);
        }
        return i;
    }

    public int aE() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.SMART_SCREEN_SLOGAN_TIME, 2000);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean aD() {
        boolean z;
        synchronized (this.f) {
            z = this.f6847a.getBoolean(SpKeys.USE_POST_AT_FRONT, false);
        }
        return z;
    }

    public int aC() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpKeys.CACHE_SLOGAN_SHOW_TIME_DEF, 0);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.gc
    public long aB() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getLong(SpKeys.ONLINE_STREAM_CLEAN_DISK_TIME, 0L);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public long aA() {
        long j;
        synchronized (this.f) {
            j = this.f6847a.getLong(SpKeys.LAST_CLEAN_DISK_TIME, 0L);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean a(Long l) {
        synchronized (this.f) {
            if (l == null) {
                return false;
            }
            long bY = bY();
            if (bY == -1) {
                return true;
            }
            return System.currentTimeMillis() < (bY * 86400000) + l.longValue();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void a(boolean z) {
        synchronized (this.f) {
            this.f6847a.edit().putBoolean(SpKeys.USE_POST_AT_FRONT, z).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void a(Set<String> set) {
        synchronized (this.f) {
            SharedPreferences.Editor edit = this.f6847a.edit();
            if (com.huawei.openalliance.ad.utils.bg.a(set)) {
                edit.putStringSet("scheme_info", null);
            } else {
                edit.putStringSet("scheme_info", set);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        synchronized (this.i) {
            SharedPreferences.Editor edit = this.l.edit();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                edit.putString(entry.getKey(), entry.getValue());
                this.o.put(entry.getKey(), entry.getValue());
                if (ho.a()) {
                    ho.a("SpHandler", "save base url to SP: %s", com.huawei.openalliance.ad.utils.dl.a(entry.getValue()));
                }
            }
            ho.b("SpHandler", "save result: " + edit.commit());
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void a(List<String> list, Map<String, Boolean> map) {
        synchronized (this.s) {
            if (list != null) {
                try {
                    this.q.a(com.huawei.openalliance.ad.utils.bg.a(list, ","));
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (map != null) {
                this.q.a(map);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.eq
    public void a(List<String> list) {
        super.a(list);
    }

    @Override // com.huawei.openalliance.ad.gc
    public void a(String str, boolean z) {
        synchronized (this.s) {
            this.q.a(str, z);
            com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.fh.2
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (fh.this.s) {
                        com.huawei.openalliance.ad.utils.cs.a(fh.this.q, fh.this.r);
                    }
                }
            });
        }
    }

    @Override // com.huawei.openalliance.ad.eq, com.huawei.openalliance.ad.gc
    public void a(String str, AppConfigRsp appConfigRsp, boolean z) {
        super.a(str, appConfigRsp, z);
    }

    @Override // com.huawei.openalliance.ad.gc
    public void a(String str, OaidRecord oaidRecord) {
        if (ho.a()) {
            ho.a("SpHandler", "saveOaidRecord, key: %s", str);
        }
        if (TextUtils.isEmpty(str) || oaidRecord == null) {
            return;
        }
        String b = com.huawei.openalliance.ad.utils.be.b(oaidRecord);
        if (TextUtils.isEmpty(b)) {
            ho.c("SpHandler", "oaid record is null");
            return;
        }
        synchronized (this.f) {
            SharedPreferences.Editor edit = this.f6847a.edit();
            edit.putString(str, b);
            edit.commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public void a(String str, FlowControl flowControl) {
        String str2;
        if (com.huawei.openalliance.ad.utils.cz.b(str) || flowControl == null) {
            str2 = "fc para null";
        } else {
            String b = com.huawei.openalliance.ad.utils.be.b(flowControl);
            if (!com.huawei.openalliance.ad.utils.cz.b(b)) {
                ho.b("SpHandler", "fc para:%s", b);
                synchronized (this.f) {
                    this.f6847a.edit().putString(str, b).commit();
                }
                return;
            }
            str2 = "fc to json failed";
        }
        ho.b("SpHandler", str2);
    }

    @Override // com.huawei.openalliance.ad.eq, com.huawei.openalliance.ad.gc
    public void a(String str, long j) {
        super.a(str, j);
    }

    @Override // com.huawei.openalliance.ad.gc
    public void a(Location location) {
        synchronized (this.j) {
            this.b.edit().putString(SpKeys.LAST_KNOWN_LOCATION, com.huawei.openalliance.ad.utils.f.a(com.huawei.openalliance.ad.utils.be.b(location), com.huawei.openalliance.ad.utils.cp.b(this.m))).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.eq
    public void a(int i) {
        super.a(i);
    }

    @Override // com.huawei.openalliance.ad.gc
    public String a(Context context, String str) {
        String str2;
        String url;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String o = bz.a(context).o();
        String str3 = str + com.huawei.openalliance.ad.utils.cz.a(context) + o;
        ho.a("SpHandler", "countryCode: %s", o);
        synchronized (this.i) {
            str2 = this.o.get(str3);
            if (TextUtils.isEmpty(str2)) {
                ej a2 = ej.a(context);
                String a3 = a2.a(str);
                ho.b("SpHandler", "baseUrlKey:" + a3);
                String str4 = this.o.get(a3 + o);
                String b = a2.b(str);
                if (!TextUtils.isEmpty(str4) && !TextUtils.isEmpty(b)) {
                    url = str4 + b;
                    str2 = url;
                    this.o.put(str3, str2);
                    c(str3, str2);
                }
                if (!bz.a(context).m()) {
                    ho.b("SpHandler", "there is no url in SP, use default");
                    url = DefaultUrlConstant.getUrl(context, str);
                    str2 = url;
                }
                this.o.put(str3, str2);
                c(str3, str2);
            } else {
                com.huawei.openalliance.ad.utils.ct.a().b(context);
            }
            ho.a("SpHandler", "serverUrl= %s", com.huawei.openalliance.ad.utils.dl.a(str2));
        }
        return str2;
    }

    @Override // com.huawei.openalliance.ad.eq, com.huawei.openalliance.ad.gc
    public String a() {
        return super.a();
    }

    @Override // com.huawei.openalliance.ad.gc
    public void D(String str) {
        synchronized (this.f) {
            this.f6847a.edit().putString("adid", str).apply();
        }
    }

    @Override // com.huawei.openalliance.ad.gc
    public int C(String str) {
        int a2;
        synchronized (this.f) {
            a2 = this.n != null ? com.huawei.openalliance.ad.utils.cz.a(this.n.get(SpKeys.LOADER_ENGINE_ENGINE_INTERVAL), 10080) : 10080;
        }
        return a2 * 60000;
    }

    @Override // com.huawei.openalliance.ad.gc
    public int B(String str) {
        int a2;
        synchronized (this.f) {
            a2 = this.n != null ? com.huawei.openalliance.ad.utils.cz.a(this.n.get(SpKeys.LOADER_ENGINE_2KIT_UPDATE), 60) : 60;
        }
        return a2 * 60000;
    }

    @Override // com.huawei.openalliance.ad.gc
    public boolean A(String str) {
        boolean equals;
        synchronized (this.f) {
            equals = "1".equals(this.n != null ? this.n.get(SpKeys.LOADER_ENGINE_UPDATE) : null);
        }
        return equals;
    }

    private int cp() {
        int i;
        synchronized (this.f) {
            i = this.f6847a.getInt(SpDefaultValues.DEFAULT_SPLASH_MODE, 2);
        }
        return i;
    }

    private String co() {
        String string;
        synchronized (this.f) {
            string = this.f6847a.getString(SpKeys.GLOBAL_SWITCH, "");
        }
        return string;
    }

    private void c(final String str, final String str2) {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.fh.1
            @Override // java.lang.Runnable
            public void run() {
                fh.this.l.edit().putString(str, str2).commit();
                com.huawei.openalliance.ad.utils.ct.a().b(fh.this.m);
            }
        });
    }

    private static gc c(Context context) {
        gc gcVar;
        synchronized (w) {
            if (v == null) {
                v = new fh(context);
            }
            gcVar = v;
        }
        return gcVar;
    }

    public static gc b(Context context) {
        return c(context);
    }

    private fh(Context context) {
        super(context);
    }
}
