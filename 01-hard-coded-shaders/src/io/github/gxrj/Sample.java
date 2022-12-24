package io.github.gxrj;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;

import javax.swing.JFrame;

class Sample extends JFrame implements GLEventListener {

    private GLJPanel glJPanel;
    
    private int renderingProgram;
    private int[] vao = new int[1];

    Sample() {
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setTitle( "Hardcoded Shaders" );
        this.setSize( 800, 600 );
        this.setLocation( 270, 65 );
        glJPanel = new GLJPanel();
        glJPanel.addGLEventListener( this );
        this.add( glJPanel );
        this.setVisible( true );
    }
    
    public void init( GLAutoDrawable drawable ) { 
        GL4 gl = (GL4) drawable.getGL();
        
        this.renderingProgram = createShaderProgram();
        
        gl.glGenVertexArrays( this.vao.length, this.vao, 0 );
        gl.glBindVertexArray( this.vao[0] );
    }
    
    public void display( GLAutoDrawable drawable ) {
        GL4 gl = (GL4) drawable.getGL();
        
        gl.glUseProgram( this.renderingProgram );
        gl.glDrawArrays( GL4.GL_POINTS, 0, 1 );
    }
    
    private int createShaderProgram() {
        GL4 gl = (GL4) GLContext.getCurrentGL();
    
        String[] vShaderSourceCode = {
        "#version 460\n", 
        "void main() {\n", 
            "gl_Position = vec4(0.0,0.0,0.0,1.0);\n", 
        "}"
        };
        
        String[] fShaderSourceCode = {
        "#version 460\n", 
        "out vec4 color;\n", 
        "void main() {\n", 
            "color = vec4(0.0,1.0,0.0,1.0);\n",
        "}"
        };
        
        int vShaderObj = gl.glCreateShader( GL4.GL_VERTEX_SHADER );
        gl.glShaderSource( vShaderObj, vShaderSourceCode.length, vShaderSourceCode, null, 0 );
        gl.glCompileShader( vShaderObj );
        
        int fShaderObj = gl.glCreateShader( GL4.GL_FRAGMENT_SHADER );
        gl.glShaderSource( fShaderObj, fShaderSourceCode.length, fShaderSourceCode, null, 0 );
        gl.glCompileShader( fShaderObj );
        
        int shaderProgram = gl.glCreateProgram();
        gl.glAttachShader( shaderProgram, vShaderObj );
        gl.glAttachShader( shaderProgram, fShaderObj );
        gl.glLinkProgram( shaderProgram );
        
        gl.glDeleteShader( vShaderObj );
        gl.glDeleteShader( fShaderObj );
        
        return shaderProgram;
    }
    
    public void dispose( GLAutoDrawable drawable ) {}
    public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height ) {}
    
    public static void main( String[] args ) {
        new Sample();
    }
}
