package com.huawei.openalliance.ad;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/* loaded from: classes9.dex */
public class tk {
    public static void a(Context context, int i, com.huawei.openalliance.ad.views.h hVar, ImageView imageView, int i2, int i3) {
        int a2;
        int a3;
        ho.b("PPSDialogUtil", "getRealOrientation orientation %s", Integer.valueOf(i));
        if (context == null || hVar == null || imageView == null) {
            ho.c("PPSDialogUtil", "param is invalid, return");
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) hVar.getLayoutParams();
        int abs = Math.abs((int) imageView.getX());
        int a4 = com.huawei.openalliance.ad.utils.ao.a(context, 36.0f);
        int i4 = (a4 >> 1) + abs;
        double d = a4 * 0.5d;
        int viewWidthPercent = (int) ((i2 * (1.0f - hVar.getViewWidthPercent()) * 0.5d) + com.huawei.openalliance.ad.utils.ao.a(context, 16.0f) + d);
        int viewWidthPercent2 = (int) (((i2 * ((hVar.getViewWidthPercent() * 0.5d) + 0.5d)) - com.huawei.openalliance.ad.utils.ao.a(context, 16.0f)) - d);
        ho.a("PPSDialogUtil", "locationX: %s, locationX2: %s", Integer.valueOf(viewWidthPercent), Integer.valueOf(viewWidthPercent2));
        ho.a("PPSDialogUtil", "curImgX: %s, curImgWidth: %s, curImgCenter: %s", Integer.valueOf(abs), Integer.valueOf(a4), Integer.valueOf(i4));
        if (1 != i && 9 != i) {
            layoutParams.removeRule(14);
            hVar.setLayoutParams(layoutParams);
            if (i4 < i2 / 3) {
                a3 = com.huawei.openalliance.ad.utils.ao.a(context, 16.0f);
            } else if (i4 < (i2 * 2) / 3) {
                a2 = i4 - (hVar.getViewWith() >> 1);
                hVar.setPaddingStart(a2 - com.huawei.openalliance.ad.utils.ao.a(context, i3));
            } else {
                abs = abs + a4 + com.huawei.openalliance.ad.utils.ao.a(context, 16.0f);
                a3 = hVar.getViewWith();
            }
            a2 = abs - a3;
            hVar.setPaddingStart(a2 - com.huawei.openalliance.ad.utils.ao.a(context, i3));
        }
        if (i4 < viewWidthPercent) {
            ho.a("PPSDialogUtil", "curImgCenter < locationX");
            layoutParams.removeRule(14);
            hVar.setLayoutParams(layoutParams);
            a3 = com.huawei.openalliance.ad.utils.ao.a(context, 16.0f);
            a2 = abs - a3;
            hVar.setPaddingStart(a2 - com.huawei.openalliance.ad.utils.ao.a(context, i3));
        }
        if (i4 <= viewWidthPercent2) {
            ho.a("PPSDialogUtil", "locationX =< curImgCenter =< locationX2");
            layoutParams.addRule(14);
            hVar.setLayoutParams(layoutParams);
        } else {
            ho.a("PPSDialogUtil", "curImgCenter > locationX2");
            layoutParams.removeRule(14);
            hVar.setLayoutParams(layoutParams);
            a2 = ((abs + a4) + com.huawei.openalliance.ad.utils.ao.a(context, 16.0f)) - hVar.getViewWith();
            ho.a("PPSDialogUtil", "paddingStart: %s", Integer.valueOf(a2));
            hVar.setPaddingStart(a2 - com.huawei.openalliance.ad.utils.ao.a(context, i3));
        }
    }

    public static void a(Context context, int i, com.huawei.openalliance.ad.views.h hVar, ImageView imageView, int i2) {
        a(context, i, hVar, imageView, i2, 0);
    }
}
