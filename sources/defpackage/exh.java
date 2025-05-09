package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwlogsmodel.LogUtil;
import com.tencent.open.SocialOperation;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class exh extends CloudCommonReponse {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("FindUserV2Rsp")
    private e f12368a = new e();

    /* loaded from: classes8.dex */
    public static class d {

        @SerializedName("nationalCode")
        private String b;
    }

    public e b() {
        return this.f12368a;
    }

    public static class e {

        @SerializedName("findUserInfo")
        private List<b> b = new ArrayList();

        public List<b> d() {
            return this.b;
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName(CommonConstant.KEY_GENDER)
        private int f12369a;

        @SerializedName("needVerify")
        private int d;

        @SerializedName("ageGroupFlag")
        private int e;

        @SerializedName("siteID")
        private int g;

        @SerializedName("region")
        private d i;

        @SerializedName("userType")
        private int k;

        @SerializedName(HwPayConstant.KEY_USER_ID)
        private long l;

        @SerializedName("imageURL")
        private String b = "";

        @SerializedName("imageURLDownload")
        private String c = "";

        @SerializedName("nickName")
        private String h = "";

        @SerializedName(SocialOperation.GAME_SIGNATURE)
        private String j = "";

        @SerializedName("phoneDigest")
        private String f = "";

        public long a() {
            return this.l;
        }

        public String e() {
            return this.b;
        }

        public String d() {
            return this.h;
        }

        public String c() {
            JSONObject jSONObject = new JSONObject(new LinkedHashMap());
            try {
                jSONObject.put("ageGroupFlag", this.e);
                jSONObject.put(CommonConstant.KEY_GENDER, this.f12369a);
                jSONObject.put("imageURL", this.b);
                jSONObject.put("imageURLDownload", this.c);
                jSONObject.put("needVerify", this.d);
                jSONObject.put("nickName", this.h);
                jSONObject.put("phoneDigest", this.f);
                jSONObject.put("region", HiJsonUtil.e(this.i));
                jSONObject.put(SocialOperation.GAME_SIGNATURE, this.j);
                jSONObject.put("siteID", this.g);
                jSONObject.put(HwPayConstant.KEY_USER_ID, this.l);
                jSONObject.put("userType", this.k);
            } catch (JSONException e) {
                LogUtil.b("FindUserInfo", "Exception = ", e.getMessage());
            }
            return jSONObject.toString();
        }
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuilder sb = new StringBuilder("FindUserV2Response{FindUserV2Rsp=");
        if (b() != null) {
            sb.append(" findUserInfo=");
            if (b().d().size() != 0) {
                sb.append(", userID='");
                sb.append(b().d().get(0).a());
                sb.append(", imageURL='");
                sb.append(b().d().get(0).e());
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
