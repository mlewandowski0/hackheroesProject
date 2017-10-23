package com.hackheroes.lev.achiever.data;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import com.hackheroes.lev.achiever.R;
import com.hackheroes.lev.achiever.utilities.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * Created by lev on 23.10.2017.
 */

public class dataManager
{
    private static final dataManager ourInstance = new dataManager();
    static dataManager getInstance() {
        return ourInstance;
    }
    public static void initialize(Resources resources, SharedPreferences preferences) {
        userValues   = preferences;
        appResources = resources;

        todosPlannedArray   = new ArrayList<>();
        todoData            = new ArrayList<>();
        goalHeaderData      = new ArrayList<>();
        achievedNameOfGoals = new ArrayList<>();
        achievedTODOs       = new HashMap<>();
        goalContentData     = new HashMap<>();

        getDataFromSQLdb();
        // if there is none value that we are interested in
        // we want to welcome user

        if (!userValues.contains(resources.getString(R.string.XP)))
        {
            // put into shared preferences few values that
            // are important for out app
            SharedPreferences.Editor editor = userValues.edit();
            editor.putInt(appResources.getString(R.string.XP),                    appResources.getInteger(R.integer.blank));
            editor.putInt(appResources.getString(R.string.actualXP),              appResources.getInteger(R.integer.blank));
            editor.putInt(appResources.getString(R.string.GoalsAchieved),         resources.getInteger(R.integer.blank));
            editor.putInt(appResources.getString(R.string.XPSubtract),            resources.getInteger(R.integer.XPSubtractValue));
            editor.putInt(appResources.getString(R.string.XPPerGoals),            resources.getInteger(R.integer.XPPerGoalsValue));
            editor.putInt(appResources.getString(R.string.XPPerTODO),             resources.getInteger(R.integer.XPPerTODOValue));
            editor.putInt(appResources.getString(R.string.level),                 resources.getInteger(R.integer.blank));
            editor.putInt(appResources.getString(R.string.TODOsAchieved),         resources.getInteger(R.integer.blank));
            editor.putInt(appResources.getString(R.string.XPNeeded),              resources.getInteger(R.integer.XPNeededForLevel));
            editor.putString(appResources.getString(R.string.whichTheme),         resources.getString(R.string.defaultTheme));
            editor.putString(appResources.getString(R.string.dayOrNight),         resources.getString(R.string.dayMode));
            editor.putString(appResources.getString(R.string.notificationsMode),  resources.getString(R.string.offNotifications));

            // let's bring changes to life !
            editor.apply();
        }
    }

    public static void setAdapter(TODOAdapter todoAdpt)
    {
        todoAdapter = todoAdpt;
    }

    public static void setAdapter(GoalAdapter goalAdpt)
    {
        goalAdapter = goalAdpt;
    }

    public static void setAdapter(AchievedAdapter achivAdpt)
    {
        achivAdapter = achivAdpt;
    }

    public static void notifyOnDataChange()
    {
        if (todoAdapter != null)
            todoAdapter.notifyDataSetChanged();

        if (goalAdapter != null)
            goalAdapter.notifyDataSetChanged();
    }

    public static int  getInteger(String key)
    {
        if (!userValues.contains(key))
            exceptionResponser.response(new Exception("there is no such a integer binded to key " + key + " in shared preferences"));

        return userValues.getInt(key, 0);
    }

    public static String getString(String key)
    {
        if (!userValues.contains(key))
            exceptionResponser.response(new Exception("there is no such a string binded to key " + key + " in shared preferences"));
        return userValues.getString(key, "");
    }

