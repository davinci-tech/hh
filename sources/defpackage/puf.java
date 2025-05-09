package defpackage;

import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.sport.utils.ResultUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ProgressListener;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class puf {
    public static void b(String str, String str2, DataCallback dataCallback) {
        if (TextUtils.isEmpty(str)) {
            e(new Exception("url is empty"), dataCallback);
        } else if (CommonUtil.aa(BaseApplication.getContext())) {
            d(str, str2, dataCallback);
        } else {
            dataCallback.onFailure(ResultUtil.ResultCode.NO_NET, BaseApplication.getContext().getString(R.string._2130850341_res_0x7f023225));
        }
    }

    private static void d(final String str, String str2, final DataCallback dataCallback) {
        ProgressListener<File> progressListener = new ProgressListener<File>() { // from class: puf.1
            @Override // com.huawei.networkclient.ProgressListener
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onFinish(File file) {
                try {
                    puf.a(str, new JSONObject().put("resultCode", 0).put("resultDesc", puq.b(0)).put(JsUtil.DATA_LIST, file.getCanonicalPath()), dataCallback);
                } catch (JSONException e) {
                    LogUtil.b("CloudImplHelper", "jsonObject Exception");
                    puf.e(e, dataCallback);
                } catch (IOException e2) {
                    LogUtil.b("CloudImplHelper", "file IOException");
                    puf.e(e2, dataCallback);
                } finally {
                    LogUtil.a("CloudImplHelper", "download data finish");
                }
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onProgress(long j, long j2, boolean z) {
                dataCallback.onProgress(j, j2, z);
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onFail(Throwable th) {
                LogUtil.h("CloudImplHelper", "onFail, throwable.getMessage() = ", th.getMessage());
                if (th instanceof Exception) {
                    puf.e((Exception) th, dataCallback);
                }
            }
        };
        File file = new File(CommonUtil.c(str2));
        if (file.exists()) {
            Object[] objArr = new Object[2];
            objArr[0] = "DeleteFile : ";
            objArr[1] = file.delete() ? "Success" : "Failed";
            LogUtil.c("CloudImplHelper", objArr);
        }
        Object[] objArr2 = new Object[2];
        objArr2[0] = "CreateFile : ";
        objArr2[1] = file.mkdirs() ? "Success" : "Failed";
        LogUtil.c("CloudImplHelper", objArr2);
        lqi.d().d(new lqg(str, null, file, progressListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(String str, JSONObject jSONObject, DataCallback dataCallback) {
        int i = 9999;
        String b = puq.b(9999);
        if (jSONObject != null) {
            LogUtil.a("CloudImplHelper", "datas = ", jSONObject);
            i = jSONObject.optInt("resultCode");
            LogUtil.a("CloudImplHelper", "resultCode = ", Integer.valueOf(i));
            b = jSONObject.optString("resultDesc");
            if (i == 0) {
                if (dataCallback != null) {
                    dataCallback.onSuccess(jSONObject);
                }
                LogUtil.a("CloudImplHelper", str, jSONObject.toString());
            } else if (dataCallback != null) {
                dataCallback.onFailure(i, b);
            }
        } else if (dataCallback != null) {
            dataCallback.onFailure(9999, b);
        }
        LogUtil.a("CloudImplHelper", str, puq.b(i), b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(Exception exc, DataCallback dataCallback) {
        int i;
        if ((exc instanceof SocketTimeoutException) || ((exc instanceof SSLException) && exc.getMessage().contains("Connection timed out"))) {
            i = 1003;
        } else if ((exc instanceof ConnectException) || (exc instanceof UnknownHostException)) {
            i = -8;
        } else if ((exc instanceof IOException) && exc.getMessage().contains("Canceled")) {
            i = -2;
        } else {
            int i2 = 500;
            if (!puq.b(500).equals(exc.getMessage())) {
                i2 = 503;
                if (!puq.b(503).equals(exc.getMessage())) {
                    LogUtil.a("CloudImplHelper", "resultCode err");
                    i = 9999;
                }
            }
            i = i2;
        }
        if (dataCallback != null) {
            dataCallback.onFailure(i, puq.b(i));
        }
        LogUtil.a("CloudImplHelper", "resultCode=", Integer.valueOf(i));
    }
}
