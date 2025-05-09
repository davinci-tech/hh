package com.huawei.ui.commonui.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.seekbar.HealthSeekBar;
import defpackage.nrf;

/* loaded from: classes9.dex */
public class HealthSpecification extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private View f8973a;
    private Context e;

    public HealthSpecification(Context context) {
        this(context, null);
        this.e = context;
        d();
    }

    public HealthSpecification(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (attributeSet == null) {
            LogUtil.h("CommonUI_HealthSpecificationSeekBar", "HealthSpecification AttributeSet is null");
        } else {
            this.e = context;
            d();
        }
    }

    private void d() {
        Context context = this.e;
        if (context == null) {
            LogUtil.h("CommonUI_HealthSpecificationSeekBar", "initView Context is null");
            return;
        }
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (layoutInflater == null) {
            LogUtil.h("CommonUI_HealthSpecificationSeekBar", "initView LayoutInflater is null");
        } else {
            this.f8973a = layoutInflater.inflate(R.layout.health_specification, this);
        }
    }

    private HealthTextView e(int i) {
        View view = this.f8973a;
        HealthTextView healthTextView = null;
        if (view == null) {
            LogUtil.h("CommonUI_HealthSpecificationSeekBar", "initValue View is null");
            return null;
        }
        if (i == 0) {
            healthTextView = (HealthTextView) view.findViewById(R.id.health_specification_value_left);
        } else if (i == 1) {
            healthTextView = (HealthTextView) view.findViewById(R.id.health_specification_value_mid);
        } else if (i == 2) {
            healthTextView = (HealthTextView) view.findViewById(R.id.health_specification_value_right);
        } else {
            LogUtil.h("CommonUI_HealthSpecificationSeekBar", "initValue id does not exist");
        }
        cML_(healthTextView);
        return healthTextView;
    }

    private HealthSeekBar e() {
        View view = this.f8973a;
        if (view == null) {
            return null;
        }
        return (HealthSeekBar) view.findViewById(R.id.health_specification_health_seek_bar);
    }

    private ImageView cMJ_(int i) {
        View view = this.f8973a;
        ImageView imageView = null;
        if (view == null) {
            LogUtil.h("CommonUI_HealthSpecificationSeekBar", "initImageView View is null");
            return null;
        }
        if (i == 0) {
            imageView = (ImageView) view.findViewById(R.id.health_specification_left);
        } else if (i == 1) {
            imageView = (ImageView) view.findViewById(R.id.health_specification_left_mid);
        } else if (i == 2) {
            imageView = (ImageView) view.findViewById(R.id.health_specification_right_mid);
        } else if (i == 3) {
            imageView = (ImageView) view.findViewById(R.id.health_specification_right);
        } else {
            LogUtil.h("CommonUI_HealthSpecificationSeekBar", "initImageView id does not exist");
        }
        cML_(imageView);
        return imageView;
    }

    private HealthTextView c(int i) {
        View view = this.f8973a;
        HealthTextView healthTextView = null;
        if (view == null) {
            LogUtil.h("CommonUI_HealthSpecificationSeekBar", "initDescription View is null");
            return null;
        }
        if (i == 0) {
            healthTextView = (HealthTextView) view.findViewById(R.id.health_specification_description_left);
        } else if (i == 1) {
            healthTextView = (HealthTextView) view.findViewById(R.id.health_specification_description_left_mid);
        } else if (i == 2) {
            healthTextView = (HealthTextView) view.findViewById(R.id.health_specification_description_right_mid);
        } else if (i == 3) {
            healthTextView = (HealthTextView) view.findViewById(R.id.health_specification_description_right);
        } else {
            LogUtil.h("CommonUI_HealthSpecificationSeekBar", "initDescription id does not exist");
        }
        cML_(healthTextView);
        return healthTextView;
    }

    private Drawable cMI_() {
        Context context = this.e;
        if (context == null) {
            LogUtil.h("CommonUI_HealthSpecificationSeekBar", "initDrawable Context is null");
            return null;
        }
        Resources resources = context.getResources();
        if (resources == null) {
            LogUtil.h("CommonUI_HealthSpecificationSeekBar", "initDrawable Resources is null");
            return null;
        }
        return resources.getDrawable(R$drawable.shape_round_rectangle_seek_bar);
    }

    private Drawable cMK_(int i) {
        Drawable cMI_ = cMI_();
        Context context = this.e;
        if (context == null || cMI_ == null) {
            LogUtil.h("CommonUI_HealthSpecificationSeekBar", "tintDrawable Context or Drawable is null");
            return null;
        }
        Resources resources = context.getResources();
        if (resources == null) {
            LogUtil.h("CommonUI_HealthSpecificationSeekBar", "tintDrawable Resources is null");
            return null;
        }
        return nrf.cJH_(cMI_, resources.getColor(i));
    }

    private void cML_(View view) {
        if (view == null) {
            LogUtil.h("CommonUI_HealthSpecificationSeekBar", "viewSetVisibility View is null");
        } else if (view.getVisibility() == 0) {
            LogUtil.h("CommonUI_HealthSpecificationSeekBar", "viewSetVisibility View do not show");
        } else {
            view.setVisibility(0);
        }
    }

    public void setProgress(int i) {
        HealthSeekBar e = e();
        if (e == null) {
            return;
        }
        e.setProgress(i);
    }

    public void setImageDrawable(int i, int i2, String str) {
        setImageDrawable(i, i2);
        setDescription(i, str);
    }

    public void setImageDrawable(int i, int i2) {
        ImageView cMJ_ = cMJ_(i);
        Drawable cMK_ = cMK_(i2);
        if (cMJ_ == null || cMK_ == null) {
            LogUtil.h("CommonUI_HealthSpecificationSeekBar", "setImageDrawable ImageView or Drawable is null");
        } else {
            cMJ_.setImageDrawable(cMK_);
        }
    }

    public void setValue(int i, String str) {
        HealthTextView e = e(i);
        if (e == null) {
            LogUtil.h("CommonUI_HealthSpecificationSeekBar", "setValue HealthTextView is null");
        } else {
            e.setText(str);
        }
    }

    public void setDescription(int i, String str) {
        HealthTextView c = c(i);
        if (c == null) {
            LogUtil.h("CommonUI_HealthSpecificationSeekBar", "setDescription HealthTextView is null");
        } else {
            c.setText(str);
        }
    }
}