    public static void putInteger(String key, int value)
    {
        SharedPreferences.Editor editor = userValues.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void putString(String key, String value)
    {
        SharedPreferences.Editor editor = userValues.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setStringCache(String key, String value)
    {
        stringCache.put(key, value);
    }

    public static String getStringCache(String key)
    {
        return stringCache.get(key);
    }

    public static ArrayList<String> getTodosPlannedArray()
    {
        return todosPlannedArray;
    }

    public static void              clearTodosPlannedArray(String goalName)
    {
        // let me copy all the todos marked to actual database of todoData
        for (String e : todosPlannedArray)
            todoData.add( new TODOData(e, goalName));
        todoAdapter.notifyDataSetChanged();
        todosPlannedArray.clear();
    }

    public static int               getTodosPlannedArraySize() { return  todosPlannedArray.size(); }

    public static ArrayList<TODOData> getTodoData() {
        return todoData;
    }

    public static HashMap<String, List<String>> getGoalContentData() {
        return goalContentData;
    }

    public static ArrayList<String> getGoalHeaderData() {
        return goalHeaderData;
    }

    public static ArrayList getAchievedNameOfGoals()
    {
        return achievedNameOfGoals;
    }

    public static HashMap<String, List<String>> getAchievedTODOs() {
        return achievedTODOs;
    }

    public static void notifyAchievedAdapter()
    {
        if (achivAdapter != null)
            achivAdapter.notifyDataSetChanged();
    }
    public  static void addTODOToAchieved(String goal,String todo)
    {
        Calendar calendar = Calendar.getInstance();

        String today = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) + " - " +
                       Integer.toString(calendar.get(Calendar.MONTH) +1) + " - " +
                       Integer.toString(calendar.get(Calendar.YEAR)) + "    " +
                        Integer.toString(Calendar.HOUR) + ":" + Integer.toString(Calendar.MINUTE);

        List<String> list = achievedTODOs.get(goal);
        if (list == null)
            list = new ArrayList<>();
        list.add(today);
        list.add(todo);
        achievedTODOs.put(goal,list);
        achievedNameOfGoals.add(goal);
        notifyAchievedAdapter();
    }

    public static void addXP(int howMany, boolean forTODO, boolean forGoal)
    {
        int newXPVal = userValues.getInt(appResources.getString(R.string.actualXP),0) + howMany;
        SharedPreferences.Editor editor = userValues.edit();

        if (newXPVal > userValues.getInt(appResources.getString(R.string.XPNeeded),0))
        {
            newXPVal %= userValues.getInt(appResources.getString(R.string.XPNeeded), 1);
            int level = userValues.getInt(appResources.getString(R.string.level),0) + 1;
            editor.putInt(appResources.getString(R.string.level), level);
        }

        if (forTODO)
        {
            int TODODone = userValues.getInt(appResources.getString(R.string.TODOsAchieved),0) + 1;
            editor.putInt(appResources.getString(R.string.TODOsAchieved), TODODone);
        }
        if (forGoal)
        {
            int TODODone = userValues.getInt(appResources.getString(R.string.GoalsAchieved),0) + 1;
            editor.putInt(appResources.getString(R.string.GoalsAchieved), TODODone);
        }


        int XpVal = userValues.getInt(appResources.getString(R.string.addNewGoalString),0) + howMany ;
        editor.putInt(appResources.getString(R.string.XP), XpVal);
        editor.putInt(appResources.getString(R.string.actualXP), newXPVal);
        editor.apply();
    }

    private static void  getDataFromSQLdb()
    {
        // TODO ! save values in database
    }
    private dataManager() {}

    // maps with data for listViews
    private static ArrayList<TODOData>           todoData;
    private static ArrayList<String>             goalHeaderData;
    private static HashMap<String, List<String>> goalContentData;
    private static ArrayList<String>             achievedNameOfGoals;
    private static HashMap<String, List<String>> achievedTODOs;

    // array that is present in addNewGoalDial but we
    // want to have access to it from other activities
    private static ArrayList<String> todosPlannedArray;

    // level, xp and other  stuff
    private static Resources         appResources;
    private static SharedPreferences userValues;

    private static GoalAdapter      goalAdapter; // <- this is memory leak !
    private static TODOAdapter      todoAdapter;
    private static AchievedAdapter  achivAdapter;

    // cuz everyone love caches and hashmaps
    private static HashMap<String, String> stringCache;

    // constants
    private static int defaultValue = 0;


}
