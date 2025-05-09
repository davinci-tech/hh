package com.huawei.android.hicloud.sync.logic;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.android.hicloud.sync.service.aidl.FileDownloadProgress;
import com.huawei.android.hicloud.sync.service.aidl.ISyncServiceCallback;
import com.huawei.android.hicloud.sync.service.aidl.UnstructData;
import com.huawei.operation.utils.Constants;
import defpackage.aag;
import defpackage.aay;
import defpackage.abd;
import defpackage.abl;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
abstract class n {

    /* renamed from: a, reason: collision with root package name */
    final aay f1834a;
    SyncDownloadInterface d;
    private final String e;
    private final String f;
    private final String g;
    private Context h;
    final ISyncServiceCallback j;
    private HandlerThread b = null;
    private Handler c = null;
    StringBuffer i = new StringBuffer();

    class c extends Handler {
        c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            String str;
            try {
                abd.c("DownloadSyncBase", "Begin handle download Message msg.what = " + message.what);
                Bundle bundle = (Bundle) message.obj;
                if (bundle == null) {
                    abd.d("DownloadSyncBase", "Receive download bundle is null");
                    return;
                }
                bundle.setClassLoader(n.class.getClassLoader());
                String string = bundle.getString("callbackUuid");
                str = bundle.getString("session_id");
                try {
                    if (TextUtils.equals(string, n.this.f1834a.b())) {
                        boolean z = bundle.getBoolean("is_in_batches", false);
                        if (!z || n.this.a(bundle)) {
                            n.this.a(message, bundle, z);
                            return;
                        }
                        return;
                    }
                    abd.d("DownloadSyncBase", "download msg not equal: targetUuid = " + string + ", thisUuid = " + n.this.f1834a.b());
                } catch (Exception e) {
                    e = e;
                    abd.d("DownloadSyncBase", "handle download msg error: " + e.toString());
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    n.this.b(str, "handle download msg error");
                }
            } catch (Exception e2) {
                e = e2;
                str = null;
            }
        }
    }

    class e extends ISyncServiceCallback.Stub {
        e() {
        }

        @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncServiceCallback
        public void handlerEventMsg(int i, int i2, int i3, Bundle bundle) throws RemoteException {
            StringBuilder sb = new StringBuilder("handler download EventMsg, mHandler is null: ");
            sb.append(n.this.c == null);
            abd.c("DownloadSyncBase", sb.toString());
            if (n.this.c != null) {
                n.this.c.sendMessage(n.this.c.obtainMessage(i, i2, i3, bundle));
            }
        }
    }

    n(Context context, String str, String str2, String str3, SyncDownloadInterface syncDownloadInterface) {
        e eVar = new e();
        this.j = eVar;
        this.h = context;
        this.e = str;
        this.f = str2;
        this.d = syncDownloadInterface;
        this.g = str3;
        this.f1834a = new aay(context, str, str2, str3, eVar);
        b();
        abd.c("DownloadSyncBase", "new DownloadSyncBase, syncType = " + str + ", dataType = " + str2 + ", sessionId = " + str3);
    }

    private void b() {
        if (this.c == null) {
            HandlerThread handlerThread = new HandlerThread("DownloadSyncBase" + this.f1834a.b());
            this.b = handlerThread;
            handlerThread.start();
            this.c = new c(this.b.getLooper());
        }
    }

    private void d(Bundle bundle, boolean z) {
        FileDownloadProgress fileDownloadProgress;
        try {
            if (!z) {
                try {
                    bundle.getString("syncType");
                    bundle.getString("dataType");
                    fileDownloadProgress = (FileDownloadProgress) bundle.getParcelable("down_file_progress_data");
                } catch (ClassCastException e2) {
                    abd.b("DownloadSyncBase", "downLoadFileProgress error:" + e2.toString());
                    fileDownloadProgress = null;
                }
                a(fileDownloadProgress);
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(this.i.toString());
                fileDownloadProgress = (FileDownloadProgress) jSONObject.get("down_file_progress_data");
                this.i = new StringBuffer();
                a(fileDownloadProgress);
                return;
            } catch (JSONException e3) {
                abd.b("DownloadSyncBase", "downLoadFileProgress batch error : " + e3.getMessage());
                return;
            }
        } catch (Exception e4) {
            abd.b("DownloadSyncBase", "downLoadFileProgress error " + e4.getMessage());
        }
        abd.b("DownloadSyncBase", "downLoadFileProgress error " + e4.getMessage());
    }

    private void e(Bundle bundle, boolean z) {
        UnstructData unstructData;
        try {
            if (z) {
                try {
                    JSONObject jSONObject = new JSONObject(this.i.toString());
                    unstructData = (UnstructData) jSONObject.get("down_file_data");
                    this.i = new StringBuffer();
                } catch (JSONException e2) {
                    abd.b("DownloadSyncBase", "downLoadFileSuccess batch error : " + e2.getMessage());
                    return;
                }
            } else {
                try {
                    unstructData = (UnstructData) bundle.getParcelable("down_file_data");
                    try {
                        bundle.getString("syncType");
                        bundle.getString("dataType");
                    } catch (ClassCastException e3) {
                        e = e3;
                        abd.b("DownloadSyncBase", "downLoadFileSuccess error:" + e.toString());
                        b(unstructData);
                    }
                } catch (ClassCastException e4) {
                    e = e4;
                    unstructData = null;
                }
            }
            b(unstructData);
        } catch (Exception e5) {
            abd.b("DownloadSyncBase", "downLoadFileSuccess error " + e5.getMessage());
        }
    }

    protected void c(String str, String str2, List<UnstructData> list, String str3) {
        this.f1834a.d(str, str2, list, str3);
    }

    private void c(Bundle bundle, boolean z) {
        UnstructData unstructData;
        int i;
        try {
            if (z) {
                try {
                    JSONObject jSONObject = new JSONObject(this.i.toString());
                    unstructData = (UnstructData) jSONObject.get("down_file_data");
                    i = jSONObject.getInt("down_file_error_code");
                    this.i = new StringBuffer();
                } catch (JSONException e2) {
                    abd.b("DownloadSyncBase", "downLoadFileFail batch error : " + e2.getMessage());
                    return;
                }
            } else {
                try {
                    bundle.getString("syncType");
                    bundle.getString("dataType");
                    unstructData = (UnstructData) bundle.getParcelable("down_file_data");
                    try {
                        i = bundle.getInt("down_file_error_code");
                    } catch (ClassCastException e3) {
                        e = e3;
                        abd.b("DownloadSyncBase", "downLoadFileFail error:" + e.toString());
                        i = -1;
                        a(unstructData, i);
                    }
                } catch (ClassCastException e4) {
                    e = e4;
                    unstructData = null;
                }
            }
            a(unstructData, i);
        } catch (Exception e5) {
            abd.b("DownloadSyncBase", "downLoadFileFail error " + e5.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Message message, Bundle bundle, boolean z) {
        abd.c("DownloadSyncBase", "Begin process download Message = " + message.what);
        switch (message.what) {
            case 10018:
                d(bundle, z);
                break;
            case 10019:
                e(bundle, z);
                break;
            case 10020:
                c(bundle, z);
                break;
            case 10021:
                b(bundle, z);
                break;
            case 10022:
            default:
                abd.b("DownloadSyncBase", "Receive error download msg, msg.what = " + message.what);
                String string = bundle.getString("session_id");
                if (!TextUtils.isEmpty(string)) {
                    b(string, "Receive error download msg");
                    break;
                }
                break;
            case 10023:
                b(bundle);
                break;
            case 10024:
                a();
                break;
            case 10025:
                a(bundle, z);
                break;
        }
    }

    protected void b(String str, String str2, List<UnstructData> list, String str3) {
        this.f1834a.b(str, str2, list, str3);
    }

    protected void b(String str, String str2) {
        this.f1834a.b(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x009a A[Catch: all -> 0x00aa, Exception -> 0x00ac, Merged into TryCatch #3 {all -> 0x00aa, Exception -> 0x00ac, blocks: (B:3:0x0002, B:22:0x0018, B:24:0x0027, B:26:0x005f, B:10:0x0093, B:12:0x009a, B:13:0x009e, B:29:0x004a, B:6:0x0067, B:8:0x0075, B:20:0x007e, B:34:0x00ad), top: B:2:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(android.os.Bundle r9, boolean r10) {
        /*
            r8 = this;
            java.lang.String r0 = "DownloadSyncBase"
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            r1.<init>()     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            aaz r2 = new aaz     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            r2.<init>()     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            java.lang.String r3 = "down_file_fail_map"
            java.lang.String r4 = "dataType"
            java.lang.String r5 = "syncType"
            java.lang.String r6 = "down_file_success_list"
            java.lang.String r7 = "session_id"
            if (r10 == 0) goto L67
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch: org.json.JSONException -> L47 java.lang.Throwable -> Laa java.lang.Exception -> Lac
            java.lang.StringBuffer r10 = r8.i     // Catch: org.json.JSONException -> L47 java.lang.Throwable -> Laa java.lang.Exception -> Lac
            java.lang.String r10 = r10.toString()     // Catch: org.json.JSONException -> L47 java.lang.Throwable -> Laa java.lang.Exception -> Lac
            r9.<init>(r10)     // Catch: org.json.JSONException -> L47 java.lang.Throwable -> Laa java.lang.Exception -> Lac
            java.lang.String r10 = r9.getString(r7)     // Catch: org.json.JSONException -> L47 java.lang.Throwable -> Laa java.lang.Exception -> Lac
            java.lang.Object r5 = r9.get(r5)     // Catch: org.json.JSONException -> L45 java.lang.Throwable -> Laa java.lang.Exception -> Lac
            java.lang.String r5 = (java.lang.String) r5     // Catch: org.json.JSONException -> L45 java.lang.Throwable -> Laa java.lang.Exception -> Lac
            java.lang.Object r4 = r9.get(r4)     // Catch: org.json.JSONException -> L45 java.lang.Throwable -> Laa java.lang.Exception -> Lac
            java.lang.String r4 = (java.lang.String) r4     // Catch: org.json.JSONException -> L45 java.lang.Throwable -> Laa java.lang.Exception -> Lac
            org.json.JSONArray r4 = r9.getJSONArray(r6)     // Catch: org.json.JSONException -> L45 java.lang.Throwable -> Laa java.lang.Exception -> Lac
            org.json.JSONArray r9 = defpackage.abl.c(r9, r3)     // Catch: org.json.JSONException -> L45 java.lang.Throwable -> Laa java.lang.Exception -> Lac
            java.util.ArrayList r1 = defpackage.aap.a(r4)     // Catch: org.json.JSONException -> L45 java.lang.Throwable -> Laa java.lang.Exception -> Lac
            aaz r9 = defpackage.aap.b(r9)     // Catch: org.json.JSONException -> L45 java.lang.Throwable -> Laa java.lang.Exception -> Lac
            r2 = r9
            goto L5f
        L45:
            r9 = move-exception
            goto L4a
        L47:
            r9 = move-exception
            java.lang.String r10 = ""
        L4a:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            java.lang.String r4 = "downLoadFileComplete batch error : "
            r3.<init>(r4)     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            r3.append(r9)     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            java.lang.String r9 = r3.toString()     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            defpackage.abd.b(r0, r9)     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
        L5f:
            java.lang.StringBuffer r9 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            r9.<init>()     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            r8.i = r9     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            goto L93
        L67:
            java.lang.String r10 = r9.getString(r7)     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            java.util.ArrayList r1 = r9.getStringArrayList(r6)     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            r9.getString(r5)     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            r9.getString(r4)     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            java.io.Serializable r9 = r9.getSerializable(r3)     // Catch: java.lang.ClassCastException -> L7d java.lang.Throwable -> Laa java.lang.Exception -> Lac
            aaz r9 = (defpackage.aaz) r9     // Catch: java.lang.ClassCastException -> L7d java.lang.Throwable -> Laa java.lang.Exception -> Lac
            r2 = r9
            goto L93
        L7d:
            r9 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            java.lang.String r4 = "downLoadFileComplete error:"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            r3.append(r9)     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            java.lang.String r9 = r3.toString()     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            defpackage.abd.b(r0, r9)     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
        L93:
            java.util.HashMap r9 = new java.util.HashMap     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            r9.<init>()     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            if (r2 == 0) goto L9e
            java.util.Map r9 = r2.b()     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
        L9e:
            r8.a(r10, r1, r9)     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lac
            r8.a()
            android.os.HandlerThread r9 = r8.b
            defpackage.abl.fv_(r9)
            goto Lca
        Laa:
            r9 = move-exception
            goto Lcb
        Lac:
            r9 = move-exception
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Laa
            java.lang.String r1 = "downLoadFileComplete error "
            r10.<init>(r1)     // Catch: java.lang.Throwable -> Laa
            java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> Laa
            r10.append(r9)     // Catch: java.lang.Throwable -> Laa
            java.lang.String r9 = r10.toString()     // Catch: java.lang.Throwable -> Laa
            defpackage.abd.b(r0, r9)     // Catch: java.lang.Throwable -> Laa
            r8.a()
            android.os.HandlerThread r9 = r8.b
            defpackage.abl.fv_(r9)
        Lca:
            return
        Lcb:
            r8.a()
            android.os.HandlerThread r10 = r8.b
            defpackage.abl.fv_(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.android.hicloud.sync.logic.n.b(android.os.Bundle, boolean):void");
    }

    protected void a(String str, String str2, List<UnstructData> list, boolean z, boolean z2, String str3) {
        this.f1834a.e(str, str2, list, z, z2, str3);
    }

    protected void a(String str, String str2, List<UnstructData> list, String str3) {
        this.f1834a.a(str, str2, list, str3);
    }

    protected void a() {
        this.f1834a.c();
        abl.fv_(this.b);
    }

    private void a(Bundle bundle, boolean z) {
        UnstructData unstructData;
        try {
            if (z) {
                try {
                    JSONObject jSONObject = new JSONObject(this.i.toString());
                    unstructData = (UnstructData) jSONObject.get("down_file_data");
                    this.i = new StringBuffer();
                    a(unstructData);
                    return;
                } catch (JSONException e2) {
                    abd.b("DownloadSyncBase", "downLoadFileAbort batch error : " + e2.getMessage());
                    return;
                }
            }
            try {
                bundle.getString("syncType");
                bundle.getString("dataType");
                unstructData = (UnstructData) bundle.getParcelable("down_file_data");
            } catch (ClassCastException e3) {
                abd.b("DownloadSyncBase", "downLoadFileAbort error:" + e3.toString());
                unstructData = null;
            }
            a(unstructData);
            return;
        } catch (Exception e4) {
            abd.b("DownloadSyncBase", "downLoadFileAbort error " + e4.getMessage());
        }
        abd.b("DownloadSyncBase", "downLoadFileAbort error " + e4.getMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(Bundle bundle) {
        boolean z = bundle.getBoolean("is_send_over", false);
        abd.c("DownloadSyncBase", "download sendOverFlag: " + z);
        if (z) {
            return true;
        }
        try {
            byte[] byteArray = bundle.getByteArray("batches_data_bytes_key");
            if (byteArray != null) {
                this.i.append(new String(byteArray, "UTF-8"));
            }
            return false;
        } catch (UnsupportedEncodingException e2) {
            abd.d("DownloadSyncBase", "download isAllBatchesReceiveOver: " + e2.toString());
            this.i = new StringBuffer();
            return true;
        }
    }

    private void b(Bundle bundle) {
        String str;
        String str2 = "";
        try {
            try {
                str = bundle.getString("errorMsg");
                try {
                    bundle.getString("syncType");
                    bundle.getString("dataType");
                    str2 = bundle.getString("session_id");
                } catch (ClassCastException e2) {
                    e = e2;
                    abd.b("DownloadSyncBase", "downLoadFileError error:" + e.toString());
                    a(str2, str);
                }
            } catch (Exception e3) {
                abd.b("DownloadSyncBase", "downLoadFileError " + e3.getMessage());
                return;
            } finally {
                a();
                abl.fv_(this.b);
            }
        } catch (ClassCastException e4) {
            e = e4;
            str = "";
        }
        a(str2, str);
    }

    boolean a(String str, List<String> list, Map<Integer, List<String>> map) {
        try {
            abd.c("DownloadSyncBase", "Call app appDownloadFilesComplete start: dataType = " + this.f + ", sessionId = " + str + ", successList = " + list.toString() + ", failErrorCodeMap = " + map.toString());
            this.d.onDownloadFilesComplete(this.e, this.f, list, map, str);
            StringBuilder sb = new StringBuilder("Call app appDownloadFilesComplete end: dataType = ");
            sb.append(this.f);
            abd.c("DownloadSyncBase", sb.toString());
            return true;
        } catch (aag e2) {
            abd.d("DownloadSyncBase", "Call app aplicationException: code = " + e2.b() + ", msg = " + e2.getMessage());
            return false;
        }
    }

    boolean a(FileDownloadProgress fileDownloadProgress) {
        try {
            StringBuilder sb = new StringBuilder("Call app appOnDownloadFileProgress start: dataType = ");
            sb.append(this.f);
            sb.append(fileDownloadProgress == null ? Constants.NULL : fileDownloadProgress.toString());
            abd.c("DownloadSyncBase", sb.toString());
            this.d.onDownloadFileProgress(this.e, this.f, fileDownloadProgress);
            abd.c("DownloadSyncBase", "Call app appOnDownloadFileProgress end: dataType = " + this.f);
            return true;
        } catch (aag e2) {
            abd.d("DownloadSyncBase", "Call app aplicationException: code = " + e2.b() + ", msg = " + e2.getMessage());
            return false;
        }
    }

    boolean a(UnstructData unstructData, int i) {
        try {
            StringBuilder sb = new StringBuilder("Call app appOnDownloadFileFail start: dataType = ");
            sb.append(this.f);
            sb.append(", errorCode = ");
            sb.append(i);
            sb.append(", unstructData = ");
            sb.append(unstructData == null ? Constants.NULL : unstructData.toString());
            abd.c("DownloadSyncBase", sb.toString());
            this.d.onDownloadFileFail(this.e, this.f, unstructData, i);
            abd.c("DownloadSyncBase", "Call app appOnDownloadFileFail end: dataType = " + this.f);
            return true;
        } catch (aag e2) {
            abd.d("DownloadSyncBase", "Call app aplicationException: code = " + e2.b() + ", msg = " + e2.getMessage());
            return false;
        }
    }

    boolean b(UnstructData unstructData) {
        try {
            StringBuilder sb = new StringBuilder("Call app appOnDownloadFileSuccess start: dataType = ");
            sb.append(this.f);
            sb.append(", unstructData = ");
            sb.append(unstructData == null ? Constants.NULL : unstructData.toString());
            abd.c("DownloadSyncBase", sb.toString());
            this.d.onDownloadFileSuccess(this.e, this.f, unstructData);
            abd.c("DownloadSyncBase", "Call app appOnDownloadFileSuccess end: dataType = " + this.f);
            return true;
        } catch (aag e2) {
            abd.d("DownloadSyncBase", "Call app aplicationException: code = " + e2.b() + ", msg = " + e2.getMessage());
            return false;
        }
    }

    boolean a(UnstructData unstructData) {
        try {
            StringBuilder sb = new StringBuilder("Call app appOnDownloadFileAbort start: dataType = ");
            sb.append(this.f);
            sb.append(", unstructData = ");
            sb.append(unstructData == null ? Constants.NULL : unstructData.toString());
            abd.c("DownloadSyncBase", sb.toString());
            this.d.onDownloadFileAbort(this.e, this.f, unstructData);
            abd.c("DownloadSyncBase", "Call app appOnDownloadFileAbort end: dataType = " + this.f);
            return true;
        } catch (aag e2) {
            abd.d("DownloadSyncBase", "Call app aplicationException: code = " + e2.b() + ", msg = " + e2.getMessage());
            return false;
        }
    }

    boolean a(String str, String str2) {
        try {
            abd.c("DownloadSyncBase", "Call app appOnDownloadFileError start: dataType = " + this.f + ", errorMsg = " + str2);
            this.d.onDownloadFilesError(this.e, this.f, str2, str);
            StringBuilder sb = new StringBuilder("Call app appOnDownloadFileError end: dataType = ");
            sb.append(this.f);
            abd.c("DownloadSyncBase", sb.toString());
            return true;
        } catch (aag e2) {
            abd.d("DownloadSyncBase", "Call app processAplicationException: code = " + e2.b() + ", msg = " + e2.getMessage());
            return false;
        }
    }
}
