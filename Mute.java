import java.awt.Color;
import java.sql.Date;
import java.text.SimpleDateFormat;

import me.nervixo.bot.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Mute extends ListenerAdapter{
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if (args[0].equalsIgnoreCase(Main.prefix + "Mute")) {
			if(args.length<= 1){
				sendErrorMessage(event.getChannel(), event.getMember());
			}else{
				Member target= event.getMessage().getMentionedMembers().get(0);
				
				Role muted = event.getGuild().getRolesByName("Muted", true).get(0);
				
				event.getGuild().getController().addSingleRoleToMember(target, muted).queue();
				
				if(args.length >= 3){
					String reason = "";
					for(int i = 2; i < args.length; i++){
						reason += args[i] + "";
					}
					log(target, event.getMember(), reason, event.getGuild().getTextChannelById("585082728186118165"));
				}
			}
			
		}
	}
	
	public void sendErrorMessage(TextChannel channel, Member member){
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle("Invalid Usage!");
		builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
		builder.setColor(Color.BLUE);
		builder.setDescription("{} = Required, [] = Optional");
		builder.addField("Proper Usage: %Mute [@user] [reason]", "", false);
    }
	
	public void log(Member muted, Member muter, String reason, TextChannel channel){
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle("Mute report");
		builder.setColor(Color.blue);
		builder.addField("Punished User", muted.getAsMention(), false);
		builder.addField("Staff", muter.getAsMention(), false);
		builder.addField("Reason", reason, false);
		builder.addField("Date", sdf.format(date), false);
		builder.addField("Time", stf.format(date), false);
		channel.sendMessage(builder.build()).queue();
    }
}
