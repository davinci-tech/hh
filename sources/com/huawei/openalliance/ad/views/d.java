package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.operation.share.HiHealthError;

/* loaded from: classes9.dex */
public class d extends RelativeLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private TextView f8054a;
    private ImageView b;
    private ImageView c;
    private a d;

    public interface a {
        void c();

        void onMenuBtnClick(View view);
    }

    public void setTitle(String str) {
        if (this.f8054a == null || cz.b(str)) {
            return;
        }
        this.f8054a.setText(str);
    }

    public void setCallBack(a aVar) {
        this.d = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        a aVar = this.d;
        if (aVar != null) {
            if (view == this.c) {
                aVar.c();
            } else {
                ImageView imageView = this.b;
                if (view == imageView) {
                    aVar.onMenuBtnClick(imageView);
                }
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void a(boolean z) {
        TextView textView = this.f8054a;
        if (textView != null) {
            textView.setVisibility(z ? 0 : 4);
        }
    }

    private void a(Context context, boolean z) {
        LayoutInflater.from(context).inflate(R.layout.hiad_custom_emui_action_bar, this);
        this.c = (ImageView) findViewById(R.id.hiad_id_back_btn);
        this.f8054a = (TextView) findViewById(R.id.hiad_id_title_tv);
        ImageView imageView = (ImageView) findViewById(R.id.hiad_id_menu_btn);
        this.b = imageView;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 4);
        }
    }

    private void a() {
        ImageView imageView = this.c;
        if (imageView != null) {
            imageView.setOnClickListener(this);
        }
        ImageView imageView2 = this.b;
        if (imageView2 != null) {
            imageView2.setOnClickListener(this);
        }
    }

    public d(Context context, boolean z) {
        super(context);
        setBackgroundColor(Color.rgb(0, HiHealthError.ERR_WRONG_DEVICE, 168));
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen._2131363334_res_0x7f0a0606);
        setPadding(dimensionPixelSize, ao.h(context) + dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        a(context, z);
        a();
    }
}
