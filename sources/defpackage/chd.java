package defpackage;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.health.device.kit.hwwsp.TlvDeviceCommand;
import com.huawei.health.device.util.EventBus;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/* loaded from: classes3.dex */
public class chd {

    /* renamed from: a, reason: collision with root package name */
    private List<che> f718a;
    private final Handler b;
    private int c;
    private int d;
    private String e;
    private boolean f;
    private int g;
    private String h;
    private FileInputStream i;
    private int j;
    private cgx k;
    private double l;
    private int m;
    private int n;
    private crl o;

    private chd() {
        this.j = 0;
        this.i = null;
        this.f718a = new ArrayList(16);
        this.g = 0;
        this.c = 0;
        this.d = 0;
        this.l = 1.0d;
        this.f = false;
        this.m = 0;
        this.o = new crl();
        this.b = new Handler(Looper.getMainLooper()) { // from class: chd.3
            @Override // android.os.Handler
            public void dispatchMessage(Message message) {
                super.dispatchMessage(message);
                LogUtil.a("TlvOtaUpdate", "msg what:", Integer.valueOf(message.what));
                int i = message.what;
                if (i != 1) {
                    switch (i) {
                        case 257:
                            chd.this.b(message.arg1);
                            break;
                        case 258:
                            chd.this.c(message.arg1);
                            break;
                        case 259:
                            Object obj = message.obj;
                            if (obj == null || !(obj instanceof byte[])) {
                                LogUtil.h("TlvOtaUpdate", "result data is not legal");
                                chd.this.d(109001, 0L);
                                break;
                            } else {
                                try {
                                    chd.this.b((byte[]) obj);
                                    break;
                                } catch (cwg unused) {
                                    LogUtil.b("TlvOtaUpdate", "dealWithResultData exception");
                                    return;
                                }
                            }
                        default:
                            LogUtil.h("TlvOtaUpdate", "dispatchMessage default");
                            break;
                    }
                }
                chd.this.c(1);
            }
        };
    }

    public static chd b() {
        return e.e;
    }

