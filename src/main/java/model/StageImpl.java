package model;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.management.InstanceNotFoundException;
import model.character.Enemy;
import model.character.Player;
import model.character.Player.PlayerBuilder;
import model.character.tools.health.SimpleHealth;
import model.map.Level;
import model.weapons.Bullet;
import model.weapons.P2020;
import util.Vector2D;
import util.map.TextMap;

/**
 * TODO: javadoc.
 *
 */
public class StageImpl {

    private final Player player;
    //private final Enemy enemy;
    private final Collection<Enemy> enemies;
    private final Collection<Bullet> bullets = null;
    private final Level level;

    /**
     * The stage constructor.
     * 
     * @param textMap
     * @throws IOException if the txt map sheet doesn't exist.
     * @throws InstanceNotFoundException 
     */
    public StageImpl(final TextMap textMap) throws IOException, InstanceNotFoundException {        
        this.level = new Level(Stream.of("src/main/resources/map.txt"
                ,"src/main/resources/map2.txt"
                ,"src/main/resources/map3.txt").collect(Collectors.toList()));
        //this.enemy = new Enemy(level.getEnemySpawn(), new Vector2D(1, 1), new SimpleHealth());
        this.enemies = new LinkedList<Enemy>();
        addEnemies();
        this.player = new PlayerBuilder()
                .hitbox(new Vector2D(1, 1.5))
                .position(level.getPlayerSpawn())
                .weapon(new P2020())
                .health(new SimpleHealth())
                .lives(3)
                .build();
    }

    private void addEnemies() {
        for (Vector2D pos : level.getEnemiesSpawn()) {
            enemies.add(new Enemy(pos, new Vector2D(1, 1.5), new SimpleHealth()));
        }
    }

    /*
    public void setPlayer(final Player player) {
        this.player = player;
    }
    */

    public Player getPlayer() {
        return this.player;
    }

    public Level getLevel() {
        return this.level;
    }

	/*public Enemy getEnemy() {
		// TODO Auto-generated method stub
		return this.enemy;
	}*/

    public Collection<Enemy> getEnemies(){
        return this.enemies;
    }

}
