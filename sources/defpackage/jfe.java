package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
class jfe {
    jfe() {
    }

    public void a() {
        a(c());
        LogUtil.a("AutoSetUp", "execute auto_send_command. end");
    }

    private DeviceInfo c() {
        DeviceInfo d = jpt.d("AutoSetUp");
        return d != null ? d : jpu.e("AutoSetUp");
    }

    private void a(DeviceInfo deviceInfo) {
        if (jpv.a()) {
            jot.a().a(deviceInfo);
            jjb.b().d(deviceInfo);
            joy.d().e(deviceInfo);
            joh.a().e(deviceInfo);
            jog.c().b(deviceInfo);
            jfz.d().e(deviceInfo);
            jfy.b().c(deviceInfo);
        }
    }
}
