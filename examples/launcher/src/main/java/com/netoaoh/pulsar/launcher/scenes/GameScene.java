package com.netoaoh.pulsar.launcher.scenes;

import com.netoaoh.pulsar.engine.components.*;
import com.netoaoh.pulsar.engine.core.GameObject;
import com.netoaoh.pulsar.engine.core.IScene;
import com.netoaoh.pulsar.engine.graphics.Material;
import com.netoaoh.pulsar.engine.graphics.Sprite;
import com.netoaoh.pulsar.engine.graphics.SpriteAnimation;
import com.netoaoh.pulsar.engine.graphics.Texture;
import com.netoaoh.pulsar.engine.math.Matrix4f;
import com.netoaoh.pulsar.engine.math.Vector2f;
import com.netoaoh.pulsar.engine.math.Vector3f;
import com.netoaoh.pulsar.engine.physics.Physics;
import com.netoaoh.pulsar.engine.utils.Convert;
import org.jbox2d.dynamics.BodyType;

public class GameScene extends IScene {

    public GameScene(){
        super("Game Scene");
    }

    public void start() {

        GameObject cameraObject = new GameObject("Camera");
        cameraObject.addComponent(new Camera(new Matrix4f().initOrthographic(-80.0f, 80.0f, -60.0f, 60.0f, -1, 100)));
        cameraObject.addComponent(new ScriptComponent("camera.js"));
        cameraObject.addComponent(new AudioListenerComponent());
        addToScene(cameraObject);

        GameObject obj = new GameObject("player");
        obj.addComponent(new SpriteRenderer(new Sprite(4,4), new Material("Default", new Texture("circle.png", 1))));
        obj.addComponent(new AudioSourceComponent("bounce.wav"));
        obj.addComponent(new ScriptComponent("player.js"));
        obj.addComponent(new BoxCollider(20, 20));

        /*GameObject floor = new GameObject("Chão");
        BoxCollider boxCollider = new BoxCollider(200, 1);
        boxCollider.getRigidBody().setDensity(1000);
        boxCollider.getRigidBody().setRestitution(0);
        boxCollider.getRigidBody().setBodyType(BodyType.STATIC);
        floor.addComponent(boxCollider);
        addToScene(floor);



        boxCollider.getRigidBody().getBody().setTransform(Convert.convertToVec2(new Vector2f(0, -50)),boxCollider.getRigidBody().getBody().getAngle());
        //floor.transform.setPos(new Vector3f(0,-10,0));
*/
        addToScene(obj);

        //root.transform.setPos(new Vector3f(5,8,2));
        root.start();
    }
}
