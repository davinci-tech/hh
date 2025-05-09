package defpackage;

import android.emcom.IHandoffSdkCallback;
import android.emcom.IOneHopAppCallback;
import android.emcom.IOneHopAuthReqCallback;
import android.os.RemoteException;
import android.util.Log;
import com.huawei.android.emcom.EmcomManagerEx;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class lsl {

    /* renamed from: a, reason: collision with root package name */
    private static volatile lsl f14851a;
    private static final Object c = new Object();
    private static HashMap<String, Integer> d = new HashMap<>(5);

    private lsl() {
        d.put("com.huawei.health", 5);
        d.put("com.android.gallery3d", 3);
        d.put("cn.wps.moffice_eng", 1);
        d.put("com.huawei.himovie", 4);
        d.put("com.android.mediacenter", 4);
    }

    public static lsl d() {
        if (f14851a == null) {
            synchronized (c) {
                if (f14851a == null) {
                    f14851a = new lsl();
                }
            }
        }
        return f14851a;
    }

    public int b(String str, int i, final IOneHopAppCallback.Stub stub) {
        Log.d("HwOneHopSdkDelegate", "registerOneHop, packageName:" + str);
        if (stub == null) {
            Log.e("HwOneHopSdkDelegate", "registerOneHop, appCallBack == null");
            return -1;
        }
        if (b()) {
            try {
                return EmcomManagerEx.getInstance().registerOneHop(str, i, stub);
            } catch (NoSuchMethodError unused) {
                Log.e("HwOneHopSdkDelegate", "registerOneHop catch NoMethodError.");
                return -1;
            }
        }
        if (!b(str, i)) {
            Log.d("HwOneHopSdkDelegate", "registerHandoff older version ,not support dataType:" + i);
            return -1;
        }
        try {
            return EmcomManagerEx.getInstance().registerHandoff(str, i, new IHandoffSdkCallback.Stub() { // from class: lsl.2
                @Override // android.emcom.IHandoffSdkCallback
                public void handoffStateChg(int i2) {
                }

                @Override // android.emcom.IHandoffSdkCallback
                public void handoffDataEvent(String str2) throws RemoteException {
                    stub.onOneHopReceived(str2);
                }
            });
        } catch (NoSuchMethodError unused2) {
            Log.e("HwOneHopSdkDelegate", "registerHandoff catch NoMethodError.");
            return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean b(java.lang.String r9, int r10) {
        /*
            r8 = this;
            java.lang.String r0 = "HwOneHopSdkDelegate"
            r1 = 2
            r2 = 0
            r3 = 1
            com.huawei.android.emcom.EmcomManagerEx r4 = com.huawei.android.emcom.EmcomManagerEx.getInstance()     // Catch: java.lang.NumberFormatException -> L2e java.lang.NoSuchMethodError -> L35
            java.lang.String r4 = r4.onehopGetVersion()     // Catch: java.lang.NumberFormatException -> L2e java.lang.NoSuchMethodError -> L35
            java.lang.String r5 = "isSupprtDataTypeWithApp strVersion: "
            android.util.Log.d(r0, r5)     // Catch: java.lang.NumberFormatException -> L2e java.lang.NoSuchMethodError -> L35
            java.lang.String r5 = "\\."
            java.lang.String[] r4 = r4.split(r5)     // Catch: java.lang.NumberFormatException -> L2e java.lang.NoSuchMethodError -> L35
            int r5 = r4.length     // Catch: java.lang.NumberFormatException -> L2e java.lang.NoSuchMethodError -> L35
            r6 = 4
            if (r5 < r6) goto L2b
            r5 = r4[r3]     // Catch: java.lang.NumberFormatException -> L2e java.lang.NoSuchMethodError -> L35
            r6 = 10
            int r5 = java.lang.Integer.parseInt(r5, r6)     // Catch: java.lang.NumberFormatException -> L2e java.lang.NoSuchMethodError -> L35
            r4 = r4[r1]     // Catch: java.lang.NumberFormatException -> L2f java.lang.NoSuchMethodError -> L36
            int r4 = java.lang.Integer.parseInt(r4, r6)     // Catch: java.lang.NumberFormatException -> L2f java.lang.NoSuchMethodError -> L36
            goto L3c
        L2b:
            r4 = r2
            r5 = r4
            goto L3c
        L2e:
            r5 = r2
        L2f:
            java.lang.String r4 = "isSupprtDataTypeWithApp parse vaule error."
            android.util.Log.e(r0, r4)
            goto L3b
        L35:
            r5 = r2
        L36:
            java.lang.String r4 = "isSupprtDataTypeWithApp catch NoMethodError: "
            android.util.Log.e(r0, r4)
        L3b:
            r4 = r2
        L3c:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "isSupprtDataTypeWithApp mainVersion: "
            r6.<init>(r7)
            r6.append(r5)
            java.lang.String r7 = "subVersion:"
            r6.append(r7)
            r6.append(r4)
            java.lang.String r6 = r6.toString()
            android.util.Log.d(r0, r6)
            if (r5 <= r1) goto L59
            return r3
        L59:
            if (r5 != r1) goto L5e
            if (r4 < r1) goto L5e
            return r3
        L5e:
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = defpackage.lsl.d
            java.lang.Object r9 = r0.get(r9)
            java.lang.Integer r9 = (java.lang.Integer) r9
            if (r9 != 0) goto L69
            return r2
        L69:
            int r9 = r9.intValue()
            if (r9 != r10) goto L70
            r2 = r3
        L70:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lsl.b(java.lang.String, int):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean b() {
        /*
            r8 = this;
            java.lang.String r0 = "HwOneHopSdkDelegate"
            r1 = 2
            r2 = 0
            r3 = 1
            com.huawei.android.emcom.EmcomManagerEx r4 = com.huawei.android.emcom.EmcomManagerEx.getInstance()     // Catch: java.lang.NumberFormatException -> L2e java.lang.NoSuchMethodError -> L35
            java.lang.String r4 = r4.onehopGetVersion()     // Catch: java.lang.NumberFormatException -> L2e java.lang.NoSuchMethodError -> L35
            java.lang.String r5 = "isSupportOneHopInterface strVersion: "
            android.util.Log.d(r0, r5)     // Catch: java.lang.NumberFormatException -> L2e java.lang.NoSuchMethodError -> L35
            java.lang.String r5 = "\\."
            java.lang.String[] r4 = r4.split(r5)     // Catch: java.lang.NumberFormatException -> L2e java.lang.NoSuchMethodError -> L35
            int r5 = r4.length     // Catch: java.lang.NumberFormatException -> L2e java.lang.NoSuchMethodError -> L35
            r6 = 4
            if (r5 < r6) goto L2b
            r5 = r4[r3]     // Catch: java.lang.NumberFormatException -> L2e java.lang.NoSuchMethodError -> L35
            r6 = 10
            int r5 = java.lang.Integer.parseInt(r5, r6)     // Catch: java.lang.NumberFormatException -> L2e java.lang.NoSuchMethodError -> L35
            r4 = r4[r1]     // Catch: java.lang.NumberFormatException -> L2f java.lang.NoSuchMethodError -> L36
            int r4 = java.lang.Integer.parseInt(r4, r6)     // Catch: java.lang.NumberFormatException -> L2f java.lang.NoSuchMethodError -> L36
            goto L3c
        L2b:
            r4 = r2
            r5 = r4
            goto L3c
        L2e:
            r5 = r2
        L2f:
            java.lang.String r4 = "isSupportOneHopInterface parse vaule error."
            android.util.Log.e(r0, r4)
            goto L3b
        L35:
            r5 = r2
        L36:
            java.lang.String r4 = "isSupportOneHopInterface catch NoMethodError: "
            android.util.Log.e(r0, r4)
        L3b:
            r4 = r2
        L3c:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "isSupportOneHopInterface mainVersion: "
            r6.<init>(r7)
            r6.append(r5)
            java.lang.String r7 = "subVersion:"
            r6.append(r7)
            r6.append(r4)
            java.lang.String r6 = r6.toString()
            android.util.Log.d(r0, r6)
            if (r5 <= r1) goto L59
            return r3
        L59:
            if (r5 != r1) goto L5f
            r0 = 3
            if (r4 < r0) goto L5f
            r2 = r3
        L5f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lsl.b():boolean");
    }

    public String a() {
        Log.d("HwOneHopSdkDelegate", "getOneHopVersion.");
        try {
            return EmcomManagerEx.getInstance().onehopGetVersion();
        } catch (NoSuchMethodError unused) {
            Log.e("HwOneHopSdkDelegate", "getOneHopVersion error occurs");
            return "";
        }
    }

    public int b(String str, IOneHopAuthReqCallback.Stub stub) {
        Log.d("HwOneHopSdkDelegate", "onehopAuthReq, packageName:" + str);
        try {
            return EmcomManagerEx.getInstance().onehopAuthReq(str, stub);
        } catch (NoSuchMethodError unused) {
            Log.e("HwOneHopSdkDelegate", "onehopAuthReq catch NoMethodError.");
            return -1;
        }
    }
}
