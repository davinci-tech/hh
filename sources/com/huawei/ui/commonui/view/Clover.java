package com.huawei.ui.commonui.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.ucd.cloveranimation.AddFrameListener;
import com.huawei.ucd.cloveranimation.CloverView;
import com.huawei.ui.commonui.R$color;
import defpackage.njr;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class Clover extends CloverView {
    public Clover(Context context) {
        this(context, null);
    }

    public Clover(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Clover(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        c();
    }

    private void c() {
        njr cloverData = getCloverData();
        if (cloverData == null) {
            ReleaseLogUtil.a("R_Clover", "initColor cloverData is null");
            return;
        }
        cloverData.a(e(R$color.clover_petal_background_left_start));
        cloverData.e(e(R$color.clover_petal_background_left_end));
        cloverData.j(e(R$color.clover_petal_background_middle_start));
        cloverData.f(e(R$color.clover_petal_background_middle_end));
        cloverData.m(e(R$color.clover_petal_background_right_start));
        cloverData.l(e(R$color.clover_petal_background_right_end));
        cloverData.h(e(R$color.clover_petal_left_start));
        cloverData.b(e(R$color.clover_petal_left_end));
        cloverData.g(e(R$color.clover_petal_middle_start));
        cloverData.i(e(R$color.clover_petal_middle_end));
        cloverData.o(e(R$color.clover_petal_right_start));
        cloverData.n(e(R$color.clover_petal_right_end));
        cloverData.d(e(R$color.clover_petal_health_start));
        cloverData.c(e(R$color.clover_petal_health_end));
    }

    private int e(int i) {
        return ContextCompat.getColor(BaseApplication.e(), i);
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView
    public njr getCloverData() {
        return super.getCloverData();
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView
    public void setInvalid(boolean z) {
        super.setInvalid(z);
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView
    public void setContentsRunIcon(boolean z) {
        super.setContentsRunIcon(z);
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView
    public void setPlayRunAnimAnimator(boolean z) {
        super.setPlayRunAnimAnimator(z);
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView
    public void setPlayAllFrameAnimation(boolean z) {
        super.setPlayAllFrameAnimation(z);
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView
    public void setPlayEncourageAnimAnimator(boolean z) {
        super.setPlayEncourageAnimAnimator(z);
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView
    public void setAddFrameListener(AddFrameListener addFrameListener) {
        super.setAddFrameListener(addFrameListener);
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView
    public void b() {
        super.b();
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView
    public void e() {
        super.e();
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView
    public void c(float f) {
        super.c(f);
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView
    public void d() {
        super.d();
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView
    public void setValuesWithoutAnim(float f, float f2, float f3) {
        super.setValuesWithoutAnim(f, f2, f3);
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView
    public void setValuesWithAnim(float f, float f2, float f3) {
        super.setValuesWithAnim(f, f2, f3);
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView
    public void setCutValuesWithAnim(float f, float f2, float f3) {
        super.setCutValuesWithAnim(f, f2, f3);
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView
    public void a(float f, float f2, float f3) {
        super.a(f, f2, f3);
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView
    public void setMaxOutsideScale(float f) {
        super.setMaxOutsideScale(f);
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView
    public void setDefaultOutsideScale() {
        super.setDefaultOutsideScale();
    }

    public void setClover(float f, float f2, float f3) {
        setContentsRunIcon(false);
        setPlayRunAnimAnimator(false);
        setPlayEncourageAnimAnimator(false);
        setValuesWithoutAnim(f, f2, f3);
    }

    public void setCloverWithIcon(float f, float f2, float f3) {
        setContentsRunIcon(true);
        setPlayRunAnimAnimator(false);
        setPlayEncourageAnimAnimator(false);
        setValuesWithoutAnim(f, f2, f3);
    }

    public void setCloverRunAnimator(float f, float f2, float f3) {
        setContentsRunIcon(true);
        setPlayRunAnimAnimator(false);
        setPlayEncourageAnimAnimator(false);
        setValuesWithAnim(f, f2, f3);
    }

    public void setCloverCutValuesWithAnim(float f, float f2, float f3) {
        setContentsRunIcon(true);
        setPlayRunAnimAnimator(false);
        setPlayEncourageAnimAnimator(false);
        setCutValuesWithAnim(f, f2, f3);
    }

    public void setCloverRunGrowAnimator(float f, float f2, float f3) {
        setContentsRunIcon(true);
        setPlayRunAnimAnimator(true);
        setPlayEncourageAnimAnimator(false);
        a(f, f2, f3);
    }
}
