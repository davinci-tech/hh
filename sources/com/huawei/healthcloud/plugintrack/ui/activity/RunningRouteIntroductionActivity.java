package com.huawei.healthcloud.plugintrack.ui.activity;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.hpn;
import defpackage.nsy;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;

/* loaded from: classes4.dex */
public class RunningRouteIntroductionActivity extends BaseActivity {
    private CustomTitleBar c;

    enum NoteIndex {
        TITLE_INDEX_ONE(1),
        TITLE_INDEX_TWO(2),
        TITLE_INDEX_THREE(3),
        TITLE_INDEX_FOUR(4),
        TITLE_INDEX_FIVE(5),
        TITLE_INDEX_SIX(6),
        TITLE_INDEX_SEVEN(7);

        private final int mIndex;

        NoteIndex(int i) {
            this.mIndex = i;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("RunningRouteIntroductionActivity", "onCreate");
        super.onCreate(bundle);
        setContentView(R.layout.activity_running_route_introduction);
        d();
    }

    private void d() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.running_route_introduction_titlebar);
        this.c = customTitleBar;
        customTitleBar.setTitleText(getString(R.string._2130845073_res_0x7f021d91));
        if (Utils.o()) {
            findViewById(R.id.introduction_group).setVisibility(8);
            nsy.cMr_((TextView) findViewById(R.id.introduction_question_2), getResources().getString(R.string._2130840149_res_0x7f020a55, Integer.valueOf(NoteIndex.TITLE_INDEX_ONE.mIndex)));
            nsy.cMr_((TextView) findViewById(R.id.introduction_question_3), getResources().getString(R.string._2130840151_res_0x7f020a57, Integer.valueOf(NoteIndex.TITLE_INDEX_TWO.mIndex)));
        } else {
            nsy.cMr_((TextView) findViewById(R.id.introduction_question_1), getResources().getString(R.string._2130840147_res_0x7f020a53, Integer.valueOf(NoteIndex.TITLE_INDEX_ONE.mIndex)));
            nsy.cMr_((TextView) findViewById(R.id.introduction_question_2), getResources().getString(R.string._2130840149_res_0x7f020a55, Integer.valueOf(NoteIndex.TITLE_INDEX_TWO.mIndex)));
            nsy.cMr_((TextView) findViewById(R.id.introduction_question_3), getResources().getString(R.string._2130840151_res_0x7f020a57, Integer.valueOf(NoteIndex.TITLE_INDEX_THREE.mIndex)));
            nsy.cMr_((TextView) findViewById(R.id.introduction_question_4), getResources().getString(R.string._2130840153_res_0x7f020a59, Integer.valueOf(NoteIndex.TITLE_INDEX_FOUR.mIndex)));
            nsy.cMr_((TextView) findViewById(R.id.introduction_question_5), getResources().getString(R.string._2130840155_res_0x7f020a5b, Integer.valueOf(NoteIndex.TITLE_INDEX_FIVE.mIndex)));
            nsy.cMr_((TextView) findViewById(R.id.introduction_question_6), getResources().getString(R.string._2130840157_res_0x7f020a5d, Integer.valueOf(NoteIndex.TITLE_INDEX_SIX.mIndex)));
            nsy.cMr_((TextView) findViewById(R.id.introduction_question_7), getResources().getString(R.string._2130840159_res_0x7f020a5f, Integer.valueOf(NoteIndex.TITLE_INDEX_SEVEN.mIndex)));
            nsy.cMr_((TextView) findViewById(R.id.introduction_answer_5), getResources().getString(R.string._2130840156_res_0x7f020a5c, UnitUtil.e(60.0d, 2, 0)));
            nsy.cMr_((TextView) findViewById(R.id.introduction_answer_7), getResources().getString(R.string._2130840160_res_0x7f020a60, 30));
            bcq_(findViewById(R.id.introduction_route_small_image), hpn.bof_("running_route_introduction_route_small"));
            bcq_(findViewById(R.id.introduction_route_draw_image), hpn.bof_("running_route_introduction_route_draw"));
            bcq_(findViewById(R.id.introduction_run_route_image), hpn.bof_("running_route_introduction_run_route"));
            bcq_(findViewById(R.id.introduction_track_image), hpn.bof_("running_route_introduction_track"));
        }
        bcq_(findViewById(R.id.introduction_draw_small_image), hpn.bof_("running_route_introduction_draw_small"));
    }

    private void bcq_(View view, Drawable drawable) {
        if (view == null) {
            return;
        }
        if (drawable == null) {
            view.setVisibility(8);
        } else if (view instanceof ImageView) {
            nsy.cMm_((ImageView) view, drawable);
            view.setVisibility(0);
        } else {
            LogUtil.h("RunningRouteIntroductionActivity", "setIntroductionImage not imageview");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
