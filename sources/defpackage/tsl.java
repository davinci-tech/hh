package defpackage;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import com.google.android.clockwork.companion.partnerapi.PartnerApi;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwbtsdk.btdatatype.callback.IBindPartnerServiceCallback;
import health.compact.a.LogUtil;

/* loaded from: classes7.dex */
public class tsl {
    private static tsl b;
    private static final Object c = new Object();
    private static PartnerApi e;

    private tsl() {
    }

    public static tsl d() {
        tsl tslVar;
        synchronized (c) {
            LogUtil.c("CommunicationManager", "getInstance() ");
            if (b == null) {
                b = new tsl();
            }
            tslVar = b;
        }
        return tslVar;
    }

    public void a(IBindPartnerServiceCallback iBindPartnerServiceCallback) {
        if (iBindPartnerServiceCallback == null) {
            return;
        }
        if (e != null) {
            LogUtil.c("CommunicationManager", "mApi is not null");
            iBindPartnerServiceCallback.onBinderResponse(e);
            return;
        }
        Intent intent = new Intent();
        if (b()) {
            intent.setPackage("com.google.android.wearable.app.cn");
        } else {
            intent.setPackage("com.google.android.wearable.app");
        }
        intent.setAction("com.google.android.wearable.app.action.INVOKE_PARTNER_API");
        try {
            LogUtil.c("CommunicationManager", "getPartnerService bind begin");
            LogUtil.c("CommunicationManager", "bind flag : ", Boolean.valueOf(BaseApplication.e().bindService(intent, new a(iBindPartnerServiceCallback), 1)));
        } catch (SecurityException e2) {
            LogUtil.c("CommunicationManager", "bind partner service error, ", e2.getMessage());
        }
    }

    public static class a implements ServiceConnection {
        private IBindPartnerServiceCallback e;

        public a(IBindPartnerServiceCallback iBindPartnerServiceCallback) {
            this.e = iBindPartnerServiceCallback;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.c("CommunicationManager", "ServiceConnection callback");
            PartnerApi asInterface = PartnerApi.Stub.asInterface(iBinder);
            PartnerApi unused = tsl.e = asInterface;
            IBindPartnerServiceCallback iBindPartnerServiceCallback = this.e;
            if (iBindPartnerServiceCallback != null) {
                iBindPartnerServiceCallback.onBinderResponse(asInterface);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.c("CommunicationManager", "onServiceDisconnected callback");
            PartnerApi unused = tsl.e = null;
        }
    }

    private boolean b() {
        PackageInfo packageInfo;
        PackageManager packageManager = BaseApplication.e().getPackageManager();
        try {
            packageInfo = packageManager.getPackageInfo("com.google.android.wearable.app.cn", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.e("CommunicationManager", "isChinaApp() androidWearNameCn, error NameNotFoundException.");
            try {
                packageInfo = packageManager.getPackageInfo("com.google.android.wearable.app", 0);
            } catch (PackageManager.NameNotFoundException unused2) {
                LogUtil.e("CommunicationManager", "isChinaApp() androidWearName, error NameNotFoundException.");
                packageInfo = null;
            }
        }
        return (packageInfo == null || packageInfo.packageName == null || !packageInfo.packageName.equals("com.google.android.wearable.app.cn")) ? false : true;
    }
}
