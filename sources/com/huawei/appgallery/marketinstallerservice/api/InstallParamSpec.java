package com.huawei.appgallery.marketinstallerservice.api;

/* loaded from: classes3.dex */
public class InstallParamSpec extends BaseParamSpec {
    public static final int FAIL_RESULT_DIALOG = 2;
    public static final int FAIL_RESULT_NOTHING = 1;
    public static final int FAIL_RESULT_TOAST = 0;
    private MarketInfo e;
    private boolean d = false;
    private int b = 0;

    public void setSilentDownload(boolean z) {
        this.d = z;
    }

    public void setMarketInfo(MarketInfo marketInfo) {
        this.e = marketInfo;
    }

    public void setFailResultPromptType(int i) {
        this.b = i;
    }

    public boolean isSilentDownload() {
        return this.d;
    }

    public MarketInfo getMarketInfo() {
        return this.e;
    }

    public int getFailResultPromptType() {
        return this.b;
    }
}
