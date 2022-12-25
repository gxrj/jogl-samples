#version 460

out vec4 color;
uniform float offset;

void drawOneColor();
void drawMultipleStaticColors();
void drawMultipleDynamicColors();
float getColorDelimiter( float );

void main( ) { 
   //drawOneColor();
   //drawMultipleStaticColors();
   drawMultipleDynamicColors();
}

void drawOneColor() {
    color = vec4( 0.0, 1.0, 0.0, 1.0 );
}

void drawMultipleStaticColors() {
    if( gl_FragCoord.x < 365 )
        color = vec4( 1.0, 0.0, 0.0, 1.0 );
    else if( gl_FragCoord.x < 428 )
        color = vec4( 0.0, 1.0, 0.0, 1.0 );
    else
        color = vec4( 0.0, 0.0, 1.0, 1.0 );
}

void drawMultipleDynamicColors() {
    if( gl_FragCoord.x < getColorDelimiter( offset ) )
        color = vec4( 1.0, 0.0, 0.0, 1.0 );
    else if( gl_FragCoord.x < getColorDelimiter( offset ) + 50 )
        color = vec4( 0.0, 1.0, 0.0, 1.0 );
    else
        color = vec4( 0.0, 0.0, 1.0, 1.0 );
}

float getColorDelimiter( float offset ) {
    return offset*400 + 370;
}