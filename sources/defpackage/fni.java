package defpackage;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.core.content.res.ResourcesCompat;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.activity.TrainDetail;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.imageview.HealthImageView;
import health.compact.a.SharedPreferenceManager;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class fni extends UiCallback<List<?>> {
    private WeakReference<TrainDetail> b;

    public fni(TrainDetail trainDetail) {
        this.b = new WeakReference<>(trainDetail);
    }

    @Override // com.huawei.basefitnessadvice.callback.UiCallback
    public void onFailure(int i, String str) {
        LogUtil.a("TrainDetailFaDeviceUiCallback", "the errorCode:", Integer.valueOf(i), " the errorInfo: ", str);
    }

    @Override // com.huawei.basefitnessadvice.callback.UiCallback
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onSuccess(List<?> list) {
        if (koq.b(list)) {
            return;
        }
        final TrainDetail trainDetail = this.b.get();
        if (trainDetail == null || trainDetail.isFinishing() || trainDetail.isDestroyed()) {
            LogUtil.h("TrainDetailFaDeviceUiCallback", "trainDetail is null or destroyed, not need to pop Dialog");
            return;
        }
        View inflate = LayoutInflater.from(trainDetail).inflate(R.layout.view_circulating_bubble, (ViewGroup) null);
        aBp_(inflate, trainDetail);
        final PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setSoftInputMode(16);
        popupWindow.setOutsideTouchable(false);
        inflate.setOnClickListener(new View.OnClickListener() { // from class: fng
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                fni.aBo_(TrainDetail.this, popupWindow, view);
            }
        });
        popupWindow.showAtLocation(inflate, 48, 0, nsn.r(trainDetail) + nsn.c(trainDetail, 8.0f));
        e(trainDetail.c().acquireWorkoutId(), trainDetail.c().acquireWorkoutName(), "0");
        SharedPreferenceManager.e("fit_SharedPreference", "showFaRemindTime", System.currentTimeMillis());
        trainDetail.azX_().postDelayed(new Runnable() { // from class: fne
            @Override // java.lang.Runnable
            public final void run() {
                popupWindow.dismiss();
            }
        }, 6000L);
    }

    static /* synthetic */ void aBo_(TrainDetail trainDetail, PopupWindow popupWindow, View view) {
        trainDetail.azX_().sendMessage(trainDetail.azX_().obtainMessage(108));
        popupWindow.dismiss();
        SharedPreferenceManager.e("fit_SharedPreference", "clickFaRemindTime", System.currentTimeMillis());
        e(trainDetail.c().acquireWorkoutId(), trainDetail.c().acquireWorkoutName(), "1");
        ViewClickInstrumentation.clickOnView(view);
    }

    public static boolean a() {
        return SharedPreferenceManager.b("fit_SharedPreference", "clickFaRemindTime", 0L) != 0 || System.currentTimeMillis() - SharedPreferenceManager.b("fit_SharedPreference", "showFaRemindTime", 0L) >= 86400000;
    }

    public static void e(String str, String str2, String str3) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("click", "1");
        hashMap.put("event", str3);
        hashMap.put("workout_id", str);
        hashMap.put("workout_name", str2);
        gge.e(AnalyticsValue.EVENT_FA_REMIND_1130062.value(), hashMap);
    }

    private void aBp_(View view, TrainDetail trainDetail) {
        HealthImageView healthImageView = (HealthImageView) view.findViewById(R.id.bubble_from_image);
        HealthImageView healthImageView2 = (HealthImageView) view.findViewById(R.id.bubble_to_image);
        HealthImageView healthImageView3 = (HealthImageView) view.findViewById(R.id.bubble_connection_image);
        boolean ae = nsn.ae(BaseApplication.getContext());
        if (nrt.a(BaseApplication.getContext())) {
            view.setBackground(ResourcesCompat.getDrawable(trainDetail.getResources(), R.drawable._2131427593_res_0x7f0b0109, null));
        } else {
            view.setBackground(ResourcesCompat.getDrawable(trainDetail.getResources(), R.drawable._2131427592_res_0x7f0b0108, null));
        }
        if (ae) {
            healthImageView.setBackground(ResourcesCompat.getDrawable(trainDetail.getResources(), R.drawable.ic_public_device_pad, null));
        } else {
            healthImageView.setBackground(ResourcesCompat.getDrawable(trainDetail.getResources(), R.drawable.ic_public_devices_phone, null));
        }
        healthImageView2.setBackground(ResourcesCompat.getDrawable(trainDetail.getResources(), R.drawable._2131430299_res_0x7f0b0b9b, null));
        healthImageView3.setBackground(ResourcesCompat.getDrawable(trainDetail.getResources(), R.drawable._2131427878_res_0x7f0b0226, null));
    }
}
