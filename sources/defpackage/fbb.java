package defpackage;

import com.huawei.basefitnessadvice.callback.DeviceCallback;
import com.huawei.featureuserprofile.route.navigationparser.NavigationFileParser;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.health.basesport.wearengine.DeviceStateListener;
import com.huawei.health.basesport.wearengine.SportHiWearBusinessType;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cba;
import defpackage.spn;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class fbb implements DeviceStateListener {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f12417a = new Object();
    private static volatile fbb c;

    private fbb() {
        caw.d().registerDeviceStateListener(this);
    }

    public static fbb e() {
        if (c == null) {
            synchronized (f12417a) {
                LogUtil.a("Suggestion_RouteDeliverManager", "getInstance()");
                if (c == null) {
                    c = new fbb();
                }
            }
        }
        return c;
    }

    @Override // com.huawei.health.basesport.wearengine.DeviceStateListener
    public void onConnecting() {
        LogUtil.b("Suggestion_RouteDeliverManager", " onConnecting");
    }

    @Override // com.huawei.health.basesport.wearengine.DeviceStateListener
    public void onDisconnected() {
        LogUtil.b("Suggestion_RouteDeliverManager", " onDisconnected");
    }

    @Override // com.huawei.health.basesport.wearengine.DeviceStateListener
    public void onConnected() {
        LogUtil.b("Suggestion_RouteDeliverManager", " onConnected");
    }

    public void d(String str, final DeviceCallback deviceCallback) {
        LogUtil.a("Suggestion_RouteDeliverManager", "sendFileNameToDevice", str);
        try {
            int b = cba.b();
            spn e = c(str, SportHiWearBusinessType.TRAJECTORY_HANDLE_SHAKE.getTypeValue(), b).e();
            IBaseResponseCallback iBaseResponseCallback = new IBaseResponseCallback() { // from class: fbg
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    fbb.d(DeviceCallback.this, i, obj);
                }
            };
            caw.d().sendCommandToDevice("RouteCallBack", e, new cbc(caw.d(), "Suggestion_RouteDeliverManager", SportHiWearBusinessType.TRAJECTORY_HANDLE_SHAKE.getTypeValue(), b, iBaseResponseCallback), iBaseResponseCallback);
        } catch (too unused) {
            LogUtil.b("Suggestion_RouteDeliverManager", "sendMessage WearEngineException");
        }
    }

    static /* synthetic */ void d(DeviceCallback deviceCallback, int i, Object obj) {
        ReleaseLogUtil.d("Suggestion_RouteDeliverManager", "RouteCallBack", " on response:", obj, " errorCode is:", Integer.valueOf(i));
        if (deviceCallback != null) {
            if (obj instanceof Integer) {
                deviceCallback.onSendResult(((Integer) obj).intValue());
            } else {
                deviceCallback.onSendResult(i);
            }
        }
    }

    public void c(String str, int i, IBaseResponseCallback iBaseResponseCallback) {
        int b = cba.b();
        e("RouteCallBack", c(str, i, b).e(), new cbf(caw.d(), "Suggestion_RouteDeliverManager", i, b, iBaseResponseCallback), iBaseResponseCallback, new DeviceCallback() { // from class: fbb.4
            @Override // com.huawei.basefitnessadvice.callback.DeviceCallback
            public void onSendResult(int i2) {
                ReleaseLogUtil.e("Suggestion_RouteDeliverManager", "sendShakeHandToDevice onSendResult: ", Integer.valueOf(i2));
            }

            @Override // com.huawei.basefitnessadvice.callback.DeviceCallback
            public void onSendProgress(long j) {
                LogUtil.a("Suggestion_RouteDeliverManager", "sendShakeHandToDevice onSendProgress: ", Long.valueOf(j));
            }
        });
    }

    public void a(final byte[] bArr, final int i, final DeviceCallback deviceCallback) {
        LogUtil.a("Suggestion_RouteDeliverManager", "sendRouteDataToDevice");
        jdx.b(new Runnable() { // from class: fbe
            @Override // java.lang.Runnable
            public final void run() {
                fbb.this.e(bArr, i, deviceCallback);
            }
        });
    }

    /* synthetic */ void e(byte[] bArr, int i, final DeviceCallback deviceCallback) {
        int b = cba.b();
        spn.b c2 = c(NavigationFileParser.BIN_FILE_NAME, bArr, b, i);
        if (c2 == null) {
            ReleaseLogUtil.e("Suggestion_RouteDeliverManager", "builder=null");
            return;
        }
        IBaseResponseCallback iBaseResponseCallback = new IBaseResponseCallback() { // from class: fbk
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                fbb.c(DeviceCallback.this, i2, obj);
            }
        };
        e("RouteCallBack", c2.e(), new cbc(caw.d(), "Suggestion_RouteDeliverManager", i, b, iBaseResponseCallback), iBaseResponseCallback, deviceCallback);
    }

    static /* synthetic */ void c(DeviceCallback deviceCallback, int i, Object obj) {
        ReleaseLogUtil.e("Suggestion_RouteDeliverManager", "RouteCallBack", " on response with objData:", obj, " errorCode is :", Integer.valueOf(i));
        if (deviceCallback != null) {
            if (obj instanceof Integer) {
                deviceCallback.onSendResult(((Integer) obj).intValue());
            } else {
                deviceCallback.onSendResult(i);
            }
        }
    }

    public void e(final String str, final spn spnVar, final cbc cbcVar, final IBaseResponseCallback iBaseResponseCallback, final DeviceCallback deviceCallback) {
        if (cbcVar != null) {
            caw.d().registerCallback(cbcVar, cbcVar.a());
            LogUtil.a("Suggestion_RouteDeliverManager", "registerSendMsgDataReceiver");
        }
        caw.d().sendCommandToDevice(spnVar, new SendCallback() { // from class: fbb.3
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                if (i == 500000 || i == 207) {
                    if (i == 207) {
                        ReleaseLogUtil.d("Suggestion_RouteDeliverManager", str, " send success, resultCode is ", Integer.valueOf(i));
                        d(i);
                        fbb.this.b(spnVar);
                        return;
                    }
                    LogUtil.a("Suggestion_RouteDeliverManager", str, " send called success, resultCode is ", Integer.valueOf(i));
                    return;
                }
                ReleaseLogUtil.c("Suggestion_RouteDeliverManager", str, " send failed, resultCode is  ", Integer.valueOf(i));
                fbb.this.b(spnVar);
                if (cbcVar != null) {
                    caw d = caw.d();
                    cbc cbcVar2 = cbcVar;
                    d.unregisterCallback(cbcVar2, cbcVar2.a());
                }
                iBaseResponseCallback.d(1, Integer.valueOf(i));
            }

            private void d(int i) {
                if (cbcVar == null) {
                    iBaseResponseCallback.d(0, Integer.valueOf(i));
                }
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
                DeviceCallback deviceCallback2 = deviceCallback;
                if (deviceCallback2 != null) {
                    deviceCallback2.onSendProgress(j);
                }
                LogUtil.a("Suggestion_RouteDeliverManager", str, " send progress is ", Long.valueOf(j));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str2) {
                LogUtil.a("Suggestion_RouteDeliverManager", "sendCommandToDevice onFileTransferReport transferWay: ", str2);
            }
        });
    }

    private byte[] e(String str) {
        String c2 = cvx.c(str);
        String str2 = cvx.e(5) + cvx.e(c2.length() / 2) + c2;
        LogUtil.a("Suggestion_RouteDeliverManager", str2);
        return cvx.a(str2);
    }

    private spn.b c(String str, int i, int i2) {
        byte[] e = e(str);
        cba.b bVar = new cba.b();
        bVar.e(i).c(i2).b(1).a(e.length);
        cba a2 = bVar.a();
        LogUtil.a("Suggestion_RouteDeliverManager", a2.toString());
        ByteBuffer allocate = ByteBuffer.allocate(a2.j());
        allocate.put(a2.e());
        allocate.put(e);
        allocate.flip();
        spn.b bVar2 = new spn.b();
        bVar2.c(allocate.array());
        return bVar2;
    }

    private spn.b c(String str, byte[] bArr, int i, int i2) {
        cba.b bVar = new cba.b();
        bVar.e(i2).c(i).b(1).a(bArr.length);
        if (b(str, bVar.a().e(), bArr)) {
            spn.b bVar2 = new spn.b();
            bVar2.a(new File(b(str)));
            return bVar2;
        }
        LogUtil.b("Suggestion_RouteDeliverManager", "getWorkoutMsgBuilder write data to file error");
        return null;
    }

    private String b(String str) {
        return BaseApplication.getContext().getFilesDir() + File.separator + str;
    }

    private boolean b(String str, byte[] bArr, byte[] bArr2) {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    fileOutputStream = BaseApplication.getContext().openFileOutput(str, 0);
                    fileOutputStream.write(bArr);
                    fileOutputStream.write(bArr2);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException unused) {
                            LogUtil.b("Suggestion_RouteDeliverManager", "writefile finally  failed IOException");
                        }
                    }
                    return true;
                } catch (IOException unused2) {
                    LogUtil.b("Suggestion_RouteDeliverManager", "IOException  failed");
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException unused3) {
                            LogUtil.b("Suggestion_RouteDeliverManager", "writefile finally  failed IOException");
                        }
                    }
                    return false;
                }
            } catch (FileNotFoundException unused4) {
                LogUtil.b("Suggestion_RouteDeliverManager", "FileNotFoundException  failed");
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused5) {
                        LogUtil.b("Suggestion_RouteDeliverManager", "writefile finally  failed IOException");
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused6) {
                    LogUtil.b("Suggestion_RouteDeliverManager", "writefile finally  failed IOException");
                }
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(spn spnVar) {
        if (spnVar != null && spnVar.d() == 2) {
            FileUtils.i(spnVar.a());
        } else {
            LogUtil.a("Suggestion_RouteDeliverManager", " releaseMsgResource failed  with null message.");
        }
    }
}
