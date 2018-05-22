/**
 * Project: PulsarGameEngine
 * Filename: PostProcessManager.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.graphics;

import java.util.ArrayList;
import java.util.HashMap;

public class PostProcessManager {

	private static PostProcessManager instance = new PostProcessManager();
	public HashMap<String, PostProcessEffect> effects;

	private PostProcessManager() {
		effects = new HashMap<String, PostProcessEffect>();
	}

	public void initialize() {

		if (effects.size() == 0) {
			addEffect(new PostProcessEffect("Default", new Shader("Default")));
			effects.get("Default").initialize();
		}
	}

	public void prepare() {
		ArrayList<PostProcessEffect> postProcess = new ArrayList<PostProcessEffect>(effects.values());

		for (int i = 0; i < postProcess.size(); i++) {
			if (postProcess.get(i).enabled) {
				postProcess.get(i).prepare();
				break;
			}
		}
	}

	public void apply() {

		ArrayList<PostProcessEffect> postProcess = new ArrayList<PostProcessEffect>(effects.values());

		for (int i = 0; i < postProcess.size(); i++) {
			if (postProcess.get(i).enabled)
				postProcess.get(i).finish();

			if (i < postProcess.size() - 1 && postProcess.get(i + 1).enabled)
				postProcess.get(i + 1).prepare();

			if (postProcess.get(i).enabled)
				postProcess.get(i).render();

			if (i < postProcess.size() - 1 && postProcess.get(i + 1).enabled) {
				postProcess.get(i + 1).finish();
				postProcess.get(i + 1).render();
			}
		}
	}

	public void addEffect(PostProcessEffect effect) {
		if (effect == null)
			return;

		effect.initialize();
		effects.put(effect.name, effect);
	}

	public void removeEffect(String name) {
		if (effects.size() <= 1 || name == "Default")
			return;

		effects.remove(name);
	}

	public void activeEffect(String name) {
		if (effects.containsKey(name))
			effects.get(name).enabled = true;
	}

	public void deactiveEffect(String name) {
		if (effects.containsKey(name) && name != "Default")
			effects.get(name).enabled = false;
	}

	public void clear() {
		ArrayList<PostProcessEffect> postProcess = new ArrayList<PostProcessEffect>(effects.values());

		for (int i = 0; i < postProcess.size(); i++) {
			postProcess.get(i).clear();
		}

		postProcess.clear();
		effects.clear();
	}

	public static PostProcessManager getInstance() {
		return instance;
	}
}
