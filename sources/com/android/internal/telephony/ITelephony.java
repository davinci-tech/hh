package com.android.internal.telephony;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.telephony.NeighboringCellInfo;
import java.util.List;

/* loaded from: classes2.dex */
public interface ITelephony extends IInterface {

    /* loaded from: classes8.dex */
    public static class Default implements ITelephony {
        @Override // com.android.internal.telephony.ITelephony
        public void answerRingingCall() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void call(String str) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void cancelMissedCallsNotification() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void dial(String str) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int disableApnType(String str) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean disableDataConnectivity() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void disableLocationUpdates() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int enableApnType(String str) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean enableDataConnectivity() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void enableLocationUpdates() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean endCall() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean endCallForSubscriber(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getActivePhoneType() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCallState() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCdmaEriIconIndex() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCdmaEriIconMode() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getCdmaEriText() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public Bundle getCellLocation() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getDataActivity() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getDataState() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getLteOnCdmaMode() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<NeighboringCellInfo> getNeighboringCellInfo() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getNetworkType() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getVoiceMessageCount() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean handlePinMmi(String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean hasIccCard() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isDataConnectivityPossible() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isIdle() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isOffhook() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isRadioOn() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isRinging() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isSimPinEnabled() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean needsOtaServiceProvisioning() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setRadio(boolean z) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean showCallScreen() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean showCallScreenWithDialpad(boolean z) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void silenceRinger() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean supplyPin(String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean supplyPuk(String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void toggleRadioOnOff() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void updateServiceLocation() throws RemoteException {
        }
    }

    void answerRingingCall() throws RemoteException;

    void call(String str) throws RemoteException;

    void cancelMissedCallsNotification() throws RemoteException;

    void dial(String str) throws RemoteException;

    int disableApnType(String str) throws RemoteException;

    boolean disableDataConnectivity() throws RemoteException;

    void disableLocationUpdates() throws RemoteException;

    int enableApnType(String str) throws RemoteException;

    boolean enableDataConnectivity() throws RemoteException;

    void enableLocationUpdates() throws RemoteException;

    boolean endCall() throws RemoteException;

    boolean endCallForSubscriber(int i) throws RemoteException;

    int getActivePhoneType() throws RemoteException;

    int getCallState() throws RemoteException;

    int getCdmaEriIconIndex() throws RemoteException;

    int getCdmaEriIconMode() throws RemoteException;

    String getCdmaEriText() throws RemoteException;

    Bundle getCellLocation() throws RemoteException;

    int getDataActivity() throws RemoteException;

    int getDataState() throws RemoteException;

    int getLteOnCdmaMode() throws RemoteException;

    List<NeighboringCellInfo> getNeighboringCellInfo() throws RemoteException;

    int getNetworkType() throws RemoteException;

    int getVoiceMessageCount() throws RemoteException;

    boolean handlePinMmi(String str) throws RemoteException;

    boolean hasIccCard() throws RemoteException;

    boolean isDataConnectivityPossible() throws RemoteException;

    boolean isIdle() throws RemoteException;

    boolean isOffhook() throws RemoteException;

    boolean isRadioOn() throws RemoteException;

    boolean isRinging() throws RemoteException;

    boolean isSimPinEnabled() throws RemoteException;

    boolean needsOtaServiceProvisioning() throws RemoteException;

    boolean setRadio(boolean z) throws RemoteException;

    boolean showCallScreen() throws RemoteException;

    boolean showCallScreenWithDialpad(boolean z) throws RemoteException;

    void silenceRinger() throws RemoteException;

    boolean supplyPin(String str) throws RemoteException;

    boolean supplyPuk(String str, String str2) throws RemoteException;

    void toggleRadioOnOff() throws RemoteException;

    void updateServiceLocation() throws RemoteException;

    /* loaded from: classes8.dex */
    public static abstract class Stub extends Binder implements ITelephony {
        public static final String DESCRIPTOR = "com.android.internal.telephony.ITelephony";
        static final int TRANSACTION_answerRingingCall = 7;
        static final int TRANSACTION_call = 3;
        static final int TRANSACTION_cancelMissedCallsNotification = 9;
        static final int TRANSACTION_dial = 2;
        static final int TRANSACTION_disableApnType = 30;
        static final int TRANSACTION_disableDataConnectivity = 8;
        static final int TRANSACTION_disableLocationUpdates = 14;
        static final int TRANSACTION_enableApnType = 15;
        static final int TRANSACTION_enableDataConnectivity = 36;
        static final int TRANSACTION_enableLocationUpdates = 29;
        static final int TRANSACTION_endCall = 41;
        static final int TRANSACTION_endCallForSubscriber = 39;
        static final int TRANSACTION_getActivePhoneType = 22;
        static final int TRANSACTION_getCallState = 19;
        static final int TRANSACTION_getCdmaEriIconIndex = 23;
        static final int TRANSACTION_getCdmaEriIconMode = 24;
        static final int TRANSACTION_getCdmaEriText = 25;
        static final int TRANSACTION_getCellLocation = 17;
        static final int TRANSACTION_getDataActivity = 20;
        static final int TRANSACTION_getDataState = 21;
        static final int TRANSACTION_getLteOnCdmaMode = 6;
        static final int TRANSACTION_getNeighboringCellInfo = 18;
        static final int TRANSACTION_getNetworkType = 38;
        static final int TRANSACTION_getVoiceMessageCount = 37;
        static final int TRANSACTION_handlePinMmi = 10;
        static final int TRANSACTION_hasIccCard = 5;
        static final int TRANSACTION_isDataConnectivityPossible = 16;
        static final int TRANSACTION_isIdle = 4;
        static final int TRANSACTION_isOffhook = 31;
        static final int TRANSACTION_isRadioOn = 11;
        static final int TRANSACTION_isRinging = 33;
        static final int TRANSACTION_isSimPinEnabled = 35;
        static final int TRANSACTION_needsOtaServiceProvisioning = 26;
        static final int TRANSACTION_setRadio = 27;
        static final int TRANSACTION_showCallScreen = 1;
        static final int TRANSACTION_showCallScreenWithDialpad = 40;
        static final int TRANSACTION_silenceRinger = 32;
        static final int TRANSACTION_supplyPin = 34;
        static final int TRANSACTION_supplyPuk = 13;
        static final int TRANSACTION_toggleRadioOnOff = 12;
        static final int TRANSACTION_updateServiceLocation = 28;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITelephony asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ITelephony)) {
                return (ITelephony) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    boolean showCallScreen = showCallScreen();
                    parcel2.writeNoException();
                    parcel2.writeInt(showCallScreen ? 1 : 0);
                    return true;
                case 2:
                    dial(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    call(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    boolean isIdle = isIdle();
                    parcel2.writeNoException();
                    parcel2.writeInt(isIdle ? 1 : 0);
                    return true;
                case 5:
                    boolean hasIccCard = hasIccCard();
                    parcel2.writeNoException();
                    parcel2.writeInt(hasIccCard ? 1 : 0);
                    return true;
                case 6:
                    int lteOnCdmaMode = getLteOnCdmaMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(lteOnCdmaMode);
                    return true;
                case 7:
                    answerRingingCall();
                    parcel2.writeNoException();
                    return true;
                case 8:
                    boolean disableDataConnectivity = disableDataConnectivity();
                    parcel2.writeNoException();
                    parcel2.writeInt(disableDataConnectivity ? 1 : 0);
                    return true;
                case 9:
                    cancelMissedCallsNotification();
                    parcel2.writeNoException();
                    return true;
                case 10:
                    boolean handlePinMmi = handlePinMmi(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(handlePinMmi ? 1 : 0);
                    return true;
                case 11:
                    boolean isRadioOn = isRadioOn();
                    parcel2.writeNoException();
                    parcel2.writeInt(isRadioOn ? 1 : 0);
                    return true;
                case 12:
                    toggleRadioOnOff();
                    parcel2.writeNoException();
                    return true;
                case 13:
                    boolean supplyPuk = supplyPuk(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(supplyPuk ? 1 : 0);
                    return true;
                case 14:
                    disableLocationUpdates();
                    parcel2.writeNoException();
                    return true;
                case 15:
                    int enableApnType = enableApnType(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(enableApnType);
                    return true;
                case 16:
                    boolean isDataConnectivityPossible = isDataConnectivityPossible();
                    parcel2.writeNoException();
                    parcel2.writeInt(isDataConnectivityPossible ? 1 : 0);
                    return true;
                case 17:
                    Bundle cellLocation = getCellLocation();
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, cellLocation, 1);
                    return true;
                case 18:
                    List<NeighboringCellInfo> neighboringCellInfo = getNeighboringCellInfo();
                    parcel2.writeNoException();
                    _Parcel.writeTypedList(parcel2, neighboringCellInfo, 1);
                    return true;
                case 19:
                    int callState = getCallState();
                    parcel2.writeNoException();
                    parcel2.writeInt(callState);
                    return true;
                case 20:
                    int dataActivity = getDataActivity();
                    parcel2.writeNoException();
                    parcel2.writeInt(dataActivity);
                    return true;
                case 21:
                    int dataState = getDataState();
                    parcel2.writeNoException();
                    parcel2.writeInt(dataState);
                    return true;
                case 22:
                    int activePhoneType = getActivePhoneType();
                    parcel2.writeNoException();
                    parcel2.writeInt(activePhoneType);
                    return true;
                case 23:
                    int cdmaEriIconIndex = getCdmaEriIconIndex();
                    parcel2.writeNoException();
                    parcel2.writeInt(cdmaEriIconIndex);
                    return true;
                case 24:
                    int cdmaEriIconMode = getCdmaEriIconMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(cdmaEriIconMode);
                    return true;
                case 25:
                    String cdmaEriText = getCdmaEriText();
                    parcel2.writeNoException();
                    parcel2.writeString(cdmaEriText);
                    return true;
                case 26:
                    boolean needsOtaServiceProvisioning = needsOtaServiceProvisioning();
                    parcel2.writeNoException();
                    parcel2.writeInt(needsOtaServiceProvisioning ? 1 : 0);
                    return true;
                case 27:
                    boolean radio = setRadio(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(radio ? 1 : 0);
                    return true;
                case 28:
                    updateServiceLocation();
                    parcel2.writeNoException();
                    return true;
                case 29:
                    enableLocationUpdates();
                    parcel2.writeNoException();
                    return true;
                case 30:
                    int disableApnType = disableApnType(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(disableApnType);
                    return true;
                case 31:
                    boolean isOffhook = isOffhook();
                    parcel2.writeNoException();
                    parcel2.writeInt(isOffhook ? 1 : 0);
                    return true;
                case 32:
                    silenceRinger();
                    parcel2.writeNoException();
                    return true;
                case 33:
                    boolean isRinging = isRinging();
                    parcel2.writeNoException();
                    parcel2.writeInt(isRinging ? 1 : 0);
                    return true;
                case 34:
                    boolean supplyPin = supplyPin(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(supplyPin ? 1 : 0);
                    return true;
                case 35:
                    boolean isSimPinEnabled = isSimPinEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(isSimPinEnabled ? 1 : 0);
                    return true;
                case 36:
                    boolean enableDataConnectivity = enableDataConnectivity();
                    parcel2.writeNoException();
                    parcel2.writeInt(enableDataConnectivity ? 1 : 0);
                    return true;
                case 37:
                    int voiceMessageCount = getVoiceMessageCount();
                    parcel2.writeNoException();
                    parcel2.writeInt(voiceMessageCount);
                    return true;
                case 38:
                    int networkType = getNetworkType();
                    parcel2.writeNoException();
                    parcel2.writeInt(networkType);
                    return true;
                case 39:
                    boolean endCallForSubscriber = endCallForSubscriber(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(endCallForSubscriber ? 1 : 0);
                    return true;
                case 40:
                    boolean showCallScreenWithDialpad = showCallScreenWithDialpad(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(showCallScreenWithDialpad ? 1 : 0);
                    return true;
                case 41:
                    boolean endCall = endCall();
                    parcel2.writeNoException();
                    parcel2.writeInt(endCall ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class Proxy implements ITelephony {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean showCallScreen() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void dial(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void call(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isIdle() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean hasIccCard() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getLteOnCdmaMode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void answerRingingCall() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean disableDataConnectivity() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void cancelMissedCallsNotification() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean handlePinMmi(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isRadioOn() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void toggleRadioOnOff() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean supplyPuk(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void disableLocationUpdates() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int enableApnType(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isDataConnectivityPossible() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public Bundle getCellLocation() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return (Bundle) _Parcel.readTypedObject(obtain2, Bundle.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<NeighboringCellInfo> getNeighboringCellInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(NeighboringCellInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCallState() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getDataActivity() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getDataState() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getActivePhoneType() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCdmaEriIconIndex() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCdmaEriIconMode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getCdmaEriText() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean needsOtaServiceProvisioning() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setRadio(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void updateServiceLocation() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void enableLocationUpdates() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int disableApnType(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isOffhook() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void silenceRinger() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isRinging() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean supplyPin(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isSimPinEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean enableDataConnectivity() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getVoiceMessageCount() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getNetworkType() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean endCallForSubscriber(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean showCallScreenWithDialpad(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean endCall() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(41, obtain, obtain2, 0);
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
    }

    /* loaded from: classes8.dex */
    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedList(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                writeTypedObject(parcel, list.get(i2), i);
            }
        }
    }
}
