#import <Scripts/Engine.js>


function Update() {

    if(gameObject.transform.getPos().y >= 50){
        gameObject.destroy();
    }

    gameObject.transform.translate(0.0, 70 * Time.deltaTime, 0.0);
}