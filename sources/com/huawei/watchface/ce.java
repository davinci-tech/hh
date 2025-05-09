package com.huawei.watchface;

import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.ResponseListener;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.querysubinfodetail.QuerySubInfoDetailResp;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.thread.MembershipPostRequest;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.util.LinkedHashMap;

@el(a = {@ek(a = "resultinfo"), @ek(a = "resultcode"), @ek(a = "hadRenewVip"), @ek(a = "subInfo"), @ek(a = "memberStatus")})
/* loaded from: classes7.dex */
public class ce extends MembershipPostRequest<QuerySubInfoDetailResp> {
    public ce(ResponseListener<QuerySubInfoDetailResp> responseListener) {
        super(responseListener, WatchFaceHttpUtil.i());
    }

    public void c() {
        try {
            QuerySubInfoDetailResp querySubInfoDetailResp = (QuerySubInfoDetailResp) GsonHelper.fromJson(b(WatchFaceHttpUtil.i()), QuerySubInfoDetailResp.class);
            if (querySubInfoDetailResp == null) {
                HwLog.d("QueryAllRequest", "membership is null");
            } else {
                ab.a().a(querySubInfoDetailResp);
            }
        } catch (Exception unused) {
            HwLog.e("QueryAllRequest", "refreshMemberStatus Exception");
        }
    }

    @Override // com.huawei.watchface.mvp.model.thread.MembershipPostRequest
    public void addHeaderParams(LinkedHashMap<String, String> linkedHashMap) {
        linkedHashMap.putAll(getReqHeaders());
        linkedHashMap.remove("Content-Type");
        linkedHashMap.remove(WatchFaceConstant.CIPHER_VERSION);
        linkedHashMap.put("productType", "2");
        linkedHashMap.put("x-hc", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getCommonCountryCode());
        linkedHashMap.put("versionCode", CommonUtils.getVersionCode() + "");
    }

    @Override // com.huawei.watchface.mvp.model.thread.MembershipPostRequest, com.huawei.watchface.cc
    public String buildRequestParams() {
        return "";
    }
}
