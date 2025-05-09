package com.huawei.ui.homehealth.runcard.trackfragments.adapters;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cat;
import defpackage.gvv;
import defpackage.kpj;
import defpackage.nrf;
import defpackage.rdn;
import defpackage.rdu;
import health.compact.a.UnitUtil;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class AutoRecordHistoryAdapter extends BaseRecyclerAdapter<rdn> {

    /* renamed from: a, reason: collision with root package name */
    private Context f9577a;
    private HashMap<Integer, BitmapDrawable> d;

    public AutoRecordHistoryAdapter(List<rdn> list, Context context) {
        super(list, R.layout.layout_sport_history_child_item);
        this.d = new HashMap<>(16);
        this.f9577a = context;
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, rdn rdnVar) {
        RelativeLayout relativeLayout = (RelativeLayout) recyclerHolder.itemView.findViewById(R.id.hw_show_health_running_history_child_item_layout);
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.setMargins(0, 20, 0, 20);
            relativeLayout.setLayoutParams(layoutParams2);
        }
        HealthDivider healthDivider = (HealthDivider) recyclerHolder.itemView.findViewById(R.id.hw_show_main_layout_sport_bottom_image_interval);
        if (i == 0) {
            healthDivider.setVisibility(8);
        } else {
            healthDivider.setVisibility(0);
        }
        dgs_(rdnVar, (ImageView) recyclerHolder.itemView.findViewById(R.id.hw_show_health_running_history_child_item_left_img), (ImageView) recyclerHolder.itemView.findViewById(R.id.hw_show_health_running_history_child_item_left_bg));
        ((HealthTextView) recyclerHolder.itemView.findViewById(R.id.hw_show_health_running_history_child_item_chief_sport_type_text)).setText(rdu.d(rdnVar.m(), this.f9577a));
        ((ImageView) recyclerHolder.itemView.findViewById(R.id.hw_show_health_running_history_track_type_img)).setVisibility(8);
        ((HealthTextView) recyclerHolder.itemView.findViewById(R.id.hw_show_health_running_history_child_item_left_date_text)).setText(a(0, rdnVar.k()));
        ((HealthTextView) recyclerHolder.itemView.findViewById(R.id.hw_show_health_running_history_child_item_date)).setText(a(2, rdnVar.k()));
        ((HealthTextView) recyclerHolder.itemView.findViewById(R.id.hw_show_health_running_history_child_item_center_text)).setText(a(1, rdnVar.f()));
        ((HealthTextView) recyclerHolder.itemView.findViewById(R.id.hw_show_health_running_history_child_item_chief_sport_data_text)).setText(c(0, rdnVar.a() / 1000.0d));
        ((HealthTextView) recyclerHolder.itemView.findViewById(R.id.hw_show_health_running_history_child_item_chief_sport_data_unit)).setText(cat.c(this.f9577a, rdnVar.a()));
        ((HealthTextView) recyclerHolder.itemView.findViewById(R.id.hw_show_health_running_history_child_item_right_text)).setText(c(1, rdnVar.d()));
        ((HealthTextView) recyclerHolder.itemView.findViewById(R.id.hw_health_sport_item_accessory_sport_data_unit)).setText(cat.e(this.f9577a));
    }

    private void dgs_(rdn rdnVar, ImageView imageView, ImageView imageView2) {
        Drawable dMu_ = rdnVar.dMu_(this.f9577a);
        Drawable Cw_ = cat.Cw_(this.f9577a, dMu_, rdnVar.m());
        Drawable cJH_ = nrf.cJH_(dMu_, this.f9577a.getColor(R.color._2131296937_res_0x7f0902a9));
        Drawable cJH_2 = nrf.cJH_(ContextCompat.getDrawable(this.f9577a, R.drawable._2131430592_res_0x7f0b0cc0), this.f9577a.getColor(R.color._2131296927_res_0x7f09029f));
        cat.Cx_(imageView, Cw_, cJH_);
        imageView.setVisibility(0);
        imageView2.setBackground(cJH_2);
    }

    private String d(int i, double d) {
        if (d != 0.0d && !cat.e(d)) {
            return null;
        }
        if (i == 0) {
            return UnitUtil.e(0.0d, 1, 2);
        }
        return UnitUtil.e(0.0d, 1, 0);
    }

    private String a(int i, long j) {
        String d = d(i, j);
        if (d != null) {
            LogUtil.a("Track_AutoRecordHistoryAdapter", "getFormatTimeData formattedZero is ", d);
            return d;
        }
        if (i == 0) {
            return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "HH:mm")).format(Long.valueOf(j));
        }
        if (i != 1) {
            return i != 2 ? "--" : DateUtils.formatDateTime(this.f9577a, j, 24);
        }
        return UnitUtil.d((int) (j / 1000));
    }

    private String c(int i, double d) {
        String d2 = d(i, d);
        if (d2 != null) {
            LogUtil.a("Track_AutoRecordHistoryAdapter", "getFormattedData formattedZero is ", d2);
            return d2;
        }
        if (i != 0) {
            return i != 1 ? i != 2 ? "--" : cat.d(d) : (d > 360000.0d || d <= 3.6d) ? "--" : gvv.a((float) kpj.d(true, 3, d));
        }
        return d(d);
    }

    private String d(double d) {
        LogUtil.a("Track_AutoRecordHistoryAdapter", "getDistanceData sportData is ", Double.valueOf(d));
        if (!UnitUtil.h()) {
            return d < 0.005d ? "--" : cat.b(d, 2);
        }
        double e = UnitUtil.e(d, 3);
        return e < 0.005d ? "--" : cat.b(e, 2);
    }
}
