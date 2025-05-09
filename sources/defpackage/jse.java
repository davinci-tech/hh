package defpackage;

import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.RestClientGlobalInstance;
import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.upload.ErrorCodeUploadApi;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes5.dex */
public class jse {
    public static void d(String str, String str2, byte[] bArr, Map<String, String> map, IBaseResponseCallback iBaseResponseCallback) {
        Submit<String> errorCodeUploadPut;
        LogUtil.a("UploadProfileUtils", "Enter postDataToOBS");
        if (d(str)) {
            return;
        }
        try {
            ErrorCodeUploadApi errorCodeUploadApi = (ErrorCodeUploadApi) lqi.a(c()).b(ErrorCodeUploadApi.class);
            RequestBody create = RequestBody.create(map.get("Content-Type"), bArr);
            if ("POST".equals(str2)) {
                errorCodeUploadPut = errorCodeUploadApi.errorCodeUploadPost(str, map, create);
            } else {
                errorCodeUploadPut = errorCodeUploadApi.errorCodeUploadPut(str, map, create);
            }
            Response<String> execute = errorCodeUploadPut.execute();
            if (execute == null) {
                LogUtil.b("UploadProfileUtils", "postDataToObs response is null");
            } else {
                iBaseResponseCallback.d(execute.getCode(), null);
            }
        } catch (IOException e) {
            LogUtil.a("UploadProfileUtils", "doGet ", e.getMessage());
        }
    }

    private static HttpClient c() {
        RestClientGlobalInstance.getInstance().init(BaseApplication.getContext());
        return new HttpClient.Builder().connectTimeout(30000).readTimeout(60000).build();
    }

    private static boolean d(String str) {
        return str == null || str.equals("");
    }
}
