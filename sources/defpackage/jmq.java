package defpackage;

import android.text.TextUtils;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.initese.tsm.laser.LaserTsmService;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public final class jmq implements LaserTsmService {

    /* renamed from: a, reason: collision with root package name */
    private jmo f13961a = new jmo();

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.initese.tsm.laser.LaserTsmService
    public int executeTsmCommand(jmp jmpVar) {
        LogUtil.a("LaserTsmServiceImpl", "tsm executeTsmCommand now.");
        if (jmpVar == null || TextUtils.isEmpty(jmpVar.c()) || TextUtils.isEmpty(jmpVar.a()) || TextUtils.isEmpty(jmpVar.b())) {
            LogUtil.h("LaserTsmServiceImpl", "tsm executeTsmCommand, params illegal.");
            return LaserTsmService.EXECUTE_RESULT_DEFAULT_ERROR;
        }
        int b = this.f13961a.b(jmpVar);
        LogUtil.a("LaserTsmServiceImpl", "tsm executeTsmCommand, result:", Integer.valueOf(b));
        return b;
    }
}
