package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.networkclient.IRequest;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class nhf {

    /* renamed from: a, reason: collision with root package name */
    private static final nhk f15283a = new nhk();

    private static <T> void c(String str, Map<String, String> map, String str2, Class<T> cls, ResultCallback<T> resultCallback) {
        LogUtil.c("SummaryCloudUtil", "callHttpPost url ", str, " headers ", map, " body ", str2);
        try {
            lqi.d().b(str, map, str2, cls, resultCallback);
        } catch (OutOfMemoryError e) {
            ReleaseLogUtil.c("R_SummaryCloudUtil", "callHttpPost error ", ExceptionUtils.d(e));
        }
    }

    private static boolean e() {
        boolean aa = CommonUtil.aa(BaseApplication.e());
        boolean i = Utils.i();
        ReleaseLogUtil.b("R_SummaryCloudUtil", "isVerify isNetworkConnected ", Boolean.valueOf(aa), " isAllowLogin ", Boolean.valueOf(i));
        return aa && i;
    }

    private static List<String> a() {
        List<String> b = b();
        b.add(MessageConstant.BLOOD_PRESSURE_TYPE);
        b.add("emotionalHealth");
        return b;
    }

    public static List<String> b() {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add("activityRings");
        arrayList.add("calorie");
        arrayList.add("activeHour");
        arrayList.add("exerciseTimeLen");
        return arrayList;
    }

    public static void c(final IRequest iRequest, final ResponseCallback<String> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_SummaryCloudUtil", "getSummaryResponse callback is null");
            return;
        }
        if (iRequest == null) {
            ReleaseLogUtil.a("R_SummaryCloudUtil", "getSummaryResponse request is null");
            return;
        }
        if (!e()) {
            responseCallback.onResponse(-1, null);
        } else {
            if (HandlerExecutor.c()) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: nhg
                    @Override // java.lang.Runnable
                    public final void run() {
                        nhf.c(IRequest.this, responseCallback);
                    }
                });
                return;
            }
            String url = iRequest.getUrl();
            nhk nhkVar = f15283a;
            c(url, nhkVar.getHeaders(), HiJsonUtil.e(nhkVar.getBody(iRequest)), String.class, new ResultCallback<String>() { // from class: nhf.1
                @Override // com.huawei.networkclient.ResultCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(String str) {
                    LogUtil.c("SummaryCloudUtil", "getSummaryResponse response ", str);
                    if (TextUtils.isEmpty(str)) {
                        ReleaseLogUtil.a("R_SummaryCloudUtil", "getSummaryResponse response is null");
                        ResponseCallback.this.onResponse(-1, null);
                        return;
                    }
                    try {
                        ResponseCallback.this.onResponse(new JSONObject(str).optInt("resultCode"), str);
                    } catch (JSONException e) {
                        String d = ExceptionUtils.d(e);
                        ReleaseLogUtil.a("SummaryCloudUtil", "getSummaryResponse exception ", d);
                        ResponseCallback.this.onResponse(-1, d);
                    }
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    ReleaseLogUtil.c("R_SummaryCloudUtil", "getSummaryResponse throwable ", ExceptionUtils.d(th));
                    ResponseCallback.this.onResponse(-1, null);
                }
            });
        }
    }

    public static void c(final ResponseCallback<nhp> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_SummaryCloudUtil", "getHighlights callback is null");
        } else {
            c(new nhw(a(), true), new ResponseCallback() { // from class: nhl
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    nhf.d(ResponseCallback.this, i, (String) obj);
                }
            });
        }
    }

    static /* synthetic */ void d(ResponseCallback responseCallback, int i, String str) {
        nhj.b(str);
        nht nhtVar = (nht) HiJsonUtil.e(str, nht.class);
        if (nhtVar == null) {
            ReleaseLogUtil.a("R_SummaryCloudUtil", "getHighlights response is null");
            responseCallback.onResponse(i, null);
        } else {
            responseCallback.onResponse(i, nhtVar.b());
        }
    }
}
