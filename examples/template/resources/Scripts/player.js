#import <Scripts/Engine.js>

var scale = 0.0;

var body;
var anim;
var audio;

function Awake() { 
	print("Awake");
}

function Update() {
    //print(gameObject.transform.getPos());
	HandleInput();
}

function OnCollisionEnter(collision){

    //body.addForce(new Vec2(600.0 * Time.deltaTime, 0.0));
    //anim.setAnimation("right");
}

function HandleInput(){

    //body = gameObject.getComponent(BoxCollider.class).getRigidBody();
    anim = gameObject.getComponent(AnimationComponent.class);
    audio = gameObject.getComponent(AudioSourceComponent.class);

	//var anim = gameObject.getComponent(AnimationComponent.class);
	//anim.setAnimation("idle");

    /*if(!audio.getSource().isLooping())
        audio.getSource().setLooping(true);

    if(!audio.getSource().isPlaying())
        audio.getSource().play();

    if(Input.getKeyDown(KeyCode.KEY_L))
        audio.getSource().play();

    if(Input.getKeyDown(KeyCode.KEY_P)){
        if(audio.getSource().isPlaying())
            audio.getSource().pause();
        //else
          //  audio.getSource().resume();
    }*/

	if(Input.getKey(KeyCode.KEY_D)){
        //body.addForce(new Vec2(600.0 * Time.deltaTime, 0.0));
		gameObject.transform.translate(60.0 * Time.deltaTime, 0.0, 0.0);
		anim.setAnimation("right");
	} else if(Input.getKey(KeyCode.KEY_A)){
		gameObject.transform.translate(-60.0 * Time.deltaTime, 0.0, 0.0);
		anim.setAnimation("left");
	} else if(Input.getKey(KeyCode.KEY_W)){
        //body.addForce(new Vec2(0.0, 600.0 * Time.deltaTime));
        gameObject.transform.translate(0.0, 60.0 * Time.deltaTime, 0.0);
        anim.setAnimation("left");
    } else if(Input.getKey(KeyCode.KEY_S)){
        gameObject.transform.translate(0.0, -60.0 * Time.deltaTime, 0.0);
        anim.setAnimation("right");
    } else {
        anim.setAnimation("idle");
    }

    if(Input.getKey(KeyCode.KEY_E)){
        gameObject.transform.rotate(new Vec3(0,0,1), 6.0 * Time.deltaTime);
    }

    if(Input.getKey(KeyCode.KEY_Q)){
       gameObject.transform.rotate(new Vec3(0,0,1), -6.0 * Time.deltaTime);
    }

    if(Input.getKey(KeyCode.KEY_R)){
        scale += 6.0 * Time.deltaTime;
        gameObject.transform.setScale(new Vec3(scale,scale,1));
    }

    if(Input.getKey(KeyCode.KEY_T)){
        scale -= 6.0 * Time.deltaTime;
        gameObject.transform.setScale(new Vec3(scale,scale,1));
    }
}