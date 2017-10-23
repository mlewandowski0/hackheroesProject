package com.hackheroes.lev.achiever.utilities;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by lev on 17.10.2017.
 */


public class exceptionResponser {

    private static final exceptionResponser ourInstance = new exceptionResponser();
    public static exceptionResponser getInstance() {
        return ourInstance;
    }

    // response to default java exception
    public static void response(Exception exc)
    {
        // not much here
        Log.v(exc.getMessage(), "EXCEPTION");
    }

    // response to our defined exception
    public static void response(appException exc)
    {
        if (exc.getContext() != null)
        {
            // let's make a toast that inform us that
            // something bad happened !
            Toast.makeText(exc.getContext(), exc.getMessage(), Toast.LENGTH_SHORT).show();

            // we should also make some new activity that
            // will show that something bad happened !
            // Intent intent = new Intent(exc.getActivity(), mainMenuActivity.class);
            // startActivity(intent);
        }
        else
            response(new Exception(exc));

    }

    private exceptionResponser() {}
}
