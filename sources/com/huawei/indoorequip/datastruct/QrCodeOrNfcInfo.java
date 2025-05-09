package com.huawei.indoorequip.datastruct;

import android.text.TextUtils;
import com.huawei.health.device.model.RecordAction;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.lbv;

/* loaded from: classes5.dex */
public class QrCodeOrNfcInfo {
    private static final int ERROR_CODE = -1;
    public static final int FROM_NFC_DATA = 1;
    public static final int FROM_QR_CODE_DATA = 2;
    private static final int INDEX_OF_BLE = 4;
    private static final int INDEX_OF_BLE_NAME = 5;
    private static final int INDEX_OF_MAC = 18;
    private static final int INDEX_OF_PID = 4;
    private static final int INDEX_OF_SEPARATOR_BETWEEN_MAC = 13;
    private static final int INDEX_OF_START = 1;
    private static final int INDEX_OF_SUB_LENGTH = 2;
    private static final String SAPARATOR_OF_SOURCE_FROM_QR_CODE_AND_NFC = "&";
    private static final String TAG = "Track_IDEQ_QRCodeOrNFCInfo";
    private String mBtMac;
    private String mBtName;
    private String mBtType;
    private int mDataSource = 0;
    private String mDeviceType;
    private String mEquipment;
    private String mGym;
    private String mModel;
    private String mPid;
    private String mTvName;

    public QrCodeOrNfcInfo() {
    }

    public QrCodeOrNfcInfo(String str, String str2, QrCodeOrNfcBean qrCodeOrNfcBean) {
        this.mBtType = str;
        this.mBtMac = str2;
        this.mGym = qrCodeOrNfcBean.mGym;
        this.mEquipment = qrCodeOrNfcBean.mEquipment;
        this.mModel = qrCodeOrNfcBean.mModel;
        this.mBtName = qrCodeOrNfcBean.mBtName;
        this.mDeviceType = qrCodeOrNfcBean.mDeviceType;
        this.mPid = qrCodeOrNfcBean.mPid;
    }

    public static QrCodeOrNfcInfo analysisQrCodeOrNfc(String str) {
        LogUtil.a(TAG, "analysisQrCodeOrNfc, input: ****");
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String btMacString = getBtMacString(str);
        String btNameString = getBtNameString(str);
        if (TextUtils.isEmpty(btMacString) && TextUtils.isEmpty(btNameString)) {
            return null;
        }
        int indexOf = str.indexOf("&mGym=");
        String gymString = indexOf != -1 ? getGymString(str, indexOf) : "";
        int indexOf2 = str.indexOf("&pid=");
        String pidString = indexOf2 != -1 ? getPidString(str, indexOf2) : "";
        int indexOf3 = str.indexOf("&eqp=");
        String equipmentString = indexOf3 != -1 ? getEquipmentString(str, indexOf3) : "";
        int indexOf4 = str.indexOf("&mdl=");
        QrCodeOrNfcBean qrCodeOrNfcBean = new QrCodeOrNfcBean(gymString, equipmentString, indexOf4 != -1 ? getModelString(str, indexOf4) : "", btNameString, pidString);
        qrCodeOrNfcBean.setDeviceType(getDeviceType(str));
        qrCodeOrNfcBean.setIsFtmp(lbv.f(str));
        return new QrCodeOrNfcInfo("BLE", btMacString, qrCodeOrNfcBean);
    }

    private static String getPidString(String str, int i) {
        int i2 = i + 4;
        if (str.length() < i2) {
            LogUtil.h(TAG, "getPidString StringIndexOutOfBoundsException");
            return "";
        }
        String substring = str.substring(i2);
        if (substring == null) {
            return "";
        }
        int indexOf = substring.indexOf("&");
        if (indexOf >= 2) {
            return substring.substring(1, indexOf);
        }
        return (indexOf != -1 || substring.length() < 2) ? "" : substring.substring(1);
    }

    private static String getBtNameString(String str) {
        int indexOf;
        if (TextUtils.isEmpty(str) || (indexOf = str.indexOf("&blen=")) == -1) {
            return "";
        }
        String substring = str.substring(indexOf + 5);
        if (TextUtils.isEmpty(substring)) {
            return "";
        }
        int indexOf2 = substring.indexOf("&");
        int indexOf3 = substring.indexOf(";");
        if (indexOf2 != -1) {
            return substring.substring(1, indexOf2);
        }
        if (indexOf3 != -1) {
            return substring.substring(1, indexOf3);
        }
        return substring.substring(1);
    }

