package vn.edu.tdc.nhom2.colorbubble.Model;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private static String PREFS_NAME = "Account";

    public Preferences() {

    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static String getName(Context context) {
        return getPrefs(context).getString("Name", "Null");
    }

    public static void setName(Context context, String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("Name", input);
        editor.commit();
    }

    private static SharedPreferences getPrefsSetting(Context context) {
        return context.getSharedPreferences("datasetting", Context.MODE_PRIVATE);
    }

    public static String getQuery(Context context, String name) {
        return getPrefsSetting(context).getString(name, "Null");
    }
}