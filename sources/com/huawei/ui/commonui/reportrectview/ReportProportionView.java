package com.huawei.ui.commonui.reportrectview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.MotionType;
import defpackage.koq;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class ReportProportionView extends LinearLayout {
    private ReportTotalDataRectView b;
    private List<ProportionItem> c;
    private List<ProportionItemView> d;
    private LinearLayout e;

    public interface ProportionItem {
        int getBlurPriority();

        float getDataValue();

        int getImageViewId();

        MotionType getMotionType();

        int getProportionItemColor();

        String getProportionItemTitle();

        void setDataValue(float f);
    }

    public ReportProportionView(Context context) {
        super(context);
        c();
    }

    public ReportProportionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c();
    }

    public ReportProportionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        c();
    }

    private void c() {
        inflate(getContext(), R.layout.report_view_proportion, this);
        this.b = (ReportTotalDataRectView) findViewById(R.id.proportion_bar);
        this.e = (LinearLayout) findViewById(R.id.proportion_detail);
    }

    public void d(List<ProportionItem> list) {
        if (koq.b(list)) {
            LogUtil.h("ReportProportionView", "initData() proportionItems is empty.");
            return;
        }
        boolean equals = list.equals(this.c);
        if (!equals) {
            this.e.removeAllViews();
        }
        int i = -1;
        ProportionItem proportionItem = null;
        float f = 0.0f;
        for (ProportionItem proportionItem2 : list) {
            f += proportionItem2.getDataValue();
            if (proportionItem2.getBlurPriority() > i && proportionItem2.getDataValue() != 0.0f) {
                i = proportionItem2.getBlurPriority();
                proportionItem = proportionItem2;
            }
        }
        int e2 = e(list, f);
        if (!equals) {
            this.d = new ArrayList(10);
        }
        for (ProportionItem proportionItem3 : list) {
            int floor = (int) Math.floor((proportionItem3.getDataValue() * 100.0f) / f);
            if (proportionItem != null && proportionItem == proportionItem3 && e2 != 0) {
                floor += e2;
            }
            int i2 = floor;
            if (!equals) {
                ProportionItemView proportionItemView = new ProportionItemView(getContext(), proportionItem3.getImageViewId(), proportionItem3.getProportionItemTitle(), i2, proportionItem3.getProportionItemColor());
                this.d.add(proportionItemView);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
                layoutParams.gravity = 17;
                this.e.addView(proportionItemView, layoutParams);
            } else {
                this.d.get(list.indexOf(proportionItem3)).setPercent(i2);
            }
        }
        if (!equals) {
            setProportionBarColors(this.d);
        }
        setProportionBarViewData(this.d);
        this.b.postInvalidate();
        this.c = list;
    }

    private int e(List<ProportionItem> list, float f) {
        if (koq.b(list)) {
            LogUtil.h("ReportProportionView", "getPercentDiff() proportionItems is empty.");
            return 0;
        }
        if (f == 0.0f) {
            return 0;
        }
        Iterator<ProportionItem> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next() == null) {
                LogUtil.h("ReportProportionView", "getPercentDiff() item is null.");
            } else {
                i += (int) Math.floor((r3.getDataValue() * 100.0f) / f);
            }
        }
        if (i <= 100 && i < 100) {
            return 100 - i;
        }
        return 0;
    }

    private void setProportionBarViewData(List<ProportionItemView> list) {
        if (koq.b(list)) {
            LogUtil.h("ReportProportionView", "setProportionBarViewData proportionItemViews is empty.");
            return;
        }
        int size = list.size();
        if (size == 3) {
            this.b.setViewData(list.get(0).f8938a, list.get(1).f8938a, list.get(2).f8938a);
            return;
        }
        if (size == 4) {
            this.b.setViewData(list.get(0).f8938a, list.get(1).f8938a, list.get(2).f8938a, list.get(3).f8938a);
            return;
        }
        if (size == 5) {
            this.b.setViewData(list.get(0).f8938a, list.get(1).f8938a, list.get(2).f8938a, list.get(3).f8938a, list.get(4).f8938a);
            return;
        }
        if (size == 2) {
            this.b.setViewData(list.get(0).f8938a, list.get(1).f8938a);
        } else if (size == 1) {
            this.b.setViewData(list.get(0).f8938a);
        } else {
            LogUtil.h("ReportProportionView", "setProportionBarViewData size = ", Integer.valueOf(size));
        }
    }

    private void setProportionBarColors(List<ProportionItemView> list) {
        if (koq.b(list)) {
            LogUtil.h("ReportProportionView", "setProportionBarColors proportionItemViews is empty.");
            return;
        }
        int size = list.size();
        if (size == 3) {
            this.b.setColors(list.get(0).e, list.get(1).e, list.get(2).e);
            return;
        }
        if (size == 4) {
            this.b.setColors(list.get(0).e, list.get(1).e, list.get(2).e, list.get(3).e);
            return;
        }
        if (size == 5) {
            this.b.setColors(list.get(0).e, list.get(1).e, list.get(2).e, list.get(3).e, list.get(4).e);
            return;
        }
        if (size == 2) {
            this.b.setColors(list.get(0).e, list.get(1).e);
        } else if (size == 1) {
            this.b.setColors(list.get(0).e);
        } else {
            LogUtil.h("ReportProportionView", "setProportionBarColors size = ", Integer.valueOf(size));
        }
    }

    static class ProportionItemView extends LinearLayout {

        /* renamed from: a, reason: collision with root package name */
        int f8938a;
        int e;

        ProportionItemView(Context context, int i, String str, int i2, int i3) {
            super(context);
            this.f8938a = i2;
            this.e = i3;
            b(i, str, i2, i3);
        }

        private void b(int i, String str, int i2, int i3) {
            inflate(getContext(), R.layout.report_view_proportion_each, this);
            ((ImageView) findViewById(R.id.image_dot)).setBackground(BaseApplication.getContext().getDrawable(i));
            ((HealthTextView) findViewById(R.id.title)).setText(str);
            ((HealthTextView) findViewById(R.id.value)).setText(UnitUtil.e(this.f8938a, 2, 0));
        }

        public void setPercent(int i) {
            this.f8938a = i;
            ((HealthTextView) findViewById(R.id.value)).setText(UnitUtil.e(this.f8938a, 2, 0));
        }
    }

    static abstract class BaseProportionItem implements ProportionItem {
        private int mBlurPriority;
        protected Context mContext;
        private int mItemColor;
        private float mValue = 0.0f;

        BaseProportionItem(Context context, int i, int i2) {
            this.mContext = context;
            this.mBlurPriority = i;
            this.mItemColor = i2;
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public void setDataValue(float f) {
            this.mValue = f;
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public float getDataValue() {
            return this.mValue;
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public int getProportionItemColor() {
            return this.mItemColor;
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public int getBlurPriority() {
            return this.mBlurPriority;
        }
    }

    public static class d extends BaseProportionItem {
        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getBlurPriority() {
            return super.getBlurPriority();
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ float getDataValue() {
            return super.getDataValue();
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getProportionItemColor() {
            return super.getProportionItemColor();
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ void setDataValue(float f) {
            super.setDataValue(f);
        }

        public d(Context context, int i) {
            super(context, 9, i);
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public int getImageViewId() {
            return R$drawable.dot_walk_proportion;
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public String getProportionItemTitle() {
            return this.mContext.getResources().getString(R$string.IDS_main_time_line_walking);
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public MotionType getMotionType() {
            return MotionType.WALK;
        }
    }

    public static class b extends BaseProportionItem {
        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getBlurPriority() {
            return super.getBlurPriority();
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ float getDataValue() {
            return super.getDataValue();
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getProportionItemColor() {
            return super.getProportionItemColor();
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ void setDataValue(float f) {
            super.setDataValue(f);
        }

        public b(Context context, int i) {
            super(context, 8, i);
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public int getImageViewId() {
            return R$drawable.dot_run_proportion;
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public String getProportionItemTitle() {
            return this.mContext.getResources().getString(R$string.IDS_start_track_sport_type_run);
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public MotionType getMotionType() {
            return MotionType.RUN;
        }
    }

    public static class e extends BaseProportionItem {
        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getBlurPriority() {
            return super.getBlurPriority();
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ float getDataValue() {
            return super.getDataValue();
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getProportionItemColor() {
            return super.getProportionItemColor();
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ void setDataValue(float f) {
            super.setDataValue(f);
        }

        public e(Context context, int i) {
            super(context, 7, i);
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public int getImageViewId() {
            return R$drawable.dot_cycle_proportion;
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public String getProportionItemTitle() {
            return this.mContext.getResources().getString(R$string.IDS_start_track_sport_type_bike);
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public MotionType getMotionType() {
            return MotionType.BIKE;
        }
    }

    public static class a extends BaseProportionItem {
        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getBlurPriority() {
            return super.getBlurPriority();
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ float getDataValue() {
            return super.getDataValue();
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getProportionItemColor() {
            return super.getProportionItemColor();
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ void setDataValue(float f) {
            super.setDataValue(f);
        }

        public a(Context context, int i) {
            super(context, 6, i);
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public int getImageViewId() {
            return R$drawable.dot_climb_proportion;
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public String getProportionItemTitle() {
            return this.mContext.getResources().getString(R$string.IDS_fitness_data_list_activity_action_climb);
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public MotionType getMotionType() {
            return MotionType.CLIMB;
        }
    }

    public static class c extends BaseProportionItem {
        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public MotionType getMotionType() {
            return null;
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getBlurPriority() {
            return super.getBlurPriority();
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ float getDataValue() {
            return super.getDataValue();
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getProportionItemColor() {
            return super.getProportionItemColor();
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.BaseProportionItem, com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public /* bridge */ /* synthetic */ void setDataValue(float f) {
            super.setDataValue(f);
        }

        public c(Context context, int i) {
            super(context, 10, i);
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public int getImageViewId() {
            return R$drawable.dot_other_proportion;
        }

        @Override // com.huawei.ui.commonui.reportrectview.ReportProportionView.ProportionItem
        public String getProportionItemTitle() {
            return this.mContext.getResources().getString(R$string.IDS_hw_messagecenter_other);
        }
    }
}
