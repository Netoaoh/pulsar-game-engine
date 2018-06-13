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
	    vec4 textureColor = texture(screenTexture, fs_in.texCoord);
	    float average = 0.2126 * textureColor.r + 0.7152 * textureColor.g + 0.0722 * textureColor.b;
	    color = vec4(average, average, average, 1.0);
	}
}
#enddef