package com.csproject;

import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.Pair;

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
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

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

        assetManager.registerLocator("./csproject/src/main/resources/town.zip", ZipLocator.class);
        this.sceneModel = assetManager.loadModel("main.scene");

        CollisionShape sceneShape = CollisionShapeFactory.createMeshShape(sceneModel);
        this.landscape = new RigidBodyControl(sceneShape, 0);  
        landscape.setFriction(2);
        sceneModel.addControl(landscape);

        Mesh box = new Box(1, 1, 1);
        assetManager.registerLocator("./csproject/src/main/resources/", FileLocator.class);
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        Material matTarget = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Material matBlocked = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");

        matBlocked.setColor("Diffuse", new ColorRGBA(255 / 255.0F, 127 / 255.0F, 80 / 255.0F, 1 / 255.0F));
        matBlocked.setColor("Ambient", new ColorRGBA(255 / 255.0F, 127 / 255.0F, 80 / 255.0F, 1 / 255.0F));
        matBlocked.setColor("Specular", ColorRGBA.White);
        matBlocked.setFloat("Shininess", 32);
        matBlocked.setBoolean("UseMaterialColors", true);

        matTarget.setColor("Color", ColorRGBA.Green);
        
        Geometry geom = new Geometry("Box");
        geom.setMesh(box);
        geom.setMaterial(mat);

        
        BoxCollisionShape boxShape = new BoxCollisionShape(new Vector3f(1, 1, 1));
        this.cube.setCollisionShape(boxShape);
        cube.setMass(2);
        
        GameMap currentMap = GameSystem.maps.get(jMEMain.mapIndex);
         IntStream.range(1, currentMap.getHeight() + 1).forEach(
            i -> {
                IntStream.range(1, currentMap.getWidth() + 1).forEach(
                    j -> {
                        switch(currentMap.GetMapByIndex(i, j)) {
                            case 0 -> {}
                            case 3 -> {
                                jMEMain.targets.add(Pair.of(2f * i, 2f * j));
                                Geometry geomBlock = new Geometry("Box");
                                geomBlock.setMesh(new Box(0.8f, 0.8f, 0.8f));
                                geomBlock.setMaterial(matTarget);
                                geomBlock.setLocalTranslation(new Vector3f(2f * (float)i, 0.8f, 2f * (float)j));
                                geomBlock.setLocalRotation(new Matrix3f());
                                rootNode.attachChild(geomBlock);
                            }
                            case 4 -> {
                                CharacterAppState.defaultPlayerPosition = Pair.of(2f * i, 2f * j);
                            }
                            case 5 -> {
                                CharacterAppState.defaultPlayerPosition = Pair.of(2f * i, 2f * j);
                                jMEMain.targets.add(Pair.of(2f * i, 2f * j));
                                Geometry geomBlock = new Geometry("Box");
                                geomBlock.setMesh(new Box(0.8f, 0.8f, 0.8f));
                                geomBlock.setMaterial(matTarget);
                                geomBlock.setLocalTranslation(new Vector3f(2f * (float)i, 0.8f, 2f * (float)j));
                                geomBlock.setLocalRotation(new Matrix3f());
                                rootNode.attachChild(geomBlock);
                            }
                            default -> {
                                RigidBodyControl block = (RigidBodyControl)cube.jmeClone();
                                Geometry geomBlock = new Geometry("Box");
                                geomBlock.setMesh(box);
                                if(currentMap.GetMapByIndex(i, j) == 1)geomBlock.setMaterial(matBlocked);
                                else geomBlock.setMaterial(mat);
                                geomBlock.addControl(block);
                                geomBlock.setLocalTranslation(new Vector3f(2f * (float)i, 1.5f, 2f * (float)j));
                                geomBlock.setLocalRotation(new Matrix3f());
                                if(currentMap.GetMapByIndex(i, j) == 1)
                                    block.setMass(0);
                                else{
                                    block.setMass(20f);
                                    jMEMain.boxes.add(geomBlock);
                                }
                                block.setPhysicsLocation(new Vector3f(2f * (float)i, 1.5f, 2f * (float)j));
                                block.setPhysicsRotation(new Matrix3f());
                                getStateManager().getState(BulletAppState.class).getPhysicsSpace().add(block);
        
                                rootNode.attachChild(geomBlock);
                            }
                        }
                    }
                );
            }
        );
    }

    @Override
    protected void cleanup(Application app) {
    }

    @Override
    protected void onEnable() {
        rootNode.attachChild(sceneModel);
        
        BulletAppState bullet = getStateManager().getState(BulletAppState.class);
        if(bullet != null) {
            bullet.getPhysicsSpace().add(landscape);
        }
    }

    @Override
    protected void onDisable() {
        sceneModel.removeFromParent();
        
        PhysicsSpace space = landscape.getPhysicsSpace();
        if(space != null) {
            space.remove(landscape);
        }
    }

}