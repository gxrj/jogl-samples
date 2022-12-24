package io.github.gxrj;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;

import io.github.gxrj.utils.ShaderUtils;

import javax.swing.JFrame;

class Sample extends JFrame implements GLEventListener {

    private GLJPanel glJPanel;
    
    private int renderingProgram;
    private int[] vao = new int[1];

    Sample() {
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setTitle( "External File Shaders" );
        this.setSize( 800, 600 );
        this.setLocation( 270, 65 );
        glJPanel = new GLJPanel();
        glJPanel.addGLEventListener( this );
        this.add( glJPanel );
        this.setVisible( true );
    }
    
    public void init( GLAutoDrawable drawable ) { 
        GL4 gl = (GL4) drawable.getGL();
        
        this.renderingProgram = ShaderUtils
                                    .createShaderProgram( 
                                                gl, "elementVS.glsl", "elementFS.glsl" );
        
        gl.glGenVertexArrays( this.vao.length, this.vao, 0 );
        gl.glBindVertexArray( this.vao[0] );
    }
    
    public void display( GLAutoDrawable drawable ) {
        GL4 gl = (GL4) drawable.getGL();
        
        gl.glUseProgram( this.renderingProgram );
        gl.glDrawArrays( GL4.GL_POINTS, 0, 1 );
    }
    
    public void dispose( GLAutoDrawable drawable ) {}
    public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height ) {}
    
    public static void main( String[] args ) {
        new Sample();
    }
}
