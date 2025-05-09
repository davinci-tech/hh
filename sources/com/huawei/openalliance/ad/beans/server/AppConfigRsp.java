package com.huawei.openalliance.ad.beans.server;

import com.huawei.openalliance.ad.annotations.a;
import com.huawei.openalliance.ad.annotations.d;
import com.huawei.openalliance.ad.beans.base.RspBean;
import com.huawei.openalliance.ad.beans.metadata.ReduceDisturbRule;
import com.huawei.openalliance.ad.beans.metadata.Rule;
import com.huawei.openalliance.ad.beans.metadata.v3.SlotTemplateRsp;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.SpKeys;
import com.huawei.openalliance.ad.utils.be;
import java.util.List;

/* loaded from: classes5.dex */
public class AppConfigRsp extends RspBean {
    private Integer allowAdSkipTime;

    @a
    private String apiWhiteList;
    private String appList;
    private Integer bdinterval;
    private String configMap;
    private Integer configRefreshInterval;
    private List<String> defBrowerPkgList;
    private Long diskCacheValidTime;
    private Integer fcSwitch;
    private String globalSwitch;
    private Integer imeiEncodeMode;
    private Integer iteAdCA;
    private Integer iteAdCloseTm;
    private Integer iteAdExp;
    private Integer iteAdFs;
    private List<SlotTemplateRsp> jsSlotTemplateRsp;
    private Integer landingMenu;
    private Integer landpageAppPrompt;

    @a
    private String landpageAppWhiteList;

    @a
    private String landpageWebBlackList;
    private double limitOfContainerAspectRatio;
    private Long maxBannerInterval;
    private Long minBannerInterval;
    private Integer notifyKitWhenRequest;
    private Integer oaidReportOnNpa;
    private String reason;
    private ReduceDisturbRule reduceDisturbRule;
    private Integer rwdCloseShowTm;
    private Integer rwdGnTm;
    private List<String> schemeInfo;
    private Integer sdkCacheDelStrategy;
    private Integer sdkCacheTotalMaxNum;
    private Integer sdkCacheTotalMaxSize;

    @a
    private String serverStore;
    private String sha256;
    private List<String> singleInstanceLSModelList;
    private Integer sloganShowTime;
    private List<SlotTemplateRsp> slotTemplateRsp;
    private String splashFeedbackBtnText;
    private Integer splashInteractCloseEffectiveTime;
    private Integer splashmode;
    private Integer supSdkServerGzip;
    private Integer supportGzip;
    private String testCountryCode;
    private String trustAppList;
    private Integer validityOfClickSkip;
    private Integer validityOfLockEvent;
    private Integer validityOfNativeEvent;
    private Integer validityOfSplashEvent;
    private Integer videoCacheSize;
    private int retcode = -1;

    @d(a = "splashcachenum")
    private int splashCacheNum = 10;
    private int splashshow = 3000;
    private int splashSkipArea = 0;

    @d(a = "gif_show_time_upper_limit")
    private int gifShowTimeUpperLimit = 8000;

    @d(a = "gif_show_time_lower_limit_each_frame")
    private int gifShowTimeLowerLimitEachFrame = 100;

    @d(a = SpKeys.GIF_SIZE_UPPER_LIMIT)
    private int gifSizeUpperLimit = Constants.GIF_SIZE_UPPER_LIMIT;

    @d(a = SpKeys.IMG_SIZE_UPPER_LIMIT)
    private int imgSizeUpperLimit = 52428800;
    private long splashShowTimeInterval = 0;
    private long sloganShowMinTimeRealMode = 300;
    private int splashUserAppDayImpFc = 0;
    private String supportHmsSdkVerCode = Constants.NEW_KIT_HMS_VERSION;
    private long preloadSplashReqTimeInterval = 600000;
    private Long locationExpireTime = 1800000L;
    private Long locationRefreshInterval = 1800000L;
    private int locationSwitch = 0;

    public Integer z() {
        return this.notifyKitWhenRequest;
    }

    public String y() {
        return this.landpageWebBlackList;
    }

    public long x() {
        return this.preloadSplashReqTimeInterval;
    }

    public String w() {
        return this.supportHmsSdkVerCode;
    }

    public List<String> v() {
        return this.defBrowerPkgList;
    }

    public String u() {
        return this.globalSwitch;
    }

    public Integer t() {
        return this.validityOfNativeEvent;
    }

    public Integer s() {
        return this.validityOfLockEvent;
    }

    public Integer r() {
        return this.validityOfClickSkip;
    }

    public Integer q() {
        return this.validityOfSplashEvent;
    }

    public Integer p() {
        return this.configRefreshInterval;
    }

    public Integer o() {
        return this.landpageAppPrompt;
    }

    public String n() {
        return this.landpageAppWhiteList;
    }

    public Integer m() {
        return this.landingMenu;
    }

    public String l() {
        return this.serverStore;
    }

    public int k() {
        return this.retcode;
    }

    public int j() {
        int i = this.splashSkipArea;
        if (i < 0 || i > 200) {
            return 0;
        }
        return i;
    }

