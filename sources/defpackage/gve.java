package defpackage;

import android.os.IBinder;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class gve {

    /* renamed from: a, reason: collision with root package name */
    private static IBinder f12954a;
    private static IBaseResponseCallback b;

    public static void aUt_(IBinder iBinder, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Track_TrackDeveloperUtils", "enter setBinderAndRegisterCallback");
        if (f12954a == null) {
            LogUtil.a("Track_TrackDeveloperUtils", "mProxy is null, set value");
            f12954a = iBinder;
        }
        b = iBaseResponseCallback;
    }

    public static void aUu_(IBinder iBinder) {
        LogUtil.a("Track_TrackDeveloperUtils", "enter setBinderAndStartService");
        if (f12954a == null) {
            f12954a = iBinder;
        }
        d();
    }

    public static void d() {
        LogUtil.a("Track_TrackDeveloperUtils", "enter pourInterfaceToKitSportApi");
        if (f12954a == null) {
            LogUtil.h("Track_TrackDeveloperUtils", "mProxy == null, request new binder");
            IBaseResponseCallback iBaseResponseCallback = b;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, "");
                return;
            }
            return;
        }
        a();
    }

    public static void b() {
        if (f12954a == null) {
            LogUtil.h("Track_TrackDeveloperUtils", "onKillProxy mProxy == null ");
            return;
        }
        LogUtil.a("Track_TrackDeveloperUtils", "recycle proxy");
        f12954a = null;
        a();
    }

    private static void a() {
        LogUtil.a("Track_TrackDeveloperUtils", "set binder: ", f12954a);
        HiHealthNativeApi.a(BaseApplication.getContext()).setBinder("TrackDeveloperService", f12954a, new HiCommonListener() { // from class: gve.3
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                LogUtil.a("Track_TrackDeveloperUtils", "setBinder onSuccess = ", Integer.valueOf(i));
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.h("Track_TrackDeveloperUtils", "setBinder onFailure = ", Integer.valueOf(i));
            }
        });
    }
}
