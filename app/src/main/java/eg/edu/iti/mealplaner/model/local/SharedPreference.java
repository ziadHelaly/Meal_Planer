package eg.edu.iti.mealplaner.model.local;

import android.content.Context;
import android.content.SharedPreferences;

import eg.edu.iti.mealplaner.utilies.Const;

public class SharedPreference {

    SharedPreferences preferences;

    public SharedPreference(Context context) {
        preferences = context.getSharedPreferences(Const.SHARED_NAME, Context.MODE_PRIVATE);
    }

    public void saveString(String str, String key) {
        preferences.edit().putString(key, str).apply();
    }

    public String getString(String key) {
        return preferences.getString(key, "NULL");
    }

    public void removeString(String key) {
        preferences.edit().remove(key).apply();
    }

    public void setBoolean(boolean bool, String key) {
        preferences.edit().putBoolean(key,bool).apply();
    }
    public boolean getBoolean(String key) {
        return preferences.getBoolean(key,false);
    }

}
