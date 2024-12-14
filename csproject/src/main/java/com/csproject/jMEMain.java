package com.csproject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.Pair;

import com.csproject.pages.SuccessDialog;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.bullet.BulletAppState;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.system.AppSettings;

public class jMEMain extends SimpleApplication {

    public static int mapIndex = 1;
    public static List < Pair < Float, Float > > targets = new ArrayList<>();
    public static List < Geometry > boxes = new ArrayList<>();

    private CharacterAppState characterAppState;
    private BulletAppState bulletAppState;
    private SceneAppState sceneAppState;
    private InputAppState inputAppState;
    
    public jMEMain() {
        super(new StatsAppState());
    }

    @Override
    public void simpleInitApp() {
        bulletAppState = new BulletAppState();
        sceneAppState = new SceneAppState();
        characterAppState = new CharacterAppState();
        inputAppState = new InputAppState();

        stateManager.attachAll(bulletAppState, sceneAppState, characterAppState, inputAppState);
        
        // Environmental Light
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(new ColorRGBA(0.7f, 0.7f, 0.7f, 0.6f));

        // Sunlight
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
        // for(Pair < Float, Float > t : targets)
        //     System.out.println(t.getKey() + " " + t.getRight());
        // for(Geometry g : boxes)
        //     System.out.println(g.getLocalTranslation().x + " " + g.getLocalTranslation().z);
        boolean complete = true;
        for(Pair < Float, Float > t : targets) {
            boolean achieve = false;
            for(Geometry g : boxes) {
                Vector3f pos =  g.getLocalTranslation();
                if(Math.abs(t.getLeft() - pos.x) < 0.3f && Math.abs(t.getRight() - pos.z) < 0.3f){
                    achieve = true;
                    break;
                }
            }complete &= achieve;
        }
        if(complete) {
            System.err.println("Success");

            //TODO Why this doesn't work?
            // Thread thread = new Thread(new Runnable(){
            //     @Override
            //     public void run() {
            //         try {
            //             // Index.CreateAndShowWindow();
            //             SuccessDialog.CreateAndShowDialog(mapIndex);
            //         } catch (Exception e) {
            //             // TODO Auto-generated catch block
            //             e.printStackTrace();
            //         }
            //     }
            // });
            // thread.start();
            this.stop();
            try {
                // Index.CreateAndShowWindow();
                SuccessDialog.CreateAndShowDialog(mapIndex, true, null);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.stop();
            // System.exit(0);
        }
    }
    public static boolean isFullScreen = false;
    public static void main(String[] args) {
        jMEMain app = new jMEMain();

        AppSettings settings = new AppSettings(true);
        settings.setTitle("Sokoban Game");
        settings.setResolution(1920, 1080);
        settings.setFullscreen(isFullScreen);
        settings.setVSync(true);

        app.setSettings(settings);
        app.setShowSettings(false);

        //TODO Fatal Error: How to handle the threads?

        Thread thread = new Thread(() -> {
            app.start();
        });
        thread.start();
    }
}