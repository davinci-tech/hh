package com.huawei.health.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.common.Constant;
import com.huawei.health.connectivity.extendstepcounter.ExtendStepCounter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.mockhealth.IMockStepReport;
import com.huawei.mockhealth.IMockStepService;
import com.huawei.openalliance.ad.db.bean.ContentTemplateRecord;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import health.compact.a.Base64;
import health.compact.a.StandStepCounter;
import health.compact.a.StepCounterSupport;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public class MockStepHelper {

    /* renamed from: a, reason: collision with root package name */
    private MyMockServiceConnection f2777a;
    private Context c;
    private MyCallback e;
    private IMockStepService f;
    private ExtendStepCounter g;
    private StandStepCounter h;
    private final Object b = new Object();
    private int d = 0;
    private volatile boolean j = false;

    public MockStepHelper(Context context) {
        this.f2777a = new MyMockServiceConnection();
        this.e = new MyCallback();
        this.c = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a() {
        /*
            r7 = this;
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r1 = "com.huawei.mockhealth"
            r0.setPackage(r1)
            java.lang.String r1 = "com.huawei.mockhealth"
            java.lang.String r2 = "com.huawei.mockhealth.MockStepService"
            r0.setClassName(r1, r2)
            r1 = 0
            r2 = 1
            android.content.Context r3 = r7.c     // Catch: java.lang.InterruptedException -> L2e java.lang.SecurityException -> L41
            com.huawei.health.manager.MockStepHelper$MyMockServiceConnection r4 = r7.f2777a     // Catch: java.lang.InterruptedException -> L2e java.lang.SecurityException -> L41
            boolean r0 = r3.bindService(r0, r4, r2)     // Catch: java.lang.InterruptedException -> L2e java.lang.SecurityException -> L41
            java.lang.Object r3 = r7.b     // Catch: java.lang.InterruptedException -> L2a java.lang.SecurityException -> L2c
            monitor-enter(r3)     // Catch: java.lang.InterruptedException -> L2a java.lang.SecurityException -> L2c
            java.lang.Object r4 = r7.b     // Catch: java.lang.Throwable -> L27
            r5 = 5000(0x1388, double:2.4703E-320)
            r4.wait(r5)     // Catch: java.lang.Throwable -> L27
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L27
            goto L53
        L27:
            r4 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L27
            throw r4     // Catch: java.lang.InterruptedException -> L2a java.lang.SecurityException -> L2c
        L2a:
            r3 = move-exception
            goto L31
        L2c:
            r3 = move-exception
            goto L44
        L2e:
            r0 = move-exception
            r3 = r0
            r0 = r1
        L31:
            java.lang.String r4 = "bindMockStepService InterruptedException: "
            java.lang.String r3 = r3.toString()
            java.lang.Object[] r3 = new java.lang.Object[]{r4, r3}
            java.lang.String r4 = "Step_Mock"
            com.huawei.hwlogsmodel.LogUtil.b(r4, r3)
            goto L53
        L41:
            r0 = move-exception
            r3 = r0
            r0 = r1
        L44:
            java.lang.String r4 = "bindMockStepService SecurityException: "
            java.lang.String r3 = r3.toString()
            java.lang.Object[] r3 = new java.lang.Object[]{r4, r3}
            java.lang.String r4 = "Step_Mock"
            com.huawei.hwlogsmodel.LogUtil.b(r4, r3)
        L53:
            java.lang.String r3 = "MockService isBind: "
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r3, r0}
            java.lang.String r3 = "Step_Mock"
            com.huawei.hwlogsmodel.LogUtil.a(r3, r0)
            com.huawei.mockhealth.IMockStepService r0 = r7.f
            if (r0 == 0) goto L67
            r1 = r2
        L67:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.manager.MockStepHelper.a():boolean");
    }

    public void a(StandStepCounter standStepCounter, ExtendStepCounter extendStepCounter) {
        this.h = standStepCounter;
        this.g = extendStepCounter;
    }

    public void e() {
        MyCallback myCallback;
        IMockStepService iMockStepService = this.f;
        if (iMockStepService == null || (myCallback = this.e) == null) {
            return;
        }
        try {
            iMockStepService.registerMockStepReport(myCallback);
        } catch (RemoteException e) {
            LogUtil.b("Step_Mock", e.getMessage());
        }
    }

    class MyMockServiceConnection implements ServiceConnection {
        private MyMockServiceConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.a("Step_Mock", "MockService disconnected");
            MockStepHelper.this.j = false;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.a("Step_Mock", Constant.SERVICE_CONNECT_MESSAGE, componentName);
            if (componentName == null || !"com.huawei.mockhealth.MockStepService".equals(componentName.getClassName())) {
                return;
            }
            MockStepHelper.this.j = true;
            MockStepHelper.this.f = IMockStepService.Stub.asInterface(iBinder);
            try {
                MockStepHelper mockStepHelper = MockStepHelper.this;
                mockStepHelper.e(mockStepHelper.f.getCapacity());
            } catch (RemoteException e) {
                LogUtil.b("Step_Mock", e.getMessage());
            }
            MockStepHelper.this.e();
            synchronized (MockStepHelper.this.b) {
                MockStepHelper.this.b.notifyAll();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        this.d = i;
        LogUtil.a("Step_Mock", "updateCapacity: capacity = ", Integer.valueOf(i));
        if ((i & 1) != 1) {
            StepCounterSupport.e(this.c, 3);
        } else if ((i & 2) == 2) {
            StepCounterSupport.e(this.c, 1);
        } else {
            StepCounterSupport.e(this.c, 2);
        }
    }

    public boolean b() {
        return (this.d & 1) == 1;
    }

    class MyCallback extends IMockStepReport.Stub {
        private MyCallback() {
        }

        @Override // com.huawei.mockhealth.IMockStepReport
        public void onExtendStepChanged(Bundle bundle) throws RemoteException {
            if (MockStepHelper.this.j) {
                if (bundle == null || MockStepHelper.this.g == null) {
                    return;
                }
                long j = bundle.getLong("start_time");
                try {
                    MockStepHelper.this.g.dataReport(MockStepHelper.this.c, j, bundle.getIntArray(MedalConstants.EVENT_STEPS), bundle.getIntArray("floors"), bundle.getIntArray(ContentTemplateRecord.MOTIONS));
                    return;
                } catch (ArrayIndexOutOfBoundsException e) {
                    LogUtil.b("Step_Mock", e.getMessage());
                    return;
                }
            }
            LogUtil.b("Step_Mock", "onExtendStepChanged bind service is disconnect");
        }

        @Override // com.huawei.mockhealth.IMockStepReport
        public void onStandStepChanged(int i) throws RemoteException {
            if (MockStepHelper.this.j) {
                if (MockStepHelper.this.h != null) {
                    MockStepHelper.this.h.a(i, 2);
                    return;
                }
                return;
            }
            LogUtil.b("Step_Mock", "onStandStepChanged bind service is disconnect");
        }
    }

    private static boolean d(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        StringBuilder sb = new StringBuilder(16);
        try {
            for (Signature signature : packageManager.getPackageInfo(str, 64).signatures) {
                sb.append(signature.toCharsString());
            }
            return "MzA4MjA0YjUzMDgyMDM5ZGEwMDMwMjAxMDIwMjA5MDBmMjA0NTZhYjk2NGZhMGYwMzAwZDA2MDkyYTg2NDg4NmY3MGQwMTAxMDUwNTAwMzA4MTk4MzEwYjMwMDkwNjAzNTUwNDA2MTMwMjQzNGUzMTEyMzAxMDA2MDM1NTA0MDgxMzA5NDc3NTYxNmU2NzY0NmY2ZTY3MzExMjMwMTAwNjAzNTUwNDA3MTMwOTUzNjg2NTZlNjc3YTY4NjU2ZTMxMGYzMDBkMDYwMzU1MDQwYTEzMDY0ODc1NjE3NzY1NjkzMTE4MzAxNjA2MDM1NTA0MGIxMzBmNTQ2NTcyNmQ2OTZlNjE2YzQzNmY2ZDcwNjE2ZTc5MzExNDMwMTIwNjAzNTUwNDAzMTMwYjQxNmU2NDcyNmY2OTY0NTQ2NTYxNmQzMTIwMzAxZTA2MDkyYTg2NDg4NmY3MGQwMTA5MDExNjExNmQ2ZjYyNjk2YzY1NDA2ODc1NjE3NzY1NjkyZTYzNmY2ZDMwMWUxNzBkMzEzMTMwMzUzMjM1MzAzNjMxMzAzNDM5NWExNzBkMzMzNjMwMzUzMTM4MzAzNjMxMzAzNDM5NWEzMDgxOTgzMTBiMzAwOTA2MDM1NTA0MDYxMzAyNDM0ZTMxMTIzMDEwMDYwMzU1MDQwODEzMDk0Nzc1NjE2ZTY3NjQ2ZjZlNjczMTEyMzAxMDA2MDM1NTA0MDcxMzA5NTM2ODY1NmU2NzdhNjg2NTZlMzEwZjMwMGQwNjAzNTUwNDBhMTMwNjQ4NzU2MTc3NjU2OTMxMTgzMDE2MDYwMzU1MDQwYjEzMGY1NDY1NzI2ZDY5NmU2MTZjNDM2ZjZkNzA2MTZlNzkzMTE0MzAxMjA2MDM1NTA0MDMxMzBiNDE2ZTY0NzI2ZjY5NjQ1NDY1NjE2ZDMxMjAzMDFlMDYwOTJhODY0ODg2ZjcwZDAxMDkwMTE2MTE2ZDZmNjI2OTZjNjU0MDY4NzU2MTc3NjU2OTJlNjM2ZjZkMzA4MjAxMjAzMDBkMDYwOTJhODY0ODg2ZjcwZDAxMDEwMTA1MDAwMzgyMDEwZDAwMzA4MjAxMDgwMjgyMDEwMTAwZTAyYzVhYjk3YTJiM2E4YTU5OTYyMjNjZGUwNmI4MmIyZDRmZjViMTVjYWY2NWI4NjBkNWM3YTNkNjg5OTVhYjA4NjIwYmI3NWEyMmZlNzY3M2E4YTFhYmEwM2UxN2I2NTFkMWZjNGQ1Y2JkYmFlOWU3M2VlZWFmNWExZDRkMmZiNzNlNzAwMDIzMWUwZGIyMTY2ZDBmYzVkZDk3ZTcwNWZkNjY1NDZjOWRhMzhlZDRlZmEyY2NjZGQyMzhhZDMyZTM5ODIxMjQyYjAxOTVkZjAxZDliOTcyNDJkYmYyMDllZGE4ZTQ0NmUwNDMyNDRiODRlNmJmY2E3OWQ3YmIzYzE5MjRjZGQyNDhlZGJkNjAwZWZmOGY3MzAwMWE4OWE0YzY2M2RiODk3MGUzMjg4Yjk0MzE1MjRjMzYxZTg1M2I4ZmEyOWUwNGU2MWViZTZmYmRiZDg3Y2RiZDNlZWI0N2IwMjdiNTg1MWJkZWFhMTNhMjNmNDM5NjdhMDMwZTc0N2VhNDMyNjUyY2JiMzRmZGRlNjEwNDliZjUwNjBjODEzZmIwZTkzZjZiYWQ5ZDM2ZjRkNDU1MTE5NWVhM2JiNDllOTIwMWFhNmRmOTc1YWUxNjllMjE0OTA1ZGUyNTc5ZDdjYzNjM2VhYzQ1OTRiMTRhYzE5ZDdlMzljNWMyNjcwMjAxMDNhMzgyMDEwMDMwODFmZDMwMWQwNjAzNTUxZDBlMDQxNjA0MTQzZWM2OTBkZTgxMWJjMzllNTFhMzAzMDFhZDIzY2YzMDA2ZDY3ZGUwMzA4MWNkMDYwMzU1MWQyMzA0ODFjNTMwODFjMjgwMTQzZWM2OTBkZTgxMWJjMzllNTFhMzAzMDFhZDIzY2YzMDA2ZDY3ZGUwYTE4MTllYTQ4MTliMzA4MTk4MzEwYjMwMDkwNjAzNTUwNDA2MTMwMjQzNGUzMTEyMzAxMDA2MDM1NTA0MDgxMzA5NDc3NTYxNmU2NzY0NmY2ZTY3MzExMjMwMTAwNjAzNTUwNDA3MTMwOTUzNjg2NTZlNjc3YTY4NjU2ZTMxMGYzMDBkMDYwMzU1MDQwYTEzMDY0ODc1NjE3NzY1NjkzMTE4MzAxNjA2MDM1NTA0MGIxMzBmNTQ2NTcyNmQ2OTZlNjE2YzQzNmY2ZDcwNjE2ZTc5MzExNDMwMTIwNjAzNTUwNDAzMTMwYjQxNmU2NDcyNmY2OTY0NTQ2NTYxNmQzMTIwMzAxZTA2MDkyYTg2NDg4NmY3MGQwMTA5MDExNjExNmQ2ZjYyNjk2YzY1NDA2ODc1NjE3NzY1NjkyZTYzNmY2ZDgyMDkwMGYyMDQ1NmFiOTY0ZmEwZjAzMDBjMDYwMzU1MWQxMzA0MDUzMDAzMDEwMWZmMzAwZDA2MDkyYTg2NDg4NmY3MGQwMTAxMDUwNTAwMDM4MjAxMDEwMDZkYWQyZmM1OTBjZjAwNzlhYzRjZTA4NjVhODg0MzExZGMxNmFkODMyNzVhMmIwMzAyNDA1Nzc2ZGQxZDFmNzA0YTI5OTkzYzhhMTM0YThkZmRkNTRjNTkxNTFhMDYxNTU2MTJkYmQzMDA2NDZlYWNmYzlmNjRmYWMyZTM1MjcwOWIwMzU5Zjc1NmFmOTZjMWY1NjQ5NTMwNGU3MjI4N2JlNDc5OGEwNDMzMzU2Y2JjY2RkOGQ1N2NkZTFmN2ZmMWMwMDRjYjk2MmFkNTk1YjFkYTIyZWYyMDRkZTE0NDkwODg2MWJiYTk2OGUwYzc0MzgxZTExOWM3NzJiMmNmMjJjM2QwYzgzMjVhOWYzMWM5Nzg3YjE1NmM5NDc3MWFmNjZmNGZmYjFhZDk5NmMzZmJlNzY0ZjNiYzUwYjdhMjY0M2MyMzM3MjYxMmYyNTA5OTQ2YTAzYmFkZmJmZWE2MWY4ZTNkMjg5MjMzODViYmMwMDg3NWFjZWM5OWI2ZDkyYWM0NjhmMGRjNTBjMDk5N2ZmNDA5ZTNhMjVkYzhkOTY2YzIwZDU4OGE5NzEzZmYyMTdlMmRlNDk0NTdhMzJjZjIzZGYxYWY1OGY0YmNkZDk0YThmMjZiYzFjOTBjNWE1MTgzZGI2NGQ0NjM1M2RkNDYxMDQ3OGM3N2YyYzg5OTg4".equals(Base64.a(sb.toString().getBytes("UTF-8")));
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.b("Step_Mock", e.getMessage());
            return false;
        } catch (UnsupportedEncodingException e2) {
            LogUtil.b("Step_Mock", e2.getMessage());
            return false;
        }
    }

    public static boolean e(Context context) {
        if (!StepCounterSupport.e(context)) {
            LogUtil.a("Step_Mock", "isSupportMockStepCounter false ");
            return false;
        }
        if (context != null) {
            return d(context, "com.huawei.mockhealth");
        }
        return false;
    }
}