    public void d(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3)) {
            LogUtil.h("TlvOtaUpdate", "update filePath is null");
            d(5, 0L);
            return;
        }
        File file = new File(str3);
        if (!file.exists()) {
            LogUtil.h("TlvOtaUpdate", "update file is not exist");
            d(5, 0L);
            return;
        }
        try {
            String canonicalPath = file.getCanonicalPath();
            this.h = canonicalPath;
            this.j = this.o.e(canonicalPath);
            this.n = 0;
            this.e = str;
            b(this.e, str2, (int) this.o.a(str3), this.n);
        } catch (IOException unused) {
            LogUtil.b("TlvOtaUpdate", "update file get path IOException");
            d(5, 0L);
        }
    }

    private void b(String str, String str2, int i, int i2) {
        TlvDeviceCommand tlvDeviceCommand = new TlvDeviceCommand();
        tlvDeviceCommand.setServiceId(9);
        tlvDeviceCommand.setCommandId(1);
        String c = cvx.c(str2);
        byte[] a2 = cvx.a((cvx.e(1) + cvx.e(c.length() / 2) + c) + (cvx.e(2) + cvx.e(2) + cvx.a((int) ((short) i))) + (cvx.e(3) + cvx.e(1) + cvx.e(i2)));
        tlvDeviceCommand.setDataContent(a2);
        tlvDeviceCommand.setDataLength(a2.length);
        tlvDeviceCommand.setIdentify(str);
        LogUtil.a("TlvOtaUpdate", "5.9.1 sending");
        a(tlvDeviceCommand, false);
    }

    private void c(chc chcVar) {
        if (chcVar == null) {
            LogUtil.h("TlvOtaUpdate", "5.9.1 result: queryAllow == null");
            d(109001, 0L);
            return;
        }
        int c = chcVar.c();
        int a2 = chcVar.a();
        LogUtil.a("TlvOtaUpdate", "5.9.1 result: errorCode=", Integer.valueOf(c), ", batteryThreshold=", Integer.valueOf(a2));
        if (a2 <= -1) {
            d(513, 0L);
            return;
        }
        if (a2 <= 20) {
            d(4, 0L);
        } else if (c == 100000) {
            a();
        } else {
            d(513, 0L);
        }
    }

    private void a() {
        TlvDeviceCommand tlvDeviceCommand = new TlvDeviceCommand();
        tlvDeviceCommand.setServiceId(9);
        tlvDeviceCommand.setCommandId(2);
        tlvDeviceCommand.setIdentify(this.e);
        LogUtil.a("TlvOtaUpdate", "5.9.2 sending");
        a(tlvDeviceCommand, false);
    }

    private void a(cgx cgxVar) {
        if (cgxVar == null) {
            LogUtil.h("TlvOtaUpdate", "5.9.2 result: parameters is null");
            d(109001, 0L);
            return;
        }
        LogUtil.a("TlvOtaUpdate", "5.9.2 result: mAppWaitTimeout=", Integer.valueOf(cgxVar.b()), ", restartTimeout=", Integer.valueOf(cgxVar.c()), ", unitSize=", Integer.valueOf(cgxVar.i()), ", interval=", Long.valueOf(cgxVar.h()), ", isAckEnable=", Boolean.valueOf(cgxVar.a()), ", isOffsetEnable=", Boolean.valueOf(cgxVar.d()));
        this.k = cgxVar;
        this.g = 0;
        this.c = 0;
        this.d = 0;
        this.f = false;
        this.m = 0;
        e();
        this.b.sendEmptyMessageDelayed(1, this.k.e());
    }

    private void d(byte[] bArr) {
        LogUtil.a("TlvOtaUpdate", "5.9.3 sending");
        int length = bArr.length - 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 2, bArr2, 0, length);
        TlvDeviceCommand tlvDeviceCommand = new TlvDeviceCommand();
        tlvDeviceCommand.setServiceId(9);
        tlvDeviceCommand.setCommandId(3);
        tlvDeviceCommand.setDataContent(bArr2);
        tlvDeviceCommand.setDataLength(length);
        a(tlvDeviceCommand, false);
    }

    private void a(cgv cgvVar) {
        if (this.b.hasMessages(1)) {
            this.b.removeMessages(1);
        }
        if (cgvVar == null) {
            LogUtil.h("TlvOtaUpdate", "5.9.3 result: applyReport == null");
            d(109001, 0L);
            return;
        }
        int e2 = (int) cgvVar.e();
        int c = (int) cgvVar.c();
        LogUtil.a("TlvOtaUpdate", "5.9.3 result: file_offset=", Integer.valueOf(e2), ", file_length=", Integer.valueOf(c));
        a(e2, e2 - this.g, c);
        this.g = e2 + c;
        a(e2, c, cgvVar.a());
        if (this.f) {
            double d = this.l;
            if (d != 0.0d) {
                int i = (int) (((this.c + this.d) / d) * 100.0d);
                a(i);
                Message obtainMessage = this.b.obtainMessage();
                obtainMessage.what = 257;
                obtainMessage.arg1 = i;
                this.b.sendMessage(obtainMessage);
            }
        }
    }

    private void a(int i, int i2, List<Integer> list) {
        cgx cgxVar = this.k;
        long j = 0;
        if (cgxVar == null) {
            LogUtil.h("TlvOtaUpdate", "5.9.4 mParameters is null");
            d(109001, 0L);
            return;
        }
        int i3 = cgxVar.i() - 9;
        int e2 = e(i2, i3);
        int i4 = i;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < e2) {
            int e3 = e(i2, i3, i4, i6);
            if (e3 < 0) {
                LogUtil.h("TlvOtaUpdate", "5.9.4 outof sendlength");
                d(513, j);
                return;
            }
            byte[] c = c(i4 - i, i, e3);
            if (i7 > 255) {
                i7 = 0;
            }
            if (list != null && i5 < list.size()) {
                if (list.get(i5).intValue() != 0) {
                    i6 += e3;
                    i4 += i3;
                    i7++;
                    i5++;
                    j = 0;
                } else {
                    this.c -= e3;
                }
            }
            b(i7, c, i4);
            this.c += e3;
            i6 += e3;
            i4 += i3;
            i7++;
            i5++;
            j = 0;
        }
        int e4 = this.k.e();
        LogUtil.a("TlvOtaUpdate", "5.9.4 sendMessage Delayed ", Integer.valueOf(e4));
        this.b.sendEmptyMessageDelayed(1, e4);
    }

    private void b(int i, byte[] bArr, int i2) {
        String e2 = cvx.e(i);
        String d = cvx.d(bArr);
        StringBuilder sb = new StringBuilder(16);
        if (this.k.d()) {
            sb.append(cvx.b(i2));
            sb.append(e2);
            sb.append(d);
        } else {
            sb.append(e2);
            sb.append(d);
        }
        TlvDeviceCommand tlvDeviceCommand = new TlvDeviceCommand();
        tlvDeviceCommand.setServiceId(9);
        tlvDeviceCommand.setCommandId(4);
        tlvDeviceCommand.setDataContent(cvx.a(sb.toString()));
        tlvDeviceCommand.setDataLength(cvx.a(sb.toString()).length);
        a(tlvDeviceCommand, false);
    }

    private void c(cgy cgyVar) {
        if (cgyVar == null) {
            LogUtil.h("TlvOtaUpdate", "5.9.5 result: pkgSizeReport == null");
            d(109001, 0L);
        } else {
            this.f = true;
            this.l = cgyVar.e();
            this.d = (int) cgyVar.b();
            LogUtil.a("TlvOtaUpdate", "5.9.5 result: mOtaFileSizeV2=", Double.valueOf(this.l), ", mDeviceReceived=", Integer.valueOf(this.d));
        }
    }

    private void e(int i) {
        this.f = false;
        this.b.removeMessages(1);
        if (i == 0) {
            LogUtil.a("TlvOtaUpdate", "5.9.6 result: ota check failed");
            d(2, 0L);
        } else if (i == 1) {
            LogUtil.a("TlvOtaUpdate", "5.9.6 result: ota check success");
            d(0, 0L);
        } else {
            LogUtil.h("TlvOtaUpdate", "\"5.9.6 result: ota check default");
            d(513, 0L);
        }
        d();
    }

    private void d() {
        TlvDeviceCommand tlvDeviceCommand = new TlvDeviceCommand();
        tlvDeviceCommand.setServiceId(9);
        tlvDeviceCommand.setCommandId(6);
        tlvDeviceCommand.setDataContent(null);
        tlvDeviceCommand.setDataLength(0);
        a(tlvDeviceCommand, false);
    }

    private void e() {
        String str = cvx.e(1) + cvx.e(1) + cvx.e(1);
        LogUtil.a("TlvOtaUpdate", "5.9.9 otaStatus : ", str);
        TlvDeviceCommand tlvDeviceCommand = new TlvDeviceCommand();
        tlvDeviceCommand.setServiceId(9);
        tlvDeviceCommand.setCommandId(9);
        tlvDeviceCommand.setDataContent(cvx.a(str));
        tlvDeviceCommand.setDataLength(cvx.a(str).length);
        tlvDeviceCommand.setIdentify(this.e);
        a(tlvDeviceCommand, false);
    }

    public boolean a(byte[] bArr) {
        Message obtainMessage = this.b.obtainMessage();
        obtainMessage.what = 259;
        obtainMessage.obj = bArr;
        return this.b.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(int i, long j) {
        Message obtainMessage = this.b.obtainMessage();
        obtainMessage.what = 258;
        obtainMessage.arg1 = i;
        if (j > 0) {
            return this.b.sendMessageDelayed(obtainMessage, j);
        }
        return this.b.sendMessage(obtainMessage);
    }

    private void a(TlvDeviceCommand tlvDeviceCommand, boolean z) {
        if (cgt.e().k() != 2) {
            LogUtil.h("TlvOtaUpdate", "HwWspMeasureController not connected");
            d(104007, 0L);
        } else {
            cgt.e().d(this.o.c(tlvDeviceCommand), z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        Intent intent = new Intent();
        intent.putExtra("update_status", i);
        EventBus.d(new EventBus.b("upgrade_update_status", intent));
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        Intent intent = new Intent();
        intent.putExtra("update_progress", i);
        EventBus.d(new EventBus.b("upgrade_update_progress", intent));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(byte[] bArr) throws cwg {
        LogUtil.c("TlvOtaUpdate", "result data:", cvx.d(bArr));
        if (bArr.length < 2) {
            LogUtil.h("TlvOtaUpdate", "result data is invalid");
            d(109001, 0L);
            return;
        }
        byte b = bArr[1];
        if (b == 1) {
            c(this.o.b(bArr));
            return;
        }
        if (b == 2) {
            a(this.o.c(bArr));
            return;
        }
        if (b == 3) {
            if (this.k.a()) {
                d(bArr);
            }
            a(this.o.a(bArr));
        } else if (b == 5) {
            c(this.o.d(bArr));
        } else if (b == 6) {
            e(this.o.e(bArr));
        } else {
            LogUtil.h("TlvOtaUpdate", "dealWithResultData default");
        }
    }

    private void a(int i, int i2, int i3) {
        if (d(i).length <= 0) {
            if (b(i, i3)) {
                return;
            }
            b(i, i2, i3);
            return;
        }
        LogUtil.h("TlvOtaUpdate", "getTransferDataBuffer dataPackage.length > 0");
    }

    private byte[] d(int i) {
        for (che cheVar : this.f718a) {
            if (cheVar != null && cheVar.b() == i) {
                return cheVar.c();
            }
        }
        return new byte[0];
    }

    private boolean b(int i, int i2) {
        FileInputStream fileInputStream = this.i;
        if (fileInputStream != null && fileInputStream.getChannel() != null) {
            try {
                if (i < this.i.getChannel().position()) {
                    LogUtil.h("TlvOtaUpdate", "getFileCache offset < mInputStream.getChannel().position()");
                    c();
                    b(i, i, i2);
                    return true;
                }
            } catch (IOException unused) {
                LogUtil.b("TlvOtaUpdate", "IOException mInputStream.getChannel().position()");
            }
        }
        return false;
    }

    private void c() {
        FileInputStream fileInputStream = this.i;
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
                this.i = null;
            } catch (IOException unused) {
                LogUtil.b("TlvOtaUpdate", "IOException closeStream exception");
            }
        }
        this.f718a.clear();
    }

    private void b(int i, int i2, int i3) {
        long j;
        int i4;
        if (TextUtils.isEmpty(this.h)) {
            LogUtil.h("TlvOtaUpdate", "getTransferDataBuffer info is null");
        }
        if (this.i == null) {
            try {
                File file = new File(CommonUtil.c(this.h));
                if (!file.exists() && !file.createNewFile()) {
                    LogUtil.h("TlvOtaUpdate", "getTransferDataBuffer, The file already exists continue");
                }
                LogUtil.a("TlvOtaUpdate", "openInputStream");
                this.i = FileUtils.openInputStream(file);
            } catch (IOException unused) {
                LogUtil.b("TlvOtaUpdate", "IOException getTransferDataBuffer() exception");
            }
        }
        FileInputStream fileInputStream = this.i;
        if (fileInputStream == null) {
            LogUtil.h("TlvOtaUpdate", "mInputStream == null");
            return;
        }
        byte[] bArr = new byte[i3];
        try {
            j = fileInputStream.skip(i2);
            try {
                i4 = this.i.read(bArr);
            } catch (IOException unused2) {
                LogUtil.b("TlvOtaUpdate", "getTransferDataBuffer IOException");
                i4 = 0;
                if (j != -1) {
                }
                LogUtil.h("TlvOtaUpdate", "getTransferDataBuffer set read position occur error or readSize error");
                return;
            }
        } catch (IOException unused3) {
            j = 0;
        }
        if (j != -1 || i4 == -1) {
            LogUtil.h("TlvOtaUpdate", "getTransferDataBuffer set read position occur error or readSize error");
            return;
        }
        if (this.f718a.size() > 4) {
            this.f718a.remove(0);
        }
        this.f718a.add(new che(i, bArr));
    }

    private int e(int i, int i2) {
        if (i % i2 == 0) {
            return i / i2;
        }
        return (i / i2) + 1;
    }

    private int e(int i, int i2, int i3, int i4) {
        if ((i - i4) / i2 == 0) {
            i2 = i % i2;
        }
        if ((i + i3) - i4 <= this.j) {
            return i2;
        }
        LogUtil.a("TlvOtaUpdate", "out of Index");
        return this.j - i3;
    }

    private byte[] c(int i, int i2, int i3) {
        byte[] bArr = new byte[i3];
        byte[] d = d(i2);
        if (d.length <= 0) {
            return new byte[0];
        }
        System.arraycopy(d, i, bArr, 0, i3);
        return bArr;
    }

    private void a(int i) {
        if (i % 5 == 0 && i != this.m) {
            this.m = i;
            LogUtil.a("TlvOtaUpdate", 1, "ota count : ", Integer.valueOf(this.m));
        }
        if (i == 100) {
            this.m = 0;
        }
    }

    static class e {
        private static chd e = new chd();
    }
}
