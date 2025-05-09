package com.android.mediacenter.localmusic;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.media.session.MediaSessionCompat;
import com.android.mediacenter.data.bean.SongBean;

/* loaded from: classes2.dex */
public interface IMediaPlaybackService extends IInterface {
    public static final String DESCRIPTOR = "com.android.mediacenter.localmusic.IMediaPlaybackService";

    /* loaded from: classes8.dex */
    public static class Default implements IMediaPlaybackService {
        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void addNextPlay(SongBean songBean) throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public boolean addUrltoPlayList(SongBean[] songBeanArr, boolean z) throws RemoteException {
            return false;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void addUrltoPlayListInPieces(SongBean[] songBeanArr, int i) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void deleteSongs(SongBean[] songBeanArr) throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public long duration() throws RemoteException {
            return 0L;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public long getAlbumId() throws RemoteException {
            return 0L;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public String getAlbumName() throws RemoteException {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public long getArtistId() throws RemoteException {
            return 0L;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public String getArtistName() throws RemoteException {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public long getAudioId() throws RemoteException {
            return 0L;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public int getAudioSessionId() throws RemoteException {
            return 0;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public int getBufferPercentage() throws RemoteException {
            return 0;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public String getCurrentDMRName() throws RemoteException {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public long getCurrentPlayPlaylistId() throws RemoteException {
            return 0L;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public int getCurrentQuality() throws RemoteException {
            return 0;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public boolean getDolbyEffectOn() throws RemoteException {
            return false;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public long[] getErrorIds() throws RemoteException {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public int getFrequency() throws RemoteException {
            return 0;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public MediaSessionCompat.Token getMediaSessionToken() throws RemoteException {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public SongBean[] getMusicIdOnly() throws RemoteException {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public SongBean[] getMusicInfos(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public NetSongInfo getNetMusicInfo() throws RemoteException {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public SongBean[] getNetMusicInfos() throws RemoteException {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public SongBean getNextBean() throws RemoteException {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public boolean getOneShot() throws RemoteException {
            return false;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public String getOnlinePlaylistId() throws RemoteException {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public String getOnlinePlaylistType() throws RemoteException {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public String getPath() throws RemoteException {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public int getPlayMode() throws RemoteException {
            return 0;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public int getPlaylistLength() throws RemoteException {
            return 0;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public SongBean getPrevBean() throws RemoteException {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public long[] getQueue() throws RemoteException {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public int getQueuePosition() throws RemoteException {
            return 0;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public int getRepeatTime() throws RemoteException {
            return 0;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public SongBean getSongBean() throws RemoteException {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public SongBean getSongBeanFromId(long j) throws RemoteException {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public String getTrackName() throws RemoteException {
            return null;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public boolean hasMultiscreenDevice() throws RemoteException {
            return false;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void initializeDlna() throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public boolean isAllSongsOnline() throws RemoteException {
            return false;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public boolean isContainsOnlineSong() throws RemoteException {
            return false;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public boolean isDownloadingLyric(String str) throws RemoteException {
            return false;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public boolean isInitialized() throws RemoteException {
            return false;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public boolean isOnlinePrepearing() throws RemoteException {
            return false;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public boolean isPauseAfterCurSong() throws RemoteException {
            return false;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public boolean isPlayerClientRendering() throws RemoteException {
            return false;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public boolean isPlaying() throws RemoteException {
            return false;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void next() throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void openFile(String str, boolean z) throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void openFileAsync(String str) throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void openUrlMusic(SongBean[] songBeanArr, int i, long j, String str, String str2) throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void pause() throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void play() throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public boolean playSong(SongBean songBean) throws RemoteException {
            return false;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public long position() throws RemoteException {
            return 0L;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void prev() throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void prevImmediately() throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void reloadQueue() throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void replacePlayingBean(SongBean songBean, SongBean songBean2, boolean z) throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public long seek(long j) throws RemoteException {
            return 0L;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public long seekRepeat(long j, int i) throws RemoteException {
            return 0L;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void setClearMediaInfoWhenPauseAudio(boolean z) throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public boolean setDolbyEffect(boolean z) throws RemoteException {
            return false;
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void setKtHistorySongPlayPos(long j) throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void setMediaInfo() throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void setPauseAfterCurSong(boolean z) throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void setPlayMode(int i) throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void setProcessSeq(String str) throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void setPush(boolean z) throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void setQueuePosition(int i) throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void setRepeatTime(int i) throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void showDeviceSelector() throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void stepFrequency(int i) throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void stop() throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void stopRunning(Bundle bundle) throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void updateSongInfo(String str, boolean z, SongBean songBean, SongBean songBean2) throws RemoteException {
        }

        @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
        public void updateSongs(SongBean[] songBeanArr) throws RemoteException {
        }
    }

    void addNextPlay(SongBean songBean) throws RemoteException;

    boolean addUrltoPlayList(SongBean[] songBeanArr, boolean z) throws RemoteException;

    void addUrltoPlayListInPieces(SongBean[] songBeanArr, int i) throws RemoteException;

    void deleteSongs(SongBean[] songBeanArr) throws RemoteException;

    long duration() throws RemoteException;

    long getAlbumId() throws RemoteException;

    String getAlbumName() throws RemoteException;

    long getArtistId() throws RemoteException;

    String getArtistName() throws RemoteException;

    long getAudioId() throws RemoteException;

    int getAudioSessionId() throws RemoteException;

    int getBufferPercentage() throws RemoteException;

    String getCurrentDMRName() throws RemoteException;

    long getCurrentPlayPlaylistId() throws RemoteException;

    int getCurrentQuality() throws RemoteException;

    boolean getDolbyEffectOn() throws RemoteException;

    long[] getErrorIds() throws RemoteException;

    int getFrequency() throws RemoteException;

    MediaSessionCompat.Token getMediaSessionToken() throws RemoteException;

    SongBean[] getMusicIdOnly() throws RemoteException;

    SongBean[] getMusicInfos(int i, int i2) throws RemoteException;

    NetSongInfo getNetMusicInfo() throws RemoteException;

    SongBean[] getNetMusicInfos() throws RemoteException;

    SongBean getNextBean() throws RemoteException;

    boolean getOneShot() throws RemoteException;

    String getOnlinePlaylistId() throws RemoteException;

    String getOnlinePlaylistType() throws RemoteException;

    String getPath() throws RemoteException;

    int getPlayMode() throws RemoteException;

    int getPlaylistLength() throws RemoteException;

    SongBean getPrevBean() throws RemoteException;

    long[] getQueue() throws RemoteException;

    int getQueuePosition() throws RemoteException;

    int getRepeatTime() throws RemoteException;

    SongBean getSongBean() throws RemoteException;

    SongBean getSongBeanFromId(long j) throws RemoteException;

    String getTrackName() throws RemoteException;

    boolean hasMultiscreenDevice() throws RemoteException;

    void initializeDlna() throws RemoteException;

    boolean isAllSongsOnline() throws RemoteException;

    boolean isContainsOnlineSong() throws RemoteException;

    boolean isDownloadingLyric(String str) throws RemoteException;

    boolean isInitialized() throws RemoteException;

    boolean isOnlinePrepearing() throws RemoteException;

    boolean isPauseAfterCurSong() throws RemoteException;

    boolean isPlayerClientRendering() throws RemoteException;

    boolean isPlaying() throws RemoteException;

    void next() throws RemoteException;

    void openFile(String str, boolean z) throws RemoteException;

    void openFileAsync(String str) throws RemoteException;

    void openUrlMusic(SongBean[] songBeanArr, int i, long j, String str, String str2) throws RemoteException;

    void pause() throws RemoteException;

    void play() throws RemoteException;

    boolean playSong(SongBean songBean) throws RemoteException;

    long position() throws RemoteException;

    void prev() throws RemoteException;

    void prevImmediately() throws RemoteException;

    void reloadQueue() throws RemoteException;

    void replacePlayingBean(SongBean songBean, SongBean songBean2, boolean z) throws RemoteException;

    long seek(long j) throws RemoteException;

    long seekRepeat(long j, int i) throws RemoteException;

    void setClearMediaInfoWhenPauseAudio(boolean z) throws RemoteException;

    boolean setDolbyEffect(boolean z) throws RemoteException;

    void setKtHistorySongPlayPos(long j) throws RemoteException;

    void setMediaInfo() throws RemoteException;

    void setPauseAfterCurSong(boolean z) throws RemoteException;

    void setPlayMode(int i) throws RemoteException;

    void setProcessSeq(String str) throws RemoteException;

    void setPush(boolean z) throws RemoteException;

    void setQueuePosition(int i) throws RemoteException;

    void setRepeatTime(int i) throws RemoteException;

    void showDeviceSelector() throws RemoteException;

    void stepFrequency(int i) throws RemoteException;

    void stop() throws RemoteException;

    void stopRunning(Bundle bundle) throws RemoteException;

    void updateSongInfo(String str, boolean z, SongBean songBean, SongBean songBean2) throws RemoteException;

    void updateSongs(SongBean[] songBeanArr) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaPlaybackService {
        static final int TRANSACTION_addNextPlay = 65;
        static final int TRANSACTION_addUrltoPlayList = 35;
        static final int TRANSACTION_addUrltoPlayListInPieces = 49;
        static final int TRANSACTION_deleteSongs = 39;
        static final int TRANSACTION_duration = 14;
        static final int TRANSACTION_getAlbumId = 9;
        static final int TRANSACTION_getAlbumName = 13;
        static final int TRANSACTION_getArtistId = 11;
        static final int TRANSACTION_getArtistName = 12;
        static final int TRANSACTION_getAudioId = 7;
        static final int TRANSACTION_getAudioSessionId = 58;
        static final int TRANSACTION_getBufferPercentage = 24;
        static final int TRANSACTION_getCurrentDMRName = 43;
        static final int TRANSACTION_getCurrentPlayPlaylistId = 32;
        static final int TRANSACTION_getCurrentQuality = 70;
        static final int TRANSACTION_getDolbyEffectOn = 36;
        static final int TRANSACTION_getErrorIds = 59;
        static final int TRANSACTION_getFrequency = 76;
        static final int TRANSACTION_getMediaSessionToken = 74;
        static final int TRANSACTION_getMusicIdOnly = 57;
        static final int TRANSACTION_getMusicInfos = 55;
        static final int TRANSACTION_getNetMusicInfo = 10;
        static final int TRANSACTION_getNetMusicInfos = 31;
        static final int TRANSACTION_getNextBean = 68;
        static final int TRANSACTION_getOneShot = 28;
        static final int TRANSACTION_getOnlinePlaylistId = 38;
        static final int TRANSACTION_getOnlinePlaylistType = 34;
        static final int TRANSACTION_getPath = 21;
        static final int TRANSACTION_getPlayMode = 22;
        static final int TRANSACTION_getPlaylistLength = 56;
        static final int TRANSACTION_getPrevBean = 69;
        static final int TRANSACTION_getQueue = 20;
        static final int TRANSACTION_getQueuePosition = 19;
        static final int TRANSACTION_getRepeatTime = 67;
        static final int TRANSACTION_getSongBean = 30;
        static final int TRANSACTION_getSongBeanFromId = 52;
        static final int TRANSACTION_getTrackName = 8;
        static final int TRANSACTION_hasMultiscreenDevice = 53;
        static final int TRANSACTION_initializeDlna = 40;
        static final int TRANSACTION_isAllSongsOnline = 63;
        static final int TRANSACTION_isContainsOnlineSong = 61;
        static final int TRANSACTION_isDownloadingLyric = 51;
        static final int TRANSACTION_isInitialized = 18;
        static final int TRANSACTION_isOnlinePrepearing = 17;
        static final int TRANSACTION_isPauseAfterCurSong = 73;
        static final int TRANSACTION_isPlayerClientRendering = 42;
        static final int TRANSACTION_isPlaying = 6;
        static final int TRANSACTION_next = 5;
        static final int TRANSACTION_openFile = 25;
        static final int TRANSACTION_openFileAsync = 26;
        static final int TRANSACTION_openUrlMusic = 33;
        static final int TRANSACTION_pause = 3;
        static final int TRANSACTION_play = 1;
        static final int TRANSACTION_playSong = 62;
        static final int TRANSACTION_position = 15;
        static final int TRANSACTION_prev = 4;
        static final int TRANSACTION_prevImmediately = 50;
        static final int TRANSACTION_reloadQueue = 29;
        static final int TRANSACTION_replacePlayingBean = 64;
        static final int TRANSACTION_seek = 16;
        static final int TRANSACTION_seekRepeat = 60;
        static final int TRANSACTION_setClearMediaInfoWhenPauseAudio = 44;
        static final int TRANSACTION_setDolbyEffect = 37;
        static final int TRANSACTION_setKtHistorySongPlayPos = 72;
        static final int TRANSACTION_setMediaInfo = 45;
        static final int TRANSACTION_setPauseAfterCurSong = 71;
        static final int TRANSACTION_setPlayMode = 23;
        static final int TRANSACTION_setProcessSeq = 46;
        static final int TRANSACTION_setPush = 47;
        static final int TRANSACTION_setQueuePosition = 27;
        static final int TRANSACTION_setRepeatTime = 66;
        static final int TRANSACTION_showDeviceSelector = 41;
        static final int TRANSACTION_stepFrequency = 75;
        static final int TRANSACTION_stop = 2;
        static final int TRANSACTION_stopRunning = 77;
        static final int TRANSACTION_updateSongInfo = 48;
        static final int TRANSACTION_updateSongs = 54;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IMediaPlaybackService.DESCRIPTOR);
        }

        public static IMediaPlaybackService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IMediaPlaybackService.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IMediaPlaybackService)) {
                return (IMediaPlaybackService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IMediaPlaybackService.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IMediaPlaybackService.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    play();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    stop();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    pause();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    prev();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    next();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    boolean isPlaying = isPlaying();
                    parcel2.writeNoException();
                    parcel2.writeInt(isPlaying ? 1 : 0);
                    return true;
                case 7:
                    long audioId = getAudioId();
                    parcel2.writeNoException();
                    parcel2.writeLong(audioId);
                    return true;
                case 8:
                    String trackName = getTrackName();
                    parcel2.writeNoException();
                    parcel2.writeString(trackName);
                    return true;
                case 9:
                    long albumId = getAlbumId();
                    parcel2.writeNoException();
                    parcel2.writeLong(albumId);
                    return true;
                case 10:
                    NetSongInfo netMusicInfo = getNetMusicInfo();
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, netMusicInfo, 1);
                    return true;
                case 11:
                    long artistId = getArtistId();
                    parcel2.writeNoException();
                    parcel2.writeLong(artistId);
                    return true;
                case 12:
                    String artistName = getArtistName();
                    parcel2.writeNoException();
                    parcel2.writeString(artistName);
                    return true;
                case 13:
                    String albumName = getAlbumName();
                    parcel2.writeNoException();
                    parcel2.writeString(albumName);
                    return true;
                case 14:
                    long duration = duration();
                    parcel2.writeNoException();
                    parcel2.writeLong(duration);
                    return true;
                case 15:
                    long position = position();
                    parcel2.writeNoException();
                    parcel2.writeLong(position);
                    return true;
                case 16:
                    long seek = seek(parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeLong(seek);
                    return true;
                case 17:
                    boolean isOnlinePrepearing = isOnlinePrepearing();
                    parcel2.writeNoException();
                    parcel2.writeInt(isOnlinePrepearing ? 1 : 0);
                    return true;
                case 18:
                    boolean isInitialized = isInitialized();
                    parcel2.writeNoException();
                    parcel2.writeInt(isInitialized ? 1 : 0);
                    return true;
                case 19:
                    int queuePosition = getQueuePosition();
                    parcel2.writeNoException();
                    parcel2.writeInt(queuePosition);
                    return true;
                case 20:
                    long[] queue = getQueue();
                    parcel2.writeNoException();
                    parcel2.writeLongArray(queue);
                    return true;
                case 21:
                    String path = getPath();
                    parcel2.writeNoException();
                    parcel2.writeString(path);
                    return true;
                case 22:
                    int playMode = getPlayMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(playMode);
                    return true;
                case 23:
                    setPlayMode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    int bufferPercentage = getBufferPercentage();
                    parcel2.writeNoException();
                    parcel2.writeInt(bufferPercentage);
                    return true;
                case 25:
                    openFile(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 26:
                    openFileAsync(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    setQueuePosition(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 28:
                    boolean oneShot = getOneShot();
                    parcel2.writeNoException();
                    parcel2.writeInt(oneShot ? 1 : 0);
                    return true;
                case 29:
                    reloadQueue();
                    parcel2.writeNoException();
                    return true;
                case 30:
                    SongBean songBean = getSongBean();
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, songBean, 1);
                    return true;
                case 31:
                    SongBean[] netMusicInfos = getNetMusicInfos();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(netMusicInfos, 1);
                    return true;
                case 32:
                    long currentPlayPlaylistId = getCurrentPlayPlaylistId();
                    parcel2.writeNoException();
                    parcel2.writeLong(currentPlayPlaylistId);
                    return true;
                case 33:
                    openUrlMusic((SongBean[]) parcel.createTypedArray(SongBean.CREATOR), parcel.readInt(), parcel.readLong(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 34:
                    String onlinePlaylistType = getOnlinePlaylistType();
                    parcel2.writeNoException();
                    parcel2.writeString(onlinePlaylistType);
                    return true;
                case 35:
                    boolean addUrltoPlayList = addUrltoPlayList((SongBean[]) parcel.createTypedArray(SongBean.CREATOR), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(addUrltoPlayList ? 1 : 0);
                    return true;
                case 36:
                    boolean dolbyEffectOn = getDolbyEffectOn();
                    parcel2.writeNoException();
                    parcel2.writeInt(dolbyEffectOn ? 1 : 0);
                    return true;
                case 37:
                    boolean dolbyEffect = setDolbyEffect(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(dolbyEffect ? 1 : 0);
                    return true;
                case 38:
                    String onlinePlaylistId = getOnlinePlaylistId();
                    parcel2.writeNoException();
                    parcel2.writeString(onlinePlaylistId);
                    return true;
                case 39:
                    deleteSongs((SongBean[]) parcel.createTypedArray(SongBean.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 40:
                    initializeDlna();
                    parcel2.writeNoException();
                    return true;
                case 41:
                    showDeviceSelector();
                    parcel2.writeNoException();
                    return true;
                case 42:
                    boolean isPlayerClientRendering = isPlayerClientRendering();
                    parcel2.writeNoException();
                    parcel2.writeInt(isPlayerClientRendering ? 1 : 0);
                    return true;
                case 43:
                    String currentDMRName = getCurrentDMRName();
                    parcel2.writeNoException();
                    parcel2.writeString(currentDMRName);
                    return true;
                case 44:
                    setClearMediaInfoWhenPauseAudio(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 45:
                    setMediaInfo();
                    parcel2.writeNoException();
                    return true;
                case 46:
                    setProcessSeq(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 47:
                    setPush(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 48:
                    updateSongInfo(parcel.readString(), parcel.readInt() != 0, (SongBean) _Parcel.readTypedObject(parcel, SongBean.CREATOR), (SongBean) _Parcel.readTypedObject(parcel, SongBean.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 49:
                    addUrltoPlayListInPieces((SongBean[]) parcel.createTypedArray(SongBean.CREATOR), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 50:
                    prevImmediately();
                    parcel2.writeNoException();
                    return true;
                case 51:
                    boolean isDownloadingLyric = isDownloadingLyric(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(isDownloadingLyric ? 1 : 0);
                    return true;
                case 52:
                    SongBean songBeanFromId = getSongBeanFromId(parcel.readLong());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, songBeanFromId, 1);
                    return true;
                case 53:
                    boolean hasMultiscreenDevice = hasMultiscreenDevice();
                    parcel2.writeNoException();
                    parcel2.writeInt(hasMultiscreenDevice ? 1 : 0);
                    return true;
                case 54:
                    updateSongs((SongBean[]) parcel.createTypedArray(SongBean.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 55:
                    SongBean[] musicInfos = getMusicInfos(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(musicInfos, 1);
                    return true;
                case 56:
                    int playlistLength = getPlaylistLength();
                    parcel2.writeNoException();
                    parcel2.writeInt(playlistLength);
                    return true;
                case 57:
                    SongBean[] musicIdOnly = getMusicIdOnly();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(musicIdOnly, 1);
                    return true;
                case 58:
                    int audioSessionId = getAudioSessionId();
                    parcel2.writeNoException();
                    parcel2.writeInt(audioSessionId);
                    return true;
                case 59:
                    long[] errorIds = getErrorIds();
                    parcel2.writeNoException();
                    parcel2.writeLongArray(errorIds);
                    return true;
                case 60:
                    long seekRepeat = seekRepeat(parcel.readLong(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(seekRepeat);
                    return true;
                case 61:
                    boolean isContainsOnlineSong = isContainsOnlineSong();
                    parcel2.writeNoException();
                    parcel2.writeInt(isContainsOnlineSong ? 1 : 0);
                    return true;
                case 62:
                    boolean playSong = playSong((SongBean) _Parcel.readTypedObject(parcel, SongBean.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(playSong ? 1 : 0);
                    return true;
                case 63:
                    boolean isAllSongsOnline = isAllSongsOnline();
                    parcel2.writeNoException();
                    parcel2.writeInt(isAllSongsOnline ? 1 : 0);
                    return true;
                case 64:
                    replacePlayingBean((SongBean) _Parcel.readTypedObject(parcel, SongBean.CREATOR), (SongBean) _Parcel.readTypedObject(parcel, SongBean.CREATOR), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 65:
                    addNextPlay((SongBean) _Parcel.readTypedObject(parcel, SongBean.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 66:
                    setRepeatTime(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 67:
                    int repeatTime = getRepeatTime();
                    parcel2.writeNoException();
                    parcel2.writeInt(repeatTime);
                    return true;
                case 68:
                    SongBean nextBean = getNextBean();
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, nextBean, 1);
                    return true;
                case 69:
                    SongBean prevBean = getPrevBean();
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, prevBean, 1);
                    return true;
                case 70:
                    int currentQuality = getCurrentQuality();
                    parcel2.writeNoException();
                    parcel2.writeInt(currentQuality);
                    return true;
                case 71:
                    setPauseAfterCurSong(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 72:
                    setKtHistorySongPlayPos(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 73:
                    boolean isPauseAfterCurSong = isPauseAfterCurSong();
                    parcel2.writeNoException();
                    parcel2.writeInt(isPauseAfterCurSong ? 1 : 0);
                    return true;
                case 74:
                    MediaSessionCompat.Token mediaSessionToken = getMediaSessionToken();
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, mediaSessionToken, 1);
                    return true;
                case 75:
                    stepFrequency(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 76:
                    int frequency = getFrequency();
                    parcel2.writeNoException();
                    parcel2.writeInt(frequency);
                    return true;
                case 77:
                    stopRunning((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class Proxy implements IMediaPlaybackService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void play() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void stop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void pause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void prev() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void next() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public boolean isPlaying() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public long getAudioId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public String getTrackName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public long getAlbumId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public NetSongInfo getNetMusicInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return (NetSongInfo) _Parcel.readTypedObject(obtain2, NetSongInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public long getArtistId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public String getArtistName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public String getAlbumName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public long duration() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public long position() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public long seek(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeLong(j);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public boolean isOnlinePrepearing() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public boolean isInitialized() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public int getQueuePosition() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public long[] getQueue() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createLongArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public String getPath() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public int getPlayMode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void setPlayMode(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public int getBufferPercentage() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void openFile(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void openFileAsync(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void setQueuePosition(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public boolean getOneShot() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void reloadQueue() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public SongBean getSongBean() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                    return (SongBean) _Parcel.readTypedObject(obtain2, SongBean.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public SongBean[] getNetMusicInfos() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                    return (SongBean[]) obtain2.createTypedArray(SongBean.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public long getCurrentPlayPlaylistId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void openUrlMusic(SongBean[] songBeanArr, int i, long j, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeTypedArray(songBeanArr, 0);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public String getOnlinePlaylistType() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public boolean addUrltoPlayList(SongBean[] songBeanArr, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeTypedArray(songBeanArr, 0);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public boolean getDolbyEffectOn() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public boolean setDolbyEffect(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public String getOnlinePlaylistId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void deleteSongs(SongBean[] songBeanArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeTypedArray(songBeanArr, 0);
                    this.mRemote.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void initializeDlna() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void showDeviceSelector() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public boolean isPlayerClientRendering() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public String getCurrentDMRName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void setClearMediaInfoWhenPauseAudio(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void setMediaInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void setProcessSeq(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void setPush(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(47, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void updateSongInfo(String str, boolean z, SongBean songBean, SongBean songBean2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    _Parcel.writeTypedObject(obtain, songBean, 0);
                    _Parcel.writeTypedObject(obtain, songBean2, 0);
                    this.mRemote.transact(48, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void addUrltoPlayListInPieces(SongBean[] songBeanArr, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeTypedArray(songBeanArr, 0);
                    obtain.writeInt(i);
                    this.mRemote.transact(49, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void prevImmediately() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(50, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public boolean isDownloadingLyric(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(51, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public SongBean getSongBeanFromId(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeLong(j);
                    this.mRemote.transact(52, obtain, obtain2, 0);
                    obtain2.readException();
                    return (SongBean) _Parcel.readTypedObject(obtain2, SongBean.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public boolean hasMultiscreenDevice() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(53, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void updateSongs(SongBean[] songBeanArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeTypedArray(songBeanArr, 0);
                    this.mRemote.transact(54, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public SongBean[] getMusicInfos(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(55, obtain, obtain2, 0);
                    obtain2.readException();
                    return (SongBean[]) obtain2.createTypedArray(SongBean.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public int getPlaylistLength() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(56, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public SongBean[] getMusicIdOnly() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(57, obtain, obtain2, 0);
                    obtain2.readException();
                    return (SongBean[]) obtain2.createTypedArray(SongBean.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public int getAudioSessionId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(58, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public long[] getErrorIds() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(59, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createLongArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public long seekRepeat(long j, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeInt(i);
                    this.mRemote.transact(60, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public boolean isContainsOnlineSong() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(61, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public boolean playSong(SongBean songBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, songBean, 0);
                    this.mRemote.transact(62, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public boolean isAllSongsOnline() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(63, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void replacePlayingBean(SongBean songBean, SongBean songBean2, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, songBean, 0);
                    _Parcel.writeTypedObject(obtain, songBean2, 0);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(64, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void addNextPlay(SongBean songBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, songBean, 0);
                    this.mRemote.transact(65, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void setRepeatTime(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(66, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public int getRepeatTime() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(67, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public SongBean getNextBean() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(68, obtain, obtain2, 0);
                    obtain2.readException();
                    return (SongBean) _Parcel.readTypedObject(obtain2, SongBean.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public SongBean getPrevBean() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(69, obtain, obtain2, 0);
                    obtain2.readException();
                    return (SongBean) _Parcel.readTypedObject(obtain2, SongBean.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public int getCurrentQuality() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(70, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void setPauseAfterCurSong(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(71, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void setKtHistorySongPlayPos(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeLong(j);
                    this.mRemote.transact(72, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public boolean isPauseAfterCurSong() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(73, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public MediaSessionCompat.Token getMediaSessionToken() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(74, obtain, obtain2, 0);
                    obtain2.readException();
                    return (MediaSessionCompat.Token) _Parcel.readTypedObject(obtain2, MediaSessionCompat.Token.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void stepFrequency(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(75, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public int getFrequency() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    this.mRemote.transact(76, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.localmusic.IMediaPlaybackService
            public void stopRunning(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaPlaybackService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(77, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IMediaPlaybackService.DESCRIPTOR;
            }
        }
    }

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
    }
}
