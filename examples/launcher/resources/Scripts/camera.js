#import <Scripts/engine.js>

function Awake() {

}

function Update() { 
	HandleInput();
}

/*function OnCollisionEnter(collision){
	//print(collision.gameObject.name);
}*/

function HandleInput(){

	if(Input.getKey(KeyCode.KEY_UP)){
		var direction = gameObject.transform.getRot().getUp();
		gameObject.transform.translate(direction.mul(60.0 * Time.deltaTime));
	}

	if(Input.getKey(KeyCode.KEY_DOWN)){
		var direction = gameObject.transform.getRot().getUp();
		gameObject.transform.translate(direction.mul(-60.0 * Time.deltaTime));
	}
	
	if(Input.getKey(KeyCode.KEY_RIGHT)){
		var direction = gameObject.transform.getRot().getRight();
		gameObject.transform.translate(direction.mul(60.0 * Time.deltaTime));
	}

	if(Input.getKey(KeyCode.KEY_LEFT)){
		var direction = gameObject.transform.getRot().getRight();
		gameObject.transform.translate(direction.mul(-60.0 * Time.deltaTime));
	}
}