package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.UiCallback;
import com.huawei.ui.main.stories.guidepage.activity.GuidePageActivity;
import com.huawei.ui.main.stories.guidepage.data.GuidePageInfo;
import com.huawei.ui.main.stories.guidepage.data.GuideResource;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class qbd {
    private static String c = null;
    private static boolean d = true;

    public static void a() {
        LogUtil.a("GuidePageManager", "enter startGuidePage");
        if (!g()) {
            LogUtil.a("GuidePageManager", "not Request");
            return;
        }
        a(true);
        if (i()) {
            LogUtil.a("GuidePageManager", "has catching data");
            k();
        } else {
            b("");
        }
    }

    private static boolean i() {
        return qbg.d() && !i(CommonUtil.e(BaseApplication.getContext()));
    }

    public static void b(final String str) {
        c = str;
        Tasks.callInBackground(new Callable<String>() { // from class: qbd.1
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public String call() throws Exception {
                return jei.b(qbd.h() + "/messageCenter/getGuide" + com.huawei.health.messagecenter.model.CommonUtil.getUrlSuffix(), qbg.b(), qbg.b(str));
            }
        }).addOnSuccessListener(new OnSuccessListener<String>() { // from class: qbd.4
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str2) {
                LogUtil.a("GuidePageManager", "enter onSuccess");
                if (!qbd.e(str2)) {
                    qbd.j(str2);
                } else {
                    LogUtil.h("GuidePageManager", "result invalid");
                }
            }
        });
    }

    public static void a(boolean z) {
        LogUtil.a("GuidePageManager", "setDisplayable", Boolean.valueOf(z));
        d = z;
    }

    private static void f(String str) {
        LogUtil.a("GuidePageManager", "enter parseResult", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("resultCode");
            if (!"0".equals(string)) {
                LogUtil.h("GuidePageManager", "handleGuidePageCloudData resultCode = ", string, ",resultDesc = ", jSONObject.getString("resultDesc"));
                return;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("guideList");
            if (jSONArray != null && jSONArray.length() >= 1) {
                GuidePageInfo guidePageInfo = (GuidePageInfo) new Gson().fromJson(jSONArray.getString(0), GuidePageInfo.class);
                List<GuideResource> resourceList = guidePageInfo.getResourceList();
                if (koq.b(resourceList)) {
                    LogUtil.a("GuidePageManager", "guideResources is empty");
                    return;
                }
                String valueOf = String.valueOf(guidePageInfo.getExpireTime());
                String e = CommonUtil.e(BaseApplication.getContext());
                if (!TextUtils.isEmpty(c)) {
                    LogUtil.a("GuidePageManager", "sHasDataVersion:", c);
                    e = c;
                }
                if (i(e)) {
                    LogUtil.a("GuidePageManager", "start downLoad");
                    qbg.e(resourceList);
                    qbg.c(e, valueOf);
                    d(resourceList);
                    return;
                }
                if (!TextUtils.isEmpty(c)) {
                    LogUtil.a("GuidePageManager", "is from message, not start activity");
                    return;
                }
                LogUtil.a("GuidePageManager", "downLoad arlready, start show");
                qbg.e(resourceList);
                a(e);
                qbg.c(e, valueOf);
                k();
                return;
            }
            LogUtil.h("GuidePageManager", "no data");
        } catch (JsonSyntaxException | JSONException unused) {
            LogUtil.b("GuidePageManager", "handleGuidePageCloudData exception.");
        }
    }

    private static void d(List<GuideResource> list) {
        ArrayList arrayList = new ArrayList(6);
        for (GuideResource guideResource : list) {
            pus pusVar = new pus();
            pusVar.b(guideResource.getResourceUrl());
            pusVar.e(qbg.a(guideResource.getResourceUrl()));
            arrayList.add(pusVar);
        }
        new pui().a(arrayList, 0L, new a());
    }

    static class a extends UiCallback<String> {
        private a() {
        }

        @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.a("GuidePageManager", "onFailure", "errorCode:", Integer.valueOf(i), " errorInfo:", str);
        }

        @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.UiCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            LogUtil.a("GuidePageManager", "onSuccess:", str);
            if (!TextUtils.isEmpty(qbd.c)) {
                qbd.a(qbd.c);
                LogUtil.a("GuidePageManager", "is from message, download success");
            } else if (BaseApplication.getActivity() == null) {
                LogUtil.h("GuidePageManager", "BaseApplication.getActivity() = null");
            } else {
                BaseApplication.getActivity().runOnUiThread(new Runnable() { // from class: qbd.a.5
                    @Override // java.lang.Runnable
                    public void run() {
                        qbd.a(CommonUtil.e(BaseApplication.getContext()));
                        qbd.k();
                    }
                });
            }
        }

        @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.UiCallback
        public boolean isCanceled() {
            return super.isCanceled();
        }

        @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.UiCallback
        public void onProgress(long j, long j2) {
            super.onProgress(j, j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void j(String str) {
        f(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void k() {
        LogUtil.a("GuidePageManager", "enter startGuideActivity:", Boolean.valueOf(!d));
        if (!d) {
            LogUtil.h("GuidePageManager", "not startGuideActivity");
            return;
        }
        j();
        try {
            Context context = BaseApplication.getContext();
            Intent intent = new Intent(context, (Class<?>) GuidePageActivity.class);
            intent.setFlags(268435456);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("GuidePageManager", "startGuideActivity: ", e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean e(String str) {
        return TextUtils.isEmpty(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String h() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("messageCenterUrl");
    }

    private static boolean g() {
        if (nsn.l() || nsn.ae(BaseApplication.getContext()) || !Utils.i()) {
            return false;
        }
        Context context = BaseApplication.getContext();
        String d2 = d();
        String e = qbg.e(CommonUtil.e(context));
        LogUtil.a("GuidePageManager", "lastDispalyAppVersion:", d2, "appVersion:", e);
        return TextUtils.isEmpty(d2) || CommonUtil.d(e, d2) != 0;
    }

    private static void j() {
        SharedPreferenceManager.e(BaseApplication.getContext(), "guide_page_display_app_version", "display_app_version", qbg.e(CommonUtil.e(BaseApplication.getContext())), (StorageParams) null);
    }

    private static String d() {
        return SharedPreferenceManager.b(BaseApplication.getContext(), "guide_page_display_app_version", "display_app_version");
    }

    public static void a(String str) {
        String e = qbg.e(str);
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        SharedPreferenceManager.e(BaseApplication.getContext(), "guide_page_download_app_version", accountInfo + "download_app_version", e, (StorageParams) null);
    }

    private static String f() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        return SharedPreferenceManager.b(BaseApplication.getContext(), "guide_page_download_app_version", accountInfo + "download_app_version");
    }

    private static boolean i(String str) {
        String e = qbg.e(str);
        String f = f();
        LogUtil.a("GuidePageManager", "resourceVersion:", f, " appVersion:", e);
        return TextUtils.isEmpty(f) || CommonUtil.d(f, e) != 0;
    }
}
