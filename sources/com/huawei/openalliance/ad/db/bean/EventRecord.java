package com.huawei.openalliance.ad.db.bean;

import com.huawei.openalliance.ad.annotations.e;
import java.util.List;

/* loaded from: classes5.dex */
public class EventRecord extends a {
    public static final String AD_TYPE = "adType";
    public static final String LAST_FAIL_REASON = "lastFailReason";
    public static final String LAST_REPORT_TIME = "lastReportTime";
    public static final String REPEATED_COUNT = "repeatedCount";
    public static final String TIME = "time";

    @e
    private String _id;
    private int adType_;
    private String agVerifyCode;
    private String appDownloadRelatedActionSource;
    private String appVersionCode;
    private String clickSuccessDestination_;
    private String contentDownMethod;
    private String contentId;
    private String creativeSize;
    private String customData;
    private String downloadDuration;
    private String downloadMode;
    private String downloadReason;
    private String downloadSize;
    private int exposure;
    private EncryptionField<String> ext;
    private String fullDownload;
    private String impSource;
    private String installRelatedActionSource;
    private String installType;
    private String intentDest;
    private String intentFailReason;
    private String isAdContainerSizeMatched;
    private List<String> keyWords;
    private List<String> keyWordsType;
    private String lastFailReason;
    private String lastReportTime;
    private int opTimesInLandingPage_;
    private EncryptionField<String> paramFromServer_;
    private String playedProgress;
    private String preCheckResult;
    private List<String> preContentSuccessList;
    private int rawX_;
    private int rawY_;

    @e
    private String realAppPkgName;
    private long repeatedCount;
    private String requestId;
    private String shakeAngle;
    private String showid_;
    private String slotPosition;
    private long time_;
    private String type_;
    private String userId;
    private String venusExt;

    @e
    private long videoTime;
    private long showTimeDuration_ = -111111;
    private int maxShowRatio_ = -111111;
    private long videoPlayStartTime_ = -111111;
    private long videoPlayEndTime_ = -111111;
    private int videoPlayStartProgress_ = -111111;
    private int videoPlayEndProgress_ = -111111;
    private int requestType = 0;
    private int encodingMode = -111111;
    private int clickX = -111111;
    private int clickY = -111111;
    private int magLockPosition = -111111;
    private int sld = -111111;

    @e
    private float density = -111111.0f;
    private int upX = -111111;
    private int upY = -111111;
    private long clickDTime = -111111;
    private long clickUTime = -111111;
    private long startShowTime = -111111;
    private int playedTime = -111111;
    private int adReqSource = -111111;

    public void z(String str) {
        this.installType = str;
    }

    public String z() {
        return this.intentFailReason;
    }

    public void y(String str) {
        this.agVerifyCode = str;
    }

    public String y() {
        return this.intentDest;
    }

    public void x(String str) {
        this.requestId = str;
    }

    public List<String> x() {
        return this.preContentSuccessList;
    }

    public void w(String str) {
        this.contentId = str;
    }

    public String w() {
        return this.contentDownMethod;
    }

    public void v(String str) {
        this.isAdContainerSizeMatched = str;
    }

    public long v() {
        return this.repeatedCount;
    }

    public void u(String str) {
        this.userId = str;
    }

    public String u() {
        return this.lastFailReason;
    }

    public void t(String str) {
        this.customData = str;
    }

    public String t() {
        return this.lastReportTime;
    }

    public void s(String str) {
        this.fullDownload = str;
    }

    public List<String> s() {
        return this.keyWordsType;
    }

    public void r(String str) {
        this.downloadMode = str;
    }

    public List<String> r() {
        return this.keyWords;
    }

    public void q(String str) {
        this.downloadDuration = str;
    }

    public EncryptionField<String> q() {
        return this.ext;
    }

    public void p(String str) {
        this.downloadSize = str;
    }

    public int p() {
        return this.opTimesInLandingPage_;
    }

    public void o(String str) {
        this.downloadReason = str;
    }

    public int o() {
        return this.rawY_;
    }

    public void n(String str) {
        this.appVersionCode = str;
    }

    public void n(int i) {
        this.adReqSource = i;
    }

    public int n() {
        return this.rawX_;
    }

    public void m(String str) {
        this.impSource = str;
    }

    public void m(int i) {
        this.upY = i;
    }

    public EncryptionField<String> m() {
        return this.paramFromServer_;
    }

    public void l(String str) {
        this.preCheckResult = str;
    }

    public void l(int i) {
        this.upX = i;
    }

    public int l() {
        return this.adType_;
    }

    public void k(String str) {
        this.installRelatedActionSource = str;
    }

    public void k(int i) {
        this.sld = i;
    }

    public String k() {
        return this.showid_;
    }

    public void j(String str) {
        this.appDownloadRelatedActionSource = str;
    }

    public void j(int i) {
        this.playedTime = i;
    }

    public long j() {
        return this.time_;
    }

    public void i(String str) {
        this.intentFailReason = str;
    }

    public void i(long j) {
        this.clickUTime = j;
    }

    public void i(int i) {
        this.requestType = i;
    }

    public String i() {
        return this.type_;
    }

