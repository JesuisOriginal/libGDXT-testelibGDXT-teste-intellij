package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class LiveWallpaperTest2 extends ApplicationAdapter {
	SpriteBatch batch;
//	Texture img;
//	Texture shape;
//	Mesh cube;
	Mesh mesh;
	ShaderProgram shader;

	// initialize variables and objects at when LiveWallpaper
	@Override
	public void create () {
//		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
//		cube = Shapes.genCube();

		// declaring OpenGL Shader
		String vertexShader = "attribute vec4 vPosition;    \n" + "void main()                  \n"
				+ "{                            \n" + "   gl_Position = vPosition;  \n" + "}                            \n";
		String fragmentShader = "#ifdef GL_ES\n" + "precision mediump float;\n" + "#endif\n"
				+ "void main()                                  \n" + "{                                            \n"
				+ "  gl_FragColor = vec4 ( 1.0, 1.0, 1.0, 1.0 );\n" + "}";


		// Create a new shader object from OpenGL shader language
		shader = new ShaderProgram(vertexShader, fragmentShader);
		// create a new mesh object containing the draw method to be rendered
		mesh = new Mesh(true, 3, 0, new VertexAttribute(VertexAttributes.Usage.Position, 3, "vPosition"));
		// definition of vertices positions
		float[] vertices = {0.0f, 0.5f, 0.0f, -0.5f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f};
		// add positions to the mesh
		mesh.setVertices(vertices);

	}

	@Override
	public void render () {
		// opengl definitions
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glViewport(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());

		// binds the shader, to indicade which shader to use for rendering
		shader.bind();
		// selects the binded render and primitive type
		mesh.render(shader, GL20.GL_TRIANGLES);
//		cube.render(shader, GL20.GL_TRIANGLES);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
//		img.dispose();
	}
}
