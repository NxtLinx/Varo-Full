package events;import org.bukkit.entity.Player;import org.bukkit.event.Listener;import org.bukkit.event.entity.PlayerDeathEvent;public class DeathListener implements Listener {    public void onDeath(PlayerDeathEvent e) {        Player p = e.getEntity().getPlayer();        Player k = e.getEntity().getKiller();        if(k == null) {        }    }}