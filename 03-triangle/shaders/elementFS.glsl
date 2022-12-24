#version 460

out vec4 color;

void drawOneColor();
void drawMultipleColors();

void main( ) { 
   //drawOneColor();
   drawMultipleColors(); 
}

void drawOneColor() {
    color = vec4( 0.0, 1.0, 0.0, 1.0 );
}

void drawMultipleColors() {
    if( gl_FragCoord.x < 365 )
        color = vec4( 1.0, 0.0, 0.0, 1.0 );
    else if( gl_FragCoord.x < 428 )
        color = vec4( 0.0, 1.0, 0.0, 1.0 );
    else
        color = vec4( 0.0, 0.0, 1.0, 1.0 );
}