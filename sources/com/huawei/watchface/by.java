package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.watchface.mvp.model.datatype.WatchFacePayResultBean;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public class by extends BaseHttpRequest<Integer> {

    /* renamed from: a, reason: collision with root package name */
    private String f10940a;
    private String b;
    private String c;
    private String d;

    public by(String str, String str2, String str3, String str4) {
        this.f10940a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a(String str) {
        return a(WatchFaceHttpUtil.s() + c(), str, getReqHeaders());
    }

    private String c() {
        LinkedHashMap<String, ?> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("tradeId", this.b);
        linkedHashMap.put("payResult", this.c);
        linkedHashMap.put("reason", this.d);
        linkedHashMap.put("productId", this.f10940a);
        String a2 = a(linkedHashMap);
        HwLog.i("HitopReqPayResultThread", "getRequestParams -- mProductId:" + this.f10940a);
        return a2;
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a() {
        return CommonUtils.getVersionCode() + "";
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Integer c(String str) {
        int i = -1;
        if (str == null || TextUtils.isEmpty(str)) {
            HwLog.e("HitopReqPayResultThread", "dealReceive json is empty");
            return -1;
        }
        try {
            WatchFacePayResultBean watchFacePayResultBean = (WatchFacePayResultBean) GsonHelper.fromJson(str, WatchFacePayResultBean.class);
            if (watchFacePayResultBean != null) {
                i = Integer.parseInt(watchFacePayResultBean.getResultCode());
                HwLog.i("HitopReqPayResultThread", "watchFacePayResultBean errCode = " + i);
            } else {
                HwLog.e("HitopReqPayResultThread", "dealReceive watch face unknown error!");
            }
            return Integer.valueOf(i);
        } catch (Exception e) {
            HwLog.e("HitopReqPayResultThread", "handleJsonData exception: " + HwLog.printException(e));
            return -1;
        }
    }
}
