package com.huawei.basichealthmodel.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.huawei.basichealthmodel.R$plurals;
import com.huawei.basichealthmodel.R$string;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import defpackage.ayf;
import defpackage.azi;
import defpackage.ban;
import defpackage.bao;
import defpackage.bcl;
import defpackage.dsl;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.LanguageUtil;

/* loaded from: classes8.dex */
public class HealthModelShareView extends BasicHealthModelShareView {
    public HealthModelShareView(Context context) {
        super(context);
    }

    public HealthModelShareView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HealthModelShareView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public static int getChallengeId() {
        int e = nsn.e(bao.e("health_model_challenge_id"));
        if (e == 200001 || e <= 0) {
            e = ban.b();
        }
        return e <= 0 ? AwarenessConstants.ERROR_UNKNOWN_CODE : e;
    }

    public void setTaskData(ayf ayfVar) {
        if (ayfVar == null) {
            LogUtil.h("HealthLife_HealthModelShareView", "setTaskData bean is null");
            return;
        }
        int c = ayfVar.c();
        if (c > 0) {
            setTaskText(ayfVar.d(), c, this.b.getString(dsl.ZJ_().get(ayfVar.d())));
        }
        if (ayfVar.a() > 0) {
            this.f.setText(this.b.getString(R$string.IDS_health_model_target_exceed_number, azi.c(ayfVar.a(), 2, 0)));
            this.f.setVisibility(0);
        } else {
            this.f.setVisibility(8);
        }
        d(ayfVar);
        setTaskBenefit(ayfVar.d());
    }

    public void setTaskText(int i, int i2, String str) {
        int i3;
        if (i == 2) {
            i3 = R$string.IDS_share_task_step;
        } else if (i == 3) {
            i3 = R$string.IDS_share_task_intensity;
        } else if (i == 5) {
            i3 = R$string.IDS_share_task_breath;
        } else if (i == 6) {
            i3 = R$string.IDS_share_task_wake_up;
        } else if (i == 7) {
            i3 = R$string.IDS_share_task_sleep;
        } else if (i == 9) {
            i3 = R$string.IDS_share_task_mindfulness;
        } else if (i != 10) {
            return;
        } else {
            i3 = R$string.IDS_share_task_drink;
        }
        this.c.setText(d(i3 != 0 ? this.b.getString(i3, this.b.getQuantityString(R$plurals.IDS_day_no_space, i2, Integer.valueOf(i2))) : "", str, String.valueOf(i2)));
    }

    private void d(ayf ayfVar) {
        this.h.setBackground(null);
        this.i.setBackground(null);
        Bitmap lb_ = ayfVar.lb_();
        Bitmap lc_ = ayfVar.lc_();
        if (lb_ == null) {
            Bitmap cHR_ = nrf.cHR_(R$drawable.health_life_background);
            if (cHR_ == null) {
                LogUtil.h("HealthLife_HealthModelShareView", "initBackground bitmap is null");
                this.h.setBackgroundResource(R$drawable.health_life_background);
                return;
            } else {
                this.h.setBackground(new BitmapDrawable(this.b, ly_(cHR_)));
                return;
            }
        }
        this.h.setBackground(new BitmapDrawable(this.b, ly_(lb_)));
        if (lc_ != null) {
            this.i.setBackground(new BitmapDrawable(this.b, ly_(lc_)));
        }
    }

    public void setChallengeData(int i, int i2, int i3) {
        d(i, i2, ban.c(i));
        if (i3 > 0) {
            this.f.setText(this.b.getQuantityString(R$plurals.IDS_share_join_people_two, i3, azi.c(i3, 1, 0)));
            this.f.setVisibility(0);
        } else {
            this.f.setVisibility(8);
        }
        String a2 = ban.a(i);
        if (TextUtils.isEmpty(a2)) {
            this.d.setVisibility(8);
        } else {
            this.d.setText(a2);
            this.d.setVisibility(0);
        }
        c(ban.a());
    }

    private void d(int i, int i2, String str) {
        int i3;
        switch (i) {
            case AwarenessConstants.ERROR_INVALID_FREQUENCY_CODE /* 200002 */:
                i3 = R$string.IDS_share_plan_relieve;
                break;
            case AwarenessConstants.ERROR_TIMEOUT_CODE /* 200003 */:
                i3 = R$string.IDS_share_plan_weight_new;
                break;
            case AwarenessConstants.ERROR_UNKNOWN_CODE /* 200004 */:
                i3 = R$string.IDS_share_plan_sleep;
                break;
            case AwarenessConstants.ERROR_LIMITED_REGISTRY_CODE /* 200005 */:
                i3 = R$string.IDS_share_plan_immunity;
                break;
            default:
                i3 = 0;
                break;
        }
        this.c.setText(a(i3 != 0 ? this.b.getString(i3, this.b.getQuantityString(R$plurals.IDS_day_no_space, i2, Integer.valueOf(i2))) : "", str, String.valueOf(i2), LanguageUtil.h(this.f1921a)));
    }

    private void c(String str) {
        Bitmap cHQ_;
        if (azi.h(str)) {
            LogUtil.h("HealthLife_HealthModelShareView", "initChallengeBackground sharePath ", str);
            cHQ_ = null;
        } else {
            cHQ_ = nrf.cHQ_(str);
        }
        if (cHQ_ == null && (cHQ_ = nrf.cHR_(R$drawable.health_life_background)) == null) {
            LogUtil.h("HealthLife_HealthModelShareView", "initBackground bitmap is null");
            this.h.setBackgroundResource(R$drawable.health_life_background);
        } else {
            this.h.setBackground(new BitmapDrawable(this.b, ly_(cHQ_)));
        }
    }

    private void setTaskBenefit(int i) {
        String e = bcl.e(i, getChallengeId(), true);
        if (TextUtils.isEmpty(e)) {
            this.d.setVisibility(8);
        } else {
            this.d.setText(e);
            this.d.setVisibility(0);
        }
    }
}
