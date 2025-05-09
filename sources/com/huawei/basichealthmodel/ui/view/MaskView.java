package com.huawei.basichealthmodel.ui.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.basichealthmodel.callback.MaskOnCallbackListener;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.healthmodel.bean.ImageBean;
import com.huawei.health.healthmodel.bean.PictureBean;
import com.huawei.health.healthmodel.bean.TextBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.axv;
import defpackage.azi;
import defpackage.bcc;
import defpackage.koq;
import defpackage.nrf;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class MaskView extends RelativeLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private axv f1927a;
    private int b;
    private final SparseArray<String> c;
    private final SparseArray<BitmapDrawable> d;
    private MaskOnCallbackListener e;
    private HealthViewPager h;

    public MaskView(Context context) {
        super(context);
        this.d = new SparseArray<>(16);
        this.c = new SparseArray<>(16);
        d();
    }

    public MaskView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new SparseArray<>(16);
        this.c = new SparseArray<>(16);
        d();
    }

    public MaskView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new SparseArray<>(16);
        this.c = new SparseArray<>(16);
        d();
    }

    private void d() {
        Context e = BaseApplication.e();
        View inflate = View.inflate(e, R.layout.view_mask, this);
        this.h = (HealthViewPager) inflate.findViewById(R.id.view_mask_view_page);
        axv axvVar = new axv(this.d, this.c);
        this.f1927a = axvVar;
        this.h.setAdapter(axvVar);
        inflate.findViewById(R.id.view_mask_button).setOnClickListener(this);
        HealthDotsPageIndicator healthDotsPageIndicator = (HealthDotsPageIndicator) inflate.findViewById(R.id.view_mask_dots_page_indicator);
        healthDotsPageIndicator.setRtlEnable(LanguageUtil.bc(e));
        healthDotsPageIndicator.setViewPager(this.h);
    }

    private void b() {
        MaskOnCallbackListener maskOnCallbackListener = this.e;
        if (maskOnCallbackListener == null) {
            LogUtil.h("HealthLife_MaskView", "setMaskShowStatus mMaskOnCallbackListener is null");
        } else {
            maskOnCallbackListener.onMaskShowStatus(getVisibility() == 0);
        }
    }

    public void setImageBeanList(boolean z, List<ImageBean> list, MaskOnCallbackListener maskOnCallbackListener) {
        this.e = maskOnCallbackListener;
        if (koq.b(list)) {
            setVisibility(8);
            b();
            LogUtil.h("HealthLife_MaskView", "setImageBeanList imageBeanList is empty");
            return;
        }
        for (ImageBean imageBean : list) {
            if (imageBean != null) {
                ArrayList<PictureBean> pictureList = imageBean.getPictureList();
                if (!koq.b(pictureList)) {
                    int order = imageBean.getOrder();
                    Iterator<PictureBean> it = pictureList.iterator();
                    while (it.hasNext()) {
                        PictureBean next = it.next();
                        if (next != null) {
                            String path = next.getPath();
                            if (!azi.h(path)) {
                                this.d.append(order, nrf.cHP_(path));
                            }
                        }
                    }
                    ArrayList<TextBean> textList = imageBean.getTextList();
                    if (!koq.b(textList)) {
                        ArrayList<String> d = bcc.d(textList, (HashMap<String, String>) null);
                        if (!koq.b(d)) {
                            this.c.append(order, d.get(0));
                        }
                    }
                }
            }
        }
        this.f1927a.a(z);
        int count = this.f1927a.getCount() - 1;
        this.b = count;
        if (count < 0) {
            LogUtil.h("HealthLife_MaskView", "setImageBeanList count is zero");
            setVisibility(8);
            b();
            return;
        }
        b();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int currentItem = this.h.getCurrentItem();
        if (currentItem < this.b) {
            this.h.setCurrentItem(currentItem + 1);
        } else {
            setVisibility(8);
            MaskOnCallbackListener maskOnCallbackListener = this.e;
            if (maskOnCallbackListener == null) {
                LogUtil.h("HealthLife_MaskView", "onClick mMaskOnCallbackListener is null");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            maskOnCallbackListener.onMaskFinish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }
}
