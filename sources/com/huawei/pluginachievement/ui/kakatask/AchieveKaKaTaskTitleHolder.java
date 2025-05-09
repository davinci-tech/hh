package com.huawei.pluginachievement.ui.kakatask;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import defpackage.mfm;
import defpackage.mkg;
import defpackage.nrt;

/* loaded from: classes8.dex */
public class AchieveKaKaTaskTitleHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private TextView f8442a;
    private RelativeLayout b;
    private LinearLayout c;
    private TextView e;

    AchieveKaKaTaskTitleHolder(View view) {
        super(view);
        this.e = (TextView) mfm.cgM_(view, R.id.achieve_task_kaka_title_text);
        this.b = (RelativeLayout) mfm.cgM_(view, R.id.achieve_task_kaka_title_text_rl);
        this.c = (LinearLayout) mfm.cgM_(view, R.id.task_finish_tip_layout);
        this.f8442a = (TextView) mfm.cgM_(view, R.id.task_finish_title_text);
    }

    public void d(mkg mkgVar, int i, int i2) {
        if (mkgVar != null) {
            this.f8442a.setText(BaseApplication.e().getString(R.string._2130841077_res_0x7f020df5, String.valueOf(i), String.valueOf(i2)));
            this.e.setVisibility(0);
            String e = mkgVar.e();
            if (TextUtils.isEmpty(e)) {
                this.b.setVisibility(8);
                return;
            }
            this.e.setVisibility(0);
            this.e.setText(mkgVar.e());
            b(e, i);
        }
    }

    private void b(String str, int i) {
        if (str.equals(BaseApplication.e().getString(R.string._2130840769_res_0x7f020cc1))) {
            this.c.setVisibility(0);
            ImageView[] imageViewArr = {(ImageView) mfm.cgM_(this.itemView, R.id.taskOne), (ImageView) mfm.cgM_(this.itemView, R.id.taskSec), (ImageView) mfm.cgM_(this.itemView, R.id.taskTh), (ImageView) mfm.cgM_(this.itemView, R.id.taskFo), (ImageView) mfm.cgM_(this.itemView, R.id.taskFi), (ImageView) mfm.cgM_(this.itemView, R.id.taskSix)};
            for (int i2 = 0; i2 < 6; i2++) {
                imageViewArr[i2].setImageResource(nrt.a(BaseApplication.e()) ? R.mipmap._2131821344_res_0x7f110320 : R.mipmap._2131821343_res_0x7f11031f);
                if (i2 < i) {
                    imageViewArr[i2].setImageResource(R.mipmap._2131821340_res_0x7f11031c);
                }
            }
        }
    }
}
