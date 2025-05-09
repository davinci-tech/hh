package defpackage;

import com.huawei.wearengine.channel.WearEngineChannel;

/* loaded from: classes7.dex */
public class toj implements WearEngineChannel {
    private volatile int d = 0;
    private final Object c = new Object();

    @Override // com.huawei.wearengine.channel.WearEngineChannel
    public void updateChannelState(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("state can only be 0 or 1.");
        }
        this.d = i;
    }

    @Override // com.huawei.wearengine.channel.WearEngineChannel
    public void reset() {
        this.d = 0;
    }

    @Override // com.huawei.wearengine.channel.WearEngineChannel
    public int getState() {
        return this.d;
    }

    @Override // com.huawei.wearengine.channel.WearEngineChannel
    public boolean isChannelOpen() {
        return this.d == 0;
    }
}
