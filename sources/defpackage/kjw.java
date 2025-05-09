package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.ThermalCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public final class kjw {
    public static void b() {
        ReleaseLogUtil.e("Dfx_Utils", "collectDataCompleteDfxLog enter");
        if (CommonUtil.as()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: kjx
                @Override // java.lang.Runnable
                public final void run() {
                    kjw.c();
                }
            });
        }
        CommonUtil.d();
        if (!ThermalCallback.getInstance().isTriggerTask()) {
            ReleaseLogUtil.d("Dfx_Utils", "mNonLocalBroadcastReceiver does not have the conditions to trigger the task");
        } else if (CommonUtil.ce()) {
            ReleaseLogUtil.e("Dfx_Utils", "ToDo dfx sync...");
            ThreadPoolManager.d().execute(new Runnable() { // from class: kjw.2
                @Override // java.lang.Runnable
                public void run() {
                    kjw.d();
                }
            });
        }
    }

    static /* synthetic */ void c() {
        jeq.e().init(BaseApplication.getContext());
        if (Utils.o()) {
            jeq.e().setProductType(20);
        } else {
            jeq.e().setProductType(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d() {
        ReleaseLogUtil.e("Dfx_Utils", "getMainWhenSyncSuccess()");
        boolean z = CommonUtil.as() && Utils.o();
        if (Utils.l() || z) {
            ReleaseLogUtil.e("Dfx_Utils", "not support oversea no cloud version");
        } else {
            jvd.d(BaseApplication.getContext()).e(0, new b());
        }
    }

    static class b implements DeviceDfxBaseResponseCallback {
        @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback
        public void onProgress(int i, String str) {
        }

        b() {
            LogUtil.a(com.huawei.hianalytics.core.transport.Utils.TAG, "init InnerDeviceDfxBaseResponseCallback");
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback
        public void onSuccess(int i, String str) {
            ReleaseLogUtil.e("Dfx_Utils", "getMaintenanceFile onSuccess ,suc_code: ", Integer.valueOf(i));
            SharedPreferenceManager.e(String.valueOf(10), "contain_app_log", false);
            jvd.d(BaseApplication.getContext()).a(str);
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.e("Dfx_Utils", "getMaintenanceFile ,onFailure err_code: ", Integer.valueOf(i));
            SharedPreferenceManager.e(String.valueOf(10), "contain_app_log", false);
            jvd.d(BaseApplication.getContext()).a(jsd.j(str));
        }
    }

    public static boolean a(int i) {
        return i == 7 || i == 8 || b(i) || i >= 32;
    }

    private static boolean b(int i) {
        return i == 13 || i == 14 || c(i);
    }

    private static boolean c(int i) {
        return i == 15 || i == 16 || e(i);
    }

    private static boolean e(int i) {
        return i == 18 || i == 19 || d(i);
    }

    private static boolean d(int i) {
        return i == 20 || i == 21 || i == 10 || cvt.c(i);
    }
}
