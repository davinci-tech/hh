package com.huawei.operation.h5pro.jsmodules.wearengine;

import android.text.TextUtils;
import android.util.SparseArray;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.wearengine.auth.AuthCallback;
import com.huawei.wearengine.auth.Permission;
import com.huawei.wearengine.client.ServiceConnectionListener;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.monitor.MonitorData;
import com.huawei.wearengine.monitor.MonitorItem;
import com.huawei.wearengine.monitor.MonitorListener;
import com.huawei.wearengine.p2p.Receiver;
import com.huawei.wearengine.p2p.SendCallback;
import defpackage.tnu;
import defpackage.toi;
import defpackage.tph;
import defpackage.tpx;
import defpackage.tpy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class WearEngineEntry extends JsBaseModule {
    private static final String ERR_MSG_NO_CONNECTED = "no device is connected";
    private static final String PARAMS_KEY_PEER_FINGER_PRINT = "peerFingerPrint";
    private static final String PARAMS_KEY_PEER_PKG_NAME = "peerPkgName";
    private static final String PARAMS_KEY_UDID = "udid";
    private List<Device> mDeviceList = new ArrayList();
    private final SparseArray<Receiver> mReceiverMap = new SparseArray<>();
    private tph mMonitorClient = null;
    private MonitorListener mMonitorListener = null;
    private toi mWearEngineClient = null;

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        if (this.mReceiverMap.size() == 0) {
            return;
        }
        for (int i = 0; i < this.mReceiverMap.size(); i++) {
            tnu.b(this.mContext).e(this.mReceiverMap.valueAt(i)).addOnSuccessListener(new OnSuccessListenerImpl()).addOnFailureListener(new OnFailureListenerImpl());
        }
        this.mReceiverMap.clear();
        super.onDestroy();
    }

    @JavascriptInterface
    public void requestPermission(long j) {
        LogUtil.i(this.TAG, "requestPermission enter");
        tnu.e(this.mContext).a(new AuthCallback() { // from class: com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry.1
            @Override // com.huawei.wearengine.auth.AuthCallback
            public void onOk(Permission[] permissionArr) {
                LogUtil.d(WearEngineEntry.this.TAG, "requestPermission -> " + Arrays.toString(permissionArr));
            }

            @Override // com.huawei.wearengine.auth.AuthCallback
            public void onCancel() {
                LogUtil.i(WearEngineEntry.this.TAG, "requestPermission onCancel");
            }
        }, Permission.DEVICE_MANAGER).addOnSuccessListener(new OnSuccessListenerImpl(this.mH5ProInstance, j)).addOnFailureListener(new OnFailureListenerImpl(this.mH5ProInstance, j));
    }

    @JavascriptInterface
    public void getBondedDevices(final long j) {
        LogUtil.i(this.TAG, "getBondedDevices enter");
        tnu.c(this.mContext).b().addOnSuccessListener(new OnSuccessListener<List<Device>>() { // from class: com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry.2
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public void onSuccess(List<Device> list) {
                WearEngineEntry.this.mDeviceList = list;
                LogUtil.d(WearEngineEntry.this.TAG, "getBondedDevices: onSuccess" + String.valueOf(WearEngineEntry.this.mDeviceList));
                try {
                    WearEngineEntry wearEngineEntry = WearEngineEntry.this;
                    wearEngineEntry.onSuccessCallback(j, WearEngineUtils.devicesToJsonArray(wearEngineEntry.mDeviceList));
                } catch (JSONException unused) {
                    WearEngineEntry wearEngineEntry2 = WearEngineEntry.this;
                    wearEngineEntry2.onSuccessCallback(j, String.valueOf(wearEngineEntry2.mDeviceList));
                }
            }
        }).addOnFailureListener(new OnFailureListenerImpl(this.mH5ProInstance, j));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003d  */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void getP2pClient(long r5, java.lang.String r7) {
        /*
            r4 = this;
            java.lang.String r0 = "udid"
            java.lang.String r1 = r4.TAG
            java.lang.String r2 = "getP2pClient enter"
            java.lang.String[] r2 = new java.lang.String[]{r2}
            com.huawei.health.h5pro.utils.LogUtil.i(r1, r2)
            java.lang.String r1 = ""
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L25
            r2.<init>(r7)     // Catch: org.json.JSONException -> L25
            java.lang.String r7 = "peerPkgName"
            java.lang.String r7 = r2.optString(r7)     // Catch: org.json.JSONException -> L25
            boolean r3 = r2.has(r0)     // Catch: org.json.JSONException -> L26
            if (r3 == 0) goto L31
            java.lang.String r1 = r2.optString(r0)     // Catch: org.json.JSONException -> L26
            goto L31
        L25:
            r7 = r1
        L26:
            java.lang.String r0 = r4.TAG
            java.lang.String r2 = "getP2pClient: JSONException"
            java.lang.String[] r2 = new java.lang.String[]{r2}
            com.huawei.health.h5pro.utils.LogUtil.e(r0, r2)
        L31:
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            if (r0 == 0) goto L3d
            java.lang.String r7 = "getP2pClient: Invalid parameter"
            r4.onFailureCallback(r5, r7)
            return
        L3d:
            java.util.List<com.huawei.wearengine.device.Device> r0 = r4.mDeviceList
            com.huawei.wearengine.device.Device r0 = com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineUtils.getTargetConnDevice(r0, r1)
            if (r0 == 0) goto L74
            boolean r1 = r0.isConnected()
            if (r1 != 0) goto L4c
            goto L74
        L4c:
            android.content.Context r1 = r4.mContext
            tpx r1 = defpackage.tnu.b(r1)
            r1.c(r7)
            com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry$3 r7 = new com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry$3
            r7.<init>()
            com.huawei.hmf.tasks.Task r7 = r1.d(r0, r7)
            com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry$OnSuccessListenerImpl r0 = new com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry$OnSuccessListenerImpl
            com.huawei.health.h5pro.vengine.H5ProInstance r1 = r4.mH5ProInstance
            r0.<init>(r1, r5)
            com.huawei.hmf.tasks.Task r7 = r7.addOnSuccessListener(r0)
            com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry$OnFailureListenerImpl r0 = new com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry$OnFailureListenerImpl
            com.huawei.health.h5pro.vengine.H5ProInstance r1 = r4.mH5ProInstance
            r0.<init>(r1, r5)
            r7.addOnFailureListener(r0)
            return
        L74:
            java.lang.String r7 = "no device is connected"
            r4.onFailureCallback(r5, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry.getP2pClient(long, java.lang.String):void");
    }

    @JavascriptInterface
    public void sendMessage(long j, String str) {
        String str2;
        String str3;
        JSONObject jSONObject;
        LogUtil.i(this.TAG, "sendMessage enter");
        String str4 = "";
        byte[] bArr = null;
        try {
            jSONObject = new JSONObject(str);
            str2 = jSONObject.optString(PARAMS_KEY_PEER_PKG_NAME);
            try {
                str3 = jSONObject.optString(PARAMS_KEY_PEER_FINGER_PRINT);
            } catch (JSONException unused) {
                str3 = "";
            }
        } catch (JSONException unused2) {
            str2 = "";
            str3 = str2;
        }
        try {
            bArr = WearEngineUtils.hexString2Bytes(jSONObject.optString("messageStr"));
            if (jSONObject.has("udid")) {
                str4 = jSONObject.optString("udid");
            }
        } catch (JSONException unused3) {
            LogUtil.e(this.TAG, "sendMessage: JSONException");
            if (!TextUtils.isEmpty(str2)) {
            }
            onFailureCallback(j, "sendMessage: Invalid parameter");
            return;
        }
        if (!TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || bArr == null) {
            onFailureCallback(j, "sendMessage: Invalid parameter");
            return;
        }
        Device targetConnDevice = WearEngineUtils.getTargetConnDevice(this.mDeviceList, str4);
        if (targetConnDevice == null || !targetConnDevice.isConnected()) {
            onFailureCallback(j, ERR_MSG_NO_CONNECTED);
            return;
        }
        tpy.b bVar = new tpy.b();
        bVar.a(bArr);
        tpy a2 = bVar.a();
        tpx b = tnu.b(this.mContext);
        b.c(str2);
        b.d(str3);
        b.e(targetConnDevice, a2, new SendCallback() { // from class: com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry.4
            @Override // com.huawei.wearengine.p2p.SendCallback
            public void onSendProgress(long j2) {
            }

            @Override // com.huawei.wearengine.p2p.SendCallback
            public void onSendResult(int i) {
                LogUtil.d(WearEngineEntry.this.TAG, "sendMessage: onSendResult ->" + i);
            }
        }).addOnSuccessListener(new OnSuccessListenerImpl(this.mH5ProInstance, j)).addOnFailureListener(new OnFailureListenerImpl(this.mH5ProInstance, j));
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void sendFile(long r7, java.lang.String r9) {
        /*
            r6 = this;
            java.lang.String r0 = "udid"
            java.lang.String r1 = r6.TAG
            java.lang.String r2 = "sendFile enter"
            java.lang.String[] r2 = new java.lang.String[]{r2}
            com.huawei.health.h5pro.utils.LogUtil.i(r1, r2)
            java.lang.String r1 = ""
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L33
            r2.<init>(r9)     // Catch: org.json.JSONException -> L33
            java.lang.String r9 = "peerPkgName"
            java.lang.String r9 = r2.optString(r9)     // Catch: org.json.JSONException -> L33
            java.lang.String r3 = "peerFingerPrint"
            java.lang.String r3 = r2.optString(r3)     // Catch: org.json.JSONException -> L34
            java.lang.String r4 = "filePath"
            java.lang.String r4 = r2.optString(r4)     // Catch: org.json.JSONException -> L31
            boolean r5 = r2.has(r0)     // Catch: org.json.JSONException -> L36
            if (r5 == 0) goto L41
            java.lang.String r1 = r2.optString(r0)     // Catch: org.json.JSONException -> L36
            goto L41
        L31:
            r4 = r1
            goto L36
        L33:
            r9 = r1
        L34:
            r3 = r1
            r4 = r3
        L36:
            java.lang.String r0 = r6.TAG
            java.lang.String r2 = "sendFile: JSONException"
            java.lang.String[] r2 = new java.lang.String[]{r2}
            com.huawei.health.h5pro.utils.LogUtil.e(r0, r2)
        L41:
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            if (r0 != 0) goto Lc8
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto Lc8
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L54
            goto Lc8
        L54:
            java.util.List<com.huawei.wearengine.device.Device> r0 = r6.mDeviceList
            com.huawei.wearengine.device.Device r0 = com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineUtils.getTargetConnDevice(r0, r1)
            if (r0 == 0) goto Lc2
            boolean r1 = r0.isConnected()
            if (r1 != 0) goto L63
            goto Lc2
        L63:
            boolean r1 = com.huawei.health.h5pro.utils.GeneralUtil.isSafePath(r4)
            if (r1 != 0) goto L86
            java.lang.String r9 = r6.TAG
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "sendFile: untrusted -> "
            r0.<init>(r1)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            java.lang.String[] r0 = new java.lang.String[]{r0}
            com.huawei.health.h5pro.utils.LogUtil.w(r9, r0)
            java.lang.String r9 = "untrusted filePath"
            r6.onFailureCallback(r7, r9)
            return
        L86:
            java.io.File r1 = new java.io.File
            r1.<init>(r4)
            tpy$b r2 = new tpy$b
            r2.<init>()
            r2.d(r1)
            tpy r1 = r2.a()
            android.content.Context r2 = r6.mContext
            tpx r2 = defpackage.tnu.b(r2)
            r2.c(r9)
            r2.d(r3)
            com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry$5 r9 = new com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry$5
            r9.<init>()
            com.huawei.hmf.tasks.Task r9 = r2.e(r0, r1, r9)
            com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry$OnSuccessListenerImpl r0 = new com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry$OnSuccessListenerImpl
            com.huawei.health.h5pro.vengine.H5ProInstance r1 = r6.mH5ProInstance
            r0.<init>(r1, r7)
            com.huawei.hmf.tasks.Task r9 = r9.addOnSuccessListener(r0)
            com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry$OnFailureListenerImpl r0 = new com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry$OnFailureListenerImpl
            com.huawei.health.h5pro.vengine.H5ProInstance r1 = r6.mH5ProInstance
            r0.<init>(r1, r7)
            r9.addOnFailureListener(r0)
            return
        Lc2:
            java.lang.String r9 = "no device is connected"
            r6.onFailureCallback(r7, r9)
            return
        Lc8:
            java.lang.String r9 = "sendFile: Invalid parameter"
            r6.onFailureCallback(r7, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry.sendFile(long, java.lang.String):void");
    }

    @JavascriptInterface
    public void monitorDeviceClient(long j, String str) {
        String str2;
        Device targetConnDevice;
        JSONObject jSONObject;
        LogUtil.i(this.TAG, "monitorDeviceClient enter");
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException unused) {
            LogUtil.e(this.TAG, "monitorDeviceClient param parse error");
        }
        if (jSONObject.has("udid")) {
            str2 = jSONObject.optString("udid");
            targetConnDevice = WearEngineUtils.getTargetConnDevice(this.mDeviceList, str2);
            if (targetConnDevice != null || !targetConnDevice.isConnected()) {
                onFailureCallback(j, ERR_MSG_NO_CONNECTED);
            }
            this.mMonitorClient = tnu.a(this.mContext);
            this.mMonitorListener = new MonitorListener() { // from class: com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry.6
                @Override // com.huawei.wearengine.monitor.MonitorListener
                public void onChanged(int i, MonitorItem monitorItem, MonitorData monitorData) {
                    LogUtil.d(WearEngineEntry.this.TAG, "monitorDeviceClient: errorCode -> " + i, "monitorItem -> " + monitorItem, "monitorData -> " + monitorData);
                }
            };
            this.mMonitorClient.b(targetConnDevice, MonitorItem.MONITOR_ITEM_CONNECTION, this.mMonitorListener).addOnSuccessListener(new OnSuccessListenerImpl(this.mH5ProInstance, j)).addOnFailureListener(new OnFailureListenerImpl(this.mH5ProInstance, j));
            return;
        }
        str2 = "";
        targetConnDevice = WearEngineUtils.getTargetConnDevice(this.mDeviceList, str2);
        if (targetConnDevice != null) {
        }
        onFailureCallback(j, ERR_MSG_NO_CONNECTED);
    }

    @JavascriptInterface
    public void unregisterDeviceMonitor(long j) {
        MonitorListener monitorListener;
        LogUtil.i(this.TAG, "unregisterDeviceMonitor enter");
        tph tphVar = this.mMonitorClient;
        if (tphVar == null || (monitorListener = this.mMonitorListener) == null) {
            onFailureCallback(j, "no monitor device");
        } else {
            tphVar.c(monitorListener);
            onSuccessCallback(j);
        }
    }

    @JavascriptInterface
    public void monitorWearEngineClient(long j) {
        LogUtil.i(this.TAG, "monitorWearEngineClient enter");
        toi b = tnu.b(this.mContext, new ServiceConnectionListener() { // from class: com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry.7
            @Override // com.huawei.wearengine.client.ServiceConnectionListener
            public void onServiceConnect() {
                LogUtil.i(WearEngineEntry.this.TAG, "monitorWearEngineClient: connect");
            }

            @Override // com.huawei.wearengine.client.ServiceConnectionListener
            public void onServiceDisconnect() {
                LogUtil.i(WearEngineEntry.this.TAG, "monitorWearEngineClient: disconnect");
            }
        });
        this.mWearEngineClient = b;
        b.a().addOnSuccessListener(new OnSuccessListenerImpl(this.mH5ProInstance, j)).addOnFailureListener(new OnFailureListenerImpl(this.mH5ProInstance, j));
    }

    @JavascriptInterface
    public void unregisterServiceConnectionListener(long j) {
        LogUtil.i(this.TAG, "unregisterServiceConnectionListener enter");
        toi toiVar = this.mWearEngineClient;
        if (toiVar == null) {
            LogUtil.w(this.TAG, "mWearEngineClient is null");
        } else {
            toiVar.d().addOnSuccessListener(new OnSuccessListenerImpl(this.mH5ProInstance, j)).addOnFailureListener(new OnFailureListenerImpl(this.mH5ProInstance, j));
        }
    }

    @JavascriptInterface
    public void releaseWearEngineConnection(long j) {
        LogUtil.i(this.TAG, "releaseWearEngineConnection enter");
        toi toiVar = this.mWearEngineClient;
        if (toiVar == null) {
            LogUtil.w(this.TAG, "mWearEngineClient is null");
        } else {
            toiVar.b().addOnSuccessListener(new OnSuccessListenerImpl(this.mH5ProInstance, j)).addOnFailureListener(new OnFailureListenerImpl(this.mH5ProInstance, j));
        }
    }

    @JavascriptInterface
    public void registerMsgReceiver(final long j, String str) {
        String str2;
        String str3;
        JSONObject jSONObject;
        LogUtil.i(this.TAG, "registerMsgReceiver enter");
        String str4 = "";
        boolean z = false;
        try {
            jSONObject = new JSONObject(str);
            str2 = jSONObject.optString(PARAMS_KEY_PEER_PKG_NAME);
            try {
                str3 = jSONObject.optString(PARAMS_KEY_PEER_FINGER_PRINT);
            } catch (JSONException unused) {
                str3 = "";
            }
        } catch (JSONException unused2) {
            str2 = "";
            str3 = str2;
        }
        try {
            if (jSONObject.has("oneTime") && jSONObject.optBoolean("oneTime")) {
                z = true;
            }
            if (jSONObject.has("udid")) {
                str4 = jSONObject.optString("udid");
            }
        } catch (JSONException unused3) {
            LogUtil.e(this.TAG, "registerMsgReceiver: JSONException");
            if (!TextUtils.isEmpty(str2)) {
            }
            onFailureCallback(j, "registerMsgReceiver: Invalid parameter");
            return;
        }
        if (!TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            onFailureCallback(j, "registerMsgReceiver: Invalid parameter");
            return;
        }
        Device targetConnDevice = WearEngineUtils.getTargetConnDevice(this.mDeviceList, str4);
        if (targetConnDevice == null || !targetConnDevice.isConnected()) {
            onFailureCallback(j, ERR_MSG_NO_CONNECTED);
            return;
        }
        tpx b = tnu.b(this.mContext);
        b.c(str2);
        b.d(str3);
        final P2pMessageReceiver p2pMessageReceiver = new P2pMessageReceiver(this.mH5ProInstance, j, z);
        tnu.b(this.mContext).a(targetConnDevice, p2pMessageReceiver).addOnFailureListener(new OnFailureListenerImpl(this.mH5ProInstance, j)).addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry.8
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public void onSuccess(Void r4) {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("code", 0);
                    jSONObject2.put("data", p2pMessageReceiver.hashCode());
                } catch (JSONException unused4) {
                    LogUtil.e(WearEngineEntry.this.TAG, "registerMsgReceiver_HiWear: JSONException");
                }
                WearEngineEntry.this.mReceiverMap.put(p2pMessageReceiver.hashCode(), p2pMessageReceiver);
                WearEngineEntry.this.onSuccessCallback(j, jSONObject2.toString());
            }
        });
    }

    @JavascriptInterface
    public void unregisterMsgReceiver(final long j, String str) {
        LogUtil.i(this.TAG, "unregisterMsgReceiver enter");
        try {
            final int optInt = new JSONObject(str).optInt("receiverId");
            Receiver receiver = this.mReceiverMap.get(optInt);
            if (receiver == null) {
                onFailureCallback(j, "no receiver matched");
            } else {
                tnu.b(this.mContext).e(receiver).addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry.9
                    @Override // com.huawei.hmf.tasks.OnSuccessListener
                    public void onSuccess(Void r3) {
                        WearEngineEntry.this.mReceiverMap.remove(optInt);
                        WearEngineEntry.this.onSuccessCallback(j);
                    }
                }).addOnFailureListener(new OnFailureListenerImpl(this.mH5ProInstance, j));
            }
        } catch (JSONException e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    static class P2pMessageReceiver implements Receiver {
        private static final String TAG = "H5PRO_WearEngineEntry_Receiver";
        private final boolean isOneTime;
        private final long mCallbackId;
        private final WeakReference<H5ProJsCbkInvoker<Object>> mWrH5ProJsCbkInvoker;

        P2pMessageReceiver(H5ProInstance h5ProInstance, long j, boolean z) {
            this.mWrH5ProJsCbkInvoker = h5ProInstance == null ? null : new WeakReference<>(h5ProInstance.getJsCbkInvoker());
            this.mCallbackId = j;
            this.isOneTime = z;
        }

        @Override // com.huawei.wearengine.p2p.Receiver
        public void onReceiveMessage(tpy tpyVar) {
            if (tpyVar == null) {
                LogUtil.w(TAG, "onReceiveMessage: message is null");
                return;
            }
            H5ProJsCbkInvoker h5ProJsCbkInvoker = (H5ProJsCbkInvoker) GeneralUtil.getReferent(this.mWrH5ProJsCbkInvoker);
            if (h5ProJsCbkInvoker == null) {
                LogUtil.w(TAG, "onReceiveMessage: h5ProJsCbkInvoker is null");
                return;
            }
            if (tpyVar.d() == 1) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("code", 1);
                    jSONObject.put("data", WearEngineUtils.bytes2HexString(tpyVar.a()));
                } catch (JSONException unused) {
                    LogUtil.e(TAG, "onReceiveMessage:JSONException");
                }
                h5ProJsCbkInvoker.onSuccess(jSONObject, this.mCallbackId);
                if (this.isOneTime) {
                    h5ProJsCbkInvoker.onComplete(0, "message complete!", this.mCallbackId);
                    return;
                }
                return;
            }
            if (tpyVar.d() == 2) {
                h5ProJsCbkInvoker.onSuccess(tpyVar, this.mCallbackId);
                return;
            }
            LogUtil.w(TAG, "Unknown type: " + tpyVar.d());
        }
    }

    static class OnSuccessListenerImpl implements OnSuccessListener<Void> {
        private static final String TAG = "H5PRO_WearEngineEntry_onSuccess";
        private long mCallbackId;
        private WeakReference<H5ProJsCbkInvoker<Object>> mWrH5ProJsCbkInvoker;

        OnSuccessListenerImpl(H5ProInstance h5ProInstance, long j) {
            this.mWrH5ProJsCbkInvoker = h5ProInstance == null ? null : new WeakReference<>(h5ProInstance.getJsCbkInvoker());
            this.mCallbackId = j;
        }

        OnSuccessListenerImpl() {
            this.mWrH5ProJsCbkInvoker = null;
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        public void onSuccess(Void r4) {
            H5ProJsCbkInvoker h5ProJsCbkInvoker = (H5ProJsCbkInvoker) GeneralUtil.getReferent(this.mWrH5ProJsCbkInvoker);
            if (h5ProJsCbkInvoker == null) {
                LogUtil.w(TAG, "onSuccess: h5ProJsCbkInvoker is null");
            } else {
                h5ProJsCbkInvoker.onSuccess(0, this.mCallbackId);
            }
        }
    }

    static class OnFailureListenerImpl implements OnFailureListener {
        private static final String TAG = "H5PRO_WearEngineEntry_onFailure";
        private long mCallbackId;
        private WeakReference<H5ProJsCbkInvoker<Object>> mWrH5ProJsCbkInvoker;

        OnFailureListenerImpl(H5ProInstance h5ProInstance, long j) {
            this.mWrH5ProJsCbkInvoker = h5ProInstance == null ? null : new WeakReference<>(h5ProInstance.getJsCbkInvoker());
            this.mCallbackId = j;
        }

        OnFailureListenerImpl() {
            this.mWrH5ProJsCbkInvoker = null;
        }

        @Override // com.huawei.hmf.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            H5ProJsCbkInvoker h5ProJsCbkInvoker = (H5ProJsCbkInvoker) GeneralUtil.getReferent(this.mWrH5ProJsCbkInvoker);
            if (h5ProJsCbkInvoker == null) {
                LogUtil.w(TAG, "onFailure: h5ProJsCbkInvoker is null");
            } else {
                h5ProJsCbkInvoker.onFailure(-1, exc.getMessage(), this.mCallbackId);
            }
        }
    }
}
