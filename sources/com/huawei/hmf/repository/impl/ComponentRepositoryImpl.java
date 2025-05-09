package com.huawei.hmf.repository.impl;

import com.huawei.hmf.orb.ConnectionCallbacks;
import com.huawei.hmf.orb.RemoteConnector;
import com.huawei.hmf.orb.RemoteRepository;
import com.huawei.hmf.orb.RemoteRepositoryFactory;
import com.huawei.hmf.orb.exception.ConnectRemoteException;
import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
public final class ComponentRepositoryImpl {
    private volatile Repository defaultRepository;
    private final Object lock = new Object();

    public Repository getRepository() {
        if (this.defaultRepository == null) {
            synchronized (this.lock) {
                if (this.defaultRepository == null) {
                    this.defaultRepository = new RepositoryImpl(true);
                }
            }
        }
        return this.defaultRepository;
    }

    public Task<RemoteRepository> getRepositoryTask(final RemoteConnector remoteConnector) {
        Task<RemoteRepository> task;
        synchronized (this) {
            final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            try {
                final RemoteRepository createRemoteRepository = createRemoteRepository(remoteConnector);
                if (createRemoteRepository.isAlive()) {
                    taskCompletionSource.setResult(createRemoteRepository);
                } else {
                    remoteConnector.addConnectionCallbacks(new ConnectionCallbacks() { // from class: com.huawei.hmf.repository.impl.ComponentRepositoryImpl.1
                        @Override // com.huawei.hmf.orb.ConnectionCallbacks
                        public void onConnected() {
                            remoteConnector.removeConnectionCallbacks(this);
                            if (createRemoteRepository.isAlive()) {
                                taskCompletionSource.setResult(createRemoteRepository);
                            } else {
                                taskCompletionSource.setException(new ConnectRemoteException(ConnectRemoteException.Status.UnableBindService));
                            }
                        }

                        @Override // com.huawei.hmf.orb.ConnectionCallbacks
                        public void onDisconnected() {
                            remoteConnector.removeConnectionCallbacks(this);
                        }

                        @Override // com.huawei.hmf.orb.ConnectionCallbacks
                        public void onConnectionFailed(ConnectRemoteException connectRemoteException) {
                            remoteConnector.removeConnectionCallbacks(this);
                            taskCompletionSource.setException(connectRemoteException);
                        }
                    });
                }
            } catch (ConnectRemoteException e) {
                taskCompletionSource.setException(e);
            }
            task = taskCompletionSource.getTask();
        }
        return task;
    }

    private static RemoteRepository createRemoteRepository(RemoteConnector remoteConnector) throws ConnectRemoteException {
        RemoteRepositoryFactory repositoryFactory = remoteConnector.getRepositoryFactory();
        if (repositoryFactory != null) {
            return repositoryFactory.create(remoteConnector);
        }
        throw new ConnectRemoteException(ConnectRemoteException.Status.UnknownConnector);
    }
}
