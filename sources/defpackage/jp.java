package defpackage;

import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class jp {
    public static DataReportRequest c(js jsVar) {
        DataReportRequest dataReportRequest = new DataReportRequest();
        if (jsVar == null) {
            return null;
        }
        dataReportRequest.os = jsVar.d;
        dataReportRequest.rpcVersion = jsVar.g;
        dataReportRequest.bizType = "1";
        HashMap hashMap = new HashMap();
        dataReportRequest.bizData = hashMap;
        hashMap.put("apdid", jsVar.b);
        dataReportRequest.bizData.put("apdidToken", jsVar.c);
        dataReportRequest.bizData.put("umidToken", jsVar.e);
        dataReportRequest.bizData.put("dynamicKey", jsVar.f14043a);
        dataReportRequest.deviceData = jsVar.j;
        return dataReportRequest;
    }

    public static jt b(DataReportResult dataReportResult) {
        jt jtVar = new jt();
        if (dataReportResult == null) {
            return null;
        }
        jtVar.d = dataReportResult.success;
        jtVar.e = dataReportResult.resultCode;
        Map<String, String> map = dataReportResult.resultData;
        if (map != null) {
            jtVar.c = map.get("apdid");
            jtVar.b = map.get("apdidToken");
            jtVar.g = map.get("dynamicKey");
            jtVar.f = map.get(HwExerciseConstants.JSON_NAME_TIME_INTERVAL);
            jtVar.i = map.get("webrtcUrl");
            jtVar.h = "";
            String str = map.get("drmSwitch");
            if (mq.b(str)) {
                if (str.length() > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str.charAt(0));
                    jtVar.f14060a = sb.toString();
                }
                if (str.length() >= 3) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str.charAt(2));
                    jtVar.j = sb2.toString();
                }
            }
            if (map.containsKey("apse_degrade")) {
                jtVar.l = map.get("apse_degrade");
            }
        }
        return jtVar;
    }
}
