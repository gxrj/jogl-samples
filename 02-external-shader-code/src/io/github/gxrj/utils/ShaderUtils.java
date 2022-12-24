package io.github.gxrj.utils;

import com.jogamp.opengl.GL4;

public class ShaderUtils {
    
    public static int createShaderProgram( GL4 gl, String vertexShaderFilePath, String fragmentShaderFilePath ) {

        String[] vShaderSource = FileUtils.readShaderSource( vertexShaderFilePath );
        String[] fShaderSource = FileUtils.readShaderSource( fragmentShaderFilePath );

        int vShaderObj = gl.glCreateShader( GL4.GL_VERTEX_SHADER );
        gl.glShaderSource( vShaderObj, vShaderSource.length, vShaderSource, null, 0 );
        gl.glCompileShader( vShaderObj );

        int fShaderObj = gl.glCreateShader( GL4.GL_FRAGMENT_SHADER );
        gl.glShaderSource( fShaderObj, fShaderSource.length, fShaderSource, null, 0 );
        gl.glCompileShader( fShaderObj );

        int shaderProgram = gl.glCreateProgram();
        gl.glAttachShader( shaderProgram, vShaderObj );
        gl.glAttachShader( shaderProgram, fShaderObj );
        gl.glLinkProgram( shaderProgram );

        gl.glDeleteShader( vShaderObj );
        gl.glDeleteShader( fShaderObj );

        return shaderProgram;
    }
}
