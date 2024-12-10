package com.csproject;

import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.Pair;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.ChaseCamera;
import com.jme3.input.InputManager;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class CharacterAppState extends BaseAppState implements AnimEventListener {
    private float height = 1.8f;
    private float stepLength = 0.5f;

    private Node character;
    private Spatial model;
    private Node camNode;
    private CharacterControl player;

    private Vector3f walkDir = new Vector3f();
    private Vector3f camDir = new Vector3f();
    private Quaternion camRot = new Quaternion();

    private AnimControl animControl;
    private AnimChannel animChannel;

    private Camera cam;
    private AssetManager assetManager;
    private InputManager inputManager;
    private AppStateManager stateManager;

    public static Pair < Float, Float > defaultPlayerPosition;

    private RigidBodyControl[] player_rigid;

    public CharacterControl getPlayer() {
        return player;
    }

    public void setPlayer(CharacterControl player) {
        this.player = player;
    }

    public RigidBodyControl[] getPlayer_rigid() {
        return player_rigid;
    }

    public void setPlayer_rigid(RigidBodyControl[] player_rigid) {
        this.player_rigid = player_rigid;
    }

    @Override
    protected void initialize(Application app) {
        this.cam = app.getCamera();
        this.assetManager = app.getAssetManager();
        this.inputManager = app.getInputManager();
        this.stateManager = app.getStateManager();

        initCharacter();
        initPhysics();
        initAnimation();
        initChaseCamera();
    }

    @Override
    public void update(float tpf) {

        if (walkDir.lengthSquared() != 0) {
            camDir.set(cam.getDirection());
            camDir.y = 0;
            camDir.normalizeLocal();
            camRot.lookAt(camDir, Vector3f.UNIT_Y);
            camRot.mult(walkDir, camDir);
            player.setViewDirection(camDir);
            camDir.multLocal(0.1f);
        } else
            camDir.set(0, 0, 0);
        player.setWalkDirection(camDir);
        cam.setLocation(camNode.getWorldTranslation());
    }

    private void initCharacter() {
        this.character = new Node("Character");
        character.setLocalTranslation(defaultPlayerPosition.getLeft(), height / 2, defaultPlayerPosition.getRight());

        assetManager.registerLocator("./csproject/src/main/resources/", FileLocator.class);
        this.model = assetManager.loadModel("Models/Jaime/Jaime.j3o");
        character.attachChild(model);

        model.setLocalTranslation(0, -(height / 2f), 0);
        model.scale(1.8f);

        this.camNode = new Node("Camera");
        character.attachChild(camNode);
        camNode.setLocalTranslation(0, height / 2, 0);
    }

    private void initPhysics() {
        BulletAppState bullet = getStateManager().getState(BulletAppState.class);
        this.player = new CharacterControl(new BoxCollisionShape(new Vector3f(0.1f, 1f, 0.1f)), stepLength);
        character.addControl(player);
        player_rigid = new RigidBodyControl[9];

        IntStream.range(1, 8 + 1).forEach(
            i->{
                player_rigid[i] = new RigidBodyControl(new BoxCollisionShape(new Vector3f(0.2f, 1, 0.2f)), 10f);
                bullet.getPhysicsSpace().add(player_rigid[i]);
            }
        );
        
        player.setJumpSpeed(10);
        player.setFallSpeed(55);
        player.setGravity(9.8f * 3);
        player.setPhysicsLocation(new Vector3f(defaultPlayerPosition.getLeft(), height / 2, defaultPlayerPosition.getRight()));

        stateManager.getState(BulletAppState.class).getPhysicsSpace().add(player);
    }

    private void initAnimation() {
        animControl = model.getControl(AnimControl.class);
        animChannel = animControl.createChannel();

        animControl.addListener(this);
        animChannel.setAnim("Idle");
    }
    
    private void initChaseCamera() {
        ChaseCamera chaseCam = new ChaseCamera(cam, camNode, inputManager);
        chaseCam.setInvertVerticalAxis(true);
        chaseCam.setMinDistance(0.1f);
        chaseCam.setDefaultDistance(10f);
    }

    @Override
    protected void onEnable() {
        SimpleApplication simpleApp = (SimpleApplication) getApplication();
        simpleApp.getRootNode().attachChild(character);
    }

    @Override
    protected void onDisable() {
        character.removeFromParent();
    }

    public void jump() {
        if (player.onGround()) {
            player.jump();
            
            animChannel.setAnim("JumpStart");
            animChannel.setLoopMode(LoopMode.DontLoop);
            animChannel.setSpeed(1.8f);
        }
    }

    public void walk(Vector3f dir) {
        if (dir != null) {
            if (walkDir.lengthSquared() == 0) {
                animChannel.setAnim("Walk");
                animChannel.setSpeed(3f);
            }
            dir.normalizeLocal();
            walkDir.set(dir);
        }
    }

    public void idle() {
        walkDir.set(0, 0, 0);
        if (player.onGround()) {
            animChannel.setAnim("Idle");
        }
    }

    @Override
    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        if ("JumpStart".equals(animName)) {
            channel.setAnim("JumpEnd");
            channel.setLoopMode(LoopMode.DontLoop);
            channel.setSpeed(1.8f);

        } else if ("JumpEnd".equals(animName)) {
            if (walkDir.lengthSquared() != 0) {
                channel.setAnim("Walk");
                channel.setLoopMode(LoopMode.Loop);
                channel.setSpeed(3f);
            } else {
                channel.setAnim("Idle");
                channel.setLoopMode(LoopMode.Loop);
            }
        }
    }

    @Override
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
    }

    @Override
    protected void cleanup(Application app) {
    }
}