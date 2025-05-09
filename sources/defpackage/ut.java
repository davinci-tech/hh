package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.account.aidl.AccountAidlInfo;
import com.huawei.account.aidl.IAccountAidlInterface;
import com.huawei.account.aidl.IBinderInterceptor;
import com.huawei.common.Constant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes2.dex */
public class ut {

    /* renamed from: a, reason: collision with root package name */
    private static ut f17538a;
    private IAccountAidlInterface b;
    private AccountAidlInfo c;
    private Context e;
    private IBaseResponseCallback d = null;
    private ServiceConnection i = new ServiceConnection() { // from class: ut.3
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.a("PLGLOGIN_AccountAidlClient", Constant.SERVICE_CONNECT_MESSAGE);
            if (iBinder == null) {
                try {
                    if (ut.this.d != null) {
                        LogUtil.a("PLGLOGIN_AccountAidlClient", "service null and bindCallBack not null ");
                        ut.this.d.onResponse(-1, Constant.SERVICE_CONNECT_MESSAGE);
                        ut.this.d = null;
                        return;
                    }
                } catch (RemoteException e) {
                    LogUtil.c("PLGLOGIN_AccountAidlClient", "Exception:" + e.getMessage());
                    LogUtil.a("PLGLOGIN_AccountAidlClient", "Exception: onServiceConnected error");
                    return;
                }
            }
            IBinder serviceBinder = IBinderInterceptor.Stub.asInterface(iBinder).getServiceBinder(Constant.HEALTH_SERVICE_NAME);
            if (serviceBinder != null || ut.this.d == null) {
                ut.this.b = IAccountAidlInterface.Stub.asInterface(serviceBinder);
                if (ut.this.b != null) {
                    if (ut.this.d != null) {
                        LogUtil.a("PLGLOGIN_AccountAidlClient", "accountAidlInterface not null");
                        ut.this.d.onResponse(0, Constant.SERVICE_CONNECT_MESSAGE);
                    }
                } else {
                    LogUtil.a("PLGLOGIN_AccountAidlClient", "accountAidlInterface null");
                    ut.this.d.onResponse(-1, Constant.SERVICE_CONNECT_MESSAGE);
                }
                ut.this.d = null;
                return;
            }
            LogUtil.a("PLGLOGIN_AccountAidlClient", "accountAidlInterface null and bindCallBack not null");
            ut.this.d.onResponse(-1, Constant.SERVICE_CONNECT_MESSAGE);
            ut.this.d = null;
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.a("PLGLOGIN_AccountAidlClient", "onServiceDisconnected");
            if (ut.this.d != null) {
                ut.this.d.onResponse(-1, Constant.SERVICE_CONNECT_MESSAGE);
                ut.this.d = null;
            }
        }
    };

    private ut(Context context) {
        this.e = context.getApplicationContext();
    }

    public static ut b(Context context) {
        if (f17538a == null) {
            f17538a = new ut(context);
        }
        return f17538a;
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("PLGLOGIN_AccountAidlClient", "Enter bindRemoteService");
        Intent intent = new Intent(Constant.ACCOUNT_AIDL_SERVICE);
        intent.setClassName("com.huawei.bone", "com.huawei.account.aidl.AccountAidlService");
        this.d = iBaseResponseCallback;
        LogUtil.c("PLGLOGIN_AccountAidlClient", "intent = " + intent);
        boolean bindService = this.e.getApplicationContext().bindService(intent, this.i, 1);
        if (!bindService) {
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.onResponse(-1, Constant.BIND_SERVICE_ERROR_MESSAGE);
            } else {
                LogUtil.b("PLGLOGIN_AccountAidlClient", "callback id null");
            }
            this.d = null;
        }
        if (this.b != null) {
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.onResponse(0, Constant.AIDI_INTERFACE_NOT_NULL_MESSAGE);
            } else {
                LogUtil.b("PLGLOGIN_AccountAidlClient", "callback id null");
            }
            this.d = null;
        }
        LogUtil.a("PLGLOGIN_AccountAidlClient", "bindResult = " + bindService);
    }

    public AccountAidlInfo b() {
        LogUtil.a("PLGLOGIN_AccountAidlClient", "Enter getRemoteInfo");
        try {
            IAccountAidlInterface iAccountAidlInterface = this.b;
            if (iAccountAidlInterface == null) {
                LogUtil.a("PLGLOGIN_AccountAidlClient", "getRemoteInfo accountAidlInterface == null");
                return null;
            }
            AccountAidlInfo remoteAccountInfo = iAccountAidlInterface.getRemoteAccountInfo();
            this.c = remoteAccountInfo;
            if (remoteAccountInfo != null) {
                LogUtil.a("PLGLOGIN_AccountAidlClient", "accountinfo get remote info success ");
                LogUtil.c("PLGLOGIN_AccountAidlClient", "accountinfo get remote info success accountAidlInfo:" + this.c.toString());
                return this.c;
            }
            LogUtil.a("PLGLOGIN_AccountAidlClient", "accountinfo = null,get remote info failed");
            return null;
        } catch (RemoteException e) {
            LogUtil.c("PLGLOGIN_AccountAidlClient", "RemoteException ERROR:" + e.getMessage());
            LogUtil.a("PLGLOGIN_AccountAidlClient", "RemoteException ERROR: getRemoteInfo error");
            return null;
        }
    }
}