    public Integer i() {
        Integer num = this.splashmode;
        if (num == null) {
            return null;
        }
        return Integer.valueOf((1 == num.intValue() || 2 == this.splashmode.intValue() || 3 == this.splashmode.intValue()) ? this.splashmode.intValue() : 1);
    }

    public int h() {
        int i = this.splashshow;
        if (i >= 2000) {
            return i;
        }
        return 3000;
    }

    public Integer g() {
        if (this.sloganShowTime == null) {
            return null;
        }
        Integer i = i();
        return Integer.valueOf((i == null || 1 == i.intValue()) ? ah() : (2 == i.intValue() || 3 == i.intValue()) ? ag() : 0);
    }

    public long f() {
        long j = this.sloganShowMinTimeRealMode;
        if (j < 0 || j > 5000) {
            return 300L;
        }
        return j;
    }

    public long e() {
        long j = this.splashShowTimeInterval;
        if (j > 0) {
            return j;
        }
        return 0L;
    }

    public int d(int i) {
        int i2 = this.imgSizeUpperLimit;
        return i2 > 0 ? i2 : i;
    }

    public int d() {
        int i = this.splashUserAppDayImpFc;
        if (i > 0) {
            return i;
        }
        return 0;
    }

    public Long c() {
        return this.maxBannerInterval;
    }

    public int c(int i) {
        int i2 = this.gifSizeUpperLimit;
        return i2 > 0 ? i2 : i;
    }

    public Long b() {
        return this.minBannerInterval;
    }

    public int b(int i) {
        int i2 = this.gifShowTimeLowerLimitEachFrame;
        return i2 > 0 ? i2 : i;
    }

    public List<SlotTemplateRsp> af() {
        return this.jsSlotTemplateRsp;
    }

    public Integer ae() {
        return this.fcSwitch;
    }

    public List<SlotTemplateRsp> ad() {
        return this.slotTemplateRsp;
    }

    public List<String> ac() {
        return this.singleInstanceLSModelList;
    }

    public Integer ab() {
        return this.videoCacheSize;
    }

    public Integer aa() {
        return this.supSdkServerGzip;
    }

    public String a(String str) {
        ReduceDisturbRule reduceDisturbRule = this.reduceDisturbRule;
        if (reduceDisturbRule != null) {
            return a(reduceDisturbRule.a(), str);
        }
        return null;
    }

    public String a() {
        return this.trustAppList;
    }

    public int a(int i) {
        int i2 = this.gifShowTimeUpperLimit;
        return i2 >= 2000 ? i2 : i;
    }

    public Integer Z() {
        return this.allowAdSkipTime;
    }

    public String Y() {
        return this.sha256;
    }

    public Integer X() {
        return this.oaidReportOnNpa;
    }

    public Integer W() {
        return this.rwdCloseShowTm;
    }

    public Integer V() {
        return this.iteAdCA;
    }

    public Integer U() {
        return this.iteAdExp;
    }

    public Integer T() {
        return this.iteAdFs;
    }

    public Integer S() {
        return this.iteAdCloseTm;
    }

    public Integer R() {
        return this.rwdGnTm;
    }

    public Integer Q() {
        return this.supportGzip;
    }

    public String P() {
        return this.testCountryCode;
    }

    public Integer O() {
        return this.bdinterval;
    }

    public String N() {
        return this.appList;
    }

    public String M() {
        return this.configMap;
    }

    public Integer L() {
        return this.sdkCacheTotalMaxSize;
    }

    public Integer K() {
        return this.sdkCacheTotalMaxNum;
    }

    public Integer J() {
        return this.sdkCacheDelStrategy;
    }

    public Long I() {
        return this.diskCacheValidTime;
    }

    public double H() {
        double d = this.limitOfContainerAspectRatio;
        if (d <= 0.0d) {
            return 0.05000000074505806d;
        }
        return d;
    }

    public String G() {
        return this.splashFeedbackBtnText;
    }

    public Integer F() {
        return this.splashInteractCloseEffectiveTime;
    }

    public Long E() {
        return Long.valueOf(this.locationRefreshInterval.longValue() > 0 ? this.locationRefreshInterval.longValue() : 1800000L);
    }

    public String D() {
        return this.apiWhiteList;
    }

    public int C() {
        return this.locationSwitch;
    }

    public Long B() {
        return Long.valueOf(this.locationExpireTime.longValue() > 0 ? this.locationExpireTime.longValue() : 1800000L);
    }

    public List<String> A() {
        return this.schemeInfo;
    }

    private int ah() {
        Integer num = this.sloganShowTime;
        if (num == null) {
            return 2000;
        }
        if (num.intValue() < 0 || this.sloganShowTime.intValue() > 5000) {
            return 0;
        }
        return this.sloganShowTime.intValue();
    }

    private int ag() {
        Integer num = this.sloganShowTime;
        if (num != null && num.intValue() >= 500 && this.sloganShowTime.intValue() <= 5000) {
            return this.sloganShowTime.intValue();
        }
        return 2000;
    }

    private String a(List<Rule> list, String str) {
        return (list == null || list.size() > 30) ? str : be.b(this.reduceDisturbRule);
    }
}
