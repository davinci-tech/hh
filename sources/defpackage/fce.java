package defpackage;

import com.huawei.alarm.AlarmManager;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public class fce extends AlarmManager<fca> {
    public boolean d(fca fcaVar) {
        if (fcaVar.b() != 1000) {
            LogUtil.b("SleepAlarmManager", "alarm id match fail");
            return false;
        }
        return addOrUpdateAlarm(fcaVar);
    }

    public fca b() {
        Set<fca> alarms = getAlarms();
        fca fcaVar = new fca(1000, 7, 0, null);
        Iterator<fca> it = alarms.iterator();
        return it.hasNext() ? it.next() : fcaVar;
    }

    @Override // com.huawei.alarm.AlarmManager
    public String getAlarmKey() {
        return "SleepAlarmManager";
    }
}
