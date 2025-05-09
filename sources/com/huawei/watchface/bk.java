package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.openalliance.ad.constant.DetailedCreativeType;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.PayInfo;
import com.huawei.watchface.mvp.model.datatype.VipOrderBean;
import com.huawei.watchface.mvp.model.datatype.VipPackageBean;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.info.VipPayInfoCoupons;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.MobileInfoHelper;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

/* loaded from: classes9.dex */
public class bk extends BaseHttpRequest<VipOrderBean> {

    /* renamed from: a, reason: collision with root package name */
    private VipPackageBean f10927a;
    private String b;
    private String c;
    private String d;
    private String e;

    public bk(VipPackageBean vipPackageBean, String str) {
        this.f10927a = vipPackageBean;
        this.b = str;
    }

    public void b(String str) {
        this.d = str;
    }

    public void e(String str) {
        this.e = str;
    }

    public String c() {
        return a(WatchFaceHttpUtil.A(), e(), d());
    }

    private LinkedHashMap<String, String> d() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(FaqConstants.FAQ_EMUIVERSION, HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWatchFaceMaxVersion(false, HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceConnectState() == 0));
        linkedHashMap.put("versionCode", CommonUtils.getVersionCode() + "");
        linkedHashMap.put(WatchFaceConstant.X_CLIENTTRACEID, MobileInfoHelper.generateUUID());
        linkedHashMap.put("x-hc", "CN");
        linkedHashMap.put(ProfileRequestConstants.X_APPID_KEY, "10003");
        linkedHashMap.put("x-devicemodel", !TextUtils.isEmpty(this.e) ? this.e : HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceModel());
        linkedHashMap.put("deviceid", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceIdAsTheme(true));
        linkedHashMap.put("deviceType", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceType());
        linkedHashMap.put("x-brandChannel", String.valueOf(cv.e()));
        linkedHashMap.put("x-accountBrand", String.valueOf(cv.c()));
        linkedHashMap.put("x-appBrand", String.valueOf(cv.c()));
        linkedHashMap.put(WatchFaceConstant.X_UID, HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getUserId());
        if (!TextUtils.isEmpty(this.d)) {
            linkedHashMap.put(WatchFaceConstant.X_SIGN, this.d);
        } else if (WatchFaceHttpUtil.a() != null) {
            linkedHashMap.put(WatchFaceConstant.X_SIGN, WatchFaceHttpUtil.a().getSign());
        }
        linkedHashMap.put("Content-Type", "application/json");
        linkedHashMap.put("terminalType", "1");
        linkedHashMap.put(WatchFaceConstant.CIPHER_VERSION, "2");
        n.a(Environment.getApplicationContext()).a(linkedHashMap, b());
        return linkedHashMap;
    }

    private String e() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        VipPackageBean vipPackageBean = this.f10927a;
        if (vipPackageBean == null) {
            return GsonHelper.toJson(linkedHashMap);
        }
        linkedHashMap.put("productCode", vipPackageBean.getProductCode());
        linkedHashMap.put("productType", this.f10927a.getProductType());
        linkedHashMap.put("versionCode", CommonUtils.getVersionCode() + "");
        VipPackageBean vipPackageBean2 = this.f10927a;
        if (vipPackageBean2 instanceof VipPayInfoCoupons) {
            VipPayInfoCoupons vipPayInfoCoupons = (VipPayInfoCoupons) vipPackageBean2;
            if (!TextUtils.isEmpty(vipPayInfoCoupons.getReportedPrice())) {
                linkedHashMap.put("reportedPrice", vipPayInfoCoupons.getReportedPrice());
            } else if (!TextUtils.isEmpty(vipPayInfoCoupons.getDiscountPrice())) {
                linkedHashMap.put("reportedPrice", vipPayInfoCoupons.getDiscountPrice());
            } else {
                linkedHashMap.put("reportedPrice", vipPayInfoCoupons.getPrice());
            }
            linkedHashMap.put("orderChannel", Integer.valueOf(vipPayInfoCoupons.getOrderChannel()));
            linkedHashMap.put("relProductCode", vipPayInfoCoupons.getRelProductCode());
            linkedHashMap.put("couponId", vipPayInfoCoupons.getCouponId());
            linkedHashMap.put("promotionId", vipPayInfoCoupons.getPromotionId());
            linkedHashMap.put("promotionName", vipPayInfoCoupons.getPromotionName());
            linkedHashMap.put("orderType", vipPayInfoCoupons.getOrderType());
        } else {
            linkedHashMap.put("reportedPrice", TextUtils.isEmpty(vipPackageBean2.getDiscountPrice()) ? this.f10927a.getPrice() : this.f10927a.getDiscountPrice());
            linkedHashMap.put("orderChannel", Integer.valueOf(DetailedCreativeType.LONG_TEXT));
            linkedHashMap.put("relProductCode", this.f10927a.getRelProductCode());
            if (!TextUtils.isEmpty(this.f10927a.getRelProductCode())) {
                linkedHashMap.put("orderType", "2");
            } else {
                linkedHashMap.put("orderType", "1");
            }
        }
        this.c = (String) linkedHashMap.get("reportedPrice");
        if (!TextUtils.isEmpty(this.b)) {
            try {
                linkedHashMap.put(HwPayConstant.KEY_RESERVEDINFOR, URLEncoder.encode(this.b, StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException e) {
                HwLog.e("GetJoinVipThread", "GetJoinVipThread, UnsupportedEncodingException" + HwLog.printException((Exception) e));
            }
        }
        return GsonHelper.toJson(linkedHashMap);
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a() {
        return CommonUtils.getVersionCode() + "";
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public VipOrderBean c(String str) {
        Exception e;
        VipOrderBean vipOrderBean = null;
        if (TextUtils.isEmpty(str)) {
            HwLog.i("GetJoinVipThread", "dealReceive json is empty");
            return null;
        }
        try {
            VipOrderBean vipOrderBean2 = (VipOrderBean) dx.a().a(str, VipOrderBean.class);
            if (vipOrderBean2 == null) {
                return vipOrderBean2;
            }
            try {
                HwLog.i("GetJoinVipThread", "dealReceive PayInfo resultinfo :" + vipOrderBean2.getResultinfo() + " code:" + vipOrderBean2.getResultcode());
                PayInfo payInfo = vipOrderBean2.getPayInfo();
                if (payInfo != null) {
                    payInfo.setReservedInfor(this.b);
                } else {
                    PayInfo payInfo2 = new PayInfo();
                    payInfo2.setAmount(this.c);
                    vipOrderBean2.setPayInfo(payInfo2);
                }
                return vipOrderBean2;
            } catch (Exception e2) {
                e = e2;
                vipOrderBean = vipOrderBean2;
                HwLog.e("GetJoinVipThread", "dealReceive " + HwLog.printException(e));
                return vipOrderBean;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }
}
