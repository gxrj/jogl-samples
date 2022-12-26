package io.github.gxrj;

import javax.swing.JFrame;

import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.Animator;

import io.github.gxrj.utils.ShaderUtils;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class Sample extends JFrame implements GLEventListener {

    private GLJPanel glJPanel;

    private int renderingProgram;
    private int vao[] = new int[ 1 ];

    private Animator animator;

    private float size = 1.0f;
    private float increment = 1.0f;

    private long previousCallTime = 0l;

    Sample() {
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setTitle( "Grow n Shrink" );
        this.setSize( 800, 600 );
        this.setLocation( 270, 65 );

        this.glJPanel = new GLJPanel();
        glJPanel.addGLEventListener( this );
        this.add( glJPanel );

        this.setVisible( true );

        animator = new Animator( glJPanel );
        animator.start();
    }

    public void init( GLAutoDrawable drawable ) {
        GL4 gl = (GL4) drawable.getGL();

        String shadersFolder = "05-grow-n-shrink/shaders/";

        this.renderingProgram = ShaderUtils
                                    .createShaderProgram( 
                                            gl, shadersFolder + "elementVS.glsl", shadersFolder + "elementFS.glsl" );

        gl.glGenVertexArrays( this.vao.length, this.vao, 0 );
        gl.glBindVertexArray( this.vao[ 0 ] );
        
    }

    public void display( GLAutoDrawable drawable ) {
        GL4 gl = (GL4) drawable.getGL();

        if( System.currentTimeMillis() < previousCallTime + 33.33 ) return;

        gl.glClear( GL4.GL_DEPTH_BUFFER_BIT );
        gl.glClear( GL4.GL_COLOR_BUFFER_BIT );

        size += increment;
        if( size < 1.0f || 30.0f < size ) increment = -increment;

        gl.glUseProgram( renderingProgram );
        gl.glPointSize( size );
        gl.glDrawArrays( GL4.GL_POINTS, 0, 1 );
        previousCallTime = System.currentTimeMillis();
    }

    public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height ) {}
    public void dispose( GLAutoDrawable drawable ) {}

    public static void main( String[] args ) {
        new Sample();
    }
}