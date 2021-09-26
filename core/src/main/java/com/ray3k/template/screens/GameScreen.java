package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasSprite;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.dongbat.walkable.PathHelper;
import com.ray3k.template.*;
import com.ray3k.template.OgmoReader.*;
import com.ray3k.template.entities.*;
import com.ray3k.template.screens.DialogPause.*;
import space.earlygrey.shapedrawer.ShapeDrawer;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.Resources.Values.*;

public class GameScreen extends JamScreen {
    public static GameScreen gameScreen;
    public static final Color BG_COLOR = new Color();
    public Stage stage;
    public boolean paused;
    private Label fpsLabel;
    public PathHelper pathHelper;
    public static final int DECAL_DEPTH = 1000000;
    public static final int ACTOR_DEPTH = 100;
    public static final int SHIP_DEPTH = 10;
    public static final int DEBUG_DEPTH = -1000;
    public static final float[] ZOOMS = {1f, .9f, .7f, .6f, .5f};
    public int zoomIndex = 0;
    public float oldZoom;
    public float oldCameraX;
    public float oldCameraY;
    public float newCameraX;
    public float newCameraY;
    public Action zoomAction;
    public Action panAction;
    public SoldierEntity selectedSoldier;
    private static final Vector2 temp = new Vector2();
    public Array<SoldierEntity> soldiers = new Array<>();
    public Array<HouseEntity> houses = new Array<>();
    public boolean playedSiren;
    public Array<EnemyEntity> enemies = new Array<>();
    public int ships;
    public boolean levelComplete;
    
