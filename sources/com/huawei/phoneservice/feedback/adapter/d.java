package com.huawei.phoneservice.feedback.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.phoneservice.feedback.mvp.base.FeedbackCITListView;

/* loaded from: classes5.dex */
public class d extends com.huawei.phoneservice.feedback.widget.c {

    /* renamed from: a, reason: collision with root package name */
    TextView f8254a;
    TextView b;
    TextView c;
    TextView d;
    TextView e;
    ImageView f;
    View g;
    ImageView h;
    FeedbackCITListView i;
    FeedbackCITListView j;
    View k;
    LinearLayout o;

    private void d() {
        this.d = (TextView) this.itemView.findViewById(R.id.tv_evaluad_content);
        this.e = (TextView) this.itemView.findViewById(R.id.imgfrom_chat_time);
        this.b = (TextView) this.itemView.findViewById(R.id.from_chat_time);
        this.c = (TextView) this.itemView.findViewById(R.id.mycontent);
        this.i = (FeedbackCITListView) this.itemView.findViewById(R.id.question_img_list);
        this.j = (FeedbackCITListView) this.itemView.findViewById(R.id.replay_img_lsit);
        this.f = (ImageView) this.itemView.findViewById(R.id.img_resolved);
        this.h = (ImageView) this.itemView.findViewById(R.id.img_no_resolved);
        this.g = this.itemView.findViewById(R.id.feedback_layout_evaluat);
        this.k = this.itemView.findViewById(R.id.feedback_sdk_item_msgfrom);
        this.f8254a = (TextView) this.itemView.findViewById(R.id.from_content);
        this.o = (LinearLayout) this.itemView.findViewById(R.id.ll_evaluat);
    }

    public d(View view) {
        super(view);
        d();
    }
}
