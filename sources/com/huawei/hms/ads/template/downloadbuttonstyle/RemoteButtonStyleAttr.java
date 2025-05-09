package com.huawei.hms.ads.template.downloadbuttonstyle;

import android.graphics.drawable.Drawable;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes4.dex */
public class RemoteButtonStyleAttr {
    private String bfDlBtnTxt;
    protected Drawable cancelBackground;
    protected Drawable cancelBackgroundDark;
    protected Drawable cancelBtn;
    protected Drawable cancelBtnDark;
    protected Drawable installingBackground;
    protected Drawable installingBackgroundDark;
    protected int installingTextColor;
    protected int installingTextColorDark;
    private boolean isAppRelated;
    private int mMaxWidth;
    private int mMinWidth;
    protected Drawable normalBackground;
    protected Drawable normalBackgroundDark;
    protected int normalTextColor;
    protected int normalTextColorDark;
    private int paddingBottom;
    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private String priorAfDlBtnTxt;
    private String priorBfDlBtnTxt;
    private String priorWebBtnTxt;
    protected Drawable processingBackground;
    protected Drawable processingBackgroundDark;
    protected int processingTextColor;
    protected int processingTextColorDark;
    private String promtBtnTxt;
    private String webBtnTxt;
    private boolean mFixedWidth = false;
    private boolean resetWidth = true;
    private int mTextColor = -1;
    private float mTextSize = 12.0f;
    String mFontFamily = Constants.FONT;

    public int z() {
        return this.paddingBottom;
    }

    public int y() {
        return this.paddingTop;
    }

    public int x() {
        return this.paddingRight;
    }

    public int w() {
        return this.paddingLeft;
    }

    public String v() {
        return this.mFontFamily;
    }

    public float u() {
        return this.mTextSize;
    }

    public int t() {
        return this.mMinWidth;
    }

    public int s() {
        return this.mMaxWidth;
    }

    public boolean r() {
        return this.resetWidth;
    }

    public boolean q() {
        return this.mFixedWidth;
    }

    public Drawable p() {
        return this.cancelBtnDark;
    }

    public int o() {
        return this.installingTextColorDark;
    }

    public Drawable n() {
        return this.installingBackgroundDark;
    }

    public int m() {
        return this.processingTextColorDark;
    }

    public Drawable l() {
        return this.processingBackgroundDark;
    }

    public int k() {
        return this.normalTextColorDark;
    }

    public void j(Drawable drawable) {
        this.cancelBtnDark = drawable;
    }

    public Drawable j() {
        return this.cancelBackgroundDark;
    }

    public void i(Drawable drawable) {
        this.installingBackgroundDark = drawable;
    }

    public Drawable i() {
        return this.cancelBackground;
    }

    public void h(Drawable drawable) {
        this.processingBackgroundDark = drawable;
    }

    public Drawable h() {
        return this.normalBackgroundDark;
    }

    public void g(Drawable drawable) {
        this.cancelBackgroundDark = drawable;
    }

    public Drawable g() {
        return this.cancelBtn;
    }

    public void f(String str) {
        this.priorWebBtnTxt = str;
    }

    public void f(Drawable drawable) {
        this.cancelBackground = drawable;
    }

    public int f() {
        return this.installingTextColor;
    }

    public void e(String str) {
        this.webBtnTxt = str;
    }

    public void e(Drawable drawable) {
        this.normalBackgroundDark = drawable;
    }

    public Drawable e() {
        return this.installingBackground;
    }

    public void d(String str) {
        this.priorAfDlBtnTxt = str;
    }

    public void d(Drawable drawable) {
        this.cancelBtn = drawable;
    }

    public int d() {
        return this.processingTextColor;
    }

    public void c(String str) {
        this.priorBfDlBtnTxt = str;
    }

    public void c(Drawable drawable) {
        this.installingBackground = drawable;
    }

    public Drawable c() {
        return this.processingBackground;
    }

    public void b(String str) {
        this.promtBtnTxt = str;
    }

    public void b(Drawable drawable) {
        this.processingBackground = drawable;
    }

    public int b() {
        return this.normalTextColor;
    }

    public void a(boolean z) {
        this.isAppRelated = z;
    }

    public void a(String str) {
        this.bfDlBtnTxt = str;
    }

    public void a(Drawable drawable) {
        this.normalBackground = drawable;
    }

    public Drawable a() {
        return this.normalBackground;
    }

    public String G() {
        return this.priorWebBtnTxt;
    }

    public boolean F() {
        return this.isAppRelated;
    }

    public String E() {
        return this.webBtnTxt;
    }

    public String D() {
        return this.priorAfDlBtnTxt;
    }

    public String C() {
        return this.priorBfDlBtnTxt;
    }

    public String B() {
        return this.promtBtnTxt;
    }

    public String A() {
        return this.bfDlBtnTxt;
    }
}
