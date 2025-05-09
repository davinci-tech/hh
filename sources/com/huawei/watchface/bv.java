package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.WatchFaceDownloadQueryFreeResp;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import java.util.LinkedHashMap;

/* loaded from: classes9.dex */
public class bv extends BaseHttpRequest<WatchFaceDownloadQueryFreeResp> {

    /* renamed from: a, reason: collision with root package name */
    private String f10936a;
    private String b;
    private int c;

    public bv(String str, String str2, int i) {
        this.f10936a = str;
        this.b = str2;
        this.c = i;
    }

    public String c() {
        return a(WatchFaceHttpUtil.r(), d(), getReqHeaders());
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a() {
        return CommonUtils.getVersionCode() + "";
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public LinkedHashMap<String, String> getReqHeaders() {
        return super.getReqHeaders();
    }

    private String d() {
        HwLog.i("HitopReqDownloadQueryFreeThread", "buildRequestParams enter");
        try {
            String str = this.b + this.c;
            LinkedHashMap<String, ?> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("productId", this.f10936a);
            linkedHashMap.put("licenseReq", d(str));
            linkedHashMap.put(JsbMapKeyNames.H5_USER_ID, WatchFaceHttpUtil.a().getUserId());
            linkedHashMap.put("hitopid", this.b);
            linkedHashMap.put("type", Integer.valueOf(this.c));
            linkedHashMap.put(FaqConstants.FAQ_EMUIVERSION, HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWatchFaceMaxVersion(false, false));
            return a(linkedHashMap);
        } catch (Exception e) {
            HwLog.i("HitopReqDownloadQueryFreeThread", "Exception : " + HwLog.printException(e));
            return "";
        }
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public WatchFaceDownloadQueryFreeResp c(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.i("HitopReqDownloadQueryFreeThread", "dealReceive json is empty");
            return null;
        }
        try {
            return (WatchFaceDownloadQueryFreeResp) GsonHelper.fromJson(str, WatchFaceDownloadQueryFreeResp.class);
        } catch (Exception e) {
            HwLog.e("HitopReqDownloadQueryFreeThread", "handleJsonData exception: " + HwLog.printException(e));
            return null;
        }
    }
}
