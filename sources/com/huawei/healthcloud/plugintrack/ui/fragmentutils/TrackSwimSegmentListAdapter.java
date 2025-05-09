package com.huawei.healthcloud.plugintrack.ui.fragmentutils;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.huawei.health.R;
import com.huawei.hihealth.data.model.TrackSwimSegment;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.gvv;
import defpackage.gwg;
import defpackage.koq;
import defpackage.nsn;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class TrackSwimSegmentListAdapter extends BaseAdapter {
    private List<TrackSwimSegment> c;
    private Context e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public TrackSwimSegmentListAdapter(Context context, List<TrackSwimSegment> list) {
        this.c = list;
        this.e = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<TrackSwimSegment> list = this.c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        List<TrackSwimSegment> list = this.c;
        if (list == null || list.size() == 0 || i < 0 || i >= this.c.size()) {
            return null;
        }
        return this.c.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        e eVar;
        if (view == null) {
            if (this.e == null) {
                return null;
            }
            eVar = new e();
            view2 = LayoutInflater.from(this.e).inflate(R.layout.track_swim_segment_item_layout, (ViewGroup) null);
            eVar.g = (HealthTextView) view2.findViewById(R.id.text_segment_index);
            eVar.d = (HealthTextView) view2.findViewById(R.id.text_distance);
            eVar.c = (HealthTextView) view2.findViewById(R.id.text_duration);
            eVar.i = (HealthTextView) view2.findViewById(R.id.text_stroke_type);
            eVar.j = (HealthTextView) view2.findViewById(R.id.text_pull_times);
            eVar.b = (HealthTextView) view2.findViewById(R.id.text_swim_pace);
            eVar.h = (HealthTextView) view2.findViewById(R.id.text_swolf);
            eVar.f = (HealthTextView) view2.findViewById(R.id.text_swim_pace_unit);
            eVar.e = (HealthTextView) view2.findViewById(R.id.text_distance_unit);
            eVar.f3767a = (HealthDivider) view2.findViewById(R.id.swim_list_divider_bar);
            view2.setTag(eVar);
        } else {
            Object tag = view.getTag();
            if (!(tag instanceof e)) {
                LogUtil.b("Track_TrackSwimSegmentListAdapter", "object is not instanceof ViewHolder");
                return null;
            }
            view2 = view;
            eVar = (e) tag;
        }
        if (i == 0) {
            eVar.f3767a.setVisibility(8);
        } else {
            eVar.f3767a.setVisibility(0);
        }
        d(i, eVar);
        if (nsn.l()) {
            nsn.cLF_(viewGroup.getContext(), true, true, view2.findViewById(R.id.swim_basic_info), view2.findViewById(R.id.swim_divider), view2.findViewById(R.id.swim_more_info));
        }
        return view2;
    }

    private void d(int i, e eVar) {
        TrackSwimSegment trackSwimSegment;
        if (koq.b(this.c, i) || (trackSwimSegment = this.c.get(i)) == null) {
            return;
        }
        eVar.g.setText(UnitUtil.e(trackSwimSegment.requestSegmentIndex(), 1, 0));
        eVar.i.setText(gwg.aUO_(this.e.getResources(), trackSwimSegment.requestStrokeType()));
        eVar.c.setText(UnitUtil.d(trackSwimSegment.requestDuration()));
        eVar.j.setText(UnitUtil.e(trackSwimSegment.requestPullTimes(), 1, 0));
        eVar.h.setText(UnitUtil.e(trackSwimSegment.requestSwolf(), 1, 0));
        eVar.d.setText(UnitUtil.e(trackSwimSegment.requestDistance(), 1, 0));
        int requestPace = trackSwimSegment.requestPace();
        if (requestPace <= 0) {
            eVar.b.setText(gvv.e(this.e));
        } else {
            eVar.b.setText(gvv.a(requestPace));
        }
        Resources resources = this.e.getResources();
        if (UnitUtil.h()) {
            eVar.e.setText(resources.getQuantityText(R.plurals._2130903227_res_0x7f0300bb, trackSwimSegment.requestDistance()));
            eVar.f.setText(resources.getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100));
        } else {
            eVar.e.setText(resources.getString(R.string._2130841568_res_0x7f020fe0));
            eVar.f.setText(resources.getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 100, 100));
        }
    }

    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        private HealthDivider f3767a;
        private HealthTextView b;
        private HealthTextView c;
        private HealthTextView d;
        private HealthTextView e;
        private HealthTextView f;
        private HealthTextView g;
        private HealthTextView h;
        private HealthTextView i;
        private HealthTextView j;
    }
}
