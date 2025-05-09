package defpackage;

import com.huawei.health.device.wifi.entity.builder.BaseBuilder;
import com.huawei.health.device.wifi.entity.utils.JsonParser;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class csx extends BaseBuilder {
    private static final long serialVersionUID = -2154071061393944584L;
    private cta b;

    public csx(cta ctaVar) {
        this.b = null;
        super.setUri("/.well-known/core?st=cloudSetup");
        super.setDefaultTime(5000L);
        this.b = ctaVar;
    }

    @Override // com.huawei.health.device.wifi.entity.builder.BaseBuilder
    public String makeRequestStream() {
        HashMap hashMap = new HashMap(16);
        cta ctaVar = this.b;
        if (ctaVar == null) {
            return "";
        }
        if (ctaVar.d() != null) {
            hashMap.put(ProfileRequestConstants.HIV, this.b.d());
        }
        if (this.b.e() != null) {
            hashMap.put("sn1", this.b.e());
        }
        return JsonParser.b(hashMap).toString();
    }

    @Override // com.huawei.health.device.wifi.entity.builder.BaseBuilder
    public ctc makeResponseEntity(String str, String str2) {
        cpw.c(false, "CoapDeviceInfoBuilder", "makeResponseEntity: stream is ", str);
        return parseResponseEntity(str, str2);
    }

    @Override // com.huawei.health.device.wifi.entity.builder.BaseBuilder
    public ctc makeResponseEntity(String str) {
        cpw.c(false, "CoapDeviceInfoBuilder", "makeResponseEntity: stream is ", str);
        return parseResponseEntity(str);
    }
}
