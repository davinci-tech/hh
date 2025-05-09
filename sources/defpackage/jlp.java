package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback;
import com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jlp {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13942a = new Object();
    private static volatile jlp d;
    private Map<IAppTransferFileResultAIDLCallback, List<Integer>> b = new HashMap(16);
    private com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback c = new IAppTransferFileResultAIDLCallback.Stub() { // from class: jlp.3
        @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
        public void onFileTransferState(int i, String str) {
            LogUtil.a("AarTransferFileRequest", "mWatchFaceResultAidlCallback, onFileTransferState percentage:", Integer.valueOf(i), ",des：", str);
            if (jlp.this.b.size() > 0) {
                jlp.this.a(i, str, "transferstate");
            }
        }

        @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
        public void onUpgradeFailed(int i, String str) {
            LogUtil.a("AarTransferFileRequest", "mWatchFaceResultAidlCallback, onUpgradeFailed errorCode:", Integer.valueOf(i), ",des:", str);
            if (jlp.this.b.size() > 0) {
                jlp.this.a(i, str, "FAILED");
            }
        }

        @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
        public void onFileRespond(int i, String str) {
            LogUtil.a("AarTransferFileRequest", "mWatchFaceResultAidlCallback, checkResult:", Integer.valueOf(i), ",des：", str);
            if (jlp.this.b.size() > 0) {
                jlp.this.a(i, str, "RESPOND");
            }
        }
    };

    private jlp() {
        LogUtil.a("AarTransferFileRequest", "create AarTransferFileRequest");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str, String str2) {
        int b = b(str);
        for (Map.Entry<com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback, List<Integer>> entry : this.b.entrySet()) {
            for (Integer num : entry.getValue()) {
                LogUtil.a("AarTransferFileRequest", "entry type is:", num, ",fileType is:", Integer.valueOf(b));
                a(i, str, str2, num.intValue(), entry.getKey());
            }
        }
    }

    private void a(int i, String str, String str2, int i2, com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) {
        char c;
        if (i2 == b(str)) {
            str2.hashCode();
            int hashCode = str2.hashCode();
            if (hashCode == -1995487354) {
                if (str2.equals("transferstate")) {
                    c = 0;
                }
                c = 65535;
            } else if (hashCode != 1815383157) {
                if (hashCode == 2066319421 && str2.equals("FAILED")) {
                    c = 2;
                }
                c = 65535;
            } else {
                if (str2.equals("RESPOND")) {
                    c = 1;
                }
                c = 65535;
            }
            if (c == 0) {
                LogUtil.a("AarTransferFileRequest", "deviceStartTransfer onFileTransferState percentage: ", Integer.valueOf(i));
                iAppTransferFileResultAIDLCallback.onFileTransferState(i, str);
            } else if (c == 1) {
                LogUtil.a("AarTransferFileRequest", "deviceStartTransfer onFileRespond checkResult: ", Integer.valueOf(i));
                iAppTransferFileResultAIDLCallback.onFileRespond(i, str);
            } else if (c == 2) {
                LogUtil.a("AarTransferFileRequest", "deviceStartTransfer onUpgradeFailed errorCode: ", Integer.valueOf(i));
                iAppTransferFileResultAIDLCallback.onUpgradeFailed(i, str);
            } else {
                LogUtil.h("AarTransferFileRequest", "call back error");
            }
        }
    }

    public static jlp d() {
        jlp jlpVar;
        synchronized (f13942a) {
            if (d == null) {
                d = new jlp();
            }
            jlpVar = d;
        }
        return jlpVar;
    }

    public com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback e() {
        LogUtil.h("AarTransferFileRequest", "getCallback");
        return this.c;
    }

    public com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback a(final com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) {
        LogUtil.h("AarTransferFileRequest", "getDefaultCallback");
        return new IAppTransferFileResultAIDLCallback.Stub() { // from class: jlp.5
            @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
            public void onFileTransferState(int i, String str) {
                iAppTransferFileResultAIDLCallback.onFileTransferState(i, str);
            }

            @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
            public void onUpgradeFailed(int i, String str) {
                LogUtil.a("AarTransferFileRequest", "mWatchFaceResultAidlCallback, onUpgradeFailed:", Integer.valueOf(i));
                iAppTransferFileResultAIDLCallback.onUpgradeFailed(i, str);
            }

            @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
            public void onFileRespond(int i, String str) {
                LogUtil.a("AarTransferFileRequest", "mWatchFaceResultAidlCallback, checkResult:", Integer.valueOf(i));
                iAppTransferFileResultAIDLCallback.onFileRespond(i, str);
            }
        };
    }

    public void b(List<Integer> list, com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) {
        LogUtil.a("AarTransferFileRequest", "registToDevicesCallback put");
        this.b.put(iAppTransferFileResultAIDLCallback, list);
        LogUtil.a("AarTransferFileRequest", "mAarCallbackMap size :", Integer.valueOf(this.b.size()));
    }

    public void d(List<Integer> list, com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) {
        LogUtil.a("AarTransferFileRequest", "unregisterCallback remove");
        this.b.remove(iAppTransferFileResultAIDLCallback);
        LogUtil.a("AarTransferFileRequest", "mAarCallbackMap size :", Integer.valueOf(this.b.size()));
    }

    private int b(String str) {
        try {
            return new JSONObject(str).getInt("fileType");
        } catch (JSONException unused) {
            LogUtil.b("AarTransferFileRequest", "JSONObject is eror");
            return 0;
        }
    }
}
