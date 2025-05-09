package com.huawei.ui.device.activity.notification;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.device.interactors.NotificationPushInteractor;
import defpackage.jje;
import defpackage.jqi;
import defpackage.jrg;
import defpackage.nwx;
import defpackage.nwy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class NotificationAppAdapter extends RecyclerView.Adapter<AppViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private Context f9152a;
    private String b;
    private boolean c = true;
    private List<jje> d = new ArrayList(16);
    private String e = "";
    private boolean f = false;
    private NotificationPushInteractor i;

    public NotificationAppAdapter(Context context, String str, NotificationPushInteractor notificationPushInteractor) {
        this.f9152a = context;
        this.b = str;
        this.i = notificationPushInteractor;
    }

    public void b(String str) {
        this.e = str;
    }

    public void d(List<jje> list) {
        if (list != null) {
            this.d = list;
            notifyDataSetChanged();
        }
    }

    public void c() {
        List<jje> list = this.d;
        if (list != null) {
            list.clear();
        }
        notifyDataSetChanged();
    }

    public void a() {
        this.c = false;
        notifyDataSetChanged();
    }

    public void b() {
        this.c = true;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: cRg_, reason: merged with bridge method [inline-methods] */
    public AppViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AppViewHolder(LayoutInflater.from(this.f9152a).inflate(R.layout.notification_list_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(final AppViewHolder appViewHolder, final int i) {
        jje jjeVar = this.d.get(i);
        if (jjeVar.bHv_() == null) {
            Drawable bJa_ = jrg.bJa_(jjeVar.g());
            if (bJa_ == null) {
                bJa_ = this.f9152a.getResources().getDrawable(R.mipmap.notification_icon_sms);
                LogUtil.h("NotificationAppAdapter", "onBindViewHolder drawable is null ", jjeVar.g());
            }
            jjeVar.bHw_(bJa_);
        }
        appViewHolder.d.setImageDrawable(jjeVar.bHv_());
        nwy.d(jjeVar);
        appViewHolder.f9154a.setText(jjeVar.c());
        appViewHolder.c.setOnCheckedChangeListener(null);
        appViewHolder.c.setChecked(jjeVar.d() == 1);
        if (i == getItemCount() - 1) {
            appViewHolder.b.setVisibility(8);
        } else {
            appViewHolder.b.setVisibility(0);
        }
        b(jjeVar);
        appViewHolder.c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.notification.NotificationAppAdapter.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (i < NotificationAppAdapter.this.getItemCount()) {
                    jje jjeVar2 = (jje) NotificationAppAdapter.this.d.get(i);
                    LogUtil.a("NotificationAppAdapter", "click ", jjeVar2.g(), " switch:", Integer.valueOf(jjeVar2.d()), " isChecked:", Boolean.valueOf(z));
                    if (z != jjeVar2.d()) {
                        NotificationAppAdapter.this.i.a(jjeVar2.g(), z ? 1 : 0);
                        if (!nwy.d(NotificationAppAdapter.this.f9152a, z ? 1 : 0, jjeVar2, appViewHolder.c)) {
                            nwx.d().a(NotificationAppAdapter.this.f9152a, jjeVar2.g());
                        }
                        jjeVar2.b(z ? 1 : 0);
                    }
                    nwx.d().d(jjeVar2.g(), z, NotificationAppAdapter.this.b, NotificationAppAdapter.this.i);
                    nwx.d().e(jjeVar2.c(), z ? 1 : 0, NotificationAppAdapter.this.e);
                    if (z) {
                        nwx.d().e(NotificationAppAdapter.this.f9152a, jjeVar2.g());
                    }
                    ViewClickInstrumentation.clickOnView(compoundButton);
                    return;
                }
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        a(i);
        cRf_(appViewHolder.c, appViewHolder.d, appViewHolder.f9154a);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.d.size();
    }

    private void b(jje jjeVar) {
        int i = jjeVar.d() == 1 ? 1 : 0;
        boolean z = jjeVar.d() == 1;
        LogUtil.a("NotificationAppAdapter", "current authority:", Integer.valueOf(i), ", flag:", Integer.valueOf(jjeVar.d()), " pkg:", jjeVar.g());
        if (i != jjeVar.d()) {
            jjeVar.b(i);
            this.i.a(jjeVar.g(), i);
        }
        nwx.d().d(jjeVar.g(), z, this.b, this.i);
        nwx.d().e(jjeVar.c(), i, this.e);
    }

    private void cRf_(HealthSwitchButton healthSwitchButton, ImageView imageView, HealthTextView healthTextView) {
        if (this.c) {
            healthSwitchButton.setEnabled(true);
            imageView.setColorFilter(this.f9152a.getResources().getColor(R.color._2131296988_res_0x7f0902dc));
            healthTextView.setTextColor(this.f9152a.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        } else {
            healthSwitchButton.setEnabled(false);
            imageView.setColorFilter(this.f9152a.getResources().getColor(R.color._2131296998_res_0x7f0902e6));
            healthTextView.setTextColor(this.f9152a.getResources().getColor(R.color._2131297863_res_0x7f090647));
        }
    }

    private void a(int i) {
        LogUtil.a("NotificationAppAdapter", "getView isSetFirstOpen: ", Boolean.valueOf("true".equals(jqi.a().getSwitchSettingFromLocal("KEY_NOTIFICATION_SETTINGS_FIRST_OPEN_FLAG", 10001))), " position: ", Integer.valueOf(i), " getCount: ", Integer.valueOf(getItemCount()));
        if (getItemCount() == 0 || getItemCount() <= i) {
            return;
        }
        int d = this.i.d(this.d.get(i).g());
        LogUtil.a("NotificationAppAdapter", "packageName:", this.d.get(i).c(), " is auto: ", Integer.valueOf(this.d.get(i).d()), " is auto 2:", Integer.valueOf(d));
        if (!LogConfig.i() && !this.f) {
            NotificationContentProviderUtil.b(this.d.get(i).g(), d);
            if (i == this.d.size() - 1) {
                this.f = true;
                return;
            }
            return;
        }
        if (d == Integer.parseInt("1")) {
            NotificationContentProviderUtil.b(this.d.get(i).g(), d);
        }
    }

    public static class AppViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private final HealthTextView f9154a;
        private final ImageView b;
        private final HealthSwitchButton c;
        private final ImageView d;

        public AppViewHolder(View view) {
            super(view);
            this.d = (ImageView) view.findViewById(R.id.app_icon);
            this.b = (ImageView) view.findViewById(R.id.notification_divider);
            this.f9154a = (HealthTextView) view.findViewById(R.id.app_name);
            this.c = (HealthSwitchButton) view.findViewById(R.id.app_switch);
        }
    }
}
