#version 330 core
layout (location = 0) in vec2 position;
//layout (location = 1) in vec3 color;

//out vec3 ourColor;

uniform mat4 scale;
uniform mat4 universalMat1;

void main()
{
    gl_Position = scale * universalMat1 *  vec4(position, 0.0f, 1.0f);
    //    ourColor = color;
}