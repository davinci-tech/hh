package defpackage;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nmn;
import health.compact.a.UnitUtil;

/* loaded from: classes3.dex */
public class cpr {
    public static void Kn_(cfi cfiVar, HealthTextView healthTextView, ImageView imageView) {
        if (cfiVar == null || imageView == null) {
            LogUtil.h("PluginDevice_HealthViewUtils", "setUserNameAndPhoto user or photoImage is null");
            return;
        }
        if (healthTextView != null) {
            healthTextView.setText(cfiVar.h());
        }
        imageView.setImageBitmap(null);
        imageView.setImageResource(0);
        if (!TextUtils.isEmpty(cfiVar.e())) {
            Ko_(cfiVar.e(), imageView, cfiVar);
        } else if (cfiVar.Ex_() == null) {
            imageView.setImageDrawable(nmn.cBh_(BaseApplication.getContext().getResources(), new nmn.c(null, cfiVar.i(), true)));
        } else {
            imageView.setImageBitmap(nmn.cBg_(cfiVar.Ex_(), true));
        }
    }

    private static void Ko_(String str, ImageView imageView, cfi cfiVar) {
        if (imageView == null || cfiVar == null) {
            LogUtil.h("PluginDevice_HealthViewUtils", "setUserPhoto photoImage or user is null");
            return;
        }
        if (!"default".equals(str)) {
            Bitmap cIe_ = nrf.cIe_(BaseApplication.getContext(), str);
            if (cIe_ != null) {
                imageView.setImageBitmap(nmn.cBg_(cIe_, true));
                return;
            }
            return;
        }
        imageView.setImageDrawable(nmn.cBh_(BaseApplication.getContext().getResources(), new nmn.c(null, cfiVar.i(), true)));
    }

    public static String e(double d, int i) {
        if (UnitUtil.h()) {
            return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903216_res_0x7f0300b0, dks.c(UnitUtil.h(d)), UnitUtil.e(UnitUtil.h(d), 1, i));
        }
        return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903215_res_0x7f0300af, dks.c(d), UnitUtil.e(d, 1, i));
    }
}
