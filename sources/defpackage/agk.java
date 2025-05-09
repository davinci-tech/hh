package defpackage;

import com.huawei.appgallery.marketinstallerservice.api.MarketInfo;
import com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.InstallerNetTransmission;
import com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.ResponseBean;

/* loaded from: classes3.dex */
public class agk extends ResponseBean {

    @InstallerNetTransmission
    private MarketInfo hiAppInfo = null;

    public void setHiAppInfo(MarketInfo marketInfo) {
        this.hiAppInfo = marketInfo;
    }

    public MarketInfo getHiAppInfo() {
        return this.hiAppInfo;
    }
}
