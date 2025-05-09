package com.huawei.openalliance.ad.inter.data;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.AdSource;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.PromoteInfo;
import com.huawei.openalliance.ad.constant.WhiteListPkgList;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.data.RewardVerifyConfig;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.cz;
import java.util.List;

/* loaded from: classes5.dex */
public class i implements c {

    /* renamed from: a, reason: collision with root package name */
    private ContentRecord f7056a;
    private MetaData b;
    private AppInfo c;

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public BiddingInfo getBiddingInfo() {
        return null;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public void showAppDetailPage(Context context) {
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public void setRewardVerifyConfig(RewardVerifyConfig rewardVerifyConfig) {
        ContentRecord contentRecord;
        if (rewardVerifyConfig == null || (contentRecord = this.f7056a) == null) {
            return;
        }
        contentRecord.G(cz.d(rewardVerifyConfig.getData()));
        this.f7056a.H(cz.d(rewardVerifyConfig.getUserId()));
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public boolean isTransparencyOpen() {
        ContentRecord contentRecord = this.f7056a;
        return (contentRecord == null || !contentRecord.bc() || TextUtils.isEmpty(this.f7056a.bb())) ? false : true;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public boolean isShowAppElement() {
        Integer N;
        MetaData metaData = this.b;
        return (metaData == null || (N = metaData.N()) == null || N.intValue() != 1) ? false : true;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public boolean isExpired() {
        ContentRecord contentRecord = this.f7056a;
        return contentRecord == null || contentRecord.q() < System.currentTimeMillis();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public boolean isAdIdInWhiteList() {
        Context d;
        if (os.k(getCtrlSwitchs())) {
            return true;
        }
        com.huawei.openalliance.ad.inter.f a2 = HiAd.a();
        if (a2 == null || (d = a2.d()) == null) {
            return false;
        }
        String packageName = d.getPackageName();
        return WhiteListPkgList.inWhiteList(packageName, com.huawei.openalliance.ad.utils.i.e(d, packageName));
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public void gotoWhyThisAdPage(Context context) {
        if (this.f7056a == null) {
            return;
        }
        a(context);
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getWhyThisAd() {
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord == null) {
            return null;
        }
        return contentRecord.Z();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getUniqueId() {
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord == null) {
            return null;
        }
        return contentRecord.aa();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getTransparencyTplUrl() {
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord == null) {
            return null;
        }
        return contentRecord.bb();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getTaskId() {
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord == null) {
            return null;
        }
        return contentRecord.n();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public long getStartTime() {
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord == null) {
            return 0L;
        }
        return contentRecord.r();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getShowId() {
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord == null) {
            return null;
        }
        return contentRecord.j();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public RewardVerifyConfig getRewardVerifyConfig() {
        if (this.f7056a == null) {
            return null;
        }
        RewardVerifyConfig.Builder builder = new RewardVerifyConfig.Builder();
        builder.setData(this.f7056a.aA());
        builder.setUserId(this.f7056a.aB());
        return builder.build();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public PromoteInfo getPromoteInfo() {
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord == null) {
            return null;
        }
        return contentRecord.af();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public long getMinEffectiveShowTime() {
        MetaData metaData = this.b;
        if (metaData != null) {
            return metaData.i();
        }
        return 500L;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public int getMinEffectiveShowRatio() {
        MetaData metaData = this.b;
        if (metaData != null) {
            return metaData.j();
        }
        return 50;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getLabel() {
        MetaData metaData = this.b;
        if (metaData != null) {
            return cz.c(metaData.k());
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public int getInterfaceDownloadConfig() {
        return os.E(getCtrlSwitchs());
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getHwChannelId() {
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord == null) {
            return null;
        }
        return contentRecord.aR();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public long getEndTime() {
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord == null) {
            return 0L;
        }
        return contentRecord.q();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getDspName() {
        AdSource a2;
        MetaData metaData = this.b;
        if (metaData == null || (a2 = AdSource.a(metaData.K())) == null) {
            return null;
        }
        return cz.c(a2.a());
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getDspLogo() {
        AdSource a2;
        MetaData metaData = this.b;
        if (metaData == null || (a2 = AdSource.a(metaData.K())) == null) {
            return null;
        }
        return a2.b();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getCtrlSwitchs() {
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord == null) {
            return null;
        }
        return contentRecord.V();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getCta() {
        MetaData metaData = this.b;
        if (metaData != null) {
            return cz.c(metaData.a());
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public int getCreativeType() {
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord == null) {
            return 0;
        }
        return contentRecord.D();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getContentId() {
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord == null) {
            return null;
        }
        return contentRecord.m();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public List<AdvertiserInfo> getCompliance() {
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord == null) {
            return null;
        }
        return contentRecord.aS();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public AppInfo getAppInfo() {
        AppInfo appInfo = this.c;
        if (appInfo != null) {
            return appInfo;
        }
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord == null) {
            return null;
        }
        AppInfo ae = contentRecord.ae();
        this.c = ae;
        return ae;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getAdSign() {
        MetaData metaData = this.b;
        if (metaData != null) {
            return metaData.t();
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getAdChoiceUrl() {
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord == null) {
            return null;
        }
        return contentRecord.ak();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getAdChoiceIcon() {
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord == null) {
            return null;
        }
        return contentRecord.al();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getAbilityDetailInfo() {
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord == null) {
            return null;
        }
        return contentRecord.aQ();
    }

    public String b() {
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord != null) {
            return contentRecord.ba();
        }
        return null;
    }

    public void a(String str) {
        ContentRecord contentRecord = this.f7056a;
        if (contentRecord != null) {
            contentRecord.R(str);
        }
    }

    public void a(Context context) {
        if (context == null) {
            ho.c("UnifyAd", "context is null not call gotoWhyThisAdPage method");
        } else {
            ao.a(context, this);
        }
    }

    public ContentRecord a() {
        return this.f7056a;
    }

    public i(ContentRecord contentRecord) {
        this.f7056a = contentRecord;
        if (contentRecord != null) {
            this.b = contentRecord.h();
        }
    }
}
