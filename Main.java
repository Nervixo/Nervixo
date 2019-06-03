import javax.security.auth.login.LoginException;

import me.nervixo.bot.commands.Clear;
import me.nervixo.bot.commands.JoinMessage;
import me.nervixo.bot.commands.Mute;
import me.nervixo.bot.commands.Tickets;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

public class Main {
	public static JDA jda;
	public static String prefix = "&";
	
	public static void main(String[] args) throws LoginException {
		jda = new JDABuilder(AccountType.BOT).setToken("NTg1MTQwNTkyNDg5NTI5NTM2.XPVIxQ.pMAJRy2C3PuVj_F6Bbj0MAV360E").build();
		jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
		jda.getPresence().setGame(Game.playing("BladeMC"));
		
		jda.addEventListener(new Clear());
		jda.addEventListener(new Mute());
	}
}
