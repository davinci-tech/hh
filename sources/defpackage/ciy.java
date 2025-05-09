package defpackage;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil;
import com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback;
import com.huawei.health.device.util.EventBus;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class ciy {

    /* renamed from: a, reason: collision with root package name */
    private static ciy f735a;
    private static final Object b = new Object();
    private int f;
    private List<byte[]> i;
    private boolean j;
    private long k;
    private int h = 0;
    private int g = 4;
    private int d = 0;
    private boolean c = false;
    private final Handler e = new Handler(Looper.getMainLooper()) { // from class: ciy.3
        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            LogUtil.a("WspOtaUpdate", "msg what:", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                LogUtil.a("WspOtaUpdate", "update progress.");
                return;
            }
            if (i == 2) {
                ciy.this.j = false;
                if (message.arg1 == 0) {
                    LogUtil.a("WspOtaUpdate", "update success.");
                } else if (message.arg1 == 1) {
                    LogUtil.h("WspOtaUpdate", "update fail timeout");
                } else if (message.arg1 == 2) {
                    LogUtil.h("WspOtaUpdate", "update fail CS error");
                } else if (message.arg1 == 4) {
                    LogUtil.h("WspOtaUpdate", "update fail power low");
                } else {
                    LogUtil.h("WspOtaUpdate", "update fail");
                }
                ciy.this.c(message.arg1);
                return;
            }
            if (i == 5) {
                ciy.this.b();
                return;
            }
            if (i == 6) {
                ciy.this.a();
                return;
            }
            if (i == 101) {
                cgt.e().g();
                return;
            }
            if (i == 102) {
                int i2 = message.arg1;
                if (message.obj instanceof byte[]) {
                    ciy.this.e(i2, (byte[]) message.obj);
                    return;
                }
                return;
            }
            LogUtil.h("WspOtaUpdate", "mHandler msg default");
        }
    };

    static /* synthetic */ int e(ciy ciyVar) {
        int i = ciyVar.g;
        ciyVar.g = i - 1;
        return i;
    }

    private ciy() {
    }

    public void b(boolean z) {
        this.c = z;
    }

    public void d(byte[] bArr) {
        LogUtil.a("WspOtaUpdate", "upgrade time:", Long.valueOf(System.currentTimeMillis() - this.k));
        Message obtainMessage = this.e.obtainMessage();
        if (bArr == null || bArr.length <= 3) {
            LogUtil.h("WspOtaUpdate", "receiverOtaComplete invalid parameter");
            return;
        }
        int i = bArr[3] & 255;
        if (i == 1) {
            LogUtil.a("WspOtaUpdate", "receiver MCU upgrade");
        } else if (i == 2) {
            LogUtil.a("WspOtaUpdate", "receiver BLE upgrade");
        } else if (i == 3) {
            LogUtil.a("WspOtaUpdate", "receiver MCU and BLE upgrade");
        } else {
            LogUtil.h("WspOtaUpdate", "receiver default");
        }
        if (bArr.length <= 4) {
            LogUtil.h("WspOtaUpdate", "receiverOtaComplete invalid parameter caused by constant START_TRANSFER_INDEX");
            return;
        }
        int i2 = bArr[4] & 255;
        if (i2 == 0) {
            obtainMessage.arg1 = 0;
        } else if (i2 == 1) {
            obtainMessage.arg1 = 1;
        } else if (i2 == 2) {
            obtainMessage.arg1 = 2;
        } else if (i2 != 3) {
            if (i2 == 4) {
                obtainMessage.arg1 = 4;
            } else {
                LogUtil.h("WspOtaUpdate", "receiverOtaComplete default");
            }
        }
        obtainMessage.what = 2;
        this.e.sendMessage(obtainMessage);
    }

    public static ciy c() {
        ciy ciyVar;
        synchronized (b) {
            if (f735a == null) {
                f735a = new ciy();
            }
            ciyVar = f735a;
        }
        return ciyVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        Intent intent = new Intent();
        intent.putExtra("update_status", i);
        EventBus.d(new EventBus.b("upgrade_update_status", intent));
    }

    private void e(int i) {
        Intent intent = new Intent();
        intent.putExtra("update_progress", i);
        EventBus.d(new EventBus.b("upgrade_update_progress", intent));
    }

    public boolean e() {
        return this.j;
    }

    public void c(boolean z) {
        this.j = z;
    }

    public void c(String str) {
        if (this.c) {
            this.d = 0;
            this.k = System.currentTimeMillis();
            LogUtil.a("WspOtaUpdate", "updateFilePath is:", str);
            this.h = 0;
            this.j = false;
            if (TextUtils.isEmpty(a(str))) {
                Message obtainMessage = this.e.obtainMessage();
                obtainMessage.what = 2;
                obtainMessage.arg1 = 2;
                this.e.sendMessage(obtainMessage);
                return;
            }
            this.g = 4;
            this.f = 0;
            d(str);
            if (cgt.e().k() != 2 || this.i.size() <= 0) {
                return;
            }
            LogUtil.a("WspOtaUpdate", "ota is connect");
            cgt.e().a(BleTaskQueueUtil.TaskType.REQUEST_OTA_UPGRADE, cgz.c(this.i));
        }
    }

    private void d(String str) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                if (!TextUtils.isEmpty(str)) {
                    fileInputStream = new FileInputStream(str);
                    try {
                        List<byte[]> a2 = a(fileInputStream);
                        this.i = a2;
                        int size = a2.size() - 4;
                        this.h = size;
                        LogUtil.a("WspOtaUpdate", "total size:", Integer.valueOf(size));
                        fileInputStream2 = fileInputStream;
                    } catch (IOException unused) {
                        fileInputStream2 = fileInputStream;
                        LogUtil.b("WspOtaUpdate", "getLoadScaleOta IOException");
                        blv.d(fileInputStream2);
                    } catch (Throwable th) {
                        th = th;
                        blv.d(fileInputStream);
                        throw th;
                    }
                }
            } catch (IOException unused2) {
            }
            blv.d(fileInputStream2);
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = fileInputStream2;
        }
    }

    public List<byte[]> a(InputStream inputStream) throws IOException {
        ArrayList arrayList = new ArrayList(16);
        byte[] bArr = new byte[16];
        while (inputStream.read(bArr) != -1) {
            arrayList.add((byte[]) bArr.clone());
            if (arrayList.size() >= 4) {
                break;
            }
        }
        for (byte[] bArr2 = new byte[13]; inputStream.read(bArr2) != -1; bArr2 = new byte[13]) {
            arrayList.add((byte[]) bArr2.clone());
        }
        return arrayList;
    }

    private static String a(String str) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
            } catch (Throwable th) {
                th = th;
                fileInputStream = null;
            }
        } catch (IOException unused) {
        } catch (NoSuchAlgorithmException e) {
            e = e;
        }
        try {
            byte[] bArr = new byte[1024];
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            int i = 0;
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                messageDigest.update(bArr, 0, read);
                i += read;
                if (i > 5242880) {
                    LogUtil.b("WspOtaUpdate", "buildVerificString exceed max size: ", Integer.valueOf(i));
                    break;
                }
            }
            String d = cvx.d(messageDigest.digest());
            blv.d(fileInputStream);
            return d;
        } catch (IOException unused2) {
            fileInputStream2 = fileInputStream;
            LogUtil.b("WspOtaUpdate", "createChecksum IOException");
            blv.d(fileInputStream2);
            return "";
        } catch (NoSuchAlgorithmException e2) {
            e = e2;
            fileInputStream2 = fileInputStream;
            LogUtil.b("WspOtaUpdate", "createChecksum NoSuchAlgorithmException:", e.getMessage());
            blv.d(fileInputStream2);
            return "";
        } catch (Throwable th2) {
            th = th2;
            blv.d(fileInputStream);
            throw th;
        }
    }

    public void a(byte[] bArr) {
        Message obtainMessage = this.e.obtainMessage();
        obtainMessage.what = 2;
        if (bArr == null || bArr.length <= 3) {
            LogUtil.h("WspOtaUpdate", "parseOtaRequest invalid parameter caused by constant DATA_START_INDEX");
            return;
        }
        byte b2 = bArr[3];
        if (b2 == 0) {
            this.e.sendEmptyMessage(5);
            return;
        }
        if (b2 == 1) {
            obtainMessage.arg1 = 4;
            this.e.sendMessage(obtainMessage);
        } else if (b2 == 2) {
            c(0);
        } else if (b2 == 3) {
            LogUtil.a("WspOtaUpdate", "parseOtaRequest is measuring.");
        } else {
            LogUtil.h("WspOtaUpdate", "parseOtaRequest default.");
        }
    }

    public void b() {
        if (this.c) {
            if (koq.b(this.i, 2)) {
                LogUtil.b("WspOtaUpdate", "sendOtaCheck out of mOtaByteList bound:", 2);
                return;
            }
            byte[] bArr = this.i.get(2);
            if (koq.b(this.i, 3)) {
                LogUtil.b("WspOtaUpdate", "sendOtaCheck out of mOtaByteList bound:", 3);
                return;
            }
            byte[] bArr2 = this.i.get(3);
            int length = bArr.length + bArr2.length;
            byte[] bArr3 = new byte[length];
            int i = 0;
            int i2 = 0;
            while (i2 < bArr.length) {
                bArr3[i2] = bArr[i2];
                i2++;
            }
            LogUtil.a("WspOtaUpdate", "stitching sha256 first package result:", Integer.valueOf(i2), "; bytes length:", Integer.valueOf(length));
            while (i2 < length && i < bArr2.length) {
                bArr3[i2] = bArr2[i];
                i++;
                i2++;
            }
            LogUtil.a("WspOtaUpdate", "sha256 data to be send value:", cvx.d(bArr3));
            cgt.e().a(BleTaskQueueUtil.TaskType.OTA_UPGRADE_SHA_CHECK, bArr3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] e(int i, byte[] bArr) {
        cgt.e().w();
        int i2 = 2;
        int length = bArr.length + 2;
        byte[] bArr2 = new byte[length];
        bArr2[0] = (byte) (i & 255);
        bArr2[1] = (byte) ((i >> 8) & 255);
        for (byte b2 : bArr) {
            if (i2 < length) {
                bArr2[i2] = b2;
                i2++;
            }
        }
        cgt.e().write(new cjq(BleTaskQueueUtil.TaskType.SEND_OTA_PACKAGE_DATA, bArr2), new IAsynBleTaskCallback() { // from class: ciy.5
            @Override // com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback
            public void success(Object obj) {
                ciy.this.g();
            }

            @Override // com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback
            public void failed() {
                ciy.e(ciy.this);
                ciy.this.g();
            }
        });
        return bArr2;
    }

    public void b(byte[] bArr) {
        if (this.c) {
            if (bArr == null || bArr.length <= 3) {
                LogUtil.h("WspOtaUpdate", "parseOtaCheckResult invalid parameter caused by constant DATA_START_INDEX");
            } else {
                if (bArr[3] == 0) {
                    LogUtil.a("WspOtaUpdate", "receiver sha 256 success");
                    this.j = true;
                    this.e.sendEmptyMessage(6);
                    return;
                }
                LogUtil.h("WspOtaUpdate", "receiver sha 256 fail");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        cgt.e().enable(new cjq(BleTaskQueueUtil.TaskType.SEND_OTA_PACKAGE_DATA, new byte[0]), new IAsynBleTaskCallback() { // from class: ciy.1
            @Override // com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback
            public void failed() {
            }

            @Override // com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback
            public void success(Object obj) {
            }
        });
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.c) {
            if (this.g >= this.i.size()) {
                this.e.sendEmptyMessageDelayed(101, 3200L);
                return;
            }
            if (cgt.e().k() != 2) {
                return;
            }
            byte[] bArr = this.i.get(this.g);
            int i = this.g;
            this.f = i - 4;
            int i2 = i + 1;
            this.g = i2;
            if (i2 == this.i.size() - 1) {
                int length = bArr.length;
                System.arraycopy(bArr, 0, new byte[length], 0, length);
                Message obtainMessage = this.e.obtainMessage();
                obtainMessage.what = 102;
                obtainMessage.arg1 = this.f;
                obtainMessage.obj = bArr;
                this.e.sendMessage(obtainMessage);
                LogUtil.a("WspOtaUpdate", "receiver success last package");
                this.e.removeMessages(101);
                this.e.sendEmptyMessageDelayed(101, 3200L);
            } else {
                Message obtainMessage2 = this.e.obtainMessage();
                obtainMessage2.what = 102;
                obtainMessage2.arg1 = this.f;
                obtainMessage2.obj = bArr;
                this.e.sendMessage(obtainMessage2);
            }
            i();
        }
    }

    private void f() {
        if (!koq.d(this.i, this.g)) {
            LogUtil.b("WspOtaUpdate", "sendErrorPackage out of mOtaByteList bound:", Integer.valueOf(this.g), " byteSize:", Integer.valueOf(this.i.size()));
            return;
        }
        byte[] bArr = this.i.get(this.g);
        this.f = this.g - 4;
        cgt.e().w();
        int i = 2;
        int length = bArr.length + 2;
        byte[] bArr2 = new byte[length];
        int i2 = this.f;
        bArr2[0] = (byte) (i2 & 255);
        bArr2[1] = (byte) ((i2 >> 8) & 255);
        for (byte b2 : bArr) {
            if (i < length) {
                bArr2[i] = b2;
                i++;
            }
        }
        cgt.e().write(new cjq(BleTaskQueueUtil.TaskType.SEND_OTA_PACKAGE_DATA, bArr2), new IAsynBleTaskCallback() { // from class: ciy.2
            @Override // com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback
            public void success(Object obj) {
                LogUtil.a("WspOtaUpdate", "sendErrorPackage write data success");
                ciy.this.g();
            }

            @Override // com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback
            public void failed() {
                ciy.e(ciy.this);
                ciy.this.g();
            }
        });
    }

    public void c(byte[] bArr) {
        if (bArr == null || bArr.length <= 3) {
            LogUtil.h("WspOtaUpdate", "receiverOtaPackage invalid parameter caused by constant DATA_START_INDEX");
            return;
        }
        int i = bArr[3] & 255;
        if (i == 0) {
            LogUtil.a("WspOtaUpdate", "PackgeIndex packageNo ");
            return;
        }
        if (i != 1) {
            return;
        }
        this.e.removeMessages(101);
        int e = cgz.e(bArr[5], bArr[4]);
        LogUtil.a("WspOtaUpdate", "errorPackgeIndex packageNo:", Integer.valueOf(e));
        this.g = e + 4;
        f();
    }

    private void i() {
        if (this.f >= this.d) {
            int round = (int) (Math.round(((((r0 * 1.0d) / this.h) * 1.0d) * 100.0d) * 10.0d) / 10.0f);
            LogUtil.a("WspOtaUpdate", "progress:", Integer.valueOf(round), "; otaIndex:", Integer.valueOf(this.f), "; packageLength:", Integer.valueOf(this.h));
            e(round);
            this.d = this.f;
        }
    }

    public byte[] d() {
        return new byte[0];
    }
}
