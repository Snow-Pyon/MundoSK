package com.pie.tlatoani.Achievement;

import org.bukkit.Achievement;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;

public class EvtAchAward extends SkriptEvent {
	private Literal<Achievement> ach;

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "border stabilize";
	}

	@Override
	public boolean check(Event arg0) {
		if (arg0 instanceof PlayerAchievementAwardedEvent) {
			if (ach != null) {
				if (((PlayerAchievementAwardedEvent) arg0).getAchievement() == ach.getSingle()) return true;
				else return false;
			} else return true;
		} else return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Literal<?>[] lit, int arg1, ParseResult arg2) {
		ach = (Literal<Achievement>) lit[0];
		return true;
	}

}