    @Override
    public void show() {
        super.show();
        
        gameScreen = this;
        BG_COLOR.set(Color.PINK);
    
        paused = false;
    
        stage = new Stage(new ScreenViewport(), batch);
        
        var root = new Table();
        root.setFillParent(true);
        root.align(Align.bottomLeft);
        root.pad(10);
        stage.addActor(root);
        
        fpsLabel = new Label("test", skin);
        root.add(fpsLabel);
        
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (!paused && keycode == Keys.ESCAPE) {
                    paused = true;
                
                    DialogPause dialogPause = new DialogPause(GameScreen.this);
                    dialogPause.show(stage);
                    dialogPause.addListener(new PauseListener() {
                        @Override
                        public void resume() {
                            paused = false;
                        }
                    
                        @Override
                        public void quit() {
                            core.transition(new MenuScreen());
                        }
                    });
                }
                
                if (!paused && keycode == Keys.F10) {
                    paused = true;
                    
                    DialogDebug dialogDebug = new DialogDebug() {
                        @Override
                        public void hide(Action action) {
                            super.hide(action);
                            paused = false;
                        }
                    };
                    dialogDebug.show(stage);
                }
                
                return super.keyDown(event, keycode);
            }
        });
    
        shapeDrawer = new ShapeDrawer(batch, skin.getRegion("white"));
        shapeDrawer.setPixelSize(.5f);
    
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);
    
        camera = new OrthographicCamera();
        viewport = new FitViewport(1024, 576, camera);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        
        stage.addListener(new ClickListener(Buttons.LEFT) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (soldiers.size > 0) {
                    var closest = soldiers.get(0);
                    var distance = Utils.pointDistance(closest.x, closest.y, mouseX, mouseY);
                    for (int i = 1; i < soldiers.size; i++) {
                        var soldier = soldiers.get(i);
                        var newDistance = Utils.pointDistance(soldier.x, soldier.y, mouseX, mouseY);
                        if (newDistance < distance) {
                            distance = newDistance;
                            closest = soldier;
                        }
                    }
                    if (distance < 30) {
                        selectedSoldier = closest.parent == null ? closest : closest.parent;
                        selectedSoldier.playSelectedSound();
                    } else {
                        selectedSoldier = null;
                    }
                }
            }
        });
        
        stage.addListener(new DragListener() {
            float cameraXstart;
            float cameraYstart;
            float xStart;
            float yStart;
            float cameraXlast;
            float cameraYlast;

            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
                xStart = x;
                yStart = y;
                cameraXstart = camera.position.x;
                cameraYstart = camera.position.y;
                stage.getRoot().removeAction(panAction);
            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                cameraXlast = camera.position.x;
                cameraYlast = camera.position.y;
                camera.position.set(cameraXstart + (xStart - x) * camera.zoom, cameraYstart + (yStart - y) * camera.zoom, 0);
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                float velocityX = MathUtils.clamp(camera.position.x - cameraXlast, -10, 10);
                float velocityY = MathUtils.clamp(camera.position.y - cameraYlast, -10, 10);

                panAction = new Action() {
                    Vector2 vector2 = new Vector2(velocityX, velocityY);
                    @Override
                    public boolean act(float delta) {
                        camera.position.x += vector2.x;
                        camera.position.y += vector2.y;

                        vector2.setLength(Utils.approach(vector2.len(), 0, 10f * delta));
                        if (MathUtils.isZero(vector2.len())) return true;
                        else return false;
                    }
                };
                stage.addAction(panAction);
            }
        });
    
        entityController.clear();
        pathHelper = new PathHelper(1024, 576);
        
        var ogmoReader = new OgmoReader();
        ogmoReader.addListener(new OgmoAdapter() {
            @Override
            public void decal(int centerX, int centerY, float scaleX, float scaleY, int rotation, String texture,
                              String folder, ObjectMap<String, OgmoValue> valuesMap) {
                var decal = new DecalEntity(new AtlasSprite(textures_textures.findRegion("levels/" + Utils.fileName(texture))), centerX, centerY);
                decal.panning = valuesMap.get("panning").asBoolean();
                entityController.add(decal);
            }
    
            @Override
            public void entity(String name, int id, int x, int y, int width, int height, boolean flippedX,
                               boolean flippedY, int originX, int originY, int rotation, Array<EntityNode> nodes,
                               ObjectMap<String, OgmoValue> valuesMap) {
                if (name.equals("level-bounds")) {
                    var verts = new FloatArray();
                    verts.add(x, y);
                    for (var node : nodes) {
                        verts.add(node.x, node.y);
                    }
                    Polygon polygon = new Polygon(verts.toArray());
                    var shape = new WallEntity(polygon);
                    entityController.add(shape);
                    
                    pathHelper.addPolygon(polygon.getTransformedVertices());
                } else if (name.equals("player")) {
                    int team = valuesMap.get("team").asInt();
                    if (team <= saveData.teams) {
                        var soldierType = saveData.types[team - 1];
                        
                        var leader = new SoldierEntity();
                        leader.team = team;
                        leader.setPosition(x, y);
                        leader.soldierType = soldierType;
                        entityController.add(leader);
                        soldiers.add(leader);
    
                        temp.set(20, 0);
                        for (int i = 0; i < soldierSquadSize - 1; i++) {
                            temp.rotateDeg(360f / (soldierSquadSize - 1));
                            var soldier = new SoldierEntity();
                            soldier.team = team;
                            soldier.targetOffsetX = temp.x;
                            soldier.targetOffsetY = temp.y;
                            soldier.setPosition(x + temp.x, y + temp.y);
                            soldier.soldierType = soldierType;
                            soldier.parent = leader;
                            leader.children.add(soldier);
                            soldiers.add(soldier);
                            entityController.add(soldier);
                        }
                    }
                } else if (name.equals("house")) {
                    var house = new HouseEntity();
                    house.setPosition(x, y);
                    houses.add(house);
                    entityController.add(house);
                    switch (valuesMap.get("skin").asString()) {
                        case "house1":
                            house.skeleton.setSkin(SpineHouse.skinHouse1);
                            break;
                        case "house2":
                            house.skeleton.setSkin(SpineHouse.skinHouse2);
                            break;
                    }
                    //pathHelper.addRect(house.getCollisionBoxLeft(), house.getCollisionBoxBottom(), house.bboxWidth, house.bboxHeight);
                } else if (name.equals("zombie")) {
                    var spawner = new EnemyShipSpawner();
                    spawner.setPosition(x, y);
                    spawner.count = valuesMap.get("count").asInt();
                    spawner.delay = valuesMap.get("delay").asFloat();
                    spawner.type = EnemyShipSpawner.class;//todo:change this;
                    spawner.targetX = nodes.first().x;
                    spawner.targetY = nodes.first().y;
                    entityController.add(spawner);
                    ships++;
                }
            }
        });
        ogmoReader.readFile(Gdx.files.internal("levels/level-" + saveData.level + ".json"));
    }
    
    @Override
    public void act(float delta) {
        if (!paused) {
            boolean updateZoom = false;
            if (isBindingJustPressed(Binding.ZOOM_IN)) {
                if (zoomIndex < ZOOMS.length - 1) {
                    zoomIndex++;
                    updateZoom = true;
                }
                
            } else if (isBindingJustPressed(Binding.ZOOM_OUT)) {
                if (zoomIndex > 0) {
                    zoomIndex--;
                    updateZoom = true;
                }
            }
            
            if (updateZoom) {
                oldZoom = camera.zoom;
                float newZoom = ZOOMS[zoomIndex];
                oldCameraX = camera.position.x;
                oldCameraY = camera.position.y;
                newCameraX = oldCameraX - (newZoom - oldZoom) * (mouseX - oldCameraX) / oldZoom;
                newCameraY = oldCameraY - (newZoom - oldZoom) * (mouseY - oldCameraY) / oldZoom;
                stage.getRoot().removeAction(zoomAction);
                zoomAction = Actions.sequence(Actions.delay(.1f), new TemporalAction(1f, Interpolation.smooth) {
                    @Override
                    protected void update(float percent) {
                        camera.zoom = MathUtils.lerp(oldZoom, newZoom, percent);
                        camera.position.set(MathUtils.lerp(oldCameraX, newCameraX, percent), MathUtils.lerp(oldCameraY, newCameraY, percent), 0);
                    }
                });
                stage.addAction(zoomAction);
            }
            
            entityController.act(delta);
            vfxManager.update(delta);
            
            if (!levelComplete && enemies.size == 0 && ships == 0) {
                Gdx.input.setInputProcessor(stage);
                levelComplete = true;
                saveData.level++;
                stage.addAction(Actions.sequence(new TemporalAction(2.0f) {
                    @Override
                    protected void update(float percent) {
                        bgm_game.setVolume(bgm * (1 - percent));
                    }
                }, Actions.run(() -> bgm_game.stop())));
                
                for (int i = 0; i < houses.size; i++) {
                    var house = houses.get(i);
                    if (house.health > 0) {
                        var coin = new CoinEntity();
                        coin.setPosition(house.x, house.y);
                        entityController.add(coin);
                        coin.animationState.getCurrent(0).setDelay(1 + .7f * i);
                        saveData.coins++;
                    }
                }
                
                stage.addAction(Actions.delay(2 + .7f * houses.size, Actions.run(() -> {
                    Dialog dialog = new Dialog("", skin) {
                        @Override
                        protected void result(Object object) {
                            if (saveData.level <= 15) Gdx.app.postRunnable(() -> Core.core.transition(new GameScreen()));
                            else Gdx.app.postRunnable(() -> Core.core.transition(new CreditsScreen()));
                        }
                    };
                    
                    var root = dialog.getContentTable();
                    
                    var table = new Table();
                    root.add(table);
                    
                    var image = new Image(skin, "coin");
                    image.setScaling(Scaling.fit);
                    table.add(image).size(100, 100);
                    
                    var label = new Label("x" + saveData.coins, skin);
                    table.add(label);
                    
                    root.row();
                    table = new Table();
                    root.add(table);
                    
                    var textButton = new TextButton("Upgrade Team 1\nCost: 3", skin);
                    table.add(textButton);
                    if (saveData.teams < 1 || saveData.coins < 3) textButton.setDisabled(true);
                    
                    textButton = new TextButton("Upgrade Team 2\nCost: 3", skin);
                    table.add(textButton);
                    if (saveData.teams < 2 || saveData.coins < 3) textButton.setDisabled(true);
    
                    table.row();
                    textButton = new TextButton("Upgrade Team 3\nCost: 3", skin);
                    table.add(textButton);
                    if (saveData.teams < 3 || saveData.coins < 3) textButton.setDisabled(true);
                    
                    textButton = new TextButton("Upgrade Team 4\nCost: 3", skin);
                    table.add(textButton);
                    if (saveData.teams < 4 || saveData.coins < 3) textButton.setDisabled(true);
                    
                    dialog.button("Next Level");
                    
                    dialog.show(stage);
                })));
            } else if (!levelComplete) {
                levelComplete = true;
                for (int i = 0; i < houses.size; i++) {
                    var house = houses.get(i);
                    if (house.health > 0) {
                        levelComplete = false;
                        break;
                    }
                }
                
                if (levelComplete) {
                    stage.addAction(Actions.sequence(new TemporalAction(2.0f) {
                        @Override
                        protected void update(float percent) {
                            bgm_game.setVolume(bgm * (1 - percent));
                        }
                    }, Actions.run(() -> bgm_game.stop()), Actions.run(() -> {
                        Gdx.app.postRunnable(() -> Core.core.transition(new GameScreen()));
                    })));
                }
            }
        }
        stage.act(delta);
        
        fpsLabel.setText(Gdx.graphics.getFramesPerSecond());
    }
    
    @Override
    public void draw(float delta) {
        batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
        
        vfxManager.cleanUpBuffers();
        vfxManager.beginInputCapture();
        Gdx.gl.glClearColor(BG_COLOR.r, BG_COLOR.g, BG_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        entityController.draw(paused ? 0 : delta);
        batch.end();
        vfxManager.endInputCapture();
        vfxManager.applyEffects();
        vfxManager.renderToScreen();
    
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        if (width + height != 0) {
            vfxManager.resize(width, height);
            viewport.update(width, height);
        
            stage.getViewport().update(width, height, true);
        }
    }
    
    @Override
    public void dispose() {
    
    }
    
    @Override
    public void hide() {
        super.hide();
        vfxManager.removeAllEffects();
        entityController.dispose();
    }
}
