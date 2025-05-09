package com.huawei.watchface.videoedit.utils;

import com.huawei.watchface.videoedit.param.TemplateModel;
import com.huawei.watchface.videoedit.param.Videos;
import com.huawei.watchface.videoedit.sysc.Optional;

/* loaded from: classes9.dex */
public class MaterialUtil {
    private MaterialUtil() {
    }

    public static Optional<Videos> getVideoFromId(TemplateModel templateModel, String str) {
        if (templateModel == null || str == null) {
            return Optional.empty();
        }
        for (Videos videos : templateModel.getMaterials().getVideos()) {
            if (str.equals(videos.getId())) {
                return Optional.of(videos);
            }
        }
        return Optional.empty();
    }
}
