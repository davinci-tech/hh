package com.huawei.openalliance.ad.inter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import com.huawei.hms.ads.dynamic.IObjectWrapper;
import com.huawei.hms.ads.dynamic.ObjectWrapper;
import com.huawei.hms.ads.uiengine.IGlobalUtil;
import com.huawei.hms.ads.uiengine.IPPSUiEngineCallback;
import com.huawei.openalliance.ad.bt;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.GlobalUtilKeys;
import com.huawei.openalliance.ad.constant.GlobalUtilMethods;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.dk;
import com.huawei.openalliance.ad.gk;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ae;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dc;
import com.huawei.openalliance.ad.utils.dd;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* loaded from: classes5.dex */
public class d extends IGlobalUtil.a {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f7033a = new byte[0];
    private static d b;
    private List<IPPSUiEngineCallback> c = new ArrayList();
    private Context d;

    @Override // com.huawei.hms.ads.uiengine.IGlobalUtil
    public void unregisterActivityStartCallBack(IPPSUiEngineCallback iPPSUiEngineCallback) {
        ho.b("GlobalUtil", "unregisterActivityStartCallBack");
        if (iPPSUiEngineCallback != null) {
            this.c.remove(iPPSUiEngineCallback);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.IGlobalUtil
    public void registerActivityStartCallBack(IPPSUiEngineCallback iPPSUiEngineCallback) {
        ho.b("GlobalUtil", "registerActivityStartCallBack");
        if (iPPSUiEngineCallback != null) {
            this.c.add(iPPSUiEngineCallback);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.IGlobalUtil
    public boolean isFreedomWindowMode(IObjectWrapper iObjectWrapper) {
        try {
            View view = (View) ObjectWrapper.unwrap(iObjectWrapper);
            if (view == null) {
                ho.c("GlobalUtil", "remote view is null.");
                return false;
            }
            Activity d = dd.d((View) view.getParent());
            if (d != null) {
                return dd.b(d);
            }
            ho.c("GlobalUtil", "activity is null.");
            return false;
        } catch (Throwable th) {
            ho.c("GlobalUtil", "is freedom window ex: %s", th.getClass().getSimpleName());
            return false;
        }
    }

    @Override // com.huawei.hms.ads.uiengine.IGlobalUtil
    public com.huawei.hms.ads.uiengine.b getMultiMediaPlayingManager() {
        bt.a(this.d).a(HiAd.a(this.d).c());
        return bt.a(this.d);
    }

    @Override // com.huawei.hms.ads.uiengine.IGlobalUtil
    public String getFilePathDirectByCacheType(String str, int i) {
        return a(str, 3 != i ? "normal" : Constants.TPLATE_CACHE);
    }

    @Override // com.huawei.hms.ads.uiengine.IGlobalUtil
    public String getFilePathDirect(String str) {
        return a(str, Constants.TPLATE_CACHE);
    }

    @Override // com.huawei.hms.ads.uiengine.IGlobalUtil
    public void getFilePath(String str, IPPSUiEngineCallback iPPSUiEngineCallback) {
        String c;
        try {
            c = dh.a(this.d, Constants.TPLATE_CACHE).c(dk.d(str));
        } catch (Throwable th) {
            ho.b("GlobalUtil", "getFilePath err: %s", th.getClass().getSimpleName());
        }
        if (ae.c(this.d, c, Constants.TPLATE_CACHE)) {
            ho.b("GlobalUtil", "getFilePath from cache");
            a(iPPSUiEngineCallback, c);
            return;
        }
        try {
            Pair<String, String> a2 = com.huawei.openalliance.ad.utils.c.a(this.d, str, Constants.TPLATE_CACHE);
            if (a2 != null && !TextUtils.isEmpty((CharSequence) a2.first) && !TextUtils.isEmpty((CharSequence) a2.second)) {
                ho.b("GlobalUtil", "getFilePath from kit");
                a(iPPSUiEngineCallback, (String) a2.first);
                return;
            }
        } catch (Throwable th2) {
            ho.b("GlobalUtil", "get path err: %s", th2.getClass().getSimpleName());
        }
        iPPSUiEngineCallback.onCallResult(ParamConstants.Cmd.GET_FILE_PATH, null);
    }

    @Override // com.huawei.hms.ads.uiengine.IGlobalUtil
    public Bundle callMethod(String str, IObjectWrapper iObjectWrapper, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ho.b("GlobalUtil", "call method: %s", str);
        str.hashCode();
        if (str.equals(GlobalUtilMethods.QUERY_LOCAL_PATH)) {
            return a(bundle);
        }
        ho.b("GlobalUtil", "call method fall to default.");
        return null;
    }

    public void b() {
        for (IPPSUiEngineCallback iPPSUiEngineCallback : this.c) {
            if (iPPSUiEngineCallback != null) {
                try {
                    iPPSUiEngineCallback.onCallResult("onActivityStartFinish", null);
                } catch (Throwable th) {
                    ho.b("GlobalUtil", "onCallResult err: %s", th.getClass().getSimpleName());
                }
            }
        }
    }

    private Future<String> b(final String str, final String str2) {
        return dc.a(new Callable<String>() { // from class: com.huawei.openalliance.ad.inter.d.1
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public String call() {
                return d.this.a(str, str2);
            }
        });
    }

    private static d b(Context context) {
        d dVar;
        synchronized (f7033a) {
            if (b == null) {
                b = new d(context);
            }
            dVar = b;
        }
        return dVar;
    }

    private void a(IPPSUiEngineCallback iPPSUiEngineCallback, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("filePath", str);
        iPPSUiEngineCallback.onCallResult(ParamConstants.Cmd.GET_FILE_PATH, bundle);
    }

    private static String a(Future<String> future) {
        try {
            return future.get();
        } catch (Throwable th) {
            ho.c("GlobalUtil", "get localPath ex: %s", th.getClass().getSimpleName());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, String str2) {
        try {
            String c = dh.a(this.d, str2).c(dk.d(str));
            if (ae.c(this.d, c, str2)) {
                return c;
            }
            return null;
        } catch (Throwable th) {
            ho.b("GlobalUtil", "getFilePath err: %s", th.getClass().getSimpleName());
            return null;
        }
    }

    public static d a(Context context) {
        return b(context);
    }

    private Bundle a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String d = new gk(bundle).d(GlobalUtilKeys.ORI_URL);
        if (TextUtils.isEmpty(d)) {
            return null;
        }
        ho.b("GlobalUtil", "get local path, oriUrl: %s", cz.g(d));
        Future<String> b2 = b(d, Constants.TPLATE_CACHE);
        Future<String> b3 = b(d, "normal");
        Bundle bundle2 = new Bundle();
        String a2 = a(b2);
        if (!TextUtils.isEmpty(a2)) {
            ho.b("GlobalUtil", "got tplateLocalPath: %s", cz.g(a2));
            bundle2.putString(GlobalUtilKeys.LOCAL_PATH, a2);
            return bundle2;
        }
        String a3 = a(b3);
        if (TextUtils.isEmpty(a3)) {
            return null;
        }
        ho.b("GlobalUtil", "got normalLocalPath: %s", cz.g(a3));
        bundle2.putString(GlobalUtilKeys.LOCAL_PATH, a3);
        return bundle2;
    }

    private d(Context context) {
        this.d = context;
    }
}
