package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class cof {
    public static void d() {
        if (CommonUtil.aa(BaseApplication.getContext())) {
            b();
        } else {
            LogUtil.h("HonorPrivacyUtils", "querySignResult no connect network.");
            b("");
        }
    }

    public static void d(boolean z) {
        LogUtil.a("HonorPrivacyUtils", "reportSignResult isSign: ", Boolean.valueOf(z));
        b(z);
        if (!dks.d(BaseApplication.getContext())) {
            LogUtil.h("HonorPrivacyUtils", "reportSignResult current zone no need sign.");
            return;
        }
        if (!Utils.i()) {
            LogUtil.h("HonorPrivacyUtils", "reportSignResult current zone no cloud.");
        } else if (CommonUtil.aa(BaseApplication.getContext())) {
            c(z);
        } else {
            LogUtil.h("HonorPrivacyUtils", "reportSignResult no connect network.");
        }
    }

    private static void b() {
        LogUtil.a("HonorPrivacyUtils", "Enter queryHonorPrivacy.");
        ArrayList arrayList = new ArrayList(16);
        String country = Locale.getDefault().getCountry();
        jbd jbdVar = new jbd();
        jbdVar.c(1050);
        jbdVar.d(country);
        arrayList.add(jbdVar);
        jbd jbdVar2 = new jbd();
        jbdVar2.c(1051);
        jbdVar2.d(country);
        arrayList.add(jbdVar2);
        jbe.a(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011), arrayList, new ResultCallback<jbk>() { // from class: cof.5
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(jbk jbkVar) {
                cof.b(String.valueOf(System.currentTimeMillis()));
                if (jbkVar == null) {
                    LogUtil.h("HonorPrivacyUtils", "queryHonorPrivacy data is null.");
                    return;
                }
                LogUtil.a("HonorPrivacyUtils", "queryHonorPrivacy data: ", jbkVar.toString());
                if (jbkVar.e() == 0) {
                    cof.e(jbkVar);
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h("HonorPrivacyUtils", "queryHonorPrivacy onFailure.");
                cof.b("");
            }
        });
    }

    private static void c(boolean z) {
        LogUtil.a("HonorPrivacyUtils", "Enter signHonorPrivacy isSign: ", Boolean.valueOf(z));
        ArrayList arrayList = new ArrayList(16);
        String country = Locale.getDefault().getCountry();
        jbm jbmVar = new jbm();
        jbmVar.c(1050);
        jbmVar.d(country);
        jbmVar.b(z);
        arrayList.add(jbmVar);
        jbm jbmVar2 = new jbm();
        jbmVar2.c(1051);
        jbmVar2.d(country);
        jbmVar2.b(z);
        arrayList.add(jbmVar2);
        jbe.c(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011), arrayList, new ResultCallback<jbk>() { // from class: cof.4
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(jbk jbkVar) {
                if (jbkVar == null) {
                    LogUtil.h("HonorPrivacyUtils", "signHonorPrivacy data is null.");
                } else {
                    LogUtil.a("HonorPrivacyUtils", "signHonorPrivacy data: ", jbkVar.toString());
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h("HonorPrivacyUtils", "signHonorPrivacy onFailure.");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(jbk jbkVar) {
        List<jbg> d = jbkVar.d();
        boolean e = SharedPreferenceManager.e(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        if (d == null || d.size() == 0) {
            if (e) {
                LogUtil.a("HonorPrivacyUtils", "saveSignResult report sign result.");
                c(true);
                return;
            }
            return;
        }
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        boolean z = false;
        boolean z2 = false;
        for (jbg jbgVar : d) {
            if (jbgVar.e().intValue() == 1050) {
                z = jbgVar.d();
                j = jbgVar.c();
                j2 = jbgVar.b();
            } else if (jbgVar.e().intValue() == 1051) {
                z2 = jbgVar.d();
                j3 = jbgVar.c();
                j4 = jbgVar.b();
            } else {
                LogUtil.h("HonorPrivacyUtils", "saveSignResult arg type is error.");
            }
        }
        if ((!z && !z2) || !e) {
            b(true);
        } else if (j > j2 || j3 > j4) {
            b(false);
        } else {
            c(true);
        }
    }

    private static void b(boolean z) {
        SharedPreferenceManager.a(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011), z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str) {
        LogUtil.a("HonorPrivacyUtils", "saveQueryTime queryTime: ", str);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), "honor_privacy_query_time", str, (StorageParams) null);
    }
}
