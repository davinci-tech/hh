package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hwdevice.mainprocess.mgr.settings.sendswitchcommandtask.SendSwitchTask;

/* loaded from: classes5.dex */
public class jpf extends SendSwitchTask {

    /* renamed from: a, reason: collision with root package name */
    private joy f14012a;
    private DeviceCapability e;

    @Override // com.huawei.hwdevice.mainprocess.mgr.settings.sendswitchcommandtask.SendSwitchTask
    public boolean isWillRunTask() {
        return true;
    }

    public jpf(String str, int i, joy joyVar, DeviceCapability deviceCapability) {
        super(str, i);
        this.f14012a = joyVar;
        this.e = deviceCapability;
    }

    @Override // java.lang.Runnable
    public void run() {
        joy joyVar = this.f14012a;
        if (joyVar != null) {
            joyVar.d(this.e);
            this.f14012a = null;
        }
    }
}