    private static String getModelString(String str, int i) {
        int i2 = i + 4;
        if (str.length() < i2) {
            LogUtil.h(TAG, "getModelString StringIndexOutOfBoundsException");
            return "";
        }
        String substring = str.substring(i2);
        if (substring == null) {
            return "";
        }
        int indexOf = substring.indexOf("&");
        if (indexOf >= 2) {
            return substring.substring(1, indexOf);
        }
        return (indexOf != -1 || substring.length() < 2) ? "" : substring.substring(1);
    }

    private static String getEquipmentString(String str, int i) {
        int i2 = i + 4;
        if (str.length() < i2) {
            LogUtil.h(TAG, "getEquipmentString StringIndexOutOfBoundsException");
            return "";
        }
        String substring = str.substring(i2);
        if (substring == null) {
            return "";
        }
        int indexOf = substring.indexOf("&");
        if (indexOf >= 2) {
            return substring.substring(1, indexOf);
        }
        return (indexOf != -1 || substring.length() < 2) ? "" : substring.substring(1);
    }

    private static String getGymString(String str, int i) {
        int i2 = i + 4;
        if (str.length() < i2) {
            LogUtil.h(TAG, "getGymString StringIndexOutOfBoundsException");
            return "";
        }
        String substring = str.substring(i2);
        if (substring == null) {
            return "";
        }
        int indexOf = substring.indexOf("&");
        if (indexOf >= 2) {
            return substring.substring(1, indexOf);
        }
        return (indexOf != -1 || substring.length() < 2) ? "" : substring.substring(1);
    }

    private static String getBtMacString(String str) {
        int indexOf;
        if (TextUtils.isEmpty(str) || (indexOf = str.indexOf("&ble=")) == -1) {
            return "";
        }
        String substring = str.substring(indexOf + 4);
        if (TextUtils.isEmpty(substring)) {
            return "";
        }
        int indexOf2 = substring.indexOf("&");
        int indexOf3 = substring.indexOf(";");
        if (indexOf2 == 13) {
            return lbv.e(substring.substring(1, 13));
        }
        if (indexOf2 == 18) {
            return substring.substring(1, 18);
        }
        if (indexOf3 == 13) {
            return lbv.e(substring.substring(1, 13));
        }
        if (indexOf3 == 18) {
            return substring.substring(1, 18);
        }
        if (indexOf2 == -1 && substring.length() == 13) {
            return lbv.e(substring.substring(1));
        }
        return (indexOf2 == -1 && substring.length() == 18) ? substring.substring(1) : "";
    }

    private static String getDeviceType(String str) {
        String substring;
        int indexOf;
        try {
            substring = str.substring(str.indexOf(RecordAction.ACT_COST_TIME_TAG));
            indexOf = substring.indexOf("&");
        } catch (IndexOutOfBoundsException unused) {
            LogUtil.a(TAG, "no devicetype(t=) in NFC payload, default type is Treadmill");
        }
        return indexOf < 0 ? "31" : substring.substring(2, indexOf);
    }

    public String getTvName() {
        return this.mTvName;
    }

    public void setTvName(String str) {
        this.mTvName = str;
    }

    public String getBtType() {
        return this.mBtType;
    }

    public void setBtType(String str) {
        this.mBtType = str;
    }

    public String getBtMac() {
        return this.mBtMac;
    }

    public void setBtMac(String str) {
        this.mBtMac = str;
    }

    public String getGym() {
        return this.mGym;
    }

    public void setGym(String str) {
        this.mGym = str;
    }

    public String getEquipment() {
        return this.mEquipment;
    }

    public void setEquipment(String str) {
        this.mEquipment = str;
    }

    public String getModel() {
        return this.mModel;
    }

    public void setModel(String str) {
        this.mModel = str;
    }

    public String getBtName() {
        return this.mBtName;
    }

    public void setBtName(String str) {
        this.mBtName = str;
    }

    public int getDataSource() {
        return this.mDataSource;
    }

    public void setDataSource(int i) {
        this.mDataSource = i;
    }

    public String getDeviceType() {
        return this.mDeviceType;
    }

    public void setDeviceType(String str) {
        this.mDeviceType = str;
    }

    public String getPid() {
        return this.mPid;
    }

    public void setPid(String str) {
        this.mPid = str;
    }

    public static class QrCodeOrNfcBean {
        private String mBtName;
        private String mDeviceType;
        private String mEquipment;
        private String mGym;
        private boolean mIsFtmp;
        private String mModel;
        private String mPid;

        public QrCodeOrNfcBean(String str, String str2, String str3, String str4, String str5) {
            this.mGym = str;
            this.mEquipment = str2;
            this.mModel = str3;
            this.mBtName = str4;
            this.mPid = str5;
        }

        public void setIsFtmp(boolean z) {
            this.mIsFtmp = z;
        }

        public String getDeviceType() {
            return this.mDeviceType;
        }

        public void setDeviceType(String str) {
            this.mDeviceType = str;
        }
    }
}
