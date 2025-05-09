package defpackage;

import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.a.a.a;
import com.huawei.harmonyos.interwork.base.ability.IInitCallBack;

/* loaded from: classes3.dex */
public class bwh {
    public static void b(String str, IInitCallBack iInitCallBack) throws IllegalArgumentException, IllegalStateException, SecurityException, bwi {
        if (iInitCallBack == null || TextUtils.isEmpty(str)) {
            Log.e("DefKitLib_Device", "initDistributedEnvironment param is invalid");
            throw new IllegalArgumentException("invalid param");
        }
        a b = bws.b();
        if (b == null) {
            Log.e("DefKitLib_Device", "get getDefInterface failed");
            throw new IllegalStateException("get getDefInterface failed");
        }
        try {
            b.a(str, bwp.c(str, iInitCallBack), 0, 0);
        } catch (RemoteException e) {
            Log.e("DefKitLib_Device", "failed to init distributed environment with the specified device");
            throw new bwi(e.getMessage());
        }
    }

    public static void a(String str, IInitCallBack iInitCallBack) throws IllegalArgumentException, IllegalStateException, SecurityException, bwi {
        if (iInitCallBack == null || TextUtils.isEmpty(str)) {
            Log.e("DefKitLib_Device", "unInitDistributedEnvironment param is invalid");
            throw new IllegalArgumentException("invalid param");
        }
        a b = bws.b();
        if (b == null) {
            Log.e("DefKitLib_Device", "get getDefInterface failed");
            throw new IllegalStateException("get getDefInterface failed");
        }
        try {
            bwp d = bwp.d(str, iInitCallBack);
            if (d == null) {
                Log.e("DefKitLib_Device", "initCallback is not find by the specified callback");
            } else {
                b.b(str, d, 0, 0);
            }
        } catch (RemoteException e) {
            Log.e("DefKitLib_Device", "failed to unInit distributed environment with the specified device");
            throw new bwi(e.getMessage());
        }
    }
}
