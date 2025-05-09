package com.huawei.hms.update.ui;

import android.content.Context;
import com.huawei.hms.utils.ResourceLoaderUtil;

/* loaded from: classes9.dex */
public class ButtonConfig {

    /* renamed from: a, reason: collision with root package name */
    int f6083a;
    int b;
    int c;
    int d;
    int e;
    Level f;

    enum Level {
        STRONG,
        ERROR,
        NORMAL
    }

    private ButtonConfig() {
    }

    public static ButtonConfig createDefault(Context context) {
        ButtonConfig buttonConfig = new ButtonConfig();
        if (context != null) {
            buttonConfig.f6083a = HwDialogUtil.a(context, ResourceLoaderUtil.getColorId("hw_cloud_dialog_button_pressed"));
            buttonConfig.b = HwDialogUtil.a(context, ResourceLoaderUtil.getColorId("hw_cloud_dialog_button_normal"));
            buttonConfig.c = HwDialogUtil.a(context, ResourceLoaderUtil.getColorId("hw_cloud_dialog_button_text_color"));
            buttonConfig.d = HwDialogUtil.a(context, ResourceLoaderUtil.getColorId("hw_cloud_dialog_button_text_color"));
            buttonConfig.e = context.getResources().getDimensionPixelSize(ResourceLoaderUtil.getDimenId("hw_cloud_dialog_button_text_size"));
        }
        buttonConfig.f = Level.NORMAL;
        return buttonConfig;
    }

    public static ButtonConfig createWatch(Context context) {
        ButtonConfig buttonConfig = new ButtonConfig();
        if (context != null) {
            buttonConfig.f6083a = HwDialogUtil.a(context, ResourceLoaderUtil.getColorId("hw_cloud_watch_dialog_button_pressed"));
            buttonConfig.b = HwDialogUtil.a(context, ResourceLoaderUtil.getColorId("hw_cloud_watch_dialog_button_normal"));
            buttonConfig.c = HwDialogUtil.a(context, ResourceLoaderUtil.getColorId("hw_cloud_watch_dialog_button_text_color"));
            buttonConfig.d = HwDialogUtil.a(context, ResourceLoaderUtil.getColorId("hw_cloud_watch_dialog_button_text_color"));
            buttonConfig.e = context.getResources().getDimensionPixelSize(ResourceLoaderUtil.getDimenId("hw_cloud_dialog_button_text_size"));
        }
        buttonConfig.f = Level.NORMAL;
        return buttonConfig;
    }
}
