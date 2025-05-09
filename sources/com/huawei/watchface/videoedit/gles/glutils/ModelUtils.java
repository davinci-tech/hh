package com.huawei.watchface.videoedit.gles.glutils;

import android.content.Context;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.videoedit.param.CanvasConfig;
import com.huawei.watchface.videoedit.param.TemplateModel;
import com.huawei.watchface.videoedit.utils.ScreenUtils;
import com.huawei.watchface.videoedit.utils.Utils;

/* loaded from: classes9.dex */
public class ModelUtils {
    public static final int INVALID_PAUSE_TIME = -1;
    private static final int SAVE_VIDEO_HEIGH = 1920;
    private static final int SAVE_VIDEO_WIDTH = 1080;
    private static final String TAG = "ModelUtils";

    private ModelUtils() {
    }

    public static int[] getModelHeightAndWidth(Context context, TemplateModel templateModel) {
        int width;
        int height;
        int i;
        int i2 = 1080;
        int[] iArr = {1080, 1920};
        if (templateModel.getCanvasConfig().isSingleVideo()) {
            iArr[0] = templateModel.getCanvasConfig().getSaveWidth();
            iArr[1] = templateModel.getCanvasConfig().getSaveHeight();
            HwLog.i(TAG, "getModelHeightAndWidth: " + iArr[0] + "==" + iArr[1]);
            return iArr;
        }
        String ratio = templateModel.getCanvasConfig().getRatio();
        String[] split = ratio.split(":");
        if (split.length == 2) {
            width = Utils.parseIntSafely(split[0], 1080);
            height = Utils.parseIntSafely(split[1], 1920);
        } else if (context != null && CanvasConfig.FULL_CONFIG.equals(ratio)) {
            templateModel.getCanvasConfig().setWidth(ScreenUtils.getDeviceWidth(context));
            templateModel.getCanvasConfig().setHeight(ScreenUtils.getDeviceHeight(context));
            width = templateModel.getCanvasConfig().getWidthResolution();
            height = templateModel.getCanvasConfig().getHeightResolution();
        } else {
            width = templateModel.getCanvasConfig().getWidth();
            height = templateModel.getCanvasConfig().getHeight();
        }
        float f = width / height;
        if (f > 1.0f) {
            i2 = (int) (1080 * f);
            i = 1080;
        } else {
            i = (int) (1080 / f);
        }
        iArr[0] = i2;
        iArr[1] = i;
        return iArr;
    }
}
