package com.huawei.openalliance.ad.linked.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.linked.view.a;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.views.AutoScaleSizeRelativeLayout;

/* loaded from: classes9.dex */
public class LinkedNativeViewControlPanel extends AutoScaleSizeRelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f7166a;
    private ImageView b;
    private ImageView c;
    private SeekBar d;
    private ImageView e;
    private TextView f;
    private TextView g;
    private View h;
    private ImageView i;
    private View j;
    private View k;
    private LinkedWifiAlertPlayButton l;
    private TextView m;

    public static int a() {
        return R.drawable._2131428571_res_0x7f0b04db;
    }

    public static int b() {
        return R.drawable._2131428570_res_0x7f0b04da;
    }

    public static int c() {
        return R.drawable._2131428572_res_0x7f0b04dc;
    }

    public void setNonWifiAlertMsg(String str) {
        TextView textView = this.m;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setNonWifiAlertMsg(int i) {
        TextView textView = this.m;
        if (textView != null) {
            textView.setText(i);
        }
    }

    public LinkedWifiAlertPlayButton o() {
        return this.l;
    }

    public View n() {
        return this.k;
    }

    public ImageView m() {
        return this.i;
    }

    public View l() {
        return this.h;
    }

    public TextView k() {
        return this.g;
    }

    public TextView j() {
        return this.f;
    }

    public ImageView i() {
        return this.e;
    }

    public SeekBar h() {
        return this.d;
    }

    public ImageView g() {
        return this.c;
    }

    public ImageView f() {
        return this.b;
    }

    public ImageView e() {
        return this.f7166a;
    }

    public void d() {
        a.C0197a a2 = this.l.getStyle().a();
        this.l.setTextColor(a2.b);
        this.l.setProgressDrawable(a2.f7168a);
    }

    private void a(Context context) {
        try {
            LayoutInflater.from(context).inflate(R.layout.hiad_linked_native_video_control_panel, this);
            this.j = findViewById(R.id.hiad_linked_native_video_control_panel);
            this.b = (ImageView) findViewById(R.id.hiad_linked_cb_sound);
            this.c = (ImageView) findViewById(R.id.hiad_linked_cb_fullcreen);
            this.e = (ImageView) findViewById(R.id.hiad_linked_restart);
            this.f = (TextView) findViewById(R.id.hiad_linked_video_now_time);
            this.g = (TextView) findViewById(R.id.hiad_linked_video_total_time);
            this.b.setImageResource(dd.a(true, false));
            dd.a(this.b);
            this.h = findViewById(R.id.hiad_linked_pb_buffering);
            this.f7166a = (ImageView) findViewById(R.id.hiad_linked_btn_play_or_pause);
            this.i = (ImageView) findViewById(R.id.hiad_linked_preview_video);
            this.k = findViewById(R.id.hiad_linked_non_wifi_alert);
            this.l = (LinkedWifiAlertPlayButton) findViewById(R.id.hiad_linked_btn_non_wifi_play);
            d();
            this.m = (TextView) findViewById(R.id.hiad_linked_non_wifi_alert_msg);
            SeekBar seekBar = (SeekBar) (bz.a(context).g() ? findViewById(R.id.hiad_linked_native_video_progress_hm) : findViewById(R.id.hiad_linked_native_video_progress));
            this.d = seekBar;
            seekBar.setVisibility(0);
        } catch (RuntimeException unused) {
            ho.c("LinkedNativeViewControlPanel", "init RuntimeException");
        } catch (Exception e) {
            ho.d("LinkedNativeViewControlPanel", "init " + e.getClass().getSimpleName());
        }
    }

    public LinkedNativeViewControlPanel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public LinkedNativeViewControlPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public LinkedNativeViewControlPanel(Context context) {
        super(context);
        a(context);
    }
}
