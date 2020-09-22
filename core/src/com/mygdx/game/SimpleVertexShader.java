package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class SimpleVertexShader extends GdxTest {
    ShaderProgram shader;
    Mesh mesh;
    Matrix4 projection = new Matrix4();
    Matrix4 view = new Matrix4();
    Matrix4 model = new Matrix4();
    Matrix4 combined = new Matrix4();
    Vector3 axis = new Vector3(1, 0, 1).nor();
    float angle = 45;

    @Override
    public void create () {
        // @off
        String vertexShader =
                "uniform mat4 u_mvpMatrix;                   \n"
                        + "attribute vec4 a_position;                  \n"
                        + "void main()                                 \n"
                        + "{                                           \n"
                        + "   gl_Position = u_mvpMatrix * a_position;  \n"
                        + "}                            \n";
        String fragmentShader =
                "#ifdef GL_ES\n"
                        + "precision mediump float;\n"
                        + "#endif\n"
                        + "void main()                                  \n"
                        + "{                                            \n"
                        + "  gl_FragColor = vec4 ( 1.0, 0.0, 0.0, 1.0 );\n"
                        + "}";
        // @on

        shader = new ShaderProgram(vertexShader, fragmentShader);
        mesh = Shapes.genCube();
        mesh.getVertexAttribute(Usage.Position).alias = "a_position";
    }

    @Override
    public void render () {
        // changes angle with time
        angle += Gdx.graphics.getDeltaTime() * 40.0f;
        // still don't understand
        float aspect = Gdx.graphics.getWidth() / (float)Gdx.graphics.getHeight();
        // sets the "camera view" of the object
        projection.setToProjection(1.0f, 20.0f, 60.0f, aspect);
        // idt() => chains methods, trn, increments the coordinates,
        view.idt().trn(0, 0, -2.0f);
        // change the matrix to a rotations matrix, of the specified angle
        model.setToRotation(axis, angle);

        combined.set(projection).mul(view).mul(model);

        Gdx.gl20.glViewport(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shader.bind();
        shader.setUniformMatrix("u_mvpMatrix", combined);
        mesh.render(shader, GL20.GL_TRIANGLES);

        Gdx.app.log("angle", "" + angle);
    }
}