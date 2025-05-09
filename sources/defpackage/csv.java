package defpackage;

import com.huawei.health.device.wifi.entity.builder.BaseBuilder;
import com.huawei.health.device.wifi.entity.model.Entity;

/* loaded from: classes3.dex */
public class csv {
    private BaseBuilder c = null;

    public void a(cta ctaVar, Entity.EntityResponseCallback entityResponseCallback) {
        this.c = new csx(ctaVar);
        ctl.b(ctx.a("238"));
        cpw.c(true, "CoapsDeviceApi", "createSessionInterface: body = ", this.c.makeRequestStream());
        ctl.e(this.c, entityResponseCallback);
    }

    public void e(String str, cti ctiVar, Entity.EntityResponseCallback entityResponseCallback) {
        this.c = new ctb(ctiVar);
        ctl.b(str);
        cpw.c(true, "CoapsDeviceApi", "createSessionInterface: ip = ", str, "; body = ", this.c.makeRequestStream());
        ctl.c(this.c, entityResponseCallback);
    }

    public void b(ctj ctjVar, String str, Long l, int i) {
        this.c = new ctd(ctjVar, str, l, i);
    }

    public void d(String str, String str2, String str3, Entity.EntityResponseCallback entityResponseCallback) {
        ((ctd) this.c).e(str2, str3);
        ctl.b(str);
        cpw.c(true, "CoapsDeviceApi", "writeIdentifyCode: ip = ", str, "; body = ", this.c.makeRequestByte());
        ctl.a(this.c, entityResponseCallback);
    }

    public void a(String str, ctj ctjVar, String str2, String str3) {
        cte cteVar = new cte(ctjVar, str2, str3);
        ctl.b(str);
        this.c = cteVar;
        cpw.c(true, "CoapsDeviceApi", "writeAdvancedIdentifyCode: ip = ", str, "; body = ", cteVar.makeRequestStream());
    }

    public void e(Integer num, Entity.EntityResponseCallback entityResponseCallback) {
        BaseBuilder baseBuilder = this.c;
        if (baseBuilder instanceof cte) {
            ctl.c(baseBuilder, num, ((cte) baseBuilder).e(), entityResponseCallback);
        } else {
            cpw.d(true, "CoapsDeviceApi", "mBaseBuilder not instanceof CoapIdentifyAdvancedCodeBuilder");
        }
    }
}
