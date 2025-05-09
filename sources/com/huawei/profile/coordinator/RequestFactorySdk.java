package com.huawei.profile.coordinator;

import android.text.TextUtils;
import com.huawei.profile.coordinator.impl.DeleteDeviceFromCloudSdk;
import com.huawei.profile.coordinator.impl.GetCharacteristicsFromCloudSdk;
import com.huawei.profile.coordinator.impl.GetDevicesFromCloudSdk;
import com.huawei.profile.coordinator.impl.GetServicesOfDeviceFromCloudSdk;
import com.huawei.profile.coordinator.impl.PutCharacteristicToCloudSdk;
import com.huawei.profile.coordinator.impl.PutServiceOfDeviceToCloudSdk;
import com.huawei.profile.coordinator.impl.RegisterDeviceToCloudSdk;
import com.huawei.profile.coordinator.impl.UpdateDeviceToCloudSdk;
import com.huawei.profile.utils.logger.DsLog;

/* loaded from: classes6.dex */
public final class RequestFactorySdk {
    private static final String TAG = "RequestFactorySdk";

    private RequestFactorySdk() {
    }

    /* renamed from: com.huawei.profile.coordinator.RequestFactorySdk$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$huawei$profile$coordinator$ProfileRequestType;

        static {
            int[] iArr = new int[ProfileRequestType.values().length];
            $SwitchMap$com$huawei$profile$coordinator$ProfileRequestType = iArr;
            try {
                iArr[ProfileRequestType.PUT_DEVICE_TO_CLOUD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$huawei$profile$coordinator$ProfileRequestType[ProfileRequestType.PUT_SERVICES_TO_CLOUD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$huawei$profile$coordinator$ProfileRequestType[ProfileRequestType.DELETE_SERVICE_FROM_CLOUD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$huawei$profile$coordinator$ProfileRequestType[ProfileRequestType.PUT_SERVICE_CHARACTERISTIC_TO_CLOUD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$huawei$profile$coordinator$ProfileRequestType[ProfileRequestType.DELETE_CHARACTERISTIC_FROM_CLOUD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$huawei$profile$coordinator$ProfileRequestType[ProfileRequestType.GET_DEVICES_FROM_CLOUD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$huawei$profile$coordinator$ProfileRequestType[ProfileRequestType.GET_SERVICES_FROM_CLOUD.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$huawei$profile$coordinator$ProfileRequestType[ProfileRequestType.GET_SERVICES_CHARACTERISTIC_FROM_CLOUD.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$huawei$profile$coordinator$ProfileRequestType[ProfileRequestType.DELETE_DEVICE_FROM_CLOUD.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public static ProfileRequestInterface getInstance(ProfileRequestType profileRequestType, ProfileRequestInputParams profileRequestInputParams) {
        switch (AnonymousClass1.$SwitchMap$com$huawei$profile$coordinator$ProfileRequestType[profileRequestType.ordinal()]) {
            case 1:
                if (TextUtils.isEmpty(profileRequestInputParams.getLocalDevId())) {
                    return null;
                }
                String cloudDevId = profileRequestInputParams.getCloudDevId();
                if (TextUtils.isEmpty(cloudDevId)) {
                    DsLog.it(TAG, "RegisterDeviceToCloud", new Object[0]);
                    return new RegisterDeviceToCloudSdk();
                }
                DsLog.it(TAG, "UpdateDeviceToCloud", new Object[0]);
                profileRequestInputParams.setCloudDevId(cloudDevId);
                return new UpdateDeviceToCloudSdk();
            case 2:
            case 3:
                return new PutServiceOfDeviceToCloudSdk();
            case 4:
            case 5:
                return new PutCharacteristicToCloudSdk();
            case 6:
                return new GetDevicesFromCloudSdk();
            case 7:
                return new GetServicesOfDeviceFromCloudSdk();
            case 8:
                return new GetCharacteristicsFromCloudSdk();
            case 9:
                return new DeleteDeviceFromCloudSdk();
            default:
                DsLog.et(TAG, "Illegal request type!", new Object[0]);
                return null;
        }
    }
}
