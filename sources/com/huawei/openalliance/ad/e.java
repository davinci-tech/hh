package com.huawei.openalliance.ad;

import android.content.Context;
import android.os.IBinder;
import android.util.Log;
import com.huawei.hms.ads.dynamic.DynamicModule;
import com.huawei.hms.ads.uiengine.IRemoteCreator;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private static volatile Context f6832a;
    private static IRemoteCreator b;
    private static String c;
    private static com.huawei.hms.ads.uiengine.e d;
    private static final List<String> e;

    private static Integer c(Context context) {
        return Integer.valueOf(e.contains(context.getPackageName()) ? 2 : 1);
    }

    public static com.huawei.hms.ads.uiengine.e b() {
        return d;
    }

    private static Context b(Context context) {
        ho.b("RemoteSdkInitializer", "newRemoteContext: ");
        if (f6832a != null) {
            return f6832a;
        }
        try {
            if (com.huawei.openalliance.ad.utils.ck.a("com.huawei.hms.ads.common.inter.LoaderSpHandlerInter")) {
                DynamicModule.setSpHandler(fa.a(context));
            } else {
                ho.d("RemoteSdkInitializer", "LoaderSpHandler is not available");
            }
            if (com.huawei.openalliance.ad.utils.ck.a("com.huawei.hms.ads.common.inter.LoaderCommonInter")) {
                DynamicModule.setCommonInter(ez.a(context));
            } else {
                ho.d("RemoteSdkInitializer", "LoaderCommonHandler is not available");
            }
            f6832a = DynamicModule.load(context, c(context), "adsuiengine", "").getModuleContext();
        } catch (Throwable th) {
            ho.d("RemoteSdkInitializer", "newRemoteContext failed: " + th.getLocalizedMessage());
        }
        return f6832a;
    }

    public static String a() {
        String str;
        synchronized (e.class) {
            str = c;
        }
        return str;
    }

    public static IRemoteCreator a(Context context) {
        Context b2;
        synchronized (e.class) {
            ho.b("RemoteSdkInitializer", "newCreator: ");
            if (b != null) {
                ho.b("RemoteSdkInitializer", "newCreator: mRemoteCreator != null return");
                return b;
            }
            try {
                b2 = b(context);
            } catch (Throwable th) {
                ho.d("RemoteSdkInitializer", "newCreator failed " + th.getLocalizedMessage());
            }
            if (b2 == null) {
                Log.i("RemoteSdkInitializer", "newCreator: remoteContext= null");
                return null;
            }
            IRemoteCreator a2 = IRemoteCreator.a.a((IBinder) b2.getClassLoader().loadClass("com.huawei.hms.ads.uiengine.remote.RemoteCreator").newInstance());
            b = a2;
            c = a2.getVersion();
            b.setGlobalUtil(com.huawei.openalliance.ad.inter.d.a(context));
            b.setSdkInfo(com.huawei.openalliance.ad.utils.d.A(context).intValue(), 30474310, null);
            d = b.getUiEngineUtil();
            Log.i("RemoteSdkInitializer", "newRemoteContext: mRemoteCreator :" + b);
            return b;
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        e = arrayList;
        arrayList.add(Constants.HW_INTELLIEGNT_PACKAGE);
    }
}
