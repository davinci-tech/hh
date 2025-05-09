package com.huawei.operation.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.operation.beans.NetworkStatusObj;
import health.compact.a.CommonUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

/* loaded from: classes9.dex */
public class NetworkStatusUtils {
    private static final String TAG = "NetworkStatusUtils";
    private static volatile NetworkStatusUtils sInstance;

    private NetworkStatusUtils() {
    }

    public static NetworkStatusUtils getsInstance() {
        if (sInstance == null) {
            synchronized (NetworkStatusUtils.class) {
                if (sInstance == null) {
                    sInstance = new NetworkStatusUtils();
                }
            }
        }
        return sInstance;
    }

    public NetworkStatusObj getNetworkStatus(Context context) {
        LogUtil.i(TAG, "getNetworkStatus start");
        Context applicationContext = context.getApplicationContext();
        NetworkStatusObj networkStatusObj = new NetworkStatusObj();
        NetworkStatusObj.NetworkType networkType = getNetworkType();
        networkStatusObj.setType(networkType);
        if (networkType == NetworkStatusObj.NetworkType.WIFI) {
            networkStatusObj.setMetered(isMeteredWifi(applicationContext));
        } else {
            networkStatusObj.setMetered(NetworkStatusObj.NetworkMeteredType.UN_METERED);
        }
        networkStatusObj.setAirplaneMode(Settings.System.getInt(applicationContext.getContentResolver(), "airplane_mode_on", 0) != 0);
        setNetworkAndSimInfo(applicationContext, networkStatusObj);
        LogUtil.i(TAG, "getNetworkStatus end");
        return networkStatusObj;
    }

    private void setNetworkAndSimInfo(Context context, NetworkStatusObj networkStatusObj) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        if (telephonyManager == null) {
            Object systemService = context.getSystemService("phone");
            if (systemService instanceof TelephonyManager) {
                telephonyManager = (TelephonyManager) systemService;
            }
        }
        if (telephonyManager == null) {
            LogUtil.w(TAG, "setNetworkAndSimInfo: telephonyManager is null");
            return;
        }
        networkStatusObj.setOperator(telephonyManager.getNetworkOperator());
        networkStatusObj.setRoaming(telephonyManager.isNetworkRoaming());
        networkStatusObj.setNetworkCountryIso(getNetworkCountryIso(telephonyManager));
        NetworkStatusObj.SimInfoObj simInfoObj = new NetworkStatusObj.SimInfoObj();
        simInfoObj.setState(telephonyManager.getSimState());
        simInfoObj.setOperator(telephonyManager.getSimOperator());
        simInfoObj.setOperatorName(telephonyManager.getSimOperatorName());
        simInfoObj.setCountryIso(telephonyManager.getSimCountryIso());
        networkStatusObj.setSimInfo(simInfoObj);
    }

    private String getNetworkCountryIso(TelephonyManager telephonyManager) {
        String networkCountryIso = telephonyManager.getNetworkCountryIso();
        if (Build.VERSION.SDK_INT < 30) {
            return networkCountryIso;
        }
        for (int i = 0; i <= 1; i++) {
            try {
                String networkCountryIso2 = telephonyManager.getNetworkCountryIso(i);
                if (!TextUtils.isEmpty(networkCountryIso2) && !networkCountryIso.contains(networkCountryIso2)) {
                    networkCountryIso = String.format(Locale.ENGLISH, "%s,%s", networkCountryIso, networkCountryIso2);
                }
            } catch (IllegalArgumentException e) {
                LogUtil.w(TAG, "getNetworkCountryIso: exception -> " + e.getMessage());
            }
        }
        return networkCountryIso;
    }

    private NetworkStatusObj.NetworkType getNetworkType() {
        if (!NetworkUtil.j()) {
            LogUtil.i(TAG, "getNetworkType unConnected");
            return NetworkStatusObj.NetworkType.UNCONNECTED;
        }
        if (NetworkUtil.f()) {
            return NetworkStatusObj.NetworkType.MOBILE;
        }
        if (NetworkUtil.m()) {
            return NetworkStatusObj.NetworkType.WIFI;
        }
        if (NetworkUtil.g()) {
            return NetworkStatusObj.NetworkType.VPN;
        }
        LogUtil.i(TAG, "getNetworkType other");
        return NetworkStatusObj.NetworkType.OTHER;
    }

    private NetworkStatusObj.NetworkMeteredType isMeteredWifi(Context context) {
        try {
            Class<?> cls = Class.forName(CommonUtil.bf() ? "com.hihonor.android.net.wifi.WifiManagerCommonEx" : "com.huawei.android.net.wifi.WifiManagerCommonEx");
            Object invoke = cls.getMethod("getHwMeteredHint", Context.class).invoke(cls.newInstance(), context);
            if (invoke instanceof Boolean) {
                if (!((Boolean) invoke).booleanValue()) {
                    return NetworkStatusObj.NetworkMeteredType.UN_METERED;
                }
            }
            return NetworkStatusObj.NetworkMeteredType.METERED;
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
            return NetworkStatusObj.NetworkMeteredType.UNKNOWN;
        }
    }
}
