package com.huawei.openalliance.ad;

import com.google.flatbuffers.FlatBufferBuilder;
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
import java.util.List;

/* loaded from: classes9.dex */
public class eb {
    public static int[] k(List<AdSource> list, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                AdSource adSource = list.get(i);
                if (adSource != null) {
                    iArr[i] = AdSourceFb.createAdSourceFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(adSource.a())), flatBufferBuilder.createString(ec.a(adSource.b())), adSource.c());
                }
            }
        }
        return ec.a(iArr);
    }

    public static int[] j(List<Om> list, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                Om om = list.get(i);
                if (om != null) {
                    iArr[i] = OmFb.createOmFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(om.a())), flatBufferBuilder.createString(ec.a(om.b())), flatBufferBuilder.createString(ec.a(om.c())));
                }
            }
        }
        return ec.a(iArr);
    }

    public static int[] i(List<AdvertiserInfo> list, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                AdvertiserInfo advertiserInfo = list.get(i);
                if (advertiserInfo != null) {
                    iArr[i] = AdvertiserInfoFb.createAdvertiserInfoFb(flatBufferBuilder, advertiserInfo.getSeq().intValue(), flatBufferBuilder.createString(ec.a(advertiserInfo.getKey())), flatBufferBuilder.createString(ec.a(advertiserInfo.getValue())));
                }
            }
        }
        return ec.a(iArr);
    }

    public static int[] h(List<ImageInfo> list, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                iArr[i] = a(list.get(i), flatBufferBuilder);
            }
        }
        return ec.a(iArr);
    }

    public static int[] g(List<PermissionEntity> list, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                PermissionEntity permissionEntity = list.get(i);
                if (permissionEntity != null) {
                    iArr[i] = PermissionEntityFb.createPermissionEntityFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(permissionEntity.getName())), permissionEntity.getType());
                }
            }
        }
        return ec.a(iArr);
    }

    public static int[] f(List<ImpEX> list, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                ImpEX impEX = list.get(i);
                if (impEX != null) {
                    iArr[i] = ImpEXFb.createImpEXFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(impEX.a())), flatBufferBuilder.createString(ec.a(impEX.b())));
                }
            }
        }
        return ec.a(iArr);
    }

    public static int[] e(List<ContentExt> list, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                ContentExt contentExt = list.get(i);
                if (contentExt != null) {
                    iArr[i] = ContentExtFb.createContentExtFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(contentExt.a())), flatBufferBuilder.createString(ec.a(contentExt.b())));
                }
            }
        }
        return ec.a(iArr);
    }

    public static int[] d(List<FeedbackInfo> list, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                FeedbackInfo feedbackInfo = list.get(i);
                if (feedbackInfo != null) {
                    iArr[i] = FeedbackInfoFb.createFeedbackInfoFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(feedbackInfo.getLabel())), feedbackInfo.getType(), feedbackInfo.a());
                }
            }
        }
        return ec.a(iArr);
    }

    public static int[] c(List<Asset> list, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                Asset asset = list.get(i);
                if (asset != null) {
                    iArr[i] = AssetFb.createAssetFb(flatBufferBuilder, asset.a(), flatBufferBuilder.createString(ec.a(asset.b())), flatBufferBuilder.createString(ec.a(asset.h())), a(asset.c(), flatBufferBuilder), a(asset.d(), flatBufferBuilder), a(asset.e(), flatBufferBuilder), a(asset.f(), flatBufferBuilder), flatBufferBuilder.createString(ec.a(asset.g())));
                }
            }
        }
        return ec.a(iArr);
    }

    public static int[] b(List<MotionData> list, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                MotionData motionData = list.get(i);
                if (motionData != null) {
                    iArr[i] = MotionDataFb.createMotionDataFb(flatBufferBuilder, motionData.a(), flatBufferBuilder.createString(ec.a(motionData.g())), motionData.b(), motionData.c(), flatBufferBuilder.createString(ec.a(motionData.d())), motionData.e(), flatBufferBuilder.createString(ec.a(motionData.h())), motionData.f(), flatBufferBuilder.createString(ec.a(motionData.i())));
                }
            }
        }
        return ec.a(iArr);
    }

    public static int[] a(List<Monitor> list, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                Monitor monitor = list.get(i);
                if (monitor != null) {
                    iArr[i] = MonitorFb.createMonitorFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(monitor.a())), MonitorFb.createUrlVector(flatBufferBuilder, ec.a(monitor.b(), flatBufferBuilder)), monitor.c());
                }
            }
        }
        return ec.a(iArr);
    }

    public static int a(VideoInfo videoInfo, FlatBufferBuilder flatBufferBuilder) {
        if (videoInfo == null) {
            return 0;
        }
        return VideoInfoFb.createVideoInfoFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(videoInfo.getVideoDownloadUrl())), flatBufferBuilder.createString(ec.a(videoInfo.g())), videoInfo.getVideoDuration(), videoInfo.getVideoFileSize(), flatBufferBuilder.createString(ec.a(videoInfo.getVideoAutoPlay())), videoInfo.getAutoPlayNetwork(), flatBufferBuilder.createString(ec.a(videoInfo.getVideoAutoPlayWithSound())), videoInfo.getTimeBeforeVideoAutoPlay(), flatBufferBuilder.createString(ec.a(videoInfo.getSha256())), videoInfo.b(), flatBufferBuilder.createString(ec.a(videoInfo.getSoundSwitch())), videoInfo.getVideoPlayMode(), videoInfo.isCheckSha256(), videoInfo.c(), videoInfo.isBackFromFullScreen(), videoInfo.getAutoPlayAreaRatio(), videoInfo.getAutoStopPlayAreaRatio(), videoInfo.getDownloadNetwork(), videoInfo.getVideoRatio().floatValue(), videoInfo.e(), videoInfo.f(), videoInfo.d(), videoInfo.h(), flatBufferBuilder.createString(ec.a(videoInfo.i())));
    }

    public static int a(ImageInfo imageInfo, FlatBufferBuilder flatBufferBuilder) {
        if (imageInfo == null) {
            return 0;
        }
        return ImageInfoFb.createImageInfoFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(imageInfo.getUrl())), flatBufferBuilder.createString(ec.a(imageInfo.getOriginalUrl())), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getFileSize(), flatBufferBuilder.createString(ec.a(imageInfo.getSha256())), imageInfo.isCheckSha256(), flatBufferBuilder.createString(ec.a(imageInfo.getImageType())));
    }

    public static int a(BiddingInfo biddingInfo, FlatBufferBuilder flatBufferBuilder) {
        if (biddingInfo == null) {
            return 0;
        }
        return BiddingInfoFb.createBiddingInfoFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(biddingInfo.getCur())), biddingInfo.getPrice().floatValue(), flatBufferBuilder.createString(ec.a(biddingInfo.getLurl())), flatBufferBuilder.createString(ec.a(biddingInfo.getNurl())));
    }

    public static int a(AudioInfo audioInfo, FlatBufferBuilder flatBufferBuilder) {
        if (audioInfo == null) {
            return 0;
        }
        return AudioInfoFb.createAudioInfoFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(audioInfo.getUrl())), audioInfo.getDuration(), audioInfo.getFileSize(), flatBufferBuilder.createString(ec.a(audioInfo.getSha256())), flatBufferBuilder.createString(ec.a(audioInfo.getMime())));
    }

    public static int a(AppInfo appInfo, FlatBufferBuilder flatBufferBuilder) {
        if (appInfo == null) {
            return 0;
        }
        int[] g = g(appInfo.getPermissions(), flatBufferBuilder);
        return AppInfoFb.createAppInfoFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(appInfo.getAppName())), flatBufferBuilder.createString(ec.a(appInfo.getPackageName())), flatBufferBuilder.createString(ec.a(appInfo.getVersionCode())), flatBufferBuilder.createString(ec.a(appInfo.getVersionName())), flatBufferBuilder.createString(ec.a(appInfo.getIconUrl())), flatBufferBuilder.createString(ec.a(appInfo.getDownloadUrl())), flatBufferBuilder.createString(ec.a(appInfo.getSafeDownloadUrl())), appInfo.getFileSize(), flatBufferBuilder.createString(ec.a(appInfo.getSha256())), appInfo.isCheckSha256(), flatBufferBuilder.createString(ec.a(appInfo.getIntentUri())), AppInfoFb.createPermissionsVector(flatBufferBuilder, g), flatBufferBuilder.createString(ec.a(appInfo.getPriorInstallWay())), a(appInfo.b(), flatBufferBuilder), appInfo.isPermPromptForCard(), appInfo.isPermPromptForLanding(), appInfo.c(), flatBufferBuilder.createString(ec.a(appInfo.e())), appInfo.f(), flatBufferBuilder.createString(ec.a(appInfo.getUniqueId())), flatBufferBuilder.createString(ec.a(appInfo.getAppDesc())), appInfo.g(), appInfo.h(), flatBufferBuilder.createString(ec.a(appInfo.d())), flatBufferBuilder.createString(ec.a(appInfo.i())), flatBufferBuilder.createString(ec.a(appInfo.j())), flatBufferBuilder.createString(ec.a(appInfo.r())), flatBufferBuilder.createString(ec.a(appInfo.s())), appInfo.m(), appInfo.n(), flatBufferBuilder.createString(ec.a(appInfo.o())), flatBufferBuilder.createString(ec.a(appInfo.p())), appInfo.q(), appInfo.isAllowedNonWifiNetwork(), appInfo.I() != null ? appInfo.I().intValue() : 0, flatBufferBuilder.createString(ec.a(appInfo.v())), flatBufferBuilder.createString(ec.a(appInfo.w())), flatBufferBuilder.createString(ec.a(appInfo.getDeveloperName())), flatBufferBuilder.createString(ec.a(appInfo.t())), flatBufferBuilder.createString(ec.a(appInfo.x())), flatBufferBuilder.createString(ec.a(appInfo.y())), appInfo.z(), appInfo.A(), appInfo.B(), appInfo.C(), flatBufferBuilder.createString(ec.a(appInfo.D())), flatBufferBuilder.createString(ec.a(appInfo.E())), flatBufferBuilder.createString(ec.a(appInfo.F())), flatBufferBuilder.createString(ec.a(appInfo.G())), flatBufferBuilder.createString(ec.a(appInfo.H())), flatBufferBuilder.createString(ec.a(appInfo.getPermissionUrl())), flatBufferBuilder.createString(ec.a(appInfo.getAppDetailUrl())), flatBufferBuilder.createString(ec.a(appInfo.J())), flatBufferBuilder.createString(ec.a(appInfo.a())));
    }

    public static int a(Video video, FlatBufferBuilder flatBufferBuilder) {
        if (video == null) {
            return 0;
        }
        return VideoFb.createVideoFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(video.a())), video.b(), video.c(), video.d(), video.e(), flatBufferBuilder.createString(ec.a(video.f())), flatBufferBuilder.createString(ec.a(video.g())), video.h(), flatBufferBuilder.createString(ec.a(video.i())));
    }

    public static int a(Title title, FlatBufferBuilder flatBufferBuilder) {
        if (title == null) {
            return 0;
        }
        return TitleFb.createTitleFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(title.a())), title.b());
    }

    public static int a(ImageExt imageExt, FlatBufferBuilder flatBufferBuilder) {
        if (imageExt == null) {
            return 0;
        }
        return ImageExtFb.createImageExtFb(flatBufferBuilder, imageExt.a(), flatBufferBuilder.createString(ec.a(imageExt.b())), flatBufferBuilder.createString(ec.a(imageExt.c())), imageExt.d());
    }

    public static int a(Image image, FlatBufferBuilder flatBufferBuilder) {
        if (image == null) {
            return 0;
        }
        return ImageFb.createImageFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(image.a())), image.b(), image.c(), a(image.d(), flatBufferBuilder), flatBufferBuilder.createString(ec.a(image.e())));
    }

    public static int a(Data data, FlatBufferBuilder flatBufferBuilder) {
        if (data == null) {
            return 0;
        }
        return DataFb.createDataFb(flatBufferBuilder, data.a(), flatBufferBuilder.createString(ec.a(data.b())), data.c());
    }

    public static int a(TemplateData templateData, FlatBufferBuilder flatBufferBuilder) {
        if (templateData == null) {
            return 0;
        }
        return TemplateDataFb.createTemplateDataFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(templateData.a())), flatBufferBuilder.createString(ec.a(templateData.b())), TemplateDataFb.createMotionDataVector(flatBufferBuilder, b(templateData.c(), flatBufferBuilder)), flatBufferBuilder.createString(ec.a(templateData.d())));
    }

    public static int a(PromoteInfo promoteInfo, FlatBufferBuilder flatBufferBuilder) {
        if (promoteInfo == null) {
            return 0;
        }
        return PromoteInfoFb.createPromoteInfoFb(flatBufferBuilder, promoteInfo.getType(), flatBufferBuilder.createString(ec.a(promoteInfo.getName())));
    }

    public static int a(InstallConfig installConfig, FlatBufferBuilder flatBufferBuilder) {
        if (installConfig == null) {
            return 0;
        }
        return InstallConfigFb.createInstallConfigFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(installConfig.a())), flatBufferBuilder.createString(ec.a(installConfig.b())), flatBufferBuilder.createString(ec.a(installConfig.c())));
    }

    public static int a(DefaultTemplate defaultTemplate, FlatBufferBuilder flatBufferBuilder) {
        if (defaultTemplate == null) {
            return 0;
        }
        return DefaultTemplateFb.createDefaultTemplateFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(defaultTemplate.a())), defaultTemplate.b() != null ? defaultTemplate.b().intValue() : 0);
    }
}
