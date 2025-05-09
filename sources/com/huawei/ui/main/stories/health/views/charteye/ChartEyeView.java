package com.huawei.ui.main.stories.health.views.charteye;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.common.util.CollectionUtils;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.views.charteye.ChartEyeView;
import com.huawei.ui.main.stories.health.views.charteye.MultiViewDataObserverView;
import defpackage.ggs;
import defpackage.nrr;
import defpackage.nrs;
import defpackage.nsn;
import defpackage.qjv;
import defpackage.rzh;
import health.compact.a.LanguageUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class ChartEyeView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private Context f10268a;
    private int b;
    private DataInfos c;
    private String d;
    private String[] e;
    private boolean f;
    private MultiViewDataObserverView.OnSelectListener g;
    private boolean h;
    private boolean i;
    private boolean j;
    private MultiViewDataObserverView k;
    private PopupWindow l;
    private List<ScrollChartParentView> m;
    private PopViewList n;
    private int o;

    public ChartEyeView(Context context) {
        super(context);
        this.i = false;
        this.h = false;
        this.j = false;
        this.f = false;
        this.e = new String[]{getResources().getString(R$string.IDS_bloodsugar_continue), getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_fingertip)};
        this.f10268a = context;
    }

    public ChartEyeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = false;
        this.h = false;
        this.j = false;
        this.f = false;
        this.e = new String[]{getResources().getString(R$string.IDS_bloodsugar_continue), getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_fingertip)};
        this.f10268a = context;
    }

    public void setRefreshViewType(int i) {
        setViewType(i);
        post(new Runnable() { // from class: qss
            @Override // java.lang.Runnable
            public final void run() {
                ChartEyeView.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        if (this.f10268a == null) {
            LogUtil.c("ChartEyeView", "notifyCardContent, the context is invalid");
        } else {
            c(this.m.get(1));
        }
    }

    public void setCurrentSelected(String str) {
        if (CollectionUtils.isEmpty(this.m)) {
            LogUtil.c("ChartEyeView", "setCurrentSelected ParentViewList is null");
            return;
        }
        LogUtil.b("ChartEyeView", "setCurrentSelected selectedType is ", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("ChartEyeView", "setCurrentSelected selectedType is null");
            this.k.setSelectIndex(this.m.get(0));
        } else {
            for (int i = 0; i < this.m.size(); i++) {
                if (str.equals(this.m.get(i).getType())) {
                    this.k.setSelectIndex(this.m.get(i));
                }
            }
        }
    }

    public void b(String str) {
        this.k = null;
        removeAllViews();
        c(str);
    }

    public void d() {
        removeAllViews();
        g();
    }

    public void c(int i) {
        LogUtil.d("ChartEyeView", "showSwitchLabelGuide() enter");
        boolean z = false;
        boolean z2 = !String.valueOf(false).equals(qjv.b(this.f10268a, "IS_FIRST_IN_WEEK_OR_MONTH"));
        this.f = z2;
        DataInfos dataInfos = this.c;
        if (dataInfos == null) {
            LogUtil.c("ChartEyeView", "showSwitchLabelGuide() failed: mDataInfos is null!");
            return;
        }
        if (z2 && ((i == 1 && dataInfos.isWeekData()) || (i == 2 && this.c.isMonthData()))) {
            z = true;
        }
        if (this.j && z) {
            this.m.get(1).post(new Runnable() { // from class: qsy
                @Override // java.lang.Runnable
                public final void run() {
                    ChartEyeView.this.e();
                }
            });
        } else {
            LogUtil.d("ChartEyeView", "switchLabelGuide pop hasShown or don't need show");
        }
    }

    public /* synthetic */ void e() {
        j();
        i();
        b();
    }

    public void a() {
        PopupWindow popupWindow = this.l;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.l.dismiss();
    }

    private void j() {
        View inflate = LayoutInflater.from(this.f10268a).inflate(R.layout.blood_sugar_label_switching_guide, (ViewGroup) null);
        if (LanguageUtil.bc(this.f10268a)) {
            inflate.setBackgroundResource(R.drawable._2131427639_res_0x7f0b0137);
        } else {
            inflate.setBackgroundResource(R.drawable._2131427638_res_0x7f0b0136);
        }
        PopupWindow popupWindow = new PopupWindow(inflate, nsn.c(this.f10268a, 220.0f), -2, false);
        this.l = popupWindow;
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.l.setSoftInputMode(16);
        this.l.setOutsideTouchable(false);
        this.l.setClippingEnabled(true);
        inflate.findViewById(R.id.blood_sugar_label_switching_guide_content).setOnClickListener(new View.OnClickListener() { // from class: qsq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChartEyeView.this.dIC_(view);
            }
        });
    }

    public /* synthetic */ void dIC_(View view) {
        qjv.a(this.f10268a, "IS_FIRST_IN_WEEK_OR_MONTH", String.valueOf(false));
        this.f = false;
        this.l.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    public void b() {
        Integer num = (Integer) rzh.d("sub_tab_bottom_index_y", Integer.class);
        Integer num2 = (Integer) rzh.d("tool_bar_top_index_y", Integer.class);
        if (num == null || num2 == null || this.l == null) {
            LogUtil.c("ChartEyeView", "bottomY or topY or pop is null");
            return;
        }
        if (!this.f) {
            LogUtil.d("ChartEyeView", "popGuide dont need update");
            return;
        }
        int[] iArr = new int[2];
        this.m.get(1).getArrowView().getLocationOnScreen(iArr);
        int c = iArr[1] + nsn.c(this.f10268a, 32.0f);
        boolean z = c > num.intValue() && c + nsn.c(this.f10268a, 64.0f) < num2.intValue();
        if (!this.l.isShowing() && z) {
            i();
        } else {
            if (!this.l.isShowing() || z) {
                return;
            }
            this.l.dismiss();
        }
    }

    private void i() {
        int i;
        int c = nsn.c(this.f10268a, 24.0f);
        int c2 = nsn.c(this.f10268a, 220.0f);
        if (LanguageUtil.bc(this.f10268a)) {
            i = (-c) / 2;
        } else {
            i = ((c * 3) / 2) + (-c2);
        }
        int c3 = nsn.c(this.f10268a, 8.0f);
        this.l.showAsDropDown(this.m.get(1).getArrowView(), i, c3);
    }

    private void g() {
        Utils.init(this.f10268a);
        this.h = nsn.ac(this.f10268a);
        this.i = nsn.ag(this.f10268a);
        c("");
    }

    private ArrayList<String> getStringList() {
        ArrayList<String> arrayList = new ArrayList<>(7);
        Resources resources = getResources();
        arrayList.add(resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_fingertips_desc));
        arrayList.add(resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_limosis));
        arrayList.add(resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_meal));
        arrayList.add(resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_meal));
        arrayList.add(resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_after_meal_difference));
        arrayList.add(resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_sleep_blood_glucose));
        arrayList.add(resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_night_blood_glucose));
        return arrayList;
    }

    private ArrayList<String> getFingerList() {
        ArrayList<String> arrayList = new ArrayList<>(7);
        Resources resources = getResources();
        arrayList.add(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_fingertip));
        arrayList.add(resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_limosis));
        arrayList.add(resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_meal));
        arrayList.add(resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_meal));
        arrayList.add(resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_after_meal_difference));
        arrayList.add(resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_sleep_blood_glucose));
        arrayList.add(resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_night_blood_glucose));
        return arrayList;
    }

    private List<ScrollChartParentView> getFingerParentViewList() {
        ScrollChartParentView scrollChartParentView;
        LogUtil.b("ChartEyeView", "getFingerParentViewList init view.");
        this.m = new ArrayList(16);
        for (int i = 0; i < getFingerList().size(); i++) {
            switch (i) {
                case 0:
                    scrollChartParentView = new ScrollChartParentView(this.f10268a, getFingerList().get(i), "BLOOD_SUGAR_FINGER_TIP", getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_fingertips_desc), "BLOOD_SUGAR_FINGER_TIP");
                    break;
                case 1:
                    scrollChartParentView = new ScrollChartParentView(this.f10268a, getFingerList().get(i), "BLOOD_SUGAR_LIMOSIS", "", "BLOOD_SUGAR_FINGER_TIP");
                    break;
                case 2:
                    scrollChartParentView = new ScrollChartParentView(this.f10268a, getFingerList().get(i), "BLOOD_SUGAR_BEFORE_MEAL", "", "BLOOD_SUGAR_FINGER_TIP");
                    break;
                case 3:
                    scrollChartParentView = new ScrollChartParentView(this.f10268a, getFingerList().get(i), "BLOOD_SUGAR_AFTER_MEAL", "", "BLOOD_SUGAR_FINGER_TIP");
                    break;
                case 4:
                    scrollChartParentView = new ScrollChartParentView(this.f10268a, getFingerList().get(i), "BLOOD_SUGAR_BEFORE_AFTER_MEAL_DIFFERENCE", "", "BLOOD_SUGAR_FINGER_TIP");
                    break;
                case 5:
                    scrollChartParentView = new ScrollChartParentView(this.f10268a, getFingerList().get(i), "BLOOD_SUGAR_BEFORE_SLEEP_BlOOD_GLUCOSE", "", "BLOOD_SUGAR_FINGER_TIP");
                    break;
                case 6:
                    scrollChartParentView = new ScrollChartParentView(this.f10268a, getFingerList().get(i), "BLOOD_SUGAR_NIGHT_BLOOD_GLUGLUCOSE", "", "BLOOD_SUGAR_FINGER_TIP");
                    break;
                default:
                    scrollChartParentView = null;
                    break;
            }
            if (scrollChartParentView != null) {
                this.m.add(scrollChartParentView);
            }
        }
        return this.m;
    }

    private List<ScrollChartParentView> getParentViewList() {
        LogUtil.b("ChartEyeView", "getParentViewList init view.");
        this.m = new ArrayList(16);
        ScrollChartParentView scrollChartParentView = new ScrollChartParentView(this.f10268a, this.e[0], "BLOOD_SUGAR_CONTINUE", "", "BLOOD_SUGAR_CONTINUE");
        scrollChartParentView.getArrowView().setVisibility(8);
        ScrollChartParentView scrollChartParentView2 = new ScrollChartParentView(this.f10268a, this.e[1], "BLOOD_SUGAR_FINGER_TIP", getStringList().get(0), "BLOOD_SUGAR_CONTINUE");
        scrollChartParentView2.getDataView().setVisibility(0);
        this.m.add(scrollChartParentView);
        this.m.add(scrollChartParentView2);
        c(scrollChartParentView2);
        return this.m;
    }

    private void c(final ScrollChartParentView scrollChartParentView) {
        LogUtil.b("ChartEyeView", "initPartCard init card ViewType is ", Integer.valueOf(getViewType()));
        scrollChartParentView.getDataView().setVisibility(0);
        if (scrollChartParentView.getArrowView() == null) {
            LogUtil.c("ChartEyeView", "arrowView is null");
        } else if (getViewType() == 0) {
            scrollChartParentView.getArrowView().setVisibility(8);
        } else {
            scrollChartParentView.getArrowView().setVisibility(0);
            scrollChartParentView.getArrowView().setOnClickListener(new View.OnClickListener() { // from class: qsr
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ChartEyeView.this.dIB_(scrollChartParentView, view);
                }
            });
        }
    }

    public /* synthetic */ void dIB_(ScrollChartParentView scrollChartParentView, View view) {
        MultiViewDataObserverView multiViewDataObserverView = this.k;
        if (multiViewDataObserverView == null) {
            LogUtil.c("ChartEyeView", "initPartCard mMultiViewDataObserverView is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        LogUtil.b("ChartEyeView", "initPartCard getSelectedType is ", multiViewDataObserverView.getSelectedType());
        if ("BLOOD_SUGAR_CONTINUE".equals(this.k.getSelectedType())) {
            this.k.setSelectIndex(scrollChartParentView);
        }
        PopViewList popViewList = new PopViewList(this.f10268a, scrollChartParentView, getStringList(), 0);
        this.n = popViewList;
        b(popViewList);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(PopViewList popViewList) {
        popViewList.e(new PopViewList.PopViewClickListener() { // from class: qsw
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public final void setOnClick(int i) {
                ChartEyeView.this.b(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void b(final int i) {
        List<ScrollChartParentView> list = this.m;
        if (list == null) {
            LogUtil.c("ChartEyeView", "mScrollChartParentViewList is null");
        } else {
            final ScrollChartParentView scrollChartParentView = list.get(1);
            post(new Runnable() { // from class: qsu
                @Override // java.lang.Runnable
                public final void run() {
                    ChartEyeView.this.c(i, scrollChartParentView);
                }
            });
        }
    }

    public /* synthetic */ void c(int i, ScrollChartParentView scrollChartParentView) {
        if (this.f10268a == null) {
            LogUtil.c("ChartEyeView", "notifyCardContent, the context is invalid");
            return;
        }
        switch (i) {
            case 0:
                scrollChartParentView.setType("BLOOD_SUGAR_FINGER_TIP");
                scrollChartParentView.d(getStringList().get(0));
                break;
            case 1:
                scrollChartParentView.setType("BLOOD_SUGAR_LIMOSIS");
                scrollChartParentView.d(getStringList().get(1));
                break;
            case 2:
                scrollChartParentView.setType("BLOOD_SUGAR_BEFORE_MEAL");
                scrollChartParentView.d(getStringList().get(2));
                break;
            case 3:
                scrollChartParentView.setType("BLOOD_SUGAR_AFTER_MEAL");
                scrollChartParentView.d(getStringList().get(3));
                break;
            case 4:
                scrollChartParentView.setType("BLOOD_SUGAR_BEFORE_AFTER_MEAL_DIFFERENCE");
                scrollChartParentView.d(getStringList().get(4));
                break;
            case 5:
                scrollChartParentView.setType("BLOOD_SUGAR_BEFORE_SLEEP_BlOOD_GLUCOSE");
                scrollChartParentView.d(getStringList().get(5));
                break;
            case 6:
                scrollChartParentView.setType("BLOOD_SUGAR_NIGHT_BLOOD_GLUGLUCOSE");
                scrollChartParentView.d(getStringList().get(6));
                break;
        }
        this.k.setSelectIndex(scrollChartParentView);
        c(scrollChartParentView);
    }

    private int getMargin() {
        Context context = this.f10268a;
        if (context == null) {
            LogUtil.c("ChartEyeView", "getMargin() mContext is null.");
            return 0;
        }
        return context.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
    }

    private int getGutter() {
        return nrr.b(this.f10268a);
    }

    private int getNormalWidth() {
        if (this.f10268a == null) {
            LogUtil.c("ChartEyeView", "getNormalWidth() mContext is null.");
            return MachineControlPointResponse.OP_CODE_EXTENSION_SET_DYNAMIC_ENERGY;
        }
        int intValue = ((Integer) BaseActivity.getSafeRegionWidth().first).intValue();
        int c = nrs.c(this.f10268a);
        if (this.h) {
            c /= 2;
        }
        return nrr.d(this.f10268a, ((c - ((getMargin() + intValue) * 2)) - getGutter()) / 2);
    }

    private int getCardWidth() {
        int margin = getMargin();
        int gutter = getGutter();
        return (!this.i || this.h) ? (int) Utils.convertDpToPixel(this.b) : ((((ggs.a(this.f10268a) - (margin * 2)) - (gutter * 7)) / 8) * 2) + gutter;
    }

    private void c(String str) {
        if (!TextUtils.isEmpty(this.d) && "BLOOD_SUGAR_FINGER_TIP".equals(this.d)) {
            this.k = new MultiViewDataObserverView(this.f10268a, true);
            this.b = 154;
            getFingerParentViewList();
        } else {
            this.k = new MultiViewDataObserverView(this.f10268a, false);
            this.b = getNormalWidth();
            getParentViewList();
        }
        LogUtil.b("ChartEyeView", "mScrollChartParentViewList size is ", Integer.valueOf(this.m.size()));
        this.k.setDefaultColor(R.color._2131299241_res_0x7f090ba9);
        this.k.setDefaultBackground(R.drawable._2131427568_res_0x7f0b00f0);
        this.k.setSelectColor(R.color._2131299238_res_0x7f090ba6);
        this.k.setSelectBackground(R.drawable._2131427582_res_0x7f0b00fe);
        this.k.dIH_().setMinimumWidth(getGutter());
        this.k.dII_().setMinimumWidth(getMargin());
        LogUtil.b("ChartEyeView", "getGutter() ", Integer.valueOf(getGutter()), " getMargin() ", Integer.valueOf(getMargin()), " getHeight() ", Integer.valueOf(this.k.getHeight()));
        this.k.setCardWidth(getCardWidth());
        this.k.b(this.m);
        setCurrentSelected(str);
        MultiViewDataObserverView.OnSelectListener onSelectListener = this.g;
        if (onSelectListener != null) {
            this.k.setListener(onSelectListener);
        }
        addView(this.k, -1, -2);
    }

    public void setListener(MultiViewDataObserverView.OnSelectListener onSelectListener) {
        MultiViewDataObserverView multiViewDataObserverView;
        this.g = onSelectListener;
        if (onSelectListener == null || (multiViewDataObserverView = this.k) == null) {
            return;
        }
        multiViewDataObserverView.setListener(onSelectListener);
    }

    public int getViewType() {
        return this.o;
    }

    public void setViewType(int i) {
        this.o = i;
    }

    public void setDataInfos(DataInfos dataInfos) {
        this.c = dataInfos;
    }

    public void setIsNeedGuide(boolean z) {
        this.j = z;
    }

    public String getCardType() {
        return this.d;
    }

    public void setCardType(String str) {
        this.d = str;
    }
}
