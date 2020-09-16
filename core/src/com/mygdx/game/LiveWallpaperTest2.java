package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class LiveWallpaperTest2 extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture shape;
	Mesh cube;
	Mesh mesh;
	ShaderProgram shader;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		cube = Shapes.genCube();

		String vertexShader = "attribute vec4 vPosition;    \n" + "void main()                  \n"
				+ "{                            \n" + "   gl_Position = vPosition;  \n" + "}                            \n";
		String fragmentShader = "#ifdef GL_ES\n" + "precision mediump float;\n" + "#endif\n"
				+ "void main()                                  \n" + "{                                            \n"
				+ "  gl_FragColor = vec4 ( 1.0, 1.0, 1.0, 1.0 );\n" + "}";

		shader = new ShaderProgram(vertexShader, fragmentShader);
		mesh = new Mesh(true, 3, 0, new VertexAttribute(VertexAttributes.Usage.Position, 3, "vPosition"));
		float[] vertices = {0.0f, 0.5f, 0.0f, -0.5f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f};
		mesh.setVertices(vertices);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glViewport(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shader.bind();
		mesh.render(shader, GL20.GL_TRIANGLES);
//		cube.render();
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
