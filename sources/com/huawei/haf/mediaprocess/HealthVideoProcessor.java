package com.huawei.haf.mediaprocess;

import android.content.Context;
import android.media.MediaExtractor;
import android.net.Uri;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes.dex */
public class HealthVideoProcessor {

    /* renamed from: a, reason: collision with root package name */
    public static final Integer f2131a = 20;

    private HealthVideoProcessor() {
    }

    public static class Processor {

        /* renamed from: a, reason: collision with root package name */
        private boolean f2133a = true;
        private Context e;

        public Processor(Context context) {
            this.e = context;
        }
    }

    public static class MediaSource {

        /* renamed from: a, reason: collision with root package name */
        private Uri f2132a;
        private Context b;
        private String c;

        public void zd_(MediaExtractor mediaExtractor) throws IOException {
            String str = this.c;
            if (str != null) {
                mediaExtractor.setDataSource(str);
            } else {
                mediaExtractor.setDataSource(this.b, this.f2132a, (Map<String, String>) null);
            }
        }
    }
}
