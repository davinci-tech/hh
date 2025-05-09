package com.huawei.hms.ads.jsb.inner.data;

import com.huawei.openalliance.ad.beans.metadata.CtrlExt;
import com.huawei.openalliance.ad.beans.metadata.ImpEX;
import com.huawei.openalliance.ad.inter.data.AdvertiserInfo;
import com.huawei.openalliance.ad.inter.data.RewardItem;
import com.huawei.openalliance.ad.inter.data.d;
import com.huawei.openalliance.ad.inter.data.e;
import com.huawei.openalliance.ad.inter.data.g;
import com.huawei.openalliance.ad.inter.data.h;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.cz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class H5Ad {
    private String abilityDetailInfo;
    private String adChoiceUrl;
    private int adType;
    private List<AdvertiserInfo> advertiserInfos;
    private int apiVer;
    private App app;
    private String bannerRefSetting;
    private String clickBtnTxt;
    private String contentId;
    private int creativeType;
    private CtrlExt ctrlExt;
    private String desc;
    private String dspLogo;
    private String dspName;
    private long endTime;
    private Map<String, String> ext;
    private ImageInfo icon;
    private List<ImageInfo> imgList;
    private int interactionType;
    private boolean isGaussianBlur;
    private boolean isSilentReserve;
    private List<String> keywords;
    private String label;
    private String logo2Text;
    private MediaFile mediaFile;
    private int minEffectiveShowRatio;
    private long minEffectiveShowTime;
    private String requestId;
    private RewardItem rewardItem;
    private int sequence;
    private boolean showAppElement;
    private String showId;
    private String slotId;
    private String source;
    private String taskId;
    private String templateId;
    private String title;
    private boolean transparencyOpen;
    private boolean transparencySwitch;
    private String transparencyTplUrl;
    private String uniqueId;
    private Video video;

    public int a() {
        return this.adType;
    }

    public H5Ad(h hVar) {
        this.apiVer = 2;
        if (hVar == null) {
            return;
        }
        this.requestId = hVar.f();
        this.uniqueId = hVar.getUniqueId();
        this.showId = hVar.getShowId();
        this.slotId = hVar.P();
        this.contentId = hVar.getContentId();
        this.taskId = hVar.getTaskId();
        this.rewardItem = hVar.getRewardItem();
        this.ctrlExt = hVar.d();
        this.showAppElement = hVar.isShowAppElement();
        this.transparencyOpen = hVar.isTransparencyOpen();
        this.transparencyTplUrl = hVar.getTransparencyTplUrl();
        this.transparencySwitch = hVar.z();
        this.abilityDetailInfo = hVar.getAbilityDetailInfo();
        if (!bg.a(hVar.getCompliance())) {
            this.advertiserInfos = hVar.getCompliance();
        }
        this.apiVer = hVar.aj() != null ? hVar.aj().intValue() : 2;
        this.templateId = hVar.ag();
    }

    public H5Ad(g gVar) {
        this.apiVer = 2;
        if (gVar == null) {
            return;
        }
        this.requestId = gVar.f();
        this.uniqueId = gVar.getUniqueId();
        this.showId = gVar.getShowId();
        this.slotId = gVar.F();
        this.contentId = gVar.getContentId();
        this.taskId = gVar.getTaskId();
        this.adType = gVar.e();
        this.creativeType = gVar.getCreativeType();
        this.interactionType = gVar.getInterActionType();
        this.endTime = gVar.getEndTime();
        this.minEffectiveShowTime = gVar.getMinEffectiveShowTime();
        this.minEffectiveShowRatio = gVar.getMinEffectiveShowRatio();
        this.source = gVar.getLabel();
        this.clickBtnTxt = gVar.getCta();
        this.sequence = gVar.getSequence();
        this.adChoiceUrl = gVar.getAdChoiceUrl();
        this.ctrlExt = gVar.d();
        this.showAppElement = gVar.isShowAppElement();
        this.transparencyOpen = gVar.isTransparencyOpen();
        this.transparencyTplUrl = gVar.getTransparencyTplUrl();
        this.transparencySwitch = gVar.z();
        this.abilityDetailInfo = gVar.getAbilityDetailInfo();
        if (gVar.getAppInfo() != null) {
            this.app = new App(gVar.getAppInfo());
        }
        if (gVar.getMediaFile() != null) {
            this.mediaFile = new MediaFile(gVar.getMediaFile());
        }
        if (bg.a(gVar.getAdvertiserInfo())) {
            return;
        }
        this.advertiserInfos = gVar.getAdvertiserInfo();
    }

    public H5Ad(e eVar) {
        this.apiVer = 2;
        if (eVar == null) {
            return;
        }
        this.isSilentReserve = os.F(eVar.getCtrlSwitchs());
        this.requestId = eVar.f();
        this.uniqueId = eVar.getUniqueId();
        this.showId = eVar.getShowId();
        this.slotId = eVar.getSlotId();
        this.contentId = eVar.getContentId();
        this.taskId = eVar.getTaskId();
        this.adType = eVar.e();
        this.creativeType = eVar.getCreativeType();
        this.interactionType = eVar.getInterActionType();
        this.endTime = eVar.getEndTime();
        this.minEffectiveShowTime = eVar.getMinEffectiveShowTime();
        this.minEffectiveShowRatio = eVar.getMinEffectiveShowRatio();
        this.desc = cz.d(eVar.getDescription());
        this.title = cz.d(eVar.getTitle());
        this.dspName = cz.d(eVar.getDspName());
        this.dspLogo = eVar.getDspLogo();
        this.logo2Text = cz.d(eVar.u());
        this.label = cz.d(eVar.getLabel());
        this.source = cz.d(eVar.getLabel());
        this.clickBtnTxt = cz.d(eVar.getCta());
        this.adChoiceUrl = eVar.getAdChoiceUrl();
        this.ctrlExt = eVar.d();
        this.showAppElement = eVar.isShowAppElement();
        this.transparencyOpen = eVar.isTransparencyOpen();
        this.transparencyTplUrl = eVar.getTransparencyTplUrl();
        this.transparencySwitch = eVar.z();
        this.abilityDetailInfo = eVar.getAbilityDetailInfo();
        if (!bg.a(eVar.getAdCloseKeyWords())) {
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = eVar.getAdCloseKeyWords().iterator();
            while (it.hasNext()) {
                arrayList.add(cz.d(it.next()));
            }
            this.keywords = arrayList;
        }
        this.isGaussianBlur = eVar.isUseGaussianBlur();
        this.bannerRefSetting = eVar.ai();
        if (eVar.getAppInfo() != null) {
            this.app = new App(eVar.getAppInfo());
        }
        this.icon = new ImageInfo(eVar.getIcon());
        if (eVar.getVideoInfo() != null) {
            this.video = new Video(eVar.getVideoInfo());
        }
        if (!bg.a(eVar.getImageInfos())) {
            ArrayList arrayList2 = new ArrayList();
            for (com.huawei.openalliance.ad.inter.data.ImageInfo imageInfo : eVar.getImageInfos()) {
                if (imageInfo != null) {
                    arrayList2.add(new ImageInfo(imageInfo));
                }
            }
            this.imgList = arrayList2;
        }
        List<ImpEX> aj = eVar.aj();
        HashMap hashMap = new HashMap();
        if (!bg.a(aj)) {
            for (ImpEX impEX : aj) {
                hashMap.put(impEX.a(), impEX.b());
            }
        }
        this.ext = hashMap;
        if (bg.a(eVar.getAdvertiserInfo())) {
            return;
        }
        this.advertiserInfos = eVar.getAdvertiserInfo();
    }

    public H5Ad(d dVar) {
        this.apiVer = 2;
        if (dVar == null) {
            return;
        }
        this.requestId = dVar.f();
        this.uniqueId = dVar.getUniqueId();
        this.showId = dVar.getShowId();
        this.slotId = dVar.O();
        this.contentId = dVar.getContentId();
        this.taskId = dVar.getTaskId();
        this.rewardItem = dVar.B();
        this.ctrlExt = dVar.d();
        this.showAppElement = dVar.isShowAppElement();
        this.transparencyOpen = dVar.isTransparencyOpen();
        this.transparencyTplUrl = dVar.getTransparencyTplUrl();
        this.transparencySwitch = dVar.z();
        this.abilityDetailInfo = dVar.getAbilityDetailInfo();
        if (bg.a(dVar.getCompliance())) {
            return;
        }
        this.advertiserInfos = dVar.getCompliance();
    }

    public H5Ad() {
        this.apiVer = 2;
    }
}
