package defpackage;

import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.inter.INativeAdLoader;
import com.huawei.openalliance.ad.inter.NativeAdLoader;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.listeners.NativeAdListener;
import com.huawei.ui.commonui.utils.ScrollUtil;
import defpackage.rxh;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class sdi {
    private static INativeAdLoader d;

    public static void d(String[] strArr, NativeAdListener nativeAdListener) {
        NativeAdLoader nativeAdLoader = new NativeAdLoader(BaseApplication.getContext(), strArr);
        d = nativeAdLoader;
        nativeAdLoader.setListener(nativeAdListener);
        d.setRequestOptions(new RequestOptions.Builder().setRequestLocation(false).build());
        d.loadAds(4, false);
    }

    public static INativeAd d(Map<String, List<INativeAd>> map) {
        if (map == null) {
            LogUtil.h("UIDV_NativeAdUtil", "ads = null");
            return null;
        }
        for (List<INativeAd> list : map.values()) {
            if (koq.b(list)) {
                LogUtil.h("UIDV_NativeAdUtil", "nativeAd isEmpty");
            } else {
                Collections.sort(list, new rxh.d());
                for (INativeAd iNativeAd : list) {
                    if (iNativeAd == null || !iNativeAd.isValid(BaseApplication.getContext()) || iNativeAd.isExpired()) {
                        LogUtil.h("UIDV_NativeAdUtil", "nativeAd isExpired or not valid");
                    } else if (koq.b(iNativeAd.getImageInfos())) {
                        LogUtil.h("UIDV_NativeAdUtil", "nativeAd.getImageInfos() isEmpty");
                    } else {
                        LogUtil.a("UIDV_NativeAdUtil", "getNativeAd success");
                        return iNativeAd;
                    }
                }
            }
        }
        return null;
    }

    public static List<MessageObject> a(List<MessageObject> list, INativeAd iNativeAd, String str) {
        if (list == null) {
            list = new ArrayList<>(16);
        }
        if (iNativeAd != null && koq.c(iNativeAd.getImageInfos())) {
            if (iNativeAd.getImageInfos().get(0) == null) {
                LogUtil.h("UIDV_NativeAdUtil", "get(FIRST_NUMBER) = null");
                return list;
            }
            pgq pgqVar = new pgq();
            float floatValue = new BigDecimal(iNativeAd.getImageInfos().get(0).getWidth() / iNativeAd.getImageInfos().get(0).getHeight()).setScale(1, RoundingMode.HALF_UP).floatValue();
            LogUtil.a("UIDV_NativeAdUtil", "result:", Float.valueOf(floatValue), "width:", Integer.valueOf(iNativeAd.getImageInfos().get(0).getWidth()), "heigh:", Integer.valueOf(iNativeAd.getImageInfos().get(0).getHeight()));
            String url = iNativeAd.getImageInfos().get(0).getUrl();
            if (ScrollUtil.c(floatValue, 0.8f)) {
                pgqVar.setHarmonyImageSize("4:5");
                pgqVar.setHarmonyImgUrl(url);
            } else if (ScrollUtil.c(floatValue, 1.8f)) {
                pgqVar.setHarmonyImageSize("16:9");
                pgqVar.setHarmonyImgUrl(url);
            } else if (ScrollUtil.c(floatValue, 2.3f)) {
                LogUtil.a("UIDV_NativeAdUtil", "imageSize is default");
            } else {
                LogUtil.h("UIDV_NativeAdUtil", "mNativeAd imageSize not match");
                return list;
            }
            pgqVar.setMsgTitle(iNativeAd.getTitle());
            pgqVar.setCreateTime(iNativeAd.getStartTime());
            pgqVar.setExpireTime(iNativeAd.getEndTime());
            pgqVar.setImgUri(iNativeAd.getImageInfos().get(0).getUrl());
            pgqVar.setDetailUri(iNativeAd.getImageInfos().get(0).getUrl());
            pgqVar.setNativeAd(iNativeAd);
            pgqVar.setWeight(1);
            pgqVar.setMsgId(str);
            list.add(0, pgqVar);
        } else {
            LogUtil.h("UIDV_NativeAdUtil", "mNativeAd is emyty");
        }
        LogUtil.a("UIDV_NativeAdUtil", "addNativeAd messageList.size:", Integer.valueOf(list.size()));
        return list;
    }
}
