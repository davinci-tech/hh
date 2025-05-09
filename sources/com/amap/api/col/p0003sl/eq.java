package com.amap.api.col.p0003sl;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.internal.view.SupportMenu;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.offlinemap.DownloadProgressView;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;

/* loaded from: classes8.dex */
public final class eq implements View.OnClickListener {
    private Context b;
    private TextView c;
    private TextView d;
    private ImageView e;
    private TextView f;
    private OfflineMapManager g;
    private OfflineMapCity h;
    private View k;
    private DownloadProgressView l;

    /* renamed from: a, reason: collision with root package name */
    private int f1022a = 0;
    private boolean i = false;
    private Handler j = new Handler() { // from class: com.amap.api.col.3sl.eq.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            try {
                eq.this.a(message.arg1, message.arg2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) throws Exception {
        if (this.f1022a == 2 && i2 > 3 && i2 < 100) {
            this.l.setVisibility(0);
            this.l.setProgress(i2);
        } else {
            this.l.setVisibility(8);
        }
        if (i == -1) {
            f();
            return;
        }
        if (i == 0) {
            if (this.f1022a == 1) {
                this.e.setVisibility(8);
                this.f.setText("下载中");
                this.f.setTextColor(Color.parseColor("#4287ff"));
                return;
            }
            j();
            return;
        }
        if (i == 1) {
            i();
            return;
        }
        if (i == 2) {
            e();
            return;
        }
        if (i == 3) {
            g();
            return;
        }
        if (i == 4) {
            h();
            return;
        }
        if (i == 6) {
            c();
        } else {
            if (i != 7) {
                switch (i) {
                    case 101:
                    case 102:
                    case 103:
                        f();
                        break;
                }
                return;
            }
            d();
        }
    }

    public eq(Context context, OfflineMapManager offlineMapManager) {
        this.b = context;
        b();
        this.g = offlineMapManager;
    }

    public final void a(int i) {
        this.f1022a = i;
    }

    public final View a() {
        return this.k;
    }

    private void b() {
        View a2 = eu.a(this.b, R.plurals._2130903042_res_0x7f030002);
        this.k = a2;
        this.l = (DownloadProgressView) a2.findViewById(R.layout.abc_list_menu_item_layout);
        this.c = (TextView) this.k.findViewById(R.layout.abc_cascading_menu_item_layout);
        this.d = (TextView) this.k.findViewById(R.layout.abc_list_menu_item_icon);
        this.e = (ImageView) this.k.findViewById(R.layout.abc_list_menu_item_checkbox);
        this.f = (TextView) this.k.findViewById(R.layout.abc_expanded_menu_layout);
        this.e.setOnClickListener(this);
    }

    public final void a(OfflineMapCity offlineMapCity) {
        if (offlineMapCity != null) {
            this.h = offlineMapCity;
            this.c.setText(offlineMapCity.getCity());
            double size = ((int) (((offlineMapCity.getSize() / 1024.0d) / 1024.0d) * 100.0d)) / 100.0d;
            this.d.setText(String.valueOf(size) + " M");
            b(this.h.getState(), this.h.getcompleteCode());
        }
    }

    private void b(int i, int i2) {
        OfflineMapCity offlineMapCity = this.h;
        if (offlineMapCity != null) {
            offlineMapCity.setState(i);
            this.h.setCompleteCode(i2);
        }
        Message message = new Message();
        message.arg1 = i;
        message.arg2 = i2;
        this.j.sendMessage(message);
    }

    private void c() {
        this.f.setVisibility(8);
        this.e.setVisibility(0);
        this.e.setImageResource(R.string._2130837506_res_0x7f020002);
    }

    private void d() {
        this.f.setVisibility(0);
        this.e.setVisibility(0);
        this.e.setImageResource(R.string._2130837506_res_0x7f020002);
        this.f.setText("已下载-有更新");
    }

    private void e() {
        if (this.f1022a == 1) {
            this.e.setVisibility(8);
            this.f.setVisibility(0);
            this.f.setText("等待中");
            this.f.setTextColor(Color.parseColor("#4287ff"));
            return;
        }
        this.f.setVisibility(0);
        this.e.setVisibility(8);
        this.f.setTextColor(Color.parseColor("#4287ff"));
        this.f.setText("等待中");
    }

    private void f() {
        this.f.setVisibility(0);
        this.e.setVisibility(8);
        this.f.setTextColor(SupportMenu.CATEGORY_MASK);
        this.f.setText("下载出现异常");
    }

    private void g() {
        this.f.setVisibility(0);
        this.e.setVisibility(8);
        this.f.setTextColor(-7829368);
        this.f.setText("暂停");
    }

    private void h() {
        this.f.setVisibility(0);
        this.e.setVisibility(8);
        this.f.setText("已下载");
        this.f.setTextColor(Color.parseColor("#898989"));
    }

    private void i() {
        if (this.f1022a == 1) {
            return;
        }
        this.f.setVisibility(0);
        this.e.setVisibility(8);
        this.f.setText("解压中");
        this.f.setTextColor(Color.parseColor("#898989"));
    }

    private void j() {
        if (this.h == null) {
            return;
        }
        this.f.setVisibility(0);
        this.f.setText("下载中");
        this.e.setVisibility(8);
        this.f.setTextColor(Color.parseColor("#4287ff"));
    }

    private void k() {
        synchronized (this) {
            this.g.pause();
            this.g.restart();
        }
    }

    private boolean l() {
        synchronized (this) {
            try {
                this.g.downloadByCityName(this.h.getCity());
            } catch (AMapException e) {
                e.printStackTrace();
                Toast.makeText(this.b, e.getErrorMessage(), 0).show();
                return false;
            }
        }
        return true;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        try {
            if (!dv.d(this.b)) {
                Toast.makeText(this.b, "无网络连接", 0).show();
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            OfflineMapCity offlineMapCity = this.h;
            if (offlineMapCity != null) {
                int state = offlineMapCity.getState();
                this.h.getcompleteCode();
                if (state == 0) {
                    k();
                    g();
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                } else if (state == 1 || state == 4) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                } else {
                    if (l()) {
                        e();
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    f();
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        } catch (Exception e) {
            e.printStackTrace();
            ViewClickInstrumentation.clickOnView(view);
        }
    }
}
