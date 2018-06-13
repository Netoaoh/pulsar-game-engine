#import <Scripts/Engine.js>

var scale = 0.0;

var body;
var anim;
var audio;

var speed = 60;
var dx = 0;
var dy = 0;
var x = 0;
var y = 0;
var fireRate = 0.2;
var currentFireRate = 0;

function Awake() { 
	print("Awake");
}

function Update() {
	HandleInput();
}

function OnCollisionEnter(collision){
    print("a");
    //body.addForce(new Vec2(600.0 * Time.deltaTime, 0.0));
    //anim.setAnimation("right");
}

function HandleInput(){
    currentFireRate += Time.deltaTime;
    audio = gameObject.getComponent(AudioSourceComponent.class);

    var position = gameObject.transform.getPos();

    //Verifica se o botao que move o player para a esquerda esta pressionado
    if(Input.getKey(KeyCode.KEY_A)) {
        if(position.x > -70)
            dx = -speed; //Decrementa a aceleracao em x
    }
    //Verifica se o botao que move o player para a direita esta pressionado
    if(Input.getKey(KeyCode.KEY_D)) {
        if(position.x < 70)
            dx = speed; //Incrementa a aceleracao em x
    }
    //Verifica se o botao que move o player para cima esta pressionado
    if(Input.getKey(KeyCode.KEY_W)) {
        if(position.y < 50)
            dy = speed; //Decrementa a aceleracao em y
    }
    //Verifica se o botao que move o player para baixo esta pressionado
    if(Input.getKey(KeyCode.KEY_S)) {
        if(position.y > -50)
            dy = -speed; //Incrementa a aceleracao em y
    }

    dx *= Time.deltaTime;
    dy *= Time.deltaTime;

    gameObject.transform.translate(dx, dy, 0.0);

    //Zera as variaveis de aceleracao
    dx = 0;
    dy = 0;
    x = 0;
    y = 0;

    if(Input.getKey(KeyCode.KEY_SPACE)){
        if(currentFireRate >= fireRate){
            var currentPosition = gameObject.transform.getPos();

            var bullet = new GameObject();
            bullet.addComponent(new SpriteRenderer(new Sprite(4,4), new Material("Default", new Texture("circle.png", 1))));
            //bullet.addComponent(new ScriptComponent("bullet.js"));
            bullet.addComponent(new BoxCollider(5, 5));
            Instantiate(bullet, new Vector3f(currentPosition.x, currentPosition.y + 5, 0.0), bullet.transform.getRot());
            currentFireRate = 0;
        }
    }


	/*if(Input.getKey(KeyCode.KEY_D)){
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
    }*/
}