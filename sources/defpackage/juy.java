package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import com.tencent.open.SocialOperation;
import health.compact.a.LogAnonymous;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class juy {
    public static juz d(String str) {
        juz juzVar = null;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ParseSonyJson", "parseJsonToEphemerisUrlBean jsonString is empty");
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() == 0) {
                LogUtil.h("ParseSonyJson", "parseJsonToEphemerisUrlBean jsonArray length is zero");
                return null;
            }
            JSONObject jSONObject = jSONArray.getJSONObject(0);
            juz juzVar2 = new juz();
            try {
                if (jSONObject.has(RecommendConstants.DOWNLOAD_URL)) {
                    juzVar2.d(jSONObject.getString(RecommendConstants.DOWNLOAD_URL));
                } else {
                    juzVar2.d(null);
                }
                if (jSONObject.has(RecommendConstants.VER)) {
                    juzVar2.a(jSONObject.getString(RecommendConstants.VER));
                } else {
                    juzVar2.a(null);
                }
                if (jSONObject.has(SocialOperation.GAME_SIGNATURE)) {
                    juzVar2.c(jSONObject.getString(SocialOperation.GAME_SIGNATURE));
                } else {
                    juzVar2.c(null);
                }
                if (jSONObject.has(RecommendConstants.FILE_ID)) {
                    juzVar2.e(jSONObject.getString(RecommendConstants.FILE_ID));
                    return juzVar2;
                }
                juzVar2.e(null);
                return juzVar2;
            } catch (JSONException e) {
                e = e;
                juzVar = juzVar2;
                LogUtil.b("ParseSonyJson", "EphemerisUrlSonyEntity exception : ", LogAnonymous.b((Throwable) e));
                return juzVar;
            }
        } catch (JSONException e2) {
            e = e2;
        }
    }
}
