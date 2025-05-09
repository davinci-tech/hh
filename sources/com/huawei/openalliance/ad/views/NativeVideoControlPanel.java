package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.utils.dd;

/* loaded from: classes9.dex */
public class NativeVideoControlPanel extends AutoScaleSizeRelativeLayout implements com.huawei.openalliance.ad.views.interfaces.v {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f7822a;
    private ImageView b;
    private View c;
    private ImageView d;
    private View e;
    private View f;
    private View g;
    private TextView h;

    @Override // com.huawei.openalliance.ad.views.interfaces.v
    public int b() {
        return R.drawable._2131428580_res_0x7f0b04e4;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.v
    public int c() {
        return R.drawable._2131428579_res_0x7f0b04e3;
    }

    public void setNonWifiAlertMsg(String str) {
        this.h.setText(str);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.v
    public View i() {
        return this.e;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.v
    public View h() {
        return this.g;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.v
    public View g() {
        return this.f;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.v
    public ImageView f() {
        return this.d;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.v
    public View e() {
        return this.c;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.v
    public ImageView d() {
        return this.b;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.v
    public void a(boolean z) {
        this.b.setVisibility(z ? 0 : 8);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.v
    public ImageView a() {
        return this.f7822a;
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.hiad_native_video_control_panel, this);
        this.e = findViewById(R.id.hiad_native_video_control_panel);
        ImageView imageView = (ImageView) findViewById(R.id.hiad_cb_sound);
        this.b = imageView;
        imageView.setImageResource(R.drawable._2131428584_res_0x7f0b04e8);
        dd.a(this.b);
        this.c = findViewById(R.id.hiad_pb_buffering);
        this.f7822a = (ImageView) findViewById(R.id.hiad_btn_play_or_pause);
        this.d = (ImageView) findViewById(R.id.hiad_iv_preview_video);
        this.f = findViewById(R.id.hiad_rl_non_wifi_alert);
        this.g = findViewById(R.id.hiad_btn_non_wifi_play);
        this.h = (TextView) findViewById(R.id.hiad_non_wifi_alert_msg);
    }

    public NativeVideoControlPanel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public NativeVideoControlPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public NativeVideoControlPanel(Context context) {
        super(context);
        a(context);
    }
}
