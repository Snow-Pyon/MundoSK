package com.pie.tlatoani.Socket;

import org.bukkit.event.Event;

import com.pie.tlatoani.Mundo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprMotdOfServer extends SimpleExpression<String>{
	private Expression<String> host;
	private Expression<Number> port;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		host = (Expression<String>) exprs[0];
		port = (Expression<Number>) exprs[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "raw information of server";
	}

	@Override
	@Nullable
	protected String[] get(Event e) {
		String host = this.host.getSingle(e);
		Integer port = (this.port != null ? this.port.getSingle(e) : 25565).intValue();
		String motd = "";
		try {
			Socket sock = new Socket(host, port);
			DataOutputStream out = new DataOutputStream(sock.getOutputStream());
			DataInputStream in = new DataInputStream(sock.getInputStream());
			out.write(0xFE);
			boolean a = true;
			int b;
			in.read();
			in.read();
			in.read();
			in.read();
			List<Integer> listofint = new ArrayList<Integer>();
			while ((b = in.read()) != -1) {
				if (a) listofint.add(b);
				debug("b: " + b);
				a = !a;
			}
			int j = listofint.size();
			int l = 0;
			while (l < 2) {
				j--;
				Integer k = listofint.get(j);
				debug("k: " + k);
				if (k.equals(167)) {
					debug("Found 167");
					l++;
				}
				listofint.remove(j);
			}
			for (int i = 0; i < listofint.size(); i++) {
				motd += (char) listofint.get(i).intValue();
			}
			debug(motd);
			sock.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return new String[]{motd};
	}
	
	private static void debug(String msg) {
		Mundo.debug(ExprMotdOfServer.class, msg);
	}

}
