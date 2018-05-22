package com.netoaoh.pulsar.engine.graphics;

import com.netoaoh.pulsar.engine.components.Renderer;

public interface IRenderingEngine {
    void initialize();
    void render();
    void shutdown();
    void addToRenderingEngine(Material material, Renderer renderer);
}
