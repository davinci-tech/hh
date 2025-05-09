package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import com.huawei.openalliance.ad.constant.ConfigMapKeys;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.SpKeys;
import com.huawei.openalliance.ad.constant.WhiteListPkgList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class er extends eq implements fr {
    public long z() {
        Long i;
        long j;
        synchronized (this.f) {
            synchronized (this.f) {
                i = !com.huawei.openalliance.ad.utils.bl.a(this.n) ? com.huawei.openalliance.ad.utils.cz.i(this.n.get(ConfigMapKeys.OAID_REPORT_TIME_INTERVAL)) : null;
            }
            if (i != null && i.longValue() > 0) {
                j = i.longValue();
            }
            j = ConfigMapDefValues.DEFAULT_OAID_EVENT_INTENVAL;
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int y() {
        int max;
        synchronized (this.f) {
            Integer h = !com.huawei.openalliance.ad.utils.bl.a(this.n) ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.DEVICE_CONNECT_LIST_MAX_SIZE)) : null;
            max = h == null ? 20 : Math.max(0, Math.min(h.intValue(), 50));
        }
        return max;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int x() {
        int intValue;
        synchronized (this.f) {
            Integer h = !com.huawei.openalliance.ad.utils.bl.a(this.n) ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.DEVICE_CONNECT_LIST_COLLECT_INTERVAL)) : null;
            intValue = (h != null && h.intValue() > 0) ? h.intValue() : 60;
        }
        return intValue;
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean w() {
        synchronized (this.f) {
            if (!com.huawei.openalliance.ad.utils.bl.a(this.n)) {
                String str = this.n.get(ConfigMapKeys.ADDRESS_CONNECT_LIST_COLLECT_SWITCH);
                if (TextUtils.equals(str, "1")) {
                    return true;
                }
                if (TextUtils.equals(str, "0")) {
                    return false;
                }
            }
            return false;
        }
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean v() {
        synchronized (this.f) {
            if (!com.huawei.openalliance.ad.utils.bl.a(this.n)) {
                String str = this.n.get(ConfigMapKeys.DEVICE_CONNECT_LIST_COLLECT_SWITCH);
                if (TextUtils.equals(str, "1")) {
                    return true;
                }
                if (TextUtils.equals(str, "0")) {
                    return false;
                }
            }
            return false;
        }
    }

    @Override // com.huawei.openalliance.ad.fr
    public String u() {
        String c;
        synchronized (this.f) {
            c = !com.huawei.openalliance.ad.utils.bl.a(this.n) ? com.huawei.openalliance.ad.utils.cz.c(this.n.get(ConfigMapKeys.WEBVIEW_PRELOAD_CLICK_ACTION)) : null;
            if (com.huawei.openalliance.ad.utils.cz.b(c)) {
                c = Constants.WEBVIEW_PRELOAD_CLICK_ACTION;
            }
        }
        return c;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int t() {
        int intValue;
        synchronized (this.f) {
            Integer h = !com.huawei.openalliance.ad.utils.bl.a(this.n) ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.WEBVIEW_PRELOAD_NETWORK)) : null;
            intValue = (h != null && h.intValue() > 0) ? h.intValue() : 0;
        }
        return intValue;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int s() {
        int intValue;
        synchronized (this.f) {
            Integer h = !com.huawei.openalliance.ad.utils.bl.a(this.n) ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.MAX_UPLOAD_EVENT_COUNT_HISUGGESTION)) : null;
            intValue = (h != null && h.intValue() > 0) ? h.intValue() : 80;
        }
        return intValue;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int r() {
        int intValue;
        synchronized (this.f) {
            Integer h = !com.huawei.openalliance.ad.utils.bl.a(this.n) ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.WEBVIEW_PRELOAD_MAXNUM)) : null;
            intValue = (h != null && h.intValue() > 0) ? h.intValue() : 20;
        }
        return intValue;
    }

    @Override // com.huawei.openalliance.ad.fr
    public long q() {
        long longValue;
        synchronized (this.f) {
            longValue = (com.huawei.openalliance.ad.utils.bl.a(this.n) ? 150L : Long.valueOf(com.huawei.openalliance.ad.utils.cz.a(this.n.get(ConfigMapKeys.REWARD_MAX_DATA), 150L))).longValue();
        }
        return longValue * 1048576;
    }

    @Override // com.huawei.openalliance.ad.fr
    public List<String> p() {
        synchronized (this.f) {
            if (!com.huawei.openalliance.ad.utils.bl.a(this.n)) {
                String str = this.n.get(ConfigMapKeys.EVENT_BLACK_LIST);
                if (!com.huawei.openalliance.ad.utils.cz.b(str)) {
                    return Arrays.asList(str.trim().split(","));
                }
            }
            return null;
        }
    }

    @Override // com.huawei.openalliance.ad.fr
    public int o() {
        return com.huawei.openalliance.ad.utils.ba.a(b("Intv_query_insapp", ""));
    }

    @Override // com.huawei.openalliance.ad.fr
    public int n() {
        return com.huawei.openalliance.ad.utils.cj.b(b(ConfigMapKeys.LOCAL_RECALL_MAX_ADS, ""));
    }

    @Override // com.huawei.openalliance.ad.fr
    public List<String> m() {
        return com.huawei.openalliance.ad.utils.cj.a(b(ConfigMapKeys.ALLOW_CACHE_TRADE_MODE, ""));
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean l() {
        return a(ConfigMapKeys.HUAWEI_USER_RECOMMEND_ENABLED, false);
    }

    @Override // com.huawei.openalliance.ad.fr
    public int k() {
        return b(ConfigMapKeys.ALLOW_TAG_AUD_MODE, 0);
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean j() {
        return a(ConfigMapKeys.ALLOW_RPT_AUD, false);
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean i() {
        return a(ConfigMapKeys.ALLOW_RPT_CTX_TAG, false);
    }

    @Override // com.huawei.openalliance.ad.fr
    public int h() {
        return b(ConfigMapKeys.VIDEO_PLAY_TIMEOUT, 5000);
    }

    @Override // com.huawei.openalliance.ad.fr
    public int g() {
        return b(ConfigMapKeys.UNINSTALLED_APP_CACHE_QUERY_INTERVAL, 60);
    }

    @Override // com.huawei.openalliance.ad.fr
    public long f() {
        return b(ConfigMapKeys.PKG_ADD_LISTEN_TIME, 5) * 60000;
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean b(String str) {
        return a(str, 30);
    }

    @Override // com.huawei.openalliance.ad.fr
    public String b(String str, String str2) {
        synchronized (this.f) {
            if (this.n == null) {
                return str2;
            }
            String str3 = this.n.get(str);
            if (!com.huawei.openalliance.ad.utils.cz.b(str3)) {
                str2 = str3;
            }
            return str2;
        }
    }

    @Override // com.huawei.openalliance.ad.fr
    public int[] aq() {
        String b = b(ConfigMapKeys.CLICK_VALID_INTVL, "");
        if (com.huawei.openalliance.ad.utils.cz.b(b)) {
            return Constants.CLICK_DEF_INTVL;
        }
        String[] split = b.split(Constants.LINK);
        if (split.length != 2) {
            return Constants.CLICK_DEF_INTVL;
        }
        int[] iArr = new int[2];
        try {
            iArr[0] = Integer.parseInt(split[0].trim());
            int parseInt = Integer.parseInt(split[1].trim());
            iArr[1] = parseInt;
            return iArr[0] >= parseInt ? Constants.CLICK_DEF_INTVL : iArr;
        } catch (Throwable unused) {
            return Constants.CLICK_DEF_INTVL;
        }
    }

    @Override // com.huawei.openalliance.ad.fr
    public String ap() {
        return b(ConfigMapKeys.PARTNER_LIST, "");
    }

    @Override // com.huawei.openalliance.ad.fr
    public int ao() {
        return b(ConfigMapKeys.PARTNER_RULE, ConfigMapDefValues.DEF_PARTNER_RULE.intValue());
    }

    @Override // com.huawei.openalliance.ad.fr
    public Map<String, String> an() {
        Map<String, String> map;
        synchronized (this.f) {
            String packageName = this.m.getPackageName();
            Map map2 = this.n != null ? (Map) com.huawei.openalliance.ad.utils.be.b(this.n.get(ConfigMapKeys.THIRD_SHARE_APP_ID), Map.class, new Class[0]) : null;
            return (map2 == null || !map2.containsKey(packageName) || (map = (Map) com.huawei.openalliance.ad.utils.be.b((String) map2.get(packageName), Map.class, new Class[0])) == null) ? new HashMap() : map;
        }
    }

    @Override // com.huawei.openalliance.ad.fr
    public String am() {
        String str;
        synchronized (this.f) {
            str = this.n != null ? this.n.get(ConfigMapKeys.LANDING_TITLE_INVISIBLE_STR) : null;
            if (TextUtils.isEmpty(str)) {
                str = Constants.LRM_STR;
            }
        }
        return str;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int al() {
        int intValue;
        synchronized (this.f) {
            Integer h = this.n != null ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.SYNC_APP_DATA_INTV)) : null;
            intValue = h == null ? 45 : h.intValue();
        }
        return intValue;
    }

    @Override // com.huawei.openalliance.ad.fr
    public long ak() {
        long b = b(ConfigMapKeys.PLAY_END_DCT_TO, 5L);
        return (b >= 0 ? b : 5L) * 1000;
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean aj() {
        return TextUtils.equals(b(ConfigMapKeys.SET_SURFACE_FIRST, "1"), "1");
    }

    @Override // com.huawei.openalliance.ad.fr
    public int ai() {
        int i;
        synchronized (this.f) {
            i = 0;
            int a2 = (this.n == null || TextUtils.isEmpty(this.n.get(ConfigMapKeys.IPC_FLAG))) ? 0 : com.huawei.openalliance.ad.utils.cz.a(this.n.get(ConfigMapKeys.IPC_FLAG), 0);
            if (a2 == 0 || a2 == 1) {
                i = a2;
            }
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.fr
    public long ah() {
        long longValue;
        synchronized (this.f) {
            longValue = (com.huawei.openalliance.ad.utils.bl.a(this.n) ? 20L : Long.valueOf(com.huawei.openalliance.ad.utils.cz.a(this.n.get(ConfigMapKeys.DELETE_INVALID_CONTENTS_DELAY), 20L))).longValue();
        }
        return longValue * 1000;
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean ag() {
        return a(ConfigMapKeys.REPORT_OTHER_ID_WITH_GRPID, false);
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean af() {
        return a(ConfigMapKeys.REPORT_AD_REQ_GRPID, false);
    }

    @Override // com.huawei.openalliance.ad.fr
    public Map<String, String> ae() {
        Map<String, String> map;
        synchronized (this.f) {
            synchronized (this.f) {
                map = this.n != null ? (Map) com.huawei.openalliance.ad.utils.be.b(this.n.get(ConfigMapKeys.CLCT_CTX_MAP), Map.class, new Class[0]) : null;
            }
        }
        return map;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int ad() {
        int intValue;
        synchronized (this.f) {
            synchronized (this.f) {
                Integer h = this.n != null ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.CLCT_CTX_SIZE)) : null;
                intValue = (h != null && h.intValue() > 0) ? h.intValue() : 200;
            }
        }
        return intValue;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int ac() {
        int intValue;
        synchronized (this.f) {
            synchronized (this.f) {
                Integer h = this.n != null ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.CLCT_CTX_INTVL)) : null;
                intValue = (h != null && h.intValue() >= 0) ? h.intValue() : 60;
            }
        }
        return intValue;
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean ab() {
        boolean z;
        synchronized (this.f) {
            Integer h = this.n != null ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.CLCT_CTX)) : null;
            if (h != null) {
                z = true;
                if (h.intValue() == 1) {
                }
            }
            z = false;
        }
        return z;
    }

    @Override // com.huawei.openalliance.ad.fr
    public String aa() {
        synchronized (this.f) {
            String str = !com.huawei.openalliance.ad.utils.bl.a(this.n) ? this.n.get(ConfigMapKeys.EMULATOR_FILE) : null;
            return !TextUtils.isEmpty(str) ? str : "";
        }
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        synchronized (this.f) {
            long j = this.f6847a.getLong(SpKeys.LAST_CALL_METHOD_TIME_PREFIX + str, 0L);
            ho.a("ConfigMapSpHandler", "methodName is %s ,lastCallTime is %s", str, Long.valueOf(j));
            if (j <= 0) {
                return false;
            }
            if (this.n != null) {
                i = com.huawei.openalliance.ad.utils.cz.a(this.n.get(ConfigMapKeys.INTERVAL_PREFIX + str), i);
            }
            ho.a("ConfigMapSpHandler", "intvConfig is %s", Integer.valueOf(i));
            return com.huawei.openalliance.ad.utils.ao.c() < j + ((long) (i * 60000));
        }
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        List list = (List) com.huawei.openalliance.ad.utils.be.b(this.n != null ? this.n.get(ConfigMapKeys.NEED_ADD_FLAGS_APPS) : null, List.class, String.class);
        return list == null ? WhiteListPkgList.isPkgNeedAddFlags(str) : list.contains(str);
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean a(long j) {
        if (j <= 0) {
            return true;
        }
        return z() * 60000 <= System.currentTimeMillis() - j;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int a(Context context) {
        int i;
        synchronized (this.f) {
            boolean q = com.huawei.openalliance.ad.utils.ao.q(context);
            i = q ? 98 : 64;
            int i2 = q ? 119 : 85;
            Integer h = this.n != null ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.PRO_BOTTOM_MARGIN)) : null;
            if (h != null && h.intValue() >= 40 && h.intValue() <= i2) {
                i = h.intValue();
            }
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int Z() {
        synchronized (this.f) {
            Integer h = !com.huawei.openalliance.ad.utils.bl.a(this.n) ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.CLCT_PRE_REQ)) : null;
            if (h == null) {
                return 0;
            }
            return h.intValue();
        }
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean Y() {
        boolean z;
        synchronized (this.f) {
            Integer h = !com.huawei.openalliance.ad.utils.bl.a(this.n) ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.PRELOAD_DETAIL)) : null;
            z = true;
            if (h != null && 1 != h.intValue()) {
                z = false;
            }
        }
        return z;
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean X() {
        boolean z;
        synchronized (this.f) {
            Integer h = !com.huawei.openalliance.ad.utils.bl.a(this.n) ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.ENABLE_CLOSE_DIALOG)) : null;
            z = true;
            if (h != null && 1 != h.intValue()) {
                z = false;
            }
        }
        return z;
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean W() {
        boolean z;
        synchronized (this.f) {
            Integer h = !com.huawei.openalliance.ad.utils.bl.a(this.n) ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.SHOW_END_MASKING)) : null;
            if (h != null) {
                z = true;
                if (1 == h.intValue()) {
                }
            }
            z = false;
        }
        return z;
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean V() {
        synchronized (this.f) {
            if (com.huawei.openalliance.ad.utils.bl.a(this.n)) {
                return true;
            }
            if (TextUtils.equals(this.n.get(ConfigMapKeys.DIALOG_DISMISS_ON_BACK), "1")) {
                return true;
            }
            return !TextUtils.equals(r1, "0");
        }
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean U() {
        return a(ConfigMapKeys.DL_CONFIRM_CLICKABLE, false);
    }

    @Override // com.huawei.openalliance.ad.fr
    public int T() {
        return b(ConfigMapKeys.CLCT_NETWORK_KIT, 0);
    }

    @Override // com.huawei.openalliance.ad.fr
    public int S() {
        int intValue;
        synchronized (this.f) {
            synchronized (this.f) {
                Integer h = this.n != null ? com.huawei.openalliance.ad.utils.cz.h(this.n.get("reqQaidInterval")) : null;
                intValue = (h != null && h.intValue() >= 0) ? h.intValue() : 30;
            }
        }
        return intValue;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int R() {
        int intValue;
        synchronized (this.f) {
            synchronized (this.f) {
                Integer h = this.n != null ? com.huawei.openalliance.ad.utils.cz.h(this.n.get("reqQaidInterval")) : null;
                intValue = (h != null && h.intValue() >= 0) ? h.intValue() : 30;
            }
        }
        return intValue;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int Q() {
        int intValue;
        Integer h;
        synchronized (this.f) {
            intValue = (com.huawei.openalliance.ad.utils.bl.a(this.n) || this.n.get(ConfigMapKeys.INSAPPS_FILTER_SWITCH) == null || (h = com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.INSAPPS_FILTER_SWITCH))) == null || h.intValue() < 0) ? 0 : h.intValue();
        }
        return intValue;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int P() {
        int intValue;
        synchronized (this.f) {
            Integer h = this.n != null ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.PRO_RADIUS)) : null;
            intValue = (h != null && h.intValue() > 0) ? h.intValue() : 36;
        }
        return intValue;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int O() {
        int intValue;
        synchronized (this.f) {
            Integer h = this.n != null ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.PRO_TEXT_SIZE)) : null;
            intValue = (h != null && h.intValue() >= 12 && h.intValue() <= 18) ? h.intValue() : 16;
        }
        return intValue;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int N() {
        int intValue;
        synchronized (this.f) {
            Integer h = this.n != null ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.PRO_HEIGHT)) : null;
            intValue = (h != null && h.intValue() >= 40 && h.intValue() <= 70) ? h.intValue() : 56;
        }
        return intValue;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int M() {
        int intValue;
        synchronized (this.f) {
            Integer h = this.n != null ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.TWIST_ACCELERATION)) : null;
            intValue = (h != null && h.intValue() > 0) ? h.intValue() : 5;
        }
        return intValue;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int L() {
        int intValue;
        synchronized (this.f) {
            Integer h = this.n != null ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.TWIST_DEGREE)) : null;
            intValue = (h != null && h.intValue() > 0) ? h.intValue() : 15;
        }
        return intValue;
    }

    @Override // com.huawei.openalliance.ad.fr
    public String K() {
        String c;
        synchronized (this.f) {
            c = this.n != null ? com.huawei.openalliance.ad.utils.cz.c(this.n.get(ConfigMapKeys.TWIST_DESC)) : null;
        }
        return c;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int J() {
        int intValue;
        synchronized (this.f) {
            Integer h = this.n != null ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.SWIPE_DP)) : null;
            intValue = (h != null && h.intValue() > 0) ? h.intValue() : 100;
        }
        return intValue;
    }

    @Override // com.huawei.openalliance.ad.fr
    public String I() {
        String c;
        synchronized (this.f) {
            c = this.n != null ? com.huawei.openalliance.ad.utils.cz.c(this.n.get(ConfigMapKeys.SWIPE_DESC)) : null;
        }
        return c;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int H() {
        int intValue;
        synchronized (this.f) {
            Integer h = this.n != null ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.CLICK_EXTRA_AREA)) : null;
            intValue = (h != null && h.intValue() >= 0 && h.intValue() <= 24) ? h.intValue() : 3;
        }
        return intValue;
    }

    @Override // com.huawei.openalliance.ad.fr
    public String G() {
        String c;
        synchronized (this.f) {
            c = this.n != null ? com.huawei.openalliance.ad.utils.cz.c(this.n.get(ConfigMapKeys.CLICK_DESC)) : null;
        }
        return c;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int F() {
        synchronized (this.f) {
            Integer h = this.n != null ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.SPLASH_INTERACT_CFG)) : null;
            if (h != null && h.intValue() >= 0) {
                if (h.intValue() <= 4) {
                    return h.intValue();
                }
                return 0;
            }
            return 0;
        }
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean E() {
        boolean z;
        synchronized (this.f) {
            z = !TextUtils.equals(com.huawei.openalliance.ad.utils.bl.a(this.n) ? "1" : this.n.get(ConfigMapKeys.RPT_REPEATED_EVT), "0");
        }
        return z;
    }

    @Override // com.huawei.openalliance.ad.fr
    public int D() {
        int intValue;
        Long i;
        synchronized (this.f) {
            Long l = 600000L;
            if (!com.huawei.openalliance.ad.utils.bl.a(this.n) && this.n.get(ConfigMapKeys.CACHE_REFRESH_INTVL) != null && (i = com.huawei.openalliance.ad.utils.cz.i(this.n.get(ConfigMapKeys.CACHE_REFRESH_INTVL))) != null && i.longValue() > 0) {
                l = Long.valueOf(i.longValue() * 1000);
            }
            intValue = l.intValue();
        }
        return intValue;
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean C() {
        synchronized (this.f) {
            if (!com.huawei.openalliance.ad.utils.bl.a(this.n)) {
                String str = this.n.get(ConfigMapKeys.CLCT_STATDATA);
                if (TextUtils.equals(str, "1")) {
                    return true;
                }
                if (TextUtils.equals(str, "0")) {
                    return false;
                }
            }
            return bz.a(this.m).d();
        }
    }

    @Override // com.huawei.openalliance.ad.fr
    public boolean B() {
        synchronized (this.f) {
            if (!com.huawei.openalliance.ad.utils.bl.a(this.n)) {
                String str = this.n.get(ConfigMapKeys.CLCT_DYNCDATA);
                if (TextUtils.equals(str, "1")) {
                    return true;
                }
                if (TextUtils.equals(str, "0")) {
                    return false;
                }
            }
            return bz.a(this.m).d();
        }
    }

    @Override // com.huawei.openalliance.ad.fr
    public int A() {
        int intValue;
        synchronized (this.f) {
            synchronized (this.f) {
                Integer h = this.n != null ? com.huawei.openalliance.ad.utils.cz.h(this.n.get(ConfigMapKeys.TEST_CFG_REF_INTERVAL_KEY)) : null;
                intValue = (h != null && h.intValue() > 0) ? h.intValue() : 10;
            }
        }
        return intValue;
    }

    private long b(String str, long j) {
        synchronized (this.f) {
            if (this.n == null) {
                return j;
            }
            return com.huawei.openalliance.ad.utils.cz.a(this.n.get(str), j);
        }
    }

    private int b(String str, int i) {
        synchronized (this.f) {
            if (this.n == null) {
                return i;
            }
            return com.huawei.openalliance.ad.utils.cz.a(this.n.get(str), i);
        }
    }

    private boolean a(String str, boolean z) {
        synchronized (this.f) {
            if (this.n == null) {
                return z;
            }
            String str2 = this.n.get(str);
            if (!com.huawei.openalliance.ad.utils.cz.b(str2)) {
                z = "1".equals(str2);
            }
            return z;
        }
    }

    public er(Context context) {
        super(context);
    }
}
