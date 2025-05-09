package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.Pictures;
import com.huawei.pluginfitnessadvice.Video;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ggg;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import java.util.List;

/* loaded from: classes4.dex */
public class ActionDetailContentView extends ConstraintLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3408a;
    private HealthTextView b;
    private LinearLayout c;
    private HealthTextView d;
    private String e;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private String j;
    private ImageView k;
    private ImageView l;
    private HealthTextView m;
    private LinearLayout n;
    private ImageView o;
    private HealthTextView p;
    private HealthTextView r;
    private HealthTextView t;

    public ActionDetailContentView(Context context) {
        super(context);
        c(context);
    }

    public ActionDetailContentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c(context);
    }

    private void c(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_action_detail_content, (ViewGroup) this, true);
        this.t = (HealthTextView) findViewById(R.id.sug_action_tv_title);
        this.c = (LinearLayout) findViewById(R.id.action_layout);
        this.d = (HealthTextView) findViewById(R.id.sug_action_tv_error_content);
        this.i = (HealthTextView) findViewById(R.id.sug_action_tv_error);
        this.g = (HealthTextView) findViewById(R.id.sug_action_tv_feeling_content);
        this.h = (HealthTextView) findViewById(R.id.sug_action_tv_feeling);
        this.b = (HealthTextView) findViewById(R.id.sug_action_tv_breath_content);
        this.f3408a = (HealthTextView) findViewById(R.id.sug_action_tv_breath);
        this.f = (HealthTextView) findViewById(R.id.sug_action_tv_introduceLyric_content);
        this.m = (HealthTextView) findViewById(R.id.sug_action_tv_introduceLyric);
        this.r = (HealthTextView) findViewById(R.id.sug_action_tv_step_content);
        this.p = (HealthTextView) findViewById(R.id.sug_action_tv_step);
        this.k = (ImageView) findViewById(R.id.sug_action_muscle_left);
        this.l = (ImageView) findViewById(R.id.sug_action_muscle_right);
        this.o = (ImageView) findViewById(R.id.sug_action_orign_image);
        this.n = (LinearLayout) findViewById(R.id.sug_detail_info_layout_course_partner);
        aLY_(this.c);
    }

    public View getContentLayout() {
        return this.c;
    }

    public static void aLY_(View view) {
        if (view == null) {
            LogUtil.h("Suggestion_ActionDetailContentView", "initWidescreenLayout contentLayout == null");
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            LogUtil.h("Suggestion_ActionDetailContentView", "initWidescreenLayout contentLayout == null");
            return;
        }
        if (nsn.ag(BaseApplication.getContext())) {
            aLZ_(layoutParams, 5);
        } else if (EnvironmentInfo.k()) {
            aLZ_(layoutParams, 3);
        } else {
            layoutParams = aLX_(layoutParams);
        }
        view.setLayoutParams(layoutParams);
    }

    private static ViewGroup.LayoutParams aLX_(ViewGroup.LayoutParams layoutParams) {
        int dimension = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131364635_res_0x7f0a0b1b);
        if (!(layoutParams instanceof LinearLayout.LayoutParams)) {
            return layoutParams;
        }
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.setMarginStart(dimension);
        layoutParams2.setMarginEnd(dimension);
        return layoutParams2;
    }

    private static void aLZ_(ViewGroup.LayoutParams layoutParams, int i) {
        layoutParams.width = (int) new HealthColumnSystem(BaseApplication.getContext(), 0).d(i);
    }

    public void setContent(AtomicAction atomicAction) {
        if (atomicAction == null) {
            return;
        }
        this.t.setText(atomicAction.getName());
        e(this.p, this.r, atomicAction.getExtendPropertyString("actionStep"));
        e(this.m, this.f, atomicAction.getExtendPropertyString("introduceLyric"));
        e(this.f3408a, this.b, atomicAction.getExtendPropertyString("breath"));
        e(this.h, this.g, atomicAction.getExtendPropertyString("feeling"));
        e(this.i, this.d, atomicAction.getExtendPropertyString("commonError"));
        d(atomicAction.getExtendPropertyList("pictures", Pictures[].class));
        a(atomicAction);
    }

    private void a(AtomicAction atomicAction) {
        if (atomicAction == null) {
            LogUtil.h("Suggestion_ActionDetailContentView", "updateOrignLogo actionInfo can not null");
            return;
        }
        List extendPropertyList = atomicAction.getExtendPropertyList("actionVideo", Video[].class);
        if (koq.b(extendPropertyList)) {
            LogUtil.h("Suggestion_ActionDetailContentView", "updateOrignLogo videos array can not null");
            return;
        }
        Video video = (Video) extendPropertyList.get(0);
        if (video == null) {
            LogUtil.h("Suggestion_ActionDetailContentView", "updateOrignLogo video can not null");
        } else if (TextUtils.isEmpty(video.getLogoImgUrl())) {
            LogUtil.h("Suggestion_ActionDetailContentView", "updateOrignLogo LogoImgUrl is empty");
        } else {
            this.n.setVisibility(0);
            nrf.cJB_(video.getLogoImgUrl(), this.o);
        }
    }

    private void e(HealthTextView healthTextView, HealthTextView healthTextView2, String str) {
        if (healthTextView == null || healthTextView2 == null) {
            LogUtil.h("Suggestion_ActionDetailContentView", "updateViewState HealthTextView can not null");
            return;
        }
        healthTextView.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        healthTextView2.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        healthTextView2.setText(str);
    }

    private void d(List<Pictures> list) {
        if (koq.b(list, 0)) {
            this.k.setVisibility(8);
            this.l.setVisibility(8);
            return;
        }
        Pictures pictures = list.get(0);
        int a2 = ggg.a();
        for (Pictures pictures2 : list) {
            if (pictures2 != null && CommonUtil.h(pictures2.getSex()) == a2) {
                pictures = pictures2;
            }
        }
        String frontMusclePicUrl = pictures.getFrontMusclePicUrl();
        this.j = frontMusclePicUrl;
        this.k.setVisibility(TextUtils.isEmpty(frontMusclePicUrl) ? 8 : 0);
        nrf.cJB_(this.j, this.k);
        String backMusclePicUrl = pictures.getBackMusclePicUrl();
        this.e = backMusclePicUrl;
        this.l.setVisibility(TextUtils.isEmpty(backMusclePicUrl) ? 8 : 0);
        nrf.cJB_(this.e, this.l);
    }
}
