package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util;

import com.huawei.animationkit.computationalwallpaper.generator.OnWallpaperGenerateListener;
import com.huawei.animationkit.computationalwallpaper.generator.StaticWallpaper;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WearWatchFaceManager;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.WearFilterPreview;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.WearWatchFaceParam;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.secure.android.common.util.SafeString;
import defpackage.abo;
import defpackage.abv;
import defpackage.adh;
import defpackage.nrf;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* loaded from: classes9.dex */
public class WearWatchFaceGenerator {
    private static final String TAG = "WearWatchFaceGenerator";
    private static final String TAG_RELEASE = "R_WearWatchFaceGenerator";

    public static void createWearWatchFace(String str, final ResultCallback<List<WearFilterPreview>> resultCallback) {
        final String watchFaceRootDir = WearWatchFaceManager.getInstance().getWatchFaceRootDir();
        try {
            createWearResourcesFile(WearWatchFaceManager.getInstance().getWatchFaceResourcePath() + WatchFaceConstant.RESOURCES_ASSETS + File.separator, WearWatchFaceManager.getInstance().getCustomResourcePath());
            adh.c().d(new OnWallpaperGenerateListener() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WearWatchFaceGenerator$$ExternalSyntheticLambda0
                @Override // com.huawei.animationkit.computationalwallpaper.generator.OnWallpaperGenerateListener
                public final void onWallpaperGenerate(List list, abv abvVar) {
                    WearWatchFaceGenerator.lambda$createWearWatchFace$0(ResultCallback.this, watchFaceRootDir, list, abvVar);
                }
            });
            adh.c().fS_(nrf.cHA_(str));
        } catch (IOException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "createWearWatchFace exception:", ExceptionUtils.d(e));
            resultCallback.onFailure(new Throwable(e.getMessage()));
        }
    }

    static /* synthetic */ void lambda$createWearWatchFace$0(ResultCallback resultCallback, String str, List list, abv abvVar) {
        if (abvVar != null) {
            ReleaseLogUtil.c(TAG_RELEASE, "createWearWatchFace exception:", ExceptionUtils.d(abvVar));
            resultCallback.onFailure(new Throwable(abvVar.getMessage()));
            return;
        }
        if (list == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "createWearWatchFace() list is null");
            resultCallback.onFailure(new Throwable("list is null"));
            return;
        }
        File file = WatchFaceUtil.getFile(str);
        if (file == null || !file.exists()) {
            ReleaseLogUtil.d(TAG_RELEASE, "createWearWatchFace() file does not exist.");
            resultCallback.onFailure(new Throwable("file does not exist"));
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < list.size(); i++) {
            try {
                try {
                    StaticWallpaper staticWallpaper = (StaticWallpaper) list.get(i);
                    WearFilterPreview wearFilterPreview = new WearFilterPreview();
                    String substring = SafeString.substring(new BigInteger(SafeString.replace(UUID.randomUUID().toString(), Constants.LINK, ""), 16).toString(), 0, 10);
                    wearFilterPreview.setUniqueId(substring);
                    staticWallpaper.save(file, substring + "_");
                    wearFilterPreview.setPreviewName(file + File.separator + substring + "_" + WatchFaceConstant.PREVIEW_RES + ".png");
                    wearFilterPreview.setOrderIndex(String.valueOf(i));
                    arrayList.add(wearFilterPreview);
                } catch (Exception e) {
                    ReleaseLogUtil.d(TAG_RELEASE, "createWearWatchFace exception:", ExceptionUtils.d(e));
                    resultCallback.onFailure(new Throwable(e.getMessage()));
                }
            } finally {
                resultCallback.onSuccess(arrayList);
            }
        }
        LogUtil.a(TAG, "createWearWatchPhoto() wearFilterPreviewList:", Integer.valueOf(arrayList.size()));
    }

    private static void createWearResourcesFile(String str, String str2) throws IOException {
        boolean z;
        File file = WatchFaceUtil.getFile(str2);
        if (file == null || !file.exists()) {
            file = WatchFaceUtil.getFile(str);
            z = false;
        } else {
            z = true;
        }
        LogUtil.a(TAG, "createWearResourcesFile isCustom:", Boolean.valueOf(z));
        if (file == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "createWearResourcesFile file null");
            return;
        }
        if (z) {
            if (file.isDirectory()) {
                if (CollectionUtils.b(file.list(new FilenameFilter() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WearWatchFaceGenerator$$ExternalSyntheticLambda1
                    @Override // java.io.FilenameFilter
                    public final boolean accept(File file2, String str3) {
                        boolean equals;
                        equals = WatchFaceConstant.TEMPLATES_CIRCLE_NAME.equals(str3);
                        return equals;
                    }
                }))) {
                    try {
                        LogUtil.a(TAG, "need copy default");
                        WatchFaceUtil.copyResources(str, file);
                    } catch (IOException e) {
                        ReleaseLogUtil.c(TAG_RELEASE, "createWearResourcesFile copy IOException :", ExceptionUtils.d(e));
                    }
                } else {
                    LogUtil.a(TAG, "no need copy default");
                }
            }
        } else {
            ReleaseLogUtil.e(TAG_RELEASE, "createWearResourcesFile() default file exists：", Boolean.valueOf(file.exists()), ", isDirectory:", Boolean.valueOf(file.isDirectory()));
            if (file.exists() && file.isDirectory()) {
                ReleaseLogUtil.e(TAG_RELEASE, "createWearResourcesFile resourceFileName :", Boolean.valueOf(CollectionUtils.b(file.list(new FilenameFilter() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WearWatchFaceGenerator$$ExternalSyntheticLambda2
                    @Override // java.io.FilenameFilter
                    public final boolean accept(File file2, String str3) {
                        boolean equals;
                        equals = WatchFaceConstant.TEMPLATES_CIRCLE_NAME.equals(str3);
                        return equals;
                    }
                }))));
            }
        }
        abo.a(file.getCanonicalPath());
        abo.b(file.getCanonicalPath());
        WearWatchFaceParam wearWatchFaceParam = WearWatchFaceManager.getInstance().getWearWatchFaceParam();
        if (wearWatchFaceParam == null) {
            LogUtil.h(TAG, "wearWatchFaceParam is null");
            return;
        }
        String str3 = WearWatchFaceManager.getInstance().isNotNeedCheckCustomTemplate(wearWatchFaceParam.getWatchFaceVersion()) ? WatchFaceConstant.TEMPLATES_CIRCLE_NAME : WatchFaceConstant.TEMPLATES_CUSTOM_NAME;
        LogUtil.a(TAG, "createWearResourcesFile template:", str3);
        abo.e(str3);
    }
}
