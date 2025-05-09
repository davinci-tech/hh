package com.huawei.openalliance.ad.beans.metadata;

import android.text.TextUtils;
import com.huawei.openalliance.ad.annotations.a;
import com.huawei.openalliance.ad.annotations.d;
import com.huawei.openalliance.ad.annotations.f;
import com.huawei.openalliance.ad.beans.base.RspBean;
import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateV3;
import com.huawei.openalliance.ad.constant.LabelPosition;
import com.huawei.openalliance.ad.inter.data.AdvertiserInfo;
import com.huawei.openalliance.ad.inter.data.DeviceAiParam;
import com.huawei.openalliance.ad.inter.data.RewardItem;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.cz;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes5.dex */
public class Content extends RspBean implements Comparable {
    private String adChoiceIcon;
    private String adChoiceUrl;
    private Integer apiVer;
    private List<Asset> assets;
    private List<Integer> clickActionList;
    private String compliance;
    private List<ContentExt> contentExts;
    private String contentid;

    @d(a = "taskinfo")
    private String contenttaskinfo;
    private int creativetype;
    private String cshareUrl;
    private String ctrlExt;

    @f
    private CtrlExt ctrlExtObj;
    private String ctrlSwitchs;
    private String cur;
    private DefaultTemplate defaultTemplate;

    @a
    private DeviceAiParam deviceAiParam;
    private String dspid;
    private long endtime;
    private List<ImpEX> ext;
    private List<Integer> filterList;
    private String harmonyDetailInfo;
    private String harmonyHwChannelId;
    private InteractCfg interactCfg;
    private int interactiontype;

    @a
    private String jssdkAllowList;
    private List<String> keyWords;
    private List<String> keyWordsType;

    @a
    private String landPageWhiteList;
    private int landingTitle;
    private String landpgDlInteractionCfg;
    private String logo2Pos;
    private String logo2Text;
    private String lurl;

    @a
    private String metaData;

    @f
    private MetaData metaDataObj;

    @a
    private List<Monitor> monitor;
    private List<NegativeFb> negativeFbs;

    @f
    private List<String> noReportEventList;
    private String nurl;

    @a
    private List<Om> om;

    @a
    private String paramfromserver;

    @a
    private Float price;

    @d(a = "prio")
    private Integer priority;
    private String proDesc;
    private int recallSource;

    @f
    private int requestType;
    private RewardItem rewardItem;
    private Integer sdkMonitor;
    private int sequence;
    private int showAppLogoFlag;
    private String showid;
    private Skip skip;
    private String skipText;
    private String skipTextPos;
    int spare;
    private Integer splashShowTime;
    private Integer splashSkipBtnDelayTime;
    private long starttime;
    private String taskid;
    private TemplateV3 template;
    private boolean transparencySwitch;
    private String transparencyTplUrl;
    private int useGaussianBlur;
    private String webConfig;
    private String whyThisAd;

    public void z(String str) {
        this.nurl = str;
    }

    public List<String> z() {
        return this.noReportEventList;
    }

    public void y(String str) {
        this.cur = str;
    }

    public List<Integer> y() {
        return this.filterList;
    }

    public void x(String str) {
        this.dspid = str;
    }

    public int x() {
        return this.sequence;
    }

    public void w(String str) {
        this.cshareUrl = str;
    }

    public String w() {
        return this.ctrlSwitchs;
    }

    public void v(String str) {
        this.transparencyTplUrl = str;
    }

    public String v() {
        return this.webConfig;
    }

    public void u(String str) {
        this.ctrlExt = str;
    }

    public List<Integer> u() {
        return this.clickActionList;
    }

    public void t(String str) {
        this.compliance = str;
    }

    public String t() {
        return this.logo2Pos;
    }

    public void s(String str) {
        this.harmonyHwChannelId = str;
    }

    public String s() {
        return this.skipTextPos;
    }

    public void r(String str) {
        this.harmonyDetailInfo = str;
    }

    public int r() {
        return this.landingTitle;
    }

    public void q(String str) {
        this.landpgDlInteractionCfg = str;
    }

    public String q() {
        return this.logo2Text;
    }

    public void p(String str) {
        this.jssdkAllowList = str;
    }

    public List<Monitor> p() {
        return this.monitor;
    }

    public void o(String str) {
        this.proDesc = str;
    }

    public List<String> o() {
        return this.keyWordsType;
    }

