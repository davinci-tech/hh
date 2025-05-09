package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import com.huawei.health.R;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.dk;
import com.huawei.openalliance.ad.views.AppDownloadButtonStyle;

/* loaded from: classes5.dex */
public class ExtandAppDownloadButtonStyleHm extends AppDownloadButtonStyle {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v24, types: [android.graphics.drawable.Drawable] */
    public ExtandAppDownloadButtonStyleHm(Context context) {
        super(context);
        AppDownloadButtonStyle.Style style;
        LayerDrawable layerDrawable;
        boolean n = ao.n(context);
        this.normalStyle.setBackground(context.getResources().getDrawable(n ? R.drawable._2131428541_res_0x7f0b04bd : R.drawable._2131428540_res_0x7f0b04bc));
        this.normalStyle.setTextColor(context.getResources().getColor(R.color._2131297944_res_0x7f090698));
        LayerDrawable layerDrawable2 = (LayerDrawable) dk.b(context, n ? R.drawable._2131428537_res_0x7f0b04b9 : R.drawable._2131428536_res_0x7f0b04b8);
        Drawable findDrawableByLayerId = layerDrawable2.findDrawableByLayerId(android.R.id.progress);
        if (findDrawableByLayerId instanceof ClipDrawable) {
            PPSHwRoundRectEclipseClipDrawable pPSHwRoundRectEclipseClipDrawable = new PPSHwRoundRectEclipseClipDrawable(findDrawableByLayerId, 17, 1);
            layerDrawable2.mutate();
            layerDrawable2.setDrawableByLayerId(android.R.id.progress, pPSHwRoundRectEclipseClipDrawable);
            layerDrawable = layerDrawable2;
            style = this.processingStyle;
        } else {
            ho.c("ExtandAppDownloadButtonStyleHm", "not clipDrawable");
            int i = n ? R.drawable._2131428535_res_0x7f0b04b7 : R.drawable._2131428534_res_0x7f0b04b6;
            AppDownloadButtonStyle.Style style2 = this.processingStyle;
            layerDrawable = dk.b(context, i);
            style = style2;
        }
        style.setBackground(layerDrawable);
        this.processingStyle.setTextColor(context.getResources().getColor(R.color._2131297957_res_0x7f0906a5));
        LayerDrawable layerDrawable3 = (LayerDrawable) dk.b(context, n ? R.drawable._2131428533_res_0x7f0b04b5 : R.drawable._2131428532_res_0x7f0b04b4);
        if (layerDrawable3.findDrawableByLayerId(android.R.id.progress) instanceof ClipDrawable) {
            PPSFlickerDrawable pPSFlickerDrawable = new PPSFlickerDrawable(ao.a(context, n ? 20 : 18));
            layerDrawable3.mutate();
            layerDrawable3.setDrawableByLayerId(android.R.id.progress, pPSFlickerDrawable);
            this.installingStyle.setBackground(layerDrawable3);
            pPSFlickerDrawable.a();
        } else {
            ho.c("ExtandAppDownloadButtonStyleHm", "not clipDrawable");
            this.installingStyle.setBackground(dk.b(context, n ? R.drawable._2131428531_res_0x7f0b04b3 : R.drawable._2131428530_res_0x7f0b04b2));
        }
        this.installingStyle.setTextColor(context.getResources().getColor(R.color._2131297944_res_0x7f090698));
        this.f7789a.setBackground(context.getResources().getDrawable(n ? R.drawable._2131428568_res_0x7f0b04d8 : R.drawable._2131428567_res_0x7f0b04d7));
        this.f7789a.setTextColor(context.getResources().getColor(R.color._2131297960_res_0x7f0906a8));
    }
}
