package defpackage;

import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.sport.utils.ResultUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ProgressListener;
import com.huawei.operation.jsoperation.JsUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class eqb {
    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject a(File file) {
        JSONObject jSONObject = new JSONObject();
        if (file == null) {
            return jSONObject;
        }
        try {
            jSONObject.put("resultCode", 0).put("resultDesc", ResultUtil.d(0)).put(JsUtil.DATA_LIST, file.getCanonicalPath());
        } catch (IOException | JSONException e) {
            LogUtil.b("Suggestion_CloudImplHelper", "constructDownloadFinishJson() ", e.getClass().getSimpleName());
        }
        return jSONObject;
    }

    static void c(final String str, final String str2, final DataCallback dataCallback) {
        LogUtil.a("Suggestion_CloudImplHelper", "url is:", str, " path :", str2);
        if (dataCallback == null) {
            LogUtil.h("Suggestion_CloudImplHelper", "downLoad callback == null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            a(fes.class.getName(), new Exception("url is empty"), dataCallback);
            return;
        }
        if (!CommonUtil.aa(arx.b())) {
            dataCallback.onFailure(ResultUtil.ResultCode.NO_NET, arx.b().getString(R.string._2130850341_res_0x7f023225));
            return;
        }
        ProgressListener<File> progressListener = new ProgressListener<File>() { // from class: eqb.3
            @Override // com.huawei.networkclient.ProgressListener
            public void onFail(Throwable th) {
            }

            @Override // com.huawei.networkclient.ProgressListener
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onFinish(File file) {
                LogUtil.a("Suggestion_CloudImplHelper", "downloadJsonFile onFinish");
                squ.e(file.getName(), file.lastModified());
                DataCallback.this.onSuccess(eqb.a(file));
                if (file.isDirectory()) {
                    LogUtil.h("Suggestion_CloudImplHelper", "downloadFile as directory, url=", str, ", path=", str2);
                }
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onProgress(long j, long j2, boolean z) {
                DataCallback.this.onProgress(j, j2, z);
            }
        };
        File file = new File(str2);
        if (file.exists()) {
            Object[] objArr = new Object[2];
            objArr[0] = "DeleteFile : ";
            objArr[1] = file.delete() ? "Success" : "Failed";
            LogUtil.c("Suggestion_CloudImplHelper", objArr);
        }
        try {
            File parentFile = file.getParentFile();
            LogUtil.a("Suggestion_CloudImplHelper", "CreateFile ", Boolean.valueOf(parentFile != null && parentFile.mkdirs() && file.createNewFile()));
        } catch (IOException e) {
            LogUtil.b("Suggestion_CloudImplHelper", "downLoad IOException: ", LogAnonymous.b((Throwable) e));
        }
        lqi.d().d(new esz(str, c(), file, progressListener, dataCallback));
    }

    private static Map<String, String> c() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("x-huid", LoginInit.getInstance(BaseApplication.getContext()).getHuidOrDefault());
        hashMap.put("x-version", CommonUtil.c(BaseApplication.getContext()));
        return hashMap;
    }

    private static void a(String str, Throwable th, DataCallback dataCallback) {
        e(str, th, dataCallback, 9999);
    }

    public static void e(String str, Throwable th, DataCallback dataCallback, int i) {
        if (dataCallback == null) {
            LogUtil.b("Suggestion_CloudImplHelper", "onFailure the callback is null.");
            return;
        }
        if (th instanceof Exception) {
            Exception exc = (Exception) th;
            if ((exc instanceof SocketTimeoutException) || ((exc instanceof SSLException) && TextUtils.equals(exc.getMessage(), "Connection timed out"))) {
                i = 1003;
            } else if ((exc instanceof ConnectException) || (exc instanceof UnknownHostException)) {
                i = -8;
            } else if ((exc instanceof IOException) && TextUtils.equals(exc.getMessage(), "Canceled")) {
                i = -2;
            } else {
                int i2 = 500;
                if (!ResultUtil.d(500).equals(exc.getMessage())) {
                    i2 = 503;
                    if (!ResultUtil.d(503).equals(exc.getMessage())) {
                        if (exc instanceof lqj) {
                            i = ((lqj) exc).e();
                        } else {
                            ReleaseLogUtil.c("Suggestion_CloudImplHelper", "onFailure, Exception=", ExceptionUtils.d(exc), ", Msg=", exc.getMessage());
                            i = 9999;
                        }
                    }
                }
                i = i2;
            }
            dataCallback.onFailure(i, ResultUtil.d(i));
        } else {
            dataCallback.onFailure(i, th.getMessage() != null ? th.getMessage() : ResultUtil.d(i));
        }
        LogUtil.b("Suggestion_CloudImplHelper", str, "resultCode=", Integer.valueOf(i), ResultUtil.d(i), th.getMessage());
    }
}
