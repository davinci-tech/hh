package com.huawei.operation.js;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.ble.BleOperator;
import defpackage.cei;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class BleJsInteraction extends JsModuleBase {
    private static final String RELEASE_TAG = "R_BleJsInteraction";
    private static final String TAG = "BleJsInteraction";
    private boolean isIndicate = false;
    protected BleOperator mBleOperator;
    public String mProductId;

    public void setBleOperator(BleOperator bleOperator) {
        if (bleOperator != null) {
            this.mBleOperator = bleOperator;
            this.mProductId = bleOperator.getDeviceId();
        }
    }

    @JavascriptInterface
    @Deprecated
    public String openBluetoothAdapter() {
        ReleaseLogUtil.e(RELEASE_TAG, "js call openBluetoothAdapter start");
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator == null) {
            LogUtil.h("BleJsInteraction", "js call openBluetoothAdapter, mBleOperator == null, result = ", BleConstants.ERRCODE_NULL);
            return BleConstants.ERRCODE_NULL;
        }
        String openBluetoothAdapter = bleOperator.openBluetoothAdapter();
        ReleaseLogUtil.e(RELEASE_TAG, "js call openBluetoothAdapter end, result + ", openBluetoothAdapter);
        return openBluetoothAdapter;
    }

    @JavascriptInterface
    public void openBluetoothAdapter(String str) {
        ReleaseLogUtil.e(RELEASE_TAG, "js call openBluetoothAdapter start");
        this.mBleOperator.setCallBackName(this.mProductId, str, BleConstants.ON_OPEN_BLE_ADAPTER);
        this.mBleOperator.openBluetoothAdapterWithCallBack();
    }

    @JavascriptInterface
    public String closeBluetoothAdapter() {
        ReleaseLogUtil.e(RELEASE_TAG, "js call closeBluetoothAdapter start");
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator == null) {
            LogUtil.h("BleJsInteraction", "js call closeBluetoothAdapter, mBleOperator == null, result = ", BleConstants.ERRCODE_NULL);
            return BleConstants.ERRCODE_NULL;
        }
        String closeBluetoothAdapter = bleOperator.closeBluetoothAdapter();
        ReleaseLogUtil.e(RELEASE_TAG, "js call closeBluetoothAdapter end, result + ", closeBluetoothAdapter);
        return closeBluetoothAdapter;
    }

    @JavascriptInterface
    public void getBluetoothAdapterState(String str) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call getBluetoothAdapterState start, function", CommonUtil.l(str));
        } else {
            LogUtil.a("BleJsInteraction", "js call getBluetoothAdapterState start, function", str);
        }
        try {
            String productInfo = ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).getProductInfo(this.mProductId);
            if (!TextUtils.isEmpty(productInfo) && "yes".equals(new JSONObject(productInfo).getString("ota"))) {
                LogUtil.a("BleJsInteraction", "getBluetoothAdapterState device supports ota");
                cei.b().releaseResource();
            }
        } catch (JSONException unused) {
            LogUtil.h("BleJsInteraction", "getBluetoothAdapterState exception");
        }
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(this.mProductId, str, BleConstants.GET_BLUETOOTH_ADAPTIVE_STATE_CALLBACK_NAME);
        }
        BleOperator bleOperator2 = this.mBleOperator;
        if (bleOperator2 != null) {
            bleOperator2.getBluetoothAdapterState();
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call getBluetoothAdapterState end");
    }

    @JavascriptInterface
    public void onBluetoothAdapterStateChange(String str) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call onBluetoothAdapterStateChange start, function", CommonUtil.l(str));
        } else {
            LogUtil.a("BleJsInteraction", "js call onBluetoothAdapterStateChange start, function", str);
        }
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(this.mProductId, str, BleConstants.ON_BLUETOOTH_ADAPTER_STATE_CHANGE_CALLBACK_NAME);
            this.mBleOperator.onBluetoothAdapterStateChange();
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call onBluetoothAdapterStateChange end");
    }

    @JavascriptInterface
    public String getDeviceId() {
        ReleaseLogUtil.e(RELEASE_TAG, "js call getDeviceId start");
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator == null) {
            LogUtil.a("BleJsInteraction", "getDeviceId mBleOperator is null");
            return BleConstants.ERRCODE_NULL;
        }
        this.mProductId = bleOperator.getDeviceId();
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call getDeviceId end, mProductId = ", CommonUtil.l(this.mProductId));
        } else {
            LogUtil.a("BleJsInteraction", "js call getDeviceId end, mProductId = ", this.mProductId);
        }
        return this.mProductId;
    }

    @JavascriptInterface
    public void getDeviceInfo(String str) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call getDeviceInfo start, function", CommonUtil.l(str));
        } else {
            LogUtil.a("BleJsInteraction", "js call getDeviceInfo start, function", str);
        }
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.getDeviceInfo(str);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call getDeviceInfo end");
    }

    @JavascriptInterface
    public void onBLEConnectionStateChange(String str) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call onBleConnectionStateChange start, function", CommonUtil.l(str));
        } else {
            LogUtil.a("BleJsInteraction", "js call onBleConnectionStateChange start, function", str);
        }
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(this.mProductId, str, BleConstants.ON_BLE_CONNECTION_STATE_CHANGE_CALLBACK_NAME);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call onBleConnectionStateChange end");
    }

    @JavascriptInterface
    public String startBluetoothDevicesDiscovery(UUID[] uuidArr, boolean z, int i) {
        ReleaseLogUtil.e(RELEASE_TAG, "js call startBluetoothDevicesDiscovery start");
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator == null) {
            LogUtil.b("BleJsInteraction", "js call startBluetoothDevicesDiscovery is null");
            return BleConstants.ERRCODE_NULL;
        }
        String startBluetoothDevicesDiscovery = bleOperator.startBluetoothDevicesDiscovery(uuidArr, z, i);
        ReleaseLogUtil.e(RELEASE_TAG, "js call startBluetoothDevicesDiscovery end, result = ", startBluetoothDevicesDiscovery);
        return startBluetoothDevicesDiscovery;
    }

    @JavascriptInterface
    public String stopBluetoothDevicesDiscovery() {
        ReleaseLogUtil.e(RELEASE_TAG, "js call stopBluetoothDevicesDiscovery start");
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator == null) {
            LogUtil.b("BleJsInteraction", "js call stopBluetoothDevicesDiscovery is null");
            return BleConstants.ERRCODE_NULL;
        }
        String stopBluetoothDevicesDiscovery = bleOperator.stopBluetoothDevicesDiscovery();
        ReleaseLogUtil.e(RELEASE_TAG, "js call stopBluetoothDevicesDiscovery end, result = ", stopBluetoothDevicesDiscovery);
        return stopBluetoothDevicesDiscovery;
    }

    @JavascriptInterface
    public void onBluetoothDeviceFound(String str) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call onBluetoothDeviceFound start, function", CommonUtil.l(str));
        } else {
            LogUtil.a("BleJsInteraction", "js call onBluetoothDeviceFound start, function", str);
        }
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(this.mProductId, str, BleConstants.ON_BLE_DEVICE_FOUND);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call onBluetoothDeviceFound end");
    }

    @JavascriptInterface
    public void onBLEServicesDiscovered(String str) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call onBleServicesDiscovered start, function", CommonUtil.l(str));
        } else {
            LogUtil.a("BleJsInteraction", "js call onBleServicesDiscovered start, function", str);
        }
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(this.mProductId, str, BleConstants.ON_BLE_SERVICES_DISCOVERED_CALLBACK_NAME);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call onBleServicesDiscovered end");
    }

    @JavascriptInterface
    public void onBLECharacteristicValueChange(String str) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call onBleCharacteristicValueChange start, function", CommonUtil.l(str));
        } else {
            LogUtil.a("BleJsInteraction", "js call onBleCharacteristicValueChange start, function", str);
        }
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(this.mProductId, str, BleConstants.ON_BLE_CHARACTERISTIC_VALUE_CHANGE_CALLBACK_NAME);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call onBleCharacteristicValueChange end");
    }

    @JavascriptInterface
    public String createBLEConnection(String str) {
        BleOperator bleOperator;
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call createBLEConnection start, deviceId", CommonUtil.l(str));
        } else {
            LogUtil.a("BleJsInteraction", "js call createBLEConnection start, deviceId", str);
        }
        if (str == null || (bleOperator = this.mBleOperator) == null) {
            LogUtil.b("BleJsInteraction", "js call createBLEConnection param is null, result = ", BleConstants.ERRCODE_NULL);
            return BleConstants.ERRCODE_NULL;
        }
        String createBleConnection = bleOperator.createBleConnection(str);
        ReleaseLogUtil.e(RELEASE_TAG, "js call createBLEConnection end, result + ", createBleConnection);
        return createBleConnection;
    }

    @JavascriptInterface
    public String closeBLEConnection(String str) {
        BleOperator bleOperator;
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call closeBLEConnection start, deviceId", CommonUtil.l(str));
        } else {
            LogUtil.a("BleJsInteraction", "js call closeBLEConnection start, deviceId", str);
        }
        if (str == null || (bleOperator = this.mBleOperator) == null) {
            LogUtil.h("BleJsInteraction", "js call closeBLEConnection is null, result = ", BleConstants.ERRCODE_NULL);
            return BleConstants.ERRCODE_NULL;
        }
        String closeBleConnection = bleOperator.closeBleConnection(str);
        ReleaseLogUtil.e(RELEASE_TAG, "js call closeBLEConnection end, result + ", closeBleConnection);
        return closeBleConnection;
    }

    @JavascriptInterface
    public void setEnableIndication(boolean z) {
        this.isIndicate = z;
        ReleaseLogUtil.e(RELEASE_TAG, "setEnableIndication", Boolean.valueOf(z));
    }

    @JavascriptInterface
    public String notifyBLECharacteristicValueChange(String str, String str2, String str3, String str4) {
        boolean indicationBleCharacteristicValueChange;
        ReleaseLogUtil.e(RELEASE_TAG, "js call notifyBLECharacteristicValueChange start, state = ", str4);
        if (str != null && str2 != null && str3 != null && str4 != null) {
            boolean equals = "true".equals(str4);
            BleOperator bleOperator = this.mBleOperator;
            if (bleOperator != null) {
                if (!this.isIndicate) {
                    indicationBleCharacteristicValueChange = bleOperator.notifyBleCharacteristicValueChange(str, str2, str3, equals);
                } else {
                    indicationBleCharacteristicValueChange = bleOperator.indicationBleCharacteristicValueChange(str, str2, str3, equals);
                }
                ReleaseLogUtil.e(RELEASE_TAG, "js call notifyBLECharacteristicValueChange end, result + ", Boolean.valueOf(indicationBleCharacteristicValueChange));
                if (indicationBleCharacteristicValueChange) {
                    return "0";
                }
            } else {
                LogUtil.b("BleJsInteraction", "js call notifyBLECharacteristicValueChange fail");
            }
        }
        return BleConstants.ERRCODE_COMMON_ERR;
    }

    @JavascriptInterface
    public void readBLECharacteristicValue(String str, String str2, String str3, String str4) {
        ReleaseLogUtil.e(RELEASE_TAG, "js call readBLECharacteristicValue start");
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(str, str4, BleConstants.ON_BLE_CHARACTERISTIC_READ_CALLBACK_NAME);
        }
        BleOperator bleOperator2 = this.mBleOperator;
        if (bleOperator2 != null) {
            bleOperator2.readBleCharacteristicValue(str2, str3);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call readBLECharacteristicValue end");
    }

    @JavascriptInterface
    public void writeBLECharacteristicValue(String str, String str2, String str3, String str4, String str5) {
        if (!TextUtils.isEmpty(str4)) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call writeBLECharacteristicValue start, data length = ", Integer.valueOf(str4.length()));
        } else {
            ReleaseLogUtil.e(RELEASE_TAG, "js call writeBLECharacteristicValue start, data is empty");
        }
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(str, str5, BleConstants.ON_BLE_CHARACTERISTIC_WRITE_CALLBACK_NAME);
        }
        BleOperator bleOperator2 = this.mBleOperator;
        if (bleOperator2 != null) {
            bleOperator2.writeBleCharacteristicValue(str, str2, str3, str4);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call writeBLECharacteristicValue end");
    }

    @JavascriptInterface
    public String requestConnectionPriority(int i) {
        ReleaseLogUtil.e(RELEASE_TAG, "js call requestConnectionPriority start, connectionPriority", Integer.valueOf(i));
        BleOperator bleOperator = this.mBleOperator;
        String requestConnectionPriority = bleOperator != null ? bleOperator.requestConnectionPriority(i) : BleConstants.ERRCODE_NULL;
        ReleaseLogUtil.e(RELEASE_TAG, "js call requestConnectionPriority end, result = ", requestConnectionPriority);
        return requestConnectionPriority;
    }

    @JavascriptInterface
    public void registerRequestMtu(String str) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call registerRequestMtu start, function", CommonUtil.l(str));
        } else {
            LogUtil.a("BleJsInteraction", "js call registerRequestMtu start, function", str);
        }
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(this.mProductId, str, BleConstants.REGISTER_REQUEST_MTU_CALLBACK_NAME);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call registerRequestMtu end");
    }

    @JavascriptInterface
    public String requestMtu(int i) {
        ReleaseLogUtil.e(RELEASE_TAG, "js call requestMtu start, mtu", Integer.valueOf(i));
        BleOperator bleOperator = this.mBleOperator;
        String requestMtu = bleOperator != null ? bleOperator.requestMtu(i) : BleConstants.ERRCODE_NULL;
        ReleaseLogUtil.e(RELEASE_TAG, "js call requestMtu end, result = ", requestMtu);
        return requestMtu;
    }

    @JavascriptInterface
    public void onBleConnectionStateChange(String str) {
        onBLEConnectionStateChange(str);
    }

    @JavascriptInterface
    public void onBleServicesDiscovered(String str) {
        onBLEServicesDiscovered(str);
    }

    @JavascriptInterface
    public void onBleCharacteristicValueChange(String str) {
        onBLECharacteristicValueChange(str);
    }

    @JavascriptInterface
    public String createBleConnection(String str) {
        return createBLEConnection(str);
    }

    @JavascriptInterface
    public String closeBleConnection(String str) {
        return closeBLEConnection(str);
    }

    @JavascriptInterface
    public String notifyBleCharacteristicValueChange(String str, String str2, String str3, String str4) {
        return notifyBLECharacteristicValueChange(str, str2, str3, str4);
    }

    @JavascriptInterface
    public void readBleCharacteristicValue(String str, String str2, String str3, String str4) {
        readBLECharacteristicValue(str, str2, str3, str4);
    }

    @JavascriptInterface
    public void writeBleCharacteristicValue(String str, String str2, String str3, String str4, String str5) {
        writeBLECharacteristicValue(str, str2, str3, str4, str5);
    }

    @JavascriptInterface
    public void saveHealthData(String str, String str2) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call saveHealthData start, function", CommonUtil.l(str2));
        } else {
            LogUtil.a("BleJsInteraction", "js call saveHealthData start, function", str2);
        }
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(this.mProductId, str2, BleConstants.ON_SAVE_MEASURE_RESULT_NAME);
        }
        BleOperator bleOperator2 = this.mBleOperator;
        if (bleOperator2 != null) {
            bleOperator2.save(str);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call saveHealthData end");
    }

    @JavascriptInterface
    public void saveMultipleHealthData(String str, String str2) {
        ReleaseLogUtil.e(RELEASE_TAG, "js call saveMultipleHealthData start");
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(this.mProductId, str2, BleConstants.ON_SAVE_MULTIPLE_MEASURE_RESULT_NAME);
            this.mBleOperator.saveMultipleData(str, str2);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call saveMultipleHealthData end");
    }

    @JavascriptInterface
    public void saveMeasure(String str, String str2) {
        saveHealthData(str, str2);
    }

    @JavascriptInterface
    public void getHealthData(String str, String str2) {
        ReleaseLogUtil.e(RELEASE_TAG, "js call getData start");
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(this.mProductId, str2, BleConstants.ON_GET_DATA_RESULT_NAME);
        }
        BleOperator bleOperator2 = this.mBleOperator;
        if (bleOperator2 != null) {
            bleOperator2.execQuery(str, str2);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call getData end");
    }

    @JavascriptInterface
    public void hasHealthData(String str, String str2) {
        ReleaseLogUtil.e(RELEASE_TAG, "js call hasHealthData start");
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(this.mProductId, str2, BleConstants.ON_HAS_HEALTH_DATA_RESULT_NAME);
            this.mBleOperator.hasHealthDataQuery(str, str2);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call hasHealthData end");
    }

    @JavascriptInterface
    public void getUserInfo(String str) {
        ReleaseLogUtil.e(RELEASE_TAG, "js call getUserInfo start");
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(this.mProductId, str, BleConstants.ON_GET_USER_INFO_RESULT_NAME);
        }
        BleOperator bleOperator2 = this.mBleOperator;
        if (bleOperator2 != null) {
            bleOperator2.getUserInfo(str);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call getUserInfo end");
    }

    @JavascriptInterface
    public void deleteMultipleHealthData(String str, String str2) {
        ReleaseLogUtil.e(RELEASE_TAG, "js call deleteMultipleHealthData start");
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(this.mProductId, str2, BleConstants.ON_DELETE_MEASURE_RESULT_NAME);
        }
        BleOperator bleOperator2 = this.mBleOperator;
        if (bleOperator2 != null) {
            bleOperator2.deleteMultipleHealthData(str, str2);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call deleteMultipleHealthData end");
    }

    @JavascriptInterface
    public void startScanCode(String str) {
        ReleaseLogUtil.e(RELEASE_TAG, "js call startScanCode start");
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(this.mProductId, str, BleConstants.ON_SCAN_CODE_RESULT_NAME);
        }
        BleOperator bleOperator2 = this.mBleOperator;
        if (bleOperator2 != null) {
            bleOperator2.startScanCode(str);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call startScanCode end");
    }

    @JavascriptInterface
    public void syncCloud(String str, String str2) {
        ReleaseLogUtil.e(RELEASE_TAG, "js call synCloud start");
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(this.mProductId, str2, BleConstants.ON_SYNC_CLOUD_RESULT_NAME);
        }
        BleOperator bleOperator2 = this.mBleOperator;
        if (bleOperator2 != null) {
            bleOperator2.syncCloud(str, str2);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call synCloud end");
    }

    @JavascriptInterface
    public void bindDevice(String str, String str2) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call bindDevice start, function = ", CommonUtil.l(str2));
        } else {
            LogUtil.a("BleJsInteraction", "js call bindDevice start, function = ", str2);
        }
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(this.mProductId, str2, BleConstants.ON_BIND_DEVICE_RESULT_NAME);
        }
        BleOperator bleOperator2 = this.mBleOperator;
        if (bleOperator2 != null) {
            bleOperator2.bindDevice(str);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call bindDevice end");
    }

    @JavascriptInterface
    public void getSn(String str, String str2) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call getSn start, function = ", CommonUtil.l(str2));
        } else {
            LogUtil.a("BleJsInteraction", "js call getSn start, function = ", str2);
        }
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(this.mProductId, str2, BleConstants.GET_SN);
            this.mBleOperator.getSn(str);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call getSn end");
    }

    @JavascriptInterface
    public void getAppVersionCode(String str) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call getAppVersionCode start, function = ", CommonUtil.l(str));
        } else {
            LogUtil.a("BleJsInteraction", "js call getAppVersionCode start, function = ", str);
        }
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setCallBackName(this.mProductId, str, BleConstants.GET_APP_VERSION);
            this.mBleOperator.getAppVersion();
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call getAppVersionCode end");
    }

    @JavascriptInterface
    public boolean isDarkMode() {
        ReleaseLogUtil.e(RELEASE_TAG, "js call isDarkMode start");
        BleOperator bleOperator = this.mBleOperator;
        boolean isDarkMode = bleOperator != null ? bleOperator.isDarkMode() : false;
        ReleaseLogUtil.e(RELEASE_TAG, "js call isDarkMode end, isDarkMode = ", Boolean.valueOf(isDarkMode));
        return isDarkMode;
    }

    @JavascriptInterface
    public int getDarkMode() {
        return isDarkMode() ? 1 : 2;
    }

    @JavascriptInterface
    public void toast(String str, String str2) {
        ReleaseLogUtil.e(RELEASE_TAG, "js call toast start");
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.toast(str2);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call toast end");
    }

    @JavascriptInterface
    public String getLocale() {
        ReleaseLogUtil.e(RELEASE_TAG, "js getLocale start");
        String valueOf = String.valueOf(1);
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            valueOf = bleOperator.getLocale();
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js getLocale end, locale = ", valueOf);
        return valueOf;
    }

    @JavascriptInterface
    public void closeWeb() {
        ReleaseLogUtil.e(RELEASE_TAG, "js closeWeb start");
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.closeWeb();
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js closeWeb end");
    }

    @JavascriptInterface
    public void setTitle(String str) {
        ReleaseLogUtil.e(RELEASE_TAG, "js setTitle start");
        BleOperator bleOperator = this.mBleOperator;
        if (bleOperator != null) {
            bleOperator.setTitle(str);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js setTitle end");
    }
}
