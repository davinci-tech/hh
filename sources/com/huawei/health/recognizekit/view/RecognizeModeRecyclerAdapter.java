package com.huawei.health.recognizekit.view;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsk;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class RecognizeModeRecyclerAdapter extends BaseRecyclerAdapter<String> {
    Context b;
    HashMap<Integer, Float> c;
    int e;

    public RecognizeModeRecyclerAdapter(List<String> list) {
        super(list, R.layout.item_recognize_tab);
        this.c = new HashMap<>();
        this.e = 0;
        this.b = BaseApplication.e();
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerHolder recyclerHolder, int i) {
        super.onBindViewHolder(recyclerHolder, i);
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.recognize_tab_tv);
        if (e() == i) {
            healthTextView.setTextColor(this.b.getResources().getColor(R.color._2131299238_res_0x7f090ba6));
            healthTextView.setTypeface(nsk.cKO_());
        } else {
            healthTextView.setTextColor(this.b.getResources().getColor(R.color._2131299243_res_0x7f090bab));
            healthTextView.setTypeface(nsk.cKP_());
        }
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, String str) {
        recyclerHolder.b(R.id.recognize_tab_tv, str);
        this.c.put(Integer.valueOf(i), Float.valueOf(((HealthTextView) recyclerHolder.cwK_(R.id.recognize_tab_tv)).getMeasureWidth()));
    }

    public float d(int i) {
        if (this.c.containsKey(Integer.valueOf(i))) {
            return this.c.get(Integer.valueOf(i)).floatValue();
        }
        return 0.0f;
    }

    public void b(int i) {
        this.e = i;
    }

    private int e() {
        return this.e;
    }
}
