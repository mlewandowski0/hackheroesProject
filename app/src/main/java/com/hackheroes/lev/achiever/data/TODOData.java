package com.hackheroes.lev.achiever.data;

/**
 * Created by lev on 21.10.2017.
 */

public class TODOData
{
    private String whatToDo;
    private String Goal;

    public TODOData(String task, String Goal)
    {
        this.whatToDo = task;
        this.Goal     = Goal;
    }

    public String getTask()
    {
        return whatToDo;
    }
    public String getGoal()
    {
        return Goal;
    }


}
