package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes9.dex */
public class ppn {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("backGroundImg")
    private String f16218a;

    @SerializedName("audioType")
    private int b;

    @SerializedName("detailJumpUrl")
    private String c;

    @SerializedName("audioUrl")
    private String d;

    @SerializedName("heatCount")
    private int e;

    @SerializedName("infoType")
    private String f;

    @SerializedName(ParsedFieldTag.TASK_MODIFY_TIME)
    private long g;

    @SerializedName("id")
    private int h;

    @SerializedName("imgIcon")
    private String i;

    @SerializedName("labelType")
    private int j;

    @SerializedName("name")
    private String k;

    @SerializedName("status")
    private int l;

    @SerializedName(Constants.SHARE_TITLE)
    private String m;

    @SerializedName("shareIcon")
    private String n;

    @SerializedName("shareDescription")
    private String o;

    @SerializedName("isVip")
    private int p;

    @SerializedName("weight")
    private int q;

    public String d() {
        return this.c;
    }

    public int b() {
        return this.e;
    }

    public String c() {
        return this.i;
    }

    public int i() {
        return this.j;
    }

    public String f() {
        return this.k;
    }

    public int g() {
        return this.p;
    }

    public String a() {
        return this.f;
    }

    public int e() {
        return this.h;
    }

    public String toString() {
        return "SleepAudiosModel{mAudioType=" + this.b + ", mAudioUrl='" + this.d + "', mBackGroundImg='" + this.f16218a + "', mHeatCount=" + this.e + ", mId=" + this.h + ", mImgIcon='" + this.i + "', mLabelType=" + this.j + ", mModifyTime=" + this.g + ", mName='" + this.k + "', mShareDescription='" + this.o + "', mShareIcon='" + this.n + "', mShareTitle='" + this.m + "', mStatus=" + this.l + ", mWeight=" + this.q + ", mDetailJumpUrl='" + this.c + "', mInfoType='" + this.f + "'}";
    }
}
