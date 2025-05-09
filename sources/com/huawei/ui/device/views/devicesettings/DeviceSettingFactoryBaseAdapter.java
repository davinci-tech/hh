package com.huawei.ui.device.views.devicesettings;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.obz;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class DeviceSettingFactoryBaseAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private LayoutInflater f9313a;
    private c b;
    private List<obz> c;
    private obz d;
    private Context e;

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f9314a;
        FrameLayout b;
        FrameLayout c;
        HealthTextView d;
        ImageView e;
        HealthTextView g;
        public HealthSwitchButton h;
        HealthTextView i;
        View j;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public DeviceSettingFactoryBaseAdapter(Context context, List<obz> list) {
        this.c = list;
        this.f9313a = LayoutInflater.from(context);
        this.e = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.c.size();
    }

    @Override // android.widget.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public obz getItem(int i) {
        if (i < 0 || i >= this.c.size()) {
            return null;
        }
        return this.c.get(i);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        if (getItem(i) == null) {
            return 1;
        }
        return getItem(i).i();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        c cVar = new c();
        try {
            this.d = this.c.get(i);
            this.b = cVar;
            View cVh_ = cVh_(cVi_(cVg_(view, i), i), i);
            cVh_.setTag(cVar);
            return cVh_;
        } catch (IndexOutOfBoundsException e) {
            LogUtil.a("DeviceSettingFactoryBaseAdapter", e.getMessage());
            return null;
        }
    }

    private View cVg_(View view, int i) {
        int i2 = this.d.i();
        if (i2 == 0) {
            if (view == null) {
                view = this.f9313a.inflate(R.layout.activity_device_settings_two_title_switch_item, (ViewGroup) null);
                cVm_(view, 0);
                BaseActivity.setViewSafeRegion(false, view);
            }
            cVl_(i, view, this.d, this.b);
        } else if (i2 == 1) {
            if (view == null) {
                view = this.f9313a.inflate(R.layout.activity_device_settings_title_switch_item, (ViewGroup) null);
                cVm_(view, 1);
                BaseActivity.setViewSafeRegion(false, view);
            }
            cVl_(i, view, this.d, this.b);
        } else if (i2 == 9) {
            if (view == null) {
                view = this.f9313a.inflate(R.layout.activity_device_settings_title_switch_no_image_item, (ViewGroup) null);
                BaseActivity.setViewSafeRegion(false, view);
            }
            cVk_(view, this.d, this.b);
        }
        return view;
    }

    private void cVm_(View view, int i) {
        if (view == null) {
            return;
        }
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.setting_device_rela);
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            if (LanguageUtil.m(this.e)) {
                if (i == 0 || i == 6) {
                    layoutParams2.height = this.e.getResources().getDimensionPixelOffset(R.dimen._2131363220_res_0x7f0a0594);
                } else if (i == 7) {
                    layoutParams2.height = this.e.getResources().getDimensionPixelOffset(R.dimen._2131362462_res_0x7f0a029e);
                } else if (i == 8 || i == 9) {
                    layoutParams2.height = this.e.getResources().getDimensionPixelOffset(R.dimen._2131362441_res_0x7f0a0289);
                } else {
                    layoutParams2.height = this.e.getResources().getDimensionPixelOffset(R.dimen._2131362450_res_0x7f0a0292);
                }
            } else if (i == 0 || i == 6 || i == 7) {
                layoutParams2.height = this.e.getResources().getDimensionPixelOffset(R.dimen._2131364756_res_0x7f0a0b94);
            } else if (i == 8 || i == 9) {
                layoutParams2.height = this.e.getResources().getDimensionPixelOffset(R.dimen._2131362441_res_0x7f0a0289);
            } else {
                layoutParams2.height = this.e.getResources().getDimensionPixelOffset(R.dimen._2131364768_res_0x7f0a0ba0);
            }
            relativeLayout.setLayoutParams(layoutParams2);
        }
    }

    private View cVi_(View view, int i) {
        int i2 = this.d.i();
        if (i2 == 2) {
            return cVf_(i, view);
        }
        if (i2 == 3) {
            return cVe_(i, view);
        }
        if (i2 == 6) {
            if (view == null) {
                view = this.f9313a.inflate(R.layout.activity_device_settings_two_title_image_item, (ViewGroup) null);
                BaseActivity.setViewSafeRegion(false, view);
                cVm_(view, 6);
            }
            cVl_(i, view, this.d, this.b);
            return view;
        }
        if (i2 == 7) {
            if (view == null) {
                view = this.f9313a.inflate(R.layout.activity_device_settings_two_title_word_image, (ViewGroup) null);
                cVm_(view, 7);
                BaseActivity.setViewSafeRegion(false, view);
                if (LanguageUtil.bc(this.e)) {
                    ((ImageView) nsy.cMd_(view, R.id.settings_switch)).setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
                }
            }
            cVl_(i, view, this.d, this.b);
            return view;
        }
        if (i2 != 8) {
            return view;
        }
        if (view == null) {
            view = this.f9313a.inflate(R.layout.activity_device_settings_two_title_item, (ViewGroup) null);
            BaseActivity.setViewSafeRegion(false, view);
            if (LanguageUtil.bc(this.e)) {
                ((ImageView) nsy.cMd_(view, R.id.settings_switch)).setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            }
        }
        cVk_(view, this.d, this.b);
        return view;
    }

    private View cVe_(int i, View view) {
        if (view == null) {
            view = this.f9313a.inflate(R.layout.activity_device_settings_title_image_item, (ViewGroup) null);
            BaseActivity.setViewSafeRegion(false, view);
            if (LanguageUtil.bc(this.e)) {
                ((ImageView) nsy.cMd_(view, R.id.settings_switch)).setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            }
        }
        cVl_(i, view, this.d, this.b);
        return view;
    }

    private View cVf_(int i, View view) {
        if (view == null) {
            view = this.f9313a.inflate(R.layout.activity_device_settings_title_two_image_item, (ViewGroup) null);
            BaseActivity.setViewSafeRegion(false, view);
            if (LanguageUtil.bc(this.e)) {
                ((ImageView) nsy.cMd_(view, R.id.settings_switch)).setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            }
        }
        cVl_(i, view, this.d, this.b);
        return view;
    }

    private View cVh_(View view, int i) {
        int i2 = this.d.i();
        if (i2 == 4) {
            if (view == null) {
                view = this.f9313a.inflate(R.layout.activity_device_settings_title_image_item_divider, (ViewGroup) null);
                BaseActivity.setViewSafeRegion(false, view);
                if (LanguageUtil.bc(this.e)) {
                    ((ImageView) nsy.cMd_(view, R.id.settings_switch)).setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
                }
            }
            cVl_(i, view, this.d, this.b);
        } else if (i2 == 5) {
            if (view == null) {
                view = this.f9313a.inflate(R.layout.activity_device_settings_divider, (ViewGroup) null);
            }
            cVl_(i, view, this.d, this.b);
        } else {
            if (view == null) {
                view = this.f9313a.inflate(R.layout.activity_device_settings_title_image_item, (ViewGroup) null);
                BaseActivity.setViewSafeRegion(false, view);
            }
            cVl_(i, view, this.d, this.b);
        }
        return view;
    }

    private void a(obz obzVar, c cVar, boolean z) {
        if (cVar.d == null) {
            return;
        }
        if (obzVar.d() != null) {
            cVar.d.setText(obzVar.d());
            cVar.d.setVisibility(0);
        } else {
            cVar.d.setVisibility(8);
        }
        if (z) {
            cVar.d.setTextColor(this.e.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        } else {
            cVar.d.setTextColor(this.e.getResources().getColor(R.color._2131297783_res_0x7f0905f7));
        }
        cVar.d.setEnabled(z);
    }

    private void e(obz obzVar, c cVar, boolean z) {
        if (cVar.g == null) {
            return;
        }
        if (obzVar.j() != null) {
            cVar.g.setText(obzVar.j());
            cVar.g.setVisibility(0);
        } else {
            cVar.g.setVisibility(8);
        }
        if (z) {
            cVar.g.setTextColor(this.e.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        } else {
            cVar.g.setTextColor(this.e.getResources().getColor(R.color._2131297783_res_0x7f0905f7));
        }
        cVar.g.setEnabled(z);
    }

    private void b(obz obzVar, c cVar, boolean z) {
        if (cVar.i == null) {
            return;
        }
        if (obzVar.c() != null) {
            cVar.i.setText(obzVar.c());
            cVar.i.setVisibility(0);
        } else {
            cVar.i.setVisibility(8);
        }
        cVar.i.setEnabled(z);
        if (z) {
            cVar.i.setTextColor(this.e.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        } else {
            cVar.i.setTextColor(this.e.getResources().getColor(R.color._2131298738_res_0x7f0909b2));
        }
    }

    private void c(obz obzVar, c cVar, boolean z) {
        if (cVar.c == null || cVar.b == null) {
            return;
        }
        if (obzVar.h()) {
            String obj = cVar.f9314a.getText().toString();
            if (!TextUtils.isEmpty(obj) && obj.length() > 1) {
                cVar.c.setVisibility(8);
                cVar.b.setVisibility(0);
            } else {
                cVar.c.setVisibility(0);
                cVar.b.setVisibility(8);
            }
        } else {
            cVar.c.setVisibility(8);
            cVar.b.setVisibility(8);
        }
        cVar.c.setEnabled(z);
        cVar.b.setEnabled(z);
    }

    private void h(obz obzVar, c cVar, boolean z) {
        if (cVar.h == null) {
            return;
        }
        if (obzVar.d() != null) {
            cVar.h.setChecked(obzVar.f());
            cVar.h.setVisibility(0);
            cVar.h.setOnCheckedChangeListener(obzVar.cVn_());
        } else {
            cVar.h.setVisibility(8);
        }
        cVar.h.setEnabled(z);
    }

    private void d(obz obzVar, c cVar, boolean z) {
        if (cVar.e == null) {
            return;
        }
        if (obzVar.b() != 0) {
            cVar.e.setImageResource(obzVar.b());
            cVar.e.setVisibility(0);
        } else {
            cVar.e.setVisibility(8);
        }
        cVar.e.setEnabled(z);
    }

    private void c(c cVar, boolean z, int i) {
        if (cVar.j == null) {
            return;
        }
        if (i < this.c.size() - 1 && i >= 0) {
            cVar.j.setVisibility(0);
            if (this.c.get(i + 1).i() == 5) {
                cVar.j.setVisibility(8);
            }
        } else if (this.c.get(i).e() == 129) {
            cVar.j.setVisibility(0);
        } else {
            cVar.j.setVisibility(8);
        }
        cVar.j.setEnabled(z);
    }

    private void cVl_(int i, View view, obz obzVar, c cVar) {
        cVar.d = (HealthTextView) view.findViewById(R.id.content);
        cVar.g = (HealthTextView) view.findViewById(R.id.sub_content);
        cVar.i = (HealthTextView) view.findViewById(R.id.right_text);
        cVar.c = (FrameLayout) view.findViewById(R.id.new_tip);
        cVar.f9314a = (HealthTextView) view.findViewById(R.id.new_textview);
        cVar.b = (FrameLayout) view.findViewById(R.id.new_tip_other_language);
        cVar.h = (HealthSwitchButton) view.findViewById(R.id.switch_button);
        cVar.e = (ImageView) view.findViewById(R.id.item_icon);
        cVar.j = view.findViewById(R.id.item_line);
        boolean g = obzVar.g();
        a(obzVar, cVar, g);
        e(obzVar, cVar, g);
        b(obzVar, cVar, g);
        c(obzVar, cVar, g);
        h(obzVar, cVar, g);
        d(obzVar, cVar, g);
        c(cVar, g, i);
        cVj_(view);
    }

    private void cVk_(View view, obz obzVar, c cVar) {
        cVar.d = (HealthTextView) view.findViewById(R.id.content);
        cVar.g = (HealthTextView) view.findViewById(R.id.sub_content);
        cVar.i = (HealthTextView) view.findViewById(R.id.right_text);
        cVar.c = (FrameLayout) view.findViewById(R.id.new_tip);
        cVar.f9314a = (HealthTextView) view.findViewById(R.id.new_textview);
        cVar.b = (FrameLayout) view.findViewById(R.id.new_tip_other_language);
        cVar.h = (HealthSwitchButton) view.findViewById(R.id.switch_button);
        boolean g = obzVar.g();
        a(obzVar, cVar, g);
        e(obzVar, cVar, g);
        b(obzVar, cVar, g);
        c(obzVar, cVar, g);
        h(obzVar, cVar, g);
        cVj_(view);
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        if (i < this.c.size()) {
            return this.c.get(i).g();
        }
        return super.isEnabled(i);
    }

    private void cVj_(View view) {
        if (nsn.l()) {
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.setting_device_rela);
            if (relativeLayout == null) {
                LogUtil.h("DeviceSettingFactoryBaseAdapter", "setDeviceItemLayout relativeLayout is null");
            } else {
                BaseActivity.cancelLayoutById(relativeLayout);
                relativeLayout.setPadding((int) this.e.getResources().getDimension(R.dimen._2131364635_res_0x7f0a0b1b), 0, (int) this.e.getResources().getDimension(R.dimen._2131364634_res_0x7f0a0b1a), 0);
            }
        }
    }
}
