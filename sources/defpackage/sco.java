package defpackage;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import java.util.List;

/* loaded from: classes7.dex */
public class sco {
    private static int b(int i) {
        if (i != 0) {
            if (i == 1) {
                return R.drawable._2131430729_res_0x7f0b0d49;
            }
            if (i == 2) {
                return R.mipmap._2131821398_res_0x7f110356;
            }
        }
        return R.mipmap._2131820660_res_0x7f110074;
    }

    public static void a(final ResponseCallback<Object> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("Step_DetailStepKakaUtil", "getKakalist callback is null");
            return;
        }
        Task<List<mdf>> taskInfoListByRule = bzw.e().getTaskInfoListByRule(BaseApplication.getContext(), 20001);
        if (taskInfoListByRule == null) {
            LogUtil.h("Step_DetailStepKakaUtil", "getTaskInfoListByRule taskList is null");
            responseCallback.onResult(404, null);
        } else {
            taskInfoListByRule.addOnSuccessListener(new OnSuccessListener<List<mdf>>() { // from class: sco.3
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<mdf> list) {
                    ResponseCallback.this.onResult(100, list);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: sco.5
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    LogUtil.h("Step_DetailStepKakaUtil", "exception ", LogAnonymous.b((Throwable) exc));
                    ResponseCallback.this.onResult(404, null);
                }
            });
        }
    }

    public static View dVR_(int i, int i2) {
        LinearLayout.LayoutParams layoutParams;
        LogUtil.a("Step_DetailStepKakaUtil", "addKakaView marginLeft ", Integer.valueOf(i));
        Context context = BaseApplication.getContext();
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(b(i2));
        imageView.setBackgroundColor(context.getResources().getColor(R.color._2131299296_res_0x7f090be0));
        if (i2 == 1) {
            layoutParams = new LinearLayout.LayoutParams(nsn.c(context, 20.0f), nsn.c(context, 20.0f));
        } else {
            layoutParams = new LinearLayout.LayoutParams(nsn.c(context, 16.0f), nsn.c(context, 16.0f));
        }
        layoutParams.gravity = 16;
        if (LanguageUtil.bc(context)) {
            layoutParams.rightMargin = i;
        } else {
            layoutParams.leftMargin = i;
        }
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

    public static void dVS_(Drawable drawable) {
        AnimationDrawable animationDrawable = drawable instanceof AnimationDrawable ? (AnimationDrawable) drawable : null;
        if (animationDrawable != null) {
            if (animationDrawable.isRunning()) {
                animationDrawable.stop();
            }
            animationDrawable.start();
        }
    }
}
