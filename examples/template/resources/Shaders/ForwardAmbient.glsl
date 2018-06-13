#define VERTEX_SHADER_PASS
{
	layout (location = 0) in vec3 position;
	layout (location = 1) in vec2 texCoord;
	layout (location = 2) in vec3 normal;
	
	out VS_DATA
	{
	    vec2 texCoord;
	    vec3 normal;
	} vs_out;
	
	uniform mat4 pr_matrix = mat4(1.0);
	uniform mat4 ml_matrix = mat4(1.0);
	
	uniform int numberOfRows;
	uniform vec2 texCoordOffset;
	
	void main()
	{
	    gl_Position = pr_matrix * ml_matrix * vec4(position, 1.0);
	    vs_out.texCoord = (texCoord / numberOfRows) + texCoordOffset;
	    //vs_out.texCoord = texCoord;
	    vs_out.normal = normal;
	}
}
#enddef

#define FRAGMENT_SHADER_PASS
{
	in VS_DATA
	{
	    vec2 texCoord;
	    vec3 normal;
	} fs_in;
	
	out vec4 color;
	
	uniform vec4 materialColor;
	uniform vec4 R_ambientColor;
	uniform float R_ambientIntensity;
	
	uniform sampler2D tex;
	
	void main()
	{
	    vec4 ambientLight = R_ambientIntensity * R_ambientColor;
		vec4 textureColor = texture(tex, fs_in.texCoord);

		if(textureColor.a < 0.5)
		    discard;
		
	    color = materialColor * textureColor * ambientLight;
	} 
}
#enddef