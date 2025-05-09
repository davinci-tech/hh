package defpackage;

import com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil;

/* loaded from: classes3.dex */
public class cfu {

    /* renamed from: a, reason: collision with root package name */
    private cfr f690a = new cfr();
    private BleTaskQueueUtil b;

    public cfu(BleTaskQueueUtil bleTaskQueueUtil) {
        this.b = bleTaskQueueUtil;
    }

    public void c(String str, String str2) {
        if (this.b != null) {
            this.b.b(new cjq(BleTaskQueueUtil.TaskType.WRITE_BLE_FILE, this.f690a.a(str, str2), false));
            this.b.e();
        }
    }

    public void a(int i, int i2) {
        if (this.b != null) {
            this.b.b(new cjq(BleTaskQueueUtil.TaskType.WRITE_BLE_FILE, this.f690a.b(i, i2), false));
            this.b.e();
        }
    }

    public void d(int i) {
        if (this.b != null) {
            this.b.b(new cjq(BleTaskQueueUtil.TaskType.WRITE_BLE_FILE, this.f690a.b(i), false));
            this.b.e();
        }
    }

    public void e(int i, int i2, int i3) {
        if (this.b != null) {
            this.b.b(new cjq(BleTaskQueueUtil.TaskType.WRITE_BLE_FILE, this.f690a.d(i, i2, i3), false));
            this.b.e();
        }
    }

    public void e(int i, int i2) {
        if (this.b != null) {
            this.b.b(new cjq(BleTaskQueueUtil.TaskType.WRITE_BLE_FILE, this.f690a.a(i, i2), false));
            this.b.e();
        }
    }
}
