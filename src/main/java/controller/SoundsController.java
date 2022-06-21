package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import view.sounds.SoundManager;
import view.sounds.SoundManager.Sounds;

/**
 * It handles sounds putting a cllodown
 * between two sounds of the same type.
 *
 */
public class SoundsController {
	private Map<Sounds, Cooldown> timers;
	private SoundManager soundManager;
	private Random rnd;

	public SoundsController() {
		this.timers = new HashMap<>();
		this.soundManager = new SoundManager();
		this.rnd = new Random();
	}
	
	/**
	 * This function is called by Controller every game tick.
	 */
	public void controllerTick() {
		this.timers.forEach((c, sc) -> { sc.tick(); });
		this.timers.entrySet().removeIf(e -> e.getValue().isCooldownOver());
	}
	
	/**
	 * Plays a sound only if the cooldown corresponding to
	 * its soundType is over.
	 * @param soundType: the type of the sound to be played.
	 * @return true if the sound has been played, otherwise false.
	 */
	public boolean playSound(final Sounds soundType) {
		if (soundType.equals(Sounds.RIFLE_FIRING)) {
			this.forcePlaySound(soundType);
		} else if (soundType.equals(Sounds.RELOAD)) {
			this.forcePlaySound(soundType);
		} else if ((soundType.equals(Sounds.HURT_1) ||
				soundType.equals(Sounds.HURT_2) ||
				soundType.equals(Sounds.HURT_3)) &&
				(!this.timers.containsKey(Sounds.HURT_1) && !this.timers.containsKey(Sounds.HURT_2) && !this.timers.containsKey(Sounds.HURT_3))) {
			int n = this.rnd.nextInt(3);
			switch (n) {
			case 0:
				this.timers.put(Sounds.HURT_1, new Cooldown(50));	/* Temporal gap between two sounds of the same type (TODO: too high, fix) */
				this.soundManager.playSound(Sounds.HURT_1);
				return true;
			case 1:
				this.timers.put(Sounds.HURT_1, new Cooldown(50));	/* Temporal gap between two sounds of the same type (TODO: too high, fix) */
				this.soundManager.playSound(Sounds.HURT_2);
				return true;
			case 2:
				this.timers.put(Sounds.HURT_1, new Cooldown(50));	/* Temporal gap between two sounds of the same type (TODO: too high, fix) */
				this.soundManager.playSound(Sounds.HURT_3);
				return true;
			default:
				break;
			}
		} else if ((soundType.equals(Sounds.JUMP_1) ||
				soundType.equals(Sounds.JUMP_2) ||
				soundType.equals(Sounds.JUMP_3)) &&
				(!this.timers.containsKey(Sounds.JUMP_1) && !this.timers.containsKey(Sounds.JUMP_2) && !this.timers.containsKey(Sounds.JUMP_3))) {
			int n = this.rnd.nextInt(3);
			switch (n) {
			case 0:
				this.timers.put(Sounds.JUMP_1, new Cooldown(50));	/* Temporal gap between two sounds of the same type (TODO: too high, fix) */
				this.soundManager.playSound(Sounds.JUMP_1);
				return true;
			case 1:
				this.timers.put(Sounds.HURT_1, new Cooldown(50));	/* Temporal gap between two sounds of the same type (TODO: too high, fix) */
				this.soundManager.playSound(Sounds.JUMP_2);
				return true;
			case 2:
				this.timers.put(Sounds.HURT_1, new Cooldown(50));	/* Temporal gap between two sounds of the same type (TODO: too high, fix) */
				this.soundManager.playSound(Sounds.JUMP_3);
				return true;
			default:
				break;
			}
		} else if (!this.timers.containsKey(soundType)) {
			/* Se un suono di tipo soundType non è stato fatto ancora partire, lo fa partire TODO: translate comment */
			this.timers.put(soundType, new Cooldown(10));	/* Temporal gap between two sounds of the same type (TODO: too high, fix) */
			this.soundManager.playSound(soundType);
			return true;
		}
		return false;
	}
	
	public void forcePlaySound(final Sounds soundType) {
		this.soundManager.playSound(soundType);
	}
	
	public void stopSound(final Sounds soundType) {
		this.soundManager.stopSound(soundType);
	}
}
