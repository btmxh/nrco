#version 410 core

in vec2 position;

out vec2 texCoords;

uniform vec4 bounds;
uniform vec2 offset;

void main(void) {
    float x = (bounds.z - bounds.x) / 2 * position.x + (bounds.z + bounds.x) / 2;
    float y = (bounds.w - bounds.y) / 2 * position.y + (bounds.w + bounds.y) / 2;
    gl_Position = vec4(x + offset.x, y + offset.y, 0.0, 1.0);

    texCoords = (position + 1.0) / 2.0;
    texCoords.y = 1 - texCoords.y;
}
