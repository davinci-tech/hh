package com.huawei.android.airsharing.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.android.airsharing.api.GrabState;
import com.huawei.android.airsharing.api.PlayInfo;
import com.huawei.android.airsharing.api.PlayInfoHolder;
import com.huawei.android.airsharing.api.PlayerCapability;
import com.huawei.android.airsharing.api.TrackInfo;
import com.huawei.android.airsharing.api.TrackInfoSet;
import com.huawei.android.airsharing.client.IRemotePlayerListener;

/* loaded from: classes2.dex */
public interface IRemotePlayController extends IInterface {
    boolean addMediaItem(PlayInfo playInfo) throws RemoteException;

    boolean configGrabState(int i, long j) throws RemoteException;

    boolean fastForward(int i) throws RemoteException;

    boolean fastRewind(int i) throws RemoteException;

    int getBufferedPosition() throws RemoteException;

    GrabState getCurrentGrabState() throws RemoteException;

    int getDuration() throws RemoteException;

    float getPlaySpeed() throws RemoteException;

    PlayerCapability getPlayerCapability() throws RemoteException;

    int getPlayerStatus() throws RemoteException;

    int getPosition() throws RemoteException;

    int getRepeatMode() throws RemoteException;

    TrackInfoSet getTrackInfoSet() throws RemoteException;

    int getVolume() throws RemoteException;

    boolean isMute() throws RemoteException;

    boolean next() throws RemoteException;

    boolean pause() throws RemoteException;

    boolean play(PlayInfoHolder playInfoHolder) throws RemoteException;

    boolean previous() throws RemoteException;

    boolean provideKeyResponse(String str, byte[] bArr) throws RemoteException;

    boolean registerRemotePlayerListener(IRemotePlayerListener iRemotePlayerListener) throws RemoteException;

    boolean resume() throws RemoteException;

    boolean seek(int i) throws RemoteException;

    boolean setAudioTrackInfo(TrackInfo trackInfo) throws RemoteException;

    boolean setMute(boolean z) throws RemoteException;

    boolean setRepeatMode(int i) throws RemoteException;

    boolean setSpeed(float f) throws RemoteException;

    boolean setSubtitleTrackInfo(TrackInfo trackInfo) throws RemoteException;

    boolean setVideoTrackInfo(TrackInfo trackInfo) throws RemoteException;

    boolean setVolume(int i) throws RemoteException;

    boolean stop() throws RemoteException;

    boolean unregisterRemotePlayerListener() throws RemoteException;

