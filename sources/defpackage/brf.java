package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.tencent.open.SocialConstants;
import health.compact.a.GRSManager;

/* loaded from: classes7.dex */
public class brf implements IRequest {
    private static String d = "ActivityKakaParamsReq";

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("activityId")
    private String f478a;

    @SerializedName("awardRecordId")
    private String b;

    @SerializedName(ParsedFieldTag.KAKA_REDEEM_GIFT_ID)
    private String c;

    @SerializedName("activityCode")
    private String e;

    @SerializedName(SocialConstants.PARAM_RECEIVER)
    private String f;

    @SerializedName("awardType")
    private String g;

    @SerializedName("receiverPhone")
    private String h;

    @SerializedName("receiverRemark")
    private String i;

    @SerializedName("receiverAddr")
    private String j;

    @SerializedName("timeZone")
    private String m;
    private int n;

    private brf(c cVar) {
        this.f478a = cVar.d;
        this.b = cVar.e;
        this.e = cVar.c;
        this.c = cVar.f479a;
        this.g = cVar.b;
        this.f = cVar.i;
        this.h = cVar.g;
        this.j = cVar.f;
        this.i = cVar.h;
        this.m = cVar.n;
        this.n = cVar.j;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        String str;
        String str2;
        int i = this.n;
        if (i != 1) {
            str = "achievementUrl";
            if (i == 20001) {
                str2 = "/achievement/acceptAward";
            } else if (i != 20002) {
                LogUtil.h(d, "getUrl handleSaveAchievementInfo source error");
                str = "";
                str2 = "";
            } else {
                str2 = "/achievement/exchangeGift";
            }
        } else {
            str = "activityUrl";
            str2 = "/activity/acceptAward";
        }
        return GRSManager.a(BaseApplication.e()).getUrl(str) + str2;
    }

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        private String f479a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private String g;
        private String h;
        private String i;
        private int j;
        private String n;

        public c a(String str) {
            this.d = str;
            return this;
        }

        public c e(String str) {
            this.e = str;
            return this;
        }

        public c c(String str) {
            this.c = str;
            return this;
        }

        public c b(String str) {
            this.f479a = str;
            return this;
        }

        public c d(String str) {
            this.b = str;
            return this;
        }

        public c f(String str) {
            this.i = str;
            return this;
        }

        public c h(String str) {
            this.g = str;
            return this;
        }

        public c i(String str) {
            this.f = str;
            return this;
        }

        public c j(String str) {
            this.h = str;
            return this;
        }

        public c g(String str) {
            this.n = str;
            return this;
        }

        public c a(int i) {
            this.j = i;
            return this;
        }

        public brf c() {
            return new brf(this);
        }
    }
}
