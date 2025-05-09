package defpackage;

import com.google.gson.annotations.SerializedName;
import com.hihonor.assistant.cardmgrsdk.CardMgrSdkConst;
import com.huawei.basefitnessadvice.model.Introduction;
import com.huawei.basefitnessadvice.model.PlanInfo;
import com.huawei.operation.h5pro.jsmodules.complaint.ComplaintConstants;
import com.tencent.open.SocialConstants;
import java.util.List;

/* loaded from: classes6.dex */
public class mmw extends PlanInfo {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("alias")
    private String f15066a;

    @SerializedName(SocialConstants.PARAM_APP_DESC)
    private String b;

    @SerializedName("imgUrl")
    private String c;

    @SerializedName("groupType")
    private int d;

    @SerializedName(ComplaintConstants.GROUP_NAME_PARAM_KEY)
    private String e;

    @SerializedName("introduction")
    private Introduction f;

    @SerializedName("name")
    private String g;

    @SerializedName(CardMgrSdkConst.KEY_RECOMMEND)
    private boolean h;
    private List<String> i;
    private int j;

    @SerializedName("type")
    private int l = 0;

    @SerializedName("planType")
    private int m;

    public Introduction h() {
        return this.f;
    }

    public int c() {
        return this.m;
    }

    public void d(int i) {
        this.m = i;
    }

    public int a() {
        return this.j;
    }

    public void c(int i) {
        this.j = i;
    }

    public String e() {
        return this.b;
    }

    public void e(String str) {
        this.b = str;
    }

    public String i() {
        return this.g;
    }

    public void d(String str) {
        this.g = str;
    }

    public int b() {
        return this.d;
    }

    public String d() {
        return this.c;
    }

    @Override // com.huawei.basefitnessadvice.model.PlanInfo
    public int getType() {
        return this.l;
    }

    @Override // com.huawei.basefitnessadvice.model.PlanInfo
    public List<String> getLabels() {
        return this.i;
    }

    @Override // com.huawei.basefitnessadvice.model.PlanInfo
    public void setLabels(List<String> list) {
        this.i = list;
    }

    public String toString() {
        return "RunPlanInfo{mName='" + this.g + "', mAbbPlanName='" + this.f15066a + "', mDescription='" + this.b + "', mImageView=" + this.j + ", mPlanType=" + this.m + ", mType=" + this.l + ", mGroupType=" + this.d + ", mGroupName='" + this.e + "', mImageUrl='" + this.c + "', mIsRecommended=" + this.h + ", mLabels=" + this.i + ", mPriceTagBeanList=" + this.mPriceTagBeanList + ", mCommodityFlag=" + this.mCommodityFlag + ", mCornerImgDisplay=" + this.mCornerImgDisplay + ", mTransactionStatus=" + this.mTransactionStatus + '}';
    }
}
