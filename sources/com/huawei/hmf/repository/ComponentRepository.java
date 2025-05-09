package com.huawei.hmf.repository;

import android.os.Looper;
import com.huawei.hmf.orb.RemoteConnector;
import com.huawei.hmf.orb.RemoteRepository;
import com.huawei.hmf.orb.exception.ConnectRemoteException;
import com.huawei.hmf.repository.impl.ComponentRepositoryImpl;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.Tasks;
import java.util.concurrent.ExecutionException;

/* loaded from: classes4.dex */
public class ComponentRepository {
    private static final ComponentRepositoryImpl IMPL = new ComponentRepositoryImpl();

    /* loaded from: classes9.dex */
    public interface OnCompleted {
        void onResult(RemoteRepository remoteRepository, ConnectRemoteException connectRemoteException);
    }

    public static Repository getRepository() {
        return IMPL.getRepository();
    }

    public static RemoteRepository getRepository(RemoteConnector remoteConnector) throws ConnectRemoteException {
        RemoteRepository remoteRepository;
        synchronized (ComponentRepository.class) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new IllegalStateException("Can not be called on the UI thread");
            }
            try {
                try {
                    remoteRepository = (RemoteRepository) Tasks.await(IMPL.getRepositoryTask(remoteConnector));
                } catch (InterruptedException unused) {
                    throw new ConnectRemoteException(ConnectRemoteException.Status.UnableBindService);
                }
            } catch (ExecutionException e) {
                if (e.getCause() instanceof ConnectRemoteException) {
                    ConnectRemoteException connectRemoteException = (ConnectRemoteException) e.getCause();
                    ConnectRemoteException connectRemoteException2 = connectRemoteException;
                    throw connectRemoteException;
                }
                throw new ConnectRemoteException(ConnectRemoteException.Status.UnableBindService, "Unknown error");
            }
        }
        return remoteRepository;
    }

    public static void getRepository(RemoteConnector remoteConnector, final OnCompleted onCompleted) {
        synchronized (ComponentRepository.class) {
            IMPL.getRepositoryTask(remoteConnector).addOnCompleteListener(new OnCompleteListener<RemoteRepository>() { // from class: com.huawei.hmf.repository.ComponentRepository.1
                @Override // com.huawei.hmf.tasks.OnCompleteListener
                public void onComplete(Task<RemoteRepository> task) {
                    ConnectRemoteException connectRemoteException;
                    if (task.isSuccessful()) {
                        OnCompleted.this.onResult(task.getResult(), null);
                        return;
                    }
                    if (task.getException() instanceof ConnectRemoteException) {
                        connectRemoteException = (ConnectRemoteException) task.getException();
                    } else {
                        connectRemoteException = new ConnectRemoteException(ConnectRemoteException.Status.UnableBindService, "Unknown error");
                    }
                    OnCompleted.this.onResult(null, connectRemoteException);
                }
            });
        }
    }
}
