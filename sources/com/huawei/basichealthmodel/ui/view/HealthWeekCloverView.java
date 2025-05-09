package com.huawei.basichealthmodel.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.basichealthmodel.callback.WeekCloverItemClickListener;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ucd.cloveranimation.CloverView;
import com.huawei.ui.commonui.view.Clover;
import defpackage.awq;
import defpackage.ayd;
import defpackage.azi;
import defpackage.koq;
import defpackage.nsf;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class HealthWeekCloverView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private SparseIntArray f1926a;
    private WeekCloverItemClickListener b;
    private SparseArray<Clover> d;
    private int e;

    public HealthWeekCloverView(Context context) {
        this(context, null);
    }

    public HealthWeekCloverView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public HealthWeekCloverView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1926a = new SparseIntArray();
        this.d = new SparseArray<>();
        b();
    }

    public void setWeekCloverItemClickListener(WeekCloverItemClickListener weekCloverItemClickListener) {
        this.b = weekCloverItemClickListener;
    }

    public void setPageNumber(int i) {
        this.f1926a.clear();
        this.f1926a = azi.lL_(i);
    }

    private void b() {
        View cKr_ = nsf.cKr_(BaseApplication.e(), R.layout.item_week_calendar_clover_view, this);
        if (cKr_ == null) {
            LogUtil.h("HealthLife_HealthWeekCloverView", "initView view is null");
            return;
        }
        lD_(cKr_);
        this.f1926a = azi.lL_(0);
        this.e = awq.e().e(DateFormatUtil.b(System.currentTimeMillis()));
    }

    private void lD_(View view) {
        Clover clover = (Clover) view.findViewById(R.id.item_week_clover_monday);
        Clover clover2 = (Clover) view.findViewById(R.id.item_week_clover_thuesday);
        Clover clover3 = (Clover) view.findViewById(R.id.item_week_clover_wedesday);
        Clover clover4 = (Clover) view.findViewById(R.id.item_week_clover_thursday);
        Clover clover5 = (Clover) view.findViewById(R.id.item_week_clover_friday);
        Clover clover6 = (Clover) view.findViewById(R.id.item_week_clover_saturday);
        Clover clover7 = (Clover) view.findViewById(R.id.item_week_clover_sunday);
        clover.setPetalClickListener(new e(2));
        clover2.setPetalClickListener(new e(3));
        clover3.setPetalClickListener(new e(4));
        clover4.setPetalClickListener(new e(5));
        clover5.setPetalClickListener(new e(6));
        clover6.setPetalClickListener(new e(7));
        clover7.setPetalClickListener(new e(1));
        this.d.put(2, clover);
        this.d.put(3, clover2);
        this.d.put(4, clover3);
        this.d.put(5, clover4);
        this.d.put(6, clover5);
        this.d.put(7, clover6);
        this.d.put(1, clover7);
    }

    public void setCloverToDateData(SparseIntArray sparseIntArray) {
        this.f1926a = sparseIntArray;
    }

    public void setCloverMapData(Map<Integer, List<ayd>> map) {
        c(map);
    }

    private void b(Clover clover, List<ayd> list) {
        if (clover == null || koq.b(list)) {
            LogUtil.h("HealthLife_HealthWeekCloverView", "notifyItemView clover = null or list is empty");
            return;
        }
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            ayd aydVar = list.get(i2);
            if (aydVar != null) {
                i = aydVar.b();
                int c = aydVar.c();
                if (c == 1) {
                    f3 = aydVar.a();
                } else if (c == 2) {
                    f2 = aydVar.a();
                } else if (c == 3) {
                    f = aydVar.a();
                } else {
                    LogUtil.h("HealthLife_HealthWeekCloverView", "notifyItemView error dayTaskStatus");
                }
            }
        }
        if (i > 0) {
            clover.d(i == 4);
            clover.setAllFull(true);
        } else {
            clover.setAllFull(false);
        }
        clover.setClover(f3, f, f2);
    }

    public void e(List<ayd> list) {
        int e2 = awq.e().e(DateFormatUtil.b(System.currentTimeMillis()));
        LogUtil.a("HealthLife_HealthWeekCloverView", "notifyItemView nowDay = ", Integer.valueOf(e2));
        if (this.f1926a.get(e2) <= 0) {
            LogUtil.h("HealthLife_HealthWeekCloverView", "notifyItemView mCloverToDate not contains nowDay");
            return;
        }
        int i = this.f1926a.get(e2);
        Clover clover = this.d.get(i);
        LogUtil.a("HealthLife_HealthWeekCloverView", "notifyItemView notify nowDay index = ", Integer.valueOf(i));
        b(clover, list);
    }

    private void c(Map<Integer, List<ayd>> map) {
        if (this.f1926a == null || map == null || map.size() == 0) {
            LogUtil.a("HealthLife_HealthWeekCloverView", "notifyMapDataChange map data is null resetEmptyClover");
            d();
            return;
        }
        LogUtil.a("HealthLife_HealthWeekCloverView", "notifyMapDataChange cloverDataMap ", map, " mCloverToDate ", this.f1926a);
        int size = this.f1926a.size();
        for (int i = 0; i < size; i++) {
            int keyAt = this.f1926a.keyAt(i);
            Clover clover = this.d.get(this.f1926a.get(keyAt));
            if (clover != null) {
                if (b(keyAt)) {
                    if (map.containsKey(Integer.valueOf(keyAt))) {
                        List<ayd> list = map.get(Integer.valueOf(keyAt));
                        LogUtil.a("HealthLife_HealthWeekCloverView", "notifyMapDataChange the date is effective date and begin notifyItemView = ", Integer.valueOf(keyAt));
                        b(clover, list);
                    } else {
                        clover.setClover(0.0f, 0.0f, 0.0f);
                    }
                } else {
                    clover.setInvalid(true);
                }
            }
        }
    }

    private void d() {
        SparseIntArray sparseIntArray = this.f1926a;
        if (sparseIntArray == null) {
            return;
        }
        int size = sparseIntArray.size();
        for (int i = 0; i < size; i++) {
            int keyAt = this.f1926a.keyAt(i);
            Clover clover = this.d.get(this.f1926a.get(keyAt));
            if (clover != null) {
                if (b(keyAt)) {
                    clover.setClover(0.0f, 0.0f, 0.0f);
                } else {
                    clover.setInvalid(true);
                }
            }
        }
    }

    public void a() {
        SparseArray<Clover> sparseArray = this.d;
        if (sparseArray == null) {
            return;
        }
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            SparseArray<Clover> sparseArray2 = this.d;
            Clover clover = sparseArray2.get(sparseArray2.keyAt(i));
            if (clover != null) {
                clover.setClover(0.0f, 0.0f, 0.0f);
            }
        }
    }

    private boolean b(int i) {
        int i2 = this.e;
        if (i2 == 0) {
            i2 = DateFormatUtil.b(System.currentTimeMillis());
        }
        int t = azi.t();
        if (t == 0) {
            t = i2;
        }
        return i >= t && i <= i2;
    }

    static class e implements CloverView.OnPetalClickListener {
        private final int c;
        private final WeakReference<HealthWeekCloverView> e;

        private e(HealthWeekCloverView healthWeekCloverView, int i) {
            this.e = new WeakReference<>(healthWeekCloverView);
            this.c = i;
        }

        private void a() {
            HealthWeekCloverView healthWeekCloverView = this.e.get();
            if (healthWeekCloverView != null) {
                WeekCloverItemClickListener weekCloverItemClickListener = healthWeekCloverView.b;
                if (weekCloverItemClickListener == null) {
                    LogUtil.h("HealthLife_HealthWeekCloverView", "onCloverItemClick listener is null");
                    return;
                } else {
                    weekCloverItemClickListener.onCloverItemClick(this.c);
                    return;
                }
            }
            LogUtil.h("HealthLife_HealthWeekCloverView", "onCloverItemClick weekCloverView is null");
        }

        @Override // com.huawei.ucd.cloveranimation.CloverView.OnPetalClickListener
        public void onTopClick() {
            a();
        }

        @Override // com.huawei.ucd.cloveranimation.CloverView.OnPetalClickListener
        public void onLeftClick() {
            a();
        }

        @Override // com.huawei.ucd.cloveranimation.CloverView.OnPetalClickListener
        public void onRightClick() {
            a();
        }
    }
}
