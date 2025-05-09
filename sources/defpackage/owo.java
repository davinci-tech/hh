package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.util.List;

/* loaded from: classes6.dex */
public class owo {
    private static volatile owo b;

    /* renamed from: a, reason: collision with root package name */
    private Handler f15991a;
    private List<Integer> d;
    private Context e;

    private owo(Context context) {
        if (context == null) {
            this.e = BaseApplication.e();
        } else {
            this.e = context.getApplicationContext();
        }
        e();
    }

    public static owo d(Context context) {
        if (b == null) {
            b = new owo(context);
        }
        return b;
    }

    private void e() {
        HiHealthNativeApi.a(this.e).subscribeHiHealthData(102, new HiSubscribeListener() { // from class: owo.4
            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onResult(List<Integer> list, List<Integer> list2) {
                if (!koq.b(list)) {
                    owo.this.d = list;
                } else {
                    LogUtil.b("UIHLH_SubscribeUserPrefUtil", "successList is null");
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
                if (i == 102) {
                    LogUtil.a("UIHLH_SubscribeUserPrefUtil", "PREFERENCE_INFO onChange");
                    if (owo.this.f15991a != null) {
                        Message obtainMessage = owo.this.f15991a.obtainMessage();
                        obtainMessage.what = 6;
                        owo.this.f15991a.sendMessage(obtainMessage);
                    }
                }
            }
        });
    }

    public void djc_(Handler handler) {
        this.f15991a = handler;
        if (handler == null) {
            if (koq.c(this.d)) {
                HiHealthNativeApi.a(this.e).unSubscribeHiHealthData(this.d, new HiUnSubscribeListener() { // from class: owo.5
                    @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                    public void onResult(boolean z) {
                        LogUtil.a("UIHLH_SubscribeUserPrefUtil", "unSubscribeUserPreference isSuccess = ", Boolean.valueOf(z));
                    }
                });
            }
            b = null;
        }
    }

    public static void e(ojs ojsVar, HealthTextView healthTextView) {
        LogUtil.a("UIHLH_SubscribeUserPrefUtil", "bindViewHolder enter");
        if (ojsVar == null) {
            LogUtil.a("UIHLH_SubscribeUserPrefUtil", "managementViewCardData is null");
        } else {
            healthTextView.setText(ojsVar.e());
        }
    }

    public static void djb_(Context context, ojs ojsVar, HealthTextView healthTextView, ImageView imageView) {
        int identifier;
        LogUtil.a("UIHLH_SubscribeUserPrefUtil", "bindViewHolder enter");
        if (ojsVar == null) {
            LogUtil.a("UIHLH_SubscribeUserPrefUtil", "managementViewCardData is null");
            return;
        }
        healthTextView.setText(ojsVar.e());
        String a2 = ojsVar.a();
        if (a2 == null || a2.length() == 0 || (identifier = context.getResources().getIdentifier(a2, "drawable", context.getPackageName())) <= 0) {
            return;
        }
        imageView.setBackground(context.getResources().getDrawable(identifier));
    }
}
