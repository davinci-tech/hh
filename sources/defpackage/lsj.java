package defpackage;

import android.content.Context;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.controlcenter.featureability.sdk.IAuthCallback;
import com.huawei.controlcenter.featureability.sdk.IConnectCallback;
import com.huawei.controlcenter.featureability.sdk.model.ExtraParams;
import com.huawei.onehop.fasdk.job.PendingRequest;
import com.huawei.onehop.fasdk.job.ResultCallback;
import com.huawei.onehop.fasdk.model.DeviceConnectState;
import com.tencent.open.apireq.BaseResp;

/* loaded from: classes5.dex */
public class lsj {
    private static lsn d;

    public static boolean b(Context context, final String str, final ResultCallback resultCallback, final IAuthCallback iAuthCallback) {
        if (context == null) {
            Log.e("FAKit: SyncConnect", "authReq: context is null");
            return false;
        }
        if (TextUtils.isEmpty(str) || iAuthCallback == null) {
            Log.e("FAKit: SyncConnect", "authReq: packageName or callback is null");
            return false;
        }
        int a2 = a(context, new PendingRequest() { // from class: lsj.3
            @Override // com.huawei.onehop.fasdk.job.PendingRequest
            public void execute() {
                Log.i("FAKit: SyncConnect", "authReq PendingRequest execute");
                boolean d2 = lsj.d.d(str, iAuthCallback);
                ResultCallback resultCallback2 = resultCallback;
                if (resultCallback2 != null) {
                    resultCallback2.onResult("auth", d2 ? 0 : -1);
                }
            }
        });
        if (a2 == 0 || resultCallback == null) {
            return true;
        }
        resultCallback.onResult("auth", a2);
        return true;
    }

    public static int cad_(String str, IBinder iBinder, ExtraParams extraParams, IConnectCallback iConnectCallback) {
        Log.i("FAKit: SyncConnect", "register start");
        lsn lsnVar = d;
        if (lsnVar == null) {
            Log.w("FAKit: SyncConnect", "register, sConnector is null");
            return BaseResp.CODE_QQ_LOW_VERSION;
        }
        return lsnVar.cac_(str, iBinder, extraParams, iConnectCallback);
    }

    public static boolean e(int i) {
        Log.i("FAKit: SyncConnect", "unregister start");
        lsn lsnVar = d;
        if (lsnVar == null) {
            Log.w("FAKit: SyncConnect", "unregister, sConnector is null");
            return false;
        }
        return lsnVar.c(i);
    }

    public static boolean d(int i, String str, DeviceConnectState deviceConnectState) {
        Log.i("FAKit: SyncConnect", "updateConnectStatus start");
        lsn lsnVar = d;
        if (lsnVar == null || deviceConnectState == null) {
            Log.w("FAKit: SyncConnect", "updateConnectStatus, sConnector or state is null");
            return false;
        }
        return lsnVar.d(i, str, deviceConnectState.getState());
    }

    public static boolean c(int i, ExtraParams extraParams) {
        Log.i("FAKit: SyncConnect", "showDeviceList start");
        lsn lsnVar = d;
        if (lsnVar == null) {
            Log.w("FAKit: SyncConnect", "showDeviceList, sConnector is null");
            return false;
        }
        return lsnVar.d(i, extraParams);
    }

    private static int a(Context context, PendingRequest pendingRequest) {
        if (d == null) {
            d = lsn.a(context);
        }
        if (!d.d()) {
            return d.a(pendingRequest);
        }
        pendingRequest.execute();
        return 0;
    }
}
