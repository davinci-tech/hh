package com.huawei.openalliance.ad.db.bean;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.annotations.c;
import com.huawei.openalliance.ad.annotations.e;
import com.huawei.openalliance.ad.annotations.f;
import com.huawei.openalliance.ad.beans.metadata.ApkInfo;
import com.huawei.openalliance.ad.beans.metadata.ContentExt;
import com.huawei.openalliance.ad.beans.metadata.CtrlExt;
import com.huawei.openalliance.ad.beans.metadata.DefaultTemplate;
import com.huawei.openalliance.ad.beans.metadata.ImageInfo;
import com.huawei.openalliance.ad.beans.metadata.ImpEX;
import com.huawei.openalliance.ad.beans.metadata.InteractCfg;
import com.huawei.openalliance.ad.beans.metadata.MediaFile;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.Monitor;
import com.huawei.openalliance.ad.beans.metadata.Om;
import com.huawei.openalliance.ad.beans.metadata.PromoteInfo;
import com.huawei.openalliance.ad.beans.metadata.TextState;
import com.huawei.openalliance.ad.beans.metadata.VideoInfo;
import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateData;
import com.huawei.openalliance.ad.beans.vast.Tracking;
import com.huawei.openalliance.ad.inter.data.AdvertiserInfo;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.FeedbackInfo;
import com.huawei.openalliance.ad.inter.data.RewardItem;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.cz;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class ContentRecord extends a implements Serializable {
    public static final String AD_TYPE = "adType";
    public static final String API_VER = "apiVer";
    public static final String CONTENT_ID = "contentId";

    @e
    public static final String CREATIVE_TYPE = "creativeType";
    public static final String DISPLAY_COUNT = "displayCount";
    public static final String DISPLAY_DATE = "displayDate";
    public static final String END_TIME = "endTime";
    public static final String FC_CTRL_DATE = "fcCtrlDate";
    public static final String HEIGHT = "height";
    public static final String LAST_SHOW_TIME = "lastShowTime";
    public static final String PRIORITY = "priority";
    public static final String SLOT_ID = "slotId";
    public static final String SPLASH_MEDIA_PATH = "splashMediaPath";
    public static final String SPLASH_PRE_CONTENT_FLAG = "splashPreContentFlag";
    public static final String START_TIME = "startTime";
    public static final String TASK_ID = "taskId";
    public static final String TEMPLATE_ID = "templateId";
    public static final String UNIQUE_ID = "uniqueId";
    public static final String UPDATE_TIME = "updateTime";
    public static final String WIDTH = "width";
    private static final long serialVersionUID = 30414300;
    private String abilityDetailInfo;
    private String adChoiceIcon;
    private String adChoiceUrl;

    @e
    private long addTime;
    private int apiVer;
    private String appCountry;
    private String appLanguage;

    @c
    private List<Asset> assets;
    private String cid;
    private List<Integer> clickActionList;
    private List<AdvertiserInfo> compliance;
    private String configMap;
    private String contentDownMethod;
    private List<ContentExt> contentExts;
    private String contentId_;

    @f
    @e
    private String cshareUrl;
    private String ctrlExt;

    @e
    private CtrlExt ctrlExtObj;
    private String ctrlSwitchs;
    private String cur;
    private String customData;
    private DefaultTemplate defaultTemplate;
    private String detailUrl_;
    private int displayCount_;
    private String displayDate_;
    private long endTime_;
    private int exposure;
    private List<ImpEX> ext;
    private List<FeedbackInfo> feedbackInfoList;
    private String fileCachePriority;
    private int height_;
    private String hwChannelId;
    private String intentUri_;
    private InteractCfg interactCfg;
    private int interactiontype_;

    @e
    private String isAdContainerSizeMatched;

    @f
    private EncryptionField<String> jssdkAllowListStr;
    private List<String> keyWords;
    private List<String> keyWordsType;

    @f
    private EncryptionField<String> landPageWhiteListStr;
    private int landingTitleFlag;
    private String landingType;
    private String landpgDlInteractionCfg;
    private long lastShowTime_;
    private String logo2Pos;
    private String logo2Text;
    private String lurl;

    @e
    private Map<String, String> mapConfigMap;

    @e
    private MetaData metaDataInner;
    private String metaData_;
    private int minEffectiveShowRatio;
    private long minEffectiveShowTime;
    private int minEffectiveVideoPlayProgress;

    @f
    private EncryptionField<List<Monitor>> monitors;
    private List<String> noReportEventList;
    private String nurl;

    @f
    private EncryptionField<List<Om>> om;

    @f
    private EncryptionField<String> paramFromServer;

    @f
    private EncryptionField<String> price;
    private int priority_;
    private String proDesc;
    private String realAppPkgName;
    private int recallSource;
    private String recordtaskinfo;

    @e
    private String requestId;
    private int rewardAmount;
    private RewardItem rewardItem;
    private String rewardType;
    private int sdkMonitor;
    private int skipTextHeight;
    private String skipTextPos;
    private int skipTextSize;
    private String skipText_;
    private String slotId_;
    private String splashMediaPath;
    private int splashPreContentFlag_;
    private long startShowTime;
    private long startTime_;
    private String style;
    private String styleExt;
    private String taskId_;

    @e
    private TemplateData templateData;
    private String templateId;

    @e
    private String templateStyle;
    private String templateUrl;
    private List<TextState> textStateList;
    private Map<String, List<Tracking>> trackingEvents;
    private int transparencySwitch;
    private String transparencyTplUrl;
    private String uniqueId;
    private String uniqueIdForUnifyDownload;
    private long updateTime_;
    private int useGaussianBlur;
    private String userId;

    @e
    private long videoTime;
    private String webConfig;
    private String whyThisAd;
    private int width_;

    @e
    private String showId = String.valueOf(ao.c());
    private int showAppLogoFlag_ = 1;
    private String fcCtrlDate_ = "";
    private int creativeType_ = 2;
    private int adType_ = -1;

    @e
    private boolean autoDownloadApp = false;
    private int requestType = 0;

    @e
    private boolean isFromExSplash = false;

    @e
    private boolean isSpare = false;
    private int splashSkipBtnDelayTime = -111111;
    private int splashShowTime = -111111;
    private int isVastAd = 0;

    @e
    private boolean isPreload = false;

    public void z(String str) {
        this.adChoiceUrl = str;
    }

    public String z() {
        MetaData h = h();
        if (h != null) {
            return h.C();
        }
        return null;
    }

    public void y(String str) {
        this.requestId = str;
    }

    public String y() {
        return this.splashMediaPath;
    }

    public void x(String str) {
        this.landingType = str;
    }

    public String x() {
        return this.displayDate_;
    }

    public void w(String str) {
        if (this.landPageWhiteListStr == null) {
            this.landPageWhiteListStr = new EncryptionField<>(String.class);
        }
        this.landPageWhiteListStr.a((EncryptionField<String>) str);
    }

    public int w() {
        return this.displayCount_;
    }

    public void v(String str) {
        this.uniqueId = str;
    }

    public String v() {
        return this.fcCtrlDate_;
    }

    public void u(String str) {
        this.whyThisAd = str;
    }

    public long u() {
        return this.updateTime_;
    }

    public void t(String str) {
        this.recordtaskinfo = str;
    }

    public void t(int i) {
        this.recallSource = i;
    }

    public int t() {
        return this.height_;
    }

    public void s(String str) {
        this.ctrlSwitchs = str;
    }

    public void s(int i) {
        this.apiVer = i;
    }

    public int s() {
        return this.width_;
    }

    public void r(String str) {
        this.webConfig = str;
    }

    public void r(int i) {
        this.useGaussianBlur = i;
    }

    public long r() {
        return this.startTime_;
    }

    public void q(String str) {
        this.contentDownMethod = str;
    }

    public void q(int i) {
        this.splashShowTime = i;
    }

    public long q() {
        return this.endTime_;
    }

    public void p(String str) {
        this.logo2Pos = str;
    }

    public void p(int i) {
        this.splashSkipBtnDelayTime = i;
    }

    public long p() {
        return this.lastShowTime_;
    }

    public void o(String str) {
        this.logo2Text = str;
    }

    public void o(int i) {
        this.requestType = i;
    }

    public int o() {
        return this.showAppLogoFlag_;
    }

    public void n(String str) {
        this.skipTextPos = str;
    }

    public void n(int i) {
        this.skipTextHeight = i;
    }

    public String n() {
        return this.taskId_;
    }

    public void m(String str) {
        this.intentUri_ = str;
    }

    public void m(int i) {
        this.skipTextSize = i;
    }

    public String m() {
        return this.contentId_;
    }

    public void l(List<Asset> list) {
        this.assets = list;
    }

    public void l(String str) {
        if (this.paramFromServer == null) {
            this.paramFromServer = new EncryptionField<>(String.class);
        }
        this.paramFromServer.a((EncryptionField<String>) str);
    }

    public void l(int i) {
        this.landingTitleFlag = i;
    }

    public String l() {
        return this.slotId_;
    }

    public void k(List<AdvertiserInfo> list) {
        this.compliance = list;
    }

    public void k(String str) {
        this.detailUrl_ = str;
    }

    public void k(int i) {
        this.creativeType_ = i;
    }

    public int k() {
        return this.exposure;
    }

    public void j(List<FeedbackInfo> list) {
        this.feedbackInfoList = list;
    }

    public void j(String str) {
        MetaData h;
        if (TextUtils.isEmpty(str) || (h = h()) == null) {
            return;
        }
        h.n(str);
        b(be.b(h));
    }

    public void j(int i) {
        this.priority_ = i;
    }

    public String j() {
        return this.showId;
    }

    public void i(List<ContentExt> list) {
        this.contentExts = list;
    }

    public void i(String str) {
        this.splashMediaPath = str;
    }

    public void i(int i) {
        this.interactiontype_ = i;
    }

    public int i() {
        return this.splashPreContentFlag_;
    }

    public void h(List<ImpEX> list) {
        this.ext = list;
    }

    public void h(String str) {
        this.displayDate_ = str;
    }

    public void h(long j) {
        this.videoTime = j;
    }

    public void h(int i) {
        this.displayCount_ = i;
    }

    public MetaData h() {
        String str = this.metaData_;
        if (str == null) {
            return null;
        }
        return (MetaData) be.b(str, MetaData.class, new Class[0]);
    }

    public void g(List<String> list) {
        this.noReportEventList = list;
    }

    public void g(String str) {
        this.fcCtrlDate_ = str;
    }

    public void g(long j) {
        this.addTime = j;
    }

    public void g(int i) {
        this.height_ = i;
    }

    public String g() {
        return this.metaData_;
    }

    public void f(boolean z) {
        this.isPreload = z;
    }

    public void f(List<TextState> list) {
        this.textStateList = list;
    }

    public void f(String str) {
        this.taskId_ = str;
    }

    public void f(long j) {
        this.startShowTime = j;
    }

    public void f(int i) {
        this.width_ = i;
    }

    public String f() {
        return this.skipText_;
    }

    public void e(boolean z) {
        this.transparencySwitch = z ? 1 : 0;
    }

    public void e(List<Integer> list) {
        this.clickActionList = list;
    }

    public void e(String str) {
        this.contentId_ = str;
    }

    public void e(long j) {
        this.updateTime_ = j;
    }

    public void e(int i) {
        this.showAppLogoFlag_ = i;
    }

    public int e() {
        return this.adType_;
    }

    public void d(boolean z) {
        this.isVastAd = z ? 1 : 0;
    }

    public void d(List<Om> list) {
        EncryptionField<List<Om>> encryptionField = new EncryptionField<>(List.class, Om.class);
        encryptionField.a((EncryptionField<List<Om>>) list);
        this.om = encryptionField;
    }

    public void d(String str) {
        this.slotId_ = str;
    }

    public void d(EncryptionField<String> encryptionField) {
        this.jssdkAllowListStr = encryptionField;
    }

    public void d(long j) {
        this.startTime_ = j;
    }

    public void d(int i) {
        this.exposure = i;
    }

    public int d() {
        return this.minEffectiveVideoPlayProgress;
    }

    public void c(boolean z) {
        this.isSpare = z;
    }

    public void c(List<Monitor> list) {
        EncryptionField<List<Monitor>> encryptionField = new EncryptionField<>(List.class, Monitor.class);
        encryptionField.a((EncryptionField<List<Monitor>>) list);
        this.monitors = encryptionField;
    }

    public void c(String str) {
        this.showId = str;
    }

    public void c(EncryptionField<String> encryptionField) {
        this.landPageWhiteListStr = encryptionField;
    }

    public void c(long j) {
        this.endTime_ = j;
    }

    public void c(int i) {
        this.splashPreContentFlag_ = i;
    }

    public String c(Context context) {
        EncryptionField<String> encryptionField = this.paramFromServer;
        if (encryptionField != null) {
            return encryptionField.a(context);
        }
        return null;
    }

    public int c() {
        return this.minEffectiveShowRatio;
    }

    public String bm() {
        return this.styleExt;
    }

    public String bl() {
        return this.style;
    }

    public String bk() {
        return this.lurl;
    }

    public String bj() {
        return this.nurl;
    }

    public EncryptionField<String> bi() {
        EncryptionField<String> encryptionField = this.price;
        if (encryptionField == null) {
            return null;
        }
        return encryptionField;
    }

    public String bh() {
        return this.cur;
    }

    public boolean bg() {
        return this.isPreload;
    }

    public String bf() {
        return this.realAppPkgName;
    }

    public long be() {
        return this.videoTime;
    }

    public long bd() {
        return this.addTime;
    }

    public boolean bc() {
        return this.transparencySwitch == 1;
    }

    public String bb() {
        return this.transparencyTplUrl;
    }

    public String ba() {
        return this.uniqueIdForUnifyDownload;
    }

    public void b(boolean z) {
        this.isFromExSplash = z;
    }

    public void b(List<String> list) {
        this.keyWordsType = list;
    }

    public void b(String str) {
        this.metaData_ = str;
    }

    public void b(Integer num) {
        if (num != null) {
            this.minEffectiveVideoPlayProgress = num.intValue();
        }
    }

    public void b(EncryptionField<List<Monitor>> encryptionField) {
        this.monitors = encryptionField;
    }

    public void b(long j) {
        this.lastShowTime_ = j;
    }

    public void b(int i) {
        this.adType_ = i;
    }

    public List<Om> b(Context context) {
        EncryptionField<List<Om>> encryptionField = this.om;
        if (encryptionField != null) {
            return encryptionField.a(context);
        }
        return null;
    }

    public long b() {
        return this.minEffectiveShowTime;
    }

    public int az() {
        if (S() != null) {
            return S().i();
        }
        if (R() != null) {
            return R().h();
        }
        return -1;
    }

    public String ay() {
        MetaData h = h();
        if (h == null) {
            return null;
        }
        VideoInfo d = h.d();
        if (d != null) {
            return String.valueOf(d.c());
        }
        MediaFile u = h.u();
        if (u != null) {
            return String.valueOf(u.d());
        }
        List<ImageInfo> o = h.o();
        if (!bg.a(o)) {
            for (ImageInfo imageInfo : o) {
                if (imageInfo != null) {
                    return String.valueOf(imageInfo.f());
                }
            }
        }
        List<ImageInfo> g = h.g();
        if (!bg.a(g)) {
            for (ImageInfo imageInfo2 : g) {
                if (imageInfo2 != null) {
                    return String.valueOf(imageInfo2.f());
                }
            }
        }
        return null;
    }

    public String ax() {
        return cz.c(this.proDesc);
    }

    public int aw() {
        return this.rewardAmount;
    }

    public String av() {
        return this.rewardType;
    }

    public int au() {
        return this.splashShowTime;
    }

    public int at() {
        return this.splashSkipBtnDelayTime;
    }

    public boolean as() {
        return this.isSpare;
    }

    public String ar() {
        return this.appCountry;
    }

    public String aq() {
        return this.appLanguage;
    }

    public boolean ap() {
        return this.isFromExSplash;
    }

    public String ao() {
        return this.isAdContainerSizeMatched;
    }

    public String an() {
        return this.fileCachePriority;
    }

    public int am() {
        return this.requestType;
    }

    public String al() {
        return this.adChoiceIcon;
    }

    public String ak() {
        return this.adChoiceUrl;
    }

    public int aj() {
        return this.skipTextHeight;
    }

    public int ai() {
        return this.skipTextSize;
    }

    public RewardItem ah() {
        return this.rewardItem;
    }

    public String ag() {
        return this.requestId;
    }

    public PromoteInfo af() {
        MetaData h = h();
        if (h == null) {
            return null;
        }
        return h.s();
    }

    public AppInfo ae() {
        ApkInfo r;
        MetaData h = h();
        if (h == null || (r = h.r()) == null) {
            return null;
        }
        AppInfo appInfo = new AppInfo(r);
        appInfo.k(h.n());
        appInfo.s(this.uniqueId);
        appInfo.h(h.B());
        return appInfo;
    }

    public String ad() {
        return this.landingType;
    }

    public boolean ac() {
        return this.autoDownloadApp;
    }

    public EncryptionField<String> ab() {
        return this.landPageWhiteListStr;
    }

    public String aa() {
        return this.uniqueId;
    }

    @Override // com.huawei.openalliance.ad.db.bean.a
    public String a_() {
        return "materialId";
    }

    public CtrlExt aZ() {
        if (this.ctrlExtObj == null) {
            this.ctrlExtObj = (CtrlExt) be.b(this.ctrlExt, CtrlExt.class, new Class[0]);
        }
        return this.ctrlExtObj;
    }

    public String aY() {
        return this.ctrlExt;
    }

    public String aX() {
        return this.templateUrl;
    }

    public int aW() {
        return this.recallSource;
    }

    public List<Asset> aV() {
        return this.assets;
    }

    public DefaultTemplate aU() {
        return this.defaultTemplate;
    }

    public TemplateData aT() {
        return this.templateData;
    }

    public List<AdvertiserInfo> aS() {
        return this.compliance;
    }

    public String aR() {
        return this.hwChannelId;
    }

    public String aQ() {
        return this.abilityDetailInfo;
    }

    public String aP() {
        return this.templateStyle;
    }

    public int aO() {
        return this.apiVer;
    }

    public String aN() {
        return this.templateId;
    }

    public boolean aM() {
        return 1 == this.isVastAd;
    }

    public List<FeedbackInfo> aL() {
        return this.feedbackInfoList;
    }

    public long aK() {
        return this.startShowTime;
    }

    public InteractCfg aJ() {
        return this.interactCfg;
    }

    public Map<String, String> aI() {
        if (this.mapConfigMap == null) {
            this.mapConfigMap = (Map) be.b(this.configMap, Map.class, new Class[0]);
        }
        return this.mapConfigMap;
    }

    public String aH() {
        return this.configMap;
    }

    public String aG() {
        return this.landpgDlInteractionCfg;
    }

    public List<ContentExt> aF() {
        return this.contentExts;
    }

    public List<ImpEX> aE() {
        return this.ext;
    }

    public EncryptionField<String> aD() {
        return this.jssdkAllowListStr;
    }

    public int aC() {
        return this.useGaussianBlur;
    }

    public String aB() {
        return this.userId;
    }

    public String aA() {
        return this.customData;
    }

    public void a(boolean z) {
        this.autoDownloadApp = z;
    }

    public void a(List<String> list) {
        this.keyWords = list;
    }

    public void a(String str) {
        this.skipText_ = str;
    }

    public void a(Integer num) {
        if (num != null) {
            this.sdkMonitor = num.intValue();
        }
    }

    public void a(Float f) {
        if (f == null) {
            return;
        }
        if (this.price == null) {
            this.price = new EncryptionField<>(Float.class);
        }
        this.price.a((EncryptionField<String>) f.toString());
    }

    public void a(RewardItem rewardItem) {
        this.rewardItem = rewardItem;
    }

    public void a(EncryptionField<String> encryptionField) {
        this.paramFromServer = encryptionField;
    }

    public void a(TemplateData templateData) {
        this.templateData = templateData;
    }

    public void a(InteractCfg interactCfg) {
        this.interactCfg = interactCfg;
    }

    public void a(DefaultTemplate defaultTemplate) {
        this.defaultTemplate = defaultTemplate;
    }

    public void a(long j) {
        this.minEffectiveShowTime = j;
    }

    public void a(int i) {
        this.minEffectiveShowRatio = i;
    }

    public List<Monitor> a(Context context) {
        EncryptionField<List<Monitor>> encryptionField = this.monitors;
        if (encryptionField != null) {
            return encryptionField.a(context);
        }
        return null;
    }

    public int a() {
        return this.sdkMonitor;
    }

    public void Z(String str) {
        this.styleExt = str;
    }

    public String Z() {
        return this.whyThisAd;
    }

    public void Y(String str) {
        this.style = str;
    }

    public String Y() {
        return this.recordtaskinfo;
    }

    public void X(String str) {
        this.lurl = str;
    }

    public List<String> X() {
        return this.noReportEventList;
    }

    public void W(String str) {
        this.nurl = str;
    }

    public List<TextState> W() {
        return this.textStateList;
    }

    public void V(String str) {
        this.cur = str;
    }

    public String V() {
        return this.ctrlSwitchs;
    }

    public void U(String str) {
        this.cshareUrl = str;
    }

    public String U() {
        return this.webConfig;
    }

    public void T(String str) {
        this.realAppPkgName = str;
    }

    public String T() {
        return this.contentDownMethod;
    }

    public void S(String str) {
        this.transparencyTplUrl = str;
    }

    public MediaFile S() {
        MetaData h = h();
        if (h != null) {
            return h.u();
        }
        return null;
    }

    public void R(String str) {
        this.uniqueIdForUnifyDownload = str;
    }

    public VideoInfo R() {
        MetaData metaData = (MetaData) be.b(this.metaData_, MetaData.class, new Class[0]);
        if (metaData != null) {
            return metaData.d();
        }
        return null;
    }

    public void Q(String str) {
        this.ctrlExt = str;
    }

    public ImageInfo Q() {
        List<ImageInfo> o;
        MetaData metaData = (MetaData) be.b(this.metaData_, MetaData.class, new Class[0]);
        if (metaData == null || (o = metaData.o()) == null || o.size() <= 0) {
            return null;
        }
        return o.get(0);
    }

    public void P(String str) {
        this.templateUrl = str;
    }

    public String P() {
        return this.logo2Pos;
    }

    public void O(String str) {
        this.hwChannelId = str;
    }

    public int O() {
        return this.landingTitleFlag;
    }

    public void N(String str) {
        this.abilityDetailInfo = str;
    }

    public String N() {
        return this.logo2Text;
    }

    public void M(String str) {
        this.templateStyle = str;
    }

    public List<Integer> M() {
        return this.clickActionList;
    }

    public void L(String str) {
        this.templateId = str;
    }

    public String L() {
        return this.skipTextPos;
    }

    public void K(String str) {
        this.configMap = str;
    }

    public EncryptionField<List<Monitor>> K() {
        return this.monitors;
    }

    public void J(String str) {
        this.landpgDlInteractionCfg = str;
    }

    public List<String> J() {
        return this.keyWordsType;
    }

    public void I(String str) {
        if (this.jssdkAllowListStr == null) {
            this.jssdkAllowListStr = new EncryptionField<>(String.class);
        }
        this.jssdkAllowListStr.a((EncryptionField<String>) str);
    }

    public List<String> I() {
        return this.keyWords;
    }

    public void H(String str) {
        this.userId = str;
    }

    public void H() {
        this.displayCount_++;
        this.priority_ = 1;
        this.lastShowTime_ = ao.c();
    }

    public void G(String str) {
        this.customData = str;
    }

    public String G() {
        return this.intentUri_;
    }

    public void F(String str) {
        this.proDesc = str;
    }

    public EncryptionField<String> F() {
        return this.paramFromServer;
    }

    public void E(String str) {
        this.appCountry = str;
    }

    public void D(String str) {
        this.appLanguage = str;
    }

    public int D() {
        return this.creativeType_;
    }

    public void C(String str) {
        this.isAdContainerSizeMatched = str;
    }

    public int C() {
        return this.priority_;
    }

    public void B(String str) {
        this.fileCachePriority = str;
    }

    public int B() {
        return this.interactiontype_;
    }

    public void A(String str) {
        this.adChoiceIcon = str;
    }

    public String A() {
        return this.detailUrl_;
    }
}
