package com.pie.tlatoani.Tablist;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

/**
 * Created by Tlatoani on 11/25/16.
 */
public class ExprNewTablist extends SimpleExpression<Tablist> {
    @Override
    protected Tablist[] get(Event event) {
        return new Tablist[]{new Tablist()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Tablist> getReturnType() {
        return Tablist.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "new tablist";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
