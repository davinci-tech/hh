package com.huawei.ui.main.stories.history.view;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.rdu;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class SportArrayAdapter extends ArrayAdapter<String> {
    private final List<Integer> c;

    public SportArrayAdapter(Context context, int i, String[] strArr, List<Integer> list) {
        super(context, i, strArr);
        this.c = list;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sport_spinner, viewGroup, false);
        ((LinearLayout) inflate.findViewById(R.id.sport_spinner_content)).setLayoutParams(new LinearLayout.LayoutParams(-2, (int) getContext().getResources().getDimension(R.dimen._2131363060_res_0x7f0a04f4)));
        dMG_(i, inflate);
        return inflate;
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sport_spinner, viewGroup, false);
        HealthTextView dMG_ = dMG_(i, inflate);
        dMG_.setTextSize(0, getContext().getResources().getDimension(R.dimen._2131362705_res_0x7f0a0391));
        dMG_.setTypeface(Typeface.create("emui_text_font_family_regular", 0));
        dMF_(i, inflate);
        return inflate;
    }

    private void dMF_(int i, View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.sport_icon);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.sport_icon_bg);
        view.findViewById(R.id.sport_icon_layout).setVisibility(0);
        if (i == 0) {
            imageView2.setVisibility(0);
            dMC_(imageView2, R.drawable.ic_health_list_all);
        } else {
            dME_(imageView, this.c.get(i).intValue(), imageView2);
        }
    }

    private void dME_(ImageView imageView, int i, ImageView imageView2) {
        Context e = BaseApplication.e();
        int b = rdu.b(e, i);
        Drawable dMy_ = rdu.dMy_(e, i);
        if (i == 512) {
            imageView.setVisibility(8);
            dMD_(imageView2, dMy_);
        } else {
            imageView.setVisibility(0);
            dMD_(imageView, dMy_);
            imageView2.setBackground(nrf.cJH_(getContext().getDrawable(R.drawable._2131431545_res_0x7f0b1079), b));
        }
    }

    private HealthTextView dMG_(int i, View view) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.sport_text);
        healthTextView.setText(getItem(i));
        return healthTextView;
    }

    private void dMC_(ImageView imageView, int i) {
        Context e = BaseApplication.e();
        if (LanguageUtil.bc(e)) {
            BitmapDrawable cKm_ = nrz.cKm_(e, ContextCompat.getDrawable(e, i));
            if (cKm_ != null) {
                imageView.setImageDrawable(cKm_);
                return;
            }
            return;
        }
        imageView.setImageResource(i);
    }

    private void dMD_(ImageView imageView, Drawable drawable) {
        Context e = BaseApplication.e();
        if (LanguageUtil.bc(e)) {
            BitmapDrawable cKm_ = nrz.cKm_(e, drawable);
            if (cKm_ != null) {
                imageView.setImageDrawable(cKm_);
                return;
            }
            return;
        }
        imageView.setImageDrawable(drawable);
    }
}
