package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.koq;
import defpackage.nru;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes3.dex */
public class OperaMsgCardSection extends BaseSection {
    public ViewFlipper e;

    public OperaMsgCardSection(Context context) {
        super(context);
    }

    public OperaMsgCardSection(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public OperaMsgCardSection(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.home_item_layout_opera_msg, (ViewGroup) this, false);
        ViewFlipper viewFlipper = (ViewFlipper) inflate.findViewById(R.id.opera_msg_flipper);
        this.e = viewFlipper;
        BaseActivity.setViewSafeRegion(true, viewFlipper);
        return inflate;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        ViewFlipper viewFlipper;
        ViewFlipper viewFlipper2;
        ViewFlipper viewFlipper3;
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("OperaMsgCardSection", "no need to bind");
            return;
        }
        nsy.cMA_(this.e, nru.d((Map) hashMap, "VIEW_FLIPPER_VISIBILITY", 8));
        if (nru.d((Map) hashMap, "REMOVE_ALL_VIEWS", false) && (viewFlipper3 = this.e) != null) {
            viewFlipper3.removeAllViews();
        }
        int d = nru.d((Map) hashMap, "FLIPPING_INTERVAL", 3000);
        ViewFlipper viewFlipper4 = this.e;
        if (viewFlipper4 != null) {
            viewFlipper4.setFlipInterval(d);
        }
        ArrayList arrayList = (ArrayList) nru.c(hashMap, "ADD_VIEW", ArrayList.class, null);
        if (!koq.b(arrayList) && this.e != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.e.addView((View) it.next());
            }
        }
        if (nru.d((Map) hashMap, "STOP_FLIPPING", false) && (viewFlipper2 = this.e) != null && viewFlipper2.isFlipping()) {
            this.e.stopFlipping();
        }
        if (!nru.d((Map) hashMap, "START_FLIPPING", false) || (viewFlipper = this.e) == null) {
            return;
        }
        viewFlipper.startFlipping();
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "OperaMsgCardSection";
    }
}
