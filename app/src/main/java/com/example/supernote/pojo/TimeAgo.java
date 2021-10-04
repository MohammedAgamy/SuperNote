package com.example.supernote.pojo;

import android.provider.ContactsContract;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeAgo {
    public  String getTimeAgo(long duration)
    {
        Date now = new Date();

        long second =TimeUnit.MICROSECONDS.toSeconds(now.getTime() - duration) ;
        long minutes =TimeUnit.MICROSECONDS.toMinutes(now.getTime() - duration) ;
        long hours =TimeUnit.MICROSECONDS.toHours(now.getTime() - duration) ;
        long day =TimeUnit.MICROSECONDS.toDays(now.getTime() - duration) ;

        if(second < 60)
        {
            return "nust now" ;
        }
        else if(minutes  == 1)
        {
            return  "a minute ago" ;
        } else if ( minutes > 1 && minutes < 60)
        {
            return minutes + " minutes ago" ;
        }
        else if(hours == 1)
        {
            return "an hour ago" ;
        }

        else if(hours > 1 && hours < 24)
        {
            return hours + " an hours ago";
        }
        else if (day == 1 )
        {
            return "a day ago" ;
        }
        else
        {
            return day + "a days ago" ;
        }

    }
}
