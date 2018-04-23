package com.androidjson.inbox.data;

public class FactResponse
{

    String text, type;
    boolean found;
    int year;
    double number;

    public FactResponse(String text, double number)
    {
        this.text = text;
        this.number = number;
    }

    public String getText()
    {
        return text;
    }

    public String getType()
    {
        return type;
    }

    public boolean isFound()
    {
        return found;
    }

    public int getYear()
    {
        return year;
    }

    public double getNumber()
    {
        return number;
    }
}
