package io.github.gxrj;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;

import javax.swing.JFrame;

class Sample extends JFrame implements GLEventListener {

    private GLJPanel glJPanel;

    Sample() {
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setTitle( "Skeleton" );
        this.setSize( 800, 600 );
        this.setLocation( 270, 65 );
        glJPanel = new GLJPanel();
        glJPanel.addGLEventListener( this );
        this.add( glJPanel );
        this.setVisible( true );
    }
    
    public void init( GLAutoDrawable drawable ) { }
    
    public void display( GLAutoDrawable drawable ) {
        GL gl = drawable.getGL();
        gl.glClear( GL.GL_COLOR_BUFFER_BIT );
    }
    
    public void dispose( GLAutoDrawable drawable ) {}
    public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height ) {}
    
    public static void main( String[] args ) {
        new Sample();
    }
}
