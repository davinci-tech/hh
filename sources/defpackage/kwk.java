package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartResponseWrapper;
import com.huawei.hwsmartinteractmgr.smarthttpmodel.smarthttpparser.SmartHttpBaseParser;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kwk extends SmartHttpBaseParser<List<String>> {
    public kwk(int i) {
        super(i);
    }

    @Override // com.huawei.hwsmartinteractmgr.smarthttpmodel.smarthttpparser.SmartHttpBaseParser
    public SmartResponseWrapper<List<String>> parseDistinctResponse(SmartResponseWrapper<List<String>> smartResponseWrapper) {
        return d(smartResponseWrapper);
    }

    private SmartResponseWrapper<List<String>> d(SmartResponseWrapper<List<String>> smartResponseWrapper) {
        LogUtil.a("SMART_UserLabelParser", "enter parseUserLabel");
        try {
            List<String> a2 = a(new JSONObject(this.mResolve).optJSONArray("labels"));
            LogUtil.c("SMART_UserLabelParser", "labels = ", a2);
            smartResponseWrapper.setResponse(a2);
            return smartResponseWrapper;
        } catch (JSONException e) {
            LogUtil.b("SMART_UserLabelParser", "parse json error ", e.getMessage());
            smartResponseWrapper.setResponseCode(101);
            smartResponseWrapper.setResponseDesc("parse json failed");
            return smartResponseWrapper;
        }
    }

    private List<String> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(5);
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            arrayList.add(jSONArray.optString(i));
        }
        return arrayList;
    }
}
