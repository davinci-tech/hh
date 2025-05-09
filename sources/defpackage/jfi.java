package defpackage;

import android.os.RemoteException;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jfi {
    private Map<IAppTransferFileResultAIDLCallback, List<Integer>> c;
    private IAppTransferFileResultAIDLCallback d;

    private jfi() {
        this.c = new HashMap(16);
        this.d = new IAppTransferFileResultAIDLCallback.Stub() { // from class: jfi.1
            @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
            public void onFileTransferState(int i, String str) {
                LogUtil.a("CommonTransferFileRequest", "mWatchFaceResultAidlCallback, onFileTransferState percentage:", Integer.valueOf(i), ",des", str);
                if (jfi.this.c.size() > 0) {
                    int b = jfi.this.b(str);
                    for (Map.Entry entry : jfi.this.c.entrySet()) {
                        if (entry != null) {
                            for (Integer num : (List) entry.getValue()) {
                                LogUtil.a("CommonTransferFileRequest", "entry type is:", num, ",fileType is:", Integer.valueOf(b));
                                if (b != 0 && num.intValue() == b) {
                                    IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback = (IAppTransferFileResultAIDLCallback) entry.getKey();
                                    try {
                                        LogUtil.a("CommonTransferFileRequest", "deviceStartTransfer onFileTransferState percentage: ", Integer.valueOf(i));
                                        if (iAppTransferFileResultAIDLCallback != null) {
                                            iAppTransferFileResultAIDLCallback.onFileTransferState(i, str);
                                            LogUtil.a("CommonTransferFileRequest", "onFileTransferState success");
                                        }
                                    } catch (RemoteException unused) {
                                        LogUtil.b("CommonTransferFileRequest", "onFileTransferState remoteException");
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
            public void onUpgradeFailed(int i, String str) {
                LogUtil.a("CommonTransferFileRequest", "mWatchFaceResultAidlCallback, onUpgradeFailed errorCode:", Integer.valueOf(i), ",des", str);
                if (jfi.this.c.size() > 0) {
                    int b = jfi.this.b(str);
                    for (Map.Entry entry : jfi.this.c.entrySet()) {
                        if (entry != null) {
                            for (Integer num : (List) entry.getValue()) {
                                LogUtil.a("CommonTransferFileRequest", "entry type is:", num, ",fileType is:", Integer.valueOf(b));
                                if (b != 0 && num.intValue() == b) {
                                    IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback = (IAppTransferFileResultAIDLCallback) entry.getKey();
                                    try {
                                        LogUtil.a("CommonTransferFileRequest", "deviceStartTransfer onUpgradeFailed errorCode: ", Integer.valueOf(i));
                                        if (iAppTransferFileResultAIDLCallback != null) {
                                            iAppTransferFileResultAIDLCallback.onUpgradeFailed(i, str);
                                            LogUtil.a("CommonTransferFileRequest", "onFileTransferState success");
                                        }
                                    } catch (RemoteException unused) {
                                        LogUtil.b("CommonTransferFileRequest", "onFileTransferState remoteException");
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
            public void onFileRespond(int i, String str) {
                LogUtil.a("CommonTransferFileRequest", "mWatchFaceResultAidlCallback, checkResult:", Integer.valueOf(i), ",des", str);
                if (jfi.this.c.size() > 0) {
                    int b = jfi.this.b(str);
                    for (Map.Entry entry : jfi.this.c.entrySet()) {
                        if (entry != null) {
                            for (Integer num : (List) entry.getValue()) {
                                LogUtil.a("CommonTransferFileRequest", "entry type is:", num, ",fileType is:", Integer.valueOf(b));
                                if (b != 0 && num.intValue() == b) {
                                    IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback = (IAppTransferFileResultAIDLCallback) entry.getKey();
                                    try {
                                        LogUtil.a("CommonTransferFileRequest", "deviceStartTransfer onFileRespond checkResult: ", Integer.valueOf(i));
                                        if (iAppTransferFileResultAIDLCallback != null) {
                                            iAppTransferFileResultAIDLCallback.onFileRespond(i, str);
                                            LogUtil.a("CommonTransferFileRequest", "onFileTransferState success");
                                        }
                                    } catch (RemoteException unused) {
                                        LogUtil.b("CommonTransferFileRequest", "onFileTransferState remoteException");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        };
        LogUtil.a("CommonTransferFileRequest", "create AarCommonTransferFileRequest");
    }

    public static jfi c() {
        return a.d;
    }

    public void c(List<Integer> list, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) {
        LogUtil.a("CommonTransferFileRequest", "registToDevicesCallback put");
        if (iAppTransferFileResultAIDLCallback == null) {
            LogUtil.h("CommonTransferFileRequest", "callback is null");
        } else {
            this.c.put(iAppTransferFileResultAIDLCallback, list);
            LogUtil.a("CommonTransferFileRequest", "mCommonCallbackMap size is :", Integer.valueOf(this.c.size()));
        }
    }

    public void b(List<Integer> list, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) {
        LogUtil.a("CommonTransferFileRequest", "unregisterCallback remove");
        if (iAppTransferFileResultAIDLCallback == null) {
            LogUtil.h("CommonTransferFileRequest", "callback is null");
        } else {
            this.c.remove(iAppTransferFileResultAIDLCallback);
            LogUtil.a("CommonTransferFileRequest", "mCommonCallbackMap size is :", Integer.valueOf(this.c.size()));
        }
    }

    public IAppTransferFileResultAIDLCallback a() {
        LogUtil.h("CommonTransferFileRequest", "getCallback");
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(String str) {
        try {
            return new JSONObject(str).getInt("fileType");
        } catch (JSONException unused) {
            LogUtil.a("CommonTransferFileRequest", "JSONObject is eror");
            return 0;
        }
    }

    static class a {
        private static jfi d = new jfi();
    }
}
