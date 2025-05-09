package defpackage;

import android.text.TextUtils;
import androidx.collection.SimpleArrayMap;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.util.LogUtil;
import java.nio.channels.SocketChannel;

/* loaded from: classes5.dex */
public class jui {
    private final SimpleArrayMap<SocketChannel, juk> e = new SimpleArrayMap<>(8);
    private final SimpleArrayMap<String, juk> b = new SimpleArrayMap<>(8);
    private final Object d = new Object();

    public void c(juk jukVar) {
        if (jukVar == null) {
            LogUtil.c("DistributionNetworkProxyStreamsMap", "stream is null");
            return;
        }
        synchronized (this.d) {
            this.e.put(jukVar.f(), jukVar);
            this.b.put(a(jukVar.i(), jukVar.j()), jukVar);
        }
    }

    public juk a(SocketChannel socketChannel) {
        juk jukVar;
        if (socketChannel == null) {
            LogUtil.c("DistributionNetworkProxyStreamsMap", "removeStream() channel is null");
            return new juk(false);
        }
        synchronized (this.d) {
            jukVar = this.e.get(socketChannel);
            if (jukVar != null) {
                this.e.remove(jukVar.f());
                this.b.remove(a(jukVar.i(), jukVar.j()));
                LogUtil.d("DistributionNetworkProxyStreamsMap", "had removed from map");
            }
        }
        return jukVar;
    }

    public void c() {
        synchronized (this.d) {
            this.e.clear();
            this.b.clear();
        }
    }

    public juk d(SocketChannel socketChannel) {
        juk jukVar;
        if (socketChannel == null) {
            LogUtil.c("DistributionNetworkProxyStreamsMap", "getStream() channel is null");
            return new juk(false);
        }
        synchronized (this.d) {
            jukVar = this.e.get(socketChannel);
        }
        return jukVar;
    }

    public juk b(String str, int i) {
        juk jukVar;
        if (TextUtils.isEmpty(str) || i < 0) {
            LogUtil.c("DistributionNetworkProxyStreamsMap", "nodeId: ", str, "streamId: ", Integer.valueOf(i));
            return new juk(false);
        }
        synchronized (this.d) {
            jukVar = this.b.get(a(str, i));
        }
        return jukVar;
    }

    private static String a(String str, int i) {
        if (TextUtils.isEmpty(str) || i < 0) {
            LogUtil.c("DistributionNetworkProxyStreamsMap", "nodeId: ", str, "streamId: ", Integer.valueOf(i));
            return "";
        }
        return str + Constants.LINK + i;
    }
}
