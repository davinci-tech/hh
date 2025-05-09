package com.huawei.ui.main.stories.configuredpage.views;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.sport.configuredpage.ConfiguredPageItemDecoration;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.stories.configuredpage.ConfigureServerDetailActivity;
import com.huawei.ui.main.stories.configuredpage.adpters.ConfiguredLayoutAdapter;
import defpackage.cdu;
import defpackage.cdy;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nrr;
import defpackage.nsn;
import defpackage.pfh;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class ConfiguredPageLinearLayout extends LinearLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private int f9691a;
    private boolean b;
    private HealthSubHeader c;
    private LinearLayout d;
    private Context e;
    private int f;
    private boolean g;
    private Pair<Integer, Integer> h;
    private cdy i;
    private int j;
    private List<List<cdu>> k;
    private List<cdu> m;
    private int n;
    private HealthRecycleView o;

    public ConfiguredPageLinearLayout(Context context) {
        super(context);
        this.k = new ArrayList(10);
        this.b = false;
        this.g = false;
        this.n = 1;
        this.h = BaseActivity.getSafeRegionWidth();
        c(context);
    }

    public ConfiguredPageLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = new ArrayList(10);
        this.b = false;
        this.g = false;
        this.n = 1;
        this.h = BaseActivity.getSafeRegionWidth();
        c(context);
    }

    public ConfiguredPageLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = new ArrayList(10);
        this.b = false;
        this.g = false;
        this.n = 1;
        this.h = BaseActivity.getSafeRegionWidth();
        c(context);
    }

    private void c(Context context) {
        this.e = context;
        View inflate = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.configured_page_parent_layout, this);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.layout_configured_page);
        this.d = linearLayout;
        linearLayout.setVisibility(0);
        this.o = (HealthRecycleView) inflate.findViewById(R.id.configured_page_recycler_view);
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.configure_page_sub_header);
        this.c = healthSubHeader;
        healthSubHeader.setMoreTextVisibility(4);
        this.o.setNestedScrollingEnabled(false);
        this.o.setHasFixedSize(true);
        this.o.a(false);
        this.o.d(false);
        this.f9691a = nrr.b(this.e);
        this.b = nsn.l();
        boolean ag = nsn.ag(BaseApplication.getContext());
        this.g = ag;
        if (this.b && ag) {
            this.n = getTahitiOrientation();
        }
    }

    private int getTahitiOrientation() {
        Resources resources = BaseApplication.getContext().getResources();
        if (resources == null) {
            return 1;
        }
        return resources.getConfiguration().orientation;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String a2 = this.i.a();
        try {
            if (this.j == 12) {
                b();
                Intent intent = new Intent(this.e, (Class<?>) ConfigureServerDetailActivity.class);
                intent.putExtra("server_name", this.i);
                intent.setFlags(268435456);
                this.e.startActivity(intent);
            } else if (!TextUtils.isEmpty(a2)) {
                b();
                if (a2.contains("h5pro=true")) {
                    pfh.d(this.e, a2);
                } else {
                    Intent intent2 = new Intent(this.e, (Class<?>) WebViewActivity.class);
                    intent2.setFlags(268435456);
                    intent2.putExtra("url", a2);
                    this.e.startActivity(intent2);
                }
            }
        } catch (ActivityNotFoundException e) {
            LogUtil.b("ConfiguredPage_ConfiguredPageLinearLayout", "StartActivity exception", e.getMessage());
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void b(cdy cdyVar) {
        ConfiguredLayoutAdapter configuredLayoutAdapter;
        this.i = cdyVar;
        if (cdyVar == null) {
            LogUtil.h("ConfiguredPage_ConfiguredPageLinearLayout", "initData() pageModuleObject is null.");
            return;
        }
        this.j = cdyVar.c();
        d();
        int b = this.i.b();
        this.f = this.i.i();
        if (!pfh.e(b, this.j)) {
            LogUtil.h("ConfiguredPage_ConfiguredPageLinearLayout", "updateConfiguredPageUi() moduleType:", Integer.valueOf(b), " is not support layout: ", Integer.valueOf(this.j));
            return;
        }
        List<cdu> e = this.i.e();
        if (koq.b(e)) {
            LogUtil.h("ConfiguredPage_ConfiguredPageLinearLayout", "updateConfiguredPageUi() allCardItemObjectList is empty.");
            return;
        }
        List<cdu> c = pfh.c(this.j, b, e);
        this.m = c;
        if (koq.b(c)) {
            this.d.setVisibility(8);
            LogUtil.h("ConfiguredPage_ConfiguredPageLinearLayout", "updateConfiguredPageUi() showCardItemObjectList is empty.");
            return;
        }
        if (this.j == 9 && !nsn.ag(BaseApplication.getContext())) {
            this.k.clear();
            this.k.add(this.m);
        }
        if (!nsn.ag(BaseApplication.getContext()) && this.j == 9) {
            configuredLayoutAdapter = new ConfiguredLayoutAdapter(this.e, this.k, this.i, true);
        } else if (this.j == 12) {
            configuredLayoutAdapter = new ConfiguredLayoutAdapter(this.e, getAppIconData(), this.i);
        } else {
            configuredLayoutAdapter = new ConfiguredLayoutAdapter(this.e, this.m, this.i);
        }
        this.o.setAdapter(configuredLayoutAdapter);
        a();
        e();
    }

    private void d() {
        if (this.j == 7) {
            this.o.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable.configured_item_image_text_background));
            ViewGroup.LayoutParams layoutParams = this.o.getLayoutParams();
            if (layoutParams instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                int dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
                int dimensionPixelSize2 = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
                layoutParams2.setMarginStart(dimensionPixelSize + ((Integer) this.h.first).intValue());
                layoutParams2.setMarginEnd(dimensionPixelSize2 + ((Integer) this.h.second).intValue());
                this.o.setLayoutParams(layoutParams2);
                return;
            }
            return;
        }
        this.o.setBackground(null);
    }

    private List<cdu> getAppIconData() {
        ArrayList arrayList = new ArrayList();
        if (koq.b(this.m)) {
            return arrayList;
        }
        List<cdu> list = this.m;
        if (this.g || list.size() <= 4) {
            return (!this.g || list.size() <= 8) ? list : list.subList(0, 8);
        }
        return list.subList(0, 4);
    }

    private void a() {
        if (this.i == null) {
            return;
        }
        LinearLayout.LayoutParams subHeaderParams = getSubHeaderParams();
        if (this.j == 10) {
            this.c.setVisibility(4);
            if (subHeaderParams != null) {
                subHeaderParams.height = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362573_res_0x7f0a030d);
                return;
            }
            return;
        }
        String g = this.i.g();
        if (this.i.d() != 1 || TextUtils.isEmpty(g)) {
            this.c.setVisibility(4);
            if (subHeaderParams != null) {
                subHeaderParams.height = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362573_res_0x7f0a030d);
                return;
            }
            return;
        }
        this.c.setVisibility(0);
        this.c.setHeadTitleText(g);
        if (this.j != 12) {
            if (!TextUtils.isEmpty(this.i.a())) {
                this.c.setMoreLayoutVisibility(0);
                this.c.setMoreViewClickListener(this);
            } else {
                this.c.setMoreLayoutVisibility(8);
            }
        } else if ((this.g && this.m.size() > 8) || (!this.g && this.m.size() > 4)) {
            this.c.setMoreLayoutVisibility(0);
            this.c.setMoreViewClickListener(this);
        } else {
            this.c.setMoreLayoutVisibility(8);
        }
        this.c.setSubHeaderBackgroundColor(0);
    }

    private LinearLayout.LayoutParams getSubHeaderParams() {
        ViewGroup.LayoutParams layoutParams = this.c.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 != null) {
            int i = layoutParams2.leftMargin;
            int i2 = layoutParams2.rightMargin;
            layoutParams2.leftMargin = i + ((Integer) this.h.first).intValue();
            layoutParams2.rightMargin = i2 + ((Integer) this.h.second).intValue();
        }
        return layoutParams2;
    }

    public boolean c() {
        return !koq.b(this.m);
    }

    private void e() {
        a(pfh.b(this.j, this.g, this.m.size()), pfh.d(this.j, this.g, this.m.size(), this.f9691a), pfh.e(this.j), pfh.b(this.i.h(), this.j));
    }

    private void a(int i, int i2, int i3, int[] iArr) {
        GridLayoutManager gridLayoutManager;
        if (this.e == null) {
            LogUtil.h("ConfiguredPage_ConfiguredPageLinearLayout", "setRecyclerViewLayout() mContext is null.");
            return;
        }
        if (koq.b(this.m) && koq.b(this.k)) {
            LogUtil.h("ConfiguredPage_ConfiguredPageLinearLayout", "setRecyclerViewLayout() mShowList or mGridVerticalList is empty.");
            return;
        }
        if (i < 1) {
            LogUtil.h("ConfiguredPage_ConfiguredPageLinearLayout", "setRecyclerViewLayout() gridNumber should be at least 1.");
            return;
        }
        if (this.f == 2) {
            gridLayoutManager = new GridLayoutManager(this.e, 1);
            gridLayoutManager.setOrientation(0);
        } else {
            gridLayoutManager = new GridLayoutManager(this.e, i);
            gridLayoutManager.setOrientation(1);
        }
        this.o.setLayoutManager(gridLayoutManager);
        if (this.o.getItemDecorationCount() > 0) {
            this.o.removeItemDecorationAt(0);
        }
        this.o.addItemDecoration(new ConfiguredPageItemDecoration(i2, i3, i, iArr));
        this.o.setHasFixedSize(true);
        this.o.setNestedScrollingEnabled(false);
        this.o.setVisibility(0);
    }

    public void c(cdy cdyVar) {
        if (cdyVar == null) {
            LogUtil.h("ConfiguredPage_ConfiguredPageLinearLayout", "refreshUiChanged() pageModuleObject is null");
            return;
        }
        boolean equals = this.i.toString().equals(cdyVar.toString());
        LogUtil.c("ConfiguredPage_ConfiguredPageLinearLayout", "refreshUiChanged() isSameObject = ", Boolean.valueOf(equals));
        if (equals) {
            boolean ag = nsn.ag(BaseApplication.getContext());
            int tahitiOrientation = getTahitiOrientation();
            if (this.b) {
                if (this.g == ag && this.n == tahitiOrientation) {
                    return;
                }
                this.g = ag;
                this.n = tahitiOrientation;
                if (this.j == 9) {
                    b(cdyVar);
                    return;
                } else {
                    e();
                    return;
                }
            }
            return;
        }
        b(cdyVar);
    }

    private void b() {
        HashMap hashMap = new HashMap(6);
        hashMap.put("click", "1");
        hashMap.put(CommonUtil.PAGE_TYPE, Integer.valueOf(this.i.h()));
        hashMap.put("moduleType", Integer.valueOf(this.i.b()));
        hashMap.put("moduleName", this.i.g());
        hashMap.put(Constants.BI_MODULE_ID, Integer.valueOf(this.i.f()));
        hashMap.put(CommonUtil.LAYOUT, Integer.valueOf(this.j));
        ixx.d().d(this.e, AnalyticsValue.HEALTH_CONFIGURE_PAGE_MORE_FRAGMENT.value(), hashMap, 0);
    }
}
