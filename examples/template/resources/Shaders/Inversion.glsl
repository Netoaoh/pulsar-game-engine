#define VERTEX_SHADER_PASS
{
	layout (location = 0) in vec3 position;
	layout (location = 1) in vec2 texCoord;
	
	out VS_DATA
	{
	    vec2 texCoord;
	} vs_out;
	
	void main()
	{
	    gl_Position = vec4(position, 1.0f); 
	    vs_out.texCoord = texCoord;
	}
}
#enddef

#define FRAGMENT_SHADER_PASS
{
	in VS_DATA
	{
	    vec2 texCoord;
	} fs_in;
	
	out vec4 color;
	
	uniform sampler2D screenTexture;
	
	void main()
	{
	    color = vec4(vec3(1.0 - texture(screenTexture, fs_in.texCoord)), 1.0);
	}
}
#enddef