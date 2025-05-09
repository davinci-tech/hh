package com.huawei.operation.ble;

import android.os.Build;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class BleConstants {
    public static final float ACCURACY = 0.001f;
    public static final int APP_INFO_DEFAULT_FLAG = 0;
    public static final String APP_PACKAGE = "com.huawei.health";
    public static final String ATTACH_SN = "attachSn";
    public static final String AVERAGE_HEART_RATE = "averageHeartRate";
    public static final String AVERAGE_PACE = "averagePace";
    public static final String AVERAGE_STEP_RATE = "averageStepRate";
    public static final int BLE_BASE = 10000;
    public static final int BLE_CHARACTERISTIC_READ = 10006;
    public static final int BLE_CHARACTERISTIC_VALUE_CHANGE = 10004;
    public static final int BLE_CHARACTERISTIC_WRITE = 10005;
    public static final int BLE_CONNECTION_STATE_CHANGE = 10002;
    public static final String BLE_HI_LINK = "hilink";
    public static final String BLE_JSINTERACTION = "BleJsInteraction";
    public static final int BLE_SERVICES_DISCOVERED = 10003;
    public static final String BLE_THIRD_DEVICE_H5 = "h5";
    public static final String BLOOD_OXYGEN = "SpO2";
    public static final String BLOOD_OXYGEN_TYPE = "bloodOxygenType";
    public static final String BLOOD_PRESSURE = "blood_pressure";
    public static final String BLOOD_PRESSURE_TYPE = "bloodPressureType";
    public static final String BLOOD_SUGAR_TYPE = "bloodSugarType";
    public static final String BLUETOOTH_NAME = "bluetoothName";
    public static final String BODY_THERMOMETER_TYPE = "bodyThermometerType";
    public static final String CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID = "00002902-0000-1000-8000-00805f9b34fb";
    public static final String CODE_AUTHORIZED_FAIL = "1002";
    public static final String DARK_MODE = "DarkMode";
    public static final String DATA = "data";
    public static final String DATA_SOURCE = "dataSource";
    public static final String DATA_TYPE_NAME = "dataTypeName";
    public static final int DEFAULT_LIST_SIZE = 10;
    public static final String DEFAULT_VALUE = "-1";
    public static final int DELETE_DATA_RESULT_MSG = 10015;
    public static final String DEVICE_NAME = "deviceName";
    public static final String DEVICE_TYPE = "deviceType";
    public static final String DEV_INFO = "devInfo";
    public static final String DEV_MAC = "mac";
    public static final String DEV_TYPE = "devType";
    public static final String DOWNLOAD_IN_PROGRESS = "90012";
    public static final String EMPTY_SEQUENCE_DATA = "0";
    public static final String END_TIME = "endTime";
    public static final String ERRCODE_BLE_CONNECT_ERROR = "90004";
    public static final String ERRCODE_BLE_DATA_ERROR = "90008";
    public static final String ERRCODE_COMMON_ERR = "90001";
    public static final String ERRCODE_NULL = "90002";
    public static final String ERRCODE_OK = "0";
    public static final String ERRCODE_PRD_NOT_FOUND = "90003";
    public static final String ERRCODE_TIMEOUT = "90014";
    public static final String EXTEND_TRACK_DATA_MAP = "mExtendTrackDataMap";
    public static final String GET_APP_VERSION = "getAppVersionCode";
    public static final String GET_BLUETOOTH_ADAPTIVE_STATE_CALLBACK_NAME = "getBluetoothAdapterState";
    public static final int GET_BLU_ADAPTER_STATE = 10007;
    public static final int GET_DATA_RESULT_MSG = 10010;
    public static final String GET_HEALTH_DATA = "getHealthData";
    public static final String GET_SN = "getSn";
    public static final int GET_USER_INFO_RESULT_MSG = 10011;
    public static final String HAS_HEALTH_DATA = "hasHealthData";
    public static final String HILINK_DEV_OFFLINE = "11";
    public static final String IS_CONFIRMED = "isConfirmed";
    public static final String IS_CONFIRMED_DB = "mIsConfirmed";
    public static final String KEY_AVAILABLE = "available";
    public static final String KEY_BR_MAC = "brMac";
    public static final String KEY_CHARACTERISTICID = "characteristicId";
    public static final String KEY_CONNECTED = "connected";
    public static final String KEY_CONNECTSTATE = "connectState";
    public static final String KEY_DATA = "data";
    public static final String KEY_DEVICEID = "deviceId";
    public static final String KEY_DISCOVERING = "discovering";
    public static final String KEY_ERRCODE = "errCode";
    public static final String KEY_FUNCTION = "function";
    public static final String KEY_PATH = "path";
    public static final String KEY_SERVICEID = "serviceId";
    public static final String LIMIT = "limit";
    public static final int MATH_ONE = 1;
    public static final int MATH_QUERY_BLOOD_SUGAR = 2;
    public static final int MATH_ZERO = 0;
    public static final int MAXIMUM_DATA_LIMIT = 1000;
    public static final int MAX_BLOOD_OXYGEN = 100;
    public static final double MAX_BLOOD_PRESSURE = 500.0d;
    public static final int MAX_BLOOD_SUGAR = 33;
    public static final double MAX_BMI = 200.0d;
    public static final double MAX_BODY_AGE = 99.0d;
    public static final double MAX_BODY_FAT_RATE = 100.0d;
    public static final double MAX_BODY_SCORE = 100.0d;
    public static final double MAX_BONE_SALT = 5.0d;
    public static final double MAX_DYNAMIC_HEARTRATE = 200.0d;
    public static final double MAX_IMPEDANCE = 100000.0d;
    public static final double MAX_MOISTURE = 500.0d;
    public static final double MAX_MOISTURE_RATE = 100.0d;
    public static final double MAX_MUSCLE_MASS = 150.0d;
    public static final double MAX_PROTEIN_RATE = 100.0d;
    public static final double MAX_PROTEIN_VALUE = 500.0d;
    public static final double MAX_SKELETAL_MUSCLE_MASS = 150.0d;
    public static final int MAX_TEMPERATURE = 42;
    public static final double MAX_VISCERAL_FAT_LEVEL = 59.0d;
    public static final double MAX_WEIGHT = 500.0d;
    public static final String MEASUREKIT_ID = "measureKitId";
    public static final int MILLISECOND_PER_MINUTE = 60000;
    public static final double MIN_BASAL_METABOLISM = 0.0d;
    public static final int MIN_BLOOD_OXYGEN = 0;
    public static final double MIN_BLOOD_PRESSURE = 10.0d;
    public static final int MIN_BLOOD_SUGAR = 1;
    public static final double MIN_BMI = 1.0d;
    public static final double MIN_BODY_AGE = 5.0d;
    public static final double MIN_BODY_FAT_RATE = 0.0d;
    public static final double MIN_BODY_SCORE = 0.0d;
    public static final double MIN_BONE_SALT = 0.5d;
    public static final double MIN_DYNAMIC_HEARTRATE = 1.0d;
    public static final double MIN_IMPEDANCE = 0.1d;
    public static final double MIN_MOISTURE = 0.0d;
    public static final double MIN_MOISTURE_RATE = 0.0d;
    public static final double MIN_MUSCLE_MASS = 0.1d;
    public static final double MIN_PROTEIN_RATE = 0.0d;
    public static final double MIN_PROTEIN_VALUE = 0.0d;
    public static final double MIN_SKELETAL_MUSCLE_MASS = 1.0d;
    public static final int MIN_TEMPERATURE = 34;
    public static final int MIN_URIC_ACID = 0;
    public static final double MIN_VISCERAL_FAT_LEVEL = 1.0d;
    public static final double MIN_WEIGHT = 0.1d;
    public static final String NAME = "name";
    public static final String ON_BIND_DEVICE_RESULT_NAME = "onBindDeviceResult";
    public static final String ON_BLE_CHARACTERISTIC_READ_CALLBACK_NAME = "onBleCharacteristicRead";
    public static final String ON_BLE_CHARACTERISTIC_VALUE_CHANGE_CALLBACK_NAME = "onBleCharacteristicValueChange";
    public static final String ON_BLE_CHARACTERISTIC_WRITE_CALLBACK_NAME = "onBleCharacteristicWrite";
    public static final String ON_BLE_CONNECTION_STATE_CHANGE_CALLBACK_NAME = "onBleConnectionStateChange";
    public static final String ON_BLE_DEVICE_FOUND = "onBluetoothDeviceFound";
    public static final String ON_BLE_SERVICES_DISCOVERED_CALLBACK_NAME = "onBleServicesDiscovered";
    public static final String ON_BLUETOOTH_ADAPTER_STATE_CHANGE_CALLBACK_NAME = "onBluetoothAdapterStateChange";
    public static final String ON_DELETE_MEASURE_RESULT_NAME = "deleteMultipleHealthData";
    public static final String ON_GET_DATA_RESULT_NAME = "onGetDataResult";
    public static final String ON_GET_USER_INFO_RESULT_NAME = "onGetUserInfoResult";
    public static final String ON_HAS_HEALTH_DATA_RESULT_NAME = "onHasHealthDataResult";
    public static final String ON_OPEN_BLE_ADAPTER = "openBluetoothAdapter";
    public static final String ON_SAVE_MEASURE_RESULT_NAME = "onSaveMeasureResult";
    public static final String ON_SAVE_MULTIPLE_MEASURE_RESULT_NAME = "onSaveMultipleMeasureResult";
    public static final String ON_SCAN_CODE_RESULT_NAME = "onScanCodeResult";
    public static final String ON_SYNC_CLOUD_RESULT_NAME = "onSyncCloudResult";
    public static final int OPEN_BLUETOOTH_CODE = 4;
    public static final String PRODUCT_ID = "productId";
    public static final String PROD_ID = "prodId";
    public static final String QUERY_KIND = "queryKind";
    public static final String REGISTER_NUM = "registerNum";
    public static final String REGISTER_REQUEST_MTU_CALLBACK_NAME = "registerRequestMtu";
    private static final String RELEASE_TAG = "R_BleConstants";
    public static final String REST_HEARTRATE = "pulse";
    public static final String RESULT_CODE = "resultCode";
    public static final int SAVE_DATA_RESULT_MSG = 10009;
    public static final String SAVE_HEALTH_DATA = "saveHealthData";
    public static final int SAVE_MULTIPLE_DATA_RESULT_MSG = 10013;
    public static final String SAVE_MULTIPLE_HEALTH_DATA = "saveMultipleHealthData";
    public static final int SAVE_RETRY_LIMIT = 3;
    public static final String SERIAL_NUMBER = "sn";
    public static final String SKIPPING_ROPE_TYPE = "skippingRopeType";
    public static final int SPORT_KIT_TYPE_WALK = 30005;
    public static final String SPORT_TYPE = "sportType";
    public static final String SPORT_TYPE_BIKE = "259";
    public static final String SPORT_TYPE_INDOOR_BIKE = "265";
    public static final String SPORT_TYPE_INDOOR_WALKING = "281";
    public static final int SPORT_TYPE_KIT_BIKE = 30007;
    public static final int SPORT_TYPE_KIT_RUN = 30006;
    public static final String SPORT_TYPE_RUN = "258";
    public static final String SPORT_TYPE_TREADMILL = "264";
    public static final String SPORT_TYPE_WALK = "257";
    public static final String START_TIME = "startTime";
    public static final String STATUS = "status";
    public static final String STEP = "step";
    private static final String TAG = "BleConstants";
    public static final String TEMPERATURE = "temp";
    public static final String TOTAL_CALORIES = "totalCalories";
    public static final String TOTAL_DESCENT = "totalDescent";
    public static final String TOTAL_DISTANCE = "totalDistance";
    public static final String TOTAL_TIME = "totalTime";
    public static final String TYPE = "type";
    public static final String TYPE_FASCIA_GUN_INDEX = "268";
    public static final int UNDEFINED_OWNER_ID = 0;
    public static final String UNIQUE_ID = "uniqueId";
    public static final String URIC_ACID = "uricAcid";
    public static final String URIC_ACID_TYPE = "uricAcidType";
    public static final String UUID = "uuid";
    public static final String VALUE = "value";
    public static final String WEIGHT = "weight";
    public static final String WEIGHT_KEY = "test";
    public static final String WEIGHT_TYPE = "weightType";
    public static final String PARAM_INVALID_STRING = String.valueOf(2);
    private static final int[] BLOOD_SUGAR_DATA_TYPES = {2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2106};
    public static final String BEFORE_BREAKFAST = "beforeBreakfast";
    public static final String AFTER_BREAKFAST = "afterBreakfast";
    public static final String BEFORE_LUNCH = "beforeLunch";
    public static final String AFTER_LUNCH = "afterLunch";
    public static final String BEFORE_DINNER = "beforeDinner";
    public static final String AFTER_DINNER = "afterDinner";
    public static final String BEFORE_BEDTIME = "beforeBedtime";
    public static final String EARLY_MOENIBNG = "earlyMorning";
    public static final String RANDOMNESS = "randomness";
    private static final String[] BLOOD_SUGAR_DATA_KEY_TYPES = {BEFORE_BREAKFAST, AFTER_BREAKFAST, BEFORE_LUNCH, AFTER_LUNCH, BEFORE_DINNER, AFTER_DINNER, BEFORE_BEDTIME, EARLY_MOENIBNG, RANDOMNESS};
    private static final int[] WEIGHT_DATA_INT_TYPES = {2004, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2090, 2029, 2030, 2001, Constants.START_TO_MAIN_ACTIVITY, 2033, 2053};
    public static final String BMI = "bmi";
    public static final String MUSCLE_MASS = "muscleMass";
    public static final String BASAL_METABOLISM = "basalMetabolism";
    public static final String MOISTURE = "moisture";
    public static final String VISCERAL_FAT_LEVEL = "visceralFatLevel";
    public static final String BONE_SALT = "boneSalt";
    public static final String PROTEIN_RATE = "proteinRate";
    public static final String PROTEIN_VALUE = "proteinValue";
    public static final String BODY_SCORE = "bodyScore";
    public static final String BODY_AGE = "bodyAge";
    public static final String BODY_FAT_RATE = "bodyFatRate";
    public static final String IMPEDANCE = "impedance";
    public static final String MOISTURE_RATE = "moistureRate";
    public static final String SKELETAL_MUSCLE_MASS = "skeletalMuscleMass";
    private static final String[] WEIGHT_DATA_JSON_TYPES = {"weight", BMI, MUSCLE_MASS, BASAL_METABOLISM, MOISTURE, VISCERAL_FAT_LEVEL, BONE_SALT, PROTEIN_RATE, PROTEIN_VALUE, BODY_SCORE, BODY_AGE, BODY_FAT_RATE, IMPEDANCE, MOISTURE_RATE, SKELETAL_MUSCLE_MASS};
    private static final String[] WEIGHT_DATA_TYPES_KEY = {"bodyWeight", BMI, MUSCLE_MASS, BASAL_METABOLISM, MOISTURE, VISCERAL_FAT_LEVEL, BONE_SALT, PROTEIN_RATE, "protein", BODY_SCORE, BODY_AGE, BODY_FAT_RATE, IMPEDANCE, MOISTURE_RATE, "skeletalMusclelMass"};
    private static final int[] BLOOD_PRESSURE_DATA_VALUE_TYPES = {DicDataTypeUtil.DataType.BLOOD_PRESSURE_SYSTOLIC.value(), DicDataTypeUtil.DataType.BLOOD_PRESSURE_DIASTOLIC.value(), DicDataTypeUtil.DataType.BLOOD_PRESSURE_SPHYGMUS.value()};
    public static final String BLOODPRESSURE_SYSTOLIC = "systolic";
    public static final String BLOODPRESSURE_DIASTOLIC = "diastolic";
    public static final String BLOODPRESSURE_SPHYGMUS = "sphygmus";
    private static final String[] BLOOD_PRESSURE_DATA_TYPES = {BLOODPRESSURE_SYSTOLIC, BLOODPRESSURE_DIASTOLIC, BLOODPRESSURE_SPHYGMUS};
    private static final String[] BLOOD_PRESSURE_DATA_KEY_TYPES = {"BLOOD_PRESSURE_SYSTOLIC", "BLOOD_PRESSURE_DIASTOLIC", BLOODPRESSURE_SPHYGMUS};

    private BleConstants() {
    }

    public static final int[] getBloodSugarDataTypesClone() {
        return (int[]) BLOOD_SUGAR_DATA_TYPES.clone();
    }

    public static final String[] getBloodSugarDataKeyTypesClone() {
        return (String[]) BLOOD_SUGAR_DATA_KEY_TYPES.clone();
    }

    public static final int[] getWeightDataIntTypesClone() {
        return (int[]) WEIGHT_DATA_INT_TYPES.clone();
    }

    public static final String[] getWeightDataJsonTypesClone() {
        return (String[]) WEIGHT_DATA_JSON_TYPES.clone();
    }

    public static final String[] getWeightDataTypesKeyClone() {
        return (String[]) WEIGHT_DATA_TYPES_KEY.clone();
    }

    public static final int[] getBloodPressureDataValueTypesClone() {
        return (int[]) BLOOD_PRESSURE_DATA_VALUE_TYPES.clone();
    }

    public static final String[] getBloodPressureDataTypesClone() {
        return (String[]) BLOOD_PRESSURE_DATA_TYPES.clone();
    }

    public static final String[] getBloodPressureDataKeyTypesClone() {
        return (String[]) BLOOD_PRESSURE_DATA_KEY_TYPES.clone();
    }

    public static boolean haveScanPermission() {
        ReleaseLogUtil.e(RELEASE_TAG, "haveScanPermission");
        if (Build.VERSION.SDK_INT > 30) {
            ReleaseLogUtil.e(RELEASE_TAG, "haveScanPermission large to R");
            return PermissionUtil.e(BaseApplication.wa_(), PermissionUtil.PermissionType.SCAN) == PermissionUtil.PermissionResult.GRANTED;
        }
        ReleaseLogUtil.e(RELEASE_TAG, "haveScanPermission small to R and GRANTED");
        return true;
    }
}
