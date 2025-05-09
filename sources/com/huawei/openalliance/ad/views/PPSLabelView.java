package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.beans.metadata.AdSource;
import com.huawei.openalliance.ad.bx;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.dk;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.jk;
import com.huawei.openalliance.ad.rt;
import com.huawei.openalliance.ad.tm;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.az;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class PPSLabelView extends TextView {

    /* renamed from: a, reason: collision with root package name */
    protected ContentRecord f7888a;
    protected Integer b;
    protected boolean c;
    protected boolean d;
    protected boolean e;
    protected WeakReference<jk> f;
    private b g;
    private boolean h;
    private boolean i;
    private Drawable j;
    private View.OnClickListener k;

    @Override // android.view.View
    public void setVisibility(int i) {
        if (!this.e) {
            i = 8;
        }
        super.setVisibility(i);
    }

    protected void setTextWhenImgLoadFail(String str) {
        String defaultAdSign = getDefaultAdSign();
        if (TextUtils.isEmpty(str)) {
            str = defaultAdSign;
        }
        if (TextUtils.isEmpty(str) && !this.h) {
            setVisibility(8);
        }
        setText(str);
        setClick(new SpannableStringBuilder(getText()));
    }

    public void setTextForAppDetailView(AdSource adSource) {
        if (adSource == null) {
            ho.b("PPSLabelView", "setTextWithDspInfo, use default adSign");
            return;
        }
        this.h = false;
        this.i = true;
        b(adSource, "");
    }

    public void setDataAndRefreshUi(ContentRecord contentRecord) {
        if (contentRecord == null) {
            return;
        }
        this.f7888a = contentRecord;
        if (contentRecord.h() != null) {
            this.e = "2".equalsIgnoreCase(contentRecord.h().t());
        }
        if (this.e) {
            return;
        }
        setVisibility(8);
    }

    protected void setClick(SpannableStringBuilder spannableStringBuilder) {
        if (!this.d && !this.c) {
            setText(spannableStringBuilder);
            return;
        }
        spannableStringBuilder.append(" ");
        setText(a(new SpannableString(spannableStringBuilder)));
        setOnClickListener(this.k);
    }

    protected boolean c() {
        b bVar = this.g;
        if (bVar == null) {
            return false;
        }
        return bVar.b();
    }

    protected void b(AdSource adSource, String str) {
        SpannableStringBuilder spannableStringBuilder;
        if (adSource == null) {
            return;
        }
        String c = cz.c(adSource.a()) == null ? "" : cz.c(adSource.a());
        if (str == null) {
            str = "";
        }
        String str2 = c + str;
        String b2 = adSource.b();
        if (TextUtils.isEmpty(c) && TextUtils.isEmpty(b2)) {
            ho.b("PPSLabelView", "displayTextWithDspInfo, use default adSign");
            spannableStringBuilder = new SpannableStringBuilder(getText());
        } else if (TextUtils.isEmpty(c) || !TextUtils.isEmpty(b2)) {
            a(str2, b2);
            return;
        } else {
            ho.b("PPSLabelView", "displayTextWithDspInfo, use dspNameWithAdSign");
            spannableStringBuilder = new SpannableStringBuilder(str2);
        }
        setClick(spannableStringBuilder);
    }

    protected void b() {
        b bVar = this.g;
        if (bVar != null) {
            bVar.a();
        }
    }

    public boolean a() {
        return this.e;
    }

    public void a(String str, String str2, String str3) {
        SpannableStringBuilder spannableStringBuilder;
        if (this.e) {
            if ((TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) || TextUtils.isEmpty(str3)) {
                ho.b("PPSLabelView", "dspInfo all empty or logo2Text is empty");
                ho.b("PPSLabelView", "setTextWithDspInfo, use default adSign");
                spannableStringBuilder = new SpannableStringBuilder(getText());
            } else {
                if (str == null) {
                    str = "";
                }
                if (str3 == null) {
                    str3 = "";
                }
                String str4 = str + str3;
                if (TextUtils.isEmpty(str4) && TextUtils.isEmpty(str2)) {
                    ho.b("PPSLabelView", "setTextWithDspInfo, use default adSign");
                    spannableStringBuilder = new SpannableStringBuilder(getText());
                } else if (TextUtils.isEmpty(str4) || !TextUtils.isEmpty(str2)) {
                    a(str4, str2);
                    return;
                } else {
                    ho.b("PPSLabelView", "setTextWithDspInfo, use dspNameWithAdSign");
                    setText(str4);
                    spannableStringBuilder = new SpannableStringBuilder(getText());
                }
            }
            setClick(spannableStringBuilder);
        }
    }

    protected void a(String str, String str2) {
        Context context;
        a aVar;
        if (this.e) {
            ho.b("PPSLabelView", "loadAndSetDspInfo, start");
            dk a2 = dh.a(getContext(), "normal");
            String c = a2.c(a2.e(str2));
            if (this.i) {
                a(str, this.j);
                if (TextUtils.isEmpty(c)) {
                    rt rtVar = new rt();
                    rtVar.b(false);
                    rtVar.c(true);
                    rtVar.c(str2);
                    az.a(getContext(), rtVar, new a(this, str));
                    return;
                }
                context = getContext();
                aVar = new a(this, str);
            } else if (!TextUtils.isEmpty(str) && TextUtils.isEmpty(c)) {
                ho.b("PPSLabelView", "displayTextWithDspInfo, use dspNameWithAdSign");
                setTextWhenImgLoadFail(str);
                return;
            } else {
                a(str, this.j);
                context = getContext();
                aVar = new a(this, str);
            }
            az.a(context, c, aVar);
        }
    }

    protected void a(String str, Drawable drawable) {
        try {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(" ");
            String defaultAdSign = getDefaultAdSign();
            if (TextUtils.isEmpty(str)) {
                str = defaultAdSign;
            }
            boolean isEmpty = TextUtils.isEmpty(str);
            spannableStringBuilder.append((CharSequence) str);
            ImageSpan a2 = a(drawable, !isEmpty);
            if (a2 != null) {
                spannableStringBuilder.setSpan(a2, 0, 1, 33);
            }
            setClick(spannableStringBuilder);
        } catch (Throwable unused) {
            ho.c("PPSLabelView", "setTextWhenImgLoaded error");
        }
    }

    public void a(jk jkVar, ContentRecord contentRecord, boolean z) {
        this.f7888a = contentRecord;
        this.d = z;
        this.f = new WeakReference<>(jkVar);
    }

    public void a(AdSource adSource, String str) {
        if (this.e) {
            if (adSource != null && !TextUtils.isEmpty(str)) {
                b(adSource, str);
            } else {
                ho.b("PPSLabelView", "setTextWithDspInfo, use default adSign");
                setClick(new SpannableStringBuilder(getText()));
            }
        }
    }

    protected ImageSpan a(Drawable drawable, boolean z) {
        Bitmap a2 = az.a(drawable);
        if (a2 == null) {
            ho.b("PPSLabelView", "originImage bitmap is null");
            return null;
        }
        float textSize = getTextSize();
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(a2, Math.round(textSize), Math.round(textSize), false));
        bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
        return new c(bitmapDrawable, 2, 0, z ? ao.a(getContext(), 4.0f) : 0);
    }

    private String getDefaultAdSign() {
        return this.h ? getResources().getString(R.string._2130850988_res_0x7f0234ac) : "";
    }

    private ImageSpan getClickImageSpanRight() {
        try {
            Drawable drawable = getResources().getDrawable(R.drawable._2131428512_res_0x7f0b04a0);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            if (!dd.c()) {
                return new c(drawable, 2, ao.a(getContext(), 4.0f), 0);
            }
            return new c(getContext(), az.b(drawable), 2, ao.a(getContext(), 4.0f), 0);
        } catch (Throwable unused) {
            return null;
        }
    }

    private boolean a(PPSLinkedView pPSLinkedView, boolean z, Integer num, RelativeLayout.LayoutParams layoutParams, tm tmVar) {
        ah n = pPSLinkedView.n();
        if (n == null) {
            ho.c("PPSLabelView", "linked splash container is null");
            return false;
        }
        tmVar.a(z, num, pPSLinkedView.m());
        n.addView(tmVar, layoutParams);
        tmVar.setScreenHeight(n.getMeasuredHeight());
        tmVar.setScreenWidth(n.getMeasuredWidth());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(jk jkVar) {
        return (jkVar instanceof PPSSplashView) || (jkVar instanceof PPSLinkedView);
    }

    private void a(RelativeLayout relativeLayout, int[] iArr) {
        if (dd.b(dd.d(relativeLayout))) {
            int y = dd.y(getContext());
            ho.b("PPSLabelView", "drag H: %s", Integer.valueOf(y));
            iArr[1] = iArr[1] - y;
        } else if (relativeLayout instanceof PPSInterstitialView) {
            int a2 = bz.a(getContext()).a(this);
            ho.b("PPSLabelView", "notch H: %s", Integer.valueOf(a2));
            if (dd.l(getContext())) {
                iArr[1] = iArr[1] - a2;
            } else {
                iArr[0] = iArr[0] - a2;
            }
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<tm> f7894a;

        /* JADX INFO: Access modifiers changed from: private */
        public boolean b() {
            tm tmVar;
            WeakReference<tm> weakReference = this.f7894a;
            if (weakReference == null || (tmVar = weakReference.get()) == null) {
                return false;
            }
            return tmVar.j();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            tm tmVar;
            WeakReference<tm> weakReference = this.f7894a;
            if (weakReference == null || (tmVar = weakReference.get()) == null) {
                return;
            }
            tmVar.i();
        }

        private b(tm tmVar) {
            this.f7894a = new WeakReference<>(tmVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(RelativeLayout relativeLayout, ContentRecord contentRecord, boolean z, Integer num, int[] iArr, int[] iArr2, int i) {
        if (relativeLayout == null || contentRecord == null) {
            return;
        }
        if (!ao.a(iArr, 2) || !ao.a(iArr2, 2)) {
            ho.c("PPSLabelView", "anchor is invalid.");
            return;
        }
        if (ho.a()) {
            ho.a("PPSLabelView", "addTransparencyDialog, loc: %s, %s", Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]));
            ho.a("PPSLabelView", "addTransparencyDialog, size: %s, %s", Integer.valueOf(iArr2[0]), Integer.valueOf(iArr2[1]));
        }
        a(relativeLayout, iArr);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        tm tmVar = new tm(relativeLayout.getContext(), iArr, iArr2, i);
        this.g = new b(tmVar);
        if (relativeLayout instanceof PPSSplashView) {
            tmVar.a(z, num, ((PPSSplashView) relativeLayout).getAdMediator());
        }
        if (!(relativeLayout instanceof PPSLinkedView)) {
            ho.a("PPSLabelView", "addTransparencyDialog, adview: %s, %s", Integer.valueOf(relativeLayout.getMeasuredHeight()), Integer.valueOf(relativeLayout.getMeasuredWidth()));
            relativeLayout.addView(tmVar, layoutParams);
            tmVar.setScreenHeight(relativeLayout.getMeasuredHeight());
            tmVar.setScreenWidth(relativeLayout.getMeasuredWidth());
            tmVar.h();
        } else if (!a((PPSLinkedView) relativeLayout, z, num, layoutParams, tmVar)) {
            return;
        }
        tmVar.setAdContent(contentRecord);
    }

    static class a implements az.a {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<PPSLabelView> f7891a;
        private String b;

        @Override // com.huawei.openalliance.ad.utils.az.a
        public void a(final Drawable drawable) {
            ho.b("PPSLabelView", "start - dspLogo load onSuccess");
            if (drawable != null) {
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSLabelView.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        PPSLabelView pPSLabelView = (PPSLabelView) a.this.f7891a.get();
                        if (pPSLabelView != null) {
                            pPSLabelView.a(a.this.b, drawable);
                        }
                    }
                });
            }
        }

        @Override // com.huawei.openalliance.ad.utils.az.a
        public void a() {
            ho.b("PPSLabelView", "start - dspLogo load failed");
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSLabelView.a.2
                @Override // java.lang.Runnable
                public void run() {
                    PPSLabelView pPSLabelView = (PPSLabelView) a.this.f7891a.get();
                    if (pPSLabelView != null) {
                        pPSLabelView.setTextWhenImgLoadFail(a.this.b);
                    }
                }
            });
        }

        a(PPSLabelView pPSLabelView, String str) {
            this.f7891a = new WeakReference<>(pPSLabelView);
            this.b = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(RelativeLayout relativeLayout, ContentRecord contentRecord, boolean z, Integer num, int[] iArr, int[] iArr2) {
        a(relativeLayout, contentRecord, z, num, iArr, iArr2, -1);
    }

    private void a(Context context) {
        try {
            this.j = context.getResources().getDrawable(R.drawable._2131428524_res_0x7f0b04ac);
        } catch (Throwable unused) {
            ho.c("PPSLabelView", "init error");
        }
    }

    private SpannableStringBuilder a(SpannableString spannableString) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spannableString);
        ImageSpan clickImageSpanRight = getClickImageSpanRight();
        if (clickImageSpanRight != null) {
            spannableStringBuilder.setSpan(clickImageSpanRight, spannableString.length() - 1, spannableString.length(), 33);
        }
        return spannableStringBuilder;
    }

    public PPSLabelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = false;
        this.d = false;
        this.e = true;
        this.h = true;
        this.i = false;
        this.k = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSLabelView.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                if (PPSLabelView.this.f != null) {
                    final jk jkVar = PPSLabelView.this.f.get();
                    if (jkVar == null) {
                        ho.c("PPSLabelView", "adView is null");
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    } else {
                        final int[] b2 = dd.b(view);
                        final int[] c = dd.c(view);
                        if (ao.a(b2, 2) && ao.a(c, 2)) {
                            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSLabelView.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (PPSLabelView.this.a(jkVar)) {
                                        PPSLabelView.this.a((RelativeLayout) jkVar, PPSLabelView.this.f7888a, PPSLabelView.this.c, PPSLabelView.this.b, b2, c, 1);
                                        return;
                                    }
                                    jk jkVar2 = jkVar;
                                    if ((jkVar2 instanceof PPSRewardView) || (jkVar2 instanceof PPSInterstitialView)) {
                                        PPSLabelView.this.a((RelativeLayout) jkVar, PPSLabelView.this.f7888a, PPSLabelView.this.c, PPSLabelView.this.b, b2, c);
                                    } else {
                                        bx.a(PPSLabelView.this.getContext(), view, PPSLabelView.this.f7888a);
                                    }
                                }
                            });
                        }
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        a(context);
    }

    public PPSLabelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = false;
        this.d = false;
        this.e = true;
        this.h = true;
        this.i = false;
        this.k = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSLabelView.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                if (PPSLabelView.this.f != null) {
                    final jk jkVar = PPSLabelView.this.f.get();
                    if (jkVar == null) {
                        ho.c("PPSLabelView", "adView is null");
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    } else {
                        final int[] b2 = dd.b(view);
                        final int[] c = dd.c(view);
                        if (ao.a(b2, 2) && ao.a(c, 2)) {
                            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSLabelView.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (PPSLabelView.this.a(jkVar)) {
                                        PPSLabelView.this.a((RelativeLayout) jkVar, PPSLabelView.this.f7888a, PPSLabelView.this.c, PPSLabelView.this.b, b2, c, 1);
                                        return;
                                    }
                                    jk jkVar2 = jkVar;
                                    if ((jkVar2 instanceof PPSRewardView) || (jkVar2 instanceof PPSInterstitialView)) {
                                        PPSLabelView.this.a((RelativeLayout) jkVar, PPSLabelView.this.f7888a, PPSLabelView.this.c, PPSLabelView.this.b, b2, c);
                                    } else {
                                        bx.a(PPSLabelView.this.getContext(), view, PPSLabelView.this.f7888a);
                                    }
                                }
                            });
                        }
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        a(context);
    }

    public PPSLabelView(Context context) {
        super(context);
        this.c = false;
        this.d = false;
        this.e = true;
        this.h = true;
        this.i = false;
        this.k = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSLabelView.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                if (PPSLabelView.this.f != null) {
                    final jk jkVar = PPSLabelView.this.f.get();
                    if (jkVar == null) {
                        ho.c("PPSLabelView", "adView is null");
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    } else {
                        final int[] b2 = dd.b(view);
                        final int[] c = dd.c(view);
                        if (ao.a(b2, 2) && ao.a(c, 2)) {
                            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSLabelView.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (PPSLabelView.this.a(jkVar)) {
                                        PPSLabelView.this.a((RelativeLayout) jkVar, PPSLabelView.this.f7888a, PPSLabelView.this.c, PPSLabelView.this.b, b2, c, 1);
                                        return;
                                    }
                                    jk jkVar2 = jkVar;
                                    if ((jkVar2 instanceof PPSRewardView) || (jkVar2 instanceof PPSInterstitialView)) {
                                        PPSLabelView.this.a((RelativeLayout) jkVar, PPSLabelView.this.f7888a, PPSLabelView.this.c, PPSLabelView.this.b, b2, c);
                                    } else {
                                        bx.a(PPSLabelView.this.getContext(), view, PPSLabelView.this.f7888a);
                                    }
                                }
                            });
                        }
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        a(context);
    }
}
