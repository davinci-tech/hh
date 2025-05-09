package defpackage;

import android.text.TextUtils;
import com.huawei.health.device.wifi.entity.builder.BaseBuilder;
import org.eclipse.californium.core.network.Endpoint;

/* loaded from: classes3.dex */
public class ctr extends uxj {
    public ctp b(BaseBuilder baseBuilder, int i) {
        if (baseBuilder == null || !(baseBuilder instanceof ctd)) {
            return null;
        }
        uxt payload = uxt.b().a(super.a()).setPayload(baseBuilder.makeRequestByte());
        ctd ctdVar = (ctd) baseBuilder;
        if (!TextUtils.isEmpty(ctdVar.b())) {
            uxs uxsVar = new uxs(2048);
            uxsVar.b(ctdVar.b());
            payload.getOptions().d(uxsVar);
        }
        uxs uxsVar2 = new uxs(2053);
        uxsVar2.a(ctdVar.c().longValue() + 1);
        payload.getOptions().d(uxsVar2);
        payload.getOptions().b(i);
        payload.setToken(ctdVar.d());
        if (ctdVar.e() != null) {
            String a2 = ctv.a(ctdVar.e());
            if (!TextUtils.isEmpty(a2)) {
                try {
                    payload.setMID(Integer.valueOf(a2, 16).intValue());
                } catch (NumberFormatException e) {
                    cpw.e(true, "MyCoapClient", e.getMessage());
                }
            }
        }
        return a(payload);
    }

    private ctp a(uxt uxtVar) {
        return e(uxtVar, b(uxtVar));
    }

    private ctp e(uxt uxtVar, Endpoint endpoint) {
        try {
            uxr d = a(uxtVar, endpoint).d(d().longValue());
            if (d == null) {
                return null;
            }
            return new ctp(d);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
