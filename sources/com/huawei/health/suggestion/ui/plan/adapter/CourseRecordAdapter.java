package com.huawei.health.suggestion.ui.plan.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.huawei.basefitnessadvice.model.intplan.RecordData;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.main.api.MainCommonApi;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hmf.services.codec.TypeToken;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ffy;
import defpackage.gic;
import defpackage.koq;
import defpackage.moe;
import defpackage.nrf;
import defpackage.nsf;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class CourseRecordAdapter extends BaseRecyclerAdapter<RecordData> {

    /* renamed from: a, reason: collision with root package name */
    private final Context f3242a;
    private List<RecordData> b;
    private final Type c;
    private final MainCommonApi d;

    public CourseRecordAdapter() {
        super(new ArrayList(), R.layout.item_recycler_course_record);
        this.f3242a = BaseApplication.e();
        this.b = new ArrayList();
        this.d = (MainCommonApi) Services.a("Main", MainCommonApi.class);
        this.c = new TypeToken<Map<String, String>>() { // from class: com.huawei.health.suggestion.ui.plan.adapter.CourseRecordAdapter.1
        }.getType();
    }

    public void a(List<RecordData> list) {
        this.b = list;
        refreshDataChange(list);
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.b)) {
            return 0;
        }
        return this.b.size();
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, RecordData recordData) {
        if (recyclerHolder == null || recordData == null) {
            ReleaseLogUtil.a("CourseRecordAdapter", "convert holder ", recyclerHolder, " itemData ", recordData, " position ", Integer.valueOf(i));
            return;
        }
        e(recyclerHolder, recordData);
        c(recyclerHolder, recordData);
        d(recyclerHolder, recordData);
        recyclerHolder.a(R.id.divider, i >= getItemCount() + (-1) ? 8 : 0);
    }

    private void e(RecyclerHolder recyclerHolder, RecordData recordData) {
        FrameLayout frameLayout = (FrameLayout) recyclerHolder.cwK_(R.id.course_record_icon_layout);
        ImageView imageView = (ImageView) recyclerHolder.cwK_(R.id.course_record_icon);
        int sportType = recordData.getSportType();
        if (sportType <= 0) {
            ReleaseLogUtil.a("CourseRecordAdapter", "setIcon sportType ", Integer.valueOf(sportType));
            frameLayout.setVisibility(8);
            imageView.setVisibility(8);
        } else if (this.d == null) {
            ReleaseLogUtil.a("CourseRecordAdapter", "setIcon mApi is null");
            frameLayout.setVisibility(8);
            imageView.setVisibility(8);
        } else {
            frameLayout.setVisibility(0);
            imageView.setVisibility(0);
            Drawable background = frameLayout.getBackground();
            if (background != null) {
                frameLayout.setBackground(nrf.cJH_(background, this.d.getSportIconBackgroundColor(this.f3242a, sportType)));
            }
            imageView.setImageDrawable(nrf.cJH_(this.d.getSportIconDrawable(this.f3242a, sportType), ContextCompat.getColor(this.f3242a, R.color._2131296937_res_0x7f0902a9)));
        }
    }

    private void c(RecyclerHolder recyclerHolder, RecordData recordData) {
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.course_record_name);
        int sportType = recordData.getSportType();
        if (sportType <= 0) {
            ReleaseLogUtil.a("CourseRecordAdapter", "setName sportType ", Integer.valueOf(sportType));
            healthTextView.setVisibility(8);
            return;
        }
        if (sportType == 10001) {
            String extraInfo = recordData.getExtraInfo();
            if (TextUtils.isEmpty(extraInfo)) {
                ReleaseLogUtil.a("CourseRecordAdapter", "setName json ", extraInfo);
                healthTextView.setVisibility(8);
                return;
            }
            Map map = (Map) HiJsonUtil.b(extraInfo, this.c);
            if (map == null) {
                ReleaseLogUtil.a("CourseRecordAdapter", "setName map is null");
                healthTextView.setVisibility(8);
                return;
            } else {
                healthTextView.setVisibility(0);
                healthTextView.setText((CharSequence) map.get("name"));
                return;
            }
        }
        if (this.d == null) {
            ReleaseLogUtil.a("CourseRecordAdapter", "setName mApi is null");
            healthTextView.setVisibility(8);
        } else {
            healthTextView.setVisibility(0);
            healthTextView.setText(this.d.getSportName(this.f3242a, sportType));
        }
    }

    private void d(RecyclerHolder recyclerHolder, RecordData recordData) {
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.course_record_info);
        if (healthTextView == null) {
            ReleaseLogUtil.a("CourseRecordAdapter", "setInfo textView is null");
            return;
        }
        float duration = recordData.getDuration();
        float b = moe.b(recordData.getActualCalorie());
        if (duration <= 0.0f || b <= 0.0f) {
            healthTextView.setVisibility(8);
            ReleaseLogUtil.a("CourseRecordAdapter", "setInfo duration ", Float.valueOf(duration), " kiloCalorie ", Float.valueOf(b));
        } else {
            healthTextView.setVisibility(0);
            healthTextView.setText(nsf.b(R.string._2130848718_res_0x7f022bce, ffy.b(R.plurals._2130903305_res_0x7f030109, UnitUtil.e(UnitUtil.a(duration / 60.0f, 0)), gic.e(duration)), ffy.b(R.plurals._2130903474_res_0x7f0301b2, UnitUtil.e(b), moe.i(b))));
        }
    }
}
