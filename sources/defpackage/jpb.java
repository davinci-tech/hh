package defpackage;

import com.huawei.hwdevice.mainprocess.mgr.settings.sendswitchcommandtask.SendSwitchTask;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jpb extends SendSwitchTask {

    /* renamed from: a, reason: collision with root package name */
    private int f14008a;
    private joy d;

    public jpb(String str, int i, int i2, joy joyVar) {
        super(str, i);
        this.d = joyVar;
        this.f14008a = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        joy joyVar = this.d;
        if (joyVar != null) {
            joyVar.a(this.f14008a);
            this.d = null;
        } else {
            LogUtil.h("HwDefaultSleepTask", "mHeartRateHelper is null.");
        }
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.settings.sendswitchcommandtask.SendSwitchTask
    public boolean isWillRunTask() {
        return (this.f14008a & 1) == 1;
    }
}
