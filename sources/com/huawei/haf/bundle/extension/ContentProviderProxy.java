package com.huawei.haf.bundle.extension;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import health.compact.a.LogUtil;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class ContentProviderProxy extends ContentProvider {
    private static final Map<String, List<ContentProviderProxy>> CONTENT_PROVIDER_MAP = new HashMap();
    private static final String NAME_SPLIT_INFIX = "_Decorated_";
    private static final int SPLIT_MIN_LEN = 2;
    private static final String TAG = "Bundle_ContentProviderProxy";
    private ProviderInfo mProviderInfo;
    private ContentProvider mRealContentProvider;
    private String mRealContentProviderClassName;
    private String mRealModuleName;

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        String[] split = getClass().getName().split(NAME_SPLIT_INFIX);
        if (split != null && split.length >= 2) {
            this.mRealContentProviderClassName = split[0];
            this.mRealModuleName = split[1];
            this.mProviderInfo = new ProviderInfo(providerInfo);
            putProviders(this.mRealModuleName, this);
        }
        super.attachInfo(context, providerInfo);
    }

    @Override // android.content.ContentProvider, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            realContentProvider.onConfigurationChanged(configuration);
        }
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.query(uri, strArr, str, strArr2, str2);
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, Bundle bundle, CancellationSignal cancellationSignal) {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.query(uri, strArr, bundle, cancellationSignal);
        }
        return super.query(uri, strArr, bundle, cancellationSignal);
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.query(uri, strArr, str, strArr2, str2, cancellationSignal);
        }
        return super.query(uri, strArr, str, strArr2, str2, cancellationSignal);
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.getType(uri);
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.applyBatch(arrayList);
        }
        return super.applyBatch(arrayList);
    }

    @Override // android.content.ContentProvider
    public Uri canonicalize(Uri uri) {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.canonicalize(uri);
        }
        return super.canonicalize(uri);
    }

    @Override // android.content.ContentProvider
    public Uri uncanonicalize(Uri uri) {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.uncanonicalize(uri);
        }
        return super.uncanonicalize(uri);
    }

    @Override // android.content.ContentProvider
    public AssetFileDescriptor openAssetFile(Uri uri, String str) throws FileNotFoundException {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.openAssetFile(uri, str);
        }
        return super.openAssetFile(uri, str);
    }

    @Override // android.content.ContentProvider
    public AssetFileDescriptor openAssetFile(Uri uri, String str, CancellationSignal cancellationSignal) throws FileNotFoundException {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.openAssetFile(uri, str, cancellationSignal);
        }
        return super.openAssetFile(uri, str, cancellationSignal);
    }

    @Override // android.content.ContentProvider
    public AssetFileDescriptor openTypedAssetFile(Uri uri, String str, Bundle bundle) throws FileNotFoundException {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.openTypedAssetFile(uri, str, bundle);
        }
        return super.openTypedAssetFile(uri, str, bundle);
    }

    @Override // android.content.ContentProvider
    public AssetFileDescriptor openTypedAssetFile(Uri uri, String str, Bundle bundle, CancellationSignal cancellationSignal) throws FileNotFoundException {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.openTypedAssetFile(uri, str, bundle, cancellationSignal);
        }
        return super.openTypedAssetFile(uri, str, bundle, cancellationSignal);
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.openFile(uri, str);
        }
        return super.openFile(uri, str);
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str, CancellationSignal cancellationSignal) throws FileNotFoundException {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.openFile(uri, str, cancellationSignal);
        }
        return super.openFile(uri, str, cancellationSignal);
    }

    @Override // android.content.ContentProvider
    public <T> ParcelFileDescriptor openPipeHelper(Uri uri, String str, Bundle bundle, T t, ContentProvider.PipeDataWriter<T> pipeDataWriter) throws FileNotFoundException {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.openPipeHelper(uri, str, bundle, t, pipeDataWriter);
        }
        return super.openPipeHelper(uri, str, bundle, t, pipeDataWriter);
    }

    @Override // android.content.ContentProvider
    public boolean refresh(Uri uri, Bundle bundle, CancellationSignal cancellationSignal) {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.refresh(uri, bundle, cancellationSignal);
        }
        return super.refresh(uri, bundle, cancellationSignal);
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.call(str, str2, bundle);
        }
        return super.call(str, str2, bundle);
    }

    @Override // android.content.ContentProvider
    public String[] getStreamTypes(Uri uri, String str) {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.getStreamTypes(uri, str);
        }
        return super.getStreamTypes(uri, str);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.insert(uri, contentValues);
        }
        return null;
    }

    @Override // android.content.ContentProvider, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        ContentProvider contentProvider = this.mRealContentProvider;
        if (contentProvider != null) {
            contentProvider.onTrimMemory(i);
        }
    }

    @Override // android.content.ContentProvider, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        ContentProvider contentProvider = this.mRealContentProvider;
        if (contentProvider != null) {
            contentProvider.onLowMemory();
        }
    }

    @Override // android.content.ContentProvider
    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.bulkInsert(uri, contentValuesArr);
        }
        return super.bulkInsert(uri, contentValuesArr);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.delete(uri, str, strArr);
        }
        return 0;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        ContentProvider realContentProvider = getRealContentProvider();
        if (realContentProvider != null) {
            return realContentProvider.update(uri, contentValues, str, strArr);
        }
        return 0;
    }

    private ContentProvider getRealContentProvider() {
        BundleLoadManager d;
        if (this.mRealContentProvider == null && (d = BundleExtension.d()) != null && !TextUtils.isEmpty(this.mRealModuleName)) {
            d.loadInstalledModules(Collections.singletonList(this.mRealModuleName));
        }
        return this.mRealContentProvider;
    }

    private ContentProvider createRealContentProvider(ClassLoader classLoader) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String str;
        Context context = getContext();
        ContentProvider contentProvider = null;
        if (context == null || (str = this.mRealContentProviderClassName) == null) {
            LogUtil.a(TAG, "Cause of null context, we can't create real provider ", this.mRealContentProviderClassName);
            return null;
        }
        Object newInstance = classLoader.loadClass(str).newInstance();
        if (newInstance instanceof ContentProvider) {
            contentProvider = (ContentProvider) newInstance;
            contentProvider.attachInfo(context, this.mProviderInfo);
        }
        LogUtil.d(TAG, "Success to create provider ", this.mRealContentProviderClassName);
        return contentProvider;
    }

    private void createAndActivateModuleProvider(ClassLoader classLoader) throws BundleExtensionException {
        try {
            this.mRealContentProvider = createRealContentProvider(classLoader);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new BundleExtensionException(e);
        }
    }

    private static void putProviders(String str, ContentProviderProxy contentProviderProxy) {
        Map<String, List<ContentProviderProxy>> map = CONTENT_PROVIDER_MAP;
        List<ContentProviderProxy> list = map.get(str);
        if (list == null) {
            list = new ArrayList<>();
            map.put(str, list);
        }
        list.add(contentProviderProxy);
    }

    public static void createAndActivateModuleProviders(ClassLoader classLoader, String str) throws BundleExtensionException {
        List<ContentProviderProxy> list = CONTENT_PROVIDER_MAP.get(str);
        if (list != null) {
            Iterator<ContentProviderProxy> it = list.iterator();
            while (it.hasNext()) {
                it.next().createAndActivateModuleProvider(classLoader);
            }
        }
    }
}
