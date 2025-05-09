package com.huawei.wear.oversea.overseamanger;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.BuildConfig;
import com.huawei.wear.oversea.overseamanger.OverSeaMangerUtil;
import com.huawei.wear.oversea.satcomcard.SatcomCardMassTestConfig;
import com.huawei.wear.oversea.satcomcard.SatcomCardSupportInfo;
import defpackage.ssz;
import defpackage.std;
import defpackage.stn;
import defpackage.stq;
import defpackage.stw;
import defpackage.stz;
import defpackage.sub;
import defpackage.suc;
import defpackage.sud;
import defpackage.sue;
import defpackage.suf;
import defpackage.suj;
import defpackage.suo;
import defpackage.sur;
import defpackage.suu;
import defpackage.svb;
import defpackage.sve;
import defpackage.svg;
import defpackage.svj;
import defpackage.svt;
import defpackage.svx;
import defpackage.swb;
import defpackage.swe;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes7.dex */
public class OverSeaMangerUtil {
    private static final Object c = new Object();
    private static volatile OverSeaMangerUtil d;

    /* renamed from: a, reason: collision with root package name */
    private std f11211a;
    private int e = -1;
    private boolean b = false;

    /* loaded from: classes9.dex */
    public interface IssuerControlFlag {
        public static final int AVAILABLE = 0;
        public static final int CAN_NOT_FIND = -1;
        public static final int CLIENT_VERSION_LOW = 2;
        public static final int ROM_VERSION_AND_CLIENT_VERSION_LOW = 3;
        public static final int ROM_VERSION_LOW = 1;
    }

    /* loaded from: classes9.dex */
    public interface SatcomCardExistStauts {
        public static final int EXIST = 0;
        public static final int NOT_EXIST = 1;
        public static final int UNKNOWN = -1;
    }

    private void d() {
    }

    private void g() {
    }

    private OverSeaMangerUtil(Context context) {
        Log.i("OverSeaMangerUtil", "context=" + context);
        ssz.d(context instanceof Activity ? context.getApplicationContext() : context);
    }

    public static OverSeaMangerUtil c(Context context) {
        if (d == null) {
            synchronized (c) {
                if (d == null) {
                    d = new OverSeaMangerUtil(context);
                }
            }
        }
        if (context != null) {
            ssz.d(context);
        } else {
            Log.e("OverSeaMangerUtil", "getInstance input incorrect context");
        }
        return d;
    }

    private void e(std stdVar, Context context) {
        this.b = b(context);
        this.f11211a = stdVar;
    }

