package com.csproject;

import java.util.stream.IntStream;

import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.bullet.BulletAppState;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;

/**
 * 演示使用第三人称控制Jaime在地图中自由行走。
 * 
 * @author yanmaoyuan
 *
 */
public class jMEMain extends SimpleApplication {

    public static int mapIndex = 1;

    private CharacterAppState characterAppState;
    private BulletAppState bulletAppState;
    private SceneAppState sceneAppState;
    public jMEMain() {
        super(new StatsAppState());
    }

    @Override
    public void simpleInitApp() {
        bulletAppState = new BulletAppState();
        sceneAppState = new SceneAppState();
        characterAppState = new CharacterAppState();

        stateManager.attachAll(bulletAppState, 
                sceneAppState,
                characterAppState,
                new InputAppState());
        
        // 环境光
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(new ColorRGBA(0.7f, 0.7f, 0.7f, 0.6f));

        // 阳光
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, -2, -3).normalizeLocal());
        rootNode.addLight(ambient);
        rootNode.addLight(sun);

        bulletAppState.setDebugEnabled(true);
    }

    @Override
    public void simpleUpdate(float deltaTime) {
        characterAppState.getPlayer_rigid()[1].setPhysicsLocation(
            characterAppState.getPlayer().getPhysicsLocation().add(new Vector3f(0.6f, 0, 0))
        );
        characterAppState.getPlayer_rigid()[2].setPhysicsLocation(
            characterAppState.getPlayer().getPhysicsLocation().add(new Vector3f(-0.6f, 0, 0))
        );
        characterAppState.getPlayer_rigid()[3].setPhysicsLocation(
            characterAppState.getPlayer().getPhysicsLocation().add(new Vector3f(0, 0, 0.6f))
        );
        characterAppState.getPlayer_rigid()[4].setPhysicsLocation(
            characterAppState.getPlayer().getPhysicsLocation().add(new Vector3f(0, 0, -0.6f))
        );
        characterAppState.getPlayer_rigid()[5].setPhysicsLocation(
            characterAppState.getPlayer().getPhysicsLocation().add(new Vector3f(0.6f, 0, 0.6f))
        );
        characterAppState.getPlayer_rigid()[6].setPhysicsLocation(
            characterAppState.getPlayer().getPhysicsLocation().add(new Vector3f(-0.6f, 0, 0.6f))
        );
        characterAppState.getPlayer_rigid()[7].setPhysicsLocation(
            characterAppState.getPlayer().getPhysicsLocation().add(new Vector3f(0.6f, 0, -0.6f))
        );
        characterAppState.getPlayer_rigid()[8].setPhysicsLocation(
            characterAppState.getPlayer().getPhysicsLocation().add(new Vector3f(-0.6f, 0, -0.6f))
        );
        IntStream.range(1, 8 + 1).forEach(
            i->{
                characterAppState.getPlayer_rigid()[i].setPhysicsRotation(new Matrix3f());
            }
        );
        
        // characterAppState.getPlayer_rigid().setAngula(
        //     characterAppState.getPlayer().getWalkDirection()
        // );
    }

    public static void main(String[] args) {
        jMEMain app = new jMEMain();
        app.start();
    }
}