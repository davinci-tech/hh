package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.huawei.health.R;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.dk;

/* loaded from: classes5.dex */
public class AppDownloadButtonStyle {
    private Context b;
    protected Drawable cancelBtnDrawable;
    protected Style normalStyle = new Style();
    protected Style processingStyle = new Style();

    /* renamed from: a, reason: collision with root package name */
    protected Style f7789a = new Style();
    protected Style cancelBtnStyle = new Style();
    protected Style installingStyle = new Style();

    public void setCancelBtnDrawable(Drawable drawable) {
        this.cancelBtnDrawable = drawable;
    }

    public Style getStyle(Context context, AppStatus appStatus) {
        if (appStatus == null) {
            return a();
        }
        int i = AnonymousClass1.f7790a[appStatus.ordinal()];
        if (i != 1 && i != 2) {
            if (i == 3) {
                return this.installingStyle;
            }
            if (i != 4) {
                return a();
            }
        }
        return this.processingStyle;
    }

    public static class Style {
        protected Drawable background;
        protected int textColor;
        protected int textSize = 12;

        public void setTextSize(int i) {
            this.textSize = i;
        }

        public void setTextColor(int i) {
            this.textColor = i;
        }

        public void setBackground(Drawable drawable) {
            this.background = drawable;
        }

        public int getTextSize() {
            return this.textSize;
        }

        public int getTextColor() {
            return this.textColor;
        }

        public Drawable getBackground() {
            return this.background;
        }
    }

    public Drawable getCancelBtnDrawable() {
        return this.cancelBtnDrawable;
    }

    public int d() {
        return ao.n(this.b) ? 28 : 18;
    }

    public Style c() {
        return this.f7789a;
    }

    public Style b() {
        return this.cancelBtnStyle;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.openalliance.ad.views.AppDownloadButtonStyle.Style a(android.content.Context r2, com.huawei.openalliance.ad.download.app.AppStatus r3, int r4) {
        /*
            r1 = this;
            if (r3 != 0) goto L7
            com.huawei.openalliance.ad.views.AppDownloadButtonStyle$Style r2 = r1.a()
            return r2
        L7:
            r0 = 11
            if (r4 != r0) goto L27
            int[] r4 = com.huawei.openalliance.ad.views.AppDownloadButtonStyle.AnonymousClass1.f7790a
            int r0 = r3.ordinal()
            r4 = r4[r0]
            r0 = 1
            if (r4 == r0) goto L22
            r0 = 2
            if (r4 == r0) goto L22
            r0 = 3
            if (r4 == r0) goto L1d
            goto L27
        L1d:
            com.huawei.openalliance.ad.views.AppDownloadButtonStyle$Style r4 = r1.c()
            goto L28
        L22:
            com.huawei.openalliance.ad.views.AppDownloadButtonStyle$Style r4 = r1.a()
            goto L28
        L27:
            r4 = 0
        L28:
            if (r4 != 0) goto L2e
            com.huawei.openalliance.ad.views.AppDownloadButtonStyle$Style r4 = r1.getStyle(r2, r3)
        L2e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.AppDownloadButtonStyle.a(android.content.Context, com.huawei.openalliance.ad.download.app.AppStatus, int):com.huawei.openalliance.ad.views.AppDownloadButtonStyle$Style");
    }

    public Style a() {
        return this.normalStyle;
    }

    /* renamed from: com.huawei.openalliance.ad.views.AppDownloadButtonStyle$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f7790a;

        static {
            int[] iArr = new int[AppStatus.values().length];
            f7790a = iArr;
            try {
                iArr[AppStatus.PAUSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7790a[AppStatus.DOWNLOADING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7790a[AppStatus.INSTALLING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7790a[AppStatus.WAITING_FOR_WIFI.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7790a[AppStatus.INSTALLED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7790a[AppStatus.DOWNLOAD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f7790a[AppStatus.INSTALL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public AppDownloadButtonStyle(Context context) {
        this.b = context;
        this.normalStyle.background = context.getResources().getDrawable(R.drawable._2131428492_res_0x7f0b048c);
        this.normalStyle.textColor = context.getResources().getColor(R.color._2131297951_res_0x7f09069f);
        this.processingStyle.setBackground(dk.b(context, R.drawable._2131428494_res_0x7f0b048e));
        this.processingStyle.setTextColor(context.getResources().getColor(R.color._2131297920_res_0x7f090680));
        this.installingStyle.setBackground(context.getResources().getDrawable(R.drawable._2131428490_res_0x7f0b048a));
        this.installingStyle.setTextColor(context.getResources().getColor(R.color._2131297914_res_0x7f09067a));
        this.f7789a.setBackground(context.getResources().getDrawable(R.drawable._2131428567_res_0x7f0b04d7));
        this.f7789a.setTextColor(context.getResources().getColor(R.color._2131297960_res_0x7f0906a8));
        this.cancelBtnDrawable = context.getResources().getDrawable(R.drawable._2131428496_res_0x7f0b0490);
    }

    public AppDownloadButtonStyle() {
    }
}