    public void n(String str) {
        this.adChoiceUrl = str;
    }

    public List<String> n() {
        return this.keyWords;
    }

    public void m(String str) {
        this.whyThisAd = str;
    }

    public String m() {
        return this.paramfromserver;
    }

    public void l(String str) {
        this.landPageWhiteList = str;
    }

    public int l() {
        return this.interactiontype;
    }

    public void k(String str) {
        this.contenttaskinfo = str;
    }

    public long k() {
        return this.starttime;
    }

    public void j(List<Asset> list) {
        this.assets = list;
    }

    public void j(String str) {
        this.ctrlSwitchs = str;
    }

    public long j() {
        return this.endtime;
    }

    public void i(List<NegativeFb> list) {
        this.negativeFbs = list;
    }

    public void i(String str) {
        this.webConfig = str;
    }

    public void i(int i) {
        this.recallSource = i;
    }

    public int i() {
        return this.showAppLogoFlag;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public void h(List<ContentExt> list) {
        this.contentExts = list;
    }

    public void h(String str) {
        this.logo2Pos = str;
    }

    public void h(int i) {
        this.useGaussianBlur = i;
    }

    public String h() {
        return this.taskid;
    }

    public void g(List<ImpEX> list) {
        this.ext = list;
    }

    public void g(String str) {
        this.skipTextPos = str;
    }

    public void g(int i) {
        this.spare = i;
    }

    public String g() {
        return this.contentid;
    }

    public void f(List<Integer> list) {
        this.filterList = list;
    }

    public void f(String str) {
        this.logo2Text = str;
    }

    public void f(int i) {
        this.requestType = i;
    }

    public int f() {
        return this.creativetype;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Content) {
            return this == obj || x() == ((Content) obj).x();
        }
        return false;
    }

    public void e(List<Integer> list) {
        this.clickActionList = list;
    }

    public void e(String str) {
        this.paramfromserver = str;
    }

    public void e(int i) {
        this.sequence = i;
    }

    public List<Om> e() {
        return this.om;
    }

    public void d(List<Monitor> list) {
        this.monitor = list;
    }

    public void d(String str) {
        this.taskid = str;
    }

    public void d(Integer num) {
        this.apiVer = num;
    }

    public void d(int i) {
        this.landingTitle = i;
    }

    public String d() {
        return this.metaData;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        if (!(obj instanceof Content)) {
            return -1;
        }
        Content content = (Content) obj;
        if (content.x() <= 0 || content.x() > x()) {
            return -1;
        }
        return content.x() == x() ? 0 : 1;
    }

    public void c(List<String> list) {
        this.keyWordsType = list;
    }

    public void c(String str) {
        this.contentid = str;
    }

    public void c(Integer num) {
        this.splashShowTime = num;
    }

    public void c(int i) {
        this.interactiontype = i;
    }

    public MetaData c() {
        if (this.metaDataObj == null) {
            this.metaDataObj = (MetaData) be.b(this.metaData, MetaData.class, new Class[0]);
        }
        return this.metaDataObj;
    }

    public void b(List<String> list) {
        this.keyWords = list;
    }

    public void b(String str) {
        this.metaData = str;
    }

    public void b(Integer num) {
        this.splashSkipBtnDelayTime = num;
    }

    public void b(long j) {
        this.starttime = j;
    }

    public void b(int i) {
        this.showAppLogoFlag = i;
    }

    public String b() {
        return this.skipText;
    }

    public String aj() {
        return this.lurl;
    }

    public String ai() {
        return this.nurl;
    }

    public Float ah() {
        return this.price;
    }

    public String ag() {
        return this.cur;
    }

    public String af() {
        return this.cshareUrl;
    }

    public Boolean ae() {
        return Boolean.valueOf(this.transparencySwitch);
    }

    public String ad() {
        return this.transparencyTplUrl;
    }

    public String ac() {
        return this.ctrlExt;
    }

    public int ab() {
        return this.recallSource;
    }

    public DefaultTemplate aa() {
        return this.defaultTemplate;
    }

    public void a(List<AdTypeEvent> list, int i) {
        List<String> b;
        if (bg.a(list)) {
            return;
        }
        for (AdTypeEvent adTypeEvent : list) {
            if (adTypeEvent != null && adTypeEvent.a() == i && (b = adTypeEvent.b()) != null && b.size() > 0) {
                ArrayList arrayList = new ArrayList();
                this.noReportEventList = arrayList;
                arrayList.addAll(b);
            }
        }
    }

