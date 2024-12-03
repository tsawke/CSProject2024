package com.csproject;
import com.jme3.app.SimpleApplication;
import com.jme3.math.FastMath;
import com.jme3.scene.Geometry;

public class jME3Test extends SimpleApplication{
	private Geometry geom;

    @Override
	public void simpleInitApp() {
		// 	// #1 创建一个方块形状的网格
        // Mesh box = new Box(1, 1, 1);

        // // #2 加载一个感光材质
        // Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");

        // // #3 创建一个几何体，应用刚才和网格和材质。
        // geom = new Geometry("Box");
        // geom.setMesh(box);
        // geom.setMaterial(mat);

        // // #4 创建一束定向光，并让它斜向下照射，好使我们能够看清那个方块。
        // DirectionalLight sun = new DirectionalLight();
        // sun.setDirection(new Vector3f(-1, -2, -3));
        
        // // #5 将方块和光源都添加到场景图中
        // rootNode.attachChild(geom);
        // rootNode.addLight(sun);

		GameSystem gameSystem = new GameSystem(1);

	}

	// /**
    //  * 初始化场景
    //  */
    // private void initScene() {
    //     // 从zip文件中加载地图场景
    //     assetManager.registerLocator("./csproject/src/main/resources/town.zip", ZipLocator.class);
    //     Spatial sceneModel = assetManager.loadModel("main.scene");

    //     // 为地图创建精确网格形状
    //     CollisionShape sceneShape = CollisionShapeFactory.createMeshShape(sceneModel);
    //     RigidBodyControl landscape = new RigidBodyControl(sceneShape, 0);
    //     sceneModel.addControl(landscape);
        
    //     rootNode.attachChild(sceneModel);
    //     bulletAppState.getPhysicsSpace().add(landscape);
    // }

	//     /**
    //  * 初始化玩家
    //  */
    // private void initPlayer() {
    //     // 使用胶囊体作为玩家的碰撞形状
    //     CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(0.3f, 1.8f, 1);
    //     float stepHeight = 0.5f;// 玩家能直接登上多高的台阶？
        
    //     // 使用CharacterControl来控制玩家物体
    //     CharacterControl player = new CharacterControl(capsuleShape, stepHeight);
    //     player.setJumpSpeed(10);// 起跳速度
    //     player.setFallSpeed(55);// 坠落速度
    //     player.setGravity(9.8f * 3);// 重力加速度
    //     player.setPhysicsLocation(new Vector3f(0, 10, 0));// 位置
        
    //     bulletAppState.getPhysicsSpace().add(player);
    // }

	/**
	 * 主循环
	 */
	@Override
	public void simpleUpdate(float deltaTime) {
		// 旋转速度：每秒360°
		float speed = FastMath.TWO_PI;
		// 让方块匀速旋转
		geom.rotate(0, deltaTime * speed, 0);
	}

	public static void main(String[] args) {
		// 启动jME3程序
		jME3Test app = new jME3Test();
		app.start();
	}
}
