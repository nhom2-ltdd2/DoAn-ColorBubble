package vn.edu.tdc.nhom2.nhatky.Models;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by TIEN on 3/17/2018.
 */

public class Preferences {
    private static String PREFS_NAME = "Account";

    public Preferences() {

    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static String getLockCode(Context context) {
        return getPrefs(context).getString("LockCode", "Null");
    }

    public static void setLockCode(Context context, String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("LockCode", input);
        editor.commit();
    }
}
