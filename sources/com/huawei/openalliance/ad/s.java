package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.inter.AdContentResponseParser;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.u;
import com.huawei.openalliance.ad.v;
import com.huawei.openalliance.ad.x;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class s extends j {
    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        String str2;
        if (ho.a()) {
            ho.a("JsbParseApiAd", "param content: %s", str);
        }
        JSONObject jSONObject = new JSONObject(str);
        int optInt = jSONObject.optInt(JsbMapKeyNames.JSSDK_VER, 2);
        Integer[] b = com.huawei.openalliance.ad.utils.cz.b(jSONObject.optJSONArray(JsbMapKeyNames.H5_AD_TYPES));
        String optString = jSONObject.optString(JsbMapKeyNames.H5_API_RSP);
        if (com.huawei.openalliance.ad.utils.bg.a(b)) {
            str2 = "adType is empty";
        } else {
            if (!TextUtils.isEmpty(optString)) {
                ho.b("JsbParseApiAd", "parse api response.");
                if (ho.a()) {
                    ho.a("JsbParseApiAd", "rsp: %s", optString);
                }
                AdContentResponseParser a2 = a(context, remoteCallResultCallback, b, optString, optInt);
                if (a(b, 13)) {
                    a2.processAdResponse(optString, true);
                    return;
                } else {
                    a2.processAdResponse(optString);
                    return;
                }
            }
            str2 = "rsp is empty";
        }
        ho.c("JsbParseApiAd", str2);
        a(remoteCallResultCallback, true);
    }

    private boolean a(Integer[] numArr, int i) {
        if (com.huawei.openalliance.ad.utils.bg.a(numArr)) {
            return false;
        }
        for (Integer num : numArr) {
            if (num.intValue() == i) {
                return true;
            }
        }
        return false;
    }

    private AdContentResponseParser a(Context context, RemoteCallResultCallback<String> remoteCallResultCallback, Integer[] numArr, String str, int i) {
        AdContentResponseParser.Builder builder = new AdContentResponseParser.Builder(context);
        builder.enableDirectReturnVideoAd(true);
        if (a(numArr, 3)) {
            builder.setNativeAdListener(new v.a(context, remoteCallResultCallback, this.f7108a, 3, i));
        } else if (a(numArr, 9)) {
            builder.setIconAdListener(new v.a(context, remoteCallResultCallback, this.f7108a, 9, i));
        } else if (a(numArr, 13)) {
            builder.setSearchAdListener(new v.a(context, remoteCallResultCallback, this.f7108a, 13, i));
        } else if (a(numArr, 7)) {
            builder.setRewardAdListener(new x.a(context, remoteCallResultCallback, this.f7108a));
        } else if (a(numArr, 12)) {
            builder.a(new u.a(context, remoteCallResultCallback, this.f7108a));
        }
        return builder.build();
    }

    public s() {
        super("pps.api.parse.ad");
    }
}
