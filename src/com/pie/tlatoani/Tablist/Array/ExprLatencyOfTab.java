package com.pie.tlatoani.Tablist.Array;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.pie.tlatoani.Tablist.Tablist;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

/**
 * Created by Tlatoani on 7/15/16.
 */
public class ExprLatencyOfTab extends SimpleExpression<Number> {
    private Expression<Number> column;
    private Expression<Number> row;
    private Expression<Tablist> tablistExpression;
    private Expression<Player> playerExpression;

    @Override
    protected Number[] get(Event event) {
        Tablist tablist = tablistExpression != null ? tablistExpression.getSingle(event) : Tablist.getTablistForPlayer(playerExpression.getSingle(event));
        return new Number[]{tablist.arrayTablist.getLatency(column.getSingle(event).intValue(), row.getSingle(event).intValue())};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "latency of tab " + column + ", " + row + " for " + playerExpression;
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        column = (Expression<Number>) expressions[0];
        row = (Expression<Number>) expressions[1];
        tablistExpression = (Expression<Tablist>) expressions[2];
        playerExpression = (Expression<Player>) expressions[3];
        return true;
    }

    public void change(Event event, Object[] delta, Changer.ChangeMode mode) {
        Tablist tablist = tablistExpression != null ? tablistExpression.getSingle(event) : Tablist.getTablistForPlayer(playerExpression.getSingle(event));
        tablist.arrayTablist.setLatency(column.getSingle(event).intValue(), row.getSingle(event).intValue(), ((Number) delta[0]).intValue());
    }

    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Number.class);
        }
        return null;
    }
}
