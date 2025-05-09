package com.huawei.ui.device.views.alarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import defpackage.nsf;
import defpackage.obx;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class AlarmListAdapter extends BaseAdapter {
    private OnAlarmSwitchClickListener b;
    private Context d;
    private LayoutInflater e;
    private List<obx> c = new ArrayList(16);

    /* renamed from: a, reason: collision with root package name */
    private View.OnClickListener f9311a = new View.OnClickListener() { // from class: com.huawei.ui.device.views.alarm.AlarmListAdapter.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (AlarmListAdapter.this.b != null) {
                AlarmListAdapter.this.b.onAlarmSwitchClick(view);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };

    public interface OnAlarmSwitchClickListener {
        void onAlarmSwitchClick(View view);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 2;
    }

    public AlarmListAdapter(Context context, List<obx> list) {
        this.d = null;
        this.d = context;
        if (context != null) {
            LogUtil.a("AlarmListAdapter", "mContext != null");
        }
        d(list);
        this.e = LayoutInflater.from(context);
    }

    private void d(List<obx> list) {
        this.c.clear();
        this.c.addAll(list);
    }

    public void c(List<obx> list) {
        this.c.clear();
        this.c.addAll(list);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.c.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (i < 0 || i >= this.c.size()) {
            return null;
        }
        return this.c.get(i);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        obx obxVar = getItem(i) instanceof obx ? (obx) getItem(i) : null;
        if (obxVar != null) {
            return obxVar.f();
        }
        return -1;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        obx obxVar;
        int f;
        if (i >= 0 && i < this.c.size() && (f = (obxVar = this.c.get(i)).f()) != 0) {
            if (f == 1) {
                LogUtil.a("AlarmListAdapter", "view", view);
                if (view == null) {
                    view = this.e.inflate(R.layout.activity_alarm_list_item_black, (ViewGroup) null);
                    BaseActivity.setViewSafeRegion(false, (LinearLayout) view.findViewById(R.id.event_alarm_linear_layout));
                }
                cUU_(i, view, obxVar);
            } else {
                LogUtil.a("AlarmListAdapter", "unknow type");
            }
        }
        return view;
    }

    private void cUU_(int i, View view, obx obxVar) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.alarm_time);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.alarm_content);
        HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.alarm_repeat);
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) view.findViewById(R.id.event_alarm_switch_btn);
        if (obxVar.e() == 0) {
            healthSwitchButton.setChecked(false);
        } else if (obxVar.e() == 1) {
            healthSwitchButton.setChecked(true);
        } else {
            LogUtil.a("AlarmListAdapter", "setItemStyle unknow type");
        }
        healthSwitchButton.setTag(Integer.valueOf(i));
        if (obxVar.g() != null) {
            healthTextView.setText(obxVar.g());
            healthTextView.setVisibility(0);
        } else {
            healthTextView.setVisibility(8);
        }
        if (LanguageUtil.bc(this.d)) {
            if (obxVar.a() != null) {
                healthTextView2.setText(obxVar.a());
                healthTextView2.setVisibility(0);
            } else {
                healthTextView2.setVisibility(8);
            }
            if (obxVar.b() != null) {
                healthTextView3.setText(obxVar.b());
                healthTextView3.setVisibility(0);
            } else {
                healthTextView3.setVisibility(8);
            }
        } else {
            if (obxVar.a() != null && obxVar.b() != null) {
                if (obxVar.c() == 1 && LanguageUtil.m(this.d)) {
                    healthTextView2.setText(obxVar.a() + "，" + this.d.getResources().getString(R.string._2130850219_res_0x7f0231ab));
                } else {
                    healthTextView2.setText(nsf.b(R$string.IDS_settings_alarm_comma, obxVar.a(), obxVar.b()));
                }
                healthTextView2.setVisibility(0);
            } else {
                healthTextView2.setVisibility(8);
            }
            healthTextView3.setVisibility(8);
        }
        healthSwitchButton.setOnClickListener(this.f9311a);
    }

    public void c(OnAlarmSwitchClickListener onAlarmSwitchClickListener) {
        this.b = onAlarmSwitchClickListener;
    }
}
