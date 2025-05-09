package com.huawei.watchface;

import com.google.gson.JsonElement;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.ResponseListener;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.BatchReportResp;
import com.huawei.watchface.mvp.model.datatype.ResApplyRecord;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.thread.MembershipPostRequest;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class bi extends MembershipPostRequest<BatchReportResp> {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10925a = "bi";
    private final List<ResApplyRecord> b;

    public bi(List<ResApplyRecord> list, ResponseListener<BatchReportResp> responseListener) {
        super(responseListener, WatchFaceHttpUtil.j());
        this.b = list;
    }

    @Override // com.huawei.watchface.mvp.model.thread.MembershipPostRequest, com.huawei.watchface.cc
    public String buildRequestParams() {
        try {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("applyRecords", this.b);
            d("portraitImage1");
            JsonElement jsonElement = (JsonElement) GsonHelper.fromJson(GsonHelper.toJson(linkedHashMap), JsonElement.class);
            return jsonElement != null ? jsonElement.getAsJsonObject().toString() : "";
        } catch (Exception e) {
            HwLog.i(f10925a, "Exception : " + HwLog.printException(e));
            return "";
        }
    }

    @Override // com.huawei.watchface.mvp.model.thread.MembershipPostRequest
    public void addHeaderParams(LinkedHashMap<String, String> linkedHashMap) {
        linkedHashMap.putAll(super.getReqHeaders());
        linkedHashMap.put("x-hc", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getCommonCountryCode());
        linkedHashMap.put("versionCode", CommonUtils.getVersionCode() + "");
        linkedHashMap.put("accept-lang", MLAsrConstants.LAN_ZH);
        linkedHashMap.remove("Content-Type");
        linkedHashMap.remove(WatchFaceConstant.CIPHER_VERSION);
    }
}
