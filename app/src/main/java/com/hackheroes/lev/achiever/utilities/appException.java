package com.hackheroes.lev.achiever.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;

/**
 * Created by lev on 17.10.2017.
 */

public class appException extends Exception{

    public appException(String msg, Context andrContext)
    {
        super(msg);

        if (andrContext != null)
            this.context = andrContext;
    }
    public Context getContext()
    {
        return this.context;
    }

    private Context context   = null;
}
