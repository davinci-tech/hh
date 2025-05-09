package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.nrf;
import defpackage.nsn;

/* loaded from: classes4.dex */
public class RunRouteView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthDivider f3789a;
    private ImageView b;
    private Context c;
    private ImageView d;
    private HealthTextView e;
    private View g;
    private HealthTextView h;
    private HealthTextView i;

    public RunRouteView(Context context) {
        super(context);
        this.c = context;
        c(context);
    }

    public RunRouteView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = context;
        c(context);
    }

    public RunRouteView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = context;
        c(context);
    }

    private void c(Context context) {
        if (context == null) {
            LogUtil.b("Track_DetailFrag_RunRouteViewHolder", "context is null");
            return;
        }
        View inflate = View.inflate(context, R.layout.track_show_detail_fragment_running_route, this);
        this.g = inflate;
        ((HealthSubHeader) inflate.findViewById(R.id.sub_header_title)).setPaddingStartEnd(0.0f, 0.0f);
        this.e = (HealthTextView) this.g.findViewById(R.id.text_running_route_name);
        this.h = (HealthTextView) this.g.findViewById(R.id.text_view_run_route_times);
        this.b = (ImageView) this.g.findViewById(R.id.image_view_run_route);
        this.f3789a = (HealthDivider) this.g.findViewById(R.id.health_divider_run_route);
        this.d = (ImageView) this.g.findViewById(R.id.right_running_route_arrow);
        this.i = (HealthTextView) this.g.findViewById(R.id.route_running_route_state);
    }

    public void setHotPathName(String str) {
        if (str == null) {
            LogUtil.h("Track_DetailFrag_RunRouteViewHolder", "setData : hotPathName or hotPathPunchCount is null");
        } else {
            this.e.setText(str);
        }
    }

    public void setHotTrackParticipateNum(String str, int i) {
        if ("0".equals(str)) {
            this.h.setText(this.c.getResources().getString(R.string._2130840139_res_0x7f020a4b));
        } else {
            this.h.setText(this.c.getResources().getQuantityString(R.plurals._2130903137_res_0x7f030061, i, Integer.valueOf(i)));
        }
    }

    public void setHotPathDrawable(Drawable drawable) {
        if (drawable == null) {
            LogUtil.h("Track_DetailFrag_RunRouteViewHolder", "setHotPathDrawable : drawable is null");
        } else {
            nrf.cIR_(this.b, drawable, nrf.e, 0);
        }
    }

    public void setHotPathUrl(String str) {
        nrf.cIS_(this.b, str, nrf.e, 0, 0);
    }

    public void e(Boolean bool) {
        if (bool.booleanValue()) {
            this.f3789a.setVisibility(0);
            if (nsn.ae(this.c)) {
                this.d.setVisibility(8);
                return;
            }
            return;
        }
        this.f3789a.setVisibility(8);
        this.d.setVisibility(8);
    }

    public void setRouteStateShow() {
        this.d.setVisibility(8);
        this.i.setVisibility(0);
    }
}
