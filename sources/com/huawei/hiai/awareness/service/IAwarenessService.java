package com.huawei.hiai.awareness.service;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.hiai.awareness.client.AwarenessEnvelope;
import com.huawei.hiai.awareness.client.OnEnvelopeReceiver;
import com.huawei.hiai.awareness.client.OnResultListener;
import com.huawei.hiai.awareness.service.IAwarenessListener;
import com.huawei.hiai.awareness.service.IRequestCallBack;
import java.util.List;

/* loaded from: classes4.dex */
public interface IAwarenessService extends IInterface {

    /* loaded from: classes8.dex */
    public static class Default implements IAwarenessService {
        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean accept(AwarenessEnvelope awarenessEnvelope) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean acceptBatch(List<AwarenessEnvelope> list, OnResultListener onResultListener) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean checkSdkVersion(int i) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean checkServerVersion(int i) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public String getAwarenessApiVersion() throws RemoteException {
            return null;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public RequestResult getCurrentAwareness(int i, boolean z, Bundle bundle, String str) throws RemoteException {
            return null;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public RequestResult getCurrentStatus(int i) throws RemoteException {
            return null;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public RequestResult getExtendFenceTriggerResult(ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return null;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public RequestResult getFenceTriggerResult(AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return null;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public RequestResult getSupportAwarenessCapability(int i) throws RemoteException {
            return null;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean isIntegrateSensorHub() throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean ohosAccept(AwarenessEnvelope awarenessEnvelope) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean ohosAcceptBatch(List<AwarenessEnvelope> list, OnEnvelopeReceiver onEnvelopeReceiver, OnResultListener onResultListener) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean ohosAcceptEx(AwarenessEnvelope awarenessEnvelope, OnEnvelopeReceiver onEnvelopeReceiver) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean ohosAcceptExEx(AwarenessEnvelope awarenessEnvelope, OnEnvelopeReceiver onEnvelopeReceiver, OnResultListener onResultListener) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean registerAppLifeChangeFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean registerAppUseTotalTimeFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean registerAwarenessListener(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, IAwarenessListener iAwarenessListener) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean registerBroadcastEventFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean registerCustomLocationFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean registerDatabaseMonitorFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean registerDeviceStatusFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean registerDeviceUseTotalTimeFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean registerLocationFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean registerMapInfoReportFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean registerMotionFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean registerMovementFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean registerOneAppContinuousUseTimeFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean registerScreenUnlockFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean registerScreenUnlockTotalNumberFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean registerSwingFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean registerTimeFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean setClientInfo(String str, Bundle bundle) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public RequestResult setReportPeriod(ExtendAwarenessFence extendAwarenessFence) throws RemoteException {
            return null;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public int setSwingController(int i) throws RemoteException {
            return 0;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean unRegisterAwarenessListener(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, IAwarenessListener iAwarenessListener) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean unRegisterExtendFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }

        @Override // com.huawei.hiai.awareness.service.IAwarenessService
        public boolean unRegisterFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }
    }

    boolean accept(AwarenessEnvelope awarenessEnvelope) throws RemoteException;

    boolean acceptBatch(List<AwarenessEnvelope> list, OnResultListener onResultListener) throws RemoteException;

    boolean checkSdkVersion(int i) throws RemoteException;

    boolean checkServerVersion(int i) throws RemoteException;

    String getAwarenessApiVersion() throws RemoteException;

    RequestResult getCurrentAwareness(int i, boolean z, Bundle bundle, String str) throws RemoteException;

    RequestResult getCurrentStatus(int i) throws RemoteException;

    RequestResult getExtendFenceTriggerResult(ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    RequestResult getFenceTriggerResult(AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    RequestResult getSupportAwarenessCapability(int i) throws RemoteException;

    boolean isIntegrateSensorHub() throws RemoteException;

    boolean ohosAccept(AwarenessEnvelope awarenessEnvelope) throws RemoteException;

    boolean ohosAcceptBatch(List<AwarenessEnvelope> list, OnEnvelopeReceiver onEnvelopeReceiver, OnResultListener onResultListener) throws RemoteException;

    boolean ohosAcceptEx(AwarenessEnvelope awarenessEnvelope, OnEnvelopeReceiver onEnvelopeReceiver) throws RemoteException;

    boolean ohosAcceptExEx(AwarenessEnvelope awarenessEnvelope, OnEnvelopeReceiver onEnvelopeReceiver, OnResultListener onResultListener) throws RemoteException;

    boolean registerAppLifeChangeFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    boolean registerAppUseTotalTimeFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    boolean registerAwarenessListener(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, IAwarenessListener iAwarenessListener) throws RemoteException;

    boolean registerBroadcastEventFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    boolean registerCustomLocationFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    boolean registerDatabaseMonitorFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    boolean registerDeviceStatusFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    boolean registerDeviceUseTotalTimeFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    boolean registerLocationFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    boolean registerMapInfoReportFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    boolean registerMotionFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    boolean registerMovementFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    boolean registerOneAppContinuousUseTimeFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    boolean registerScreenUnlockFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    boolean registerScreenUnlockTotalNumberFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    boolean registerSwingFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    boolean registerTimeFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    boolean setClientInfo(String str, Bundle bundle) throws RemoteException;

    RequestResult setReportPeriod(ExtendAwarenessFence extendAwarenessFence) throws RemoteException;

    int setSwingController(int i) throws RemoteException;

    boolean unRegisterAwarenessListener(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, IAwarenessListener iAwarenessListener) throws RemoteException;

    boolean unRegisterExtendFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    boolean unRegisterFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    public static abstract class Stub extends Binder implements IAwarenessService {
        private static final String DESCRIPTOR = "com.huawei.hiai.awareness.service.IAwarenessService";
        static final int TRANSACTION_accept = 33;
        static final int TRANSACTION_acceptBatch = 37;
        static final int TRANSACTION_checkSdkVersion = 32;
        static final int TRANSACTION_checkServerVersion = 12;
        static final int TRANSACTION_getAwarenessApiVersion = 13;
        static final int TRANSACTION_getCurrentAwareness = 14;
        static final int TRANSACTION_getCurrentStatus = 1;
        static final int TRANSACTION_getExtendFenceTriggerResult = 18;
        static final int TRANSACTION_getFenceTriggerResult = 11;
        static final int TRANSACTION_getSupportAwarenessCapability = 19;
        static final int TRANSACTION_isIntegrateSensorHub = 23;
        static final int TRANSACTION_ohosAccept = 34;
        static final int TRANSACTION_ohosAcceptBatch = 38;
        static final int TRANSACTION_ohosAcceptEx = 35;
        static final int TRANSACTION_ohosAcceptExEx = 36;
        static final int TRANSACTION_registerAppLifeChangeFence = 24;
        static final int TRANSACTION_registerAppUseTotalTimeFence = 5;
        static final int TRANSACTION_registerAwarenessListener = 27;
        static final int TRANSACTION_registerBroadcastEventFence = 16;
        static final int TRANSACTION_registerCustomLocationFence = 15;
        static final int TRANSACTION_registerDatabaseMonitorFence = 31;
        static final int TRANSACTION_registerDeviceStatusFence = 21;
        static final int TRANSACTION_registerDeviceUseTotalTimeFence = 7;
        static final int TRANSACTION_registerLocationFence = 4;
        static final int TRANSACTION_registerMapInfoReportFence = 30;
        static final int TRANSACTION_registerMotionFence = 2;
        static final int TRANSACTION_registerMovementFence = 20;
        static final int TRANSACTION_registerOneAppContinuousUseTimeFence = 6;
        static final int TRANSACTION_registerScreenUnlockFence = 9;
        static final int TRANSACTION_registerScreenUnlockTotalNumberFence = 8;
        static final int TRANSACTION_registerSwingFence = 25;
        static final int TRANSACTION_registerTimeFence = 3;
        static final int TRANSACTION_setClientInfo = 29;
        static final int TRANSACTION_setReportPeriod = 22;
        static final int TRANSACTION_setSwingController = 26;
        static final int TRANSACTION_unRegisterAwarenessListener = 28;
        static final int TRANSACTION_unRegisterExtendFence = 17;
        static final int TRANSACTION_unRegisterFence = 10;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAwarenessService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAwarenessService)) {
                return (IAwarenessService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    RequestResult currentStatus = getCurrentStatus(parcel.readInt());
                    parcel2.writeNoException();
                    if (currentStatus != null) {
                        parcel2.writeInt(1);
                        currentStatus.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerMotionFence = registerMotionFence(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? AwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(registerMotionFence ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerTimeFence = registerTimeFence(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? AwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(registerTimeFence ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerLocationFence = registerLocationFence(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? AwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(registerLocationFence ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerAppUseTotalTimeFence = registerAppUseTotalTimeFence(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? AwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(registerAppUseTotalTimeFence ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerOneAppContinuousUseTimeFence = registerOneAppContinuousUseTimeFence(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? AwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(registerOneAppContinuousUseTimeFence ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerDeviceUseTotalTimeFence = registerDeviceUseTotalTimeFence(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? AwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(registerDeviceUseTotalTimeFence ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerScreenUnlockTotalNumberFence = registerScreenUnlockTotalNumberFence(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? AwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(registerScreenUnlockTotalNumberFence ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerScreenUnlockFence = registerScreenUnlockFence(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? AwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(registerScreenUnlockFence ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean unRegisterFence = unRegisterFence(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? AwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(unRegisterFence ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    RequestResult fenceTriggerResult = getFenceTriggerResult(parcel.readInt() != 0 ? AwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (fenceTriggerResult != null) {
                        parcel2.writeInt(1);
                        fenceTriggerResult.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean checkServerVersion = checkServerVersion(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(checkServerVersion ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    String awarenessApiVersion = getAwarenessApiVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(awarenessApiVersion);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    RequestResult currentAwareness = getCurrentAwareness(parcel.readInt(), parcel.readInt() != 0, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    if (currentAwareness != null) {
                        parcel2.writeInt(1);
                        currentAwareness.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerCustomLocationFence = registerCustomLocationFence(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? ExtendAwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(registerCustomLocationFence ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerBroadcastEventFence = registerBroadcastEventFence(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? ExtendAwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(registerBroadcastEventFence ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean unRegisterExtendFence = unRegisterExtendFence(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? ExtendAwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(unRegisterExtendFence ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    RequestResult extendFenceTriggerResult = getExtendFenceTriggerResult(parcel.readInt() != 0 ? ExtendAwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (extendFenceTriggerResult != null) {
                        parcel2.writeInt(1);
                        extendFenceTriggerResult.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    RequestResult supportAwarenessCapability = getSupportAwarenessCapability(parcel.readInt());
                    parcel2.writeNoException();
                    if (supportAwarenessCapability != null) {
                        parcel2.writeInt(1);
                        supportAwarenessCapability.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerMovementFence = registerMovementFence(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? ExtendAwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(registerMovementFence ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerDeviceStatusFence = registerDeviceStatusFence(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? ExtendAwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(registerDeviceStatusFence ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    RequestResult reportPeriod = setReportPeriod(parcel.readInt() != 0 ? ExtendAwarenessFence.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (reportPeriod != null) {
                        parcel2.writeInt(1);
                        reportPeriod.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isIntegrateSensorHub = isIntegrateSensorHub();
                    parcel2.writeNoException();
                    parcel2.writeInt(isIntegrateSensorHub ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerAppLifeChangeFence = registerAppLifeChangeFence(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? ExtendAwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(registerAppLifeChangeFence ? 1 : 0);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerSwingFence = registerSwingFence(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? ExtendAwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(registerSwingFence ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    int swingController = setSwingController(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(swingController);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerAwarenessListener = registerAwarenessListener(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? ExtendAwarenessFence.CREATOR.createFromParcel(parcel) : null, IAwarenessListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(registerAwarenessListener ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean unRegisterAwarenessListener = unRegisterAwarenessListener(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? ExtendAwarenessFence.CREATOR.createFromParcel(parcel) : null, IAwarenessListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(unRegisterAwarenessListener ? 1 : 0);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean clientInfo = setClientInfo(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(clientInfo ? 1 : 0);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerMapInfoReportFence = registerMapInfoReportFence(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? ExtendAwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(registerMapInfoReportFence ? 1 : 0);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerDatabaseMonitorFence = registerDatabaseMonitorFence(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? ExtendAwarenessFence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(registerDatabaseMonitorFence ? 1 : 0);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean checkSdkVersion = checkSdkVersion(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(checkSdkVersion ? 1 : 0);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean accept = accept(parcel.readInt() != 0 ? AwarenessEnvelope.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(accept ? 1 : 0);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean ohosAccept = ohosAccept(parcel.readInt() != 0 ? AwarenessEnvelope.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(ohosAccept ? 1 : 0);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean ohosAcceptEx = ohosAcceptEx(parcel.readInt() != 0 ? AwarenessEnvelope.CREATOR.createFromParcel(parcel) : null, OnEnvelopeReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(ohosAcceptEx ? 1 : 0);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean ohosAcceptExEx = ohosAcceptExEx(parcel.readInt() != 0 ? AwarenessEnvelope.CREATOR.createFromParcel(parcel) : null, OnEnvelopeReceiver.Stub.asInterface(parcel.readStrongBinder()), OnResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(ohosAcceptExEx ? 1 : 0);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean acceptBatch = acceptBatch(parcel.createTypedArrayList(AwarenessEnvelope.CREATOR), OnResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(acceptBatch ? 1 : 0);
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean ohosAcceptBatch = ohosAcceptBatch(parcel.createTypedArrayList(AwarenessEnvelope.CREATOR), OnEnvelopeReceiver.Stub.asInterface(parcel.readStrongBinder()), OnResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(ohosAcceptBatch ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class Proxy implements IAwarenessService {
            public static IAwarenessService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public RequestResult getCurrentStatus(int i) throws RemoteException {
                RequestResult createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getCurrentStatus(i);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? RequestResult.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean registerMotionFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (awarenessFence != null) {
                        obtain.writeInt(1);
                        awarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerMotionFence(iRequestCallBack, awarenessFence, bundle, pendingIntent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean registerTimeFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (awarenessFence != null) {
                        obtain.writeInt(1);
                        awarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerTimeFence(iRequestCallBack, awarenessFence, bundle, pendingIntent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean registerLocationFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (awarenessFence != null) {
                        obtain.writeInt(1);
                        awarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerLocationFence(iRequestCallBack, awarenessFence, bundle, pendingIntent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean registerAppUseTotalTimeFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (awarenessFence != null) {
                        obtain.writeInt(1);
                        awarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerAppUseTotalTimeFence(iRequestCallBack, awarenessFence, bundle, pendingIntent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean registerOneAppContinuousUseTimeFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (awarenessFence != null) {
                        obtain.writeInt(1);
                        awarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerOneAppContinuousUseTimeFence(iRequestCallBack, awarenessFence, bundle, pendingIntent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean registerDeviceUseTotalTimeFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (awarenessFence != null) {
                        obtain.writeInt(1);
                        awarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerDeviceUseTotalTimeFence(iRequestCallBack, awarenessFence, bundle, pendingIntent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean registerScreenUnlockTotalNumberFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (awarenessFence != null) {
                        obtain.writeInt(1);
                        awarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerScreenUnlockTotalNumberFence(iRequestCallBack, awarenessFence, bundle, pendingIntent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean registerScreenUnlockFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (awarenessFence != null) {
                        obtain.writeInt(1);
                        awarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerScreenUnlockFence(iRequestCallBack, awarenessFence, bundle, pendingIntent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean unRegisterFence(IRequestCallBack iRequestCallBack, AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (awarenessFence != null) {
                        obtain.writeInt(1);
                        awarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unRegisterFence(iRequestCallBack, awarenessFence, bundle, pendingIntent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public RequestResult getFenceTriggerResult(AwarenessFence awarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                RequestResult createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (awarenessFence != null) {
                        obtain.writeInt(1);
                        awarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getFenceTriggerResult(awarenessFence, bundle, pendingIntent);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? RequestResult.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean checkServerVersion(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkServerVersion(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public String getAwarenessApiVersion() throws RemoteException {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getAwarenessApiVersion();
                    } else {
                        obtain2.readException();
                        readString = obtain2.readString();
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public RequestResult getCurrentAwareness(int i, boolean z, Bundle bundle, String str) throws RemoteException {
                RequestResult createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getCurrentAwareness(i, z, bundle, str);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? RequestResult.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean registerCustomLocationFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (extendAwarenessFence != null) {
                        obtain.writeInt(1);
                        extendAwarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCustomLocationFence(iRequestCallBack, extendAwarenessFence, bundle, pendingIntent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean registerBroadcastEventFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (extendAwarenessFence != null) {
                        obtain.writeInt(1);
                        extendAwarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerBroadcastEventFence(iRequestCallBack, extendAwarenessFence, bundle, pendingIntent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean unRegisterExtendFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (extendAwarenessFence != null) {
                        obtain.writeInt(1);
                        extendAwarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unRegisterExtendFence(iRequestCallBack, extendAwarenessFence, bundle, pendingIntent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public RequestResult getExtendFenceTriggerResult(ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                RequestResult createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (extendAwarenessFence != null) {
                        obtain.writeInt(1);
                        extendAwarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getExtendFenceTriggerResult(extendAwarenessFence, bundle, pendingIntent);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? RequestResult.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public RequestResult getSupportAwarenessCapability(int i) throws RemoteException {
                RequestResult createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getSupportAwarenessCapability(i);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? RequestResult.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean registerMovementFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (extendAwarenessFence != null) {
                        obtain.writeInt(1);
                        extendAwarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerMovementFence(iRequestCallBack, extendAwarenessFence, bundle, pendingIntent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean registerDeviceStatusFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (extendAwarenessFence != null) {
                        obtain.writeInt(1);
                        extendAwarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerDeviceStatusFence(iRequestCallBack, extendAwarenessFence, bundle, pendingIntent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public RequestResult setReportPeriod(ExtendAwarenessFence extendAwarenessFence) throws RemoteException {
                RequestResult createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (extendAwarenessFence != null) {
                        obtain.writeInt(1);
                        extendAwarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().setReportPeriod(extendAwarenessFence);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? RequestResult.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean isIntegrateSensorHub() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isIntegrateSensorHub();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean registerAppLifeChangeFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (extendAwarenessFence != null) {
                        obtain.writeInt(1);
                        extendAwarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(24, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerAppLifeChangeFence(iRequestCallBack, extendAwarenessFence, bundle, pendingIntent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean registerSwingFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (extendAwarenessFence != null) {
                        obtain.writeInt(1);
                        extendAwarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(25, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerSwingFence(iRequestCallBack, extendAwarenessFence, bundle, pendingIntent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public int setSwingController(int i) throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(26, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().setSwingController(i);
                    } else {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean registerAwarenessListener(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, IAwarenessListener iAwarenessListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (extendAwarenessFence != null) {
                        obtain.writeInt(1);
                        extendAwarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iAwarenessListener != null ? iAwarenessListener.asBinder() : null);
                    if (!this.mRemote.transact(27, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerAwarenessListener(iRequestCallBack, extendAwarenessFence, iAwarenessListener);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean unRegisterAwarenessListener(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, IAwarenessListener iAwarenessListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (extendAwarenessFence != null) {
                        obtain.writeInt(1);
                        extendAwarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iAwarenessListener != null ? iAwarenessListener.asBinder() : null);
                    if (!this.mRemote.transact(28, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unRegisterAwarenessListener(iRequestCallBack, extendAwarenessFence, iAwarenessListener);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean setClientInfo(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(29, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setClientInfo(str, bundle);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean registerMapInfoReportFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (extendAwarenessFence != null) {
                        obtain.writeInt(1);
                        extendAwarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(30, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerMapInfoReportFence(iRequestCallBack, extendAwarenessFence, bundle, pendingIntent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean registerDatabaseMonitorFence(IRequestCallBack iRequestCallBack, ExtendAwarenessFence extendAwarenessFence, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (extendAwarenessFence != null) {
                        obtain.writeInt(1);
                        extendAwarenessFence.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(31, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerDatabaseMonitorFence(iRequestCallBack, extendAwarenessFence, bundle, pendingIntent);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean checkSdkVersion(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(32, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkSdkVersion(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean accept(AwarenessEnvelope awarenessEnvelope) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (awarenessEnvelope != null) {
                        obtain.writeInt(1);
                        awarenessEnvelope.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(33, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().accept(awarenessEnvelope);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean ohosAccept(AwarenessEnvelope awarenessEnvelope) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (awarenessEnvelope != null) {
                        obtain.writeInt(1);
                        awarenessEnvelope.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(34, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().ohosAccept(awarenessEnvelope);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean ohosAcceptEx(AwarenessEnvelope awarenessEnvelope, OnEnvelopeReceiver onEnvelopeReceiver) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (awarenessEnvelope != null) {
                        obtain.writeInt(1);
                        awarenessEnvelope.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(onEnvelopeReceiver != null ? onEnvelopeReceiver.asBinder() : null);
                    if (!this.mRemote.transact(35, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().ohosAcceptEx(awarenessEnvelope, onEnvelopeReceiver);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean ohosAcceptExEx(AwarenessEnvelope awarenessEnvelope, OnEnvelopeReceiver onEnvelopeReceiver, OnResultListener onResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (awarenessEnvelope != null) {
                        obtain.writeInt(1);
                        awarenessEnvelope.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(onEnvelopeReceiver != null ? onEnvelopeReceiver.asBinder() : null);
                    obtain.writeStrongBinder(onResultListener != null ? onResultListener.asBinder() : null);
                    if (!this.mRemote.transact(36, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().ohosAcceptExEx(awarenessEnvelope, onEnvelopeReceiver, onResultListener);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean acceptBatch(List<AwarenessEnvelope> list, OnResultListener onResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    obtain.writeStrongBinder(onResultListener != null ? onResultListener.asBinder() : null);
                    if (!this.mRemote.transact(37, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().acceptBatch(list, onResultListener);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hiai.awareness.service.IAwarenessService
            public boolean ohosAcceptBatch(List<AwarenessEnvelope> list, OnEnvelopeReceiver onEnvelopeReceiver, OnResultListener onResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    obtain.writeStrongBinder(onEnvelopeReceiver != null ? onEnvelopeReceiver.asBinder() : null);
                    obtain.writeStrongBinder(onResultListener != null ? onResultListener.asBinder() : null);
                    if (!this.mRemote.transact(38, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().ohosAcceptBatch(list, onEnvelopeReceiver, onResultListener);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }
        }

        public static boolean setDefaultImpl(IAwarenessService iAwarenessService) {
            if (Proxy.sDefaultImpl != null || iAwarenessService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAwarenessService;
            return true;
        }

        public static IAwarenessService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
