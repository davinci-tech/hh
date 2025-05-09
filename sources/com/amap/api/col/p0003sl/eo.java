package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;

/* loaded from: classes8.dex */
public final class eo extends ep implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private OfflineMapManager f1020a;
    private View b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private int g;
    private String h;

    public eo(Context context, OfflineMapManager offlineMapManager) {
        super(context);
        this.f1020a = offlineMapManager;
    }

    @Override // com.amap.api.col.p0003sl.ep
    protected final void a() {
        View a2 = eu.a(getContext(), R.plurals._2130903041_res_0x7f030001);
        this.b = a2;
        setContentView(a2);
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.amap.api.col.3sl.eo.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                eo.this.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.c = (TextView) this.b.findViewById(R.layout.abc_activity_chooser_view_list_item);
        TextView textView = (TextView) this.b.findViewById(R.layout.abc_alert_dialog_button_bar_material);
        this.d = textView;
        textView.setText("暂停下载");
        this.e = (TextView) this.b.findViewById(R.layout.abc_alert_dialog_material);
        this.f = (TextView) this.b.findViewById(R.layout.abc_alert_dialog_title_material);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
    }

    public final void a(int i, String str) {
        this.c.setText(str);
        if (i == 0) {
            this.d.setText("暂停下载");
            this.d.setVisibility(0);
            this.e.setText("取消下载");
        }
        if (i == 2) {
            this.d.setVisibility(8);
            this.e.setText("取消下载");
        } else if (i == -1 || i == 101 || i == 102 || i == 103) {
            this.d.setText("继续下载");
            this.d.setVisibility(0);
        } else if (i == 3) {
            this.d.setVisibility(0);
            this.d.setText("继续下载");
            this.e.setText("取消下载");
        } else if (i == 4) {
            this.e.setText("删除");
            this.d.setVisibility(8);
        }
        this.g = i;
        this.h = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        try {
            int id = view.getId();
            if (id == R.layout.abc_alert_dialog_button_bar_material) {
                int i = this.g;
                if (i == 0) {
                    this.d.setText("继续下载");
                    this.f1020a.pauseByName(this.h);
                } else if (i == 3 || i == -1 || i == 101 || i == 102 || i == 103) {
                    this.d.setText("暂停下载");
                    this.f1020a.downloadByCityName(this.h);
                }
                dismiss();
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (id == R.layout.abc_alert_dialog_material) {
                if (!TextUtils.isEmpty(this.h)) {
                    this.f1020a.remove(this.h);
                    dismiss();
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
            } else if (id == R.layout.abc_alert_dialog_title_material) {
                dismiss();
            }
            ViewClickInstrumentation.clickOnView(view);
        } catch (Exception e) {
            e.printStackTrace();
            ViewClickInstrumentation.clickOnView(view);
        }
    }
}
