package com.huawei.openalliance.ad.beans.inner;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.ads.VideoConfiguration;
import com.huawei.openalliance.ad.annotations.a;
import com.huawei.openalliance.ad.annotations.f;
import com.huawei.openalliance.ad.beans.metadata.ContentExt;
import com.huawei.openalliance.ad.beans.metadata.CtrlExt;
import com.huawei.openalliance.ad.beans.metadata.DefaultTemplate;
import com.huawei.openalliance.ad.beans.metadata.ImpEX;
import com.huawei.openalliance.ad.beans.metadata.InteractCfg;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.Om;
import com.huawei.openalliance.ad.beans.metadata.TextState;
import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateData;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.Scheme;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.inter.data.AdvertiserInfo;
import com.huawei.openalliance.ad.inter.data.FeedbackInfo;
import com.huawei.openalliance.ad.inter.data.JSFeedbackInfo;
import com.huawei.openalliance.ad.inter.data.e;
import com.huawei.openalliance.ad.utils.ae;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.cz;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class AdContentData {
    private String abilityDetailInfo;
    private String abilityDetailInfoEncode;
    private String adChoiceIcon;
    private String adChoiceUrl;
    private int apiVer;
    private List<Asset> assets;
    private String bannerRefSetting;
    private List<Integer> clickActionList;
    private List<AdvertiserInfo> compliance;
    private String configMap;
    private String contentDownMethod;
    private List<ContentExt> contentExts;
    private String contentId;
    private int creativeType;
    private String cshareUrl;
    private String ctrlExt;
    private CtrlExt ctrlExtObj;
    private String ctrlSwitchs;
    private Integer customExposureType;
    private DefaultTemplate defaultTemplate;
    private String detailUrl;
    private int displayCount;
    private String displayDate;
    private long endTime;
    private List<ImpEX> ext;
    private List<FeedbackInfo> feedbackInfoList;
    private Integer fileCachePriority;
    private int height;
    private String intentUri;
    private InteractCfg interactCfg;
    private int interactiontype;
    private boolean isDownloaded;
    private boolean isJssdkInWhiteList;
    private boolean isVastAd;
    private List<JSFeedbackInfo> jsFeedbackInfos;
    private List<String> keyWords;
    private List<String> keyWordsType;
    private String landPageWhiteListStr;
    private int landingTitleFlag;
    private String landingType;
    private long lastShowTime;
    private String logo2Pos;
    private String logo2Text;

    @a
    private String metaData;

    @f
    private MetaData metaDataObj;
    private int minEffectiveShowRatio;
    private long minEffectiveShowTime;
    private Integer minEffectiveVideoPlayProgress;
    private boolean needAppDownload;
    private List<String> noReportEventList;
    private List<Om> omArgs;
    private int originCreativeType;
    private int priority;
    private String proDesc;
    private String recordtaskinfo;
    private String requestId;
    private Integer requestType;
    private int rewardAmount;
    private String rewardType;
    private int sequence;
    private String skipText;
    private int skipTextHeight;
    private String skipTextPos;
    private int skipTextSize;
    private String slotId;
    private String splashMediaPath;
    private int splashPreContentFlag;
    private long startTime;
    private String style;
    private String styleExt;
    private String taskId;
    private String templateContent;
    private TemplateData templateData;
    private int templateId;
    private String templateIdV3;
    private String templateStyle;
    private String templateUrl;
    private List<TextState> textStateList;
    private boolean transparencyOpen;
    private boolean transparencySwitch;
    private String transparencyTplUrl;
    private String uniqueId;
    private long updateTime;
    private int useGaussianBlur;
    private VideoConfiguration videoConfiguration;
    private String webConfig;
    private String whyThisAd;
    private int width;
    private String showId = String.valueOf(ao.c());
    private int sdkVer = 30474310;
    private int showAppLogoFlag = 1;
    private String fcCtrlDate = "";
    private int adType = -1;
    private boolean autoDownloadApp = false;
    private boolean directReturnVideoAd = false;
    private int linkedVideoMode = 10;
    private boolean isLast = false;
    private boolean isSpare = false;
    private int splashSkipBtnDelayTime = -111111;
    private int splashShowTime = -111111;
    private boolean rewarded = false;

    public String i() {
        return this.templateIdV3;
    }

    public boolean h() {
        return this.isSpare;
    }

    public String g() {
        return this.requestId;
    }

    public String f() {
        return this.uniqueId;
    }

    public long e() {
        return this.lastShowTime;
    }

    public void d(int i) {
        this.originCreativeType = i;
    }

    public String d() {
        return this.contentId;
    }

    public void c(String str) {
        this.templateUrl = str;
    }

    public void c(int i) {
        this.creativeType = i;
    }

    public String c() {
        return this.slotId;
    }

    public void b(List<FeedbackInfo> list) {
        if (bg.a(list)) {
            return;
        }
        this.jsFeedbackInfos = new ArrayList();
        for (FeedbackInfo feedbackInfo : list) {
            if (feedbackInfo != null) {
                this.jsFeedbackInfos.add(new JSFeedbackInfo(feedbackInfo));
            }
        }
    }

    public void b(String str) {
        this.templateIdV3 = str;
    }

    public void b(int i) {
        this.apiVer = i;
    }

    public String b() {
        return this.showId;
    }

    public void a(boolean z) {
        this.isDownloaded = z;
    }

    public void a(List<Asset> list) {
        this.assets = list;
    }

    public void a(String str) {
        this.metaData = str;
        this.metaDataObj = null;
    }

    public void a(TemplateData templateData) {
        this.templateData = templateData;
    }

    public void a(DefaultTemplate defaultTemplate) {
        this.defaultTemplate = defaultTemplate;
    }

    public void a(int i) {
        this.adType = i;
    }

    public int a() {
        return this.adType;
    }

    private static ArrayList<FeedbackInfo> b(e eVar) {
        if (!bg.a(eVar.getFeedbackInfoList()) || bg.a(eVar.getAdCloseKeyWords()) || bg.a(eVar.N()) || eVar.getAdCloseKeyWords().size() != eVar.N().size()) {
            return null;
        }
        ArrayList<FeedbackInfo> arrayList = new ArrayList<>();
        for (int i = 0; i < eVar.getAdCloseKeyWords().size(); i++) {
            FeedbackInfo feedbackInfo = new FeedbackInfo();
            feedbackInfo.a(eVar.getAdCloseKeyWords().get(i));
            feedbackInfo.a(cz.a(eVar.N().get(i), -1L));
            feedbackInfo.a(1);
            arrayList.add(feedbackInfo);
        }
        return arrayList;
    }

    private static String b(Context context, ContentRecord contentRecord) {
        if (contentRecord.y() == null) {
            return null;
        }
        if (contentRecord.y().startsWith(Scheme.CONTENT.toString())) {
            return contentRecord.y();
        }
        String c = dh.a(context, "normal").c(contentRecord.y());
        if (!cz.b(c) && ae.d(new File(c))) {
            return c;
        }
        String c2 = dh.a(context, Constants.TPLATE_CACHE).c(contentRecord.y());
        if (cz.b(c2) || !ae.d(new File(c2))) {
            return null;
        }
        return c2;
    }

    public static AdContentData a(e eVar) {
        return a((Context) null, eVar);
    }

    public static AdContentData a(Context context, e eVar) {
        if (eVar == null) {
            return null;
        }
        AdContentData adContentData = new AdContentData();
        adContentData.showId = eVar.getShowId();
        adContentData.slotId = eVar.getSlotId();
        adContentData.contentId = eVar.getContentId();
        adContentData.taskId = eVar.getTaskId();
        adContentData.endTime = eVar.getEndTime();
        adContentData.startTime = eVar.getStartTime();
        adContentData.intentUri = eVar.getIntentUri();
        adContentData.adType = eVar.e();
        adContentData.a(eVar.O());
        adContentData.logo2Text = eVar.u();
        adContentData.clickActionList = eVar.R();
        adContentData.ctrlSwitchs = eVar.getCtrlSwitchs();
        adContentData.uniqueId = eVar.getUniqueId();
        adContentData.requestId = eVar.f();
        adContentData.creativeType = eVar.getCreativeType();
        adContentData.interactiontype = eVar.getInterActionType();
        adContentData.whyThisAd = cz.c(eVar.getWhyThisAd());
        adContentData.adChoiceUrl = cz.c(eVar.getAdChoiceUrl());
        adContentData.adChoiceIcon = cz.c(eVar.getAdChoiceIcon());
        adContentData.omArgs = eVar.m();
        adContentData.requestType = Integer.valueOf(eVar.g());
        adContentData.contentExts = eVar.ak();
        adContentData.feedbackInfoList = eVar.getFeedbackInfoList();
        adContentData.b(eVar.getFeedbackInfoList());
        if (!bg.a(b(eVar))) {
            adContentData.b(b(eVar));
            adContentData.feedbackInfoList = b(eVar);
        }
        adContentData.isVastAd = eVar.s();
        adContentData.apiVer = eVar.aa() == null ? -1 : eVar.aa().intValue();
        adContentData.assets = eVar.X();
        adContentData.templateIdV3 = eVar.W();
        adContentData.templateData = eVar.Y();
        adContentData.templateStyle = eVar.Z();
        adContentData.style = eVar.aw();
        adContentData.styleExt = eVar.ax();
        adContentData.templateUrl = eVar.au();
        adContentData.customExposureType = eVar.q();
        adContentData.minEffectiveVideoPlayProgress = eVar.r();
        adContentData.minEffectiveShowTime = eVar.getMinEffectiveShowTime();
        adContentData.minEffectiveShowRatio = eVar.getMinEffectiveShowRatio();
        adContentData.defaultTemplate = eVar.ab();
        adContentData.originCreativeType = eVar.w();
        adContentData.compliance = eVar.getCompliance();
        adContentData.bannerRefSetting = eVar.ai();
        adContentData.ctrlExt = eVar.c();
        adContentData.transparencySwitch = eVar.z();
        adContentData.transparencyTplUrl = eVar.getTransparencyTplUrl();
        adContentData.transparencyOpen = eVar.isTransparencyOpen();
        adContentData.abilityDetailInfoEncode = cz.c(eVar.getAbilityDetailInfo());
        adContentData.cshareUrl = cz.c(eVar.av());
        adContentData.videoConfiguration = eVar.getVideoConfiguration();
        adContentData.ext = eVar.aj();
        return adContentData;
    }

    public static AdContentData a(Context context, ContentRecord contentRecord) {
        if (contentRecord == null) {
            return null;
        }
        AdContentData adContentData = new AdContentData();
        adContentData.showId = contentRecord.j();
        adContentData.slotId = contentRecord.l();
        adContentData.contentId = contentRecord.m();
        adContentData.taskId = contentRecord.n();
        adContentData.showAppLogoFlag = contentRecord.o();
        adContentData.lastShowTime = contentRecord.p();
        adContentData.endTime = contentRecord.q();
        adContentData.startTime = contentRecord.r();
        adContentData.priority = contentRecord.C();
        adContentData.width = contentRecord.s();
        adContentData.height = contentRecord.t();
        adContentData.updateTime = contentRecord.u();
        adContentData.fcCtrlDate = contentRecord.v();
        adContentData.displayCount = contentRecord.w();
        adContentData.displayDate = contentRecord.x();
        adContentData.detailUrl = contentRecord.A();
        adContentData.interactiontype = contentRecord.B();
        adContentData.intentUri = contentRecord.G();
        adContentData.splashPreContentFlag = contentRecord.i();
        adContentData.adType = contentRecord.e();
        adContentData.skipText = contentRecord.f();
        adContentData.skipTextPos = contentRecord.L();
        adContentData.a(contentRecord.g());
        adContentData.keyWords = contentRecord.I();
        adContentData.keyWordsType = contentRecord.J();
        adContentData.logo2Text = contentRecord.N();
        adContentData.logo2Pos = contentRecord.P();
        adContentData.landingTitleFlag = contentRecord.O();
        adContentData.clickActionList = contentRecord.M();
        adContentData.contentDownMethod = contentRecord.T();
        adContentData.ctrlSwitchs = contentRecord.V();
        adContentData.textStateList = contentRecord.W();
        adContentData.uniqueId = contentRecord.aa();
        adContentData.landingType = contentRecord.ad();
        adContentData.requestId = contentRecord.ag();
        adContentData.rewardType = contentRecord.av();
        adContentData.rewardAmount = contentRecord.aw();
        adContentData.whyThisAd = cz.c(contentRecord.Z());
        adContentData.adChoiceUrl = cz.c(contentRecord.ak());
        adContentData.adChoiceIcon = cz.c(contentRecord.al());
        adContentData.skipTextHeight = contentRecord.aj();
        adContentData.skipTextSize = contentRecord.ai();
        adContentData.omArgs = contentRecord.b(context);
        adContentData.fileCachePriority = cz.h(contentRecord.an());
        adContentData.useGaussianBlur = contentRecord.aC();
        adContentData.splashShowTime = contentRecord.au();
        adContentData.splashSkipBtnDelayTime = contentRecord.at();
        adContentData.proDesc = contentRecord.ax();
        adContentData.requestType = Integer.valueOf(contentRecord.am());
        adContentData.ext = contentRecord.aE();
        adContentData.contentExts = contentRecord.aF();
        adContentData.configMap = contentRecord.aH();
        adContentData.feedbackInfoList = contentRecord.aL();
        adContentData.isVastAd = contentRecord.aM();
        adContentData.apiVer = contentRecord.aO();
        adContentData.assets = contentRecord.aV();
        adContentData.templateIdV3 = contentRecord.aN();
        adContentData.templateData = contentRecord.aT();
        adContentData.templateStyle = contentRecord.aP();
        adContentData.style = contentRecord.bl();
        adContentData.styleExt = contentRecord.bm();
        adContentData.splashMediaPath = b(context, contentRecord);
        adContentData.interactCfg = contentRecord.aJ();
        adContentData.defaultTemplate = contentRecord.aU();
        adContentData.ctrlExt = contentRecord.aY();
        adContentData.transparencySwitch = contentRecord.bc();
        adContentData.transparencyTplUrl = contentRecord.bb();
        adContentData.transparencyOpen = contentRecord.bc() && !TextUtils.isEmpty(contentRecord.bb());
        adContentData.abilityDetailInfoEncode = cz.c(contentRecord.aQ());
        return adContentData;
    }
}
