package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.MotionType;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.views.base.TotalDataRectView;
import defpackage.nrz;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class ProportionView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f9947a;
    private LinearLayout b;
    private TotalDataRectView c;
    private List<ProportionItemView> d;
    private List<ProportionItem> e;

    public interface ProportionItem {
        int getBlurPriority();

        float getDataValue();

        int getImageViewId();

        MotionType getMotionType();

        int getProportionItemColor();

        String getProportionItemTitle();

        void setDataValue(float f);
    }

    public ProportionView(Context context) {
        super(context);
        d();
    }

    public ProportionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d();
    }

    public ProportionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d();
    }

    private void d() {
        inflate(getContext(), R.layout.view_proportion, this);
        this.c = (TotalDataRectView) findViewById(R.id.proportion_bar);
        this.b = (LinearLayout) findViewById(R.id.proportion_detail);
        this.f9947a = (HealthTextView) findViewById(R.id.view_proportionSubHeader_title);
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(java.util.List<com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem> r9) {
        /*
            r8 = this;
            if (r9 != 0) goto Le
            java.lang.String r9 = "proportionItems is null"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            java.lang.String r0 = "ProportionView"
            com.huawei.hwlogsmodel.LogUtil.b(r0, r9)
            return
        Le:
            java.util.List<com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView$ProportionItem> r0 = r8.e
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L1b
            android.widget.LinearLayout r1 = r8.b
            r1.removeAllViews()
        L1b:
            java.util.Iterator r1 = r9.iterator()
            r2 = 0
            r3 = -1
            r4 = 0
            r5 = r4
            r4 = r2
        L24:
            boolean r6 = r1.hasNext()
            if (r6 == 0) goto L4c
            java.lang.Object r6 = r1.next()
            com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView$ProportionItem r6 = (com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem) r6
            if (r6 != 0) goto L33
            goto L24
        L33:
            float r7 = r6.getDataValue()
            float r4 = r4 + r7
            int r7 = r6.getBlurPriority()
            if (r7 <= r3) goto L24
            float r7 = r6.getDataValue()
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r7 == 0) goto L24
            int r3 = r6.getBlurPriority()
            r5 = r6
            goto L24
        L4c:
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            r2 = 0
            if (r1 == 0) goto L83
            java.util.Iterator r1 = r9.iterator()
            r3 = r2
        L56:
            boolean r6 = r1.hasNext()
            if (r6 == 0) goto L72
            java.lang.Object r6 = r1.next()
            com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView$ProportionItem r6 = (com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem) r6
            float r6 = r6.getDataValue()
            r7 = 1120403456(0x42c80000, float:100.0)
            float r6 = r6 * r7
            float r6 = r6 / r4
            double r6 = (double) r6
            double r6 = java.lang.Math.floor(r6)
            int r6 = (int) r6
            int r3 = r3 + r6
            goto L56
        L72:
            r1 = 100
            if (r3 > r1) goto L7b
            if (r3 >= r1) goto L83
            int r1 = r1 - r3
            r6 = r1
            goto L84
        L7b:
            java.lang.RuntimeException r9 = new java.lang.RuntimeException
            java.lang.String r0 = "percentSum > 100"
            r9.<init>(r0)
            throw r9
        L83:
            r6 = r2
        L84:
            r1 = r8
            r2 = r9
            r3 = r0
            r1.d(r2, r3, r4, r5, r6)
            java.util.List<com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView$ProportionItemView> r1 = r8.d
            int r1 = r1.size()
            r2 = 3
            if (r1 == r2) goto Lae
            java.util.List<com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView$ProportionItemView> r1 = r8.d
            int r1 = r1.size()
            r2 = 4
            if (r1 == r2) goto Lae
            java.util.List<com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView$ProportionItemView> r1 = r8.d
            int r1 = r1.size()
            r2 = 5
            if (r1 != r2) goto La6
            goto Lae
        La6:
            java.lang.RuntimeException r9 = new java.lang.RuntimeException
            java.lang.String r0 = "ProportionView current only support three/four/five proportions"
            r9.<init>(r0)
            throw r9
        Lae:
            if (r0 != 0) goto Lb5
            java.util.List<com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView$ProportionItemView> r0 = r8.d
            r8.setProportionBarColors(r0)
        Lb5:
            java.util.List<com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView$ProportionItemView> r0 = r8.d
            r8.setProportionBarViewData(r0)
            com.huawei.ui.main.stories.fitness.views.base.TotalDataRectView r0 = r8.c
            r0.postInvalidate()
            r8.e = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.b(java.util.List):void");
    }

    private void d(List<ProportionItem> list, boolean z, float f, ProportionItem proportionItem, int i) {
        if (!z) {
            this.d = new ArrayList();
        }
        for (ProportionItem proportionItem2 : list) {
            if (proportionItem2 != null) {
                int floor = (int) Math.floor((proportionItem2.getDataValue() * 100.0f) / f);
                if (proportionItem != null && proportionItem == proportionItem2 && i != 0) {
                    floor += i;
                }
                int i2 = floor;
                if (!z) {
                    ProportionItemView proportionItemView = new ProportionItemView(getContext(), proportionItem2.getImageViewId(), proportionItem2.getProportionItemTitle(), i2, proportionItem2.getProportionItemColor());
                    this.d.add(proportionItemView);
                    this.b.addView(proportionItemView, new LinearLayout.LayoutParams(0, -2, 1.0f));
                } else {
                    this.d.get(list.indexOf(proportionItem2)).setPercent(i2);
                }
            }
        }
    }

    public void setSubHeaderText(String str) {
        this.f9947a.setText(str);
    }

    private void setProportionBarViewData(List<ProportionItemView> list) {
        if (list == null) {
            return;
        }
        if (list.size() == 3) {
            this.c.setViewData(list.get(0).e, list.get(1).e, list.get(2).e);
            return;
        }
        if (list.size() == 4) {
            this.c.setViewData(list.get(0).e, list.get(1).e, list.get(2).e, list.get(3).e);
        } else if (list.size() == 5) {
            this.c.setViewData(list.get(0).e, list.get(1).e, list.get(2).e, list.get(3).e, list.get(4).e);
        } else {
            throw new RuntimeException("setProportionBarViewData not support:" + list.size());
        }
    }

    private void setProportionBarColors(List<ProportionItemView> list) {
        if (list.size() == 3) {
            this.c.setColors(list.get(0).d, list.get(1).d, list.get(2).d);
            return;
        }
        if (list.size() == 4) {
            this.c.setColors(list.get(0).d, list.get(1).d, list.get(2).d, list.get(3).d);
        } else if (list.size() == 5) {
            this.c.setColors(list.get(0).d, list.get(1).d, list.get(2).d, list.get(3).d, list.get(4).d);
        } else {
            throw new RuntimeException("setProportionBarColors not support:" + list.size());
        }
    }

    static class ProportionItemView extends LinearLayout {
        int d;
        int e;

        public ProportionItemView(Context context, int i, String str, int i2, int i3) {
            super(context);
            this.e = i2;
            this.d = i3;
            e(i, str, i3);
        }

        private void e(int i, String str, int i2) {
            inflate(getContext(), R.layout.view_proportion_each, this);
            dvA_((ImageView) findViewById(R.id.image), i);
            ((HealthTextView) findViewById(R.id.title)).setText(str);
            HealthTextView healthTextView = (HealthTextView) findViewById(R.id.value);
            healthTextView.setText(UnitUtil.e(this.e, 2, 0));
            healthTextView.setTextColor(i2);
        }

        public void setPercent(int i) {
            this.e = i;
            HealthTextView healthTextView = (HealthTextView) findViewById(R.id.value);
            String e = UnitUtil.e(this.e, 2, 0);
            if (healthTextView != null) {
                healthTextView.setText(e);
            }
        }

        private void dvA_(ImageView imageView, int i) {
            Context context = BaseApplication.getContext();
            if (LanguageUtil.bc(context)) {
                imageView.setBackground(nrz.cKn_(context, i));
            } else {
                imageView.setBackgroundResource(i);
            }
        }
    }

    static abstract class BaseProportionItem implements ProportionItem {
        private int mBlurPriority;
        protected Context mContext;
        private int mItemColor;
        private float mValue = 0.0f;

        public BaseProportionItem(Context context, int i, int i2) {
            this.mContext = context;
            this.mBlurPriority = i;
            this.mItemColor = i2;
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public void setDataValue(float f) {
            this.mValue = f;
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public float getDataValue() {
            return this.mValue;
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public int getProportionItemColor() {
            return this.mItemColor;
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public int getBlurPriority() {
            return this.mBlurPriority;
        }
    }

    public static class c extends BaseProportionItem {
        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public int getImageViewId() {
            return R.mipmap._2131820918_res_0x7f110176;
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getBlurPriority() {
            return super.getBlurPriority();
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ float getDataValue() {
            return super.getDataValue();
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getProportionItemColor() {
            return super.getProportionItemColor();
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ void setDataValue(float f) {
            super.setDataValue(f);
        }

        public c(Context context, int i) {
            super(context, 9, i);
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public String getProportionItemTitle() {
            return this.mContext.getResources().getString(R$string.IDS_main_time_line_walking);
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public MotionType getMotionType() {
            return MotionType.WALK;
        }
    }

    public static class d extends BaseProportionItem {
        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public int getImageViewId() {
            return R.mipmap._2131820917_res_0x7f110175;
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getBlurPriority() {
            return super.getBlurPriority();
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ float getDataValue() {
            return super.getDataValue();
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getProportionItemColor() {
            return super.getProportionItemColor();
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ void setDataValue(float f) {
            super.setDataValue(f);
        }

        public d(Context context, int i) {
            super(context, 8, i);
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public String getProportionItemTitle() {
            return this.mContext.getResources().getString(R$string.IDS_start_track_sport_type_run);
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public MotionType getMotionType() {
            return MotionType.RUN;
        }
    }

    public static class a extends BaseProportionItem {
        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public int getImageViewId() {
            return R.mipmap._2131820915_res_0x7f110173;
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getBlurPriority() {
            return super.getBlurPriority();
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ float getDataValue() {
            return super.getDataValue();
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getProportionItemColor() {
            return super.getProportionItemColor();
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ void setDataValue(float f) {
            super.setDataValue(f);
        }

        public a(Context context, int i) {
            super(context, 7, i);
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public String getProportionItemTitle() {
            return this.mContext.getResources().getString(R$string.IDS_start_track_sport_type_bike);
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public MotionType getMotionType() {
            return MotionType.BIKE;
        }
    }

    public static class b extends BaseProportionItem {
        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public int getImageViewId() {
            return R.drawable._2131429865_res_0x7f0b09e9;
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getBlurPriority() {
            return super.getBlurPriority();
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ float getDataValue() {
            return super.getDataValue();
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getProportionItemColor() {
            return super.getProportionItemColor();
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ void setDataValue(float f) {
            super.setDataValue(f);
        }

        public b(Context context, int i) {
            super(context, 6, i);
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public String getProportionItemTitle() {
            return this.mContext.getResources().getString(R$string.IDS_fitness_data_list_activity_action_climb);
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public MotionType getMotionType() {
            return MotionType.CLIMB;
        }
    }

    public static class e extends BaseProportionItem {
        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public int getImageViewId() {
            return R.mipmap._2131820916_res_0x7f110174;
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getBlurPriority() {
            return super.getBlurPriority();
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ float getDataValue() {
            return super.getDataValue();
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ int getProportionItemColor() {
            return super.getProportionItemColor();
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.BaseProportionItem, com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public /* bridge */ /* synthetic */ void setDataValue(float f) {
            super.setDataValue(f);
        }

        public e(Context context, int i) {
            super(context, 10, i);
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public String getProportionItemTitle() {
            return this.mContext.getResources().getString(R$string.IDS_device_setting_other);
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView.ProportionItem
        public MotionType getMotionType() {
            throw new RuntimeException("other not have a specified motiontype");
        }
    }
}
