package com.google.android.gms.maps.model;

import com.google.android.gms.internal.maps.zzag;

/* loaded from: classes8.dex */
final class zzt extends zzag {
    private final /* synthetic */ TileProvider zzem;

    @Override // com.google.android.gms.internal.maps.zzaf
    public final Tile getTile(int i, int i2, int i3) {
        return this.zzem.getTile(i, i2, i3);
    }

    zzt(TileOverlayOptions tileOverlayOptions, TileProvider tileProvider) {
        this.zzem = tileProvider;
    }
}
