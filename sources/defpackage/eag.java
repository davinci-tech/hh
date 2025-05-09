package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.knit.bff.impl.IResourceRequestCallback;
import com.huawei.health.knit.data.KnitDataProvider;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.CommonUtil;
import health.compact.a.ReflectionUtils;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class eag {
    public static boolean d(int i) {
        return i == 23;
    }

    public static void e(WeakReference<Context> weakReference, final List<Integer> list, final IResourceRequestCallback iResourceRequestCallback) {
        if (koq.b(list)) {
            LogUtil.h("BffResTrigger", "resPosIdList is null.");
            iResourceRequestCallback.onFailure(FitnessStatusCodes.APP_MISMATCH, "resPosIdList is invalid");
            return;
        }
        if (weakReference == null) {
            LogUtil.h("BffResTrigger", "context is null.");
            iResourceRequestCallback.onFailure(FitnessStatusCodes.APP_MISMATCH, "weakReference is invalid");
            return;
        }
        Context context = weakReference.get();
        if (context == null) {
            LogUtil.a("BffResTrigger", "context is null.");
            iResourceRequestCallback.onFailure(FitnessStatusCodes.APP_MISMATCH, "context is invalid");
            return;
        }
        if (LoginInit.getInstance(context).isBrowseMode() || LoginInit.getInstance(context).isKidAccount() || Utils.l() || CommonUtil.bu()) {
            LogUtil.a("BffResTrigger", "isKidAccount or isOverseaNoCloudVersion or isStoreDemoVersion, return");
            iResourceRequestCallback.onFailure(FitnessStatusCodes.UNKNOWN_AUTH_ERROR, "isBrowseMode or isKidAccount or isOverseaNoCloudVersion or isStoreDemoVersion");
        } else if (CommonUtil.aa(context)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: eag.5
                @Override // java.lang.Runnable
                public void run() {
                    JSONObject a2 = eaa.a(list);
                    eae eaeVar = new eae();
                    eaeVar.setExtraRequestArgs(a2);
                    eaeVar.getPageResource(list, true, iResourceRequestCallback);
                }
            });
        } else {
            iResourceRequestCallback.onFailure(FitnessStatusCodes.MISSING_BLE_PERMISSION, "Network is not connected");
            LogUtil.a("BffResTrigger", "network not connected");
        }
    }

    public static KnitDataProvider e(int i, int i2) {
        if (i != 23) {
            return null;
        }
        return b(i2);
    }

    private static KnitDataProvider b(int i) {
        String str;
        switch (i) {
            case 32:
                str = "local:content//YogaRecommendCourseProvider";
                break;
            case 33:
                str = "local:content//YogaMyCourseProvider";
                break;
            case 34:
                str = "local:content//YogaSeriesCourseProvider";
                break;
            default:
                str = null;
                break;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("BffResTrigger", "dp url is invalid");
            return null;
        }
        String a2 = eai.a(str);
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        Object e = ReflectionUtils.e(a2);
        if (e instanceof KnitDataProvider) {
            return (KnitDataProvider) e;
        }
        return null;
    }
}
