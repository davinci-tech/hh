package com.huawei.pluginachievement.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.manager.model.KakaRecord;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.mfm;
import defpackage.mle;
import defpackage.nrf;
import defpackage.nrt;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Marker;

/* loaded from: classes8.dex */
public class AchieveKaKaAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private LayoutInflater f8420a;
    private Context b;
    private List<KakaRecord> c;
    private Map<Integer, String> e;

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public AchieveKaKaAdapter(Context context) {
        this.b = context;
        b();
    }

    public void c(List<KakaRecord> list) {
        if (list == null) {
            this.c = new ArrayList(16);
        } else {
            this.c = list;
        }
        notifyDataSetChanged();
    }

    private void b() {
        this.f8420a = LayoutInflater.from(this.b);
        HashMap hashMap = new HashMap(16);
        this.e = hashMap;
        hashMap.put(1, this.b.getResources().getString(R.string._2130840680_res_0x7f020c68));
        this.e.put(2, this.b.getResources().getString(R.string._2130840680_res_0x7f020c68));
        this.e.put(3, this.b.getResources().getString(R.string._2130840681_res_0x7f020c69));
        this.e.put(4, this.b.getResources().getString(R.string._2130840682_res_0x7f020c6a));
        this.e.put(5, this.b.getResources().getString(R.string._2130840683_res_0x7f020c6b));
        this.e.put(6, this.b.getResources().getString(R.string._2130840684_res_0x7f020c6c));
        this.e.put(7, this.b.getResources().getString(R.string._2130840685_res_0x7f020c6d));
        this.e.put(8, this.b.getResources().getString(R.string._2130840686_res_0x7f020c6e));
        this.e.put(127, this.b.getResources().getString(R.string._2130840687_res_0x7f020c6f));
        this.e.put(128, this.b.getResources().getString(R.string._2130849005_res_0x7f022ced));
        this.e.put(9, this.b.getResources().getString(R.string._2130840772_res_0x7f020cc4));
        this.e.put(10, this.b.getResources().getString(R.string._2130840820_res_0x7f020cf4));
        this.e.put(11, this.b.getResources().getString(R.string._2130840866_res_0x7f020d22));
        this.e.put(12, this.b.getResources().getString(R.string._2130840867_res_0x7f020d23));
        this.e.put(13, this.b.getResources().getString(R.string._2130840868_res_0x7f020d24));
        this.e.put(14, this.b.getResources().getString(R.string._2130840869_res_0x7f020d25));
        this.e.put(15, this.b.getResources().getString(R.string._2130850188_res_0x7f02318c));
        this.e.put(16, this.b.getResources().getString(R.string._2130840891_res_0x7f020d3b));
        this.e.put(17, this.b.getResources().getString(R.string._2130840892_res_0x7f020d3c));
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<KakaRecord> list = this.c;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        b bVar;
        if (view == null) {
            bVar = new b();
            view2 = this.f8420a.inflate(R.layout.achieve_kk_item, (ViewGroup) null);
            bVar.e = (HealthTextView) mfm.cgM_(view2, R.id.text_time);
            bVar.c = (HealthTextView) mfm.cgM_(view2, R.id.text_desc);
            bVar.d = (HealthTextView) mfm.cgM_(view2, R.id.text_count);
            bVar.b = (HealthTextView) mfm.cgM_(view2, R.id.text_fuhao);
            bVar.f8421a = (ImageView) mfm.cgM_(view2, R.id.img);
            view2.setTag(bVar);
        } else {
            view2 = view;
            bVar = (b) view.getTag();
        }
        a(i, bVar);
        return view2;
    }

    private void a(int i, b bVar) {
        if (i < 0 || i >= this.c.size()) {
            return;
        }
        KakaRecord kakaRecord = this.c.get(i);
        if (kakaRecord.getKakaNum() > 0) {
            bVar.d.setTextColor(this.b.getResources().getColor(R.color._2131296378_res_0x7f09007a));
            bVar.b.setTextColor(this.b.getResources().getColor(R.color._2131296378_res_0x7f09007a));
            bVar.f8421a.setImageResource(R.mipmap._2131821348_res_0x7f110324);
            bVar.b.setText(Marker.ANY_NON_NULL_MARKER);
            bVar.d.setText(UnitUtil.e(kakaRecord.getKakaNum(), 1, 0));
        } else {
            bVar.d.setTextColor(this.b.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            bVar.b.setTextColor(this.b.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            bVar.f8421a.setImageResource(R.mipmap._2131821347_res_0x7f110323);
            if (nrt.a(this.b.getApplicationContext())) {
                bVar.f8421a.setImageDrawable(nrf.cJH_(bVar.f8421a.getDrawable(), this.b.getResources().getColor(R.color._2131297781_res_0x7f0905f5)));
            }
            bVar.b.setText(Constants.LINK);
            bVar.d.setText(UnitUtil.e(Math.abs(kakaRecord.getKakaNum()), 1, 0));
        }
        if (mle.i(String.valueOf(kakaRecord.getDescription()))) {
            bVar.c.setText(this.b.getResources().getString(R.string._2130850187_res_0x7f02318b));
        } else if (mle.g(String.valueOf(kakaRecord.getDescription()))) {
            if (kakaRecord.getDescription() == 3904161) {
                bVar.c.setText(this.b.getResources().getString(R.string._2130840989_res_0x7f020d9d));
            } else {
                bVar.c.setText(this.b.getResources().getString(R.string._2130840988_res_0x7f020d9c));
            }
        } else if (kakaRecord.getDescription() > 128) {
            bVar.c.setText(this.b.getResources().getString(R.string._2130840771_res_0x7f020cc3));
        } else {
            bVar.c.setText(this.e.get(Integer.valueOf(kakaRecord.getDescription())));
        }
        bVar.e.setText(UnitUtil.a("yyyy-MM-dd HH:mm:ss", kakaRecord.getOccurDate()));
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f8421a;
        private HealthTextView b;
        private HealthTextView c;
        private HealthTextView d;
        private HealthTextView e;

        b() {
        }
    }
}
