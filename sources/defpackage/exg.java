package defpackage;

import com.amap.api.services.district.DistrictSearchQuery;
import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.up.request.HttpRequestBase;
import com.tencent.open.SocialConstants;
import com.tencent.open.SocialOperation;
import java.util.List;

/* loaded from: classes3.dex */
public class exg {

    @SerializedName("resultCode")
    private int b;

    @SerializedName("CException")
    private d d;

    @SerializedName("GetOtherUserInfoRsp")
    private e e;

    /* loaded from: classes8.dex */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName(SocialConstants.PARAM_APP_DESC)
        private String f12365a;

        @SerializedName("type")
        private int b;

        @SerializedName("siteID")
        private int c;
    }

    /* loaded from: classes8.dex */
    public static class c {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName(DistrictSearchQuery.KEYWORDS_PROVINCE)
        private String f12367a;

        @SerializedName("nationalCode")
        private String b;

        @SerializedName(DistrictSearchQuery.KEYWORDS_CITY)
        private String c;
    }

    public void a(int i) {
        this.b = i;
    }

    public int c() {
        return this.b;
    }

    public e b() {
        return this.e;
    }

    public static class e {

        @SerializedName("version")
        private String b;

        @SerializedName("reault")
        private int c;

        @SerializedName("otherUserInfo")
        private b d;

        @SerializedName("otherUserInfoList")
        private List<b> e;

        public b e() {
            return this.d;
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("ageGroupFlag")
        private int f12366a;

        @SerializedName("ext")
        private String b;

        @SerializedName("frdOrigin")
        private a c;

        @SerializedName(CommonConstant.KEY_GENDER)
        private int d;

        @SerializedName("frdState")
        private int e;

        @SerializedName("imageURLDownload")
        private String f;

        @SerializedName("loginID")
        private String g;

        @SerializedName("imageURL")
        private String h;

        @SerializedName("needVerify")
        private int i;

        @SerializedName("nickName")
        private String j;

        @SerializedName("rCY")
        private String k;

        @SerializedName("region")
        private c l;

        @SerializedName("phoneDigest")
        private String m;

        @SerializedName(SocialOperation.GAME_SIGNATURE)
        private String n;

        @SerializedName("privacySetFlags")
        private String o;

        @SerializedName(HwPayConstant.KEY_USER_ID)
        private String p;

        @SerializedName("userType")
        private int r;

        @SerializedName("userAccount")
        private String s;

        @SerializedName("siteID")
        private int t;

        public String a() {
            return this.s;
        }

        public String c() {
            return this.h;
        }

        public String b() {
            return this.j;
        }
    }

    /* loaded from: classes8.dex */
    static class d {

        @SerializedName("errorCode")
        private int b;

        @SerializedName(HttpRequestBase.TAG_ERROR_DESC)
        private String d;

        d() {
        }
    }
}
