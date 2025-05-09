package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.View;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import health.compact.a.HuaweiHealth;
import health.compact.a.LanguageUtil;
import java.util.Locale;

/* loaded from: classes5.dex */
public class lbx {
    public static CustomTextAlertDialog bVP_(Context context, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, DialogInterface.OnKeyListener onKeyListener) {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(context);
        builder.b(HuaweiHealth.a().getString(R.string._2130840310_res_0x7f020af6)).e(HuaweiHealth.a().getString(R.string._2130840309_res_0x7f020af5)).cyV_(HuaweiHealth.a().getString(R.string._2130840311_res_0x7f020af7), onClickListener).cyS_(HuaweiHealth.a().getString(R.string._2130840312_res_0x7f020af8), onClickListener2);
        builder.a(nsn.c(context, 12.0f), nsn.c(context, 10.0f));
        if (LanguageUtil.x(context)) {
            builder.a(nsn.c(context, 12.0f), nsn.c(context, 8.0f));
        }
        CustomTextAlertDialog a2 = builder.a();
        a2.setCanceledOnTouchOutside(false);
        a2.setOnKeyListener(onKeyListener);
        return a2;
    }

    public static NoTitleCustomAlertDialog bVO_(Context context, boolean z, View.OnClickListener onClickListener) {
        if (!(context instanceof Activity)) {
            return null;
        }
        String string = HuaweiHealth.a().getResources().getString(R.string._2130840325_res_0x7f020b05);
        SpannableString spannableString = new SpannableString(string + System.lineSeparator() + System.lineSeparator() + String.format(Locale.ENGLISH, HuaweiHealth.a().getResources().getString(z ? R.string._2130840338_res_0x7f020b12 : R.string._2130840326_res_0x7f020b06), 1, 2, 3));
        spannableString.setSpan(new TypefaceSpan(Constants.FONT), 0, string.length(), 17);
        spannableString.setSpan(new AbsoluteSizeSpan(20, true), 0, string.length(), 17);
        spannableString.setSpan(new ForegroundColorSpan(HuaweiHealth.a().getResources().getColor(R.color._2131299236_res_0x7f090ba4)), 0, string.length(), 17);
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(context).czx_(spannableString).czE_(HuaweiHealth.a().getString(R.string._2130840262_res_0x7f020ac6), onClickListener).e();
        e.setCancelable(false);
        return e;
    }
}
