package com.huawei.health.suggestion.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.pluginfitnessadvice.audio.SleepAudioSeries;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.bzs;
import defpackage.ggs;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.util.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class MySingleCourseAdapter extends RecyclerView.Adapter<c> {

    /* renamed from: a, reason: collision with root package name */
    private Context f3065a;
    private List<SleepAudioSeries> b;
    private OnItemClickListener d;
    private int e;

    public interface OnItemClickListener {
        void onItemCheckBoxClick(View view, int i);
    }

    public MySingleCourseAdapter(Context context, List<SleepAudioSeries> list, int i) {
        this.f3065a = context;
        this.b = list;
        this.e = i;
    }

    public List<SleepAudioSeries> c() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: ayG_, reason: merged with bridge method [inline-methods] */
    public c onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new c(LayoutInflater.from(this.f3065a).inflate(R.layout.sug_my_course_single_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(c cVar, int i) {
        LogUtil.d("Suggestion_MySingleCourseAdapter", "MySingleCourseAdapter onBindViewHolder position = ", Integer.valueOf(i));
        if (koq.b(this.b, i)) {
            LogUtil.c("Suggestion_MySingleCourseAdapter", "onBindViewHolder : out of bound! position:", Integer.valueOf(i));
            return;
        }
        SleepAudioSeries sleepAudioSeries = this.b.get(i);
        if (sleepAudioSeries == null) {
            LogUtil.c("Suggestion_MySingleCourseAdapter", "onBindViewHolder : singleData == null");
            return;
        }
        String valueOf = String.valueOf(sleepAudioSeries.getId());
        LogUtil.d("Suggestion_MySingleCourseAdapter", "onBindViewHolder audioCourseId = ", valueOf);
        cVar.e.setText(sleepAudioSeries.getName());
        cVar.d.setVisibility(sleepAudioSeries.getIsVip() == 1 ? 0 : 8);
        nrf.cIS_(cVar.b, sleepAudioSeries.getSmallImage(), nrf.e, 0, R.drawable._2131431605_res_0x7f0b10b5);
        d(cVar, sleepAudioSeries, valueOf);
        b(cVar, sleepAudioSeries, i);
    }

    private void d(final c cVar, final SleepAudioSeries sleepAudioSeries, final String str) {
        cVar.g.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.adapter.MySingleCourseAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (cVar.f3068a.getVisibility() == 0) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                if (nsn.cLj_(500, view)) {
                    LogUtil.c("Suggestion_MySingleCourseAdapter", "itemView click too fast.");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.d("Suggestion_MySingleCourseAdapter", "onClick() audioCourseId = ", str);
                    MySingleCourseAdapter.this.e(str, sleepAudioSeries, false);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
        cVar.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.adapter.MySingleCourseAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (cVar.f3068a.getVisibility() == 0) {
                    ViewClickInstrumentation.clickOnView(view);
                } else if (!nsn.cLj_(500, view)) {
                    MySingleCourseAdapter.this.e(str, sleepAudioSeries, true);
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.c("Suggestion_MySingleCourseAdapter", "itemView click too fast.");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    private void b(c cVar, SleepAudioSeries sleepAudioSeries, final int i) {
        if (sleepAudioSeries.getIsShowCheckBox() == 1) {
            cVar.f3068a.setVisibility(0);
        } else {
            cVar.f3068a.setVisibility(8);
        }
        if (sleepAudioSeries.getIsSelected() == 1) {
            cVar.f3068a.setChecked(true);
        } else {
            cVar.f3068a.setChecked(false);
        }
        cVar.f3068a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.adapter.MySingleCourseAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MySingleCourseAdapter.this.d != null) {
                    MySingleCourseAdapter.this.d.onItemCheckBoxClick(view, i);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SleepAudioSeries> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, SleepAudioSeries sleepAudioSeries, boolean z) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            LogUtil.d("Suggestion_MySingleCourseAdapter", "gotoSeriesCourseH5 id is empty");
            return;
        }
        if (ggs.d(this.e, sleepAudioSeries.getAudioType())) {
            str2 = "com.huawei.health.h5.sleeping-music?h5pro=true&urlType=4&pName=com.huawei.health&path=audioDetail&statusBarTextBlack&isImmerse&id=" + str + "&type=sleepAudio";
        } else {
            str2 = "";
        }
        if (ggs.e(this.e, sleepAudioSeries.getDecompressType())) {
            str2 = "com.huawei.health.h5.sleeping-music?h5pro=true&urlType=4&pName=com.huawei.health&path=audioDetail&statusBarTextBlack&isImmerse&id=" + str + "&type=decompressAudio";
        }
        if (z) {
            str2 = str2 + "&playing=true";
        }
        LogUtil.d("Suggestion_MySingleCourseAdapter", "gotoAudioCourseH5 url = ", str2);
        bzs.e().startOperationWebPage(this.f3065a, str2);
    }

    public void e(OnItemClickListener onItemClickListener) {
        this.d = onItemClickListener;
    }

    static class c extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthCheckBox f3068a;
        private ImageView b;
        private ImageView c;
        private HealthTextView d;
        private HealthTextView e;
        private ConstraintLayout g;

        c(View view) {
            super(view);
            this.g = (ConstraintLayout) view.findViewById(R.id.single_course_item);
            this.e = (HealthTextView) view.findViewById(R.id.course_title);
            this.c = (ImageView) view.findViewById(R.id.audio_play_image_button);
            this.f3068a = (HealthCheckBox) view.findViewById(R.id.single_audio_course_checkbox);
            this.d = (HealthTextView) view.findViewById(R.id.single_audio_course_tag);
            this.b = (ImageView) view.findViewById(R.id.recycle_item_picture);
        }
    }
}
