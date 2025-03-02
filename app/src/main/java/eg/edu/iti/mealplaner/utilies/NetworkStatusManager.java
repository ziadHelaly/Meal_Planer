package eg.edu.iti.mealplaner.utilies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class NetworkStatusManager {

    private static NetworkStatusManager instance;
    private MutableLiveData<Boolean> networkStatus = new MutableLiveData<>();

    private NetworkStatusManager() {}

    public static synchronized NetworkStatusManager getInstance() {
        if (instance == null) {
            instance = new NetworkStatusManager();
        }
        return instance;
    }

    public LiveData<Boolean> getNetworkStatus() {
        return networkStatus;
    }

    public void updateNetworkStatus(boolean isConnected) {
        networkStatus.postValue(isConnected);
    }
}