package com.huawei.health.suggestion.ui.fitness.mvp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.adapter.NewDetailInfoAdapter;
import com.huawei.health.suggestion.ui.fitness.mvp.VideoPlayer;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.LayoutTemplateInfo;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import java.util.List;

/* loaded from: classes4.dex */
public class NewViewDetailInfo {

    /* renamed from: a, reason: collision with root package name */
    private HealthRecycleView f3196a;
    private VideoPlayer b;
    private NewDetailInfoAdapter c;
    private ViewVideoPlayListener d;
    private View e;

    public NewViewDetailInfo(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.new_view_detail_info, (ViewGroup) null);
        this.e = inflate;
        this.f3196a = (HealthRecycleView) inflate.findViewById(R.id.rv_main_id);
        this.f3196a.setLayoutManager(new LinearLayoutManager(context, 1, false));
        VideoPlayer videoPlayer = new VideoPlayer(context);
        this.b = videoPlayer;
        videoPlayer.c();
        this.b.b(new VideoPlayer.VideoPlayPositionListener() { // from class: com.huawei.health.suggestion.ui.fitness.mvp.NewViewDetailInfo.4
            @Override // com.huawei.health.suggestion.ui.fitness.mvp.VideoPlayer.VideoPlayPositionListener
            public void onPlayPositionChange(int i, int i2) {
                if (NewViewDetailInfo.this.d != null) {
                    NewViewDetailInfo.this.d.onVideoStartPlay();
                }
                if (NewViewDetailInfo.this.c != null) {
                    NewViewDetailInfo.this.c.e(i, i2);
                }
            }
        });
        NewDetailInfoAdapter newDetailInfoAdapter = new NewDetailInfoAdapter(this.b);
        this.c = newDetailInfoAdapter;
        this.f3196a.setAdapter(newDetailInfoAdapter);
    }

    public View aEI_() {
        LogUtil.a("Suggestion_NewViewDetailInfo", "getRootView ok");
        return this.e;
    }

    public void c(LayoutTemplateInfo layoutTemplateInfo) {
        if (layoutTemplateInfo == null) {
            LogUtil.b("Suggestion_NewViewDetailInfo", "templateInfo is null");
        } else {
            this.c.b(layoutTemplateInfo);
            LogUtil.a("Suggestion_NewViewDetailInfo", "bindData ok");
        }
    }

    public void d() {
        VideoPlayer videoPlayer = this.b;
        if (videoPlayer != null) {
            videoPlayer.f();
        }
    }

    public void a() {
        VideoPlayer videoPlayer = this.b;
        if (videoPlayer != null) {
            videoPlayer.a();
        }
    }

    public void b(List<FitWorkout> list, ArrayMap<String, String> arrayMap) {
        this.c.a(list, arrayMap);
    }

    public void c() {
        LogUtil.a("Suggestion_NewViewDetailInfo", "stopPlayVideo");
        this.c.e(-1, -1);
        this.c.notifyDataSetChanged();
    }

    public void a(ViewVideoPlayListener viewVideoPlayListener) {
        this.d = viewVideoPlayListener;
    }
}