    public void a(std stdVar, final OverSeaQueryCallBack overSeaQueryCallBack) {
        if (stdVar != null) {
            d(stdVar);
            if (!e(stdVar)) {
                overSeaQueryCallBack.onFail(5);
                stq.c("OverSeaMangerUtil", "querySupportOverSeaWalletUtil checkParams returns error ", false);
                return;
            }
            svg.a().c(ssz.e());
            e(stdVar, ssz.e());
            stq.c("OverSeaMangerUtil", "querySupportOverSeaWalletUtil, isDebugBuild=" + this.b, false);
            svt.e().a(new Runnable() { // from class: com.huawei.wear.oversea.overseamanger.OverSeaMangerUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    int n = OverSeaMangerUtil.this.n();
                    if (n == 0) {
                        stq.c("OverSeaMangerUtil", "querySupportOverSeaWalletUtil onSuccess = " + n, false);
                        overSeaQueryCallBack.onSuccess(n);
                        return;
                    }
                    stq.c("OverSeaMangerUtil", "querySupportOverSeaWalletUtil onFail = " + n, false);
                    overSeaQueryCallBack.onFail(n);
                }
            });
        }
    }

    public void e(Context context, std stdVar, final SatcomQueryCallBack satcomQueryCallBack) {
        if (context != null) {
            ssz.d(context);
        } else {
            Log.e("OverSeaMangerUtil", "querySupportSatcomCardUtil input incorrect context");
        }
        if (stdVar != null) {
            d(stdVar);
            if (!e(stdVar)) {
                SatcomCardSupportInfo satcomCardSupportInfo = new SatcomCardSupportInfo();
                satcomCardSupportInfo.e(5);
                satcomQueryCallBack.onFail(satcomCardSupportInfo);
                stq.c("OverSeaMangerUtil", "querySupportSatcomCardUtil checkParams returns error ", false);
                return;
            }
            svg.a().c(ssz.e());
            e(stdVar, ssz.e());
            stq.c("OverSeaMangerUtil", "querySupportSatcomCardUtil, isDebugBuild=" + this.b, false);
            svt.e().a(new Runnable() { // from class: com.huawei.wear.oversea.overseamanger.OverSeaMangerUtil.5
                @Override // java.lang.Runnable
                public void run() {
                    SatcomCardSupportInfo i = OverSeaMangerUtil.this.i();
                    if (i.e() == 0) {
                        stq.b("OverSeaMangerUtil", "querySupportSatcomCardUtil onSuccess = " + i.e());
                        satcomQueryCallBack.onSuccess(i);
                        return;
                    }
                    stq.b("OverSeaMangerUtil", "querySupportSatcomCardUtil onFail = " + i.e());
                    satcomQueryCallBack.onFail(i);
                }
            });
        }
    }

    public void e(Context context, std stdVar, final SatcomExistInfoCallback satcomExistInfoCallback) {
        if (satcomExistInfoCallback == null) {
            Log.e("OverSeaMangerUtil", "queryNeedShowSatcomOobe input callback invalid");
            return;
        }
        if (context != null) {
            ssz.d(context);
            if (stdVar != null) {
                if (!e(stdVar) || TextUtils.isEmpty(stdVar.b()) || TextUtils.isEmpty(stdVar.i())) {
                    satcomExistInfoCallback.onResult(-1, -1);
                    stq.c("OverSeaMangerUtil", "queryNeedShowSatcomOobe checkParams returns error ", false);
                    return;
                } else {
                    svg.a().c(ssz.e());
                    e(stdVar, ssz.e());
                    stq.c("OverSeaMangerUtil", "queryNeedShowSatcomOobe", false);
                    svt.e().a(new Runnable() { // from class: stu
                        @Override // java.lang.Runnable
                        public final void run() {
                            OverSeaMangerUtil.this.a(satcomExistInfoCallback);
                        }
                    });
                    return;
                }
            }
            satcomExistInfoCallback.onResult(-1, -1);
            return;
        }
        Log.e("OverSeaMangerUtil", "queryNeedShowSatcomOobe input incorrect context");
        satcomExistInfoCallback.onResult(-1, -1);
    }

    public /* synthetic */ void a(SatcomExistInfoCallback satcomExistInfoCallback) {
        satcomExistInfoCallback.onResult(0, !l() ? 1 : 0);
    }

    private boolean e(String str) {
        return "1".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SatcomCardSupportInfo i() {
        SatcomCardSupportInfo satcomCardSupportInfo = new SatcomCardSupportInfo();
        if (this.f11211a == null) {
            satcomCardSupportInfo.e(5);
            return satcomCardSupportInfo;
        }
        boolean z = false;
        satcomCardSupportInfo.e(0);
        if (o() != 0) {
            satcomCardSupportInfo.a(false);
            return satcomCardSupportInfo;
        }
        boolean a2 = stn.a(this.f11211a, "satcomcard");
        boolean d2 = d("Oversea_Wear_SatcomCard");
        if (!a2 || !d2) {
            satcomCardSupportInfo.a(false);
            return satcomCardSupportInfo;
        }
        if (a2 && d2) {
            z = true;
        }
        satcomCardSupportInfo.a(z);
        satcomCardSupportInfo.d(m());
        return satcomCardSupportInfo;
    }

    private int o() {
        std stdVar = this.f11211a;
        if (stdVar == null) {
            return 5;
        }
        if (!stdVar.p() || e(this.f11211a.h())) {
            return 0;
        }
        if (s() && svx.d()) {
            return y();
        }
        e(k());
        stq.b("OverSeaMangerUtil", "querySupportOverSeaWallet end " + this.e);
        return this.e;
    }

    private boolean s() {
        if (Math.abs(System.currentTimeMillis() - suj.b(ssz.e()).d("query_wallet_entrance_control_end_time_sub_sdk", (Long) 0L).longValue()) >= 3600000) {
            stq.b("OverSeaMangerUtil", "isWalletEntranceControlNeedQueryServer need query server");
            return true;
        }
        if (suj.b(ssz.e()).d("query_wallet_entrance_control_result_sub_sdk", -1) != -1) {
            return false;
        }
        stq.b("OverSeaMangerUtil", "isWalletEntranceControlNeedQueryServer sp invalid");
        return true;
    }

    private void d(std stdVar) {
        int b = swe.b(stdVar.n(), 0);
        if (d(stdVar.f(), b)) {
            h();
            if (!TextUtils.isEmpty(stdVar.f())) {
                suj.b(ssz.e()).a("DEVICE_ROM_VERSION_SUB_SDK", stdVar.f());
            }
            if (b != 0) {
                suj.b(ssz.e()).c("HEALTH_APP_VERSION_FOR_WEAR_WALLET_SUB_SDK", b);
            }
        }
    }

    private boolean d(String str, int i) {
        String e = suj.b(ssz.e()).e("DEVICE_ROM_VERSION_SUB_SDK", "");
        int d2 = suj.b(ssz.e()).d("HEALTH_APP_VERSION_FOR_WEAR_WALLET_SUB_SDK", -1);
        stq.b("OverSeaMangerUtil", "needUpdateCache deviceRomVersion=" + str + ", healthAppVersion=" + i + ", deviceRomVersionCache=" + e + ", healthAppVersionCache=" + d2);
        return (d2 == i && e.equals(str)) ? false : true;
    }

    private void h() {
        d();
        g();
        f();
        j();
    }

    private void f() {
        stn.e();
        suj.b(ssz.e()).a("query_wallet_entrance_control_end_time_sub_sdk", (Long) 0L);
        suj.b(ssz.e()).c("query_wallet_entrance_control_result_sub_sdk", -1);
        c();
    }

    private void j() {
        suj.b(ssz.e()).c("satcom_card_exist", -1);
        suj.b(ssz.e()).a("satocm_card_exist_save_time", (Long) 0L);
    }

    private void c() {
        if (suj.b(ssz.e()).a("support_business_oversea")) {
            suj.b(ssz.e()).e("support_business_oversea");
        }
        if (suj.b(ssz.e()).a("support_business_query_end_time_oversea")) {
            suj.b(ssz.e()).e("support_business_query_end_time_oversea");
        }
    }

    private int m() {
        if (!svx.d()) {
            return v();
        }
        if (r()) {
            if (p()) {
                return v();
            }
            return v();
        }
        return v();
    }

    private int v() {
        String b = b();
        if (!swe.b((CharSequence) b, true) && b.contains("|")) {
            String[] split = b.split("\\|");
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            int parseInt3 = Integer.parseInt(swb.e(new Date(System.currentTimeMillis()), "yyyyMMdd"));
            if (parseInt3 >= parseInt && parseInt3 <= parseInt2) {
                return 1;
            }
        }
        return 0;
    }

    public String b() {
        return suj.b(ssz.e()).e("Mass_Test_Period", "");
    }

    private boolean r() {
        if (Math.abs(System.currentTimeMillis() - suj.b(ssz.e()).d("Test_Period_Query_End_Time", (Long) 0L).longValue()) < 3600000) {
            return false;
        }
        stq.b("OverSeaMangerUtil", "need query mass test info from server");
        return true;
    }

    private boolean p() {
        sur surVar = new sur();
        surVar.c("hwpass.wallet.satcomcard");
        suf processTask = new sve(ssz.e(), svg.a().d("query.pass.type", "WalletPass", this.f11211a.n(), this.f11211a.d(), ssz.e())).processTask(surVar);
        if (processTask == null || processTask.g != 0) {
            return false;
        }
        return b(processTask);
    }

    private boolean b(suf sufVar) {
        String e = sufVar.e();
        try {
            if (TextUtils.isEmpty(e)) {
                stq.b("OverSeaMangerUtil", "querySatcomActivityPeriodFromServer, reserve is null");
                return false;
            }
            SatcomCardMassTestConfig satcomCardMassTestConfig = (SatcomCardMassTestConfig) new Gson().fromJson(e, SatcomCardMassTestConfig.class);
            if (satcomCardMassTestConfig == null) {
                stq.a("OverSeaMangerUtil", "querySatcomActivityPeriodFromServer, config is null");
                return false;
            }
            suj.b(ssz.e()).a("Mass_Test_Period", satcomCardMassTestConfig.a());
            suj.b(ssz.e()).a("Test_Period_Query_End_Time", Long.valueOf(System.currentTimeMillis()));
            suj.b(ssz.e()).c("BeiDou_Audit_Cycle", satcomCardMassTestConfig.d());
            suj.b(ssz.e()).c("Beidou_Audit_Wait_Day", satcomCardMassTestConfig.c());
            return true;
        } catch (JsonSyntaxException unused) {
            stq.e("OverSeaMangerUtil", "querySatcomActivityPeriodFromServer JsonSyntaxException");
            return false;
        } catch (NumberFormatException unused2) {
            stq.e("OverSeaMangerUtil", "querySatcomActivityPeriodFromServer NumberFormatException");
            return false;
        }
    }

    private boolean l() {
        stq.b("OverSeaMangerUtil", "isHasIssuedSatcomCard begin.");
        if (!t()) {
            return suj.b(ssz.e()).d("satcom_card_exist", -1) == 0;
        }
        suo suoVar = new suo();
        suoVar.e = this.f11211a.b();
        suoVar.a(BuildConfig.WALLET_MERCHANT_ID);
        suoVar.c(-1);
        suoVar.d("2");
        sud processTask = new svb(ssz.e(), svg.d(ssz.e(), "VIRTUALCARD", this.f11211a.d())).processTask(suoVar);
        if (processTask != null && processTask.g == 0) {
            stq.b("OverSeaMangerUtil", "isHasIssuedSatcomCard response success");
            suj.b(ssz.e()).a("satocm_card_exist_save_time", Long.valueOf(System.currentTimeMillis()));
            if (processTask.d() == 0) {
                stq.b("OverSeaMangerUtil", "isHasIssuedSatcomCard 1 NOT exist issued satcom card");
                suj.b(ssz.e()).c("satcom_card_exist", 1);
                return false;
            }
            Iterator<sue> it = processTask.e().iterator();
            while (it.hasNext()) {
                if ("satcomcard".equals(it.next().b())) {
                    suj.b(ssz.e()).c("satcom_card_exist", 0);
                    stq.b("OverSeaMangerUtil", "isHasIssuedSatcomCard has issued satcom card");
                    return true;
                }
            }
            stq.b("OverSeaMangerUtil", "isHasIssuedSatcomCard 2 NOT exist issued satcom card");
            suj.b(ssz.e()).c("satcom_card_exist", 1);
            return false;
        }
        stq.b("OverSeaMangerUtil", "isHasIssuedSatcomCard response fail");
        return false;
    }

    private boolean t() {
        if (Math.abs(suj.b(ssz.e()).d("satocm_card_exist_save_time", (Long) 0L).longValue() - System.currentTimeMillis()) >= 3600000) {
            stq.b("OverSeaMangerUtil", "needQuerySatomExistFromServer cache overTimeLimited");
            return true;
        }
        if (suj.b(ssz.e()).d("satcom_card_exist", -1) != -1) {
            return false;
        }
        stq.b("OverSeaMangerUtil", "needQuerySatomExistFromServer cache invalid");
        return true;
    }

    private boolean d(String str) {
        String e;
        stq.b("OverSeaMangerUtil", "isSupportBusiness " + str);
        if (svx.d() && q()) {
            stq.b("OverSeaMangerUtil", "isSupportBusiness query from server");
            e = d(svg.d(ssz.e(), "VIRTUALCARD", this.f11211a.d()), this.f11211a.d());
            stq.b("OverSeaMangerUtil", "isSupportBusiness serverResult=" + e);
        } else {
            stq.b("OverSeaMangerUtil", "isSupportBusiness use cache");
            e = suj.b(ssz.e()).e("support_business_oversea", "");
        }
        if (TextUtils.isEmpty(e)) {
            stq.b("OverSeaMangerUtil", "isSupportBusiness cache invalid return NOT support");
            return false;
        }
        boolean a2 = a(str, a(e));
        stq.b("OverSeaMangerUtil", "isSupportBusiness type=" + str + ", result=" + a2);
        return a2;
    }

    private boolean q() {
        long abs = Math.abs(System.currentTimeMillis() - suj.b(ssz.e()).d("support_business_query_end_time_oversea", (Long) 0L).longValue());
        String e = suj.b(ssz.e()).e("support_business_oversea", "");
        if (abs >= 3600000) {
            stq.b("OverSeaMangerUtil", "needQueryBusinessFromServer overTimeLimitation");
            return true;
        }
        if (!TextUtils.isEmpty(e)) {
            return false;
        }
        stq.b("OverSeaMangerUtil", "needQueryBusinessFromServer cache invalid");
        return true;
    }

    private String d(String str, String str2) {
        svj svjVar = new svj(ssz.e(), str);
        suu suuVar = new suu();
        suuVar.c(str2);
        suuVar.d(this.f11211a.a());
        suc processTask = svjVar.processTask(suuVar);
        if (processTask.g != 0) {
            return "";
        }
        String d2 = processTask.d();
        c(d2);
        return d2;
    }

    private void c(String str) {
        suj.b(ssz.e()).a("support_business_oversea", str);
        suj.b(ssz.e()).a("support_business_query_end_time_oversea", Long.valueOf(System.currentTimeMillis()));
    }

    public boolean a(String str, Map<String, String> map) {
        String str2;
        if (map == null || (str2 = map.get(str)) == null) {
            return false;
        }
        return str2.equals("1");
    }

    private Map<String, String> a(String str) {
        HashMap hashMap = new HashMap();
        if (str != null && !TextUtils.isEmpty(str)) {
            hashMap = new HashMap();
            for (String str2 : str.split("\\|")) {
                if (str2 != null && !TextUtils.isEmpty(str2)) {
                    String[] split = str2.split(":");
                    if (split.length > 1) {
                        hashMap.put(split[0], split[1]);
                    }
                }
            }
        }
        return hashMap;
    }

    private boolean e(std stdVar) {
        if (TextUtils.isEmpty(stdVar.d())) {
            stq.b("OverSeaMangerUtil", "checkParams countryCode is null ");
            return false;
        }
        if (TextUtils.isEmpty(stdVar.m())) {
            stq.b("OverSeaMangerUtil", "checkParams userid is null ");
            return false;
        }
        if (TextUtils.isEmpty(stdVar.c())) {
            stq.b("OverSeaMangerUtil", "checkParams appID is null ");
            return false;
        }
        if (TextUtils.isEmpty(stdVar.a())) {
            stq.b("OverSeaMangerUtil", "checkParams certModel is null ");
            return false;
        }
        if (TextUtils.isEmpty(stdVar.n())) {
            stq.b("OverSeaMangerUtil", "checkParams softVersion is null ");
            return false;
        }
        b(stdVar);
        return true;
    }

    private void b(std stdVar) {
        stq.b("OverSeaMangerUtil", "debugPrintHealthInfo healthAppVersion=" + stdVar.n() + ", watchWalletVersion=" + stdVar.g() + ", deviceRomVersion=" + stdVar.f() + ", newCtl=" + stdVar.p() + ", otaType=" + stdVar.h());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int n() {
        std stdVar = this.f11211a;
        if (stdVar == null) {
            return 5;
        }
        if (!stdVar.p() || e(this.f11211a.h())) {
            return 0;
        }
        if (!svx.d()) {
            stq.b("OverSeaMangerUtil", "querySupportOverSeaWallet, no network.");
            return 7;
        }
        if (stn.a(this.f11211a, "over_sea_vip_card")) {
            return 0;
        }
        return y();
    }

    private int y() {
        String d2 = this.f11211a.d();
        String n = this.f11211a.n();
        String f = this.f11211a.f();
        String a2 = this.f11211a.a();
        if (!svx.d()) {
            stq.b("OverSeaMangerUtil", "querySupportOverSeaWallet,  no network.");
            return 7;
        }
        if (TextUtils.isEmpty(a2) || TextUtils.isEmpty(f) || TextUtils.isEmpty(n) || TextUtils.isEmpty(d2)) {
            return 5;
        }
        Log.i("OverSeaMangerUtil", "mContext=" + ssz.e());
        String d3 = d(ssz.e(), "/api/v1/client/wearQueryHuaweiPayUsability", "/app/gateway");
        stw stwVar = new stw();
        stwVar.c(a2);
        stwVar.e(f);
        stwVar.d(n);
        stwVar.b(d2);
        stq.b("OverSeaMangerUtil", "romVersion = " + f + " terminal = " + a2 + " wearClientVersion = " + n);
        stz processTask = new sub(ssz.e(), d3).processTask(stwVar);
        StringBuilder sb = new StringBuilder("querySupportOverSeaWallet = ");
        sb.append(processTask.g);
        stq.b("OverSeaMangerUtil", sb.toString());
        b(processTask.g);
        e(processTask.g);
        stq.b("OverSeaMangerUtil", "querySupportOverSeaWallet end " + this.e);
        return this.e;
    }

    private void e(int i) {
        if (i == -12 || i == -11) {
            this.e = 6;
            return;
        }
        if (i == 0) {
            this.e = 0;
            return;
        }
        if (i == 1) {
            this.e = 1;
            return;
        }
        if (i == 2) {
            this.e = 2;
            return;
        }
        if (i == 3) {
            this.e = 3;
        } else if (i == 4) {
            this.e = 4;
        } else {
            this.e = 8;
        }
    }

    private static void b(int i) {
        suj.b(ssz.e()).c("query_wallet_entrance_control_result_sub_sdk", i);
        suj.b(ssz.e()).a("query_wallet_entrance_control_end_time_sub_sdk", Long.valueOf(System.currentTimeMillis()));
    }

    private static int k() {
        return suj.b(ssz.e()).d("query_wallet_entrance_control_result_sub_sdk", 8);
    }

    private static String d(Context context, String str, String str2) {
        int i;
        String str3;
        svg.a();
        String d2 = svg.d(context, "VIRTUALCARD", c(ssz.e()).e().d());
        try {
            i = Integer.parseInt(c(ssz.e()).e().n());
        } catch (NumberFormatException unused) {
            stq.b("OverSeaMangerUtil", "getNewAddressUrl NumberFormatException");
            i = 0;
        }
        stq.b("OverSeaMangerUtil", "getNewAddressUrl  " + i);
        if (!TextUtils.isEmpty(d2) && d2.contains(str2) && d2.endsWith(str2)) {
            str3 = d2.replace(str2, str);
        } else {
            str3 = d2 + str;
        }
        return str3 + "?clientVersion=" + i;
    }

    private static boolean b(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public std e() {
        std stdVar = this.f11211a;
        return stdVar == null ? new std() : stdVar;
    }

    public boolean a() {
        return this.b;
    }
}
