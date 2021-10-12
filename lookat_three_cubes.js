
var canvas;
var gl;

var NumVertices  = 84;

var points = [];
var colors = [];

var radius = 1.0;
var theta  = 0.0;
var phi    = 0.0;
var dr = 5.0 * Math.PI/180.0;


var mvMatrix;
var modelView;
var eye;

const at = vec3(0.0, 0.0, 0.0);
const up = vec3(0.0, 1.0, 0.0);

window.onload = function init()
{
    canvas = document.getElementById( "gl-canvas" );
    
    gl = WebGLUtils.setupWebGL( canvas );
    if ( !gl ) { alert( "WebGL isn't available" ); }

    gl.viewport( 0, 0, canvas.width, canvas.height );
    gl.clearColor( 1.0, 1.0, 1.0, 1.0 );
    
    gl.enable(gl.DEPTH_TEST);
	gl.enable(gl.CULL_FACE);

    //
    //  Load shaders and initialize attribute buffers
    //
    var program = initShaders( gl, "vertex-shader", "fragment-shader" );
    gl.useProgram( program );

	colorCube();

    var cBuffer = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, cBuffer );
    gl.bufferData( gl.ARRAY_BUFFER, flatten(colors), gl.STATIC_DRAW );

    var vColor = gl.getAttribLocation( program, "vColor" );
    gl.vertexAttribPointer( vColor, 4, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( vColor );

    var vBuffer = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, vBuffer );
    gl.bufferData( gl.ARRAY_BUFFER, flatten(points), gl.STATIC_DRAW );
    

    var vPosition = gl.getAttribLocation( program, "vPosition" );
    gl.vertexAttribPointer( vPosition, 4, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( vPosition );

    modelView = gl.getUniformLocation( program, "modelView" );

// buttons to change viewing parameters

    document.getElementById("Button1").onclick = function(){radius *= 1.1; render();};
    document.getElementById("Button2").onclick = function(){radius *= 0.9; render();};
    document.getElementById("Button3").onclick = function(){theta += dr; render();};
    document.getElementById("Button4").onclick = function(){theta -= dr; render();};
    document.getElementById("Button5").onclick = function(){phi += dr; render();};
    document.getElementById("Button6").onclick = function(){phi -= dr; render();};

        
    render();
}

function colorCube()
{
	
    quad(1, 0, 3, 2);
    quad(2, 3, 7, 6);
    quad(3, 0, 4, 7);
    quad(6, 5, 1, 2);
    quad(4, 5, 6, 7);
    quad(5, 4, 0, 1);

}
var vertices = [
        vec4(-0.5, -0.5, 0.5, 1.0), // 0
        vec4(-0.5, 0.5, 0.5, 1.0), // 1
        vec4(0.5, 0.5, 0.5, 1.0), // 2
        vec4(0.5, -0.5, 0.5, 1.0), // 3
        vec4(-0.5, -0.5, -0.5, 1.0), // 4 
        vec4(-0.5, 0.5, -0.5, 1.0), // 5
        vec4(0.5, 0.5, -0.5, 1.0), // 6
        vec4(0.5, -0.5, -0.5, 1.0),  // 7


];

var vertexColors = [
    [0.0, 0.0, 0.0, 1.0],  // black
    [1.0, 0.0, 0.0, 1.0],  // red
    [1.0, 1.0, 0.0, 1.0],  // yellow
    [0.0, 1.0, 0.0, 1.0],  // green
    [0.0, 0.0, 1.0, 1.0],  // blue
    [1.0, 0.0, 1.0, 1.0],  // magenta
    [0.0, 1.0, 1.0, 1.0],  // cyan
    [1.0, 1.0, 1.0, 1.0]   // white
];
function quad(a, b, c, d) 
{
    

    // We need to parition the quad into two triangles in order for
    // WebGL to be able to render it.  In this case, we create two
    // triangles from the quad indices
    
    //vertex color assigned by the index of the vertex
    
    points.push(vertices[a]);
    colors.push(vertexColors[a]);
    points.push(vertices[b]);
    colors.push(vertexColors[a]);
    points.push(vertices[c]);
    colors.push(vertexColors[a]);
    points.push(vertices[a]);
    colors.push(vertexColors[a]);
    points.push(vertices[c]);
    colors.push(vertexColors[a]);
    points.push(vertices[d]);
    colors.push(vertexColors[a]);
}

function render()
{
    gl.clear( gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);
    eye = vec3(radius*Math.cos(theta)*Math.sin(phi), radius*Math.sin(theta),
             radius*Math.cos(theta)*Math.cos(phi)); // eye point
	mvMatrix = lookAt(eye, at , up);
	gl.uniformMatrix4fv( modelView, false, flatten(mvMatrix) )
    gl.drawArrays( gl.TRIANGLES, 0, NumVertices );
    requestAnimFrame( render );
}

