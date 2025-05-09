package defpackage;

import android.content.Context;
import com.huawei.pluginbase.PluginBaseAdapter;

/* loaded from: classes.dex */
public class mml {
    private PluginBaseAdapter mAdapter;

    public void finish() {
    }

    public void init(Context context) {
    }

    public PluginBaseAdapter getAdapter() {
        return (PluginBaseAdapter) jdy.d(this.mAdapter);
    }

    public void setAdapter(PluginBaseAdapter pluginBaseAdapter) {
        this.mAdapter = (PluginBaseAdapter) jdy.d(pluginBaseAdapter);
    }
}
