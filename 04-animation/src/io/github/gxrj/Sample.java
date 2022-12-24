package io.github.gxrj;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.Animator;

import io.github.gxrj.utils.ShaderUtils;

import javax.swing.JFrame;

class Sample extends JFrame implements GLEventListener {

    private GLJPanel glJPanel;
    
    private int renderingProgram;
    private int[] vao = new int[1];

    private float locationX = 0.0f;
    private float increment = 0.01f;

    Sample() {
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setTitle( "Animation" );
        this.setSize( 800, 600 );
        this.setLocation( 270, 65 );

        glJPanel = new GLJPanel();
        glJPanel.addGLEventListener( this );
        
        this.add( glJPanel );
        this.setVisible( true );

        //Responsible for continuous display calls over execution
        Animator animator = new Animator( glJPanel );
        animator.start();
    }
    
    public void init( GLAutoDrawable drawable ) { 
        GL4 gl = (GL4) drawable.getGL();
        
        String shadersFolder = "04-animation/shaders/";

        this.renderingProgram = ShaderUtils
                                    .createShaderProgram( 
                                                gl, shadersFolder + "elementVS.glsl", shadersFolder + "elementFS.glsl" );
        
        gl.glGenVertexArrays( this.vao.length, this.vao, 0 );
        gl.glBindVertexArray( this.vao[0] );
    }
    
    public void display( GLAutoDrawable  drawable ) {
        GL4 gl = (GL4) drawable.getGL();

        //Clears the old buffers
        gl.glClear( GL4.GL_DEPTH_BUFFER_BIT );
        gl.glClear( GL4.GL_COLOR_BUFFER_BIT );
        
        //Declares the shader program to be used by its reference
        gl.glUseProgram( this.renderingProgram );
        
        //Updates the element position
        locationX += increment;
        if( locationX > 1.0f || locationX < -1.0f ) { //Bounces when off bounds
            increment = -increment;
        }
        int offsetReference = gl.glGetUniformLocation( renderingProgram, "offset" );
        gl.glProgramUniform1f( renderingProgram, offsetReference, locationX );
        
        //Initiates the pipeline processing
        gl.glDrawArrays( GL4.GL_TRIANGLES, 0, 3 );
    }
    
    public void dispose( GLAutoDrawable drawable ) {}
    public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height ) {}
    
    public static void main( String[] args ) {
        new Sample();
    }
}
