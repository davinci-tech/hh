package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

/* loaded from: classes2.dex */
public final class zzg extends com.google.android.gms.internal.maps.zza implements IGoogleMapDelegate {
    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean useViewLifecycleWhenInFragment() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(59, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void stopAnimation() throws RemoteException {
        transactAndReadExceptionReturnVoid(8, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void snapshotForTest(zzbs zzbsVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzbsVar);
        transactAndReadExceptionReturnVoid(71, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void snapshot(zzbs zzbsVar, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzbsVar);
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        transactAndReadExceptionReturnVoid(38, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setWatermarkEnabled(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(51, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setTrafficEnabled(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(18, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setPadding(int i, int i2, int i3, int i4) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(i);
        obtainAndWriteInterfaceToken.writeInt(i2);
        obtainAndWriteInterfaceToken.writeInt(i3);
        obtainAndWriteInterfaceToken.writeInt(i4);
        transactAndReadExceptionReturnVoid(39, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnPolylineClickListener(zzbf zzbfVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzbfVar);
        transactAndReadExceptionReturnVoid(87, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnPolygonClickListener(zzbd zzbdVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzbdVar);
        transactAndReadExceptionReturnVoid(85, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnPoiClickListener(zzbb zzbbVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzbbVar);
        transactAndReadExceptionReturnVoid(80, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMyLocationClickListener(zzaz zzazVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzazVar);
        transactAndReadExceptionReturnVoid(107, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMyLocationChangeListener(zzax zzaxVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzaxVar);
        transactAndReadExceptionReturnVoid(36, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMyLocationButtonClickListener(zzav zzavVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzavVar);
        transactAndReadExceptionReturnVoid(37, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMarkerDragListener(zzat zzatVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzatVar);
        transactAndReadExceptionReturnVoid(31, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMarkerClickListener(zzar zzarVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzarVar);
        transactAndReadExceptionReturnVoid(30, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMapLongClickListener(zzan zzanVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzanVar);
        transactAndReadExceptionReturnVoid(29, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMapLoadedCallback(zzal zzalVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzalVar);
        transactAndReadExceptionReturnVoid(42, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMapClickListener(zzaj zzajVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzajVar);
        transactAndReadExceptionReturnVoid(28, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnInfoWindowLongClickListener(zzaf zzafVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzafVar);
        transactAndReadExceptionReturnVoid(84, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnInfoWindowCloseListener(zzad zzadVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzadVar);
        transactAndReadExceptionReturnVoid(86, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnInfoWindowClickListener(zzab zzabVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzabVar);
        transactAndReadExceptionReturnVoid(32, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnIndoorStateChangeListener(zzz zzzVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzzVar);
        transactAndReadExceptionReturnVoid(45, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnGroundOverlayClickListener(zzx zzxVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzxVar);
        transactAndReadExceptionReturnVoid(83, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCircleClickListener(zzv zzvVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzvVar);
        transactAndReadExceptionReturnVoid(89, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraMoveStartedListener(zzt zztVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zztVar);
        transactAndReadExceptionReturnVoid(96, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraMoveListener(zzr zzrVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzrVar);
        transactAndReadExceptionReturnVoid(97, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraMoveCanceledListener(zzp zzpVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzpVar);
        transactAndReadExceptionReturnVoid(98, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraIdleListener(zzn zznVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zznVar);
        transactAndReadExceptionReturnVoid(99, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraChangeListener(zzl zzlVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzlVar);
        transactAndReadExceptionReturnVoid(27, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setMyLocationEnabled(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(22, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setMinZoomPreference(float f) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(f);
        transactAndReadExceptionReturnVoid(92, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setMaxZoomPreference(float f) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(f);
        transactAndReadExceptionReturnVoid(93, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setMapType(int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(16, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean setMapStyle(MapStyleOptions mapStyleOptions) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, mapStyleOptions);
        Parcel transactAndReadException = transactAndReadException(91, obtainAndWriteInterfaceToken);
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, iLocationSourceDelegate);
        transactAndReadExceptionReturnVoid(24, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, latLngBounds);
        transactAndReadExceptionReturnVoid(95, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setInfoWindowAdapter(zzh zzhVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzhVar);
        transactAndReadExceptionReturnVoid(33, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean setIndoorEnabled(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        Parcel transactAndReadException = transactAndReadException(20, obtainAndWriteInterfaceToken);
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setContentDescription(String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(61, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setBuildingsEnabled(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(41, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void resetMinMaxZoomPreference() throws RemoteException {
        transactAndReadExceptionReturnVoid(94, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onStop() throws RemoteException {
        transactAndReadExceptionReturnVoid(102, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onStart() throws RemoteException {
        transactAndReadExceptionReturnVoid(101, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, bundle);
        Parcel transactAndReadException = transactAndReadException(60, obtainAndWriteInterfaceToken);
        if (transactAndReadException.readInt() != 0) {
            bundle.readFromParcel(transactAndReadException);
        }
        transactAndReadException.recycle();
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onResume() throws RemoteException {
        transactAndReadExceptionReturnVoid(55, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onPause() throws RemoteException {
        transactAndReadExceptionReturnVoid(56, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onLowMemory() throws RemoteException {
        transactAndReadExceptionReturnVoid(58, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onExitAmbient() throws RemoteException {
        transactAndReadExceptionReturnVoid(82, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onEnterAmbient(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, bundle);
        transactAndReadExceptionReturnVoid(81, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onDestroy() throws RemoteException {
        transactAndReadExceptionReturnVoid(57, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onCreate(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, bundle);
        transactAndReadExceptionReturnVoid(54, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void moveCamera(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean isTrafficEnabled() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(17, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean isMyLocationEnabled() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(21, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean isIndoorEnabled() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(19, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean isBuildingsEnabled() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(40, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final IUiSettingsDelegate getUiSettings() throws RemoteException {
        IUiSettingsDelegate zzbxVar;
        Parcel transactAndReadException = transactAndReadException(25, obtainAndWriteInterfaceToken());
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzbxVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            zzbxVar = queryLocalInterface instanceof IUiSettingsDelegate ? (IUiSettingsDelegate) queryLocalInterface : new zzbx(readStrongBinder);
        }
        transactAndReadException.recycle();
        return zzbxVar;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final IProjectionDelegate getProjection() throws RemoteException {
        IProjectionDelegate zzbrVar;
        Parcel transactAndReadException = transactAndReadException(26, obtainAndWriteInterfaceToken());
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzbrVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
            zzbrVar = queryLocalInterface instanceof IProjectionDelegate ? (IProjectionDelegate) queryLocalInterface : new zzbr(readStrongBinder);
        }
        transactAndReadException.recycle();
        return zzbrVar;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final Location getMyLocation() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(23, obtainAndWriteInterfaceToken());
        Location location = (Location) com.google.android.gms.internal.maps.zzc.zza(transactAndReadException, Location.CREATOR);
        transactAndReadException.recycle();
        return location;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final float getMinZoomLevel() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken());
        float readFloat = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final float getMaxZoomLevel() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken());
        float readFloat = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final int getMapType() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(15, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void getMapAsync(zzap zzapVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzapVar);
        transactAndReadExceptionReturnVoid(53, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.internal.maps.zzn getFocusedBuilding() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(44, obtainAndWriteInterfaceToken());
        com.google.android.gms.internal.maps.zzn zze = com.google.android.gms.internal.maps.zzo.zze(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zze;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final CameraPosition getCameraPosition() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken());
        CameraPosition cameraPosition = (CameraPosition) com.google.android.gms.internal.maps.zzc.zza(transactAndReadException, CameraPosition.CREATOR);
        transactAndReadException.recycle();
        return cameraPosition;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void clear() throws RemoteException {
        transactAndReadExceptionReturnVoid(14, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i, zzc zzcVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        obtainAndWriteInterfaceToken.writeInt(i);
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzcVar);
        transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void animateCameraWithCallback(IObjectWrapper iObjectWrapper, zzc zzcVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzcVar);
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void animateCamera(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.internal.maps.zzac addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, tileOverlayOptions);
        Parcel transactAndReadException = transactAndReadException(13, obtainAndWriteInterfaceToken);
        com.google.android.gms.internal.maps.zzac zzj = com.google.android.gms.internal.maps.zzad.zzj(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzj;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.internal.maps.zzz addPolyline(PolylineOptions polylineOptions) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, polylineOptions);
        Parcel transactAndReadException = transactAndReadException(9, obtainAndWriteInterfaceToken);
        com.google.android.gms.internal.maps.zzz zzi = com.google.android.gms.internal.maps.zzaa.zzi(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzi;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.internal.maps.zzw addPolygon(PolygonOptions polygonOptions) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, polygonOptions);
        Parcel transactAndReadException = transactAndReadException(10, obtainAndWriteInterfaceToken);
        com.google.android.gms.internal.maps.zzw zzh = com.google.android.gms.internal.maps.zzx.zzh(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.internal.maps.zzt addMarker(MarkerOptions markerOptions) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, markerOptions);
        Parcel transactAndReadException = transactAndReadException(11, obtainAndWriteInterfaceToken);
        com.google.android.gms.internal.maps.zzt zzg = com.google.android.gms.internal.maps.zzu.zzg(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.internal.maps.zzk addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, groundOverlayOptions);
        Parcel transactAndReadException = transactAndReadException(12, obtainAndWriteInterfaceToken);
        com.google.android.gms.internal.maps.zzk zzd = com.google.android.gms.internal.maps.zzl.zzd(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzd;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.internal.maps.zzh addCircle(CircleOptions circleOptions) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, circleOptions);
        Parcel transactAndReadException = transactAndReadException(35, obtainAndWriteInterfaceToken);
        com.google.android.gms.internal.maps.zzh zzc = com.google.android.gms.internal.maps.zzi.zzc(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzc;
    }

    zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IGoogleMapDelegate");
    }
}
