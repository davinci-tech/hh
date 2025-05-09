package com.huawei.health.vip.datatypes;

import com.google.gson.annotations.SerializedName;
import com.huawei.trade.datatype.Product;
import java.util.List;

/* loaded from: classes4.dex */
public class MemberTypeInfo {

    @SerializedName("darkMarketingArticleId")
    private String darkMarketingArticleId;

    @SerializedName("largeBenefitIntroductions")
    private List<BenefitIntroduction> largeBenefitIntroductions;

    @SerializedName("marketingArticleId")
    private String marketingArticleId;

    @SerializedName("memberType")
    private int memberType;

    @SerializedName("normalBenefitIntroductions")
    private List<BenefitIntroduction> normalBenefitIntroductions;

    @SerializedName("privilegeIntroductions")
    private List<PrivilegeIntroduction> privilegeIntroductions;

    @SerializedName("products")
    private List<Product> products;

    public String getDarkMarketingArticleId() {
        return this.darkMarketingArticleId;
    }

    public void setDarkMarketingArticleId(String str) {
        this.darkMarketingArticleId = str;
    }

    public void setMemberType(int i) {
        this.memberType = i;
    }

    public int getMemberType() {
        return this.memberType;
    }

    public void setMarketingArticleId(String str) {
        this.marketingArticleId = str;
    }

    public String getMarketingArticleId() {
        return this.marketingArticleId;
    }

    public void setNormalBenefitIntroductions(List<BenefitIntroduction> list) {
        this.normalBenefitIntroductions = list;
    }

    public List<BenefitIntroduction> getNormalBenefitIntroductions() {
        return this.normalBenefitIntroductions;
    }

    public void setLargeBenefitIntroductions(List<BenefitIntroduction> list) {
        this.largeBenefitIntroductions = list;
    }

    public List<BenefitIntroduction> getLargeBenefitIntroductions() {
        return this.largeBenefitIntroductions;
    }

    public void setPrivilegeIntroductions(List<PrivilegeIntroduction> list) {
        this.privilegeIntroductions = list;
    }

    public List<PrivilegeIntroduction> getPrivilegeIntroductions() {
        return this.privilegeIntroductions;
    }

    public void setProducts(List<Product> list) {
        this.products = list;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public String toString() {
        return "MemberTypeInfo{memberType=" + this.memberType + ", marketingArticleId='" + this.marketingArticleId + "', darkMarketingArticleId='" + this.darkMarketingArticleId + "', normalBenefitIntroductions=" + this.normalBenefitIntroductions + ", largeBenefitIntroductions=" + this.largeBenefitIntroductions + ", privilegeIntroductions=" + this.privilegeIntroductions + ", products=" + this.products + '}';
    }
}
