package com.huawei.hms.iapfull;

import android.app.Activity;
import android.content.Intent;
import com.huawei.hms.iapfull.webpay.thirdpay.ThirdPayActivity;
import com.huawei.secure.android.common.intent.IntentUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;

/* loaded from: classes9.dex */
public class IapFullSdkAPI {
    private static final String TAG = "IapFullSdkAPI";
    private static final String WX_SIGN_MESSAGE_EXT = "from=weixin_papay";

    private static boolean isWXSignBack(BaseReq baseReq) {
        ShowMessageFromWX.Req req;
        WXMediaMessage wXMediaMessage;
        return (baseReq instanceof ShowMessageFromWX.Req) && 4 == baseReq.getType() && (wXMediaMessage = (req = (ShowMessageFromWX.Req) baseReq).message) != null && WX_SIGN_MESSAGE_EXT.equals(wXMediaMessage.messageExt) && (req.message.mediaObject instanceof WXAppExtendObject);
    }

    public static boolean handleWXSignResult(Activity activity, BaseReq baseReq) {
        if (activity == null || baseReq == null) {
            y0.a(TAG, "handleWXSignResult params error");
            return false;
        }
        if (isWXSignBack(baseReq)) {
            Intent intent = new Intent(activity, (Class<?>) ThirdPayActivity.class);
            intent.putExtra("wechat_sign_result", true);
            y0.b(TAG, "handleWXSignResult result " + IntentUtils.safeStartActivity(activity, intent));
            activity.finish();
            return true;
        }
        y0.b(TAG, "handleWXSignResult back");
        return false;
    }
}
