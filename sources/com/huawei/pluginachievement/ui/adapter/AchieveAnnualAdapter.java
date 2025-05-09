package com.huawei.pluginachievement.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.mfm;
import defpackage.mkx;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes9.dex */
public class AchieveAnnualAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f8418a;
    private LayoutInflater b;
    private List<Integer> c;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public AchieveAnnualAdapter(Context context, List<Integer> list) {
        this.f8418a = context;
        this.c = list;
        this.b = LayoutInflater.from(context);
    }

    public void d(List<Integer> list) {
        if (list == null) {
            this.c = new ArrayList(10);
        } else {
            this.c = list;
        }
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<Integer> list = this.c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (koq.b(this.c, i)) {
            return null;
        }
        return this.c.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        a aVar;
        if (view == null) {
            aVar = new a();
            view2 = this.b.inflate(R.layout.achieve_report_annual_item, (ViewGroup) null);
            aVar.f8419a = (HealthTextView) mfm.cgM_(view2, R.id.annual_title);
            aVar.d = (ImageView) mfm.cgM_(view2, R.id.annual_view);
            aVar.b = (HealthDivider) mfm.cgM_(view2, R.id.data_line);
            view2.setTag(aVar);
        } else {
            view2 = view;
            aVar = (a) view.getTag();
        }
        if (koq.b(this.c)) {
            return view2;
        }
        aVar.f8419a.setText(String.format(Locale.ENGLISH, this.f8418a.getString(R.string._2130840817_res_0x7f020cf1), this.c.get(i)));
        if (i == this.c.size() - 1) {
            aVar.b.setVisibility(4);
        } else {
            aVar.b.setVisibility(0);
        }
        cij_(this.c.get(i).intValue(), aVar.d);
        return view2;
    }

    private void cij_(int i, ImageView imageView) {
        double d;
        if (nsn.ag(this.f8418a)) {
            cil_(i, imageView);
            d = 0.1557377049180328d;
        } else {
            cik_(i, imageView);
            d = 0.36145574855252277d;
        }
        cin_(imageView, d);
    }

    private void cin_(ImageView imageView, double d) {
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.height = (int) ((nsn.h(this.f8418a) - nsn.c(this.f8418a, 24.0f)) * d);
        imageView.setLayoutParams(layoutParams);
    }

    private void cik_(int i, ImageView imageView) {
        switch (i) {
            case 2018:
                imageView.setBackgroundResource(R.mipmap._2131820554_res_0x7f11000a);
                break;
            case 2019:
                imageView.setBackgroundResource(R.mipmap._2131820556_res_0x7f11000c);
                break;
            case 2020:
                imageView.setBackgroundResource(R.mipmap._2131820558_res_0x7f11000e);
                break;
            default:
                cim_(imageView, i);
                break;
        }
    }

    private void cil_(int i, ImageView imageView) {
        switch (i) {
            case 2018:
                imageView.setBackgroundResource(R.mipmap._2131820555_res_0x7f11000b);
                break;
            case 2019:
                imageView.setBackgroundResource(R.mipmap._2131820557_res_0x7f11000d);
                break;
            case 2020:
                imageView.setBackgroundResource(R.mipmap._2131820559_res_0x7f11000f);
                break;
            default:
                cim_(imageView, i);
                break;
        }
    }

    private void cim_(ImageView imageView, int i) {
        String str;
        String str2;
        if (CommonUtil.cc()) {
            str2 = "/sandbox/cch5/testappCCH5/com.huawei.health.h5.annual-report-2021/static/image/entry.png";
            str = "/sandbox/cch5/testappCCH5/com.huawei.health.h5.annual-report-2021/static/image/entry-big.png";
        } else if (!CommonUtil.as() && CommonUtil.bv()) {
            str2 = "/cch5/health/com.huawei.health.h5.annual-report-2021/static/image/entry.png";
            str = "/cch5/health/com.huawei.health.h5.annual-report-2021/static/image/entry-big.png";
        } else {
            str = "/sandbox/cch5/health/com.huawei.health.h5.annual-report-2021/static/image/entry-big.png";
            str2 = "/sandbox/cch5/health/com.huawei.health.h5.annual-report-2021/static/image/entry.png";
        }
        if (i >= 2022) {
            str2 = str2.replace(String.valueOf(2021), String.valueOf(i));
            str = str.replace(String.valueOf(2021), String.valueOf(i));
        }
        LogUtil.a("PLGACHIEVE_AchieveAnnualAdapter", "setEnterPic picUrl ", str2, " bigPicUrl ", str);
        mkx.ckf_(imageView, str2, str, this.f8418a.getResources().getDrawable(R.mipmap._2131820561_res_0x7f110011));
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f8419a;
        private HealthDivider b;
        private ImageView d;

        a() {
        }
    }
}
