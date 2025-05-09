package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartResponseWrapper;
import com.huawei.hwsmartinteractmgr.smarthttpmodel.smarthttpparser.SmartHttpBaseParser;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kwh extends SmartHttpBaseParser<Long> {
    public kwh(int i) {
        super(i);
    }

    @Override // com.huawei.hwsmartinteractmgr.smarthttpmodel.smarthttpparser.SmartHttpBaseParser
    public SmartResponseWrapper<Long> parseDistinctResponse(SmartResponseWrapper<Long> smartResponseWrapper) {
        LogUtil.a("CompletionNumParser", "enter parseDistinctResponse");
        if (smartResponseWrapper == null) {
            return smartResponseWrapper;
        }
        long j = 0;
        try {
            JSONObject jSONObject = new JSONObject(this.mResolve);
            j = jSONObject.getLong("completionNum");
            LogUtil.a("CompletionNumParser", "enter parseDistinctResponse jsonObject =", jSONObject.toString());
        } catch (JSONException e) {
            LogUtil.b("CompletionNumParser", "parse json error ", e.getMessage());
            smartResponseWrapper.setResponseCode(101);
            smartResponseWrapper.setResponseDesc("parse json failed");
        }
        smartResponseWrapper.setResponse(Long.valueOf(j));
        return smartResponseWrapper;
    }
}
