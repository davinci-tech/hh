package com.huawei.ui.main.stories.configuredpage.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cdu;
import defpackage.koq;
import defpackage.mfm;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.pfh;
import java.util.List;

/* loaded from: classes6.dex */
public class ConfiguredServerContentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthDivider f9692a;
    private Context b;
    private cdu c;
    private ImageView d;
    private LinearLayout e;
    private HealthTextView f;
    private HealthTextView i;

    public ConfiguredServerContentHolder(View view) {
        super(view);
        this.b = BaseApplication.getContext();
        this.e = (LinearLayout) mfm.cgM_(view, R.id.server_item_content_rl);
        this.i = (HealthTextView) mfm.cgM_(view, R.id.server_title);
        this.f = (HealthTextView) mfm.cgM_(view, R.id.server_content);
        this.d = (ImageView) mfm.cgM_(view, R.id.server_content_icon);
        this.f9692a = (HealthDivider) mfm.cgM_(view, R.id.server_list_content_line);
        this.e.setOnClickListener(this);
    }

    public void d(List<cdu> list, cdu cduVar, int i) {
        if (koq.b(list) || cduVar == null) {
            return;
        }
        this.c = cduVar;
        this.i.setText(cduVar.o());
        if (TextUtils.isEmpty(cduVar.d())) {
            this.f.setVisibility(8);
        } else {
            this.f.setVisibility(0);
            this.f.setText(cduVar.d());
        }
        String f = cduVar.f();
        if (!TextUtils.isEmpty(f)) {
            int c = nsn.c(this.b, 48.0f);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(c, c);
            layoutParams.gravity = 16;
            this.d.setLayoutParams(layoutParams);
            nrf.cIS_(this.d, f, (int) this.b.getResources().getDimension(R.dimen._2131362819_res_0x7f0a0403), 0, 0);
        } else {
            LogUtil.h("ConfiguredServerContentHolder", "initView() imageUrl is empty.");
        }
        if (i == list.size() - 1) {
            this.f9692a.setVisibility(8);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.e) {
            pfh.e(this.b, this.c);
        }
        ViewClickInstrumentation.clickOnView(view);
    }
}
