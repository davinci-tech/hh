package defpackage;

import com.huawei.hwdevice.mainprocess.mgr.settings.sendswitchcommandtask.SendSwitchTask;

/* loaded from: classes5.dex */
public class jpc extends SendSwitchTask {
    private joy b;

    @Override // com.huawei.hwdevice.mainprocess.mgr.settings.sendswitchcommandtask.SendSwitchTask
    public boolean isWillRunTask() {
        return true;
    }

    public jpc(String str, int i, joy joyVar) {
        super(str, i);
        this.b = joyVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        joy joyVar = this.b;
        if (joyVar != null) {
            joyVar.e();
            this.b = null;
        }
    }
}
