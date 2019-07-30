#version 410 core

in vec2 texCoords;

out vec4 out_color;

uniform sampler2D tex;
uniform vec3 tint;

void main(void) {
    out_color = texture(tex, texCoords);
    
    out_color *= vec4(tint, 1);
}

