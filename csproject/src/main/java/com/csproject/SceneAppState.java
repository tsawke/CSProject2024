package com.csproject;

import java.util.stream.IntStream;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
/**
 * 场景管理模块
 * @author yanmaoyuan
 *
 */
public class SceneAppState extends BaseAppState {

    private Node rootNode;

    private Spatial sceneModel;
    private RigidBodyControl landscape;
    private RigidBodyControl cube = new RigidBodyControl(1f);

    private AssetManager assetManager;

    @Override
    protected void initialize(Application app) {
        this.assetManager = app.getAssetManager();

        this.rootNode = ((SimpleApplication) getApplication()).getRootNode();

        // 从zip文件中加载地图场景
        assetManager.registerLocator("./csproject/src/main/resources/town.zip", ZipLocator.class);
        this.sceneModel = assetManager.loadModel("main.scene");

        // 为地图创建精确网格形状
        CollisionShape sceneShape = CollisionShapeFactory.createMeshShape(sceneModel);
        this.landscape = new RigidBodyControl(sceneShape, 0);  
        landscape.setFriction(2);
        sceneModel.addControl(landscape);

        Mesh box = new Box(1, 1, 1);
        assetManager.registerLocator("./csproject/src/main/resources/", FileLocator.class);
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");

        Geometry geom = new Geometry("Box");
        geom.setMesh(box);
        geom.setMaterial(mat);

        
        BoxCollisionShape boxShape = new BoxCollisionShape(new Vector3f(1, 1, 1));
        this.cube.setCollisionShape(boxShape);
        cube.setMass(2);

        // geom.addControl(cube);
        // geom.setLocalTranslation(new Vector3f(-1, 3, -1));
        // geom.setLocalRotation(new Matrix3f());

        // cube.setPhysicsLocation(new Vector3f(-1, 3, -1));
        // cube.setPhysicsRotation(new Matrix3f());

        
        GameMap currentMap = GameSystem.maps.get(jMEMain.mapIndex);
         IntStream.range(1, currentMap.getHeight() + 1).forEach(
            i -> {
                IntStream.range(1, currentMap.getWidth() + 1).forEach(
                    j -> {
                        if(currentMap.GetMapByIndex(i, j) == 0)return;
                        

                        RigidBodyControl block = (RigidBodyControl)cube.jmeClone();
                        
                        
                        Geometry geomBlock = new Geometry("Box");
                        geomBlock.setMesh(box);
                        geomBlock.setMaterial(mat);
                        geomBlock.addControl(block);
                        geomBlock.setLocalTranslation(new Vector3f(2.2f * (float)i, 1.5f, 2.2f * (float)j));
                        geomBlock.setLocalRotation(new Matrix3f());
                        // Geometry geoBlock = (Geometry)geom.jmeClone();
                        // geoBlock.addControl(block);
                        // geoBlock.setLocalTranslation(new Vector3f(10 * i, 5, 10 * j));
                        // geoBlock.setLocalRotation(new Matrix3f());
                        if(currentMap.GetMapByIndex(i, j) == 1){
                            block.setMass(9999999f);
                            block.setFriction(99999999f);
                        }
                           
                        else
                            block.setMass(10f);
                        block.setPhysicsLocation(new Vector3f(2.2f * (float)i, 1.5f, 2.2f * (float)j));
                        block.setPhysicsRotation(new Matrix3f());
                        getStateManager().getState(BulletAppState.class).getPhysicsSpace().add(block);

                        rootNode.attachChild(geomBlock);

                        
                    }
                );
            }
        );

        // DirectionalLight sun = new DirectionalLight();
        // sun.setDirection(new Vector3f(-1, -2, -3));

        // rootNode.attachChild(geom);
        // rootNode.addLight(sun);
    }

    @Override
    protected void cleanup(Application app) {
    }

    @Override
    protected void onEnable() {
        rootNode.attachChild(sceneModel);

        BulletAppState bullet = getStateManager().getState(BulletAppState.class);
        if (bullet != null) {
            bullet.getPhysicsSpace().add(landscape);
            // bullet.getPhysicsSpace().add(cube);
        }
    }

    @Override
    protected void onDisable() {
        sceneModel.removeFromParent();
        
        PhysicsSpace space = landscape.getPhysicsSpace();
        if (space != null) {
            space.remove(landscape);
        }
    }

}