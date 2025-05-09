package defpackage;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class cfv {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f691a = new Object();
    private static cfv d;
    private ExtendHandler e;
    private WspFileTransferCallback i;
    private cga g = new cga();
    private cft f = new cft();
    private ConcurrentHashMap<Integer, cfn> b = new ConcurrentHashMap<>(20);
    private ConcurrentHashMap<Integer, cgc> c = new ConcurrentHashMap<>(20);

    private cfv() {
    }

    public static cfv b() {
        cfv cfvVar;
        synchronized (f691a) {
            if (d == null) {
                d = new cfv();
            }
            cfvVar = d;
        }
        return cfvVar;
    }

    public void a(WspFileTransferCallback wspFileTransferCallback) {
        this.i = wspFileTransferCallback;
    }

    public void c(byte[] bArr) {
        if (bArr == null || bArr.length <= 1) {
            LogUtil.h("WspFileCommandHandler", "onHandleUploadBleFileData() dataBytes is error.");
            return;
        }
        byte b = bArr[1];
        if (b == 2) {
            LogUtil.a("WspFileCommandHandler", "get 5.44.2 command");
            d(bArr);
            return;
        }
        if (b == 3) {
            LogUtil.a("WspFileCommandHandler", "get 5.44.3 command");
            b(bArr);
            return;
        }
        if (b == 5) {
            LogUtil.a("WspFileCommandHandler", "get 5.44.5 command");
            e(bArr);
            return;
        }
        if (b == 6) {
            LogUtil.a("WspFileCommandHandler", "get 5.44.6 command");
            h(bArr);
        } else if (b == 7) {
            LogUtil.a("WspFileCommandHandler", "get 5.44.7 command");
            g(bArr);
        } else if (b == 8) {
            LogUtil.a("WspFileCommandHandler", "get 5.44.8 command");
            a(bArr);
        } else {
            LogUtil.a("WspFileCommandHandler", "onHandleUploadBleFileData() default");
        }
    }

    private void a(byte[] bArr) {
        LogUtil.a("WspFileCommandHandler", "onHandleFileInfoRequestCommand enter.");
        cfz d2 = this.g.d(bArr);
        if (d2 == null) {
            LogUtil.h("WspFileCommandHandler", "onHandleFileSendCommand wspFileRequestCommand is null");
            return;
        }
        WspFileTransferCallback wspFileTransferCallback = this.i;
        if (wspFileTransferCallback != null) {
            wspFileTransferCallback.onStart(d2.i().size());
        } else {
            LogUtil.b("WspFileCommandHandler", "onHandleFileInfoRequestCommand callback is null");
        }
    }

    private void g(byte[] bArr) {
        e(330005);
        cfx a2 = this.g.a(bArr);
        if (a2 == null) {
            LogUtil.h("WspFileCommandHandler", "onHandleFileSendCommand wspFileInfoCommand is null");
            return;
        }
        cfn cfnVar = new cfn();
        cfnVar.c(a2.j());
        cfnVar.a(a2.f());
        cfnVar.d(a2.h());
        cfnVar.b(a2.g());
        LogUtil.a("WspFileCommandHandler", "File info ", Integer.valueOf(a2.j()), "|", a2.f(), "|", Integer.valueOf(a2.g()));
        this.b.put(Integer.valueOf(cfnVar.c()), cfnVar);
        if (cfnVar.g() == 0) {
            int j = a2.j();
            this.f.e(j, 1);
            this.i.onSuccess(j, 30004, cfnVar.i());
        } else {
            this.f.d(a2.j(), 1);
        }
    }

    private void d(byte[] bArr) {
        cfw b = this.g.b(bArr);
        if (b == null) {
            LogUtil.h("WspFileCommandHandler", "onHandleFileCheckCommand wspFileCheckCommand is null");
            return;
        }
        int j = b.j();
        Object[] objArr = new Object[6];
        objArr[0] = "wspFileInfo is ";
        objArr[1] = this.b.get(Integer.valueOf(j)) == null ? Constants.NULL : "not null";
        objArr[2] = "file id is ";
        objArr[3] = Integer.valueOf(j);
        objArr[4] = "mTransferringFileMap size:";
        objArr[5] = Integer.valueOf(this.b.size());
        LogUtil.a("WspFileCommandHandler", objArr);
        cfn cfnVar = this.b.get(Integer.valueOf(j));
        if (cfnVar != null) {
            cfnVar.b(b.g());
            this.f.a(j);
        }
    }

    private void b(byte[] bArr) {
        cgc e2 = this.g.e(bArr);
        if (e2 == null) {
            LogUtil.h("WspFileCommandHandler", "onHandleFileParameterCommand wspFileTransferParameterCommand is null");
            return;
        }
        e2.a(e2.d() + 5);
        int c = e2.c();
        this.c.put(Integer.valueOf(c), e2);
        if (this.b.get(Integer.valueOf(c)) != null) {
            cfn cfnVar = this.b.get(Integer.valueOf(c));
            LogUtil.a("WspFileCommandHandler", "get file log for id:", Integer.valueOf(cfnVar.c()), " get file log for file name:", cfnVar.a());
            cfnVar.e(ByteBuffer.allocate(cfnVar.g()));
            this.f.e(c, cfnVar.b(), cfnVar.d());
            if (this.c.get(Integer.valueOf(c)) != null) {
                a(c, this.c.get(Integer.valueOf(c)).d());
            }
        }
    }

    private void e(byte[] bArr) {
        cge c;
        String d2 = cvx.d(bArr);
        LogUtil.c("WspFileCommandHandler", "data all package:", d2);
        if (TextUtils.isEmpty(d2) || d2.length() < 16 || (c = this.g.c(d2)) == null) {
            return;
        }
        int g = c.g();
        cfn cfnVar = this.b.get(Integer.valueOf(g));
        if (cfnVar != null) {
            LogUtil.a("WspFileCommandHandler", "for 5.44.4 return data fileid:", Integer.valueOf(g), " filename:", cfnVar.a());
            int h = c.h();
            int j = c.j();
            if (cfnVar.b() != h || cfnVar.f() != j) {
                ReleaseLogUtil.d("R_Weight_WspFileCommandHandler", "handleDeviceDataReceived fileOffset or psn error");
                return;
            }
            WspFileTransferCallback wspFileTransferCallback = this.i;
            String e2 = (wspFileTransferCallback == null || TextUtils.isEmpty(wspFileTransferCallback.getFilePath())) ? "" : e(this.i.getFilePath(), cfnVar.a());
            cfnVar.e(e2);
            LogUtil.a("WspFileCommandHandler", "save log file in:", e2);
            int g2 = cfnVar.g() > 0 ? (h * 100) / cfnVar.g() : 0;
            LogUtil.a("WspFileCommandHandler", "reportProgressForUi progress:", Integer.valueOf(g2));
            WspFileTransferCallback wspFileTransferCallback2 = this.i;
            if (wspFileTransferCallback2 != null) {
                wspFileTransferCallback2.onProgress(g, g2, cfnVar.a());
            }
            c(c.f(), g, j, cfnVar);
            return;
        }
        ReleaseLogUtil.d("R_Weight_WspFileCommandHandler", "onHandleFileReceivedCommand fileId error");
    }

    private void c(String str, int i, int i2, cfn cfnVar) {
        e(i);
        LogUtil.a("WspFileCommandHandler", "save file package:", str);
        byte[] a2 = cvx.a(str);
        ReleaseLogUtil.e("R_Weight_WspFileCommandHandler", "init handleFileReceivedRequest", Integer.valueOf(i2), "|", Integer.valueOf(a2.length));
        LogUtil.a("WspFileCommandHandler", "handleFileReceivedRequest put bytes");
        ByteBuffer e2 = cfnVar.e();
        if (e2 == null) {
            LogUtil.h("WspFileCommandHandler", "handleFileReceivedRequest getByteAll is null");
            return;
        }
        ReleaseLogUtil.e("R_Weight_WspFileCommandHandler", "fileId:", Integer.valueOf(i), ", bytes size:", Integer.valueOf(e2.limit()), "|", Integer.valueOf(e2.position()));
        if (e2.position() + a2.length <= cfnVar.g()) {
            e2.put(a2);
            cfnVar.e(cfnVar.b() + a2.length);
        } else {
            int g = cfnVar.g() - e2.position();
            LogUtil.a("WspFileCommandHandler", "last size:", Integer.valueOf(g));
            byte[] bArr = new byte[g];
            ByteBuffer.wrap(a2).get(bArr);
            e2.put(bArr);
            cfnVar.e(cfnVar.b() + g);
        }
        LogUtil.a("WspFileCommandHandler", "bytes size:", Integer.valueOf(e2.limit()), "|", Integer.valueOf(e2.position()));
        ReleaseLogUtil.e("R_Weight_WspFileCommandHandler", "out handleFileReceivedRequest, ", Integer.valueOf(cfnVar.b()), "|", Integer.valueOf(cfnVar.g()));
        if (cfnVar.b() < cfnVar.g()) {
            LogUtil.a("WspFileCommandHandler", "go to next");
            this.f.e(i, cfnVar.b(), cfnVar.d());
        } else {
            e(i);
            this.f.e(i, 1);
            e(cfnVar);
            ReleaseLogUtil.e("R_Weight_WspFileCommandHandler", "log file saved");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00a6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0099 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r3v9, types: [java.io.FileOutputStream, java.io.OutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(defpackage.cfn r9) {
        /*
            r8 = this;
            java.lang.String r0 = "createFileWithByte bufferedOutputStream close error"
            java.lang.String r1 = "createFileWithByte outputStream close error"
            java.lang.String r2 = "WspFileCommandHandler"
            java.lang.String r3 = r9.i()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L1c
            java.lang.String r9 = "file path is empty"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            java.lang.String r0 = "R_Weight_WspFileCommandHandler"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r0, r9)
            return
        L1c:
            java.io.File r3 = new java.io.File
            java.lang.String r4 = r9.i()
            r3.<init>(r4)
            r4 = 0
            java.io.File r5 = r3.getParentFile()     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6c
            if (r5 == 0) goto L4d
            java.io.FileOutputStream r3 = org.apache.commons.io.FileUtils.openOutputStream(r3)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6c
            java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream     // Catch: java.io.IOException -> L6d java.lang.Throwable -> L93
            r5.<init>(r3)     // Catch: java.io.IOException -> L6d java.lang.Throwable -> L93
            java.nio.ByteBuffer r4 = r9.e()     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4b
            byte[] r4 = r4.array()     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4b
            r5.write(r4)     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4b
            r5.flush()     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4b
            r8.a(r9)     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4b
            r4 = r3
            goto L4e
        L48:
            r9 = move-exception
            r4 = r5
            goto L94
        L4b:
            r4 = r5
            goto L6d
        L4d:
            r5 = r4
        L4e:
            if (r4 == 0) goto L5b
            r4.close()     // Catch: java.io.IOException -> L54
            goto L5b
        L54:
            java.lang.Object[] r9 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r9)
        L5b:
            if (r5 == 0) goto L92
            r5.close()     // Catch: java.io.IOException -> L61
            goto L92
        L61:
            java.lang.Object[] r9 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r9)
            goto L92
        L69:
            r9 = move-exception
            r3 = r4
            goto L97
        L6c:
            r3 = r4
        L6d:
            r9 = 1
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> L93
            java.lang.String r5 = "createFileWithByte File IOException"
            r6 = 0
            r9[r6] = r5     // Catch: java.lang.Throwable -> L93
            com.huawei.hwlogsmodel.LogUtil.b(r2, r9)     // Catch: java.lang.Throwable -> L93
            if (r3 == 0) goto L85
            r3.close()     // Catch: java.io.IOException -> L7e
            goto L85
        L7e:
            java.lang.Object[] r9 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r9)
        L85:
            if (r4 == 0) goto L92
            r4.close()     // Catch: java.io.IOException -> L8b
            goto L92
        L8b:
            java.lang.Object[] r9 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r9)
        L92:
            return
        L93:
            r9 = move-exception
        L94:
            r7 = r4
            r4 = r3
            r3 = r7
        L97:
            if (r4 == 0) goto La4
            r4.close()     // Catch: java.io.IOException -> L9d
            goto La4
        L9d:
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r1)
        La4:
            if (r3 == 0) goto Lb1
            r3.close()     // Catch: java.io.IOException -> Laa
            goto Lb1
        Laa:
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r0)
        Lb1:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cfv.e(cfn):void");
    }

    private String e(String str, String str2) {
        return CommonUtil.c(str + File.separator + str2);
    }

    private void a(cfn cfnVar) {
        int c = cfnVar.c();
        if (this.b.get(Integer.valueOf(c)) != null) {
            this.b.remove(Integer.valueOf(c));
        }
        if (this.c.get(Integer.valueOf(c)) != null) {
            this.c.remove(Integer.valueOf(c));
        }
        boolean isEmpty = this.b.isEmpty();
        WspFileTransferCallback wspFileTransferCallback = this.i;
        if (wspFileTransferCallback != null) {
            wspFileTransferCallback.onSuccess(c, isEmpty ? 30000 : 30004, cfnVar.i());
        }
    }

    private void h(byte[] bArr) {
        Optional<cfy> c = this.g.c(bArr);
        if (!c.isPresent()) {
            LogUtil.h("WspFileCommandHandler", "onHandleFileResultNotifyCommand wspFileResultNotifyCommand is null");
            return;
        }
        if (c.get().b() != 100000) {
            WspFileTransferCallback wspFileTransferCallback = this.i;
            if (wspFileTransferCallback != null) {
                wspFileTransferCallback.onFailure(30001, null);
                return;
            }
            return;
        }
        a(330005, 10);
    }

    class e implements Handler.Callback {
        private e() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            LogUtil.a("WspFileCommandHandler", "msg is ", Integer.valueOf(message.arg1));
            if (message.arg1 != 100) {
                return false;
            }
            int i = message.what;
            LogUtil.a("WspFileCommandHandler", "wait timeout! file id:", Integer.valueOf(i));
            if (i == 10) {
                if (cfv.this.i != null) {
                    cfv.this.i.onSuccess(i, 30004, null);
                }
            } else {
                if (cfv.this.i != null) {
                    cfv.this.i.onFailure(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, null);
                }
                cfv.this.f.e(i, 3);
            }
            cfv.this.e(i);
            return true;
        }
    }

    private void a(int i, int i2) {
        if (this.e == null) {
            this.e = HandlerCenter.yt_(new e(), "WspFileCommandHandler");
        }
        if (i2 != 0) {
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.arg1 = 100;
            this.e.sendMessage(obtain, i2 * 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        ExtendHandler extendHandler = this.e;
        if (extendHandler != null) {
            extendHandler.removeMessages(i);
        }
    }
}
