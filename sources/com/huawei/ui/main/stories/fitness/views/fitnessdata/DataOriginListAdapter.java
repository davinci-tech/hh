package com.huawei.ui.main.stories.fitness.views.fitnessdata;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.utils.FoundationCommonUtil;
import defpackage.jdn;
import defpackage.koq;
import defpackage.nsn;
import defpackage.pwb;
import defpackage.qak;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class DataOriginListAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9980a;
    private boolean b;
    private LayoutInflater d;
    private List<pwb> e = new ArrayList();

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f9981a;
        HealthTextView c;
        ImageView d;
        HealthDivider e;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public void e(boolean z) {
        this.b = z;
    }

    public DataOriginListAdapter(Context context) {
        this.d = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.e.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.e.get(i);
    }

    public void c(List<pwb> list) {
        this.e.clear();
        if (koq.c(list)) {
            this.e.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        d dVar;
        pwb pwbVar = (pwb) jdn.a(this.e.get(i));
        if (view == null) {
            view = this.d.inflate(R.layout.item_data_origin_listview, viewGroup, false);
            dVar = new d();
            dVar.d = (ImageView) view.findViewById(R.id.iv_data_type_icon);
            dVar.f9981a = (HealthTextView) view.findViewById(R.id.tv_data_origin_text);
            if (nsn.s()) {
                dVar.f9981a.setTextSize(1, 30.0f);
            }
            dVar.e = (HealthDivider) view.findViewById(R.id.data_origin_list_divider);
            dVar.c = (HealthTextView) view.findViewById(R.id.tv_sum_text);
            view.setTag(dVar);
        } else {
            dVar = (d) view.getTag();
        }
        if (this.e.size() > 0 && i == this.e.size() - 1) {
            dVar.e.setVisibility(8);
        } else {
            dVar.e.setVisibility(0);
        }
        d(pwbVar, dVar);
        return view;
    }

    private void d(pwb pwbVar, d dVar) {
        LogUtil.a("DataOriginListAdapter", "setDataIcon dataType:", Integer.valueOf(pwbVar.c()));
        c(pwbVar, dVar);
        dVar.d.setBackgroundResource(qak.a(pwbVar.c(), pwbVar.d()));
        int c = pwbVar.c();
        if (c != 32) {
            if (c != 39 && c != 41 && c != 46 && c != 51 && c != 88 && c != 395 && c != 506 && c != 35 && c != 36) {
                switch (c) {
                    case 21:
                    case 22:
                    case 23:
                        break;
                    default:
                        b(pwbVar, dVar);
                        break;
                }
            }
            e(pwbVar, dVar);
            return;
        }
        if (pwbVar.a() != null) {
            dVar.f9981a.setText(pwbVar.a());
        } else {
            dVar.f9981a.setText(R$string.IDS_origin_phone);
        }
    }

    private void c(pwb pwbVar, d dVar) {
        if ((pwbVar.b() instanceof Boolean) && !e(pwbVar)) {
            dVar.c.setVisibility(0);
            if (((Boolean) pwbVar.b()).booleanValue() && pwbVar.c() != 32) {
                dVar.c.setText(R$string.IDS_fitness_core_sleep_title);
                return;
            } else {
                dVar.c.setText(R$string.IDS_sleep_data_source_normal_sleep);
                return;
            }
        }
        if (this.f9980a && b(pwbVar.e())) {
            dVar.c.setVisibility(0);
            dVar.c.setText(R$string.IDS_fitness_step_device);
        } else if (this.b && pwbVar.b() == null && pwbVar.c() == 32) {
            dVar.c.setVisibility(0);
            dVar.c.setText(R$string.IDS_sleep_record_entry_title);
        } else {
            dVar.c.setVisibility(8);
        }
    }

    private void b(pwb pwbVar, d dVar) {
        if (e(pwbVar)) {
            dVar.f9981a.setText(R$string.IDS_manual_sleep_title);
        } else if (pwbVar.d() != null) {
            dVar.f9981a.setText(pwbVar.d());
        } else {
            dVar.f9981a.setText(R$string.IDS_hw_data_origin_unknow_device);
        }
    }

    private boolean e(pwb pwbVar) {
        return pwbVar.e() != null && pwbVar.e().equals("-1");
    }

    private void e(pwb pwbVar, d dVar) {
        if (pwbVar.d() != null) {
            dVar.f9981a.setText(pwbVar.d());
        } else {
            dVar.f9981a.setText(R$string.IDS_hw_data_origin_unknow_device);
        }
    }

    public void d(boolean z) {
        this.f9980a = z;
    }

    private boolean b(String str) {
        String androidId = FoundationCommonUtil.getAndroidId(BaseApplication.getContext());
        if (TextUtils.isEmpty(androidId)) {
            LogUtil.h("DataOriginListAdapter", "showStepSourceList isLocalDevice phoneId is error");
            return false;
        }
        return androidId.equals(str);
    }
}
