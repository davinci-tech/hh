package okhttp3;

import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hms.network.embedded.y;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import com.huawei.watchface.videoedit.gles.Constant;
import defpackage.uhy;
import kotlin.Metadata;
import okio.ByteString;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J \u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\"\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\nH\u0016J\u0018\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006\u0016"}, d2 = {"Lokhttp3/WebSocketListener;", "", "()V", "onClosed", "", "webSocket", "Lokhttp3/WebSocket;", "code", "", "reason", "", "onClosing", "onFailure", FitRunPlayAudio.PLAY_TYPE_T, "", TrackConstants$Opers.RESPONSE, "Lokhttp3/Response;", "onMessage", Constant.TEXT, "bytes", "Lokio/ByteString;", "onOpen", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class WebSocketListener {
    public void onOpen(WebSocket webSocket, Response response) {
        uhy.e((Object) webSocket, "");
        uhy.e((Object) response, "");
    }

    public void onMessage(WebSocket webSocket, ByteString bytes) {
        uhy.e((Object) webSocket, "");
        uhy.e((Object) bytes, "");
    }

    public void onMessage(WebSocket webSocket, String text) {
        uhy.e((Object) webSocket, "");
        uhy.e((Object) text, "");
    }

    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        uhy.e((Object) webSocket, "");
        uhy.e((Object) t, "");
    }

    public void onClosing(WebSocket webSocket, int code, String reason) {
        uhy.e((Object) webSocket, "");
        uhy.e((Object) reason, "");
    }

    public void onClosed(WebSocket webSocket, int code, String reason) {
        uhy.e((Object) webSocket, "");
        uhy.e((Object) reason, "");
    }
}
