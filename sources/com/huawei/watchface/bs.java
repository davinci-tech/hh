package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import java.util.LinkedHashMap;
import org.json.JSONException;

/* loaded from: classes7.dex */
public class bs extends BaseHttpRequest<cg> {

    /* renamed from: a, reason: collision with root package name */
    private String f10934a;
    private String b;
    private String c;
    private int d;
    private cg e;

    public bs(String str, String str2, String str3, int i) {
        this.f10934a = str;
        this.b = str2;
        this.c = str3;
        this.d = i;
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a(String str) {
        HwLog.i("GetWatchFaceTryOutInfoThread", "getResponse url");
        return a(WatchFaceHttpUtil.o(), str + c(), getReqHeaders());
    }

    private String c() {
        String str = this.c + this.d;
        LinkedHashMap<String, ?> linkedHashMap = new LinkedHashMap<>();
        try {
            linkedHashMap.put("productId", this.f10934a);
            linkedHashMap.put(RecommendConstants.VER, "1.6");
            linkedHashMap.put("licenseReq", d(str));
            linkedHashMap.put(JsbMapKeyNames.H5_USER_ID, WatchFaceHttpUtil.a().getUserId());
            linkedHashMap.put(FaqConstants.FAQ_EMUIVERSION, HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWatchFaceMaxVersion(false, false));
            linkedHashMap.put("isZip", Boolean.TRUE.toString());
            linkedHashMap.put("isFontGzip", Boolean.TRUE.toString());
            linkedHashMap.put("hitopid", this.c);
            linkedHashMap.put("type", Integer.valueOf(this.d));
            linkedHashMap.put("isVipVersion", Integer.valueOf(CommonUtils.A()));
        } catch (Exception e) {
            HwLog.e("GetWatchFaceTryOutInfoThread", "getRequestParams Exception:" + HwLog.printException(e));
        }
        String a2 = a(linkedHashMap);
        HwLog.i("GetWatchFaceTryOutInfoThread", "getRequestParams -- mProductId:" + this.f10934a);
        return a2;
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a() {
        return this.b;
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public cg c(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.i("GetWatchFaceTryOutInfoThread", "dealReceive json is empty");
            return null;
        }
        try {
            cg cgVar = new cg();
            this.e = cgVar;
            cgVar.a(str);
        } catch (JSONException e) {
            HwLog.e("GetWatchFaceTryOutInfoThread", "dealReceive " + HwLog.printException((Exception) e));
        }
        return this.e;
    }
}
