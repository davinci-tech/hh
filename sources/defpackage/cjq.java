package defpackage;

import android.bluetooth.BluetoothGattDescriptor;
import android.os.Bundle;
import com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil;
import com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback;

/* loaded from: classes3.dex */
public class cjq {

    /* renamed from: a, reason: collision with root package name */
    private boolean f750a;
    private IAsynBleTaskCallback b;
    private Bundle c;
    private byte[] d;
    private a e;
    private BleTaskQueueUtil.TaskType f;
    private int g;
    private int j;

    public cjq(BleTaskQueueUtil.TaskType taskType, byte[] bArr) {
        this(taskType, bArr, false, -1);
    }

    public cjq(BleTaskQueueUtil.TaskType taskType, byte[] bArr, boolean z) {
        this(taskType, bArr, z, -1);
    }

    public cjq(BleTaskQueueUtil.TaskType taskType, byte[] bArr, boolean z, int i) {
        this(taskType, bArr, z, i, true);
    }

    public cjq(BleTaskQueueUtil.TaskType taskType, byte[] bArr, boolean z, int i, boolean z2) {
        this.j = 0;
        this.f = taskType;
        this.d = bArr == null ? null : (byte[]) bArr.clone();
        this.f750a = z;
        this.g = i;
        this.e = new a(z2) { // from class: cjq.3
            {
                e(new byte[][]{BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE, BluetoothGattDescriptor.ENABLE_INDICATION_VALUE});
            }
        };
    }

    public BleTaskQueueUtil.TaskType f() {
        return this.f;
    }

    public byte[] c() {
        byte[] bArr = this.d;
        return bArr == null ? new byte[0] : (byte[]) bArr.clone();
    }

    public boolean i() {
        return this.f750a;
    }

    public int h() {
        return this.g;
    }

    public IAsynBleTaskCallback e() {
        return this.b;
    }

    public void d(IAsynBleTaskCallback iAsynBleTaskCallback) {
        this.b = iAsynBleTaskCallback;
    }

    public int d() {
        return this.j;
    }

    public void b(int i) {
        this.j = i;
    }

    public a b() {
        return this.e;
    }

    public void c(a aVar) {
        this.e = aVar;
    }

    public void FO_(Bundle bundle) {
        this.c = bundle;
    }

    public Bundle FN_() {
        return this.c;
    }

    public class a {

        /* renamed from: a, reason: collision with root package name */
        private boolean f751a;
        private byte[][] b;

        public a(boolean z) {
            this.f751a = z;
        }

        public void e(byte[][] bArr) {
            if (bArr != null) {
                this.b = (byte[][]) bArr.clone();
            }
        }

        public byte[][] c() {
            byte[][] bArr = this.b;
            return bArr != null ? (byte[][]) bArr.clone() : new byte[0][];
        }

        public boolean b() {
            return this.f751a;
        }
    }
}
