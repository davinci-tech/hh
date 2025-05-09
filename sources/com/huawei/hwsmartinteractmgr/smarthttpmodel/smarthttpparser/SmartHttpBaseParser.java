package com.huawei.hwsmartinteractmgr.smarthttpmodel.smarthttpparser;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartResponseWrapper;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class SmartHttpBaseParser<U> {
    public static final String LABELS = "labels";
    public static final String RESULT_CODE = "resultCode";
    public static final String RESULT_DESC = "resultDesc";
    private static final String TAG = "SMART_SmartHttpParser";
    protected int mParseType;
    protected String mResolve;

    protected abstract SmartResponseWrapper<U> parseDistinctResponse(SmartResponseWrapper<U> smartResponseWrapper);

    public SmartHttpBaseParser(int i) {
        this.mParseType = i;
    }

    public SmartResponseWrapper<U> parse(String str) {
        this.mResolve = str;
        SmartResponseWrapper<U> parseSharedResponse = parseSharedResponse();
        parseDistinctResponse(parseSharedResponse);
        return parseSharedResponse;
    }

    private SmartResponseWrapper<U> parseSharedResponse() {
        try {
            JSONObject jSONObject = new JSONObject(this.mResolve);
            int optInt = jSONObject.optInt("resultCode");
            SmartResponseWrapper<U> smartResponseWrapper = new SmartResponseWrapper<>(optInt, jSONObject.optString("resultDesc"), this.mParseType);
            return optInt != 0 ? smartResponseWrapper : smartResponseWrapper;
        } catch (JSONException e) {
            LogUtil.b(TAG, "parse common json error ", e.getMessage());
            return new SmartResponseWrapper<>(101, "parse json failed", this.mParseType);
        }
    }
}
