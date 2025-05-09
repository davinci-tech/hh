package com.huawei.openalliance.ad.beans.metadata;

import com.huawei.openalliance.ad.annotations.a;
import com.huawei.openalliance.ad.beans.base.RspBean;
import com.huawei.openalliance.ad.utils.bg;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class MetaData extends RspBean {
    private String adSign;
    private List<AdSource> adSources;
    private ApkInfo apkInfo;
    private String appPromotionChannel;
    private AudioInfo audioInfo;

    @a
    private String clickUrl;
    private String cta;
    private Integer customExposureType;
    private String description;
    private String dwnParameter;
    private List<ImageInfo> icon;
    private Integer iconDispDu;
    private List<ImageInfo> imageInfo;
    private int insreTemplate;

    @a
    private String intent;
    private int isPreload;
    private String label;
    private String landingPageStyle;
    private String landingPageType;
    private String marketAppId;
    private String mediaCachePath;
    private MediaFile mediaFile;
    private List<MediaFile> mediaFiles;
    private Integer minEffectiveVideoPlayProgress;
    private String privacyUrl;
    private PromoteInfo promoteInfo;
    private String rewardCriterion;
    private List<String> schemeInfo;
    private String screenOrientation;
    private ShareInfo shareInfo;
    private Integer showAppElement;
    private List<String> subDescriptions;
    private String templateId;
    private List<TextState> textStateList;
    private ImageInfo thumbNail;
    private String title;

    @a
    private String vastInfo;
    private VideoInfo videoInfo;
    private long minEffectiveShowTime = 500;
    private int minEffectiveShowRatio = 50;
    private long duration = 0;

    public String z() {
        return this.screenOrientation;
    }

    public String y() {
        return this.rewardCriterion;
    }

    public long x() {
        return this.duration;
    }

    public List<MediaFile> w() {
        return this.mediaFiles;
    }

    public List<TextState> v() {
        return this.textStateList;
    }

    public MediaFile u() {
        return this.mediaFile;
    }

    public String t() {
        return this.adSign;
    }

    public PromoteInfo s() {
        return this.promoteInfo;
    }

    public ApkInfo r() {
        return this.apkInfo;
    }

    public void q(String str) {
        this.vastInfo = str;
    }

    public ShareInfo q() {
        return this.shareInfo;
    }

    public void p(String str) {
        this.dwnParameter = str;
    }

    public ImageInfo p() {
        return this.thumbNail;
    }

    public void o(String str) {
        this.landingPageStyle = str;
    }

    public List<ImageInfo> o() {
        return this.imageInfo;
    }

    public void n(String str) {
        this.mediaCachePath = str;
    }

    public String n() {
        return this.intent;
    }

    public void m(String str) {
        this.privacyUrl = str;
    }

    public String m() {
        return this.marketAppId;
    }

    public void l(String str) {
        this.landingPageType = str;
    }

    public String l() {
        return this.appPromotionChannel;
    }

    public void k(String str) {
        this.screenOrientation = str;
    }

    public String k() {
        return this.label;
    }

    public void j(String str) {
        this.rewardCriterion = str;
    }

    public int j() {
        return this.minEffectiveShowRatio;
    }

    public void i(String str) {
        this.adSign = str;
    }

    public long i() {
        return this.minEffectiveShowTime;
    }

    public void h(String str) {
        this.intent = str;
    }

    public String h() {
        return this.clickUrl;
    }

    public void g(List<String> list) {
        this.subDescriptions = list;
    }

    public void g(String str) {
        this.marketAppId = str;
    }

    public List<ImageInfo> g() {
        return this.icon;
    }

    public void f(List<AdSource> list) {
        this.adSources = list;
    }

    public void f(String str) {
        this.appPromotionChannel = str;
    }

    public String f() {
        return this.description;
    }

    public void e(List<String> list) {
        this.schemeInfo = list;
    }

    public void e(String str) {
        this.label = str;
    }

    public String e() {
        return this.title;
    }

    public void d(List<MediaFile> list) {
        this.mediaFiles = list;
    }

    public void d(String str) {
        this.clickUrl = str;
    }

    public void d(Integer num) {
        this.showAppElement = num;
    }

    public VideoInfo d() {
        return this.videoInfo;
    }

    public void c(List<TextState> list) {
        this.textStateList = list;
    }

    public void c(String str) {
        this.description = str;
    }

    public void c(Integer num) {
        this.iconDispDu = num;
    }

    public void c(int i) {
        this.insreTemplate = i;
    }

    public Integer c() {
        return this.customExposureType;
    }

    public void b(List<ImageInfo> list) {
        this.imageInfo = list;
    }

    public void b(String str) {
        this.title = str;
    }

    public void b(Integer num) {
        this.customExposureType = num;
    }

    public void b(long j) {
        this.duration = j;
    }

    public void b(int i) {
        this.isPreload = i;
    }

    public Integer b() {
        return this.minEffectiveVideoPlayProgress;
    }

    public void a(List<ImageInfo> list) {
        this.icon = list;
    }

    public void a(String str) {
        this.cta = str;
    }

    public void a(Integer num) {
        this.minEffectiveVideoPlayProgress = num;
    }

    public void a(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }

    public void a(ShareInfo shareInfo) {
        this.shareInfo = shareInfo;
    }

    public void a(PromoteInfo promoteInfo) {
        this.promoteInfo = promoteInfo;
    }

    public void a(MediaFile mediaFile) {
        this.mediaFile = mediaFile;
    }

    public void a(ImageInfo imageInfo) {
        this.thumbNail = imageInfo;
    }

    public void a(AudioInfo audioInfo) {
        this.audioInfo = audioInfo;
    }

    public void a(ApkInfo apkInfo) {
        this.apkInfo = apkInfo;
    }

    public void a(long j) {
        this.minEffectiveShowTime = j;
    }

    public void a(int i) {
        this.minEffectiveShowRatio = i;
    }

    public String a() {
        return this.cta;
    }

    public List<String> O() {
        return this.subDescriptions;
    }

    public Integer N() {
        return this.showAppElement;
    }

    public List<String> M() {
        ArrayList arrayList = new ArrayList();
        if (!bg.a(this.imageInfo)) {
            Iterator<ImageInfo> it = this.imageInfo.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().c());
            }
        }
        return arrayList;
    }

    public List<String> L() {
        ArrayList arrayList = new ArrayList();
        VideoInfo videoInfo = this.videoInfo;
        if (videoInfo != null) {
            arrayList.add(videoInfo.a());
        }
        return arrayList;
    }

    public List<AdSource> K() {
        return this.adSources;
    }

    public String J() {
        return this.vastInfo;
    }

    public String I() {
        return this.landingPageStyle;
    }

    public int H() {
        return this.insreTemplate;
    }

    public int G() {
        return this.isPreload;
    }

    public Integer F() {
        return this.iconDispDu;
    }

    public AudioInfo E() {
        return this.audioInfo;
    }

    public List<String> D() {
        return this.schemeInfo;
    }

    public String C() {
        return this.mediaCachePath;
    }

    public String B() {
        return this.privacyUrl;
    }

    public String A() {
        return this.landingPageType;
    }
}
