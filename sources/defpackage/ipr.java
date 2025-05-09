package defpackage;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.view.PointerIconCompat;
import com.huawei.common.scope.AccessScopeReport;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hihealthservice.hihealthkit.util.AccountLog;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.HealthAccessTokenUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.VastAttribute;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class ipr {

    /* renamed from: a, reason: collision with root package name */
    private JSONObject f13537a;
    private String c;
    private int f = 0;
    private String e = "";
    private int i = 0;
    private Map<String, iph> d = new HashMap();
    private String j = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1015);
    private String b = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl(HealthEngineRequestManager.GRS_KEY, "com.huawei.health" + ipo.d, "");

    public ipr() {
        HMSLog.setExtLogger(new AccountLog(BaseApplication.getContext()), true);
        LogUtil.a("HMSAuth_HmsJsAuthUtil", "enter HmsJsAuthUtil");
    }

    private void b() {
        while (TextUtils.isEmpty(this.b)) {
            LogUtil.a("R_HiH_HMSAuth_HmsJsAuthUtil", "retry to get domain " + this.i + " times");
            int i = this.i + 1;
            this.i = i;
            if (i == 2) {
                this.i = 0;
                return;
            }
            this.b = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl(HealthEngineRequestManager.GRS_KEY, "com.huawei.health" + ipo.d, "");
        }
        this.i = 0;
    }

    private int e(String str) {
        final String str2;
        this.e = "";
        final String trim = str.trim();
        this.b = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl(HealthEngineRequestManager.GRS_KEY, "com.huawei.health" + ipo.d, "");
        b();
        LogUtil.a("R_HiH_HMSAuth_HmsJsAuthUtil", "mDomain = " + this.b);
        if (TextUtils.isEmpty(this.b)) {
            LogUtil.a("R_HiH_HMSAuth_HmsJsAuthUtil", "domain is empty");
            return 0;
        }
        if (this.b.startsWith("https://")) {
            str2 = this.b + "/healthkit/v1/thirdAuthInfo";
        } else {
            str2 = "https://" + this.b + "/healthkit/v1/thirdAuthInfo";
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        long currentTimeMillis = System.currentTimeMillis();
        ThreadPoolManager.d().execute(new Runnable() { // from class: ipp
            @Override // java.lang.Runnable
            public final void run() {
                ipr.this.d(str2, trim, countDownLatch);
            }
        });
        try {
            countDownLatch.await(20000L, TimeUnit.MILLISECONDS);
            if (TextUtils.isEmpty(this.e)) {
                LogUtil.a("R_HiH_HMSAuth_HmsJsAuthUtil", "mHttpResult is null");
                return 0;
            }
            LogUtil.c("HMSAuth_HmsJsAuthUtil", "get http needs ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), " ms");
            return 1;
        } catch (InterruptedException e) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsJsAuthUtil", "InterruptedException", LogAnonymous.b((Throwable) e));
            return 0;
        }
    }

    /* synthetic */ void d(String str, String str2, CountDownLatch countDownLatch) {
        String c = ips.c(str, this.j, str2);
        this.e = c;
        if (b(c)) {
            this.e = ips.c(str, this.j, str2);
        }
        LogUtil.a("HMSAuth_HmsJsAuthUtil", "mHttpResult:", this.e);
        countDownLatch.countDown();
    }

    private boolean b(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(VastAttribute.ERROR) && "401".equals(jSONObject.getString(VastAttribute.ERROR))) {
                    LogUtil.b("R_HiH_HMSAuth_HmsJsAuthUtil", "userAT is expired");
                    this.j = HealthAccessTokenUtil.getAtInstance().refreshAccessToken();
                    return true;
                }
            } catch (JSONException e) {
                ReleaseLogUtil.c("R_HiH_HMSAuth_HmsJsAuthUtil", "http response jsonObject == null", LogAnonymous.b((Throwable) e));
            }
        }
        return false;
    }

    private iph d(String str) {
        if (this.d.containsKey(str)) {
            iph iphVar = this.d.get(str);
            if (!iphVar.c()) {
                LogUtil.a("R_HiH_HMSAuth_HmsJsAuthUtil", "get AuthInfo from cache");
                return iphVar;
            }
            this.d.remove(str);
            LogUtil.a("R_HiH_HMSAuth_HmsJsAuthUtil", "AuthInfo is expired, and get AuthInfo from network");
        }
        if (e(str) != 1) {
            LogUtil.b("R_HiH_HMSAuth_HmsJsAuthUtil", "GetHttpRes code is not 200");
            return null;
        }
        LogUtil.c("HMSAuth_HmsJsAuthUtil", "getAtAuthInfo after getReq");
        if (TextUtils.isEmpty(this.e)) {
            LogUtil.b("R_HiH_HMSAuth_HmsJsAuthUtil", "mHttpResult is empty");
            return null;
        }
        return a(str);
    }

    private void c() {
        String e = KeyValDbManager.b(BaseApplication.getContext()).e("AT_SCOPE_DEAL");
        AccessScopeReport.reportErrorScope(this.j);
        if (!TextUtils.isEmpty(e) && "1".equals(e)) {
            LogUtil.a("R_HiH_HMSAuth_HmsJsAuthUtil", "come to download hmscore");
            KeyValDbManager.b(BaseApplication.getContext()).e("AT_SCOPE_DEAL", "2");
        } else if (TextUtils.isEmpty(e) || "0".equals(e)) {
            LogUtil.a("R_HiH_HMSAuth_HmsJsAuthUtil", "come to refresh rt");
            KeyValDbManager.b(BaseApplication.getContext()).e("AT_SCOPE_DEAL", "1");
        } else {
            LogUtil.a("R_HiH_HMSAuth_HmsJsAuthUtil", "already download hms core");
        }
    }

    private iph a(String str) {
        iph iphVar = new iph();
        try {
            JSONObject jSONObject = new JSONObject(this.e);
            this.f13537a = jSONObject;
            if (jSONObject.has(VastAttribute.ERROR)) {
                LogUtil.b("R_HiH_HMSAuth_HmsJsAuthUtil", "http response contain error,AT maybe not right." + this.f13537a.toString());
                if ((this.c.equals("104111665") || this.c.equals("105449157")) && "403".equals(this.f13537a.getString(VastAttribute.ERROR)) && CommonUtil.z(BaseApplication.getContext())) {
                    LogUtil.b("R_HiH_HMSAuth_HmsJsAuthUtil", "maybe At scope is satisfied");
                    c();
                }
                String e = KeyValDbManager.b(BaseApplication.getContext()).e("AT_SCOPE_DEAL");
                LogUtil.a("R_HiH_HMSAuth_HmsJsAuthUtil", "flag is " + e);
                if ((this.c.equals("104111665") || this.c.equals("105449157")) && !TextUtils.isEmpty(e) && "2".equals(e) && !CommonUtil.z(BaseApplication.getContext())) {
                    LogUtil.a("R_HiH_HMSAuth_HmsJsAuthUtil", "when download hms core, error occurs, set normal flag");
                    KeyValDbManager.b(BaseApplication.getContext()).e("AT_SCOPE_DEAL", "0");
                }
                return null;
            }
            KeyValDbManager.b(BaseApplication.getContext()).e("AT_SCOPE_DEAL", "0");
            iphVar.d(this.f13537a.getString("clientId"));
            iphVar.a(this.f13537a.getLong("expirIn"));
            iphVar.b(this.f13537a.getString("uid"));
            String string = this.f13537a.getString("scope");
            if (TextUtils.isEmpty(string)) {
                LogUtil.b("R_HiH_HMSAuth_HmsJsAuthUtil", "scopeResult is empty");
                return null;
            }
            String[] split = string.split(" ");
            ArrayList arrayList = new ArrayList(split.length);
            for (String str2 : split) {
                if (!"openid".equals(str2) && !"profile".equals(str2) && !"https://www.huawei.com/auth/account/base.profile".equals(str2)) {
                    arrayList.add(str2);
                }
            }
            iphVar.a(arrayList);
            this.d.put(str, iphVar);
            return iphVar;
        } catch (JSONException e2) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsJsAuthUtil", "http response jsonObject == null", LogAnonymous.b((Throwable) e2));
            return null;
        }
    }

    public int a(String str, String str2, int i, boolean z, Context context) {
        ReleaseLogUtil.e("R_HiH_HMSAuth_HmsJsAuthUtil", "go to check hms Permission");
        this.c = str2;
        this.j = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1015);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsJsAuthUtil", "accessToken is empty or appid is empty");
            return 1001;
        }
        ird d = ird.d(context);
        String b = z ? d.b(i) : d.a(i);
        if (ipq.e(i, z) && TextUtils.isEmpty(b)) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsJsAuthUtil", "datatype is empty or scope == null");
            return 1001;
        }
        if (TextUtils.isEmpty(this.j)) {
            ReleaseLogUtil.e("R_HiH_HMSAuth_HmsJsAuthUtil", "userAt is null,try to refresh at");
            while (TextUtils.isEmpty(this.j)) {
                if (this.f <= 2) {
                    LogUtil.a("HMSAuth_HmsJsAuthUtil", "second getUserAt");
                    this.f++;
                    this.j = HealthAccessTokenUtil.getAtInstance().refreshAccessToken();
                } else {
                    this.f = 0;
                    ReleaseLogUtil.e("R_HiH_HMSAuth_HmsJsAuthUtil", "after retry max times, still cannot get userAT");
                    return PointerIconCompat.TYPE_GRAB;
                }
            }
        }
        if (e(str, str2, i, z, b)) {
            return 0;
        }
        String e = KeyValDbManager.b(BaseApplication.getContext()).e("AT_SCOPE_DEAL");
        if (TextUtils.isEmpty(e) || !"1".equals(e)) {
            return 1001;
        }
        LogUtil.a("R_HiH_HMSAuth_HmsJsAuthUtil", "refreshRT next");
        return PointerIconCompat.TYPE_GRAB;
    }

    private boolean a(List<String> list, long j, String str, String str2, String str3) {
        if (list == null || list.size() == 0) {
            LogUtil.b("R_HiH_HMSAuth_HmsJsAuthUtil", "scopes is empty");
            return false;
        }
        if (j == 0) {
            LogUtil.b("R_HiH_HMSAuth_HmsJsAuthUtil", "expiredTime == 0");
            return false;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.b("R_HiH_HMSAuth_HmsJsAuthUtil", "uid is empty or hwUid is empty");
            return false;
        }
        if (!str.equals(str2)) {
            LogUtil.b("R_HiH_HMSAuth_HmsJsAuthUtil", "uid != huid ");
            return false;
        }
        if (!TextUtils.isEmpty(str3)) {
            return true;
        }
        LogUtil.b("R_HiH_HMSAuth_HmsJsAuthUtil", "clientId == null");
        return false;
    }

    private boolean e(String str, String str2, int i, boolean z, String str3) {
        iph d = d(str);
        if (d == null) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsJsAuthUtil", "atAuthInfo == null");
            return false;
        }
        long b = d.b();
        String a2 = d.a();
        String e = d.e();
        List<String> d2 = d.d();
        if (!a(d2, b, a2, LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011), e)) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsJsAuthUtil", "detailItems didn't pass");
            return false;
        }
        if (!e.equals(str2)) {
            ReleaseLogUtil.c("R_HiH_HMSAuth_HmsJsAuthUtil", "appid is not equals clientId:");
            return false;
        }
        LogUtil.a("HMSAuth_HmsJsAuthUtil", "expiredTime:", Long.valueOf(b), " scopes:", d2);
        List<String> a3 = ipq.a(i, z);
        String str4 = a3.get(0);
        String str5 = a3.get(1);
        String str6 = a3.get(2);
        String str7 = a3.get(3);
        boolean z2 = (!TextUtils.isEmpty(str5) && d2.contains(str5)) || (!TextUtils.isEmpty(str6) && d2.contains(str6));
        boolean contains = d2.contains(str4);
        boolean contains2 = d2.contains(str7);
        boolean contains3 = d2.contains(str3);
        ReleaseLogUtil.e("HMSAuth_HmsJsAuthUtil", "isAtomicScopePassed:", Boolean.valueOf(z2), " isHmsScopePassed:", Boolean.valueOf(contains), " isExtendScopePassed:", Boolean.valueOf(contains2), " isLocalScopePassed:", Boolean.valueOf(contains3));
        if (contains || contains3 || contains2 || z2) {
            ReleaseLogUtil.e("R_HiH_HMSAuth_HmsJsAuthUtil", "check permission success");
            return true;
        }
        ReleaseLogUtil.c("R_HiH_HMSAuth_HmsJsAuthUtil", "verify scope failed");
        return false;
    }
}
