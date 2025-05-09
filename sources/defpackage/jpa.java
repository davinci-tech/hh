package defpackage;

import com.huawei.hwdevice.mainprocess.mgr.settings.sendswitchcommandtask.SendSwitchTask;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jpa extends SendSwitchTask {

    /* renamed from: a, reason: collision with root package name */
    private int f14007a;
    private joy c;

    public jpa(String str, int i, int i2, joy joyVar) {
        super(str, i);
        this.c = joyVar;
        this.f14007a = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.c != null) {
            LogUtil.a("HwDefaultHeartRateTask", "run sendDefaultHeartRate");
            this.c.d(this.f14007a);
            this.c = null;
            return;
        }
        LogUtil.h("HwDefaultHeartRateTask", "mHeartRateHelper is null.");
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.settings.sendswitchcommandtask.SendSwitchTask
    public boolean isWillRunTask() {
        int i = this.f14007a;
        return (i & 2) == 2 || (i & 8) == 8;
    }
}
