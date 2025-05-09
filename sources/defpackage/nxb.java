package defpackage;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.activity.pairing.DevicePairGuideActivity;
import com.huawei.ui.device.interactors.CompatibilityInteractor;
import health.compact.a.LanguageUtil;
import java.util.Locale;

/* loaded from: classes6.dex */
class nxb {
    nxb() {
    }

    void b(DevicePairGuideActivity devicePairGuideActivity) {
        LogUtil.a("DevicePairGuideViewHelper", "Enter initView():");
        if (devicePairGuideActivity == null) {
            return;
        }
        devicePairGuideActivity.z = new CompatibilityInteractor();
        devicePairGuideActivity.aa = (CustomTitleBar) nsy.cMc_(devicePairGuideActivity, R.id.pair_guide_custom_title);
        devicePairGuideActivity.bu = (ImageView) nsy.cMc_(devicePairGuideActivity, R.id.device_pairing_guide_img);
        devicePairGuideActivity.al = (HealthScrollView) nsy.cMc_(devicePairGuideActivity, R.id.device_pairing_guide_sl);
        DevicePairGuideActivity.cancelLayoutById(devicePairGuideActivity.al);
        devicePairGuideActivity.bs = (ImageView) nsy.cMc_(devicePairGuideActivity, R.id.device_pairing_guide_b3_anim_img);
        devicePairGuideActivity.bs.setVisibility(8);
        devicePairGuideActivity.l = (RelativeLayout) nsy.cMc_(devicePairGuideActivity, R.id.aw70_pair_progress);
        devicePairGuideActivity.k = (ImageView) nsy.cMc_(devicePairGuideActivity, R.id.aw70_pair_progress_img);
        nsn.cLC_(devicePairGuideActivity.l, 2);
        devicePairGuideActivity.n = (RelativeLayout) nsy.cMc_(devicePairGuideActivity, R.id.aw70_pair_progress_big);
        devicePairGuideActivity.m = (ImageView) nsy.cMc_(devicePairGuideActivity, R.id.aw70_pair_progress_img_big);
        nsn.cLC_(devicePairGuideActivity.n, 2);
        devicePairGuideActivity.s = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.aw70_pair_result_device_show_txt);
        devicePairGuideActivity.y = (ImageView) nsy.cMc_(devicePairGuideActivity, R.id.pair_guide_left_image);
        devicePairGuideActivity.ch = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.device_janus_pair_b5_describe);
        devicePairGuideActivity.ch.setText(String.format(Locale.ENGLISH, devicePairGuideActivity.getResources().getString(R.string.IDS_device_janus_pair_guide_01), devicePairGuideActivity.getResources().getString(R.string.IDS_device_start_paring_title)));
        devicePairGuideActivity.bq = (ImageView) nsy.cMc_(devicePairGuideActivity, R.id.pair_guide_right_image);
        if (LanguageUtil.bc(devicePairGuideActivity)) {
            LogUtil.a("DevicePairGuideViewHelper", "isRTLLanguage!!!");
            devicePairGuideActivity.y.setImageResource(R.drawable._2131429722_res_0x7f0b095a);
            devicePairGuideActivity.bq.setImageResource(R.mipmap._2131820816_res_0x7f110110);
            LogUtil.a("DevicePairGuideViewHelper", "isRTLLanguage!!!!!!!");
        }
        devicePairGuideActivity.at = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.ear_phone_pair_error);
        e(devicePairGuideActivity);
    }

    private void e(DevicePairGuideActivity devicePairGuideActivity) {
        if (devicePairGuideActivity == null) {
            return;
        }
        devicePairGuideActivity.ai = (RelativeLayout) nsy.cMc_(devicePairGuideActivity, R.id.device_pair_guide_text_tips);
        devicePairGuideActivity.cn = (LinearLayout) nsy.cMc_(devicePairGuideActivity, R.id.device_pair_guide_text_tips_b5);
        devicePairGuideActivity.cp = (LinearLayout) nsy.cMc_(devicePairGuideActivity, R.id.device_pair_guide_text_tips_cassini);
        devicePairGuideActivity.bt = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.device_cassini_pair_first_describe);
        devicePairGuideActivity.bw = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.device_cassini_pair_second_describe);
        devicePairGuideActivity.ci = (RelativeLayout) nsy.cMc_(devicePairGuideActivity, R.id.device_pair_guide_text_tips_1);
        devicePairGuideActivity.cj = (RelativeLayout) nsy.cMc_(devicePairGuideActivity, R.id.device_pair_guide_text_tips_2);
        devicePairGuideActivity.cl = (RelativeLayout) nsy.cMc_(devicePairGuideActivity, R.id.device_pair_guide_text_tips_3);
        devicePairGuideActivity.cq = (RelativeLayout) nsy.cMc_(devicePairGuideActivity, R.id.device_pair_guide_text_tips_4);
        devicePairGuideActivity.cm = (RelativeLayout) nsy.cMc_(devicePairGuideActivity, R.id.device_pair_guide_text_tips_single);
        devicePairGuideActivity.cf = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.device_pairing_guide_txt_1);
        devicePairGuideActivity.ce = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.device_pairing_guide_txt_2);
        devicePairGuideActivity.ck = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.device_pairing_guide_txt_3);
        devicePairGuideActivity.co = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.device_pairing_guide_single);
        devicePairGuideActivity.bz = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.device_pairing_guide_num_text_1);
        devicePairGuideActivity.cg = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.device_pairing_guide_num_text_2);
        devicePairGuideActivity.cc = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.device_pairing_guide_num_text_3);
        devicePairGuideActivity.cd = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.device_pairing_guide_num_text_4);
        jfq.c().d("deleteDevice", new DeviceInfo(), 0, String.valueOf(true));
        a(devicePairGuideActivity);
    }

    private void a(DevicePairGuideActivity devicePairGuideActivity) {
        if (devicePairGuideActivity == null) {
            return;
        }
        devicePairGuideActivity.by = (LinearLayout) nsy.cMc_(devicePairGuideActivity, R.id.pair_guide_left_cancel_layout);
        devicePairGuideActivity.bx = (LinearLayout) nsy.cMc_(devicePairGuideActivity, R.id.pair_guide_right_btn_layout);
        devicePairGuideActivity.x = (HealthColumnLinearLayout) nsy.cMc_(devicePairGuideActivity, R.id.cancel_button_layout);
        devicePairGuideActivity.av = (HealthColumnLinearLayout) nsy.cMc_(devicePairGuideActivity, R.id.got_it_button_layout);
        devicePairGuideActivity.ac = (LinearLayout) nsy.cMc_(devicePairGuideActivity, R.id.connecting_device_name_layout);
        devicePairGuideActivity.ab = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.connecting_device_name);
        devicePairGuideActivity.cs = (RelativeLayout) nsy.cMc_(devicePairGuideActivity, R.id.pair_result_device_progress_img);
        devicePairGuideActivity.t = (LinearLayout) nsy.cMc_(devicePairGuideActivity, R.id.device_pairing_guide_bottom_arrow_layout);
        devicePairGuideActivity.dd = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.right_arrow_txt);
        devicePairGuideActivity.dd.setText(devicePairGuideActivity.getResources().getString(R.string.IDS_device_start_paring_title).toUpperCase(Locale.ENGLISH));
        devicePairGuideActivity.cb = (ImageView) nsy.cMc_(devicePairGuideActivity, R.id.device_pair_guide_progress_anim);
        if (devicePairGuideActivity.cb.getDrawable() instanceof AnimationDrawable) {
            devicePairGuideActivity.c = (AnimationDrawable) devicePairGuideActivity.cb.getDrawable();
        }
        devicePairGuideActivity.ct = (ImageView) nsy.cMc_(devicePairGuideActivity, R.id.pair_result_device_img);
        devicePairGuideActivity.ba = (LinearLayout) nsy.cMc_(devicePairGuideActivity, R.id.ll_icon_pair);
        nsn.cLC_(devicePairGuideActivity.cs, 3);
        devicePairGuideActivity.cw = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.pair_result_device_show_txt);
        devicePairGuideActivity.cu = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.device_pairing_progress_guide_single_note);
        devicePairGuideActivity.dh = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.device_pairing_progress_leo_failure);
        devicePairGuideActivity.w = (HealthButton) nsy.cMc_(devicePairGuideActivity, R.id.cancel_button);
        devicePairGuideActivity.w.setOnClickListener(devicePairGuideActivity);
        devicePairGuideActivity.w.setVisibility(8);
        devicePairGuideActivity.cy = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.pair_result_device_privacy_txt);
        devicePairGuideActivity.bp = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.pair_result_ear_privacy_txt);
        devicePairGuideActivity.bp.setVisibility(8);
        String string = devicePairGuideActivity.getString(R.string.IDS_device_paring_success_descommon_info_new);
        if (nsn.ae(BaseApplication.getContext())) {
            string = devicePairGuideActivity.getString(R.string._2130844352_res_0x7f021ac0);
        }
        devicePairGuideActivity.cy.setText(string);
        devicePairGuideActivity.am = (RelativeLayout) nsy.cMc_(devicePairGuideActivity, R.id.device_pair_result_img_text);
        devicePairGuideActivity.cv = (ImageView) nsy.cMc_(devicePairGuideActivity, R.id.pair_result_device_show_img);
        devicePairGuideActivity.cr = (RelativeLayout) nsy.cMc_(devicePairGuideActivity, R.id.pair_text_layout);
        devicePairGuideActivity.ay.h();
        d(devicePairGuideActivity);
    }

    private void d(final DevicePairGuideActivity devicePairGuideActivity) {
        if (devicePairGuideActivity == null) {
            return;
        }
        devicePairGuideActivity.ap = (HealthProgressBar) nsy.cMc_(devicePairGuideActivity, R.id.download_progress);
        devicePairGuideActivity.ap.d(devicePairGuideActivity, R.color._2131296657_res_0x7f090191, R.color.emui_accent);
        devicePairGuideActivity.ap.setProgress(0);
        devicePairGuideActivity.bn = (LinearLayout) nsy.cMc_(devicePairGuideActivity, R.id.device_error_bad_layout);
        devicePairGuideActivity.db = (LinearLayout) nsy.cMc_(devicePairGuideActivity, R.id.device_download_bad_layout);
        devicePairGuideActivity.aq = (HealthButton) nsy.cMc_(devicePairGuideActivity, R.id.cancel_res_button);
        devicePairGuideActivity.aw = (HealthColumnLinearLayout) nsy.cMc_(devicePairGuideActivity, R.id.cancel_res_layout);
        devicePairGuideActivity.dc = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.resource_error);
        devicePairGuideActivity.aq.setOnClickListener(new View.OnClickListener() { // from class: nxb.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                devicePairGuideActivity.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        c(devicePairGuideActivity);
    }

    private void c(DevicePairGuideActivity devicePairGuideActivity) {
        if (devicePairGuideActivity == null) {
            return;
        }
        devicePairGuideActivity.bv = (LinearLayout) nsy.cMc_(devicePairGuideActivity, R.id.ll_pairing_guide_choose);
        devicePairGuideActivity.df = (HealthButton) nsy.cMc_(devicePairGuideActivity, R.id.pair_guide_again);
        devicePairGuideActivity.dk = (HealthButton) nsy.cMc_(devicePairGuideActivity, R.id.skip_pair_guide);
    }
}
