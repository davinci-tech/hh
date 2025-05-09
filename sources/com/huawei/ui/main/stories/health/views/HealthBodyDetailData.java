package com.huawei.ui.main.stories.health.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.line.HealthLine;
import com.huawei.ui.commonui.oval.HealthOval;

/* loaded from: classes7.dex */
public class HealthBodyDetailData extends RelativeLayout {
    private Context b;
    private int c;
    private View d;
    private HealthBodyDetail e;

    public HealthBodyDetailData(Context context) {
        this(context, null);
        a(context);
    }

    public HealthBodyDetailData(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (attributeSet == null) {
            LogUtil.h("HealthBodyDetailData", "HealthBodyDetailData AttributeSet is null");
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099781_res_0x7f060085});
        try {
            this.c = obtainStyledAttributes.getInteger(0, 0);
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("HealthBodyDetailData", "HealthBodyDetailData Resources NotFoundException");
        }
        obtainStyledAttributes.recycle();
        a(context);
    }

    private void a(Context context) {
        if (context == null) {
            LogUtil.h("HealthBodyDetailData", "initBodyDetailView Context is null");
            return;
        }
        this.b = context;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (layoutInflater == null) {
            LogUtil.h("HealthBodyDetailData", "initBodyDetailView LayoutInflater is null");
            return;
        }
        int i = this.c;
        if (i == 0) {
            this.d = layoutInflater.inflate(R.layout.health_body_detail_data, this);
        } else if (i == 2) {
            this.d = layoutInflater.inflate(R.layout.health_body_detail_data_report_oversea, this);
        } else {
            this.d = layoutInflater.inflate(R.layout.health_body_detail_data_report, this);
        }
        this.e = (HealthBodyDetail) this.d.findViewById(R.id.health_body_detail_data_body);
    }

    private void c(int i) {
        HealthOval healthOval;
        View view = this.d;
        if (view == null) {
            LogUtil.h("HealthBodyDetailData", "initHealthOval View is null");
            return;
        }
        if (i == 1) {
            healthOval = (HealthOval) view.findViewById(R.id.health_body_detail_data_oval_trunk);
        } else if (i == 2) {
            healthOval = (HealthOval) view.findViewById(R.id.health_body_detail_data_oval_left_hand);
        } else if (i == 3) {
            healthOval = (HealthOval) view.findViewById(R.id.health_body_detail_data_oval_right_hand);
        } else if (i == 4) {
            healthOval = (HealthOval) view.findViewById(R.id.health_body_detail_data_oval_left_leg);
        } else if (i == 5) {
            healthOval = (HealthOval) view.findViewById(R.id.health_body_detail_data_oval_right_leg);
        } else {
            LogUtil.h("HealthBodyDetailData", "initHealthOval id does not exist");
            healthOval = null;
        }
        dIr_(healthOval);
    }

    private void b(int i) {
        HealthLine healthLine;
        View view = this.d;
        if (view == null) {
            LogUtil.h("HealthBodyDetailData", "initHealthLine View is null");
            return;
        }
        if (i == 1) {
            healthLine = (HealthLine) view.findViewById(R.id.health_body_detail_data_line_trunk);
        } else if (i == 2) {
            healthLine = (HealthLine) view.findViewById(R.id.health_body_detail_data_line_left_hand);
        } else if (i == 3) {
            healthLine = (HealthLine) view.findViewById(R.id.health_body_detail_data_line_right_hand);
        } else if (i == 4) {
            healthLine = (HealthLine) view.findViewById(R.id.health_body_detail_data_line_left_leg);
        } else if (i == 5) {
            healthLine = (HealthLine) view.findViewById(R.id.health_body_detail_data_line_right_leg);
        } else {
            LogUtil.h("HealthBodyDetailData", "initHealthLine id does not exist");
            healthLine = null;
        }
        dIr_(healthLine);
    }

    private HealthTextView a(int i) {
        View view = this.d;
        HealthTextView healthTextView = null;
        if (view == null) {
            LogUtil.h("HealthBodyDetailData", "initTitle View is null");
            return null;
        }
        if (i == 1) {
            healthTextView = (HealthTextView) view.findViewById(R.id.health_body_detail_data_title_trunk);
        } else if (i == 2) {
            healthTextView = (HealthTextView) view.findViewById(R.id.health_body_detail_data_title_left_hand);
        } else if (i == 3) {
            healthTextView = (HealthTextView) view.findViewById(R.id.health_body_detail_data_title_right_hand);
        } else if (i == 4) {
            healthTextView = (HealthTextView) view.findViewById(R.id.health_body_detail_data_title_left_leg);
        } else if (i == 5) {
            healthTextView = (HealthTextView) view.findViewById(R.id.health_body_detail_data_title_right_leg);
        } else {
            LogUtil.h("HealthBodyDetailData", "initTitle id does not exist");
        }
        dIr_(healthTextView);
        return healthTextView;
    }

    private HealthTextView e(int i) {
        View view = this.d;
        HealthTextView healthTextView = null;
        if (view == null) {
            LogUtil.h("HealthBodyDetailData", "initValue View is null");
            return null;
        }
        if (i == 1) {
            healthTextView = (HealthTextView) view.findViewById(R.id.health_body_detail_data_value_trunk);
        } else if (i == 2) {
            healthTextView = (HealthTextView) view.findViewById(R.id.health_body_detail_data_value_left_hand);
        } else if (i == 3) {
            healthTextView = (HealthTextView) view.findViewById(R.id.health_body_detail_data_value_right_hand);
        } else if (i == 4) {
            healthTextView = (HealthTextView) view.findViewById(R.id.health_body_detail_data_value_left_leg);
        } else if (i == 5) {
            healthTextView = (HealthTextView) view.findViewById(R.id.health_body_detail_data_value_right_leg);
        } else {
            LogUtil.h("HealthBodyDetailData", "initValue id does not exist");
        }
        dIr_(healthTextView);
        return healthTextView;
    }

    private void dIr_(View view) {
        if (view == null) {
            LogUtil.h("HealthBodyDetailData", "viewSetVisibility ViewGroup is null");
        } else {
            if (view.getVisibility() == 0) {
                return;
            }
            view.setVisibility(0);
        }
    }

    public void setBodyDetailImageView(int i, int i2) {
        HealthBodyDetail healthBodyDetail = this.e;
        if (healthBodyDetail == null) {
            return;
        }
        healthBodyDetail.setImageView(i, i2);
    }

    public void setBodyDetailType(int i) {
        HealthBodyDetail healthBodyDetail = this.e;
        if (healthBodyDetail == null) {
            return;
        }
        healthBodyDetail.setType(i);
    }

    public void setValue(int i, String str) {
        HealthTextView e = e(i);
        if (e == null) {
            LogUtil.h("HealthBodyDetailData", "setValue HealthTextView is null");
        } else {
            e.setText(str);
        }
    }

    public void setContent(int i, int i2, String str) {
        setBodyDetailImageView(i, i2);
        c(i);
        b(i);
        a(i);
        setValue(i, str);
    }
}
