package com.hackheroes.lev.achiever.data;

import android.provider.BaseColumns;

/**
 * Created by lev on 23.10.2017.
 */

public class dbContract
{
    private dbContract() {}


    public static class entry implements BaseColumns
    {
        public static final String TABLE_NAME       = "Goals";
        public static final String COLUMN_NAME_TASK = "name";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_WHY  = "why";
        public static final String COLUMN_NAME_ = "date";

    }

}
