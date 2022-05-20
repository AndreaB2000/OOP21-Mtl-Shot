package model;

import java.io.IOException;
import java.util.Collection;

import model.character.Player;
import model.character.Player.PlayerBuilder;
import model.character.tools.health.SimpleHealth;
import model.map.Level;
import model.map.Segment;
import model.weapons.Bullet;
import model.weapons.P2020;
import util.Vector2D;
import util.map.TextMap;

/**
 * TODO: javadoc.
 *
 */
public class StageImpl {

    private Player player;
    private final Collection<Bullet> bullets = null;
    private final Level level;

    public StageImpl(final TextMap textMap) throws IOException {        
        this.level = new Level(null);
        this.player = new PlayerBuilder()
                .hitbox(new Vector2D(1, 1))
                .position(level.getPlayerSpawn())
                .weapon(new P2020())
                .health(new SimpleHealth())
                .lives(3)
                .build();
    }

    public void setPlayer(final Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Level getLevel() {
        return this.level;
    }

}