    public static abstract class Stub extends Binder implements IRemotePlayController {
        private static final String DESCRIPTOR = "com.huawei.android.airsharing.client.IRemotePlayController";
        static final int TRANSACTION_addMediaItem = 7;
        static final int TRANSACTION_configGrabState = 4;
        static final int TRANSACTION_fastForward = 14;
        static final int TRANSACTION_fastRewind = 15;
        static final int TRANSACTION_getBufferedPosition = 26;
        static final int TRANSACTION_getCurrentGrabState = 3;
        static final int TRANSACTION_getDuration = 25;
        static final int TRANSACTION_getPlaySpeed = 28;
        static final int TRANSACTION_getPlayerCapability = 5;
        static final int TRANSACTION_getPlayerStatus = 23;
        static final int TRANSACTION_getPosition = 24;
        static final int TRANSACTION_getRepeatMode = 27;
        static final int TRANSACTION_getTrackInfoSet = 31;
        static final int TRANSACTION_getVolume = 30;
        static final int TRANSACTION_isMute = 29;
        static final int TRANSACTION_next = 11;
        static final int TRANSACTION_pause = 8;
        static final int TRANSACTION_play = 6;
        static final int TRANSACTION_previous = 12;
        static final int TRANSACTION_provideKeyResponse = 22;
        static final int TRANSACTION_registerRemotePlayerListener = 1;
        static final int TRANSACTION_resume = 9;
        static final int TRANSACTION_seek = 13;
        static final int TRANSACTION_setAudioTrackInfo = 20;
        static final int TRANSACTION_setMute = 19;
        static final int TRANSACTION_setRepeatMode = 16;
        static final int TRANSACTION_setSpeed = 17;
        static final int TRANSACTION_setSubtitleTrackInfo = 32;
        static final int TRANSACTION_setVideoTrackInfo = 21;
        static final int TRANSACTION_setVolume = 18;
        static final int TRANSACTION_stop = 10;
        static final int TRANSACTION_unregisterRemotePlayerListener = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemotePlayController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRemotePlayController)) {
                return (IRemotePlayController) queryLocalInterface;
            }
            return new c(iBinder);
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
                    boolean registerRemotePlayerListener = registerRemotePlayerListener(IRemotePlayerListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(registerRemotePlayerListener ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean unregisterRemotePlayerListener = unregisterRemotePlayerListener();
                    parcel2.writeNoException();
                    parcel2.writeInt(unregisterRemotePlayerListener ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    GrabState currentGrabState = getCurrentGrabState();
                    parcel2.writeNoException();
                    if (currentGrabState != null) {
                        parcel2.writeInt(1);
                        currentGrabState.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean configGrabState = configGrabState(parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(configGrabState ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    PlayerCapability playerCapability = getPlayerCapability();
                    parcel2.writeNoException();
                    if (playerCapability != null) {
                        parcel2.writeInt(1);
                        playerCapability.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean play = play(parcel.readInt() != 0 ? PlayInfoHolder.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(play ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean addMediaItem = addMediaItem(parcel.readInt() != 0 ? PlayInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(addMediaItem ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean pause = pause();
                    parcel2.writeNoException();
                    parcel2.writeInt(pause ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean resume = resume();
                    parcel2.writeNoException();
                    parcel2.writeInt(resume ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean stop = stop();
                    parcel2.writeNoException();
                    parcel2.writeInt(stop ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean next = next();
                    parcel2.writeNoException();
                    parcel2.writeInt(next ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean previous = previous();
                    parcel2.writeNoException();
                    parcel2.writeInt(previous ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean seek = seek(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(seek ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean fastForward = fastForward(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(fastForward ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean fastRewind = fastRewind(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(fastRewind ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean repeatMode = setRepeatMode(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(repeatMode ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean speed = setSpeed(parcel.readFloat());
                    parcel2.writeNoException();
                    parcel2.writeInt(speed ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean volume = setVolume(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(volume ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean mute = setMute(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(mute ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean audioTrackInfo = setAudioTrackInfo(parcel.readInt() != 0 ? TrackInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(audioTrackInfo ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean videoTrackInfo = setVideoTrackInfo(parcel.readInt() != 0 ? TrackInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(videoTrackInfo ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean provideKeyResponse = provideKeyResponse(parcel.readString(), parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(provideKeyResponse ? 1 : 0);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    int playerStatus = getPlayerStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(playerStatus);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    int position = getPosition();
                    parcel2.writeNoException();
                    parcel2.writeInt(position);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    int duration = getDuration();
                    parcel2.writeNoException();
                    parcel2.writeInt(duration);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    int bufferedPosition = getBufferedPosition();
                    parcel2.writeNoException();
                    parcel2.writeInt(bufferedPosition);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    int repeatMode2 = getRepeatMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(repeatMode2);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    float playSpeed = getPlaySpeed();
                    parcel2.writeNoException();
                    parcel2.writeFloat(playSpeed);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isMute = isMute();
                    parcel2.writeNoException();
                    parcel2.writeInt(isMute ? 1 : 0);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    int volume2 = getVolume();
                    parcel2.writeNoException();
                    parcel2.writeInt(volume2);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    TrackInfoSet trackInfoSet = getTrackInfoSet();
                    parcel2.writeNoException();
                    if (trackInfoSet != null) {
                        parcel2.writeInt(1);
                        trackInfoSet.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean subtitleTrackInfo = setSubtitleTrackInfo(parcel.readInt() != 0 ? TrackInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(subtitleTrackInfo ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class c implements IRemotePlayController {
            public static IRemotePlayController d;
            private IBinder c;

            c(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean registerRemotePlayerListener(IRemotePlayerListener iRemotePlayerListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRemotePlayerListener != null ? iRemotePlayerListener.asBinder() : null);
                    if (!this.c.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerRemotePlayerListener(iRemotePlayerListener);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean unregisterRemotePlayerListener() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.c.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterRemotePlayerListener();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public GrabState getCurrentGrabState() throws RemoteException {
                GrabState createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.c.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getCurrentGrabState();
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? GrabState.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean configGrabState(int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    if (!this.c.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().configGrabState(i, j);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public PlayerCapability getPlayerCapability() throws RemoteException {
                PlayerCapability createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.c.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getPlayerCapability();
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? PlayerCapability.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean play(PlayInfoHolder playInfoHolder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (playInfoHolder != null) {
                        obtain.writeInt(1);
                        playInfoHolder.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.c.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().play(playInfoHolder);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean addMediaItem(PlayInfo playInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (playInfo != null) {
                        obtain.writeInt(1);
                        playInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.c.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addMediaItem(playInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean pause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.c.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().pause();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean resume() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.c.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resume();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean stop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.c.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stop();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean next() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.c.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().next();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean previous() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.c.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().previous();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean seek(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.c.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().seek(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean fastForward(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.c.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().fastForward(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean fastRewind(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.c.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().fastRewind(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean setRepeatMode(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.c.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRepeatMode(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean setSpeed(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeFloat(f);
                    if (!this.c.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSpeed(f);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean setVolume(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.c.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setVolume(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean setMute(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.c.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setMute(z);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean setAudioTrackInfo(TrackInfo trackInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (trackInfo != null) {
                        obtain.writeInt(1);
                        trackInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.c.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAudioTrackInfo(trackInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean setVideoTrackInfo(TrackInfo trackInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (trackInfo != null) {
                        obtain.writeInt(1);
                        trackInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.c.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setVideoTrackInfo(trackInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean provideKeyResponse(String str, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    if (!this.c.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().provideKeyResponse(str, bArr);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public int getPlayerStatus() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.c.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getPlayerStatus();
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

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public int getPosition() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.c.transact(24, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getPosition();
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

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public int getDuration() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.c.transact(25, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getDuration();
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

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public int getBufferedPosition() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.c.transact(26, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getBufferedPosition();
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

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public int getRepeatMode() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.c.transact(27, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getRepeatMode();
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

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public float getPlaySpeed() throws RemoteException {
                float readFloat;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.c.transact(28, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readFloat = Stub.getDefaultImpl().getPlaySpeed();
                    } else {
                        obtain2.readException();
                        readFloat = obtain2.readFloat();
                    }
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean isMute() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.c.transact(29, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMute();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public int getVolume() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.c.transact(30, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getVolume();
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

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public TrackInfoSet getTrackInfoSet() throws RemoteException {
                TrackInfoSet createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.c.transact(31, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getTrackInfoSet();
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? TrackInfoSet.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayController
            public boolean setSubtitleTrackInfo(TrackInfo trackInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (trackInfo != null) {
                        obtain.writeInt(1);
                        trackInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.c.transact(32, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSubtitleTrackInfo(trackInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemotePlayController iRemotePlayController) {
            if (c.d != null || iRemotePlayController == null) {
                return false;
            }
            c.d = iRemotePlayController;
            return true;
        }

        public static IRemotePlayController getDefaultImpl() {
            return c.d;
        }
    }
}
