package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import com.huawei.openalliance.ad.beans.metadata.AdSource;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.cz;

/* loaded from: classes9.dex */
public class PPSSplashLabelView extends PPSLabelView {
    @Override // com.huawei.openalliance.ad.views.PPSLabelView
    protected void setTextWhenImgLoadFail(String str) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("");
        if (!TextUtils.isEmpty(str)) {
            spannableStringBuilder.append((CharSequence) str);
        }
        setClick(spannableStringBuilder);
    }

    @Override // com.huawei.openalliance.ad.views.PPSLabelView
    protected void b(AdSource adSource, String str) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        if (adSource == null) {
            ho.b("PPSSplashLabelView", "adSource is null");
            setClick(spannableStringBuilder);
            return;
        }
        String c = cz.c(adSource.a()) == null ? "" : cz.c(adSource.a());
        if (str == null) {
            str = "";
        }
        String str2 = c + str;
        String b = adSource.b();
        if (TextUtils.isEmpty(c) && TextUtils.isEmpty(b)) {
            setClick(spannableStringBuilder);
        } else if (TextUtils.isEmpty(c) || !TextUtils.isEmpty(b)) {
            a(str2, b);
        } else {
            setClick(new SpannableStringBuilder(str2));
        }
    }

    @Override // com.huawei.openalliance.ad.views.PPSLabelView
    protected void a(String str, Drawable drawable) {
        try {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(" ");
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            boolean isEmpty = TextUtils.isEmpty(str);
            spannableStringBuilder.append((CharSequence) str);
            ImageSpan a2 = a(drawable, !isEmpty);
            if (a2 != null) {
                spannableStringBuilder.setSpan(a2, 0, 1, 33);
            }
            setClick(spannableStringBuilder);
        } catch (Throwable unused) {
            ho.c("PPSSplashLabelView", "setTextWhenImgLoaded error");
        }
    }

    public void a(AdSource adSource, String str, Integer num, boolean z) {
        this.b = num;
        this.c = z;
        b(adSource, str);
    }

    public PPSSplashLabelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public PPSSplashLabelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PPSSplashLabelView(Context context) {
        super(context);
    }
}