    public void a(List<Om> list) {
        this.om = list;
    }

    public void a(String str) {
        this.skipText = str;
    }

    public void a(Integer num) {
        this.sdkMonitor = num;
    }

    public void a(Float f) {
        this.price = f;
    }

    public void a(RewardItem rewardItem) {
        this.rewardItem = rewardItem;
    }

    public void a(DeviceAiParam deviceAiParam) {
        this.deviceAiParam = deviceAiParam;
    }

    public void a(TemplateV3 templateV3) {
        this.template = templateV3;
    }

    public void a(Skip skip) {
        this.skip = skip;
    }

    public void a(InteractCfg interactCfg) {
        this.interactCfg = interactCfg;
    }

    public void a(DefaultTemplate defaultTemplate) {
        this.defaultTemplate = defaultTemplate;
    }

    public void a(long j) {
        this.endtime = j;
    }

    public void a(int i) {
        this.creativetype = i;
    }

    public Integer a() {
        return this.sdkMonitor;
    }

    public List<AdvertiserInfo> Z() {
        if (TextUtils.isEmpty(this.compliance)) {
            return null;
        }
        List<AdvertiserInfo> list = (List) be.b(cz.c(this.compliance), List.class, AdvertiserInfo.class);
        if (!bg.a(list)) {
            Collections.sort(list);
        }
        return list;
    }

    public String Y() {
        return this.harmonyHwChannelId;
    }

    public String X() {
        return this.harmonyDetailInfo;
    }

    public TemplateV3 W() {
        return this.template;
    }

    public List<Asset> V() {
        return this.assets;
    }

    public Integer U() {
        return this.apiVer;
    }

    public List<NegativeFb> T() {
        return this.negativeFbs;
    }

    public InteractCfg S() {
        return this.interactCfg;
    }

    public String R() {
        return this.landpgDlInteractionCfg;
    }

    public List<ContentExt> Q() {
        return this.contentExts;
    }

    public List<ImpEX> P() {
        return this.ext;
    }

    public String O() {
        return this.jssdkAllowList;
    }

    public int N() {
        return this.useGaussianBlur;
    }

    public String M() {
        return this.proDesc;
    }

    public Integer L() {
        return this.splashShowTime;
    }

    public Integer K() {
        return this.splashSkipBtnDelayTime;
    }

    public int J() {
        return this.spare;
    }

    public Integer I() {
        return this.priority;
    }

    public int H() {
        return this.requestType;
    }

    public Skip G() {
        return this.skip;
    }

    public String F() {
        return this.adChoiceIcon;
    }

    public String E() {
        return this.adChoiceUrl;
    }

    public String D() {
        return this.whyThisAd;
    }

    public RewardItem C() {
        return this.rewardItem;
    }

    public String B() {
        return this.landPageWhiteList;
    }

    public void A(String str) {
        this.lurl = str;
    }

    public String A() {
        return this.contenttaskinfo;
    }

    public Content(Precontent precontent) {
        this.showAppLogoFlag = 1;
        this.starttime = -1L;
        this.interactiontype = 0;
        this.creativetype = 1;
        this.showid = "";
        this.skipTextPos = "tr";
        this.recallSource = 0;
        this.logo2Text = "";
        this.logo2Pos = LabelPosition.LOWER_LEFT;
        this.requestType = 0;
        this.transparencySwitch = false;
        this.sdkMonitor = 0;
        if (precontent != null) {
            this.contentid = precontent.b();
            this.creativetype = precontent.c();
            this.ctrlSwitchs = precontent.f();
            this.noReportEventList = precontent.g();
            MetaData metaData = new MetaData();
            metaData.b(precontent.d());
            metaData.a(precontent.e());
            metaData.d(precontent.h());
            this.metaData = be.b(metaData);
            this.priority = precontent.i();
            this.apiVer = precontent.j();
            this.assets = precontent.k();
            this.template = precontent.l();
        }
    }

    public Content() {
        this.showAppLogoFlag = 1;
        this.starttime = -1L;
        this.interactiontype = 0;
        this.creativetype = 1;
        this.showid = "";
        this.skipTextPos = "tr";
        this.recallSource = 0;
        this.logo2Text = "";
        this.logo2Pos = LabelPosition.LOWER_LEFT;
        this.requestType = 0;
        this.transparencySwitch = false;
        this.sdkMonitor = 0;
    }
}
