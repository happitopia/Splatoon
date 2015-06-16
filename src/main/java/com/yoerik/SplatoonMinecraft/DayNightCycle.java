package com.yoerik.SplatoonMinecraft;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;



public class DayNightCycle extends BukkitRunnable{
		static long startTime=System.currentTimeMillis();
		static World w=Bukkit.getServer().getWorld("world");

		
	static public void SetDayNightCycle(){
		startTime= System.currentTimeMillis();
		w.setTime(0);
	}

	    @Override
	    public void run() {
	    long currentTime= System.currentTimeMillis();
	    long convert=18L;
	    long timecalc=(currentTime-startTime)/convert;
	    long currenttime=w.getTime();
	    if (currenttime<timecalc)
	    {w.setTime(timecalc);}

	    }
	 
	}

