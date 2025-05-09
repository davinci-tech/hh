package defpackage;

import android.text.TextUtils;
import com.huawei.health.device.wifi.entity.builder.BaseBuilder;
import com.huawei.health.device.wifi.entity.utils.JsonParser;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class ctb extends BaseBuilder {
    private static final long serialVersionUID = -3512139991530124788L;
    private cti b;

    public ctb(cti ctiVar) {
        this.b = null;
        super.setUri("/.sys/sessMngr");
        super.setDefaultTime(OpAnalyticsConstants.H5_LOADING_DELAY);
        this.b = ctiVar;
    }

    @Override // com.huawei.health.device.wifi.entity.builder.BaseBuilder
    public String makeRequestStream() {
        HashMap hashMap = new HashMap(4);
        cti ctiVar = this.b;
        if (ctiVar == null) {
            return "";
        }
        hashMap.put("type", ctiVar.a());
        hashMap.put("modeSupport", this.b.c());
        hashMap.put("sn1", String.valueOf(this.b.d()));
        hashMap.put("seq", this.b.e());
        String jSONObject = JsonParser.b(hashMap).toString();
        LogUtil.a("CoapSessionInterfaceBuilder", "makeRequestStream: result is ", jSONObject);
        return jSONObject;
    }

    @Override // com.huawei.health.device.wifi.entity.builder.BaseBuilder
    public ctc makeResponseEntity(String str) {
        LogUtil.c("CoapSessionInterfaceBuilder", "makeResponseEntity: stream 1 is ", e(str));
        ctg ctgVar = new ctg();
        if (str != null && str.length() > 0) {
            Map<String, Object> c = JsonParser.c(str);
            try {
                if (c.containsKey("errcode")) {
                    Object obj = c.get("errcode");
                    if (obj instanceof Integer) {
                        ctgVar.a((Integer) obj);
                    } else {
                        LogUtil.h("CoapSessionInterfaceBuilder", "errorCodeObj not instanceof Integer");
                    }
                }
                if (c.containsKey("sessId")) {
                    Object obj2 = c.get("sessId");
                    if (obj2 instanceof String) {
                        ctgVar.b((String) obj2);
                    } else {
                        LogUtil.h("CoapSessionInterfaceBuilder", "sessIdObj not instanceof String");
                    }
                }
                c(ctgVar, c);
            } catch (ClassCastException unused) {
                LogUtil.b("CoapSessionInterfaceBuilder", "makeResponseEntity catch ClassCastException");
            }
        } else {
            LogUtil.h("CoapSessionInterfaceBuilder", "makeResponseEntity stream is null");
        }
        return ctgVar;
    }

    private void c(ctg ctgVar, Map<String, Object> map) {
        if (map.containsKey("modeResp")) {
            Object obj = map.get("modeResp");
            if (obj instanceof Integer) {
                ctgVar.d((Integer) obj);
            } else {
                LogUtil.h("CoapSessionInterfaceBuilder", "modeRespObj not instanceof Integer");
            }
        }
        if (map.containsKey("sn2")) {
            Object obj2 = map.get("sn2");
            if (obj2 instanceof String) {
                ctgVar.c((String) obj2);
            } else {
                LogUtil.h("CoapSessionInterfaceBuilder", "sn2Obj not instanceof String");
            }
        }
        if (map.containsKey("seq")) {
            if (map.get("seq") instanceof Integer) {
                ctgVar.e(Long.valueOf(((Integer) r0).intValue()));
            } else {
                LogUtil.h("CoapSessionInterfaceBuilder", "seqObj not instanceof Integer");
            }
        }
        if (map.containsKey("dtlsPort")) {
            Object obj3 = map.get("dtlsPort");
            if (obj3 instanceof Integer) {
                ctgVar.c((Integer) obj3);
            } else {
                LogUtil.h("CoapSessionInterfaceBuilder", "dtlsPortObj not instanceof Integer");
            }
        }
    }

    public static String e(String str) {
        return !TextUtils.isEmpty(str) ? Pattern.compile(System.lineSeparator()).matcher(str).replaceAll("") : "";
    }
}
