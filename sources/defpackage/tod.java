package defpackage;

import com.huawei.wearengine.channel.ChannelState;

/* loaded from: classes7.dex */
public class tod implements ChannelState {
    private String d;
    private int e;

    public void b(int i) {
        this.e = i;
    }

    public String c() {
        String str = this.d;
        return str == null ? "" : str;
    }

    public void b(String str) {
        this.d = str;
    }

    @Override // com.huawei.wearengine.channel.ChannelState
    public boolean isChannelOpen() {
        return this.e == 1;
    }
}
