package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.util.HwWatchFaceUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.api.WebViewActivity;
import com.huawei.watchface.mvp.model.datatype.WatchFaceListBean;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSupportInfo;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class jls {

    /* renamed from: a, reason: collision with root package name */
    private static WatchFaceListBean f13945a;

    public static Map<String, String> d(String str) {
        return HwWatchFaceApi.getInstance(BaseApplication.getContext()).getWatchFaceUrlMap(str) != null ? HwWatchFaceApi.getInstance(BaseApplication.getContext()).getWatchFaceUrlMap(str) : new HashMap(16);
    }

    public static void bHW_(Activity activity, int i) {
        HwWatchFaceApi.getInstance(BaseApplication.getContext()).gotoDetailOrMarket(activity, i);
    }

    public static boolean b(Context context) {
        WatchFaceSupportInfo watchFaceSupportInfo = HwWatchFaceBtManager.getInstance(context).getWatchFaceSupportInfo();
        LogUtil.c("WatchFaceAarUtil", "isCircleWatchFace()");
        if (watchFaceSupportInfo != null) {
            return watchFaceSupportInfo.getWatchFaceHeight() == watchFaceSupportInfo.getWatchFaceWidth();
        }
        LogUtil.h("WatchFaceAarUtil", "isCircleWatchFace(), watchFaceInfo is null.");
        return false;
    }

    public static void e(Context context) {
        if (!tri.d(context)) {
            LogUtil.h("WatchFaceAarUtil", "initWatchfaceAdapter is not authorized");
        } else {
            ReleaseLogUtil.e("DEVMGR_WatchFaceAarUtil", "initWatchfaceAdapter");
            HwWatchFaceApi.getInstance(context).setAdapter(jlv.c());
        }
    }

    public static void a() {
        try {
            f13945a = (WatchFaceListBean) new Gson().fromJson(SharedPreferenceManager.j(BaseApplication.getContext()), WatchFaceListBean.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("WatchFaceAarUtil", "JsonSyntaxException");
        }
    }

    public static void bHX_(Intent intent) {
        LogUtil.a("WatchFaceAarUtil", "onReceive mWatchFaceReceiver");
        if (intent != null) {
            f13945a = (WatchFaceListBean) intent.getParcelableExtra("watchFaceBeanList");
        }
    }

    public static boolean b() {
        WatchFaceListBean watchFaceListBean = f13945a;
        return watchFaceListBean == null || watchFaceListBean.getWatchFaceBeanList() == null || f13945a.getWatchFaceBeanList().isEmpty();
    }

    public static int c() {
        WatchFaceListBean watchFaceListBean = f13945a;
        if (watchFaceListBean != null) {
            return watchFaceListBean.getWatchFaceBeanList().size();
        }
        return 0;
    }

    public static String e(int i) {
        return (f13945a == null || i >= c() || f13945a.getWatchFaceBeanList().get(i).getPreviews().size() < 2) ? "" : f13945a.getWatchFaceBeanList().get(i).getPreviews().get(1).getPreviewUrl();
    }

    public static String d(int i) {
        return (f13945a == null || i >= c()) ? "" : f13945a.getWatchFaceBeanList().get(i).getTitleLocal();
    }

    public static String b(int i) {
        return (f13945a == null || i >= c()) ? "" : f13945a.getWatchFaceBeanList().get(i).getHitopId();
    }

    public static String a(int i) {
        return (f13945a == null || i >= c()) ? "" : f13945a.getWatchFaceBeanList().get(i).getVersion();
    }

    public static void c(Context context, String str) {
        Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
        intent.putExtra("url", str);
        intent.setFlags(268435456);
        HwWatchFaceUtil.bJf_(context, intent, "WatchFaceAarUtil");
    }

    public static void b(String str, String str2, int i) {
        HwWatchFaceApi.getInstance(BaseApplication.getContext()).createBinFile(str, str2, i);
    }
}
