package id.sch.smktelkom_mlg.privateassignment.xirpl119.seemovie;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by A455L on 14/05/2017.
 */
public class IntroManager {
        private static final String PREF_NAME = "androidhive-welcome";
        private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
        SharedPreferences pref;
        SharedPreferences.Editor editor;
        Context context;
        int PRIVATE_MODE = 0;

                public IntroManager(Context context) {
                this.context = context;
                pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
                editor = pref.edit();
            }
                    public boolean isFirstTimeLaunch() {
                return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
            }

                public void setFirstTimeLaunch(boolean isFirstTime) {
                editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
                editor.commit();
            }
    }
