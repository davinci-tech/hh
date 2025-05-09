package com.huawei.hms.ads;

/* loaded from: classes4.dex */
public class VideoConfiguration {
    int autoPlayNetwork;
    boolean isMute;

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        int f4287a = 0;
        boolean b = true;

        public Builder setMute(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setAutoPlayNetwork(int i) {
            this.f4287a = i;
            return this;
        }

        public VideoConfiguration build() {
            return new VideoConfiguration(this);
        }
    }

    public boolean isMute() {
        return this.isMute;
    }

    public int getAutoPlayNetwork() {
        return this.autoPlayNetwork;
    }

    public VideoConfiguration(Builder builder) {
        this.autoPlayNetwork = 0;
        this.isMute = true;
        this.autoPlayNetwork = builder.f4287a;
        this.isMute = builder.b;
    }

    public VideoConfiguration() {
        this.autoPlayNetwork = 0;
        this.isMute = true;
    }
}
