package defpackage;

import android.text.TextUtils;
import com.huawei.health.themecloud.ThemeCloudApi;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.ResultCallback;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class git {
    private static volatile git e;
    private final ThemeCloudApi c = (ThemeCloudApi) lqi.d().b(ThemeCloudApi.class);
    private final gio d = new gio();

    private git() {
    }

    public static git e() {
        if (e == null) {
            e = new git();
        }
        return e;
    }

    public void d(giu giuVar, Map<String, String> map, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("ThemeCloudManager", "enter productQueryByType");
        Map<String, String> headers = this.d.getHeaders();
        if (!giuVar.a().isEmpty()) {
            headers.putAll(giuVar.a());
        }
        String e2 = e(giuVar.a());
        if (!TextUtils.isEmpty(e2)) {
            headers.put(WatchFaceConstant.X_SIGN, e2);
        }
        this.c.productQueryByType(giuVar.getUrl(), headers, map).enqueue(new b(iBaseResponseCallback));
    }

    private String e(Map<String, String> map) {
        String str;
        Response<gix> execute;
        gis gisVar = new gis();
        Map<String, String> headers = this.d.getHeaders();
        if (!map.isEmpty()) {
            headers.putAll(map);
        }
        HashMap hashMap = new HashMap();
        if (map.containsKey("deviceid")) {
            hashMap.put(JsbMapKeyNames.H5_USER_ID, map.get("deviceid"));
        }
        if (map.containsKey("x-devicemodel")) {
            hashMap.put("phoneType", map.get("x-devicemodel"));
        }
        try {
            execute = this.c.getSign(gisVar.getUrl(), headers, gisVar.e(hashMap)).execute();
        } catch (IOException e2) {
            LogUtil.b("ThemeCloudManager", "getWatchSign IOException:", e2.getMessage());
        }
        if (execute.isOK() && execute.getBody() != null) {
            str = execute.getBody().d();
            LogUtil.a("ThemeCloudManager", "getWatchSign sign:", str);
            return str;
        }
        str = "";
        LogUtil.a("ThemeCloudManager", "getWatchSign sign:", str);
        return str;
    }

    static class b implements ResultCallback {
        private IBaseResponseCallback d;

        b(IBaseResponseCallback iBaseResponseCallback) {
            this.d = iBaseResponseCallback;
        }

        @Override // com.huawei.hms.framework.network.restclient.ResultCallback
        public void onResponse(Response response) {
            if (response != null && (response.getBody() instanceof giy)) {
                giy giyVar = (giy) response.getBody();
                String e = giyVar.e();
                LogUtil.a("ThemeCloudManager", "ThemeCloudCallBack resultCode= ", e, ",resultInfo=", giyVar.b());
                if (this.d == null) {
                    return;
                }
                if (!"00000".equals(e)) {
                    this.d.d(-1, null);
                    return;
                } else {
                    this.d.d(0, giyVar);
                    return;
                }
            }
            LogUtil.b("ThemeCloudManager", "ThemeCloudCallBack rsp is null");
            IBaseResponseCallback iBaseResponseCallback = this.d;
            if (iBaseResponseCallback == null) {
                return;
            }
            iBaseResponseCallback.d(-1, null);
        }

        @Override // com.huawei.hms.framework.network.restclient.ResultCallback
        public void onFailure(Throwable th) {
            LogUtil.b("ThemeCloudManager", "ThemeCloudCallBack IOException ", th.getMessage());
            IBaseResponseCallback iBaseResponseCallback = this.d;
            if (iBaseResponseCallback == null) {
                return;
            }
            iBaseResponseCallback.d(-1, null);
        }
    }
}
