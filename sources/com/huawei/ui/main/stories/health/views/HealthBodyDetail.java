package com.huawei.ui.main.stories.health.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes7.dex */
public class HealthBodyDetail extends FrameLayout {
    private static final int[][][] e = {new int[][]{new int[]{R.drawable._2131428313_res_0x7f0b03d9, R.drawable._2131428347_res_0x7f0b03fb, R.drawable._2131428315_res_0x7f0b03db, R.drawable._2131428331_res_0x7f0b03eb, R.drawable._2131428323_res_0x7f0b03e3, R.drawable._2131428339_res_0x7f0b03f3}, new int[]{R.drawable._2131428313_res_0x7f0b03d9, R.drawable._2131428348_res_0x7f0b03fc, R.drawable._2131428316_res_0x7f0b03dc, R.drawable._2131428332_res_0x7f0b03ec, R.drawable._2131428324_res_0x7f0b03e4, R.drawable._2131428340_res_0x7f0b03f4}, new int[]{R.drawable._2131428313_res_0x7f0b03d9, R.drawable._2131428349_res_0x7f0b03fd, R.drawable._2131428317_res_0x7f0b03dd, R.drawable._2131428333_res_0x7f0b03ed, R.drawable._2131428325_res_0x7f0b03e5, R.drawable._2131428341_res_0x7f0b03f5}, new int[]{R.drawable._2131428313_res_0x7f0b03d9, R.drawable._2131428350_res_0x7f0b03fe, R.drawable._2131428318_res_0x7f0b03de, R.drawable._2131428334_res_0x7f0b03ee, R.drawable._2131428326_res_0x7f0b03e6, R.drawable._2131428342_res_0x7f0b03f6}}, new int[][]{new int[]{R.drawable._2131428314_res_0x7f0b03da, R.drawable._2131428351_res_0x7f0b03ff, R.drawable._2131428319_res_0x7f0b03df, R.drawable._2131428335_res_0x7f0b03ef, R.drawable._2131428327_res_0x7f0b03e7, R.drawable._2131428343_res_0x7f0b03f7}, new int[]{R.drawable._2131428314_res_0x7f0b03da, R.drawable._2131428352_res_0x7f0b0400, R.drawable._2131428320_res_0x7f0b03e0, R.drawable._2131428336_res_0x7f0b03f0, R.drawable._2131428328_res_0x7f0b03e8, R.drawable._2131428344_res_0x7f0b03f8}, new int[]{R.drawable._2131428314_res_0x7f0b03da, R.drawable._2131428353_res_0x7f0b0401, R.drawable._2131428321_res_0x7f0b03e1, R.drawable._2131428337_res_0x7f0b03f1, R.drawable._2131428329_res_0x7f0b03e9, R.drawable._2131428345_res_0x7f0b03f9}, new int[]{R.drawable._2131428314_res_0x7f0b03da, R.drawable._2131428354_res_0x7f0b0402, R.drawable._2131428322_res_0x7f0b03e2, R.drawable._2131428338_res_0x7f0b03f2, R.drawable._2131428330_res_0x7f0b03ea, R.drawable._2131428346_res_0x7f0b03fa}}};

    /* renamed from: a, reason: collision with root package name */
    private int f10264a;
    private Context c;
    private View d;

    private int b(int i) {
        if (i > 3) {
            return 0;
        }
        return i;
    }

    private int c(int i) {
        if (i > 5) {
            return 0;
        }
        return i;
    }

    public HealthBodyDetail(Context context) {
        this(context, null);
        b(context);
    }

    public HealthBodyDetail(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (attributeSet == null) {
            LogUtil.h("HealthBodyDetail", "HealthBodyDetail AttributeSet is null");
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099782_res_0x7f060086});
        try {
            this.f10264a = obtainStyledAttributes.getInteger(0, 0);
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("HealthBodyDetail", "HealthBodyDetail Resources NotFoundException");
        }
        obtainStyledAttributes.recycle();
        b(context);
    }

    private void b(Context context) {
        if (context == null) {
            LogUtil.h("HealthBodyDetail", "initBodyDetailView Context is null");
            return;
        }
        this.c = context;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (layoutInflater == null) {
            LogUtil.h("HealthBodyDetail", "initBodyDetailView LayoutInflater is null");
            return;
        }
        this.d = layoutInflater.inflate(R.layout.health_body_detail, this);
        setImageView(0, 4);
        setImageView(1, 4);
        setImageView(2, 4);
        setImageView(3, 4);
        setImageView(4, 4);
        setImageView(5, 4);
    }

    private ImageView dIq_(int i) {
        View view = this.d;
        if (view == null) {
            LogUtil.h("HealthBodyDetail", "initImageView View is null");
            return null;
        }
        if (i == 0) {
            return (ImageView) view.findViewById(R.id.health_body_detail_head);
        }
        if (i == 1) {
            return (ImageView) view.findViewById(R.id.health_body_detail_trunk);
        }
        if (i == 2) {
            return (ImageView) view.findViewById(R.id.health_body_detail_left_hand);
        }
        if (i == 3) {
            return (ImageView) view.findViewById(R.id.health_body_detail_right_hand);
        }
        if (i == 4) {
            return (ImageView) view.findViewById(R.id.health_body_detail_left_leg);
        }
        if (i == 5) {
            return (ImageView) view.findViewById(R.id.health_body_detail_right_leg);
        }
        LogUtil.h("HealthBodyDetail", "initImageView id does not exist");
        return null;
    }

    private int getTypeIndex() {
        return this.f10264a == 1 ? 1 : 0;
    }

    public void setType(int i) {
        this.f10264a = i;
    }

    public void setImageView(int i, int i2) {
        ImageView dIq_ = dIq_(i);
        if (dIq_ == null) {
            return;
        }
        dIq_.setImageResource(e[getTypeIndex()][b(i2)][c(i)]);
    }
}
