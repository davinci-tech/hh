package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.beans.metadata.AdSource;
import com.huawei.openalliance.ad.beans.metadata.ContentExt;
import com.huawei.openalliance.ad.beans.metadata.DefaultTemplate;
import com.huawei.openalliance.ad.beans.metadata.ImpEX;
import com.huawei.openalliance.ad.beans.metadata.InstallConfig;
import com.huawei.openalliance.ad.beans.metadata.Monitor;
import com.huawei.openalliance.ad.beans.metadata.Om;
import com.huawei.openalliance.ad.beans.metadata.PromoteInfo;
import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.beans.metadata.v3.MotionData;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateData;
import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.Data;
import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.Image;
import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.ImageExt;
import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.Title;
import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.Video;
import com.huawei.openalliance.ad.inter.data.AdvertiserInfo;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.AudioInfo;
import com.huawei.openalliance.ad.inter.data.BiddingInfo;
import com.huawei.openalliance.ad.inter.data.FeedbackInfo;
import com.huawei.openalliance.ad.inter.data.ImageInfo;
import com.huawei.openalliance.ad.inter.data.PermissionEntity;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.inter.data.fb.AdSourceFb;
import com.huawei.openalliance.ad.inter.data.fb.AdvertiserInfoFb;
import com.huawei.openalliance.ad.inter.data.fb.AppInfoFb;
import com.huawei.openalliance.ad.inter.data.fb.AssetFb;
import com.huawei.openalliance.ad.inter.data.fb.AudioInfoFb;
import com.huawei.openalliance.ad.inter.data.fb.BiddingInfoFb;
import com.huawei.openalliance.ad.inter.data.fb.ContentExtFb;
import com.huawei.openalliance.ad.inter.data.fb.DataFb;
import com.huawei.openalliance.ad.inter.data.fb.DefaultTemplateFb;
import com.huawei.openalliance.ad.inter.data.fb.FeedbackInfoFb;
import com.huawei.openalliance.ad.inter.data.fb.ImageExtFb;
import com.huawei.openalliance.ad.inter.data.fb.ImageFb;
import com.huawei.openalliance.ad.inter.data.fb.ImageInfoFb;
import com.huawei.openalliance.ad.inter.data.fb.ImpEXFb;
import com.huawei.openalliance.ad.inter.data.fb.InstallConfigFb;
import com.huawei.openalliance.ad.inter.data.fb.MonitorFb;
import com.huawei.openalliance.ad.inter.data.fb.MotionDataFb;
import com.huawei.openalliance.ad.inter.data.fb.OmFb;
import com.huawei.openalliance.ad.inter.data.fb.PermissionEntityFb;
import com.huawei.openalliance.ad.inter.data.fb.PromoteInfoFb;
import com.huawei.openalliance.ad.inter.data.fb.TemplateDataFb;
import com.huawei.openalliance.ad.inter.data.fb.TitleFb;
import com.huawei.openalliance.ad.inter.data.fb.VideoFb;
import com.huawei.openalliance.ad.inter.data.fb.VideoInfoFb;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class dz {
    public static List<PermissionEntity> a(PermissionEntityFb.Vector vector) {
        if (vector == null || vector.length() == 0) {
            return null;
        }
        int length = vector.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            PermissionEntity permissionEntity = new PermissionEntity();
            PermissionEntityFb permissionEntityFb = vector.get(i);
            if (permissionEntityFb != null) {
                permissionEntity.a(permissionEntityFb.name());
                permissionEntity.a(permissionEntityFb.type());
                arrayList.add(permissionEntity);
            }
        }
        return arrayList;
    }

    public static List<Om> a(OmFb.Vector vector) {
        if (vector == null || vector.length() == 0) {
            return null;
        }
        int length = vector.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            OmFb omFb = vector.get(i);
            if (omFb != null) {
                Om om = new Om();
                om.b(omFb.resourceUrl());
                om.a(omFb.vendorKey());
                om.c(omFb.verificationParameters());
                arrayList.add(om);
            }
        }
        return arrayList;
    }

    public static List<MotionData> a(MotionDataFb.Vector vector) {
        if (vector == null || vector.length() == 0) {
            return null;
        }
        int length = vector.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            MotionData motionData = new MotionData();
            MotionDataFb motionDataFb = vector.get(i);
            if (motionDataFb != null) {
                motionData.a(motionDataFb.dataId());
                motionData.b(motionDataFb.url());
                motionData.a(motionDataFb.width());
                motionData.b(motionDataFb.height());
                motionData.a(motionDataFb.format());
                motionData.c(motionDataFb.size());
                motionData.c(motionDataFb.sha256());
                motionData.d(motionDataFb.duration());
                motionData.d(motionDataFb.filePath());
                arrayList.add(motionData);
            }
        }
        return arrayList;
    }

    public static List<Monitor> a(MonitorFb.Vector vector) {
        if (vector == null || vector.length() == 0) {
            return null;
        }
        int length = vector.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            Monitor monitor = new Monitor();
            MonitorFb monitorFb = vector.get(i);
            if (monitorFb != null) {
                monitor.a(monitorFb.eventType());
                monitor.a(monitorFb.retryFlag());
                monitor.a(ec.a(monitorFb.urlVector()));
                arrayList.add(monitor);
            }
        }
        return arrayList;
    }

    public static List<ImpEX> a(ImpEXFb.Vector vector) {
        if (vector == null || vector.length() == 0) {
            return null;
        }
        int length = vector.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            ImpEX impEX = new ImpEX();
            ImpEXFb impEXFb = vector.get(i);
            if (impEXFb != null) {
                impEX.a(impEXFb.key());
                impEX.b(impEXFb.value());
                arrayList.add(impEX);
            }
        }
        return arrayList;
    }

    public static List<ImageInfo> a(ImageInfoFb.Vector vector) {
        if (vector == null || vector.length() == 0) {
            return null;
        }
        int length = vector.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(a(vector.get(i)));
        }
        return arrayList;
    }

    public static List<FeedbackInfo> a(FeedbackInfoFb.Vector vector) {
        if (vector == null || vector.length() == 0) {
            return null;
        }
        int length = vector.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            FeedbackInfo feedbackInfo = new FeedbackInfo();
            FeedbackInfoFb feedbackInfoFb = vector.get(i);
            if (feedbackInfoFb != null) {
                feedbackInfo.a(feedbackInfoFb.id());
                feedbackInfo.a(feedbackInfoFb.label());
                feedbackInfo.a(feedbackInfoFb.type());
                arrayList.add(feedbackInfo);
            }
        }
        return arrayList;
    }

    public static List<ContentExt> a(ContentExtFb.Vector vector) {
        if (vector == null || vector.length() == 0) {
            return null;
        }
        int length = vector.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            ContentExt contentExt = new ContentExt();
            ContentExtFb contentExtFb = vector.get(i);
            if (contentExtFb != null) {
                contentExt.a(contentExtFb.key());
                contentExt.b(contentExtFb.value());
                arrayList.add(contentExt);
            }
        }
        return arrayList;
    }

    public static List<Asset> a(AssetFb.Vector vector) {
        if (vector == null || vector.length() == 0) {
            return null;
        }
        int length = vector.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            Asset asset = new Asset();
            AssetFb assetFb = vector.get(i);
            if (assetFb != null) {
                asset.a(assetFb.id());
                asset.a(assetFb.alias());
                asset.c(assetFb.context());
                asset.a(a(assetFb.data()));
                asset.a(a(assetFb.img()));
                asset.a(a(assetFb.video()));
                asset.a(a(assetFb.title()));
                asset.b(assetFb.filePath());
                arrayList.add(asset);
            }
        }
        return arrayList;
    }

    public static List<AdvertiserInfo> a(AdvertiserInfoFb.Vector vector) {
        if (vector == null || vector.length() == 0) {
            return null;
        }
        int length = vector.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            AdvertiserInfoFb advertiserInfoFb = vector.get(i);
            if (advertiserInfoFb != null) {
                AdvertiserInfo advertiserInfo = new AdvertiserInfo();
                advertiserInfo.a(advertiserInfoFb.key());
                advertiserInfo.a(Integer.valueOf(advertiserInfoFb.seq()));
                advertiserInfo.b(advertiserInfoFb.value());
                arrayList.add(advertiserInfo);
            }
        }
        return arrayList;
    }

    public static List<AdSource> a(AdSourceFb.Vector vector) {
        if (vector == null || vector.length() == 0) {
            return null;
        }
        int length = vector.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            AdSourceFb adSourceFb = vector.get(i);
            if (adSourceFb != null) {
                AdSource adSource = new AdSource();
                adSource.a(adSourceFb.displayPosition());
                adSource.b(adSourceFb.dspLogo());
                adSource.a(adSourceFb.dspName());
                arrayList.add(adSource);
            }
        }
        return arrayList;
    }

    public static VideoInfo a(VideoInfoFb videoInfoFb) {
        if (videoInfoFb == null) {
            return null;
        }
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.a(videoInfoFb.videoDownloadUrl());
        videoInfo.f(videoInfoFb.originUrl());
        videoInfo.a(videoInfoFb.videoDuration());
        videoInfo.b(videoInfoFb.videoFileSize());
        videoInfo.b(videoInfoFb.videoAutoPlay());
        videoInfo.i(videoInfoFb.autoPlayNetwork());
        videoInfo.c(videoInfoFb.videoAutoPlayWithSound());
        videoInfo.c(videoInfoFb.timeBeforeVideoAutoPlay());
        videoInfo.d(videoInfoFb.sha256());
        videoInfo.e(videoInfoFb.playProgress());
        videoInfo.e(videoInfoFb.soundSwitch());
        videoInfo.d(videoInfoFb.videoPlayMode());
        videoInfo.a(videoInfoFb.checkSha256());
        videoInfo.b(videoInfoFb.needContinueAutoPlay());
        videoInfo.c(videoInfoFb.backFromFullScreen());
        videoInfo.f(videoInfoFb.autoPlayAreaRatio());
        videoInfo.g(videoInfoFb.autoStopPlayAreaRatio());
        videoInfo.h(videoInfoFb.downloadNetwork());
        videoInfo.a(Float.valueOf(videoInfoFb.videoRatio()));
        videoInfo.d(videoInfoFb.showSoundIcon());
        videoInfo.e(videoInfoFb.directReturnVideoAd());
        videoInfo.a(videoInfoFb.splashSwitchTime());
        videoInfo.j(videoInfoFb.customExposureType());
        videoInfo.g(videoInfoFb.useTemplate());
        return videoInfo;
    }

    public static ImageInfo a(ImageInfoFb imageInfoFb) {
        if (imageInfoFb == null) {
            return null;
        }
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.a(imageInfoFb.checkSha256());
        imageInfo.c(imageInfoFb.fileSize());
        imageInfo.a(imageInfoFb.height());
        imageInfo.b(imageInfoFb.width());
        imageInfo.d(imageInfoFb.originalUrl());
        imageInfo.c(imageInfoFb.url());
        imageInfo.b(imageInfoFb.sha256());
        imageInfo.a(imageInfoFb.imageType());
        return imageInfo;
    }

    public static BiddingInfo a(BiddingInfoFb biddingInfoFb) {
        if (biddingInfoFb == null) {
            return null;
        }
        BiddingInfo.a aVar = new BiddingInfo.a();
        aVar.a(biddingInfoFb.cur());
        aVar.a(Float.valueOf(biddingInfoFb.price()));
        aVar.setLurl(biddingInfoFb.lurl());
        aVar.b(biddingInfoFb.nurl());
        return aVar.a();
    }

    public static AudioInfo a(AudioInfoFb audioInfoFb) {
        if (audioInfoFb == null) {
            return null;
        }
        AudioInfo audioInfo = new AudioInfo();
        audioInfo.a(audioInfoFb.url());
        audioInfo.a(audioInfoFb.duration());
        audioInfo.b(audioInfoFb.fileSize());
        audioInfo.b(audioInfoFb.sha256());
        audioInfo.c(audioInfoFb.mime());
        return audioInfo;
    }

    public static AppInfo a(AppInfoFb appInfoFb) {
        if (appInfoFb == null) {
            return null;
        }
        AppInfo appInfo = new AppInfo();
        appInfo.l(appInfoFb.appName());
        appInfo.a(appInfoFb.packageName());
        appInfo.b(appInfoFb.versionCode());
        appInfo.c(appInfoFb.versionName());
        appInfo.d(appInfoFb.iconUrl());
        appInfo.e(appInfoFb.downloadUrl());
        appInfo.j(appInfoFb.safeDownloadUrl());
        appInfo.a(appInfoFb.fileSize());
        appInfo.i(appInfoFb.sha256());
        appInfo.c(appInfoFb.checkSha256());
        appInfo.k(appInfoFb.intentUri());
        appInfo.b(a(appInfoFb.permissionsVector()));
        appInfo.o(appInfoFb.priorInstallWay());
        appInfo.a(a(appInfoFb.installConfig()));
        appInfo.a(appInfoFb.permPromptForCard());
        appInfo.b(appInfoFb.permPromptForLanding());
        appInfo.a(appInfoFb.popUpAfterInstallNew());
        appInfo.r(appInfoFb.channelInfo());
        appInfo.b(appInfoFb.channelInfoSaveLimit());
        appInfo.s(appInfoFb.uniqueId());
        appInfo.m(appInfoFb.appDesc());
        appInfo.c(appInfoFb.noAlertTime());
        appInfo.d(appInfoFb.trafficReminder());
        appInfo.q(appInfoFb.popUpAfterInstallText());
        appInfo.t(appInfoFb.dlBtnText());
        appInfo.u(appInfoFb.afDlBtnText());
        appInfo.z(appInfoFb.intent());
        appInfo.A(appInfoFb.intentPackage());
        appInfo.e(appInfoFb.popNotify());
        appInfo.f(appInfoFb.fullScrnNotify());
        appInfo.x(appInfoFb.fullScrnNotifyText());
        appInfo.y(appInfoFb.insActvNotifyBtnText());
        appInfo.g(appInfoFb.insActvNotifyCfg());
        appInfo.c(Integer.valueOf(appInfoFb.hasPermissions()));
        appInfo.C(appInfoFb.appLanguage());
        appInfo.D(appInfoFb.appCountry());
        appInfo.n(appInfoFb.developerName());
        appInfo.B(appInfoFb.nextInstallWays());
        appInfo.E(appInfoFb.curInstallWay());
        appInfo.F(appInfoFb.actName());
        appInfo.h(appInfoFb.appType());
        appInfo.i(appInfoFb.autoOpenAfterInstall());
        appInfo.b(appInfoFb.allAreaPopDelay());
        appInfo.j(appInfoFb.popUpStyle());
        appInfo.G(appInfoFb.installPermiText());
        appInfo.H(appInfoFb.pureModeText());
        appInfo.I(appInfoFb.installPureModeText());
        appInfo.J(appInfoFb.contiBtn());
        appInfo.K(appInfoFb.reservedPkgName());
        appInfo.f(appInfoFb.permissionUrl());
        appInfo.g(appInfoFb.appDetailsUrl());
        appInfo.h(appInfoFb.privacyUrl());
        appInfo.p(appInfoFb.contentInstallBtnAction());
        return appInfo;
    }

    public static Video a(VideoFb videoFb) {
        if (videoFb == null) {
            return null;
        }
        Video video = new Video();
        video.c(videoFb.checkSha256Flag());
        video.a(videoFb.url());
        video.a(videoFb.width());
        video.b(videoFb.height());
        video.a(videoFb.duration());
        video.b(videoFb.size());
        video.b(videoFb.sha256());
        video.c(videoFb.format());
        video.d(videoFb.localPath());
        return video;
    }

    public static Title a(TitleFb titleFb) {
        if (titleFb == null) {
            return null;
        }
        Title title = new Title();
        title.a(titleFb.len());
        title.a(titleFb.text());
        return title;
    }

    public static ImageExt a(ImageExtFb imageExtFb) {
        if (imageExtFb == null) {
            return null;
        }
        ImageExt imageExt = new ImageExt();
        imageExt.a(imageExtFb.checkSha256Flag());
        imageExt.b(imageExtFb.format());
        imageExt.a(imageExtFb.sha256());
        imageExt.a(imageExtFb.size());
        return imageExt;
    }

    public static Image a(ImageFb imageFb) {
        if (imageFb == null) {
            return null;
        }
        Image image = new Image();
        image.a(a(imageFb.ext()));
        image.b(imageFb.height());
        image.a(imageFb.width());
        image.b(imageFb.localPath());
        image.a(imageFb.url());
        return image;
    }

    public static Data a(DataFb dataFb) {
        if (dataFb == null) {
            return null;
        }
        Data data = new Data();
        data.b(dataFb.len());
        data.a(dataFb.type());
        data.a(dataFb.value());
        return data;
    }

    public static TemplateData a(TemplateDataFb templateDataFb) {
        if (templateDataFb == null) {
            return null;
        }
        TemplateData templateData = new TemplateData();
        templateData.a(templateDataFb.templateContext());
        templateData.b(templateDataFb.motions());
        templateData.a(a(templateDataFb.motionDataVector()));
        templateData.c(templateDataFb.componentContext());
        return templateData;
    }

    public static PromoteInfo a(PromoteInfoFb promoteInfoFb) {
        if (promoteInfoFb == null) {
            return null;
        }
        PromoteInfo promoteInfo = new PromoteInfo();
        promoteInfo.a(promoteInfoFb.type());
        promoteInfo.a(promoteInfoFb.name());
        return promoteInfo;
    }

    public static InstallConfig a(InstallConfigFb installConfigFb) {
        if (installConfigFb == null) {
            return null;
        }
        InstallConfig installConfig = new InstallConfig();
        installConfig.b(installConfigFb.appBtnInstallWay());
        installConfig.a(installConfigFb.creativeInstallWay());
        installConfig.c(installConfigFb.contentBtnInstallWay());
        return installConfig;
    }

    public static DefaultTemplate a(DefaultTemplateFb defaultTemplateFb) {
        if (defaultTemplateFb == null) {
            return null;
        }
        DefaultTemplate defaultTemplate = new DefaultTemplate();
        defaultTemplate.a(defaultTemplateFb.defTptId());
        defaultTemplate.a(Integer.valueOf(defaultTemplateFb.tptFcCtl()));
        return defaultTemplate;
    }
}
