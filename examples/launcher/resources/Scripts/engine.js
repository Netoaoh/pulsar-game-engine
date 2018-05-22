// Components
var Camera = Java.type("com.netoaoh.pulsar.engine.components.Camera");
var AnimationComponent = Java.type("com.netoaoh.pulsar.engine.components.AnimationComponent");
var SpriteRenderer = Java.type("com.netoaoh.pulsar.engine.components.SpriteRenderer");
var ScriptComponent = Java.type("com.netoaoh.pulsar.engine.components.ScriptComponent");
var Transform = Java.type("com.netoaoh.pulsar.engine.components.Transform");
/*var Collider = Java.type("com.netoaoh.pulsar.engine.components.Collider");
var SphereCollider = Java.type("com.netoaoh.pulsar.engine.components.SphereCollider");
var BoxCollider = Java.type("com.netoaoh.pulsar.engine.components.BoxCollider");
var PlaneCollider = Java.type("com.netoaoh.pulsar.engine.components.PlaneCollider");*/
var Collider = Java.type("com.netoaoh.pulsar.engine.components.Collider");
var BoxCollider = Java.type("com.netoaoh.pulsar.engine.components.BoxCollider");
var AudioSourceComponent = Java.type("com.netoaoh.pulsar.engine.components.AudioSourceComponent");
var AudioListenerComponent = Java.type("com.netoaoh.pulsar.engine.components.AudioListenerComponent");

// Core
var Time = Java.type("com.netoaoh.pulsar.engine.core.Time");
var Input = Java.type("com.netoaoh.pulsar.engine.core.Input");
var KeyCode = Java.type("com.netoaoh.pulsar.engine.core.KeyCode");
var GameObject = Java.type("com.netoaoh.pulsar.engine.core.GameObject");
var Application = Java.type("com.netoaoh.pulsar.engine.core.Application");

// DataTypes
//var Vec2 = Java.type("glm.vec._2.Vec2");
//var Vec3 = Java.type("glm.vec._3.Vec3");
var Vector2f = Java.type("com.netoaoh.pulsar.engine.math.Vector2f");
var Vector3f = Java.type("com.netoaoh.pulsar.engine.math.Vector3f");
var Color = Java.type("com.netoaoh.pulsar.engine.math.Color");
var Quaternion = Java.type("com.netoaoh.pulsar.engine.math.Quaternion");
var Matrix4f = Java.type("com.netoaoh.pulsar.engine.math.Matrix4f");
//var Quat = Java.type("glm.quat.Quat");
//var Mat4 = Java.type("glm.mat._4.Mat4");

// Graphics
var Material = Java.type("com.netoaoh.pulsar.engine.graphics.Material");
var Shader = Java.type("com.netoaoh.pulsar.engine.graphics.Shader");
var Texture = Java.type("com.netoaoh.pulsar.engine.graphics.Texture");
var Sprite = Java.type("com.netoaoh.pulsar.engine.graphics.Sprite");
var SpriteAnimation = Java.type("com.netoaoh.pulsar.engine.graphics.SpriteAnimation");
var Window = Java.type("com.netoaoh.pulsar.engine.graphics.Window");

// Physics
var RigidBody = Java.type("com.netoaoh.pulsar.engine.physics.RigidBody");
var Collision = Java.type("com.netoaoh.pulsar.engine.physics.Collision");
/*var Collision = Java.type("com.netoaoh.pulsar.engine.physics.Collision");
var Constrains = Java.type("com.netoaoh.pulsar.engine.physics.Constrains");
var PhysicsWorld = Java.type("com.netoaoh.pulsar.engine.physics.PhysicsWorld");
var RigidBody = Java.type("com.netoaoh.pulsar.engine.physics.RigidBody");*/

//  Audio
var AudioSource = Java.type("com.netoaoh.pulsar.engine.audio.AudioSource");

// Utils
var FileManager = Java.type("com.netoaoh.pulsar.engine.utils.FileManager");

// Funções

function Instantiate(object, position, rotation){
	object.transform.setPos(position);
	object.transform.setRot(rotation);
	GameObject.root.AddChild(object);
}

function GetGlobal(name){
	return ScriptComponent.getGlobalData(name);
}

function SetGlobal(name, object){
	ScriptComponent.setGlobalData(name, object);
}