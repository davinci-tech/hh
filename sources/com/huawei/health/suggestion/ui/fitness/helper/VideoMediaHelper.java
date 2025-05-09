package com.huawei.health.suggestion.ui.fitness.helper;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.IOException;
import java.util.List;

/* loaded from: classes4.dex */
public class VideoMediaHelper extends MediaHelper {
    private int j;

    public VideoMediaHelper(Context context) {
        super(context);
        this.j = 0;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.MediaHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface start() {
        this.j = 0;
        return super.start();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.MediaHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface setMediaSources(Uri... uriArr) {
        this.j = 0;
        return super.setMediaSources(uriArr);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.MediaHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface setAssetSources(String... strArr) {
        this.j = 0;
        return super.setAssetSources(strArr);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.MediaHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface setAudioAssetSources(List<String> list) {
        this.j = 0;
        return super.setAudioAssetSources(list);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.MediaHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface setSdSources(String... strArr) {
        this.j = 0;
        return super.setSdSources(strArr);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.MediaHelper
    public VideoInterface a(List<String> list) {
        this.j = 0;
        return super.a(list);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.MediaHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface setRawSources(Integer... numArr) {
        this.j = 0;
        return super.setRawSources(numArr);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.MediaHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface stop() {
        this.j = 0;
        return super.stop();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.MediaHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface pause() {
        if (this.e != null && this.e.isPlaying()) {
            this.e.pause();
            this.j = this.e.getCurrentPosition();
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.MediaHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface videoContinue() {
        String g;
        LogUtil.a("Suggestion_VideoMediaHelper", "Continue");
        if (this.e != null) {
            try {
                g = g();
                LogUtil.a("Suggestion_VideoMediaHelper", "Continue  currentName = ", g);
            } catch (IOException | IllegalArgumentException | IllegalStateException | SecurityException e) {
                LogUtil.b("Suggestion_VideoMediaHelper", "setDataSource fail -- ", e.getMessage());
            }
            if (TextUtils.isEmpty(g)) {
                return this;
            }
            this.e.reset();
            this.e.setDataSource(g);
            this.e.setLooping(true);
            this.e.prepare();
            LogUtil.a("Suggestion_VideoMediaHelper", "Continue  mCurrentPosition = ", Integer.valueOf(this.j));
            if (this.j != 0) {
                this.e.seekTo(this.j);
            }
            this.j = 0;
            k();
            this.e.start();
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.MediaHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface release() {
        this.j = 0;
        return super.release();
    }
}
