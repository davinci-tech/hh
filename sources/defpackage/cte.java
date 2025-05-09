package defpackage;

import android.text.TextUtils;
import com.huawei.health.device.wifi.entity.builder.BaseBuilder;
import com.huawei.health.device.wifi.entity.utils.JsonParser;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class cte extends BaseBuilder {
    private static final long serialVersionUID = 1486107962578854578L;

    /* renamed from: a, reason: collision with root package name */
    private transient csz f11457a;
    private ctj c;

    public cte(ctj ctjVar, String str, String str2) {
        this.c = null;
        super.setUri("/cloudSetup");
        super.setDefaultTime(5000L);
        this.c = ctjVar;
        csz cszVar = new csz();
        this.f11457a = cszVar;
        cszVar.d(str, str2);
    }

    @Override // com.huawei.health.device.wifi.entity.builder.BaseBuilder
    public String makeRequestStream() {
        HashMap hashMap = new HashMap(16);
        ctj ctjVar = this.c;
        if (ctjVar == null) {
            return "";
        }
        if (ctjVar.c() != null) {
            hashMap.put("code", this.c.c());
        }
        if (this.c.d() != null) {
            hashMap.put("devId", this.c.d());
        }
        if (this.c.a() != null) {
            hashMap.put("psk", this.c.a());
        }
        if (this.c.e() != null) {
            hashMap.put("cloudUrl", this.c.e());
        }
        String jSONObject = JsonParser.b(hashMap).toString();
        cpw.c(true, "CoapIdentifyAdvancedCodeBuilder", "encryption String: ", jSONObject);
        cpw.c(true, "CoapIdentifyAdvancedCodeBuilder", "makeRequestStream: ", "");
        return jSONObject;
    }

    @Override // com.huawei.health.device.wifi.entity.builder.BaseBuilder
    public ctc makeResponseEntity(String str) {
        Map<String, Object> c;
        ctj ctjVar = new ctj();
        if (!TextUtils.isEmpty(str) && (c = JsonParser.c(str)) != null && c.containsKey("errcode")) {
            try {
                ctjVar.c(Integer.parseInt(c.get("errcode").toString()));
            } catch (NumberFormatException e) {
                cpw.e(true, "CoapIdentifyAdvancedCodeBuilder", e.getMessage());
            }
        }
        return ctjVar;
    }

    public csz e() {
        return this.f11457a;
    }
}
