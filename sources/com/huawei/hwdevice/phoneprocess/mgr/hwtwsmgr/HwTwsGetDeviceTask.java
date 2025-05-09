package com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.profile.client.profile.ProfileClient;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import defpackage.cvx;
import defpackage.cwl;
import defpackage.jsz;
import defpackage.kfg;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class HwTwsGetDeviceTask extends BaseTwsTask {
    private static final int ACCOUNT_ID = 1;
    private static final int COMPARE_BIG = 1;
    private static final int COMPARE_SMALL = -1;
    private static final int CONVERT_TIME = 2;
    private static final int DEFAULT_VALUE = 0;
    private static final int DEVICE_NAME_ORDER = 4;
    private static final int FRAME_MAX_NUMBER = 4;
    private static final int INDEX_VALUE = 8;
    private static final int LENGTH_VALUE = 1;
    private static final int LIST_ORDER = 129;
    private static final int MAC_VALUE = 5;
    private static final String SERVICE_DEVICE_TYPE = "serviceDeviceType";
    private static final int STRUCT_ORDER = 130;
    private static final int SUPPORT_MAX_NUMBER = 3;
    private static final String TAG = "HwTwsGetDeviceTask";
    private static final String TAG_RELEASE = "R_HwTwsGetDeviceTask";
    private static final int TIME_STAMP_VALUE = 7;
    private static final int TOKEN_VALUE = 6;
    private static final int TOTAL_NAME = 2;
    private static final int UUID_ORDER = 3;
    private static final long WAIT_MESSAGE_TIMEOUT = 20000;
    private Context mContext;
    private List<kfg> mPairDeviceList = null;
    private cwl mTlvUtils;

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.BaseTwsTask
    public boolean isTypeGet() {
        return true;
    }

    public HwTwsGetDeviceTask(cwl cwlVar, Context context) {
        this.mTlvUtils = cwlVar;
        this.mContext = context;
    }

    static class e implements Comparator<kfg>, Serializable {
        private static final long serialVersionUID = -7495480833886617508L;

        private e() {
        }

        @Override // java.util.Comparator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public int compare(kfg kfgVar, kfg kfgVar2) {
            if (kfgVar.o() > kfgVar2.o()) {
                return -1;
            }
            return kfgVar.o() < kfgVar2.o() ? 1 : 0;
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.BaseTwsTask
    public boolean onExecutor(ProfileClient profileClient) {
        List<kfg> devicesFromProfile = getDevicesFromProfile(profileClient);
        if (devicesFromProfile == null) {
            LogUtil.h(TAG, "Task find pair list is null,task name:", getTaskName());
            return true;
        }
        this.mPairDeviceList = devicesFromProfile;
        ReleaseLogUtil.e(TAG, "getDevicesPromProfile total pair lists.size:", Integer.valueOf(devicesFromProfile.size()));
        sendCommand18(devicesFromProfile.size());
        setWaitMessage("Wait_5_43_18", WAIT_MESSAGE_TIMEOUT);
        return false;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.BaseTwsTask
    public boolean onWaitFor(String str, Object obj) {
        LogUtil.a(TAG, "onWaitFor:", str, ",taskName:", getTaskName());
        byte[] bArr = obj instanceof byte[] ? (byte[]) obj : null;
        if (bArr == null || bArr.length <= 0) {
            LogUtil.h(TAG, "onWaitFor do not find wait message");
            return true;
        }
        receiverCommand18(bArr);
        return true;
    }

    private String getValueFromProfile(Map<String, Object> map, String str) {
        Object obj = map.get(str);
        if (obj == null) {
            LogUtil.h(TAG, "getValueFromProfile: Get fail key.");
            return "";
        }
        if (!(obj instanceof String)) {
            LogUtil.h(TAG, "getValueFromProfile: class type is wrong key.");
            return "";
        }
        return (String) obj;
    }

    private List<kfg> getDevicesFromProfile(ProfileClient profileClient) {
        LogUtil.a(TAG, "getDevicesPromProfile enter");
        List<DeviceProfile> devicesByType = profileClient.getDevicesByType("082", true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (devicesByType != null) {
            LogUtil.a(TAG, "getDevicesByType list size = ", Integer.valueOf(devicesByType.size()));
            ArrayList arrayList = new ArrayList(16);
            for (DeviceProfile deviceProfile : devicesByType) {
                if (deviceProfile == null || TextUtils.isEmpty(deviceProfile.getId())) {
                    LogUtil.h(TAG, "getDevicesPromProfile but deviceProfile is null or get id is null");
                } else {
                    Map<String, Object> profile = deviceProfile.getProfile();
                    String valueFromProfile = getValueFromProfile(profile, "devType");
                    if (!"082".equals(valueFromProfile)) {
                        LogUtil.h(TAG, "getDevicesPromProfile but not Tws type:", valueFromProfile);
                    } else {
                        String valueFromProfile2 = getValueFromProfile(profile, "mac");
                        if (TextUtils.isEmpty(valueFromProfile2)) {
                            LogUtil.h(TAG, "getDevicesPromProfile but not find mac address:");
                        } else {
                            String valueFromProfile3 = getValueFromProfile(profile, "udid");
                            Map<String, Object> serviceProfileMap = getServiceProfileMap(profileClient, valueFromProfile3);
                            if (serviceProfileMap == null || serviceProfileMap.isEmpty()) {
                                LogUtil.h(TAG, "getDevicesPromProfile do not get serviceProfileMap");
                            } else {
                                kfg buildTrustPairDevice = buildTrustPairDevice(valueFromProfile, valueFromProfile3, valueFromProfile2, profile, serviceProfileMap);
                                if (buildTrustPairDevice == null) {
                                    LogUtil.h(TAG, "getDevicesPromProfile do not create pair device");
                                } else {
                                    kfg deviceByUdId = getDeviceByUdId(valueFromProfile3, arrayList);
                                    if (deviceByUdId != null) {
                                        arrayList.remove(deviceByUdId);
                                        LogUtil.h(TAG, "getDevicesPromProfile find same udid device,need remove");
                                    }
                                    LogUtil.a(TAG, "getDevicesPromProfile add trust pair device");
                                    arrayList.add(buildTrustPairDevice);
                                }
                            }
                        }
                    }
                }
            }
            return arrayList;
        }
        LogUtil.h(TAG, "getDevicesPromProfile but profile find error");
        return null;
    }

    private kfg getDeviceByUdId(String str, List<kfg> list) {
        for (kfg kfgVar : list) {
            if (str.equals(kfgVar.n())) {
                return kfgVar;
            }
        }
        return null;
    }

    private kfg buildTrustPairDevice(String str, String str2, String str3, Map<String, Object> map, Map<String, Object> map2) {
        kfg kfgVar = new kfg();
        kfgVar.m(str2);
        kfgVar.d(str3);
        kfgVar.e(str);
        kfgVar.k(getValueFromProfile(map, "udid"));
        kfgVar.j(getValueFromProfile(map, "model"));
        kfgVar.f(getValueFromProfile(map, ProfileRequestConstants.MANU));
        kfgVar.i(getValueFromProfile(map, "prodId"));
        kfgVar.a(getValueFromProfile(map, ProfileRequestConstants.HIV));
        kfgVar.b(getValueFromProfile(map, "deviceName"));
        kfgVar.g("SyncPairToken");
        kfgVar.h(getValueFromProfile(map2, SERVICE_DEVICE_TYPE));
        if (!kfgVar.e(cvx.a(getValueFromProfile(map2, "pairToken")))) {
            LogUtil.h(TAG, "buildTrustPairDevice but do not find token");
            return null;
        }
        long longFromProfile = getLongFromProfile(map2, "timestamp");
        if (longFromProfile == null) {
            longFromProfile = 0L;
            LogUtil.a(TAG, "buildTrustPairDevice find timestamp is null");
        }
        kfgVar.a(longFromProfile);
        return kfgVar;
    }

    private Map<String, Object> getServiceProfileMap(ProfileClient profileClient, String str) {
        List<ServiceProfile> serviceProfiles = getServiceProfiles(profileClient, str);
        if (serviceProfiles == null) {
            LogUtil.h(TAG, "getServiceProfileMap getServicesOfDevice : fail,serviceProfileList is null");
            return null;
        }
        LogUtil.h(TAG, "getServiceProfileMap serviceProfileList.size():", Integer.valueOf(serviceProfiles.size()));
        for (ServiceProfile serviceProfile : serviceProfiles) {
            if ("SyncPairToken".equals(serviceProfile.getId())) {
                if (TextUtils.isEmpty(serviceProfile.getType())) {
                    LogUtil.h(TAG, "getServiceProfileMap serviceProfile.getType() is empty");
                    return null;
                }
                ServiceCharacteristicProfile serviceCharacteristicProfile = getServiceCharacteristicProfile(profileClient, str);
                if (serviceCharacteristicProfile == null) {
                    LogUtil.h(TAG, "getServiceProfileMap characteristicProfile is null");
                    return null;
                }
                Map<String, Object> profile = serviceCharacteristicProfile.getProfile();
                profile.put(SERVICE_DEVICE_TYPE, serviceProfile.getType());
                return profile;
            }
        }
        return null;
    }

    private ServiceCharacteristicProfile getServiceCharacteristicProfile(ProfileClient profileClient, String str) {
        LogUtil.a(TAG, "getServiceCharacteristicProfile enter");
        ServiceCharacteristicProfile serviceCharacteristics = profileClient.getServiceCharacteristics(str, "SyncPairToken", false, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (serviceCharacteristics != null && serviceCharacteristics.getProfile() != null && !serviceCharacteristics.getProfile().isEmpty() && isTokensValid(serviceCharacteristics)) {
            return serviceCharacteristics;
        }
        LogUtil.h(TAG, "getServiceCharacteristicProfile mIsSyncFromCloud is true");
        return profileClient.getServiceCharacteristics(str, "SyncPairToken", true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
    }

    private boolean isTokensValid(ServiceCharacteristicProfile serviceCharacteristicProfile) {
        LogUtil.a(TAG, "isTokensValid enter");
        if (serviceCharacteristicProfile == null) {
            LogUtil.h(TAG, "isTokensValid characteristicProfile is null");
            return false;
        }
        Map<String, Object> profile = serviceCharacteristicProfile.getProfile();
        if (profile == null || profile.isEmpty()) {
            LogUtil.h(TAG, "isTokensValid characteristicProfile.getProfile() is empty");
            return false;
        }
        byte[] a2 = cvx.a(getValueFromProfile(profile, "pairToken"));
        if (a2 != null && a2.length != 0) {
            return true;
        }
        LogUtil.h(TAG, "isTokensValid is false");
        return false;
    }

    private List<ServiceProfile> getServiceProfiles(ProfileClient profileClient, String str) {
        LogUtil.a(TAG, "getServiceProfiles enter");
        List<ServiceProfile> servicesOfDevice = profileClient.getServicesOfDevice(str, false, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (servicesOfDevice != null && servicesOfDevice.size() != 0) {
            return servicesOfDevice;
        }
        LogUtil.h(TAG, "getServiceProfiles mIsSyncFromCloud is true");
        return profileClient.getServicesOfDevice(str, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
    }

    private Long getLongFromProfile(Map<String, Object> map, String str) {
        Object obj = map.get(str);
        if (obj == null) {
            LogUtil.h(TAG, "getLong: Get fail key.");
            return null;
        }
        if (!(obj instanceof Long)) {
            LogUtil.h(TAG, "getLong: class type is wrong key.");
            return null;
        }
        return (Long) obj;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00a5 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void receiverCommand18(byte[] r10) {
        /*
            r9 = this;
            java.lang.String r0 = "receiverCommand18 enter."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "R_HwTwsGetDeviceTask"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r0)
            java.lang.String r10 = defpackage.cvx.d(r10)
            boolean r0 = android.text.TextUtils.isEmpty(r10)
            java.lang.String r1 = "HwTwsGetDeviceTask"
            if (r0 != 0) goto Lb5
            int r0 = r10.length()
            r2 = 4
            if (r0 >= r2) goto L20
            goto Lb5
        L20:
            java.lang.String r10 = r10.substring(r2)
            r0 = 0
            cwl r3 = r9.mTlvUtils     // Catch: java.lang.IndexOutOfBoundsException -> L81 java.lang.NumberFormatException -> L8c defpackage.cwg -> L98
            cwe r10 = r3.a(r10)     // Catch: java.lang.IndexOutOfBoundsException -> L81 java.lang.NumberFormatException -> L8c defpackage.cwg -> L98
            java.util.List r10 = r10.e()     // Catch: java.lang.IndexOutOfBoundsException -> L81 java.lang.NumberFormatException -> L8c defpackage.cwg -> L98
            boolean r3 = defpackage.koq.b(r10)     // Catch: java.lang.IndexOutOfBoundsException -> L81 java.lang.NumberFormatException -> L8c defpackage.cwg -> L98
            r4 = 1
            if (r3 == 0) goto L40
            java.lang.Object[] r10 = new java.lang.Object[r4]     // Catch: java.lang.IndexOutOfBoundsException -> L81 java.lang.NumberFormatException -> L8c defpackage.cwg -> L98
            java.lang.String r2 = "receiverCommand18 tlvs isEmpty"
            r10[r0] = r2     // Catch: java.lang.IndexOutOfBoundsException -> L81 java.lang.NumberFormatException -> L8c defpackage.cwg -> L98
            com.huawei.hwlogsmodel.LogUtil.b(r1, r10)     // Catch: java.lang.IndexOutOfBoundsException -> L81 java.lang.NumberFormatException -> L8c defpackage.cwg -> L98
            return
        L40:
            java.util.Iterator r10 = r10.iterator()     // Catch: java.lang.IndexOutOfBoundsException -> L81 java.lang.NumberFormatException -> L8c defpackage.cwg -> L98
            r3 = r0
            r5 = r3
        L46:
            boolean r6 = r10.hasNext()     // Catch: java.lang.IndexOutOfBoundsException -> L7b java.lang.NumberFormatException -> L7d defpackage.cwg -> L7f
            if (r6 == 0) goto La3
            java.lang.Object r6 = r10.next()     // Catch: java.lang.IndexOutOfBoundsException -> L7b java.lang.NumberFormatException -> L7d defpackage.cwg -> L7f
            cwd r6 = (defpackage.cwd) r6     // Catch: java.lang.IndexOutOfBoundsException -> L7b java.lang.NumberFormatException -> L7d defpackage.cwg -> L7f
            java.lang.String r7 = r6.e()     // Catch: java.lang.IndexOutOfBoundsException -> L7b java.lang.NumberFormatException -> L7d defpackage.cwg -> L7f
            int r7 = health.compact.a.CommonUtil.w(r7)     // Catch: java.lang.IndexOutOfBoundsException -> L7b java.lang.NumberFormatException -> L7d defpackage.cwg -> L7f
            r8 = 3
            if (r7 == r8) goto L72
            if (r7 == r2) goto L69
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch: java.lang.IndexOutOfBoundsException -> L7b java.lang.NumberFormatException -> L7d defpackage.cwg -> L7f
            java.lang.String r7 = "receiverCommand18 default"
            r6[r0] = r7     // Catch: java.lang.IndexOutOfBoundsException -> L7b java.lang.NumberFormatException -> L7d defpackage.cwg -> L7f
            com.huawei.hwlogsmodel.LogUtil.h(r1, r6)     // Catch: java.lang.IndexOutOfBoundsException -> L7b java.lang.NumberFormatException -> L7d defpackage.cwg -> L7f
            goto L46
        L69:
            java.lang.String r6 = r6.c()     // Catch: java.lang.IndexOutOfBoundsException -> L7b java.lang.NumberFormatException -> L7d defpackage.cwg -> L7f
            int r5 = health.compact.a.CommonUtil.w(r6)     // Catch: java.lang.IndexOutOfBoundsException -> L7b java.lang.NumberFormatException -> L7d defpackage.cwg -> L7f
            goto L46
        L72:
            java.lang.String r6 = r6.c()     // Catch: java.lang.IndexOutOfBoundsException -> L7b java.lang.NumberFormatException -> L7d defpackage.cwg -> L7f
            int r3 = health.compact.a.CommonUtil.w(r6)     // Catch: java.lang.IndexOutOfBoundsException -> L7b java.lang.NumberFormatException -> L7d defpackage.cwg -> L7f
            goto L46
        L7b:
            r0 = r3
            goto L82
        L7d:
            r0 = r3
            goto L8d
        L7f:
            r0 = r5
            goto L99
        L81:
            r5 = r0
        L82:
            java.lang.String r10 = "receiverCommand18 IndexOutOfBoundsException"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r10)
            goto L96
        L8c:
            r5 = r0
        L8d:
            java.lang.String r10 = "receiverCommand18 NumberFormatException"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r10)
        L96:
            r3 = r0
            goto La3
        L98:
            r3 = r0
        L99:
            java.lang.String r10 = "receiverCommand18 TlvException"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r10)
            r5 = r0
        La3:
            if (r3 <= 0) goto Lab
            if (r5 <= 0) goto Lab
            r9.sendCommand19(r3, r5)
            goto Lb4
        Lab:
            java.lang.String r10 = "receiverCommand18 but sendCommand19 find error"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r10)
        Lb4:
            return
        Lb5:
            java.lang.String r10 = "handleTws18 data is error"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.HwTwsGetDeviceTask.receiverCommand18(byte[]):void");
    }

    private void sendCommand18(int i) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1000);
        if (accountInfo == null) {
            accountInfo = "";
        }
        String str = (cvx.e(1) + cvx.e(cvx.c(accountInfo).length() / 2) + cvx.c(accountInfo)) + cvx.e(2) + cvx.e(2) + cvx.a(i);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(43);
        deviceCommand.setCommandID(18);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        LogUtil.a(TAG, "sendCommand18, deviceCommand :", deviceCommand.toString());
        jsz.b(this.mContext).sendDeviceData(deviceCommand);
    }

    private void sendCommand19(int i, int i2) {
        int i3;
        ReleaseLogUtil.e(TAG_RELEASE, "sendCommand19 enter.");
        if (this.mPairDeviceList.size() < i) {
            LogUtil.a(TAG, "supportMaxNum is bigger than maxNumber.");
            i = this.mPairDeviceList.size();
            if (this.mPairDeviceList.size() == 0) {
                String str = cvx.e(129) + cvx.e(0) + (cvx.e(8) + cvx.e(1) + cvx.e(0));
                DeviceCommand deviceCommand = new DeviceCommand();
                deviceCommand.setServiceID(43);
                deviceCommand.setCommandID(19);
                deviceCommand.setDataContent(cvx.a(str));
                deviceCommand.setDataLen(cvx.a(str).length);
                LogUtil.a(TAG, "deviceCommand :", deviceCommand.toString());
                jsz.b(this.mContext).sendDeviceData(deviceCommand);
                return;
            }
        }
        if (i % i2 == 0) {
            i3 = i / i2;
        } else {
            i3 = (i / i2) + 1;
        }
        Collections.sort(this.mPairDeviceList, new e());
        sendFramesCommand(i, i3, i2);
    }

    private void sendFramesCommand(int i, int i2, int i3) {
        StringBuffer stringBuffer = new StringBuffer(i);
        int i4 = 0;
        while (i4 < i2) {
            int i5 = i4 + 1;
            int i6 = i5 * i3;
            if (i6 > i) {
                i6 = i;
            }
            for (int i7 = i4 * i3; i7 < i6; i7++) {
                kfg kfgVar = this.mPairDeviceList.get(i7);
                String str = ((((cvx.e(3) + cvx.e(cvx.c(kfgVar.n()).length() / 2) + cvx.c(kfgVar.n())) + cvx.e(4) + cvx.e(cvx.c(kfgVar.a()).length() / 2) + cvx.c(kfgVar.a())) + cvx.e(5) + cvx.e(cvx.c(kfgVar.h()).length() / 2) + cvx.c(kfgVar.h())) + cvx.e(6) + cvx.e(cvx.d(kfgVar.l()).length() / 2) + cvx.d(kfgVar.l())) + cvx.e(7) + cvx.e(cvx.c(kfgVar.o()).length() / 2) + cvx.c(kfgVar.o());
                stringBuffer.append(cvx.e(130) + cvx.d(str.length() / 2) + str);
            }
            String str2 = (cvx.e(129) + cvx.d(stringBuffer.length() / 2) + ((Object) stringBuffer)) + cvx.e(8) + cvx.e(1) + cvx.e(i4);
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(43);
            deviceCommand.setCommandID(19);
            deviceCommand.setDataContent(cvx.a(str2));
            deviceCommand.setDataLen(cvx.a(str2).length);
            LogUtil.a(TAG, "sendFramesCommand: ", deviceCommand.toString());
            jsz.b(this.mContext).sendDeviceData(deviceCommand);
            if (i2 == i5) {
                LogUtil.a(TAG, "totalFrames clear.");
            }
            i4 = i5;
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.BaseTwsTask
    public String getTaskName() {
        return "DeviceConnect";
    }
}
