package com.huawei.operation.h5pro.ble;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.google.gson.Gson;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.device.api.DeviceManagerApi;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker;
import com.huawei.hiai.awareness.client.AwarenessRequest;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.data.type.HiSyncType;
import com.huawei.hihealthservice.hihealthkit.util.AppStatusHelper;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.beans.BleDeviceBean;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.ble.BleJsDataApi;
import com.huawei.operation.ble.BleOperator;
import com.huawei.operation.h5pro.dataproceessor.HealthDataProcessor;
import com.huawei.operation.h5pro.jsmodules.healthengine.service.HealthEngineService;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.operation.share.ResultCallback;
import com.huawei.operation.utils.BleJsBiOperate;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.operation.utils.UriConstants;
import com.huawei.operation.utils.Utils;
import com.huawei.pluginbase.PluginBaseAdapter;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.up.utils.Constants;
import defpackage.ezd;
import defpackage.idr;
import defpackage.iov;
import defpackage.koq;
import defpackage.nrt;
import health.compact.a.GRSManager;
import health.compact.a.HEXUtils;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class BleOperatorCompactImpl implements BleOperator {
    private static final int BINARY_COMPLEMENT = 255;
    private static final int DATA_BYTE_LENGTH = 2;
    private static final int DEFAULT_VALUE_BLUETOOTH = 0;
    private static final String RELEASE_TAG = "R_PluginOperation_BleOperatorCompactImpl";
    private static final int SN_CODE_LENGTH = 11;
    private static final int SYNC_ERROR_TYPE = -1;
    private static final String TAG = "PluginOperation_BleOperatorCompactImpl";
    private PluginOperationAdapter mAdapter;
    private String mAttachSn;
    private ArrayList<BleDeviceBean> mBleDeviceBeans;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothGatt mBluetoothGatt;
    private BluetoothGattCallback mBluetoothGattCallback;
    private BroadcastReceiver mBroadcastReceiver;
    private HashMap<String, String> mCallbackMap;
    private Context mContext;
    private long mCurrentTimeMillis;
    private HealthDataProcessor mDataProcessor;
    private BluetoothDevice mDevice;
    private ContentValues mDeviceInfo;
    private BluetoothGattDescriptor mGattDescriptor;
    private BluetoothGattService mGattService;
    private H5ProInstance mH5proInstance;
    private int mInterval;
    private boolean mIsAllowDuplicatesKey;
    private boolean mIsScanning;
    private H5ProJsCbkInvoker<Object> mJsInvoker;
    private BluetoothGattCharacteristic mNotifyCharacteristic;
    private String mProductId;
    private BluetoothGattCharacteristic mReadCharacteristic;
    private CustomTextAlertDialog mUnbindDialog;
    private String mUniqueId;
    private BluetoothGattCharacteristic mWriteCharacteristic;
    private boolean mIsConnected = false;
    private boolean mIsDiscoveredSrv = false;
    private PermissionsResultAction mQrCodeAction = null;
    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() { // from class: com.huawei.operation.h5pro.ble.BleOperatorCompactImpl.1
        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            if (bluetoothDevice == null || BleOperatorCompactImpl.this.mBleDeviceBeans == null) {
                LogUtil.h(BleOperatorCompactImpl.TAG, "mLeScanCallback device or mBleDeviceBeans is null");
                return;
            }
            BleDeviceBean bleDeviceBean = new BleDeviceBean();
            try {
                bleDeviceBean.setDeviceId(bluetoothDevice.getAddress());
                bleDeviceBean.setRssi(i);
                bleDeviceBean.setAdvertisData(BleOperatorCompactImpl.bytesToHexString(bArr).toUpperCase(Locale.ROOT));
                bleDeviceBean.setLocalName(bluetoothDevice.getName());
                if (bluetoothDevice.getUuids() != null) {
                    bleDeviceBean.setAdvertisServiceUUIDs(Arrays.asList(bluetoothDevice.getUuids()));
                }
                if (BleOperatorCompactImpl.this.mIsAllowDuplicatesKey) {
                    if (!BleOperatorCompactImpl.this.hasServiceUuid(bluetoothDevice)) {
                        BleOperatorCompactImpl.this.mBleDeviceBeans.add(bleDeviceBean);
                    } else {
                        LogUtil.a(BleOperatorCompactImpl.TAG, "mBleDeviceBeans has this device");
                        return;
                    }
                } else {
                    BleOperatorCompactImpl.this.mBleDeviceBeans.add(bleDeviceBean);
                }
                if (System.currentTimeMillis() - BleOperatorCompactImpl.this.mCurrentTimeMillis >= BleOperatorCompactImpl.this.mInterval) {
                    BleOperatorCompactImpl.this.mCurrentTimeMillis = System.currentTimeMillis();
                    BleOperatorCompactImpl bleOperatorCompactImpl = BleOperatorCompactImpl.this;
                    bleOperatorCompactImpl.foundBluetoothDevicesHandleTask(bleOperatorCompactImpl.mBleDeviceBeans);
                }
            } catch (SecurityException e) {
                LogUtil.b(BleOperatorCompactImpl.TAG, "onLeScan SecurityException:", ExceptionUtils.d(e));
            }
        }
    };

    public BleOperatorCompactImpl(Context context, H5ProInstance h5ProInstance, ContentValues contentValues, String str) {
        this.mCallbackMap = null;
        LogUtil.a(TAG, "startBleState");
        this.mContext = context;
        this.mH5proInstance = h5ProInstance;
        this.mJsInvoker = h5ProInstance.getJsCbkInvoker();
        this.mDeviceInfo = contentValues;
        this.mProductId = str;
        if (contentValues != null) {
            this.mUniqueId = contentValues.getAsString("uniqueId");
            this.mAttachSn = contentValues.getAsString(BleConstants.ATTACH_SN);
        }
        this.mCallbackMap = new HashMap<>(16);
        this.mBleDeviceBeans = new ArrayList<>();
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        PluginBaseAdapter adapter = PluginOperation.getInstance(context).getAdapter();
        if (adapter instanceof PluginOperationAdapter) {
            this.mAdapter = (PluginOperationAdapter) adapter;
        }
        if (this.mAdapter == null) {
            this.mAdapter = Utils.initPluginOperationAdapter();
        }
        this.mDataProcessor = new HealthDataProcessor(this.mContext, h5ProInstance, this.mDeviceInfo, this.mProductId, this.mUniqueId);
        initQrCodeAction();
    }

    @Override // com.huawei.operation.ble.BleOperator
    public String getDeviceId() {
        LogUtil.a(TAG, "getDeviceId：", this.mProductId);
        return this.mProductId;
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void setCallBackName(String str, String str2, String str3) {
        LogUtil.a(TAG, "setCallBackName, function = ", str2);
        if (this.mCallbackMap == null) {
            this.mCallbackMap = new HashMap<>(16);
        }
        if (str != null && str.equals(this.mProductId)) {
            this.mCallbackMap.put(str3, str2);
        } else {
            LogUtil.h(TAG, "setCallBackName deviceId is null or deviceId is not equals mDeviceId");
        }
    }

    @Override // com.huawei.operation.ble.BleOperator
    public String startBluetoothDevicesDiscovery(UUID[] uuidArr, boolean z, int i) {
        LogUtil.a(TAG, "startBluetoothDevicesDiscovery");
        if (this.mBluetoothAdapter == null) {
            return BleConstants.ERRCODE_COMMON_ERR;
        }
        if (!BleConstants.haveScanPermission()) {
            ReleaseLogUtil.c(RELEASE_TAG, "startBluetoothDevicesDiscovery need permission!");
            return BleConstants.ERRCODE_COMMON_ERR;
        }
        this.mInterval = i;
        this.mIsAllowDuplicatesKey = z;
        LogUtil.a(TAG, "startBluetoothDevicesDiscovery mIsScanning is ", Boolean.valueOf(this.mIsScanning));
        try {
            if (!this.mIsScanning) {
                this.mIsScanning = true;
                if (!this.mBluetoothAdapter.startLeScan(uuidArr, this.mLeScanCallback)) {
                    return BleConstants.ERRCODE_COMMON_ERR;
                }
            }
            return "0";
        } catch (SecurityException e) {
            LogUtil.b(TAG, "startBluetoothDevicesDiscovery SecurityException:", ExceptionUtils.d(e));
            return BleConstants.ERRCODE_COMMON_ERR;
        }
    }

    @Override // com.huawei.operation.ble.BleOperator
    public String stopBluetoothDevicesDiscovery() {
        LogUtil.a(TAG, "stopBluetoothDevicesDiscovery");
        if (this.mBluetoothAdapter == null) {
            return BleConstants.ERRCODE_COMMON_ERR;
        }
        if (BleConstants.haveScanPermission()) {
            LogUtil.a(TAG, "stopBluetoothDevicesDiscovery mIsScanning is ", Boolean.valueOf(this.mIsScanning));
            if (!this.mIsScanning) {
                return "0";
            }
            this.mIsScanning = false;
            this.mBluetoothAdapter.stopLeScan(this.mLeScanCallback);
            return "0";
        }
        ReleaseLogUtil.c(RELEASE_TAG, "stopBluetoothDevicesDiscovery need permission!");
        return BleConstants.ERRCODE_COMMON_ERR;
    }

    @Override // com.huawei.operation.ble.BleOperator
    public boolean notifyBleCharacteristicValueChange(String str, String str2, String str3, boolean z) {
        LogUtil.a(TAG, "notifyBleCharacteristicValueChange");
        if (this.mProductId.equals(str)) {
            if (this.mIsConnected && this.mIsDiscoveredSrv) {
                return setClientCharacteristicConfigDescriptor(str2, str3, z);
            }
            LogUtil.b(TAG, "notifyBleCharacteristicValueChange BleState is not correct");
        } else {
            LogUtil.b(TAG, "notifyBleCharacteristicValueChange deviceId is not correct");
        }
        return false;
    }

    @Override // com.huawei.operation.ble.BleOperator
    public boolean indicationBleCharacteristicValueChange(String str, String str2, String str3, boolean z) {
        LogUtil.a(TAG, "indicationBleCharacteristicValueChange");
        if (this.mProductId.equals(str)) {
            if (this.mIsConnected && this.mIsDiscoveredSrv) {
                return setClientCharacteristicConfigDescriptorIndication(str2, str3, z);
            }
            LogUtil.b(TAG, "indicationBleCharacteristicValueChange BleState is not correct");
        } else {
            LogUtil.b(TAG, "indicationBleCharacteristicValueChange deviceId is not correct");
        }
        return false;
    }

    @Override // com.huawei.operation.ble.BleOperator
    public String requestConnectionPriority(int i) {
        LogUtil.a(TAG, "requestConnectionPriority:", Integer.valueOf(i));
        try {
            if (i < 0 || i > 2) {
                LogUtil.a(TAG, "requestConnectionPriority fail:", Integer.valueOf(i));
                return BleConstants.ERRCODE_COMMON_ERR;
            }
            if (this.mBluetoothAdapter != null && this.mBluetoothGatt != null) {
                if (BleConstants.haveScanPermission()) {
                    return this.mBluetoothGatt.requestConnectionPriority(i) ? "0" : BleConstants.ERRCODE_COMMON_ERR;
                }
                ReleaseLogUtil.d(RELEASE_TAG, "requestConnectionPriority need permission!");
                return BleConstants.ERRCODE_COMMON_ERR;
            }
            LogUtil.h(TAG, "mBluetoothAdapter or mBluetoothGatt is null");
            return BleConstants.ERRCODE_COMMON_ERR;
        } catch (SecurityException e) {
            LogUtil.b(TAG, "requestConnectionPriority SecurityException:", ExceptionUtils.d(e));
            return BleConstants.ERRCODE_COMMON_ERR;
        }
    }

    @Override // com.huawei.operation.ble.BleOperator
    public String requestMtu(int i) {
        LogUtil.a(TAG, "requestMtu:", Integer.valueOf(i));
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null) {
            LogUtil.h(TAG, "mBluetoothGatt is null");
            return BleConstants.ERRCODE_COMMON_ERR;
        }
        try {
            return bluetoothGatt.requestMtu(i) ? "0" : BleConstants.ERRCODE_COMMON_ERR;
        } catch (SecurityException e) {
            LogUtil.b(TAG, "requestMtu SecurityException:", ExceptionUtils.d(e));
            return BleConstants.ERRCODE_COMMON_ERR;
        }
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void readBleCharacteristicValue(String str, String str2) {
        LogUtil.a(TAG, "readBleCharacteristicValue");
        if (this.mIsDiscoveredSrv) {
            initService(str);
            readCharacteristic(str2);
        }
    }

    @Override // com.huawei.operation.ble.BleOperator
    public String writeBleCharacteristicValue(String str, String str2, String str3, String str4) {
        LogUtil.a(TAG, "writeBleCharacteristicValue");
        if (!this.mProductId.equals(str)) {
            return BleConstants.ERRCODE_PRD_NOT_FOUND;
        }
        if (!this.mIsDiscoveredSrv) {
            return BleConstants.ERRCODE_COMMON_ERR;
        }
        initService(str2);
        writeCharacteristic(str3, str4);
        return "0";
    }

    @Override // com.huawei.operation.ble.BleOperator
    public String createBleConnection(String str) {
        LogUtil.a(TAG, "createBleConnection");
        return connect(str);
    }

    private String connect(String str) {
        String str2;
        LogUtil.a(TAG, "connect");
        if (BluetoothAdapter.checkBluetoothAddress(str)) {
            return connectByMacAddress(str);
        }
        if (this.mAdapter == null) {
            str2 = null;
        } else if (TextUtils.isEmpty(this.mUniqueId)) {
            str2 = this.mAdapter.getBondedDeviceAddress(str);
        } else {
            str2 = this.mUniqueId;
        }
        return str2 == null ? BleConstants.ERRCODE_PRD_NOT_FOUND : connectByMacAddress(str2);
    }

    private String connectByMacAddress(String str) {
        try {
            if (this.mBluetoothAdapter == null) {
                return "0";
            }
            if (!BleConstants.haveScanPermission()) {
                ReleaseLogUtil.c(RELEASE_TAG, "connectByMacAddress need permission!");
                return BleConstants.ERRCODE_COMMON_ERR;
            }
            this.mDevice = this.mBluetoothAdapter.getRemoteDevice(str);
            if (this.mBluetoothGattCallback == null) {
                this.mBluetoothGattCallback = new BleGattCallback();
            }
            BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
            if (bluetoothGatt != null) {
                bluetoothGatt.close();
            }
            this.mBluetoothGatt = this.mDevice.connectGatt(this.mContext, false, this.mBluetoothGattCallback);
            return "0";
        } catch (IllegalArgumentException | SecurityException e) {
            LogUtil.b(TAG, "connectByMacAddress exception:", ExceptionUtils.d(e));
            return BleConstants.ERRCODE_BLE_CONNECT_ERROR;
        }
    }

    private boolean initService(String str) {
        LogUtil.a(TAG, "initService");
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt != null) {
            BluetoothGattService service = bluetoothGatt.getService(UUID.fromString(str));
            this.mGattService = service;
            if (service != null) {
                return true;
            }
            LogUtil.b(TAG, "getService fail");
        } else {
            LogUtil.b(TAG, "BluetoothGatt is null");
        }
        return false;
    }

    private void writeCharacteristic(String str, String str2) {
        LogUtil.a(TAG, "writeCharacteristic");
        try {
            BluetoothGattService bluetoothGattService = this.mGattService;
            if (bluetoothGattService != null) {
                this.mWriteCharacteristic = bluetoothGattService.getCharacteristic(UUID.fromString(str));
            }
            if (this.mWriteCharacteristic != null) {
                byte[] c = HEXUtils.c(str2);
                if (c != null) {
                    this.mWriteCharacteristic.setValue(c);
                }
                this.mBluetoothGatt.writeCharacteristic(this.mWriteCharacteristic);
            }
        } catch (SecurityException e) {
            LogUtil.b(TAG, "writeCharacteristic SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void readCharacteristic(String str) {
        LogUtil.a(TAG, "readCharacteristic");
        try {
            BluetoothGattService bluetoothGattService = this.mGattService;
            if (bluetoothGattService != null) {
                this.mReadCharacteristic = bluetoothGattService.getCharacteristic(UUID.fromString(str));
            }
            BluetoothGattCharacteristic bluetoothGattCharacteristic = this.mReadCharacteristic;
            if (bluetoothGattCharacteristic != null) {
                this.mBluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
            }
        } catch (SecurityException e) {
            LogUtil.b(TAG, "readCharacteristic SecurityException:", ExceptionUtils.d(e));
        }
    }

    @Override // com.huawei.operation.ble.BleOperator
    public String closeBleConnection(String str) {
        LogUtil.a(TAG, "closeBleConnection");
        disconnect(str);
        return "0";
    }

    private void disconnect(String str) {
        LogUtil.a(TAG, AwarenessRequest.MessageType.DISCONNECT);
        if (this.mProductId.equals(str)) {
            try {
                BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
                if (bluetoothGatt != null) {
                    bluetoothGatt.disconnect();
                    this.mBluetoothGatt.close();
                }
                HashMap<String, String> hashMap = this.mCallbackMap;
                if (hashMap != null) {
                    hashMap.clear();
                }
                this.mIsScanning = false;
                ArrayList<BleDeviceBean> arrayList = this.mBleDeviceBeans;
                if (arrayList != null) {
                    arrayList.clear();
                }
            } catch (SecurityException e) {
                LogUtil.b(TAG, "disconnect SecurityException:", ExceptionUtils.d(e));
            }
        }
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a(TAG, "onActivityResult  requestCode = ", Integer.valueOf(i), "resultCode = ", Integer.valueOf(i));
        handleOpenBluetoothAdapter(i, i2);
    }

    @Override // com.huawei.operation.ble.BleOperator
    public String openBluetoothAdapter() {
        LogUtil.a(TAG, BleConstants.ON_OPEN_BLE_ADAPTER);
        return openBlu();
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void openBluetoothAdapterWithCallBack() {
        HashMap<String, String> hashMap;
        if (this.mJsInvoker == null || (hashMap = this.mCallbackMap) == null) {
            LogUtil.h(TAG, "openBluetoothAdapter mJsInvoker or mCallbackMap is null");
            return;
        }
        String str = hashMap.get(BleConstants.ON_OPEN_BLE_ADAPTER);
        if (!BleConstants.haveScanPermission()) {
            ReleaseLogUtil.c(RELEASE_TAG, "openBluetoothAdapter need permission!");
            this.mJsInvoker.invokeJsFunc(str, BleConstants.ERRCODE_COMMON_ERR);
        }
        try {
            BaseApplication.getActivity().startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 4);
        } catch (ActivityNotFoundException unused) {
            LogUtil.a(TAG, "openBluetoothAdapter ActivityNotFoundException");
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.mJsInvoker.invokeJsFunc(str, BleConstants.ERRCODE_COMMON_ERR);
        }
    }

    @Override // com.huawei.operation.ble.BleOperator
    public String closeBluetoothAdapter() {
        LogUtil.a(TAG, "closeBluetoothAdapter");
        return closeBlu();
    }

    private String openBlu() {
        String str;
        BluetoothAdapter bluetoothAdapter;
        LogUtil.a(TAG, "openBlu");
        try {
            bluetoothAdapter = this.mBluetoothAdapter;
        } catch (SecurityException e) {
            LogUtil.b(TAG, "openBlu SecurityException:", ExceptionUtils.d(e));
        }
        if (bluetoothAdapter != null) {
            if (bluetoothAdapter.isEnabled()) {
                str = "0";
                LogUtil.a(TAG, "openBlu result:", str);
                return str;
            }
        }
        str = BleConstants.ERRCODE_COMMON_ERR;
        LogUtil.a(TAG, "openBlu result:", str);
        return str;
    }

    private String closeBlu() {
        String str;
        BluetoothAdapter bluetoothAdapter;
        LogUtil.a(TAG, "closeBlu");
        try {
            bluetoothAdapter = this.mBluetoothAdapter;
        } catch (SecurityException e) {
            LogUtil.b(TAG, "closeBlu SecurityException:", ExceptionUtils.d(e));
        }
        if (bluetoothAdapter != null) {
            if (!bluetoothAdapter.isEnabled()) {
                str = "0";
                LogUtil.a(TAG, "closeBlu result:", str);
                return str;
            }
        }
        str = BleConstants.ERRCODE_COMMON_ERR;
        LogUtil.a(TAG, "closeBlu result:", str);
        return str;
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void getBluetoothAdapterState() {
        LogUtil.a(TAG, BleConstants.GET_BLUETOOTH_ADAPTIVE_STATE_CALLBACK_NAME);
        getBluState();
    }

    private void getBluState() {
        HashMap<String, String> hashMap;
        LogUtil.a(TAG, "getBluState");
        if (this.mJsInvoker == null || (hashMap = this.mCallbackMap) == null) {
            LogUtil.h(TAG, "getBluState mJsInvoker or mCallbackMap is null");
            return;
        }
        String str = hashMap.get(BleConstants.GET_BLUETOOTH_ADAPTIVE_STATE_CALLBACK_NAME);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "getBluState callbackFunc is null");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            if (!BleConstants.haveScanPermission()) {
                ReleaseLogUtil.c(RELEASE_TAG, "getBluState need permission!");
                jSONObject.put("errCode", BleConstants.ERRCODE_COMMON_ERR);
                this.mJsInvoker.invokeJsFunc(str, jSONObject.toString());
                return;
            }
            BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
            if (bluetoothAdapter != null) {
                boolean z = bluetoothAdapter.getState() == 12;
                jSONObject.put(BleConstants.KEY_DISCOVERING, this.mBluetoothAdapter.isDiscovering());
                jSONObject.put(BleConstants.KEY_AVAILABLE, z);
                jSONObject.put("errCode", "0");
                String jSONObject2 = jSONObject.toString();
                LogUtil.a(TAG, "getBluState jsonResult: ", jSONObject2);
                this.mJsInvoker.invokeJsFunc(str, jSONObject2);
            }
        } catch (SecurityException | JSONException e) {
            LogUtil.b(TAG, "getBluState Exception:", ExceptionUtils.d(e));
        }
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void onBluetoothAdapterStateChange() {
        LogUtil.a(TAG, BleConstants.ON_BLUETOOTH_ADAPTER_STATE_CHANGE_CALLBACK_NAME);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        if (this.mBroadcastReceiver == null) {
            this.mBroadcastReceiver = new BluetoothStateChangeBroadcastReceiver();
        }
        Context context = this.mContext;
        if (context != null) {
            context.registerReceiver(this.mBroadcastReceiver, intentFilter);
        }
    }

    /* loaded from: classes9.dex */
    class BluetoothStateChangeBroadcastReceiver extends BroadcastReceiver {
        private BluetoothStateChangeBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a(BleOperatorCompactImpl.TAG, "onReceive: " + intent.getAction());
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0)) {
                    case 10:
                        LogUtil.a(BleOperatorCompactImpl.TAG, "STATE_OFF");
                        BleOperatorCompactImpl.this.handleBluetoothAdapterStateChange(false);
                        break;
                    case 11:
                        LogUtil.a(BleOperatorCompactImpl.TAG, "STATE_TURNING_ON");
                        BleOperatorCompactImpl.this.handleBluetoothAdapterStateChange(false);
                        break;
                    case 12:
                        LogUtil.a(BleOperatorCompactImpl.TAG, "STATE_ON");
                        BleOperatorCompactImpl.this.handleBluetoothAdapterStateChange(true);
                        break;
                    case 13:
                        LogUtil.a(BleOperatorCompactImpl.TAG, "STATE_TURNING_OFF");
                        BleOperatorCompactImpl.this.handleBluetoothAdapterStateChange(false);
                        break;
                }
            }
        }
    }

    private void handleOpenBluetoothAdapter(int i, int i2) {
        LogUtil.a(TAG, "handleOpenBluetoothAdapter enter");
        if (this.mJsInvoker != null && this.mCallbackMap != null) {
            LogUtil.a(TAG, "requestCode = ", Integer.valueOf(i), "resultCode = ", Integer.valueOf(i));
            String str = this.mCallbackMap.get(BleConstants.ON_OPEN_BLE_ADAPTER);
            if (i == 4) {
                String str2 = i2 == -1 ? "0" : BleConstants.ERRCODE_COMMON_ERR;
                this.mJsInvoker.invokeJsFunc(str, str2);
                LogUtil.a(TAG, "handleOpenBluetoothAdapter end result = ", str2, "function = ", str);
                return;
            }
            return;
        }
        LogUtil.h(TAG, "handleBluetoothAdapterStateChange mJsInvoker or mCallbackMap is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleBluetoothAdapterStateChange(boolean z) {
        HashMap<String, String> hashMap;
        LogUtil.a(TAG, "handleBluetoothAdapterStateChange");
        if (this.mJsInvoker == null || (hashMap = this.mCallbackMap) == null) {
            LogUtil.h(TAG, "handleBluetoothAdapterStateChange mJsInvoker or mCallbackMap is null");
            return;
        }
        String str = hashMap.get(BleConstants.ON_BLUETOOTH_ADAPTER_STATE_CHANGE_CALLBACK_NAME);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "handleBluetoothAdapterStateChange callbackFunc is null");
            return;
        }
        if (!BleConstants.haveScanPermission()) {
            ReleaseLogUtil.d(RELEASE_TAG, "handleBluetoothAdapterStateChange need permission!");
            return;
        }
        try {
            BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
            if (bluetoothAdapter != null) {
                boolean isDiscovering = bluetoothAdapter.isDiscovering();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put(BleConstants.KEY_DISCOVERING, isDiscovering);
                    jSONObject.put(BleConstants.KEY_AVAILABLE, z);
                } catch (JSONException unused) {
                    LogUtil.b(TAG, "handleBluetoothAdapterStateChange fail JSONException");
                }
                String jSONObject2 = jSONObject.toString();
                LogUtil.a(TAG, "handleBluetoothAdapterStateChange ", jSONObject2);
                this.mJsInvoker.invokeJsFunc(str, jSONObject2);
            }
        } catch (SecurityException e) {
            LogUtil.b(TAG, "handleBluetoothAdapterStateChange SecurityException:", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasServiceUuid(BluetoothDevice bluetoothDevice) {
        Iterator<BleDeviceBean> it = this.mBleDeviceBeans.iterator();
        while (it.hasNext()) {
            if (it.next().getDeviceId().equals(bluetoothDevice.getAddress())) {
                return true;
            }
        }
        return false;
    }

    /* loaded from: classes9.dex */
    class BleGattCallback extends BluetoothGattCallback {
        private BleGattCallback() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            LogUtil.a(BleOperatorCompactImpl.TAG, "onConnectionStateChange, status:", Integer.valueOf(i2));
            if (bluetoothGatt != null) {
                if (i2 == 2) {
                    try {
                        bluetoothGatt.discoverServices();
                        BleOperatorCompactImpl.this.mIsConnected = true;
                    } catch (SecurityException e) {
                        LogUtil.b(BleOperatorCompactImpl.TAG, "onConnectionStateChange SecurityException:", ExceptionUtils.d(e));
                        return;
                    }
                }
                if (i2 == 0) {
                    LogUtil.h(BleOperatorCompactImpl.TAG, "BleOperatorImpl it's disconnected");
                    BleOperatorCompactImpl.this.mIsDiscoveredSrv = false;
                    BleOperatorCompactImpl.this.mIsConnected = false;
                }
                BleOperatorCompactImpl.this.connectionStateHandleTask();
                new BleJsBiOperate().tickBiBleConnect(BleOperatorCompactImpl.this.mDeviceInfo, i2, BleOperatorCompactImpl.this.mProductId);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            LogUtil.a(BleOperatorCompactImpl.TAG, "onServicesDiscovered, status = ", Integer.valueOf(i));
            if (bluetoothGatt == null || i != 0) {
                return;
            }
            BleOperatorCompactImpl.this.mIsDiscoveredSrv = true;
            BleOperatorCompactImpl.this.servicesDiscoveredTask();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            LogUtil.a(BleOperatorCompactImpl.TAG, "onCharacteristicChanged");
            BleOperatorCompactImpl.this.characteristicHandleTask(10004, bluetoothGattCharacteristic, 0);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            LogUtil.a(BleOperatorCompactImpl.TAG, "onCharacteristicWrite, status = ", Integer.valueOf(i));
            BleOperatorCompactImpl.this.characteristicHandleTask(10005, bluetoothGattCharacteristic, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            LogUtil.a(BleOperatorCompactImpl.TAG, "onCharacteristicRead, status = ", Integer.valueOf(i));
            BleOperatorCompactImpl.this.characteristicHandleTask(10006, bluetoothGattCharacteristic, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onMtuChanged(bluetoothGatt, i, i2);
            LogUtil.a(BleOperatorCompactImpl.TAG, "onMtuChanged, mtu = ", Integer.valueOf(i), ", status = ", Integer.valueOf(i2));
            BleOperatorCompactImpl.this.mtuChangedHandleTask(i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectionStateHandleTask() {
        HashMap<String, String> hashMap;
        LogUtil.a(TAG, "connectionStateHandleTask");
        if (this.mJsInvoker == null || (hashMap = this.mCallbackMap) == null) {
            LogUtil.h(TAG, "connectionStateHandleTask mJsInvoker or mCallbackMap is null");
            return;
        }
        String str = hashMap.get(BleConstants.ON_BLE_CONNECTION_STATE_CHANGE_CALLBACK_NAME);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "connectionStateHandleTask callbackFunc is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("deviceId", this.mProductId);
            jSONObject.put("connected", this.mIsConnected);
            jSONObject.put(BleConstants.KEY_CONNECTSTATE, this.mIsConnected);
        } catch (JSONException unused) {
            LogUtil.b(TAG, "connectionStateHandleTask fail JSONException");
        }
        String jSONObject2 = jSONObject.toString();
        LogUtil.a(TAG, "connectionStateHandleTask jsonResult: ", jSONObject2);
        this.mJsInvoker.invokeJsFunc(str, jSONObject2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void servicesDiscoveredTask() {
        HashMap<String, String> hashMap;
        LogUtil.a(TAG, "servicesDiscoveredTask");
        if (this.mJsInvoker == null || (hashMap = this.mCallbackMap) == null) {
            LogUtil.h(TAG, "servicesDiscoveredTask mJsInvoker or mCallbackMap is null");
            return;
        }
        String str = hashMap.get(BleConstants.ON_BLE_SERVICES_DISCOVERED_CALLBACK_NAME);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "servicesDiscoveredTask callbackFunc is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errCode", "0");
        } catch (JSONException unused) {
            LogUtil.b(TAG, "servicesDiscoveredTask fail JSONException");
        }
        String jSONObject2 = jSONObject.toString();
        LogUtil.a(TAG, "servicesDiscoveredTask jsonResult: ", jSONObject2);
        this.mJsInvoker.invokeJsFunc(str, jSONObject2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void characteristicHandleTask(int i, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
        LogUtil.a(TAG, "characteristicHandleTask");
        if (this.mJsInvoker == null || this.mCallbackMap == null) {
            LogUtil.h(TAG, "characteristicHandleTask mJsInvoker or mCallbackMap is null");
            return;
        }
        try {
            execCharacteristicHandleTask(i, bluetoothGattCharacteristic, i2);
        } catch (JSONException unused) {
            LogUtil.b(TAG, "characteristicHandleTask fail JSONException");
        }
    }

    private void execCharacteristicHandleTask(int i, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) throws JSONException {
        String str;
        String str2;
        LogUtil.a(TAG, "execCharacteristicHandleTask");
        JSONObject jSONObject = new JSONObject();
        if (i == 10004) {
            str = this.mCallbackMap.get(BleConstants.ON_BLE_CHARACTERISTIC_VALUE_CHANGE_CALLBACK_NAME);
            String uuid = bluetoothGattCharacteristic.getUuid().toString();
            if (TextUtils.isEmpty(str) || (str2 = this.mProductId) == null || uuid == null) {
                LogUtil.h(TAG, "execCharacteristicHandleTask * is null");
                return;
            }
            jSONObject.put("deviceId", str2);
            jSONObject.put("serviceId", "");
            jSONObject.put(BleConstants.KEY_CHARACTERISTICID, uuid);
            jSONObject.put("data", bytesToHexString(bluetoothGattCharacteristic.getValue()));
        } else {
            String str3 = BleConstants.ERRCODE_COMMON_ERR;
            if (i == 10005) {
                if (i2 == 0) {
                    str3 = "0";
                }
                str = this.mCallbackMap.get(BleConstants.ON_BLE_CHARACTERISTIC_WRITE_CALLBACK_NAME);
                if (TextUtils.isEmpty(str)) {
                    LogUtil.h(TAG, "execCharacteristicHandleTask callbackFunc is null");
                    return;
                }
                jSONObject.put("errCode", str3);
            } else if (i == 10006) {
                if (i2 == 0) {
                    str3 = "0";
                }
                String bytesToHexString = bytesToHexString(bluetoothGattCharacteristic.getValue());
                String str4 = this.mCallbackMap.get(BleConstants.ON_BLE_CHARACTERISTIC_READ_CALLBACK_NAME);
                if (TextUtils.isEmpty(str4)) {
                    LogUtil.h(TAG, "execCharacteristicHandleTask callbackFunc or data is null");
                    return;
                } else {
                    jSONObject.put("data", bytesToHexString);
                    jSONObject.put("errCode", str3);
                    str = str4;
                }
            } else {
                LogUtil.a(TAG, "execCharacteristicHandleTask not VALUE_CHANGE READ WRITE");
                return;
            }
        }
        String jSONObject2 = jSONObject.toString();
        LogUtil.c(TAG, "execCharacteristicHandleTask jsonResult: ", jSONObject2);
        this.mJsInvoker.invokeJsFunc(str, jSONObject2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String bytesToHexString(byte[] bArr) {
        LogUtil.a(TAG, "bytesToHexString");
        StringBuilder sb = new StringBuilder(16);
        if (bArr == null || bArr.length <= 0) {
            return "";
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void foundBluetoothDevicesHandleTask(ArrayList<BleDeviceBean> arrayList) {
        HashMap<String, String> hashMap;
        LogUtil.a(TAG, "foundBluetoothDevicesHandleTask");
        if (this.mJsInvoker == null || (hashMap = this.mCallbackMap) == null) {
            LogUtil.h(TAG, "foundBluetoothDevicesHandleTask mJsInvoker or mCallbackMap is null");
            return;
        }
        String str = hashMap.get(BleConstants.ON_BLE_DEVICE_FOUND);
        if (TextUtils.isEmpty(str) || koq.b(arrayList)) {
            LogUtil.h(TAG, "foundBluetoothDevicesHandleTask callbackFunc or bleDeviceBeans is null");
            return;
        }
        String json = new Gson().toJson(arrayList);
        LogUtil.c(TAG, "foundBluetoothDevicesHandleTask jsonResult: ", json);
        this.mJsInvoker.invokeJsFunc(str, json);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mtuChangedHandleTask(int i, int i2) {
        HashMap<String, String> hashMap;
        LogUtil.a(TAG, "mtuChangedHandleTask");
        if (this.mJsInvoker == null || (hashMap = this.mCallbackMap) == null) {
            LogUtil.h(TAG, "mtuChangedHandleTask mJsInvoker or mCallbackMap is null");
            return;
        }
        String str = hashMap.get(BleConstants.REGISTER_REQUEST_MTU_CALLBACK_NAME);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "mtuChangedHandleTask callbackFunc is null");
            return;
        }
        String str2 = i2 == 0 ? "0" : BleConstants.ERRCODE_COMMON_ERR;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errCode", str2);
            jSONObject.put("data", i);
        } catch (JSONException unused) {
            LogUtil.b(TAG, "mtuChangedHandleTask fail JsonException");
        }
        String jSONObject2 = jSONObject.toString();
        LogUtil.a(TAG, "mtuChangedHandleTask jsonResult: ", jSONObject2);
        this.mJsInvoker.invokeJsFunc(str, jSONObject2);
    }

    private boolean setClientCharacteristicConfigDescriptor(String str, String str2, boolean z) {
        LogUtil.a(TAG, "setClientCharacteristicConfigDescriptor");
        if (initService(str) && initCharacteristic(str2) && initDescriptor(BleConstants.CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID) && setCharNotification(z)) {
            return true;
        }
        LogUtil.b(TAG, "setClientCharacteristicConfigDescriptor fail");
        return false;
    }

    private boolean setClientCharacteristicConfigDescriptorIndication(String str, String str2, boolean z) {
        LogUtil.a(TAG, "setClientCharacteristicConfigDescriptorIndication");
        if (initService(str) && initCharacteristic(str2) && initDescriptor(BleConstants.CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID) && setCharIndication(z)) {
            return true;
        }
        LogUtil.b(TAG, "setClientCharacteristicConfigDescriptorIndication fail");
        return false;
    }

    private boolean initCharacteristic(String str) {
        LogUtil.a(TAG, "initCharacteristic");
        if (this.mGattService != null) {
            LogUtil.a(TAG, "initCharacteristic getCharacteristic");
            BluetoothGattCharacteristic characteristic = this.mGattService.getCharacteristic(UUID.fromString(str));
            this.mNotifyCharacteristic = characteristic;
            if (characteristic != null) {
                return true;
            }
            LogUtil.b(TAG, "getCharacteristic fail");
        } else {
            LogUtil.b(TAG, "GattService is null");
        }
        return false;
    }

    private boolean initDescriptor(String str) {
        LogUtil.a(TAG, "initDescriptor");
        if (this.mNotifyCharacteristic != null) {
            LogUtil.a(TAG, "initDescriptor getDescriptor");
            BluetoothGattDescriptor descriptor = this.mNotifyCharacteristic.getDescriptor(UUID.fromString(str));
            this.mGattDescriptor = descriptor;
            if (descriptor != null) {
                return true;
            }
            LogUtil.b(TAG, "getDescriptor fail");
        } else {
            LogUtil.b(TAG, "GattCharacteristic is null");
        }
        return false;
    }

    private boolean setCharNotification(boolean z) {
        LogUtil.a(TAG, "setCharNotification");
        try {
            byte[] bArr = BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE;
            if (z) {
                LogUtil.a(TAG, "setCharNotification flag is enable");
                bArr = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE;
            }
            if (this.mGattDescriptor != null) {
                if (this.mBluetoothGatt.setCharacteristicNotification(this.mNotifyCharacteristic, z) && this.mGattDescriptor.setValue(bArr) && this.mBluetoothGatt.writeDescriptor(this.mGattDescriptor)) {
                    return true;
                }
                LogUtil.h(TAG, "setCharNotification fail");
                return false;
            }
            LogUtil.h(TAG, "GattDescriptor is null");
            return false;
        } catch (SecurityException e) {
            LogUtil.b(TAG, "setCharNotification SecurityException:", ExceptionUtils.d(e));
            return false;
        }
    }

    private boolean setCharIndication(boolean z) {
        LogUtil.a(TAG, "setCharIndication");
        try {
            byte[] bArr = BluetoothGattDescriptor.ENABLE_INDICATION_VALUE;
            if (z) {
                LogUtil.a(TAG, "setCharIndication flag is enable");
                bArr = BluetoothGattDescriptor.ENABLE_INDICATION_VALUE;
            }
            if (this.mGattDescriptor != null) {
                if (this.mBluetoothGatt.setCharacteristicNotification(this.mNotifyCharacteristic, z) && this.mGattDescriptor.setValue(bArr) && this.mBluetoothGatt.writeDescriptor(this.mGattDescriptor)) {
                    return true;
                }
                LogUtil.h(TAG, "setCharIndication fail");
                return false;
            }
            LogUtil.h(TAG, "GattDescriptor is null");
            return false;
        } catch (SecurityException e) {
            LogUtil.b(TAG, "setCharIndication SecurityException:", ExceptionUtils.d(e));
            return false;
        }
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void execQuery(String str, String str2) {
        LogUtil.a(TAG, "execQuery");
        HealthDataProcessor healthDataProcessor = this.mDataProcessor;
        if (healthDataProcessor == null) {
            LogUtil.a(TAG, "execQuery mDataProcessor is null");
        } else {
            healthDataProcessor.execQuery(str, str2);
        }
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void hasHealthDataQuery(String str, String str2) {
        LogUtil.a(TAG, "hasHealthDataQuery");
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void save(String str) {
        LogUtil.a(TAG, "save");
        if (this.mDataProcessor == null) {
            LogUtil.a(TAG, "save mDataProcessor is null");
            return;
        }
        HashMap<String, String> hashMap = this.mCallbackMap;
        if (hashMap == null) {
            LogUtil.a(TAG, "save mCallbackMap is null");
            return;
        }
        String str2 = hashMap.get(BleConstants.ON_SAVE_MEASURE_RESULT_NAME);
        this.mDataProcessor.setIsRetryFailed(false);
        this.mDataProcessor.saveData(str, str2);
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void saveMultipleData(String str, String str2) {
        LogUtil.a(TAG, "saveMultipleData");
        HealthDataProcessor healthDataProcessor = this.mDataProcessor;
        if (healthDataProcessor == null) {
            LogUtil.a(TAG, "saveMultipleData mDataProcessor is null");
        } else {
            healthDataProcessor.setIsRetryFailed(false);
            this.mDataProcessor.saveMultipleData(str, str2);
        }
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void deleteMultipleHealthData(String str, String str2) {
        LogUtil.a(TAG, BleConstants.ON_DELETE_MEASURE_RESULT_NAME);
        HealthDataProcessor healthDataProcessor = this.mDataProcessor;
        if (healthDataProcessor == null) {
            LogUtil.a(TAG, "deleteMultipleHealthData mDataProcessor is null");
        } else {
            healthDataProcessor.deleteMultipleHealthData(str, str2);
        }
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void getUserInfo(String str) {
        LogUtil.a(TAG, Constants.METHOD_GET_USER_INFO);
        getUserInfoData(str);
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void startScanCode(String str) {
        LogUtil.a(TAG, "startScanCode function = ", str);
        if (this.mQrCodeAction == null) {
            return;
        }
        PermissionUtil.b(this.mContext, PermissionUtil.PermissionType.CAMERA, this.mQrCodeAction);
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void bindDevice(String str) {
        LogUtil.a(TAG, "bindDevice");
        HashMap<String, String> hashMap = this.mCallbackMap;
        if (hashMap == null || this.mAdapter == null) {
            LogUtil.h(TAG, "bindDevice mCallbackMap or mAdapter is null");
            return;
        }
        String str2 = hashMap.get(BleConstants.ON_BIND_DEVICE_RESULT_NAME);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("uuid");
            String string2 = jSONObject.getString(BleConstants.MEASUREKIT_ID);
            String string3 = jSONObject.getString("sn");
            if (TextUtils.isEmpty(string)) {
                LogUtil.h(TAG, "bindDevice productId is empty");
                callBackJsResult(this.mJsInvoker, String.valueOf(2), str2, null);
                return;
            }
            String string4 = jSONObject.getString("deviceName");
            String string5 = jSONObject.getString("uniqueId");
            if (TextUtils.isEmpty(string5) && TextUtils.isEmpty(string3)) {
                LogUtil.h(TAG, "bindDevice uniqueId & serialNumber is empty");
                callBackJsResult(this.mJsInvoker, String.valueOf(2), str2, null);
            } else {
                int bindDevice = this.mAdapter.bindDevice(string, string2, string3, string4, string5);
                LogUtil.a(TAG, "bindDevice resultCode: ", Integer.valueOf(bindDevice));
                callBackJsResult(this.mJsInvoker, String.valueOf(bindDevice), str2, null);
                uploadDataToHota(string5, string3, string);
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "bindDevice parseData fail JSONException");
            callBackJsResult(this.mJsInvoker, String.valueOf(2), str2, null);
        }
    }

    private void uploadDataToHota(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2) || BluetoothAdapter.checkBluetoothAddress(str)) {
            str = str2;
        } else {
            LogUtil.h(TAG, "uploadDataToHota uniqueId = sn");
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "uploadDataToHota sn is null");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("sn", str);
            ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).uploadDataToHota(str3, jSONObject.toString());
        } catch (JSONException unused) {
            LogUtil.b(TAG, "uploadDataToHota JSONException");
        }
    }

    public void unbindDevice(final String str) {
        if (this.mContext == null || this.mAdapter == null) {
            LogUtil.h(TAG, "mContext or mAdapter is null!");
        } else {
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.operation.h5pro.ble.BleOperatorCompactImpl$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    BleOperatorCompactImpl.this.m709xb44210b6(str);
                }
            });
        }
    }

    /* renamed from: lambda$unbindDevice$2$com-huawei-operation-h5pro-ble-BleOperatorCompactImpl, reason: not valid java name */
    /* synthetic */ void m709xb44210b6(final String str) {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.mContext).b(R.string.IDS_hw_health_wear_connect_device_unpair_button).d(R.string.IDS_unbind_device_wear_home).cyU_(R.string.IDS_hw_health_wear_connect_device_unpair_button, new View.OnClickListener() { // from class: com.huawei.operation.h5pro.ble.BleOperatorCompactImpl$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BleOperatorCompactImpl.this.m707x61996634(str, view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.operation.h5pro.ble.BleOperatorCompactImpl$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BleOperatorCompactImpl.this.m708x8aedbb75(view);
            }
        }).a();
        this.mUnbindDialog = a2;
        a2.setCancelable(false);
        this.mUnbindDialog.show();
    }

    /* renamed from: lambda$unbindDevice$0$com-huawei-operation-h5pro-ble-BleOperatorCompactImpl, reason: not valid java name */
    /* synthetic */ void m707x61996634(String str, View view) {
        CustomTextAlertDialog customTextAlertDialog = this.mUnbindDialog;
        if (customTextAlertDialog == null) {
            LogUtil.a(TAG, "unbindDevice setPositiveButton mUnbindDialog = null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        customTextAlertDialog.dismiss();
        this.mUnbindDialog = null;
        CompactAdapterResponseCallback compactAdapterResponseCallback = new CompactAdapterResponseCallback(this.mH5proInstance, str);
        if (this.mAdapter.unbindDevice(this.mContext, this.mProductId, this.mUniqueId)) {
            compactAdapterResponseCallback.d(0, null);
            jumpMainActivityOrFinish();
        } else {
            compactAdapterResponseCallback.d(-1, null);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$unbindDevice$1$com-huawei-operation-h5pro-ble-BleOperatorCompactImpl, reason: not valid java name */
    /* synthetic */ void m708x8aedbb75(View view) {
        CustomTextAlertDialog customTextAlertDialog = this.mUnbindDialog;
        if (customTextAlertDialog == null) {
            LogUtil.a(TAG, "unbindDevice setNegativeButton mUnbindDialog = null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            customTextAlertDialog.dismiss();
            this.mUnbindDialog = null;
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void jumpMainActivityOrFinish() {
        if (HealthDevice.HealthDeviceKind.HDK_FITTINGS.name().equals(this.mDeviceInfo.getAsString("deviceType"))) {
            ObserverManagerUtil.c("H5_UNBIND_DEVICE", 2);
        } else {
            Intent launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(this.mContext.getPackageName());
            if (launchIntentForPackage != null) {
                launchIntentForPackage.setFlags(AppRouterExtras.COLDSTART);
                this.mContext.startActivity(launchIntentForPackage);
            }
        }
        Context context = this.mContext;
        if (context instanceof Activity) {
            ((Activity) context).finish();
        } else {
            LogUtil.a(TAG, " unBind finish context is not Activity ");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0082  */
    @Override // com.huawei.operation.ble.BleOperator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void syncCloud(java.lang.String r9, java.lang.String r10) {
        /*
            r8 = this;
            java.lang.String r0 = "uniqueId"
            java.lang.String r1 = "dataType"
            java.lang.String r2 = "syncCloud"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            java.lang.String r3 = "PluginOperation_BleOperatorCompactImpl"
            com.huawei.hwlogsmodel.LogUtil.a(r3, r2)
            boolean r2 = android.text.TextUtils.isEmpty(r9)
            r4 = 0
            r5 = 2
            if (r2 != 0) goto L97
            java.lang.String r2 = "undefined"
            boolean r2 = r2.equals(r9)
            if (r2 == 0) goto L24
            goto L97
        L24:
            r2 = -1
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch: java.lang.NumberFormatException -> L51 org.json.JSONException -> L5f
            r6.<init>(r9)     // Catch: java.lang.NumberFormatException -> L51 org.json.JSONException -> L5f
            boolean r7 = r6.has(r1)     // Catch: java.lang.NumberFormatException -> L51 org.json.JSONException -> L5f
            if (r7 == 0) goto L3d
            java.lang.String r1 = r6.getString(r1)     // Catch: java.lang.NumberFormatException -> L51 org.json.JSONException -> L5f
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> L51 org.json.JSONException -> L5f
            int r1 = r8.switchDataType(r1)     // Catch: java.lang.NumberFormatException -> L51 org.json.JSONException -> L5f
            goto L3e
        L3d:
            r1 = r2
        L3e:
            if (r1 != r2) goto L6c
            boolean r7 = r6.has(r0)     // Catch: java.lang.NumberFormatException -> L4f org.json.JSONException -> L5f
            if (r7 == 0) goto L6c
            java.lang.String r0 = r6.getString(r0)     // Catch: java.lang.NumberFormatException -> L4f org.json.JSONException -> L5f
            int r1 = r8.getSyncType(r0)     // Catch: java.lang.NumberFormatException -> L4f org.json.JSONException -> L5f
            goto L6c
        L4f:
            r9 = move-exception
            goto L53
        L51:
            r9 = move-exception
            r1 = r2
        L53:
            java.lang.String r9 = r9.getMessage()
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.b(r3, r9)
            goto L6c
        L5f:
            java.lang.String r0 = "Use params as uniqueId."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r3, r0)
            int r1 = r8.getSyncType(r9)
        L6c:
            if (r1 != r2) goto L82
            java.lang.String r9 = "syncCloud params is invalid"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.h(r3, r9)
            com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker<java.lang.Object> r9 = r8.mJsInvoker
            java.lang.String r0 = java.lang.String.valueOf(r5)
            callBackJsResult(r9, r0, r10, r4)
            return
        L82:
            java.lang.String r9 = "EcologyDevice"
            java.lang.Class<com.huawei.health.device.api.EcologyDeviceApi> r0 = com.huawei.health.device.api.EcologyDeviceApi.class
            java.lang.Object r9 = health.compact.a.Services.c(r9, r0)
            com.huawei.health.device.api.EcologyDeviceApi r9 = (com.huawei.health.device.api.EcologyDeviceApi) r9
            com.huawei.operation.h5pro.ble.BleOperatorCompactImpl$CompactAdapterResponseCallback r0 = new com.huawei.operation.h5pro.ble.BleOperatorCompactImpl$CompactAdapterResponseCallback
            com.huawei.health.h5pro.vengine.H5ProInstance r2 = r8.mH5proInstance
            r0.<init>(r2, r10)
            r9.syncCloud(r1, r0)
            return
        L97:
            java.lang.String r9 = "params is empty!"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.a(r3, r9)
            com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker<java.lang.Object> r9 = r8.mJsInvoker
            java.lang.String r0 = java.lang.String.valueOf(r5)
            callBackJsResult(r9, r0, r10, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.h5pro.ble.BleOperatorCompactImpl.syncCloud(java.lang.String, java.lang.String):void");
    }

    private int getSyncType(String str) {
        MeasurableDevice bondedDeviceByUniqueId = ((DeviceManagerApi) Services.c("PluginDevice", DeviceManagerApi.class)).getBondedDeviceByUniqueId(str);
        if (bondedDeviceByUniqueId == null) {
            LogUtil.h(TAG, "deviceInfo is null!");
            return -1;
        }
        return switchDataType(bondedDeviceByUniqueId.getKind());
    }

    /* renamed from: com.huawei.operation.h5pro.ble.BleOperatorCompactImpl$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$huawei$health$device$model$HealthDevice$HealthDeviceKind;

        static {
            int[] iArr = new int[HealthDevice.HealthDeviceKind.values().length];
            $SwitchMap$com$huawei$health$device$model$HealthDevice$HealthDeviceKind = iArr;
            try {
                iArr[HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$huawei$health$device$model$HealthDevice$HealthDeviceKind[HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$huawei$health$device$model$HealthDevice$HealthDeviceKind[HealthDevice.HealthDeviceKind.HDK_BODY_TEMPERATURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$huawei$health$device$model$HealthDevice$HealthDeviceKind[HealthDevice.HealthDeviceKind.HDK_WEIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private int switchDataType(HealthDevice.HealthDeviceKind healthDeviceKind) {
        int i = AnonymousClass3.$SwitchMap$com$huawei$health$device$model$HealthDevice$HealthDeviceKind[healthDeviceKind.ordinal()];
        if (i == 1) {
            return 5;
        }
        if (i == 2) {
            return 4;
        }
        if (i == 3) {
            return 18;
        }
        if (i != 4) {
            return 20000;
        }
        return HiSyncType.HiSyncDataType.c;
    }

    private int switchDataType(int i) {
        if (i == 10001) {
            return 4;
        }
        if (i == 10002) {
            return 5;
        }
        if (i != 10006) {
            return i != 400011975 ? -1 : 18;
        }
        return HiSyncType.HiSyncDataType.c;
    }

    public void getBannerInfo(String str, String str2) {
        callBackJsResult(this.mJsInvoker, String.valueOf(0), str2, str);
    }

    public void requestHeartRateInterval(String str, String str2, String str3) {
        callBackJsResult(this.mJsInvoker, str2, str3, str);
    }

    public void downloadFile(String str, String str2, String str3) {
        callBackJsResult(this.mJsInvoker, str2, str3, str);
    }

    public void getStorageSync(String str, int i, String str2) {
        callBackJsResult(this.mJsInvoker, String.valueOf(i), str2, str);
    }

    public void readFileSync(String str, String str2, String str3) {
        callBackJsResult(this.mJsInvoker, str2, str3, str);
    }

    @Override // com.huawei.operation.ble.BleOperator
    public String getLocale() {
        LogUtil.a(TAG, "getLocale");
        return "";
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void closeWeb() {
        LogUtil.a(TAG, "closeWeb");
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void setTitle(String str) {
        LogUtil.a(TAG, "setTitle");
    }

    /* loaded from: classes9.dex */
    static class CompactAdapterResponseCallback implements IBaseResponseCallback {
        private String mFunction;
        private WeakReference<H5ProInstance> mInstance;

        CompactAdapterResponseCallback(H5ProInstance h5ProInstance, String str) {
            this.mInstance = new WeakReference<>(h5ProInstance);
            this.mFunction = str;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            H5ProInstance h5ProInstance = this.mInstance.get();
            if (h5ProInstance == null) {
                LogUtil.h(BleOperatorCompactImpl.TAG, "mInstance is null");
                return;
            }
            H5ProJsCbkInvoker<Object> jsCbkInvoker = h5ProInstance.getJsCbkInvoker();
            if (i == 0) {
                LogUtil.a(BleOperatorCompactImpl.TAG, "onResponse success");
                BleOperatorCompactImpl.callBackJsResult(jsCbkInvoker, String.valueOf(0), this.mFunction, null);
            } else {
                LogUtil.a(BleOperatorCompactImpl.TAG, "onResponse fail");
                BleOperatorCompactImpl.callBackJsResult(jsCbkInvoker, String.valueOf(1), this.mFunction, null);
            }
        }
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void getSn(String str) {
        String str2;
        String str3;
        LogUtil.a(TAG, BleConstants.GET_SN);
        String url = GRSManager.a(this.mContext).getUrl("domainWwwHuawei");
        if (TextUtils.isEmpty(url)) {
            LogUtil.h(TAG, "JsInteraction mHuaweiHost is empty");
            url = "https:/";
        }
        boolean doAccessTokenCheck = OperationUtils.getInstance().doAccessTokenCheck(this.mContext, str, url + UriConstants.BOTH_BLOOD_GLUCOSE);
        LogUtil.a(TAG, "getSn isLegal = ", Boolean.valueOf(doAccessTokenCheck));
        if (doAccessTokenCheck) {
            str2 = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), "key_sn_sino_blood_sugar");
            str3 = "0";
        } else {
            LogUtil.a(TAG, "doAccessTokenCheck is not legal");
            str2 = "";
            str3 = BleConstants.CODE_AUTHORIZED_FAIL;
        }
        HashMap<String, String> hashMap = this.mCallbackMap;
        if (hashMap == null) {
            LogUtil.h(TAG, "getSn mCallbackMap is null");
        } else {
            callBackJsResult(this.mJsInvoker, str3, hashMap.get(BleConstants.GET_SN), str2);
        }
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void getAppVersion() {
        PackageInfo packageInfo;
        String str = "";
        try {
            PackageManager packageManager = this.mContext.getPackageManager();
            if (packageManager != null && (packageInfo = packageManager.getPackageInfo("com.huawei.health", 0)) != null) {
                str = packageInfo.versionCode + "";
            }
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b(TAG, "getAppVersionName() Exception: ", "PackageManager.NameNotFoundException");
        }
        LogUtil.a(TAG, "getAppVersion version = ", str);
        if (this.mCallbackMap == null) {
            LogUtil.h(TAG, "getAppVersion mCallbackMap is null");
        } else if (!TextUtils.isEmpty(str)) {
            callBackJsResultNoErrCode(this.mCallbackMap.get(BleConstants.GET_APP_VERSION), str);
        } else {
            callBackJsResult(this.mJsInvoker, "11", this.mCallbackMap.get(BleConstants.GET_APP_VERSION), null);
        }
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void getDeviceInfo(String str) {
        LogUtil.a(TAG, "getDeviceInfo function = ", str);
        if (this.mJsInvoker == null) {
            LogUtil.h(TAG, "getDeviceInfo mJsInvoker is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h(TAG, "getDeviceInfo function null");
                jSONObject.put("errCode", 7);
                jSONObject.put(BleConstants.DEV_INFO, "");
                this.mJsInvoker.invokeJsFunc(str, jSONObject.toString());
                return;
            }
            int authPermission = getAuthPermission(101201);
            LogUtil.a(TAG, "getDeviceInfo  getAuthPermission errorCode = ", Integer.valueOf(authPermission));
            if (authPermission != 0) {
                jSONObject.put("errCode", authPermission);
                jSONObject.put(BleConstants.DEV_INFO, "");
                this.mJsInvoker.invokeJsFunc(str, jSONObject.toString());
                return;
            }
            JSONObject jSONObject2 = new JSONObject();
            if (BluetoothAdapter.checkBluetoothAddress(this.mUniqueId)) {
                jSONObject2.put("mac", this.mUniqueId);
            } else {
                jSONObject2.put("sn", this.mUniqueId);
            }
            jSONObject2.put("devType", "");
            String productInfo = ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).getProductInfo(this.mProductId);
            if (!TextUtils.isEmpty(productInfo)) {
                jSONObject2.put("deviceName", new JSONObject(productInfo).getString("deviceName"));
            }
            String prodId = ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).getProdId(this.mProductId);
            if (!TextUtils.isEmpty(prodId)) {
                jSONObject2.put("prodId", prodId);
            }
            jSONObject2.put(BleConstants.KEY_BR_MAC, this.mDeviceInfo.getAsString(BleConstants.KEY_BR_MAC));
            if (!TextUtils.isEmpty(this.mAttachSn)) {
                jSONObject2.put(BleConstants.ATTACH_SN, this.mAttachSn);
            }
            jSONObject.put("errCode", String.valueOf(0));
            jSONObject.put(BleConstants.DEV_INFO, jSONObject2);
            this.mJsInvoker.invokeJsFunc(str, jSONObject.toString());
        } catch (RemoteException | JSONException unused) {
            LogUtil.h(TAG, "getDeviceInfo fail jsonException or RemoteException");
        }
    }

    @Override // com.huawei.operation.ble.BleOperator
    public boolean isDarkMode() {
        return nrt.a(this.mContext);
    }

    @Override // com.huawei.operation.ble.BleOperator
    public void toast(String str) {
        Toast.makeText(this.mContext, str, 0).show();
    }

    private void initQrCodeAction() {
        LogUtil.a(TAG, "initQrCodeAction");
        this.mQrCodeAction = new CustomPermissionAction(this.mContext) { // from class: com.huawei.operation.h5pro.ble.BleOperatorCompactImpl.2
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a(BleOperatorCompactImpl.TAG, "mQrCodeAction onGranted");
                BleOperatorCompactImpl.this.startScan();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a(BleOperatorCompactImpl.TAG, "mQrCodeAction onDenied");
                super.onDenied(str);
                BleOperatorCompactImpl.this.startScan();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                LogUtil.a(BleOperatorCompactImpl.TAG, "mQrCodeAction onForeverDenied");
                BleOperatorCompactImpl.this.startScan();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startScan() {
        Context context = this.mContext;
        if (context instanceof Activity) {
            ezd.aue_((Activity) context, 20002);
        } else {
            LogUtil.a(TAG, "mContext is not Activity");
        }
    }

    public void handleScanCodeResult(int i, Intent intent) {
        String str;
        int i2;
        LogUtil.a(TAG, "handleScanCodeResult resultCode: ", Integer.valueOf(i));
        if (i == -1) {
            str = ezd.auc_(intent);
            if (checkSnCode(str)) {
                LogUtil.a(TAG, "handleScanCodeResult SnCode is match");
                i2 = 0;
            } else {
                i2 = 3;
            }
        } else {
            str = "";
            i2 = 1;
        }
        HashMap<String, String> hashMap = this.mCallbackMap;
        if (hashMap == null) {
            LogUtil.h(TAG, "handleScanCodeResult mCallbackMap is null");
        } else {
            callBackJsResult(this.mJsInvoker, String.valueOf(i2), hashMap.get(BleConstants.ON_SCAN_CODE_RESULT_NAME), str);
        }
    }

    private boolean checkSnCode(String str) {
        LogUtil.a(TAG, "checkSnCode");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "content is empty");
            return false;
        }
        if (str.length() == 11) {
            return true;
        }
        LogUtil.h(TAG, "sn code does not match the length");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void callBackJsResult(H5ProJsCbkInvoker<Object> h5ProJsCbkInvoker, String str, String str2, String str3) {
        LogUtil.a(TAG, "callBackJsResult callbackFunc ", str2);
        if (TextUtils.isEmpty(str2) || h5ProJsCbkInvoker == null) {
            LogUtil.h(TAG, "callbackFunc or mJsInvoker is null");
            return;
        }
        LogUtil.a(TAG, "callBackJsResult errCode: ", str);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errCode", str);
            if (str3 != null) {
                jSONObject.put("data", str3);
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "callBackJsResult fail JSONException");
        }
        String jSONObject2 = jSONObject.toString();
        LogUtil.c(TAG, "callBackJsResult jsonResult: ", jSONObject2);
        h5ProJsCbkInvoker.invokeJsFunc(str2, jSONObject2);
    }

    public void callBackJsResult(String str, String str2, String str3) {
        callBackJsResult(this.mJsInvoker, str, str2, str3);
    }

    private void callBackJsResultNoErrCode(String str, String str2) {
        LogUtil.a(TAG, "callBackJsResultNoErrCode");
        if (TextUtils.isEmpty(str) || this.mJsInvoker == null) {
            LogUtil.h(TAG, "callbackFunc or mJsInvoker is null");
        } else {
            LogUtil.c(TAG, "callBackJsResultNoErrCode data: ", str2);
            this.mJsInvoker.invokeJsFunc(str, str2);
        }
    }

    private void getUserInfoData(String str) {
        LogUtil.a(TAG, "getUserInfoData function = ", str);
        BleJsDataApi.getInstance().getUserInfoImpl(new GetUserInfoCallback(this.mH5proInstance, str));
    }

    /* loaded from: classes9.dex */
    static class GetUserInfoCallback implements ResultCallback {
        private String mFunction;
        private WeakReference<H5ProInstance> mH5ProInstance;

        GetUserInfoCallback(H5ProInstance h5ProInstance, String str) {
            this.mH5ProInstance = new WeakReference<>(h5ProInstance);
            this.mFunction = str;
        }

        @Override // com.huawei.operation.share.ResultCallback
        public void onResult(int i, Object obj) {
            LogUtil.a(BleOperatorCompactImpl.TAG, "getUserInfoData resultCode: ", Integer.valueOf(i));
            H5ProInstance h5ProInstance = this.mH5ProInstance.get();
            if (h5ProInstance == null) {
                LogUtil.h(BleOperatorCompactImpl.TAG, "getUserInfoData instance is null");
                return;
            }
            H5ProJsCbkInvoker<Object> jsCbkInvoker = h5ProInstance.getJsCbkInvoker();
            if (TextUtils.isEmpty(this.mFunction) || jsCbkInvoker == null) {
                LogUtil.h(BleOperatorCompactImpl.TAG, "getUserInfoData function or mJsInvoker is null");
                return;
            }
            if (obj instanceof String) {
                jsCbkInvoker.invokeJsFunc(this.mFunction, obj);
                return;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("resultCode", String.valueOf(i));
                jSONObject.put("data", "");
            } catch (JSONException unused) {
                LogUtil.b(BleOperatorCompactImpl.TAG, "getUserInfoData fail JSONException");
            }
            jsCbkInvoker.invokeJsFunc(this.mFunction, jSONObject.toString());
        }
    }

    public void getBoundDeviceInfo(String str, String str2) {
        if (this.mJsInvoker == null || this.mAdapter == null) {
            LogUtil.h(TAG, "getBoundDeviceInfo mJsInvoker is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
        } catch (RemoteException | JSONException unused) {
            LogUtil.h(TAG, "getBoundDeviceInfo fail JSONException or RemoteException");
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            int authPermission = getAuthPermission(101201);
            if (authPermission != 0) {
                LogUtil.a(TAG, "no permission to getBoundDeviceInfo");
                jSONObject.put("resultCode", authPermission);
                jSONObject.put("data", "");
                this.mJsInvoker.invokeJsFunc(str2, jSONObject.toString());
                return;
            }
            ArrayList<ContentValues> bondedDeviceByProductId = this.mAdapter.getBondedDeviceByProductId(str);
            JSONArray jSONArray = new JSONArray();
            for (ContentValues contentValues : bondedDeviceByProductId) {
                JSONObject jSONObject2 = new JSONObject();
                String asString = contentValues.getAsString("uniqueId");
                String asString2 = contentValues.getAsString("name");
                if (BluetoothAdapter.checkBluetoothAddress(asString)) {
                    jSONObject2.put("mac", asString);
                } else {
                    jSONObject2.put("sn", asString);
                }
                jSONObject2.put(BleConstants.BLUETOOTH_NAME, asString2);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("resultCode", String.valueOf(0));
            jSONObject.put("data", jSONArray);
            LogUtil.a(TAG, "getBoundDeviceInfo mac list size = ", Integer.valueOf(jSONArray.length()));
            this.mJsInvoker.invokeJsFunc(str2, jSONObject.toString());
            return;
        }
        LogUtil.h(TAG, "getBoundDeviceInfo productId or function is null");
        jSONObject.put("resultCode", 7);
        jSONObject.put("data", "");
        this.mJsInvoker.invokeJsFunc(str2, jSONObject.toString());
    }

    private int getAuthPermission(int i) throws RemoteException {
        idr h5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5proInstance);
        if (h5AppContext == null) {
            LogUtil.a(TAG, "mAppContext is null");
            return 2;
        }
        return iov.b(this.mContext, h5AppContext.getOutOfBandData(), new AppStatusHelper(this.mContext), i, 1);
    }

    public void stopBleState() {
        BroadcastReceiver broadcastReceiver;
        LogUtil.a(TAG, "onStop");
        HealthDataProcessor healthDataProcessor = this.mDataProcessor;
        if (healthDataProcessor != null) {
            healthDataProcessor.stopProcessor();
            this.mDataProcessor = null;
        }
        Context context = this.mContext;
        if (context != null && (broadcastReceiver = this.mBroadcastReceiver) != null) {
            context.unregisterReceiver(broadcastReceiver);
            this.mBroadcastReceiver = null;
        }
        this.mContext = null;
        this.mJsInvoker = null;
        this.mProductId = null;
        this.mUniqueId = null;
        this.mAdapter = null;
        ContentValues contentValues = this.mDeviceInfo;
        if (contentValues != null) {
            contentValues.clear();
            this.mDeviceInfo = null;
        }
        ArrayList<BleDeviceBean> arrayList = this.mBleDeviceBeans;
        if (arrayList != null) {
            arrayList.clear();
            this.mBleDeviceBeans = null;
        }
        this.mIsConnected = false;
        this.mIsDiscoveredSrv = false;
        if (this.mIsScanning) {
            stopBluetoothDevicesDiscovery();
            this.mIsScanning = false;
        }
        this.mCallbackMap = null;
        this.mDevice = null;
        try {
            if (this.mBluetoothGatt != null && BleConstants.haveScanPermission()) {
                this.mBluetoothGatt.close();
            }
            this.mBluetoothGatt = null;
            this.mBluetoothGattCallback = null;
            this.mGattService = null;
            this.mNotifyCharacteristic = null;
            this.mWriteCharacteristic = null;
            this.mReadCharacteristic = null;
            this.mGattDescriptor = null;
            this.mBluetoothAdapter = null;
            this.mQrCodeAction = null;
            this.mLeScanCallback = null;
            this.mH5proInstance = null;
        } catch (SecurityException e) {
            LogUtil.b(TAG, "stopBleState SecurityException:", ExceptionUtils.d(e));
        }
    }
}