    public void h(String str) {
        this.intentDest = str;
    }

    public void h(long j) {
        this.clickDTime = j;
    }

    public void h(int i) {
        this.exposure = i;
    }

    public String h() {
        return this._id;
    }

    public void g(String str) {
        this.contentDownMethod = str;
    }

    public void g(long j) {
        this.videoTime = j;
    }

    public void g(int i) {
        this.opTimesInLandingPage_ = i;
    }

    public int g() {
        return this.maxShowRatio_;
    }

    public void f(String str) {
        this.lastFailReason = str;
    }

    public void f(long j) {
        this.startShowTime = j;
    }

    public void f(int i) {
        this.rawY_ = i;
    }

    public int f() {
        return this.videoPlayEndProgress_;
    }

    public void e(String str) {
        this.lastReportTime = str;
    }

    public void e(long j) {
        this.repeatedCount = j;
    }

    public void e(int i) {
        this.rawX_ = i;
    }

    public int e() {
        return this.videoPlayStartProgress_;
    }

    public void d(String str) {
        if (this.ext == null) {
            this.ext = new EncryptionField<>(String.class);
        }
        this.ext.a((EncryptionField<String>) str);
    }

    public void d(long j) {
        this.time_ = j;
    }

    public void d(int i) {
        this.adType_ = i;
    }

    public long d() {
        return this.videoPlayEndTime_;
    }

    public void c(String str) {
        this.showid_ = str;
    }

    public void c(long j) {
        this.videoPlayEndTime_ = j;
    }

    public void c(int i) {
        this.maxShowRatio_ = i;
    }

    public long c() {
        return this.videoPlayStartTime_;
    }

    public void b(List<String> list) {
        this.keyWordsType = list;
    }

    public void b(String str) {
        this.type_ = str;
    }

    public void b(Integer num) {
        this.clickY = num.intValue();
    }

    public void b(long j) {
        this.videoPlayStartTime_ = j;
    }

    public void b(int i) {
        this.videoPlayEndProgress_ = i;
    }

    public String b() {
        return this.clickSuccessDestination_;
    }

    public String am() {
        return this.shakeAngle;
    }

    public long al() {
        return this.clickUTime;
    }

    public long ak() {
        return this.clickDTime;
    }

    public String aj() {
        return this.realAppPkgName;
    }

    public long ai() {
        return this.videoTime;
    }

    public int ah() {
        return this.adReqSource;
    }

    public String ag() {
        return this.slotPosition;
    }

    public int af() {
        return this.upY;
    }

    public int ae() {
        return this.upX;
    }

    public float ad() {
        return this.density;
    }

    public int ac() {
        return this.sld;
    }

    public int ab() {
        return this.encodingMode;
    }

    public String aa() {
        return this.playedProgress;
    }

    public void a(List<String> list) {
        this.keyWords = list;
    }

    public void a(String str) {
        this.clickSuccessDestination_ = str;
    }

    public void a(Integer num) {
        this.clickX = num.intValue();
    }

    public void a(EncryptionField<String> encryptionField) {
        this.paramFromServer_ = encryptionField;
    }

    public void a(long j) {
        this.showTimeDuration_ = j;
    }

    public void a(int i) {
        this.videoPlayStartProgress_ = i;
    }

    public void a(float f) {
        this.density = f;
    }

    public long a() {
        return this.showTimeDuration_;
    }

    public int Z() {
        return this.playedTime;
    }

    public long Y() {
        return this.startShowTime;
    }

    public String X() {
        return this.venusExt;
    }

    public String W() {
        return this.creativeSize;
    }

    public int V() {
        return this.clickY;
    }

    public int U() {
        return this.clickX;
    }

    public String T() {
        return this.installType;
    }

    public String S() {
        return this.agVerifyCode;
    }

    public String R() {
        return this.requestId;
    }

    public String Q() {
        return this.contentId;
    }

    public int P() {
        return this.requestType;
    }

    public Integer O() {
        try {
            return Integer.valueOf(Integer.parseInt(this.isAdContainerSizeMatched));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public String N() {
        return this.userId;
    }

    public String M() {
        return this.customData;
    }

    public int L() {
        return this.exposure;
    }

    public String K() {
        return this.fullDownload;
    }

    public String J() {
        return this.downloadMode;
    }

    public String I() {
        return this.downloadDuration;
    }

    public String H() {
        return this.downloadSize;
    }

    public String G() {
        return this.downloadReason;
    }

    public void F(String str) {
        this.shakeAngle = str;
    }

    public String F() {
        return this.appVersionCode;
    }

    public void E(String str) {
        this.realAppPkgName = str;
    }

    public void D(String str) {
        this.slotPosition = str;
    }

    public String D() {
        return this.impSource;
    }

    public void C(String str) {
        this.playedProgress = str;
    }

    public String C() {
        return this.preCheckResult;
    }

    public void B(String str) {
        this.venusExt = str;
    }

    public String B() {
        return this.installRelatedActionSource;
    }

    public void A(String str) {
        this.creativeSize = str;
    }

    public String A() {
        return this.appDownloadRelatedActionSource;
    }
}
