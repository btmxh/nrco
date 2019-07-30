#version 410 core

in vec2 position;

out vec2 texCoords;

uniform vec2 offset;

void main(void) {
    gl_Position = vec4(position + offset, 0.0, 1.0);

    texCoords = (position + 1.0) / 2.0;
}
