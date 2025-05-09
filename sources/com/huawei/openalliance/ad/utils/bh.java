package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.location.Location;
import android.os.Looper;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.location.FusedLocationProviderClient;
import com.huawei.hms.location.LocationAvailability;
import com.huawei.hms.location.LocationCallback;
import com.huawei.hms.location.LocationRequest;
import com.huawei.hms.location.LocationResult;
import com.huawei.hms.location.LocationServices;
import com.huawei.openalliance.ad.ho;
import com.huawei.operation.OpAnalyticsConstants;

/* loaded from: classes5.dex */
public class bh {

    /* renamed from: a, reason: collision with root package name */
    LocationCallback f7627a;
    LocationRequest b;
    private FusedLocationProviderClient c;
    private a d;
    private volatile boolean e = false;

    public interface a {
        void a();

        void a(Location location);
    }

    public void a() {
        if (this.c == null) {
            return;
        }
        this.e = false;
        this.c.requestLocationUpdates(this.b, this.f7627a, Looper.getMainLooper()).addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.huawei.openalliance.ad.utils.bh.3
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Void r2) {
                ho.b("LocationUtils", "loc_tag requestLocationUpdates onSuccess");
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.huawei.openalliance.ad.utils.bh.2
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                ho.d("LocationUtils", "loc_tag requestLocationUpdates onFailure");
                bh.this.d.a();
                bh.this.e = true;
            }
        });
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.bh.4
            @Override // java.lang.Runnable
            public void run() {
                bh.this.b();
            }
        }, OpAnalyticsConstants.H5_LOADING_DELAY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.e) {
            return;
        }
        try {
            this.c.removeLocationUpdates(this.f7627a).addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.huawei.openalliance.ad.utils.bh.6
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Void r2) {
                    ho.a("LocationUtils", "loc_tag removeLocationUpdates onSuccess");
                    bh.this.e = true;
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.huawei.openalliance.ad.utils.bh.5
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    ho.a("LocationUtils", "loc_tag removeLocationUpdates onFailure:%s", exc.getClass().getSimpleName());
                    bh.this.e = false;
                }
            });
        } catch (Throwable th) {
            ho.c("LocationUtils", "loc_tag removeLocationUpdates encounter exception:" + th.getClass().getSimpleName());
        }
    }

    public bh(Context context, final a aVar) {
        if (context == null || aVar == null) {
            return;
        }
        this.d = aVar;
        this.c = LocationServices.getFusedLocationProviderClient(context);
        LocationRequest locationRequest = new LocationRequest();
        this.b = locationRequest;
        locationRequest.setPriority(100);
        this.b.setNumUpdates(1);
        this.b.setInterval(5000L);
        this.f7627a = new LocationCallback() { // from class: com.huawei.openalliance.ad.utils.bh.1
            public void onLocationResult(LocationResult locationResult) {
                String str;
                ho.a("LocationUtils", "loc_tag getLocationByKit onLocationResult-callback");
                if (locationResult == null) {
                    str = "loc_tag getLocationByKit onLocationResult-callback is null";
                } else if (bg.a(locationResult.getLocations())) {
                    str = "loc_tag getLocationByKit onLocationResult-callback getLocations() is wrong";
                } else {
                    Location location = (Location) locationResult.getLocations().get(0);
                    if (location != null) {
                        ho.a("LocationUtils", "loc_tag getLocationByKit onLocationResult-callback lat = %s, lon = %s", dl.a(String.valueOf(location.getLatitude())), dl.a(String.valueOf(location.getLongitude())));
                        aVar.a(location);
                        bh.this.e = true;
                    }
                    str = "loc_tag getLocationByKit onLocationResult-callback location is null";
                }
                ho.c("LocationUtils", str);
                aVar.a();
                bh.this.e = true;
            }

            public void onLocationAvailability(LocationAvailability locationAvailability) {
                if (locationAvailability != null) {
                    ho.a("LocationUtils", "loc_tag onLocationResult onLocationAvailability isLocationAvailable: %s", Boolean.valueOf(locationAvailability.isLocationAvailable()));
                }
            }
        };
    }
}
